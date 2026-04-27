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
		T__17=18, T__18=19, T__19=20, StringConstant=21, NAME=22, WS=23;
	public static final int
		RULE_ap = 0, RULE_fileName = 1, RULE_rp = 2, RULE_f = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"ap", "fileName", "rp", "f"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'doc'", "'('", "')'", "'/'", "'//'", "','", "'['", "']'", "'*'", 
			"'.'", "'..'", "'text'", "'@'", "'or'", "'and'", "'not'", "'='", "'eq'", 
			"'=='", "'is'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "StringConstant", 
			"NAME", "WS"
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
	public static class ApContext extends ParserRuleContext {
		public FileNameContext fileName() {
			return getRuleContext(FileNameContext.class,0);
		}
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
		enterRule(_localctx, 0, RULE_ap);
		try {
			setState(22);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(8);
				match(T__0);
				setState(9);
				match(T__1);
				setState(10);
				fileName();
				setState(11);
				match(T__2);
				setState(12);
				match(T__3);
				setState(13);
				rp(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(15);
				match(T__0);
				setState(16);
				match(T__1);
				setState(17);
				fileName();
				setState(18);
				match(T__2);
				setState(19);
				match(T__4);
				setState(20);
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
		public TerminalNode StringConstant() { return getToken(XPathParser.StringConstant, 0); }
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
		enterRule(_localctx, 2, RULE_fileName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(StringConstant);
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
		public TerminalNode NAME() { return getToken(XPathParser.NAME, 0); }
		public List<RpContext> rp() {
			return getRuleContexts(RpContext.class);
		}
		public RpContext rp(int i) {
			return getRuleContext(RpContext.class,i);
		}
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
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_rp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				{
				setState(27);
				match(NAME);
				}
				break;
			case T__8:
				{
				setState(28);
				match(T__8);
				}
				break;
			case T__9:
				{
				setState(29);
				match(T__9);
				}
				break;
			case T__10:
				{
				setState(30);
				match(T__10);
				}
				break;
			case T__11:
				{
				setState(31);
				match(T__11);
				setState(32);
				match(T__1);
				setState(33);
				match(T__2);
				}
				break;
			case T__12:
				{
				setState(34);
				match(T__12);
				setState(35);
				match(NAME);
				}
				break;
			case T__1:
				{
				setState(36);
				match(T__1);
				setState(37);
				rp(0);
				setState(38);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(58);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(56);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						_localctx = new RpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_rp);
						setState(42);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(43);
						match(T__5);
						setState(44);
						rp(12);
						}
						break;
					case 2:
						{
						_localctx = new RpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_rp);
						setState(45);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(46);
						match(T__3);
						setState(47);
						rp(11);
						}
						break;
					case 3:
						{
						_localctx = new RpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_rp);
						setState(48);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(49);
						match(T__4);
						setState(50);
						rp(10);
						}
						break;
					case 4:
						{
						_localctx = new RpContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_rp);
						setState(51);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(52);
						match(T__6);
						setState(53);
						f(0);
						setState(54);
						match(T__7);
						}
						break;
					}
					} 
				}
				setState(60);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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
		public List<FContext> f() {
			return getRuleContexts(FContext.class);
		}
		public FContext f(int i) {
			return getRuleContext(FContext.class,i);
		}
		public List<RpContext> rp() {
			return getRuleContexts(RpContext.class);
		}
		public RpContext rp(int i) {
			return getRuleContext(RpContext.class,i);
		}
		public TerminalNode StringConstant() { return getToken(XPathParser.StringConstant, 0); }
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
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_f, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(62);
				match(T__15);
				setState(63);
				f(8);
				}
				break;
			case 2:
				{
				setState(64);
				rp(0);
				setState(65);
				match(T__16);
				setState(66);
				match(StringConstant);
				}
				break;
			case 3:
				{
				setState(68);
				rp(0);
				setState(69);
				match(T__16);
				setState(70);
				rp(0);
				}
				break;
			case 4:
				{
				setState(72);
				rp(0);
				setState(73);
				match(T__17);
				setState(74);
				rp(0);
				}
				break;
			case 5:
				{
				setState(76);
				rp(0);
				setState(77);
				match(T__18);
				setState(78);
				rp(0);
				}
				break;
			case 6:
				{
				setState(80);
				rp(0);
				setState(81);
				match(T__19);
				setState(82);
				rp(0);
				}
				break;
			case 7:
				{
				setState(84);
				rp(0);
				}
				break;
			case 8:
				{
				setState(85);
				match(T__1);
				setState(86);
				f(0);
				setState(87);
				match(T__2);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(99);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(97);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new FContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_f);
						setState(91);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(92);
						match(T__13);
						setState(93);
						f(11);
						}
						break;
					case 2:
						{
						_localctx = new FContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_f);
						setState(94);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(95);
						match(T__14);
						setState(96);
						f(10);
						}
						break;
					}
					} 
				}
				setState(101);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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
		case 2:
			return rp_sempred((RpContext)_localctx, predIndex);
		case 3:
			return f_sempred((FContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean rp_sempred(RpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		}
		return true;
	}
	private boolean f_sempred(FContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 10);
		case 5:
			return precpred(_ctx, 9);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0017g\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003"+
		"\u0000\u0017\b\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003"+
		"\u0002)\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u00029\b\u0002\n\u0002"+
		"\f\u0002<\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u0003Z\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0005\u0003b\b\u0003\n\u0003\f\u0003e\t\u0003\u0001"+
		"\u0003\u0000\u0002\u0004\u0006\u0004\u0000\u0002\u0004\u0006\u0000\u0000"+
		"v\u0000\u0016\u0001\u0000\u0000\u0000\u0002\u0018\u0001\u0000\u0000\u0000"+
		"\u0004(\u0001\u0000\u0000\u0000\u0006Y\u0001\u0000\u0000\u0000\b\t\u0005"+
		"\u0001\u0000\u0000\t\n\u0005\u0002\u0000\u0000\n\u000b\u0003\u0002\u0001"+
		"\u0000\u000b\f\u0005\u0003\u0000\u0000\f\r\u0005\u0004\u0000\u0000\r\u000e"+
		"\u0003\u0004\u0002\u0000\u000e\u0017\u0001\u0000\u0000\u0000\u000f\u0010"+
		"\u0005\u0001\u0000\u0000\u0010\u0011\u0005\u0002\u0000\u0000\u0011\u0012"+
		"\u0003\u0002\u0001\u0000\u0012\u0013\u0005\u0003\u0000\u0000\u0013\u0014"+
		"\u0005\u0005\u0000\u0000\u0014\u0015\u0003\u0004\u0002\u0000\u0015\u0017"+
		"\u0001\u0000\u0000\u0000\u0016\b\u0001\u0000\u0000\u0000\u0016\u000f\u0001"+
		"\u0000\u0000\u0000\u0017\u0001\u0001\u0000\u0000\u0000\u0018\u0019\u0005"+
		"\u0015\u0000\u0000\u0019\u0003\u0001\u0000\u0000\u0000\u001a\u001b\u0006"+
		"\u0002\uffff\uffff\u0000\u001b)\u0005\u0016\u0000\u0000\u001c)\u0005\t"+
		"\u0000\u0000\u001d)\u0005\n\u0000\u0000\u001e)\u0005\u000b\u0000\u0000"+
		"\u001f \u0005\f\u0000\u0000 !\u0005\u0002\u0000\u0000!)\u0005\u0003\u0000"+
		"\u0000\"#\u0005\r\u0000\u0000#)\u0005\u0016\u0000\u0000$%\u0005\u0002"+
		"\u0000\u0000%&\u0003\u0004\u0002\u0000&\'\u0005\u0003\u0000\u0000\')\u0001"+
		"\u0000\u0000\u0000(\u001a\u0001\u0000\u0000\u0000(\u001c\u0001\u0000\u0000"+
		"\u0000(\u001d\u0001\u0000\u0000\u0000(\u001e\u0001\u0000\u0000\u0000("+
		"\u001f\u0001\u0000\u0000\u0000(\"\u0001\u0000\u0000\u0000($\u0001\u0000"+
		"\u0000\u0000):\u0001\u0000\u0000\u0000*+\n\u000b\u0000\u0000+,\u0005\u0006"+
		"\u0000\u0000,9\u0003\u0004\u0002\f-.\n\n\u0000\u0000./\u0005\u0004\u0000"+
		"\u0000/9\u0003\u0004\u0002\u000b01\n\t\u0000\u000012\u0005\u0005\u0000"+
		"\u000029\u0003\u0004\u0002\n34\n\b\u0000\u000045\u0005\u0007\u0000\u0000"+
		"56\u0003\u0006\u0003\u000067\u0005\b\u0000\u000079\u0001\u0000\u0000\u0000"+
		"8*\u0001\u0000\u0000\u00008-\u0001\u0000\u0000\u000080\u0001\u0000\u0000"+
		"\u000083\u0001\u0000\u0000\u00009<\u0001\u0000\u0000\u0000:8\u0001\u0000"+
		"\u0000\u0000:;\u0001\u0000\u0000\u0000;\u0005\u0001\u0000\u0000\u0000"+
		"<:\u0001\u0000\u0000\u0000=>\u0006\u0003\uffff\uffff\u0000>?\u0005\u0010"+
		"\u0000\u0000?Z\u0003\u0006\u0003\b@A\u0003\u0004\u0002\u0000AB\u0005\u0011"+
		"\u0000\u0000BC\u0005\u0015\u0000\u0000CZ\u0001\u0000\u0000\u0000DE\u0003"+
		"\u0004\u0002\u0000EF\u0005\u0011\u0000\u0000FG\u0003\u0004\u0002\u0000"+
		"GZ\u0001\u0000\u0000\u0000HI\u0003\u0004\u0002\u0000IJ\u0005\u0012\u0000"+
		"\u0000JK\u0003\u0004\u0002\u0000KZ\u0001\u0000\u0000\u0000LM\u0003\u0004"+
		"\u0002\u0000MN\u0005\u0013\u0000\u0000NO\u0003\u0004\u0002\u0000OZ\u0001"+
		"\u0000\u0000\u0000PQ\u0003\u0004\u0002\u0000QR\u0005\u0014\u0000\u0000"+
		"RS\u0003\u0004\u0002\u0000SZ\u0001\u0000\u0000\u0000TZ\u0003\u0004\u0002"+
		"\u0000UV\u0005\u0002\u0000\u0000VW\u0003\u0006\u0003\u0000WX\u0005\u0003"+
		"\u0000\u0000XZ\u0001\u0000\u0000\u0000Y=\u0001\u0000\u0000\u0000Y@\u0001"+
		"\u0000\u0000\u0000YD\u0001\u0000\u0000\u0000YH\u0001\u0000\u0000\u0000"+
		"YL\u0001\u0000\u0000\u0000YP\u0001\u0000\u0000\u0000YT\u0001\u0000\u0000"+
		"\u0000YU\u0001\u0000\u0000\u0000Zc\u0001\u0000\u0000\u0000[\\\n\n\u0000"+
		"\u0000\\]\u0005\u000e\u0000\u0000]b\u0003\u0006\u0003\u000b^_\n\t\u0000"+
		"\u0000_`\u0005\u000f\u0000\u0000`b\u0003\u0006\u0003\na[\u0001\u0000\u0000"+
		"\u0000a^\u0001\u0000\u0000\u0000be\u0001\u0000\u0000\u0000ca\u0001\u0000"+
		"\u0000\u0000cd\u0001\u0000\u0000\u0000d\u0007\u0001\u0000\u0000\u0000"+
		"ec\u0001\u0000\u0000\u0000\u0007\u0016(8:Yac";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}