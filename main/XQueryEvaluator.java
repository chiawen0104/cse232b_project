package main;
import org.antlr.v4.runtime.tree.ParseTree;
import main.antlr.XPathParser;
import org.w3c.dom.*;
import java.util.*;

class XQueryContext {
    Node contextItem;
    Map<String, List<Node>> env;

    XQueryContext() { this.env = new HashMap<>(); }
    XQueryContext(Node contextItem, Map<String, List<Node>> env) {
        this.contextItem = contextItem;
        this.env = env;
    }
}

public class XQueryEvaluator {
 
    public static List<Node> evaluate(ParseTree t, XQueryContext ctx, Document doc, String xmlFilePath) throws Exception {
        return evaluateXQ(t, ctx, doc, xmlFilePath);
    }
 
    // -----------------------------------------------------------------------
    // Helper: collect all element descendants (same as XPathEvaluator's version)
    // -----------------------------------------------------------------------
    static List<Node> recurrDescendant(Node node, List<Node> listOfNodes) {
        NodeList kids = node.getChildNodes();
        for (int i = 0; i < kids.getLength(); i++) {
            Node c = kids.item(i);
            if (c.getNodeType() == Node.ELEMENT_NODE) {
                listOfNodes.add(c);
                recurrDescendant(c, listOfNodes);
            }
        }
        return listOfNodes;
    }
 
    // -----------------------------------------------------------------------
    // Main XQ evaluator
    // -----------------------------------------------------------------------
    private static List<Node> evaluateXQ(ParseTree t, XQueryContext context, Document doc, String xmlFilePath) throws Exception {
 
        // Guard: terminal node has no children → nothing to evaluate
        if (t.getChildCount() == 0) return new ArrayList<>();
 
        // ── 1-child cases ──────────────────────────────────────────────────
        if (t.getChildCount() == 1) {
            String text = t.getChild(0).getText();
 
            // STRING literal  →  text node
            if ((text.startsWith("\"") && text.endsWith("\""))
             || (text.startsWith("'")  && text.endsWith("'"))) {
                String s = text.substring(1, text.length() - 1);
                return new ArrayList<>(List.of(doc.createTextNode(s)));
            }
 
            // ap  →  delegate to XPathEvaluator
            if (t.getChild(0) instanceof XPathParser.ApContext) {
                return XPathEvaluator.evaluateAP(t.getChild(0), xmlFilePath);
            }
 
            // VAR  →  look up in environment
            if (context.env.containsKey(text)) {
                return new ArrayList<>(context.env.get(text));
            }
            return new ArrayList<>();
        }
 
        // ── 3-child cases ──────────────────────────────────────────────────
        if (t.getChildCount() == 3) {
 
            // BUG 1 FIX: ( xq )
            if ("(".equals(t.getChild(0).getText()) && ")".equals(t.getChild(2).getText())) {
                return evaluateXQ(t.getChild(1), context, doc, xmlFilePath);
            }
 
            // xq , xq
            if (",".equals(t.getChild(1).getText())) {
                List<Node> result = new ArrayList<>();
                result.addAll(evaluateXQ(t.getChild(0), context, doc, xmlFilePath));
                result.addAll(evaluateXQ(t.getChild(2), context, doc, xmlFilePath));
                return result;
            }
 
            // xq / rp
            if ("/".equals(t.getChild(1).getText())) {
                List<Node> xqResults = evaluateXQ(t.getChild(0), context, doc, xmlFilePath);
                List<Node> result = new ArrayList<>();
                for (Node n : xqResults) {
                    for (Node r : XPathEvaluator.evaluateRP(t.getChild(2), n)) {
                        if (!result.contains(r)) result.add(r);
                    }
                }
                return result;
            }
 
            // xq // rp
            if ("//".equals(t.getChild(1).getText())) {
                List<Node> xqResults = evaluateXQ(t.getChild(0), context, doc, xmlFilePath);
                List<Node> result = new ArrayList<>();
                for (Node n : xqResults) {
                    // self level
                    for (Node r : XPathEvaluator.evaluateRP(t.getChild(2), n)) {
                        if (!result.contains(r)) result.add(r);
                    }
                    // descendants
                    List<Node> desc = recurrDescendant(n, new ArrayList<>());
                    for (Node d : desc) {
                        for (Node r : XPathEvaluator.evaluateRP(t.getChild(2), d)) {
                            if (!result.contains(r)) result.add(r);
                        }
                    }
                }
                return result;
            }
        }
 
        // ── Element construction: < TAGNAME > { xq } </ TAGNAME >  (10 children) ──
        // BUG 2 FIX: match on '<' and '>' tokens, not '(' and ')'
        if (t.getChildCount() == 10
                && "<".equals(t.getChild(0).getText())
                && ">".equals(t.getChild(2).getText())
                && "{".equals(t.getChild(3).getText())
                && "}".equals(t.getChild(5).getText())) {
            String tagName = t.getChild(1).getText();
            Element newElem = doc.createElement(tagName);
            for (Node child : evaluateXQ(t.getChild(4), context, doc, xmlFilePath)) {
                newElem.appendChild(doc.importNode(child, true));
            }
            return List.of(newElem);
        }
 
        // ── FLWOR: forClause letClause? whereClause? returnClause ──────────
        if (t.getChild(0) instanceof XPathParser.ForClauseContext) {
            ParseTree forClause    = t.getChild(0);
            ParseTree letClause    = null;
            ParseTree whereClause  = null;
            ParseTree returnClause = null;
 
            for (int i = 1; i < t.getChildCount(); i++) {
                ParseTree child = t.getChild(i);
                if (child instanceof XPathParser.LetClauseContext)    letClause    = child;
                else if (child instanceof XPathParser.WhereClauseContext) whereClause  = child;
                else if (child instanceof XPathParser.ReturnClauseContext) returnClause = child;
            }
 
            // Collect for-bindings (skip commas)
            List<ParseTree> bindings = new ArrayList<>();
            for (int i = 1; i < forClause.getChildCount(); i++) {
                ParseTree child = forClause.getChild(i);
                if (!",".equals(child.getText())) bindings.add(child);
            }
 
            return evaluateForRecursive(bindings, 0, context, letClause, whereClause, returnClause, doc, xmlFilePath);
        }
 
        // ── letClause xq ────────────────────────────────────────────────────
        if (t.getChild(0) instanceof XPathParser.LetClauseContext) {
            ParseTree letClause = t.getChild(0);
            // BUG 3 FIX: copy env instead of aliasing it
            Map<String, List<Node>> newEnv = new HashMap<>(context.env);
            XQueryContext current = new XQueryContext(context.contextItem, newEnv);
 
            for (int i = 1; i < letClause.getChildCount(); i++) {
                ParseTree child = letClause.getChild(i);
                if (",".equals(child.getText())) continue;
                if (child.getChildCount() >= 3) {
                    String varName = child.getChild(0).getText();
                    List<Node> value = evaluateXQ(child.getChild(2), current, doc, xmlFilePath);
                    newEnv.put(varName, value);
                }
            }
            return evaluateXQ(t.getChild(1), current, doc, xmlFilePath);
        }
 
        return new ArrayList<>();
    }
 
    // -----------------------------------------------------------------------
    // FLWOR recursive helper
    // -----------------------------------------------------------------------
    private static List<Node> evaluateForRecursive(
            List<ParseTree> bindings, int index,
            XQueryContext context,
            ParseTree letClause, ParseTree whereClause, ParseTree returnClause,
            Document doc, String xmlFilePath) throws Exception {
 
        // Base case: all for-variables assigned
        if (index == bindings.size()) {
            XQueryContext current = context;
 
            // Apply let-bindings (if any)
            if (letClause != null) {
                // BUG 3 FIX: proper copy, not alias
                Map<String, List<Node>> newEnv = new HashMap<>(context.env);
                XQueryContext focus = new XQueryContext(context.contextItem, newEnv);
                for (int i = 1; i < letClause.getChildCount(); i++) {
                    ParseTree child = letClause.getChild(i);
                    if (",".equals(child.getText())) continue;
                    if (child.getChildCount() >= 3) {
                        String varName = child.getChild(0).getText();
                        List<Node> value = evaluateXQ(child.getChild(2), focus, doc, xmlFilePath);
                        newEnv.put(varName, value);
                    }
                }
                current = focus;
            }
 
            // Apply where-filter (if any)
            if (whereClause != null && !evaluateCOND(whereClause.getChild(1), current, doc, xmlFilePath)) {
                return new ArrayList<>();
            }
 
            // Evaluate return
            return evaluateXQ(returnClause.getChild(1), current, doc, xmlFilePath);
        }
 
        // Recursive case: bind next for-variable
        ParseTree binding = bindings.get(index);
        String varName = binding.getChild(0).getText();   // VAR
        ParseTree xqExpr = binding.getChild(2);           // xq  (skip 'in' at index 1)
 
        List<Node> values = evaluateXQ(xqExpr, context, doc, xmlFilePath);
        List<Node> results = new ArrayList<>();
 
        for (Node v : values) {
            Map<String, List<Node>> newEnv = new HashMap<>(context.env);
            newEnv.put(varName, List.of(v));
            XQueryContext next = new XQueryContext(context.contextItem, newEnv);
            results.addAll(evaluateForRecursive(bindings, index + 1, next,
                    letClause, whereClause, returnClause, doc, xmlFilePath));
        }
        return results;
    }
 
    // -----------------------------------------------------------------------
    // Condition evaluator
    // -----------------------------------------------------------------------
    private static boolean evaluateCOND(ParseTree t, XQueryContext context, Document doc, String xmlFilePath) throws Exception {
 
        if (t.getChildCount() == 3) {
            String op = t.getChild(1).getText();
 
            // value equality
            if ("=".equals(op) || "eq".equals(op)) {
                List<Node> l = evaluateXQ(t.getChild(0), context, doc, xmlFilePath);
                List<Node> r = evaluateXQ(t.getChild(2), context, doc, xmlFilePath);
                for (Node n1 : l)
                    for (Node n2 : r)
                        if (n1.getTextContent().equals(n2.getTextContent())) return true;
                return false;
            }
 
            // identity equality
            if ("==".equals(op) || "is".equals(op)) {
                List<Node> l = evaluateXQ(t.getChild(0), context, doc, xmlFilePath);
                List<Node> r = evaluateXQ(t.getChild(2), context, doc, xmlFilePath);
                for (Node n1 : l)
                    for (Node n2 : r)
                        if (n1.isSameNode(n2)) return true;
                return false;
            }
 
            // ( cond )
            if ("(".equals(t.getChild(0).getText()) && ")".equals(t.getChild(2).getText())) {
                return evaluateCOND(t.getChild(1), context, doc, xmlFilePath);
            }
 
            // cond and cond
            if ("and".equals(op)) {
                return evaluateCOND(t.getChild(0), context, doc, xmlFilePath)
                    && evaluateCOND(t.getChild(2), context, doc, xmlFilePath);
            }
 
            // cond or cond
            if ("or".equals(op)) {
                return evaluateCOND(t.getChild(0), context, doc, xmlFilePath)
                    || evaluateCOND(t.getChild(2), context, doc, xmlFilePath);
            }
        }
 
        // not cond  (2 children)
        if (t.getChildCount() == 2 && "not".equals(t.getChild(0).getText())) {
            return !evaluateCOND(t.getChild(1), context, doc, xmlFilePath);
        }
 
        // empty( xq )  and  some … satisfies …
        if (t.getChildCount() >= 4) {
            if ("empty".equals(t.getChild(0).getText())) {
                return evaluateXQ(t.getChild(2), context, doc, xmlFilePath).isEmpty();
            }
 
            if ("some".equals(t.getChild(0).getText())) {
                List<ParseTree> bindings = new ArrayList<>();
                int i = 1;
                while (!"satisfies".equals(t.getChild(i).getText())) {
                    if (!",".equals(t.getChild(i).getText())) bindings.add(t.getChild(i));
                    i++;
                }
                ParseTree condClause = t.getChild(i + 1);
                return evaluateSomeRecursive(bindings, 0, context, condClause, doc, xmlFilePath);
            }
        }
 
        return false;
    }
 
    // -----------------------------------------------------------------------
    // 'some' recursive helper
    // -----------------------------------------------------------------------
    private static boolean evaluateSomeRecursive(
            List<ParseTree> bindings, int index,
            XQueryContext context,
            ParseTree condClause,
            Document doc, String xmlFilePath) throws Exception {
 
        if (index == bindings.size()) {
            return evaluateCOND(condClause, context, doc, xmlFilePath);
        }
 
        ParseTree binding = bindings.get(index);
        String varName = binding.getChild(0).getText();
        ParseTree xqExpr = binding.getChild(2);
 
        for (Node v : evaluateXQ(xqExpr, context, doc, xmlFilePath)) {
            Map<String, List<Node>> newEnv = new HashMap<>(context.env);
            newEnv.put(varName, List.of(v));
            XQueryContext next = new XQueryContext(context.contextItem, newEnv);
            if (evaluateSomeRecursive(bindings, index + 1, next, condClause, doc, xmlFilePath))
                return true;
        }
        return false;
    }
}