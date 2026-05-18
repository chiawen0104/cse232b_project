// Generated from XPath.g4 by ANTLR 4.13.2
package main.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class XPathParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, Number=31, 
		WS=32, STRING=33, TAGNAME=34, ATTRNAME=35, COMMA=36, LT=37, GT=38, SLASH=39, 
		LBRACE=40, RBRACE=41, VAR=42;
	public static final int
		RULE_eval = 0, RULE_additionExp = 1, RULE_multiplyExp = 2, RULE_atomExp = 3, 
		RULE_ap = 4, RULE_fileName = 5, RULE_rp = 6, RULE_f = 7, RULE_xq = 8, 
		RULE_content = 9, RULE_forClause = 10, RULE_varRepeat1 = 11, RULE_letClause = 12, 
		RULE_varRepeat2 = 13, RULE_whereClause = 14, RULE_returnClause = 15, RULE_cond = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"eval", "additionExp", "multiplyExp", "atomExp", "ap", "fileName", "rp", 
			"f", "xq", "content", "forClause", "varRepeat1", "letClause", "varRepeat2", 
			"whereClause", "returnClause", "cond"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'", "'*'", "'('", "')'", "'doc'", "'//'", "'document'", 
			"'.'", "'..'", "'text'", "'@'", "'['", "']'", "'='", "'eq'", "'=='", 
			"'is'", "'and'", "'or'", "'not'", "'for'", "'in'", "'let'", "':='", "'where'", 
			"'return'", "'empty'", "'some'", "'satisfies'", null, null, null, null, 
			null, "','", "'<'", "'>'", "'/'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "Number", "WS", "STRING", "TAGNAME", 
			"ATTRNAME", "COMMA", "LT", "GT", "SLASH", "LBRACE", "RBRACE", "VAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "XPath.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public XPathParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EvalContext extends ParserRuleContext {
		public AdditionExpContext additionExp() {
			return getRuleContext(AdditionExpContext.class,0);
		}
		public EvalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eval; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterEval(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitEval(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitEval(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EvalContext eval() throws RecognitionException {
		EvalContext _localctx = new EvalContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_eval);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			additionExp();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AdditionExpContext extends ParserRuleContext {
		public List<MultiplyExpContext> multiplyExp() {
			return getRuleContexts(MultiplyExpContext.class);
		}
		public MultiplyExpContext multiplyExp(int i) {
			return getRuleContext(MultiplyExpContext.class,i);
		}
		public AdditionExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additionExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterAdditionExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitAdditionExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitAdditionExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditionExpContext additionExp() throws RecognitionException {
		AdditionExpContext _localctx = new AdditionExpContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_additionExp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			multiplyExp();
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==T__1) {
				{
				setState(41);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
					{
					setState(37);
					match(T__0);
					setState(38);
					multiplyExp();
					}
					break;
				case T__1:
					{
					setState(39);
					match(T__1);
					setState(40);
					multiplyExp();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MultiplyExpContext extends ParserRuleContext {
		public List<AtomExpContext> atomExp() {
			return getRuleContexts(AtomExpContext.class);
		}
		public AtomExpContext atomExp(int i) {
			return getRuleContext(AtomExpContext.class,i);
		}
		public List<TerminalNode> SLASH() { return getTokens(XPathParser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(XPathParser.SLASH, i);
		}
		public MultiplyExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplyExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterMultiplyExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitMultiplyExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitMultiplyExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplyExpContext multiplyExp() throws RecognitionException {
		MultiplyExpContext _localctx = new MultiplyExpContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_multiplyExp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			atomExp();
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2 || _la==SLASH) {
				{
				setState(51);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__2:
					{
					setState(47);
					match(T__2);
					setState(48);
					atomExp();
					}
					break;
				case SLASH:
					{
					setState(49);
					match(SLASH);
					setState(50);
					atomExp();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomExpContext extends ParserRuleContext {
		public TerminalNode Number() { return getToken(XPathParser.Number, 0); }
		public AdditionExpContext additionExp() {
			return getRuleContext(AdditionExpContext.class,0);
		}
		public AtomExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterAtomExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitAtomExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitAtomExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomExpContext atomExp() throws RecognitionException {
		AtomExpContext _localctx = new AtomExpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_atomExp);
		try {
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Number:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				match(Number);
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				match(T__3);
				setState(58);
				additionExp();
				setState(59);
				match(T__4);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ApContext extends ParserRuleContext {
		public FileNameContext fileName() {
			return getRuleContext(FileNameContext.class,0);
		}
		public TerminalNode SLASH() { return getToken(XPathParser.SLASH, 0); }
		public RpContext rp() {
			return getRuleContext(RpContext.class,0);
		}
		public ApContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ap; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterAp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitAp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitAp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApContext ap() throws RecognitionException {
		ApContext _localctx = new ApContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ap);
		try {
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				match(T__5);
				setState(64);
				match(T__3);
				setState(65);
				fileName();
				setState(66);
				match(T__4);
				setState(67);
				match(SLASH);
				setState(68);
				rp(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				match(T__5);
				setState(71);
				match(T__3);
				setState(72);
				fileName();
				setState(73);
				match(T__4);
				setState(74);
				match(T__6);
				setState(75);
				rp(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(77);
				match(T__7);
				setState(78);
				match(T__3);
				setState(79);
				fileName();
				setState(80);
				match(T__4);
				setState(81);
				match(T__6);
				setState(82);
				rp(0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(84);
				match(T__7);
				setState(85);
				match(T__3);
				setState(86);
				fileName();
				setState(87);
				match(T__4);
				setState(88);
				match(SLASH);
				setState(89);
				rp(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FileNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(XPathParser.STRING, 0); }
		public FileNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fileName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterFileName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitFileName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitFileName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileNameContext fileName() throws RecognitionException {
		FileNameContext _localctx = new FileNameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_fileName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RpContext extends ParserRuleContext {
		public TerminalNode TAGNAME() { return getToken(XPathParser.TAGNAME, 0); }
		public TerminalNode ATTRNAME() { return getToken(XPathParser.ATTRNAME, 0); }
		public List<RpContext> rp() {
			return getRuleContexts(RpContext.class);
		}
		public RpContext rp(int i) {
			return getRuleContext(RpContext.class,i);
		}
		public TerminalNode SLASH() { return getToken(XPathParser.SLASH, 0); }
		public TerminalNode COMMA() { return getToken(XPathParser.COMMA, 0); }
		public FContext f() {
			return getRuleContext(FContext.class,0);
		}
		public RpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterRp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitRp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitRp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RpContext rp() throws RecognitionException {
		return rp(0);
	}

	private RpContext rp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		RpContext _localctx = new RpContext(_ctx, _parentState);
		RpContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_rp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TAGNAME:
				{
				setState(96);
				match(TAGNAME);
				}
				break;
			case T__2:
				{
				setState(97);
				match(T__2);
				}
				break;
			case T__8:
				{
				setState(98);
				match(T__8);
				}
				break;
			case T__9:
				{
				setState(99);
				match(T__9);
				}
				break;
			case T__10:
				{
				setState(100);
				match(T__10);
				setState(101);
				match(T__3);
				setState(102);
				match(T__4);
				}
				break;
			case T__11:
				{
				setState(103);
				match(T__11);
				setState(104);
				match(ATTRNAME);
				}
				break;
			case T__3:
				{
				setState(105);
				match(T__3);
				setState(106);
				rp(0);
				setState(107);
				match(T__4);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(127);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(125);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new RpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_rp);
						setState(111);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(112);
						match(SLASH);
						setState(113);
						rp(5);
						}
						break;
					case 2:
						{
						_localctx = new RpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_rp);
						setState(114);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(115);
						match(T__6);
						setState(116);
						rp(4);
						}
						break;
					case 3:
						{
						_localctx = new RpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_rp);
						setState(117);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(118);
						match(COMMA);
						setState(119);
						rp(2);
						}
						break;
					case 4:
						{
						_localctx = new RpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_rp);
						setState(120);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(121);
						match(T__12);
						setState(122);
						f(0);
						setState(123);
						match(T__13);
						}
						break;
					}
					} 
				}
				setState(129);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FContext extends ParserRuleContext {
		public List<RpContext> rp() {
			return getRuleContexts(RpContext.class);
		}
		public RpContext rp(int i) {
			return getRuleContext(RpContext.class,i);
		}
		public TerminalNode STRING() { return getToken(XPathParser.STRING, 0); }
		public List<FContext> f() {
			return getRuleContexts(FContext.class);
		}
		public FContext f(int i) {
			return getRuleContext(FContext.class,i);
		}
		public FContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_f; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterF(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitF(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitF(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FContext f() throws RecognitionException {
		return f(0);
	}

	private FContext f(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FContext _localctx = new FContext(_ctx, _parentState);
		FContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_f, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(131);
				rp(0);
				}
				break;
			case 2:
				{
				setState(132);
				rp(0);
				setState(133);
				match(T__14);
				setState(134);
				rp(0);
				}
				break;
			case 3:
				{
				setState(136);
				rp(0);
				setState(137);
				match(T__15);
				setState(138);
				rp(0);
				}
				break;
			case 4:
				{
				setState(140);
				rp(0);
				setState(141);
				match(T__16);
				setState(142);
				rp(0);
				}
				break;
			case 5:
				{
				setState(144);
				rp(0);
				setState(145);
				match(T__17);
				setState(146);
				rp(0);
				}
				break;
			case 6:
				{
				setState(148);
				rp(0);
				setState(149);
				match(T__14);
				setState(150);
				match(STRING);
				}
				break;
			case 7:
				{
				setState(152);
				match(T__3);
				setState(153);
				f(0);
				setState(154);
				match(T__4);
				}
				break;
			case 8:
				{
				setState(156);
				match(T__20);
				setState(157);
				f(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(168);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(166);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new FContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_f);
						setState(160);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(161);
						match(T__18);
						setState(162);
						f(4);
						}
						break;
					case 2:
						{
						_localctx = new FContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_f);
						setState(163);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(164);
						match(T__19);
						setState(165);
						f(3);
						}
						break;
					}
					} 
				}
				setState(170);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class XqContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(XPathParser.VAR, 0); }
		public TerminalNode STRING() { return getToken(XPathParser.STRING, 0); }
		public ApContext ap() {
			return getRuleContext(ApContext.class,0);
		}
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public List<TerminalNode> LT() { return getTokens(XPathParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(XPathParser.LT, i);
		}
		public List<TerminalNode> TAGNAME() { return getTokens(XPathParser.TAGNAME); }
		public TerminalNode TAGNAME(int i) {
			return getToken(XPathParser.TAGNAME, i);
		}
		public List<TerminalNode> GT() { return getTokens(XPathParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(XPathParser.GT, i);
		}
		public TerminalNode LBRACE() { return getToken(XPathParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(XPathParser.RBRACE, 0); }
		public TerminalNode SLASH() { return getToken(XPathParser.SLASH, 0); }
		public ForClauseContext forClause() {
			return getRuleContext(ForClauseContext.class,0);
		}
		public ReturnClauseContext returnClause() {
			return getRuleContext(ReturnClauseContext.class,0);
		}
		public LetClauseContext letClause() {
			return getRuleContext(LetClauseContext.class,0);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(XPathParser.COMMA, 0); }
		public RpContext rp() {
			return getRuleContext(RpContext.class,0);
		}
		public XqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterXq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitXq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitXq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XqContext xq() throws RecognitionException {
		return xq(0);
	}

	private XqContext xq(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		XqContext _localctx = new XqContext(_ctx, _parentState);
		XqContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_xq, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				{
				setState(172);
				match(VAR);
				}
				break;
			case STRING:
				{
				setState(173);
				match(STRING);
				}
				break;
			case T__5:
			case T__7:
				{
				setState(174);
				ap();
				}
				break;
			case T__3:
				{
				setState(175);
				match(T__3);
				setState(176);
				xq(0);
				setState(177);
				match(T__4);
				}
				break;
			case LT:
				{
				setState(179);
				match(LT);
				setState(180);
				match(TAGNAME);
				setState(181);
				match(GT);
				setState(182);
				match(LBRACE);
				setState(183);
				xq(0);
				setState(184);
				match(RBRACE);
				setState(185);
				match(LT);
				setState(186);
				match(SLASH);
				setState(187);
				match(TAGNAME);
				setState(188);
				match(GT);
				}
				break;
			case T__21:
				{
				setState(190);
				forClause();
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23) {
					{
					setState(191);
					letClause();
					}
				}

				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__25) {
					{
					setState(194);
					whereClause();
					}
				}

				setState(197);
				returnClause();
				}
				break;
			case T__23:
				{
				setState(199);
				letClause();
				setState(200);
				xq(1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(215);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(213);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new XqContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_xq);
						setState(204);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(205);
						match(COMMA);
						setState(206);
						xq(7);
						}
						break;
					case 2:
						{
						_localctx = new XqContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_xq);
						setState(207);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(208);
						match(SLASH);
						setState(209);
						rp(0);
						}
						break;
					case 3:
						{
						_localctx = new XqContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_xq);
						setState(210);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(211);
						match(T__6);
						setState(212);
						rp(0);
						}
						break;
					}
					} 
				}
				setState(217);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContentContext extends ParserRuleContext {
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitContent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_content);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4544096371024L) != 0)) {
				{
				setState(218);
				xq(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForClauseContext extends ParserRuleContext {
		public List<VarRepeat1Context> varRepeat1() {
			return getRuleContexts(VarRepeat1Context.class);
		}
		public VarRepeat1Context varRepeat1(int i) {
			return getRuleContext(VarRepeat1Context.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(XPathParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(XPathParser.COMMA, i);
		}
		public ForClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterForClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitForClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitForClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForClauseContext forClause() throws RecognitionException {
		ForClauseContext _localctx = new ForClauseContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_forClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			match(T__21);
			setState(222);
			varRepeat1();
			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(223);
				match(COMMA);
				setState(224);
				varRepeat1();
				}
				}
				setState(229);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarRepeat1Context extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(XPathParser.VAR, 0); }
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public VarRepeat1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varRepeat1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterVarRepeat1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitVarRepeat1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitVarRepeat1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarRepeat1Context varRepeat1() throws RecognitionException {
		VarRepeat1Context _localctx = new VarRepeat1Context(_ctx, getState());
		enterRule(_localctx, 22, RULE_varRepeat1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			match(VAR);
			setState(231);
			match(T__22);
			setState(232);
			xq(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LetClauseContext extends ParserRuleContext {
		public List<VarRepeat2Context> varRepeat2() {
			return getRuleContexts(VarRepeat2Context.class);
		}
		public VarRepeat2Context varRepeat2(int i) {
			return getRuleContext(VarRepeat2Context.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(XPathParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(XPathParser.COMMA, i);
		}
		public LetClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterLetClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitLetClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitLetClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetClauseContext letClause() throws RecognitionException {
		LetClauseContext _localctx = new LetClauseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_letClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			match(T__23);
			setState(235);
			varRepeat2();
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(236);
				match(COMMA);
				setState(237);
				varRepeat2();
				}
				}
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarRepeat2Context extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(XPathParser.VAR, 0); }
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public VarRepeat2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varRepeat2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterVarRepeat2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitVarRepeat2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitVarRepeat2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarRepeat2Context varRepeat2() throws RecognitionException {
		VarRepeat2Context _localctx = new VarRepeat2Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_varRepeat2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			match(VAR);
			setState(244);
			match(T__24);
			setState(245);
			xq(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhereClauseContext extends ParserRuleContext {
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public WhereClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterWhereClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitWhereClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitWhereClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereClauseContext whereClause() throws RecognitionException {
		WhereClauseContext _localctx = new WhereClauseContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_whereClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(T__25);
			setState(248);
			cond(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnClauseContext extends ParserRuleContext {
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public ReturnClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterReturnClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitReturnClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitReturnClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnClauseContext returnClause() throws RecognitionException {
		ReturnClauseContext _localctx = new ReturnClauseContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_returnClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			match(T__26);
			setState(251);
			xq(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CondContext extends ParserRuleContext {
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public List<VarRepeat1Context> varRepeat1() {
			return getRuleContexts(VarRepeat1Context.class);
		}
		public VarRepeat1Context varRepeat1(int i) {
			return getRuleContext(VarRepeat1Context.class,i);
		}
		public List<CondContext> cond() {
			return getRuleContexts(CondContext.class);
		}
		public CondContext cond(int i) {
			return getRuleContext(CondContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(XPathParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(XPathParser.COMMA, i);
		}
		public CondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitCond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondContext cond() throws RecognitionException {
		return cond(0);
	}

	private CondContext cond(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CondContext _localctx = new CondContext(_ctx, _parentState);
		CondContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_cond, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(254);
				xq(0);
				setState(255);
				match(T__14);
				setState(256);
				xq(0);
				}
				break;
			case 2:
				{
				setState(258);
				xq(0);
				setState(259);
				match(T__15);
				setState(260);
				xq(0);
				}
				break;
			case 3:
				{
				setState(262);
				xq(0);
				setState(263);
				match(T__16);
				setState(264);
				xq(0);
				}
				break;
			case 4:
				{
				setState(266);
				xq(0);
				setState(267);
				match(T__17);
				setState(268);
				xq(0);
				}
				break;
			case 5:
				{
				setState(270);
				match(T__27);
				setState(271);
				match(T__3);
				setState(272);
				xq(0);
				setState(273);
				match(T__4);
				}
				break;
			case 6:
				{
				setState(275);
				match(T__28);
				setState(276);
				varRepeat1();
				setState(281);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(277);
					match(COMMA);
					setState(278);
					varRepeat1();
					}
					}
					setState(283);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(284);
				match(T__29);
				setState(285);
				cond(5);
				}
				break;
			case 7:
				{
				setState(287);
				match(T__3);
				setState(288);
				cond(0);
				setState(289);
				match(T__4);
				}
				break;
			case 8:
				{
				setState(291);
				match(T__20);
				setState(292);
				cond(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(303);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(301);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new CondContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_cond);
						setState(295);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(296);
						match(T__18);
						setState(297);
						cond(4);
						}
						break;
					case 2:
						{
						_localctx = new CondContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_cond);
						setState(298);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(299);
						match(T__19);
						setState(300);
						cond(3);
						}
						break;
					}
					} 
				}
				setState(305);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return rp_sempred((RpContext)_localctx, predIndex);
		case 7:
			return f_sempred((FContext)_localctx, predIndex);
		case 8:
			return xq_sempred((XqContext)_localctx, predIndex);
		case 16:
			return cond_sempred((CondContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean rp_sempred(RpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 1);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean f_sempred(FContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean xq_sempred(XqContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 6);
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean cond_sempred(CondContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return precpred(_ctx, 3);
		case 10:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001*\u0133\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001*\b\u0001\n\u0001\f\u0001"+
		"-\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0005\u00024\b\u0002\n\u0002\f\u00027\t\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003>\b\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\\\b\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006n\b\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0005\u0006~\b\u0006\n\u0006\f\u0006\u0081\t\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u009f\b\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005"+
		"\u0007\u00a7\b\u0007\n\u0007\f\u0007\u00aa\t\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0003\b\u00c1\b\b\u0001\b\u0003\b\u00c4\b\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0003\b\u00cb\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005\b\u00d6\b\b\n\b\f\b\u00d9\t\b"+
		"\u0001\t\u0003\t\u00dc\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n\u00e2"+
		"\b\n\n\n\f\n\u00e5\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u00ef\b\f\n\f\f\f\u00f2\t\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u0118\b\u0010"+
		"\n\u0010\f\u0010\u011b\t\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003"+
		"\u0010\u0126\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0005\u0010\u012e\b\u0010\n\u0010\f\u0010\u0131\t\u0010"+
		"\u0001\u0010\u0000\u0004\f\u000e\u0010 \u0011\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \u0000\u0000"+
		"\u0154\u0000\"\u0001\u0000\u0000\u0000\u0002$\u0001\u0000\u0000\u0000"+
		"\u0004.\u0001\u0000\u0000\u0000\u0006=\u0001\u0000\u0000\u0000\b[\u0001"+
		"\u0000\u0000\u0000\n]\u0001\u0000\u0000\u0000\fm\u0001\u0000\u0000\u0000"+
		"\u000e\u009e\u0001\u0000\u0000\u0000\u0010\u00ca\u0001\u0000\u0000\u0000"+
		"\u0012\u00db\u0001\u0000\u0000\u0000\u0014\u00dd\u0001\u0000\u0000\u0000"+
		"\u0016\u00e6\u0001\u0000\u0000\u0000\u0018\u00ea\u0001\u0000\u0000\u0000"+
		"\u001a\u00f3\u0001\u0000\u0000\u0000\u001c\u00f7\u0001\u0000\u0000\u0000"+
		"\u001e\u00fa\u0001\u0000\u0000\u0000 \u0125\u0001\u0000\u0000\u0000\""+
		"#\u0003\u0002\u0001\u0000#\u0001\u0001\u0000\u0000\u0000$+\u0003\u0004"+
		"\u0002\u0000%&\u0005\u0001\u0000\u0000&*\u0003\u0004\u0002\u0000\'(\u0005"+
		"\u0002\u0000\u0000(*\u0003\u0004\u0002\u0000)%\u0001\u0000\u0000\u0000"+
		")\'\u0001\u0000\u0000\u0000*-\u0001\u0000\u0000\u0000+)\u0001\u0000\u0000"+
		"\u0000+,\u0001\u0000\u0000\u0000,\u0003\u0001\u0000\u0000\u0000-+\u0001"+
		"\u0000\u0000\u0000.5\u0003\u0006\u0003\u0000/0\u0005\u0003\u0000\u0000"+
		"04\u0003\u0006\u0003\u000012\u0005\'\u0000\u000024\u0003\u0006\u0003\u0000"+
		"3/\u0001\u0000\u0000\u000031\u0001\u0000\u0000\u000047\u0001\u0000\u0000"+
		"\u000053\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u00006\u0005\u0001"+
		"\u0000\u0000\u000075\u0001\u0000\u0000\u00008>\u0005\u001f\u0000\u0000"+
		"9:\u0005\u0004\u0000\u0000:;\u0003\u0002\u0001\u0000;<\u0005\u0005\u0000"+
		"\u0000<>\u0001\u0000\u0000\u0000=8\u0001\u0000\u0000\u0000=9\u0001\u0000"+
		"\u0000\u0000>\u0007\u0001\u0000\u0000\u0000?@\u0005\u0006\u0000\u0000"+
		"@A\u0005\u0004\u0000\u0000AB\u0003\n\u0005\u0000BC\u0005\u0005\u0000\u0000"+
		"CD\u0005\'\u0000\u0000DE\u0003\f\u0006\u0000E\\\u0001\u0000\u0000\u0000"+
		"FG\u0005\u0006\u0000\u0000GH\u0005\u0004\u0000\u0000HI\u0003\n\u0005\u0000"+
		"IJ\u0005\u0005\u0000\u0000JK\u0005\u0007\u0000\u0000KL\u0003\f\u0006\u0000"+
		"L\\\u0001\u0000\u0000\u0000MN\u0005\b\u0000\u0000NO\u0005\u0004\u0000"+
		"\u0000OP\u0003\n\u0005\u0000PQ\u0005\u0005\u0000\u0000QR\u0005\u0007\u0000"+
		"\u0000RS\u0003\f\u0006\u0000S\\\u0001\u0000\u0000\u0000TU\u0005\b\u0000"+
		"\u0000UV\u0005\u0004\u0000\u0000VW\u0003\n\u0005\u0000WX\u0005\u0005\u0000"+
		"\u0000XY\u0005\'\u0000\u0000YZ\u0003\f\u0006\u0000Z\\\u0001\u0000\u0000"+
		"\u0000[?\u0001\u0000\u0000\u0000[F\u0001\u0000\u0000\u0000[M\u0001\u0000"+
		"\u0000\u0000[T\u0001\u0000\u0000\u0000\\\t\u0001\u0000\u0000\u0000]^\u0005"+
		"!\u0000\u0000^\u000b\u0001\u0000\u0000\u0000_`\u0006\u0006\uffff\uffff"+
		"\u0000`n\u0005\"\u0000\u0000an\u0005\u0003\u0000\u0000bn\u0005\t\u0000"+
		"\u0000cn\u0005\n\u0000\u0000de\u0005\u000b\u0000\u0000ef\u0005\u0004\u0000"+
		"\u0000fn\u0005\u0005\u0000\u0000gh\u0005\f\u0000\u0000hn\u0005#\u0000"+
		"\u0000ij\u0005\u0004\u0000\u0000jk\u0003\f\u0006\u0000kl\u0005\u0005\u0000"+
		"\u0000ln\u0001\u0000\u0000\u0000m_\u0001\u0000\u0000\u0000ma\u0001\u0000"+
		"\u0000\u0000mb\u0001\u0000\u0000\u0000mc\u0001\u0000\u0000\u0000md\u0001"+
		"\u0000\u0000\u0000mg\u0001\u0000\u0000\u0000mi\u0001\u0000\u0000\u0000"+
		"n\u007f\u0001\u0000\u0000\u0000op\n\u0004\u0000\u0000pq\u0005\'\u0000"+
		"\u0000q~\u0003\f\u0006\u0005rs\n\u0003\u0000\u0000st\u0005\u0007\u0000"+
		"\u0000t~\u0003\f\u0006\u0004uv\n\u0001\u0000\u0000vw\u0005$\u0000\u0000"+
		"w~\u0003\f\u0006\u0002xy\n\u0002\u0000\u0000yz\u0005\r\u0000\u0000z{\u0003"+
		"\u000e\u0007\u0000{|\u0005\u000e\u0000\u0000|~\u0001\u0000\u0000\u0000"+
		"}o\u0001\u0000\u0000\u0000}r\u0001\u0000\u0000\u0000}u\u0001\u0000\u0000"+
		"\u0000}x\u0001\u0000\u0000\u0000~\u0081\u0001\u0000\u0000\u0000\u007f"+
		"}\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080\r"+
		"\u0001\u0000\u0000\u0000\u0081\u007f\u0001\u0000\u0000\u0000\u0082\u0083"+
		"\u0006\u0007\uffff\uffff\u0000\u0083\u009f\u0003\f\u0006\u0000\u0084\u0085"+
		"\u0003\f\u0006\u0000\u0085\u0086\u0005\u000f\u0000\u0000\u0086\u0087\u0003"+
		"\f\u0006\u0000\u0087\u009f\u0001\u0000\u0000\u0000\u0088\u0089\u0003\f"+
		"\u0006\u0000\u0089\u008a\u0005\u0010\u0000\u0000\u008a\u008b\u0003\f\u0006"+
		"\u0000\u008b\u009f\u0001\u0000\u0000\u0000\u008c\u008d\u0003\f\u0006\u0000"+
		"\u008d\u008e\u0005\u0011\u0000\u0000\u008e\u008f\u0003\f\u0006\u0000\u008f"+
		"\u009f\u0001\u0000\u0000\u0000\u0090\u0091\u0003\f\u0006\u0000\u0091\u0092"+
		"\u0005\u0012\u0000\u0000\u0092\u0093\u0003\f\u0006\u0000\u0093\u009f\u0001"+
		"\u0000\u0000\u0000\u0094\u0095\u0003\f\u0006\u0000\u0095\u0096\u0005\u000f"+
		"\u0000\u0000\u0096\u0097\u0005!\u0000\u0000\u0097\u009f\u0001\u0000\u0000"+
		"\u0000\u0098\u0099\u0005\u0004\u0000\u0000\u0099\u009a\u0003\u000e\u0007"+
		"\u0000\u009a\u009b\u0005\u0005\u0000\u0000\u009b\u009f\u0001\u0000\u0000"+
		"\u0000\u009c\u009d\u0005\u0015\u0000\u0000\u009d\u009f\u0003\u000e\u0007"+
		"\u0001\u009e\u0082\u0001\u0000\u0000\u0000\u009e\u0084\u0001\u0000\u0000"+
		"\u0000\u009e\u0088\u0001\u0000\u0000\u0000\u009e\u008c\u0001\u0000\u0000"+
		"\u0000\u009e\u0090\u0001\u0000\u0000\u0000\u009e\u0094\u0001\u0000\u0000"+
		"\u0000\u009e\u0098\u0001\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000"+
		"\u0000\u009f\u00a8\u0001\u0000\u0000\u0000\u00a0\u00a1\n\u0003\u0000\u0000"+
		"\u00a1\u00a2\u0005\u0013\u0000\u0000\u00a2\u00a7\u0003\u000e\u0007\u0004"+
		"\u00a3\u00a4\n\u0002\u0000\u0000\u00a4\u00a5\u0005\u0014\u0000\u0000\u00a5"+
		"\u00a7\u0003\u000e\u0007\u0003\u00a6\u00a0\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a7\u00aa\u0001\u0000\u0000\u0000\u00a8"+
		"\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9"+
		"\u000f\u0001\u0000\u0000\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00ab"+
		"\u00ac\u0006\b\uffff\uffff\u0000\u00ac\u00cb\u0005*\u0000\u0000\u00ad"+
		"\u00cb\u0005!\u0000\u0000\u00ae\u00cb\u0003\b\u0004\u0000\u00af\u00b0"+
		"\u0005\u0004\u0000\u0000\u00b0\u00b1\u0003\u0010\b\u0000\u00b1\u00b2\u0005"+
		"\u0005\u0000\u0000\u00b2\u00cb\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005"+
		"%\u0000\u0000\u00b4\u00b5\u0005\"\u0000\u0000\u00b5\u00b6\u0005&\u0000"+
		"\u0000\u00b6\u00b7\u0005(\u0000\u0000\u00b7\u00b8\u0003\u0010\b\u0000"+
		"\u00b8\u00b9\u0005)\u0000\u0000\u00b9\u00ba\u0005%\u0000\u0000\u00ba\u00bb"+
		"\u0005\'\u0000\u0000\u00bb\u00bc\u0005\"\u0000\u0000\u00bc\u00bd\u0005"+
		"&\u0000\u0000\u00bd\u00cb\u0001\u0000\u0000\u0000\u00be\u00c0\u0003\u0014"+
		"\n\u0000\u00bf\u00c1\u0003\u0018\f\u0000\u00c0\u00bf\u0001\u0000\u0000"+
		"\u0000\u00c0\u00c1\u0001\u0000\u0000\u0000\u00c1\u00c3\u0001\u0000\u0000"+
		"\u0000\u00c2\u00c4\u0003\u001c\u000e\u0000\u00c3\u00c2\u0001\u0000\u0000"+
		"\u0000\u00c3\u00c4\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000"+
		"\u0000\u00c5\u00c6\u0003\u001e\u000f\u0000\u00c6\u00cb\u0001\u0000\u0000"+
		"\u0000\u00c7\u00c8\u0003\u0018\f\u0000\u00c8\u00c9\u0003\u0010\b\u0001"+
		"\u00c9\u00cb\u0001\u0000\u0000\u0000\u00ca\u00ab\u0001\u0000\u0000\u0000"+
		"\u00ca\u00ad\u0001\u0000\u0000\u0000\u00ca\u00ae\u0001\u0000\u0000\u0000"+
		"\u00ca\u00af\u0001\u0000\u0000\u0000\u00ca\u00b3\u0001\u0000\u0000\u0000"+
		"\u00ca\u00be\u0001\u0000\u0000\u0000\u00ca\u00c7\u0001\u0000\u0000\u0000"+
		"\u00cb\u00d7\u0001\u0000\u0000\u0000\u00cc\u00cd\n\u0006\u0000\u0000\u00cd"+
		"\u00ce\u0005$\u0000\u0000\u00ce\u00d6\u0003\u0010\b\u0007\u00cf\u00d0"+
		"\n\u0005\u0000\u0000\u00d0\u00d1\u0005\'\u0000\u0000\u00d1\u00d6\u0003"+
		"\f\u0006\u0000\u00d2\u00d3\n\u0004\u0000\u0000\u00d3\u00d4\u0005\u0007"+
		"\u0000\u0000\u00d4\u00d6\u0003\f\u0006\u0000\u00d5\u00cc\u0001\u0000\u0000"+
		"\u0000\u00d5\u00cf\u0001\u0000\u0000\u0000\u00d5\u00d2\u0001\u0000\u0000"+
		"\u0000\u00d6\u00d9\u0001\u0000\u0000\u0000\u00d7\u00d5\u0001\u0000\u0000"+
		"\u0000\u00d7\u00d8\u0001\u0000\u0000\u0000\u00d8\u0011\u0001\u0000\u0000"+
		"\u0000\u00d9\u00d7\u0001\u0000\u0000\u0000\u00da\u00dc\u0003\u0010\b\u0000"+
		"\u00db\u00da\u0001\u0000\u0000\u0000\u00db\u00dc\u0001\u0000\u0000\u0000"+
		"\u00dc\u0013\u0001\u0000\u0000\u0000\u00dd\u00de\u0005\u0016\u0000\u0000"+
		"\u00de\u00e3\u0003\u0016\u000b\u0000\u00df\u00e0\u0005$\u0000\u0000\u00e0"+
		"\u00e2\u0003\u0016\u000b\u0000\u00e1\u00df\u0001\u0000\u0000\u0000\u00e2"+
		"\u00e5\u0001\u0000\u0000\u0000\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e3"+
		"\u00e4\u0001\u0000\u0000\u0000\u00e4\u0015\u0001\u0000\u0000\u0000\u00e5"+
		"\u00e3\u0001\u0000\u0000\u0000\u00e6\u00e7\u0005*\u0000\u0000\u00e7\u00e8"+
		"\u0005\u0017\u0000\u0000\u00e8\u00e9\u0003\u0010\b\u0000\u00e9\u0017\u0001"+
		"\u0000\u0000\u0000\u00ea\u00eb\u0005\u0018\u0000\u0000\u00eb\u00f0\u0003"+
		"\u001a\r\u0000\u00ec\u00ed\u0005$\u0000\u0000\u00ed\u00ef\u0003\u001a"+
		"\r\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ef\u00f2\u0001\u0000\u0000"+
		"\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000"+
		"\u0000\u00f1\u0019\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000\u0000"+
		"\u0000\u00f3\u00f4\u0005*\u0000\u0000\u00f4\u00f5\u0005\u0019\u0000\u0000"+
		"\u00f5\u00f6\u0003\u0010\b\u0000\u00f6\u001b\u0001\u0000\u0000\u0000\u00f7"+
		"\u00f8\u0005\u001a\u0000\u0000\u00f8\u00f9\u0003 \u0010\u0000\u00f9\u001d"+
		"\u0001\u0000\u0000\u0000\u00fa\u00fb\u0005\u001b\u0000\u0000\u00fb\u00fc"+
		"\u0003\u0010\b\u0000\u00fc\u001f\u0001\u0000\u0000\u0000\u00fd\u00fe\u0006"+
		"\u0010\uffff\uffff\u0000\u00fe\u00ff\u0003\u0010\b\u0000\u00ff\u0100\u0005"+
		"\u000f\u0000\u0000\u0100\u0101\u0003\u0010\b\u0000\u0101\u0126\u0001\u0000"+
		"\u0000\u0000\u0102\u0103\u0003\u0010\b\u0000\u0103\u0104\u0005\u0010\u0000"+
		"\u0000\u0104\u0105\u0003\u0010\b\u0000\u0105\u0126\u0001\u0000\u0000\u0000"+
		"\u0106\u0107\u0003\u0010\b\u0000\u0107\u0108\u0005\u0011\u0000\u0000\u0108"+
		"\u0109\u0003\u0010\b\u0000\u0109\u0126\u0001\u0000\u0000\u0000\u010a\u010b"+
		"\u0003\u0010\b\u0000\u010b\u010c\u0005\u0012\u0000\u0000\u010c\u010d\u0003"+
		"\u0010\b\u0000\u010d\u0126\u0001\u0000\u0000\u0000\u010e\u010f\u0005\u001c"+
		"\u0000\u0000\u010f\u0110\u0005\u0004\u0000\u0000\u0110\u0111\u0003\u0010"+
		"\b\u0000\u0111\u0112\u0005\u0005\u0000\u0000\u0112\u0126\u0001\u0000\u0000"+
		"\u0000\u0113\u0114\u0005\u001d\u0000\u0000\u0114\u0119\u0003\u0016\u000b"+
		"\u0000\u0115\u0116\u0005$\u0000\u0000\u0116\u0118\u0003\u0016\u000b\u0000"+
		"\u0117\u0115\u0001\u0000\u0000\u0000\u0118\u011b\u0001\u0000\u0000\u0000"+
		"\u0119\u0117\u0001\u0000\u0000\u0000\u0119\u011a\u0001\u0000\u0000\u0000"+
		"\u011a\u011c\u0001\u0000\u0000\u0000\u011b\u0119\u0001\u0000\u0000\u0000"+
		"\u011c\u011d\u0005\u001e\u0000\u0000\u011d\u011e\u0003 \u0010\u0005\u011e"+
		"\u0126\u0001\u0000\u0000\u0000\u011f\u0120\u0005\u0004\u0000\u0000\u0120"+
		"\u0121\u0003 \u0010\u0000\u0121\u0122\u0005\u0005\u0000\u0000\u0122\u0126"+
		"\u0001\u0000\u0000\u0000\u0123\u0124\u0005\u0015\u0000\u0000\u0124\u0126"+
		"\u0003 \u0010\u0001\u0125\u00fd\u0001\u0000\u0000\u0000\u0125\u0102\u0001"+
		"\u0000\u0000\u0000\u0125\u0106\u0001\u0000\u0000\u0000\u0125\u010a\u0001"+
		"\u0000\u0000\u0000\u0125\u010e\u0001\u0000\u0000\u0000\u0125\u0113\u0001"+
		"\u0000\u0000\u0000\u0125\u011f\u0001\u0000\u0000\u0000\u0125\u0123\u0001"+
		"\u0000\u0000\u0000\u0126\u012f\u0001\u0000\u0000\u0000\u0127\u0128\n\u0003"+
		"\u0000\u0000\u0128\u0129\u0005\u0013\u0000\u0000\u0129\u012e\u0003 \u0010"+
		"\u0004\u012a\u012b\n\u0002\u0000\u0000\u012b\u012c\u0005\u0014\u0000\u0000"+
		"\u012c\u012e\u0003 \u0010\u0003\u012d\u0127\u0001\u0000\u0000\u0000\u012d"+
		"\u012a\u0001\u0000\u0000\u0000\u012e\u0131\u0001\u0000\u0000\u0000\u012f"+
		"\u012d\u0001\u0000\u0000\u0000\u012f\u0130\u0001\u0000\u0000\u0000\u0130"+
		"!\u0001\u0000\u0000\u0000\u0131\u012f\u0001\u0000\u0000\u0000\u0018)+"+
		"35=[m}\u007f\u009e\u00a6\u00a8\u00c0\u00c3\u00ca\u00d5\u00d7\u00db\u00e3"+
		"\u00f0\u0119\u0125\u012d\u012f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}