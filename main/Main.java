import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.ArrayList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.List;
public class Main {
    public static void main(String[] args) throws Exception {
        String fname = args[0];
        ExpLexer lexer = new ExpLexer(new ANTLRFileStream(fname));
        ExpParser parser = new ExpParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.eval();
        evaluate(tree);
        System.out.println(compute(tree));
    }
    private static List<Node> evaluateAP(ParseTree t) {
      if (t instanceof ExpParser.ApContext) {
        String filename =t.getChild(2).filename().getText();
        String sep = t.getChild(4).getText();
        if(sep.equals("/")) {
          //evaluate the rp tree with a single slash
          return evalApSingleSlash(file, rpTree);
        } else {
          //evaluate the rp tree with a double slash
          return evalApDoubleSlash(file, rpTree);
        }
     }
    }
   private static List<Node> evaluateRP(ParseTree t, Node context) {
      if (t instanceof ExpParser.RpContext) {
        if(t.getChildCount() == 1) {
          //getTagName() / getAttribute() can only be called on the Element node
          if(t.getChild(0) instanceof ExpParser.TAGNAMEContext) {
            String tag = rp.TAGNAME().getText(); 
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
          if('*'.equals(t.getChild(0).getText())) {
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
          if('.'.equals(t.getChild(0).getText())) {
            return List.of(context);
          }
          if('..'.equals(t.getChild(0).getText())) {
            //since it's ..
            if (context.getParentNode() == null) {
              return new ArrayList<Node>();
            }
            else{
              return new ArrayList<Node>(context.getParentNode());
            }
          }
        }
        else{
          if('text'.equals(t.getChild(0).getText())) {
            String s = context.getTextContent();
            if s==null{
              return new ArrayList<Node>();
            }
            else{
              return s;
            }
          }
          if('@'.equals(t.getChild(0).getText())) {
            //get the list of attribute nodes
            return List.of((Element)context.getAttributeNode(t.getChild(1).getText()));
          }
          if(t.getChildCount() == 3 && "(".equals(rp.getChild(0).getText()) && ")".equals(rp.getChild(2).getText());) {
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
            private void recurrDescendant(Node node, List<Node> listOfNodes) {
              NodeList kids = node.getChildNodes();
              for(int i = 0; i < kids.getLength(); i++) {
                Node c = kids.item(i);
                if(c.getNodeType() == Node.ELEMENT_NODE) {
                  listOfNodes.add(c);
                  recurrDescendant(c, listOfNodes);
                }
              }
            }
            //the second path is that we go to the descendant of every node in rp1 results and then evaluate rp2 with the descendant
            List<Node> rp1Results = evaluateRP(t.getChild(0), context);
            List<Node> uniqueResults2 = new ArrayList<>();
            for(Node rp1Result : rp1Results) {
              List<Node> listOfNodes = new ArrayList<>();
              recurrDescendant(rp1Result, listOfNodes);
              for(Node node : listOfNodes) {
                List<Node> rp2Results = evaluateRP(t.getChild(2), node);
                for(Node rp2Result : rp2Results) {
                  if(!uniqueResults2.contains(rp2Result)) {
                    uniqueResults2.add(rp2Result);
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
              if(evaluateF(t.getChild(1), rpResult)) {
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
    }
   }
   private static Boolean evaluateF(ParseTree t, List<Node> listOfNodes) {
      if(t.getChildCount() == 1) {
        if (!evaluateRP(t.getChild(0), listOfNodes).isEmpty()) {
          //if it is not empty, then there is at least one node that satisfies the condition
          return true;
        }
        else {
          return false;
        }
      }
      if(t.getChild(1).getText().equals("=") && t.getChild(2).getType() == ExpParser.RpContext ||t.getChild(1).getText().equals("eq") && t.getChild(2).getType() == ExpParser.FContext) {
        List<Node> rp1Results = evaluateRP(t.getChild(0), listOfNodes);
        List<Node> rp2Results = evaluateRP(t.getChild(2), listOfNodes);
        for(Node rp1Result : rp1Results) {
          for(Node rp2Result : rp2Results) {
            //check the value of the nodes is there exist some are the same
            if(rp1Result.getTextContent().equals(rp2Result.getTextContent())) {
              return true;
            }
          }
        }
        return false;
      }
      if(t.getChild(1).getText().equals("==") && t.getChild(2).getType() == ExpParser.RpContext ||t.getChild(1).getText().equals("is") && t.getChild(2).getType() == ExpParser.FContext) {
        List<Node> rp1Results = evaluateRP(t.getChild(0), listOfNodes);
        List<Node> rp2Results = evaluateRP(t.getChild(2), listOfNodes);
        for(Node rp1Result : rp1Results) {
          for(Node rp2Result : rp2Results) {
            //check if threr are really the same node exist in both list
          if(rp1Result.equals(rp2Result)) {
            return true;
          }
        }
      }
      return false;
      }
      // rp '=' STRING
      if(t.getChild(2) instanceof ExpParser.STRINGContext){
        //∃x ∈ [[rp]]R(n) x eq StringConstant 
        //just check if there is any node in the list of nodes that value is the same as the StringConstant
        rpResults = evaluateRP(t.getChild(0), listOfNodes);
        for(Node rpResult : rpResults) {
          if(rpResult.getTextContent().equals(t.getChild(2).getText())) {
            return true;
          }
        }
        return false;
      }
      if(t.getChild(1) instanceof ExpParser.FContext){
        //if it is a (f ), then we need to evaluate the f context
        return evaluateF(t.getChild(1), listOfNodes);
      }
      if(t.getChild(1).getText().equal('and')){
        return evaluateF(t.getChild(0), listOfNodes) && evaluateF(t.getChild(2), listOfNodes);
      }
      if(t.getChild(1).getText().equal('or')){
        return evaluateF(t.getChild(0), listOfNodes) || evaluateF(t.getChild(2), listOfNodes);
      }
      if(t.getChild(0).getText().equal('not')){
        return !evaluateF(t.getChild(1), listOfNodes);
      }
      return false;
   }
   private static double compute(ParseTree t) {
     if (t instanceof ExpParser.EvalContext) {
        return compute(t.getChild(0));
     }
     if (t instanceof ExpParser.AdditionExpContext) {
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
     if (t instanceof ExpParser.MultiplyExpContext) {
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
     if (t instanceof ExpParser.AtomExpContext) {
       if (t.getChildCount() > 1) {
         return compute(t.getChild(1));
       }
       return Double.parseDouble(t.getChild(0).getText());
     }
     // will never reach here, just pleasing Java compiler    
     return 0.0;
   }
}
