package main;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import main.antlr.XPathParser;
import java.util.ArrayList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.*;


public class XPathEvaluator {
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
    public static List<Node> evaluateAP(ParseTree t, String xmlFilePath) throws Exception {
        // There is only ever one input document: the file passed on the command
        // line (args[0]). doc("input"), doc("j_caesar.xml"), etc. all refer to it.
        File file = new File(xmlFilePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        Node root = doc.getDocumentElement();
        String sep = t.getChild(4).getText();
        //the rp
        ParseTree rpTree = t.getChild(5);
        if(sep.equals("/")) {
          // Standard XPath: rp starts at the document node, so /data selects the
          // root element <data>. Evaluating from the Document node makes the root
          // a "child".
          List<Node> apRes = evaluateRP(rpTree, doc);
          // Legacy fallback: some queries name a child of the root directly (e.g.
          // /book when the root is <inventory>). If the standard interpretation
          // matched nothing, retry against the root element's children.
          if (apRes.isEmpty()) {
              apRes = evaluateRP(rpTree, root);
          }
          //evaluate the rp tree with a single slash
          return apRes;
        } else {
          //evaluate the rp tree with a double slash
            List<Node> results = new ArrayList<>();
            // Include the document node so the root element is reachable as the
            // first step (e.g. //data or /data when <data> is the root).
            List<Node> rpResults1 = evaluateRP(rpTree, doc);
            for(Node rpResult : rpResults1) {
              if(!results.contains(rpResult)) {
                results.add(rpResult);
              }
            }
            List<Node> descendants = new ArrayList<>();
            descendants.add(root);   // root is a descendant of the document node
            descendants= recurrDescendant(root, descendants);
            for(Node descendant : descendants) {
              List<Node> rpResults2 = evaluateRP(rpTree, descendant);
              for(Node rpResult : rpResults2) {
                if(!results.contains(rpResult)) {
                  results.add(rpResult);
                }
              }
            }
            return results;
          }
    }
   static List<Node> evaluateRP(ParseTree t, Node context) {
        if(t.getChildCount() == 1) {
          if ("*".equals(t.getChild(0).getText())) {
            NodeList kids = context.getChildNodes();
            List<Node> nodes = new ArrayList<>();
            for (int i = 0; i < kids.getLength(); i++) {
                Node c = kids.item(i);  
                if (c.getNodeType() == Node.ELEMENT_NODE) {
                  nodes.add(c);
                }
            }
            return nodes;
          }
          if (".".equals(t.getChild(0).getText())) {
            return List.of(context);
          }
          if ("..".equals(t.getChild(0).getText())) {
            //since it's ..
            if (context.getParentNode() == null) {
              return new ArrayList<Node>();
            }
            else{
              return List.of(context.getParentNode());
            }
          }
          // 'text()' is a single token, so it lands in the childCount==1 branch.
          // Select the text-node children of the context (matches text node logic below).
          else if ("text()".equals(t.getChild(0).getText()) || "text".equals(t.getChild(0).getText())) {
            List<Node> textNodes = new ArrayList<>();
            NodeList kids = context.getChildNodes();
            for (int i = 0; i < kids.getLength(); i++) {
                if (kids.item(i).getNodeType() == Node.TEXT_NODE) {
                    textNodes.add(kids.item(i));
                }
            }
            return textNodes;
          }
          //getTagName() / getAttribute() can only be called on the Element node
          else{
            String tag = t.getChild(0).getText();
            List<Node> nodes = new ArrayList<>();
            NodeList kids = context.getChildNodes();
            for (int i = 0; i < kids.getLength(); i++) {
                Node c = kids.item(i);
                if (c.getNodeType() == Node.ELEMENT_NODE && ((Element)c).getTagName().equals(tag)) {
                  nodes.add(c);
                }
            }
            return nodes;
          }
        }
        else{
          if ("text()".equals(t.getChild(0).getText()) || "text".equals(t.getChild(0).getText())) {
            String s = context.getTextContent();
            if (s==null){
              return new ArrayList<Node>();
            }
            else{
                List<Node> textNodes = new ArrayList<>();
                NodeList kids = context.getChildNodes();
                for (int i = 0; i < kids.getLength(); i++) {
                    Node c = kids.item(i);
                    if (c.getNodeType() == Node.TEXT_NODE){
                        textNodes.add(c);
                    }
                }
                return textNodes;
            }
          }
          if ("@".equals(t.getChild(0).getText())) {
            //get the list of attribute nodes
            Element e = (Element) context;
            Node attr = e.getAttributeNode(t.getChild(1).getText());
            if (attr == null) {
              return new ArrayList<Node>();
            }
            return List.of(attr);
          }
          if(t.getChildCount() == 3 && "(".equals(t.getChild(0).getText()) && ")".equals(t.getChild(2).getText())) {
            return evaluateRP(t.getChild(1), context); //evaluate the rp tree with the context  which is the child of the current context
          }
          if(t.getChildCount() == 3 && t.getChild(1).getText().equals("/")) {
            //we want the unique set of nodes both in rp1 and rp2 like composition
            //so need to get rp1 results first and then evaluate rp2 with the rp1 results
            List<Node> rp1Results = evaluateRP(t.getChild(0), context);
            List<Node> uniqueResults = new ArrayList<>();
            for(Node rp1Result : rp1Results) {
              List<Node> rp2Results = evaluateRP(t.getChild(2), rp1Result);
              for(Node rp2Result : rp2Results) {
                if(!uniqueResults.contains(rp2Result)) {
                  uniqueResults.add(rp2Result);
                }
              }
            }
            return uniqueResults;
          }
          if(t.getChildCount() == 3 && t.getChild(1).getText().equals("//")) {
            List<Node> combinedResults = new ArrayList<>();
            //first path is that just like the '/'
            List<Node> rp1Results = evaluateRP(t.getChild(0), context);
            List<Node> uniqueResults1 = new ArrayList<>();
            for(Node rp1Result : rp1Results) {
              List<Node> rp2Results = evaluateRP(t.getChild(2), rp1Result);
              for(Node rp2Result : rp2Results) {
                if(!uniqueResults1.contains(rp2Result)) {
                  uniqueResults1.add(rp2Result);
                }
              }
            }
            //the second path is that we go to the descendant of every node in rp1 results and then evaluate rp2 with the descendant
            List<Node> rp2Results = evaluateRP(t.getChild(0), context);
            List<Node> uniqueResults2 = new ArrayList<>();
            for(Node rp2Result : rp2Results) {
              List<Node> listOfNodes = new ArrayList<>();
              listOfNodes = recurrDescendant(rp2Result, listOfNodes);
              for(Node node : listOfNodes) {
                List<Node> rp3Results = evaluateRP(t.getChild(2), node);
                for(Node rp3Result : rp3Results) {
                  if(!uniqueResults2.contains(rp3Result)) {
                    uniqueResults2.add(rp3Result);
                  }
                }
              }  
            }
            combinedResults.addAll(uniqueResults1);
            for(Node uniqueResult : uniqueResults2) {
              if(!combinedResults.contains(uniqueResult)) {
                combinedResults.add(uniqueResult);
              }
            }
            return combinedResults;
          }
          if(t.getChildCount() == 4) {
            //since it is rp '[' f ']'
            //we want to see if in the list of nodes, there is any node that satisfies the condition in f
            List<Node> rpResults = evaluateRP(t.getChild(0), context);
            List<Node> results = new ArrayList<>();
            for(Node rpResult : rpResults) {
              if(evaluateF(t.getChild(2), rpResult)) {
                results.add(rpResult);
              }
            }
            if(results.isEmpty()) {
              return new ArrayList<Node>();
            }
            else {
              return results;
            }
          }
          if (t.getChildCount() == 3 && t.getChild(1).getText().equals(",")) {
            List<Node> rp1Results=evaluateRP(t.getChild(0), context);
            List<Node> rp2Results=evaluateRP(t.getChild(2), context);
            List<Node> combinedResults=new ArrayList<>(rp1Results);
            combinedResults.addAll(rp2Results);
            return combinedResults;
          }
        }
        return new ArrayList<Node>();
   }
   private static Boolean evaluateF(ParseTree t, Node node) {
      if (t.getChildCount() == 1) {
        if (!evaluateRP(t.getChild(0), node).isEmpty()) {
          return true;
        }
        else {
          return false;
        }
      }

      if (t.getChildCount() == 3 && "(".equals(t.getChild(0).getText()) && ")".equals(t.getChild(2).getText())) {
        
     return evaluateF(t.getChild(1), node); }

      if (t.getChildCount() == 2 && "not".equals(t.getChild(0).getText())) {
        return !evaluateF(t.getChild(1), node);
      }

      if (t.getChildCount() == 3 && "and".equals(t.getChild(1).getText())) {
        return evaluateF(t.getChild(0), node) && evaluateF(t.getChild(2),node);
      }

      if (t.getChildCount() == 3 && "or".equals(t.getChild(1).getText())) {
        return evaluateF(t.getChild(0),node) || evaluateF(t.getChild(2),node);
      }

      if (t.getChildCount() == 3 && ("==".equals(t.getChild(1).getText()) || "is".equals(t.getChild(1).getText()))) {
          List<Node> rp1Results = evaluateRP(t.getChild(0), node);
          List<Node> rp2Results = evaluateRP(t.getChild(2), node);
          for (Node rp1Result : rp1Results) {
            for (Node rp2Result : rp2Results) {
              if (rp1Result.isSameNode(rp2Result)) {
                return true;
              }
            }
          }
        return false;
      }

      if (t.getChildCount() == 3 && "=".equals(t.getChild(1).getText())
          && (t.getChild(2).getText().startsWith("\"") && t.getChild(2).getText().endsWith("\"") || t.getChild(2).getText().startsWith("'") && t.getChild(2).getText().endsWith("'"))) {
        String str = t.getChild(2).getText().substring(1, t.getChild(2).getText().length() - 1);
        List<Node> rpResults = evaluateRP(t.getChild(0), node);
        for (Node rpResult : rpResults) {
          if (str.equals(rpResult.getTextContent())) {
            return true;
          }
        }
        return false;
      }

      if (t.getChildCount() == 3 && ("=".equals(t.getChild(1).getText()) || "eq".equals(t.getChild(1).getText()))) {
          List<Node> rp1Results = evaluateRP(t.getChild(0), node);
          List<Node> rp2Results = evaluateRP(t.getChild(2), node);
          for (Node rp1Result : rp1Results) {
            for (Node rp2Result : rp2Results) {
              if (rp1Result.getTextContent().equals(rp2Result.getTextContent())) {
                return true;
              }
            }
          }
        return false;
      }

      return false;
   }
   private static double compute(ParseTree t) {
     if (t instanceof XPathParser.EvalContext) {
        return compute(t.getChild(0));
     }
     if (t instanceof XPathParser.AdditionExpContext) {
        double soFar = compute(t.getChild(0));
        for (int i = 1; i < t.getChildCount(); i+=2) {
           double nextExpVal = compute(t.getChild(i+1));        
   	   if (t.getChild(i).getText().equals("+")) {
             soFar = soFar + nextExpVal;
           } else {
             soFar = soFar - nextExpVal;
           }           
        }
        return soFar;
     }           
     if (t instanceof XPathParser.MultiplyExpContext) {
        double soFar = compute(t.getChild(0));
        for (int i = 1; i < t.getChildCount(); i+=2) {
           double nextExpVal = compute(t.getChild(i+1));        
   	   if (t.getChild(i).getText().equals("*")) {
             soFar = soFar * nextExpVal;
           } else {
             soFar = soFar / nextExpVal;
           }           
        }           
        return soFar;
     }
     if (t instanceof XPathParser.AtomExpContext) {
       if (t.getChildCount() > 1) {
         return compute(t.getChild(1));
       }
       return Double.parseDouble(t.getChild(0).getText());
     }
     // will never reach here, just pleasing Java compiler    
     return 0.0;
   }
}
