package main;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import main.antlr.XPathParser;

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
                Map<String, List<Node>> newEnv=new HashMap<>();
                newEnv=context.env;
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
            }
            if (whereClause != null &&!evaluateCOND(whereClause.getChild(1),current,doc,xmlFilePath)) {
                return new ArrayList<>();
            }
            return evaluateXQ(returnClause.getChild(1),current,doc,xmlFilePath);
        }
        //recursive case
        else{
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
                List<Node> partial =evaluateForRecursive(bindings,index + 1,next,letClause,whereClause,returnClause,doc,xmlFilePath);
                results.addAll(partial);
            }

            return results;
        }
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
            //VAR
            else{
                String var = t.getChild(0).getText();
                List<Node> value = context.env.get(var);
                if (context.env.containsKey(var)) {
                    return new ArrayList<>(context.env.get(var));
                }
                else{
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
            }
        }
        else if(t.getChildCount()==3){
            //(xq)
            if ("(".equals(t.getChild(0).getText()) && ")".equals(t.getChild(0).getText())){
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
        else{
            // '(' TAGNAME ')' '{' xq '}' '(' '/' TAGNAME ')'
           if ("(".equals(t.getChild(0).getText())&& ")".equals(t.getChild(2).getText()) && "{".equals(t.getChild(3).getText()) && "}".equals(t.getChild(5).getText())) {
                String tagname=t.getChild(1).getText();
                Element newElem =doc.createElement(tagname);
                List<Node> xqResults = evaluateXQ(t.getChild(4),context,doc,xmlFilePath);
                for (Node child :xqResults) {
                    newElem.appendChild(doc.importNode(child, true));
                }
                return List.of(newElem);
            }
            // forClause letClause? whereClause? returnClause
            if(t.getChild(0) instanceof XPathParser.ForClauseContext) {
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
                List<Node> results = new ArrayList<>();
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
            if(t.getChild(0) instanceof XPathParser.LetClauseContext){
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
        }
        return new ArrayList<>();
    }
    private static Boolean evaluateCOND(ParseTree t,XQueryContext context,Document doc,String xmlFilePath) throws Exception {
        if (t.getChildCount()==3){
            if("=".equals(t.getChild(1).getText()) || "eq".equals(t.getChild(1).getText())){
                List<Node> results1 =evaluateXQ(t.getChild(0), context, doc, xmlFilePath);
                List<Node> results2 =evaluateXQ(t.getChild(2), context, doc, xmlFilePath);
                for (Node xqResult1 : results1) {
                    for (Node xqResult2 : results2){
                        if (xqResult1.getTextContent().equals(xqResult2.getTextContent())) {
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
}