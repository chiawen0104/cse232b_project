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
        // args[0] = path to .xml
        // args[1] = path to query file
        // args[2] = path to output file

        // Step 1: Parse the query file
        CharStream input = CharStreams.fromFileName(args[1]);
        XPathLexer lexer = new XPathLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        XPathParser parser = new XPathParser(tokens);
        ParseTree apTree = parser.ap();

        // Step 2: Read xml file and execute the XPath query evaluator
        String xmlFilePath = args[0];
        List<Node> results = XPathEvaluator.evalAP(apTree, xmlFilePath);

        // Step 3: Build output XML, wrapping results in <r>...</r>
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document outDoc = builder.newDocument();

        Element root = outDoc.createElement("r");
        outDoc.appendChild(root);

        for (Node n : results) {
            Node imported = outDoc.importNode(n, true); // deep copy of each result node
            root.appendChild(imported);
        }

        // Step 4: Write the output XML
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

        File outputFile = new File(args[2]);
        outputFile.getParentFile().mkdirs(); // create folder 

        transformer.transform(
            new DOMSource(outDoc),
            new StreamResult(outputFile)
        );
    }
}