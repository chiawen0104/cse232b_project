package main;

import org.w3c.dom.*;

import java.util.ArrayList;
import java.util.List;

/** Equi-join on tuple elements using hash buckets (h(key) = sum(chars) mod b). */
public class HashJoinEvaluator {
    private static final int NUM_BUCKETS = 101;
    private static int hash(String key) {
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += key.charAt(i);
        }
        return Math.floorMod(sum, NUM_BUCKETS);
    }
    static class KeyTuple {
        String hashKey;
        Node tuple;
        KeyTuple(String hashKey, Node tuple) { this.hashKey = hashKey; this.tuple = tuple; }
        String getHashKey() { return hashKey; }
        Node getTuple() { return tuple; }
    }

    public static List<Node> join(List<Node> r1, List<Node> r2,
            List<String> leftAttrs, List<String> rightAttrs, Document doc) {
        if (leftAttrs.isEmpty() || rightAttrs.isEmpty()) {
            return new ArrayList<>();
        }
                //Left subquery results, they re all tuples!!!!
                //Right subquery results, theye all tuples!!!!
                //Left attributes
                //Right attributes
            List<List<KeyTuple>> buckets = new ArrayList<>(NUM_BUCKETS);
            for (int i = 0; i < NUM_BUCKETS; i++){
                buckets.add(new ArrayList<>());
            }
            
            List<Node> result = new ArrayList<>();
            for (Node t1 : r1) {
                String hashKey1= "";
                for (String leftAttr : leftAttrs) {
                    for (int i=0;i<t1.getChildNodes().getLength();i++) {
                        Node c = t1.getChildNodes().item(i);
                        if (c.getNodeType() == Node.ELEMENT_NODE) {
                            if(c.getNodeName().equals(leftAttr)) {
                                hashKey1 += c.getTextContent().trim();
                            }
                        }
                    }
                }
                int hash = hash(hashKey1);
               buckets.get(hash).add(new KeyTuple(hashKey1, t1));

            }
            for (Node t2 : r2) {
                String hashKey2= "";
                for (String rightAttr : rightAttrs) {
                    for (int i = 0; i <t2.getChildNodes().getLength(); i++){
                        Node c = t2.getChildNodes().item(i);
                        if (c.getNodeType() == Node.ELEMENT_NODE) {
                            if(c.getNodeName().equals(rightAttr)) {
                                hashKey2 += c.getTextContent().trim();
                            }
                        }
                    }
                }
                int hash = hash(hashKey2);
                for (KeyTuple keyTuple : buckets.get(hash)) {
                    if (keyTuple.getHashKey().equals(hashKey2)) {
                        Element merged = doc.createElement("tuple");
                        for (int i = 0; i < keyTuple.getTuple().getChildNodes().getLength(); i++) {
                            Node c = keyTuple.getTuple().getChildNodes().item(i);
                            if (c.getNodeType() == Node.ELEMENT_NODE) {
                                merged.appendChild(doc.importNode(c, true));
                            }
                        }
                        for (int i = 0; i < t2.getChildNodes().getLength(); i++) {
                            Node c = t2.getChildNodes().item(i);
                            if (c.getNodeType() == Node.ELEMENT_NODE) {
                                merged.appendChild(doc.importNode(c, true));
                            }
                        }
                        result.add(merged);
                    }
                }
            }
            return result;
    }

}
