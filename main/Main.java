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
        if (args.length != 3 && args.length != 4) {
            System.err.println("Usage: java main.Main <xml-data> <input-query> <result-output>");
            System.err.println("   or: java main.Main <xml-data> <input-query> <rewrite-output> <result-output>");
            System.exit(1);
        }

        String xmlFilePath = args[0];
        String queryFilePath = args[1];
        String rewriteOutPath;
        String resultOutPath;
        if (args.length == 3) {
            resultOutPath = args[2];
            rewriteOutPath = null;
        } else {
            rewriteOutPath = args[2];
            resultOutPath = args[3];
        }

        // Step 1: try to parse the input as a plain FLWR query (the rewriter's
        // input grammar). Track syntax errors so we can tell whether the input
        // really is a plain FLWR query that should be rewritten into join form.
        CharStream queryInput = CharStreams.fromFileName(queryFilePath);
        XPathParser queryParser =
                new XPathParser(new CommonTokenStream(new XPathLexer(queryInput)));
        final int[] flwrErrors = {0};
        queryParser.removeErrorListeners();
        queryParser.addErrorListener(new BaseErrorListener() {
            @Override public void syntaxError(Recognizer<?, ?> r, Object sym, int line,
                    int pos, String msg, RecognitionException e) {
                flwrErrors[0]++;
            }
        });
        ParseTree inputTree = queryParser.xquery();

        String rewritten = null;
        if (flwrErrors[0] == 0) {
            // Cleanly a plain FLWR query: rewrite it into explicit join form.
            rewritten = QueryRewriter.rewrite(inputTree);
        }

        // Step 2: obtain the tree to evaluate.
        ParseTree rewrittenTree;
        String rewriteFileContents;
        if (rewritten != null) {
            // FLWR input was rewritten: evaluate the rewritten join query.
            rewriteFileContents = rewritten;
            CharStream evalInput = CharStreams.fromString(rewritten);
            rewrittenTree =
                    new XPathParser(new CommonTokenStream(new XPathLexer(evalInput))).xq();
        } else {
            // Input needs no rewriting (e.g. <result>{...}, nested FLWR
            //Evaluate it directly with the general xq
            // grammar, and treat the rewrite as the query itself.
            rewriteFileContents = new String(
                    java.nio.file.Files.readAllBytes(new File(queryFilePath).toPath()));
            CharStream evalInput = CharStreams.fromFileName(queryFilePath);
            rewrittenTree =
                    new XPathParser(new CommonTokenStream(new XPathLexer(evalInput))).xq();
        }

        if (rewriteOutPath != null) {
            File rewriteFile = new File(rewriteOutPath);
            if (rewriteFile.getParentFile() != null) {
                rewriteFile.getParentFile().mkdirs();
            }
            try (PrintWriter pw = new PrintWriter(new FileWriter(rewriteFile))) {
                pw.print(rewriteFileContents);
            }
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document outDoc = builder.newDocument();
        XQueryContext ctx = new XQueryContext();
        ctx.env = new HashMap<>();

        List<Node> results = XQueryEvaluator.evaluate(rewrittenTree, ctx, outDoc, xmlFilePath);

        if (results.size() == 1 && results.get(0).getNodeType() == Node.ELEMENT_NODE) {
            outDoc.appendChild(outDoc.importNode(results.get(0), true));
        } else {
            Element root = outDoc.createElement("result");
            outDoc.appendChild(root);
            for (Node n : results) {
                root.appendChild(outDoc.importNode(n, true));
            }
        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

        File resultFile = new File(resultOutPath);
        if (resultFile.getParentFile() != null) {
            resultFile.getParentFile().mkdirs();
        }
        transformer.transform(new DOMSource(outDoc), new StreamResult(resultFile));
    }
}
