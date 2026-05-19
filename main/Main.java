package main;
import main.antlr.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // args[0] = path to XML file
        // args[1] = path to query file
        // args[2] = path to output file

        String xmlFilePath = args[0];

        // Step 1: Parse the query.
        CharStream input = CharStreams.fromFileName(args[1]);
        XPathLexer lexer = new XPathLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        XPathParser parser = new XPathParser(tokens);

        // Step 2: Evaluate
        ParseTree tree = parser.xq();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        List<Node> results;

        if (tree.getChildCount() == 1 && tree.getChild(0) instanceof XPathParser.ApContext) {
            // Milestone 1: pure XPath absolute path
            results = XPathEvaluator.evaluateAP(tree.getChild(0), xmlFilePath);
        } else {
            // Milestone 2: XQuery expression
            Document scratchDoc = builder.newDocument();
            XQueryContext ctx = new XQueryContext();
            ctx.contextItem = null;
            ctx.env = new HashMap<>();
            results = XQueryEvaluator.evaluate(tree, ctx, scratchDoc, xmlFilePath);
        }

        // Step 3: Build output document directly from results (no extra wrapper).
        // The query's outermost element construction IS the root.
        Document outDoc = builder.newDocument();

        if (results.size() == 1 && results.get(0).getNodeType() == Node.ELEMENT_NODE) {
            // Single element result — use it directly as document root
            outDoc.appendChild(outDoc.importNode(results.get(0), true));
        } else {
            // Multiple results or non-element results — wrap in <result>
            Element root = outDoc.createElement("result");
            outDoc.appendChild(root);
            for (Node n : results) {
                root.appendChild(outDoc.importNode(n, true));
            }
        }

        // Step 4: Write output XML
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

        File outputFile = new File(args[2]);
        if (outputFile.getParentFile() != null) {
            outputFile.getParentFile().mkdirs();
        }
        transformer.transform(new DOMSource(outDoc), new StreamResult(outputFile));
    }
}