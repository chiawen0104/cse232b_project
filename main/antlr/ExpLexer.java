// Generated from ./XPath.g4 by ANTLR 4.13.2
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
public class ExpLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, Number=23, WS=24, STRING=25, 
		TAGNAME=26, ATTRNAME=27;
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
			"T__17", "T__18", "T__19", "T__20", "T__21", "Number", "WS", "STRING", 
			"TAGNAME", "ATTRNAME"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'", "'*'", "'/'", "'('", "')'", "'doc'", "'//'", "'.'", 
			"'..'", "'text'", "'@'", "'['", "']'", "','", "'='", "'eq'", "'=='", 
			"'is'", "'and'", "'or'", "'not'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "Number", 
			"WS", "STRING", "TAGNAME", "ATTRNAME"
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


	public ExpLexer(CharStream input) {
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
		"\u0004\u0000\u001b\u0096\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0016\u0004\u0016t\b\u0016\u000b\u0016\f\u0016u\u0001\u0016\u0001"+
		"\u0016\u0004\u0016z\b\u0016\u000b\u0016\f\u0016{\u0003\u0016~\b\u0016"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0004\u0018"+
		"\u0085\b\u0018\u000b\u0018\f\u0018\u0086\u0001\u0019\u0001\u0019\u0005"+
		"\u0019\u008b\b\u0019\n\u0019\f\u0019\u008e\t\u0019\u0001\u001a\u0001\u001a"+
		"\u0005\u001a\u0092\b\u001a\n\u001a\f\u001a\u0095\t\u001a\u0000\u0000\u001b"+
		"\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r"+
		"\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e"+
		"\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017"+
		"/\u00181\u00193\u001a5\u001b\u0001\u0000\u0004\u0003\u0000\t\n\r\r  \u0004"+
		"\u0000/9AZ__az\u0003\u0000AZ__az\u0004\u000009AZ__az\u009b\u0000\u0001"+
		"\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005"+
		"\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001"+
		"\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000"+
		"\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000"+
		"\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000"+
		"\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000"+
		"\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000"+
		"\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000"+
		"\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000"+
		"\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001"+
		"\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000"+
		"\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u0000"+
		"5\u0001\u0000\u0000\u0000\u00017\u0001\u0000\u0000\u0000\u00039\u0001"+
		"\u0000\u0000\u0000\u0005;\u0001\u0000\u0000\u0000\u0007=\u0001\u0000\u0000"+
		"\u0000\t?\u0001\u0000\u0000\u0000\u000bA\u0001\u0000\u0000\u0000\rC\u0001"+
		"\u0000\u0000\u0000\u000fG\u0001\u0000\u0000\u0000\u0011J\u0001\u0000\u0000"+
		"\u0000\u0013L\u0001\u0000\u0000\u0000\u0015O\u0001\u0000\u0000\u0000\u0017"+
		"T\u0001\u0000\u0000\u0000\u0019V\u0001\u0000\u0000\u0000\u001bX\u0001"+
		"\u0000\u0000\u0000\u001dZ\u0001\u0000\u0000\u0000\u001f\\\u0001\u0000"+
		"\u0000\u0000!^\u0001\u0000\u0000\u0000#a\u0001\u0000\u0000\u0000%d\u0001"+
		"\u0000\u0000\u0000\'g\u0001\u0000\u0000\u0000)k\u0001\u0000\u0000\u0000"+
		"+n\u0001\u0000\u0000\u0000-s\u0001\u0000\u0000\u0000/\u007f\u0001\u0000"+
		"\u0000\u00001\u0084\u0001\u0000\u0000\u00003\u0088\u0001\u0000\u0000\u0000"+
		"5\u008f\u0001\u0000\u0000\u000078\u0005+\u0000\u00008\u0002\u0001\u0000"+
		"\u0000\u00009:\u0005-\u0000\u0000:\u0004\u0001\u0000\u0000\u0000;<\u0005"+
		"*\u0000\u0000<\u0006\u0001\u0000\u0000\u0000=>\u0005/\u0000\u0000>\b\u0001"+
		"\u0000\u0000\u0000?@\u0005(\u0000\u0000@\n\u0001\u0000\u0000\u0000AB\u0005"+
		")\u0000\u0000B\f\u0001\u0000\u0000\u0000CD\u0005d\u0000\u0000DE\u0005"+
		"o\u0000\u0000EF\u0005c\u0000\u0000F\u000e\u0001\u0000\u0000\u0000GH\u0005"+
		"/\u0000\u0000HI\u0005/\u0000\u0000I\u0010\u0001\u0000\u0000\u0000JK\u0005"+
		".\u0000\u0000K\u0012\u0001\u0000\u0000\u0000LM\u0005.\u0000\u0000MN\u0005"+
		".\u0000\u0000N\u0014\u0001\u0000\u0000\u0000OP\u0005t\u0000\u0000PQ\u0005"+
		"e\u0000\u0000QR\u0005x\u0000\u0000RS\u0005t\u0000\u0000S\u0016\u0001\u0000"+
		"\u0000\u0000TU\u0005@\u0000\u0000U\u0018\u0001\u0000\u0000\u0000VW\u0005"+
		"[\u0000\u0000W\u001a\u0001\u0000\u0000\u0000XY\u0005]\u0000\u0000Y\u001c"+
		"\u0001\u0000\u0000\u0000Z[\u0005,\u0000\u0000[\u001e\u0001\u0000\u0000"+
		"\u0000\\]\u0005=\u0000\u0000] \u0001\u0000\u0000\u0000^_\u0005e\u0000"+
		"\u0000_`\u0005q\u0000\u0000`\"\u0001\u0000\u0000\u0000ab\u0005=\u0000"+
		"\u0000bc\u0005=\u0000\u0000c$\u0001\u0000\u0000\u0000de\u0005i\u0000\u0000"+
		"ef\u0005s\u0000\u0000f&\u0001\u0000\u0000\u0000gh\u0005a\u0000\u0000h"+
		"i\u0005n\u0000\u0000ij\u0005d\u0000\u0000j(\u0001\u0000\u0000\u0000kl"+
		"\u0005o\u0000\u0000lm\u0005r\u0000\u0000m*\u0001\u0000\u0000\u0000no\u0005"+
		"n\u0000\u0000op\u0005o\u0000\u0000pq\u0005t\u0000\u0000q,\u0001\u0000"+
		"\u0000\u0000rt\u000209\u0000sr\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000"+
		"\u0000us\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000v}\u0001\u0000"+
		"\u0000\u0000wy\u0005.\u0000\u0000xz\u000209\u0000yx\u0001\u0000\u0000"+
		"\u0000z{\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000{|\u0001\u0000"+
		"\u0000\u0000|~\u0001\u0000\u0000\u0000}w\u0001\u0000\u0000\u0000}~\u0001"+
		"\u0000\u0000\u0000~.\u0001\u0000\u0000\u0000\u007f\u0080\u0007\u0000\u0000"+
		"\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0082\u0006\u0017\u0000"+
		"\u0000\u00820\u0001\u0000\u0000\u0000\u0083\u0085\u0007\u0001\u0000\u0000"+
		"\u0084\u0083\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000"+
		"\u0086\u0084\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000"+
		"\u00872\u0001\u0000\u0000\u0000\u0088\u008c\u0007\u0002\u0000\u0000\u0089"+
		"\u008b\u0007\u0003\u0000\u0000\u008a\u0089\u0001\u0000\u0000\u0000\u008b"+
		"\u008e\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008c"+
		"\u008d\u0001\u0000\u0000\u0000\u008d4\u0001\u0000\u0000\u0000\u008e\u008c"+
		"\u0001\u0000\u0000\u0000\u008f\u0093\u0007\u0002\u0000\u0000\u0090\u0092"+
		"\u0007\u0003\u0000\u0000\u0091\u0090\u0001\u0000\u0000\u0000\u0092\u0095"+
		"\u0001\u0000\u0000\u0000\u0093\u0091\u0001\u0000\u0000\u0000\u0093\u0094"+
		"\u0001\u0000\u0000\u0000\u00946\u0001\u0000\u0000\u0000\u0095\u0093\u0001"+
		"\u0000\u0000\u0000\u0007\u0000u{}\u0086\u008c\u0093\u0001\u0006\u0000"+
		"\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}