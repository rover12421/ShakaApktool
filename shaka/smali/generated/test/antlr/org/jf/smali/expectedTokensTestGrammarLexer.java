// $ANTLR 3.5.2 expectedTokensTestGrammar.g

package org.jf.smali;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class expectedTokensTestGrammarLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__11=11;
	public static final int T__12=12;
	public static final int BASE_STRING_LITERAL=4;
	public static final int ESCAPE_SEQUENCE=5;
	public static final int HEX_DIGIT=6;
	public static final int HEX_DIGITS=7;
	public static final int STRING_LITERAL=8;
	public static final int TOKEN_NAME=9;
	public static final int WHITE_SPACE=10;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public expectedTokensTestGrammarLexer() {} 
	public expectedTokensTestGrammarLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public expectedTokensTestGrammarLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "expectedTokensTestGrammar.g"; }

	// $ANTLR start "T__11"
	public final void mT__11() throws RecognitionException {
		try {
			int _type = T__11;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// expectedTokensTestGrammar.g:6:7: ( '(' )
			// expectedTokensTestGrammar.g:6:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__11"

	// $ANTLR start "T__12"
	public final void mT__12() throws RecognitionException {
		try {
			int _type = T__12;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// expectedTokensTestGrammar.g:7:7: ( ')' )
			// expectedTokensTestGrammar.g:7:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__12"

	// $ANTLR start "HEX_DIGIT"
	public final void mHEX_DIGIT() throws RecognitionException {
		try {
			// expectedTokensTestGrammar.g:107:2: ( ( '0' .. '9' ) | ( 'A' .. 'F' ) | ( 'a' .. 'f' ) )
			// expectedTokensTestGrammar.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HEX_DIGIT"

	// $ANTLR start "HEX_DIGITS"
	public final void mHEX_DIGITS() throws RecognitionException {
		try {
			// expectedTokensTestGrammar.g:110:2: ( HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
			// expectedTokensTestGrammar.g:110:4: HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
			{
			mHEX_DIGIT(); 

			mHEX_DIGIT(); 

			mHEX_DIGIT(); 

			mHEX_DIGIT(); 

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HEX_DIGITS"

	// $ANTLR start "ESCAPE_SEQUENCE"
	public final void mESCAPE_SEQUENCE(StringBuilder sb) throws RecognitionException {
		try {
			CommonToken HEX_DIGITS1=null;

			// expectedTokensTestGrammar.g:113:2: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' | 'u' HEX_DIGITS ) )
			// expectedTokensTestGrammar.g:113:4: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' | 'u' HEX_DIGITS )
			{
			match('\\'); 
			// expectedTokensTestGrammar.g:114:3: ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' | 'u' HEX_DIGITS )
			int alt1=9;
			switch ( input.LA(1) ) {
			case 'b':
				{
				alt1=1;
				}
				break;
			case 't':
				{
				alt1=2;
				}
				break;
			case 'n':
				{
				alt1=3;
				}
				break;
			case 'f':
				{
				alt1=4;
				}
				break;
			case 'r':
				{
				alt1=5;
				}
				break;
			case '\"':
				{
				alt1=6;
				}
				break;
			case '\'':
				{
				alt1=7;
				}
				break;
			case '\\':
				{
				alt1=8;
				}
				break;
			case 'u':
				{
				alt1=9;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// expectedTokensTestGrammar.g:115:4: 'b'
					{
					match('b'); 
					sb.append("\b");
					}
					break;
				case 2 :
					// expectedTokensTestGrammar.g:116:5: 't'
					{
					match('t'); 
					sb.append("\t");
					}
					break;
				case 3 :
					// expectedTokensTestGrammar.g:117:5: 'n'
					{
					match('n'); 
					sb.append("\n");
					}
					break;
				case 4 :
					// expectedTokensTestGrammar.g:118:5: 'f'
					{
					match('f'); 
					sb.append("\f");
					}
					break;
				case 5 :
					// expectedTokensTestGrammar.g:119:5: 'r'
					{
					match('r'); 
					sb.append("\r");
					}
					break;
				case 6 :
					// expectedTokensTestGrammar.g:120:5: '\\\"'
					{
					match('\"'); 
					sb.append("\"");
					}
					break;
				case 7 :
					// expectedTokensTestGrammar.g:121:5: '\\''
					{
					match('\''); 
					sb.append("'");
					}
					break;
				case 8 :
					// expectedTokensTestGrammar.g:122:5: '\\\\'
					{
					match('\\'); 
					sb.append("\\");
					}
					break;
				case 9 :
					// expectedTokensTestGrammar.g:123:5: 'u' HEX_DIGITS
					{
					match('u'); 
					int HEX_DIGITS1Start156 = getCharIndex();
					int HEX_DIGITS1StartLine156 = getLine();
					int HEX_DIGITS1StartCharPos156 = getCharPositionInLine();
					mHEX_DIGITS(); 
					HEX_DIGITS1 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, HEX_DIGITS1Start156, getCharIndex()-1);
					HEX_DIGITS1.setLine(HEX_DIGITS1StartLine156);
					HEX_DIGITS1.setCharPositionInLine(HEX_DIGITS1StartCharPos156);

					sb.append((char)Integer.parseInt((HEX_DIGITS1!=null?HEX_DIGITS1.getText():null), 16));
					}
					break;

			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ESCAPE_SEQUENCE"

	// $ANTLR start "STRING_LITERAL"
	public final void mSTRING_LITERAL() throws RecognitionException {
		try {
			int _type = STRING_LITERAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			StringBuilder sb = new StringBuilder();
			// expectedTokensTestGrammar.g:129:2: ( BASE_STRING_LITERAL[sb] )
			// expectedTokensTestGrammar.g:129:4: BASE_STRING_LITERAL[sb]
			{
			mBASE_STRING_LITERAL(sb); 

			setText(sb.toString());
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING_LITERAL"

	// $ANTLR start "BASE_STRING_LITERAL"
	public final void mBASE_STRING_LITERAL(StringBuilder sb) throws RecognitionException {
		try {
			// expectedTokensTestGrammar.g:132:2: ( '\"' ( ESCAPE_SEQUENCE[sb] |~ ( '\\\\' | '\"' | '\\r' | '\\n' ) )* '\"' )
			// expectedTokensTestGrammar.g:132:4: '\"' ( ESCAPE_SEQUENCE[sb] |~ ( '\\\\' | '\"' | '\\r' | '\\n' ) )* '\"'
			{
			match('\"'); 
			// expectedTokensTestGrammar.g:133:3: ( ESCAPE_SEQUENCE[sb] |~ ( '\\\\' | '\"' | '\\r' | '\\n' ) )*
			loop2:
			while (true) {
				int alt2=3;
				int LA2_0 = input.LA(1);
				if ( (LA2_0=='\\') ) {
					alt2=1;
				}
				else if ( ((LA2_0 >= '\u0000' && LA2_0 <= '\t')||(LA2_0 >= '\u000B' && LA2_0 <= '\f')||(LA2_0 >= '\u000E' && LA2_0 <= '!')||(LA2_0 >= '#' && LA2_0 <= '[')||(LA2_0 >= ']' && LA2_0 <= '\uFFFF')) ) {
					alt2=2;
				}

				switch (alt2) {
				case 1 :
					// expectedTokensTestGrammar.g:133:5: ESCAPE_SEQUENCE[sb]
					{
					mESCAPE_SEQUENCE(sb); 

					}
					break;
				case 2 :
					// expectedTokensTestGrammar.g:134:5: ~ ( '\\\\' | '\"' | '\\r' | '\\n' )
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					sb.append((char)input.LA(-1));
					}
					break;

				default :
					break loop2;
				}
			}

			match('\"'); 
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BASE_STRING_LITERAL"

	// $ANTLR start "TOKEN_NAME"
	public final void mTOKEN_NAME() throws RecognitionException {
		try {
			int _type = TOKEN_NAME;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// expectedTokensTestGrammar.g:139:2: ( ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' | ( '0' .. '9' ) )+ )
			// expectedTokensTestGrammar.g:139:4: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' | ( '0' .. '9' ) )+
			{
			// expectedTokensTestGrammar.g:139:4: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' | ( '0' .. '9' ) )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '0' && LA3_0 <= '9')||(LA3_0 >= 'A' && LA3_0 <= 'Z')||LA3_0=='_'||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// expectedTokensTestGrammar.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TOKEN_NAME"

	// $ANTLR start "WHITE_SPACE"
	public final void mWHITE_SPACE() throws RecognitionException {
		try {
			int _type = WHITE_SPACE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// expectedTokensTestGrammar.g:142:2: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
			// expectedTokensTestGrammar.g:142:4: ( ' ' | '\\t' | '\\n' | '\\r' )+
			{
			// expectedTokensTestGrammar.g:142:4: ( ' ' | '\\t' | '\\n' | '\\r' )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '\t' && LA4_0 <= '\n')||LA4_0=='\r'||LA4_0==' ') ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// expectedTokensTestGrammar.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			_channel = HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHITE_SPACE"

	@Override
	public void mTokens() throws RecognitionException {
		// expectedTokensTestGrammar.g:1:8: ( T__11 | T__12 | STRING_LITERAL | TOKEN_NAME | WHITE_SPACE )
		int alt5=5;
		switch ( input.LA(1) ) {
		case '(':
			{
			alt5=1;
			}
			break;
		case ')':
			{
			alt5=2;
			}
			break;
		case '\"':
			{
			alt5=3;
			}
			break;
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
		case 'G':
		case 'H':
		case 'I':
		case 'J':
		case 'K':
		case 'L':
		case 'M':
		case 'N':
		case 'O':
		case 'P':
		case 'Q':
		case 'R':
		case 'S':
		case 'T':
		case 'U':
		case 'V':
		case 'W':
		case 'X':
		case 'Y':
		case 'Z':
		case '_':
		case 'a':
		case 'b':
		case 'c':
		case 'd':
		case 'e':
		case 'f':
		case 'g':
		case 'h':
		case 'i':
		case 'j':
		case 'k':
		case 'l':
		case 'm':
		case 'n':
		case 'o':
		case 'p':
		case 'q':
		case 'r':
		case 's':
		case 't':
		case 'u':
		case 'v':
		case 'w':
		case 'x':
		case 'y':
		case 'z':
			{
			alt5=4;
			}
			break;
		case '\t':
		case '\n':
		case '\r':
		case ' ':
			{
			alt5=5;
			}
			break;
		default:
			NoViableAltException nvae =
				new NoViableAltException("", 5, 0, input);
			throw nvae;
		}
		switch (alt5) {
			case 1 :
				// expectedTokensTestGrammar.g:1:10: T__11
				{
				mT__11(); 

				}
				break;
			case 2 :
				// expectedTokensTestGrammar.g:1:16: T__12
				{
				mT__12(); 

				}
				break;
			case 3 :
				// expectedTokensTestGrammar.g:1:22: STRING_LITERAL
				{
				mSTRING_LITERAL(); 

				}
				break;
			case 4 :
				// expectedTokensTestGrammar.g:1:37: TOKEN_NAME
				{
				mTOKEN_NAME(); 

				}
				break;
			case 5 :
				// expectedTokensTestGrammar.g:1:48: WHITE_SPACE
				{
				mWHITE_SPACE(); 

				}
				break;

		}
	}



}
