package main;

import org.antlr.v4.runtime.tree.ParseTree;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.*;

public class XPathEvaluator {

    // evalAP: rule 1, 2
    public static List<Node> evalAP(ParseTree t, String xmlFilePath) throws Exception {
        String fileName = stripQuotes(t.getChild(2).getChild(0).getText());

        // Locate the XML file: try same directory as xmlFilePath first, then relative path
        File xmlFile = new File(new File(xmlFilePath).getParent(), fileName);
        if (!xmlFile.exists()) {
            xmlFile = new File(fileName);
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        Node root = doc.getDocumentElement();

        String sep = t.getChild(4).getText();
        ParseTree rpTree = t.getChild(5);

        if (sep.equals("/")) {
            // rule 1: [[doc(fn)/rp]]A = [[rp]]R(root(fn))
            return evalRP(rpTree, root);
        } else {
            // rule 2: [[doc(fn)//rp]]A = [[.//rp]]R(root(fn))
            return evalDoubleSlashFromNode(rpTree, root);
        }
    }

    // evalRP: rule 3~13
    public static List<Node> evalRP(ParseTree t, Node n) {
        int count = t.getChildCount();
        
        // single child
        if (count == 1) {
            String text = t.getChild(0).getText();

            // rule 5: [[.]]R(n) = <n>
            if (text.equals(".")) {
                List<Node> res = new ArrayList<>();
                res.add(n);
                return res;
            }

            // rule 6: [[..]]R(n) = parent(n)
            if (text.equals("..")) {
                List<Node> res = new ArrayList<>();
                Node parent = n.getParentNode();
                if (parent != null && parent.getNodeType() == Node.ELEMENT_NODE) {
                    res.add(parent);
                }
                return res;
            }

            // rule 4: [[*]]R(n) = children(n)
            if (text.equals("*")) {
                return getElementChildren(n);
            }

            // rule 3: [[tagName]]R(n)
            List<Node> res = new ArrayList<>();
            NodeList kids = n.getChildNodes();
            for (int i = 0; i < kids.getLength(); i++) {
                Node c = kids.item(i);
                if (c.getNodeType() == Node.ELEMENT_NODE
                        && c.getNodeName().equals(text)) {
                    res.add(c);
                }
            }
            return res;
        }

        // two children 
        if (count == 2) {
            // rule 8: [[@attName]]R(n)
            if (t.getChild(0).getText().equals("@")) {
                String attrName = t.getChild(1).getText();
                List<Node> res = new ArrayList<>();
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Attr attr = ((Element) n).getAttributeNode(attrName);
                    if (attr != null) res.add(attr);
                }
                return res;
            }
        }

        // three children
        if (count == 3) {
            String c0 = t.getChild(0).getText();
            String c1 = t.getChild(1).getText();
            String c2 = t.getChild(2).getText();

            // rule 7: [[text()]]R(n)
            if (c0.equals("text") && c1.equals("(") && c2.equals(")")) {
                List<Node> res = new ArrayList<>();
                NodeList kids = n.getChildNodes();
                for (int i = 0; i < kids.getLength(); i++) {
                    Node c = kids.item(i);
                    if (c.getNodeType() == Node.TEXT_NODE
                            && !c.getNodeValue().trim().isEmpty()) {
                        res.add(c);
                    }
                }
                return res;
            }

            // rule 9: [[(rp)]]R(n) = [[rp]]R(n)
            if (c0.equals("(") && c2.equals(")")) {
                return evalRP(t.getChild(1), n);
            }

            // rule 10: [[rp1/rp2]]R(n)
            if (c1.equals("/")) {
                return evalSlash(t.getChild(0), t.getChild(2), n);
            }

            // rule 11: [[rp1//rp2]]R(n)
            if (c1.equals("//")) {
                return evalDoubleSlash(t.getChild(0), t.getChild(2), n);
            }

            // rule 13: [[rp1,rp2]]R(n) = [[rp1]]R(n), [[rp2]]R(n)
            if (c1.equals(",")) {
                List<Node> res = new ArrayList<>(evalRP(t.getChild(0), n));
                res.addAll(evalRP(t.getChild(2), n));
                return res;
            }
        }

        // four children
        if (count == 4) {
            // rule 12: [[rp[f]]]R(n)
            if (t.getChild(1).getText().equals("[")
                    && t.getChild(3).getText().equals("]")) {
                List<Node> rpResults = evalRP(t.getChild(0), n);
                List<Node> res = new ArrayList<>();
                for (Node x : rpResults) {
                    if (evalF(t.getChild(2), x)) {
                        res.add(x);
                    }
                }
                return res;
            }
        }

        return new ArrayList<>();
    }


    // evalF — rule 14~21
    public static boolean evalF(ParseTree t, Node n) {
        int count = t.getChildCount();

        // single child: rule 14 
        // [[rp]]F(n) = [[rp]]R(n) != <>
        if (count == 1) {
            return !evalRP(t.getChild(0), n).isEmpty();
        }

        // two children: rule 21
        // 'not' f
        if (count == 2 && t.getChild(0).getText().equals("not")) {
            return !evalF(t.getChild(1), n);
        }

        // three children
        if (count == 3) {
            String c0 = t.getChild(0).getText();
            String c1 = t.getChild(1).getText();
            String c2 = t.getChild(2).getText();

            // rule 18: [[(f)]]F(n) = [[f]]F(n)
            if (c0.equals("(") && c2.equals(")")) {
                return evalF(t.getChild(1), n);
            }

            // rule 19: [[f1 and f2]]F(n)
            if (c1.equals("and")) {
                return evalF(t.getChild(0), n) && evalF(t.getChild(2), n);
            }

            // rule 20: [[f1 or f2]]F(n)
            if (c1.equals("or")) {
                return evalF(t.getChild(0), n) || evalF(t.getChild(2), n);
            }

            // rule 17: [[rp = StringConstant]]F(n)
            if ((c1.equals("=") || c1.equals("eq")) && isStringConstant(c2)) {
                String strVal = stripQuotes(c2);
                List<Node> rpResults = evalRP(t.getChild(0), n);
                for (Node x : rpResults) {
                    if (getTextValue(x).equals(strVal)) return true;
                }
                return false;
            }

            // rule 15: [[rp1 = rp2]]F(n) / [[rp1 eq rp2]]F(n)
            if (c1.equals("=") || c1.equals("eq")) {
                List<Node> rp1Results = evalRP(t.getChild(0), n);
                List<Node> rp2Results = evalRP(t.getChild(2), n);
                for (Node x : rp1Results) {
                    for (Node y : rp2Results) {
                        if (valueEqual(x, y)) return true;
                    }
                }
                return false;
            }

            // rule 16: [[rp1 == rp2]]F(n) / [[rp1 is rp2]]F(n)
            if (c1.equals("==") || c1.equals("is")) {
                List<Node> rp1Results = evalRP(t.getChild(0), n);
                List<Node> rp2Results = evalRP(t.getChild(2), n);
                for (Node x : rp1Results) {
                    for (Node y : rp2Results) {
                        if (x.isSameNode(y)) return true;
                    }
                }
                return false;
            }
        }

        return false;
    }

    
    // private helpers
    private static List<Node> evalSlash(ParseTree rp1, ParseTree rp2, Node n) {
        List<Node> rp1Results = evalRP(rp1, n);
        List<Node> res = new ArrayList<>();
        for (Node x : rp1Results) {
            for (Node y : evalRP(rp2, x)) {
                if (!res.contains(y)) res.add(y);
            }
        }
        return res;
    }

    /**
     * rule 11: [[rp1//rp2]]R(n)
     * unique([[rp1/rp2]]R(n), [[rp1/* //rp2]]R(n))
     *
     * Expanded as:
     *   part1 = result of rp1/rp2
     *   part2 = for each node in rp1 results, collect all descendants, apply rp2 to each
     */
    private static List<Node> evalDoubleSlash(ParseTree rp1, ParseTree rp2, Node n) {
        // part1: rp1/rp2
        List<Node> res = evalSlash(rp1, rp2, n);

        // part2: all descendants of each rp1 result -> apply rp2
        List<Node> rp1Results = evalRP(rp1, n);
        for (Node x : rp1Results) {
            List<Node> descendants = new ArrayList<>();
            collectDescendants(x, descendants);
            for (Node d : descendants) {
                for (Node y : evalRP(rp2, d)) {
                    if (!res.contains(y)) res.add(y);
                }
            }
        }
        return res;
    }

    private static List<Node> evalDoubleSlashFromNode(ParseTree rp, Node n) {
        List<Node> res = new ArrayList<>();

        // Apply rp to n itself
        for (Node y : evalRP(rp, n)) {
            if (!res.contains(y)) res.add(y);
        }

        // Apply rp to all descendants
        List<Node> descendants = new ArrayList<>();
        collectDescendants(n, descendants);
        for (Node d : descendants) {
            for (Node y : evalRP(rp, d)) {
                if (!res.contains(y)) res.add(y);
            }
        }
        return res;
    }

    // Collect all element descendants of node n in DFS order.
    private static void collectDescendants(Node n, List<Node> result) {
        NodeList kids = n.getChildNodes();
        for (int i = 0; i < kids.getLength(); i++) {
            Node c = kids.item(i);
            if (c.getNodeType() == Node.ELEMENT_NODE) {
                result.add(c);
                collectDescendants(c, result);
            }
        }
    }

    // Return all element children of node n
    private static List<Node> getElementChildren(Node n) {
        List<Node> res = new ArrayList<>();
        NodeList kids = n.getChildNodes();
        for (int i = 0; i < kids.getLength(); i++) {
            Node c = kids.item(i);
            if (c.getNodeType() == Node.ELEMENT_NODE) {
                res.add(c);
            }
        }
        return res;
    }

    // rule 15: value equality
    private static boolean valueEqual(Node n, Node m) {
        if (n.getNodeType() != m.getNodeType()) return false;

        // Text node: compare text content
        if (n.getNodeType() == Node.TEXT_NODE) {
            return n.getNodeValue().equals(m.getNodeValue());
        }

        // Attribute node: compare name and value
        if (n.getNodeType() == Node.ATTRIBUTE_NODE) {
            return n.getNodeName().equals(m.getNodeName())
                    && n.getNodeValue().equals(m.getNodeValue());
        }

        // Element node: compare tag, text content, and children
        if (n.getNodeType() == Node.ELEMENT_NODE) {
            if (!n.getNodeName().equals(m.getNodeName())) return false;
            if (!n.getTextContent().equals(m.getTextContent())) return false;
            List<Node> nKids = getElementChildren(n);
            List<Node> mKids = getElementChildren(m);
            if (nKids.size() != mKids.size()) return false;
            for (int i = 0; i < nKids.size(); i++) {
                if (!valueEqual(nKids.get(i), mKids.get(i))) return false;
            }
            return true;
        }

        return false;
    }

    // Get the text value of a node
    private static String getTextValue(Node n) {
        if (n.getNodeType() == Node.TEXT_NODE) {
            return n.getNodeValue();
        }
        if (n.getNodeType() == Node.ATTRIBUTE_NODE) {
            return n.getNodeValue();
        }
        return n.getTextContent();
    }

    // Check whether a string is a string constant
    private static boolean isStringConstant(String s) {
        return (s.startsWith("\"") && s.endsWith("\""))
                || (s.startsWith("'") && s.endsWith("'"));
    }

    // Strip surrounding quotes from a string constant
    private static String stripQuotes(String s) {
        if (s.length() >= 2 && isStringConstant(s)) {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }
}