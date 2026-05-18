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
        // One grammar (XPath.g4) covers both XPath and XQuery, so one parser handles both.
        CharStream input = CharStreams.fromFileName(args[1]);
        XPathLexer lexer = new XPathLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        XPathParser parser = new XPathParser(tokens);

        // Step 2: Evaluate - parse as xq (which includes ap as one of its alternatives,
        // so this handles both Milestone 1 XPath and Milestone 2 XQuery).
        ParseTree tree = parser.xq();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        List<Node> results;

        if (tree.getChildCount() == 1 && tree.getChild(0) instanceof XPathParser.ApContext) {
            // Milestone 1: pure XPath absolute path - delegate to XPathEvaluator
            results = XPathEvaluator.evaluateAP(tree.getChild(0), xmlFilePath);
        } else {
            // Milestone 2: XQuery expression   delegate to XQueryEvaluator
            Document scratchDoc = builder.newDocument();
            XQueryContext ctx = new XQueryContext();
            ctx.contextItem = null;
            ctx.env = new HashMap<>();
            results = XQueryEvaluator.evaluate(tree, ctx, scratchDoc, xmlFilePath);
        }

        // Step 3: Wrap results in a <RESULT> root element
        Document outDoc = builder.newDocument();
        Element root = outDoc.createElement("RESULT");
        outDoc.appendChild(root);
        for (Node n : results) {
            root.appendChild(outDoc.importNode(n, true));
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
