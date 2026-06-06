package main;

import main.antlr.XPathParser;

import org.antlr.v4.misc.Graph;
import org.antlr.v4.runtime.tree.*;
import org.w3c.dom.*;

import java.io.File;
import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Rewrites a FLWR query into an equivalent query using explicit join() calls.
 *
 * join(xq1, xq2, [vars1], [vars2]) semantics:
 *   - evaluates xq1 and xq2 independently
 *   - returns all tuples t from xq1 , xq2 where t/vars1[i] eq t/vars2[i] for all i
 *
 * Algorithm:
 *  1. Build a dependency graph over the for-variables.
 *  2. Find connected components (variable groups).
 *  3. Partition where-conditions into:
 *       a) intra-group filters (stay inside the sub-query)
 *       b) inter-group equi-joins (become join attributes)
 *  4. Emit one sub-query per group that returns a <tuple> of relevant vars.
 *  5. Build a tree of join() calls over those sub-queries.
 *  6. Rewrite the return clause in terms of $tuple/varName paths.
 */
public class QueryRewriter {
    private static void dfsAssign(String var, int gid,
        Map<String, List<String>> adjList,Map<String, Integer> varToGroup,Set<String> walked) {
        varToGroup.put(var, gid);
        walked.add(var);
        // outgoing: vars this one depends on  ($a to $b)
        for (String dep : adjList.get(var)) {
            if (!walked.contains(dep)) {
                dfsAssign(dep, gid, adjList, varToGroup, walked);
            }
        }
        //incoming: vars that depend on this one  ($b pulls in $y when $y to $b)
        for (Map.Entry<String, List<String>> entry : adjList.entrySet()) {
            if (!walked.contains(entry.getKey()) && entry.getValue().contains(var)) {
                dfsAssign(entry.getKey(), gid, adjList, varToGroup, walked);
            }
        }
    }

    /**
     * Rewrite a parsed xq tree. Returns the rewritten query as a string,
     * or null if the tree is not a rewritable FLWR expression.
     */
    public static String rewrite(ParseTree xqTree) {
        try {
            return buildRewrittenQuery(xqTree);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // private static List<Node> evaluateForRecursive(List<ParseTree> bindings,int index,
    //     ParseTree whereClause,ParseTree returnClause) throws Exception {
    //     //it's the base case
    //     //all for-variables have been assigned one node each
    //     if (index == bindings.size()) {
    //     }
    //     //recursive case
    //     else{
    //         //varRepeat: VAR 'in' path;
    //         //bind the 
    //         Graph group;
    //         ParseTree binding = bindings.get(index);
    //         String varName =binding.getChild(0).getText();
    //         String checking=binding.getChild(2).getText();
    //         if (checking instanceof XPathParser.VAR){
    //             group[varName]


            
    //         }

    //         return results;
    //     }
    // }
    private static String buildSubQuery(int gid,List<ParseTree> bindings,List<String> whereConditions,
    List<String> exposedVars) {
        List<String> strings=new ArrayList<>();
        strings.add("for ");
        for (int i = 0; i < bindings.size(); i++) {
            if (i > 0) strings.add(",\n    ");
            ParseTree b = bindings.get(i);
            strings.add(b.getChild(0).getText() + " in " + b.getChild(2).getText());
        }
        if (!whereConditions.isEmpty()) {
            strings.add("\nwhere ");
            strings.add(String.join(" and ", whereConditions));
        }
        strings.add("\nreturn <tuple> {\n");
        for (int i = 0; i < exposedVars.size(); i++) {
            String v = exposedVars.get(i);
            String tag = v.substring(1);
            strings.add("  <" + tag + ">{" + v + "}</" + tag + ">");
            if (i < exposedVars.size() - 1) {
                strings.add(",\n");
            } else {
                strings.add("\n");
            }
        }
        strings.add("}\n</tuple>");
        return String.join("", strings);

    }
    private static String buildRewrittenQuery(ParseTree t) throws Exception {
        List<ParseTree> bindings = new ArrayList<>();
        Map<String, List<String>> adjList = new LinkedHashMap<>();
        ParseTree whereClause = null;
        ParseTree returnClause = null;

        for (int i = 0; i < t.getChildCount(); i++) {
            ParseTree child = t.getChild(i);
            if (child instanceof XPathParser.VarRepeat3Context) {
                String varName  = child.getChild(0).getText();
                String pathText = child.getChild(2).getText();
                adjList.put(varName, new ArrayList<>());
                if (pathText.startsWith("$")) {
                    int slash = pathText.indexOf('/');
                    adjList.get(varName).add(pathText.substring(0, slash));
                }
                bindings.add(child);
            } else if (child instanceof XPathParser.Cond2Context) {
                whereClause = child;
            } else if (child instanceof XPathParser.ReturnexprContext) {
                returnClause = child;
            }
        }
        // Grouping from for-dependency graph
        Map<String, Integer> varToGroup = new LinkedHashMap<>();
        int nextGroupId = 0;
        for (String var : adjList.keySet()) {
            if (!varToGroup.containsKey(var)) {
                dfsAssign(var, nextGroupId, adjList, varToGroup, new HashSet<String>());
                nextGroupId++;
            }
        }
        Map<Integer, List<String>> returnVarList = new HashMap<>();
        Map<Integer, List<ParseTree>> groupBindings = new LinkedHashMap<>();
        for (int gid : new HashSet<>(varToGroup.values())) {
            returnVarList.put(gid, new ArrayList<>());
            groupBindings.put(gid, new ArrayList<>());
        }
        String returnText = returnClause.getText();
        for (ParseTree binding : bindings) {
            String varName = binding.getChild(0).getText();   // "$a"
            int gid = varToGroup.get(varName);
            String tag = varName.substring(1);
            groupBindings.get(gid).add(binding);
            returnVarList.get(gid).add(varName);
            returnText = returnText.replace(varName, "$tuple/" + tag + "/*");
        }
        //evalute the for clause for each subtree, each different sub-bindings
        // for (Map.Entry<Integer, List<ParseTree>> entry : groupBindings.entrySet()) {
        //     int gid = entry.getKey();
        //     List<ParseTree> subBindings = entry.getValue();
        //     evaluateForRecursive(subBindings, 0, whereClause, returnClause);
        // }
        Map<Integer, List<String>> intraWhere = new HashMap<>();  
        //we need to check the intra group connections cause they are for join
        List<String[]> joinConds = new ArrayList<>();
        // initialize empty lists for each group
        for (int gid : groupBindings.keySet()) {
            intraWhere.put(gid, new ArrayList<>());
        }
        List<ParseTree> whereLeaves = new ArrayList<>();
        Deque<ParseTree> stack = new ArrayDeque<>();
        stack.push(whereClause);
        while (!stack.isEmpty()) {
            ParseTree cond = stack.pop();
            if (cond.getChildCount() == 3 && "and".equals(cond.getChild(1).getText())) {
                stack.push(cond.getChild(2));
                stack.push(cond.getChild(0));
            } else {
                // leaf condition: VAR 'eq' VAR  or  VAR 'eq' STRING
                String lhs = cond.getChild(0).getText();
                String rhs = cond.getChild(2).getText();

                Integer lhsGroup = varToGroup.get(lhs);
                Integer rhsGroup = varToGroup.get(rhs);   
                // null if rhs is STRING, or the same group of elements
                if (rhsGroup == null || lhsGroup.equals(rhsGroup)) {
                    // intra-group
                    // intraWhere.get(lhsGroup).add(cond.getText());
                    intraWhere.get(lhsGroup).add(lhs +" eq "+rhs);
                } else {
                    // cross-group join condition
                    //append Left side of eq in the query (parse tree child 0)
                    //append Right side of eq in the query (parse tree child 2)
                    //append Group id of left side of eq
                    //append Group id of right side of eq
                    joinConds.add(new String[]{lhs, rhs,String.valueOf(lhsGroup),String.valueOf(rhsGroup)});
                }
            }
        }
        // Group order = order of first appearance in the original for-clause
        List<Integer> gidOrder = new ArrayList<>();
        Set<Integer> seenGids = new HashSet<>();
        for (ParseTree binding : bindings) {
            int gid = varToGroup.get(binding.getChild(0).getText());
            if (!seenGids.contains(gid)) {
                gidOrder.add(gid);
                seenGids.add(gid);
            }
        }

        List<String> subQueries = new ArrayList<>();
        for (int gid : gidOrder) {
            List<ParseTree> subBindings = groupBindings.get(gid);
            List<String> subWhereCondition = intraWhere.get(gid);
            List<String> subReturnVarList = returnVarList.get(gid);
            subQueries.add(buildSubQuery(gid,subBindings, subWhereCondition, subReturnVarList));
        }
        // Only one variable group means there is no cross-group join to
        // introduce so the query needs no rewriting, evaluates the original query as-is.
        if (subQueries.size() == 1) {
            return null;
        }
        String result = subQueries.get(0);
        for (int k = 1; k < subQueries.size(); k++) {
            List<String> leftAttrs = new ArrayList<>();
            List<String> rightAttrs = new ArrayList<>();
            // The left operand is the join of ALL groups joined so far, not just
            // the immediate predecessor. Its merged tuple carries every original
            // <tag> child, so a join condition to any earlier group is addressable.
            // Attribute lists stay consistent with argument order: left operand is
            // always the accumulated join, right operand is always the new group.
            Set<Integer> leftGids = new HashSet<>(gidOrder.subList(0, k));
            int rightGid = gidOrder.get(k);
            for (String[] jc : joinConds) {
                int jLG = Integer.parseInt(jc[2]);
                int jRG = Integer.parseInt(jc[3]);
                if (leftGids.contains(jLG) && jRG == rightGid) {
                    leftAttrs.add(jc[0].substring(1));
                    rightAttrs.add(jc[1].substring(1));
                } else if (leftGids.contains(jRG) && jLG == rightGid) {
                    leftAttrs.add(jc[1].substring(1));
                    rightAttrs.add(jc[0].substring(1));
                }
            }
            result = "join(\n" + result + ",\n" + subQueries.get(k) + ",\n"
                    + "[" + String.join(", ", leftAttrs) + "],\n"
                    + "[" + String.join(", ", rightAttrs) + "]\n" + ")";
        }
        //returnClause doesn't do the tuple/tag/*
        result ="for $tuple in " + result + "\nreturn " +returnText;
        return result;
    }
    // private static List<Node> evaluateReturnExpr(ParseTree t,XQueryContext ctx, 
    //     Document doc, String xmlFilePath) throws Exception {
    //     if(t.getChildCount()==1){
    //         String text = t.getChild(0).getText();
    //         if (text.startsWith("$")) {
    //             //VAR
    //             return new ArrayList<>(ctx.env.get(text));
    //         } else {
    //             //path
    //             return evaluatePATH(t.getChild(0), ctx);
    //         }
    //     }
    //     else{
    //         if("<".equals(t.getChild(0).getText())){
    //             String tagName = t.getChild(1).getText();
    //             Element elem = doc.createElement(tagName);
    //             // '<' TAGNAME '>' '{' returnexpr '}' '</' TAGNAME '>'  (childCount==8)
    //             // '<' TAGNAME '>' returnexpr '</' TAGNAME '>'(childCount==7)
    //             int innerIdx;
    //             if(t.getChildCount() == 8){
    //                 innerIdx=4;
    //             }
    //             else{
    //                 innerIdx=3;
    //             }
    //             List<Node> returnEvaluate = evaluateReturnExpr(t.getChild(innerIdx), ctx, doc, xmlFilePath);
    //             for (Node n : returnEvaluate) {
    //                 elem.appendChild(doc.importNode(n, true));
    //             }
    //             return List.of(elem);
    //         }
    //         else{
    //             //returnexpr ',' returnexpr
    //             List<Node> result = new ArrayList<>(evaluateReturnExpr(t.getChild(0), ctx, doc, xmlFilePath));
    //             result.addAll(evaluateReturnExpr(t.getChild(2), ctx, doc, xmlFilePath));
    //             return result;
    //         }

    //     }

    // }

}