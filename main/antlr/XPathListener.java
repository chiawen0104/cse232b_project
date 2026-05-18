// Generated from XPath.g4 by ANTLR 4.13.2
package main.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XPathParser}.
 */
public interface XPathListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link XPathParser#eval}.
	 * @param ctx the parse tree
	 */
	void enterEval(XPathParser.EvalContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#eval}.
	 * @param ctx the parse tree
	 */
	void exitEval(XPathParser.EvalContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#additionExp}.
	 * @param ctx the parse tree
	 */
	void enterAdditionExp(XPathParser.AdditionExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#additionExp}.
	 * @param ctx the parse tree
	 */
	void exitAdditionExp(XPathParser.AdditionExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#multiplyExp}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyExp(XPathParser.MultiplyExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#multiplyExp}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyExp(XPathParser.MultiplyExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#atomExp}.
	 * @param ctx the parse tree
	 */
	void enterAtomExp(XPathParser.AtomExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#atomExp}.
	 * @param ctx the parse tree
	 */
	void exitAtomExp(XPathParser.AtomExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterAp(XPathParser.ApContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitAp(XPathParser.ApContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#fileName}.
	 * @param ctx the parse tree
	 */
	void enterFileName(XPathParser.FileNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#fileName}.
	 * @param ctx the parse tree
	 */
	void exitFileName(XPathParser.FileNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRp(XPathParser.RpContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRp(XPathParser.RpContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterF(XPathParser.FContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitF(XPathParser.FContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXq(XPathParser.XqContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXq(XPathParser.XqContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(XPathParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(XPathParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#forClause}.
	 * @param ctx the parse tree
	 */
	void enterForClause(XPathParser.ForClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#forClause}.
	 * @param ctx the parse tree
	 */
	void exitForClause(XPathParser.ForClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#varRepeat1}.
	 * @param ctx the parse tree
	 */
	void enterVarRepeat1(XPathParser.VarRepeat1Context ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#varRepeat1}.
	 * @param ctx the parse tree
	 */
	void exitVarRepeat1(XPathParser.VarRepeat1Context ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#letClause}.
	 * @param ctx the parse tree
	 */
	void enterLetClause(XPathParser.LetClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#letClause}.
	 * @param ctx the parse tree
	 */
	void exitLetClause(XPathParser.LetClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#varRepeat2}.
	 * @param ctx the parse tree
	 */
	void enterVarRepeat2(XPathParser.VarRepeat2Context ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#varRepeat2}.
	 * @param ctx the parse tree
	 */
	void exitVarRepeat2(XPathParser.VarRepeat2Context ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(XPathParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(XPathParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterReturnClause(XPathParser.ReturnClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitReturnClause(XPathParser.ReturnClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XPathParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(XPathParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link XPathParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(XPathParser.CondContext ctx);
}