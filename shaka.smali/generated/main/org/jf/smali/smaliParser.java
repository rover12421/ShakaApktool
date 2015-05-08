// $ANTLR 3.5.2 shaka.smali/smali/smali/src/main/antlr/smaliParser.g 2015-05-07 18:19:43

package org.jf.smali;

import org.jf.dexlib2.Format;
import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.Opcodes;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class smaliParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ACCESS_SPEC", "ANNOTATION_DIRECTIVE", 
		"ANNOTATION_VISIBILITY", "ARRAY_DATA_DIRECTIVE", "ARRAY_DESCRIPTOR", "ARROW", 
		"BOOL_LITERAL", "BYTE_LITERAL", "CATCHALL_DIRECTIVE", "CATCH_DIRECTIVE", 
		"CHAR_LITERAL", "CLASS_DESCRIPTOR", "CLASS_DIRECTIVE", "CLOSE_BRACE", 
		"CLOSE_PAREN", "COLON", "COMMA", "DOTDOT", "DOUBLE_LITERAL", "DOUBLE_LITERAL_OR_ID", 
		"END_ANNOTATION_DIRECTIVE", "END_ARRAY_DATA_DIRECTIVE", "END_FIELD_DIRECTIVE", 
		"END_LOCAL_DIRECTIVE", "END_METHOD_DIRECTIVE", "END_PACKED_SWITCH_DIRECTIVE", 
		"END_PARAMETER_DIRECTIVE", "END_SPARSE_SWITCH_DIRECTIVE", "END_SUBANNOTATION_DIRECTIVE", 
		"ENUM_DIRECTIVE", "EPILOGUE_DIRECTIVE", "EQUAL", "FIELD_DIRECTIVE", "FIELD_OFFSET", 
		"FLOAT_LITERAL", "FLOAT_LITERAL_OR_ID", "IMPLEMENTS_DIRECTIVE", "INLINE_INDEX", 
		"INSTRUCTION_FORMAT10t", "INSTRUCTION_FORMAT10x", "INSTRUCTION_FORMAT10x_ODEX", 
		"INSTRUCTION_FORMAT11n", "INSTRUCTION_FORMAT11x", "INSTRUCTION_FORMAT12x", 
		"INSTRUCTION_FORMAT12x_OR_ID", "INSTRUCTION_FORMAT20bc", "INSTRUCTION_FORMAT20t", 
		"INSTRUCTION_FORMAT21c_FIELD", "INSTRUCTION_FORMAT21c_FIELD_ODEX", "INSTRUCTION_FORMAT21c_LAMBDA", 
		"INSTRUCTION_FORMAT21c_METHOD", "INSTRUCTION_FORMAT21c_STRING", "INSTRUCTION_FORMAT21c_TYPE", 
		"INSTRUCTION_FORMAT21ih", "INSTRUCTION_FORMAT21lh", "INSTRUCTION_FORMAT21s", 
		"INSTRUCTION_FORMAT21t", "INSTRUCTION_FORMAT22b", "INSTRUCTION_FORMAT22c_FIELD", 
		"INSTRUCTION_FORMAT22c_FIELD_ODEX", "INSTRUCTION_FORMAT22c_STRING", "INSTRUCTION_FORMAT22c_TYPE", 
		"INSTRUCTION_FORMAT22cs_FIELD", "INSTRUCTION_FORMAT22s", "INSTRUCTION_FORMAT22s_OR_ID", 
		"INSTRUCTION_FORMAT22t", "INSTRUCTION_FORMAT22x", "INSTRUCTION_FORMAT23x", 
		"INSTRUCTION_FORMAT25x", "INSTRUCTION_FORMAT30t", "INSTRUCTION_FORMAT31c", 
		"INSTRUCTION_FORMAT31i", "INSTRUCTION_FORMAT31i_OR_ID", "INSTRUCTION_FORMAT31t", 
		"INSTRUCTION_FORMAT32x", "INSTRUCTION_FORMAT35c_METHOD", "INSTRUCTION_FORMAT35c_METHOD_ODEX", 
		"INSTRUCTION_FORMAT35c_TYPE", "INSTRUCTION_FORMAT35mi_METHOD", "INSTRUCTION_FORMAT35ms_METHOD", 
		"INSTRUCTION_FORMAT3rc_METHOD", "INSTRUCTION_FORMAT3rc_METHOD_ODEX", "INSTRUCTION_FORMAT3rc_TYPE", 
		"INSTRUCTION_FORMAT3rmi_METHOD", "INSTRUCTION_FORMAT3rms_METHOD", "INSTRUCTION_FORMAT51l", 
		"INTEGER_LITERAL", "INVALID_TOKEN", "I_ACCESS_LIST", "I_ANNOTATION", "I_ANNOTATIONS", 
		"I_ANNOTATION_ELEMENT", "I_ARRAY_ELEMENTS", "I_ARRAY_ELEMENT_SIZE", "I_CATCH", 
		"I_CATCHALL", "I_CATCHES", "I_CLASS_DEF", "I_ENCODED_ARRAY", "I_ENCODED_ENUM", 
		"I_ENCODED_FIELD", "I_ENCODED_METHOD", "I_END_LOCAL", "I_EPILOGUE", "I_FIELD", 
		"I_FIELDS", "I_FIELD_INITIAL_VALUE", "I_FIELD_TYPE", "I_IMPLEMENTS", "I_LABEL", 
		"I_LINE", "I_LOCAL", "I_LOCALS", "I_METHOD", "I_METHODS", "I_METHOD_PROTOTYPE", 
		"I_METHOD_RETURN_TYPE", "I_ORDERED_METHOD_ITEMS", "I_PACKED_SWITCH_ELEMENTS", 
		"I_PACKED_SWITCH_START_KEY", "I_PARAMETER", "I_PARAMETERS", "I_PARAMETER_NOT_SPECIFIED", 
		"I_PROLOGUE", "I_REGISTERS", "I_REGISTER_LIST", "I_REGISTER_RANGE", "I_RESTART_LOCAL", 
		"I_SOURCE", "I_SPARSE_SWITCH_ELEMENTS", "I_STATEMENT_ARRAY_DATA", "I_STATEMENT_FORMAT10t", 
		"I_STATEMENT_FORMAT10x", "I_STATEMENT_FORMAT11n", "I_STATEMENT_FORMAT11x", 
		"I_STATEMENT_FORMAT12x", "I_STATEMENT_FORMAT20bc", "I_STATEMENT_FORMAT20t", 
		"I_STATEMENT_FORMAT21c_FIELD", "I_STATEMENT_FORMAT21c_LAMBDA", "I_STATEMENT_FORMAT21c_METHOD", 
		"I_STATEMENT_FORMAT21c_STRING", "I_STATEMENT_FORMAT21c_TYPE", "I_STATEMENT_FORMAT21ih", 
		"I_STATEMENT_FORMAT21lh", "I_STATEMENT_FORMAT21s", "I_STATEMENT_FORMAT21t", 
		"I_STATEMENT_FORMAT22b", "I_STATEMENT_FORMAT22c_FIELD", "I_STATEMENT_FORMAT22c_STRING", 
		"I_STATEMENT_FORMAT22c_TYPE", "I_STATEMENT_FORMAT22s", "I_STATEMENT_FORMAT22t", 
		"I_STATEMENT_FORMAT22x", "I_STATEMENT_FORMAT23x", "I_STATEMENT_FORMAT25x", 
		"I_STATEMENT_FORMAT30t", "I_STATEMENT_FORMAT31c", "I_STATEMENT_FORMAT31i", 
		"I_STATEMENT_FORMAT31t", "I_STATEMENT_FORMAT32x", "I_STATEMENT_FORMAT35c_METHOD", 
		"I_STATEMENT_FORMAT35c_TYPE", "I_STATEMENT_FORMAT3rc_METHOD", "I_STATEMENT_FORMAT3rc_TYPE", 
		"I_STATEMENT_FORMAT51l", "I_STATEMENT_PACKED_SWITCH", "I_STATEMENT_SPARSE_SWITCH", 
		"I_SUBANNOTATION", "I_SUPER", "LINE_COMMENT", "LINE_DIRECTIVE", "LOCALS_DIRECTIVE", 
		"LOCAL_DIRECTIVE", "LONG_LITERAL", "MEMBER_NAME", "METHOD_DIRECTIVE", 
		"NEGATIVE_INTEGER_LITERAL", "NULL_LITERAL", "OPEN_BRACE", "OPEN_PAREN", 
		"PACKED_SWITCH_DIRECTIVE", "PARAMETER_DIRECTIVE", "PARAM_LIST_END", "PARAM_LIST_OR_ID_END", 
		"PARAM_LIST_OR_ID_START", "PARAM_LIST_START", "POSITIVE_INTEGER_LITERAL", 
		"PRIMITIVE_TYPE", "PROLOGUE_DIRECTIVE", "REGISTER", "REGISTERS_DIRECTIVE", 
		"RESTART_LOCAL_DIRECTIVE", "SHORT_LITERAL", "SIMPLE_NAME", "SOURCE_DIRECTIVE", 
		"SPARSE_SWITCH_DIRECTIVE", "STRING_LITERAL", "SUBANNOTATION_DIRECTIVE", 
		"SUPER_DIRECTIVE", "VERIFICATION_ERROR_TYPE", "VOID_TYPE", "VTABLE_INDEX", 
		"WHITE_SPACE"
	};
	public static final int EOF=-1;
	public static final int ACCESS_SPEC=4;
	public static final int ANNOTATION_DIRECTIVE=5;
	public static final int ANNOTATION_VISIBILITY=6;
	public static final int ARRAY_DATA_DIRECTIVE=7;
	public static final int ARRAY_DESCRIPTOR=8;
	public static final int ARROW=9;
	public static final int BOOL_LITERAL=10;
	public static final int BYTE_LITERAL=11;
	public static final int CATCHALL_DIRECTIVE=12;
	public static final int CATCH_DIRECTIVE=13;
	public static final int CHAR_LITERAL=14;
	public static final int CLASS_DESCRIPTOR=15;
	public static final int CLASS_DIRECTIVE=16;
	public static final int CLOSE_BRACE=17;
	public static final int CLOSE_PAREN=18;
	public static final int COLON=19;
	public static final int COMMA=20;
	public static final int DOTDOT=21;
	public static final int DOUBLE_LITERAL=22;
	public static final int DOUBLE_LITERAL_OR_ID=23;
	public static final int END_ANNOTATION_DIRECTIVE=24;
	public static final int END_ARRAY_DATA_DIRECTIVE=25;
	public static final int END_FIELD_DIRECTIVE=26;
	public static final int END_LOCAL_DIRECTIVE=27;
	public static final int END_METHOD_DIRECTIVE=28;
	public static final int END_PACKED_SWITCH_DIRECTIVE=29;
	public static final int END_PARAMETER_DIRECTIVE=30;
	public static final int END_SPARSE_SWITCH_DIRECTIVE=31;
	public static final int END_SUBANNOTATION_DIRECTIVE=32;
	public static final int ENUM_DIRECTIVE=33;
	public static final int EPILOGUE_DIRECTIVE=34;
	public static final int EQUAL=35;
	public static final int FIELD_DIRECTIVE=36;
	public static final int FIELD_OFFSET=37;
	public static final int FLOAT_LITERAL=38;
	public static final int FLOAT_LITERAL_OR_ID=39;
	public static final int IMPLEMENTS_DIRECTIVE=40;
	public static final int INLINE_INDEX=41;
	public static final int INSTRUCTION_FORMAT10t=42;
	public static final int INSTRUCTION_FORMAT10x=43;
	public static final int INSTRUCTION_FORMAT10x_ODEX=44;
	public static final int INSTRUCTION_FORMAT11n=45;
	public static final int INSTRUCTION_FORMAT11x=46;
	public static final int INSTRUCTION_FORMAT12x=47;
	public static final int INSTRUCTION_FORMAT12x_OR_ID=48;
	public static final int INSTRUCTION_FORMAT20bc=49;
	public static final int INSTRUCTION_FORMAT20t=50;
	public static final int INSTRUCTION_FORMAT21c_FIELD=51;
	public static final int INSTRUCTION_FORMAT21c_FIELD_ODEX=52;
	public static final int INSTRUCTION_FORMAT21c_LAMBDA=53;
	public static final int INSTRUCTION_FORMAT21c_METHOD=54;
	public static final int INSTRUCTION_FORMAT21c_STRING=55;
	public static final int INSTRUCTION_FORMAT21c_TYPE=56;
	public static final int INSTRUCTION_FORMAT21ih=57;
	public static final int INSTRUCTION_FORMAT21lh=58;
	public static final int INSTRUCTION_FORMAT21s=59;
	public static final int INSTRUCTION_FORMAT21t=60;
	public static final int INSTRUCTION_FORMAT22b=61;
	public static final int INSTRUCTION_FORMAT22c_FIELD=62;
	public static final int INSTRUCTION_FORMAT22c_FIELD_ODEX=63;
	public static final int INSTRUCTION_FORMAT22c_STRING=64;
	public static final int INSTRUCTION_FORMAT22c_TYPE=65;
	public static final int INSTRUCTION_FORMAT22cs_FIELD=66;
	public static final int INSTRUCTION_FORMAT22s=67;
	public static final int INSTRUCTION_FORMAT22s_OR_ID=68;
	public static final int INSTRUCTION_FORMAT22t=69;
	public static final int INSTRUCTION_FORMAT22x=70;
	public static final int INSTRUCTION_FORMAT23x=71;
	public static final int INSTRUCTION_FORMAT25x=72;
	public static final int INSTRUCTION_FORMAT30t=73;
	public static final int INSTRUCTION_FORMAT31c=74;
	public static final int INSTRUCTION_FORMAT31i=75;
	public static final int INSTRUCTION_FORMAT31i_OR_ID=76;
	public static final int INSTRUCTION_FORMAT31t=77;
	public static final int INSTRUCTION_FORMAT32x=78;
	public static final int INSTRUCTION_FORMAT35c_METHOD=79;
	public static final int INSTRUCTION_FORMAT35c_METHOD_ODEX=80;
	public static final int INSTRUCTION_FORMAT35c_TYPE=81;
	public static final int INSTRUCTION_FORMAT35mi_METHOD=82;
	public static final int INSTRUCTION_FORMAT35ms_METHOD=83;
	public static final int INSTRUCTION_FORMAT3rc_METHOD=84;
	public static final int INSTRUCTION_FORMAT3rc_METHOD_ODEX=85;
	public static final int INSTRUCTION_FORMAT3rc_TYPE=86;
	public static final int INSTRUCTION_FORMAT3rmi_METHOD=87;
	public static final int INSTRUCTION_FORMAT3rms_METHOD=88;
	public static final int INSTRUCTION_FORMAT51l=89;
	public static final int INTEGER_LITERAL=90;
	public static final int INVALID_TOKEN=91;
	public static final int I_ACCESS_LIST=92;
	public static final int I_ANNOTATION=93;
	public static final int I_ANNOTATIONS=94;
	public static final int I_ANNOTATION_ELEMENT=95;
	public static final int I_ARRAY_ELEMENTS=96;
	public static final int I_ARRAY_ELEMENT_SIZE=97;
	public static final int I_CATCH=98;
	public static final int I_CATCHALL=99;
	public static final int I_CATCHES=100;
	public static final int I_CLASS_DEF=101;
	public static final int I_ENCODED_ARRAY=102;
	public static final int I_ENCODED_ENUM=103;
	public static final int I_ENCODED_FIELD=104;
	public static final int I_ENCODED_METHOD=105;
	public static final int I_END_LOCAL=106;
	public static final int I_EPILOGUE=107;
	public static final int I_FIELD=108;
	public static final int I_FIELDS=109;
	public static final int I_FIELD_INITIAL_VALUE=110;
	public static final int I_FIELD_TYPE=111;
	public static final int I_IMPLEMENTS=112;
	public static final int I_LABEL=113;
	public static final int I_LINE=114;
	public static final int I_LOCAL=115;
	public static final int I_LOCALS=116;
	public static final int I_METHOD=117;
	public static final int I_METHODS=118;
	public static final int I_METHOD_PROTOTYPE=119;
	public static final int I_METHOD_RETURN_TYPE=120;
	public static final int I_ORDERED_METHOD_ITEMS=121;
	public static final int I_PACKED_SWITCH_ELEMENTS=122;
	public static final int I_PACKED_SWITCH_START_KEY=123;
	public static final int I_PARAMETER=124;
	public static final int I_PARAMETERS=125;
	public static final int I_PARAMETER_NOT_SPECIFIED=126;
	public static final int I_PROLOGUE=127;
	public static final int I_REGISTERS=128;
	public static final int I_REGISTER_LIST=129;
	public static final int I_REGISTER_RANGE=130;
	public static final int I_RESTART_LOCAL=131;
	public static final int I_SOURCE=132;
	public static final int I_SPARSE_SWITCH_ELEMENTS=133;
	public static final int I_STATEMENT_ARRAY_DATA=134;
	public static final int I_STATEMENT_FORMAT10t=135;
	public static final int I_STATEMENT_FORMAT10x=136;
	public static final int I_STATEMENT_FORMAT11n=137;
	public static final int I_STATEMENT_FORMAT11x=138;
	public static final int I_STATEMENT_FORMAT12x=139;
	public static final int I_STATEMENT_FORMAT20bc=140;
	public static final int I_STATEMENT_FORMAT20t=141;
	public static final int I_STATEMENT_FORMAT21c_FIELD=142;
	public static final int I_STATEMENT_FORMAT21c_LAMBDA=143;
	public static final int I_STATEMENT_FORMAT21c_METHOD=144;
	public static final int I_STATEMENT_FORMAT21c_STRING=145;
	public static final int I_STATEMENT_FORMAT21c_TYPE=146;
	public static final int I_STATEMENT_FORMAT21ih=147;
	public static final int I_STATEMENT_FORMAT21lh=148;
	public static final int I_STATEMENT_FORMAT21s=149;
	public static final int I_STATEMENT_FORMAT21t=150;
	public static final int I_STATEMENT_FORMAT22b=151;
	public static final int I_STATEMENT_FORMAT22c_FIELD=152;
	public static final int I_STATEMENT_FORMAT22c_STRING=153;
	public static final int I_STATEMENT_FORMAT22c_TYPE=154;
	public static final int I_STATEMENT_FORMAT22s=155;
	public static final int I_STATEMENT_FORMAT22t=156;
	public static final int I_STATEMENT_FORMAT22x=157;
	public static final int I_STATEMENT_FORMAT23x=158;
	public static final int I_STATEMENT_FORMAT25x=159;
	public static final int I_STATEMENT_FORMAT30t=160;
	public static final int I_STATEMENT_FORMAT31c=161;
	public static final int I_STATEMENT_FORMAT31i=162;
	public static final int I_STATEMENT_FORMAT31t=163;
	public static final int I_STATEMENT_FORMAT32x=164;
	public static final int I_STATEMENT_FORMAT35c_METHOD=165;
	public static final int I_STATEMENT_FORMAT35c_TYPE=166;
	public static final int I_STATEMENT_FORMAT3rc_METHOD=167;
	public static final int I_STATEMENT_FORMAT3rc_TYPE=168;
	public static final int I_STATEMENT_FORMAT51l=169;
	public static final int I_STATEMENT_PACKED_SWITCH=170;
	public static final int I_STATEMENT_SPARSE_SWITCH=171;
	public static final int I_SUBANNOTATION=172;
	public static final int I_SUPER=173;
	public static final int LINE_COMMENT=174;
	public static final int LINE_DIRECTIVE=175;
	public static final int LOCALS_DIRECTIVE=176;
	public static final int LOCAL_DIRECTIVE=177;
	public static final int LONG_LITERAL=178;
	public static final int MEMBER_NAME=179;
	public static final int METHOD_DIRECTIVE=180;
	public static final int NEGATIVE_INTEGER_LITERAL=181;
	public static final int NULL_LITERAL=182;
	public static final int OPEN_BRACE=183;
	public static final int OPEN_PAREN=184;
	public static final int PACKED_SWITCH_DIRECTIVE=185;
	public static final int PARAMETER_DIRECTIVE=186;
	public static final int PARAM_LIST_END=187;
	public static final int PARAM_LIST_OR_ID_END=188;
	public static final int PARAM_LIST_OR_ID_START=189;
	public static final int PARAM_LIST_START=190;
	public static final int POSITIVE_INTEGER_LITERAL=191;
	public static final int PRIMITIVE_TYPE=192;
	public static final int PROLOGUE_DIRECTIVE=193;
	public static final int REGISTER=194;
	public static final int REGISTERS_DIRECTIVE=195;
	public static final int RESTART_LOCAL_DIRECTIVE=196;
	public static final int SHORT_LITERAL=197;
	public static final int SIMPLE_NAME=198;
	public static final int SOURCE_DIRECTIVE=199;
	public static final int SPARSE_SWITCH_DIRECTIVE=200;
	public static final int STRING_LITERAL=201;
	public static final int SUBANNOTATION_DIRECTIVE=202;
	public static final int SUPER_DIRECTIVE=203;
	public static final int VERIFICATION_ERROR_TYPE=204;
	public static final int VOID_TYPE=205;
	public static final int VTABLE_INDEX=206;
	public static final int WHITE_SPACE=207;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public smaliParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public smaliParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return smaliParser.tokenNames; }
	@Override public String getGrammarFileName() { return "shaka.smali/smali/smali/src/main/antlr/smaliParser.g"; }


	  public static final int ERROR_CHANNEL = 100;

	  private boolean verboseErrors = false;
	  private boolean allowOdex = false;
	  private int apiLevel = 15;
	  private Opcodes opcodes = new Opcodes(apiLevel, false);

	  public void setVerboseErrors(boolean verboseErrors) {
	    this.verboseErrors = verboseErrors;
	  }

	  public void setAllowOdex(boolean allowOdex) {
	      this.allowOdex = allowOdex;
	  }

	  public void setApiLevel(int apiLevel, boolean experimental) {
	      this.opcodes = new Opcodes(apiLevel, experimental);
	      this.apiLevel = apiLevel;
	  }

	  public String getErrorMessage(RecognitionException e,
	    String[] tokenNames) {

	    if (verboseErrors) {
	      List stack = getRuleInvocationStack(e, this.getClass().getName());
	      String msg = null;

	      if (e instanceof NoViableAltException) {
	        NoViableAltException nvae = (NoViableAltException)e;
	        msg = " no viable alt; token="+getTokenErrorDisplay(e.token)+
	        " (decision="+nvae.decisionNumber+
	        " state "+nvae.stateNumber+")"+
	        " decision=<<"+nvae.grammarDecisionDescription+">>";
	      } else {
	        msg = super.getErrorMessage(e, tokenNames);
	      }

	      return stack + " " + msg;
	    } else {
	      return super.getErrorMessage(e, tokenNames);
	    }
	  }

	  public String getTokenErrorDisplay(Token t) {
	    if (!verboseErrors) {
	      String s = t.getText();
	      if ( s==null ) {
	        if ( t.getType()==Token.EOF ) {
	          s = "<EOF>";
	        }
	        else {
	          s = "<"+tokenNames[t.getType()]+">";
	        }
	      }
	      s = s.replaceAll("\n","\\\\n");
	      s = s.replaceAll("\r","\\\\r");
	      s = s.replaceAll("\t","\\\\t");
	      return "'"+s+"'";
	    }

	    CommonToken ct = (CommonToken)t;

	    String channelStr = "";
	    if (t.getChannel()>0) {
	      channelStr=",channel="+t.getChannel();
	    }
	    String txt = t.getText();
	    if ( txt!=null ) {
	      txt = txt.replaceAll("\n","\\\\n");
	      txt = txt.replaceAll("\r","\\\\r");
	      txt = txt.replaceAll("\t","\\\\t");
	    }
	    else {
	      txt = "<no text>";
	    }
	    return "[@"+t.getTokenIndex()+","+ct.getStartIndex()+":"+ct.getStopIndex()+"='"+txt+"',<"+tokenNames[t.getType()]+">"+channelStr+","+t.getLine()+":"+t.getCharPositionInLine()+"]";
	  }

	  public String getErrorHeader(RecognitionException e) {
	    return getSourceName()+"["+ e.line+","+e.charPositionInLine+"]";
	  }

	  private CommonTree buildTree(int type, String text, List<CommonTree> children) {
	    CommonTree root = new CommonTree(new CommonToken(type, text));
	    for (CommonTree child: children) {
	      root.addChild(child);
	    }
	    return root;
	  }

	  private CommonToken getParamListSubToken(CommonToken baseToken, String str, int typeStartIndex) {
	    CommonToken token = new CommonToken(baseToken);
	    token.setStartIndex(baseToken.getStartIndex() + typeStartIndex);

	    switch (str.charAt(typeStartIndex)) {
	      case 'Z':
	      case 'B':
	      case 'S':
	      case 'C':
	      case 'I':
	      case 'J':
	      case 'F':
	      case 'D':
	      {
	        token.setType(PRIMITIVE_TYPE);
	        token.setText(str.substring(typeStartIndex, typeStartIndex+1));
	        token.setStopIndex(baseToken.getStartIndex() + typeStartIndex);
	        break;
	      }
	      case 'L':
	      {
	        int i = typeStartIndex;
	        while (str.charAt(++i) != ';');

	        token.setType(CLASS_DESCRIPTOR);
	        token.setText(str.substring(typeStartIndex, i + 1));
	        token.setStopIndex(baseToken.getStartIndex() + i);
	        break;
	      }
	      case '[':
	      {
	        int i = typeStartIndex;
	            while (str.charAt(++i) == '[');

	            if (str.charAt(i++) == 'L') {
	                while (str.charAt(i++) != ';');
	        }

	            token.setType(ARRAY_DESCRIPTOR);
	            token.setText(str.substring(typeStartIndex, i));
	            token.setStopIndex(baseToken.getStartIndex() + i - 1);
	            break;
	      }
	      default:
	        throw new RuntimeException(String.format("Invalid character '%c' in param list \"%s\" at position %d", str.charAt(typeStartIndex), str, typeStartIndex));
	    }

	    return token;
	  }

	  private CommonTree parseParamList(CommonToken paramListToken) {
	    String paramList = paramListToken.getText();
	    CommonTree root = new CommonTree();

	    int startIndex = paramListToken.getStartIndex();

	    int i=0;
	    while (i<paramList.length()) {
	      CommonToken token = getParamListSubToken(paramListToken, paramList, i);
	      root.addChild(new CommonTree(token));
	      i += token.getText().length();
	    }

	    if (root.getChildCount() == 0) {
	      return null;
	    }
	    return root;
	  }

	  private void throwOdexedInstructionException(IntStream input, String odexedInstruction)
	      throws OdexedInstructionException {
	    /*this has to be done in a separate method, otherwise java will complain about the
	    auto-generated code in the rule after the throw not being reachable*/
	    throw new OdexedInstructionException(input, odexedInstruction);
	  }


	protected static class smali_file_scope {
		boolean hasClassSpec;
		boolean hasSuperSpec;
		boolean hasSourceSpec;
		List<CommonTree> classAnnotations;
	}
	protected Stack<smali_file_scope> smali_file_stack = new Stack<smali_file_scope>();

	public static class smali_file_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "smali_file"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:426:1: smali_file : ({...}? => class_spec |{...}? => super_spec | implements_spec |{...}? => source_spec | method | field | annotation )+ EOF -> ^( I_CLASS_DEF class_spec ( super_spec )? ( implements_spec )* ( source_spec )? ^( I_METHODS ( method )* ) ^( I_FIELDS ( field )* ) ) ;
	public final smaliParser.smali_file_return smali_file() throws RecognitionException {
		smali_file_stack.push(new smali_file_scope());
		smaliParser.smali_file_return retval = new smaliParser.smali_file_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token EOF8=null;
		ParserRuleReturnScope class_spec1 =null;
		ParserRuleReturnScope super_spec2 =null;
		ParserRuleReturnScope implements_spec3 =null;
		ParserRuleReturnScope source_spec4 =null;
		ParserRuleReturnScope method5 =null;
		ParserRuleReturnScope field6 =null;
		ParserRuleReturnScope annotation7 =null;

		CommonTree EOF8_tree=null;
		RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
		RewriteRuleSubtreeStream stream_field=new RewriteRuleSubtreeStream(adaptor,"rule field");
		RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");
		RewriteRuleSubtreeStream stream_super_spec=new RewriteRuleSubtreeStream(adaptor,"rule super_spec");
		RewriteRuleSubtreeStream stream_implements_spec=new RewriteRuleSubtreeStream(adaptor,"rule implements_spec");
		RewriteRuleSubtreeStream stream_source_spec=new RewriteRuleSubtreeStream(adaptor,"rule source_spec");
		RewriteRuleSubtreeStream stream_method=new RewriteRuleSubtreeStream(adaptor,"rule method");
		RewriteRuleSubtreeStream stream_class_spec=new RewriteRuleSubtreeStream(adaptor,"rule class_spec");

		 smali_file_stack.peek().hasClassSpec = smali_file_stack.peek().hasSuperSpec = smali_file_stack.peek().hasSourceSpec = false;
		    smali_file_stack.peek().classAnnotations = new ArrayList<CommonTree>();
		  
		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:438:3: ( ({...}? => class_spec |{...}? => super_spec | implements_spec |{...}? => source_spec | method | field | annotation )+ EOF -> ^( I_CLASS_DEF class_spec ( super_spec )? ( implements_spec )* ( source_spec )? ^( I_METHODS ( method )* ) ^( I_FIELDS ( field )* ) ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:439:3: ({...}? => class_spec |{...}? => super_spec | implements_spec |{...}? => source_spec | method | field | annotation )+ EOF
			{
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:439:3: ({...}? => class_spec |{...}? => super_spec | implements_spec |{...}? => source_spec | method | field | annotation )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=8;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==CLASS_DIRECTIVE) && ((!smali_file_stack.peek().hasClassSpec))) {
					alt1=1;
				}
				else if ( (LA1_0==SUPER_DIRECTIVE) && ((!smali_file_stack.peek().hasSuperSpec))) {
					alt1=2;
				}
				else if ( (LA1_0==IMPLEMENTS_DIRECTIVE) ) {
					alt1=3;
				}
				else if ( (LA1_0==SOURCE_DIRECTIVE) && ((!smali_file_stack.peek().hasSourceSpec))) {
					alt1=4;
				}
				else if ( (LA1_0==METHOD_DIRECTIVE) ) {
					alt1=5;
				}
				else if ( (LA1_0==FIELD_DIRECTIVE) ) {
					alt1=6;
				}
				else if ( (LA1_0==ANNOTATION_DIRECTIVE) ) {
					alt1=7;
				}

				switch (alt1) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:439:5: {...}? => class_spec
					{
					if ( !((!smali_file_stack.peek().hasClassSpec)) ) {
						throw new FailedPredicateException(input, "smali_file", "!$smali_file::hasClassSpec");
					}
					pushFollow(FOLLOW_class_spec_in_smali_file1110);
					class_spec1=class_spec();
					state._fsp--;

					stream_class_spec.add(class_spec1.getTree());
					smali_file_stack.peek().hasClassSpec = true;
					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:440:5: {...}? => super_spec
					{
					if ( !((!smali_file_stack.peek().hasSuperSpec)) ) {
						throw new FailedPredicateException(input, "smali_file", "!$smali_file::hasSuperSpec");
					}
					pushFollow(FOLLOW_super_spec_in_smali_file1121);
					super_spec2=super_spec();
					state._fsp--;

					stream_super_spec.add(super_spec2.getTree());
					smali_file_stack.peek().hasSuperSpec = true;
					}
					break;
				case 3 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:441:5: implements_spec
					{
					pushFollow(FOLLOW_implements_spec_in_smali_file1129);
					implements_spec3=implements_spec();
					state._fsp--;

					stream_implements_spec.add(implements_spec3.getTree());
					}
					break;
				case 4 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:442:5: {...}? => source_spec
					{
					if ( !((!smali_file_stack.peek().hasSourceSpec)) ) {
						throw new FailedPredicateException(input, "smali_file", "!$smali_file::hasSourceSpec");
					}
					pushFollow(FOLLOW_source_spec_in_smali_file1138);
					source_spec4=source_spec();
					state._fsp--;

					stream_source_spec.add(source_spec4.getTree());
					smali_file_stack.peek().hasSourceSpec = true;
					}
					break;
				case 5 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:443:5: method
					{
					pushFollow(FOLLOW_method_in_smali_file1146);
					method5=method();
					state._fsp--;

					stream_method.add(method5.getTree());
					}
					break;
				case 6 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:444:5: field
					{
					pushFollow(FOLLOW_field_in_smali_file1152);
					field6=field();
					state._fsp--;

					stream_field.add(field6.getTree());
					}
					break;
				case 7 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:445:5: annotation
					{
					pushFollow(FOLLOW_annotation_in_smali_file1158);
					annotation7=annotation();
					state._fsp--;

					stream_annotation.add(annotation7.getTree());
					smali_file_stack.peek().classAnnotations.add((annotation7!=null?((CommonTree)annotation7.getTree()):null));
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			EOF8=(Token)match(input,EOF,FOLLOW_EOF_in_smali_file1169);  
			stream_EOF.add(EOF8);


			    if (!smali_file_stack.peek().hasClassSpec) {
			      throw new SemanticException(input, "The file must contain a .class directive");
			    }

			    if (!smali_file_stack.peek().hasSuperSpec) {
			      if (!(class_spec1!=null?((smaliParser.class_spec_return)class_spec1).className:null).equals("Ljava/lang/Object;")) {
			        throw new SemanticException(input, "The file must contain a .super directive");
			      }
			    }
			  
			// AST REWRITE
			// elements: method, class_spec, source_spec, implements_spec, super_spec, field
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 459:3: -> ^( I_CLASS_DEF class_spec ( super_spec )? ( implements_spec )* ( source_spec )? ^( I_METHODS ( method )* ) ^( I_FIELDS ( field )* ) )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:459:6: ^( I_CLASS_DEF class_spec ( super_spec )? ( implements_spec )* ( source_spec )? ^( I_METHODS ( method )* ) ^( I_FIELDS ( field )* ) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_CLASS_DEF, "I_CLASS_DEF"), root_1);
				adaptor.addChild(root_1, stream_class_spec.nextTree());
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:461:8: ( super_spec )?
				if ( stream_super_spec.hasNext() ) {
					adaptor.addChild(root_1, stream_super_spec.nextTree());
				}
				stream_super_spec.reset();

				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:462:8: ( implements_spec )*
				while ( stream_implements_spec.hasNext() ) {
					adaptor.addChild(root_1, stream_implements_spec.nextTree());
				}
				stream_implements_spec.reset();

				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:463:8: ( source_spec )?
				if ( stream_source_spec.hasNext() ) {
					adaptor.addChild(root_1, stream_source_spec.nextTree());
				}
				stream_source_spec.reset();

				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:464:8: ^( I_METHODS ( method )* )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_METHODS, "I_METHODS"), root_2);
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:464:20: ( method )*
				while ( stream_method.hasNext() ) {
					adaptor.addChild(root_2, stream_method.nextTree());
				}
				stream_method.reset();

				adaptor.addChild(root_1, root_2);
				}

				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:464:29: ^( I_FIELDS ( field )* )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_FIELDS, "I_FIELDS"), root_2);
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:464:40: ( field )*
				while ( stream_field.hasNext() ) {
					adaptor.addChild(root_2, stream_field.nextTree());
				}
				stream_field.reset();

				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, buildTree(I_ANNOTATIONS, "I_ANNOTATIONS", smali_file_stack.peek().classAnnotations));
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			smali_file_stack.pop();
		}
		return retval;
	}
	// $ANTLR end "smali_file"


	public static class class_spec_return extends ParserRuleReturnScope {
		public String className;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "class_spec"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:466:1: class_spec returns [String className] : CLASS_DIRECTIVE access_list CLASS_DESCRIPTOR -> CLASS_DESCRIPTOR access_list ;
	public final smaliParser.class_spec_return class_spec() throws RecognitionException {
		smaliParser.class_spec_return retval = new smaliParser.class_spec_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token CLASS_DIRECTIVE9=null;
		Token CLASS_DESCRIPTOR11=null;
		ParserRuleReturnScope access_list10 =null;

		CommonTree CLASS_DIRECTIVE9_tree=null;
		CommonTree CLASS_DESCRIPTOR11_tree=null;
		RewriteRuleTokenStream stream_CLASS_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token CLASS_DIRECTIVE");
		RewriteRuleTokenStream stream_CLASS_DESCRIPTOR=new RewriteRuleTokenStream(adaptor,"token CLASS_DESCRIPTOR");
		RewriteRuleSubtreeStream stream_access_list=new RewriteRuleSubtreeStream(adaptor,"rule access_list");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:467:3: ( CLASS_DIRECTIVE access_list CLASS_DESCRIPTOR -> CLASS_DESCRIPTOR access_list )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:467:5: CLASS_DIRECTIVE access_list CLASS_DESCRIPTOR
			{
			CLASS_DIRECTIVE9=(Token)match(input,CLASS_DIRECTIVE,FOLLOW_CLASS_DIRECTIVE_in_class_spec1256);  
			stream_CLASS_DIRECTIVE.add(CLASS_DIRECTIVE9);

			pushFollow(FOLLOW_access_list_in_class_spec1258);
			access_list10=access_list();
			state._fsp--;

			stream_access_list.add(access_list10.getTree());
			CLASS_DESCRIPTOR11=(Token)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_class_spec1260);  
			stream_CLASS_DESCRIPTOR.add(CLASS_DESCRIPTOR11);

			retval.className = (CLASS_DESCRIPTOR11!=null?CLASS_DESCRIPTOR11.getText():null);
			// AST REWRITE
			// elements: access_list, CLASS_DESCRIPTOR
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 467:89: -> CLASS_DESCRIPTOR access_list
			{
				adaptor.addChild(root_0, stream_CLASS_DESCRIPTOR.nextNode());
				adaptor.addChild(root_0, stream_access_list.nextTree());
			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "class_spec"


	public static class super_spec_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "super_spec"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:469:1: super_spec : SUPER_DIRECTIVE CLASS_DESCRIPTOR -> ^( I_SUPER[$start, \"I_SUPER\"] CLASS_DESCRIPTOR ) ;
	public final smaliParser.super_spec_return super_spec() throws RecognitionException {
		smaliParser.super_spec_return retval = new smaliParser.super_spec_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SUPER_DIRECTIVE12=null;
		Token CLASS_DESCRIPTOR13=null;

		CommonTree SUPER_DIRECTIVE12_tree=null;
		CommonTree CLASS_DESCRIPTOR13_tree=null;
		RewriteRuleTokenStream stream_SUPER_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token SUPER_DIRECTIVE");
		RewriteRuleTokenStream stream_CLASS_DESCRIPTOR=new RewriteRuleTokenStream(adaptor,"token CLASS_DESCRIPTOR");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:470:3: ( SUPER_DIRECTIVE CLASS_DESCRIPTOR -> ^( I_SUPER[$start, \"I_SUPER\"] CLASS_DESCRIPTOR ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:470:5: SUPER_DIRECTIVE CLASS_DESCRIPTOR
			{
			SUPER_DIRECTIVE12=(Token)match(input,SUPER_DIRECTIVE,FOLLOW_SUPER_DIRECTIVE_in_super_spec1278);  
			stream_SUPER_DIRECTIVE.add(SUPER_DIRECTIVE12);

			CLASS_DESCRIPTOR13=(Token)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_super_spec1280);  
			stream_CLASS_DESCRIPTOR.add(CLASS_DESCRIPTOR13);

			// AST REWRITE
			// elements: CLASS_DESCRIPTOR
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 470:38: -> ^( I_SUPER[$start, \"I_SUPER\"] CLASS_DESCRIPTOR )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:470:41: ^( I_SUPER[$start, \"I_SUPER\"] CLASS_DESCRIPTOR )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_SUPER, (retval.start), "I_SUPER"), root_1);
				adaptor.addChild(root_1, stream_CLASS_DESCRIPTOR.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "super_spec"


	public static class implements_spec_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "implements_spec"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:472:1: implements_spec : IMPLEMENTS_DIRECTIVE CLASS_DESCRIPTOR -> ^( I_IMPLEMENTS[$start, \"I_IMPLEMENTS\"] CLASS_DESCRIPTOR ) ;
	public final smaliParser.implements_spec_return implements_spec() throws RecognitionException {
		smaliParser.implements_spec_return retval = new smaliParser.implements_spec_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token IMPLEMENTS_DIRECTIVE14=null;
		Token CLASS_DESCRIPTOR15=null;

		CommonTree IMPLEMENTS_DIRECTIVE14_tree=null;
		CommonTree CLASS_DESCRIPTOR15_tree=null;
		RewriteRuleTokenStream stream_IMPLEMENTS_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token IMPLEMENTS_DIRECTIVE");
		RewriteRuleTokenStream stream_CLASS_DESCRIPTOR=new RewriteRuleTokenStream(adaptor,"token CLASS_DESCRIPTOR");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:473:3: ( IMPLEMENTS_DIRECTIVE CLASS_DESCRIPTOR -> ^( I_IMPLEMENTS[$start, \"I_IMPLEMENTS\"] CLASS_DESCRIPTOR ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:473:5: IMPLEMENTS_DIRECTIVE CLASS_DESCRIPTOR
			{
			IMPLEMENTS_DIRECTIVE14=(Token)match(input,IMPLEMENTS_DIRECTIVE,FOLLOW_IMPLEMENTS_DIRECTIVE_in_implements_spec1299);  
			stream_IMPLEMENTS_DIRECTIVE.add(IMPLEMENTS_DIRECTIVE14);

			CLASS_DESCRIPTOR15=(Token)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_implements_spec1301);  
			stream_CLASS_DESCRIPTOR.add(CLASS_DESCRIPTOR15);

			// AST REWRITE
			// elements: CLASS_DESCRIPTOR
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 473:43: -> ^( I_IMPLEMENTS[$start, \"I_IMPLEMENTS\"] CLASS_DESCRIPTOR )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:473:46: ^( I_IMPLEMENTS[$start, \"I_IMPLEMENTS\"] CLASS_DESCRIPTOR )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_IMPLEMENTS, (retval.start), "I_IMPLEMENTS"), root_1);
				adaptor.addChild(root_1, stream_CLASS_DESCRIPTOR.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "implements_spec"


	public static class source_spec_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "source_spec"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:475:1: source_spec : SOURCE_DIRECTIVE STRING_LITERAL -> ^( I_SOURCE[$start, \"I_SOURCE\"] STRING_LITERAL ) ;
	public final smaliParser.source_spec_return source_spec() throws RecognitionException {
		smaliParser.source_spec_return retval = new smaliParser.source_spec_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SOURCE_DIRECTIVE16=null;
		Token STRING_LITERAL17=null;

		CommonTree SOURCE_DIRECTIVE16_tree=null;
		CommonTree STRING_LITERAL17_tree=null;
		RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
		RewriteRuleTokenStream stream_SOURCE_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token SOURCE_DIRECTIVE");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:476:3: ( SOURCE_DIRECTIVE STRING_LITERAL -> ^( I_SOURCE[$start, \"I_SOURCE\"] STRING_LITERAL ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:476:5: SOURCE_DIRECTIVE STRING_LITERAL
			{
			SOURCE_DIRECTIVE16=(Token)match(input,SOURCE_DIRECTIVE,FOLLOW_SOURCE_DIRECTIVE_in_source_spec1320);  
			stream_SOURCE_DIRECTIVE.add(SOURCE_DIRECTIVE16);

			STRING_LITERAL17=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_source_spec1322);  
			stream_STRING_LITERAL.add(STRING_LITERAL17);

			// AST REWRITE
			// elements: STRING_LITERAL
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 476:37: -> ^( I_SOURCE[$start, \"I_SOURCE\"] STRING_LITERAL )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:476:40: ^( I_SOURCE[$start, \"I_SOURCE\"] STRING_LITERAL )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_SOURCE, (retval.start), "I_SOURCE"), root_1);
				adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "source_spec"


	public static class access_list_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "access_list"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:478:1: access_list : ( ACCESS_SPEC )* -> ^( I_ACCESS_LIST[$start,\"I_ACCESS_LIST\"] ( ACCESS_SPEC )* ) ;
	public final smaliParser.access_list_return access_list() throws RecognitionException {
		smaliParser.access_list_return retval = new smaliParser.access_list_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ACCESS_SPEC18=null;

		CommonTree ACCESS_SPEC18_tree=null;
		RewriteRuleTokenStream stream_ACCESS_SPEC=new RewriteRuleTokenStream(adaptor,"token ACCESS_SPEC");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:479:3: ( ( ACCESS_SPEC )* -> ^( I_ACCESS_LIST[$start,\"I_ACCESS_LIST\"] ( ACCESS_SPEC )* ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:479:5: ( ACCESS_SPEC )*
			{
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:479:5: ( ACCESS_SPEC )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==ACCESS_SPEC) ) {
					int LA2_2 = input.LA(2);
					if ( (LA2_2==ACCESS_SPEC||LA2_2==ANNOTATION_VISIBILITY||LA2_2==BOOL_LITERAL||LA2_2==CLASS_DESCRIPTOR||LA2_2==DOUBLE_LITERAL_OR_ID||LA2_2==FLOAT_LITERAL_OR_ID||(LA2_2 >= INSTRUCTION_FORMAT10t && LA2_2 <= INSTRUCTION_FORMAT10x_ODEX)||LA2_2==INSTRUCTION_FORMAT11x||LA2_2==INSTRUCTION_FORMAT12x_OR_ID||(LA2_2 >= INSTRUCTION_FORMAT21c_FIELD && LA2_2 <= INSTRUCTION_FORMAT21c_TYPE)||LA2_2==INSTRUCTION_FORMAT21t||(LA2_2 >= INSTRUCTION_FORMAT22c_FIELD && LA2_2 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA2_2 >= INSTRUCTION_FORMAT22s_OR_ID && LA2_2 <= INSTRUCTION_FORMAT22t)||(LA2_2 >= INSTRUCTION_FORMAT23x && LA2_2 <= INSTRUCTION_FORMAT25x)||(LA2_2 >= INSTRUCTION_FORMAT31i_OR_ID && LA2_2 <= INSTRUCTION_FORMAT31t)||(LA2_2 >= INSTRUCTION_FORMAT35c_METHOD && LA2_2 <= INSTRUCTION_FORMAT35ms_METHOD)||LA2_2==INSTRUCTION_FORMAT51l||LA2_2==MEMBER_NAME||(LA2_2 >= NEGATIVE_INTEGER_LITERAL && LA2_2 <= NULL_LITERAL)||LA2_2==PARAM_LIST_OR_ID_START||(LA2_2 >= POSITIVE_INTEGER_LITERAL && LA2_2 <= PRIMITIVE_TYPE)||LA2_2==REGISTER||LA2_2==SIMPLE_NAME||(LA2_2 >= VERIFICATION_ERROR_TYPE && LA2_2 <= VOID_TYPE)) ) {
						alt2=1;
					}

				}

				switch (alt2) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:479:5: ACCESS_SPEC
					{
					ACCESS_SPEC18=(Token)match(input,ACCESS_SPEC,FOLLOW_ACCESS_SPEC_in_access_list1341);  
					stream_ACCESS_SPEC.add(ACCESS_SPEC18);

					}
					break;

				default :
					break loop2;
				}
			}

			// AST REWRITE
			// elements: ACCESS_SPEC
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 479:18: -> ^( I_ACCESS_LIST[$start,\"I_ACCESS_LIST\"] ( ACCESS_SPEC )* )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:479:21: ^( I_ACCESS_LIST[$start,\"I_ACCESS_LIST\"] ( ACCESS_SPEC )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ACCESS_LIST, (retval.start), "I_ACCESS_LIST"), root_1);
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:479:61: ( ACCESS_SPEC )*
				while ( stream_ACCESS_SPEC.hasNext() ) {
					adaptor.addChild(root_1, stream_ACCESS_SPEC.nextNode());
				}
				stream_ACCESS_SPEC.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "access_list"


	public static class field_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "field"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:486:1: field : FIELD_DIRECTIVE access_list member_name COLON nonvoid_type_descriptor ( EQUAL literal )? ( ({...}? annotation )* ( END_FIELD_DIRECTIVE -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ) ) ) ) ;
	public final smaliParser.field_return field() throws RecognitionException {
		smaliParser.field_return retval = new smaliParser.field_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token FIELD_DIRECTIVE19=null;
		Token COLON22=null;
		Token EQUAL24=null;
		Token END_FIELD_DIRECTIVE27=null;
		ParserRuleReturnScope access_list20 =null;
		ParserRuleReturnScope member_name21 =null;
		ParserRuleReturnScope nonvoid_type_descriptor23 =null;
		ParserRuleReturnScope literal25 =null;
		ParserRuleReturnScope annotation26 =null;

		CommonTree FIELD_DIRECTIVE19_tree=null;
		CommonTree COLON22_tree=null;
		CommonTree EQUAL24_tree=null;
		CommonTree END_FIELD_DIRECTIVE27_tree=null;
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleTokenStream stream_FIELD_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token FIELD_DIRECTIVE");
		RewriteRuleTokenStream stream_EQUAL=new RewriteRuleTokenStream(adaptor,"token EQUAL");
		RewriteRuleTokenStream stream_END_FIELD_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_FIELD_DIRECTIVE");
		RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");
		RewriteRuleSubtreeStream stream_access_list=new RewriteRuleSubtreeStream(adaptor,"rule access_list");
		RewriteRuleSubtreeStream stream_member_name=new RewriteRuleSubtreeStream(adaptor,"rule member_name");
		RewriteRuleSubtreeStream stream_literal=new RewriteRuleSubtreeStream(adaptor,"rule literal");

		List<CommonTree> annotations = new ArrayList<CommonTree>();
		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:488:3: ( FIELD_DIRECTIVE access_list member_name COLON nonvoid_type_descriptor ( EQUAL literal )? ( ({...}? annotation )* ( END_FIELD_DIRECTIVE -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ) ) ) ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:488:5: FIELD_DIRECTIVE access_list member_name COLON nonvoid_type_descriptor ( EQUAL literal )? ( ({...}? annotation )* ( END_FIELD_DIRECTIVE -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ) ) ) )
			{
			FIELD_DIRECTIVE19=(Token)match(input,FIELD_DIRECTIVE,FOLLOW_FIELD_DIRECTIVE_in_field1372);  
			stream_FIELD_DIRECTIVE.add(FIELD_DIRECTIVE19);

			pushFollow(FOLLOW_access_list_in_field1374);
			access_list20=access_list();
			state._fsp--;

			stream_access_list.add(access_list20.getTree());
			pushFollow(FOLLOW_member_name_in_field1376);
			member_name21=member_name();
			state._fsp--;

			stream_member_name.add(member_name21.getTree());
			COLON22=(Token)match(input,COLON,FOLLOW_COLON_in_field1378);  
			stream_COLON.add(COLON22);

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_field1380);
			nonvoid_type_descriptor23=nonvoid_type_descriptor();
			state._fsp--;

			stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor23.getTree());
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:488:75: ( EQUAL literal )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==EQUAL) ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:488:76: EQUAL literal
					{
					EQUAL24=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_field1383);  
					stream_EQUAL.add(EQUAL24);

					pushFollow(FOLLOW_literal_in_field1385);
					literal25=literal();
					state._fsp--;

					stream_literal.add(literal25.getTree());
					}
					break;

			}

			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:489:5: ( ({...}? annotation )* ( END_FIELD_DIRECTIVE -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ) ) ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:489:7: ({...}? annotation )* ( END_FIELD_DIRECTIVE -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ) ) )
			{
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:489:7: ({...}? annotation )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==ANNOTATION_DIRECTIVE) ) {
					int LA4_9 = input.LA(2);
					if ( ((input.LA(1) == ANNOTATION_DIRECTIVE)) ) {
						alt4=1;
					}

				}

				switch (alt4) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:489:8: {...}? annotation
					{
					if ( !((input.LA(1) == ANNOTATION_DIRECTIVE)) ) {
						throw new FailedPredicateException(input, "field", "input.LA(1) == ANNOTATION_DIRECTIVE");
					}
					pushFollow(FOLLOW_annotation_in_field1398);
					annotation26=annotation();
					state._fsp--;

					stream_annotation.add(annotation26.getTree());
					annotations.add((annotation26!=null?((CommonTree)annotation26.getTree()):null));
					}
					break;

				default :
					break loop4;
				}
			}

			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:490:7: ( END_FIELD_DIRECTIVE -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ) ) )
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==END_FIELD_DIRECTIVE) ) {
				alt5=1;
			}
			else if ( (LA5_0==EOF||LA5_0==ANNOTATION_DIRECTIVE||LA5_0==CLASS_DIRECTIVE||LA5_0==FIELD_DIRECTIVE||LA5_0==IMPLEMENTS_DIRECTIVE||LA5_0==METHOD_DIRECTIVE||LA5_0==SOURCE_DIRECTIVE||LA5_0==SUPER_DIRECTIVE) ) {
				alt5=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}

			switch (alt5) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:490:9: END_FIELD_DIRECTIVE
					{
					END_FIELD_DIRECTIVE27=(Token)match(input,END_FIELD_DIRECTIVE,FOLLOW_END_FIELD_DIRECTIVE_in_field1412);  
					stream_END_FIELD_DIRECTIVE.add(END_FIELD_DIRECTIVE27);

					// AST REWRITE
					// elements: nonvoid_type_descriptor, literal, annotation, member_name, access_list
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 491:9: -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ( annotation )* ) )
					{
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:491:12: ^( I_FIELD[$start, \"I_FIELD\"] member_name access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ( annotation )* ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_FIELD, (retval.start), "I_FIELD"), root_1);
						adaptor.addChild(root_1, stream_member_name.nextTree());
						adaptor.addChild(root_1, stream_access_list.nextTree());
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:491:65: ^( I_FIELD_TYPE nonvoid_type_descriptor )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_FIELD_TYPE, "I_FIELD_TYPE"), root_2);
						adaptor.addChild(root_2, stream_nonvoid_type_descriptor.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:491:105: ( ^( I_FIELD_INITIAL_VALUE literal ) )?
						if ( stream_literal.hasNext() ) {
							// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:491:105: ^( I_FIELD_INITIAL_VALUE literal )
							{
							CommonTree root_2 = (CommonTree)adaptor.nil();
							root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_FIELD_INITIAL_VALUE, "I_FIELD_INITIAL_VALUE"), root_2);
							adaptor.addChild(root_2, stream_literal.nextTree());
							adaptor.addChild(root_1, root_2);
							}

						}
						stream_literal.reset();

						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:491:139: ^( I_ANNOTATIONS ( annotation )* )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ANNOTATIONS, "I_ANNOTATIONS"), root_2);
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:491:155: ( annotation )*
						while ( stream_annotation.hasNext() ) {
							adaptor.addChild(root_2, stream_annotation.nextTree());
						}
						stream_annotation.reset();

						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:492:21: 
					{
					smali_file_stack.peek().classAnnotations.addAll(annotations);
					// AST REWRITE
					// elements: access_list, literal, member_name, nonvoid_type_descriptor
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 493:9: -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ) )
					{
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:493:12: ^( I_FIELD[$start, \"I_FIELD\"] member_name access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_FIELD, (retval.start), "I_FIELD"), root_1);
						adaptor.addChild(root_1, stream_member_name.nextTree());
						adaptor.addChild(root_1, stream_access_list.nextTree());
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:493:65: ^( I_FIELD_TYPE nonvoid_type_descriptor )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_FIELD_TYPE, "I_FIELD_TYPE"), root_2);
						adaptor.addChild(root_2, stream_nonvoid_type_descriptor.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:493:105: ( ^( I_FIELD_INITIAL_VALUE literal ) )?
						if ( stream_literal.hasNext() ) {
							// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:493:105: ^( I_FIELD_INITIAL_VALUE literal )
							{
							CommonTree root_2 = (CommonTree)adaptor.nil();
							root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_FIELD_INITIAL_VALUE, "I_FIELD_INITIAL_VALUE"), root_2);
							adaptor.addChild(root_2, stream_literal.nextTree());
							adaptor.addChild(root_1, root_2);
							}

						}
						stream_literal.reset();

						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:493:139: ^( I_ANNOTATIONS )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ANNOTATIONS, "I_ANNOTATIONS"), root_2);
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "field"


	public static class method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "method"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:497:1: method : METHOD_DIRECTIVE access_list member_name method_prototype statements_and_directives END_METHOD_DIRECTIVE -> ^( I_METHOD[$start, \"I_METHOD\"] member_name method_prototype access_list statements_and_directives ) ;
	public final smaliParser.method_return method() throws RecognitionException {
		smaliParser.method_return retval = new smaliParser.method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token METHOD_DIRECTIVE28=null;
		Token END_METHOD_DIRECTIVE33=null;
		ParserRuleReturnScope access_list29 =null;
		ParserRuleReturnScope member_name30 =null;
		ParserRuleReturnScope method_prototype31 =null;
		ParserRuleReturnScope statements_and_directives32 =null;

		CommonTree METHOD_DIRECTIVE28_tree=null;
		CommonTree END_METHOD_DIRECTIVE33_tree=null;
		RewriteRuleTokenStream stream_END_METHOD_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_METHOD_DIRECTIVE");
		RewriteRuleTokenStream stream_METHOD_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token METHOD_DIRECTIVE");
		RewriteRuleSubtreeStream stream_access_list=new RewriteRuleSubtreeStream(adaptor,"rule access_list");
		RewriteRuleSubtreeStream stream_method_prototype=new RewriteRuleSubtreeStream(adaptor,"rule method_prototype");
		RewriteRuleSubtreeStream stream_statements_and_directives=new RewriteRuleSubtreeStream(adaptor,"rule statements_and_directives");
		RewriteRuleSubtreeStream stream_member_name=new RewriteRuleSubtreeStream(adaptor,"rule member_name");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:498:3: ( METHOD_DIRECTIVE access_list member_name method_prototype statements_and_directives END_METHOD_DIRECTIVE -> ^( I_METHOD[$start, \"I_METHOD\"] member_name method_prototype access_list statements_and_directives ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:498:5: METHOD_DIRECTIVE access_list member_name method_prototype statements_and_directives END_METHOD_DIRECTIVE
			{
			METHOD_DIRECTIVE28=(Token)match(input,METHOD_DIRECTIVE,FOLLOW_METHOD_DIRECTIVE_in_method1523);  
			stream_METHOD_DIRECTIVE.add(METHOD_DIRECTIVE28);

			pushFollow(FOLLOW_access_list_in_method1525);
			access_list29=access_list();
			state._fsp--;

			stream_access_list.add(access_list29.getTree());
			pushFollow(FOLLOW_member_name_in_method1527);
			member_name30=member_name();
			state._fsp--;

			stream_member_name.add(member_name30.getTree());
			pushFollow(FOLLOW_method_prototype_in_method1529);
			method_prototype31=method_prototype();
			state._fsp--;

			stream_method_prototype.add(method_prototype31.getTree());
			pushFollow(FOLLOW_statements_and_directives_in_method1531);
			statements_and_directives32=statements_and_directives();
			state._fsp--;

			stream_statements_and_directives.add(statements_and_directives32.getTree());
			END_METHOD_DIRECTIVE33=(Token)match(input,END_METHOD_DIRECTIVE,FOLLOW_END_METHOD_DIRECTIVE_in_method1537);  
			stream_END_METHOD_DIRECTIVE.add(END_METHOD_DIRECTIVE33);

			// AST REWRITE
			// elements: member_name, method_prototype, statements_and_directives, access_list
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 500:5: -> ^( I_METHOD[$start, \"I_METHOD\"] member_name method_prototype access_list statements_and_directives )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:500:8: ^( I_METHOD[$start, \"I_METHOD\"] member_name method_prototype access_list statements_and_directives )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_METHOD, (retval.start), "I_METHOD"), root_1);
				adaptor.addChild(root_1, stream_member_name.nextTree());
				adaptor.addChild(root_1, stream_method_prototype.nextTree());
				adaptor.addChild(root_1, stream_access_list.nextTree());
				adaptor.addChild(root_1, stream_statements_and_directives.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "method"


	protected static class statements_and_directives_scope {
		boolean hasRegistersDirective;
		List<CommonTree> methodAnnotations;
	}
	protected Stack<statements_and_directives_scope> statements_and_directives_stack = new Stack<statements_and_directives_scope>();

	public static class statements_and_directives_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "statements_and_directives"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:502:1: statements_and_directives : ( ordered_method_item | registers_directive | catch_directive | catchall_directive | parameter_directive | annotation )* -> ( registers_directive )? ^( I_ORDERED_METHOD_ITEMS ( ordered_method_item )* ) ^( I_CATCHES ( catch_directive )* ( catchall_directive )* ) ^( I_PARAMETERS ( parameter_directive )* ) ;
	public final smaliParser.statements_and_directives_return statements_and_directives() throws RecognitionException {
		statements_and_directives_stack.push(new statements_and_directives_scope());
		smaliParser.statements_and_directives_return retval = new smaliParser.statements_and_directives_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope ordered_method_item34 =null;
		ParserRuleReturnScope registers_directive35 =null;
		ParserRuleReturnScope catch_directive36 =null;
		ParserRuleReturnScope catchall_directive37 =null;
		ParserRuleReturnScope parameter_directive38 =null;
		ParserRuleReturnScope annotation39 =null;

		RewriteRuleSubtreeStream stream_catchall_directive=new RewriteRuleSubtreeStream(adaptor,"rule catchall_directive");
		RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");
		RewriteRuleSubtreeStream stream_ordered_method_item=new RewriteRuleSubtreeStream(adaptor,"rule ordered_method_item");
		RewriteRuleSubtreeStream stream_catch_directive=new RewriteRuleSubtreeStream(adaptor,"rule catch_directive");
		RewriteRuleSubtreeStream stream_registers_directive=new RewriteRuleSubtreeStream(adaptor,"rule registers_directive");
		RewriteRuleSubtreeStream stream_parameter_directive=new RewriteRuleSubtreeStream(adaptor,"rule parameter_directive");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:508:3: ( ( ordered_method_item | registers_directive | catch_directive | catchall_directive | parameter_directive | annotation )* -> ( registers_directive )? ^( I_ORDERED_METHOD_ITEMS ( ordered_method_item )* ) ^( I_CATCHES ( catch_directive )* ( catchall_directive )* ) ^( I_PARAMETERS ( parameter_directive )* ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:508:5: ( ordered_method_item | registers_directive | catch_directive | catchall_directive | parameter_directive | annotation )*
			{

			      statements_and_directives_stack.peek().hasRegistersDirective = false;
			      statements_and_directives_stack.peek().methodAnnotations = new ArrayList<CommonTree>();
			    
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:512:5: ( ordered_method_item | registers_directive | catch_directive | catchall_directive | parameter_directive | annotation )*
			loop6:
			while (true) {
				int alt6=7;
				switch ( input.LA(1) ) {
				case ARRAY_DATA_DIRECTIVE:
				case COLON:
				case END_LOCAL_DIRECTIVE:
				case EPILOGUE_DIRECTIVE:
				case INSTRUCTION_FORMAT10t:
				case INSTRUCTION_FORMAT10x:
				case INSTRUCTION_FORMAT10x_ODEX:
				case INSTRUCTION_FORMAT11n:
				case INSTRUCTION_FORMAT11x:
				case INSTRUCTION_FORMAT12x:
				case INSTRUCTION_FORMAT12x_OR_ID:
				case INSTRUCTION_FORMAT20bc:
				case INSTRUCTION_FORMAT20t:
				case INSTRUCTION_FORMAT21c_FIELD:
				case INSTRUCTION_FORMAT21c_FIELD_ODEX:
				case INSTRUCTION_FORMAT21c_LAMBDA:
				case INSTRUCTION_FORMAT21c_METHOD:
				case INSTRUCTION_FORMAT21c_STRING:
				case INSTRUCTION_FORMAT21c_TYPE:
				case INSTRUCTION_FORMAT21ih:
				case INSTRUCTION_FORMAT21lh:
				case INSTRUCTION_FORMAT21s:
				case INSTRUCTION_FORMAT21t:
				case INSTRUCTION_FORMAT22b:
				case INSTRUCTION_FORMAT22c_FIELD:
				case INSTRUCTION_FORMAT22c_FIELD_ODEX:
				case INSTRUCTION_FORMAT22c_STRING:
				case INSTRUCTION_FORMAT22c_TYPE:
				case INSTRUCTION_FORMAT22cs_FIELD:
				case INSTRUCTION_FORMAT22s:
				case INSTRUCTION_FORMAT22s_OR_ID:
				case INSTRUCTION_FORMAT22t:
				case INSTRUCTION_FORMAT22x:
				case INSTRUCTION_FORMAT23x:
				case INSTRUCTION_FORMAT25x:
				case INSTRUCTION_FORMAT30t:
				case INSTRUCTION_FORMAT31c:
				case INSTRUCTION_FORMAT31i:
				case INSTRUCTION_FORMAT31i_OR_ID:
				case INSTRUCTION_FORMAT31t:
				case INSTRUCTION_FORMAT32x:
				case INSTRUCTION_FORMAT35c_METHOD:
				case INSTRUCTION_FORMAT35c_METHOD_ODEX:
				case INSTRUCTION_FORMAT35c_TYPE:
				case INSTRUCTION_FORMAT35mi_METHOD:
				case INSTRUCTION_FORMAT35ms_METHOD:
				case INSTRUCTION_FORMAT3rc_METHOD:
				case INSTRUCTION_FORMAT3rc_METHOD_ODEX:
				case INSTRUCTION_FORMAT3rc_TYPE:
				case INSTRUCTION_FORMAT3rmi_METHOD:
				case INSTRUCTION_FORMAT3rms_METHOD:
				case INSTRUCTION_FORMAT51l:
				case LINE_DIRECTIVE:
				case LOCAL_DIRECTIVE:
				case PACKED_SWITCH_DIRECTIVE:
				case PROLOGUE_DIRECTIVE:
				case RESTART_LOCAL_DIRECTIVE:
				case SOURCE_DIRECTIVE:
				case SPARSE_SWITCH_DIRECTIVE:
					{
					alt6=1;
					}
					break;
				case LOCALS_DIRECTIVE:
				case REGISTERS_DIRECTIVE:
					{
					alt6=2;
					}
					break;
				case CATCH_DIRECTIVE:
					{
					alt6=3;
					}
					break;
				case CATCHALL_DIRECTIVE:
					{
					alt6=4;
					}
					break;
				case PARAMETER_DIRECTIVE:
					{
					alt6=5;
					}
					break;
				case ANNOTATION_DIRECTIVE:
					{
					alt6=6;
					}
					break;
				}
				switch (alt6) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:512:7: ordered_method_item
					{
					pushFollow(FOLLOW_ordered_method_item_in_statements_and_directives1582);
					ordered_method_item34=ordered_method_item();
					state._fsp--;

					stream_ordered_method_item.add(ordered_method_item34.getTree());
					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:513:7: registers_directive
					{
					pushFollow(FOLLOW_registers_directive_in_statements_and_directives1590);
					registers_directive35=registers_directive();
					state._fsp--;

					stream_registers_directive.add(registers_directive35.getTree());
					}
					break;
				case 3 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:514:7: catch_directive
					{
					pushFollow(FOLLOW_catch_directive_in_statements_and_directives1598);
					catch_directive36=catch_directive();
					state._fsp--;

					stream_catch_directive.add(catch_directive36.getTree());
					}
					break;
				case 4 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:515:7: catchall_directive
					{
					pushFollow(FOLLOW_catchall_directive_in_statements_and_directives1606);
					catchall_directive37=catchall_directive();
					state._fsp--;

					stream_catchall_directive.add(catchall_directive37.getTree());
					}
					break;
				case 5 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:516:7: parameter_directive
					{
					pushFollow(FOLLOW_parameter_directive_in_statements_and_directives1614);
					parameter_directive38=parameter_directive();
					state._fsp--;

					stream_parameter_directive.add(parameter_directive38.getTree());
					}
					break;
				case 6 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:517:7: annotation
					{
					pushFollow(FOLLOW_annotation_in_statements_and_directives1622);
					annotation39=annotation();
					state._fsp--;

					stream_annotation.add(annotation39.getTree());
					statements_and_directives_stack.peek().methodAnnotations.add((annotation39!=null?((CommonTree)annotation39.getTree()):null));
					}
					break;

				default :
					break loop6;
				}
			}

			// AST REWRITE
			// elements: catch_directive, ordered_method_item, parameter_directive, registers_directive, catchall_directive
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 519:5: -> ( registers_directive )? ^( I_ORDERED_METHOD_ITEMS ( ordered_method_item )* ) ^( I_CATCHES ( catch_directive )* ( catchall_directive )* ) ^( I_PARAMETERS ( parameter_directive )* )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:519:8: ( registers_directive )?
				if ( stream_registers_directive.hasNext() ) {
					adaptor.addChild(root_0, stream_registers_directive.nextTree());
				}
				stream_registers_directive.reset();

				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:520:8: ^( I_ORDERED_METHOD_ITEMS ( ordered_method_item )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ORDERED_METHOD_ITEMS, "I_ORDERED_METHOD_ITEMS"), root_1);
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:520:33: ( ordered_method_item )*
				while ( stream_ordered_method_item.hasNext() ) {
					adaptor.addChild(root_1, stream_ordered_method_item.nextTree());
				}
				stream_ordered_method_item.reset();

				adaptor.addChild(root_0, root_1);
				}

				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:521:8: ^( I_CATCHES ( catch_directive )* ( catchall_directive )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_CATCHES, "I_CATCHES"), root_1);
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:521:20: ( catch_directive )*
				while ( stream_catch_directive.hasNext() ) {
					adaptor.addChild(root_1, stream_catch_directive.nextTree());
				}
				stream_catch_directive.reset();

				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:521:37: ( catchall_directive )*
				while ( stream_catchall_directive.hasNext() ) {
					adaptor.addChild(root_1, stream_catchall_directive.nextTree());
				}
				stream_catchall_directive.reset();

				adaptor.addChild(root_0, root_1);
				}

				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:522:8: ^( I_PARAMETERS ( parameter_directive )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_PARAMETERS, "I_PARAMETERS"), root_1);
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:522:23: ( parameter_directive )*
				while ( stream_parameter_directive.hasNext() ) {
					adaptor.addChild(root_1, stream_parameter_directive.nextTree());
				}
				stream_parameter_directive.reset();

				adaptor.addChild(root_0, root_1);
				}

				adaptor.addChild(root_0, buildTree(I_ANNOTATIONS, "I_ANNOTATIONS", statements_and_directives_stack.peek().methodAnnotations));
			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			statements_and_directives_stack.pop();
		}
		return retval;
	}
	// $ANTLR end "statements_and_directives"


	public static class ordered_method_item_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "ordered_method_item"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:526:1: ordered_method_item : ( label | instruction | debug_directive );
	public final smaliParser.ordered_method_item_return ordered_method_item() throws RecognitionException {
		smaliParser.ordered_method_item_return retval = new smaliParser.ordered_method_item_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope label40 =null;
		ParserRuleReturnScope instruction41 =null;
		ParserRuleReturnScope debug_directive42 =null;


		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:527:3: ( label | instruction | debug_directive )
			int alt7=3;
			switch ( input.LA(1) ) {
			case COLON:
				{
				alt7=1;
				}
				break;
			case ARRAY_DATA_DIRECTIVE:
			case INSTRUCTION_FORMAT10t:
			case INSTRUCTION_FORMAT10x:
			case INSTRUCTION_FORMAT10x_ODEX:
			case INSTRUCTION_FORMAT11n:
			case INSTRUCTION_FORMAT11x:
			case INSTRUCTION_FORMAT12x:
			case INSTRUCTION_FORMAT12x_OR_ID:
			case INSTRUCTION_FORMAT20bc:
			case INSTRUCTION_FORMAT20t:
			case INSTRUCTION_FORMAT21c_FIELD:
			case INSTRUCTION_FORMAT21c_FIELD_ODEX:
			case INSTRUCTION_FORMAT21c_LAMBDA:
			case INSTRUCTION_FORMAT21c_METHOD:
			case INSTRUCTION_FORMAT21c_STRING:
			case INSTRUCTION_FORMAT21c_TYPE:
			case INSTRUCTION_FORMAT21ih:
			case INSTRUCTION_FORMAT21lh:
			case INSTRUCTION_FORMAT21s:
			case INSTRUCTION_FORMAT21t:
			case INSTRUCTION_FORMAT22b:
			case INSTRUCTION_FORMAT22c_FIELD:
			case INSTRUCTION_FORMAT22c_FIELD_ODEX:
			case INSTRUCTION_FORMAT22c_STRING:
			case INSTRUCTION_FORMAT22c_TYPE:
			case INSTRUCTION_FORMAT22cs_FIELD:
			case INSTRUCTION_FORMAT22s:
			case INSTRUCTION_FORMAT22s_OR_ID:
			case INSTRUCTION_FORMAT22t:
			case INSTRUCTION_FORMAT22x:
			case INSTRUCTION_FORMAT23x:
			case INSTRUCTION_FORMAT25x:
			case INSTRUCTION_FORMAT30t:
			case INSTRUCTION_FORMAT31c:
			case INSTRUCTION_FORMAT31i:
			case INSTRUCTION_FORMAT31i_OR_ID:
			case INSTRUCTION_FORMAT31t:
			case INSTRUCTION_FORMAT32x:
			case INSTRUCTION_FORMAT35c_METHOD:
			case INSTRUCTION_FORMAT35c_METHOD_ODEX:
			case INSTRUCTION_FORMAT35c_TYPE:
			case INSTRUCTION_FORMAT35mi_METHOD:
			case INSTRUCTION_FORMAT35ms_METHOD:
			case INSTRUCTION_FORMAT3rc_METHOD:
			case INSTRUCTION_FORMAT3rc_METHOD_ODEX:
			case INSTRUCTION_FORMAT3rc_TYPE:
			case INSTRUCTION_FORMAT3rmi_METHOD:
			case INSTRUCTION_FORMAT3rms_METHOD:
			case INSTRUCTION_FORMAT51l:
			case PACKED_SWITCH_DIRECTIVE:
			case SPARSE_SWITCH_DIRECTIVE:
				{
				alt7=2;
				}
				break;
			case END_LOCAL_DIRECTIVE:
			case EPILOGUE_DIRECTIVE:
			case LINE_DIRECTIVE:
			case LOCAL_DIRECTIVE:
			case PROLOGUE_DIRECTIVE:
			case RESTART_LOCAL_DIRECTIVE:
			case SOURCE_DIRECTIVE:
				{
				alt7=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}
			switch (alt7) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:527:5: label
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_label_in_ordered_method_item1707);
					label40=label();
					state._fsp--;

					adaptor.addChild(root_0, label40.getTree());

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:528:5: instruction
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_instruction_in_ordered_method_item1713);
					instruction41=instruction();
					state._fsp--;

					adaptor.addChild(root_0, instruction41.getTree());

					}
					break;
				case 3 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:529:5: debug_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_debug_directive_in_ordered_method_item1719);
					debug_directive42=debug_directive();
					state._fsp--;

					adaptor.addChild(root_0, debug_directive42.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "ordered_method_item"


	public static class registers_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "registers_directive"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:531:1: registers_directive : (directive= REGISTERS_DIRECTIVE regCount= integral_literal -> ^( I_REGISTERS[$REGISTERS_DIRECTIVE, \"I_REGISTERS\"] $regCount) |directive= LOCALS_DIRECTIVE regCount2= integral_literal -> ^( I_LOCALS[$LOCALS_DIRECTIVE, \"I_LOCALS\"] $regCount2) ) ;
	public final smaliParser.registers_directive_return registers_directive() throws RecognitionException {
		smaliParser.registers_directive_return retval = new smaliParser.registers_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token directive=null;
		ParserRuleReturnScope regCount =null;
		ParserRuleReturnScope regCount2 =null;

		CommonTree directive_tree=null;
		RewriteRuleTokenStream stream_REGISTERS_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token REGISTERS_DIRECTIVE");
		RewriteRuleTokenStream stream_LOCALS_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token LOCALS_DIRECTIVE");
		RewriteRuleSubtreeStream stream_integral_literal=new RewriteRuleSubtreeStream(adaptor,"rule integral_literal");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:532:3: ( (directive= REGISTERS_DIRECTIVE regCount= integral_literal -> ^( I_REGISTERS[$REGISTERS_DIRECTIVE, \"I_REGISTERS\"] $regCount) |directive= LOCALS_DIRECTIVE regCount2= integral_literal -> ^( I_LOCALS[$LOCALS_DIRECTIVE, \"I_LOCALS\"] $regCount2) ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:532:5: (directive= REGISTERS_DIRECTIVE regCount= integral_literal -> ^( I_REGISTERS[$REGISTERS_DIRECTIVE, \"I_REGISTERS\"] $regCount) |directive= LOCALS_DIRECTIVE regCount2= integral_literal -> ^( I_LOCALS[$LOCALS_DIRECTIVE, \"I_LOCALS\"] $regCount2) )
			{
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:532:5: (directive= REGISTERS_DIRECTIVE regCount= integral_literal -> ^( I_REGISTERS[$REGISTERS_DIRECTIVE, \"I_REGISTERS\"] $regCount) |directive= LOCALS_DIRECTIVE regCount2= integral_literal -> ^( I_LOCALS[$LOCALS_DIRECTIVE, \"I_LOCALS\"] $regCount2) )
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==REGISTERS_DIRECTIVE) ) {
				alt8=1;
			}
			else if ( (LA8_0==LOCALS_DIRECTIVE) ) {
				alt8=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}

			switch (alt8) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:533:7: directive= REGISTERS_DIRECTIVE regCount= integral_literal
					{
					directive=(Token)match(input,REGISTERS_DIRECTIVE,FOLLOW_REGISTERS_DIRECTIVE_in_registers_directive1739);  
					stream_REGISTERS_DIRECTIVE.add(directive);

					pushFollow(FOLLOW_integral_literal_in_registers_directive1743);
					regCount=integral_literal();
					state._fsp--;

					stream_integral_literal.add(regCount.getTree());
					// AST REWRITE
					// elements: regCount
					// token labels: 
					// rule labels: retval, regCount
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_regCount=new RewriteRuleSubtreeStream(adaptor,"rule regCount",regCount!=null?regCount.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 533:63: -> ^( I_REGISTERS[$REGISTERS_DIRECTIVE, \"I_REGISTERS\"] $regCount)
					{
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:533:66: ^( I_REGISTERS[$REGISTERS_DIRECTIVE, \"I_REGISTERS\"] $regCount)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_REGISTERS, directive, "I_REGISTERS"), root_1);
						adaptor.addChild(root_1, stream_regCount.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:534:7: directive= LOCALS_DIRECTIVE regCount2= integral_literal
					{
					directive=(Token)match(input,LOCALS_DIRECTIVE,FOLLOW_LOCALS_DIRECTIVE_in_registers_directive1763);  
					stream_LOCALS_DIRECTIVE.add(directive);

					pushFollow(FOLLOW_integral_literal_in_registers_directive1767);
					regCount2=integral_literal();
					state._fsp--;

					stream_integral_literal.add(regCount2.getTree());
					// AST REWRITE
					// elements: regCount2
					// token labels: 
					// rule labels: retval, regCount2
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
					RewriteRuleSubtreeStream stream_regCount2=new RewriteRuleSubtreeStream(adaptor,"rule regCount2",regCount2!=null?regCount2.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 534:61: -> ^( I_LOCALS[$LOCALS_DIRECTIVE, \"I_LOCALS\"] $regCount2)
					{
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:534:64: ^( I_LOCALS[$LOCALS_DIRECTIVE, \"I_LOCALS\"] $regCount2)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_LOCALS, directive, "I_LOCALS"), root_1);
						adaptor.addChild(root_1, stream_regCount2.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}


			      if (statements_and_directives_stack.peek().hasRegistersDirective) {
			        throw new SemanticException(input, directive, "There can only be a single .registers or .locals directive in a method");
			      }
			      statements_and_directives_stack.peek().hasRegistersDirective =true;
			    
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "registers_directive"


	public static class param_list_or_id_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "param_list_or_id"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:543:1: param_list_or_id : PARAM_LIST_OR_ID_START ( PRIMITIVE_TYPE )+ PARAM_LIST_OR_ID_END ;
	public final smaliParser.param_list_or_id_return param_list_or_id() throws RecognitionException {
		smaliParser.param_list_or_id_return retval = new smaliParser.param_list_or_id_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token PARAM_LIST_OR_ID_START43=null;
		Token PRIMITIVE_TYPE44=null;
		Token PARAM_LIST_OR_ID_END45=null;

		CommonTree PARAM_LIST_OR_ID_START43_tree=null;
		CommonTree PRIMITIVE_TYPE44_tree=null;
		CommonTree PARAM_LIST_OR_ID_END45_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:544:3: ( PARAM_LIST_OR_ID_START ( PRIMITIVE_TYPE )+ PARAM_LIST_OR_ID_END )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:544:5: PARAM_LIST_OR_ID_START ( PRIMITIVE_TYPE )+ PARAM_LIST_OR_ID_END
			{
			root_0 = (CommonTree)adaptor.nil();


			PARAM_LIST_OR_ID_START43=(Token)match(input,PARAM_LIST_OR_ID_START,FOLLOW_PARAM_LIST_OR_ID_START_in_param_list_or_id1799); 
			PARAM_LIST_OR_ID_START43_tree = (CommonTree)adaptor.create(PARAM_LIST_OR_ID_START43);
			adaptor.addChild(root_0, PARAM_LIST_OR_ID_START43_tree);

			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:544:28: ( PRIMITIVE_TYPE )+
			int cnt9=0;
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0==PRIMITIVE_TYPE) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:544:28: PRIMITIVE_TYPE
					{
					PRIMITIVE_TYPE44=(Token)match(input,PRIMITIVE_TYPE,FOLLOW_PRIMITIVE_TYPE_in_param_list_or_id1801); 
					PRIMITIVE_TYPE44_tree = (CommonTree)adaptor.create(PRIMITIVE_TYPE44);
					adaptor.addChild(root_0, PRIMITIVE_TYPE44_tree);

					}
					break;

				default :
					if ( cnt9 >= 1 ) break loop9;
					EarlyExitException eee = new EarlyExitException(9, input);
					throw eee;
				}
				cnt9++;
			}

			PARAM_LIST_OR_ID_END45=(Token)match(input,PARAM_LIST_OR_ID_END,FOLLOW_PARAM_LIST_OR_ID_END_in_param_list_or_id1804); 
			PARAM_LIST_OR_ID_END45_tree = (CommonTree)adaptor.create(PARAM_LIST_OR_ID_END45);
			adaptor.addChild(root_0, PARAM_LIST_OR_ID_END45_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "param_list_or_id"


	public static class simple_name_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "simple_name"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:548:1: simple_name : ( SIMPLE_NAME | ACCESS_SPEC -> SIMPLE_NAME[$ACCESS_SPEC] | VERIFICATION_ERROR_TYPE -> SIMPLE_NAME[$VERIFICATION_ERROR_TYPE] | POSITIVE_INTEGER_LITERAL -> SIMPLE_NAME[$POSITIVE_INTEGER_LITERAL] | NEGATIVE_INTEGER_LITERAL -> SIMPLE_NAME[$NEGATIVE_INTEGER_LITERAL] | FLOAT_LITERAL_OR_ID -> SIMPLE_NAME[$FLOAT_LITERAL_OR_ID] | DOUBLE_LITERAL_OR_ID -> SIMPLE_NAME[$DOUBLE_LITERAL_OR_ID] | BOOL_LITERAL -> SIMPLE_NAME[$BOOL_LITERAL] | NULL_LITERAL -> SIMPLE_NAME[$NULL_LITERAL] | REGISTER -> SIMPLE_NAME[$REGISTER] | param_list_or_id ->| PRIMITIVE_TYPE -> SIMPLE_NAME[$PRIMITIVE_TYPE] | VOID_TYPE -> SIMPLE_NAME[$VOID_TYPE] | ANNOTATION_VISIBILITY -> SIMPLE_NAME[$ANNOTATION_VISIBILITY] | INSTRUCTION_FORMAT10t -> SIMPLE_NAME[$INSTRUCTION_FORMAT10t] | INSTRUCTION_FORMAT10x -> SIMPLE_NAME[$INSTRUCTION_FORMAT10x] | INSTRUCTION_FORMAT10x_ODEX -> SIMPLE_NAME[$INSTRUCTION_FORMAT10x_ODEX] | INSTRUCTION_FORMAT11x -> SIMPLE_NAME[$INSTRUCTION_FORMAT11x] | INSTRUCTION_FORMAT12x_OR_ID -> SIMPLE_NAME[$INSTRUCTION_FORMAT12x_OR_ID] | INSTRUCTION_FORMAT21c_FIELD -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_FIELD] | INSTRUCTION_FORMAT21c_FIELD_ODEX -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_FIELD_ODEX] | INSTRUCTION_FORMAT21c_STRING -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_STRING] | INSTRUCTION_FORMAT21c_TYPE -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_TYPE] | INSTRUCTION_FORMAT21c_LAMBDA -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_LAMBDA] | INSTRUCTION_FORMAT21c_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_METHOD] | INSTRUCTION_FORMAT21t -> SIMPLE_NAME[$INSTRUCTION_FORMAT21t] | INSTRUCTION_FORMAT22c_FIELD -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_FIELD] | INSTRUCTION_FORMAT22c_FIELD_ODEX -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_FIELD_ODEX] | INSTRUCTION_FORMAT22c_TYPE -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_TYPE] | INSTRUCTION_FORMAT22c_STRING -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_STRING] | INSTRUCTION_FORMAT22cs_FIELD -> SIMPLE_NAME[$INSTRUCTION_FORMAT22cs_FIELD] | INSTRUCTION_FORMAT22s_OR_ID -> SIMPLE_NAME[$INSTRUCTION_FORMAT22s_OR_ID] | INSTRUCTION_FORMAT22t -> SIMPLE_NAME[$INSTRUCTION_FORMAT22t] | INSTRUCTION_FORMAT23x -> SIMPLE_NAME[$INSTRUCTION_FORMAT23x] | INSTRUCTION_FORMAT25x -> SIMPLE_NAME[$INSTRUCTION_FORMAT25x] | INSTRUCTION_FORMAT31i_OR_ID -> SIMPLE_NAME[$INSTRUCTION_FORMAT31i_OR_ID] | INSTRUCTION_FORMAT31t -> SIMPLE_NAME[$INSTRUCTION_FORMAT31t] | INSTRUCTION_FORMAT35c_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_METHOD] | INSTRUCTION_FORMAT35c_METHOD_ODEX -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_METHOD_ODEX] | INSTRUCTION_FORMAT35c_TYPE -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_TYPE] | INSTRUCTION_FORMAT35mi_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT35mi_METHOD] | INSTRUCTION_FORMAT35ms_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT35ms_METHOD] | INSTRUCTION_FORMAT51l -> SIMPLE_NAME[$INSTRUCTION_FORMAT51l] );
	public final smaliParser.simple_name_return simple_name() throws RecognitionException {
		smaliParser.simple_name_return retval = new smaliParser.simple_name_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SIMPLE_NAME46=null;
		Token ACCESS_SPEC47=null;
		Token VERIFICATION_ERROR_TYPE48=null;
		Token POSITIVE_INTEGER_LITERAL49=null;
		Token NEGATIVE_INTEGER_LITERAL50=null;
		Token FLOAT_LITERAL_OR_ID51=null;
		Token DOUBLE_LITERAL_OR_ID52=null;
		Token BOOL_LITERAL53=null;
		Token NULL_LITERAL54=null;
		Token REGISTER55=null;
		Token PRIMITIVE_TYPE57=null;
		Token VOID_TYPE58=null;
		Token ANNOTATION_VISIBILITY59=null;
		Token INSTRUCTION_FORMAT10t60=null;
		Token INSTRUCTION_FORMAT10x61=null;
		Token INSTRUCTION_FORMAT10x_ODEX62=null;
		Token INSTRUCTION_FORMAT11x63=null;
		Token INSTRUCTION_FORMAT12x_OR_ID64=null;
		Token INSTRUCTION_FORMAT21c_FIELD65=null;
		Token INSTRUCTION_FORMAT21c_FIELD_ODEX66=null;
		Token INSTRUCTION_FORMAT21c_STRING67=null;
		Token INSTRUCTION_FORMAT21c_TYPE68=null;
		Token INSTRUCTION_FORMAT21c_LAMBDA69=null;
		Token INSTRUCTION_FORMAT21c_METHOD70=null;
		Token INSTRUCTION_FORMAT21t71=null;
		Token INSTRUCTION_FORMAT22c_FIELD72=null;
		Token INSTRUCTION_FORMAT22c_FIELD_ODEX73=null;
		Token INSTRUCTION_FORMAT22c_TYPE74=null;
		Token INSTRUCTION_FORMAT22c_STRING75=null;
		Token INSTRUCTION_FORMAT22cs_FIELD76=null;
		Token INSTRUCTION_FORMAT22s_OR_ID77=null;
		Token INSTRUCTION_FORMAT22t78=null;
		Token INSTRUCTION_FORMAT23x79=null;
		Token INSTRUCTION_FORMAT25x80=null;
		Token INSTRUCTION_FORMAT31i_OR_ID81=null;
		Token INSTRUCTION_FORMAT31t82=null;
		Token INSTRUCTION_FORMAT35c_METHOD83=null;
		Token INSTRUCTION_FORMAT35c_METHOD_ODEX84=null;
		Token INSTRUCTION_FORMAT35c_TYPE85=null;
		Token INSTRUCTION_FORMAT35mi_METHOD86=null;
		Token INSTRUCTION_FORMAT35ms_METHOD87=null;
		Token INSTRUCTION_FORMAT51l88=null;
		ParserRuleReturnScope param_list_or_id56 =null;

		CommonTree SIMPLE_NAME46_tree=null;
		CommonTree ACCESS_SPEC47_tree=null;
		CommonTree VERIFICATION_ERROR_TYPE48_tree=null;
		CommonTree POSITIVE_INTEGER_LITERAL49_tree=null;
		CommonTree NEGATIVE_INTEGER_LITERAL50_tree=null;
		CommonTree FLOAT_LITERAL_OR_ID51_tree=null;
		CommonTree DOUBLE_LITERAL_OR_ID52_tree=null;
		CommonTree BOOL_LITERAL53_tree=null;
		CommonTree NULL_LITERAL54_tree=null;
		CommonTree REGISTER55_tree=null;
		CommonTree PRIMITIVE_TYPE57_tree=null;
		CommonTree VOID_TYPE58_tree=null;
		CommonTree ANNOTATION_VISIBILITY59_tree=null;
		CommonTree INSTRUCTION_FORMAT10t60_tree=null;
		CommonTree INSTRUCTION_FORMAT10x61_tree=null;
		CommonTree INSTRUCTION_FORMAT10x_ODEX62_tree=null;
		CommonTree INSTRUCTION_FORMAT11x63_tree=null;
		CommonTree INSTRUCTION_FORMAT12x_OR_ID64_tree=null;
		CommonTree INSTRUCTION_FORMAT21c_FIELD65_tree=null;
		CommonTree INSTRUCTION_FORMAT21c_FIELD_ODEX66_tree=null;
		CommonTree INSTRUCTION_FORMAT21c_STRING67_tree=null;
		CommonTree INSTRUCTION_FORMAT21c_TYPE68_tree=null;
		CommonTree INSTRUCTION_FORMAT21c_LAMBDA69_tree=null;
		CommonTree INSTRUCTION_FORMAT21c_METHOD70_tree=null;
		CommonTree INSTRUCTION_FORMAT21t71_tree=null;
		CommonTree INSTRUCTION_FORMAT22c_FIELD72_tree=null;
		CommonTree INSTRUCTION_FORMAT22c_FIELD_ODEX73_tree=null;
		CommonTree INSTRUCTION_FORMAT22c_TYPE74_tree=null;
		CommonTree INSTRUCTION_FORMAT22c_STRING75_tree=null;
		CommonTree INSTRUCTION_FORMAT22cs_FIELD76_tree=null;
		CommonTree INSTRUCTION_FORMAT22s_OR_ID77_tree=null;
		CommonTree INSTRUCTION_FORMAT22t78_tree=null;
		CommonTree INSTRUCTION_FORMAT23x79_tree=null;
		CommonTree INSTRUCTION_FORMAT25x80_tree=null;
		CommonTree INSTRUCTION_FORMAT31i_OR_ID81_tree=null;
		CommonTree INSTRUCTION_FORMAT31t82_tree=null;
		CommonTree INSTRUCTION_FORMAT35c_METHOD83_tree=null;
		CommonTree INSTRUCTION_FORMAT35c_METHOD_ODEX84_tree=null;
		CommonTree INSTRUCTION_FORMAT35c_TYPE85_tree=null;
		CommonTree INSTRUCTION_FORMAT35mi_METHOD86_tree=null;
		CommonTree INSTRUCTION_FORMAT35ms_METHOD87_tree=null;
		CommonTree INSTRUCTION_FORMAT51l88_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT25x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT25x");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22c_TYPE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_METHOD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35c_METHOD");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT11x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT11x");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21t");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35c_TYPE");
		RewriteRuleTokenStream stream_ANNOTATION_VISIBILITY=new RewriteRuleTokenStream(adaptor,"token ANNOTATION_VISIBILITY");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT31i_OR_ID=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT31i_OR_ID");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_METHOD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_METHOD");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22s_OR_ID=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22s_OR_ID");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT51l=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT51l");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT23x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT23x");
		RewriteRuleTokenStream stream_NULL_LITERAL=new RewriteRuleTokenStream(adaptor,"token NULL_LITERAL");
		RewriteRuleTokenStream stream_BOOL_LITERAL=new RewriteRuleTokenStream(adaptor,"token BOOL_LITERAL");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_LAMBDA=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_LAMBDA");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_FIELD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_FIELD");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_METHOD_ODEX=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35c_METHOD_ODEX");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_FIELD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22c_FIELD");
		RewriteRuleTokenStream stream_ACCESS_SPEC=new RewriteRuleTokenStream(adaptor,"token ACCESS_SPEC");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_STRING=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_STRING");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT12x_OR_ID=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT12x_OR_ID");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35ms_METHOD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35ms_METHOD");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35mi_METHOD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35mi_METHOD");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22cs_FIELD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22cs_FIELD");
		RewriteRuleTokenStream stream_VOID_TYPE=new RewriteRuleTokenStream(adaptor,"token VOID_TYPE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT10x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT10x");
		RewriteRuleTokenStream stream_FLOAT_LITERAL_OR_ID=new RewriteRuleTokenStream(adaptor,"token FLOAT_LITERAL_OR_ID");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22t");
		RewriteRuleTokenStream stream_PRIMITIVE_TYPE=new RewriteRuleTokenStream(adaptor,"token PRIMITIVE_TYPE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT10x_ODEX=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT10x_ODEX");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT31t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT31t");
		RewriteRuleTokenStream stream_DOUBLE_LITERAL_OR_ID=new RewriteRuleTokenStream(adaptor,"token DOUBLE_LITERAL_OR_ID");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_FIELD_ODEX=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22c_FIELD_ODEX");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT10t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT10t");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_STRING=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22c_STRING");
		RewriteRuleTokenStream stream_NEGATIVE_INTEGER_LITERAL=new RewriteRuleTokenStream(adaptor,"token NEGATIVE_INTEGER_LITERAL");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_VERIFICATION_ERROR_TYPE=new RewriteRuleTokenStream(adaptor,"token VERIFICATION_ERROR_TYPE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_TYPE");
		RewriteRuleTokenStream stream_POSITIVE_INTEGER_LITERAL=new RewriteRuleTokenStream(adaptor,"token POSITIVE_INTEGER_LITERAL");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_FIELD_ODEX=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_FIELD_ODEX");
		RewriteRuleSubtreeStream stream_param_list_or_id=new RewriteRuleSubtreeStream(adaptor,"rule param_list_or_id");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:549:3: ( SIMPLE_NAME | ACCESS_SPEC -> SIMPLE_NAME[$ACCESS_SPEC] | VERIFICATION_ERROR_TYPE -> SIMPLE_NAME[$VERIFICATION_ERROR_TYPE] | POSITIVE_INTEGER_LITERAL -> SIMPLE_NAME[$POSITIVE_INTEGER_LITERAL] | NEGATIVE_INTEGER_LITERAL -> SIMPLE_NAME[$NEGATIVE_INTEGER_LITERAL] | FLOAT_LITERAL_OR_ID -> SIMPLE_NAME[$FLOAT_LITERAL_OR_ID] | DOUBLE_LITERAL_OR_ID -> SIMPLE_NAME[$DOUBLE_LITERAL_OR_ID] | BOOL_LITERAL -> SIMPLE_NAME[$BOOL_LITERAL] | NULL_LITERAL -> SIMPLE_NAME[$NULL_LITERAL] | REGISTER -> SIMPLE_NAME[$REGISTER] | param_list_or_id ->| PRIMITIVE_TYPE -> SIMPLE_NAME[$PRIMITIVE_TYPE] | VOID_TYPE -> SIMPLE_NAME[$VOID_TYPE] | ANNOTATION_VISIBILITY -> SIMPLE_NAME[$ANNOTATION_VISIBILITY] | INSTRUCTION_FORMAT10t -> SIMPLE_NAME[$INSTRUCTION_FORMAT10t] | INSTRUCTION_FORMAT10x -> SIMPLE_NAME[$INSTRUCTION_FORMAT10x] | INSTRUCTION_FORMAT10x_ODEX -> SIMPLE_NAME[$INSTRUCTION_FORMAT10x_ODEX] | INSTRUCTION_FORMAT11x -> SIMPLE_NAME[$INSTRUCTION_FORMAT11x] | INSTRUCTION_FORMAT12x_OR_ID -> SIMPLE_NAME[$INSTRUCTION_FORMAT12x_OR_ID] | INSTRUCTION_FORMAT21c_FIELD -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_FIELD] | INSTRUCTION_FORMAT21c_FIELD_ODEX -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_FIELD_ODEX] | INSTRUCTION_FORMAT21c_STRING -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_STRING] | INSTRUCTION_FORMAT21c_TYPE -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_TYPE] | INSTRUCTION_FORMAT21c_LAMBDA -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_LAMBDA] | INSTRUCTION_FORMAT21c_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_METHOD] | INSTRUCTION_FORMAT21t -> SIMPLE_NAME[$INSTRUCTION_FORMAT21t] | INSTRUCTION_FORMAT22c_FIELD -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_FIELD] | INSTRUCTION_FORMAT22c_FIELD_ODEX -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_FIELD_ODEX] | INSTRUCTION_FORMAT22c_TYPE -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_TYPE] | INSTRUCTION_FORMAT22c_STRING -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_STRING] | INSTRUCTION_FORMAT22cs_FIELD -> SIMPLE_NAME[$INSTRUCTION_FORMAT22cs_FIELD] | INSTRUCTION_FORMAT22s_OR_ID -> SIMPLE_NAME[$INSTRUCTION_FORMAT22s_OR_ID] | INSTRUCTION_FORMAT22t -> SIMPLE_NAME[$INSTRUCTION_FORMAT22t] | INSTRUCTION_FORMAT23x -> SIMPLE_NAME[$INSTRUCTION_FORMAT23x] | INSTRUCTION_FORMAT25x -> SIMPLE_NAME[$INSTRUCTION_FORMAT25x] | INSTRUCTION_FORMAT31i_OR_ID -> SIMPLE_NAME[$INSTRUCTION_FORMAT31i_OR_ID] | INSTRUCTION_FORMAT31t -> SIMPLE_NAME[$INSTRUCTION_FORMAT31t] | INSTRUCTION_FORMAT35c_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_METHOD] | INSTRUCTION_FORMAT35c_METHOD_ODEX -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_METHOD_ODEX] | INSTRUCTION_FORMAT35c_TYPE -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_TYPE] | INSTRUCTION_FORMAT35mi_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT35mi_METHOD] | INSTRUCTION_FORMAT35ms_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT35ms_METHOD] | INSTRUCTION_FORMAT51l -> SIMPLE_NAME[$INSTRUCTION_FORMAT51l] )
			int alt10=43;
			switch ( input.LA(1) ) {
			case SIMPLE_NAME:
				{
				alt10=1;
				}
				break;
			case ACCESS_SPEC:
				{
				alt10=2;
				}
				break;
			case VERIFICATION_ERROR_TYPE:
				{
				alt10=3;
				}
				break;
			case POSITIVE_INTEGER_LITERAL:
				{
				alt10=4;
				}
				break;
			case NEGATIVE_INTEGER_LITERAL:
				{
				alt10=5;
				}
				break;
			case FLOAT_LITERAL_OR_ID:
				{
				alt10=6;
				}
				break;
			case DOUBLE_LITERAL_OR_ID:
				{
				alt10=7;
				}
				break;
			case BOOL_LITERAL:
				{
				alt10=8;
				}
				break;
			case NULL_LITERAL:
				{
				alt10=9;
				}
				break;
			case REGISTER:
				{
				alt10=10;
				}
				break;
			case PARAM_LIST_OR_ID_START:
				{
				alt10=11;
				}
				break;
			case PRIMITIVE_TYPE:
				{
				alt10=12;
				}
				break;
			case VOID_TYPE:
				{
				alt10=13;
				}
				break;
			case ANNOTATION_VISIBILITY:
				{
				alt10=14;
				}
				break;
			case INSTRUCTION_FORMAT10t:
				{
				alt10=15;
				}
				break;
			case INSTRUCTION_FORMAT10x:
				{
				alt10=16;
				}
				break;
			case INSTRUCTION_FORMAT10x_ODEX:
				{
				alt10=17;
				}
				break;
			case INSTRUCTION_FORMAT11x:
				{
				alt10=18;
				}
				break;
			case INSTRUCTION_FORMAT12x_OR_ID:
				{
				alt10=19;
				}
				break;
			case INSTRUCTION_FORMAT21c_FIELD:
				{
				alt10=20;
				}
				break;
			case INSTRUCTION_FORMAT21c_FIELD_ODEX:
				{
				alt10=21;
				}
				break;
			case INSTRUCTION_FORMAT21c_STRING:
				{
				alt10=22;
				}
				break;
			case INSTRUCTION_FORMAT21c_TYPE:
				{
				alt10=23;
				}
				break;
			case INSTRUCTION_FORMAT21c_LAMBDA:
				{
				alt10=24;
				}
				break;
			case INSTRUCTION_FORMAT21c_METHOD:
				{
				alt10=25;
				}
				break;
			case INSTRUCTION_FORMAT21t:
				{
				alt10=26;
				}
				break;
			case INSTRUCTION_FORMAT22c_FIELD:
				{
				alt10=27;
				}
				break;
			case INSTRUCTION_FORMAT22c_FIELD_ODEX:
				{
				alt10=28;
				}
				break;
			case INSTRUCTION_FORMAT22c_TYPE:
				{
				alt10=29;
				}
				break;
			case INSTRUCTION_FORMAT22c_STRING:
				{
				alt10=30;
				}
				break;
			case INSTRUCTION_FORMAT22cs_FIELD:
				{
				alt10=31;
				}
				break;
			case INSTRUCTION_FORMAT22s_OR_ID:
				{
				alt10=32;
				}
				break;
			case INSTRUCTION_FORMAT22t:
				{
				alt10=33;
				}
				break;
			case INSTRUCTION_FORMAT23x:
				{
				alt10=34;
				}
				break;
			case INSTRUCTION_FORMAT25x:
				{
				alt10=35;
				}
				break;
			case INSTRUCTION_FORMAT31i_OR_ID:
				{
				alt10=36;
				}
				break;
			case INSTRUCTION_FORMAT31t:
				{
				alt10=37;
				}
				break;
			case INSTRUCTION_FORMAT35c_METHOD:
				{
				alt10=38;
				}
				break;
			case INSTRUCTION_FORMAT35c_METHOD_ODEX:
				{
				alt10=39;
				}
				break;
			case INSTRUCTION_FORMAT35c_TYPE:
				{
				alt10=40;
				}
				break;
			case INSTRUCTION_FORMAT35mi_METHOD:
				{
				alt10=41;
				}
				break;
			case INSTRUCTION_FORMAT35ms_METHOD:
				{
				alt10=42;
				}
				break;
			case INSTRUCTION_FORMAT51l:
				{
				alt10=43;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}
			switch (alt10) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:549:5: SIMPLE_NAME
					{
					root_0 = (CommonTree)adaptor.nil();


					SIMPLE_NAME46=(Token)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_simple_name1816); 
					SIMPLE_NAME46_tree = (CommonTree)adaptor.create(SIMPLE_NAME46);
					adaptor.addChild(root_0, SIMPLE_NAME46_tree);

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:550:5: ACCESS_SPEC
					{
					ACCESS_SPEC47=(Token)match(input,ACCESS_SPEC,FOLLOW_ACCESS_SPEC_in_simple_name1822);  
					stream_ACCESS_SPEC.add(ACCESS_SPEC47);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 550:17: -> SIMPLE_NAME[$ACCESS_SPEC]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, ACCESS_SPEC47));
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:551:5: VERIFICATION_ERROR_TYPE
					{
					VERIFICATION_ERROR_TYPE48=(Token)match(input,VERIFICATION_ERROR_TYPE,FOLLOW_VERIFICATION_ERROR_TYPE_in_simple_name1833);  
					stream_VERIFICATION_ERROR_TYPE.add(VERIFICATION_ERROR_TYPE48);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 551:29: -> SIMPLE_NAME[$VERIFICATION_ERROR_TYPE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, VERIFICATION_ERROR_TYPE48));
					}


					retval.tree = root_0;

					}
					break;
				case 4 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:552:5: POSITIVE_INTEGER_LITERAL
					{
					POSITIVE_INTEGER_LITERAL49=(Token)match(input,POSITIVE_INTEGER_LITERAL,FOLLOW_POSITIVE_INTEGER_LITERAL_in_simple_name1844);  
					stream_POSITIVE_INTEGER_LITERAL.add(POSITIVE_INTEGER_LITERAL49);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 552:30: -> SIMPLE_NAME[$POSITIVE_INTEGER_LITERAL]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, POSITIVE_INTEGER_LITERAL49));
					}


					retval.tree = root_0;

					}
					break;
				case 5 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:553:5: NEGATIVE_INTEGER_LITERAL
					{
					NEGATIVE_INTEGER_LITERAL50=(Token)match(input,NEGATIVE_INTEGER_LITERAL,FOLLOW_NEGATIVE_INTEGER_LITERAL_in_simple_name1855);  
					stream_NEGATIVE_INTEGER_LITERAL.add(NEGATIVE_INTEGER_LITERAL50);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 553:30: -> SIMPLE_NAME[$NEGATIVE_INTEGER_LITERAL]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, NEGATIVE_INTEGER_LITERAL50));
					}


					retval.tree = root_0;

					}
					break;
				case 6 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:554:5: FLOAT_LITERAL_OR_ID
					{
					FLOAT_LITERAL_OR_ID51=(Token)match(input,FLOAT_LITERAL_OR_ID,FOLLOW_FLOAT_LITERAL_OR_ID_in_simple_name1866);  
					stream_FLOAT_LITERAL_OR_ID.add(FLOAT_LITERAL_OR_ID51);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 554:25: -> SIMPLE_NAME[$FLOAT_LITERAL_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, FLOAT_LITERAL_OR_ID51));
					}


					retval.tree = root_0;

					}
					break;
				case 7 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:555:5: DOUBLE_LITERAL_OR_ID
					{
					DOUBLE_LITERAL_OR_ID52=(Token)match(input,DOUBLE_LITERAL_OR_ID,FOLLOW_DOUBLE_LITERAL_OR_ID_in_simple_name1877);  
					stream_DOUBLE_LITERAL_OR_ID.add(DOUBLE_LITERAL_OR_ID52);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 555:26: -> SIMPLE_NAME[$DOUBLE_LITERAL_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, DOUBLE_LITERAL_OR_ID52));
					}


					retval.tree = root_0;

					}
					break;
				case 8 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:556:5: BOOL_LITERAL
					{
					BOOL_LITERAL53=(Token)match(input,BOOL_LITERAL,FOLLOW_BOOL_LITERAL_in_simple_name1888);  
					stream_BOOL_LITERAL.add(BOOL_LITERAL53);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 556:18: -> SIMPLE_NAME[$BOOL_LITERAL]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, BOOL_LITERAL53));
					}


					retval.tree = root_0;

					}
					break;
				case 9 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:557:5: NULL_LITERAL
					{
					NULL_LITERAL54=(Token)match(input,NULL_LITERAL,FOLLOW_NULL_LITERAL_in_simple_name1899);  
					stream_NULL_LITERAL.add(NULL_LITERAL54);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 557:18: -> SIMPLE_NAME[$NULL_LITERAL]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, NULL_LITERAL54));
					}


					retval.tree = root_0;

					}
					break;
				case 10 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:558:5: REGISTER
					{
					REGISTER55=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_simple_name1910);  
					stream_REGISTER.add(REGISTER55);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 558:14: -> SIMPLE_NAME[$REGISTER]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, REGISTER55));
					}


					retval.tree = root_0;

					}
					break;
				case 11 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:559:5: param_list_or_id
					{
					pushFollow(FOLLOW_param_list_or_id_in_simple_name1921);
					param_list_or_id56=param_list_or_id();
					state._fsp--;

					stream_param_list_or_id.add(param_list_or_id56.getTree());
					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 559:22: ->
					{
						adaptor.addChild(root_0,  adaptor.create(SIMPLE_NAME, (param_list_or_id56!=null?input.toString(param_list_or_id56.start,param_list_or_id56.stop):null)) );
					}


					retval.tree = root_0;

					}
					break;
				case 12 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:560:5: PRIMITIVE_TYPE
					{
					PRIMITIVE_TYPE57=(Token)match(input,PRIMITIVE_TYPE,FOLLOW_PRIMITIVE_TYPE_in_simple_name1931);  
					stream_PRIMITIVE_TYPE.add(PRIMITIVE_TYPE57);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 560:20: -> SIMPLE_NAME[$PRIMITIVE_TYPE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, PRIMITIVE_TYPE57));
					}


					retval.tree = root_0;

					}
					break;
				case 13 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:561:5: VOID_TYPE
					{
					VOID_TYPE58=(Token)match(input,VOID_TYPE,FOLLOW_VOID_TYPE_in_simple_name1942);  
					stream_VOID_TYPE.add(VOID_TYPE58);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 561:15: -> SIMPLE_NAME[$VOID_TYPE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, VOID_TYPE58));
					}


					retval.tree = root_0;

					}
					break;
				case 14 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:562:5: ANNOTATION_VISIBILITY
					{
					ANNOTATION_VISIBILITY59=(Token)match(input,ANNOTATION_VISIBILITY,FOLLOW_ANNOTATION_VISIBILITY_in_simple_name1953);  
					stream_ANNOTATION_VISIBILITY.add(ANNOTATION_VISIBILITY59);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 562:27: -> SIMPLE_NAME[$ANNOTATION_VISIBILITY]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, ANNOTATION_VISIBILITY59));
					}


					retval.tree = root_0;

					}
					break;
				case 15 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:563:5: INSTRUCTION_FORMAT10t
					{
					INSTRUCTION_FORMAT10t60=(Token)match(input,INSTRUCTION_FORMAT10t,FOLLOW_INSTRUCTION_FORMAT10t_in_simple_name1964);  
					stream_INSTRUCTION_FORMAT10t.add(INSTRUCTION_FORMAT10t60);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 563:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT10t]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT10t60));
					}


					retval.tree = root_0;

					}
					break;
				case 16 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:564:5: INSTRUCTION_FORMAT10x
					{
					INSTRUCTION_FORMAT10x61=(Token)match(input,INSTRUCTION_FORMAT10x,FOLLOW_INSTRUCTION_FORMAT10x_in_simple_name1975);  
					stream_INSTRUCTION_FORMAT10x.add(INSTRUCTION_FORMAT10x61);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 564:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT10x]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT10x61));
					}


					retval.tree = root_0;

					}
					break;
				case 17 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:565:5: INSTRUCTION_FORMAT10x_ODEX
					{
					INSTRUCTION_FORMAT10x_ODEX62=(Token)match(input,INSTRUCTION_FORMAT10x_ODEX,FOLLOW_INSTRUCTION_FORMAT10x_ODEX_in_simple_name1986);  
					stream_INSTRUCTION_FORMAT10x_ODEX.add(INSTRUCTION_FORMAT10x_ODEX62);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 565:32: -> SIMPLE_NAME[$INSTRUCTION_FORMAT10x_ODEX]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT10x_ODEX62));
					}


					retval.tree = root_0;

					}
					break;
				case 18 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:566:5: INSTRUCTION_FORMAT11x
					{
					INSTRUCTION_FORMAT11x63=(Token)match(input,INSTRUCTION_FORMAT11x,FOLLOW_INSTRUCTION_FORMAT11x_in_simple_name1997);  
					stream_INSTRUCTION_FORMAT11x.add(INSTRUCTION_FORMAT11x63);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 566:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT11x]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT11x63));
					}


					retval.tree = root_0;

					}
					break;
				case 19 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:567:5: INSTRUCTION_FORMAT12x_OR_ID
					{
					INSTRUCTION_FORMAT12x_OR_ID64=(Token)match(input,INSTRUCTION_FORMAT12x_OR_ID,FOLLOW_INSTRUCTION_FORMAT12x_OR_ID_in_simple_name2008);  
					stream_INSTRUCTION_FORMAT12x_OR_ID.add(INSTRUCTION_FORMAT12x_OR_ID64);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 567:33: -> SIMPLE_NAME[$INSTRUCTION_FORMAT12x_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT12x_OR_ID64));
					}


					retval.tree = root_0;

					}
					break;
				case 20 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:568:5: INSTRUCTION_FORMAT21c_FIELD
					{
					INSTRUCTION_FORMAT21c_FIELD65=(Token)match(input,INSTRUCTION_FORMAT21c_FIELD,FOLLOW_INSTRUCTION_FORMAT21c_FIELD_in_simple_name2019);  
					stream_INSTRUCTION_FORMAT21c_FIELD.add(INSTRUCTION_FORMAT21c_FIELD65);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 568:33: -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_FIELD]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT21c_FIELD65));
					}


					retval.tree = root_0;

					}
					break;
				case 21 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:569:5: INSTRUCTION_FORMAT21c_FIELD_ODEX
					{
					INSTRUCTION_FORMAT21c_FIELD_ODEX66=(Token)match(input,INSTRUCTION_FORMAT21c_FIELD_ODEX,FOLLOW_INSTRUCTION_FORMAT21c_FIELD_ODEX_in_simple_name2030);  
					stream_INSTRUCTION_FORMAT21c_FIELD_ODEX.add(INSTRUCTION_FORMAT21c_FIELD_ODEX66);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 569:38: -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_FIELD_ODEX]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT21c_FIELD_ODEX66));
					}


					retval.tree = root_0;

					}
					break;
				case 22 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:570:5: INSTRUCTION_FORMAT21c_STRING
					{
					INSTRUCTION_FORMAT21c_STRING67=(Token)match(input,INSTRUCTION_FORMAT21c_STRING,FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_simple_name2041);  
					stream_INSTRUCTION_FORMAT21c_STRING.add(INSTRUCTION_FORMAT21c_STRING67);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 570:34: -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_STRING]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT21c_STRING67));
					}


					retval.tree = root_0;

					}
					break;
				case 23 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:571:5: INSTRUCTION_FORMAT21c_TYPE
					{
					INSTRUCTION_FORMAT21c_TYPE68=(Token)match(input,INSTRUCTION_FORMAT21c_TYPE,FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_simple_name2052);  
					stream_INSTRUCTION_FORMAT21c_TYPE.add(INSTRUCTION_FORMAT21c_TYPE68);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 571:32: -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_TYPE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT21c_TYPE68));
					}


					retval.tree = root_0;

					}
					break;
				case 24 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:572:5: INSTRUCTION_FORMAT21c_LAMBDA
					{
					INSTRUCTION_FORMAT21c_LAMBDA69=(Token)match(input,INSTRUCTION_FORMAT21c_LAMBDA,FOLLOW_INSTRUCTION_FORMAT21c_LAMBDA_in_simple_name2063);  
					stream_INSTRUCTION_FORMAT21c_LAMBDA.add(INSTRUCTION_FORMAT21c_LAMBDA69);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 572:34: -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_LAMBDA]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT21c_LAMBDA69));
					}


					retval.tree = root_0;

					}
					break;
				case 25 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:573:5: INSTRUCTION_FORMAT21c_METHOD
					{
					INSTRUCTION_FORMAT21c_METHOD70=(Token)match(input,INSTRUCTION_FORMAT21c_METHOD,FOLLOW_INSTRUCTION_FORMAT21c_METHOD_in_simple_name2074);  
					stream_INSTRUCTION_FORMAT21c_METHOD.add(INSTRUCTION_FORMAT21c_METHOD70);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 573:34: -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_METHOD]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT21c_METHOD70));
					}


					retval.tree = root_0;

					}
					break;
				case 26 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:574:5: INSTRUCTION_FORMAT21t
					{
					INSTRUCTION_FORMAT21t71=(Token)match(input,INSTRUCTION_FORMAT21t,FOLLOW_INSTRUCTION_FORMAT21t_in_simple_name2085);  
					stream_INSTRUCTION_FORMAT21t.add(INSTRUCTION_FORMAT21t71);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 574:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT21t]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT21t71));
					}


					retval.tree = root_0;

					}
					break;
				case 27 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:575:5: INSTRUCTION_FORMAT22c_FIELD
					{
					INSTRUCTION_FORMAT22c_FIELD72=(Token)match(input,INSTRUCTION_FORMAT22c_FIELD,FOLLOW_INSTRUCTION_FORMAT22c_FIELD_in_simple_name2096);  
					stream_INSTRUCTION_FORMAT22c_FIELD.add(INSTRUCTION_FORMAT22c_FIELD72);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 575:33: -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_FIELD]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT22c_FIELD72));
					}


					retval.tree = root_0;

					}
					break;
				case 28 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:576:5: INSTRUCTION_FORMAT22c_FIELD_ODEX
					{
					INSTRUCTION_FORMAT22c_FIELD_ODEX73=(Token)match(input,INSTRUCTION_FORMAT22c_FIELD_ODEX,FOLLOW_INSTRUCTION_FORMAT22c_FIELD_ODEX_in_simple_name2107);  
					stream_INSTRUCTION_FORMAT22c_FIELD_ODEX.add(INSTRUCTION_FORMAT22c_FIELD_ODEX73);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 576:38: -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_FIELD_ODEX]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT22c_FIELD_ODEX73));
					}


					retval.tree = root_0;

					}
					break;
				case 29 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:577:5: INSTRUCTION_FORMAT22c_TYPE
					{
					INSTRUCTION_FORMAT22c_TYPE74=(Token)match(input,INSTRUCTION_FORMAT22c_TYPE,FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_simple_name2118);  
					stream_INSTRUCTION_FORMAT22c_TYPE.add(INSTRUCTION_FORMAT22c_TYPE74);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 577:32: -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_TYPE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT22c_TYPE74));
					}


					retval.tree = root_0;

					}
					break;
				case 30 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:578:5: INSTRUCTION_FORMAT22c_STRING
					{
					INSTRUCTION_FORMAT22c_STRING75=(Token)match(input,INSTRUCTION_FORMAT22c_STRING,FOLLOW_INSTRUCTION_FORMAT22c_STRING_in_simple_name2129);  
					stream_INSTRUCTION_FORMAT22c_STRING.add(INSTRUCTION_FORMAT22c_STRING75);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 578:34: -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_STRING]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT22c_STRING75));
					}


					retval.tree = root_0;

					}
					break;
				case 31 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:579:5: INSTRUCTION_FORMAT22cs_FIELD
					{
					INSTRUCTION_FORMAT22cs_FIELD76=(Token)match(input,INSTRUCTION_FORMAT22cs_FIELD,FOLLOW_INSTRUCTION_FORMAT22cs_FIELD_in_simple_name2140);  
					stream_INSTRUCTION_FORMAT22cs_FIELD.add(INSTRUCTION_FORMAT22cs_FIELD76);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 579:34: -> SIMPLE_NAME[$INSTRUCTION_FORMAT22cs_FIELD]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT22cs_FIELD76));
					}


					retval.tree = root_0;

					}
					break;
				case 32 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:580:5: INSTRUCTION_FORMAT22s_OR_ID
					{
					INSTRUCTION_FORMAT22s_OR_ID77=(Token)match(input,INSTRUCTION_FORMAT22s_OR_ID,FOLLOW_INSTRUCTION_FORMAT22s_OR_ID_in_simple_name2151);  
					stream_INSTRUCTION_FORMAT22s_OR_ID.add(INSTRUCTION_FORMAT22s_OR_ID77);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 580:33: -> SIMPLE_NAME[$INSTRUCTION_FORMAT22s_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT22s_OR_ID77));
					}


					retval.tree = root_0;

					}
					break;
				case 33 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:581:5: INSTRUCTION_FORMAT22t
					{
					INSTRUCTION_FORMAT22t78=(Token)match(input,INSTRUCTION_FORMAT22t,FOLLOW_INSTRUCTION_FORMAT22t_in_simple_name2162);  
					stream_INSTRUCTION_FORMAT22t.add(INSTRUCTION_FORMAT22t78);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 581:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT22t]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT22t78));
					}


					retval.tree = root_0;

					}
					break;
				case 34 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:582:5: INSTRUCTION_FORMAT23x
					{
					INSTRUCTION_FORMAT23x79=(Token)match(input,INSTRUCTION_FORMAT23x,FOLLOW_INSTRUCTION_FORMAT23x_in_simple_name2173);  
					stream_INSTRUCTION_FORMAT23x.add(INSTRUCTION_FORMAT23x79);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 582:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT23x]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT23x79));
					}


					retval.tree = root_0;

					}
					break;
				case 35 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:583:5: INSTRUCTION_FORMAT25x
					{
					INSTRUCTION_FORMAT25x80=(Token)match(input,INSTRUCTION_FORMAT25x,FOLLOW_INSTRUCTION_FORMAT25x_in_simple_name2184);  
					stream_INSTRUCTION_FORMAT25x.add(INSTRUCTION_FORMAT25x80);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 583:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT25x]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT25x80));
					}


					retval.tree = root_0;

					}
					break;
				case 36 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:584:5: INSTRUCTION_FORMAT31i_OR_ID
					{
					INSTRUCTION_FORMAT31i_OR_ID81=(Token)match(input,INSTRUCTION_FORMAT31i_OR_ID,FOLLOW_INSTRUCTION_FORMAT31i_OR_ID_in_simple_name2195);  
					stream_INSTRUCTION_FORMAT31i_OR_ID.add(INSTRUCTION_FORMAT31i_OR_ID81);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 584:33: -> SIMPLE_NAME[$INSTRUCTION_FORMAT31i_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT31i_OR_ID81));
					}


					retval.tree = root_0;

					}
					break;
				case 37 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:585:5: INSTRUCTION_FORMAT31t
					{
					INSTRUCTION_FORMAT31t82=(Token)match(input,INSTRUCTION_FORMAT31t,FOLLOW_INSTRUCTION_FORMAT31t_in_simple_name2206);  
					stream_INSTRUCTION_FORMAT31t.add(INSTRUCTION_FORMAT31t82);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 585:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT31t]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT31t82));
					}


					retval.tree = root_0;

					}
					break;
				case 38 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:586:5: INSTRUCTION_FORMAT35c_METHOD
					{
					INSTRUCTION_FORMAT35c_METHOD83=(Token)match(input,INSTRUCTION_FORMAT35c_METHOD,FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_simple_name2217);  
					stream_INSTRUCTION_FORMAT35c_METHOD.add(INSTRUCTION_FORMAT35c_METHOD83);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 586:34: -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_METHOD]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT35c_METHOD83));
					}


					retval.tree = root_0;

					}
					break;
				case 39 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:587:5: INSTRUCTION_FORMAT35c_METHOD_ODEX
					{
					INSTRUCTION_FORMAT35c_METHOD_ODEX84=(Token)match(input,INSTRUCTION_FORMAT35c_METHOD_ODEX,FOLLOW_INSTRUCTION_FORMAT35c_METHOD_ODEX_in_simple_name2228);  
					stream_INSTRUCTION_FORMAT35c_METHOD_ODEX.add(INSTRUCTION_FORMAT35c_METHOD_ODEX84);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 587:39: -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_METHOD_ODEX]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT35c_METHOD_ODEX84));
					}


					retval.tree = root_0;

					}
					break;
				case 40 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:588:5: INSTRUCTION_FORMAT35c_TYPE
					{
					INSTRUCTION_FORMAT35c_TYPE85=(Token)match(input,INSTRUCTION_FORMAT35c_TYPE,FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_simple_name2239);  
					stream_INSTRUCTION_FORMAT35c_TYPE.add(INSTRUCTION_FORMAT35c_TYPE85);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 588:32: -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_TYPE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT35c_TYPE85));
					}


					retval.tree = root_0;

					}
					break;
				case 41 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:589:5: INSTRUCTION_FORMAT35mi_METHOD
					{
					INSTRUCTION_FORMAT35mi_METHOD86=(Token)match(input,INSTRUCTION_FORMAT35mi_METHOD,FOLLOW_INSTRUCTION_FORMAT35mi_METHOD_in_simple_name2250);  
					stream_INSTRUCTION_FORMAT35mi_METHOD.add(INSTRUCTION_FORMAT35mi_METHOD86);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 589:35: -> SIMPLE_NAME[$INSTRUCTION_FORMAT35mi_METHOD]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT35mi_METHOD86));
					}


					retval.tree = root_0;

					}
					break;
				case 42 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:590:5: INSTRUCTION_FORMAT35ms_METHOD
					{
					INSTRUCTION_FORMAT35ms_METHOD87=(Token)match(input,INSTRUCTION_FORMAT35ms_METHOD,FOLLOW_INSTRUCTION_FORMAT35ms_METHOD_in_simple_name2261);  
					stream_INSTRUCTION_FORMAT35ms_METHOD.add(INSTRUCTION_FORMAT35ms_METHOD87);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 590:35: -> SIMPLE_NAME[$INSTRUCTION_FORMAT35ms_METHOD]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT35ms_METHOD87));
					}


					retval.tree = root_0;

					}
					break;
				case 43 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:591:5: INSTRUCTION_FORMAT51l
					{
					INSTRUCTION_FORMAT51l88=(Token)match(input,INSTRUCTION_FORMAT51l,FOLLOW_INSTRUCTION_FORMAT51l_in_simple_name2272);  
					stream_INSTRUCTION_FORMAT51l.add(INSTRUCTION_FORMAT51l88);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 591:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT51l]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT51l88));
					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "simple_name"


	public static class member_name_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "member_name"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:593:1: member_name : ( simple_name | MEMBER_NAME -> SIMPLE_NAME[$MEMBER_NAME] );
	public final smaliParser.member_name_return member_name() throws RecognitionException {
		smaliParser.member_name_return retval = new smaliParser.member_name_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token MEMBER_NAME90=null;
		ParserRuleReturnScope simple_name89 =null;

		CommonTree MEMBER_NAME90_tree=null;
		RewriteRuleTokenStream stream_MEMBER_NAME=new RewriteRuleTokenStream(adaptor,"token MEMBER_NAME");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:594:3: ( simple_name | MEMBER_NAME -> SIMPLE_NAME[$MEMBER_NAME] )
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==ACCESS_SPEC||LA11_0==ANNOTATION_VISIBILITY||LA11_0==BOOL_LITERAL||LA11_0==DOUBLE_LITERAL_OR_ID||LA11_0==FLOAT_LITERAL_OR_ID||(LA11_0 >= INSTRUCTION_FORMAT10t && LA11_0 <= INSTRUCTION_FORMAT10x_ODEX)||LA11_0==INSTRUCTION_FORMAT11x||LA11_0==INSTRUCTION_FORMAT12x_OR_ID||(LA11_0 >= INSTRUCTION_FORMAT21c_FIELD && LA11_0 <= INSTRUCTION_FORMAT21c_TYPE)||LA11_0==INSTRUCTION_FORMAT21t||(LA11_0 >= INSTRUCTION_FORMAT22c_FIELD && LA11_0 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA11_0 >= INSTRUCTION_FORMAT22s_OR_ID && LA11_0 <= INSTRUCTION_FORMAT22t)||(LA11_0 >= INSTRUCTION_FORMAT23x && LA11_0 <= INSTRUCTION_FORMAT25x)||(LA11_0 >= INSTRUCTION_FORMAT31i_OR_ID && LA11_0 <= INSTRUCTION_FORMAT31t)||(LA11_0 >= INSTRUCTION_FORMAT35c_METHOD && LA11_0 <= INSTRUCTION_FORMAT35ms_METHOD)||LA11_0==INSTRUCTION_FORMAT51l||(LA11_0 >= NEGATIVE_INTEGER_LITERAL && LA11_0 <= NULL_LITERAL)||LA11_0==PARAM_LIST_OR_ID_START||(LA11_0 >= POSITIVE_INTEGER_LITERAL && LA11_0 <= PRIMITIVE_TYPE)||LA11_0==REGISTER||LA11_0==SIMPLE_NAME||(LA11_0 >= VERIFICATION_ERROR_TYPE && LA11_0 <= VOID_TYPE)) ) {
				alt11=1;
			}
			else if ( (LA11_0==MEMBER_NAME) ) {
				alt11=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}

			switch (alt11) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:594:5: simple_name
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_simple_name_in_member_name2287);
					simple_name89=simple_name();
					state._fsp--;

					adaptor.addChild(root_0, simple_name89.getTree());

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:595:5: MEMBER_NAME
					{
					MEMBER_NAME90=(Token)match(input,MEMBER_NAME,FOLLOW_MEMBER_NAME_in_member_name2293);  
					stream_MEMBER_NAME.add(MEMBER_NAME90);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 595:17: -> SIMPLE_NAME[$MEMBER_NAME]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, MEMBER_NAME90));
					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "member_name"


	public static class method_prototype_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "method_prototype"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:597:1: method_prototype : OPEN_PAREN param_list CLOSE_PAREN type_descriptor -> ^( I_METHOD_PROTOTYPE[$start, \"I_METHOD_PROTOTYPE\"] ^( I_METHOD_RETURN_TYPE type_descriptor ) ( param_list )? ) ;
	public final smaliParser.method_prototype_return method_prototype() throws RecognitionException {
		smaliParser.method_prototype_return retval = new smaliParser.method_prototype_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token OPEN_PAREN91=null;
		Token CLOSE_PAREN93=null;
		ParserRuleReturnScope param_list92 =null;
		ParserRuleReturnScope type_descriptor94 =null;

		CommonTree OPEN_PAREN91_tree=null;
		CommonTree CLOSE_PAREN93_tree=null;
		RewriteRuleTokenStream stream_OPEN_PAREN=new RewriteRuleTokenStream(adaptor,"token OPEN_PAREN");
		RewriteRuleTokenStream stream_CLOSE_PAREN=new RewriteRuleTokenStream(adaptor,"token CLOSE_PAREN");
		RewriteRuleSubtreeStream stream_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule type_descriptor");
		RewriteRuleSubtreeStream stream_param_list=new RewriteRuleSubtreeStream(adaptor,"rule param_list");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:598:3: ( OPEN_PAREN param_list CLOSE_PAREN type_descriptor -> ^( I_METHOD_PROTOTYPE[$start, \"I_METHOD_PROTOTYPE\"] ^( I_METHOD_RETURN_TYPE type_descriptor ) ( param_list )? ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:598:5: OPEN_PAREN param_list CLOSE_PAREN type_descriptor
			{
			OPEN_PAREN91=(Token)match(input,OPEN_PAREN,FOLLOW_OPEN_PAREN_in_method_prototype2308);  
			stream_OPEN_PAREN.add(OPEN_PAREN91);

			pushFollow(FOLLOW_param_list_in_method_prototype2310);
			param_list92=param_list();
			state._fsp--;

			stream_param_list.add(param_list92.getTree());
			CLOSE_PAREN93=(Token)match(input,CLOSE_PAREN,FOLLOW_CLOSE_PAREN_in_method_prototype2312);  
			stream_CLOSE_PAREN.add(CLOSE_PAREN93);

			pushFollow(FOLLOW_type_descriptor_in_method_prototype2314);
			type_descriptor94=type_descriptor();
			state._fsp--;

			stream_type_descriptor.add(type_descriptor94.getTree());
			// AST REWRITE
			// elements: param_list, type_descriptor
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 599:5: -> ^( I_METHOD_PROTOTYPE[$start, \"I_METHOD_PROTOTYPE\"] ^( I_METHOD_RETURN_TYPE type_descriptor ) ( param_list )? )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:599:8: ^( I_METHOD_PROTOTYPE[$start, \"I_METHOD_PROTOTYPE\"] ^( I_METHOD_RETURN_TYPE type_descriptor ) ( param_list )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_METHOD_PROTOTYPE, (retval.start), "I_METHOD_PROTOTYPE"), root_1);
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:599:59: ^( I_METHOD_RETURN_TYPE type_descriptor )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_METHOD_RETURN_TYPE, "I_METHOD_RETURN_TYPE"), root_2);
				adaptor.addChild(root_2, stream_type_descriptor.nextTree());
				adaptor.addChild(root_1, root_2);
				}

				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:599:99: ( param_list )?
				if ( stream_param_list.hasNext() ) {
					adaptor.addChild(root_1, stream_param_list.nextTree());
				}
				stream_param_list.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "method_prototype"


	public static class param_list_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "param_list"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:601:1: param_list : ( PARAM_LIST_START ( nonvoid_type_descriptor )* PARAM_LIST_END -> ( nonvoid_type_descriptor )* | PARAM_LIST_OR_ID_START ( PRIMITIVE_TYPE )* PARAM_LIST_OR_ID_END -> ( PRIMITIVE_TYPE )* | ( nonvoid_type_descriptor )* );
	public final smaliParser.param_list_return param_list() throws RecognitionException {
		smaliParser.param_list_return retval = new smaliParser.param_list_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token PARAM_LIST_START95=null;
		Token PARAM_LIST_END97=null;
		Token PARAM_LIST_OR_ID_START98=null;
		Token PRIMITIVE_TYPE99=null;
		Token PARAM_LIST_OR_ID_END100=null;
		ParserRuleReturnScope nonvoid_type_descriptor96 =null;
		ParserRuleReturnScope nonvoid_type_descriptor101 =null;

		CommonTree PARAM_LIST_START95_tree=null;
		CommonTree PARAM_LIST_END97_tree=null;
		CommonTree PARAM_LIST_OR_ID_START98_tree=null;
		CommonTree PRIMITIVE_TYPE99_tree=null;
		CommonTree PARAM_LIST_OR_ID_END100_tree=null;
		RewriteRuleTokenStream stream_PARAM_LIST_OR_ID_START=new RewriteRuleTokenStream(adaptor,"token PARAM_LIST_OR_ID_START");
		RewriteRuleTokenStream stream_PARAM_LIST_OR_ID_END=new RewriteRuleTokenStream(adaptor,"token PARAM_LIST_OR_ID_END");
		RewriteRuleTokenStream stream_PARAM_LIST_START=new RewriteRuleTokenStream(adaptor,"token PARAM_LIST_START");
		RewriteRuleTokenStream stream_PARAM_LIST_END=new RewriteRuleTokenStream(adaptor,"token PARAM_LIST_END");
		RewriteRuleTokenStream stream_PRIMITIVE_TYPE=new RewriteRuleTokenStream(adaptor,"token PRIMITIVE_TYPE");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:602:3: ( PARAM_LIST_START ( nonvoid_type_descriptor )* PARAM_LIST_END -> ( nonvoid_type_descriptor )* | PARAM_LIST_OR_ID_START ( PRIMITIVE_TYPE )* PARAM_LIST_OR_ID_END -> ( PRIMITIVE_TYPE )* | ( nonvoid_type_descriptor )* )
			int alt15=3;
			switch ( input.LA(1) ) {
			case PARAM_LIST_START:
				{
				alt15=1;
				}
				break;
			case PARAM_LIST_OR_ID_START:
				{
				alt15=2;
				}
				break;
			case ARRAY_DESCRIPTOR:
			case CLASS_DESCRIPTOR:
			case CLOSE_PAREN:
			case PRIMITIVE_TYPE:
				{
				alt15=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}
			switch (alt15) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:602:5: PARAM_LIST_START ( nonvoid_type_descriptor )* PARAM_LIST_END
					{
					PARAM_LIST_START95=(Token)match(input,PARAM_LIST_START,FOLLOW_PARAM_LIST_START_in_param_list2344);  
					stream_PARAM_LIST_START.add(PARAM_LIST_START95);

					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:602:22: ( nonvoid_type_descriptor )*
					loop12:
					while (true) {
						int alt12=2;
						int LA12_0 = input.LA(1);
						if ( (LA12_0==ARRAY_DESCRIPTOR||LA12_0==CLASS_DESCRIPTOR||LA12_0==PRIMITIVE_TYPE) ) {
							alt12=1;
						}

						switch (alt12) {
						case 1 :
							// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:602:22: nonvoid_type_descriptor
							{
							pushFollow(FOLLOW_nonvoid_type_descriptor_in_param_list2346);
							nonvoid_type_descriptor96=nonvoid_type_descriptor();
							state._fsp--;

							stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor96.getTree());
							}
							break;

						default :
							break loop12;
						}
					}

					PARAM_LIST_END97=(Token)match(input,PARAM_LIST_END,FOLLOW_PARAM_LIST_END_in_param_list2349);  
					stream_PARAM_LIST_END.add(PARAM_LIST_END97);

					// AST REWRITE
					// elements: nonvoid_type_descriptor
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 602:62: -> ( nonvoid_type_descriptor )*
					{
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:602:65: ( nonvoid_type_descriptor )*
						while ( stream_nonvoid_type_descriptor.hasNext() ) {
							adaptor.addChild(root_0, stream_nonvoid_type_descriptor.nextTree());
						}
						stream_nonvoid_type_descriptor.reset();

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:603:5: PARAM_LIST_OR_ID_START ( PRIMITIVE_TYPE )* PARAM_LIST_OR_ID_END
					{
					PARAM_LIST_OR_ID_START98=(Token)match(input,PARAM_LIST_OR_ID_START,FOLLOW_PARAM_LIST_OR_ID_START_in_param_list2360);  
					stream_PARAM_LIST_OR_ID_START.add(PARAM_LIST_OR_ID_START98);

					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:603:28: ( PRIMITIVE_TYPE )*
					loop13:
					while (true) {
						int alt13=2;
						int LA13_0 = input.LA(1);
						if ( (LA13_0==PRIMITIVE_TYPE) ) {
							alt13=1;
						}

						switch (alt13) {
						case 1 :
							// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:603:28: PRIMITIVE_TYPE
							{
							PRIMITIVE_TYPE99=(Token)match(input,PRIMITIVE_TYPE,FOLLOW_PRIMITIVE_TYPE_in_param_list2362);  
							stream_PRIMITIVE_TYPE.add(PRIMITIVE_TYPE99);

							}
							break;

						default :
							break loop13;
						}
					}

					PARAM_LIST_OR_ID_END100=(Token)match(input,PARAM_LIST_OR_ID_END,FOLLOW_PARAM_LIST_OR_ID_END_in_param_list2365);  
					stream_PARAM_LIST_OR_ID_END.add(PARAM_LIST_OR_ID_END100);

					// AST REWRITE
					// elements: PRIMITIVE_TYPE
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 603:65: -> ( PRIMITIVE_TYPE )*
					{
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:603:68: ( PRIMITIVE_TYPE )*
						while ( stream_PRIMITIVE_TYPE.hasNext() ) {
							adaptor.addChild(root_0, stream_PRIMITIVE_TYPE.nextNode());
						}
						stream_PRIMITIVE_TYPE.reset();

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:604:5: ( nonvoid_type_descriptor )*
					{
					root_0 = (CommonTree)adaptor.nil();


					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:604:5: ( nonvoid_type_descriptor )*
					loop14:
					while (true) {
						int alt14=2;
						int LA14_0 = input.LA(1);
						if ( (LA14_0==ARRAY_DESCRIPTOR||LA14_0==CLASS_DESCRIPTOR||LA14_0==PRIMITIVE_TYPE) ) {
							alt14=1;
						}

						switch (alt14) {
						case 1 :
							// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:604:5: nonvoid_type_descriptor
							{
							pushFollow(FOLLOW_nonvoid_type_descriptor_in_param_list2376);
							nonvoid_type_descriptor101=nonvoid_type_descriptor();
							state._fsp--;

							adaptor.addChild(root_0, nonvoid_type_descriptor101.getTree());

							}
							break;

						default :
							break loop14;
						}
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "param_list"


	public static class type_descriptor_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "type_descriptor"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:606:1: type_descriptor : ( VOID_TYPE | PRIMITIVE_TYPE | CLASS_DESCRIPTOR | ARRAY_DESCRIPTOR );
	public final smaliParser.type_descriptor_return type_descriptor() throws RecognitionException {
		smaliParser.type_descriptor_return retval = new smaliParser.type_descriptor_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set102=null;

		CommonTree set102_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:607:3: ( VOID_TYPE | PRIMITIVE_TYPE | CLASS_DESCRIPTOR | ARRAY_DESCRIPTOR )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set102=input.LT(1);
			if ( input.LA(1)==ARRAY_DESCRIPTOR||input.LA(1)==CLASS_DESCRIPTOR||input.LA(1)==PRIMITIVE_TYPE||input.LA(1)==VOID_TYPE ) {
				input.consume();
				adaptor.addChild(root_0, (CommonTree)adaptor.create(set102));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "type_descriptor"


	public static class nonvoid_type_descriptor_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "nonvoid_type_descriptor"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:612:1: nonvoid_type_descriptor : ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR | ARRAY_DESCRIPTOR );
	public final smaliParser.nonvoid_type_descriptor_return nonvoid_type_descriptor() throws RecognitionException {
		smaliParser.nonvoid_type_descriptor_return retval = new smaliParser.nonvoid_type_descriptor_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set103=null;

		CommonTree set103_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:613:3: ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR | ARRAY_DESCRIPTOR )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set103=input.LT(1);
			if ( input.LA(1)==ARRAY_DESCRIPTOR||input.LA(1)==CLASS_DESCRIPTOR||input.LA(1)==PRIMITIVE_TYPE ) {
				input.consume();
				adaptor.addChild(root_0, (CommonTree)adaptor.create(set103));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "nonvoid_type_descriptor"


	public static class reference_type_descriptor_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "reference_type_descriptor"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:617:1: reference_type_descriptor : ( CLASS_DESCRIPTOR | ARRAY_DESCRIPTOR );
	public final smaliParser.reference_type_descriptor_return reference_type_descriptor() throws RecognitionException {
		smaliParser.reference_type_descriptor_return retval = new smaliParser.reference_type_descriptor_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set104=null;

		CommonTree set104_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:618:3: ( CLASS_DESCRIPTOR | ARRAY_DESCRIPTOR )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set104=input.LT(1);
			if ( input.LA(1)==ARRAY_DESCRIPTOR||input.LA(1)==CLASS_DESCRIPTOR ) {
				input.consume();
				adaptor.addChild(root_0, (CommonTree)adaptor.create(set104));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "reference_type_descriptor"


	public static class integer_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "integer_literal"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:621:1: integer_literal : ( POSITIVE_INTEGER_LITERAL -> INTEGER_LITERAL[$POSITIVE_INTEGER_LITERAL] | NEGATIVE_INTEGER_LITERAL -> INTEGER_LITERAL[$NEGATIVE_INTEGER_LITERAL] );
	public final smaliParser.integer_literal_return integer_literal() throws RecognitionException {
		smaliParser.integer_literal_return retval = new smaliParser.integer_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token POSITIVE_INTEGER_LITERAL105=null;
		Token NEGATIVE_INTEGER_LITERAL106=null;

		CommonTree POSITIVE_INTEGER_LITERAL105_tree=null;
		CommonTree NEGATIVE_INTEGER_LITERAL106_tree=null;
		RewriteRuleTokenStream stream_NEGATIVE_INTEGER_LITERAL=new RewriteRuleTokenStream(adaptor,"token NEGATIVE_INTEGER_LITERAL");
		RewriteRuleTokenStream stream_POSITIVE_INTEGER_LITERAL=new RewriteRuleTokenStream(adaptor,"token POSITIVE_INTEGER_LITERAL");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:622:3: ( POSITIVE_INTEGER_LITERAL -> INTEGER_LITERAL[$POSITIVE_INTEGER_LITERAL] | NEGATIVE_INTEGER_LITERAL -> INTEGER_LITERAL[$NEGATIVE_INTEGER_LITERAL] )
			int alt16=2;
			int LA16_0 = input.LA(1);
			if ( (LA16_0==POSITIVE_INTEGER_LITERAL) ) {
				alt16=1;
			}
			else if ( (LA16_0==NEGATIVE_INTEGER_LITERAL) ) {
				alt16=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 16, 0, input);
				throw nvae;
			}

			switch (alt16) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:622:5: POSITIVE_INTEGER_LITERAL
					{
					POSITIVE_INTEGER_LITERAL105=(Token)match(input,POSITIVE_INTEGER_LITERAL,FOLLOW_POSITIVE_INTEGER_LITERAL_in_integer_literal2453);  
					stream_POSITIVE_INTEGER_LITERAL.add(POSITIVE_INTEGER_LITERAL105);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 622:30: -> INTEGER_LITERAL[$POSITIVE_INTEGER_LITERAL]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(INTEGER_LITERAL, POSITIVE_INTEGER_LITERAL105));
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:623:5: NEGATIVE_INTEGER_LITERAL
					{
					NEGATIVE_INTEGER_LITERAL106=(Token)match(input,NEGATIVE_INTEGER_LITERAL,FOLLOW_NEGATIVE_INTEGER_LITERAL_in_integer_literal2464);  
					stream_NEGATIVE_INTEGER_LITERAL.add(NEGATIVE_INTEGER_LITERAL106);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 623:30: -> INTEGER_LITERAL[$NEGATIVE_INTEGER_LITERAL]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(INTEGER_LITERAL, NEGATIVE_INTEGER_LITERAL106));
					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "integer_literal"


	public static class float_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "float_literal"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:625:1: float_literal : ( FLOAT_LITERAL_OR_ID -> FLOAT_LITERAL[$FLOAT_LITERAL_OR_ID] | FLOAT_LITERAL );
	public final smaliParser.float_literal_return float_literal() throws RecognitionException {
		smaliParser.float_literal_return retval = new smaliParser.float_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token FLOAT_LITERAL_OR_ID107=null;
		Token FLOAT_LITERAL108=null;

		CommonTree FLOAT_LITERAL_OR_ID107_tree=null;
		CommonTree FLOAT_LITERAL108_tree=null;
		RewriteRuleTokenStream stream_FLOAT_LITERAL_OR_ID=new RewriteRuleTokenStream(adaptor,"token FLOAT_LITERAL_OR_ID");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:626:3: ( FLOAT_LITERAL_OR_ID -> FLOAT_LITERAL[$FLOAT_LITERAL_OR_ID] | FLOAT_LITERAL )
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==FLOAT_LITERAL_OR_ID) ) {
				alt17=1;
			}
			else if ( (LA17_0==FLOAT_LITERAL) ) {
				alt17=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}

			switch (alt17) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:626:5: FLOAT_LITERAL_OR_ID
					{
					FLOAT_LITERAL_OR_ID107=(Token)match(input,FLOAT_LITERAL_OR_ID,FOLLOW_FLOAT_LITERAL_OR_ID_in_float_literal2479);  
					stream_FLOAT_LITERAL_OR_ID.add(FLOAT_LITERAL_OR_ID107);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 626:25: -> FLOAT_LITERAL[$FLOAT_LITERAL_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(FLOAT_LITERAL, FLOAT_LITERAL_OR_ID107));
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:627:5: FLOAT_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					FLOAT_LITERAL108=(Token)match(input,FLOAT_LITERAL,FOLLOW_FLOAT_LITERAL_in_float_literal2490); 
					FLOAT_LITERAL108_tree = (CommonTree)adaptor.create(FLOAT_LITERAL108);
					adaptor.addChild(root_0, FLOAT_LITERAL108_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "float_literal"


	public static class double_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "double_literal"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:629:1: double_literal : ( DOUBLE_LITERAL_OR_ID -> DOUBLE_LITERAL[$DOUBLE_LITERAL_OR_ID] | DOUBLE_LITERAL );
	public final smaliParser.double_literal_return double_literal() throws RecognitionException {
		smaliParser.double_literal_return retval = new smaliParser.double_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token DOUBLE_LITERAL_OR_ID109=null;
		Token DOUBLE_LITERAL110=null;

		CommonTree DOUBLE_LITERAL_OR_ID109_tree=null;
		CommonTree DOUBLE_LITERAL110_tree=null;
		RewriteRuleTokenStream stream_DOUBLE_LITERAL_OR_ID=new RewriteRuleTokenStream(adaptor,"token DOUBLE_LITERAL_OR_ID");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:630:3: ( DOUBLE_LITERAL_OR_ID -> DOUBLE_LITERAL[$DOUBLE_LITERAL_OR_ID] | DOUBLE_LITERAL )
			int alt18=2;
			int LA18_0 = input.LA(1);
			if ( (LA18_0==DOUBLE_LITERAL_OR_ID) ) {
				alt18=1;
			}
			else if ( (LA18_0==DOUBLE_LITERAL) ) {
				alt18=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 18, 0, input);
				throw nvae;
			}

			switch (alt18) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:630:5: DOUBLE_LITERAL_OR_ID
					{
					DOUBLE_LITERAL_OR_ID109=(Token)match(input,DOUBLE_LITERAL_OR_ID,FOLLOW_DOUBLE_LITERAL_OR_ID_in_double_literal2500);  
					stream_DOUBLE_LITERAL_OR_ID.add(DOUBLE_LITERAL_OR_ID109);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 630:26: -> DOUBLE_LITERAL[$DOUBLE_LITERAL_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(DOUBLE_LITERAL, DOUBLE_LITERAL_OR_ID109));
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:631:5: DOUBLE_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					DOUBLE_LITERAL110=(Token)match(input,DOUBLE_LITERAL,FOLLOW_DOUBLE_LITERAL_in_double_literal2511); 
					DOUBLE_LITERAL110_tree = (CommonTree)adaptor.create(DOUBLE_LITERAL110);
					adaptor.addChild(root_0, DOUBLE_LITERAL110_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "double_literal"


	public static class literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "literal"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:633:1: literal : ( LONG_LITERAL | integer_literal | SHORT_LITERAL | BYTE_LITERAL | float_literal | double_literal | CHAR_LITERAL | STRING_LITERAL | BOOL_LITERAL | NULL_LITERAL | array_literal | subannotation | type_field_method_literal | enum_literal );
	public final smaliParser.literal_return literal() throws RecognitionException {
		smaliParser.literal_return retval = new smaliParser.literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LONG_LITERAL111=null;
		Token SHORT_LITERAL113=null;
		Token BYTE_LITERAL114=null;
		Token CHAR_LITERAL117=null;
		Token STRING_LITERAL118=null;
		Token BOOL_LITERAL119=null;
		Token NULL_LITERAL120=null;
		ParserRuleReturnScope integer_literal112 =null;
		ParserRuleReturnScope float_literal115 =null;
		ParserRuleReturnScope double_literal116 =null;
		ParserRuleReturnScope array_literal121 =null;
		ParserRuleReturnScope subannotation122 =null;
		ParserRuleReturnScope type_field_method_literal123 =null;
		ParserRuleReturnScope enum_literal124 =null;

		CommonTree LONG_LITERAL111_tree=null;
		CommonTree SHORT_LITERAL113_tree=null;
		CommonTree BYTE_LITERAL114_tree=null;
		CommonTree CHAR_LITERAL117_tree=null;
		CommonTree STRING_LITERAL118_tree=null;
		CommonTree BOOL_LITERAL119_tree=null;
		CommonTree NULL_LITERAL120_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:634:3: ( LONG_LITERAL | integer_literal | SHORT_LITERAL | BYTE_LITERAL | float_literal | double_literal | CHAR_LITERAL | STRING_LITERAL | BOOL_LITERAL | NULL_LITERAL | array_literal | subannotation | type_field_method_literal | enum_literal )
			int alt19=14;
			switch ( input.LA(1) ) {
			case LONG_LITERAL:
				{
				alt19=1;
				}
				break;
			case POSITIVE_INTEGER_LITERAL:
				{
				int LA19_2 = input.LA(2);
				if ( (LA19_2==EOF||(LA19_2 >= ACCESS_SPEC && LA19_2 <= ANNOTATION_VISIBILITY)||LA19_2==BOOL_LITERAL||(LA19_2 >= CLASS_DIRECTIVE && LA19_2 <= CLOSE_BRACE)||LA19_2==COMMA||(LA19_2 >= DOUBLE_LITERAL_OR_ID && LA19_2 <= END_ANNOTATION_DIRECTIVE)||LA19_2==END_FIELD_DIRECTIVE||LA19_2==END_SUBANNOTATION_DIRECTIVE||LA19_2==FIELD_DIRECTIVE||(LA19_2 >= FLOAT_LITERAL_OR_ID && LA19_2 <= IMPLEMENTS_DIRECTIVE)||(LA19_2 >= INSTRUCTION_FORMAT10t && LA19_2 <= INSTRUCTION_FORMAT10x_ODEX)||LA19_2==INSTRUCTION_FORMAT11x||LA19_2==INSTRUCTION_FORMAT12x_OR_ID||(LA19_2 >= INSTRUCTION_FORMAT21c_FIELD && LA19_2 <= INSTRUCTION_FORMAT21c_TYPE)||LA19_2==INSTRUCTION_FORMAT21t||(LA19_2 >= INSTRUCTION_FORMAT22c_FIELD && LA19_2 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA19_2 >= INSTRUCTION_FORMAT22s_OR_ID && LA19_2 <= INSTRUCTION_FORMAT22t)||(LA19_2 >= INSTRUCTION_FORMAT23x && LA19_2 <= INSTRUCTION_FORMAT25x)||(LA19_2 >= INSTRUCTION_FORMAT31i_OR_ID && LA19_2 <= INSTRUCTION_FORMAT31t)||(LA19_2 >= INSTRUCTION_FORMAT35c_METHOD && LA19_2 <= INSTRUCTION_FORMAT35ms_METHOD)||LA19_2==INSTRUCTION_FORMAT51l||(LA19_2 >= METHOD_DIRECTIVE && LA19_2 <= NULL_LITERAL)||LA19_2==PARAM_LIST_OR_ID_START||(LA19_2 >= POSITIVE_INTEGER_LITERAL && LA19_2 <= PRIMITIVE_TYPE)||LA19_2==REGISTER||(LA19_2 >= SIMPLE_NAME && LA19_2 <= SOURCE_DIRECTIVE)||(LA19_2 >= SUPER_DIRECTIVE && LA19_2 <= VOID_TYPE)) ) {
					alt19=2;
				}
				else if ( (LA19_2==COLON||LA19_2==OPEN_PAREN) ) {
					alt19=13;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 19, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case NEGATIVE_INTEGER_LITERAL:
				{
				int LA19_3 = input.LA(2);
				if ( (LA19_3==EOF||(LA19_3 >= ACCESS_SPEC && LA19_3 <= ANNOTATION_VISIBILITY)||LA19_3==BOOL_LITERAL||(LA19_3 >= CLASS_DIRECTIVE && LA19_3 <= CLOSE_BRACE)||LA19_3==COMMA||(LA19_3 >= DOUBLE_LITERAL_OR_ID && LA19_3 <= END_ANNOTATION_DIRECTIVE)||LA19_3==END_FIELD_DIRECTIVE||LA19_3==END_SUBANNOTATION_DIRECTIVE||LA19_3==FIELD_DIRECTIVE||(LA19_3 >= FLOAT_LITERAL_OR_ID && LA19_3 <= IMPLEMENTS_DIRECTIVE)||(LA19_3 >= INSTRUCTION_FORMAT10t && LA19_3 <= INSTRUCTION_FORMAT10x_ODEX)||LA19_3==INSTRUCTION_FORMAT11x||LA19_3==INSTRUCTION_FORMAT12x_OR_ID||(LA19_3 >= INSTRUCTION_FORMAT21c_FIELD && LA19_3 <= INSTRUCTION_FORMAT21c_TYPE)||LA19_3==INSTRUCTION_FORMAT21t||(LA19_3 >= INSTRUCTION_FORMAT22c_FIELD && LA19_3 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA19_3 >= INSTRUCTION_FORMAT22s_OR_ID && LA19_3 <= INSTRUCTION_FORMAT22t)||(LA19_3 >= INSTRUCTION_FORMAT23x && LA19_3 <= INSTRUCTION_FORMAT25x)||(LA19_3 >= INSTRUCTION_FORMAT31i_OR_ID && LA19_3 <= INSTRUCTION_FORMAT31t)||(LA19_3 >= INSTRUCTION_FORMAT35c_METHOD && LA19_3 <= INSTRUCTION_FORMAT35ms_METHOD)||LA19_3==INSTRUCTION_FORMAT51l||(LA19_3 >= METHOD_DIRECTIVE && LA19_3 <= NULL_LITERAL)||LA19_3==PARAM_LIST_OR_ID_START||(LA19_3 >= POSITIVE_INTEGER_LITERAL && LA19_3 <= PRIMITIVE_TYPE)||LA19_3==REGISTER||(LA19_3 >= SIMPLE_NAME && LA19_3 <= SOURCE_DIRECTIVE)||(LA19_3 >= SUPER_DIRECTIVE && LA19_3 <= VOID_TYPE)) ) {
					alt19=2;
				}
				else if ( (LA19_3==COLON||LA19_3==OPEN_PAREN) ) {
					alt19=13;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 19, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case SHORT_LITERAL:
				{
				alt19=3;
				}
				break;
			case BYTE_LITERAL:
				{
				alt19=4;
				}
				break;
			case FLOAT_LITERAL_OR_ID:
				{
				int LA19_6 = input.LA(2);
				if ( (LA19_6==EOF||(LA19_6 >= ACCESS_SPEC && LA19_6 <= ANNOTATION_VISIBILITY)||LA19_6==BOOL_LITERAL||(LA19_6 >= CLASS_DIRECTIVE && LA19_6 <= CLOSE_BRACE)||LA19_6==COMMA||(LA19_6 >= DOUBLE_LITERAL_OR_ID && LA19_6 <= END_ANNOTATION_DIRECTIVE)||LA19_6==END_FIELD_DIRECTIVE||LA19_6==END_SUBANNOTATION_DIRECTIVE||LA19_6==FIELD_DIRECTIVE||(LA19_6 >= FLOAT_LITERAL_OR_ID && LA19_6 <= IMPLEMENTS_DIRECTIVE)||(LA19_6 >= INSTRUCTION_FORMAT10t && LA19_6 <= INSTRUCTION_FORMAT10x_ODEX)||LA19_6==INSTRUCTION_FORMAT11x||LA19_6==INSTRUCTION_FORMAT12x_OR_ID||(LA19_6 >= INSTRUCTION_FORMAT21c_FIELD && LA19_6 <= INSTRUCTION_FORMAT21c_TYPE)||LA19_6==INSTRUCTION_FORMAT21t||(LA19_6 >= INSTRUCTION_FORMAT22c_FIELD && LA19_6 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA19_6 >= INSTRUCTION_FORMAT22s_OR_ID && LA19_6 <= INSTRUCTION_FORMAT22t)||(LA19_6 >= INSTRUCTION_FORMAT23x && LA19_6 <= INSTRUCTION_FORMAT25x)||(LA19_6 >= INSTRUCTION_FORMAT31i_OR_ID && LA19_6 <= INSTRUCTION_FORMAT31t)||(LA19_6 >= INSTRUCTION_FORMAT35c_METHOD && LA19_6 <= INSTRUCTION_FORMAT35ms_METHOD)||LA19_6==INSTRUCTION_FORMAT51l||(LA19_6 >= METHOD_DIRECTIVE && LA19_6 <= NULL_LITERAL)||LA19_6==PARAM_LIST_OR_ID_START||(LA19_6 >= POSITIVE_INTEGER_LITERAL && LA19_6 <= PRIMITIVE_TYPE)||LA19_6==REGISTER||(LA19_6 >= SIMPLE_NAME && LA19_6 <= SOURCE_DIRECTIVE)||(LA19_6 >= SUPER_DIRECTIVE && LA19_6 <= VOID_TYPE)) ) {
					alt19=5;
				}
				else if ( (LA19_6==COLON||LA19_6==OPEN_PAREN) ) {
					alt19=13;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 19, 6, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case FLOAT_LITERAL:
				{
				alt19=5;
				}
				break;
			case DOUBLE_LITERAL_OR_ID:
				{
				int LA19_8 = input.LA(2);
				if ( (LA19_8==EOF||(LA19_8 >= ACCESS_SPEC && LA19_8 <= ANNOTATION_VISIBILITY)||LA19_8==BOOL_LITERAL||(LA19_8 >= CLASS_DIRECTIVE && LA19_8 <= CLOSE_BRACE)||LA19_8==COMMA||(LA19_8 >= DOUBLE_LITERAL_OR_ID && LA19_8 <= END_ANNOTATION_DIRECTIVE)||LA19_8==END_FIELD_DIRECTIVE||LA19_8==END_SUBANNOTATION_DIRECTIVE||LA19_8==FIELD_DIRECTIVE||(LA19_8 >= FLOAT_LITERAL_OR_ID && LA19_8 <= IMPLEMENTS_DIRECTIVE)||(LA19_8 >= INSTRUCTION_FORMAT10t && LA19_8 <= INSTRUCTION_FORMAT10x_ODEX)||LA19_8==INSTRUCTION_FORMAT11x||LA19_8==INSTRUCTION_FORMAT12x_OR_ID||(LA19_8 >= INSTRUCTION_FORMAT21c_FIELD && LA19_8 <= INSTRUCTION_FORMAT21c_TYPE)||LA19_8==INSTRUCTION_FORMAT21t||(LA19_8 >= INSTRUCTION_FORMAT22c_FIELD && LA19_8 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA19_8 >= INSTRUCTION_FORMAT22s_OR_ID && LA19_8 <= INSTRUCTION_FORMAT22t)||(LA19_8 >= INSTRUCTION_FORMAT23x && LA19_8 <= INSTRUCTION_FORMAT25x)||(LA19_8 >= INSTRUCTION_FORMAT31i_OR_ID && LA19_8 <= INSTRUCTION_FORMAT31t)||(LA19_8 >= INSTRUCTION_FORMAT35c_METHOD && LA19_8 <= INSTRUCTION_FORMAT35ms_METHOD)||LA19_8==INSTRUCTION_FORMAT51l||(LA19_8 >= METHOD_DIRECTIVE && LA19_8 <= NULL_LITERAL)||LA19_8==PARAM_LIST_OR_ID_START||(LA19_8 >= POSITIVE_INTEGER_LITERAL && LA19_8 <= PRIMITIVE_TYPE)||LA19_8==REGISTER||(LA19_8 >= SIMPLE_NAME && LA19_8 <= SOURCE_DIRECTIVE)||(LA19_8 >= SUPER_DIRECTIVE && LA19_8 <= VOID_TYPE)) ) {
					alt19=6;
				}
				else if ( (LA19_8==COLON||LA19_8==OPEN_PAREN) ) {
					alt19=13;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 19, 8, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case DOUBLE_LITERAL:
				{
				alt19=6;
				}
				break;
			case CHAR_LITERAL:
				{
				alt19=7;
				}
				break;
			case STRING_LITERAL:
				{
				alt19=8;
				}
				break;
			case BOOL_LITERAL:
				{
				int LA19_12 = input.LA(2);
				if ( (LA19_12==EOF||(LA19_12 >= ACCESS_SPEC && LA19_12 <= ANNOTATION_VISIBILITY)||LA19_12==BOOL_LITERAL||(LA19_12 >= CLASS_DIRECTIVE && LA19_12 <= CLOSE_BRACE)||LA19_12==COMMA||(LA19_12 >= DOUBLE_LITERAL_OR_ID && LA19_12 <= END_ANNOTATION_DIRECTIVE)||LA19_12==END_FIELD_DIRECTIVE||LA19_12==END_SUBANNOTATION_DIRECTIVE||LA19_12==FIELD_DIRECTIVE||(LA19_12 >= FLOAT_LITERAL_OR_ID && LA19_12 <= IMPLEMENTS_DIRECTIVE)||(LA19_12 >= INSTRUCTION_FORMAT10t && LA19_12 <= INSTRUCTION_FORMAT10x_ODEX)||LA19_12==INSTRUCTION_FORMAT11x||LA19_12==INSTRUCTION_FORMAT12x_OR_ID||(LA19_12 >= INSTRUCTION_FORMAT21c_FIELD && LA19_12 <= INSTRUCTION_FORMAT21c_TYPE)||LA19_12==INSTRUCTION_FORMAT21t||(LA19_12 >= INSTRUCTION_FORMAT22c_FIELD && LA19_12 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA19_12 >= INSTRUCTION_FORMAT22s_OR_ID && LA19_12 <= INSTRUCTION_FORMAT22t)||(LA19_12 >= INSTRUCTION_FORMAT23x && LA19_12 <= INSTRUCTION_FORMAT25x)||(LA19_12 >= INSTRUCTION_FORMAT31i_OR_ID && LA19_12 <= INSTRUCTION_FORMAT31t)||(LA19_12 >= INSTRUCTION_FORMAT35c_METHOD && LA19_12 <= INSTRUCTION_FORMAT35ms_METHOD)||LA19_12==INSTRUCTION_FORMAT51l||(LA19_12 >= METHOD_DIRECTIVE && LA19_12 <= NULL_LITERAL)||LA19_12==PARAM_LIST_OR_ID_START||(LA19_12 >= POSITIVE_INTEGER_LITERAL && LA19_12 <= PRIMITIVE_TYPE)||LA19_12==REGISTER||(LA19_12 >= SIMPLE_NAME && LA19_12 <= SOURCE_DIRECTIVE)||(LA19_12 >= SUPER_DIRECTIVE && LA19_12 <= VOID_TYPE)) ) {
					alt19=9;
				}
				else if ( (LA19_12==COLON||LA19_12==OPEN_PAREN) ) {
					alt19=13;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 19, 12, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case NULL_LITERAL:
				{
				int LA19_13 = input.LA(2);
				if ( (LA19_13==EOF||(LA19_13 >= ACCESS_SPEC && LA19_13 <= ANNOTATION_VISIBILITY)||LA19_13==BOOL_LITERAL||(LA19_13 >= CLASS_DIRECTIVE && LA19_13 <= CLOSE_BRACE)||LA19_13==COMMA||(LA19_13 >= DOUBLE_LITERAL_OR_ID && LA19_13 <= END_ANNOTATION_DIRECTIVE)||LA19_13==END_FIELD_DIRECTIVE||LA19_13==END_SUBANNOTATION_DIRECTIVE||LA19_13==FIELD_DIRECTIVE||(LA19_13 >= FLOAT_LITERAL_OR_ID && LA19_13 <= IMPLEMENTS_DIRECTIVE)||(LA19_13 >= INSTRUCTION_FORMAT10t && LA19_13 <= INSTRUCTION_FORMAT10x_ODEX)||LA19_13==INSTRUCTION_FORMAT11x||LA19_13==INSTRUCTION_FORMAT12x_OR_ID||(LA19_13 >= INSTRUCTION_FORMAT21c_FIELD && LA19_13 <= INSTRUCTION_FORMAT21c_TYPE)||LA19_13==INSTRUCTION_FORMAT21t||(LA19_13 >= INSTRUCTION_FORMAT22c_FIELD && LA19_13 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA19_13 >= INSTRUCTION_FORMAT22s_OR_ID && LA19_13 <= INSTRUCTION_FORMAT22t)||(LA19_13 >= INSTRUCTION_FORMAT23x && LA19_13 <= INSTRUCTION_FORMAT25x)||(LA19_13 >= INSTRUCTION_FORMAT31i_OR_ID && LA19_13 <= INSTRUCTION_FORMAT31t)||(LA19_13 >= INSTRUCTION_FORMAT35c_METHOD && LA19_13 <= INSTRUCTION_FORMAT35ms_METHOD)||LA19_13==INSTRUCTION_FORMAT51l||(LA19_13 >= METHOD_DIRECTIVE && LA19_13 <= NULL_LITERAL)||LA19_13==PARAM_LIST_OR_ID_START||(LA19_13 >= POSITIVE_INTEGER_LITERAL && LA19_13 <= PRIMITIVE_TYPE)||LA19_13==REGISTER||(LA19_13 >= SIMPLE_NAME && LA19_13 <= SOURCE_DIRECTIVE)||(LA19_13 >= SUPER_DIRECTIVE && LA19_13 <= VOID_TYPE)) ) {
					alt19=10;
				}
				else if ( (LA19_13==COLON||LA19_13==OPEN_PAREN) ) {
					alt19=13;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 19, 13, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case OPEN_BRACE:
				{
				alt19=11;
				}
				break;
			case SUBANNOTATION_DIRECTIVE:
				{
				alt19=12;
				}
				break;
			case ACCESS_SPEC:
			case ANNOTATION_VISIBILITY:
			case ARRAY_DESCRIPTOR:
			case CLASS_DESCRIPTOR:
			case INSTRUCTION_FORMAT10t:
			case INSTRUCTION_FORMAT10x:
			case INSTRUCTION_FORMAT10x_ODEX:
			case INSTRUCTION_FORMAT11x:
			case INSTRUCTION_FORMAT12x_OR_ID:
			case INSTRUCTION_FORMAT21c_FIELD:
			case INSTRUCTION_FORMAT21c_FIELD_ODEX:
			case INSTRUCTION_FORMAT21c_LAMBDA:
			case INSTRUCTION_FORMAT21c_METHOD:
			case INSTRUCTION_FORMAT21c_STRING:
			case INSTRUCTION_FORMAT21c_TYPE:
			case INSTRUCTION_FORMAT21t:
			case INSTRUCTION_FORMAT22c_FIELD:
			case INSTRUCTION_FORMAT22c_FIELD_ODEX:
			case INSTRUCTION_FORMAT22c_STRING:
			case INSTRUCTION_FORMAT22c_TYPE:
			case INSTRUCTION_FORMAT22cs_FIELD:
			case INSTRUCTION_FORMAT22s_OR_ID:
			case INSTRUCTION_FORMAT22t:
			case INSTRUCTION_FORMAT23x:
			case INSTRUCTION_FORMAT25x:
			case INSTRUCTION_FORMAT31i_OR_ID:
			case INSTRUCTION_FORMAT31t:
			case INSTRUCTION_FORMAT35c_METHOD:
			case INSTRUCTION_FORMAT35c_METHOD_ODEX:
			case INSTRUCTION_FORMAT35c_TYPE:
			case INSTRUCTION_FORMAT35mi_METHOD:
			case INSTRUCTION_FORMAT35ms_METHOD:
			case INSTRUCTION_FORMAT51l:
			case MEMBER_NAME:
			case PARAM_LIST_OR_ID_START:
			case PRIMITIVE_TYPE:
			case REGISTER:
			case SIMPLE_NAME:
			case VERIFICATION_ERROR_TYPE:
			case VOID_TYPE:
				{
				alt19=13;
				}
				break;
			case ENUM_DIRECTIVE:
				{
				alt19=14;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}
			switch (alt19) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:634:5: LONG_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					LONG_LITERAL111=(Token)match(input,LONG_LITERAL,FOLLOW_LONG_LITERAL_in_literal2521); 
					LONG_LITERAL111_tree = (CommonTree)adaptor.create(LONG_LITERAL111);
					adaptor.addChild(root_0, LONG_LITERAL111_tree);

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:635:5: integer_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_integer_literal_in_literal2527);
					integer_literal112=integer_literal();
					state._fsp--;

					adaptor.addChild(root_0, integer_literal112.getTree());

					}
					break;
				case 3 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:636:5: SHORT_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					SHORT_LITERAL113=(Token)match(input,SHORT_LITERAL,FOLLOW_SHORT_LITERAL_in_literal2533); 
					SHORT_LITERAL113_tree = (CommonTree)adaptor.create(SHORT_LITERAL113);
					adaptor.addChild(root_0, SHORT_LITERAL113_tree);

					}
					break;
				case 4 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:637:5: BYTE_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					BYTE_LITERAL114=(Token)match(input,BYTE_LITERAL,FOLLOW_BYTE_LITERAL_in_literal2539); 
					BYTE_LITERAL114_tree = (CommonTree)adaptor.create(BYTE_LITERAL114);
					adaptor.addChild(root_0, BYTE_LITERAL114_tree);

					}
					break;
				case 5 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:638:5: float_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_float_literal_in_literal2545);
					float_literal115=float_literal();
					state._fsp--;

					adaptor.addChild(root_0, float_literal115.getTree());

					}
					break;
				case 6 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:639:5: double_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_double_literal_in_literal2551);
					double_literal116=double_literal();
					state._fsp--;

					adaptor.addChild(root_0, double_literal116.getTree());

					}
					break;
				case 7 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:640:5: CHAR_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					CHAR_LITERAL117=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_literal2557); 
					CHAR_LITERAL117_tree = (CommonTree)adaptor.create(CHAR_LITERAL117);
					adaptor.addChild(root_0, CHAR_LITERAL117_tree);

					}
					break;
				case 8 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:641:5: STRING_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					STRING_LITERAL118=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_literal2563); 
					STRING_LITERAL118_tree = (CommonTree)adaptor.create(STRING_LITERAL118);
					adaptor.addChild(root_0, STRING_LITERAL118_tree);

					}
					break;
				case 9 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:642:5: BOOL_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					BOOL_LITERAL119=(Token)match(input,BOOL_LITERAL,FOLLOW_BOOL_LITERAL_in_literal2569); 
					BOOL_LITERAL119_tree = (CommonTree)adaptor.create(BOOL_LITERAL119);
					adaptor.addChild(root_0, BOOL_LITERAL119_tree);

					}
					break;
				case 10 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:643:5: NULL_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					NULL_LITERAL120=(Token)match(input,NULL_LITERAL,FOLLOW_NULL_LITERAL_in_literal2575); 
					NULL_LITERAL120_tree = (CommonTree)adaptor.create(NULL_LITERAL120);
					adaptor.addChild(root_0, NULL_LITERAL120_tree);

					}
					break;
				case 11 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:644:5: array_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_array_literal_in_literal2581);
					array_literal121=array_literal();
					state._fsp--;

					adaptor.addChild(root_0, array_literal121.getTree());

					}
					break;
				case 12 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:645:5: subannotation
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_subannotation_in_literal2587);
					subannotation122=subannotation();
					state._fsp--;

					adaptor.addChild(root_0, subannotation122.getTree());

					}
					break;
				case 13 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:646:5: type_field_method_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_type_field_method_literal_in_literal2593);
					type_field_method_literal123=type_field_method_literal();
					state._fsp--;

					adaptor.addChild(root_0, type_field_method_literal123.getTree());

					}
					break;
				case 14 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:647:5: enum_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_enum_literal_in_literal2599);
					enum_literal124=enum_literal();
					state._fsp--;

					adaptor.addChild(root_0, enum_literal124.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "literal"


	public static class parsed_integer_literal_return extends ParserRuleReturnScope {
		public int value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "parsed_integer_literal"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:649:1: parsed_integer_literal returns [int value] : integer_literal ;
	public final smaliParser.parsed_integer_literal_return parsed_integer_literal() throws RecognitionException {
		smaliParser.parsed_integer_literal_return retval = new smaliParser.parsed_integer_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope integer_literal125 =null;


		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:650:3: ( integer_literal )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:650:5: integer_literal
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_integer_literal_in_parsed_integer_literal2612);
			integer_literal125=integer_literal();
			state._fsp--;

			adaptor.addChild(root_0, integer_literal125.getTree());

			 retval.value = LiteralTools.parseInt((integer_literal125!=null?input.toString(integer_literal125.start,integer_literal125.stop):null)); 
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "parsed_integer_literal"


	public static class integral_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "integral_literal"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:652:1: integral_literal : ( LONG_LITERAL | integer_literal | SHORT_LITERAL | CHAR_LITERAL | BYTE_LITERAL );
	public final smaliParser.integral_literal_return integral_literal() throws RecognitionException {
		smaliParser.integral_literal_return retval = new smaliParser.integral_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LONG_LITERAL126=null;
		Token SHORT_LITERAL128=null;
		Token CHAR_LITERAL129=null;
		Token BYTE_LITERAL130=null;
		ParserRuleReturnScope integer_literal127 =null;

		CommonTree LONG_LITERAL126_tree=null;
		CommonTree SHORT_LITERAL128_tree=null;
		CommonTree CHAR_LITERAL129_tree=null;
		CommonTree BYTE_LITERAL130_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:653:3: ( LONG_LITERAL | integer_literal | SHORT_LITERAL | CHAR_LITERAL | BYTE_LITERAL )
			int alt20=5;
			switch ( input.LA(1) ) {
			case LONG_LITERAL:
				{
				alt20=1;
				}
				break;
			case NEGATIVE_INTEGER_LITERAL:
			case POSITIVE_INTEGER_LITERAL:
				{
				alt20=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt20=3;
				}
				break;
			case CHAR_LITERAL:
				{
				alt20=4;
				}
				break;
			case BYTE_LITERAL:
				{
				alt20=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 20, 0, input);
				throw nvae;
			}
			switch (alt20) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:653:5: LONG_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					LONG_LITERAL126=(Token)match(input,LONG_LITERAL,FOLLOW_LONG_LITERAL_in_integral_literal2624); 
					LONG_LITERAL126_tree = (CommonTree)adaptor.create(LONG_LITERAL126);
					adaptor.addChild(root_0, LONG_LITERAL126_tree);

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:654:5: integer_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_integer_literal_in_integral_literal2630);
					integer_literal127=integer_literal();
					state._fsp--;

					adaptor.addChild(root_0, integer_literal127.getTree());

					}
					break;
				case 3 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:655:5: SHORT_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					SHORT_LITERAL128=(Token)match(input,SHORT_LITERAL,FOLLOW_SHORT_LITERAL_in_integral_literal2636); 
					SHORT_LITERAL128_tree = (CommonTree)adaptor.create(SHORT_LITERAL128);
					adaptor.addChild(root_0, SHORT_LITERAL128_tree);

					}
					break;
				case 4 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:656:5: CHAR_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					CHAR_LITERAL129=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_integral_literal2642); 
					CHAR_LITERAL129_tree = (CommonTree)adaptor.create(CHAR_LITERAL129);
					adaptor.addChild(root_0, CHAR_LITERAL129_tree);

					}
					break;
				case 5 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:657:5: BYTE_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					BYTE_LITERAL130=(Token)match(input,BYTE_LITERAL,FOLLOW_BYTE_LITERAL_in_integral_literal2648); 
					BYTE_LITERAL130_tree = (CommonTree)adaptor.create(BYTE_LITERAL130);
					adaptor.addChild(root_0, BYTE_LITERAL130_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "integral_literal"


	public static class fixed_32bit_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "fixed_32bit_literal"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:659:1: fixed_32bit_literal : ( LONG_LITERAL | integer_literal | SHORT_LITERAL | BYTE_LITERAL | float_literal | CHAR_LITERAL | BOOL_LITERAL );
	public final smaliParser.fixed_32bit_literal_return fixed_32bit_literal() throws RecognitionException {
		smaliParser.fixed_32bit_literal_return retval = new smaliParser.fixed_32bit_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LONG_LITERAL131=null;
		Token SHORT_LITERAL133=null;
		Token BYTE_LITERAL134=null;
		Token CHAR_LITERAL136=null;
		Token BOOL_LITERAL137=null;
		ParserRuleReturnScope integer_literal132 =null;
		ParserRuleReturnScope float_literal135 =null;

		CommonTree LONG_LITERAL131_tree=null;
		CommonTree SHORT_LITERAL133_tree=null;
		CommonTree BYTE_LITERAL134_tree=null;
		CommonTree CHAR_LITERAL136_tree=null;
		CommonTree BOOL_LITERAL137_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:660:3: ( LONG_LITERAL | integer_literal | SHORT_LITERAL | BYTE_LITERAL | float_literal | CHAR_LITERAL | BOOL_LITERAL )
			int alt21=7;
			switch ( input.LA(1) ) {
			case LONG_LITERAL:
				{
				alt21=1;
				}
				break;
			case NEGATIVE_INTEGER_LITERAL:
			case POSITIVE_INTEGER_LITERAL:
				{
				alt21=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt21=3;
				}
				break;
			case BYTE_LITERAL:
				{
				alt21=4;
				}
				break;
			case FLOAT_LITERAL:
			case FLOAT_LITERAL_OR_ID:
				{
				alt21=5;
				}
				break;
			case CHAR_LITERAL:
				{
				alt21=6;
				}
				break;
			case BOOL_LITERAL:
				{
				alt21=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 21, 0, input);
				throw nvae;
			}
			switch (alt21) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:660:5: LONG_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					LONG_LITERAL131=(Token)match(input,LONG_LITERAL,FOLLOW_LONG_LITERAL_in_fixed_32bit_literal2658); 
					LONG_LITERAL131_tree = (CommonTree)adaptor.create(LONG_LITERAL131);
					adaptor.addChild(root_0, LONG_LITERAL131_tree);

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:661:5: integer_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_integer_literal_in_fixed_32bit_literal2664);
					integer_literal132=integer_literal();
					state._fsp--;

					adaptor.addChild(root_0, integer_literal132.getTree());

					}
					break;
				case 3 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:662:5: SHORT_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					SHORT_LITERAL133=(Token)match(input,SHORT_LITERAL,FOLLOW_SHORT_LITERAL_in_fixed_32bit_literal2670); 
					SHORT_LITERAL133_tree = (CommonTree)adaptor.create(SHORT_LITERAL133);
					adaptor.addChild(root_0, SHORT_LITERAL133_tree);

					}
					break;
				case 4 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:663:5: BYTE_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					BYTE_LITERAL134=(Token)match(input,BYTE_LITERAL,FOLLOW_BYTE_LITERAL_in_fixed_32bit_literal2676); 
					BYTE_LITERAL134_tree = (CommonTree)adaptor.create(BYTE_LITERAL134);
					adaptor.addChild(root_0, BYTE_LITERAL134_tree);

					}
					break;
				case 5 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:664:5: float_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_float_literal_in_fixed_32bit_literal2682);
					float_literal135=float_literal();
					state._fsp--;

					adaptor.addChild(root_0, float_literal135.getTree());

					}
					break;
				case 6 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:665:5: CHAR_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					CHAR_LITERAL136=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_fixed_32bit_literal2688); 
					CHAR_LITERAL136_tree = (CommonTree)adaptor.create(CHAR_LITERAL136);
					adaptor.addChild(root_0, CHAR_LITERAL136_tree);

					}
					break;
				case 7 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:666:5: BOOL_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					BOOL_LITERAL137=(Token)match(input,BOOL_LITERAL,FOLLOW_BOOL_LITERAL_in_fixed_32bit_literal2694); 
					BOOL_LITERAL137_tree = (CommonTree)adaptor.create(BOOL_LITERAL137);
					adaptor.addChild(root_0, BOOL_LITERAL137_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fixed_32bit_literal"


	public static class fixed_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "fixed_literal"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:668:1: fixed_literal : ( integer_literal | LONG_LITERAL | SHORT_LITERAL | BYTE_LITERAL | float_literal | double_literal | CHAR_LITERAL | BOOL_LITERAL );
	public final smaliParser.fixed_literal_return fixed_literal() throws RecognitionException {
		smaliParser.fixed_literal_return retval = new smaliParser.fixed_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LONG_LITERAL139=null;
		Token SHORT_LITERAL140=null;
		Token BYTE_LITERAL141=null;
		Token CHAR_LITERAL144=null;
		Token BOOL_LITERAL145=null;
		ParserRuleReturnScope integer_literal138 =null;
		ParserRuleReturnScope float_literal142 =null;
		ParserRuleReturnScope double_literal143 =null;

		CommonTree LONG_LITERAL139_tree=null;
		CommonTree SHORT_LITERAL140_tree=null;
		CommonTree BYTE_LITERAL141_tree=null;
		CommonTree CHAR_LITERAL144_tree=null;
		CommonTree BOOL_LITERAL145_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:669:3: ( integer_literal | LONG_LITERAL | SHORT_LITERAL | BYTE_LITERAL | float_literal | double_literal | CHAR_LITERAL | BOOL_LITERAL )
			int alt22=8;
			switch ( input.LA(1) ) {
			case NEGATIVE_INTEGER_LITERAL:
			case POSITIVE_INTEGER_LITERAL:
				{
				alt22=1;
				}
				break;
			case LONG_LITERAL:
				{
				alt22=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt22=3;
				}
				break;
			case BYTE_LITERAL:
				{
				alt22=4;
				}
				break;
			case FLOAT_LITERAL:
			case FLOAT_LITERAL_OR_ID:
				{
				alt22=5;
				}
				break;
			case DOUBLE_LITERAL:
			case DOUBLE_LITERAL_OR_ID:
				{
				alt22=6;
				}
				break;
			case CHAR_LITERAL:
				{
				alt22=7;
				}
				break;
			case BOOL_LITERAL:
				{
				alt22=8;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}
			switch (alt22) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:669:5: integer_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_integer_literal_in_fixed_literal2704);
					integer_literal138=integer_literal();
					state._fsp--;

					adaptor.addChild(root_0, integer_literal138.getTree());

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:670:5: LONG_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					LONG_LITERAL139=(Token)match(input,LONG_LITERAL,FOLLOW_LONG_LITERAL_in_fixed_literal2710); 
					LONG_LITERAL139_tree = (CommonTree)adaptor.create(LONG_LITERAL139);
					adaptor.addChild(root_0, LONG_LITERAL139_tree);

					}
					break;
				case 3 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:671:5: SHORT_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					SHORT_LITERAL140=(Token)match(input,SHORT_LITERAL,FOLLOW_SHORT_LITERAL_in_fixed_literal2716); 
					SHORT_LITERAL140_tree = (CommonTree)adaptor.create(SHORT_LITERAL140);
					adaptor.addChild(root_0, SHORT_LITERAL140_tree);

					}
					break;
				case 4 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:672:5: BYTE_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					BYTE_LITERAL141=(Token)match(input,BYTE_LITERAL,FOLLOW_BYTE_LITERAL_in_fixed_literal2722); 
					BYTE_LITERAL141_tree = (CommonTree)adaptor.create(BYTE_LITERAL141);
					adaptor.addChild(root_0, BYTE_LITERAL141_tree);

					}
					break;
				case 5 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:673:5: float_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_float_literal_in_fixed_literal2728);
					float_literal142=float_literal();
					state._fsp--;

					adaptor.addChild(root_0, float_literal142.getTree());

					}
					break;
				case 6 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:674:5: double_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_double_literal_in_fixed_literal2734);
					double_literal143=double_literal();
					state._fsp--;

					adaptor.addChild(root_0, double_literal143.getTree());

					}
					break;
				case 7 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:675:5: CHAR_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					CHAR_LITERAL144=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_fixed_literal2740); 
					CHAR_LITERAL144_tree = (CommonTree)adaptor.create(CHAR_LITERAL144);
					adaptor.addChild(root_0, CHAR_LITERAL144_tree);

					}
					break;
				case 8 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:676:5: BOOL_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					BOOL_LITERAL145=(Token)match(input,BOOL_LITERAL,FOLLOW_BOOL_LITERAL_in_fixed_literal2746); 
					BOOL_LITERAL145_tree = (CommonTree)adaptor.create(BOOL_LITERAL145);
					adaptor.addChild(root_0, BOOL_LITERAL145_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fixed_literal"


	public static class array_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "array_literal"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:678:1: array_literal : OPEN_BRACE ( literal ( COMMA literal )* |) CLOSE_BRACE -> ^( I_ENCODED_ARRAY[$start, \"I_ENCODED_ARRAY\"] ( literal )* ) ;
	public final smaliParser.array_literal_return array_literal() throws RecognitionException {
		smaliParser.array_literal_return retval = new smaliParser.array_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token OPEN_BRACE146=null;
		Token COMMA148=null;
		Token CLOSE_BRACE150=null;
		ParserRuleReturnScope literal147 =null;
		ParserRuleReturnScope literal149 =null;

		CommonTree OPEN_BRACE146_tree=null;
		CommonTree COMMA148_tree=null;
		CommonTree CLOSE_BRACE150_tree=null;
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleSubtreeStream stream_literal=new RewriteRuleSubtreeStream(adaptor,"rule literal");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:679:3: ( OPEN_BRACE ( literal ( COMMA literal )* |) CLOSE_BRACE -> ^( I_ENCODED_ARRAY[$start, \"I_ENCODED_ARRAY\"] ( literal )* ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:679:5: OPEN_BRACE ( literal ( COMMA literal )* |) CLOSE_BRACE
			{
			OPEN_BRACE146=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_array_literal2756);  
			stream_OPEN_BRACE.add(OPEN_BRACE146);

			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:679:16: ( literal ( COMMA literal )* |)
			int alt24=2;
			int LA24_0 = input.LA(1);
			if ( (LA24_0==ACCESS_SPEC||LA24_0==ANNOTATION_VISIBILITY||LA24_0==ARRAY_DESCRIPTOR||(LA24_0 >= BOOL_LITERAL && LA24_0 <= BYTE_LITERAL)||(LA24_0 >= CHAR_LITERAL && LA24_0 <= CLASS_DESCRIPTOR)||(LA24_0 >= DOUBLE_LITERAL && LA24_0 <= DOUBLE_LITERAL_OR_ID)||LA24_0==ENUM_DIRECTIVE||(LA24_0 >= FLOAT_LITERAL && LA24_0 <= FLOAT_LITERAL_OR_ID)||(LA24_0 >= INSTRUCTION_FORMAT10t && LA24_0 <= INSTRUCTION_FORMAT10x_ODEX)||LA24_0==INSTRUCTION_FORMAT11x||LA24_0==INSTRUCTION_FORMAT12x_OR_ID||(LA24_0 >= INSTRUCTION_FORMAT21c_FIELD && LA24_0 <= INSTRUCTION_FORMAT21c_TYPE)||LA24_0==INSTRUCTION_FORMAT21t||(LA24_0 >= INSTRUCTION_FORMAT22c_FIELD && LA24_0 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA24_0 >= INSTRUCTION_FORMAT22s_OR_ID && LA24_0 <= INSTRUCTION_FORMAT22t)||(LA24_0 >= INSTRUCTION_FORMAT23x && LA24_0 <= INSTRUCTION_FORMAT25x)||(LA24_0 >= INSTRUCTION_FORMAT31i_OR_ID && LA24_0 <= INSTRUCTION_FORMAT31t)||(LA24_0 >= INSTRUCTION_FORMAT35c_METHOD && LA24_0 <= INSTRUCTION_FORMAT35ms_METHOD)||LA24_0==INSTRUCTION_FORMAT51l||(LA24_0 >= LONG_LITERAL && LA24_0 <= MEMBER_NAME)||(LA24_0 >= NEGATIVE_INTEGER_LITERAL && LA24_0 <= OPEN_BRACE)||LA24_0==PARAM_LIST_OR_ID_START||(LA24_0 >= POSITIVE_INTEGER_LITERAL && LA24_0 <= PRIMITIVE_TYPE)||LA24_0==REGISTER||(LA24_0 >= SHORT_LITERAL && LA24_0 <= SIMPLE_NAME)||(LA24_0 >= STRING_LITERAL && LA24_0 <= SUBANNOTATION_DIRECTIVE)||(LA24_0 >= VERIFICATION_ERROR_TYPE && LA24_0 <= VOID_TYPE)) ) {
				alt24=1;
			}
			else if ( (LA24_0==CLOSE_BRACE) ) {
				alt24=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 24, 0, input);
				throw nvae;
			}

			switch (alt24) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:679:17: literal ( COMMA literal )*
					{
					pushFollow(FOLLOW_literal_in_array_literal2759);
					literal147=literal();
					state._fsp--;

					stream_literal.add(literal147.getTree());
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:679:25: ( COMMA literal )*
					loop23:
					while (true) {
						int alt23=2;
						int LA23_0 = input.LA(1);
						if ( (LA23_0==COMMA) ) {
							alt23=1;
						}

						switch (alt23) {
						case 1 :
							// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:679:26: COMMA literal
							{
							COMMA148=(Token)match(input,COMMA,FOLLOW_COMMA_in_array_literal2762);  
							stream_COMMA.add(COMMA148);

							pushFollow(FOLLOW_literal_in_array_literal2764);
							literal149=literal();
							state._fsp--;

							stream_literal.add(literal149.getTree());
							}
							break;

						default :
							break loop23;
						}
					}

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:679:44: 
					{
					}
					break;

			}

			CLOSE_BRACE150=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_array_literal2772);  
			stream_CLOSE_BRACE.add(CLOSE_BRACE150);

			// AST REWRITE
			// elements: literal
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 680:5: -> ^( I_ENCODED_ARRAY[$start, \"I_ENCODED_ARRAY\"] ( literal )* )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:680:8: ^( I_ENCODED_ARRAY[$start, \"I_ENCODED_ARRAY\"] ( literal )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ENCODED_ARRAY, (retval.start), "I_ENCODED_ARRAY"), root_1);
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:680:53: ( literal )*
				while ( stream_literal.hasNext() ) {
					adaptor.addChild(root_1, stream_literal.nextTree());
				}
				stream_literal.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "array_literal"


	public static class annotation_element_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "annotation_element"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:682:1: annotation_element : simple_name EQUAL literal -> ^( I_ANNOTATION_ELEMENT[$start, \"I_ANNOTATION_ELEMENT\"] simple_name literal ) ;
	public final smaliParser.annotation_element_return annotation_element() throws RecognitionException {
		smaliParser.annotation_element_return retval = new smaliParser.annotation_element_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token EQUAL152=null;
		ParserRuleReturnScope simple_name151 =null;
		ParserRuleReturnScope literal153 =null;

		CommonTree EQUAL152_tree=null;
		RewriteRuleTokenStream stream_EQUAL=new RewriteRuleTokenStream(adaptor,"token EQUAL");
		RewriteRuleSubtreeStream stream_simple_name=new RewriteRuleSubtreeStream(adaptor,"rule simple_name");
		RewriteRuleSubtreeStream stream_literal=new RewriteRuleSubtreeStream(adaptor,"rule literal");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:683:3: ( simple_name EQUAL literal -> ^( I_ANNOTATION_ELEMENT[$start, \"I_ANNOTATION_ELEMENT\"] simple_name literal ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:683:5: simple_name EQUAL literal
			{
			pushFollow(FOLLOW_simple_name_in_annotation_element2796);
			simple_name151=simple_name();
			state._fsp--;

			stream_simple_name.add(simple_name151.getTree());
			EQUAL152=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_annotation_element2798);  
			stream_EQUAL.add(EQUAL152);

			pushFollow(FOLLOW_literal_in_annotation_element2800);
			literal153=literal();
			state._fsp--;

			stream_literal.add(literal153.getTree());
			// AST REWRITE
			// elements: simple_name, literal
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 684:5: -> ^( I_ANNOTATION_ELEMENT[$start, \"I_ANNOTATION_ELEMENT\"] simple_name literal )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:684:8: ^( I_ANNOTATION_ELEMENT[$start, \"I_ANNOTATION_ELEMENT\"] simple_name literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ANNOTATION_ELEMENT, (retval.start), "I_ANNOTATION_ELEMENT"), root_1);
				adaptor.addChild(root_1, stream_simple_name.nextTree());
				adaptor.addChild(root_1, stream_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "annotation_element"


	public static class annotation_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "annotation"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:686:1: annotation : ANNOTATION_DIRECTIVE ANNOTATION_VISIBILITY CLASS_DESCRIPTOR ( annotation_element )* END_ANNOTATION_DIRECTIVE -> ^( I_ANNOTATION[$start, \"I_ANNOTATION\"] ANNOTATION_VISIBILITY ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* ) ) ;
	public final smaliParser.annotation_return annotation() throws RecognitionException {
		smaliParser.annotation_return retval = new smaliParser.annotation_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ANNOTATION_DIRECTIVE154=null;
		Token ANNOTATION_VISIBILITY155=null;
		Token CLASS_DESCRIPTOR156=null;
		Token END_ANNOTATION_DIRECTIVE158=null;
		ParserRuleReturnScope annotation_element157 =null;

		CommonTree ANNOTATION_DIRECTIVE154_tree=null;
		CommonTree ANNOTATION_VISIBILITY155_tree=null;
		CommonTree CLASS_DESCRIPTOR156_tree=null;
		CommonTree END_ANNOTATION_DIRECTIVE158_tree=null;
		RewriteRuleTokenStream stream_ANNOTATION_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token ANNOTATION_DIRECTIVE");
		RewriteRuleTokenStream stream_END_ANNOTATION_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_ANNOTATION_DIRECTIVE");
		RewriteRuleTokenStream stream_ANNOTATION_VISIBILITY=new RewriteRuleTokenStream(adaptor,"token ANNOTATION_VISIBILITY");
		RewriteRuleTokenStream stream_CLASS_DESCRIPTOR=new RewriteRuleTokenStream(adaptor,"token CLASS_DESCRIPTOR");
		RewriteRuleSubtreeStream stream_annotation_element=new RewriteRuleSubtreeStream(adaptor,"rule annotation_element");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:687:3: ( ANNOTATION_DIRECTIVE ANNOTATION_VISIBILITY CLASS_DESCRIPTOR ( annotation_element )* END_ANNOTATION_DIRECTIVE -> ^( I_ANNOTATION[$start, \"I_ANNOTATION\"] ANNOTATION_VISIBILITY ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* ) ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:687:5: ANNOTATION_DIRECTIVE ANNOTATION_VISIBILITY CLASS_DESCRIPTOR ( annotation_element )* END_ANNOTATION_DIRECTIVE
			{
			ANNOTATION_DIRECTIVE154=(Token)match(input,ANNOTATION_DIRECTIVE,FOLLOW_ANNOTATION_DIRECTIVE_in_annotation2825);  
			stream_ANNOTATION_DIRECTIVE.add(ANNOTATION_DIRECTIVE154);

			ANNOTATION_VISIBILITY155=(Token)match(input,ANNOTATION_VISIBILITY,FOLLOW_ANNOTATION_VISIBILITY_in_annotation2827);  
			stream_ANNOTATION_VISIBILITY.add(ANNOTATION_VISIBILITY155);

			CLASS_DESCRIPTOR156=(Token)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_annotation2829);  
			stream_CLASS_DESCRIPTOR.add(CLASS_DESCRIPTOR156);

			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:688:5: ( annotation_element )*
			loop25:
			while (true) {
				int alt25=2;
				int LA25_0 = input.LA(1);
				if ( (LA25_0==ACCESS_SPEC||LA25_0==ANNOTATION_VISIBILITY||LA25_0==BOOL_LITERAL||LA25_0==DOUBLE_LITERAL_OR_ID||LA25_0==FLOAT_LITERAL_OR_ID||(LA25_0 >= INSTRUCTION_FORMAT10t && LA25_0 <= INSTRUCTION_FORMAT10x_ODEX)||LA25_0==INSTRUCTION_FORMAT11x||LA25_0==INSTRUCTION_FORMAT12x_OR_ID||(LA25_0 >= INSTRUCTION_FORMAT21c_FIELD && LA25_0 <= INSTRUCTION_FORMAT21c_TYPE)||LA25_0==INSTRUCTION_FORMAT21t||(LA25_0 >= INSTRUCTION_FORMAT22c_FIELD && LA25_0 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA25_0 >= INSTRUCTION_FORMAT22s_OR_ID && LA25_0 <= INSTRUCTION_FORMAT22t)||(LA25_0 >= INSTRUCTION_FORMAT23x && LA25_0 <= INSTRUCTION_FORMAT25x)||(LA25_0 >= INSTRUCTION_FORMAT31i_OR_ID && LA25_0 <= INSTRUCTION_FORMAT31t)||(LA25_0 >= INSTRUCTION_FORMAT35c_METHOD && LA25_0 <= INSTRUCTION_FORMAT35ms_METHOD)||LA25_0==INSTRUCTION_FORMAT51l||(LA25_0 >= NEGATIVE_INTEGER_LITERAL && LA25_0 <= NULL_LITERAL)||LA25_0==PARAM_LIST_OR_ID_START||(LA25_0 >= POSITIVE_INTEGER_LITERAL && LA25_0 <= PRIMITIVE_TYPE)||LA25_0==REGISTER||LA25_0==SIMPLE_NAME||(LA25_0 >= VERIFICATION_ERROR_TYPE && LA25_0 <= VOID_TYPE)) ) {
					alt25=1;
				}

				switch (alt25) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:688:5: annotation_element
					{
					pushFollow(FOLLOW_annotation_element_in_annotation2835);
					annotation_element157=annotation_element();
					state._fsp--;

					stream_annotation_element.add(annotation_element157.getTree());
					}
					break;

				default :
					break loop25;
				}
			}

			END_ANNOTATION_DIRECTIVE158=(Token)match(input,END_ANNOTATION_DIRECTIVE,FOLLOW_END_ANNOTATION_DIRECTIVE_in_annotation2838);  
			stream_END_ANNOTATION_DIRECTIVE.add(END_ANNOTATION_DIRECTIVE158);

			// AST REWRITE
			// elements: annotation_element, CLASS_DESCRIPTOR, ANNOTATION_VISIBILITY
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 689:5: -> ^( I_ANNOTATION[$start, \"I_ANNOTATION\"] ANNOTATION_VISIBILITY ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* ) )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:689:8: ^( I_ANNOTATION[$start, \"I_ANNOTATION\"] ANNOTATION_VISIBILITY ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* ) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ANNOTATION, (retval.start), "I_ANNOTATION"), root_1);
				adaptor.addChild(root_1, stream_ANNOTATION_VISIBILITY.nextNode());
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:689:69: ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_SUBANNOTATION, (retval.start), "I_SUBANNOTATION"), root_2);
				adaptor.addChild(root_2, stream_CLASS_DESCRIPTOR.nextNode());
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:689:131: ( annotation_element )*
				while ( stream_annotation_element.hasNext() ) {
					adaptor.addChild(root_2, stream_annotation_element.nextTree());
				}
				stream_annotation_element.reset();

				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "annotation"


	public static class subannotation_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "subannotation"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:691:1: subannotation : SUBANNOTATION_DIRECTIVE CLASS_DESCRIPTOR ( annotation_element )* END_SUBANNOTATION_DIRECTIVE -> ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* ) ;
	public final smaliParser.subannotation_return subannotation() throws RecognitionException {
		smaliParser.subannotation_return retval = new smaliParser.subannotation_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SUBANNOTATION_DIRECTIVE159=null;
		Token CLASS_DESCRIPTOR160=null;
		Token END_SUBANNOTATION_DIRECTIVE162=null;
		ParserRuleReturnScope annotation_element161 =null;

		CommonTree SUBANNOTATION_DIRECTIVE159_tree=null;
		CommonTree CLASS_DESCRIPTOR160_tree=null;
		CommonTree END_SUBANNOTATION_DIRECTIVE162_tree=null;
		RewriteRuleTokenStream stream_SUBANNOTATION_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token SUBANNOTATION_DIRECTIVE");
		RewriteRuleTokenStream stream_END_SUBANNOTATION_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_SUBANNOTATION_DIRECTIVE");
		RewriteRuleTokenStream stream_CLASS_DESCRIPTOR=new RewriteRuleTokenStream(adaptor,"token CLASS_DESCRIPTOR");
		RewriteRuleSubtreeStream stream_annotation_element=new RewriteRuleSubtreeStream(adaptor,"rule annotation_element");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:692:3: ( SUBANNOTATION_DIRECTIVE CLASS_DESCRIPTOR ( annotation_element )* END_SUBANNOTATION_DIRECTIVE -> ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:692:5: SUBANNOTATION_DIRECTIVE CLASS_DESCRIPTOR ( annotation_element )* END_SUBANNOTATION_DIRECTIVE
			{
			SUBANNOTATION_DIRECTIVE159=(Token)match(input,SUBANNOTATION_DIRECTIVE,FOLLOW_SUBANNOTATION_DIRECTIVE_in_subannotation2871);  
			stream_SUBANNOTATION_DIRECTIVE.add(SUBANNOTATION_DIRECTIVE159);

			CLASS_DESCRIPTOR160=(Token)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_subannotation2873);  
			stream_CLASS_DESCRIPTOR.add(CLASS_DESCRIPTOR160);

			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:692:46: ( annotation_element )*
			loop26:
			while (true) {
				int alt26=2;
				int LA26_0 = input.LA(1);
				if ( (LA26_0==ACCESS_SPEC||LA26_0==ANNOTATION_VISIBILITY||LA26_0==BOOL_LITERAL||LA26_0==DOUBLE_LITERAL_OR_ID||LA26_0==FLOAT_LITERAL_OR_ID||(LA26_0 >= INSTRUCTION_FORMAT10t && LA26_0 <= INSTRUCTION_FORMAT10x_ODEX)||LA26_0==INSTRUCTION_FORMAT11x||LA26_0==INSTRUCTION_FORMAT12x_OR_ID||(LA26_0 >= INSTRUCTION_FORMAT21c_FIELD && LA26_0 <= INSTRUCTION_FORMAT21c_TYPE)||LA26_0==INSTRUCTION_FORMAT21t||(LA26_0 >= INSTRUCTION_FORMAT22c_FIELD && LA26_0 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA26_0 >= INSTRUCTION_FORMAT22s_OR_ID && LA26_0 <= INSTRUCTION_FORMAT22t)||(LA26_0 >= INSTRUCTION_FORMAT23x && LA26_0 <= INSTRUCTION_FORMAT25x)||(LA26_0 >= INSTRUCTION_FORMAT31i_OR_ID && LA26_0 <= INSTRUCTION_FORMAT31t)||(LA26_0 >= INSTRUCTION_FORMAT35c_METHOD && LA26_0 <= INSTRUCTION_FORMAT35ms_METHOD)||LA26_0==INSTRUCTION_FORMAT51l||(LA26_0 >= NEGATIVE_INTEGER_LITERAL && LA26_0 <= NULL_LITERAL)||LA26_0==PARAM_LIST_OR_ID_START||(LA26_0 >= POSITIVE_INTEGER_LITERAL && LA26_0 <= PRIMITIVE_TYPE)||LA26_0==REGISTER||LA26_0==SIMPLE_NAME||(LA26_0 >= VERIFICATION_ERROR_TYPE && LA26_0 <= VOID_TYPE)) ) {
					alt26=1;
				}

				switch (alt26) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:692:46: annotation_element
					{
					pushFollow(FOLLOW_annotation_element_in_subannotation2875);
					annotation_element161=annotation_element();
					state._fsp--;

					stream_annotation_element.add(annotation_element161.getTree());
					}
					break;

				default :
					break loop26;
				}
			}

			END_SUBANNOTATION_DIRECTIVE162=(Token)match(input,END_SUBANNOTATION_DIRECTIVE,FOLLOW_END_SUBANNOTATION_DIRECTIVE_in_subannotation2878);  
			stream_END_SUBANNOTATION_DIRECTIVE.add(END_SUBANNOTATION_DIRECTIVE162);

			// AST REWRITE
			// elements: annotation_element, CLASS_DESCRIPTOR
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 693:5: -> ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:693:8: ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_SUBANNOTATION, (retval.start), "I_SUBANNOTATION"), root_1);
				adaptor.addChild(root_1, stream_CLASS_DESCRIPTOR.nextNode());
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:693:70: ( annotation_element )*
				while ( stream_annotation_element.hasNext() ) {
					adaptor.addChild(root_1, stream_annotation_element.nextTree());
				}
				stream_annotation_element.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "subannotation"


	public static class enum_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "enum_literal"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:695:1: enum_literal : ENUM_DIRECTIVE reference_type_descriptor ARROW simple_name COLON reference_type_descriptor -> ^( I_ENCODED_ENUM reference_type_descriptor simple_name reference_type_descriptor ) ;
	public final smaliParser.enum_literal_return enum_literal() throws RecognitionException {
		smaliParser.enum_literal_return retval = new smaliParser.enum_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ENUM_DIRECTIVE163=null;
		Token ARROW165=null;
		Token COLON167=null;
		ParserRuleReturnScope reference_type_descriptor164 =null;
		ParserRuleReturnScope simple_name166 =null;
		ParserRuleReturnScope reference_type_descriptor168 =null;

		CommonTree ENUM_DIRECTIVE163_tree=null;
		CommonTree ARROW165_tree=null;
		CommonTree COLON167_tree=null;
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleTokenStream stream_ENUM_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token ENUM_DIRECTIVE");
		RewriteRuleTokenStream stream_ARROW=new RewriteRuleTokenStream(adaptor,"token ARROW");
		RewriteRuleSubtreeStream stream_reference_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule reference_type_descriptor");
		RewriteRuleSubtreeStream stream_simple_name=new RewriteRuleSubtreeStream(adaptor,"rule simple_name");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:696:3: ( ENUM_DIRECTIVE reference_type_descriptor ARROW simple_name COLON reference_type_descriptor -> ^( I_ENCODED_ENUM reference_type_descriptor simple_name reference_type_descriptor ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:696:5: ENUM_DIRECTIVE reference_type_descriptor ARROW simple_name COLON reference_type_descriptor
			{
			ENUM_DIRECTIVE163=(Token)match(input,ENUM_DIRECTIVE,FOLLOW_ENUM_DIRECTIVE_in_enum_literal2904);  
			stream_ENUM_DIRECTIVE.add(ENUM_DIRECTIVE163);

			pushFollow(FOLLOW_reference_type_descriptor_in_enum_literal2906);
			reference_type_descriptor164=reference_type_descriptor();
			state._fsp--;

			stream_reference_type_descriptor.add(reference_type_descriptor164.getTree());
			ARROW165=(Token)match(input,ARROW,FOLLOW_ARROW_in_enum_literal2908);  
			stream_ARROW.add(ARROW165);

			pushFollow(FOLLOW_simple_name_in_enum_literal2910);
			simple_name166=simple_name();
			state._fsp--;

			stream_simple_name.add(simple_name166.getTree());
			COLON167=(Token)match(input,COLON,FOLLOW_COLON_in_enum_literal2912);  
			stream_COLON.add(COLON167);

			pushFollow(FOLLOW_reference_type_descriptor_in_enum_literal2914);
			reference_type_descriptor168=reference_type_descriptor();
			state._fsp--;

			stream_reference_type_descriptor.add(reference_type_descriptor168.getTree());
			// AST REWRITE
			// elements: reference_type_descriptor, reference_type_descriptor, simple_name
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 697:3: -> ^( I_ENCODED_ENUM reference_type_descriptor simple_name reference_type_descriptor )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:697:6: ^( I_ENCODED_ENUM reference_type_descriptor simple_name reference_type_descriptor )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ENCODED_ENUM, "I_ENCODED_ENUM"), root_1);
				adaptor.addChild(root_1, stream_reference_type_descriptor.nextTree());
				adaptor.addChild(root_1, stream_simple_name.nextTree());
				adaptor.addChild(root_1, stream_reference_type_descriptor.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "enum_literal"


	public static class type_field_method_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "type_field_method_literal"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:699:1: type_field_method_literal : ( reference_type_descriptor | ( ( reference_type_descriptor ARROW )? ( member_name COLON nonvoid_type_descriptor -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor ) | member_name method_prototype -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype ) ) ) | PRIMITIVE_TYPE | VOID_TYPE );
	public final smaliParser.type_field_method_literal_return type_field_method_literal() throws RecognitionException {
		smaliParser.type_field_method_literal_return retval = new smaliParser.type_field_method_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ARROW171=null;
		Token COLON173=null;
		Token PRIMITIVE_TYPE177=null;
		Token VOID_TYPE178=null;
		ParserRuleReturnScope reference_type_descriptor169 =null;
		ParserRuleReturnScope reference_type_descriptor170 =null;
		ParserRuleReturnScope member_name172 =null;
		ParserRuleReturnScope nonvoid_type_descriptor174 =null;
		ParserRuleReturnScope member_name175 =null;
		ParserRuleReturnScope method_prototype176 =null;

		CommonTree ARROW171_tree=null;
		CommonTree COLON173_tree=null;
		CommonTree PRIMITIVE_TYPE177_tree=null;
		CommonTree VOID_TYPE178_tree=null;
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleTokenStream stream_ARROW=new RewriteRuleTokenStream(adaptor,"token ARROW");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");
		RewriteRuleSubtreeStream stream_method_prototype=new RewriteRuleSubtreeStream(adaptor,"rule method_prototype");
		RewriteRuleSubtreeStream stream_reference_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule reference_type_descriptor");
		RewriteRuleSubtreeStream stream_member_name=new RewriteRuleSubtreeStream(adaptor,"rule member_name");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:700:3: ( reference_type_descriptor | ( ( reference_type_descriptor ARROW )? ( member_name COLON nonvoid_type_descriptor -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor ) | member_name method_prototype -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype ) ) ) | PRIMITIVE_TYPE | VOID_TYPE )
			int alt29=4;
			switch ( input.LA(1) ) {
			case ARRAY_DESCRIPTOR:
			case CLASS_DESCRIPTOR:
				{
				int LA29_1 = input.LA(2);
				if ( (LA29_1==EOF||(LA29_1 >= ACCESS_SPEC && LA29_1 <= ANNOTATION_VISIBILITY)||LA29_1==BOOL_LITERAL||(LA29_1 >= CLASS_DIRECTIVE && LA29_1 <= CLOSE_BRACE)||LA29_1==COMMA||(LA29_1 >= DOUBLE_LITERAL_OR_ID && LA29_1 <= END_ANNOTATION_DIRECTIVE)||LA29_1==END_FIELD_DIRECTIVE||LA29_1==END_SUBANNOTATION_DIRECTIVE||LA29_1==FIELD_DIRECTIVE||(LA29_1 >= FLOAT_LITERAL_OR_ID && LA29_1 <= IMPLEMENTS_DIRECTIVE)||(LA29_1 >= INSTRUCTION_FORMAT10t && LA29_1 <= INSTRUCTION_FORMAT10x_ODEX)||LA29_1==INSTRUCTION_FORMAT11x||LA29_1==INSTRUCTION_FORMAT12x_OR_ID||(LA29_1 >= INSTRUCTION_FORMAT21c_FIELD && LA29_1 <= INSTRUCTION_FORMAT21c_TYPE)||LA29_1==INSTRUCTION_FORMAT21t||(LA29_1 >= INSTRUCTION_FORMAT22c_FIELD && LA29_1 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA29_1 >= INSTRUCTION_FORMAT22s_OR_ID && LA29_1 <= INSTRUCTION_FORMAT22t)||(LA29_1 >= INSTRUCTION_FORMAT23x && LA29_1 <= INSTRUCTION_FORMAT25x)||(LA29_1 >= INSTRUCTION_FORMAT31i_OR_ID && LA29_1 <= INSTRUCTION_FORMAT31t)||(LA29_1 >= INSTRUCTION_FORMAT35c_METHOD && LA29_1 <= INSTRUCTION_FORMAT35ms_METHOD)||LA29_1==INSTRUCTION_FORMAT51l||(LA29_1 >= METHOD_DIRECTIVE && LA29_1 <= NULL_LITERAL)||LA29_1==PARAM_LIST_OR_ID_START||(LA29_1 >= POSITIVE_INTEGER_LITERAL && LA29_1 <= PRIMITIVE_TYPE)||LA29_1==REGISTER||(LA29_1 >= SIMPLE_NAME && LA29_1 <= SOURCE_DIRECTIVE)||(LA29_1 >= SUPER_DIRECTIVE && LA29_1 <= VOID_TYPE)) ) {
					alt29=1;
				}
				else if ( (LA29_1==ARROW) ) {
					alt29=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 29, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case ACCESS_SPEC:
			case ANNOTATION_VISIBILITY:
			case BOOL_LITERAL:
			case DOUBLE_LITERAL_OR_ID:
			case FLOAT_LITERAL_OR_ID:
			case INSTRUCTION_FORMAT10t:
			case INSTRUCTION_FORMAT10x:
			case INSTRUCTION_FORMAT10x_ODEX:
			case INSTRUCTION_FORMAT11x:
			case INSTRUCTION_FORMAT12x_OR_ID:
			case INSTRUCTION_FORMAT21c_FIELD:
			case INSTRUCTION_FORMAT21c_FIELD_ODEX:
			case INSTRUCTION_FORMAT21c_LAMBDA:
			case INSTRUCTION_FORMAT21c_METHOD:
			case INSTRUCTION_FORMAT21c_STRING:
			case INSTRUCTION_FORMAT21c_TYPE:
			case INSTRUCTION_FORMAT21t:
			case INSTRUCTION_FORMAT22c_FIELD:
			case INSTRUCTION_FORMAT22c_FIELD_ODEX:
			case INSTRUCTION_FORMAT22c_STRING:
			case INSTRUCTION_FORMAT22c_TYPE:
			case INSTRUCTION_FORMAT22cs_FIELD:
			case INSTRUCTION_FORMAT22s_OR_ID:
			case INSTRUCTION_FORMAT22t:
			case INSTRUCTION_FORMAT23x:
			case INSTRUCTION_FORMAT25x:
			case INSTRUCTION_FORMAT31i_OR_ID:
			case INSTRUCTION_FORMAT31t:
			case INSTRUCTION_FORMAT35c_METHOD:
			case INSTRUCTION_FORMAT35c_METHOD_ODEX:
			case INSTRUCTION_FORMAT35c_TYPE:
			case INSTRUCTION_FORMAT35mi_METHOD:
			case INSTRUCTION_FORMAT35ms_METHOD:
			case INSTRUCTION_FORMAT51l:
			case MEMBER_NAME:
			case NEGATIVE_INTEGER_LITERAL:
			case NULL_LITERAL:
			case PARAM_LIST_OR_ID_START:
			case POSITIVE_INTEGER_LITERAL:
			case REGISTER:
			case SIMPLE_NAME:
			case VERIFICATION_ERROR_TYPE:
				{
				alt29=2;
				}
				break;
			case PRIMITIVE_TYPE:
				{
				int LA29_3 = input.LA(2);
				if ( (LA29_3==COLON||LA29_3==OPEN_PAREN) ) {
					alt29=2;
				}
				else if ( (LA29_3==EOF||(LA29_3 >= ACCESS_SPEC && LA29_3 <= ANNOTATION_VISIBILITY)||LA29_3==BOOL_LITERAL||(LA29_3 >= CLASS_DIRECTIVE && LA29_3 <= CLOSE_BRACE)||LA29_3==COMMA||(LA29_3 >= DOUBLE_LITERAL_OR_ID && LA29_3 <= END_ANNOTATION_DIRECTIVE)||LA29_3==END_FIELD_DIRECTIVE||LA29_3==END_SUBANNOTATION_DIRECTIVE||LA29_3==FIELD_DIRECTIVE||(LA29_3 >= FLOAT_LITERAL_OR_ID && LA29_3 <= IMPLEMENTS_DIRECTIVE)||(LA29_3 >= INSTRUCTION_FORMAT10t && LA29_3 <= INSTRUCTION_FORMAT10x_ODEX)||LA29_3==INSTRUCTION_FORMAT11x||LA29_3==INSTRUCTION_FORMAT12x_OR_ID||(LA29_3 >= INSTRUCTION_FORMAT21c_FIELD && LA29_3 <= INSTRUCTION_FORMAT21c_TYPE)||LA29_3==INSTRUCTION_FORMAT21t||(LA29_3 >= INSTRUCTION_FORMAT22c_FIELD && LA29_3 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA29_3 >= INSTRUCTION_FORMAT22s_OR_ID && LA29_3 <= INSTRUCTION_FORMAT22t)||(LA29_3 >= INSTRUCTION_FORMAT23x && LA29_3 <= INSTRUCTION_FORMAT25x)||(LA29_3 >= INSTRUCTION_FORMAT31i_OR_ID && LA29_3 <= INSTRUCTION_FORMAT31t)||(LA29_3 >= INSTRUCTION_FORMAT35c_METHOD && LA29_3 <= INSTRUCTION_FORMAT35ms_METHOD)||LA29_3==INSTRUCTION_FORMAT51l||(LA29_3 >= METHOD_DIRECTIVE && LA29_3 <= NULL_LITERAL)||LA29_3==PARAM_LIST_OR_ID_START||(LA29_3 >= POSITIVE_INTEGER_LITERAL && LA29_3 <= PRIMITIVE_TYPE)||LA29_3==REGISTER||(LA29_3 >= SIMPLE_NAME && LA29_3 <= SOURCE_DIRECTIVE)||(LA29_3 >= SUPER_DIRECTIVE && LA29_3 <= VOID_TYPE)) ) {
					alt29=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 29, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case VOID_TYPE:
				{
				int LA29_4 = input.LA(2);
				if ( (LA29_4==COLON||LA29_4==OPEN_PAREN) ) {
					alt29=2;
				}
				else if ( (LA29_4==EOF||(LA29_4 >= ACCESS_SPEC && LA29_4 <= ANNOTATION_VISIBILITY)||LA29_4==BOOL_LITERAL||(LA29_4 >= CLASS_DIRECTIVE && LA29_4 <= CLOSE_BRACE)||LA29_4==COMMA||(LA29_4 >= DOUBLE_LITERAL_OR_ID && LA29_4 <= END_ANNOTATION_DIRECTIVE)||LA29_4==END_FIELD_DIRECTIVE||LA29_4==END_SUBANNOTATION_DIRECTIVE||LA29_4==FIELD_DIRECTIVE||(LA29_4 >= FLOAT_LITERAL_OR_ID && LA29_4 <= IMPLEMENTS_DIRECTIVE)||(LA29_4 >= INSTRUCTION_FORMAT10t && LA29_4 <= INSTRUCTION_FORMAT10x_ODEX)||LA29_4==INSTRUCTION_FORMAT11x||LA29_4==INSTRUCTION_FORMAT12x_OR_ID||(LA29_4 >= INSTRUCTION_FORMAT21c_FIELD && LA29_4 <= INSTRUCTION_FORMAT21c_TYPE)||LA29_4==INSTRUCTION_FORMAT21t||(LA29_4 >= INSTRUCTION_FORMAT22c_FIELD && LA29_4 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA29_4 >= INSTRUCTION_FORMAT22s_OR_ID && LA29_4 <= INSTRUCTION_FORMAT22t)||(LA29_4 >= INSTRUCTION_FORMAT23x && LA29_4 <= INSTRUCTION_FORMAT25x)||(LA29_4 >= INSTRUCTION_FORMAT31i_OR_ID && LA29_4 <= INSTRUCTION_FORMAT31t)||(LA29_4 >= INSTRUCTION_FORMAT35c_METHOD && LA29_4 <= INSTRUCTION_FORMAT35ms_METHOD)||LA29_4==INSTRUCTION_FORMAT51l||(LA29_4 >= METHOD_DIRECTIVE && LA29_4 <= NULL_LITERAL)||LA29_4==PARAM_LIST_OR_ID_START||(LA29_4 >= POSITIVE_INTEGER_LITERAL && LA29_4 <= PRIMITIVE_TYPE)||LA29_4==REGISTER||(LA29_4 >= SIMPLE_NAME && LA29_4 <= SOURCE_DIRECTIVE)||(LA29_4 >= SUPER_DIRECTIVE && LA29_4 <= VOID_TYPE)) ) {
					alt29=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 29, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 29, 0, input);
				throw nvae;
			}
			switch (alt29) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:700:5: reference_type_descriptor
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_reference_type_descriptor_in_type_field_method_literal2938);
					reference_type_descriptor169=reference_type_descriptor();
					state._fsp--;

					adaptor.addChild(root_0, reference_type_descriptor169.getTree());

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:701:5: ( ( reference_type_descriptor ARROW )? ( member_name COLON nonvoid_type_descriptor -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor ) | member_name method_prototype -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype ) ) )
					{
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:701:5: ( ( reference_type_descriptor ARROW )? ( member_name COLON nonvoid_type_descriptor -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor ) | member_name method_prototype -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype ) ) )
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:701:7: ( reference_type_descriptor ARROW )? ( member_name COLON nonvoid_type_descriptor -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor ) | member_name method_prototype -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype ) )
					{
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:701:7: ( reference_type_descriptor ARROW )?
					int alt27=2;
					int LA27_0 = input.LA(1);
					if ( (LA27_0==ARRAY_DESCRIPTOR||LA27_0==CLASS_DESCRIPTOR) ) {
						alt27=1;
					}
					switch (alt27) {
						case 1 :
							// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:701:8: reference_type_descriptor ARROW
							{
							pushFollow(FOLLOW_reference_type_descriptor_in_type_field_method_literal2947);
							reference_type_descriptor170=reference_type_descriptor();
							state._fsp--;

							stream_reference_type_descriptor.add(reference_type_descriptor170.getTree());
							ARROW171=(Token)match(input,ARROW,FOLLOW_ARROW_in_type_field_method_literal2949);  
							stream_ARROW.add(ARROW171);

							}
							break;

					}

					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:702:7: ( member_name COLON nonvoid_type_descriptor -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor ) | member_name method_prototype -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype ) )
					int alt28=2;
					alt28 = dfa28.predict(input);
					switch (alt28) {
						case 1 :
							// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:702:9: member_name COLON nonvoid_type_descriptor
							{
							pushFollow(FOLLOW_member_name_in_type_field_method_literal2961);
							member_name172=member_name();
							state._fsp--;

							stream_member_name.add(member_name172.getTree());
							COLON173=(Token)match(input,COLON,FOLLOW_COLON_in_type_field_method_literal2963);  
							stream_COLON.add(COLON173);

							pushFollow(FOLLOW_nonvoid_type_descriptor_in_type_field_method_literal2965);
							nonvoid_type_descriptor174=nonvoid_type_descriptor();
							state._fsp--;

							stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor174.getTree());
							// AST REWRITE
							// elements: reference_type_descriptor, member_name, nonvoid_type_descriptor
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 702:51: -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor )
							{
								// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:702:54: ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ENCODED_FIELD, "I_ENCODED_FIELD"), root_1);
								// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:702:72: ( reference_type_descriptor )?
								if ( stream_reference_type_descriptor.hasNext() ) {
									adaptor.addChild(root_1, stream_reference_type_descriptor.nextTree());
								}
								stream_reference_type_descriptor.reset();

								adaptor.addChild(root_1, stream_member_name.nextTree());
								adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;
						case 2 :
							// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:703:9: member_name method_prototype
							{
							pushFollow(FOLLOW_member_name_in_type_field_method_literal2988);
							member_name175=member_name();
							state._fsp--;

							stream_member_name.add(member_name175.getTree());
							pushFollow(FOLLOW_method_prototype_in_type_field_method_literal2990);
							method_prototype176=method_prototype();
							state._fsp--;

							stream_method_prototype.add(method_prototype176.getTree());
							// AST REWRITE
							// elements: member_name, method_prototype, reference_type_descriptor
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 703:38: -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype )
							{
								// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:703:41: ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ENCODED_METHOD, "I_ENCODED_METHOD"), root_1);
								// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:703:60: ( reference_type_descriptor )?
								if ( stream_reference_type_descriptor.hasNext() ) {
									adaptor.addChild(root_1, stream_reference_type_descriptor.nextTree());
								}
								stream_reference_type_descriptor.reset();

								adaptor.addChild(root_1, stream_member_name.nextTree());
								adaptor.addChild(root_1, stream_method_prototype.nextTree());
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

					}

					}
					break;
				case 3 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:706:5: PRIMITIVE_TYPE
					{
					root_0 = (CommonTree)adaptor.nil();


					PRIMITIVE_TYPE177=(Token)match(input,PRIMITIVE_TYPE,FOLLOW_PRIMITIVE_TYPE_in_type_field_method_literal3023); 
					PRIMITIVE_TYPE177_tree = (CommonTree)adaptor.create(PRIMITIVE_TYPE177);
					adaptor.addChild(root_0, PRIMITIVE_TYPE177_tree);

					}
					break;
				case 4 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:707:5: VOID_TYPE
					{
					root_0 = (CommonTree)adaptor.nil();


					VOID_TYPE178=(Token)match(input,VOID_TYPE,FOLLOW_VOID_TYPE_in_type_field_method_literal3029); 
					VOID_TYPE178_tree = (CommonTree)adaptor.create(VOID_TYPE178);
					adaptor.addChild(root_0, VOID_TYPE178_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "type_field_method_literal"


	public static class method_reference_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "method_reference"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:709:1: method_reference : ( reference_type_descriptor ARROW )? member_name method_prototype -> ( reference_type_descriptor )? member_name method_prototype ;
	public final smaliParser.method_reference_return method_reference() throws RecognitionException {
		smaliParser.method_reference_return retval = new smaliParser.method_reference_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ARROW180=null;
		ParserRuleReturnScope reference_type_descriptor179 =null;
		ParserRuleReturnScope member_name181 =null;
		ParserRuleReturnScope method_prototype182 =null;

		CommonTree ARROW180_tree=null;
		RewriteRuleTokenStream stream_ARROW=new RewriteRuleTokenStream(adaptor,"token ARROW");
		RewriteRuleSubtreeStream stream_method_prototype=new RewriteRuleSubtreeStream(adaptor,"rule method_prototype");
		RewriteRuleSubtreeStream stream_reference_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule reference_type_descriptor");
		RewriteRuleSubtreeStream stream_member_name=new RewriteRuleSubtreeStream(adaptor,"rule member_name");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:710:3: ( ( reference_type_descriptor ARROW )? member_name method_prototype -> ( reference_type_descriptor )? member_name method_prototype )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:710:5: ( reference_type_descriptor ARROW )? member_name method_prototype
			{
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:710:5: ( reference_type_descriptor ARROW )?
			int alt30=2;
			int LA30_0 = input.LA(1);
			if ( (LA30_0==ARRAY_DESCRIPTOR||LA30_0==CLASS_DESCRIPTOR) ) {
				alt30=1;
			}
			switch (alt30) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:710:6: reference_type_descriptor ARROW
					{
					pushFollow(FOLLOW_reference_type_descriptor_in_method_reference3040);
					reference_type_descriptor179=reference_type_descriptor();
					state._fsp--;

					stream_reference_type_descriptor.add(reference_type_descriptor179.getTree());
					ARROW180=(Token)match(input,ARROW,FOLLOW_ARROW_in_method_reference3042);  
					stream_ARROW.add(ARROW180);

					}
					break;

			}

			pushFollow(FOLLOW_member_name_in_method_reference3046);
			member_name181=member_name();
			state._fsp--;

			stream_member_name.add(member_name181.getTree());
			pushFollow(FOLLOW_method_prototype_in_method_reference3048);
			method_prototype182=method_prototype();
			state._fsp--;

			stream_method_prototype.add(method_prototype182.getTree());
			// AST REWRITE
			// elements: member_name, method_prototype, reference_type_descriptor
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 711:3: -> ( reference_type_descriptor )? member_name method_prototype
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:711:6: ( reference_type_descriptor )?
				if ( stream_reference_type_descriptor.hasNext() ) {
					adaptor.addChild(root_0, stream_reference_type_descriptor.nextTree());
				}
				stream_reference_type_descriptor.reset();

				adaptor.addChild(root_0, stream_member_name.nextTree());
				adaptor.addChild(root_0, stream_method_prototype.nextTree());
			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "method_reference"


	public static class field_reference_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "field_reference"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:713:1: field_reference : ( reference_type_descriptor ARROW )? member_name COLON nonvoid_type_descriptor -> ( reference_type_descriptor )? member_name nonvoid_type_descriptor ;
	public final smaliParser.field_reference_return field_reference() throws RecognitionException {
		smaliParser.field_reference_return retval = new smaliParser.field_reference_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ARROW184=null;
		Token COLON186=null;
		ParserRuleReturnScope reference_type_descriptor183 =null;
		ParserRuleReturnScope member_name185 =null;
		ParserRuleReturnScope nonvoid_type_descriptor187 =null;

		CommonTree ARROW184_tree=null;
		CommonTree COLON186_tree=null;
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleTokenStream stream_ARROW=new RewriteRuleTokenStream(adaptor,"token ARROW");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");
		RewriteRuleSubtreeStream stream_reference_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule reference_type_descriptor");
		RewriteRuleSubtreeStream stream_member_name=new RewriteRuleSubtreeStream(adaptor,"rule member_name");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:714:3: ( ( reference_type_descriptor ARROW )? member_name COLON nonvoid_type_descriptor -> ( reference_type_descriptor )? member_name nonvoid_type_descriptor )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:714:5: ( reference_type_descriptor ARROW )? member_name COLON nonvoid_type_descriptor
			{
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:714:5: ( reference_type_descriptor ARROW )?
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0==ARRAY_DESCRIPTOR||LA31_0==CLASS_DESCRIPTOR) ) {
				alt31=1;
			}
			switch (alt31) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:714:6: reference_type_descriptor ARROW
					{
					pushFollow(FOLLOW_reference_type_descriptor_in_field_reference3070);
					reference_type_descriptor183=reference_type_descriptor();
					state._fsp--;

					stream_reference_type_descriptor.add(reference_type_descriptor183.getTree());
					ARROW184=(Token)match(input,ARROW,FOLLOW_ARROW_in_field_reference3072);  
					stream_ARROW.add(ARROW184);

					}
					break;

			}

			pushFollow(FOLLOW_member_name_in_field_reference3076);
			member_name185=member_name();
			state._fsp--;

			stream_member_name.add(member_name185.getTree());
			COLON186=(Token)match(input,COLON,FOLLOW_COLON_in_field_reference3078);  
			stream_COLON.add(COLON186);

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_field_reference3080);
			nonvoid_type_descriptor187=nonvoid_type_descriptor();
			state._fsp--;

			stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor187.getTree());
			// AST REWRITE
			// elements: member_name, reference_type_descriptor, nonvoid_type_descriptor
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 715:3: -> ( reference_type_descriptor )? member_name nonvoid_type_descriptor
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:715:6: ( reference_type_descriptor )?
				if ( stream_reference_type_descriptor.hasNext() ) {
					adaptor.addChild(root_0, stream_reference_type_descriptor.nextTree());
				}
				stream_reference_type_descriptor.reset();

				adaptor.addChild(root_0, stream_member_name.nextTree());
				adaptor.addChild(root_0, stream_nonvoid_type_descriptor.nextTree());
			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "field_reference"


	public static class label_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "label"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:717:1: label : COLON simple_name -> ^( I_LABEL[$COLON, \"I_LABEL\"] simple_name ) ;
	public final smaliParser.label_return label() throws RecognitionException {
		smaliParser.label_return retval = new smaliParser.label_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COLON188=null;
		ParserRuleReturnScope simple_name189 =null;

		CommonTree COLON188_tree=null;
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleSubtreeStream stream_simple_name=new RewriteRuleSubtreeStream(adaptor,"rule simple_name");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:718:3: ( COLON simple_name -> ^( I_LABEL[$COLON, \"I_LABEL\"] simple_name ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:718:5: COLON simple_name
			{
			COLON188=(Token)match(input,COLON,FOLLOW_COLON_in_label3101);  
			stream_COLON.add(COLON188);

			pushFollow(FOLLOW_simple_name_in_label3103);
			simple_name189=simple_name();
			state._fsp--;

			stream_simple_name.add(simple_name189.getTree());
			// AST REWRITE
			// elements: simple_name
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 718:23: -> ^( I_LABEL[$COLON, \"I_LABEL\"] simple_name )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:718:26: ^( I_LABEL[$COLON, \"I_LABEL\"] simple_name )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_LABEL, COLON188, "I_LABEL"), root_1);
				adaptor.addChild(root_1, stream_simple_name.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "label"


	public static class label_ref_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "label_ref"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:720:1: label_ref : COLON simple_name -> simple_name ;
	public final smaliParser.label_ref_return label_ref() throws RecognitionException {
		smaliParser.label_ref_return retval = new smaliParser.label_ref_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COLON190=null;
		ParserRuleReturnScope simple_name191 =null;

		CommonTree COLON190_tree=null;
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleSubtreeStream stream_simple_name=new RewriteRuleSubtreeStream(adaptor,"rule simple_name");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:721:3: ( COLON simple_name -> simple_name )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:721:5: COLON simple_name
			{
			COLON190=(Token)match(input,COLON,FOLLOW_COLON_in_label_ref3122);  
			stream_COLON.add(COLON190);

			pushFollow(FOLLOW_simple_name_in_label_ref3124);
			simple_name191=simple_name();
			state._fsp--;

			stream_simple_name.add(simple_name191.getTree());
			// AST REWRITE
			// elements: simple_name
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 721:23: -> simple_name
			{
				adaptor.addChild(root_0, stream_simple_name.nextTree());
			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "label_ref"


	public static class register_list_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "register_list"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:723:1: register_list : ( REGISTER ( COMMA REGISTER )* -> ^( I_REGISTER_LIST[$start, \"I_REGISTER_LIST\"] ( REGISTER )* ) | -> ^( I_REGISTER_LIST[$start, \"I_REGISTER_LIST\"] ) );
	public final smaliParser.register_list_return register_list() throws RecognitionException {
		smaliParser.register_list_return retval = new smaliParser.register_list_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token REGISTER192=null;
		Token COMMA193=null;
		Token REGISTER194=null;

		CommonTree REGISTER192_tree=null;
		CommonTree COMMA193_tree=null;
		CommonTree REGISTER194_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:724:3: ( REGISTER ( COMMA REGISTER )* -> ^( I_REGISTER_LIST[$start, \"I_REGISTER_LIST\"] ( REGISTER )* ) | -> ^( I_REGISTER_LIST[$start, \"I_REGISTER_LIST\"] ) )
			int alt33=2;
			int LA33_0 = input.LA(1);
			if ( (LA33_0==REGISTER) ) {
				alt33=1;
			}
			else if ( (LA33_0==CLOSE_BRACE) ) {
				alt33=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 33, 0, input);
				throw nvae;
			}

			switch (alt33) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:724:5: REGISTER ( COMMA REGISTER )*
					{
					REGISTER192=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_register_list3138);  
					stream_REGISTER.add(REGISTER192);

					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:724:14: ( COMMA REGISTER )*
					loop32:
					while (true) {
						int alt32=2;
						int LA32_0 = input.LA(1);
						if ( (LA32_0==COMMA) ) {
							alt32=1;
						}

						switch (alt32) {
						case 1 :
							// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:724:15: COMMA REGISTER
							{
							COMMA193=(Token)match(input,COMMA,FOLLOW_COMMA_in_register_list3141);  
							stream_COMMA.add(COMMA193);

							REGISTER194=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_register_list3143);  
							stream_REGISTER.add(REGISTER194);

							}
							break;

						default :
							break loop32;
						}
					}

					// AST REWRITE
					// elements: REGISTER
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 724:32: -> ^( I_REGISTER_LIST[$start, \"I_REGISTER_LIST\"] ( REGISTER )* )
					{
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:724:35: ^( I_REGISTER_LIST[$start, \"I_REGISTER_LIST\"] ( REGISTER )* )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_REGISTER_LIST, (retval.start), "I_REGISTER_LIST"), root_1);
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:724:80: ( REGISTER )*
						while ( stream_REGISTER.hasNext() ) {
							adaptor.addChild(root_1, stream_REGISTER.nextNode());
						}
						stream_REGISTER.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:725:5: 
					{
					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 725:5: -> ^( I_REGISTER_LIST[$start, \"I_REGISTER_LIST\"] )
					{
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:725:7: ^( I_REGISTER_LIST[$start, \"I_REGISTER_LIST\"] )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_REGISTER_LIST, (retval.start), "I_REGISTER_LIST"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "register_list"


	public static class register_range_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "register_range"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:727:1: register_range : (startreg= REGISTER ( DOTDOT endreg= REGISTER )? )? -> ^( I_REGISTER_RANGE[$start, \"I_REGISTER_RANGE\"] ( $startreg)? ( $endreg)? ) ;
	public final smaliParser.register_range_return register_range() throws RecognitionException {
		smaliParser.register_range_return retval = new smaliParser.register_range_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token startreg=null;
		Token endreg=null;
		Token DOTDOT195=null;

		CommonTree startreg_tree=null;
		CommonTree endreg_tree=null;
		CommonTree DOTDOT195_tree=null;
		RewriteRuleTokenStream stream_DOTDOT=new RewriteRuleTokenStream(adaptor,"token DOTDOT");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:728:3: ( (startreg= REGISTER ( DOTDOT endreg= REGISTER )? )? -> ^( I_REGISTER_RANGE[$start, \"I_REGISTER_RANGE\"] ( $startreg)? ( $endreg)? ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:728:5: (startreg= REGISTER ( DOTDOT endreg= REGISTER )? )?
			{
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:728:5: (startreg= REGISTER ( DOTDOT endreg= REGISTER )? )?
			int alt35=2;
			int LA35_0 = input.LA(1);
			if ( (LA35_0==REGISTER) ) {
				alt35=1;
			}
			switch (alt35) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:728:6: startreg= REGISTER ( DOTDOT endreg= REGISTER )?
					{
					startreg=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_register_range3178);  
					stream_REGISTER.add(startreg);

					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:728:24: ( DOTDOT endreg= REGISTER )?
					int alt34=2;
					int LA34_0 = input.LA(1);
					if ( (LA34_0==DOTDOT) ) {
						alt34=1;
					}
					switch (alt34) {
						case 1 :
							// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:728:25: DOTDOT endreg= REGISTER
							{
							DOTDOT195=(Token)match(input,DOTDOT,FOLLOW_DOTDOT_in_register_range3181);  
							stream_DOTDOT.add(DOTDOT195);

							endreg=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_register_range3185);  
							stream_REGISTER.add(endreg);

							}
							break;

					}

					}
					break;

			}

			// AST REWRITE
			// elements: endreg, startreg
			// token labels: endreg, startreg
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleTokenStream stream_endreg=new RewriteRuleTokenStream(adaptor,"token endreg",endreg);
			RewriteRuleTokenStream stream_startreg=new RewriteRuleTokenStream(adaptor,"token startreg",startreg);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 728:52: -> ^( I_REGISTER_RANGE[$start, \"I_REGISTER_RANGE\"] ( $startreg)? ( $endreg)? )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:728:55: ^( I_REGISTER_RANGE[$start, \"I_REGISTER_RANGE\"] ( $startreg)? ( $endreg)? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_REGISTER_RANGE, (retval.start), "I_REGISTER_RANGE"), root_1);
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:728:103: ( $startreg)?
				if ( stream_startreg.hasNext() ) {
					adaptor.addChild(root_1, stream_startreg.nextNode());
				}
				stream_startreg.reset();

				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:728:114: ( $endreg)?
				if ( stream_endreg.hasNext() ) {
					adaptor.addChild(root_1, stream_endreg.nextNode());
				}
				stream_endreg.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "register_range"


	public static class verification_error_reference_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "verification_error_reference"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:730:1: verification_error_reference : ( CLASS_DESCRIPTOR | field_reference | method_reference );
	public final smaliParser.verification_error_reference_return verification_error_reference() throws RecognitionException {
		smaliParser.verification_error_reference_return retval = new smaliParser.verification_error_reference_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token CLASS_DESCRIPTOR196=null;
		ParserRuleReturnScope field_reference197 =null;
		ParserRuleReturnScope method_reference198 =null;

		CommonTree CLASS_DESCRIPTOR196_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:731:3: ( CLASS_DESCRIPTOR | field_reference | method_reference )
			int alt36=3;
			alt36 = dfa36.predict(input);
			switch (alt36) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:731:5: CLASS_DESCRIPTOR
					{
					root_0 = (CommonTree)adaptor.nil();


					CLASS_DESCRIPTOR196=(Token)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_verification_error_reference3214); 
					CLASS_DESCRIPTOR196_tree = (CommonTree)adaptor.create(CLASS_DESCRIPTOR196);
					adaptor.addChild(root_0, CLASS_DESCRIPTOR196_tree);

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:731:24: field_reference
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_field_reference_in_verification_error_reference3218);
					field_reference197=field_reference();
					state._fsp--;

					adaptor.addChild(root_0, field_reference197.getTree());

					}
					break;
				case 3 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:731:42: method_reference
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_method_reference_in_verification_error_reference3222);
					method_reference198=method_reference();
					state._fsp--;

					adaptor.addChild(root_0, method_reference198.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "verification_error_reference"


	public static class catch_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "catch_directive"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:733:1: catch_directive : CATCH_DIRECTIVE nonvoid_type_descriptor OPEN_BRACE from= label_ref DOTDOT to= label_ref CLOSE_BRACE using= label_ref -> ^( I_CATCH[$start, \"I_CATCH\"] nonvoid_type_descriptor $from $to $using) ;
	public final smaliParser.catch_directive_return catch_directive() throws RecognitionException {
		smaliParser.catch_directive_return retval = new smaliParser.catch_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token CATCH_DIRECTIVE199=null;
		Token OPEN_BRACE201=null;
		Token DOTDOT202=null;
		Token CLOSE_BRACE203=null;
		ParserRuleReturnScope from =null;
		ParserRuleReturnScope to =null;
		ParserRuleReturnScope using =null;
		ParserRuleReturnScope nonvoid_type_descriptor200 =null;

		CommonTree CATCH_DIRECTIVE199_tree=null;
		CommonTree OPEN_BRACE201_tree=null;
		CommonTree DOTDOT202_tree=null;
		CommonTree CLOSE_BRACE203_tree=null;
		RewriteRuleTokenStream stream_DOTDOT=new RewriteRuleTokenStream(adaptor,"token DOTDOT");
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleTokenStream stream_CATCH_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token CATCH_DIRECTIVE");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:734:3: ( CATCH_DIRECTIVE nonvoid_type_descriptor OPEN_BRACE from= label_ref DOTDOT to= label_ref CLOSE_BRACE using= label_ref -> ^( I_CATCH[$start, \"I_CATCH\"] nonvoid_type_descriptor $from $to $using) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:734:5: CATCH_DIRECTIVE nonvoid_type_descriptor OPEN_BRACE from= label_ref DOTDOT to= label_ref CLOSE_BRACE using= label_ref
			{
			CATCH_DIRECTIVE199=(Token)match(input,CATCH_DIRECTIVE,FOLLOW_CATCH_DIRECTIVE_in_catch_directive3232);  
			stream_CATCH_DIRECTIVE.add(CATCH_DIRECTIVE199);

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_catch_directive3234);
			nonvoid_type_descriptor200=nonvoid_type_descriptor();
			state._fsp--;

			stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor200.getTree());
			OPEN_BRACE201=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_catch_directive3236);  
			stream_OPEN_BRACE.add(OPEN_BRACE201);

			pushFollow(FOLLOW_label_ref_in_catch_directive3240);
			from=label_ref();
			state._fsp--;

			stream_label_ref.add(from.getTree());
			DOTDOT202=(Token)match(input,DOTDOT,FOLLOW_DOTDOT_in_catch_directive3242);  
			stream_DOTDOT.add(DOTDOT202);

			pushFollow(FOLLOW_label_ref_in_catch_directive3246);
			to=label_ref();
			state._fsp--;

			stream_label_ref.add(to.getTree());
			CLOSE_BRACE203=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_catch_directive3248);  
			stream_CLOSE_BRACE.add(CLOSE_BRACE203);

			pushFollow(FOLLOW_label_ref_in_catch_directive3252);
			using=label_ref();
			state._fsp--;

			stream_label_ref.add(using.getTree());
			// AST REWRITE
			// elements: to, from, nonvoid_type_descriptor, using
			// token labels: 
			// rule labels: to, retval, using, from
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_to=new RewriteRuleSubtreeStream(adaptor,"rule to",to!=null?to.getTree():null);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_using=new RewriteRuleSubtreeStream(adaptor,"rule using",using!=null?using.getTree():null);
			RewriteRuleSubtreeStream stream_from=new RewriteRuleSubtreeStream(adaptor,"rule from",from!=null?from.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 735:5: -> ^( I_CATCH[$start, \"I_CATCH\"] nonvoid_type_descriptor $from $to $using)
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:735:8: ^( I_CATCH[$start, \"I_CATCH\"] nonvoid_type_descriptor $from $to $using)
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_CATCH, (retval.start), "I_CATCH"), root_1);
				adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
				adaptor.addChild(root_1, stream_from.nextTree());
				adaptor.addChild(root_1, stream_to.nextTree());
				adaptor.addChild(root_1, stream_using.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "catch_directive"


	public static class catchall_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "catchall_directive"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:737:1: catchall_directive : CATCHALL_DIRECTIVE OPEN_BRACE from= label_ref DOTDOT to= label_ref CLOSE_BRACE using= label_ref -> ^( I_CATCHALL[$start, \"I_CATCHALL\"] $from $to $using) ;
	public final smaliParser.catchall_directive_return catchall_directive() throws RecognitionException {
		smaliParser.catchall_directive_return retval = new smaliParser.catchall_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token CATCHALL_DIRECTIVE204=null;
		Token OPEN_BRACE205=null;
		Token DOTDOT206=null;
		Token CLOSE_BRACE207=null;
		ParserRuleReturnScope from =null;
		ParserRuleReturnScope to =null;
		ParserRuleReturnScope using =null;

		CommonTree CATCHALL_DIRECTIVE204_tree=null;
		CommonTree OPEN_BRACE205_tree=null;
		CommonTree DOTDOT206_tree=null;
		CommonTree CLOSE_BRACE207_tree=null;
		RewriteRuleTokenStream stream_DOTDOT=new RewriteRuleTokenStream(adaptor,"token DOTDOT");
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleTokenStream stream_CATCHALL_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token CATCHALL_DIRECTIVE");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:738:3: ( CATCHALL_DIRECTIVE OPEN_BRACE from= label_ref DOTDOT to= label_ref CLOSE_BRACE using= label_ref -> ^( I_CATCHALL[$start, \"I_CATCHALL\"] $from $to $using) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:738:5: CATCHALL_DIRECTIVE OPEN_BRACE from= label_ref DOTDOT to= label_ref CLOSE_BRACE using= label_ref
			{
			CATCHALL_DIRECTIVE204=(Token)match(input,CATCHALL_DIRECTIVE,FOLLOW_CATCHALL_DIRECTIVE_in_catchall_directive3284);  
			stream_CATCHALL_DIRECTIVE.add(CATCHALL_DIRECTIVE204);

			OPEN_BRACE205=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_catchall_directive3286);  
			stream_OPEN_BRACE.add(OPEN_BRACE205);

			pushFollow(FOLLOW_label_ref_in_catchall_directive3290);
			from=label_ref();
			state._fsp--;

			stream_label_ref.add(from.getTree());
			DOTDOT206=(Token)match(input,DOTDOT,FOLLOW_DOTDOT_in_catchall_directive3292);  
			stream_DOTDOT.add(DOTDOT206);

			pushFollow(FOLLOW_label_ref_in_catchall_directive3296);
			to=label_ref();
			state._fsp--;

			stream_label_ref.add(to.getTree());
			CLOSE_BRACE207=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_catchall_directive3298);  
			stream_CLOSE_BRACE.add(CLOSE_BRACE207);

			pushFollow(FOLLOW_label_ref_in_catchall_directive3302);
			using=label_ref();
			state._fsp--;

			stream_label_ref.add(using.getTree());
			// AST REWRITE
			// elements: using, to, from
			// token labels: 
			// rule labels: to, retval, using, from
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_to=new RewriteRuleSubtreeStream(adaptor,"rule to",to!=null?to.getTree():null);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_using=new RewriteRuleSubtreeStream(adaptor,"rule using",using!=null?using.getTree():null);
			RewriteRuleSubtreeStream stream_from=new RewriteRuleSubtreeStream(adaptor,"rule from",from!=null?from.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 739:5: -> ^( I_CATCHALL[$start, \"I_CATCHALL\"] $from $to $using)
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:739:8: ^( I_CATCHALL[$start, \"I_CATCHALL\"] $from $to $using)
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_CATCHALL, (retval.start), "I_CATCHALL"), root_1);
				adaptor.addChild(root_1, stream_from.nextTree());
				adaptor.addChild(root_1, stream_to.nextTree());
				adaptor.addChild(root_1, stream_using.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "catchall_directive"


	public static class parameter_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "parameter_directive"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:745:1: parameter_directive : PARAMETER_DIRECTIVE REGISTER ( COMMA STRING_LITERAL )? ({...}? annotation )* ( END_PARAMETER_DIRECTIVE -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ) ) ) ;
	public final smaliParser.parameter_directive_return parameter_directive() throws RecognitionException {
		smaliParser.parameter_directive_return retval = new smaliParser.parameter_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token PARAMETER_DIRECTIVE208=null;
		Token REGISTER209=null;
		Token COMMA210=null;
		Token STRING_LITERAL211=null;
		Token END_PARAMETER_DIRECTIVE213=null;
		ParserRuleReturnScope annotation212 =null;

		CommonTree PARAMETER_DIRECTIVE208_tree=null;
		CommonTree REGISTER209_tree=null;
		CommonTree COMMA210_tree=null;
		CommonTree STRING_LITERAL211_tree=null;
		CommonTree END_PARAMETER_DIRECTIVE213_tree=null;
		RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
		RewriteRuleTokenStream stream_END_PARAMETER_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_PARAMETER_DIRECTIVE");
		RewriteRuleTokenStream stream_PARAMETER_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token PARAMETER_DIRECTIVE");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");

		List<CommonTree> annotations = new ArrayList<CommonTree>();
		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:747:3: ( PARAMETER_DIRECTIVE REGISTER ( COMMA STRING_LITERAL )? ({...}? annotation )* ( END_PARAMETER_DIRECTIVE -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ) ) ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:747:5: PARAMETER_DIRECTIVE REGISTER ( COMMA STRING_LITERAL )? ({...}? annotation )* ( END_PARAMETER_DIRECTIVE -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ) ) )
			{
			PARAMETER_DIRECTIVE208=(Token)match(input,PARAMETER_DIRECTIVE,FOLLOW_PARAMETER_DIRECTIVE_in_parameter_directive3341);  
			stream_PARAMETER_DIRECTIVE.add(PARAMETER_DIRECTIVE208);

			REGISTER209=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_parameter_directive3343);  
			stream_REGISTER.add(REGISTER209);

			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:747:34: ( COMMA STRING_LITERAL )?
			int alt37=2;
			int LA37_0 = input.LA(1);
			if ( (LA37_0==COMMA) ) {
				alt37=1;
			}
			switch (alt37) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:747:35: COMMA STRING_LITERAL
					{
					COMMA210=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameter_directive3346);  
					stream_COMMA.add(COMMA210);

					STRING_LITERAL211=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_parameter_directive3348);  
					stream_STRING_LITERAL.add(STRING_LITERAL211);

					}
					break;

			}

			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:748:5: ({...}? annotation )*
			loop38:
			while (true) {
				int alt38=2;
				alt38 = dfa38.predict(input);
				switch (alt38) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:748:6: {...}? annotation
					{
					if ( !((input.LA(1) == ANNOTATION_DIRECTIVE)) ) {
						throw new FailedPredicateException(input, "parameter_directive", "input.LA(1) == ANNOTATION_DIRECTIVE");
					}
					pushFollow(FOLLOW_annotation_in_parameter_directive3359);
					annotation212=annotation();
					state._fsp--;

					stream_annotation.add(annotation212.getTree());
					annotations.add((annotation212!=null?((CommonTree)annotation212.getTree()):null));
					}
					break;

				default :
					break loop38;
				}
			}

			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:750:5: ( END_PARAMETER_DIRECTIVE -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ) ) )
			int alt39=2;
			int LA39_0 = input.LA(1);
			if ( (LA39_0==END_PARAMETER_DIRECTIVE) ) {
				alt39=1;
			}
			else if ( (LA39_0==ANNOTATION_DIRECTIVE||LA39_0==ARRAY_DATA_DIRECTIVE||(LA39_0 >= CATCHALL_DIRECTIVE && LA39_0 <= CATCH_DIRECTIVE)||LA39_0==COLON||(LA39_0 >= END_LOCAL_DIRECTIVE && LA39_0 <= END_METHOD_DIRECTIVE)||LA39_0==EPILOGUE_DIRECTIVE||(LA39_0 >= INSTRUCTION_FORMAT10t && LA39_0 <= INSTRUCTION_FORMAT51l)||(LA39_0 >= LINE_DIRECTIVE && LA39_0 <= LOCAL_DIRECTIVE)||(LA39_0 >= PACKED_SWITCH_DIRECTIVE && LA39_0 <= PARAMETER_DIRECTIVE)||LA39_0==PROLOGUE_DIRECTIVE||(LA39_0 >= REGISTERS_DIRECTIVE && LA39_0 <= RESTART_LOCAL_DIRECTIVE)||(LA39_0 >= SOURCE_DIRECTIVE && LA39_0 <= SPARSE_SWITCH_DIRECTIVE)) ) {
				alt39=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 39, 0, input);
				throw nvae;
			}

			switch (alt39) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:750:7: END_PARAMETER_DIRECTIVE
					{
					END_PARAMETER_DIRECTIVE213=(Token)match(input,END_PARAMETER_DIRECTIVE,FOLLOW_END_PARAMETER_DIRECTIVE_in_parameter_directive3372);  
					stream_END_PARAMETER_DIRECTIVE.add(END_PARAMETER_DIRECTIVE213);

					// AST REWRITE
					// elements: annotation, REGISTER, STRING_LITERAL
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 751:7: -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ( annotation )* ) )
					{
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:751:10: ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ( annotation )* ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_PARAMETER, (retval.start), "I_PARAMETER"), root_1);
						adaptor.addChild(root_1, stream_REGISTER.nextNode());
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:751:56: ( STRING_LITERAL )?
						if ( stream_STRING_LITERAL.hasNext() ) {
							adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
						}
						stream_STRING_LITERAL.reset();

						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:751:72: ^( I_ANNOTATIONS ( annotation )* )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ANNOTATIONS, "I_ANNOTATIONS"), root_2);
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:751:88: ( annotation )*
						while ( stream_annotation.hasNext() ) {
							adaptor.addChild(root_2, stream_annotation.nextTree());
						}
						stream_annotation.reset();

						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:752:19: 
					{
					statements_and_directives_stack.peek().methodAnnotations.addAll(annotations);
					// AST REWRITE
					// elements: STRING_LITERAL, REGISTER
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 753:7: -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ) )
					{
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:753:10: ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_PARAMETER, (retval.start), "I_PARAMETER"), root_1);
						adaptor.addChild(root_1, stream_REGISTER.nextNode());
						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:753:56: ( STRING_LITERAL )?
						if ( stream_STRING_LITERAL.hasNext() ) {
							adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
						}
						stream_STRING_LITERAL.reset();

						// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:753:72: ^( I_ANNOTATIONS )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ANNOTATIONS, "I_ANNOTATIONS"), root_2);
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "parameter_directive"


	public static class debug_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "debug_directive"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:756:1: debug_directive : ( line_directive | local_directive | end_local_directive | restart_local_directive | prologue_directive | epilogue_directive | source_directive );
	public final smaliParser.debug_directive_return debug_directive() throws RecognitionException {
		smaliParser.debug_directive_return retval = new smaliParser.debug_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope line_directive214 =null;
		ParserRuleReturnScope local_directive215 =null;
		ParserRuleReturnScope end_local_directive216 =null;
		ParserRuleReturnScope restart_local_directive217 =null;
		ParserRuleReturnScope prologue_directive218 =null;
		ParserRuleReturnScope epilogue_directive219 =null;
		ParserRuleReturnScope source_directive220 =null;


		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:757:3: ( line_directive | local_directive | end_local_directive | restart_local_directive | prologue_directive | epilogue_directive | source_directive )
			int alt40=7;
			switch ( input.LA(1) ) {
			case LINE_DIRECTIVE:
				{
				alt40=1;
				}
				break;
			case LOCAL_DIRECTIVE:
				{
				alt40=2;
				}
				break;
			case END_LOCAL_DIRECTIVE:
				{
				alt40=3;
				}
				break;
			case RESTART_LOCAL_DIRECTIVE:
				{
				alt40=4;
				}
				break;
			case PROLOGUE_DIRECTIVE:
				{
				alt40=5;
				}
				break;
			case EPILOGUE_DIRECTIVE:
				{
				alt40=6;
				}
				break;
			case SOURCE_DIRECTIVE:
				{
				alt40=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 40, 0, input);
				throw nvae;
			}
			switch (alt40) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:757:5: line_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_line_directive_in_debug_directive3445);
					line_directive214=line_directive();
					state._fsp--;

					adaptor.addChild(root_0, line_directive214.getTree());

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:758:5: local_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_local_directive_in_debug_directive3451);
					local_directive215=local_directive();
					state._fsp--;

					adaptor.addChild(root_0, local_directive215.getTree());

					}
					break;
				case 3 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:759:5: end_local_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_end_local_directive_in_debug_directive3457);
					end_local_directive216=end_local_directive();
					state._fsp--;

					adaptor.addChild(root_0, end_local_directive216.getTree());

					}
					break;
				case 4 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:760:5: restart_local_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_restart_local_directive_in_debug_directive3463);
					restart_local_directive217=restart_local_directive();
					state._fsp--;

					adaptor.addChild(root_0, restart_local_directive217.getTree());

					}
					break;
				case 5 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:761:5: prologue_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_prologue_directive_in_debug_directive3469);
					prologue_directive218=prologue_directive();
					state._fsp--;

					adaptor.addChild(root_0, prologue_directive218.getTree());

					}
					break;
				case 6 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:762:5: epilogue_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_epilogue_directive_in_debug_directive3475);
					epilogue_directive219=epilogue_directive();
					state._fsp--;

					adaptor.addChild(root_0, epilogue_directive219.getTree());

					}
					break;
				case 7 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:763:5: source_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_source_directive_in_debug_directive3481);
					source_directive220=source_directive();
					state._fsp--;

					adaptor.addChild(root_0, source_directive220.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "debug_directive"


	public static class line_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "line_directive"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:765:1: line_directive : LINE_DIRECTIVE integral_literal -> ^( I_LINE[$start, \"I_LINE\"] integral_literal ) ;
	public final smaliParser.line_directive_return line_directive() throws RecognitionException {
		smaliParser.line_directive_return retval = new smaliParser.line_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LINE_DIRECTIVE221=null;
		ParserRuleReturnScope integral_literal222 =null;

		CommonTree LINE_DIRECTIVE221_tree=null;
		RewriteRuleTokenStream stream_LINE_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token LINE_DIRECTIVE");
		RewriteRuleSubtreeStream stream_integral_literal=new RewriteRuleSubtreeStream(adaptor,"rule integral_literal");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:766:3: ( LINE_DIRECTIVE integral_literal -> ^( I_LINE[$start, \"I_LINE\"] integral_literal ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:766:5: LINE_DIRECTIVE integral_literal
			{
			LINE_DIRECTIVE221=(Token)match(input,LINE_DIRECTIVE,FOLLOW_LINE_DIRECTIVE_in_line_directive3491);  
			stream_LINE_DIRECTIVE.add(LINE_DIRECTIVE221);

			pushFollow(FOLLOW_integral_literal_in_line_directive3493);
			integral_literal222=integral_literal();
			state._fsp--;

			stream_integral_literal.add(integral_literal222.getTree());
			// AST REWRITE
			// elements: integral_literal
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 767:5: -> ^( I_LINE[$start, \"I_LINE\"] integral_literal )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:767:8: ^( I_LINE[$start, \"I_LINE\"] integral_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_LINE, (retval.start), "I_LINE"), root_1);
				adaptor.addChild(root_1, stream_integral_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "line_directive"


	public static class local_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "local_directive"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:769:1: local_directive : LOCAL_DIRECTIVE REGISTER ( COMMA ( NULL_LITERAL |name= STRING_LITERAL ) COLON ( VOID_TYPE | nonvoid_type_descriptor ) ( COMMA signature= STRING_LITERAL )? )? -> ^( I_LOCAL[$start, \"I_LOCAL\"] REGISTER ( NULL_LITERAL )? ( $name)? ( nonvoid_type_descriptor )? ( $signature)? ) ;
	public final smaliParser.local_directive_return local_directive() throws RecognitionException {
		smaliParser.local_directive_return retval = new smaliParser.local_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token name=null;
		Token signature=null;
		Token LOCAL_DIRECTIVE223=null;
		Token REGISTER224=null;
		Token COMMA225=null;
		Token NULL_LITERAL226=null;
		Token COLON227=null;
		Token VOID_TYPE228=null;
		Token COMMA230=null;
		ParserRuleReturnScope nonvoid_type_descriptor229 =null;

		CommonTree name_tree=null;
		CommonTree signature_tree=null;
		CommonTree LOCAL_DIRECTIVE223_tree=null;
		CommonTree REGISTER224_tree=null;
		CommonTree COMMA225_tree=null;
		CommonTree NULL_LITERAL226_tree=null;
		CommonTree COLON227_tree=null;
		CommonTree VOID_TYPE228_tree=null;
		CommonTree COMMA230_tree=null;
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleTokenStream stream_NULL_LITERAL=new RewriteRuleTokenStream(adaptor,"token NULL_LITERAL");
		RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
		RewriteRuleTokenStream stream_LOCAL_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token LOCAL_DIRECTIVE");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_VOID_TYPE=new RewriteRuleTokenStream(adaptor,"token VOID_TYPE");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:770:3: ( LOCAL_DIRECTIVE REGISTER ( COMMA ( NULL_LITERAL |name= STRING_LITERAL ) COLON ( VOID_TYPE | nonvoid_type_descriptor ) ( COMMA signature= STRING_LITERAL )? )? -> ^( I_LOCAL[$start, \"I_LOCAL\"] REGISTER ( NULL_LITERAL )? ( $name)? ( nonvoid_type_descriptor )? ( $signature)? ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:770:5: LOCAL_DIRECTIVE REGISTER ( COMMA ( NULL_LITERAL |name= STRING_LITERAL ) COLON ( VOID_TYPE | nonvoid_type_descriptor ) ( COMMA signature= STRING_LITERAL )? )?
			{
			LOCAL_DIRECTIVE223=(Token)match(input,LOCAL_DIRECTIVE,FOLLOW_LOCAL_DIRECTIVE_in_local_directive3516);  
			stream_LOCAL_DIRECTIVE.add(LOCAL_DIRECTIVE223);

			REGISTER224=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_local_directive3518);  
			stream_REGISTER.add(REGISTER224);

			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:770:30: ( COMMA ( NULL_LITERAL |name= STRING_LITERAL ) COLON ( VOID_TYPE | nonvoid_type_descriptor ) ( COMMA signature= STRING_LITERAL )? )?
			int alt44=2;
			int LA44_0 = input.LA(1);
			if ( (LA44_0==COMMA) ) {
				alt44=1;
			}
			switch (alt44) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:770:31: COMMA ( NULL_LITERAL |name= STRING_LITERAL ) COLON ( VOID_TYPE | nonvoid_type_descriptor ) ( COMMA signature= STRING_LITERAL )?
					{
					COMMA225=(Token)match(input,COMMA,FOLLOW_COMMA_in_local_directive3521);  
					stream_COMMA.add(COMMA225);

					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:770:37: ( NULL_LITERAL |name= STRING_LITERAL )
					int alt41=2;
					int LA41_0 = input.LA(1);
					if ( (LA41_0==NULL_LITERAL) ) {
						alt41=1;
					}
					else if ( (LA41_0==STRING_LITERAL) ) {
						alt41=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 41, 0, input);
						throw nvae;
					}

					switch (alt41) {
						case 1 :
							// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:770:38: NULL_LITERAL
							{
							NULL_LITERAL226=(Token)match(input,NULL_LITERAL,FOLLOW_NULL_LITERAL_in_local_directive3524);  
							stream_NULL_LITERAL.add(NULL_LITERAL226);

							}
							break;
						case 2 :
							// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:770:53: name= STRING_LITERAL
							{
							name=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_local_directive3530);  
							stream_STRING_LITERAL.add(name);

							}
							break;

					}

					COLON227=(Token)match(input,COLON,FOLLOW_COLON_in_local_directive3533);  
					stream_COLON.add(COLON227);

					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:770:80: ( VOID_TYPE | nonvoid_type_descriptor )
					int alt42=2;
					int LA42_0 = input.LA(1);
					if ( (LA42_0==VOID_TYPE) ) {
						alt42=1;
					}
					else if ( (LA42_0==ARRAY_DESCRIPTOR||LA42_0==CLASS_DESCRIPTOR||LA42_0==PRIMITIVE_TYPE) ) {
						alt42=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 42, 0, input);
						throw nvae;
					}

					switch (alt42) {
						case 1 :
							// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:770:81: VOID_TYPE
							{
							VOID_TYPE228=(Token)match(input,VOID_TYPE,FOLLOW_VOID_TYPE_in_local_directive3536);  
							stream_VOID_TYPE.add(VOID_TYPE228);

							}
							break;
						case 2 :
							// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:770:93: nonvoid_type_descriptor
							{
							pushFollow(FOLLOW_nonvoid_type_descriptor_in_local_directive3540);
							nonvoid_type_descriptor229=nonvoid_type_descriptor();
							state._fsp--;

							stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor229.getTree());
							}
							break;

					}

					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:771:31: ( COMMA signature= STRING_LITERAL )?
					int alt43=2;
					int LA43_0 = input.LA(1);
					if ( (LA43_0==COMMA) ) {
						alt43=1;
					}
					switch (alt43) {
						case 1 :
							// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:771:32: COMMA signature= STRING_LITERAL
							{
							COMMA230=(Token)match(input,COMMA,FOLLOW_COMMA_in_local_directive3574);  
							stream_COMMA.add(COMMA230);

							signature=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_local_directive3578);  
							stream_STRING_LITERAL.add(signature);

							}
							break;

					}

					}
					break;

			}

			// AST REWRITE
			// elements: REGISTER, name, NULL_LITERAL, nonvoid_type_descriptor, signature
			// token labels: name, signature
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleTokenStream stream_name=new RewriteRuleTokenStream(adaptor,"token name",name);
			RewriteRuleTokenStream stream_signature=new RewriteRuleTokenStream(adaptor,"token signature",signature);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 772:5: -> ^( I_LOCAL[$start, \"I_LOCAL\"] REGISTER ( NULL_LITERAL )? ( $name)? ( nonvoid_type_descriptor )? ( $signature)? )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:772:8: ^( I_LOCAL[$start, \"I_LOCAL\"] REGISTER ( NULL_LITERAL )? ( $name)? ( nonvoid_type_descriptor )? ( $signature)? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_LOCAL, (retval.start), "I_LOCAL"), root_1);
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:772:46: ( NULL_LITERAL )?
				if ( stream_NULL_LITERAL.hasNext() ) {
					adaptor.addChild(root_1, stream_NULL_LITERAL.nextNode());
				}
				stream_NULL_LITERAL.reset();

				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:772:61: ( $name)?
				if ( stream_name.hasNext() ) {
					adaptor.addChild(root_1, stream_name.nextNode());
				}
				stream_name.reset();

				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:772:67: ( nonvoid_type_descriptor )?
				if ( stream_nonvoid_type_descriptor.hasNext() ) {
					adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
				}
				stream_nonvoid_type_descriptor.reset();

				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:772:93: ( $signature)?
				if ( stream_signature.hasNext() ) {
					adaptor.addChild(root_1, stream_signature.nextNode());
				}
				stream_signature.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "local_directive"


	public static class end_local_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "end_local_directive"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:774:1: end_local_directive : END_LOCAL_DIRECTIVE REGISTER -> ^( I_END_LOCAL[$start, \"I_END_LOCAL\"] REGISTER ) ;
	public final smaliParser.end_local_directive_return end_local_directive() throws RecognitionException {
		smaliParser.end_local_directive_return retval = new smaliParser.end_local_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token END_LOCAL_DIRECTIVE231=null;
		Token REGISTER232=null;

		CommonTree END_LOCAL_DIRECTIVE231_tree=null;
		CommonTree REGISTER232_tree=null;
		RewriteRuleTokenStream stream_END_LOCAL_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_LOCAL_DIRECTIVE");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:775:3: ( END_LOCAL_DIRECTIVE REGISTER -> ^( I_END_LOCAL[$start, \"I_END_LOCAL\"] REGISTER ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:775:5: END_LOCAL_DIRECTIVE REGISTER
			{
			END_LOCAL_DIRECTIVE231=(Token)match(input,END_LOCAL_DIRECTIVE,FOLLOW_END_LOCAL_DIRECTIVE_in_end_local_directive3620);  
			stream_END_LOCAL_DIRECTIVE.add(END_LOCAL_DIRECTIVE231);

			REGISTER232=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_end_local_directive3622);  
			stream_REGISTER.add(REGISTER232);

			// AST REWRITE
			// elements: REGISTER
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 776:5: -> ^( I_END_LOCAL[$start, \"I_END_LOCAL\"] REGISTER )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:776:8: ^( I_END_LOCAL[$start, \"I_END_LOCAL\"] REGISTER )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_END_LOCAL, (retval.start), "I_END_LOCAL"), root_1);
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "end_local_directive"


	public static class restart_local_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "restart_local_directive"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:778:1: restart_local_directive : RESTART_LOCAL_DIRECTIVE REGISTER -> ^( I_RESTART_LOCAL[$start, \"I_RESTART_LOCAL\"] REGISTER ) ;
	public final smaliParser.restart_local_directive_return restart_local_directive() throws RecognitionException {
		smaliParser.restart_local_directive_return retval = new smaliParser.restart_local_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token RESTART_LOCAL_DIRECTIVE233=null;
		Token REGISTER234=null;

		CommonTree RESTART_LOCAL_DIRECTIVE233_tree=null;
		CommonTree REGISTER234_tree=null;
		RewriteRuleTokenStream stream_RESTART_LOCAL_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token RESTART_LOCAL_DIRECTIVE");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:779:3: ( RESTART_LOCAL_DIRECTIVE REGISTER -> ^( I_RESTART_LOCAL[$start, \"I_RESTART_LOCAL\"] REGISTER ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:779:5: RESTART_LOCAL_DIRECTIVE REGISTER
			{
			RESTART_LOCAL_DIRECTIVE233=(Token)match(input,RESTART_LOCAL_DIRECTIVE,FOLLOW_RESTART_LOCAL_DIRECTIVE_in_restart_local_directive3645);  
			stream_RESTART_LOCAL_DIRECTIVE.add(RESTART_LOCAL_DIRECTIVE233);

			REGISTER234=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_restart_local_directive3647);  
			stream_REGISTER.add(REGISTER234);

			// AST REWRITE
			// elements: REGISTER
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 780:5: -> ^( I_RESTART_LOCAL[$start, \"I_RESTART_LOCAL\"] REGISTER )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:780:8: ^( I_RESTART_LOCAL[$start, \"I_RESTART_LOCAL\"] REGISTER )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_RESTART_LOCAL, (retval.start), "I_RESTART_LOCAL"), root_1);
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "restart_local_directive"


	public static class prologue_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "prologue_directive"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:782:1: prologue_directive : PROLOGUE_DIRECTIVE -> ^( I_PROLOGUE[$start, \"I_PROLOGUE\"] ) ;
	public final smaliParser.prologue_directive_return prologue_directive() throws RecognitionException {
		smaliParser.prologue_directive_return retval = new smaliParser.prologue_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token PROLOGUE_DIRECTIVE235=null;

		CommonTree PROLOGUE_DIRECTIVE235_tree=null;
		RewriteRuleTokenStream stream_PROLOGUE_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token PROLOGUE_DIRECTIVE");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:783:3: ( PROLOGUE_DIRECTIVE -> ^( I_PROLOGUE[$start, \"I_PROLOGUE\"] ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:783:5: PROLOGUE_DIRECTIVE
			{
			PROLOGUE_DIRECTIVE235=(Token)match(input,PROLOGUE_DIRECTIVE,FOLLOW_PROLOGUE_DIRECTIVE_in_prologue_directive3670);  
			stream_PROLOGUE_DIRECTIVE.add(PROLOGUE_DIRECTIVE235);

			// AST REWRITE
			// elements: 
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 784:5: -> ^( I_PROLOGUE[$start, \"I_PROLOGUE\"] )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:784:8: ^( I_PROLOGUE[$start, \"I_PROLOGUE\"] )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_PROLOGUE, (retval.start), "I_PROLOGUE"), root_1);
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "prologue_directive"


	public static class epilogue_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "epilogue_directive"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:786:1: epilogue_directive : EPILOGUE_DIRECTIVE -> ^( I_EPILOGUE[$start, \"I_EPILOGUE\"] ) ;
	public final smaliParser.epilogue_directive_return epilogue_directive() throws RecognitionException {
		smaliParser.epilogue_directive_return retval = new smaliParser.epilogue_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token EPILOGUE_DIRECTIVE236=null;

		CommonTree EPILOGUE_DIRECTIVE236_tree=null;
		RewriteRuleTokenStream stream_EPILOGUE_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token EPILOGUE_DIRECTIVE");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:787:3: ( EPILOGUE_DIRECTIVE -> ^( I_EPILOGUE[$start, \"I_EPILOGUE\"] ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:787:5: EPILOGUE_DIRECTIVE
			{
			EPILOGUE_DIRECTIVE236=(Token)match(input,EPILOGUE_DIRECTIVE,FOLLOW_EPILOGUE_DIRECTIVE_in_epilogue_directive3691);  
			stream_EPILOGUE_DIRECTIVE.add(EPILOGUE_DIRECTIVE236);

			// AST REWRITE
			// elements: 
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 788:5: -> ^( I_EPILOGUE[$start, \"I_EPILOGUE\"] )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:788:8: ^( I_EPILOGUE[$start, \"I_EPILOGUE\"] )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_EPILOGUE, (retval.start), "I_EPILOGUE"), root_1);
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "epilogue_directive"


	public static class source_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "source_directive"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:790:1: source_directive : SOURCE_DIRECTIVE ( STRING_LITERAL )? -> ^( I_SOURCE[$start, \"I_SOURCE\"] ( STRING_LITERAL )? ) ;
	public final smaliParser.source_directive_return source_directive() throws RecognitionException {
		smaliParser.source_directive_return retval = new smaliParser.source_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SOURCE_DIRECTIVE237=null;
		Token STRING_LITERAL238=null;

		CommonTree SOURCE_DIRECTIVE237_tree=null;
		CommonTree STRING_LITERAL238_tree=null;
		RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
		RewriteRuleTokenStream stream_SOURCE_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token SOURCE_DIRECTIVE");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:791:3: ( SOURCE_DIRECTIVE ( STRING_LITERAL )? -> ^( I_SOURCE[$start, \"I_SOURCE\"] ( STRING_LITERAL )? ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:791:5: SOURCE_DIRECTIVE ( STRING_LITERAL )?
			{
			SOURCE_DIRECTIVE237=(Token)match(input,SOURCE_DIRECTIVE,FOLLOW_SOURCE_DIRECTIVE_in_source_directive3712);  
			stream_SOURCE_DIRECTIVE.add(SOURCE_DIRECTIVE237);

			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:791:22: ( STRING_LITERAL )?
			int alt45=2;
			int LA45_0 = input.LA(1);
			if ( (LA45_0==STRING_LITERAL) ) {
				alt45=1;
			}
			switch (alt45) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:791:22: STRING_LITERAL
					{
					STRING_LITERAL238=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_source_directive3714);  
					stream_STRING_LITERAL.add(STRING_LITERAL238);

					}
					break;

			}

			// AST REWRITE
			// elements: STRING_LITERAL
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 792:5: -> ^( I_SOURCE[$start, \"I_SOURCE\"] ( STRING_LITERAL )? )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:792:8: ^( I_SOURCE[$start, \"I_SOURCE\"] ( STRING_LITERAL )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_SOURCE, (retval.start), "I_SOURCE"), root_1);
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:792:39: ( STRING_LITERAL )?
				if ( stream_STRING_LITERAL.hasNext() ) {
					adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
				}
				stream_STRING_LITERAL.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "source_directive"


	public static class instruction_format12x_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "instruction_format12x"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:794:1: instruction_format12x : ( INSTRUCTION_FORMAT12x | INSTRUCTION_FORMAT12x_OR_ID -> INSTRUCTION_FORMAT12x[$INSTRUCTION_FORMAT12x_OR_ID] );
	public final smaliParser.instruction_format12x_return instruction_format12x() throws RecognitionException {
		smaliParser.instruction_format12x_return retval = new smaliParser.instruction_format12x_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT12x239=null;
		Token INSTRUCTION_FORMAT12x_OR_ID240=null;

		CommonTree INSTRUCTION_FORMAT12x239_tree=null;
		CommonTree INSTRUCTION_FORMAT12x_OR_ID240_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT12x_OR_ID=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT12x_OR_ID");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:795:3: ( INSTRUCTION_FORMAT12x | INSTRUCTION_FORMAT12x_OR_ID -> INSTRUCTION_FORMAT12x[$INSTRUCTION_FORMAT12x_OR_ID] )
			int alt46=2;
			int LA46_0 = input.LA(1);
			if ( (LA46_0==INSTRUCTION_FORMAT12x) ) {
				alt46=1;
			}
			else if ( (LA46_0==INSTRUCTION_FORMAT12x_OR_ID) ) {
				alt46=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 46, 0, input);
				throw nvae;
			}

			switch (alt46) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:795:5: INSTRUCTION_FORMAT12x
					{
					root_0 = (CommonTree)adaptor.nil();


					INSTRUCTION_FORMAT12x239=(Token)match(input,INSTRUCTION_FORMAT12x,FOLLOW_INSTRUCTION_FORMAT12x_in_instruction_format12x3739); 
					INSTRUCTION_FORMAT12x239_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT12x239);
					adaptor.addChild(root_0, INSTRUCTION_FORMAT12x239_tree);

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:796:5: INSTRUCTION_FORMAT12x_OR_ID
					{
					INSTRUCTION_FORMAT12x_OR_ID240=(Token)match(input,INSTRUCTION_FORMAT12x_OR_ID,FOLLOW_INSTRUCTION_FORMAT12x_OR_ID_in_instruction_format12x3745);  
					stream_INSTRUCTION_FORMAT12x_OR_ID.add(INSTRUCTION_FORMAT12x_OR_ID240);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 796:33: -> INSTRUCTION_FORMAT12x[$INSTRUCTION_FORMAT12x_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(INSTRUCTION_FORMAT12x, INSTRUCTION_FORMAT12x_OR_ID240));
					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "instruction_format12x"


	public static class instruction_format22s_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "instruction_format22s"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:798:1: instruction_format22s : ( INSTRUCTION_FORMAT22s | INSTRUCTION_FORMAT22s_OR_ID -> INSTRUCTION_FORMAT22s[$INSTRUCTION_FORMAT22s_OR_ID] );
	public final smaliParser.instruction_format22s_return instruction_format22s() throws RecognitionException {
		smaliParser.instruction_format22s_return retval = new smaliParser.instruction_format22s_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22s241=null;
		Token INSTRUCTION_FORMAT22s_OR_ID242=null;

		CommonTree INSTRUCTION_FORMAT22s241_tree=null;
		CommonTree INSTRUCTION_FORMAT22s_OR_ID242_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22s_OR_ID=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22s_OR_ID");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:799:3: ( INSTRUCTION_FORMAT22s | INSTRUCTION_FORMAT22s_OR_ID -> INSTRUCTION_FORMAT22s[$INSTRUCTION_FORMAT22s_OR_ID] )
			int alt47=2;
			int LA47_0 = input.LA(1);
			if ( (LA47_0==INSTRUCTION_FORMAT22s) ) {
				alt47=1;
			}
			else if ( (LA47_0==INSTRUCTION_FORMAT22s_OR_ID) ) {
				alt47=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 47, 0, input);
				throw nvae;
			}

			switch (alt47) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:799:5: INSTRUCTION_FORMAT22s
					{
					root_0 = (CommonTree)adaptor.nil();


					INSTRUCTION_FORMAT22s241=(Token)match(input,INSTRUCTION_FORMAT22s,FOLLOW_INSTRUCTION_FORMAT22s_in_instruction_format22s3760); 
					INSTRUCTION_FORMAT22s241_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT22s241);
					adaptor.addChild(root_0, INSTRUCTION_FORMAT22s241_tree);

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:800:5: INSTRUCTION_FORMAT22s_OR_ID
					{
					INSTRUCTION_FORMAT22s_OR_ID242=(Token)match(input,INSTRUCTION_FORMAT22s_OR_ID,FOLLOW_INSTRUCTION_FORMAT22s_OR_ID_in_instruction_format22s3766);  
					stream_INSTRUCTION_FORMAT22s_OR_ID.add(INSTRUCTION_FORMAT22s_OR_ID242);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 800:33: -> INSTRUCTION_FORMAT22s[$INSTRUCTION_FORMAT22s_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(INSTRUCTION_FORMAT22s, INSTRUCTION_FORMAT22s_OR_ID242));
					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "instruction_format22s"


	public static class instruction_format31i_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "instruction_format31i"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:802:1: instruction_format31i : ( INSTRUCTION_FORMAT31i | INSTRUCTION_FORMAT31i_OR_ID -> INSTRUCTION_FORMAT31i[$INSTRUCTION_FORMAT31i_OR_ID] );
	public final smaliParser.instruction_format31i_return instruction_format31i() throws RecognitionException {
		smaliParser.instruction_format31i_return retval = new smaliParser.instruction_format31i_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT31i243=null;
		Token INSTRUCTION_FORMAT31i_OR_ID244=null;

		CommonTree INSTRUCTION_FORMAT31i243_tree=null;
		CommonTree INSTRUCTION_FORMAT31i_OR_ID244_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT31i_OR_ID=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT31i_OR_ID");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:803:3: ( INSTRUCTION_FORMAT31i | INSTRUCTION_FORMAT31i_OR_ID -> INSTRUCTION_FORMAT31i[$INSTRUCTION_FORMAT31i_OR_ID] )
			int alt48=2;
			int LA48_0 = input.LA(1);
			if ( (LA48_0==INSTRUCTION_FORMAT31i) ) {
				alt48=1;
			}
			else if ( (LA48_0==INSTRUCTION_FORMAT31i_OR_ID) ) {
				alt48=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 48, 0, input);
				throw nvae;
			}

			switch (alt48) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:803:5: INSTRUCTION_FORMAT31i
					{
					root_0 = (CommonTree)adaptor.nil();


					INSTRUCTION_FORMAT31i243=(Token)match(input,INSTRUCTION_FORMAT31i,FOLLOW_INSTRUCTION_FORMAT31i_in_instruction_format31i3781); 
					INSTRUCTION_FORMAT31i243_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT31i243);
					adaptor.addChild(root_0, INSTRUCTION_FORMAT31i243_tree);

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:804:5: INSTRUCTION_FORMAT31i_OR_ID
					{
					INSTRUCTION_FORMAT31i_OR_ID244=(Token)match(input,INSTRUCTION_FORMAT31i_OR_ID,FOLLOW_INSTRUCTION_FORMAT31i_OR_ID_in_instruction_format31i3787);  
					stream_INSTRUCTION_FORMAT31i_OR_ID.add(INSTRUCTION_FORMAT31i_OR_ID244);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 804:33: -> INSTRUCTION_FORMAT31i[$INSTRUCTION_FORMAT31i_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(INSTRUCTION_FORMAT31i, INSTRUCTION_FORMAT31i_OR_ID244));
					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "instruction_format31i"


	public static class instruction_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "instruction"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:808:1: instruction : ( insn_format10t | insn_format10x | insn_format10x_odex | insn_format11n | insn_format11x | insn_format12x | insn_format20bc | insn_format20t | insn_format21c_field | insn_format21c_field_odex | insn_format21c_string | insn_format21c_type | insn_format21c_lambda | insn_format21c_method | insn_format21ih | insn_format21lh | insn_format21s | insn_format21t | insn_format22b | insn_format22c_field | insn_format22c_field_odex | insn_format22c_type | insn_format22c_string | insn_format22cs_field | insn_format22s | insn_format22t | insn_format22x | insn_format23x | insn_format25x | insn_format30t | insn_format31c | insn_format31i | insn_format31t | insn_format32x | insn_format35c_method | insn_format35c_type | insn_format35c_method_odex | insn_format35mi_method | insn_format35ms_method | insn_format3rc_method | insn_format3rc_method_odex | insn_format3rc_type | insn_format3rmi_method | insn_format3rms_method | insn_format51l | insn_array_data_directive | insn_packed_switch_directive | insn_sparse_switch_directive );
	public final smaliParser.instruction_return instruction() throws RecognitionException {
		smaliParser.instruction_return retval = new smaliParser.instruction_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope insn_format10t245 =null;
		ParserRuleReturnScope insn_format10x246 =null;
		ParserRuleReturnScope insn_format10x_odex247 =null;
		ParserRuleReturnScope insn_format11n248 =null;
		ParserRuleReturnScope insn_format11x249 =null;
		ParserRuleReturnScope insn_format12x250 =null;
		ParserRuleReturnScope insn_format20bc251 =null;
		ParserRuleReturnScope insn_format20t252 =null;
		ParserRuleReturnScope insn_format21c_field253 =null;
		ParserRuleReturnScope insn_format21c_field_odex254 =null;
		ParserRuleReturnScope insn_format21c_string255 =null;
		ParserRuleReturnScope insn_format21c_type256 =null;
		ParserRuleReturnScope insn_format21c_lambda257 =null;
		ParserRuleReturnScope insn_format21c_method258 =null;
		ParserRuleReturnScope insn_format21ih259 =null;
		ParserRuleReturnScope insn_format21lh260 =null;
		ParserRuleReturnScope insn_format21s261 =null;
		ParserRuleReturnScope insn_format21t262 =null;
		ParserRuleReturnScope insn_format22b263 =null;
		ParserRuleReturnScope insn_format22c_field264 =null;
		ParserRuleReturnScope insn_format22c_field_odex265 =null;
		ParserRuleReturnScope insn_format22c_type266 =null;
		ParserRuleReturnScope insn_format22c_string267 =null;
		ParserRuleReturnScope insn_format22cs_field268 =null;
		ParserRuleReturnScope insn_format22s269 =null;
		ParserRuleReturnScope insn_format22t270 =null;
		ParserRuleReturnScope insn_format22x271 =null;
		ParserRuleReturnScope insn_format23x272 =null;
		ParserRuleReturnScope insn_format25x273 =null;
		ParserRuleReturnScope insn_format30t274 =null;
		ParserRuleReturnScope insn_format31c275 =null;
		ParserRuleReturnScope insn_format31i276 =null;
		ParserRuleReturnScope insn_format31t277 =null;
		ParserRuleReturnScope insn_format32x278 =null;
		ParserRuleReturnScope insn_format35c_method279 =null;
		ParserRuleReturnScope insn_format35c_type280 =null;
		ParserRuleReturnScope insn_format35c_method_odex281 =null;
		ParserRuleReturnScope insn_format35mi_method282 =null;
		ParserRuleReturnScope insn_format35ms_method283 =null;
		ParserRuleReturnScope insn_format3rc_method284 =null;
		ParserRuleReturnScope insn_format3rc_method_odex285 =null;
		ParserRuleReturnScope insn_format3rc_type286 =null;
		ParserRuleReturnScope insn_format3rmi_method287 =null;
		ParserRuleReturnScope insn_format3rms_method288 =null;
		ParserRuleReturnScope insn_format51l289 =null;
		ParserRuleReturnScope insn_array_data_directive290 =null;
		ParserRuleReturnScope insn_packed_switch_directive291 =null;
		ParserRuleReturnScope insn_sparse_switch_directive292 =null;


		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:809:3: ( insn_format10t | insn_format10x | insn_format10x_odex | insn_format11n | insn_format11x | insn_format12x | insn_format20bc | insn_format20t | insn_format21c_field | insn_format21c_field_odex | insn_format21c_string | insn_format21c_type | insn_format21c_lambda | insn_format21c_method | insn_format21ih | insn_format21lh | insn_format21s | insn_format21t | insn_format22b | insn_format22c_field | insn_format22c_field_odex | insn_format22c_type | insn_format22c_string | insn_format22cs_field | insn_format22s | insn_format22t | insn_format22x | insn_format23x | insn_format25x | insn_format30t | insn_format31c | insn_format31i | insn_format31t | insn_format32x | insn_format35c_method | insn_format35c_type | insn_format35c_method_odex | insn_format35mi_method | insn_format35ms_method | insn_format3rc_method | insn_format3rc_method_odex | insn_format3rc_type | insn_format3rmi_method | insn_format3rms_method | insn_format51l | insn_array_data_directive | insn_packed_switch_directive | insn_sparse_switch_directive )
			int alt49=48;
			switch ( input.LA(1) ) {
			case INSTRUCTION_FORMAT10t:
				{
				alt49=1;
				}
				break;
			case INSTRUCTION_FORMAT10x:
				{
				alt49=2;
				}
				break;
			case INSTRUCTION_FORMAT10x_ODEX:
				{
				alt49=3;
				}
				break;
			case INSTRUCTION_FORMAT11n:
				{
				alt49=4;
				}
				break;
			case INSTRUCTION_FORMAT11x:
				{
				alt49=5;
				}
				break;
			case INSTRUCTION_FORMAT12x:
			case INSTRUCTION_FORMAT12x_OR_ID:
				{
				alt49=6;
				}
				break;
			case INSTRUCTION_FORMAT20bc:
				{
				alt49=7;
				}
				break;
			case INSTRUCTION_FORMAT20t:
				{
				alt49=8;
				}
				break;
			case INSTRUCTION_FORMAT21c_FIELD:
				{
				alt49=9;
				}
				break;
			case INSTRUCTION_FORMAT21c_FIELD_ODEX:
				{
				alt49=10;
				}
				break;
			case INSTRUCTION_FORMAT21c_STRING:
				{
				alt49=11;
				}
				break;
			case INSTRUCTION_FORMAT21c_TYPE:
				{
				alt49=12;
				}
				break;
			case INSTRUCTION_FORMAT21c_LAMBDA:
				{
				alt49=13;
				}
				break;
			case INSTRUCTION_FORMAT21c_METHOD:
				{
				alt49=14;
				}
				break;
			case INSTRUCTION_FORMAT21ih:
				{
				alt49=15;
				}
				break;
			case INSTRUCTION_FORMAT21lh:
				{
				alt49=16;
				}
				break;
			case INSTRUCTION_FORMAT21s:
				{
				alt49=17;
				}
				break;
			case INSTRUCTION_FORMAT21t:
				{
				alt49=18;
				}
				break;
			case INSTRUCTION_FORMAT22b:
				{
				alt49=19;
				}
				break;
			case INSTRUCTION_FORMAT22c_FIELD:
				{
				alt49=20;
				}
				break;
			case INSTRUCTION_FORMAT22c_FIELD_ODEX:
				{
				alt49=21;
				}
				break;
			case INSTRUCTION_FORMAT22c_TYPE:
				{
				alt49=22;
				}
				break;
			case INSTRUCTION_FORMAT22c_STRING:
				{
				alt49=23;
				}
				break;
			case INSTRUCTION_FORMAT22cs_FIELD:
				{
				alt49=24;
				}
				break;
			case INSTRUCTION_FORMAT22s:
			case INSTRUCTION_FORMAT22s_OR_ID:
				{
				alt49=25;
				}
				break;
			case INSTRUCTION_FORMAT22t:
				{
				alt49=26;
				}
				break;
			case INSTRUCTION_FORMAT22x:
				{
				alt49=27;
				}
				break;
			case INSTRUCTION_FORMAT23x:
				{
				alt49=28;
				}
				break;
			case INSTRUCTION_FORMAT25x:
				{
				alt49=29;
				}
				break;
			case INSTRUCTION_FORMAT30t:
				{
				alt49=30;
				}
				break;
			case INSTRUCTION_FORMAT31c:
				{
				alt49=31;
				}
				break;
			case INSTRUCTION_FORMAT31i:
			case INSTRUCTION_FORMAT31i_OR_ID:
				{
				alt49=32;
				}
				break;
			case INSTRUCTION_FORMAT31t:
				{
				alt49=33;
				}
				break;
			case INSTRUCTION_FORMAT32x:
				{
				alt49=34;
				}
				break;
			case INSTRUCTION_FORMAT35c_METHOD:
				{
				alt49=35;
				}
				break;
			case INSTRUCTION_FORMAT35c_TYPE:
				{
				alt49=36;
				}
				break;
			case INSTRUCTION_FORMAT35c_METHOD_ODEX:
				{
				alt49=37;
				}
				break;
			case INSTRUCTION_FORMAT35mi_METHOD:
				{
				alt49=38;
				}
				break;
			case INSTRUCTION_FORMAT35ms_METHOD:
				{
				alt49=39;
				}
				break;
			case INSTRUCTION_FORMAT3rc_METHOD:
				{
				alt49=40;
				}
				break;
			case INSTRUCTION_FORMAT3rc_METHOD_ODEX:
				{
				alt49=41;
				}
				break;
			case INSTRUCTION_FORMAT3rc_TYPE:
				{
				alt49=42;
				}
				break;
			case INSTRUCTION_FORMAT3rmi_METHOD:
				{
				alt49=43;
				}
				break;
			case INSTRUCTION_FORMAT3rms_METHOD:
				{
				alt49=44;
				}
				break;
			case INSTRUCTION_FORMAT51l:
				{
				alt49=45;
				}
				break;
			case ARRAY_DATA_DIRECTIVE:
				{
				alt49=46;
				}
				break;
			case PACKED_SWITCH_DIRECTIVE:
				{
				alt49=47;
				}
				break;
			case SPARSE_SWITCH_DIRECTIVE:
				{
				alt49=48;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 49, 0, input);
				throw nvae;
			}
			switch (alt49) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:809:5: insn_format10t
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format10t_in_instruction3804);
					insn_format10t245=insn_format10t();
					state._fsp--;

					adaptor.addChild(root_0, insn_format10t245.getTree());

					}
					break;
				case 2 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:810:5: insn_format10x
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format10x_in_instruction3810);
					insn_format10x246=insn_format10x();
					state._fsp--;

					adaptor.addChild(root_0, insn_format10x246.getTree());

					}
					break;
				case 3 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:811:5: insn_format10x_odex
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format10x_odex_in_instruction3816);
					insn_format10x_odex247=insn_format10x_odex();
					state._fsp--;

					adaptor.addChild(root_0, insn_format10x_odex247.getTree());

					}
					break;
				case 4 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:812:5: insn_format11n
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format11n_in_instruction3822);
					insn_format11n248=insn_format11n();
					state._fsp--;

					adaptor.addChild(root_0, insn_format11n248.getTree());

					}
					break;
				case 5 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:813:5: insn_format11x
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format11x_in_instruction3828);
					insn_format11x249=insn_format11x();
					state._fsp--;

					adaptor.addChild(root_0, insn_format11x249.getTree());

					}
					break;
				case 6 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:814:5: insn_format12x
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format12x_in_instruction3834);
					insn_format12x250=insn_format12x();
					state._fsp--;

					adaptor.addChild(root_0, insn_format12x250.getTree());

					}
					break;
				case 7 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:815:5: insn_format20bc
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format20bc_in_instruction3840);
					insn_format20bc251=insn_format20bc();
					state._fsp--;

					adaptor.addChild(root_0, insn_format20bc251.getTree());

					}
					break;
				case 8 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:816:5: insn_format20t
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format20t_in_instruction3846);
					insn_format20t252=insn_format20t();
					state._fsp--;

					adaptor.addChild(root_0, insn_format20t252.getTree());

					}
					break;
				case 9 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:817:5: insn_format21c_field
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21c_field_in_instruction3852);
					insn_format21c_field253=insn_format21c_field();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21c_field253.getTree());

					}
					break;
				case 10 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:818:5: insn_format21c_field_odex
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21c_field_odex_in_instruction3858);
					insn_format21c_field_odex254=insn_format21c_field_odex();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21c_field_odex254.getTree());

					}
					break;
				case 11 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:819:5: insn_format21c_string
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21c_string_in_instruction3864);
					insn_format21c_string255=insn_format21c_string();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21c_string255.getTree());

					}
					break;
				case 12 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:820:5: insn_format21c_type
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21c_type_in_instruction3870);
					insn_format21c_type256=insn_format21c_type();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21c_type256.getTree());

					}
					break;
				case 13 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:821:5: insn_format21c_lambda
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21c_lambda_in_instruction3876);
					insn_format21c_lambda257=insn_format21c_lambda();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21c_lambda257.getTree());

					}
					break;
				case 14 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:822:5: insn_format21c_method
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21c_method_in_instruction3882);
					insn_format21c_method258=insn_format21c_method();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21c_method258.getTree());

					}
					break;
				case 15 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:823:5: insn_format21ih
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21ih_in_instruction3888);
					insn_format21ih259=insn_format21ih();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21ih259.getTree());

					}
					break;
				case 16 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:824:5: insn_format21lh
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21lh_in_instruction3894);
					insn_format21lh260=insn_format21lh();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21lh260.getTree());

					}
					break;
				case 17 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:825:5: insn_format21s
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21s_in_instruction3900);
					insn_format21s261=insn_format21s();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21s261.getTree());

					}
					break;
				case 18 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:826:5: insn_format21t
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21t_in_instruction3906);
					insn_format21t262=insn_format21t();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21t262.getTree());

					}
					break;
				case 19 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:827:5: insn_format22b
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22b_in_instruction3912);
					insn_format22b263=insn_format22b();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22b263.getTree());

					}
					break;
				case 20 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:828:5: insn_format22c_field
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22c_field_in_instruction3918);
					insn_format22c_field264=insn_format22c_field();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22c_field264.getTree());

					}
					break;
				case 21 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:829:5: insn_format22c_field_odex
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22c_field_odex_in_instruction3924);
					insn_format22c_field_odex265=insn_format22c_field_odex();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22c_field_odex265.getTree());

					}
					break;
				case 22 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:830:5: insn_format22c_type
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22c_type_in_instruction3930);
					insn_format22c_type266=insn_format22c_type();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22c_type266.getTree());

					}
					break;
				case 23 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:831:5: insn_format22c_string
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22c_string_in_instruction3936);
					insn_format22c_string267=insn_format22c_string();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22c_string267.getTree());

					}
					break;
				case 24 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:832:5: insn_format22cs_field
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22cs_field_in_instruction3942);
					insn_format22cs_field268=insn_format22cs_field();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22cs_field268.getTree());

					}
					break;
				case 25 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:833:5: insn_format22s
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22s_in_instruction3948);
					insn_format22s269=insn_format22s();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22s269.getTree());

					}
					break;
				case 26 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:834:5: insn_format22t
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22t_in_instruction3954);
					insn_format22t270=insn_format22t();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22t270.getTree());

					}
					break;
				case 27 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:835:5: insn_format22x
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22x_in_instruction3960);
					insn_format22x271=insn_format22x();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22x271.getTree());

					}
					break;
				case 28 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:836:5: insn_format23x
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format23x_in_instruction3966);
					insn_format23x272=insn_format23x();
					state._fsp--;

					adaptor.addChild(root_0, insn_format23x272.getTree());

					}
					break;
				case 29 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:837:5: insn_format25x
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format25x_in_instruction3972);
					insn_format25x273=insn_format25x();
					state._fsp--;

					adaptor.addChild(root_0, insn_format25x273.getTree());

					}
					break;
				case 30 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:838:5: insn_format30t
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format30t_in_instruction3978);
					insn_format30t274=insn_format30t();
					state._fsp--;

					adaptor.addChild(root_0, insn_format30t274.getTree());

					}
					break;
				case 31 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:839:5: insn_format31c
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format31c_in_instruction3984);
					insn_format31c275=insn_format31c();
					state._fsp--;

					adaptor.addChild(root_0, insn_format31c275.getTree());

					}
					break;
				case 32 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:840:5: insn_format31i
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format31i_in_instruction3990);
					insn_format31i276=insn_format31i();
					state._fsp--;

					adaptor.addChild(root_0, insn_format31i276.getTree());

					}
					break;
				case 33 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:841:5: insn_format31t
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format31t_in_instruction3996);
					insn_format31t277=insn_format31t();
					state._fsp--;

					adaptor.addChild(root_0, insn_format31t277.getTree());

					}
					break;
				case 34 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:842:5: insn_format32x
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format32x_in_instruction4002);
					insn_format32x278=insn_format32x();
					state._fsp--;

					adaptor.addChild(root_0, insn_format32x278.getTree());

					}
					break;
				case 35 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:843:5: insn_format35c_method
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format35c_method_in_instruction4008);
					insn_format35c_method279=insn_format35c_method();
					state._fsp--;

					adaptor.addChild(root_0, insn_format35c_method279.getTree());

					}
					break;
				case 36 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:844:5: insn_format35c_type
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format35c_type_in_instruction4014);
					insn_format35c_type280=insn_format35c_type();
					state._fsp--;

					adaptor.addChild(root_0, insn_format35c_type280.getTree());

					}
					break;
				case 37 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:845:5: insn_format35c_method_odex
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format35c_method_odex_in_instruction4020);
					insn_format35c_method_odex281=insn_format35c_method_odex();
					state._fsp--;

					adaptor.addChild(root_0, insn_format35c_method_odex281.getTree());

					}
					break;
				case 38 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:846:5: insn_format35mi_method
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format35mi_method_in_instruction4026);
					insn_format35mi_method282=insn_format35mi_method();
					state._fsp--;

					adaptor.addChild(root_0, insn_format35mi_method282.getTree());

					}
					break;
				case 39 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:847:5: insn_format35ms_method
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format35ms_method_in_instruction4032);
					insn_format35ms_method283=insn_format35ms_method();
					state._fsp--;

					adaptor.addChild(root_0, insn_format35ms_method283.getTree());

					}
					break;
				case 40 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:848:5: insn_format3rc_method
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format3rc_method_in_instruction4038);
					insn_format3rc_method284=insn_format3rc_method();
					state._fsp--;

					adaptor.addChild(root_0, insn_format3rc_method284.getTree());

					}
					break;
				case 41 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:849:5: insn_format3rc_method_odex
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format3rc_method_odex_in_instruction4044);
					insn_format3rc_method_odex285=insn_format3rc_method_odex();
					state._fsp--;

					adaptor.addChild(root_0, insn_format3rc_method_odex285.getTree());

					}
					break;
				case 42 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:850:5: insn_format3rc_type
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format3rc_type_in_instruction4050);
					insn_format3rc_type286=insn_format3rc_type();
					state._fsp--;

					adaptor.addChild(root_0, insn_format3rc_type286.getTree());

					}
					break;
				case 43 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:851:5: insn_format3rmi_method
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format3rmi_method_in_instruction4056);
					insn_format3rmi_method287=insn_format3rmi_method();
					state._fsp--;

					adaptor.addChild(root_0, insn_format3rmi_method287.getTree());

					}
					break;
				case 44 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:852:5: insn_format3rms_method
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format3rms_method_in_instruction4062);
					insn_format3rms_method288=insn_format3rms_method();
					state._fsp--;

					adaptor.addChild(root_0, insn_format3rms_method288.getTree());

					}
					break;
				case 45 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:853:5: insn_format51l
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format51l_in_instruction4068);
					insn_format51l289=insn_format51l();
					state._fsp--;

					adaptor.addChild(root_0, insn_format51l289.getTree());

					}
					break;
				case 46 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:854:5: insn_array_data_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_array_data_directive_in_instruction4074);
					insn_array_data_directive290=insn_array_data_directive();
					state._fsp--;

					adaptor.addChild(root_0, insn_array_data_directive290.getTree());

					}
					break;
				case 47 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:855:5: insn_packed_switch_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_packed_switch_directive_in_instruction4080);
					insn_packed_switch_directive291=insn_packed_switch_directive();
					state._fsp--;

					adaptor.addChild(root_0, insn_packed_switch_directive291.getTree());

					}
					break;
				case 48 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:856:5: insn_sparse_switch_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_sparse_switch_directive_in_instruction4086);
					insn_sparse_switch_directive292=insn_sparse_switch_directive();
					state._fsp--;

					adaptor.addChild(root_0, insn_sparse_switch_directive292.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "instruction"


	public static class insn_format10t_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format10t"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:858:1: insn_format10t : INSTRUCTION_FORMAT10t label_ref -> ^( I_STATEMENT_FORMAT10t[$start, \"I_STATEMENT_FORMAT10t\"] INSTRUCTION_FORMAT10t label_ref ) ;
	public final smaliParser.insn_format10t_return insn_format10t() throws RecognitionException {
		smaliParser.insn_format10t_return retval = new smaliParser.insn_format10t_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT10t293=null;
		ParserRuleReturnScope label_ref294 =null;

		CommonTree INSTRUCTION_FORMAT10t293_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT10t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT10t");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:859:3: ( INSTRUCTION_FORMAT10t label_ref -> ^( I_STATEMENT_FORMAT10t[$start, \"I_STATEMENT_FORMAT10t\"] INSTRUCTION_FORMAT10t label_ref ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:861:5: INSTRUCTION_FORMAT10t label_ref
			{
			INSTRUCTION_FORMAT10t293=(Token)match(input,INSTRUCTION_FORMAT10t,FOLLOW_INSTRUCTION_FORMAT10t_in_insn_format10t4106);  
			stream_INSTRUCTION_FORMAT10t.add(INSTRUCTION_FORMAT10t293);

			pushFollow(FOLLOW_label_ref_in_insn_format10t4108);
			label_ref294=label_ref();
			state._fsp--;

			stream_label_ref.add(label_ref294.getTree());
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT10t, label_ref
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 862:5: -> ^( I_STATEMENT_FORMAT10t[$start, \"I_STATEMENT_FORMAT10t\"] INSTRUCTION_FORMAT10t label_ref )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:862:8: ^( I_STATEMENT_FORMAT10t[$start, \"I_STATEMENT_FORMAT10t\"] INSTRUCTION_FORMAT10t label_ref )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT10t, (retval.start), "I_STATEMENT_FORMAT10t"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT10t.nextNode());
				adaptor.addChild(root_1, stream_label_ref.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format10t"


	public static class insn_format10x_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format10x"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:864:1: insn_format10x : INSTRUCTION_FORMAT10x -> ^( I_STATEMENT_FORMAT10x[$start, \"I_STATEMENT_FORMAT10x\"] INSTRUCTION_FORMAT10x ) ;
	public final smaliParser.insn_format10x_return insn_format10x() throws RecognitionException {
		smaliParser.insn_format10x_return retval = new smaliParser.insn_format10x_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT10x295=null;

		CommonTree INSTRUCTION_FORMAT10x295_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT10x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT10x");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:865:3: ( INSTRUCTION_FORMAT10x -> ^( I_STATEMENT_FORMAT10x[$start, \"I_STATEMENT_FORMAT10x\"] INSTRUCTION_FORMAT10x ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:866:5: INSTRUCTION_FORMAT10x
			{
			INSTRUCTION_FORMAT10x295=(Token)match(input,INSTRUCTION_FORMAT10x,FOLLOW_INSTRUCTION_FORMAT10x_in_insn_format10x4138);  
			stream_INSTRUCTION_FORMAT10x.add(INSTRUCTION_FORMAT10x295);

			// AST REWRITE
			// elements: INSTRUCTION_FORMAT10x
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 867:5: -> ^( I_STATEMENT_FORMAT10x[$start, \"I_STATEMENT_FORMAT10x\"] INSTRUCTION_FORMAT10x )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:867:8: ^( I_STATEMENT_FORMAT10x[$start, \"I_STATEMENT_FORMAT10x\"] INSTRUCTION_FORMAT10x )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT10x, (retval.start), "I_STATEMENT_FORMAT10x"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT10x.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format10x"


	public static class insn_format10x_odex_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format10x_odex"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:869:1: insn_format10x_odex : INSTRUCTION_FORMAT10x_ODEX ;
	public final smaliParser.insn_format10x_odex_return insn_format10x_odex() throws RecognitionException {
		smaliParser.insn_format10x_odex_return retval = new smaliParser.insn_format10x_odex_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT10x_ODEX296=null;

		CommonTree INSTRUCTION_FORMAT10x_ODEX296_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:870:3: ( INSTRUCTION_FORMAT10x_ODEX )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:871:5: INSTRUCTION_FORMAT10x_ODEX
			{
			root_0 = (CommonTree)adaptor.nil();


			INSTRUCTION_FORMAT10x_ODEX296=(Token)match(input,INSTRUCTION_FORMAT10x_ODEX,FOLLOW_INSTRUCTION_FORMAT10x_ODEX_in_insn_format10x_odex4166); 
			INSTRUCTION_FORMAT10x_ODEX296_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT10x_ODEX296);
			adaptor.addChild(root_0, INSTRUCTION_FORMAT10x_ODEX296_tree);


			      throwOdexedInstructionException(input, (INSTRUCTION_FORMAT10x_ODEX296!=null?INSTRUCTION_FORMAT10x_ODEX296.getText():null));
			    
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format10x_odex"


	public static class insn_format11n_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format11n"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:876:1: insn_format11n : INSTRUCTION_FORMAT11n REGISTER COMMA integral_literal -> ^( I_STATEMENT_FORMAT11n[$start, \"I_STATEMENT_FORMAT11n\"] INSTRUCTION_FORMAT11n REGISTER integral_literal ) ;
	public final smaliParser.insn_format11n_return insn_format11n() throws RecognitionException {
		smaliParser.insn_format11n_return retval = new smaliParser.insn_format11n_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT11n297=null;
		Token REGISTER298=null;
		Token COMMA299=null;
		ParserRuleReturnScope integral_literal300 =null;

		CommonTree INSTRUCTION_FORMAT11n297_tree=null;
		CommonTree REGISTER298_tree=null;
		CommonTree COMMA299_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT11n=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT11n");
		RewriteRuleSubtreeStream stream_integral_literal=new RewriteRuleSubtreeStream(adaptor,"rule integral_literal");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:877:3: ( INSTRUCTION_FORMAT11n REGISTER COMMA integral_literal -> ^( I_STATEMENT_FORMAT11n[$start, \"I_STATEMENT_FORMAT11n\"] INSTRUCTION_FORMAT11n REGISTER integral_literal ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:878:5: INSTRUCTION_FORMAT11n REGISTER COMMA integral_literal
			{
			INSTRUCTION_FORMAT11n297=(Token)match(input,INSTRUCTION_FORMAT11n,FOLLOW_INSTRUCTION_FORMAT11n_in_insn_format11n4187);  
			stream_INSTRUCTION_FORMAT11n.add(INSTRUCTION_FORMAT11n297);

			REGISTER298=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format11n4189);  
			stream_REGISTER.add(REGISTER298);

			COMMA299=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format11n4191);  
			stream_COMMA.add(COMMA299);

			pushFollow(FOLLOW_integral_literal_in_insn_format11n4193);
			integral_literal300=integral_literal();
			state._fsp--;

			stream_integral_literal.add(integral_literal300.getTree());
			// AST REWRITE
			// elements: integral_literal, INSTRUCTION_FORMAT11n, REGISTER
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 879:5: -> ^( I_STATEMENT_FORMAT11n[$start, \"I_STATEMENT_FORMAT11n\"] INSTRUCTION_FORMAT11n REGISTER integral_literal )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:879:8: ^( I_STATEMENT_FORMAT11n[$start, \"I_STATEMENT_FORMAT11n\"] INSTRUCTION_FORMAT11n REGISTER integral_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT11n, (retval.start), "I_STATEMENT_FORMAT11n"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT11n.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_integral_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format11n"


	public static class insn_format11x_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format11x"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:881:1: insn_format11x : INSTRUCTION_FORMAT11x REGISTER -> ^( I_STATEMENT_FORMAT11x[$start, \"I_STATEMENT_FORMAT11x\"] INSTRUCTION_FORMAT11x REGISTER ) ;
	public final smaliParser.insn_format11x_return insn_format11x() throws RecognitionException {
		smaliParser.insn_format11x_return retval = new smaliParser.insn_format11x_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT11x301=null;
		Token REGISTER302=null;

		CommonTree INSTRUCTION_FORMAT11x301_tree=null;
		CommonTree REGISTER302_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT11x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT11x");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:882:3: ( INSTRUCTION_FORMAT11x REGISTER -> ^( I_STATEMENT_FORMAT11x[$start, \"I_STATEMENT_FORMAT11x\"] INSTRUCTION_FORMAT11x REGISTER ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:883:5: INSTRUCTION_FORMAT11x REGISTER
			{
			INSTRUCTION_FORMAT11x301=(Token)match(input,INSTRUCTION_FORMAT11x,FOLLOW_INSTRUCTION_FORMAT11x_in_insn_format11x4225);  
			stream_INSTRUCTION_FORMAT11x.add(INSTRUCTION_FORMAT11x301);

			REGISTER302=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format11x4227);  
			stream_REGISTER.add(REGISTER302);

			// AST REWRITE
			// elements: INSTRUCTION_FORMAT11x, REGISTER
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 884:5: -> ^( I_STATEMENT_FORMAT11x[$start, \"I_STATEMENT_FORMAT11x\"] INSTRUCTION_FORMAT11x REGISTER )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:884:8: ^( I_STATEMENT_FORMAT11x[$start, \"I_STATEMENT_FORMAT11x\"] INSTRUCTION_FORMAT11x REGISTER )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT11x, (retval.start), "I_STATEMENT_FORMAT11x"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT11x.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format11x"


	public static class insn_format12x_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format12x"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:886:1: insn_format12x : instruction_format12x REGISTER COMMA REGISTER -> ^( I_STATEMENT_FORMAT12x[$start, \"I_STATEMENT_FORMAT12x\"] instruction_format12x REGISTER REGISTER ) ;
	public final smaliParser.insn_format12x_return insn_format12x() throws RecognitionException {
		smaliParser.insn_format12x_return retval = new smaliParser.insn_format12x_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token REGISTER304=null;
		Token COMMA305=null;
		Token REGISTER306=null;
		ParserRuleReturnScope instruction_format12x303 =null;

		CommonTree REGISTER304_tree=null;
		CommonTree COMMA305_tree=null;
		CommonTree REGISTER306_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_instruction_format12x=new RewriteRuleSubtreeStream(adaptor,"rule instruction_format12x");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:887:3: ( instruction_format12x REGISTER COMMA REGISTER -> ^( I_STATEMENT_FORMAT12x[$start, \"I_STATEMENT_FORMAT12x\"] instruction_format12x REGISTER REGISTER ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:888:5: instruction_format12x REGISTER COMMA REGISTER
			{
			pushFollow(FOLLOW_instruction_format12x_in_insn_format12x4257);
			instruction_format12x303=instruction_format12x();
			state._fsp--;

			stream_instruction_format12x.add(instruction_format12x303.getTree());
			REGISTER304=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format12x4259);  
			stream_REGISTER.add(REGISTER304);

			COMMA305=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format12x4261);  
			stream_COMMA.add(COMMA305);

			REGISTER306=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format12x4263);  
			stream_REGISTER.add(REGISTER306);

			// AST REWRITE
			// elements: instruction_format12x, REGISTER, REGISTER
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 889:5: -> ^( I_STATEMENT_FORMAT12x[$start, \"I_STATEMENT_FORMAT12x\"] instruction_format12x REGISTER REGISTER )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:889:8: ^( I_STATEMENT_FORMAT12x[$start, \"I_STATEMENT_FORMAT12x\"] instruction_format12x REGISTER REGISTER )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT12x, (retval.start), "I_STATEMENT_FORMAT12x"), root_1);
				adaptor.addChild(root_1, stream_instruction_format12x.nextTree());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format12x"


	public static class insn_format20bc_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format20bc"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:891:1: insn_format20bc : INSTRUCTION_FORMAT20bc VERIFICATION_ERROR_TYPE COMMA verification_error_reference -> ^( I_STATEMENT_FORMAT20bc INSTRUCTION_FORMAT20bc VERIFICATION_ERROR_TYPE verification_error_reference ) ;
	public final smaliParser.insn_format20bc_return insn_format20bc() throws RecognitionException {
		smaliParser.insn_format20bc_return retval = new smaliParser.insn_format20bc_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT20bc307=null;
		Token VERIFICATION_ERROR_TYPE308=null;
		Token COMMA309=null;
		ParserRuleReturnScope verification_error_reference310 =null;

		CommonTree INSTRUCTION_FORMAT20bc307_tree=null;
		CommonTree VERIFICATION_ERROR_TYPE308_tree=null;
		CommonTree COMMA309_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT20bc=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT20bc");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_VERIFICATION_ERROR_TYPE=new RewriteRuleTokenStream(adaptor,"token VERIFICATION_ERROR_TYPE");
		RewriteRuleSubtreeStream stream_verification_error_reference=new RewriteRuleSubtreeStream(adaptor,"rule verification_error_reference");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:892:3: ( INSTRUCTION_FORMAT20bc VERIFICATION_ERROR_TYPE COMMA verification_error_reference -> ^( I_STATEMENT_FORMAT20bc INSTRUCTION_FORMAT20bc VERIFICATION_ERROR_TYPE verification_error_reference ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:893:5: INSTRUCTION_FORMAT20bc VERIFICATION_ERROR_TYPE COMMA verification_error_reference
			{
			INSTRUCTION_FORMAT20bc307=(Token)match(input,INSTRUCTION_FORMAT20bc,FOLLOW_INSTRUCTION_FORMAT20bc_in_insn_format20bc4295);  
			stream_INSTRUCTION_FORMAT20bc.add(INSTRUCTION_FORMAT20bc307);

			VERIFICATION_ERROR_TYPE308=(Token)match(input,VERIFICATION_ERROR_TYPE,FOLLOW_VERIFICATION_ERROR_TYPE_in_insn_format20bc4297);  
			stream_VERIFICATION_ERROR_TYPE.add(VERIFICATION_ERROR_TYPE308);

			COMMA309=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format20bc4299);  
			stream_COMMA.add(COMMA309);

			pushFollow(FOLLOW_verification_error_reference_in_insn_format20bc4301);
			verification_error_reference310=verification_error_reference();
			state._fsp--;

			stream_verification_error_reference.add(verification_error_reference310.getTree());

			      if (!allowOdex || opcodes.getOpcodeByName((INSTRUCTION_FORMAT20bc307!=null?INSTRUCTION_FORMAT20bc307.getText():null)) == null || apiLevel >= 14) {
			        throwOdexedInstructionException(input, (INSTRUCTION_FORMAT20bc307!=null?INSTRUCTION_FORMAT20bc307.getText():null));
			      }
			    
			// AST REWRITE
			// elements: verification_error_reference, INSTRUCTION_FORMAT20bc, VERIFICATION_ERROR_TYPE
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 899:5: -> ^( I_STATEMENT_FORMAT20bc INSTRUCTION_FORMAT20bc VERIFICATION_ERROR_TYPE verification_error_reference )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:899:8: ^( I_STATEMENT_FORMAT20bc INSTRUCTION_FORMAT20bc VERIFICATION_ERROR_TYPE verification_error_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT20bc, "I_STATEMENT_FORMAT20bc"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT20bc.nextNode());
				adaptor.addChild(root_1, stream_VERIFICATION_ERROR_TYPE.nextNode());
				adaptor.addChild(root_1, stream_verification_error_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format20bc"


	public static class insn_format20t_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format20t"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:901:1: insn_format20t : INSTRUCTION_FORMAT20t label_ref -> ^( I_STATEMENT_FORMAT20t[$start, \"I_STATEMENT_FORMAT20t\"] INSTRUCTION_FORMAT20t label_ref ) ;
	public final smaliParser.insn_format20t_return insn_format20t() throws RecognitionException {
		smaliParser.insn_format20t_return retval = new smaliParser.insn_format20t_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT20t311=null;
		ParserRuleReturnScope label_ref312 =null;

		CommonTree INSTRUCTION_FORMAT20t311_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT20t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT20t");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:902:3: ( INSTRUCTION_FORMAT20t label_ref -> ^( I_STATEMENT_FORMAT20t[$start, \"I_STATEMENT_FORMAT20t\"] INSTRUCTION_FORMAT20t label_ref ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:903:5: INSTRUCTION_FORMAT20t label_ref
			{
			INSTRUCTION_FORMAT20t311=(Token)match(input,INSTRUCTION_FORMAT20t,FOLLOW_INSTRUCTION_FORMAT20t_in_insn_format20t4338);  
			stream_INSTRUCTION_FORMAT20t.add(INSTRUCTION_FORMAT20t311);

			pushFollow(FOLLOW_label_ref_in_insn_format20t4340);
			label_ref312=label_ref();
			state._fsp--;

			stream_label_ref.add(label_ref312.getTree());
			// AST REWRITE
			// elements: label_ref, INSTRUCTION_FORMAT20t
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 904:5: -> ^( I_STATEMENT_FORMAT20t[$start, \"I_STATEMENT_FORMAT20t\"] INSTRUCTION_FORMAT20t label_ref )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:904:8: ^( I_STATEMENT_FORMAT20t[$start, \"I_STATEMENT_FORMAT20t\"] INSTRUCTION_FORMAT20t label_ref )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT20t, (retval.start), "I_STATEMENT_FORMAT20t"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT20t.nextNode());
				adaptor.addChild(root_1, stream_label_ref.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format20t"


	public static class insn_format21c_field_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21c_field"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:906:1: insn_format21c_field : INSTRUCTION_FORMAT21c_FIELD REGISTER COMMA field_reference -> ^( I_STATEMENT_FORMAT21c_FIELD[$start, \"I_STATEMENT_FORMAT21c_FIELD\"] INSTRUCTION_FORMAT21c_FIELD REGISTER field_reference ) ;
	public final smaliParser.insn_format21c_field_return insn_format21c_field() throws RecognitionException {
		smaliParser.insn_format21c_field_return retval = new smaliParser.insn_format21c_field_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21c_FIELD313=null;
		Token REGISTER314=null;
		Token COMMA315=null;
		ParserRuleReturnScope field_reference316 =null;

		CommonTree INSTRUCTION_FORMAT21c_FIELD313_tree=null;
		CommonTree REGISTER314_tree=null;
		CommonTree COMMA315_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_FIELD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_FIELD");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_field_reference=new RewriteRuleSubtreeStream(adaptor,"rule field_reference");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:907:3: ( INSTRUCTION_FORMAT21c_FIELD REGISTER COMMA field_reference -> ^( I_STATEMENT_FORMAT21c_FIELD[$start, \"I_STATEMENT_FORMAT21c_FIELD\"] INSTRUCTION_FORMAT21c_FIELD REGISTER field_reference ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:908:5: INSTRUCTION_FORMAT21c_FIELD REGISTER COMMA field_reference
			{
			INSTRUCTION_FORMAT21c_FIELD313=(Token)match(input,INSTRUCTION_FORMAT21c_FIELD,FOLLOW_INSTRUCTION_FORMAT21c_FIELD_in_insn_format21c_field4370);  
			stream_INSTRUCTION_FORMAT21c_FIELD.add(INSTRUCTION_FORMAT21c_FIELD313);

			REGISTER314=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_field4372);  
			stream_REGISTER.add(REGISTER314);

			COMMA315=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21c_field4374);  
			stream_COMMA.add(COMMA315);

			pushFollow(FOLLOW_field_reference_in_insn_format21c_field4376);
			field_reference316=field_reference();
			state._fsp--;

			stream_field_reference.add(field_reference316.getTree());
			// AST REWRITE
			// elements: field_reference, REGISTER, INSTRUCTION_FORMAT21c_FIELD
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 909:5: -> ^( I_STATEMENT_FORMAT21c_FIELD[$start, \"I_STATEMENT_FORMAT21c_FIELD\"] INSTRUCTION_FORMAT21c_FIELD REGISTER field_reference )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:909:8: ^( I_STATEMENT_FORMAT21c_FIELD[$start, \"I_STATEMENT_FORMAT21c_FIELD\"] INSTRUCTION_FORMAT21c_FIELD REGISTER field_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21c_FIELD, (retval.start), "I_STATEMENT_FORMAT21c_FIELD"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_FIELD.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_field_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21c_field"


	public static class insn_format21c_field_odex_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21c_field_odex"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:911:1: insn_format21c_field_odex : INSTRUCTION_FORMAT21c_FIELD_ODEX REGISTER COMMA field_reference -> ^( I_STATEMENT_FORMAT21c_FIELD[$start, \"I_STATEMENT_FORMAT21c_FIELD\"] INSTRUCTION_FORMAT21c_FIELD_ODEX REGISTER field_reference ) ;
	public final smaliParser.insn_format21c_field_odex_return insn_format21c_field_odex() throws RecognitionException {
		smaliParser.insn_format21c_field_odex_return retval = new smaliParser.insn_format21c_field_odex_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21c_FIELD_ODEX317=null;
		Token REGISTER318=null;
		Token COMMA319=null;
		ParserRuleReturnScope field_reference320 =null;

		CommonTree INSTRUCTION_FORMAT21c_FIELD_ODEX317_tree=null;
		CommonTree REGISTER318_tree=null;
		CommonTree COMMA319_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_FIELD_ODEX=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_FIELD_ODEX");
		RewriteRuleSubtreeStream stream_field_reference=new RewriteRuleSubtreeStream(adaptor,"rule field_reference");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:912:3: ( INSTRUCTION_FORMAT21c_FIELD_ODEX REGISTER COMMA field_reference -> ^( I_STATEMENT_FORMAT21c_FIELD[$start, \"I_STATEMENT_FORMAT21c_FIELD\"] INSTRUCTION_FORMAT21c_FIELD_ODEX REGISTER field_reference ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:913:5: INSTRUCTION_FORMAT21c_FIELD_ODEX REGISTER COMMA field_reference
			{
			INSTRUCTION_FORMAT21c_FIELD_ODEX317=(Token)match(input,INSTRUCTION_FORMAT21c_FIELD_ODEX,FOLLOW_INSTRUCTION_FORMAT21c_FIELD_ODEX_in_insn_format21c_field_odex4408);  
			stream_INSTRUCTION_FORMAT21c_FIELD_ODEX.add(INSTRUCTION_FORMAT21c_FIELD_ODEX317);

			REGISTER318=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_field_odex4410);  
			stream_REGISTER.add(REGISTER318);

			COMMA319=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21c_field_odex4412);  
			stream_COMMA.add(COMMA319);

			pushFollow(FOLLOW_field_reference_in_insn_format21c_field_odex4414);
			field_reference320=field_reference();
			state._fsp--;

			stream_field_reference.add(field_reference320.getTree());

			      if (!allowOdex || opcodes.getOpcodeByName((INSTRUCTION_FORMAT21c_FIELD_ODEX317!=null?INSTRUCTION_FORMAT21c_FIELD_ODEX317.getText():null)) == null || apiLevel >= 14) {
			        throwOdexedInstructionException(input, (INSTRUCTION_FORMAT21c_FIELD_ODEX317!=null?INSTRUCTION_FORMAT21c_FIELD_ODEX317.getText():null));
			      }
			    
			// AST REWRITE
			// elements: REGISTER, field_reference, INSTRUCTION_FORMAT21c_FIELD_ODEX
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 919:5: -> ^( I_STATEMENT_FORMAT21c_FIELD[$start, \"I_STATEMENT_FORMAT21c_FIELD\"] INSTRUCTION_FORMAT21c_FIELD_ODEX REGISTER field_reference )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:919:8: ^( I_STATEMENT_FORMAT21c_FIELD[$start, \"I_STATEMENT_FORMAT21c_FIELD\"] INSTRUCTION_FORMAT21c_FIELD_ODEX REGISTER field_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21c_FIELD, (retval.start), "I_STATEMENT_FORMAT21c_FIELD"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_FIELD_ODEX.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_field_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21c_field_odex"


	public static class insn_format21c_string_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21c_string"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:921:1: insn_format21c_string : INSTRUCTION_FORMAT21c_STRING REGISTER COMMA STRING_LITERAL -> ^( I_STATEMENT_FORMAT21c_STRING[$start, \"I_STATEMENT_FORMAT21c_STRING\"] INSTRUCTION_FORMAT21c_STRING REGISTER STRING_LITERAL ) ;
	public final smaliParser.insn_format21c_string_return insn_format21c_string() throws RecognitionException {
		smaliParser.insn_format21c_string_return retval = new smaliParser.insn_format21c_string_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21c_STRING321=null;
		Token REGISTER322=null;
		Token COMMA323=null;
		Token STRING_LITERAL324=null;

		CommonTree INSTRUCTION_FORMAT21c_STRING321_tree=null;
		CommonTree REGISTER322_tree=null;
		CommonTree COMMA323_tree=null;
		CommonTree STRING_LITERAL324_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_STRING=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_STRING");
		RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:922:3: ( INSTRUCTION_FORMAT21c_STRING REGISTER COMMA STRING_LITERAL -> ^( I_STATEMENT_FORMAT21c_STRING[$start, \"I_STATEMENT_FORMAT21c_STRING\"] INSTRUCTION_FORMAT21c_STRING REGISTER STRING_LITERAL ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:923:5: INSTRUCTION_FORMAT21c_STRING REGISTER COMMA STRING_LITERAL
			{
			INSTRUCTION_FORMAT21c_STRING321=(Token)match(input,INSTRUCTION_FORMAT21c_STRING,FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_insn_format21c_string4452);  
			stream_INSTRUCTION_FORMAT21c_STRING.add(INSTRUCTION_FORMAT21c_STRING321);

			REGISTER322=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_string4454);  
			stream_REGISTER.add(REGISTER322);

			COMMA323=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21c_string4456);  
			stream_COMMA.add(COMMA323);

			STRING_LITERAL324=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_insn_format21c_string4458);  
			stream_STRING_LITERAL.add(STRING_LITERAL324);

			// AST REWRITE
			// elements: STRING_LITERAL, INSTRUCTION_FORMAT21c_STRING, REGISTER
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 924:5: -> ^( I_STATEMENT_FORMAT21c_STRING[$start, \"I_STATEMENT_FORMAT21c_STRING\"] INSTRUCTION_FORMAT21c_STRING REGISTER STRING_LITERAL )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:924:8: ^( I_STATEMENT_FORMAT21c_STRING[$start, \"I_STATEMENT_FORMAT21c_STRING\"] INSTRUCTION_FORMAT21c_STRING REGISTER STRING_LITERAL )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21c_STRING, (retval.start), "I_STATEMENT_FORMAT21c_STRING"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_STRING.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21c_string"


	public static class insn_format21c_type_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21c_type"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:926:1: insn_format21c_type : INSTRUCTION_FORMAT21c_TYPE REGISTER COMMA nonvoid_type_descriptor -> ^( I_STATEMENT_FORMAT21c_TYPE[$start, \"I_STATEMENT_FORMAT21c\"] INSTRUCTION_FORMAT21c_TYPE REGISTER nonvoid_type_descriptor ) ;
	public final smaliParser.insn_format21c_type_return insn_format21c_type() throws RecognitionException {
		smaliParser.insn_format21c_type_return retval = new smaliParser.insn_format21c_type_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21c_TYPE325=null;
		Token REGISTER326=null;
		Token COMMA327=null;
		ParserRuleReturnScope nonvoid_type_descriptor328 =null;

		CommonTree INSTRUCTION_FORMAT21c_TYPE325_tree=null;
		CommonTree REGISTER326_tree=null;
		CommonTree COMMA327_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_TYPE");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:927:3: ( INSTRUCTION_FORMAT21c_TYPE REGISTER COMMA nonvoid_type_descriptor -> ^( I_STATEMENT_FORMAT21c_TYPE[$start, \"I_STATEMENT_FORMAT21c\"] INSTRUCTION_FORMAT21c_TYPE REGISTER nonvoid_type_descriptor ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:928:5: INSTRUCTION_FORMAT21c_TYPE REGISTER COMMA nonvoid_type_descriptor
			{
			INSTRUCTION_FORMAT21c_TYPE325=(Token)match(input,INSTRUCTION_FORMAT21c_TYPE,FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_insn_format21c_type4490);  
			stream_INSTRUCTION_FORMAT21c_TYPE.add(INSTRUCTION_FORMAT21c_TYPE325);

			REGISTER326=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_type4492);  
			stream_REGISTER.add(REGISTER326);

			COMMA327=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21c_type4494);  
			stream_COMMA.add(COMMA327);

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format21c_type4496);
			nonvoid_type_descriptor328=nonvoid_type_descriptor();
			state._fsp--;

			stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor328.getTree());
			// AST REWRITE
			// elements: REGISTER, nonvoid_type_descriptor, INSTRUCTION_FORMAT21c_TYPE
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 929:5: -> ^( I_STATEMENT_FORMAT21c_TYPE[$start, \"I_STATEMENT_FORMAT21c\"] INSTRUCTION_FORMAT21c_TYPE REGISTER nonvoid_type_descriptor )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:929:8: ^( I_STATEMENT_FORMAT21c_TYPE[$start, \"I_STATEMENT_FORMAT21c\"] INSTRUCTION_FORMAT21c_TYPE REGISTER nonvoid_type_descriptor )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21c_TYPE, (retval.start), "I_STATEMENT_FORMAT21c"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_TYPE.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21c_type"


	public static class insn_format21c_lambda_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21c_lambda"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:931:1: insn_format21c_lambda : INSTRUCTION_FORMAT21c_LAMBDA REGISTER COMMA STRING_LITERAL -> ^( I_STATEMENT_FORMAT21c_LAMBDA[$start, \"I_STATEMENT_FORMAT21c_LAMBDA\"] INSTRUCTION_FORMAT21c_LAMBDA REGISTER STRING_LITERAL ) ;
	public final smaliParser.insn_format21c_lambda_return insn_format21c_lambda() throws RecognitionException {
		smaliParser.insn_format21c_lambda_return retval = new smaliParser.insn_format21c_lambda_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21c_LAMBDA329=null;
		Token REGISTER330=null;
		Token COMMA331=null;
		Token STRING_LITERAL332=null;

		CommonTree INSTRUCTION_FORMAT21c_LAMBDA329_tree=null;
		CommonTree REGISTER330_tree=null;
		CommonTree COMMA331_tree=null;
		CommonTree STRING_LITERAL332_tree=null;
		RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_LAMBDA=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_LAMBDA");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:932:3: ( INSTRUCTION_FORMAT21c_LAMBDA REGISTER COMMA STRING_LITERAL -> ^( I_STATEMENT_FORMAT21c_LAMBDA[$start, \"I_STATEMENT_FORMAT21c_LAMBDA\"] INSTRUCTION_FORMAT21c_LAMBDA REGISTER STRING_LITERAL ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:933:5: INSTRUCTION_FORMAT21c_LAMBDA REGISTER COMMA STRING_LITERAL
			{
			INSTRUCTION_FORMAT21c_LAMBDA329=(Token)match(input,INSTRUCTION_FORMAT21c_LAMBDA,FOLLOW_INSTRUCTION_FORMAT21c_LAMBDA_in_insn_format21c_lambda4528);  
			stream_INSTRUCTION_FORMAT21c_LAMBDA.add(INSTRUCTION_FORMAT21c_LAMBDA329);

			REGISTER330=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_lambda4530);  
			stream_REGISTER.add(REGISTER330);

			COMMA331=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21c_lambda4532);  
			stream_COMMA.add(COMMA331);

			STRING_LITERAL332=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_insn_format21c_lambda4534);  
			stream_STRING_LITERAL.add(STRING_LITERAL332);

			// AST REWRITE
			// elements: STRING_LITERAL, REGISTER, INSTRUCTION_FORMAT21c_LAMBDA
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 934:5: -> ^( I_STATEMENT_FORMAT21c_LAMBDA[$start, \"I_STATEMENT_FORMAT21c_LAMBDA\"] INSTRUCTION_FORMAT21c_LAMBDA REGISTER STRING_LITERAL )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:934:8: ^( I_STATEMENT_FORMAT21c_LAMBDA[$start, \"I_STATEMENT_FORMAT21c_LAMBDA\"] INSTRUCTION_FORMAT21c_LAMBDA REGISTER STRING_LITERAL )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21c_LAMBDA, (retval.start), "I_STATEMENT_FORMAT21c_LAMBDA"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_LAMBDA.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21c_lambda"


	public static class insn_format21c_method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21c_method"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:936:1: insn_format21c_method : INSTRUCTION_FORMAT21c_METHOD REGISTER COMMA method_reference -> ^( I_STATEMENT_FORMAT21c_METHOD[$start, \"I_STATEMENT_FORMAT21c_METHOD\"] INSTRUCTION_FORMAT21c_METHOD REGISTER method_reference ) ;
	public final smaliParser.insn_format21c_method_return insn_format21c_method() throws RecognitionException {
		smaliParser.insn_format21c_method_return retval = new smaliParser.insn_format21c_method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21c_METHOD333=null;
		Token REGISTER334=null;
		Token COMMA335=null;
		ParserRuleReturnScope method_reference336 =null;

		CommonTree INSTRUCTION_FORMAT21c_METHOD333_tree=null;
		CommonTree REGISTER334_tree=null;
		CommonTree COMMA335_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_METHOD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_METHOD");
		RewriteRuleSubtreeStream stream_method_reference=new RewriteRuleSubtreeStream(adaptor,"rule method_reference");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:937:3: ( INSTRUCTION_FORMAT21c_METHOD REGISTER COMMA method_reference -> ^( I_STATEMENT_FORMAT21c_METHOD[$start, \"I_STATEMENT_FORMAT21c_METHOD\"] INSTRUCTION_FORMAT21c_METHOD REGISTER method_reference ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:938:5: INSTRUCTION_FORMAT21c_METHOD REGISTER COMMA method_reference
			{
			INSTRUCTION_FORMAT21c_METHOD333=(Token)match(input,INSTRUCTION_FORMAT21c_METHOD,FOLLOW_INSTRUCTION_FORMAT21c_METHOD_in_insn_format21c_method4566);  
			stream_INSTRUCTION_FORMAT21c_METHOD.add(INSTRUCTION_FORMAT21c_METHOD333);

			REGISTER334=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_method4568);  
			stream_REGISTER.add(REGISTER334);

			COMMA335=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21c_method4570);  
			stream_COMMA.add(COMMA335);

			pushFollow(FOLLOW_method_reference_in_insn_format21c_method4572);
			method_reference336=method_reference();
			state._fsp--;

			stream_method_reference.add(method_reference336.getTree());
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT21c_METHOD, method_reference, REGISTER
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 939:5: -> ^( I_STATEMENT_FORMAT21c_METHOD[$start, \"I_STATEMENT_FORMAT21c_METHOD\"] INSTRUCTION_FORMAT21c_METHOD REGISTER method_reference )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:939:8: ^( I_STATEMENT_FORMAT21c_METHOD[$start, \"I_STATEMENT_FORMAT21c_METHOD\"] INSTRUCTION_FORMAT21c_METHOD REGISTER method_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21c_METHOD, (retval.start), "I_STATEMENT_FORMAT21c_METHOD"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_METHOD.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_method_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21c_method"


	public static class insn_format21ih_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21ih"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:941:1: insn_format21ih : INSTRUCTION_FORMAT21ih REGISTER COMMA fixed_32bit_literal -> ^( I_STATEMENT_FORMAT21ih[$start, \"I_STATEMENT_FORMAT21ih\"] INSTRUCTION_FORMAT21ih REGISTER fixed_32bit_literal ) ;
	public final smaliParser.insn_format21ih_return insn_format21ih() throws RecognitionException {
		smaliParser.insn_format21ih_return retval = new smaliParser.insn_format21ih_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21ih337=null;
		Token REGISTER338=null;
		Token COMMA339=null;
		ParserRuleReturnScope fixed_32bit_literal340 =null;

		CommonTree INSTRUCTION_FORMAT21ih337_tree=null;
		CommonTree REGISTER338_tree=null;
		CommonTree COMMA339_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21ih=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21ih");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_fixed_32bit_literal=new RewriteRuleSubtreeStream(adaptor,"rule fixed_32bit_literal");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:942:3: ( INSTRUCTION_FORMAT21ih REGISTER COMMA fixed_32bit_literal -> ^( I_STATEMENT_FORMAT21ih[$start, \"I_STATEMENT_FORMAT21ih\"] INSTRUCTION_FORMAT21ih REGISTER fixed_32bit_literal ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:943:5: INSTRUCTION_FORMAT21ih REGISTER COMMA fixed_32bit_literal
			{
			INSTRUCTION_FORMAT21ih337=(Token)match(input,INSTRUCTION_FORMAT21ih,FOLLOW_INSTRUCTION_FORMAT21ih_in_insn_format21ih4604);  
			stream_INSTRUCTION_FORMAT21ih.add(INSTRUCTION_FORMAT21ih337);

			REGISTER338=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21ih4606);  
			stream_REGISTER.add(REGISTER338);

			COMMA339=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21ih4608);  
			stream_COMMA.add(COMMA339);

			pushFollow(FOLLOW_fixed_32bit_literal_in_insn_format21ih4610);
			fixed_32bit_literal340=fixed_32bit_literal();
			state._fsp--;

			stream_fixed_32bit_literal.add(fixed_32bit_literal340.getTree());
			// AST REWRITE
			// elements: REGISTER, fixed_32bit_literal, INSTRUCTION_FORMAT21ih
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 944:5: -> ^( I_STATEMENT_FORMAT21ih[$start, \"I_STATEMENT_FORMAT21ih\"] INSTRUCTION_FORMAT21ih REGISTER fixed_32bit_literal )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:944:8: ^( I_STATEMENT_FORMAT21ih[$start, \"I_STATEMENT_FORMAT21ih\"] INSTRUCTION_FORMAT21ih REGISTER fixed_32bit_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21ih, (retval.start), "I_STATEMENT_FORMAT21ih"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21ih.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_fixed_32bit_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21ih"


	public static class insn_format21lh_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21lh"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:946:1: insn_format21lh : INSTRUCTION_FORMAT21lh REGISTER COMMA fixed_32bit_literal -> ^( I_STATEMENT_FORMAT21lh[$start, \"I_STATEMENT_FORMAT21lh\"] INSTRUCTION_FORMAT21lh REGISTER fixed_32bit_literal ) ;
	public final smaliParser.insn_format21lh_return insn_format21lh() throws RecognitionException {
		smaliParser.insn_format21lh_return retval = new smaliParser.insn_format21lh_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21lh341=null;
		Token REGISTER342=null;
		Token COMMA343=null;
		ParserRuleReturnScope fixed_32bit_literal344 =null;

		CommonTree INSTRUCTION_FORMAT21lh341_tree=null;
		CommonTree REGISTER342_tree=null;
		CommonTree COMMA343_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21lh=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21lh");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_fixed_32bit_literal=new RewriteRuleSubtreeStream(adaptor,"rule fixed_32bit_literal");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:947:3: ( INSTRUCTION_FORMAT21lh REGISTER COMMA fixed_32bit_literal -> ^( I_STATEMENT_FORMAT21lh[$start, \"I_STATEMENT_FORMAT21lh\"] INSTRUCTION_FORMAT21lh REGISTER fixed_32bit_literal ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:948:5: INSTRUCTION_FORMAT21lh REGISTER COMMA fixed_32bit_literal
			{
			INSTRUCTION_FORMAT21lh341=(Token)match(input,INSTRUCTION_FORMAT21lh,FOLLOW_INSTRUCTION_FORMAT21lh_in_insn_format21lh4642);  
			stream_INSTRUCTION_FORMAT21lh.add(INSTRUCTION_FORMAT21lh341);

			REGISTER342=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21lh4644);  
			stream_REGISTER.add(REGISTER342);

			COMMA343=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21lh4646);  
			stream_COMMA.add(COMMA343);

			pushFollow(FOLLOW_fixed_32bit_literal_in_insn_format21lh4648);
			fixed_32bit_literal344=fixed_32bit_literal();
			state._fsp--;

			stream_fixed_32bit_literal.add(fixed_32bit_literal344.getTree());
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT21lh, REGISTER, fixed_32bit_literal
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 949:5: -> ^( I_STATEMENT_FORMAT21lh[$start, \"I_STATEMENT_FORMAT21lh\"] INSTRUCTION_FORMAT21lh REGISTER fixed_32bit_literal )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:949:8: ^( I_STATEMENT_FORMAT21lh[$start, \"I_STATEMENT_FORMAT21lh\"] INSTRUCTION_FORMAT21lh REGISTER fixed_32bit_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21lh, (retval.start), "I_STATEMENT_FORMAT21lh"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21lh.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_fixed_32bit_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21lh"


	public static class insn_format21s_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21s"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:951:1: insn_format21s : INSTRUCTION_FORMAT21s REGISTER COMMA integral_literal -> ^( I_STATEMENT_FORMAT21s[$start, \"I_STATEMENT_FORMAT21s\"] INSTRUCTION_FORMAT21s REGISTER integral_literal ) ;
	public final smaliParser.insn_format21s_return insn_format21s() throws RecognitionException {
		smaliParser.insn_format21s_return retval = new smaliParser.insn_format21s_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21s345=null;
		Token REGISTER346=null;
		Token COMMA347=null;
		ParserRuleReturnScope integral_literal348 =null;

		CommonTree INSTRUCTION_FORMAT21s345_tree=null;
		CommonTree REGISTER346_tree=null;
		CommonTree COMMA347_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21s=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21s");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_integral_literal=new RewriteRuleSubtreeStream(adaptor,"rule integral_literal");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:952:3: ( INSTRUCTION_FORMAT21s REGISTER COMMA integral_literal -> ^( I_STATEMENT_FORMAT21s[$start, \"I_STATEMENT_FORMAT21s\"] INSTRUCTION_FORMAT21s REGISTER integral_literal ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:953:5: INSTRUCTION_FORMAT21s REGISTER COMMA integral_literal
			{
			INSTRUCTION_FORMAT21s345=(Token)match(input,INSTRUCTION_FORMAT21s,FOLLOW_INSTRUCTION_FORMAT21s_in_insn_format21s4680);  
			stream_INSTRUCTION_FORMAT21s.add(INSTRUCTION_FORMAT21s345);

			REGISTER346=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21s4682);  
			stream_REGISTER.add(REGISTER346);

			COMMA347=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21s4684);  
			stream_COMMA.add(COMMA347);

			pushFollow(FOLLOW_integral_literal_in_insn_format21s4686);
			integral_literal348=integral_literal();
			state._fsp--;

			stream_integral_literal.add(integral_literal348.getTree());
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT21s, integral_literal, REGISTER
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 954:5: -> ^( I_STATEMENT_FORMAT21s[$start, \"I_STATEMENT_FORMAT21s\"] INSTRUCTION_FORMAT21s REGISTER integral_literal )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:954:8: ^( I_STATEMENT_FORMAT21s[$start, \"I_STATEMENT_FORMAT21s\"] INSTRUCTION_FORMAT21s REGISTER integral_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21s, (retval.start), "I_STATEMENT_FORMAT21s"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21s.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_integral_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21s"


	public static class insn_format21t_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21t"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:956:1: insn_format21t : INSTRUCTION_FORMAT21t REGISTER COMMA label_ref -> ^( I_STATEMENT_FORMAT21t[$start, \"I_STATEMENT_FORMAT21t\"] INSTRUCTION_FORMAT21t REGISTER label_ref ) ;
	public final smaliParser.insn_format21t_return insn_format21t() throws RecognitionException {
		smaliParser.insn_format21t_return retval = new smaliParser.insn_format21t_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21t349=null;
		Token REGISTER350=null;
		Token COMMA351=null;
		ParserRuleReturnScope label_ref352 =null;

		CommonTree INSTRUCTION_FORMAT21t349_tree=null;
		CommonTree REGISTER350_tree=null;
		CommonTree COMMA351_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21t");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:957:3: ( INSTRUCTION_FORMAT21t REGISTER COMMA label_ref -> ^( I_STATEMENT_FORMAT21t[$start, \"I_STATEMENT_FORMAT21t\"] INSTRUCTION_FORMAT21t REGISTER label_ref ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:958:5: INSTRUCTION_FORMAT21t REGISTER COMMA label_ref
			{
			INSTRUCTION_FORMAT21t349=(Token)match(input,INSTRUCTION_FORMAT21t,FOLLOW_INSTRUCTION_FORMAT21t_in_insn_format21t4718);  
			stream_INSTRUCTION_FORMAT21t.add(INSTRUCTION_FORMAT21t349);

			REGISTER350=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21t4720);  
			stream_REGISTER.add(REGISTER350);

			COMMA351=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21t4722);  
			stream_COMMA.add(COMMA351);

			pushFollow(FOLLOW_label_ref_in_insn_format21t4724);
			label_ref352=label_ref();
			state._fsp--;

			stream_label_ref.add(label_ref352.getTree());
			// AST REWRITE
			// elements: REGISTER, label_ref, INSTRUCTION_FORMAT21t
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 959:5: -> ^( I_STATEMENT_FORMAT21t[$start, \"I_STATEMENT_FORMAT21t\"] INSTRUCTION_FORMAT21t REGISTER label_ref )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:959:8: ^( I_STATEMENT_FORMAT21t[$start, \"I_STATEMENT_FORMAT21t\"] INSTRUCTION_FORMAT21t REGISTER label_ref )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21t, (retval.start), "I_STATEMENT_FORMAT21t"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21t.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_label_ref.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21t"


	public static class insn_format22b_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22b"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:961:1: insn_format22b : INSTRUCTION_FORMAT22b REGISTER COMMA REGISTER COMMA integral_literal -> ^( I_STATEMENT_FORMAT22b[$start, \"I_STATEMENT_FORMAT22b\"] INSTRUCTION_FORMAT22b REGISTER REGISTER integral_literal ) ;
	public final smaliParser.insn_format22b_return insn_format22b() throws RecognitionException {
		smaliParser.insn_format22b_return retval = new smaliParser.insn_format22b_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22b353=null;
		Token REGISTER354=null;
		Token COMMA355=null;
		Token REGISTER356=null;
		Token COMMA357=null;
		ParserRuleReturnScope integral_literal358 =null;

		CommonTree INSTRUCTION_FORMAT22b353_tree=null;
		CommonTree REGISTER354_tree=null;
		CommonTree COMMA355_tree=null;
		CommonTree REGISTER356_tree=null;
		CommonTree COMMA357_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22b=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22b");
		RewriteRuleSubtreeStream stream_integral_literal=new RewriteRuleSubtreeStream(adaptor,"rule integral_literal");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:962:3: ( INSTRUCTION_FORMAT22b REGISTER COMMA REGISTER COMMA integral_literal -> ^( I_STATEMENT_FORMAT22b[$start, \"I_STATEMENT_FORMAT22b\"] INSTRUCTION_FORMAT22b REGISTER REGISTER integral_literal ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:963:5: INSTRUCTION_FORMAT22b REGISTER COMMA REGISTER COMMA integral_literal
			{
			INSTRUCTION_FORMAT22b353=(Token)match(input,INSTRUCTION_FORMAT22b,FOLLOW_INSTRUCTION_FORMAT22b_in_insn_format22b4756);  
			stream_INSTRUCTION_FORMAT22b.add(INSTRUCTION_FORMAT22b353);

			REGISTER354=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22b4758);  
			stream_REGISTER.add(REGISTER354);

			COMMA355=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22b4760);  
			stream_COMMA.add(COMMA355);

			REGISTER356=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22b4762);  
			stream_REGISTER.add(REGISTER356);

			COMMA357=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22b4764);  
			stream_COMMA.add(COMMA357);

			pushFollow(FOLLOW_integral_literal_in_insn_format22b4766);
			integral_literal358=integral_literal();
			state._fsp--;

			stream_integral_literal.add(integral_literal358.getTree());
			// AST REWRITE
			// elements: REGISTER, REGISTER, integral_literal, INSTRUCTION_FORMAT22b
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 964:5: -> ^( I_STATEMENT_FORMAT22b[$start, \"I_STATEMENT_FORMAT22b\"] INSTRUCTION_FORMAT22b REGISTER REGISTER integral_literal )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:964:8: ^( I_STATEMENT_FORMAT22b[$start, \"I_STATEMENT_FORMAT22b\"] INSTRUCTION_FORMAT22b REGISTER REGISTER integral_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT22b, (retval.start), "I_STATEMENT_FORMAT22b"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22b.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_integral_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22b"


	public static class insn_format22c_field_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22c_field"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:966:1: insn_format22c_field : INSTRUCTION_FORMAT22c_FIELD REGISTER COMMA REGISTER COMMA field_reference -> ^( I_STATEMENT_FORMAT22c_FIELD[$start, \"I_STATEMENT_FORMAT22c_FIELD\"] INSTRUCTION_FORMAT22c_FIELD REGISTER REGISTER field_reference ) ;
	public final smaliParser.insn_format22c_field_return insn_format22c_field() throws RecognitionException {
		smaliParser.insn_format22c_field_return retval = new smaliParser.insn_format22c_field_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22c_FIELD359=null;
		Token REGISTER360=null;
		Token COMMA361=null;
		Token REGISTER362=null;
		Token COMMA363=null;
		ParserRuleReturnScope field_reference364 =null;

		CommonTree INSTRUCTION_FORMAT22c_FIELD359_tree=null;
		CommonTree REGISTER360_tree=null;
		CommonTree COMMA361_tree=null;
		CommonTree REGISTER362_tree=null;
		CommonTree COMMA363_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_FIELD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22c_FIELD");
		RewriteRuleSubtreeStream stream_field_reference=new RewriteRuleSubtreeStream(adaptor,"rule field_reference");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:967:3: ( INSTRUCTION_FORMAT22c_FIELD REGISTER COMMA REGISTER COMMA field_reference -> ^( I_STATEMENT_FORMAT22c_FIELD[$start, \"I_STATEMENT_FORMAT22c_FIELD\"] INSTRUCTION_FORMAT22c_FIELD REGISTER REGISTER field_reference ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:968:5: INSTRUCTION_FORMAT22c_FIELD REGISTER COMMA REGISTER COMMA field_reference
			{
			INSTRUCTION_FORMAT22c_FIELD359=(Token)match(input,INSTRUCTION_FORMAT22c_FIELD,FOLLOW_INSTRUCTION_FORMAT22c_FIELD_in_insn_format22c_field4800);  
			stream_INSTRUCTION_FORMAT22c_FIELD.add(INSTRUCTION_FORMAT22c_FIELD359);

			REGISTER360=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_field4802);  
			stream_REGISTER.add(REGISTER360);

			COMMA361=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22c_field4804);  
			stream_COMMA.add(COMMA361);

			REGISTER362=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_field4806);  
			stream_REGISTER.add(REGISTER362);

			COMMA363=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22c_field4808);  
			stream_COMMA.add(COMMA363);

			pushFollow(FOLLOW_field_reference_in_insn_format22c_field4810);
			field_reference364=field_reference();
			state._fsp--;

			stream_field_reference.add(field_reference364.getTree());
			// AST REWRITE
			// elements: REGISTER, REGISTER, field_reference, INSTRUCTION_FORMAT22c_FIELD
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 969:5: -> ^( I_STATEMENT_FORMAT22c_FIELD[$start, \"I_STATEMENT_FORMAT22c_FIELD\"] INSTRUCTION_FORMAT22c_FIELD REGISTER REGISTER field_reference )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:969:8: ^( I_STATEMENT_FORMAT22c_FIELD[$start, \"I_STATEMENT_FORMAT22c_FIELD\"] INSTRUCTION_FORMAT22c_FIELD REGISTER REGISTER field_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT22c_FIELD, (retval.start), "I_STATEMENT_FORMAT22c_FIELD"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22c_FIELD.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_field_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22c_field"


	public static class insn_format22c_field_odex_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22c_field_odex"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:971:1: insn_format22c_field_odex : INSTRUCTION_FORMAT22c_FIELD_ODEX REGISTER COMMA REGISTER COMMA field_reference -> ^( I_STATEMENT_FORMAT22c_FIELD[$start, \"I_STATEMENT_FORMAT22c_FIELD\"] INSTRUCTION_FORMAT22c_FIELD_ODEX REGISTER REGISTER field_reference ) ;
	public final smaliParser.insn_format22c_field_odex_return insn_format22c_field_odex() throws RecognitionException {
		smaliParser.insn_format22c_field_odex_return retval = new smaliParser.insn_format22c_field_odex_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22c_FIELD_ODEX365=null;
		Token REGISTER366=null;
		Token COMMA367=null;
		Token REGISTER368=null;
		Token COMMA369=null;
		ParserRuleReturnScope field_reference370 =null;

		CommonTree INSTRUCTION_FORMAT22c_FIELD_ODEX365_tree=null;
		CommonTree REGISTER366_tree=null;
		CommonTree COMMA367_tree=null;
		CommonTree REGISTER368_tree=null;
		CommonTree COMMA369_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_FIELD_ODEX=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22c_FIELD_ODEX");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_field_reference=new RewriteRuleSubtreeStream(adaptor,"rule field_reference");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:972:3: ( INSTRUCTION_FORMAT22c_FIELD_ODEX REGISTER COMMA REGISTER COMMA field_reference -> ^( I_STATEMENT_FORMAT22c_FIELD[$start, \"I_STATEMENT_FORMAT22c_FIELD\"] INSTRUCTION_FORMAT22c_FIELD_ODEX REGISTER REGISTER field_reference ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:973:5: INSTRUCTION_FORMAT22c_FIELD_ODEX REGISTER COMMA REGISTER COMMA field_reference
			{
			INSTRUCTION_FORMAT22c_FIELD_ODEX365=(Token)match(input,INSTRUCTION_FORMAT22c_FIELD_ODEX,FOLLOW_INSTRUCTION_FORMAT22c_FIELD_ODEX_in_insn_format22c_field_odex4844);  
			stream_INSTRUCTION_FORMAT22c_FIELD_ODEX.add(INSTRUCTION_FORMAT22c_FIELD_ODEX365);

			REGISTER366=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_field_odex4846);  
			stream_REGISTER.add(REGISTER366);

			COMMA367=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22c_field_odex4848);  
			stream_COMMA.add(COMMA367);

			REGISTER368=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_field_odex4850);  
			stream_REGISTER.add(REGISTER368);

			COMMA369=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22c_field_odex4852);  
			stream_COMMA.add(COMMA369);

			pushFollow(FOLLOW_field_reference_in_insn_format22c_field_odex4854);
			field_reference370=field_reference();
			state._fsp--;

			stream_field_reference.add(field_reference370.getTree());

			      if (!allowOdex || opcodes.getOpcodeByName((INSTRUCTION_FORMAT22c_FIELD_ODEX365!=null?INSTRUCTION_FORMAT22c_FIELD_ODEX365.getText():null)) == null || apiLevel >= 14) {
			        throwOdexedInstructionException(input, (INSTRUCTION_FORMAT22c_FIELD_ODEX365!=null?INSTRUCTION_FORMAT22c_FIELD_ODEX365.getText():null));
			      }
			    
			// AST REWRITE
			// elements: field_reference, REGISTER, REGISTER, INSTRUCTION_FORMAT22c_FIELD_ODEX
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 979:5: -> ^( I_STATEMENT_FORMAT22c_FIELD[$start, \"I_STATEMENT_FORMAT22c_FIELD\"] INSTRUCTION_FORMAT22c_FIELD_ODEX REGISTER REGISTER field_reference )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:979:8: ^( I_STATEMENT_FORMAT22c_FIELD[$start, \"I_STATEMENT_FORMAT22c_FIELD\"] INSTRUCTION_FORMAT22c_FIELD_ODEX REGISTER REGISTER field_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT22c_FIELD, (retval.start), "I_STATEMENT_FORMAT22c_FIELD"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22c_FIELD_ODEX.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_field_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22c_field_odex"


	public static class insn_format22c_type_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22c_type"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:981:1: insn_format22c_type : INSTRUCTION_FORMAT22c_TYPE REGISTER COMMA REGISTER COMMA nonvoid_type_descriptor -> ^( I_STATEMENT_FORMAT22c_TYPE[$start, \"I_STATEMENT_FORMAT22c_TYPE\"] INSTRUCTION_FORMAT22c_TYPE REGISTER REGISTER nonvoid_type_descriptor ) ;
	public final smaliParser.insn_format22c_type_return insn_format22c_type() throws RecognitionException {
		smaliParser.insn_format22c_type_return retval = new smaliParser.insn_format22c_type_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22c_TYPE371=null;
		Token REGISTER372=null;
		Token COMMA373=null;
		Token REGISTER374=null;
		Token COMMA375=null;
		ParserRuleReturnScope nonvoid_type_descriptor376 =null;

		CommonTree INSTRUCTION_FORMAT22c_TYPE371_tree=null;
		CommonTree REGISTER372_tree=null;
		CommonTree COMMA373_tree=null;
		CommonTree REGISTER374_tree=null;
		CommonTree COMMA375_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22c_TYPE");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:982:3: ( INSTRUCTION_FORMAT22c_TYPE REGISTER COMMA REGISTER COMMA nonvoid_type_descriptor -> ^( I_STATEMENT_FORMAT22c_TYPE[$start, \"I_STATEMENT_FORMAT22c_TYPE\"] INSTRUCTION_FORMAT22c_TYPE REGISTER REGISTER nonvoid_type_descriptor ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:983:5: INSTRUCTION_FORMAT22c_TYPE REGISTER COMMA REGISTER COMMA nonvoid_type_descriptor
			{
			INSTRUCTION_FORMAT22c_TYPE371=(Token)match(input,INSTRUCTION_FORMAT22c_TYPE,FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_insn_format22c_type4894);  
			stream_INSTRUCTION_FORMAT22c_TYPE.add(INSTRUCTION_FORMAT22c_TYPE371);

			REGISTER372=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_type4896);  
			stream_REGISTER.add(REGISTER372);

			COMMA373=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22c_type4898);  
			stream_COMMA.add(COMMA373);

			REGISTER374=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_type4900);  
			stream_REGISTER.add(REGISTER374);

			COMMA375=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22c_type4902);  
			stream_COMMA.add(COMMA375);

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format22c_type4904);
			nonvoid_type_descriptor376=nonvoid_type_descriptor();
			state._fsp--;

			stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor376.getTree());
			// AST REWRITE
			// elements: REGISTER, nonvoid_type_descriptor, REGISTER, INSTRUCTION_FORMAT22c_TYPE
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 984:5: -> ^( I_STATEMENT_FORMAT22c_TYPE[$start, \"I_STATEMENT_FORMAT22c_TYPE\"] INSTRUCTION_FORMAT22c_TYPE REGISTER REGISTER nonvoid_type_descriptor )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:984:8: ^( I_STATEMENT_FORMAT22c_TYPE[$start, \"I_STATEMENT_FORMAT22c_TYPE\"] INSTRUCTION_FORMAT22c_TYPE REGISTER REGISTER nonvoid_type_descriptor )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT22c_TYPE, (retval.start), "I_STATEMENT_FORMAT22c_TYPE"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22c_TYPE.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22c_type"


	public static class insn_format22c_string_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22c_string"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:986:1: insn_format22c_string : INSTRUCTION_FORMAT22c_STRING REGISTER COMMA REGISTER COMMA STRING_LITERAL -> ^( I_STATEMENT_FORMAT22c_STRING[$start, \"I_STATEMENT_FORMAT22c_STRING\"] INSTRUCTION_FORMAT22c_STRING REGISTER REGISTER STRING_LITERAL ) ;
	public final smaliParser.insn_format22c_string_return insn_format22c_string() throws RecognitionException {
		smaliParser.insn_format22c_string_return retval = new smaliParser.insn_format22c_string_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22c_STRING377=null;
		Token REGISTER378=null;
		Token COMMA379=null;
		Token REGISTER380=null;
		Token COMMA381=null;
		Token STRING_LITERAL382=null;

		CommonTree INSTRUCTION_FORMAT22c_STRING377_tree=null;
		CommonTree REGISTER378_tree=null;
		CommonTree COMMA379_tree=null;
		CommonTree REGISTER380_tree=null;
		CommonTree COMMA381_tree=null;
		CommonTree STRING_LITERAL382_tree=null;
		RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_STRING=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22c_STRING");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:987:3: ( INSTRUCTION_FORMAT22c_STRING REGISTER COMMA REGISTER COMMA STRING_LITERAL -> ^( I_STATEMENT_FORMAT22c_STRING[$start, \"I_STATEMENT_FORMAT22c_STRING\"] INSTRUCTION_FORMAT22c_STRING REGISTER REGISTER STRING_LITERAL ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:988:5: INSTRUCTION_FORMAT22c_STRING REGISTER COMMA REGISTER COMMA STRING_LITERAL
			{
			INSTRUCTION_FORMAT22c_STRING377=(Token)match(input,INSTRUCTION_FORMAT22c_STRING,FOLLOW_INSTRUCTION_FORMAT22c_STRING_in_insn_format22c_string4938);  
			stream_INSTRUCTION_FORMAT22c_STRING.add(INSTRUCTION_FORMAT22c_STRING377);

			REGISTER378=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_string4940);  
			stream_REGISTER.add(REGISTER378);

			COMMA379=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22c_string4942);  
			stream_COMMA.add(COMMA379);

			REGISTER380=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_string4944);  
			stream_REGISTER.add(REGISTER380);

			COMMA381=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22c_string4946);  
			stream_COMMA.add(COMMA381);

			STRING_LITERAL382=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_insn_format22c_string4948);  
			stream_STRING_LITERAL.add(STRING_LITERAL382);

			// AST REWRITE
			// elements: INSTRUCTION_FORMAT22c_STRING, REGISTER, REGISTER, STRING_LITERAL
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 989:5: -> ^( I_STATEMENT_FORMAT22c_STRING[$start, \"I_STATEMENT_FORMAT22c_STRING\"] INSTRUCTION_FORMAT22c_STRING REGISTER REGISTER STRING_LITERAL )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:989:8: ^( I_STATEMENT_FORMAT22c_STRING[$start, \"I_STATEMENT_FORMAT22c_STRING\"] INSTRUCTION_FORMAT22c_STRING REGISTER REGISTER STRING_LITERAL )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT22c_STRING, (retval.start), "I_STATEMENT_FORMAT22c_STRING"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22c_STRING.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22c_string"


	public static class insn_format22cs_field_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22cs_field"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:991:1: insn_format22cs_field : INSTRUCTION_FORMAT22cs_FIELD REGISTER COMMA REGISTER COMMA FIELD_OFFSET ;
	public final smaliParser.insn_format22cs_field_return insn_format22cs_field() throws RecognitionException {
		smaliParser.insn_format22cs_field_return retval = new smaliParser.insn_format22cs_field_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22cs_FIELD383=null;
		Token REGISTER384=null;
		Token COMMA385=null;
		Token REGISTER386=null;
		Token COMMA387=null;
		Token FIELD_OFFSET388=null;

		CommonTree INSTRUCTION_FORMAT22cs_FIELD383_tree=null;
		CommonTree REGISTER384_tree=null;
		CommonTree COMMA385_tree=null;
		CommonTree REGISTER386_tree=null;
		CommonTree COMMA387_tree=null;
		CommonTree FIELD_OFFSET388_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:992:3: ( INSTRUCTION_FORMAT22cs_FIELD REGISTER COMMA REGISTER COMMA FIELD_OFFSET )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:993:5: INSTRUCTION_FORMAT22cs_FIELD REGISTER COMMA REGISTER COMMA FIELD_OFFSET
			{
			root_0 = (CommonTree)adaptor.nil();


			INSTRUCTION_FORMAT22cs_FIELD383=(Token)match(input,INSTRUCTION_FORMAT22cs_FIELD,FOLLOW_INSTRUCTION_FORMAT22cs_FIELD_in_insn_format22cs_field4982); 
			INSTRUCTION_FORMAT22cs_FIELD383_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT22cs_FIELD383);
			adaptor.addChild(root_0, INSTRUCTION_FORMAT22cs_FIELD383_tree);

			REGISTER384=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22cs_field4984); 
			REGISTER384_tree = (CommonTree)adaptor.create(REGISTER384);
			adaptor.addChild(root_0, REGISTER384_tree);

			COMMA385=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22cs_field4986); 
			COMMA385_tree = (CommonTree)adaptor.create(COMMA385);
			adaptor.addChild(root_0, COMMA385_tree);

			REGISTER386=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22cs_field4988); 
			REGISTER386_tree = (CommonTree)adaptor.create(REGISTER386);
			adaptor.addChild(root_0, REGISTER386_tree);

			COMMA387=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22cs_field4990); 
			COMMA387_tree = (CommonTree)adaptor.create(COMMA387);
			adaptor.addChild(root_0, COMMA387_tree);

			FIELD_OFFSET388=(Token)match(input,FIELD_OFFSET,FOLLOW_FIELD_OFFSET_in_insn_format22cs_field4992); 
			FIELD_OFFSET388_tree = (CommonTree)adaptor.create(FIELD_OFFSET388);
			adaptor.addChild(root_0, FIELD_OFFSET388_tree);


			      throwOdexedInstructionException(input, (INSTRUCTION_FORMAT22cs_FIELD383!=null?INSTRUCTION_FORMAT22cs_FIELD383.getText():null));
			    
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22cs_field"


	public static class insn_format22s_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22s"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:998:1: insn_format22s : instruction_format22s REGISTER COMMA REGISTER COMMA integral_literal -> ^( I_STATEMENT_FORMAT22s[$start, \"I_STATEMENT_FORMAT22s\"] instruction_format22s REGISTER REGISTER integral_literal ) ;
	public final smaliParser.insn_format22s_return insn_format22s() throws RecognitionException {
		smaliParser.insn_format22s_return retval = new smaliParser.insn_format22s_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token REGISTER390=null;
		Token COMMA391=null;
		Token REGISTER392=null;
		Token COMMA393=null;
		ParserRuleReturnScope instruction_format22s389 =null;
		ParserRuleReturnScope integral_literal394 =null;

		CommonTree REGISTER390_tree=null;
		CommonTree COMMA391_tree=null;
		CommonTree REGISTER392_tree=null;
		CommonTree COMMA393_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_integral_literal=new RewriteRuleSubtreeStream(adaptor,"rule integral_literal");
		RewriteRuleSubtreeStream stream_instruction_format22s=new RewriteRuleSubtreeStream(adaptor,"rule instruction_format22s");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:999:3: ( instruction_format22s REGISTER COMMA REGISTER COMMA integral_literal -> ^( I_STATEMENT_FORMAT22s[$start, \"I_STATEMENT_FORMAT22s\"] instruction_format22s REGISTER REGISTER integral_literal ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1000:5: instruction_format22s REGISTER COMMA REGISTER COMMA integral_literal
			{
			pushFollow(FOLLOW_instruction_format22s_in_insn_format22s5013);
			instruction_format22s389=instruction_format22s();
			state._fsp--;

			stream_instruction_format22s.add(instruction_format22s389.getTree());
			REGISTER390=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22s5015);  
			stream_REGISTER.add(REGISTER390);

			COMMA391=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22s5017);  
			stream_COMMA.add(COMMA391);

			REGISTER392=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22s5019);  
			stream_REGISTER.add(REGISTER392);

			COMMA393=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22s5021);  
			stream_COMMA.add(COMMA393);

			pushFollow(FOLLOW_integral_literal_in_insn_format22s5023);
			integral_literal394=integral_literal();
			state._fsp--;

			stream_integral_literal.add(integral_literal394.getTree());
			// AST REWRITE
			// elements: REGISTER, integral_literal, REGISTER, instruction_format22s
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1001:5: -> ^( I_STATEMENT_FORMAT22s[$start, \"I_STATEMENT_FORMAT22s\"] instruction_format22s REGISTER REGISTER integral_literal )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1001:8: ^( I_STATEMENT_FORMAT22s[$start, \"I_STATEMENT_FORMAT22s\"] instruction_format22s REGISTER REGISTER integral_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT22s, (retval.start), "I_STATEMENT_FORMAT22s"), root_1);
				adaptor.addChild(root_1, stream_instruction_format22s.nextTree());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_integral_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22s"


	public static class insn_format22t_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22t"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1003:1: insn_format22t : INSTRUCTION_FORMAT22t REGISTER COMMA REGISTER COMMA label_ref -> ^( I_STATEMENT_FORMAT22t[$start, \"I_STATEMENT_FFORMAT22t\"] INSTRUCTION_FORMAT22t REGISTER REGISTER label_ref ) ;
	public final smaliParser.insn_format22t_return insn_format22t() throws RecognitionException {
		smaliParser.insn_format22t_return retval = new smaliParser.insn_format22t_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22t395=null;
		Token REGISTER396=null;
		Token COMMA397=null;
		Token REGISTER398=null;
		Token COMMA399=null;
		ParserRuleReturnScope label_ref400 =null;

		CommonTree INSTRUCTION_FORMAT22t395_tree=null;
		CommonTree REGISTER396_tree=null;
		CommonTree COMMA397_tree=null;
		CommonTree REGISTER398_tree=null;
		CommonTree COMMA399_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22t");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1004:3: ( INSTRUCTION_FORMAT22t REGISTER COMMA REGISTER COMMA label_ref -> ^( I_STATEMENT_FORMAT22t[$start, \"I_STATEMENT_FFORMAT22t\"] INSTRUCTION_FORMAT22t REGISTER REGISTER label_ref ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1005:5: INSTRUCTION_FORMAT22t REGISTER COMMA REGISTER COMMA label_ref
			{
			INSTRUCTION_FORMAT22t395=(Token)match(input,INSTRUCTION_FORMAT22t,FOLLOW_INSTRUCTION_FORMAT22t_in_insn_format22t5057);  
			stream_INSTRUCTION_FORMAT22t.add(INSTRUCTION_FORMAT22t395);

			REGISTER396=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22t5059);  
			stream_REGISTER.add(REGISTER396);

			COMMA397=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22t5061);  
			stream_COMMA.add(COMMA397);

			REGISTER398=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22t5063);  
			stream_REGISTER.add(REGISTER398);

			COMMA399=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22t5065);  
			stream_COMMA.add(COMMA399);

			pushFollow(FOLLOW_label_ref_in_insn_format22t5067);
			label_ref400=label_ref();
			state._fsp--;

			stream_label_ref.add(label_ref400.getTree());
			// AST REWRITE
			// elements: label_ref, REGISTER, REGISTER, INSTRUCTION_FORMAT22t
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1006:5: -> ^( I_STATEMENT_FORMAT22t[$start, \"I_STATEMENT_FFORMAT22t\"] INSTRUCTION_FORMAT22t REGISTER REGISTER label_ref )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1006:8: ^( I_STATEMENT_FORMAT22t[$start, \"I_STATEMENT_FFORMAT22t\"] INSTRUCTION_FORMAT22t REGISTER REGISTER label_ref )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT22t, (retval.start), "I_STATEMENT_FFORMAT22t"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22t.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_label_ref.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22t"


	public static class insn_format22x_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22x"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1008:1: insn_format22x : INSTRUCTION_FORMAT22x REGISTER COMMA REGISTER -> ^( I_STATEMENT_FORMAT22x[$start, \"I_STATEMENT_FORMAT22x\"] INSTRUCTION_FORMAT22x REGISTER REGISTER ) ;
	public final smaliParser.insn_format22x_return insn_format22x() throws RecognitionException {
		smaliParser.insn_format22x_return retval = new smaliParser.insn_format22x_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22x401=null;
		Token REGISTER402=null;
		Token COMMA403=null;
		Token REGISTER404=null;

		CommonTree INSTRUCTION_FORMAT22x401_tree=null;
		CommonTree REGISTER402_tree=null;
		CommonTree COMMA403_tree=null;
		CommonTree REGISTER404_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22x");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1009:3: ( INSTRUCTION_FORMAT22x REGISTER COMMA REGISTER -> ^( I_STATEMENT_FORMAT22x[$start, \"I_STATEMENT_FORMAT22x\"] INSTRUCTION_FORMAT22x REGISTER REGISTER ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1010:5: INSTRUCTION_FORMAT22x REGISTER COMMA REGISTER
			{
			INSTRUCTION_FORMAT22x401=(Token)match(input,INSTRUCTION_FORMAT22x,FOLLOW_INSTRUCTION_FORMAT22x_in_insn_format22x5101);  
			stream_INSTRUCTION_FORMAT22x.add(INSTRUCTION_FORMAT22x401);

			REGISTER402=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22x5103);  
			stream_REGISTER.add(REGISTER402);

			COMMA403=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22x5105);  
			stream_COMMA.add(COMMA403);

			REGISTER404=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22x5107);  
			stream_REGISTER.add(REGISTER404);

			// AST REWRITE
			// elements: REGISTER, REGISTER, INSTRUCTION_FORMAT22x
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1011:5: -> ^( I_STATEMENT_FORMAT22x[$start, \"I_STATEMENT_FORMAT22x\"] INSTRUCTION_FORMAT22x REGISTER REGISTER )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1011:8: ^( I_STATEMENT_FORMAT22x[$start, \"I_STATEMENT_FORMAT22x\"] INSTRUCTION_FORMAT22x REGISTER REGISTER )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT22x, (retval.start), "I_STATEMENT_FORMAT22x"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22x.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22x"


	public static class insn_format23x_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format23x"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1013:1: insn_format23x : INSTRUCTION_FORMAT23x REGISTER COMMA REGISTER COMMA REGISTER -> ^( I_STATEMENT_FORMAT23x[$start, \"I_STATEMENT_FORMAT23x\"] INSTRUCTION_FORMAT23x REGISTER REGISTER REGISTER ) ;
	public final smaliParser.insn_format23x_return insn_format23x() throws RecognitionException {
		smaliParser.insn_format23x_return retval = new smaliParser.insn_format23x_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT23x405=null;
		Token REGISTER406=null;
		Token COMMA407=null;
		Token REGISTER408=null;
		Token COMMA409=null;
		Token REGISTER410=null;

		CommonTree INSTRUCTION_FORMAT23x405_tree=null;
		CommonTree REGISTER406_tree=null;
		CommonTree COMMA407_tree=null;
		CommonTree REGISTER408_tree=null;
		CommonTree COMMA409_tree=null;
		CommonTree REGISTER410_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT23x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT23x");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1014:3: ( INSTRUCTION_FORMAT23x REGISTER COMMA REGISTER COMMA REGISTER -> ^( I_STATEMENT_FORMAT23x[$start, \"I_STATEMENT_FORMAT23x\"] INSTRUCTION_FORMAT23x REGISTER REGISTER REGISTER ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1015:5: INSTRUCTION_FORMAT23x REGISTER COMMA REGISTER COMMA REGISTER
			{
			INSTRUCTION_FORMAT23x405=(Token)match(input,INSTRUCTION_FORMAT23x,FOLLOW_INSTRUCTION_FORMAT23x_in_insn_format23x5139);  
			stream_INSTRUCTION_FORMAT23x.add(INSTRUCTION_FORMAT23x405);

			REGISTER406=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format23x5141);  
			stream_REGISTER.add(REGISTER406);

			COMMA407=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format23x5143);  
			stream_COMMA.add(COMMA407);

			REGISTER408=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format23x5145);  
			stream_REGISTER.add(REGISTER408);

			COMMA409=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format23x5147);  
			stream_COMMA.add(COMMA409);

			REGISTER410=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format23x5149);  
			stream_REGISTER.add(REGISTER410);

			// AST REWRITE
			// elements: REGISTER, REGISTER, REGISTER, INSTRUCTION_FORMAT23x
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1016:5: -> ^( I_STATEMENT_FORMAT23x[$start, \"I_STATEMENT_FORMAT23x\"] INSTRUCTION_FORMAT23x REGISTER REGISTER REGISTER )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1016:8: ^( I_STATEMENT_FORMAT23x[$start, \"I_STATEMENT_FORMAT23x\"] INSTRUCTION_FORMAT23x REGISTER REGISTER REGISTER )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT23x, (retval.start), "I_STATEMENT_FORMAT23x"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT23x.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format23x"


	public static class insn_format25x_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format25x"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1018:1: insn_format25x : INSTRUCTION_FORMAT25x REGISTER COMMA OPEN_BRACE register_list CLOSE_BRACE -> ^( I_STATEMENT_FORMAT25x[$start, \"I_STATEMENT_FORMAT25x\"] INSTRUCTION_FORMAT25x REGISTER register_list ) ;
	public final smaliParser.insn_format25x_return insn_format25x() throws RecognitionException {
		smaliParser.insn_format25x_return retval = new smaliParser.insn_format25x_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT25x411=null;
		Token REGISTER412=null;
		Token COMMA413=null;
		Token OPEN_BRACE414=null;
		Token CLOSE_BRACE416=null;
		ParserRuleReturnScope register_list415 =null;

		CommonTree INSTRUCTION_FORMAT25x411_tree=null;
		CommonTree REGISTER412_tree=null;
		CommonTree COMMA413_tree=null;
		CommonTree OPEN_BRACE414_tree=null;
		CommonTree CLOSE_BRACE416_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT25x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT25x");
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleSubtreeStream stream_register_list=new RewriteRuleSubtreeStream(adaptor,"rule register_list");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1019:3: ( INSTRUCTION_FORMAT25x REGISTER COMMA OPEN_BRACE register_list CLOSE_BRACE -> ^( I_STATEMENT_FORMAT25x[$start, \"I_STATEMENT_FORMAT25x\"] INSTRUCTION_FORMAT25x REGISTER register_list ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1020:5: INSTRUCTION_FORMAT25x REGISTER COMMA OPEN_BRACE register_list CLOSE_BRACE
			{
			INSTRUCTION_FORMAT25x411=(Token)match(input,INSTRUCTION_FORMAT25x,FOLLOW_INSTRUCTION_FORMAT25x_in_insn_format25x5183);  
			stream_INSTRUCTION_FORMAT25x.add(INSTRUCTION_FORMAT25x411);

			REGISTER412=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format25x5185);  
			stream_REGISTER.add(REGISTER412);

			COMMA413=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format25x5187);  
			stream_COMMA.add(COMMA413);

			OPEN_BRACE414=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format25x5189);  
			stream_OPEN_BRACE.add(OPEN_BRACE414);

			pushFollow(FOLLOW_register_list_in_insn_format25x5191);
			register_list415=register_list();
			state._fsp--;

			stream_register_list.add(register_list415.getTree());
			CLOSE_BRACE416=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format25x5193);  
			stream_CLOSE_BRACE.add(CLOSE_BRACE416);

			// AST REWRITE
			// elements: REGISTER, register_list, INSTRUCTION_FORMAT25x
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1021:5: -> ^( I_STATEMENT_FORMAT25x[$start, \"I_STATEMENT_FORMAT25x\"] INSTRUCTION_FORMAT25x REGISTER register_list )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1021:8: ^( I_STATEMENT_FORMAT25x[$start, \"I_STATEMENT_FORMAT25x\"] INSTRUCTION_FORMAT25x REGISTER register_list )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT25x, (retval.start), "I_STATEMENT_FORMAT25x"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT25x.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_register_list.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format25x"


	public static class insn_format30t_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format30t"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1023:1: insn_format30t : INSTRUCTION_FORMAT30t label_ref -> ^( I_STATEMENT_FORMAT30t[$start, \"I_STATEMENT_FORMAT30t\"] INSTRUCTION_FORMAT30t label_ref ) ;
	public final smaliParser.insn_format30t_return insn_format30t() throws RecognitionException {
		smaliParser.insn_format30t_return retval = new smaliParser.insn_format30t_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT30t417=null;
		ParserRuleReturnScope label_ref418 =null;

		CommonTree INSTRUCTION_FORMAT30t417_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT30t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT30t");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1024:3: ( INSTRUCTION_FORMAT30t label_ref -> ^( I_STATEMENT_FORMAT30t[$start, \"I_STATEMENT_FORMAT30t\"] INSTRUCTION_FORMAT30t label_ref ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1025:5: INSTRUCTION_FORMAT30t label_ref
			{
			INSTRUCTION_FORMAT30t417=(Token)match(input,INSTRUCTION_FORMAT30t,FOLLOW_INSTRUCTION_FORMAT30t_in_insn_format30t5225);  
			stream_INSTRUCTION_FORMAT30t.add(INSTRUCTION_FORMAT30t417);

			pushFollow(FOLLOW_label_ref_in_insn_format30t5227);
			label_ref418=label_ref();
			state._fsp--;

			stream_label_ref.add(label_ref418.getTree());
			// AST REWRITE
			// elements: label_ref, INSTRUCTION_FORMAT30t
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1026:5: -> ^( I_STATEMENT_FORMAT30t[$start, \"I_STATEMENT_FORMAT30t\"] INSTRUCTION_FORMAT30t label_ref )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1026:8: ^( I_STATEMENT_FORMAT30t[$start, \"I_STATEMENT_FORMAT30t\"] INSTRUCTION_FORMAT30t label_ref )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT30t, (retval.start), "I_STATEMENT_FORMAT30t"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT30t.nextNode());
				adaptor.addChild(root_1, stream_label_ref.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format30t"


	public static class insn_format31c_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format31c"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1028:1: insn_format31c : INSTRUCTION_FORMAT31c REGISTER COMMA STRING_LITERAL -> ^( I_STATEMENT_FORMAT31c[$start, \"I_STATEMENT_FORMAT31c\"] INSTRUCTION_FORMAT31c REGISTER STRING_LITERAL ) ;
	public final smaliParser.insn_format31c_return insn_format31c() throws RecognitionException {
		smaliParser.insn_format31c_return retval = new smaliParser.insn_format31c_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT31c419=null;
		Token REGISTER420=null;
		Token COMMA421=null;
		Token STRING_LITERAL422=null;

		CommonTree INSTRUCTION_FORMAT31c419_tree=null;
		CommonTree REGISTER420_tree=null;
		CommonTree COMMA421_tree=null;
		CommonTree STRING_LITERAL422_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT31c=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT31c");
		RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1029:3: ( INSTRUCTION_FORMAT31c REGISTER COMMA STRING_LITERAL -> ^( I_STATEMENT_FORMAT31c[$start, \"I_STATEMENT_FORMAT31c\"] INSTRUCTION_FORMAT31c REGISTER STRING_LITERAL ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1030:5: INSTRUCTION_FORMAT31c REGISTER COMMA STRING_LITERAL
			{
			INSTRUCTION_FORMAT31c419=(Token)match(input,INSTRUCTION_FORMAT31c,FOLLOW_INSTRUCTION_FORMAT31c_in_insn_format31c5257);  
			stream_INSTRUCTION_FORMAT31c.add(INSTRUCTION_FORMAT31c419);

			REGISTER420=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format31c5259);  
			stream_REGISTER.add(REGISTER420);

			COMMA421=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format31c5261);  
			stream_COMMA.add(COMMA421);

			STRING_LITERAL422=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_insn_format31c5263);  
			stream_STRING_LITERAL.add(STRING_LITERAL422);

			// AST REWRITE
			// elements: INSTRUCTION_FORMAT31c, REGISTER, STRING_LITERAL
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1031:5: -> ^( I_STATEMENT_FORMAT31c[$start, \"I_STATEMENT_FORMAT31c\"] INSTRUCTION_FORMAT31c REGISTER STRING_LITERAL )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1031:7: ^( I_STATEMENT_FORMAT31c[$start, \"I_STATEMENT_FORMAT31c\"] INSTRUCTION_FORMAT31c REGISTER STRING_LITERAL )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT31c, (retval.start), "I_STATEMENT_FORMAT31c"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT31c.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format31c"


	public static class insn_format31i_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format31i"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1033:1: insn_format31i : instruction_format31i REGISTER COMMA fixed_32bit_literal -> ^( I_STATEMENT_FORMAT31i[$start, \"I_STATEMENT_FORMAT31i\"] instruction_format31i REGISTER fixed_32bit_literal ) ;
	public final smaliParser.insn_format31i_return insn_format31i() throws RecognitionException {
		smaliParser.insn_format31i_return retval = new smaliParser.insn_format31i_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token REGISTER424=null;
		Token COMMA425=null;
		ParserRuleReturnScope instruction_format31i423 =null;
		ParserRuleReturnScope fixed_32bit_literal426 =null;

		CommonTree REGISTER424_tree=null;
		CommonTree COMMA425_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_instruction_format31i=new RewriteRuleSubtreeStream(adaptor,"rule instruction_format31i");
		RewriteRuleSubtreeStream stream_fixed_32bit_literal=new RewriteRuleSubtreeStream(adaptor,"rule fixed_32bit_literal");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1034:3: ( instruction_format31i REGISTER COMMA fixed_32bit_literal -> ^( I_STATEMENT_FORMAT31i[$start, \"I_STATEMENT_FORMAT31i\"] instruction_format31i REGISTER fixed_32bit_literal ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1035:5: instruction_format31i REGISTER COMMA fixed_32bit_literal
			{
			pushFollow(FOLLOW_instruction_format31i_in_insn_format31i5294);
			instruction_format31i423=instruction_format31i();
			state._fsp--;

			stream_instruction_format31i.add(instruction_format31i423.getTree());
			REGISTER424=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format31i5296);  
			stream_REGISTER.add(REGISTER424);

			COMMA425=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format31i5298);  
			stream_COMMA.add(COMMA425);

			pushFollow(FOLLOW_fixed_32bit_literal_in_insn_format31i5300);
			fixed_32bit_literal426=fixed_32bit_literal();
			state._fsp--;

			stream_fixed_32bit_literal.add(fixed_32bit_literal426.getTree());
			// AST REWRITE
			// elements: fixed_32bit_literal, REGISTER, instruction_format31i
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1036:5: -> ^( I_STATEMENT_FORMAT31i[$start, \"I_STATEMENT_FORMAT31i\"] instruction_format31i REGISTER fixed_32bit_literal )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1036:8: ^( I_STATEMENT_FORMAT31i[$start, \"I_STATEMENT_FORMAT31i\"] instruction_format31i REGISTER fixed_32bit_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT31i, (retval.start), "I_STATEMENT_FORMAT31i"), root_1);
				adaptor.addChild(root_1, stream_instruction_format31i.nextTree());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_fixed_32bit_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format31i"


	public static class insn_format31t_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format31t"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1038:1: insn_format31t : INSTRUCTION_FORMAT31t REGISTER COMMA label_ref -> ^( I_STATEMENT_FORMAT31t[$start, \"I_STATEMENT_FORMAT31t\"] INSTRUCTION_FORMAT31t REGISTER label_ref ) ;
	public final smaliParser.insn_format31t_return insn_format31t() throws RecognitionException {
		smaliParser.insn_format31t_return retval = new smaliParser.insn_format31t_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT31t427=null;
		Token REGISTER428=null;
		Token COMMA429=null;
		ParserRuleReturnScope label_ref430 =null;

		CommonTree INSTRUCTION_FORMAT31t427_tree=null;
		CommonTree REGISTER428_tree=null;
		CommonTree COMMA429_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT31t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT31t");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1039:3: ( INSTRUCTION_FORMAT31t REGISTER COMMA label_ref -> ^( I_STATEMENT_FORMAT31t[$start, \"I_STATEMENT_FORMAT31t\"] INSTRUCTION_FORMAT31t REGISTER label_ref ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1040:5: INSTRUCTION_FORMAT31t REGISTER COMMA label_ref
			{
			INSTRUCTION_FORMAT31t427=(Token)match(input,INSTRUCTION_FORMAT31t,FOLLOW_INSTRUCTION_FORMAT31t_in_insn_format31t5332);  
			stream_INSTRUCTION_FORMAT31t.add(INSTRUCTION_FORMAT31t427);

			REGISTER428=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format31t5334);  
			stream_REGISTER.add(REGISTER428);

			COMMA429=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format31t5336);  
			stream_COMMA.add(COMMA429);

			pushFollow(FOLLOW_label_ref_in_insn_format31t5338);
			label_ref430=label_ref();
			state._fsp--;

			stream_label_ref.add(label_ref430.getTree());
			// AST REWRITE
			// elements: label_ref, INSTRUCTION_FORMAT31t, REGISTER
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1041:5: -> ^( I_STATEMENT_FORMAT31t[$start, \"I_STATEMENT_FORMAT31t\"] INSTRUCTION_FORMAT31t REGISTER label_ref )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1041:8: ^( I_STATEMENT_FORMAT31t[$start, \"I_STATEMENT_FORMAT31t\"] INSTRUCTION_FORMAT31t REGISTER label_ref )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT31t, (retval.start), "I_STATEMENT_FORMAT31t"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT31t.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_label_ref.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format31t"


	public static class insn_format32x_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format32x"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1043:1: insn_format32x : INSTRUCTION_FORMAT32x REGISTER COMMA REGISTER -> ^( I_STATEMENT_FORMAT32x[$start, \"I_STATEMENT_FORMAT32x\"] INSTRUCTION_FORMAT32x REGISTER REGISTER ) ;
	public final smaliParser.insn_format32x_return insn_format32x() throws RecognitionException {
		smaliParser.insn_format32x_return retval = new smaliParser.insn_format32x_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT32x431=null;
		Token REGISTER432=null;
		Token COMMA433=null;
		Token REGISTER434=null;

		CommonTree INSTRUCTION_FORMAT32x431_tree=null;
		CommonTree REGISTER432_tree=null;
		CommonTree COMMA433_tree=null;
		CommonTree REGISTER434_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT32x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT32x");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1044:3: ( INSTRUCTION_FORMAT32x REGISTER COMMA REGISTER -> ^( I_STATEMENT_FORMAT32x[$start, \"I_STATEMENT_FORMAT32x\"] INSTRUCTION_FORMAT32x REGISTER REGISTER ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1045:5: INSTRUCTION_FORMAT32x REGISTER COMMA REGISTER
			{
			INSTRUCTION_FORMAT32x431=(Token)match(input,INSTRUCTION_FORMAT32x,FOLLOW_INSTRUCTION_FORMAT32x_in_insn_format32x5370);  
			stream_INSTRUCTION_FORMAT32x.add(INSTRUCTION_FORMAT32x431);

			REGISTER432=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format32x5372);  
			stream_REGISTER.add(REGISTER432);

			COMMA433=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format32x5374);  
			stream_COMMA.add(COMMA433);

			REGISTER434=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format32x5376);  
			stream_REGISTER.add(REGISTER434);

			// AST REWRITE
			// elements: REGISTER, REGISTER, INSTRUCTION_FORMAT32x
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1046:5: -> ^( I_STATEMENT_FORMAT32x[$start, \"I_STATEMENT_FORMAT32x\"] INSTRUCTION_FORMAT32x REGISTER REGISTER )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1046:8: ^( I_STATEMENT_FORMAT32x[$start, \"I_STATEMENT_FORMAT32x\"] INSTRUCTION_FORMAT32x REGISTER REGISTER )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT32x, (retval.start), "I_STATEMENT_FORMAT32x"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT32x.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format32x"


	public static class insn_format35c_method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format35c_method"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1048:1: insn_format35c_method : INSTRUCTION_FORMAT35c_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference -> ^( I_STATEMENT_FORMAT35c_METHOD[$start, \"I_STATEMENT_FORMAT35c_METHOD\"] INSTRUCTION_FORMAT35c_METHOD register_list method_reference ) ;
	public final smaliParser.insn_format35c_method_return insn_format35c_method() throws RecognitionException {
		smaliParser.insn_format35c_method_return retval = new smaliParser.insn_format35c_method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT35c_METHOD435=null;
		Token OPEN_BRACE436=null;
		Token CLOSE_BRACE438=null;
		Token COMMA439=null;
		ParserRuleReturnScope register_list437 =null;
		ParserRuleReturnScope method_reference440 =null;

		CommonTree INSTRUCTION_FORMAT35c_METHOD435_tree=null;
		CommonTree OPEN_BRACE436_tree=null;
		CommonTree CLOSE_BRACE438_tree=null;
		CommonTree COMMA439_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_METHOD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35c_METHOD");
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleSubtreeStream stream_register_list=new RewriteRuleSubtreeStream(adaptor,"rule register_list");
		RewriteRuleSubtreeStream stream_method_reference=new RewriteRuleSubtreeStream(adaptor,"rule method_reference");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1049:3: ( INSTRUCTION_FORMAT35c_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference -> ^( I_STATEMENT_FORMAT35c_METHOD[$start, \"I_STATEMENT_FORMAT35c_METHOD\"] INSTRUCTION_FORMAT35c_METHOD register_list method_reference ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1050:5: INSTRUCTION_FORMAT35c_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference
			{
			INSTRUCTION_FORMAT35c_METHOD435=(Token)match(input,INSTRUCTION_FORMAT35c_METHOD,FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_insn_format35c_method5408);  
			stream_INSTRUCTION_FORMAT35c_METHOD.add(INSTRUCTION_FORMAT35c_METHOD435);

			OPEN_BRACE436=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format35c_method5410);  
			stream_OPEN_BRACE.add(OPEN_BRACE436);

			pushFollow(FOLLOW_register_list_in_insn_format35c_method5412);
			register_list437=register_list();
			state._fsp--;

			stream_register_list.add(register_list437.getTree());
			CLOSE_BRACE438=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format35c_method5414);  
			stream_CLOSE_BRACE.add(CLOSE_BRACE438);

			COMMA439=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format35c_method5416);  
			stream_COMMA.add(COMMA439);

			pushFollow(FOLLOW_method_reference_in_insn_format35c_method5418);
			method_reference440=method_reference();
			state._fsp--;

			stream_method_reference.add(method_reference440.getTree());
			// AST REWRITE
			// elements: register_list, INSTRUCTION_FORMAT35c_METHOD, method_reference
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1051:5: -> ^( I_STATEMENT_FORMAT35c_METHOD[$start, \"I_STATEMENT_FORMAT35c_METHOD\"] INSTRUCTION_FORMAT35c_METHOD register_list method_reference )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1051:8: ^( I_STATEMENT_FORMAT35c_METHOD[$start, \"I_STATEMENT_FORMAT35c_METHOD\"] INSTRUCTION_FORMAT35c_METHOD register_list method_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT35c_METHOD, (retval.start), "I_STATEMENT_FORMAT35c_METHOD"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT35c_METHOD.nextNode());
				adaptor.addChild(root_1, stream_register_list.nextTree());
				adaptor.addChild(root_1, stream_method_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format35c_method"


	public static class insn_format35c_type_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format35c_type"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1053:1: insn_format35c_type : INSTRUCTION_FORMAT35c_TYPE OPEN_BRACE register_list CLOSE_BRACE COMMA nonvoid_type_descriptor -> ^( I_STATEMENT_FORMAT35c_TYPE[$start, \"I_STATEMENT_FORMAT35c_TYPE\"] INSTRUCTION_FORMAT35c_TYPE register_list nonvoid_type_descriptor ) ;
	public final smaliParser.insn_format35c_type_return insn_format35c_type() throws RecognitionException {
		smaliParser.insn_format35c_type_return retval = new smaliParser.insn_format35c_type_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT35c_TYPE441=null;
		Token OPEN_BRACE442=null;
		Token CLOSE_BRACE444=null;
		Token COMMA445=null;
		ParserRuleReturnScope register_list443 =null;
		ParserRuleReturnScope nonvoid_type_descriptor446 =null;

		CommonTree INSTRUCTION_FORMAT35c_TYPE441_tree=null;
		CommonTree OPEN_BRACE442_tree=null;
		CommonTree CLOSE_BRACE444_tree=null;
		CommonTree COMMA445_tree=null;
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35c_TYPE");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");
		RewriteRuleSubtreeStream stream_register_list=new RewriteRuleSubtreeStream(adaptor,"rule register_list");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1054:3: ( INSTRUCTION_FORMAT35c_TYPE OPEN_BRACE register_list CLOSE_BRACE COMMA nonvoid_type_descriptor -> ^( I_STATEMENT_FORMAT35c_TYPE[$start, \"I_STATEMENT_FORMAT35c_TYPE\"] INSTRUCTION_FORMAT35c_TYPE register_list nonvoid_type_descriptor ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1055:5: INSTRUCTION_FORMAT35c_TYPE OPEN_BRACE register_list CLOSE_BRACE COMMA nonvoid_type_descriptor
			{
			INSTRUCTION_FORMAT35c_TYPE441=(Token)match(input,INSTRUCTION_FORMAT35c_TYPE,FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_insn_format35c_type5450);  
			stream_INSTRUCTION_FORMAT35c_TYPE.add(INSTRUCTION_FORMAT35c_TYPE441);

			OPEN_BRACE442=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format35c_type5452);  
			stream_OPEN_BRACE.add(OPEN_BRACE442);

			pushFollow(FOLLOW_register_list_in_insn_format35c_type5454);
			register_list443=register_list();
			state._fsp--;

			stream_register_list.add(register_list443.getTree());
			CLOSE_BRACE444=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format35c_type5456);  
			stream_CLOSE_BRACE.add(CLOSE_BRACE444);

			COMMA445=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format35c_type5458);  
			stream_COMMA.add(COMMA445);

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format35c_type5460);
			nonvoid_type_descriptor446=nonvoid_type_descriptor();
			state._fsp--;

			stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor446.getTree());
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT35c_TYPE, nonvoid_type_descriptor, register_list
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1056:5: -> ^( I_STATEMENT_FORMAT35c_TYPE[$start, \"I_STATEMENT_FORMAT35c_TYPE\"] INSTRUCTION_FORMAT35c_TYPE register_list nonvoid_type_descriptor )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1056:8: ^( I_STATEMENT_FORMAT35c_TYPE[$start, \"I_STATEMENT_FORMAT35c_TYPE\"] INSTRUCTION_FORMAT35c_TYPE register_list nonvoid_type_descriptor )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT35c_TYPE, (retval.start), "I_STATEMENT_FORMAT35c_TYPE"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT35c_TYPE.nextNode());
				adaptor.addChild(root_1, stream_register_list.nextTree());
				adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format35c_type"


	public static class insn_format35c_method_odex_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format35c_method_odex"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1058:1: insn_format35c_method_odex : INSTRUCTION_FORMAT35c_METHOD_ODEX OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference ;
	public final smaliParser.insn_format35c_method_odex_return insn_format35c_method_odex() throws RecognitionException {
		smaliParser.insn_format35c_method_odex_return retval = new smaliParser.insn_format35c_method_odex_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT35c_METHOD_ODEX447=null;
		Token OPEN_BRACE448=null;
		Token CLOSE_BRACE450=null;
		Token COMMA451=null;
		ParserRuleReturnScope register_list449 =null;
		ParserRuleReturnScope method_reference452 =null;

		CommonTree INSTRUCTION_FORMAT35c_METHOD_ODEX447_tree=null;
		CommonTree OPEN_BRACE448_tree=null;
		CommonTree CLOSE_BRACE450_tree=null;
		CommonTree COMMA451_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1059:3: ( INSTRUCTION_FORMAT35c_METHOD_ODEX OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1060:5: INSTRUCTION_FORMAT35c_METHOD_ODEX OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference
			{
			root_0 = (CommonTree)adaptor.nil();


			INSTRUCTION_FORMAT35c_METHOD_ODEX447=(Token)match(input,INSTRUCTION_FORMAT35c_METHOD_ODEX,FOLLOW_INSTRUCTION_FORMAT35c_METHOD_ODEX_in_insn_format35c_method_odex5492); 
			INSTRUCTION_FORMAT35c_METHOD_ODEX447_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT35c_METHOD_ODEX447);
			adaptor.addChild(root_0, INSTRUCTION_FORMAT35c_METHOD_ODEX447_tree);

			OPEN_BRACE448=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format35c_method_odex5494); 
			OPEN_BRACE448_tree = (CommonTree)adaptor.create(OPEN_BRACE448);
			adaptor.addChild(root_0, OPEN_BRACE448_tree);

			pushFollow(FOLLOW_register_list_in_insn_format35c_method_odex5496);
			register_list449=register_list();
			state._fsp--;

			adaptor.addChild(root_0, register_list449.getTree());

			CLOSE_BRACE450=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format35c_method_odex5498); 
			CLOSE_BRACE450_tree = (CommonTree)adaptor.create(CLOSE_BRACE450);
			adaptor.addChild(root_0, CLOSE_BRACE450_tree);

			COMMA451=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format35c_method_odex5500); 
			COMMA451_tree = (CommonTree)adaptor.create(COMMA451);
			adaptor.addChild(root_0, COMMA451_tree);

			pushFollow(FOLLOW_method_reference_in_insn_format35c_method_odex5502);
			method_reference452=method_reference();
			state._fsp--;

			adaptor.addChild(root_0, method_reference452.getTree());


			      throwOdexedInstructionException(input, (INSTRUCTION_FORMAT35c_METHOD_ODEX447!=null?INSTRUCTION_FORMAT35c_METHOD_ODEX447.getText():null));
			    
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format35c_method_odex"


	public static class insn_format35mi_method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format35mi_method"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1065:1: insn_format35mi_method : INSTRUCTION_FORMAT35mi_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA INLINE_INDEX ;
	public final smaliParser.insn_format35mi_method_return insn_format35mi_method() throws RecognitionException {
		smaliParser.insn_format35mi_method_return retval = new smaliParser.insn_format35mi_method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT35mi_METHOD453=null;
		Token OPEN_BRACE454=null;
		Token CLOSE_BRACE456=null;
		Token COMMA457=null;
		Token INLINE_INDEX458=null;
		ParserRuleReturnScope register_list455 =null;

		CommonTree INSTRUCTION_FORMAT35mi_METHOD453_tree=null;
		CommonTree OPEN_BRACE454_tree=null;
		CommonTree CLOSE_BRACE456_tree=null;
		CommonTree COMMA457_tree=null;
		CommonTree INLINE_INDEX458_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1066:3: ( INSTRUCTION_FORMAT35mi_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA INLINE_INDEX )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1067:5: INSTRUCTION_FORMAT35mi_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA INLINE_INDEX
			{
			root_0 = (CommonTree)adaptor.nil();


			INSTRUCTION_FORMAT35mi_METHOD453=(Token)match(input,INSTRUCTION_FORMAT35mi_METHOD,FOLLOW_INSTRUCTION_FORMAT35mi_METHOD_in_insn_format35mi_method5523); 
			INSTRUCTION_FORMAT35mi_METHOD453_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT35mi_METHOD453);
			adaptor.addChild(root_0, INSTRUCTION_FORMAT35mi_METHOD453_tree);

			OPEN_BRACE454=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format35mi_method5525); 
			OPEN_BRACE454_tree = (CommonTree)adaptor.create(OPEN_BRACE454);
			adaptor.addChild(root_0, OPEN_BRACE454_tree);

			pushFollow(FOLLOW_register_list_in_insn_format35mi_method5527);
			register_list455=register_list();
			state._fsp--;

			adaptor.addChild(root_0, register_list455.getTree());

			CLOSE_BRACE456=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format35mi_method5529); 
			CLOSE_BRACE456_tree = (CommonTree)adaptor.create(CLOSE_BRACE456);
			adaptor.addChild(root_0, CLOSE_BRACE456_tree);

			COMMA457=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format35mi_method5531); 
			COMMA457_tree = (CommonTree)adaptor.create(COMMA457);
			adaptor.addChild(root_0, COMMA457_tree);

			INLINE_INDEX458=(Token)match(input,INLINE_INDEX,FOLLOW_INLINE_INDEX_in_insn_format35mi_method5533); 
			INLINE_INDEX458_tree = (CommonTree)adaptor.create(INLINE_INDEX458);
			adaptor.addChild(root_0, INLINE_INDEX458_tree);


			      throwOdexedInstructionException(input, (INSTRUCTION_FORMAT35mi_METHOD453!=null?INSTRUCTION_FORMAT35mi_METHOD453.getText():null));
			    
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format35mi_method"


	public static class insn_format35ms_method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format35ms_method"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1072:1: insn_format35ms_method : INSTRUCTION_FORMAT35ms_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA VTABLE_INDEX ;
	public final smaliParser.insn_format35ms_method_return insn_format35ms_method() throws RecognitionException {
		smaliParser.insn_format35ms_method_return retval = new smaliParser.insn_format35ms_method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT35ms_METHOD459=null;
		Token OPEN_BRACE460=null;
		Token CLOSE_BRACE462=null;
		Token COMMA463=null;
		Token VTABLE_INDEX464=null;
		ParserRuleReturnScope register_list461 =null;

		CommonTree INSTRUCTION_FORMAT35ms_METHOD459_tree=null;
		CommonTree OPEN_BRACE460_tree=null;
		CommonTree CLOSE_BRACE462_tree=null;
		CommonTree COMMA463_tree=null;
		CommonTree VTABLE_INDEX464_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1073:3: ( INSTRUCTION_FORMAT35ms_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA VTABLE_INDEX )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1074:5: INSTRUCTION_FORMAT35ms_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA VTABLE_INDEX
			{
			root_0 = (CommonTree)adaptor.nil();


			INSTRUCTION_FORMAT35ms_METHOD459=(Token)match(input,INSTRUCTION_FORMAT35ms_METHOD,FOLLOW_INSTRUCTION_FORMAT35ms_METHOD_in_insn_format35ms_method5554); 
			INSTRUCTION_FORMAT35ms_METHOD459_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT35ms_METHOD459);
			adaptor.addChild(root_0, INSTRUCTION_FORMAT35ms_METHOD459_tree);

			OPEN_BRACE460=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format35ms_method5556); 
			OPEN_BRACE460_tree = (CommonTree)adaptor.create(OPEN_BRACE460);
			adaptor.addChild(root_0, OPEN_BRACE460_tree);

			pushFollow(FOLLOW_register_list_in_insn_format35ms_method5558);
			register_list461=register_list();
			state._fsp--;

			adaptor.addChild(root_0, register_list461.getTree());

			CLOSE_BRACE462=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format35ms_method5560); 
			CLOSE_BRACE462_tree = (CommonTree)adaptor.create(CLOSE_BRACE462);
			adaptor.addChild(root_0, CLOSE_BRACE462_tree);

			COMMA463=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format35ms_method5562); 
			COMMA463_tree = (CommonTree)adaptor.create(COMMA463);
			adaptor.addChild(root_0, COMMA463_tree);

			VTABLE_INDEX464=(Token)match(input,VTABLE_INDEX,FOLLOW_VTABLE_INDEX_in_insn_format35ms_method5564); 
			VTABLE_INDEX464_tree = (CommonTree)adaptor.create(VTABLE_INDEX464);
			adaptor.addChild(root_0, VTABLE_INDEX464_tree);


			      throwOdexedInstructionException(input, (INSTRUCTION_FORMAT35ms_METHOD459!=null?INSTRUCTION_FORMAT35ms_METHOD459.getText():null));
			    
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format35ms_method"


	public static class insn_format3rc_method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format3rc_method"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1079:1: insn_format3rc_method : INSTRUCTION_FORMAT3rc_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA method_reference -> ^( I_STATEMENT_FORMAT3rc_METHOD[$start, \"I_STATEMENT_FORMAT3rc_METHOD\"] INSTRUCTION_FORMAT3rc_METHOD register_range method_reference ) ;
	public final smaliParser.insn_format3rc_method_return insn_format3rc_method() throws RecognitionException {
		smaliParser.insn_format3rc_method_return retval = new smaliParser.insn_format3rc_method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT3rc_METHOD465=null;
		Token OPEN_BRACE466=null;
		Token CLOSE_BRACE468=null;
		Token COMMA469=null;
		ParserRuleReturnScope register_range467 =null;
		ParserRuleReturnScope method_reference470 =null;

		CommonTree INSTRUCTION_FORMAT3rc_METHOD465_tree=null;
		CommonTree OPEN_BRACE466_tree=null;
		CommonTree CLOSE_BRACE468_tree=null;
		CommonTree COMMA469_tree=null;
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT3rc_METHOD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT3rc_METHOD");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleSubtreeStream stream_method_reference=new RewriteRuleSubtreeStream(adaptor,"rule method_reference");
		RewriteRuleSubtreeStream stream_register_range=new RewriteRuleSubtreeStream(adaptor,"rule register_range");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1080:3: ( INSTRUCTION_FORMAT3rc_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA method_reference -> ^( I_STATEMENT_FORMAT3rc_METHOD[$start, \"I_STATEMENT_FORMAT3rc_METHOD\"] INSTRUCTION_FORMAT3rc_METHOD register_range method_reference ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1081:5: INSTRUCTION_FORMAT3rc_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA method_reference
			{
			INSTRUCTION_FORMAT3rc_METHOD465=(Token)match(input,INSTRUCTION_FORMAT3rc_METHOD,FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_in_insn_format3rc_method5585);  
			stream_INSTRUCTION_FORMAT3rc_METHOD.add(INSTRUCTION_FORMAT3rc_METHOD465);

			OPEN_BRACE466=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format3rc_method5587);  
			stream_OPEN_BRACE.add(OPEN_BRACE466);

			pushFollow(FOLLOW_register_range_in_insn_format3rc_method5589);
			register_range467=register_range();
			state._fsp--;

			stream_register_range.add(register_range467.getTree());
			CLOSE_BRACE468=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format3rc_method5591);  
			stream_CLOSE_BRACE.add(CLOSE_BRACE468);

			COMMA469=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format3rc_method5593);  
			stream_COMMA.add(COMMA469);

			pushFollow(FOLLOW_method_reference_in_insn_format3rc_method5595);
			method_reference470=method_reference();
			state._fsp--;

			stream_method_reference.add(method_reference470.getTree());
			// AST REWRITE
			// elements: register_range, method_reference, INSTRUCTION_FORMAT3rc_METHOD
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1082:5: -> ^( I_STATEMENT_FORMAT3rc_METHOD[$start, \"I_STATEMENT_FORMAT3rc_METHOD\"] INSTRUCTION_FORMAT3rc_METHOD register_range method_reference )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1082:8: ^( I_STATEMENT_FORMAT3rc_METHOD[$start, \"I_STATEMENT_FORMAT3rc_METHOD\"] INSTRUCTION_FORMAT3rc_METHOD register_range method_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT3rc_METHOD, (retval.start), "I_STATEMENT_FORMAT3rc_METHOD"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT3rc_METHOD.nextNode());
				adaptor.addChild(root_1, stream_register_range.nextTree());
				adaptor.addChild(root_1, stream_method_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format3rc_method"


	public static class insn_format3rc_method_odex_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format3rc_method_odex"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1084:1: insn_format3rc_method_odex : INSTRUCTION_FORMAT3rc_METHOD_ODEX OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference ;
	public final smaliParser.insn_format3rc_method_odex_return insn_format3rc_method_odex() throws RecognitionException {
		smaliParser.insn_format3rc_method_odex_return retval = new smaliParser.insn_format3rc_method_odex_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT3rc_METHOD_ODEX471=null;
		Token OPEN_BRACE472=null;
		Token CLOSE_BRACE474=null;
		Token COMMA475=null;
		ParserRuleReturnScope register_list473 =null;
		ParserRuleReturnScope method_reference476 =null;

		CommonTree INSTRUCTION_FORMAT3rc_METHOD_ODEX471_tree=null;
		CommonTree OPEN_BRACE472_tree=null;
		CommonTree CLOSE_BRACE474_tree=null;
		CommonTree COMMA475_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1085:3: ( INSTRUCTION_FORMAT3rc_METHOD_ODEX OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1086:5: INSTRUCTION_FORMAT3rc_METHOD_ODEX OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference
			{
			root_0 = (CommonTree)adaptor.nil();


			INSTRUCTION_FORMAT3rc_METHOD_ODEX471=(Token)match(input,INSTRUCTION_FORMAT3rc_METHOD_ODEX,FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_ODEX_in_insn_format3rc_method_odex5627); 
			INSTRUCTION_FORMAT3rc_METHOD_ODEX471_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT3rc_METHOD_ODEX471);
			adaptor.addChild(root_0, INSTRUCTION_FORMAT3rc_METHOD_ODEX471_tree);

			OPEN_BRACE472=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format3rc_method_odex5629); 
			OPEN_BRACE472_tree = (CommonTree)adaptor.create(OPEN_BRACE472);
			adaptor.addChild(root_0, OPEN_BRACE472_tree);

			pushFollow(FOLLOW_register_list_in_insn_format3rc_method_odex5631);
			register_list473=register_list();
			state._fsp--;

			adaptor.addChild(root_0, register_list473.getTree());

			CLOSE_BRACE474=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format3rc_method_odex5633); 
			CLOSE_BRACE474_tree = (CommonTree)adaptor.create(CLOSE_BRACE474);
			adaptor.addChild(root_0, CLOSE_BRACE474_tree);

			COMMA475=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format3rc_method_odex5635); 
			COMMA475_tree = (CommonTree)adaptor.create(COMMA475);
			adaptor.addChild(root_0, COMMA475_tree);

			pushFollow(FOLLOW_method_reference_in_insn_format3rc_method_odex5637);
			method_reference476=method_reference();
			state._fsp--;

			adaptor.addChild(root_0, method_reference476.getTree());


			      throwOdexedInstructionException(input, (INSTRUCTION_FORMAT3rc_METHOD_ODEX471!=null?INSTRUCTION_FORMAT3rc_METHOD_ODEX471.getText():null));
			    
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format3rc_method_odex"


	public static class insn_format3rc_type_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format3rc_type"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1091:1: insn_format3rc_type : INSTRUCTION_FORMAT3rc_TYPE OPEN_BRACE register_range CLOSE_BRACE COMMA nonvoid_type_descriptor -> ^( I_STATEMENT_FORMAT3rc_TYPE[$start, \"I_STATEMENT_FORMAT3rc_TYPE\"] INSTRUCTION_FORMAT3rc_TYPE register_range nonvoid_type_descriptor ) ;
	public final smaliParser.insn_format3rc_type_return insn_format3rc_type() throws RecognitionException {
		smaliParser.insn_format3rc_type_return retval = new smaliParser.insn_format3rc_type_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT3rc_TYPE477=null;
		Token OPEN_BRACE478=null;
		Token CLOSE_BRACE480=null;
		Token COMMA481=null;
		ParserRuleReturnScope register_range479 =null;
		ParserRuleReturnScope nonvoid_type_descriptor482 =null;

		CommonTree INSTRUCTION_FORMAT3rc_TYPE477_tree=null;
		CommonTree OPEN_BRACE478_tree=null;
		CommonTree CLOSE_BRACE480_tree=null;
		CommonTree COMMA481_tree=null;
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT3rc_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT3rc_TYPE");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");
		RewriteRuleSubtreeStream stream_register_range=new RewriteRuleSubtreeStream(adaptor,"rule register_range");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1092:3: ( INSTRUCTION_FORMAT3rc_TYPE OPEN_BRACE register_range CLOSE_BRACE COMMA nonvoid_type_descriptor -> ^( I_STATEMENT_FORMAT3rc_TYPE[$start, \"I_STATEMENT_FORMAT3rc_TYPE\"] INSTRUCTION_FORMAT3rc_TYPE register_range nonvoid_type_descriptor ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1093:5: INSTRUCTION_FORMAT3rc_TYPE OPEN_BRACE register_range CLOSE_BRACE COMMA nonvoid_type_descriptor
			{
			INSTRUCTION_FORMAT3rc_TYPE477=(Token)match(input,INSTRUCTION_FORMAT3rc_TYPE,FOLLOW_INSTRUCTION_FORMAT3rc_TYPE_in_insn_format3rc_type5658);  
			stream_INSTRUCTION_FORMAT3rc_TYPE.add(INSTRUCTION_FORMAT3rc_TYPE477);

			OPEN_BRACE478=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format3rc_type5660);  
			stream_OPEN_BRACE.add(OPEN_BRACE478);

			pushFollow(FOLLOW_register_range_in_insn_format3rc_type5662);
			register_range479=register_range();
			state._fsp--;

			stream_register_range.add(register_range479.getTree());
			CLOSE_BRACE480=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format3rc_type5664);  
			stream_CLOSE_BRACE.add(CLOSE_BRACE480);

			COMMA481=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format3rc_type5666);  
			stream_COMMA.add(COMMA481);

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format3rc_type5668);
			nonvoid_type_descriptor482=nonvoid_type_descriptor();
			state._fsp--;

			stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor482.getTree());
			// AST REWRITE
			// elements: register_range, INSTRUCTION_FORMAT3rc_TYPE, nonvoid_type_descriptor
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1094:5: -> ^( I_STATEMENT_FORMAT3rc_TYPE[$start, \"I_STATEMENT_FORMAT3rc_TYPE\"] INSTRUCTION_FORMAT3rc_TYPE register_range nonvoid_type_descriptor )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1094:8: ^( I_STATEMENT_FORMAT3rc_TYPE[$start, \"I_STATEMENT_FORMAT3rc_TYPE\"] INSTRUCTION_FORMAT3rc_TYPE register_range nonvoid_type_descriptor )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT3rc_TYPE, (retval.start), "I_STATEMENT_FORMAT3rc_TYPE"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT3rc_TYPE.nextNode());
				adaptor.addChild(root_1, stream_register_range.nextTree());
				adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format3rc_type"


	public static class insn_format3rmi_method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format3rmi_method"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1096:1: insn_format3rmi_method : INSTRUCTION_FORMAT3rmi_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA INLINE_INDEX ;
	public final smaliParser.insn_format3rmi_method_return insn_format3rmi_method() throws RecognitionException {
		smaliParser.insn_format3rmi_method_return retval = new smaliParser.insn_format3rmi_method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT3rmi_METHOD483=null;
		Token OPEN_BRACE484=null;
		Token CLOSE_BRACE486=null;
		Token COMMA487=null;
		Token INLINE_INDEX488=null;
		ParserRuleReturnScope register_range485 =null;

		CommonTree INSTRUCTION_FORMAT3rmi_METHOD483_tree=null;
		CommonTree OPEN_BRACE484_tree=null;
		CommonTree CLOSE_BRACE486_tree=null;
		CommonTree COMMA487_tree=null;
		CommonTree INLINE_INDEX488_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1097:3: ( INSTRUCTION_FORMAT3rmi_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA INLINE_INDEX )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1098:5: INSTRUCTION_FORMAT3rmi_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA INLINE_INDEX
			{
			root_0 = (CommonTree)adaptor.nil();


			INSTRUCTION_FORMAT3rmi_METHOD483=(Token)match(input,INSTRUCTION_FORMAT3rmi_METHOD,FOLLOW_INSTRUCTION_FORMAT3rmi_METHOD_in_insn_format3rmi_method5700); 
			INSTRUCTION_FORMAT3rmi_METHOD483_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT3rmi_METHOD483);
			adaptor.addChild(root_0, INSTRUCTION_FORMAT3rmi_METHOD483_tree);

			OPEN_BRACE484=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format3rmi_method5702); 
			OPEN_BRACE484_tree = (CommonTree)adaptor.create(OPEN_BRACE484);
			adaptor.addChild(root_0, OPEN_BRACE484_tree);

			pushFollow(FOLLOW_register_range_in_insn_format3rmi_method5704);
			register_range485=register_range();
			state._fsp--;

			adaptor.addChild(root_0, register_range485.getTree());

			CLOSE_BRACE486=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format3rmi_method5706); 
			CLOSE_BRACE486_tree = (CommonTree)adaptor.create(CLOSE_BRACE486);
			adaptor.addChild(root_0, CLOSE_BRACE486_tree);

			COMMA487=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format3rmi_method5708); 
			COMMA487_tree = (CommonTree)adaptor.create(COMMA487);
			adaptor.addChild(root_0, COMMA487_tree);

			INLINE_INDEX488=(Token)match(input,INLINE_INDEX,FOLLOW_INLINE_INDEX_in_insn_format3rmi_method5710); 
			INLINE_INDEX488_tree = (CommonTree)adaptor.create(INLINE_INDEX488);
			adaptor.addChild(root_0, INLINE_INDEX488_tree);


			      throwOdexedInstructionException(input, (INSTRUCTION_FORMAT3rmi_METHOD483!=null?INSTRUCTION_FORMAT3rmi_METHOD483.getText():null));
			    
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format3rmi_method"


	public static class insn_format3rms_method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format3rms_method"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1103:1: insn_format3rms_method : INSTRUCTION_FORMAT3rms_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA VTABLE_INDEX ;
	public final smaliParser.insn_format3rms_method_return insn_format3rms_method() throws RecognitionException {
		smaliParser.insn_format3rms_method_return retval = new smaliParser.insn_format3rms_method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT3rms_METHOD489=null;
		Token OPEN_BRACE490=null;
		Token CLOSE_BRACE492=null;
		Token COMMA493=null;
		Token VTABLE_INDEX494=null;
		ParserRuleReturnScope register_range491 =null;

		CommonTree INSTRUCTION_FORMAT3rms_METHOD489_tree=null;
		CommonTree OPEN_BRACE490_tree=null;
		CommonTree CLOSE_BRACE492_tree=null;
		CommonTree COMMA493_tree=null;
		CommonTree VTABLE_INDEX494_tree=null;

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1104:3: ( INSTRUCTION_FORMAT3rms_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA VTABLE_INDEX )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1105:5: INSTRUCTION_FORMAT3rms_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA VTABLE_INDEX
			{
			root_0 = (CommonTree)adaptor.nil();


			INSTRUCTION_FORMAT3rms_METHOD489=(Token)match(input,INSTRUCTION_FORMAT3rms_METHOD,FOLLOW_INSTRUCTION_FORMAT3rms_METHOD_in_insn_format3rms_method5731); 
			INSTRUCTION_FORMAT3rms_METHOD489_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT3rms_METHOD489);
			adaptor.addChild(root_0, INSTRUCTION_FORMAT3rms_METHOD489_tree);

			OPEN_BRACE490=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format3rms_method5733); 
			OPEN_BRACE490_tree = (CommonTree)adaptor.create(OPEN_BRACE490);
			adaptor.addChild(root_0, OPEN_BRACE490_tree);

			pushFollow(FOLLOW_register_range_in_insn_format3rms_method5735);
			register_range491=register_range();
			state._fsp--;

			adaptor.addChild(root_0, register_range491.getTree());

			CLOSE_BRACE492=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format3rms_method5737); 
			CLOSE_BRACE492_tree = (CommonTree)adaptor.create(CLOSE_BRACE492);
			adaptor.addChild(root_0, CLOSE_BRACE492_tree);

			COMMA493=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format3rms_method5739); 
			COMMA493_tree = (CommonTree)adaptor.create(COMMA493);
			adaptor.addChild(root_0, COMMA493_tree);

			VTABLE_INDEX494=(Token)match(input,VTABLE_INDEX,FOLLOW_VTABLE_INDEX_in_insn_format3rms_method5741); 
			VTABLE_INDEX494_tree = (CommonTree)adaptor.create(VTABLE_INDEX494);
			adaptor.addChild(root_0, VTABLE_INDEX494_tree);


			      throwOdexedInstructionException(input, (INSTRUCTION_FORMAT3rms_METHOD489!=null?INSTRUCTION_FORMAT3rms_METHOD489.getText():null));
			    
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format3rms_method"


	public static class insn_format51l_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format51l"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1110:1: insn_format51l : INSTRUCTION_FORMAT51l REGISTER COMMA fixed_literal -> ^( I_STATEMENT_FORMAT51l[$start, \"I_STATEMENT_FORMAT51l\"] INSTRUCTION_FORMAT51l REGISTER fixed_literal ) ;
	public final smaliParser.insn_format51l_return insn_format51l() throws RecognitionException {
		smaliParser.insn_format51l_return retval = new smaliParser.insn_format51l_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT51l495=null;
		Token REGISTER496=null;
		Token COMMA497=null;
		ParserRuleReturnScope fixed_literal498 =null;

		CommonTree INSTRUCTION_FORMAT51l495_tree=null;
		CommonTree REGISTER496_tree=null;
		CommonTree COMMA497_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT51l=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT51l");
		RewriteRuleSubtreeStream stream_fixed_literal=new RewriteRuleSubtreeStream(adaptor,"rule fixed_literal");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1111:3: ( INSTRUCTION_FORMAT51l REGISTER COMMA fixed_literal -> ^( I_STATEMENT_FORMAT51l[$start, \"I_STATEMENT_FORMAT51l\"] INSTRUCTION_FORMAT51l REGISTER fixed_literal ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1112:5: INSTRUCTION_FORMAT51l REGISTER COMMA fixed_literal
			{
			INSTRUCTION_FORMAT51l495=(Token)match(input,INSTRUCTION_FORMAT51l,FOLLOW_INSTRUCTION_FORMAT51l_in_insn_format51l5762);  
			stream_INSTRUCTION_FORMAT51l.add(INSTRUCTION_FORMAT51l495);

			REGISTER496=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format51l5764);  
			stream_REGISTER.add(REGISTER496);

			COMMA497=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format51l5766);  
			stream_COMMA.add(COMMA497);

			pushFollow(FOLLOW_fixed_literal_in_insn_format51l5768);
			fixed_literal498=fixed_literal();
			state._fsp--;

			stream_fixed_literal.add(fixed_literal498.getTree());
			// AST REWRITE
			// elements: fixed_literal, REGISTER, INSTRUCTION_FORMAT51l
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1113:5: -> ^( I_STATEMENT_FORMAT51l[$start, \"I_STATEMENT_FORMAT51l\"] INSTRUCTION_FORMAT51l REGISTER fixed_literal )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1113:8: ^( I_STATEMENT_FORMAT51l[$start, \"I_STATEMENT_FORMAT51l\"] INSTRUCTION_FORMAT51l REGISTER fixed_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT51l, (retval.start), "I_STATEMENT_FORMAT51l"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT51l.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_fixed_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format51l"


	public static class insn_array_data_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_array_data_directive"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1115:1: insn_array_data_directive : ARRAY_DATA_DIRECTIVE parsed_integer_literal ( fixed_literal )* END_ARRAY_DATA_DIRECTIVE -> ^( I_STATEMENT_ARRAY_DATA[$start, \"I_STATEMENT_ARRAY_DATA\"] ^( I_ARRAY_ELEMENT_SIZE parsed_integer_literal ) ^( I_ARRAY_ELEMENTS ( fixed_literal )* ) ) ;
	public final smaliParser.insn_array_data_directive_return insn_array_data_directive() throws RecognitionException {
		smaliParser.insn_array_data_directive_return retval = new smaliParser.insn_array_data_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ARRAY_DATA_DIRECTIVE499=null;
		Token END_ARRAY_DATA_DIRECTIVE502=null;
		ParserRuleReturnScope parsed_integer_literal500 =null;
		ParserRuleReturnScope fixed_literal501 =null;

		CommonTree ARRAY_DATA_DIRECTIVE499_tree=null;
		CommonTree END_ARRAY_DATA_DIRECTIVE502_tree=null;
		RewriteRuleTokenStream stream_ARRAY_DATA_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token ARRAY_DATA_DIRECTIVE");
		RewriteRuleTokenStream stream_END_ARRAY_DATA_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_ARRAY_DATA_DIRECTIVE");
		RewriteRuleSubtreeStream stream_fixed_literal=new RewriteRuleSubtreeStream(adaptor,"rule fixed_literal");
		RewriteRuleSubtreeStream stream_parsed_integer_literal=new RewriteRuleSubtreeStream(adaptor,"rule parsed_integer_literal");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1116:3: ( ARRAY_DATA_DIRECTIVE parsed_integer_literal ( fixed_literal )* END_ARRAY_DATA_DIRECTIVE -> ^( I_STATEMENT_ARRAY_DATA[$start, \"I_STATEMENT_ARRAY_DATA\"] ^( I_ARRAY_ELEMENT_SIZE parsed_integer_literal ) ^( I_ARRAY_ELEMENTS ( fixed_literal )* ) ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1116:5: ARRAY_DATA_DIRECTIVE parsed_integer_literal ( fixed_literal )* END_ARRAY_DATA_DIRECTIVE
			{
			ARRAY_DATA_DIRECTIVE499=(Token)match(input,ARRAY_DATA_DIRECTIVE,FOLLOW_ARRAY_DATA_DIRECTIVE_in_insn_array_data_directive5795);  
			stream_ARRAY_DATA_DIRECTIVE.add(ARRAY_DATA_DIRECTIVE499);

			pushFollow(FOLLOW_parsed_integer_literal_in_insn_array_data_directive5801);
			parsed_integer_literal500=parsed_integer_literal();
			state._fsp--;

			stream_parsed_integer_literal.add(parsed_integer_literal500.getTree());

			        int elementWidth = (parsed_integer_literal500!=null?((smaliParser.parsed_integer_literal_return)parsed_integer_literal500).value:0);
			        if (elementWidth != 4 && elementWidth != 8 && elementWidth != 1 && elementWidth != 2) {
			            throw new SemanticException(input, (retval.start), "Invalid element width: %d. Must be 1, 2, 4 or 8", elementWidth);
			        }
			    
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1124:5: ( fixed_literal )*
			loop50:
			while (true) {
				int alt50=2;
				int LA50_0 = input.LA(1);
				if ( ((LA50_0 >= BOOL_LITERAL && LA50_0 <= BYTE_LITERAL)||LA50_0==CHAR_LITERAL||(LA50_0 >= DOUBLE_LITERAL && LA50_0 <= DOUBLE_LITERAL_OR_ID)||(LA50_0 >= FLOAT_LITERAL && LA50_0 <= FLOAT_LITERAL_OR_ID)||LA50_0==LONG_LITERAL||LA50_0==NEGATIVE_INTEGER_LITERAL||LA50_0==POSITIVE_INTEGER_LITERAL||LA50_0==SHORT_LITERAL) ) {
					alt50=1;
				}

				switch (alt50) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1124:5: fixed_literal
					{
					pushFollow(FOLLOW_fixed_literal_in_insn_array_data_directive5813);
					fixed_literal501=fixed_literal();
					state._fsp--;

					stream_fixed_literal.add(fixed_literal501.getTree());
					}
					break;

				default :
					break loop50;
				}
			}

			END_ARRAY_DATA_DIRECTIVE502=(Token)match(input,END_ARRAY_DATA_DIRECTIVE,FOLLOW_END_ARRAY_DATA_DIRECTIVE_in_insn_array_data_directive5816);  
			stream_END_ARRAY_DATA_DIRECTIVE.add(END_ARRAY_DATA_DIRECTIVE502);

			// AST REWRITE
			// elements: fixed_literal, parsed_integer_literal
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1126:5: -> ^( I_STATEMENT_ARRAY_DATA[$start, \"I_STATEMENT_ARRAY_DATA\"] ^( I_ARRAY_ELEMENT_SIZE parsed_integer_literal ) ^( I_ARRAY_ELEMENTS ( fixed_literal )* ) )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1126:8: ^( I_STATEMENT_ARRAY_DATA[$start, \"I_STATEMENT_ARRAY_DATA\"] ^( I_ARRAY_ELEMENT_SIZE parsed_integer_literal ) ^( I_ARRAY_ELEMENTS ( fixed_literal )* ) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_ARRAY_DATA, (retval.start), "I_STATEMENT_ARRAY_DATA"), root_1);
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1126:67: ^( I_ARRAY_ELEMENT_SIZE parsed_integer_literal )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ARRAY_ELEMENT_SIZE, "I_ARRAY_ELEMENT_SIZE"), root_2);
				adaptor.addChild(root_2, stream_parsed_integer_literal.nextTree());
				adaptor.addChild(root_1, root_2);
				}

				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1127:8: ^( I_ARRAY_ELEMENTS ( fixed_literal )* )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ARRAY_ELEMENTS, "I_ARRAY_ELEMENTS"), root_2);
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1127:27: ( fixed_literal )*
				while ( stream_fixed_literal.hasNext() ) {
					adaptor.addChild(root_2, stream_fixed_literal.nextTree());
				}
				stream_fixed_literal.reset();

				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_array_data_directive"


	public static class insn_packed_switch_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_packed_switch_directive"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1129:1: insn_packed_switch_directive : PACKED_SWITCH_DIRECTIVE fixed_32bit_literal ( label_ref )* END_PACKED_SWITCH_DIRECTIVE -> ^( I_STATEMENT_PACKED_SWITCH[$start, \"I_STATEMENT_PACKED_SWITCH\"] ^( I_PACKED_SWITCH_START_KEY[$start, \"I_PACKED_SWITCH_START_KEY\"] fixed_32bit_literal ) ^( I_PACKED_SWITCH_ELEMENTS[$start, \"I_PACKED_SWITCH_ELEMENTS\"] ( label_ref )* ) ) ;
	public final smaliParser.insn_packed_switch_directive_return insn_packed_switch_directive() throws RecognitionException {
		smaliParser.insn_packed_switch_directive_return retval = new smaliParser.insn_packed_switch_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token PACKED_SWITCH_DIRECTIVE503=null;
		Token END_PACKED_SWITCH_DIRECTIVE506=null;
		ParserRuleReturnScope fixed_32bit_literal504 =null;
		ParserRuleReturnScope label_ref505 =null;

		CommonTree PACKED_SWITCH_DIRECTIVE503_tree=null;
		CommonTree END_PACKED_SWITCH_DIRECTIVE506_tree=null;
		RewriteRuleTokenStream stream_PACKED_SWITCH_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token PACKED_SWITCH_DIRECTIVE");
		RewriteRuleTokenStream stream_END_PACKED_SWITCH_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_PACKED_SWITCH_DIRECTIVE");
		RewriteRuleSubtreeStream stream_fixed_32bit_literal=new RewriteRuleSubtreeStream(adaptor,"rule fixed_32bit_literal");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1130:5: ( PACKED_SWITCH_DIRECTIVE fixed_32bit_literal ( label_ref )* END_PACKED_SWITCH_DIRECTIVE -> ^( I_STATEMENT_PACKED_SWITCH[$start, \"I_STATEMENT_PACKED_SWITCH\"] ^( I_PACKED_SWITCH_START_KEY[$start, \"I_PACKED_SWITCH_START_KEY\"] fixed_32bit_literal ) ^( I_PACKED_SWITCH_ELEMENTS[$start, \"I_PACKED_SWITCH_ELEMENTS\"] ( label_ref )* ) ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1130:9: PACKED_SWITCH_DIRECTIVE fixed_32bit_literal ( label_ref )* END_PACKED_SWITCH_DIRECTIVE
			{
			PACKED_SWITCH_DIRECTIVE503=(Token)match(input,PACKED_SWITCH_DIRECTIVE,FOLLOW_PACKED_SWITCH_DIRECTIVE_in_insn_packed_switch_directive5862);  
			stream_PACKED_SWITCH_DIRECTIVE.add(PACKED_SWITCH_DIRECTIVE503);

			pushFollow(FOLLOW_fixed_32bit_literal_in_insn_packed_switch_directive5868);
			fixed_32bit_literal504=fixed_32bit_literal();
			state._fsp--;

			stream_fixed_32bit_literal.add(fixed_32bit_literal504.getTree());
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1132:5: ( label_ref )*
			loop51:
			while (true) {
				int alt51=2;
				int LA51_0 = input.LA(1);
				if ( (LA51_0==COLON) ) {
					alt51=1;
				}

				switch (alt51) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1132:5: label_ref
					{
					pushFollow(FOLLOW_label_ref_in_insn_packed_switch_directive5874);
					label_ref505=label_ref();
					state._fsp--;

					stream_label_ref.add(label_ref505.getTree());
					}
					break;

				default :
					break loop51;
				}
			}

			END_PACKED_SWITCH_DIRECTIVE506=(Token)match(input,END_PACKED_SWITCH_DIRECTIVE,FOLLOW_END_PACKED_SWITCH_DIRECTIVE_in_insn_packed_switch_directive5881);  
			stream_END_PACKED_SWITCH_DIRECTIVE.add(END_PACKED_SWITCH_DIRECTIVE506);

			// AST REWRITE
			// elements: label_ref, fixed_32bit_literal
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1134:5: -> ^( I_STATEMENT_PACKED_SWITCH[$start, \"I_STATEMENT_PACKED_SWITCH\"] ^( I_PACKED_SWITCH_START_KEY[$start, \"I_PACKED_SWITCH_START_KEY\"] fixed_32bit_literal ) ^( I_PACKED_SWITCH_ELEMENTS[$start, \"I_PACKED_SWITCH_ELEMENTS\"] ( label_ref )* ) )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1134:8: ^( I_STATEMENT_PACKED_SWITCH[$start, \"I_STATEMENT_PACKED_SWITCH\"] ^( I_PACKED_SWITCH_START_KEY[$start, \"I_PACKED_SWITCH_START_KEY\"] fixed_32bit_literal ) ^( I_PACKED_SWITCH_ELEMENTS[$start, \"I_PACKED_SWITCH_ELEMENTS\"] ( label_ref )* ) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_PACKED_SWITCH, (retval.start), "I_STATEMENT_PACKED_SWITCH"), root_1);
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1135:10: ^( I_PACKED_SWITCH_START_KEY[$start, \"I_PACKED_SWITCH_START_KEY\"] fixed_32bit_literal )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_PACKED_SWITCH_START_KEY, (retval.start), "I_PACKED_SWITCH_START_KEY"), root_2);
				adaptor.addChild(root_2, stream_fixed_32bit_literal.nextTree());
				adaptor.addChild(root_1, root_2);
				}

				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1136:10: ^( I_PACKED_SWITCH_ELEMENTS[$start, \"I_PACKED_SWITCH_ELEMENTS\"] ( label_ref )* )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_PACKED_SWITCH_ELEMENTS, (retval.start), "I_PACKED_SWITCH_ELEMENTS"), root_2);
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1137:11: ( label_ref )*
				while ( stream_label_ref.hasNext() ) {
					adaptor.addChild(root_2, stream_label_ref.nextTree());
				}
				stream_label_ref.reset();

				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_packed_switch_directive"


	public static class insn_sparse_switch_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_sparse_switch_directive"
	// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1140:1: insn_sparse_switch_directive : SPARSE_SWITCH_DIRECTIVE ( fixed_32bit_literal ARROW label_ref )* END_SPARSE_SWITCH_DIRECTIVE -> ^( I_STATEMENT_SPARSE_SWITCH[$start, \"I_STATEMENT_SPARSE_SWITCH\"] ^( I_SPARSE_SWITCH_ELEMENTS[$start, \"I_SPARSE_SWITCH_ELEMENTS\"] ( fixed_32bit_literal label_ref )* ) ) ;
	public final smaliParser.insn_sparse_switch_directive_return insn_sparse_switch_directive() throws RecognitionException {
		smaliParser.insn_sparse_switch_directive_return retval = new smaliParser.insn_sparse_switch_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SPARSE_SWITCH_DIRECTIVE507=null;
		Token ARROW509=null;
		Token END_SPARSE_SWITCH_DIRECTIVE511=null;
		ParserRuleReturnScope fixed_32bit_literal508 =null;
		ParserRuleReturnScope label_ref510 =null;

		CommonTree SPARSE_SWITCH_DIRECTIVE507_tree=null;
		CommonTree ARROW509_tree=null;
		CommonTree END_SPARSE_SWITCH_DIRECTIVE511_tree=null;
		RewriteRuleTokenStream stream_SPARSE_SWITCH_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token SPARSE_SWITCH_DIRECTIVE");
		RewriteRuleTokenStream stream_ARROW=new RewriteRuleTokenStream(adaptor,"token ARROW");
		RewriteRuleTokenStream stream_END_SPARSE_SWITCH_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_SPARSE_SWITCH_DIRECTIVE");
		RewriteRuleSubtreeStream stream_fixed_32bit_literal=new RewriteRuleSubtreeStream(adaptor,"rule fixed_32bit_literal");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1141:3: ( SPARSE_SWITCH_DIRECTIVE ( fixed_32bit_literal ARROW label_ref )* END_SPARSE_SWITCH_DIRECTIVE -> ^( I_STATEMENT_SPARSE_SWITCH[$start, \"I_STATEMENT_SPARSE_SWITCH\"] ^( I_SPARSE_SWITCH_ELEMENTS[$start, \"I_SPARSE_SWITCH_ELEMENTS\"] ( fixed_32bit_literal label_ref )* ) ) )
			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1141:7: SPARSE_SWITCH_DIRECTIVE ( fixed_32bit_literal ARROW label_ref )* END_SPARSE_SWITCH_DIRECTIVE
			{
			SPARSE_SWITCH_DIRECTIVE507=(Token)match(input,SPARSE_SWITCH_DIRECTIVE,FOLLOW_SPARSE_SWITCH_DIRECTIVE_in_insn_sparse_switch_directive5955);  
			stream_SPARSE_SWITCH_DIRECTIVE.add(SPARSE_SWITCH_DIRECTIVE507);

			// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1142:5: ( fixed_32bit_literal ARROW label_ref )*
			loop52:
			while (true) {
				int alt52=2;
				int LA52_0 = input.LA(1);
				if ( ((LA52_0 >= BOOL_LITERAL && LA52_0 <= BYTE_LITERAL)||LA52_0==CHAR_LITERAL||(LA52_0 >= FLOAT_LITERAL && LA52_0 <= FLOAT_LITERAL_OR_ID)||LA52_0==LONG_LITERAL||LA52_0==NEGATIVE_INTEGER_LITERAL||LA52_0==POSITIVE_INTEGER_LITERAL||LA52_0==SHORT_LITERAL) ) {
					alt52=1;
				}

				switch (alt52) {
				case 1 :
					// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1142:6: fixed_32bit_literal ARROW label_ref
					{
					pushFollow(FOLLOW_fixed_32bit_literal_in_insn_sparse_switch_directive5962);
					fixed_32bit_literal508=fixed_32bit_literal();
					state._fsp--;

					stream_fixed_32bit_literal.add(fixed_32bit_literal508.getTree());
					ARROW509=(Token)match(input,ARROW,FOLLOW_ARROW_in_insn_sparse_switch_directive5964);  
					stream_ARROW.add(ARROW509);

					pushFollow(FOLLOW_label_ref_in_insn_sparse_switch_directive5966);
					label_ref510=label_ref();
					state._fsp--;

					stream_label_ref.add(label_ref510.getTree());
					}
					break;

				default :
					break loop52;
				}
			}

			END_SPARSE_SWITCH_DIRECTIVE511=(Token)match(input,END_SPARSE_SWITCH_DIRECTIVE,FOLLOW_END_SPARSE_SWITCH_DIRECTIVE_in_insn_sparse_switch_directive5974);  
			stream_END_SPARSE_SWITCH_DIRECTIVE.add(END_SPARSE_SWITCH_DIRECTIVE511);

			// AST REWRITE
			// elements: fixed_32bit_literal, label_ref
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1144:5: -> ^( I_STATEMENT_SPARSE_SWITCH[$start, \"I_STATEMENT_SPARSE_SWITCH\"] ^( I_SPARSE_SWITCH_ELEMENTS[$start, \"I_SPARSE_SWITCH_ELEMENTS\"] ( fixed_32bit_literal label_ref )* ) )
			{
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1144:8: ^( I_STATEMENT_SPARSE_SWITCH[$start, \"I_STATEMENT_SPARSE_SWITCH\"] ^( I_SPARSE_SWITCH_ELEMENTS[$start, \"I_SPARSE_SWITCH_ELEMENTS\"] ( fixed_32bit_literal label_ref )* ) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_SPARSE_SWITCH, (retval.start), "I_STATEMENT_SPARSE_SWITCH"), root_1);
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1145:8: ^( I_SPARSE_SWITCH_ELEMENTS[$start, \"I_SPARSE_SWITCH_ELEMENTS\"] ( fixed_32bit_literal label_ref )* )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_SPARSE_SWITCH_ELEMENTS, (retval.start), "I_SPARSE_SWITCH_ELEMENTS"), root_2);
				// shaka.smali/smali/smali/src/main/antlr/smaliParser.g:1145:71: ( fixed_32bit_literal label_ref )*
				while ( stream_fixed_32bit_literal.hasNext()||stream_label_ref.hasNext() ) {
					adaptor.addChild(root_2, stream_fixed_32bit_literal.nextTree());
					adaptor.addChild(root_2, stream_label_ref.nextTree());
				}
				stream_fixed_32bit_literal.reset();
				stream_label_ref.reset();

				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_sparse_switch_directive"

	// Delegated rules


	protected DFA28 dfa28 = new DFA28(this);
	protected DFA36 dfa36 = new DFA36(this);
	protected DFA38 dfa38 = new DFA38(this);
	static final String DFA28_eotS =
		"\61\uffff";
	static final String DFA28_eofS =
		"\61\uffff";
	static final String DFA28_minS =
		"\1\4\12\23\1\u00c0\41\23\2\uffff\1\u00bc\1\23";
	static final String DFA28_maxS =
		"\1\u00cd\12\u00b8\1\u00c0\41\u00b8\2\uffff\1\u00c0\1\u00b8";
	static final String DFA28_acceptS =
		"\55\uffff\1\1\1\2\2\uffff";
	static final String DFA28_specialS =
		"\61\uffff}>";
	static final String[] DFA28_transitionS = {
			"\1\2\1\uffff\1\16\3\uffff\1\10\14\uffff\1\7\17\uffff\1\6\2\uffff\1\17"+
			"\1\20\1\21\1\uffff\1\22\1\uffff\1\23\2\uffff\1\24\1\25\1\30\1\31\1\26"+
			"\1\27\3\uffff\1\32\1\uffff\1\33\1\34\1\36\1\35\1\37\1\uffff\1\40\1\41"+
			"\1\uffff\1\42\1\43\3\uffff\1\44\1\45\1\uffff\1\46\1\47\1\50\1\51\1\52"+
			"\5\uffff\1\53\131\uffff\1\54\1\uffff\1\5\1\11\6\uffff\1\13\1\uffff\1"+
			"\4\1\14\1\uffff\1\12\3\uffff\1\1\5\uffff\1\3\1\15",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\57",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"\1\55\u00a4\uffff\1\56",
			"",
			"",
			"\1\60\3\uffff\1\57",
			"\1\55\u00a4\uffff\1\56"
	};

	static final short[] DFA28_eot = DFA.unpackEncodedString(DFA28_eotS);
	static final short[] DFA28_eof = DFA.unpackEncodedString(DFA28_eofS);
	static final char[] DFA28_min = DFA.unpackEncodedStringToUnsignedChars(DFA28_minS);
	static final char[] DFA28_max = DFA.unpackEncodedStringToUnsignedChars(DFA28_maxS);
	static final short[] DFA28_accept = DFA.unpackEncodedString(DFA28_acceptS);
	static final short[] DFA28_special = DFA.unpackEncodedString(DFA28_specialS);
	static final short[][] DFA28_transition;

	static {
		int numStates = DFA28_transitionS.length;
		DFA28_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA28_transition[i] = DFA.unpackEncodedString(DFA28_transitionS[i]);
		}
	}

	protected class DFA28 extends DFA {

		public DFA28(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 28;
			this.eot = DFA28_eot;
			this.eof = DFA28_eof;
			this.min = DFA28_min;
			this.max = DFA28_max;
			this.accept = DFA28_accept;
			this.special = DFA28_special;
			this.transition = DFA28_transition;
		}
		@Override
		public String getDescription() {
			return "702:7: ( member_name COLON nonvoid_type_descriptor -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor ) | member_name method_prototype -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype ) )";
		}
	}

	static final String DFA36_eotS =
		"\65\uffff";
	static final String DFA36_eofS =
		"\65\uffff";
	static final String DFA36_minS =
		"\1\4\1\5\1\11\12\23\1\u00c0\41\23\1\uffff\1\4\2\uffff\1\u00bc\1\23";
	static final String DFA36_maxS =
		"\1\u00cd\1\u00c8\1\11\12\u00b8\1\u00c0\41\u00b8\1\uffff\1\u00cd\2\uffff"+
		"\1\u00c0\1\u00b8";
	static final String DFA36_acceptS =
		"\57\uffff\1\1\1\uffff\1\2\1\3\2\uffff";
	static final String DFA36_specialS =
		"\65\uffff}>";
	static final String[] DFA36_transitionS = {
			"\1\4\1\uffff\1\20\1\uffff\1\2\1\uffff\1\12\4\uffff\1\1\7\uffff\1\11\17"+
			"\uffff\1\10\2\uffff\1\21\1\22\1\23\1\uffff\1\24\1\uffff\1\25\2\uffff"+
			"\1\26\1\27\1\32\1\33\1\30\1\31\3\uffff\1\34\1\uffff\1\35\1\36\1\40\1"+
			"\37\1\41\1\uffff\1\42\1\43\1\uffff\1\44\1\45\3\uffff\1\46\1\47\1\uffff"+
			"\1\50\1\51\1\52\1\53\1\54\5\uffff\1\55\131\uffff\1\56\1\uffff\1\7\1\13"+
			"\6\uffff\1\15\1\uffff\1\6\1\16\1\uffff\1\14\3\uffff\1\3\5\uffff\1\5\1"+
			"\17",
			"\1\57\1\uffff\1\57\1\uffff\1\60\2\uffff\2\57\5\uffff\1\57\7\uffff\2"+
			"\57\5\uffff\1\57\7\uffff\60\57\125\uffff\3\57\7\uffff\2\57\6\uffff\1"+
			"\57\1\uffff\2\57\2\uffff\2\57",
			"\1\60",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\63",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"\1\61\u00a4\uffff\1\62",
			"",
			"\1\4\1\uffff\1\20\3\uffff\1\12\14\uffff\1\11\17\uffff\1\10\2\uffff\1"+
			"\21\1\22\1\23\1\uffff\1\24\1\uffff\1\25\2\uffff\1\26\1\27\1\32\1\33\1"+
			"\30\1\31\3\uffff\1\34\1\uffff\1\35\1\36\1\40\1\37\1\41\1\uffff\1\42\1"+
			"\43\1\uffff\1\44\1\45\3\uffff\1\46\1\47\1\uffff\1\50\1\51\1\52\1\53\1"+
			"\54\5\uffff\1\55\131\uffff\1\56\1\uffff\1\7\1\13\6\uffff\1\15\1\uffff"+
			"\1\6\1\16\1\uffff\1\14\3\uffff\1\3\5\uffff\1\5\1\17",
			"",
			"",
			"\1\64\3\uffff\1\63",
			"\1\61\u00a4\uffff\1\62"
	};

	static final short[] DFA36_eot = DFA.unpackEncodedString(DFA36_eotS);
	static final short[] DFA36_eof = DFA.unpackEncodedString(DFA36_eofS);
	static final char[] DFA36_min = DFA.unpackEncodedStringToUnsignedChars(DFA36_minS);
	static final char[] DFA36_max = DFA.unpackEncodedStringToUnsignedChars(DFA36_maxS);
	static final short[] DFA36_accept = DFA.unpackEncodedString(DFA36_acceptS);
	static final short[] DFA36_special = DFA.unpackEncodedString(DFA36_specialS);
	static final short[][] DFA36_transition;

	static {
		int numStates = DFA36_transitionS.length;
		DFA36_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA36_transition[i] = DFA.unpackEncodedString(DFA36_transitionS[i]);
		}
	}

	protected class DFA36 extends DFA {

		public DFA36(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 36;
			this.eot = DFA36_eot;
			this.eof = DFA36_eof;
			this.min = DFA36_min;
			this.max = DFA36_max;
			this.accept = DFA36_accept;
			this.special = DFA36_special;
			this.transition = DFA36_transition;
		}
		@Override
		public String getDescription() {
			return "730:1: verification_error_reference : ( CLASS_DESCRIPTOR | field_reference | method_reference );";
		}
	}

	static final String DFA38_eotS =
		"\105\uffff";
	static final String DFA38_eofS =
		"\105\uffff";
	static final String DFA38_minS =
		"\1\5\102\uffff\1\0\1\uffff";
	static final String DFA38_maxS =
		"\1\u00c8\102\uffff\1\0\1\uffff";
	static final String DFA38_acceptS =
		"\1\uffff\1\2\102\uffff\1\1";
	static final String DFA38_specialS =
		"\103\uffff\1\0\1\uffff}>";
	static final String[] DFA38_transitionS = {
			"\1\103\1\uffff\1\1\4\uffff\2\1\5\uffff\1\1\7\uffff\2\1\1\uffff\1\1\3"+
			"\uffff\1\1\7\uffff\60\1\125\uffff\3\1\7\uffff\2\1\6\uffff\1\1\1\uffff"+
			"\2\1\2\uffff\2\1",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\uffff",
			""
	};

	static final short[] DFA38_eot = DFA.unpackEncodedString(DFA38_eotS);
	static final short[] DFA38_eof = DFA.unpackEncodedString(DFA38_eofS);
	static final char[] DFA38_min = DFA.unpackEncodedStringToUnsignedChars(DFA38_minS);
	static final char[] DFA38_max = DFA.unpackEncodedStringToUnsignedChars(DFA38_maxS);
	static final short[] DFA38_accept = DFA.unpackEncodedString(DFA38_acceptS);
	static final short[] DFA38_special = DFA.unpackEncodedString(DFA38_specialS);
	static final short[][] DFA38_transition;

	static {
		int numStates = DFA38_transitionS.length;
		DFA38_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA38_transition[i] = DFA.unpackEncodedString(DFA38_transitionS[i]);
		}
	}

	protected class DFA38 extends DFA {

		public DFA38(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 38;
			this.eot = DFA38_eot;
			this.eof = DFA38_eof;
			this.min = DFA38_min;
			this.max = DFA38_max;
			this.accept = DFA38_accept;
			this.special = DFA38_special;
			this.transition = DFA38_transition;
		}
		@Override
		public String getDescription() {
			return "()* loopback of 748:5: ({...}? annotation )*";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			TokenStream input = (TokenStream)_input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA38_67 = input.LA(1);
						 
						int index38_67 = input.index();
						input.rewind();
						s = -1;
						if ( ((input.LA(1) == ANNOTATION_DIRECTIVE)) ) {s = 68;}
						else if ( (true) ) {s = 1;}
						 
						input.seek(index38_67);
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 38, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	public static final BitSet FOLLOW_class_spec_in_smali_file1110 = new BitSet(new long[]{0x0000011000010020L,0x0000000000000000L,0x0010000000000000L,0x0000000000000880L});
	public static final BitSet FOLLOW_super_spec_in_smali_file1121 = new BitSet(new long[]{0x0000011000010020L,0x0000000000000000L,0x0010000000000000L,0x0000000000000880L});
	public static final BitSet FOLLOW_implements_spec_in_smali_file1129 = new BitSet(new long[]{0x0000011000010020L,0x0000000000000000L,0x0010000000000000L,0x0000000000000880L});
	public static final BitSet FOLLOW_source_spec_in_smali_file1138 = new BitSet(new long[]{0x0000011000010020L,0x0000000000000000L,0x0010000000000000L,0x0000000000000880L});
	public static final BitSet FOLLOW_method_in_smali_file1146 = new BitSet(new long[]{0x0000011000010020L,0x0000000000000000L,0x0010000000000000L,0x0000000000000880L});
	public static final BitSet FOLLOW_field_in_smali_file1152 = new BitSet(new long[]{0x0000011000010020L,0x0000000000000000L,0x0010000000000000L,0x0000000000000880L});
	public static final BitSet FOLLOW_annotation_in_smali_file1158 = new BitSet(new long[]{0x0000011000010020L,0x0000000000000000L,0x0010000000000000L,0x0000000000000880L});
	public static final BitSet FOLLOW_EOF_in_smali_file1169 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLASS_DIRECTIVE_in_class_spec1256 = new BitSet(new long[]{0x0000000000008010L});
	public static final BitSet FOLLOW_access_list_in_class_spec1258 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_class_spec1260 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SUPER_DIRECTIVE_in_super_spec1278 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_super_spec1280 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IMPLEMENTS_DIRECTIVE_in_implements_spec1299 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_implements_spec1301 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SOURCE_DIRECTIVE_in_source_spec1320 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_source_spec1322 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ACCESS_SPEC_in_access_list1341 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_FIELD_DIRECTIVE_in_field1372 = new BitSet(new long[]{0xD1F95C8000800450L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_access_list_in_field1374 = new BitSet(new long[]{0xD1F95C8000800450L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_member_name_in_field1376 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_COLON_in_field1378 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_field1380 = new BitSet(new long[]{0x0000000804000022L});
	public static final BitSet FOLLOW_EQUAL_in_field1383 = new BitSet(new long[]{0xD1F95CC200C0CD50L,0x00000000020FB1B7L,0xA0EC000000000000L,0x0000000000003665L});
	public static final BitSet FOLLOW_literal_in_field1385 = new BitSet(new long[]{0x0000000004000022L});
	public static final BitSet FOLLOW_annotation_in_field1398 = new BitSet(new long[]{0x0000000004000022L});
	public static final BitSet FOLLOW_END_FIELD_DIRECTIVE_in_field1412 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_METHOD_DIRECTIVE_in_method1523 = new BitSet(new long[]{0xD1F95C8000800450L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_access_list_in_method1525 = new BitSet(new long[]{0xD1F95C8000800450L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_member_name_in_method1527 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_method_prototype_in_method1529 = new BitSet(new long[]{0xFFFFFC04180830A0L,0x0000000003FFFFFFL,0x0603800000000000L,0x000000000000019AL});
	public static final BitSet FOLLOW_statements_and_directives_in_method1531 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_END_METHOD_DIRECTIVE_in_method1537 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ordered_method_item_in_statements_and_directives1582 = new BitSet(new long[]{0xFFFFFC04080830A2L,0x0000000003FFFFFFL,0x0603800000000000L,0x000000000000019AL});
	public static final BitSet FOLLOW_registers_directive_in_statements_and_directives1590 = new BitSet(new long[]{0xFFFFFC04080830A2L,0x0000000003FFFFFFL,0x0603800000000000L,0x000000000000019AL});
	public static final BitSet FOLLOW_catch_directive_in_statements_and_directives1598 = new BitSet(new long[]{0xFFFFFC04080830A2L,0x0000000003FFFFFFL,0x0603800000000000L,0x000000000000019AL});
	public static final BitSet FOLLOW_catchall_directive_in_statements_and_directives1606 = new BitSet(new long[]{0xFFFFFC04080830A2L,0x0000000003FFFFFFL,0x0603800000000000L,0x000000000000019AL});
	public static final BitSet FOLLOW_parameter_directive_in_statements_and_directives1614 = new BitSet(new long[]{0xFFFFFC04080830A2L,0x0000000003FFFFFFL,0x0603800000000000L,0x000000000000019AL});
	public static final BitSet FOLLOW_annotation_in_statements_and_directives1622 = new BitSet(new long[]{0xFFFFFC04080830A2L,0x0000000003FFFFFFL,0x0603800000000000L,0x000000000000019AL});
	public static final BitSet FOLLOW_label_in_ordered_method_item1707 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_instruction_in_ordered_method_item1713 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_debug_directive_in_ordered_method_item1719 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REGISTERS_DIRECTIVE_in_registers_directive1739 = new BitSet(new long[]{0x0000000000004800L,0x0000000000000000L,0x8024000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_integral_literal_in_registers_directive1743 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LOCALS_DIRECTIVE_in_registers_directive1763 = new BitSet(new long[]{0x0000000000004800L,0x0000000000000000L,0x8024000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_integral_literal_in_registers_directive1767 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PARAM_LIST_OR_ID_START_in_param_list_or_id1799 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_PRIMITIVE_TYPE_in_param_list_or_id1801 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x1000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_PARAM_LIST_OR_ID_END_in_param_list_or_id1804 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_simple_name1816 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ACCESS_SPEC_in_simple_name1822 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VERIFICATION_ERROR_TYPE_in_simple_name1833 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_POSITIVE_INTEGER_LITERAL_in_simple_name1844 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEGATIVE_INTEGER_LITERAL_in_simple_name1855 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FLOAT_LITERAL_OR_ID_in_simple_name1866 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLE_LITERAL_OR_ID_in_simple_name1877 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOL_LITERAL_in_simple_name1888 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NULL_LITERAL_in_simple_name1899 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REGISTER_in_simple_name1910 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_param_list_or_id_in_simple_name1921 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMITIVE_TYPE_in_simple_name1931 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_TYPE_in_simple_name1942 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ANNOTATION_VISIBILITY_in_simple_name1953 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT10t_in_simple_name1964 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT10x_in_simple_name1975 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT10x_ODEX_in_simple_name1986 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT11x_in_simple_name1997 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT12x_OR_ID_in_simple_name2008 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_FIELD_in_simple_name2019 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_FIELD_ODEX_in_simple_name2030 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_simple_name2041 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_simple_name2052 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_LAMBDA_in_simple_name2063 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_METHOD_in_simple_name2074 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21t_in_simple_name2085 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_FIELD_in_simple_name2096 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_FIELD_ODEX_in_simple_name2107 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_simple_name2118 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_STRING_in_simple_name2129 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22cs_FIELD_in_simple_name2140 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22s_OR_ID_in_simple_name2151 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22t_in_simple_name2162 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT23x_in_simple_name2173 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT25x_in_simple_name2184 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31i_OR_ID_in_simple_name2195 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31t_in_simple_name2206 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_simple_name2217 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_ODEX_in_simple_name2228 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_simple_name2239 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35mi_METHOD_in_simple_name2250 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35ms_METHOD_in_simple_name2261 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT51l_in_simple_name2272 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simple_name_in_member_name2287 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MEMBER_NAME_in_member_name2293 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPEN_PAREN_in_method_prototype2308 = new BitSet(new long[]{0x0000000000048100L,0x0000000000000000L,0x6000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_param_list_in_method_prototype2310 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_PAREN_in_method_prototype2312 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0000000000000000L,0x0000000000002001L});
	public static final BitSet FOLLOW_type_descriptor_in_method_prototype2314 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PARAM_LIST_START_in_param_list2344 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0800000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_param_list2346 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0800000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_PARAM_LIST_END_in_param_list2349 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PARAM_LIST_OR_ID_START_in_param_list2360 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x1000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_PRIMITIVE_TYPE_in_param_list2362 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x1000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_PARAM_LIST_OR_ID_END_in_param_list2365 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_param_list2376 = new BitSet(new long[]{0x0000000000008102L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_POSITIVE_INTEGER_LITERAL_in_integer_literal2453 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEGATIVE_INTEGER_LITERAL_in_integer_literal2464 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FLOAT_LITERAL_OR_ID_in_float_literal2479 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FLOAT_LITERAL_in_float_literal2490 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLE_LITERAL_OR_ID_in_double_literal2500 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLE_LITERAL_in_double_literal2511 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LONG_LITERAL_in_literal2521 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_literal2527 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SHORT_LITERAL_in_literal2533 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BYTE_LITERAL_in_literal2539 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_float_literal_in_literal2545 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_double_literal_in_literal2551 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CHAR_LITERAL_in_literal2557 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_literal2563 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOL_LITERAL_in_literal2569 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NULL_LITERAL_in_literal2575 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_literal_in_literal2581 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_subannotation_in_literal2587 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_field_method_literal_in_literal2593 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_enum_literal_in_literal2599 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_parsed_integer_literal2612 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LONG_LITERAL_in_integral_literal2624 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_integral_literal2630 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SHORT_LITERAL_in_integral_literal2636 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CHAR_LITERAL_in_integral_literal2642 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BYTE_LITERAL_in_integral_literal2648 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LONG_LITERAL_in_fixed_32bit_literal2658 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_fixed_32bit_literal2664 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SHORT_LITERAL_in_fixed_32bit_literal2670 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BYTE_LITERAL_in_fixed_32bit_literal2676 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_float_literal_in_fixed_32bit_literal2682 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CHAR_LITERAL_in_fixed_32bit_literal2688 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOL_LITERAL_in_fixed_32bit_literal2694 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_fixed_literal2704 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LONG_LITERAL_in_fixed_literal2710 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SHORT_LITERAL_in_fixed_literal2716 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BYTE_LITERAL_in_fixed_literal2722 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_float_literal_in_fixed_literal2728 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_double_literal_in_fixed_literal2734 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CHAR_LITERAL_in_fixed_literal2740 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOL_LITERAL_in_fixed_literal2746 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_array_literal2756 = new BitSet(new long[]{0xD1F95CC200C2CD50L,0x00000000020FB1B7L,0xA0EC000000000000L,0x0000000000003665L});
	public static final BitSet FOLLOW_literal_in_array_literal2759 = new BitSet(new long[]{0x0000000000120000L});
	public static final BitSet FOLLOW_COMMA_in_array_literal2762 = new BitSet(new long[]{0xD1F95CC200C0CD50L,0x00000000020FB1B7L,0xA0EC000000000000L,0x0000000000003665L});
	public static final BitSet FOLLOW_literal_in_array_literal2764 = new BitSet(new long[]{0x0000000000120000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_array_literal2772 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simple_name_in_annotation_element2796 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_EQUAL_in_annotation_element2798 = new BitSet(new long[]{0xD1F95CC200C0CD50L,0x00000000020FB1B7L,0xA0EC000000000000L,0x0000000000003665L});
	public static final BitSet FOLLOW_literal_in_annotation_element2800 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ANNOTATION_DIRECTIVE_in_annotation2825 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_ANNOTATION_VISIBILITY_in_annotation2827 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_annotation2829 = new BitSet(new long[]{0xD1F95C8001800450L,0x00000000020FB1B7L,0xA060000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_annotation_element_in_annotation2835 = new BitSet(new long[]{0xD1F95C8001800450L,0x00000000020FB1B7L,0xA060000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_END_ANNOTATION_DIRECTIVE_in_annotation2838 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SUBANNOTATION_DIRECTIVE_in_subannotation2871 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_subannotation2873 = new BitSet(new long[]{0xD1F95C8100800450L,0x00000000020FB1B7L,0xA060000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_annotation_element_in_subannotation2875 = new BitSet(new long[]{0xD1F95C8100800450L,0x00000000020FB1B7L,0xA060000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_END_SUBANNOTATION_DIRECTIVE_in_subannotation2878 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ENUM_DIRECTIVE_in_enum_literal2904 = new BitSet(new long[]{0x0000000000008100L});
	public static final BitSet FOLLOW_reference_type_descriptor_in_enum_literal2906 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_ARROW_in_enum_literal2908 = new BitSet(new long[]{0xD1F95C8000800450L,0x00000000020FB1B7L,0xA060000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_simple_name_in_enum_literal2910 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_COLON_in_enum_literal2912 = new BitSet(new long[]{0x0000000000008100L});
	public static final BitSet FOLLOW_reference_type_descriptor_in_enum_literal2914 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_reference_type_descriptor_in_type_field_method_literal2938 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_reference_type_descriptor_in_type_field_method_literal2947 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_ARROW_in_type_field_method_literal2949 = new BitSet(new long[]{0xD1F95C8000800450L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_member_name_in_type_field_method_literal2961 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_COLON_in_type_field_method_literal2963 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_type_field_method_literal2965 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_member_name_in_type_field_method_literal2988 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_method_prototype_in_type_field_method_literal2990 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMITIVE_TYPE_in_type_field_method_literal3023 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_TYPE_in_type_field_method_literal3029 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_reference_type_descriptor_in_method_reference3040 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_ARROW_in_method_reference3042 = new BitSet(new long[]{0xD1F95C8000800450L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_member_name_in_method_reference3046 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_method_prototype_in_method_reference3048 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_reference_type_descriptor_in_field_reference3070 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_ARROW_in_field_reference3072 = new BitSet(new long[]{0xD1F95C8000800450L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_member_name_in_field_reference3076 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_COLON_in_field_reference3078 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_field_reference3080 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COLON_in_label3101 = new BitSet(new long[]{0xD1F95C8000800450L,0x00000000020FB1B7L,0xA060000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_simple_name_in_label3103 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COLON_in_label_ref3122 = new BitSet(new long[]{0xD1F95C8000800450L,0x00000000020FB1B7L,0xA060000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_simple_name_in_label_ref3124 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REGISTER_in_register_list3138 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_COMMA_in_register_list3141 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_register_list3143 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_REGISTER_in_register_range3178 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_DOTDOT_in_register_range3181 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_register_range3185 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_verification_error_reference3214 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_field_reference_in_verification_error_reference3218 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_method_reference_in_verification_error_reference3222 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CATCH_DIRECTIVE_in_catch_directive3232 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_catch_directive3234 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_catch_directive3236 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_label_ref_in_catch_directive3240 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_DOTDOT_in_catch_directive3242 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_label_ref_in_catch_directive3246 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_catch_directive3248 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_label_ref_in_catch_directive3252 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CATCHALL_DIRECTIVE_in_catchall_directive3284 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_catchall_directive3286 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_label_ref_in_catchall_directive3290 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_DOTDOT_in_catchall_directive3292 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_label_ref_in_catchall_directive3296 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_catchall_directive3298 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_label_ref_in_catchall_directive3302 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PARAMETER_DIRECTIVE_in_parameter_directive3341 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_parameter_directive3343 = new BitSet(new long[]{0x0000000040100022L});
	public static final BitSet FOLLOW_COMMA_in_parameter_directive3346 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_parameter_directive3348 = new BitSet(new long[]{0x0000000040000022L});
	public static final BitSet FOLLOW_annotation_in_parameter_directive3359 = new BitSet(new long[]{0x0000000040000022L});
	public static final BitSet FOLLOW_END_PARAMETER_DIRECTIVE_in_parameter_directive3372 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_line_directive_in_debug_directive3445 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_local_directive_in_debug_directive3451 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_end_local_directive_in_debug_directive3457 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_restart_local_directive_in_debug_directive3463 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_prologue_directive_in_debug_directive3469 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_epilogue_directive_in_debug_directive3475 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_source_directive_in_debug_directive3481 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LINE_DIRECTIVE_in_line_directive3491 = new BitSet(new long[]{0x0000000000004800L,0x0000000000000000L,0x8024000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_integral_literal_in_line_directive3493 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LOCAL_DIRECTIVE_in_local_directive3516 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_local_directive3518 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_COMMA_in_local_directive3521 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0040000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_NULL_LITERAL_in_local_directive3524 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_local_directive3530 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_COLON_in_local_directive3533 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0000000000000000L,0x0000000000002001L});
	public static final BitSet FOLLOW_VOID_TYPE_in_local_directive3536 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_local_directive3540 = new BitSet(new long[]{0x0000000000100002L});
	public static final BitSet FOLLOW_COMMA_in_local_directive3574 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_local_directive3578 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_END_LOCAL_DIRECTIVE_in_end_local_directive3620 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_end_local_directive3622 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_RESTART_LOCAL_DIRECTIVE_in_restart_local_directive3645 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_restart_local_directive3647 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PROLOGUE_DIRECTIVE_in_prologue_directive3670 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EPILOGUE_DIRECTIVE_in_epilogue_directive3691 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SOURCE_DIRECTIVE_in_source_directive3712 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_source_directive3714 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT12x_in_instruction_format12x3739 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT12x_OR_ID_in_instruction_format12x3745 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22s_in_instruction_format22s3760 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22s_OR_ID_in_instruction_format22s3766 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31i_in_instruction_format31i3781 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31i_OR_ID_in_instruction_format31i3787 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format10t_in_instruction3804 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format10x_in_instruction3810 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format10x_odex_in_instruction3816 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format11n_in_instruction3822 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format11x_in_instruction3828 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format12x_in_instruction3834 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format20bc_in_instruction3840 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format20t_in_instruction3846 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_field_in_instruction3852 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_field_odex_in_instruction3858 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_string_in_instruction3864 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_type_in_instruction3870 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_lambda_in_instruction3876 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_method_in_instruction3882 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21ih_in_instruction3888 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21lh_in_instruction3894 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21s_in_instruction3900 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21t_in_instruction3906 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22b_in_instruction3912 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22c_field_in_instruction3918 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22c_field_odex_in_instruction3924 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22c_type_in_instruction3930 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22c_string_in_instruction3936 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22cs_field_in_instruction3942 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22s_in_instruction3948 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22t_in_instruction3954 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22x_in_instruction3960 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format23x_in_instruction3966 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format25x_in_instruction3972 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format30t_in_instruction3978 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format31c_in_instruction3984 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format31i_in_instruction3990 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format31t_in_instruction3996 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format32x_in_instruction4002 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format35c_method_in_instruction4008 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format35c_type_in_instruction4014 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format35c_method_odex_in_instruction4020 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format35mi_method_in_instruction4026 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format35ms_method_in_instruction4032 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format3rc_method_in_instruction4038 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format3rc_method_odex_in_instruction4044 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format3rc_type_in_instruction4050 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format3rmi_method_in_instruction4056 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format3rms_method_in_instruction4062 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format51l_in_instruction4068 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_array_data_directive_in_instruction4074 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_packed_switch_directive_in_instruction4080 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_sparse_switch_directive_in_instruction4086 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT10t_in_insn_format10t4106 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format10t4108 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT10x_in_insn_format10x4138 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT10x_ODEX_in_insn_format10x_odex4166 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT11n_in_insn_format11n4187 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format11n4189 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format11n4191 = new BitSet(new long[]{0x0000000000004800L,0x0000000000000000L,0x8024000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_integral_literal_in_insn_format11n4193 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT11x_in_insn_format11x4225 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format11x4227 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_instruction_format12x_in_insn_format12x4257 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format12x4259 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format12x4261 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format12x4263 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT20bc_in_insn_format20bc4295 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_VERIFICATION_ERROR_TYPE_in_insn_format20bc4297 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format20bc4299 = new BitSet(new long[]{0xD1F95C8000808550L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_verification_error_reference_in_insn_format20bc4301 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT20t_in_insn_format20t4338 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format20t4340 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_FIELD_in_insn_format21c_field4370 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_field4372 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21c_field4374 = new BitSet(new long[]{0xD1F95C8000808550L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_field_reference_in_insn_format21c_field4376 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_FIELD_ODEX_in_insn_format21c_field_odex4408 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_field_odex4410 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21c_field_odex4412 = new BitSet(new long[]{0xD1F95C8000808550L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_field_reference_in_insn_format21c_field_odex4414 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_insn_format21c_string4452 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_string4454 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21c_string4456 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_insn_format21c_string4458 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_insn_format21c_type4490 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_type4492 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21c_type4494 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format21c_type4496 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_LAMBDA_in_insn_format21c_lambda4528 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_lambda4530 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21c_lambda4532 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_insn_format21c_lambda4534 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_METHOD_in_insn_format21c_method4566 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_method4568 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21c_method4570 = new BitSet(new long[]{0xD1F95C8000808550L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_method_reference_in_insn_format21c_method4572 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21ih_in_insn_format21ih4604 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21ih4606 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21ih4608 = new BitSet(new long[]{0x000000C000004C00L,0x0000000000000000L,0x8024000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_format21ih4610 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21lh_in_insn_format21lh4642 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21lh4644 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21lh4646 = new BitSet(new long[]{0x000000C000004C00L,0x0000000000000000L,0x8024000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_format21lh4648 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21s_in_insn_format21s4680 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21s4682 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21s4684 = new BitSet(new long[]{0x0000000000004800L,0x0000000000000000L,0x8024000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_integral_literal_in_insn_format21s4686 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21t_in_insn_format21t4718 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21t4720 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21t4722 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format21t4724 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22b_in_insn_format22b4756 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22b4758 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22b4760 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22b4762 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22b4764 = new BitSet(new long[]{0x0000000000004800L,0x0000000000000000L,0x8024000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_integral_literal_in_insn_format22b4766 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_FIELD_in_insn_format22c_field4800 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_field4802 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22c_field4804 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_field4806 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22c_field4808 = new BitSet(new long[]{0xD1F95C8000808550L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_field_reference_in_insn_format22c_field4810 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_FIELD_ODEX_in_insn_format22c_field_odex4844 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_field_odex4846 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22c_field_odex4848 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_field_odex4850 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22c_field_odex4852 = new BitSet(new long[]{0xD1F95C8000808550L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_field_reference_in_insn_format22c_field_odex4854 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_insn_format22c_type4894 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_type4896 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22c_type4898 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_type4900 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22c_type4902 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format22c_type4904 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_STRING_in_insn_format22c_string4938 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_string4940 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22c_string4942 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_string4944 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22c_string4946 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_insn_format22c_string4948 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22cs_FIELD_in_insn_format22cs_field4982 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22cs_field4984 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22cs_field4986 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22cs_field4988 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22cs_field4990 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_FIELD_OFFSET_in_insn_format22cs_field4992 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_instruction_format22s_in_insn_format22s5013 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22s5015 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22s5017 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22s5019 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22s5021 = new BitSet(new long[]{0x0000000000004800L,0x0000000000000000L,0x8024000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_integral_literal_in_insn_format22s5023 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22t_in_insn_format22t5057 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22t5059 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22t5061 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22t5063 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22t5065 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format22t5067 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22x_in_insn_format22x5101 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22x5103 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22x5105 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22x5107 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT23x_in_insn_format23x5139 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format23x5141 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format23x5143 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format23x5145 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format23x5147 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format23x5149 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT25x_in_insn_format25x5183 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format25x5185 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format25x5187 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format25x5189 = new BitSet(new long[]{0x0000000000020000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_register_list_in_insn_format25x5191 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format25x5193 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT30t_in_insn_format30t5225 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format30t5227 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31c_in_insn_format31c5257 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format31c5259 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format31c5261 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_insn_format31c5263 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_instruction_format31i_in_insn_format31i5294 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format31i5296 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format31i5298 = new BitSet(new long[]{0x000000C000004C00L,0x0000000000000000L,0x8024000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_format31i5300 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31t_in_insn_format31t5332 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format31t5334 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format31t5336 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format31t5338 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT32x_in_insn_format32x5370 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format32x5372 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format32x5374 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format32x5376 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_insn_format35c_method5408 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format35c_method5410 = new BitSet(new long[]{0x0000000000020000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_register_list_in_insn_format35c_method5412 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format35c_method5414 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format35c_method5416 = new BitSet(new long[]{0xD1F95C8000808550L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_method_reference_in_insn_format35c_method5418 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_insn_format35c_type5450 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format35c_type5452 = new BitSet(new long[]{0x0000000000020000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_register_list_in_insn_format35c_type5454 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format35c_type5456 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format35c_type5458 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format35c_type5460 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_ODEX_in_insn_format35c_method_odex5492 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format35c_method_odex5494 = new BitSet(new long[]{0x0000000000020000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_register_list_in_insn_format35c_method_odex5496 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format35c_method_odex5498 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format35c_method_odex5500 = new BitSet(new long[]{0xD1F95C8000808550L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_method_reference_in_insn_format35c_method_odex5502 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35mi_METHOD_in_insn_format35mi_method5523 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format35mi_method5525 = new BitSet(new long[]{0x0000000000020000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_register_list_in_insn_format35mi_method5527 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format35mi_method5529 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format35mi_method5531 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_INLINE_INDEX_in_insn_format35mi_method5533 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35ms_METHOD_in_insn_format35ms_method5554 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format35ms_method5556 = new BitSet(new long[]{0x0000000000020000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_register_list_in_insn_format35ms_method5558 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format35ms_method5560 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format35ms_method5562 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000004000L});
	public static final BitSet FOLLOW_VTABLE_INDEX_in_insn_format35ms_method5564 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_in_insn_format3rc_method5585 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format3rc_method5587 = new BitSet(new long[]{0x0000000000020000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_register_range_in_insn_format3rc_method5589 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format3rc_method5591 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format3rc_method5593 = new BitSet(new long[]{0xD1F95C8000808550L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_method_reference_in_insn_format3rc_method5595 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_ODEX_in_insn_format3rc_method_odex5627 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format3rc_method_odex5629 = new BitSet(new long[]{0x0000000000020000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_register_list_in_insn_format3rc_method_odex5631 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format3rc_method_odex5633 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format3rc_method_odex5635 = new BitSet(new long[]{0xD1F95C8000808550L,0x00000000020FB1B7L,0xA068000000000000L,0x0000000000003045L});
	public static final BitSet FOLLOW_method_reference_in_insn_format3rc_method_odex5637 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_TYPE_in_insn_format3rc_type5658 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format3rc_type5660 = new BitSet(new long[]{0x0000000000020000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_register_range_in_insn_format3rc_type5662 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format3rc_type5664 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format3rc_type5666 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format3rc_type5668 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rmi_METHOD_in_insn_format3rmi_method5700 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format3rmi_method5702 = new BitSet(new long[]{0x0000000000020000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_register_range_in_insn_format3rmi_method5704 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format3rmi_method5706 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format3rmi_method5708 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_INLINE_INDEX_in_insn_format3rmi_method5710 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rms_METHOD_in_insn_format3rms_method5731 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format3rms_method5733 = new BitSet(new long[]{0x0000000000020000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_register_range_in_insn_format3rms_method5735 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format3rms_method5737 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format3rms_method5739 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000004000L});
	public static final BitSet FOLLOW_VTABLE_INDEX_in_insn_format3rms_method5741 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT51l_in_insn_format51l5762 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format51l5764 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format51l5766 = new BitSet(new long[]{0x000000C000C04C00L,0x0000000000000000L,0x8024000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_fixed_literal_in_insn_format51l5768 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ARRAY_DATA_DIRECTIVE_in_insn_array_data_directive5795 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8020000000000000L});
	public static final BitSet FOLLOW_parsed_integer_literal_in_insn_array_data_directive5801 = new BitSet(new long[]{0x000000C002C04C00L,0x0000000000000000L,0x8024000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_fixed_literal_in_insn_array_data_directive5813 = new BitSet(new long[]{0x000000C002C04C00L,0x0000000000000000L,0x8024000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_END_ARRAY_DATA_DIRECTIVE_in_insn_array_data_directive5816 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PACKED_SWITCH_DIRECTIVE_in_insn_packed_switch_directive5862 = new BitSet(new long[]{0x000000C000004C00L,0x0000000000000000L,0x8024000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_packed_switch_directive5868 = new BitSet(new long[]{0x0000000020080000L});
	public static final BitSet FOLLOW_label_ref_in_insn_packed_switch_directive5874 = new BitSet(new long[]{0x0000000020080000L});
	public static final BitSet FOLLOW_END_PACKED_SWITCH_DIRECTIVE_in_insn_packed_switch_directive5881 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SPARSE_SWITCH_DIRECTIVE_in_insn_sparse_switch_directive5955 = new BitSet(new long[]{0x000000C080004C00L,0x0000000000000000L,0x8024000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_sparse_switch_directive5962 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_ARROW_in_insn_sparse_switch_directive5964 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_label_ref_in_insn_sparse_switch_directive5966 = new BitSet(new long[]{0x000000C080004C00L,0x0000000000000000L,0x8024000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_END_SPARSE_SWITCH_DIRECTIVE_in_insn_sparse_switch_directive5974 = new BitSet(new long[]{0x0000000000000002L});
}
