// Generated from XPath.g4 by ANTLR 4.13.2
package main.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link XPathParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface XPathVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link XPathParser#eval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval(XPathParser.EvalContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#additionExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionExp(XPathParser.AdditionExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#multiplyExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplyExp(XPathParser.MultiplyExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#atomExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomExp(XPathParser.AtomExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAp(XPathParser.ApContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#fileName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileName(XPathParser.FileNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRp(XPathParser.RpContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitF(XPathParser.FContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXq(XPathParser.XqContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContent(XPathParser.ContentContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#forClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForClause(XPathParser.ForClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#varRepeat1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarRepeat1(XPathParser.VarRepeat1Context ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#letClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetClause(XPathParser.LetClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#varRepeat2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarRepeat2(XPathParser.VarRepeat2Context ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#whereClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereClause(XPathParser.WhereClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#returnClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnClause(XPathParser.ReturnClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XPathParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(XPathParser.CondContext ctx);
}