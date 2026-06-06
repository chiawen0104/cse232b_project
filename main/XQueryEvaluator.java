package main;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import main.antlr.XPathParser;
import main.antlr.XPathParser.ForClauseContext;

import org.antlr.v4.runtime.tree.ParseTree;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
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
    static List<Node> recurrDescendant(Node node, List<Node> listOfNodes) {
            NodeList kids = node.getChildNodes();
            for(int i = 0; i < kids.getLength(); i++) {
                Node c = kids.item(i);
                if(c.getNodeType() == Node.ELEMENT_NODE) {
                    listOfNodes.add(c);
                    recurrDescendant(c, listOfNodes);
                }
            }
            return listOfNodes;
    }
    private static Boolean evaluateSomeRecursive(List<ParseTree> bindings,int index,
        XQueryContext context,ParseTree condClause,Document doc,String xmlFilePath) throws Exception {
        if (index == bindings.size()) {
            return evaluateCOND(condClause, context, doc, xmlFilePath);
        }
        //varRepeat: VAR 'in' xq;
        //bind the 
        ParseTree binding = bindings.get(index);
        String varName =binding.getChild(0).getText();
        ParseTree xqExpr =binding.getChild(2);
        List<Node> values =evaluateXQ(xqExpr,context,doc,xmlFilePath);
        List<Node> results =new ArrayList<>();
        //evaluate one node at a time
        for (Node v : values) {
            Map<String, List<Node>> newEnv = new HashMap<>(context.env);
            // v already bound from for-each
            //Ci = {Vari vi} Ci-1
            newEnv.put(varName, List.of(v));
            //we are only update the environment
            XQueryContext next = new XQueryContext(context.contextItem,newEnv);
            //move to the next for-variable with the updated context.
            if (evaluateSomeRecursive(bindings,index + 1,next,condClause,doc,xmlFilePath)) {
                return true;
            }  
        }
        return false;
    }
    private static List<Node> evaluateForRecursive(List<ParseTree> bindings,int index,
        XQueryContext context,ParseTree letClause,ParseTree whereClause,ParseTree returnClause,
        Document doc,String xmlFilePath) throws Exception {
        //it's the base case
        //all for-variables have been assigned one node each
        if (index == bindings.size()) {
            XQueryContext current = context;
            if (letClause != null) {
                // Map<String, List<Node>> newEnv=new HashMap<>();
                // newEnv=context.env;
                Map<String, List<Node>> newEnv = new HashMap<>(context.env);
                XQueryContext focus =new XQueryContext(context.contextItem,newEnv);
                for (int i = 1; i < letClause.getChildCount(); i++) {
                    ParseTree child = letClause.getChild(i);
                    // ignore commas
                    if (",".equals(child.getText())) {
                        continue;
                    }
                    if (child.getChildCount() >= 3) {
                        String varName =child.getChild(0).getText();
                        ParseTree xqExpr =child.getChild(2);
                        //evaluate using PREVIOUS context Ci-1
                        List<Node> value =evaluateXQ(xqExpr, focus, doc, xmlFilePath);
                        //create Ci from Ci-1
                        newEnv.put(varName, value);
                    }
                }
                current = focus;
            }
            if (whereClause != null &&!evaluateCOND(whereClause.getChild(1),current,doc,xmlFilePath)) {
                return new ArrayList<>();
            }
            return evaluateXQ(returnClause.getChild(1),current,doc,xmlFilePath);
        }
        //recursive case
        else{
            // VAR in xq or VAR in path
            //bind the 
            ParseTree binding = bindings.get(index);
            String varName = binding.getChild(0).getText();
            List<Node> values = evaluateBinding(binding, context, doc, xmlFilePath);
            List<Node> results =new ArrayList<>();
            //evaluate one node at a time
            for (Node v : values) {
                Map<String, List<Node>> newEnv = new HashMap<>(context.env);
                // v already bound from for-each
                //Ci = {Vari vi} Ci-1
                newEnv.put(varName, List.of(v));
                //we are only update the environment
                XQueryContext next = new XQueryContext(context.contextItem,newEnv);
                //move to the next for-variable with the updated context.
                List<Node> partial =evaluateForRecursive(bindings,index + 1,next,letClause,whereClause,returnClause,doc,xmlFilePath);
                results.addAll(partial);
            }

            return results;
        }
    }

    /** Evaluate `VAR in xq` or `VAR in path` from a for-clause binding. */
    private static List<Node> evaluateBinding(ParseTree binding, XQueryContext context,Document doc, String xmlFilePath)throws Exception {
        if(binding.getChild(2) instanceof XPathParser.XqContext){
            return evaluateXQ(binding.getChild(2), context, doc, xmlFilePath);
        }
        else if(binding.getChild(2) instanceof XPathParser.PathContext){
            return evaluatePATH(binding.getChild(2), context,xmlFilePath);
        }
        return new ArrayList<>();
    }

    private static List<Node> evaluateXQ(ParseTree t,XQueryContext context,Document doc,String xmlFilePath) throws Exception {
        if(t.getChildCount()== 1){
            //STRING
            if((t.getChild(0).getText().startsWith("\"") && t.getChild(0).getText().endsWith("\"") || t.getChild(0).getText().startsWith("'") && t.getChild(0).getText().endsWith("'"))) {
                String string =t.getChild(0).getText();
                String s = string.substring(1, string.length() - 1);
                Text textNode = doc.createTextNode(s);

                return new ArrayList<>(List.of(textNode));
            }
            //ap
            else if(t.getChild(0) instanceof XPathParser.ApContext) {
                List<Node> rpResults=XPathEvaluator.evaluateAP(t.getChild(0), xmlFilePath);
                return rpResults;
            }
            //joinExpr
            else if (t.getChild(0) instanceof XPathParser.JoinExprContext) {
                return evaluateJoinExpr((XPathParser.JoinExprContext) t.getChild(0), doc, xmlFilePath);
            }
            //VAR
            else if (t.getChild(0).getText().startsWith("$")) {
                String var = t.getChild(0).getText();
                List<Node> value = context.env.get(var);
                if (context.env.containsKey(var)) {
                    return new ArrayList<>(context.env.get(var));
                }
                else {
                    return new ArrayList<>();

                }
                // List<Node> nodes = new ArrayList<>();
                // NodeList kids = context.getChildNodes();
                // for (int i=0; i<kids.getLength();i++) {
                //     Node c=kids.item(i);
                //     if (c.getNodeType()== Node.ELEMENT_NODE &&((Element)c).getName().equals(var)) {
                //         nodes.add(c);
                //     }
                // }
                // return nodes;
            } else {
                return new ArrayList<>();
            }
        }
        // forClause letClause? whereClause? returnClause
        else if( t.getChild(0) instanceof XPathParser.ForClauseContext){
             ParseTree forClause = t.getChild(0);
                ParseTree letClause=null;
                ParseTree whereClause=null;
                ParseTree returnClause=null;
                for (int i = 1; i< t.getChildCount(); i++) {

                    if (t.getChild(i) instanceof XPathParser.LetClauseContext){
                        letClause =t.getChild(i);
                    }
                    else if(t.getChild(i) instanceof XPathParser.WhereClauseContext){
                        whereClause=t.getChild(i);
                    }
                    else if (t.getChild(i) instanceof XPathParser.ReturnClauseContext){
                        returnClause=t.getChild(i);
                    }
                }
                //List<Node> results = new ArrayList<>();
                List<ParseTree> bindings =new ArrayList<>();
                //collecting the for clause
                for (int i = 1;i < forClause.getChildCount();i++) {
                    ParseTree child =forClause.getChild(i);
                    if (!",".equals(child.getText())) {
                        bindings.add(child);
                    }
                }
                return evaluateForRecursive(bindings,0,context,letClause,whereClause,returnClause,doc,xmlFilePath);
        }
        //letClause xq
        else if(t.getChild(0) instanceof XPathParser.LetClauseContext){
                ParseTree letClause = t.getChild(0);
                Map<String, List<Node>> newEnv = new HashMap<>(context.env);
                XQueryContext current = new XQueryContext(context.contextItem, newEnv);
                // letClause: 'let' varRepeat2 (',' varRepeat2)*
                for (int i = 1; i < letClause.getChildCount(); i++) {
                    ParseTree child = letClause.getChild(i);
                    if (",".equals(child.getText())) continue;
                    if (child.getChildCount() >= 3) {
                        String varName = child.getChild(0).getText();
                        ParseTree xqExpr = child.getChild(2);
                        List<Node> value = evaluateXQ(xqExpr, current, doc, xmlFilePath);
                        newEnv.put(varName, value);
                    }
                }
                return evaluateXQ(t.getChild(1), current, doc, xmlFilePath);
        }
        // '<' TAGNAME '>' '{' xq '}' '</' TAGNAME '>'
        else if (t.getChildCount() >= 9
                && "<".equals(t.getChild(0).getText())
                && "{".equals(t.getChild(3).getText())
                && "}".equals(t.getChild(5).getText())
                && "</".equals(t.getChild(6).getText())) {
                String tagname=t.getChild(1).getText();
                Element newElem =doc.createElement(tagname);
                List<Node> xqResults = evaluateXQ(t.getChild(4),context,doc,xmlFilePath);
                for (Node child :xqResults) {
                    newElem.appendChild(doc.importNode(child, true));
                }
                return List.of(newElem);
        }
        // '<' TAGNAME '>' xqElem+ '</' TAGNAME '>'         (adjacent elements, no commas)
        // else if ("<".equals(t.getChild(0).getText())
        //         && t.getChild(3) instanceof XPathParser.XqElemContext) {
        //     String tagname = t.getChild(1).getText();
        //     Element newElem = doc.createElement(tagname);
        //     for (int i = 3; i < t.getChildCount(); i++) {
        //         if (t.getChild(i) instanceof XPathParser.XqElemContext) {
        //             //evaluateXQ matches it against these same branches.
        //             for (Node child : evaluateXQ(t.getChild(i), context, doc, xmlFilePath)) {
        //                 newElem.appendChild(doc.importNode(child, true));
        //             }
        //         }
        //     }
        //     return List.of(newElem);
        // }
        // '<' TAGNAME '>' STRING '</' TAGNAME '>'   (literal text content)
        else if(t.getChildCount() == 7&& "<".equals(t.getChild(0).getText())&& ">".equals(t.getChild(2).getText())
        && "</".equals(t.getChild(4).getText()))
        {
            String tagname = t.getChild(1).getText();
            Element newElem = doc.createElement(tagname);
            String raw =t.getChild(3).getText();
            String s = raw.substring(1, raw.length() - 1);   // strip surrounding quotes
            newElem.appendChild(doc.createTextNode(s));
            return List.of(newElem);

        }
        else if(t.getChildCount()==3){
            //(xq)
            if ("(".equals(t.getChild(0).getText()) && ")".equals(t.getChild(2).getText())) {
                return evaluateXQ(t.getChild(1),context,doc,xmlFilePath);
            }
            //xq ',' xq
            if (",".equals(t.getChild(1).getText())) {
                List<Node> combinedResults= new ArrayList<>();
                //evaluateXQ returns List<Node>
                combinedResults.addAll(evaluateXQ(t.getChild(0),context,doc,xmlFilePath));
                combinedResults.addAll(evaluateXQ(t.getChild(2),context,doc,xmlFilePath));
                return combinedResults;
            }
            //xq '/' rp
            if ("/".equals(t.getChild(1).getText())) {
                List<Node> xqResults = evaluateXQ(t.getChild(0),context, doc, xmlFilePath);
                List<Node> result = new ArrayList<>();
                for (Node n : xqResults) {
                    List<Node> rpResults = XPathEvaluator.evaluateRP(t.getChild(2), n);
                    for (Node rp2Result : rpResults) {
                        if(!result.contains(rp2Result)) {
                            result.add(rp2Result);
                        }
                    }
                }
                return result;
            }
            //xq '//' rp
            if ("//".equals(t.getChild(1).getText())) {
                List<Node> Finalresults=new ArrayList<>();
                List<Node> xqResults = evaluateXQ(t.getChild(0),context, doc, xmlFilePath);
                List<Node> result1 = new ArrayList<>();
                for (Node n : xqResults) {
                    List<Node> rpResults = XPathEvaluator.evaluateRP(t.getChild(2), n);
                    for(Node rp2Result : rpResults) {
                        if(!result1.contains(rp2Result)) {
                            result1.add(rp2Result);
                        }
                    }
                }
                //the second path is that we go to the descendant of every node in rp1 results and then evaluate rp2 with the descendant
                List<Node> xqResults2 = evaluateXQ(t.getChild(0),context,doc,xmlFilePath);
                List<Node> result2 = new ArrayList<>();
                for(Node n :xqResults2) {
                    List<Node> listOfNodes = new ArrayList<>();
                    listOfNodes = recurrDescendant(n, listOfNodes);
                    for(Node node : listOfNodes) {
                        List<Node> rp3Results = XPathEvaluator.evaluateRP(t.getChild(2),node);
                        for(Node rp3Result : rp3Results) {
                            if(!result2.contains(rp3Result)) {
                                result2.add(rp3Result);
                            }
                        }
                    }  
                }
                Finalresults.addAll(result1);
                for(Node uniqueResult : result2) {
                    if(!Finalresults.contains(uniqueResult)) {
                        Finalresults.add(uniqueResult);
                    }
                }
                return Finalresults;
            }
        }
        // else if (t.getChild(0) instanceof XPathParser.JoinExprContext) {
        //     return evaluateJoinExpr((XPathParser.JoinExprContext) t.getChild(0), doc, xmlFilePath);
        // }
        return new ArrayList<>();
    }
    private static List<Node> evaluateJoinExpr(XPathParser.JoinExprContext join, Document doc,
            String xmlFilePath) throws Exception {
        List<String> leftAttrs = new ArrayList<>();
        if (join.joinAttrs(0) != null) {
            for (int i = 0; i < join.joinAttrs(0).TAGNAME().size(); i++) {
                leftAttrs.add(join.joinAttrs(0).TAGNAME(i).getText());
            }
        }
        List<String> rightAttrs = new ArrayList<>();
        if (join.joinAttrs(1) != null) {
            for (int i = 0; i < join.joinAttrs(1).TAGNAME().size(); i++) {
                rightAttrs.add(join.joinAttrs(1).TAGNAME(i).getText());
            }
        }
        XQueryContext newContext = new XQueryContext(null, new HashMap<>());
        List<Node> r1 = evaluateXQ(join.xq(0), newContext, doc, xmlFilePath);
        List<Node> r2 = evaluateXQ(join.xq(1), newContext, doc, xmlFilePath);
        return HashJoinEvaluator.join(r1, r2, leftAttrs, rightAttrs, doc);
    }
    private static Boolean evaluateCOND(ParseTree t,XQueryContext context,Document doc,String xmlFilePath) throws Exception {
        if (t.getChildCount()==3){
            if("=".equals(t.getChild(1).getText()) || "eq".equals(t.getChild(1).getText())){
                List<Node> results1 =evaluateXQ(t.getChild(0), context, doc, xmlFilePath);
                List<Node> results2 =evaluateXQ(t.getChild(2), context, doc, xmlFilePath);
                for (Node xqResult1 : results1) {
                    for (Node xqResult2 : results2){
                        if (xqResult1.getTextContent().trim().equals(xqResult2.getTextContent().trim())) {
                            return true;
                        }
                    }
                }
                return false;

            }
            else if("==".equals(t.getChild(1).getText()) || "is".equals(t.getChild(1).getText())){
                List<Node> results1 =evaluateXQ(t.getChild(0), context, doc, xmlFilePath);
                List<Node> results2 =evaluateXQ(t.getChild(2), context, doc, xmlFilePath);
                for (Node xqResult1 : results1) {
                    for (Node xqResult2 : results2){
                        if (xqResult1.isSameNode(xqResult2)) {
                            return true;
                        }
                    }
                }
                return false;
            }
            else if("(".equals(t.getChild(0).getText()) && ")".equals(t.getChild(2).getText())){
                return  evaluateCOND(t.getChild(1),context,doc,xmlFilePath);

            }
            else if("and".equals(t.getChild(1).getText())){
                return evaluateCOND(t.getChild(0),context,doc,xmlFilePath) && evaluateCOND(t.getChild(2),context,doc,xmlFilePath);

            }
            else if("or".equals(t.getChild(1).getText())){
                return evaluateCOND(t.getChild(0),context,doc,xmlFilePath) || evaluateCOND(t.getChild(2),context,doc,xmlFilePath);
            }
        }
        else if(t.getChildCount()==2){
            return !evaluateCOND(t.getChild(1),context,doc,xmlFilePath);
        }
        else{
            if("empty".equals(t.getChild(0).getText())){
                List<Node> results =evaluateXQ(t.getChild(2), context, doc, xmlFilePath);
                return results.isEmpty();

            }
            else if("some".equals(t.getChild(0).getText())){
                //ParseTree someClause = t.getChild(1);
                List<ParseTree> bindings = new ArrayList<>();
                int i = 1;
                // collect all varRepeat1
                while (!"satisfies".equals(t.getChild(i).getText())) {
                    if (!",".equals(t.getChild(i).getText())){
                        bindings.add(t.getChild(i));
                    }
                    i++;
                }

                // cond after satisfies
                ParseTree condClause = t.getChild(i + 1);
                return evaluateSomeRecursive(bindings,0,context,condClause,doc,xmlFilePath);

            }
        }
        return false;
    }

    private static List<Node> step(List<Node> nodes, String sep, String tag) {
        List<Node> result = new ArrayList<>();
        for (Node n : nodes) {
            if (sep.equals("//")) {
                List<Node> desc = new ArrayList<>();
                desc.add(n);
                recurrDescendant(n, desc);
                for (Node d : desc) {
                    if (tag.equals("text()")) {
                        NodeList kids = d.getChildNodes(); 
                        for (int i = 0; i < kids.getLength(); i++) {
                            if (kids.item(i).getNodeType() == Node.TEXT_NODE)
                                result.add(kids.item(i));
                        }
                    } else {
                        NodeList kids = d.getChildNodes();
                        for (int i = 0; i < kids.getLength(); i++) {
                            if (kids.item(i).getNodeType() == Node.ELEMENT_NODE
                                    && kids.item(i).getNodeName().equals(tag))
                                result.add(kids.item(i));
                        }
                    }
                }
            } 
            else {
                // direct children only
                if (tag.equals("text()")) {
                    NodeList kids = n.getChildNodes();
                    for (int i = 0; i < kids.getLength(); i++) {
                        if (kids.item(i).getNodeType() == Node.TEXT_NODE)
                            result.add(kids.item(i));
                    }
                } else {
                    NodeList kids = n.getChildNodes();
                    for (int i = 0; i < kids.getLength(); i++) {
                        if (kids.item(i).getNodeType() == Node.ELEMENT_NODE && kids.item(i).getNodeName().equals(tag))
                            result.add(kids.item(i));
                    }
                }        
            }
        }
        return result;
    }
    private static List<Node> evaluatePATH(ParseTree path, XQueryContext ctx, String xmlFilePath) throws Exception {

        if (path.getChild(0).getText().equals("doc")) {
            // There is only ever one input document: the file passed on the
            // command line. doc("input"), doc("j_caesar.xml"), etc. all map to it.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document doc = factory.newDocumentBuilder().parse(new File(xmlFilePath));
            List<Node> current = new ArrayList<>();
            current.add(doc.getDocumentElement());
            // walk remaining children: (sepRepeat)* SEP TAGNAME/text()
            for(int i=4;i<path.getChildCount();i++){
                ParseTree child = path.getChild(i);
                if (child instanceof XPathParser.SepRepeatContext) {
                    // sepRepeat is one node with 2 children: SEP TAGNAME
                    String sep = child.getChild(0).getText();
                    String tag = child.getChild(1).getText();
                    current = step(current, sep, tag);
                    // no extra i++, for-loop handles it
                } else {
                    // final two flat tokens: SEP  TAGNAME|text()
                    String sep  = path.getChild(i).getText();
                    String last = path.getChild(i + 1).getText();
                    current = step(current, sep, last);
                    i += 1;
                }
                    
            }
            return current;         
        }
        else{
            List<Node> current = new ArrayList<>();
            String varName = path.getChild(0).getText();
            List<Node> bound = ctx.env.get(varName);
            if (bound == null) {
                return new ArrayList<>();
            }
            current = new ArrayList<>(bound);
            for(int i=1;i<path.getChildCount();i++){
                ParseTree child = path.getChild(i);
                if (child instanceof XPathParser.SepRepeatContext) {
                    // sepRepeat is one node with 2 children: SEP TAGNAME
                    String sep = child.getChild(0).getText();
                    String tag = child.getChild(1).getText();
                    current = step(current, sep, tag);
                    // no extra i++, for-loop handles it
                } else {
                    // final two flat tokens: SEP  TAGNAME|text()
                    String sep  = path.getChild(i).getText();
                    String last = path.getChild(i + 1).getText();
                    current = step(current, sep, last);
                    i += 1;
                }     
            }
            return current;
        }

    }
    private static Boolean evaluateCOND2(ParseTree t,XQueryContext ctx){
        //STRING
        if((t.getChild(0).getText().startsWith("\"") && t.getChild(0).getText().endsWith("\"") || t.getChild(0).getText().startsWith("'") && t.getChild(0).getText().endsWith("'"))) {
            String string1 =t.getChild(0).getText();
            String string2 =t.getChild(2).getText();
            return string1.equals(string2);
        }
        //cond2 'and' cond2
        else if(t.getChild(1).getText().equals("and")){
            return evaluateCOND2(t.getChild(0), ctx) && evaluateCOND2(t.getChild(2), ctx);

        }
        //VAR VAR
        else{
            String lhs = ctx.env.get(t.getChild(0).getText()).get(0).getTextContent().trim();
            String rhs = ctx.env.get(t.getChild(2).getText()).get(0).getTextContent().trim();
            return lhs.equals(rhs);
        }
    }
}