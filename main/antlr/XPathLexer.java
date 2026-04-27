// Generated from XPath.g4 by ANTLR 4.13.2
package main.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class XPathLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, StringConstant=21, NAME=22, WS=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "StringConstant", "NAME", "WS"
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


	public XPathLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "XPath.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0017\u0086\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0005\u0014i\b\u0014\n\u0014\f\u0014"+
		"l\t\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014q\b\u0014\n\u0014"+
		"\f\u0014t\t\u0014\u0001\u0014\u0003\u0014w\b\u0014\u0001\u0015\u0001\u0015"+
		"\u0005\u0015{\b\u0015\n\u0015\f\u0015~\t\u0015\u0001\u0016\u0004\u0016"+
		"\u0081\b\u0016\u000b\u0016\f\u0016\u0082\u0001\u0016\u0001\u0016\u0000"+
		"\u0000\u0017\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b"+
		"\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b"+
		"\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016"+
		"-\u0017\u0001\u0000\u0005\u0001\u0000\"\"\u0001\u0000\'\'\u0003\u0000"+
		"AZ__az\u0005\u0000--09AZ__az\u0003\u0000\t\n\r\r  \u008a\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000"+
		"\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000"+
		"\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000"+
		"\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000"+
		"\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000"+
		"\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'"+
		"\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000"+
		"\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0001/\u0001\u0000\u0000\u0000"+
		"\u00033\u0001\u0000\u0000\u0000\u00055\u0001\u0000\u0000\u0000\u00077"+
		"\u0001\u0000\u0000\u0000\t9\u0001\u0000\u0000\u0000\u000b<\u0001\u0000"+
		"\u0000\u0000\r>\u0001\u0000\u0000\u0000\u000f@\u0001\u0000\u0000\u0000"+
		"\u0011B\u0001\u0000\u0000\u0000\u0013D\u0001\u0000\u0000\u0000\u0015F"+
		"\u0001\u0000\u0000\u0000\u0017I\u0001\u0000\u0000\u0000\u0019N\u0001\u0000"+
		"\u0000\u0000\u001bP\u0001\u0000\u0000\u0000\u001dS\u0001\u0000\u0000\u0000"+
		"\u001fW\u0001\u0000\u0000\u0000![\u0001\u0000\u0000\u0000#]\u0001\u0000"+
		"\u0000\u0000%`\u0001\u0000\u0000\u0000\'c\u0001\u0000\u0000\u0000)v\u0001"+
		"\u0000\u0000\u0000+x\u0001\u0000\u0000\u0000-\u0080\u0001\u0000\u0000"+
		"\u0000/0\u0005d\u0000\u000001\u0005o\u0000\u000012\u0005c\u0000\u0000"+
		"2\u0002\u0001\u0000\u0000\u000034\u0005(\u0000\u00004\u0004\u0001\u0000"+
		"\u0000\u000056\u0005)\u0000\u00006\u0006\u0001\u0000\u0000\u000078\u0005"+
		"/\u0000\u00008\b\u0001\u0000\u0000\u00009:\u0005/\u0000\u0000:;\u0005"+
		"/\u0000\u0000;\n\u0001\u0000\u0000\u0000<=\u0005,\u0000\u0000=\f\u0001"+
		"\u0000\u0000\u0000>?\u0005[\u0000\u0000?\u000e\u0001\u0000\u0000\u0000"+
		"@A\u0005]\u0000\u0000A\u0010\u0001\u0000\u0000\u0000BC\u0005*\u0000\u0000"+
		"C\u0012\u0001\u0000\u0000\u0000DE\u0005.\u0000\u0000E\u0014\u0001\u0000"+
		"\u0000\u0000FG\u0005.\u0000\u0000GH\u0005.\u0000\u0000H\u0016\u0001\u0000"+
		"\u0000\u0000IJ\u0005t\u0000\u0000JK\u0005e\u0000\u0000KL\u0005x\u0000"+
		"\u0000LM\u0005t\u0000\u0000M\u0018\u0001\u0000\u0000\u0000NO\u0005@\u0000"+
		"\u0000O\u001a\u0001\u0000\u0000\u0000PQ\u0005o\u0000\u0000QR\u0005r\u0000"+
		"\u0000R\u001c\u0001\u0000\u0000\u0000ST\u0005a\u0000\u0000TU\u0005n\u0000"+
		"\u0000UV\u0005d\u0000\u0000V\u001e\u0001\u0000\u0000\u0000WX\u0005n\u0000"+
		"\u0000XY\u0005o\u0000\u0000YZ\u0005t\u0000\u0000Z \u0001\u0000\u0000\u0000"+
		"[\\\u0005=\u0000\u0000\\\"\u0001\u0000\u0000\u0000]^\u0005e\u0000\u0000"+
		"^_\u0005q\u0000\u0000_$\u0001\u0000\u0000\u0000`a\u0005=\u0000\u0000a"+
		"b\u0005=\u0000\u0000b&\u0001\u0000\u0000\u0000cd\u0005i\u0000\u0000de"+
		"\u0005s\u0000\u0000e(\u0001\u0000\u0000\u0000fj\u0005\"\u0000\u0000gi"+
		"\b\u0000\u0000\u0000hg\u0001\u0000\u0000\u0000il\u0001\u0000\u0000\u0000"+
		"jh\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000km\u0001\u0000\u0000"+
		"\u0000lj\u0001\u0000\u0000\u0000mw\u0005\"\u0000\u0000nr\u0005\'\u0000"+
		"\u0000oq\b\u0001\u0000\u0000po\u0001\u0000\u0000\u0000qt\u0001\u0000\u0000"+
		"\u0000rp\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000su\u0001\u0000"+
		"\u0000\u0000tr\u0001\u0000\u0000\u0000uw\u0005\'\u0000\u0000vf\u0001\u0000"+
		"\u0000\u0000vn\u0001\u0000\u0000\u0000w*\u0001\u0000\u0000\u0000x|\u0007"+
		"\u0002\u0000\u0000y{\u0007\u0003\u0000\u0000zy\u0001\u0000\u0000\u0000"+
		"{~\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000"+
		"\u0000},\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000\u007f\u0081"+
		"\u0007\u0004\u0000\u0000\u0080\u007f\u0001\u0000\u0000\u0000\u0081\u0082"+
		"\u0001\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0082\u0083"+
		"\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0085"+
		"\u0006\u0016\u0000\u0000\u0085.\u0001\u0000\u0000\u0000\u0006\u0000jr"+
		"v|\u0082\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}