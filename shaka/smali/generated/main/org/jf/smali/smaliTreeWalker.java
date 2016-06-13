// $ANTLR 3.5.2 smaliTreeWalker.g

package org.jf.smali;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.TreeNodeStream;
import org.antlr.runtime.tree.TreeParser;
import org.antlr.runtime.tree.TreeRuleReturnScope;
import org.jf.dexlib2.*;
import org.jf.dexlib2.builder.Label;
import org.jf.dexlib2.builder.MethodImplementationBuilder;
import org.jf.dexlib2.builder.SwitchLabelElement;
import org.jf.dexlib2.builder.instruction.*;
import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.AnnotationElement;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.MethodImplementation;
import org.jf.dexlib2.iface.reference.FieldReference;
import org.jf.dexlib2.iface.reference.MethodReference;
import org.jf.dexlib2.iface.value.EncodedValue;
import org.jf.dexlib2.immutable.ImmutableAnnotation;
import org.jf.dexlib2.immutable.ImmutableAnnotationElement;
import org.jf.dexlib2.immutable.reference.ImmutableFieldReference;
import org.jf.dexlib2.immutable.reference.ImmutableMethodReference;
import org.jf.dexlib2.immutable.reference.ImmutableReference;
import org.jf.dexlib2.immutable.reference.ImmutableTypeReference;
import org.jf.dexlib2.immutable.value.*;
import org.jf.dexlib2.util.MethodUtil;
import org.jf.dexlib2.writer.InstructionFactory;
import org.jf.dexlib2.writer.builder.*;
import org.jf.util.LinearSearch;

import java.util.*;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class smaliTreeWalker extends TreeParser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ACCESS_SPEC", "ANNOTATION_DIRECTIVE", 
		"ANNOTATION_VISIBILITY", "ARRAY_DATA_DIRECTIVE", "ARRAY_TYPE_PREFIX", 
		"ARROW", "BOOL_LITERAL", "BYTE_LITERAL", "CATCHALL_DIRECTIVE", "CATCH_DIRECTIVE", 
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
		"PACKED_SWITCH_DIRECTIVE", "PARAMETER_DIRECTIVE", "PARAM_LIST_OR_ID_PRIMITIVE_TYPE", 
		"POSITIVE_INTEGER_LITERAL", "PRIMITIVE_TYPE", "PROLOGUE_DIRECTIVE", "REGISTER", 
		"REGISTERS_DIRECTIVE", "RESTART_LOCAL_DIRECTIVE", "SHORT_LITERAL", "SIMPLE_NAME", 
		"SOURCE_DIRECTIVE", "SPARSE_SWITCH_DIRECTIVE", "STRING_LITERAL", "SUBANNOTATION_DIRECTIVE", 
		"SUPER_DIRECTIVE", "VERIFICATION_ERROR_TYPE", "VOID_TYPE", "VTABLE_INDEX", 
		"WHITE_SPACE"
	};
	public static final int EOF=-1;
	public static final int ACCESS_SPEC=4;
	public static final int ANNOTATION_DIRECTIVE=5;
	public static final int ANNOTATION_VISIBILITY=6;
	public static final int ARRAY_DATA_DIRECTIVE=7;
	public static final int ARRAY_TYPE_PREFIX=8;
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
	public static final int PARAM_LIST_OR_ID_PRIMITIVE_TYPE=187;
	public static final int POSITIVE_INTEGER_LITERAL=188;
	public static final int PRIMITIVE_TYPE=189;
	public static final int PROLOGUE_DIRECTIVE=190;
	public static final int REGISTER=191;
	public static final int REGISTERS_DIRECTIVE=192;
	public static final int RESTART_LOCAL_DIRECTIVE=193;
	public static final int SHORT_LITERAL=194;
	public static final int SIMPLE_NAME=195;
	public static final int SOURCE_DIRECTIVE=196;
	public static final int SPARSE_SWITCH_DIRECTIVE=197;
	public static final int STRING_LITERAL=198;
	public static final int SUBANNOTATION_DIRECTIVE=199;
	public static final int SUPER_DIRECTIVE=200;
	public static final int VERIFICATION_ERROR_TYPE=201;
	public static final int VOID_TYPE=202;
	public static final int VTABLE_INDEX=203;
	public static final int WHITE_SPACE=204;

	// delegates
	public TreeParser[] getDelegates() {
		return new TreeParser[] {};
	}

	// delegators


	public smaliTreeWalker(TreeNodeStream input) {
		this(input, new RecognizerSharedState());
	}
	public smaliTreeWalker(TreeNodeStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return smaliTreeWalker.tokenNames; }
	@Override public String getGrammarFileName() { return "smaliTreeWalker.g"; }


	  public String classType;
	  protected boolean verboseErrors = false;
	  protected int apiLevel = 15;
	  protected Opcodes opcodes = Opcodes.forApi(apiLevel);
	  protected DexBuilder dexBuilder;

	  public void setDexBuilder(DexBuilder dexBuilder) {
	      this.dexBuilder = dexBuilder;
	  }

	  public void setApiLevel(int apiLevel, boolean experimental) {
	      this.opcodes = new Opcodes(apiLevel, experimental);
	      this.apiLevel = apiLevel;
	  }

	  public void setVerboseErrors(boolean verboseErrors) {
	    this.verboseErrors = verboseErrors;
	  }

	  protected byte parseRegister_nibble(String register)
	      throws SemanticException {
	    int totalMethodRegisters = method_stack.peek().totalMethodRegisters;
	    int methodParameterRegisters = method_stack.peek().methodParameterRegisters;

	    //register should be in the format "v12"
	    int val = Byte.parseByte(register.substring(1));
	    if (register.charAt(0) == 'p') {
	      val = totalMethodRegisters - methodParameterRegisters + val;
	    }
	    if (val >= 2<<4) {
	      throw new SemanticException(input, "The maximum allowed register in this context is list of registers is v15");
	    }
	    //the parser wouldn't have accepted a negative register, i.e. v-1, so we don't have to check for val<0;
	    return (byte)val;
	  }

	  //return a short, because java's byte is signed
	  protected short parseRegister_byte(String register)
	      throws SemanticException {
	    int totalMethodRegisters = method_stack.peek().totalMethodRegisters;
	    int methodParameterRegisters = method_stack.peek().methodParameterRegisters;
	    //register should be in the format "v123"
	    int val = Short.parseShort(register.substring(1));
	    if (register.charAt(0) == 'p') {
	      val = totalMethodRegisters - methodParameterRegisters + val;
	    }
	    if (val >= 2<<8) {
	      throw new SemanticException(input, "The maximum allowed register in this context is v255");
	    }
	    return (short)val;
	  }

	  //return an int because java's short is signed
	  protected int parseRegister_short(String register)
	      throws SemanticException {
	    int totalMethodRegisters = method_stack.peek().totalMethodRegisters;
	    int methodParameterRegisters = method_stack.peek().methodParameterRegisters;
	    //register should be in the format "v12345"
	    int val = Integer.parseInt(register.substring(1));
	    if (register.charAt(0) == 'p') {
	      val = totalMethodRegisters - methodParameterRegisters + val;
	    }
	    if (val >= 2<<16) {
	      throw new SemanticException(input, "The maximum allowed register in this context is v65535");
	    }
	    //the parser wouldn't accept a negative register, i.e. v-1, so we don't have to check for val<0;
	    return val;
	  }

	  public String getErrorMessage(RecognitionException e, String[] tokenNames) {
	    if ( e instanceof SemanticException ) {
	      return e.getMessage();
	    } else {
	      return super.getErrorMessage(e, tokenNames);
	    }
	  }

	  public String getErrorHeader(RecognitionException e) {
	    return getSourceName()+"["+ e.line+","+e.charPositionInLine+"]";
	  }



	// $ANTLR start "smali_file"
	// smaliTreeWalker.g:159:1: smali_file returns [ClassDef classDef] : ^( I_CLASS_DEF header methods fields annotations ) ;
	public final ClassDef smali_file() throws RecognitionException {
		ClassDef classDef = null;


		TreeRuleReturnScope header1 =null;
		Set<Annotation> annotations2 =null;
		List<BuilderField> fields3 =null;
		List<BuilderMethod> methods4 =null;

		try {
			// smaliTreeWalker.g:160:3: ( ^( I_CLASS_DEF header methods fields annotations ) )
			// smaliTreeWalker.g:160:5: ^( I_CLASS_DEF header methods fields annotations )
			{
			match(input,I_CLASS_DEF,FOLLOW_I_CLASS_DEF_in_smali_file52); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_header_in_smali_file54);
			header1=header();
			state._fsp--;

			pushFollow(FOLLOW_methods_in_smali_file56);
			methods4=methods();
			state._fsp--;

			pushFollow(FOLLOW_fields_in_smali_file58);
			fields3=fields();
			state._fsp--;

			pushFollow(FOLLOW_annotations_in_smali_file60);
			annotations2=annotations();
			state._fsp--;

			match(input, Token.UP, null); 


			    classDef = dexBuilder.internClassDef((header1!=null?((smaliTreeWalker.header_return)header1).classType:null), (header1!=null?((smaliTreeWalker.header_return)header1).accessFlags:0), (header1!=null?((smaliTreeWalker.header_return)header1).superType:null),
			            (header1!=null?((smaliTreeWalker.header_return)header1).implementsList:null), (header1!=null?((smaliTreeWalker.header_return)header1).sourceSpec:null), annotations2, fields3, methods4);
			  
			}

		}
		catch (Exception ex) {

			    if (verboseErrors) {
			      ex.printStackTrace(System.err);
			    }
			    reportError(new SemanticException(input, ex));
			  
		}

		finally {
			// do for sure before leaving
		}
		return classDef;
	}
	// $ANTLR end "smali_file"


	public static class header_return extends TreeRuleReturnScope {
		public String classType;
		public int accessFlags;
		public String superType;
		public List<String> implementsList;
		public String sourceSpec;
	};


	// $ANTLR start "header"
	// smaliTreeWalker.g:173:1: header returns [String classType, int accessFlags, String superType, List<String> implementsList, String sourceSpec] : class_spec ( super_spec )? implements_list source_spec ;
	public final smaliTreeWalker.header_return header() throws RecognitionException {
		smaliTreeWalker.header_return retval = new smaliTreeWalker.header_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope class_spec5 =null;
		String super_spec6 =null;
		List<String> implements_list7 =null;
		String source_spec8 =null;

		try {
			// smaliTreeWalker.g:174:3: ( class_spec ( super_spec )? implements_list source_spec )
			// smaliTreeWalker.g:174:3: class_spec ( super_spec )? implements_list source_spec
			{
			pushFollow(FOLLOW_class_spec_in_header85);
			class_spec5=class_spec();
			state._fsp--;

			// smaliTreeWalker.g:174:14: ( super_spec )?
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0==I_SUPER) ) {
				alt1=1;
			}
			switch (alt1) {
				case 1 :
					// smaliTreeWalker.g:174:14: super_spec
					{
					pushFollow(FOLLOW_super_spec_in_header87);
					super_spec6=super_spec();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_implements_list_in_header90);
			implements_list7=implements_list();
			state._fsp--;

			pushFollow(FOLLOW_source_spec_in_header92);
			source_spec8=source_spec();
			state._fsp--;


			    classType = (class_spec5!=null?((smaliTreeWalker.class_spec_return)class_spec5).type:null);
			    retval.classType = classType;
			    retval.accessFlags = (class_spec5!=null?((smaliTreeWalker.class_spec_return)class_spec5).accessFlags:0);
			    retval.superType = super_spec6;
			    retval.implementsList = implements_list7;
			    retval.sourceSpec = source_spec8;
			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "header"


	public static class class_spec_return extends TreeRuleReturnScope {
		public String type;
		public int accessFlags;
	};


	// $ANTLR start "class_spec"
	// smaliTreeWalker.g:185:1: class_spec returns [String type, int accessFlags] : CLASS_DESCRIPTOR access_list ;
	public final smaliTreeWalker.class_spec_return class_spec() throws RecognitionException {
		smaliTreeWalker.class_spec_return retval = new smaliTreeWalker.class_spec_return();
		retval.start = input.LT(1);

		CommonTree CLASS_DESCRIPTOR9=null;
		int access_list10 =0;

		try {
			// smaliTreeWalker.g:186:3: ( CLASS_DESCRIPTOR access_list )
			// smaliTreeWalker.g:186:5: CLASS_DESCRIPTOR access_list
			{
			CLASS_DESCRIPTOR9=(CommonTree)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_class_spec110); 
			pushFollow(FOLLOW_access_list_in_class_spec112);
			access_list10=access_list();
			state._fsp--;


			    retval.type = (CLASS_DESCRIPTOR9!=null?CLASS_DESCRIPTOR9.getText():null);
			    retval.accessFlags = access_list10;
			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "class_spec"



	// $ANTLR start "super_spec"
	// smaliTreeWalker.g:192:1: super_spec returns [String type] : ^( I_SUPER CLASS_DESCRIPTOR ) ;
	public final String super_spec() throws RecognitionException {
		String type = null;


		CommonTree CLASS_DESCRIPTOR11=null;

		try {
			// smaliTreeWalker.g:193:3: ( ^( I_SUPER CLASS_DESCRIPTOR ) )
			// smaliTreeWalker.g:193:5: ^( I_SUPER CLASS_DESCRIPTOR )
			{
			match(input,I_SUPER,FOLLOW_I_SUPER_in_super_spec130); 
			match(input, Token.DOWN, null); 
			CLASS_DESCRIPTOR11=(CommonTree)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_super_spec132); 
			match(input, Token.UP, null); 


			    type = (CLASS_DESCRIPTOR11!=null?CLASS_DESCRIPTOR11.getText():null);
			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return type;
	}
	// $ANTLR end "super_spec"



	// $ANTLR start "implements_spec"
	// smaliTreeWalker.g:199:1: implements_spec returns [String type] : ^( I_IMPLEMENTS CLASS_DESCRIPTOR ) ;
	public final String implements_spec() throws RecognitionException {
		String type = null;


		CommonTree CLASS_DESCRIPTOR12=null;

		try {
			// smaliTreeWalker.g:200:3: ( ^( I_IMPLEMENTS CLASS_DESCRIPTOR ) )
			// smaliTreeWalker.g:200:5: ^( I_IMPLEMENTS CLASS_DESCRIPTOR )
			{
			match(input,I_IMPLEMENTS,FOLLOW_I_IMPLEMENTS_in_implements_spec152); 
			match(input, Token.DOWN, null); 
			CLASS_DESCRIPTOR12=(CommonTree)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_implements_spec154); 
			match(input, Token.UP, null); 


			    type = (CLASS_DESCRIPTOR12!=null?CLASS_DESCRIPTOR12.getText():null);
			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return type;
	}
	// $ANTLR end "implements_spec"



	// $ANTLR start "implements_list"
	// smaliTreeWalker.g:205:1: implements_list returns [List<String> implementsList] : ( implements_spec )* ;
	public final List<String> implements_list() throws RecognitionException {
		List<String> implementsList = null;


		String implements_spec13 =null;

		 List<String> typeList; 
		try {
			// smaliTreeWalker.g:207:3: ( ( implements_spec )* )
			// smaliTreeWalker.g:207:5: ( implements_spec )*
			{
			typeList = Lists.newArrayList();
			// smaliTreeWalker.g:208:5: ( implements_spec )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==I_IMPLEMENTS) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// smaliTreeWalker.g:208:6: implements_spec
					{
					pushFollow(FOLLOW_implements_spec_in_implements_list184);
					implements_spec13=implements_spec();
					state._fsp--;

					typeList.add(implements_spec13);
					}
					break;

				default :
					break loop2;
				}
			}


			    if (typeList.size() > 0) {
			      implementsList = typeList;
			    } else {
			      implementsList = null;
			    }
			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return implementsList;
	}
	// $ANTLR end "implements_list"



	// $ANTLR start "source_spec"
	// smaliTreeWalker.g:217:1: source_spec returns [String source] : ( ^( I_SOURCE string_literal ) |);
	public final String source_spec() throws RecognitionException {
		String source = null;


		String string_literal14 =null;

		try {
			// smaliTreeWalker.g:218:3: ( ^( I_SOURCE string_literal ) |)
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==I_SOURCE) ) {
				alt3=1;
			}
			else if ( (LA3_0==I_METHODS) ) {
				alt3=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}

			switch (alt3) {
				case 1 :
					// smaliTreeWalker.g:218:5: ^( I_SOURCE string_literal )
					{
					source = null;
					match(input,I_SOURCE,FOLLOW_I_SOURCE_in_source_spec213); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_string_literal_in_source_spec215);
					string_literal14=string_literal();
					state._fsp--;

					source = string_literal14;
					match(input, Token.UP, null); 

					}
					break;
				case 2 :
					// smaliTreeWalker.g:220:16: 
					{
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return source;
	}
	// $ANTLR end "source_spec"



	// $ANTLR start "access_list"
	// smaliTreeWalker.g:222:1: access_list returns [int value] : ^( I_ACCESS_LIST ( ACCESS_SPEC )* ) ;
	public final int access_list() throws RecognitionException {
		int value = 0;


		CommonTree ACCESS_SPEC15=null;


		    value = 0;
		  
		try {
			// smaliTreeWalker.g:227:3: ( ^( I_ACCESS_LIST ( ACCESS_SPEC )* ) )
			// smaliTreeWalker.g:227:5: ^( I_ACCESS_LIST ( ACCESS_SPEC )* )
			{
			match(input,I_ACCESS_LIST,FOLLOW_I_ACCESS_LIST_in_access_list248); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// smaliTreeWalker.g:228:7: ( ACCESS_SPEC )*
				loop4:
				while (true) {
					int alt4=2;
					int LA4_0 = input.LA(1);
					if ( (LA4_0==ACCESS_SPEC) ) {
						alt4=1;
					}

					switch (alt4) {
					case 1 :
						// smaliTreeWalker.g:229:9: ACCESS_SPEC
						{
						ACCESS_SPEC15=(CommonTree)match(input,ACCESS_SPEC,FOLLOW_ACCESS_SPEC_in_access_list266); 

						          value |= AccessFlags.getAccessFlag(ACCESS_SPEC15.getText()).getValue();
						        
						}
						break;

					default :
						break loop4;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "access_list"



	// $ANTLR start "fields"
	// smaliTreeWalker.g:236:1: fields returns [List<BuilderField> fields] : ^( I_FIELDS ( field )* ) ;
	public final List<BuilderField> fields() throws RecognitionException {
		List<BuilderField> fields = null;


		BuilderField field16 =null;

		fields = Lists.newArrayList();
		try {
			// smaliTreeWalker.g:238:3: ( ^( I_FIELDS ( field )* ) )
			// smaliTreeWalker.g:238:5: ^( I_FIELDS ( field )* )
			{
			match(input,I_FIELDS,FOLLOW_I_FIELDS_in_fields308); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// smaliTreeWalker.g:239:7: ( field )*
				loop5:
				while (true) {
					int alt5=2;
					int LA5_0 = input.LA(1);
					if ( (LA5_0==I_FIELD) ) {
						alt5=1;
					}

					switch (alt5) {
					case 1 :
						// smaliTreeWalker.g:239:8: field
						{
						pushFollow(FOLLOW_field_in_fields317);
						field16=field();
						state._fsp--;


						        fields.add(field16);
						      
						}
						break;

					default :
						break loop5;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return fields;
	}
	// $ANTLR end "fields"



	// $ANTLR start "methods"
	// smaliTreeWalker.g:244:1: methods returns [List<BuilderMethod> methods] : ^( I_METHODS ( method )* ) ;
	public final List<BuilderMethod> methods() throws RecognitionException {
		List<BuilderMethod> methods = null;


		BuilderMethod method17 =null;

		methods = Lists.newArrayList();
		try {
			// smaliTreeWalker.g:246:3: ( ^( I_METHODS ( method )* ) )
			// smaliTreeWalker.g:246:5: ^( I_METHODS ( method )* )
			{
			match(input,I_METHODS,FOLLOW_I_METHODS_in_methods349); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// smaliTreeWalker.g:247:7: ( method )*
				loop6:
				while (true) {
					int alt6=2;
					int LA6_0 = input.LA(1);
					if ( (LA6_0==I_METHOD) ) {
						alt6=1;
					}

					switch (alt6) {
					case 1 :
						// smaliTreeWalker.g:247:8: method
						{
						pushFollow(FOLLOW_method_in_methods358);
						method17=method();
						state._fsp--;


						        methods.add(method17);
						      
						}
						break;

					default :
						break loop6;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return methods;
	}
	// $ANTLR end "methods"



	// $ANTLR start "field"
	// smaliTreeWalker.g:252:1: field returns [BuilderField field] : ^( I_FIELD SIMPLE_NAME access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) field_initial_value ( annotations )? ) ;
	public final BuilderField field() throws RecognitionException {
		BuilderField field = null;


		CommonTree SIMPLE_NAME20=null;
		int access_list18 =0;
		EncodedValue field_initial_value19 =null;
		TreeRuleReturnScope nonvoid_type_descriptor21 =null;
		Set<Annotation> annotations22 =null;

		try {
			// smaliTreeWalker.g:253:3: ( ^( I_FIELD SIMPLE_NAME access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) field_initial_value ( annotations )? ) )
			// smaliTreeWalker.g:253:4: ^( I_FIELD SIMPLE_NAME access_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) field_initial_value ( annotations )? )
			{
			match(input,I_FIELD,FOLLOW_I_FIELD_in_field383); 
			match(input, Token.DOWN, null); 
			SIMPLE_NAME20=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_field385); 
			pushFollow(FOLLOW_access_list_in_field387);
			access_list18=access_list();
			state._fsp--;

			match(input,I_FIELD_TYPE,FOLLOW_I_FIELD_TYPE_in_field390); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_nonvoid_type_descriptor_in_field392);
			nonvoid_type_descriptor21=nonvoid_type_descriptor();
			state._fsp--;

			match(input, Token.UP, null); 

			pushFollow(FOLLOW_field_initial_value_in_field395);
			field_initial_value19=field_initial_value();
			state._fsp--;

			// smaliTreeWalker.g:253:98: ( annotations )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==I_ANNOTATIONS) ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// smaliTreeWalker.g:253:98: annotations
					{
					pushFollow(FOLLOW_annotations_in_field397);
					annotations22=annotations();
					state._fsp--;

					}
					break;

			}

			match(input, Token.UP, null); 


			    int accessFlags = access_list18;


			    if (!AccessFlags.STATIC.isSet(accessFlags) && field_initial_value19 != null) {
			        throw new SemanticException(input, "Initial field values can only be specified for static fields.");
			    }

			    field = dexBuilder.internField(classType, (SIMPLE_NAME20!=null?SIMPLE_NAME20.getText():null), (nonvoid_type_descriptor21!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor21).type:null), access_list18,
			            field_initial_value19, annotations22);
			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return field;
	}
	// $ANTLR end "field"



	// $ANTLR start "field_initial_value"
	// smaliTreeWalker.g:267:1: field_initial_value returns [EncodedValue encodedValue] : ( ^( I_FIELD_INITIAL_VALUE literal ) |);
	public final EncodedValue field_initial_value() throws RecognitionException {
		EncodedValue encodedValue = null;


		EncodedValue literal23 =null;

		try {
			// smaliTreeWalker.g:268:3: ( ^( I_FIELD_INITIAL_VALUE literal ) |)
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==I_FIELD_INITIAL_VALUE) ) {
				alt8=1;
			}
			else if ( (LA8_0==UP||LA8_0==I_ANNOTATIONS) ) {
				alt8=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}

			switch (alt8) {
				case 1 :
					// smaliTreeWalker.g:268:5: ^( I_FIELD_INITIAL_VALUE literal )
					{
					match(input,I_FIELD_INITIAL_VALUE,FOLLOW_I_FIELD_INITIAL_VALUE_in_field_initial_value418); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_literal_in_field_initial_value420);
					literal23=literal();
					state._fsp--;

					match(input, Token.UP, null); 

					encodedValue = literal23;
					}
					break;
				case 2 :
					// smaliTreeWalker.g:269:16: 
					{
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return encodedValue;
	}
	// $ANTLR end "field_initial_value"



	// $ANTLR start "literal"
	// smaliTreeWalker.g:271:1: literal returns [EncodedValue encodedValue] : ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | string_literal | bool_literal | NULL_LITERAL | type_descriptor | array_literal | subannotation | field_literal | method_literal | enum_literal );
	public final EncodedValue literal() throws RecognitionException {
		EncodedValue encodedValue = null;


		int integer_literal24 =0;
		long long_literal25 =0;
		short short_literal26 =0;
		byte byte_literal27 =0;
		float float_literal28 =0.0f;
		double double_literal29 =0.0;
		char char_literal30 =0;
		String string_literal31 =null;
		boolean bool_literal32 =false;
		String type_descriptor33 =null;
		List<EncodedValue> array_literal34 =null;
		TreeRuleReturnScope subannotation35 =null;
		FieldReference field_literal36 =null;
		MethodReference method_literal37 =null;
		FieldReference enum_literal38 =null;

		try {
			// smaliTreeWalker.g:272:3: ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | string_literal | bool_literal | NULL_LITERAL | type_descriptor | array_literal | subannotation | field_literal | method_literal | enum_literal )
			int alt9=16;
			switch ( input.LA(1) ) {
			case INTEGER_LITERAL:
				{
				alt9=1;
				}
				break;
			case LONG_LITERAL:
				{
				alt9=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt9=3;
				}
				break;
			case BYTE_LITERAL:
				{
				alt9=4;
				}
				break;
			case FLOAT_LITERAL:
				{
				alt9=5;
				}
				break;
			case DOUBLE_LITERAL:
				{
				alt9=6;
				}
				break;
			case CHAR_LITERAL:
				{
				alt9=7;
				}
				break;
			case STRING_LITERAL:
				{
				alt9=8;
				}
				break;
			case BOOL_LITERAL:
				{
				alt9=9;
				}
				break;
			case NULL_LITERAL:
				{
				alt9=10;
				}
				break;
			case ARRAY_TYPE_PREFIX:
			case CLASS_DESCRIPTOR:
			case PRIMITIVE_TYPE:
			case VOID_TYPE:
				{
				alt9=11;
				}
				break;
			case I_ENCODED_ARRAY:
				{
				alt9=12;
				}
				break;
			case I_SUBANNOTATION:
				{
				alt9=13;
				}
				break;
			case I_ENCODED_FIELD:
				{
				alt9=14;
				}
				break;
			case I_ENCODED_METHOD:
				{
				alt9=15;
				}
				break;
			case I_ENCODED_ENUM:
				{
				alt9=16;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}
			switch (alt9) {
				case 1 :
					// smaliTreeWalker.g:272:5: integer_literal
					{
					pushFollow(FOLLOW_integer_literal_in_literal442);
					integer_literal24=integer_literal();
					state._fsp--;

					 encodedValue = new ImmutableIntEncodedValue(integer_literal24); 
					}
					break;
				case 2 :
					// smaliTreeWalker.g:273:5: long_literal
					{
					pushFollow(FOLLOW_long_literal_in_literal450);
					long_literal25=long_literal();
					state._fsp--;

					 encodedValue = new ImmutableLongEncodedValue(long_literal25); 
					}
					break;
				case 3 :
					// smaliTreeWalker.g:274:5: short_literal
					{
					pushFollow(FOLLOW_short_literal_in_literal458);
					short_literal26=short_literal();
					state._fsp--;

					 encodedValue = new ImmutableShortEncodedValue(short_literal26); 
					}
					break;
				case 4 :
					// smaliTreeWalker.g:275:5: byte_literal
					{
					pushFollow(FOLLOW_byte_literal_in_literal466);
					byte_literal27=byte_literal();
					state._fsp--;

					 encodedValue = new ImmutableByteEncodedValue(byte_literal27); 
					}
					break;
				case 5 :
					// smaliTreeWalker.g:276:5: float_literal
					{
					pushFollow(FOLLOW_float_literal_in_literal474);
					float_literal28=float_literal();
					state._fsp--;

					 encodedValue = new ImmutableFloatEncodedValue(float_literal28); 
					}
					break;
				case 6 :
					// smaliTreeWalker.g:277:5: double_literal
					{
					pushFollow(FOLLOW_double_literal_in_literal482);
					double_literal29=double_literal();
					state._fsp--;

					 encodedValue = new ImmutableDoubleEncodedValue(double_literal29); 
					}
					break;
				case 7 :
					// smaliTreeWalker.g:278:5: char_literal
					{
					pushFollow(FOLLOW_char_literal_in_literal490);
					char_literal30=char_literal();
					state._fsp--;

					 encodedValue = new ImmutableCharEncodedValue(char_literal30); 
					}
					break;
				case 8 :
					// smaliTreeWalker.g:279:5: string_literal
					{
					pushFollow(FOLLOW_string_literal_in_literal498);
					string_literal31=string_literal();
					state._fsp--;

					 encodedValue = new ImmutableStringEncodedValue(string_literal31); 
					}
					break;
				case 9 :
					// smaliTreeWalker.g:280:5: bool_literal
					{
					pushFollow(FOLLOW_bool_literal_in_literal506);
					bool_literal32=bool_literal();
					state._fsp--;

					 encodedValue = ImmutableBooleanEncodedValue.forBoolean(bool_literal32); 
					}
					break;
				case 10 :
					// smaliTreeWalker.g:281:5: NULL_LITERAL
					{
					match(input,NULL_LITERAL,FOLLOW_NULL_LITERAL_in_literal514); 
					 encodedValue = ImmutableNullEncodedValue.INSTANCE; 
					}
					break;
				case 11 :
					// smaliTreeWalker.g:282:5: type_descriptor
					{
					pushFollow(FOLLOW_type_descriptor_in_literal522);
					type_descriptor33=type_descriptor();
					state._fsp--;

					 encodedValue = new ImmutableTypeEncodedValue(type_descriptor33); 
					}
					break;
				case 12 :
					// smaliTreeWalker.g:283:5: array_literal
					{
					pushFollow(FOLLOW_array_literal_in_literal530);
					array_literal34=array_literal();
					state._fsp--;

					 encodedValue = new ImmutableArrayEncodedValue(array_literal34); 
					}
					break;
				case 13 :
					// smaliTreeWalker.g:284:5: subannotation
					{
					pushFollow(FOLLOW_subannotation_in_literal538);
					subannotation35=subannotation();
					state._fsp--;

					 encodedValue = new ImmutableAnnotationEncodedValue((subannotation35!=null?((smaliTreeWalker.subannotation_return)subannotation35).annotationType:null), (subannotation35!=null?((smaliTreeWalker.subannotation_return)subannotation35).elements:null)); 
					}
					break;
				case 14 :
					// smaliTreeWalker.g:285:5: field_literal
					{
					pushFollow(FOLLOW_field_literal_in_literal546);
					field_literal36=field_literal();
					state._fsp--;

					 encodedValue = new ImmutableFieldEncodedValue(field_literal36); 
					}
					break;
				case 15 :
					// smaliTreeWalker.g:286:5: method_literal
					{
					pushFollow(FOLLOW_method_literal_in_literal554);
					method_literal37=method_literal();
					state._fsp--;

					 encodedValue = new ImmutableMethodEncodedValue(method_literal37); 
					}
					break;
				case 16 :
					// smaliTreeWalker.g:287:5: enum_literal
					{
					pushFollow(FOLLOW_enum_literal_in_literal562);
					enum_literal38=enum_literal();
					state._fsp--;

					 encodedValue = new ImmutableEnumEncodedValue(enum_literal38); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return encodedValue;
	}
	// $ANTLR end "literal"



	// $ANTLR start "fixed_64bit_literal_number"
	// smaliTreeWalker.g:290:1: fixed_64bit_literal_number returns [Number value] : ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | bool_literal );
	public final Number fixed_64bit_literal_number() throws RecognitionException {
		Number value = null;


		int integer_literal39 =0;
		long long_literal40 =0;
		short short_literal41 =0;
		byte byte_literal42 =0;
		float float_literal43 =0.0f;
		double double_literal44 =0.0;
		char char_literal45 =0;
		boolean bool_literal46 =false;

		try {
			// smaliTreeWalker.g:291:3: ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | bool_literal )
			int alt10=8;
			switch ( input.LA(1) ) {
			case INTEGER_LITERAL:
				{
				alt10=1;
				}
				break;
			case LONG_LITERAL:
				{
				alt10=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt10=3;
				}
				break;
			case BYTE_LITERAL:
				{
				alt10=4;
				}
				break;
			case FLOAT_LITERAL:
				{
				alt10=5;
				}
				break;
			case DOUBLE_LITERAL:
				{
				alt10=6;
				}
				break;
			case CHAR_LITERAL:
				{
				alt10=7;
				}
				break;
			case BOOL_LITERAL:
				{
				alt10=8;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}
			switch (alt10) {
				case 1 :
					// smaliTreeWalker.g:291:5: integer_literal
					{
					pushFollow(FOLLOW_integer_literal_in_fixed_64bit_literal_number578);
					integer_literal39=integer_literal();
					state._fsp--;

					 value = integer_literal39; 
					}
					break;
				case 2 :
					// smaliTreeWalker.g:292:5: long_literal
					{
					pushFollow(FOLLOW_long_literal_in_fixed_64bit_literal_number586);
					long_literal40=long_literal();
					state._fsp--;

					 value = long_literal40; 
					}
					break;
				case 3 :
					// smaliTreeWalker.g:293:5: short_literal
					{
					pushFollow(FOLLOW_short_literal_in_fixed_64bit_literal_number594);
					short_literal41=short_literal();
					state._fsp--;

					 value = short_literal41; 
					}
					break;
				case 4 :
					// smaliTreeWalker.g:294:5: byte_literal
					{
					pushFollow(FOLLOW_byte_literal_in_fixed_64bit_literal_number602);
					byte_literal42=byte_literal();
					state._fsp--;

					 value = byte_literal42; 
					}
					break;
				case 5 :
					// smaliTreeWalker.g:295:5: float_literal
					{
					pushFollow(FOLLOW_float_literal_in_fixed_64bit_literal_number610);
					float_literal43=float_literal();
					state._fsp--;

					 value = Float.floatToRawIntBits(float_literal43); 
					}
					break;
				case 6 :
					// smaliTreeWalker.g:296:5: double_literal
					{
					pushFollow(FOLLOW_double_literal_in_fixed_64bit_literal_number618);
					double_literal44=double_literal();
					state._fsp--;

					 value = Double.doubleToRawLongBits(double_literal44); 
					}
					break;
				case 7 :
					// smaliTreeWalker.g:297:5: char_literal
					{
					pushFollow(FOLLOW_char_literal_in_fixed_64bit_literal_number626);
					char_literal45=char_literal();
					state._fsp--;

					 value = (int)char_literal45; 
					}
					break;
				case 8 :
					// smaliTreeWalker.g:298:5: bool_literal
					{
					pushFollow(FOLLOW_bool_literal_in_fixed_64bit_literal_number634);
					bool_literal46=bool_literal();
					state._fsp--;

					 value = bool_literal46?1:0; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "fixed_64bit_literal_number"



	// $ANTLR start "fixed_64bit_literal"
	// smaliTreeWalker.g:300:1: fixed_64bit_literal returns [long value] : ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | bool_literal );
	public final long fixed_64bit_literal() throws RecognitionException {
		long value = 0;


		int integer_literal47 =0;
		long long_literal48 =0;
		short short_literal49 =0;
		byte byte_literal50 =0;
		float float_literal51 =0.0f;
		double double_literal52 =0.0;
		char char_literal53 =0;
		boolean bool_literal54 =false;

		try {
			// smaliTreeWalker.g:301:3: ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | bool_literal )
			int alt11=8;
			switch ( input.LA(1) ) {
			case INTEGER_LITERAL:
				{
				alt11=1;
				}
				break;
			case LONG_LITERAL:
				{
				alt11=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt11=3;
				}
				break;
			case BYTE_LITERAL:
				{
				alt11=4;
				}
				break;
			case FLOAT_LITERAL:
				{
				alt11=5;
				}
				break;
			case DOUBLE_LITERAL:
				{
				alt11=6;
				}
				break;
			case CHAR_LITERAL:
				{
				alt11=7;
				}
				break;
			case BOOL_LITERAL:
				{
				alt11=8;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// smaliTreeWalker.g:301:5: integer_literal
					{
					pushFollow(FOLLOW_integer_literal_in_fixed_64bit_literal649);
					integer_literal47=integer_literal();
					state._fsp--;

					 value = integer_literal47; 
					}
					break;
				case 2 :
					// smaliTreeWalker.g:302:5: long_literal
					{
					pushFollow(FOLLOW_long_literal_in_fixed_64bit_literal657);
					long_literal48=long_literal();
					state._fsp--;

					 value = long_literal48; 
					}
					break;
				case 3 :
					// smaliTreeWalker.g:303:5: short_literal
					{
					pushFollow(FOLLOW_short_literal_in_fixed_64bit_literal665);
					short_literal49=short_literal();
					state._fsp--;

					 value = short_literal49; 
					}
					break;
				case 4 :
					// smaliTreeWalker.g:304:5: byte_literal
					{
					pushFollow(FOLLOW_byte_literal_in_fixed_64bit_literal673);
					byte_literal50=byte_literal();
					state._fsp--;

					 value = byte_literal50; 
					}
					break;
				case 5 :
					// smaliTreeWalker.g:305:5: float_literal
					{
					pushFollow(FOLLOW_float_literal_in_fixed_64bit_literal681);
					float_literal51=float_literal();
					state._fsp--;

					 value = Float.floatToRawIntBits(float_literal51); 
					}
					break;
				case 6 :
					// smaliTreeWalker.g:306:5: double_literal
					{
					pushFollow(FOLLOW_double_literal_in_fixed_64bit_literal689);
					double_literal52=double_literal();
					state._fsp--;

					 value = Double.doubleToRawLongBits(double_literal52); 
					}
					break;
				case 7 :
					// smaliTreeWalker.g:307:5: char_literal
					{
					pushFollow(FOLLOW_char_literal_in_fixed_64bit_literal697);
					char_literal53=char_literal();
					state._fsp--;

					 value = char_literal53; 
					}
					break;
				case 8 :
					// smaliTreeWalker.g:308:5: bool_literal
					{
					pushFollow(FOLLOW_bool_literal_in_fixed_64bit_literal705);
					bool_literal54=bool_literal();
					state._fsp--;

					 value = bool_literal54?1:0; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "fixed_64bit_literal"



	// $ANTLR start "fixed_32bit_literal"
	// smaliTreeWalker.g:312:1: fixed_32bit_literal returns [int value] : ( integer_literal | long_literal | short_literal | byte_literal | float_literal | char_literal | bool_literal );
	public final int fixed_32bit_literal() throws RecognitionException {
		int value = 0;


		int integer_literal55 =0;
		long long_literal56 =0;
		short short_literal57 =0;
		byte byte_literal58 =0;
		float float_literal59 =0.0f;
		char char_literal60 =0;
		boolean bool_literal61 =false;

		try {
			// smaliTreeWalker.g:313:3: ( integer_literal | long_literal | short_literal | byte_literal | float_literal | char_literal | bool_literal )
			int alt12=7;
			switch ( input.LA(1) ) {
			case INTEGER_LITERAL:
				{
				alt12=1;
				}
				break;
			case LONG_LITERAL:
				{
				alt12=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt12=3;
				}
				break;
			case BYTE_LITERAL:
				{
				alt12=4;
				}
				break;
			case FLOAT_LITERAL:
				{
				alt12=5;
				}
				break;
			case CHAR_LITERAL:
				{
				alt12=6;
				}
				break;
			case BOOL_LITERAL:
				{
				alt12=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}
			switch (alt12) {
				case 1 :
					// smaliTreeWalker.g:313:5: integer_literal
					{
					pushFollow(FOLLOW_integer_literal_in_fixed_32bit_literal722);
					integer_literal55=integer_literal();
					state._fsp--;

					 value = integer_literal55; 
					}
					break;
				case 2 :
					// smaliTreeWalker.g:314:5: long_literal
					{
					pushFollow(FOLLOW_long_literal_in_fixed_32bit_literal730);
					long_literal56=long_literal();
					state._fsp--;

					 LiteralTools.checkInt(long_literal56); value = (int)long_literal56; 
					}
					break;
				case 3 :
					// smaliTreeWalker.g:315:5: short_literal
					{
					pushFollow(FOLLOW_short_literal_in_fixed_32bit_literal738);
					short_literal57=short_literal();
					state._fsp--;

					 value = short_literal57; 
					}
					break;
				case 4 :
					// smaliTreeWalker.g:316:5: byte_literal
					{
					pushFollow(FOLLOW_byte_literal_in_fixed_32bit_literal746);
					byte_literal58=byte_literal();
					state._fsp--;

					 value = byte_literal58; 
					}
					break;
				case 5 :
					// smaliTreeWalker.g:317:5: float_literal
					{
					pushFollow(FOLLOW_float_literal_in_fixed_32bit_literal754);
					float_literal59=float_literal();
					state._fsp--;

					 value = Float.floatToRawIntBits(float_literal59); 
					}
					break;
				case 6 :
					// smaliTreeWalker.g:318:5: char_literal
					{
					pushFollow(FOLLOW_char_literal_in_fixed_32bit_literal762);
					char_literal60=char_literal();
					state._fsp--;

					 value = char_literal60; 
					}
					break;
				case 7 :
					// smaliTreeWalker.g:319:5: bool_literal
					{
					pushFollow(FOLLOW_bool_literal_in_fixed_32bit_literal770);
					bool_literal61=bool_literal();
					state._fsp--;

					 value = bool_literal61?1:0; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "fixed_32bit_literal"



	// $ANTLR start "array_elements"
	// smaliTreeWalker.g:321:1: array_elements returns [List<Number> elements] : ^( I_ARRAY_ELEMENTS ( fixed_64bit_literal_number )* ) ;
	public final List<Number> array_elements() throws RecognitionException {
		List<Number> elements = null;


		Number fixed_64bit_literal_number62 =null;

		try {
			// smaliTreeWalker.g:322:3: ( ^( I_ARRAY_ELEMENTS ( fixed_64bit_literal_number )* ) )
			// smaliTreeWalker.g:322:5: ^( I_ARRAY_ELEMENTS ( fixed_64bit_literal_number )* )
			{
			elements = Lists.newArrayList();
			match(input,I_ARRAY_ELEMENTS,FOLLOW_I_ARRAY_ELEMENTS_in_array_elements792); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// smaliTreeWalker.g:324:7: ( fixed_64bit_literal_number )*
				loop13:
				while (true) {
					int alt13=2;
					int LA13_0 = input.LA(1);
					if ( ((LA13_0 >= BOOL_LITERAL && LA13_0 <= BYTE_LITERAL)||LA13_0==CHAR_LITERAL||LA13_0==DOUBLE_LITERAL||LA13_0==FLOAT_LITERAL||LA13_0==INTEGER_LITERAL||LA13_0==LONG_LITERAL||LA13_0==SHORT_LITERAL) ) {
						alt13=1;
					}

					switch (alt13) {
					case 1 :
						// smaliTreeWalker.g:324:8: fixed_64bit_literal_number
						{
						pushFollow(FOLLOW_fixed_64bit_literal_number_in_array_elements801);
						fixed_64bit_literal_number62=fixed_64bit_literal_number();
						state._fsp--;


						        elements.add(fixed_64bit_literal_number62);
						      
						}
						break;

					default :
						break loop13;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return elements;
	}
	// $ANTLR end "array_elements"



	// $ANTLR start "packed_switch_elements"
	// smaliTreeWalker.g:329:1: packed_switch_elements returns [List<Label> elements] : ^( I_PACKED_SWITCH_ELEMENTS ( label_ref )* ) ;
	public final List<Label> packed_switch_elements() throws RecognitionException {
		List<Label> elements = null;


		Label label_ref63 =null;

		elements = Lists.newArrayList();
		try {
			// smaliTreeWalker.g:331:3: ( ^( I_PACKED_SWITCH_ELEMENTS ( label_ref )* ) )
			// smaliTreeWalker.g:332:5: ^( I_PACKED_SWITCH_ELEMENTS ( label_ref )* )
			{
			match(input,I_PACKED_SWITCH_ELEMENTS,FOLLOW_I_PACKED_SWITCH_ELEMENTS_in_packed_switch_elements837); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// smaliTreeWalker.g:333:7: ( label_ref )*
				loop14:
				while (true) {
					int alt14=2;
					int LA14_0 = input.LA(1);
					if ( (LA14_0==SIMPLE_NAME) ) {
						alt14=1;
					}

					switch (alt14) {
					case 1 :
						// smaliTreeWalker.g:333:8: label_ref
						{
						pushFollow(FOLLOW_label_ref_in_packed_switch_elements846);
						label_ref63=label_ref();
						state._fsp--;

						 elements.add(label_ref63); 
						}
						break;

					default :
						break loop14;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return elements;
	}
	// $ANTLR end "packed_switch_elements"



	// $ANTLR start "sparse_switch_elements"
	// smaliTreeWalker.g:336:1: sparse_switch_elements returns [List<SwitchLabelElement> elements] : ^( I_SPARSE_SWITCH_ELEMENTS ( fixed_32bit_literal label_ref )* ) ;
	public final List<SwitchLabelElement> sparse_switch_elements() throws RecognitionException {
		List<SwitchLabelElement> elements = null;


		int fixed_32bit_literal64 =0;
		Label label_ref65 =null;

		elements = Lists.newArrayList();
		try {
			// smaliTreeWalker.g:338:3: ( ^( I_SPARSE_SWITCH_ELEMENTS ( fixed_32bit_literal label_ref )* ) )
			// smaliTreeWalker.g:339:5: ^( I_SPARSE_SWITCH_ELEMENTS ( fixed_32bit_literal label_ref )* )
			{
			match(input,I_SPARSE_SWITCH_ELEMENTS,FOLLOW_I_SPARSE_SWITCH_ELEMENTS_in_sparse_switch_elements881); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// smaliTreeWalker.g:340:8: ( fixed_32bit_literal label_ref )*
				loop15:
				while (true) {
					int alt15=2;
					int LA15_0 = input.LA(1);
					if ( ((LA15_0 >= BOOL_LITERAL && LA15_0 <= BYTE_LITERAL)||LA15_0==CHAR_LITERAL||LA15_0==FLOAT_LITERAL||LA15_0==INTEGER_LITERAL||LA15_0==LONG_LITERAL||LA15_0==SHORT_LITERAL) ) {
						alt15=1;
					}

					switch (alt15) {
					case 1 :
						// smaliTreeWalker.g:340:9: fixed_32bit_literal label_ref
						{
						pushFollow(FOLLOW_fixed_32bit_literal_in_sparse_switch_elements891);
						fixed_32bit_literal64=fixed_32bit_literal();
						state._fsp--;

						pushFollow(FOLLOW_label_ref_in_sparse_switch_elements893);
						label_ref65=label_ref();
						state._fsp--;


						         elements.add(new SwitchLabelElement(fixed_32bit_literal64, label_ref65));
						       
						}
						break;

					default :
						break loop15;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return elements;
	}
	// $ANTLR end "sparse_switch_elements"


	protected static class method_scope {
		boolean isStatic;
		int totalMethodRegisters;
		int methodParameterRegisters;
		MethodImplementationBuilder methodBuilder;
	}
	protected Stack<method_scope> method_stack = new Stack<method_scope>();


	// $ANTLR start "method"
	// smaliTreeWalker.g:346:1: method returns [BuilderMethod ret] : ^( I_METHOD method_name_and_prototype access_list ( ( registers_directive ) |) ordered_method_items catches parameters[$method_name_and_prototype.parameters] annotations ) ;
	public final BuilderMethod method() throws RecognitionException {
		method_stack.push(new method_scope());
		BuilderMethod ret = null;


		CommonTree I_METHOD70=null;
		int access_list66 =0;
		TreeRuleReturnScope method_name_and_prototype67 =null;
		TreeRuleReturnScope registers_directive68 =null;
		List<BuilderTryBlock> catches69 =null;
		Set<Annotation> annotations71 =null;


		    method_stack.peek().totalMethodRegisters = 0;
		    method_stack.peek().methodParameterRegisters = 0;
		    int accessFlags = 0;
		    method_stack.peek().isStatic = false;
		  
		try {
			// smaliTreeWalker.g:361:3: ( ^( I_METHOD method_name_and_prototype access_list ( ( registers_directive ) |) ordered_method_items catches parameters[$method_name_and_prototype.parameters] annotations ) )
			// smaliTreeWalker.g:362:5: ^( I_METHOD method_name_and_prototype access_list ( ( registers_directive ) |) ordered_method_items catches parameters[$method_name_and_prototype.parameters] annotations )
			{
			I_METHOD70=(CommonTree)match(input,I_METHOD,FOLLOW_I_METHOD_in_method945); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_method_name_and_prototype_in_method953);
			method_name_and_prototype67=method_name_and_prototype();
			state._fsp--;

			pushFollow(FOLLOW_access_list_in_method961);
			access_list66=access_list();
			state._fsp--;


			        accessFlags = access_list66;
			        method_stack.peek().isStatic = AccessFlags.STATIC.isSet(accessFlags);
			        method_stack.peek().methodParameterRegisters =
			                MethodUtil.getParameterRegisterCount((method_name_and_prototype67!=null?((smaliTreeWalker.method_name_and_prototype_return)method_name_and_prototype67).parameters:null), method_stack.peek().isStatic);
			      
			// smaliTreeWalker.g:371:7: ( ( registers_directive ) |)
			int alt16=2;
			int LA16_0 = input.LA(1);
			if ( (LA16_0==I_LOCALS||LA16_0==I_REGISTERS) ) {
				alt16=1;
			}
			else if ( (LA16_0==I_ORDERED_METHOD_ITEMS) ) {
				alt16=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 16, 0, input);
				throw nvae;
			}

			switch (alt16) {
				case 1 :
					// smaliTreeWalker.g:372:9: ( registers_directive )
					{
					// smaliTreeWalker.g:372:9: ( registers_directive )
					// smaliTreeWalker.g:372:10: registers_directive
					{
					pushFollow(FOLLOW_registers_directive_in_method988);
					registers_directive68=registers_directive();
					state._fsp--;


					          if ((registers_directive68!=null?((smaliTreeWalker.registers_directive_return)registers_directive68).isLocalsDirective:false)) {
					            method_stack.peek().totalMethodRegisters = (registers_directive68!=null?((smaliTreeWalker.registers_directive_return)registers_directive68).registers:0) + method_stack.peek().methodParameterRegisters;
					          } else {
					            method_stack.peek().totalMethodRegisters = (registers_directive68!=null?((smaliTreeWalker.registers_directive_return)registers_directive68).registers:0);
					          }

					          method_stack.peek().methodBuilder = new MethodImplementationBuilder(method_stack.peek().totalMethodRegisters);

					        
					}

					}
					break;
				case 2 :
					// smaliTreeWalker.g:385:9: 
					{

					          method_stack.peek().methodBuilder = new MethodImplementationBuilder(0);
					        
					}
					break;

			}

			pushFollow(FOLLOW_ordered_method_items_in_method1045);
			ordered_method_items();
			state._fsp--;

			pushFollow(FOLLOW_catches_in_method1053);
			catches69=catches();
			state._fsp--;

			pushFollow(FOLLOW_parameters_in_method1061);
			parameters((method_name_and_prototype67!=null?((smaliTreeWalker.method_name_and_prototype_return)method_name_and_prototype67).parameters:null));
			state._fsp--;

			pushFollow(FOLLOW_annotations_in_method1070);
			annotations71=annotations();
			state._fsp--;

			match(input, Token.UP, null); 


			    MethodImplementation methodImplementation = null;
			    List<BuilderTryBlock> tryBlocks = catches69;

			    boolean isAbstract = false;
			    boolean isNative = false;

			    if ((accessFlags & AccessFlags.ABSTRACT.getValue()) != 0) {
			      isAbstract = true;
			    } else if ((accessFlags & AccessFlags.NATIVE.getValue()) != 0) {
			      isNative = true;
			    }

			    methodImplementation = method_stack.peek().methodBuilder.getMethodImplementation();

			    if (Iterables.isEmpty(methodImplementation.getInstructions())) {
			      if (!isAbstract && !isNative) {
			        throw new SemanticException(input, I_METHOD70, "A non-abstract/non-native method must have at least 1 instruction");
			      }

			      String methodType;
			      if (isAbstract) {
			        methodType = "an abstract";
			      } else {
			        methodType = "a native";
			      }

			      if ((registers_directive68!=null?((CommonTree)registers_directive68.start):null) != null) {
			        if ((registers_directive68!=null?((smaliTreeWalker.registers_directive_return)registers_directive68).isLocalsDirective:false)) {
			          throw new SemanticException(input, (registers_directive68!=null?((CommonTree)registers_directive68.start):null), "A .locals directive is not valid in %s method", methodType);
			        } else {
			          throw new SemanticException(input, (registers_directive68!=null?((CommonTree)registers_directive68.start):null), "A .registers directive is not valid in %s method", methodType);
			        }
			      }

			      if (methodImplementation.getTryBlocks().size() > 0) {
			        throw new SemanticException(input, I_METHOD70, "try/catch blocks cannot be present in %s method", methodType);
			      }

			      if (!Iterables.isEmpty(methodImplementation.getDebugItems())) {
			        throw new SemanticException(input, I_METHOD70, "debug directives cannot be present in %s method", methodType);
			      }

			      methodImplementation = null;
			    } else {
			      if (isAbstract) {
			        throw new SemanticException(input, I_METHOD70, "An abstract method cannot have any instructions");
			      }
			      if (isNative) {
			        throw new SemanticException(input, I_METHOD70, "A native method cannot have any instructions");
			      }

			      if ((registers_directive68!=null?((CommonTree)registers_directive68.start):null) == null) {
			        throw new SemanticException(input, I_METHOD70, "A .registers or .locals directive must be present for a non-abstract/non-final method");
			      }

			      if (method_stack.peek().totalMethodRegisters < method_stack.peek().methodParameterRegisters) {
			        throw new SemanticException(input, (registers_directive68!=null?((CommonTree)registers_directive68.start):null), "This method requires at least " +
			                Integer.toString(method_stack.peek().methodParameterRegisters) +
			                " registers, for the method parameters");
			      }
			    }

			    ret = dexBuilder.internMethod(
			            classType,
			            (method_name_and_prototype67!=null?((smaliTreeWalker.method_name_and_prototype_return)method_name_and_prototype67).name:null),
			            (method_name_and_prototype67!=null?((smaliTreeWalker.method_name_and_prototype_return)method_name_and_prototype67).parameters:null),
			            (method_name_and_prototype67!=null?((smaliTreeWalker.method_name_and_prototype_return)method_name_and_prototype67).returnType:null),
			            accessFlags,
			            annotations71,
			            methodImplementation);
			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
			method_stack.pop();
		}
		return ret;
	}
	// $ANTLR end "method"


	public static class method_prototype_return extends TreeRuleReturnScope {
		public List<String> parameters;
		public String returnType;
	};


	// $ANTLR start "method_prototype"
	// smaliTreeWalker.g:467:1: method_prototype returns [List<String> parameters, String returnType] : ^( I_METHOD_PROTOTYPE ^( I_METHOD_RETURN_TYPE type_descriptor ) method_type_list ) ;
	public final smaliTreeWalker.method_prototype_return method_prototype() throws RecognitionException {
		smaliTreeWalker.method_prototype_return retval = new smaliTreeWalker.method_prototype_return();
		retval.start = input.LT(1);

		String type_descriptor72 =null;
		List<String> method_type_list73 =null;

		try {
			// smaliTreeWalker.g:468:3: ( ^( I_METHOD_PROTOTYPE ^( I_METHOD_RETURN_TYPE type_descriptor ) method_type_list ) )
			// smaliTreeWalker.g:468:5: ^( I_METHOD_PROTOTYPE ^( I_METHOD_RETURN_TYPE type_descriptor ) method_type_list )
			{
			match(input,I_METHOD_PROTOTYPE,FOLLOW_I_METHOD_PROTOTYPE_in_method_prototype1094); 
			match(input, Token.DOWN, null); 
			match(input,I_METHOD_RETURN_TYPE,FOLLOW_I_METHOD_RETURN_TYPE_in_method_prototype1097); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_descriptor_in_method_prototype1099);
			type_descriptor72=type_descriptor();
			state._fsp--;

			match(input, Token.UP, null); 

			pushFollow(FOLLOW_method_type_list_in_method_prototype1102);
			method_type_list73=method_type_list();
			state._fsp--;

			match(input, Token.UP, null); 


			    retval.returnType = type_descriptor72;
			    retval.parameters = method_type_list73;
			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "method_prototype"


	public static class method_name_and_prototype_return extends TreeRuleReturnScope {
		public String name;
		public List<SmaliMethodParameter> parameters;
		public String returnType;
	};


	// $ANTLR start "method_name_and_prototype"
	// smaliTreeWalker.g:474:1: method_name_and_prototype returns [String name, List<SmaliMethodParameter> parameters, String returnType] : SIMPLE_NAME method_prototype ;
	public final smaliTreeWalker.method_name_and_prototype_return method_name_and_prototype() throws RecognitionException {
		smaliTreeWalker.method_name_and_prototype_return retval = new smaliTreeWalker.method_name_and_prototype_return();
		retval.start = input.LT(1);

		CommonTree SIMPLE_NAME74=null;
		TreeRuleReturnScope method_prototype75 =null;

		try {
			// smaliTreeWalker.g:475:3: ( SIMPLE_NAME method_prototype )
			// smaliTreeWalker.g:475:5: SIMPLE_NAME method_prototype
			{
			SIMPLE_NAME74=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_method_name_and_prototype1120); 
			pushFollow(FOLLOW_method_prototype_in_method_name_and_prototype1122);
			method_prototype75=method_prototype();
			state._fsp--;


			    retval.name = (SIMPLE_NAME74!=null?SIMPLE_NAME74.getText():null);
			    retval.parameters = Lists.newArrayList();

			    int paramRegister = 0;
			    for (String type: (method_prototype75!=null?((smaliTreeWalker.method_prototype_return)method_prototype75).parameters:null)) {
			        retval.parameters.add(new SmaliMethodParameter(paramRegister++, type));
			        char c = type.charAt(0);
			        if (c == 'D' || c == 'J') {
			            paramRegister++;
			        }
			    }
			    retval.returnType = (method_prototype75!=null?((smaliTreeWalker.method_prototype_return)method_prototype75).returnType:null);
			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "method_name_and_prototype"



	// $ANTLR start "method_type_list"
	// smaliTreeWalker.g:491:1: method_type_list returns [List<String> types] : ( nonvoid_type_descriptor )* ;
	public final List<String> method_type_list() throws RecognitionException {
		List<String> types = null;


		TreeRuleReturnScope nonvoid_type_descriptor76 =null;


		    types = Lists.newArrayList();
		  
		try {
			// smaliTreeWalker.g:496:3: ( ( nonvoid_type_descriptor )* )
			// smaliTreeWalker.g:496:5: ( nonvoid_type_descriptor )*
			{
			// smaliTreeWalker.g:496:5: ( nonvoid_type_descriptor )*
			loop17:
			while (true) {
				int alt17=2;
				int LA17_0 = input.LA(1);
				if ( (LA17_0==ARRAY_TYPE_PREFIX||LA17_0==CLASS_DESCRIPTOR||LA17_0==PRIMITIVE_TYPE) ) {
					alt17=1;
				}

				switch (alt17) {
				case 1 :
					// smaliTreeWalker.g:497:7: nonvoid_type_descriptor
					{
					pushFollow(FOLLOW_nonvoid_type_descriptor_in_method_type_list1156);
					nonvoid_type_descriptor76=nonvoid_type_descriptor();
					state._fsp--;


					        types.add((nonvoid_type_descriptor76!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor76).type:null));
					      
					}
					break;

				default :
					break loop17;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return types;
	}
	// $ANTLR end "method_type_list"



	// $ANTLR start "method_reference"
	// smaliTreeWalker.g:504:1: method_reference returns [ImmutableMethodReference methodReference] : ( reference_type_descriptor )? SIMPLE_NAME method_prototype ;
	public final ImmutableMethodReference method_reference() throws RecognitionException {
		ImmutableMethodReference methodReference = null;


		CommonTree SIMPLE_NAME78=null;
		TreeRuleReturnScope reference_type_descriptor77 =null;
		TreeRuleReturnScope method_prototype79 =null;

		try {
			// smaliTreeWalker.g:505:3: ( ( reference_type_descriptor )? SIMPLE_NAME method_prototype )
			// smaliTreeWalker.g:505:5: ( reference_type_descriptor )? SIMPLE_NAME method_prototype
			{
			// smaliTreeWalker.g:505:5: ( reference_type_descriptor )?
			int alt18=2;
			int LA18_0 = input.LA(1);
			if ( (LA18_0==ARRAY_TYPE_PREFIX||LA18_0==CLASS_DESCRIPTOR) ) {
				alt18=1;
			}
			switch (alt18) {
				case 1 :
					// smaliTreeWalker.g:505:5: reference_type_descriptor
					{
					pushFollow(FOLLOW_reference_type_descriptor_in_method_reference1185);
					reference_type_descriptor77=reference_type_descriptor();
					state._fsp--;

					}
					break;

			}

			SIMPLE_NAME78=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_method_reference1188); 
			pushFollow(FOLLOW_method_prototype_in_method_reference1190);
			method_prototype79=method_prototype();
			state._fsp--;


			    String type;
			    if ((reference_type_descriptor77!=null?((smaliTreeWalker.reference_type_descriptor_return)reference_type_descriptor77).type:null) == null) {
			        type = classType;
			    } else {
			        type = (reference_type_descriptor77!=null?((smaliTreeWalker.reference_type_descriptor_return)reference_type_descriptor77).type:null);
			    }
			    methodReference = new ImmutableMethodReference(type, (SIMPLE_NAME78!=null?SIMPLE_NAME78.getText():null),
			             (method_prototype79!=null?((smaliTreeWalker.method_prototype_return)method_prototype79).parameters:null), (method_prototype79!=null?((smaliTreeWalker.method_prototype_return)method_prototype79).returnType:null));
			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return methodReference;
	}
	// $ANTLR end "method_reference"



	// $ANTLR start "field_reference"
	// smaliTreeWalker.g:517:1: field_reference returns [ImmutableFieldReference fieldReference] : ( reference_type_descriptor )? SIMPLE_NAME nonvoid_type_descriptor ;
	public final ImmutableFieldReference field_reference() throws RecognitionException {
		ImmutableFieldReference fieldReference = null;


		CommonTree SIMPLE_NAME81=null;
		TreeRuleReturnScope reference_type_descriptor80 =null;
		TreeRuleReturnScope nonvoid_type_descriptor82 =null;

		try {
			// smaliTreeWalker.g:518:3: ( ( reference_type_descriptor )? SIMPLE_NAME nonvoid_type_descriptor )
			// smaliTreeWalker.g:518:5: ( reference_type_descriptor )? SIMPLE_NAME nonvoid_type_descriptor
			{
			// smaliTreeWalker.g:518:5: ( reference_type_descriptor )?
			int alt19=2;
			int LA19_0 = input.LA(1);
			if ( (LA19_0==ARRAY_TYPE_PREFIX||LA19_0==CLASS_DESCRIPTOR) ) {
				alt19=1;
			}
			switch (alt19) {
				case 1 :
					// smaliTreeWalker.g:518:5: reference_type_descriptor
					{
					pushFollow(FOLLOW_reference_type_descriptor_in_field_reference1207);
					reference_type_descriptor80=reference_type_descriptor();
					state._fsp--;

					}
					break;

			}

			SIMPLE_NAME81=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_field_reference1210); 
			pushFollow(FOLLOW_nonvoid_type_descriptor_in_field_reference1212);
			nonvoid_type_descriptor82=nonvoid_type_descriptor();
			state._fsp--;


			    String type;
			    if ((reference_type_descriptor80!=null?((smaliTreeWalker.reference_type_descriptor_return)reference_type_descriptor80).type:null) == null) {
			        type = classType;
			    } else {
			        type = (reference_type_descriptor80!=null?((smaliTreeWalker.reference_type_descriptor_return)reference_type_descriptor80).type:null);
			    }
			    fieldReference = new ImmutableFieldReference(type, (SIMPLE_NAME81!=null?SIMPLE_NAME81.getText():null),
			            (nonvoid_type_descriptor82!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor82).type:null));
			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return fieldReference;
	}
	// $ANTLR end "field_reference"


	public static class registers_directive_return extends TreeRuleReturnScope {
		public boolean isLocalsDirective;
		public int registers;
	};


	// $ANTLR start "registers_directive"
	// smaliTreeWalker.g:530:1: registers_directive returns [boolean isLocalsDirective, int registers] : ^( ( I_REGISTERS | I_LOCALS ) short_integral_literal ) ;
	public final smaliTreeWalker.registers_directive_return registers_directive() throws RecognitionException {
		smaliTreeWalker.registers_directive_return retval = new smaliTreeWalker.registers_directive_return();
		retval.start = input.LT(1);

		short short_integral_literal83 =0;

		try {
			// smaliTreeWalker.g:531:3: ( ^( ( I_REGISTERS | I_LOCALS ) short_integral_literal ) )
			// smaliTreeWalker.g:531:5: ^( ( I_REGISTERS | I_LOCALS ) short_integral_literal )
			{
			retval.registers = 0;
			// smaliTreeWalker.g:532:7: ( I_REGISTERS | I_LOCALS )
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==I_REGISTERS) ) {
				alt20=1;
			}
			else if ( (LA20_0==I_LOCALS) ) {
				alt20=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 20, 0, input);
				throw nvae;
			}

			switch (alt20) {
				case 1 :
					// smaliTreeWalker.g:532:9: I_REGISTERS
					{
					match(input,I_REGISTERS,FOLLOW_I_REGISTERS_in_registers_directive1238); 
					retval.isLocalsDirective = false;
					}
					break;
				case 2 :
					// smaliTreeWalker.g:533:9: I_LOCALS
					{
					match(input,I_LOCALS,FOLLOW_I_LOCALS_in_registers_directive1250); 
					retval.isLocalsDirective = true;
					}
					break;

			}

			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_short_integral_literal_in_registers_directive1268);
			short_integral_literal83=short_integral_literal();
			state._fsp--;

			retval.registers = short_integral_literal83 & 0xFFFF;
			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "registers_directive"



	// $ANTLR start "label_def"
	// smaliTreeWalker.g:538:1: label_def : ^( I_LABEL SIMPLE_NAME ) ;
	public final void label_def() throws RecognitionException {
		CommonTree SIMPLE_NAME84=null;

		try {
			// smaliTreeWalker.g:539:3: ( ^( I_LABEL SIMPLE_NAME ) )
			// smaliTreeWalker.g:539:5: ^( I_LABEL SIMPLE_NAME )
			{
			match(input,I_LABEL,FOLLOW_I_LABEL_in_label_def1288); 
			match(input, Token.DOWN, null); 
			SIMPLE_NAME84=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_label_def1290); 
			match(input, Token.UP, null); 


			    method_stack.peek().methodBuilder.addLabel((SIMPLE_NAME84!=null?SIMPLE_NAME84.getText():null));
			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "label_def"



	// $ANTLR start "catches"
	// smaliTreeWalker.g:544:1: catches returns [List<BuilderTryBlock> tryBlocks] : ^( I_CATCHES ( catch_directive )* ( catchall_directive )* ) ;
	public final List<BuilderTryBlock> catches() throws RecognitionException {
		List<BuilderTryBlock> tryBlocks = null;


		tryBlocks = Lists.newArrayList();
		try {
			// smaliTreeWalker.g:546:3: ( ^( I_CATCHES ( catch_directive )* ( catchall_directive )* ) )
			// smaliTreeWalker.g:546:5: ^( I_CATCHES ( catch_directive )* ( catchall_directive )* )
			{
			match(input,I_CATCHES,FOLLOW_I_CATCHES_in_catches1316); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// smaliTreeWalker.g:546:17: ( catch_directive )*
				loop21:
				while (true) {
					int alt21=2;
					int LA21_0 = input.LA(1);
					if ( (LA21_0==I_CATCH) ) {
						alt21=1;
					}

					switch (alt21) {
					case 1 :
						// smaliTreeWalker.g:546:17: catch_directive
						{
						pushFollow(FOLLOW_catch_directive_in_catches1318);
						catch_directive();
						state._fsp--;

						}
						break;

					default :
						break loop21;
					}
				}

				// smaliTreeWalker.g:546:34: ( catchall_directive )*
				loop22:
				while (true) {
					int alt22=2;
					int LA22_0 = input.LA(1);
					if ( (LA22_0==I_CATCHALL) ) {
						alt22=1;
					}

					switch (alt22) {
					case 1 :
						// smaliTreeWalker.g:546:34: catchall_directive
						{
						pushFollow(FOLLOW_catchall_directive_in_catches1321);
						catchall_directive();
						state._fsp--;

						}
						break;

					default :
						break loop22;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return tryBlocks;
	}
	// $ANTLR end "catches"



	// $ANTLR start "catch_directive"
	// smaliTreeWalker.g:548:1: catch_directive : ^( I_CATCH nonvoid_type_descriptor from= label_ref to= label_ref using= label_ref ) ;
	public final void catch_directive() throws RecognitionException {
		Label from =null;
		Label to =null;
		Label using =null;
		TreeRuleReturnScope nonvoid_type_descriptor85 =null;

		try {
			// smaliTreeWalker.g:549:3: ( ^( I_CATCH nonvoid_type_descriptor from= label_ref to= label_ref using= label_ref ) )
			// smaliTreeWalker.g:549:5: ^( I_CATCH nonvoid_type_descriptor from= label_ref to= label_ref using= label_ref )
			{
			match(input,I_CATCH,FOLLOW_I_CATCH_in_catch_directive1334); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_nonvoid_type_descriptor_in_catch_directive1336);
			nonvoid_type_descriptor85=nonvoid_type_descriptor();
			state._fsp--;

			pushFollow(FOLLOW_label_ref_in_catch_directive1340);
			from=label_ref();
			state._fsp--;

			pushFollow(FOLLOW_label_ref_in_catch_directive1344);
			to=label_ref();
			state._fsp--;

			pushFollow(FOLLOW_label_ref_in_catch_directive1348);
			using=label_ref();
			state._fsp--;

			match(input, Token.UP, null); 


			    method_stack.peek().methodBuilder.addCatch(dexBuilder.internTypeReference((nonvoid_type_descriptor85!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor85).type:null)),
			        from, to, using);
			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "catch_directive"



	// $ANTLR start "catchall_directive"
	// smaliTreeWalker.g:555:1: catchall_directive : ^( I_CATCHALL from= label_ref to= label_ref using= label_ref ) ;
	public final void catchall_directive() throws RecognitionException {
		Label from =null;
		Label to =null;
		Label using =null;

		try {
			// smaliTreeWalker.g:556:3: ( ^( I_CATCHALL from= label_ref to= label_ref using= label_ref ) )
			// smaliTreeWalker.g:556:5: ^( I_CATCHALL from= label_ref to= label_ref using= label_ref )
			{
			match(input,I_CATCHALL,FOLLOW_I_CATCHALL_in_catchall_directive1364); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_label_ref_in_catchall_directive1368);
			from=label_ref();
			state._fsp--;

			pushFollow(FOLLOW_label_ref_in_catchall_directive1372);
			to=label_ref();
			state._fsp--;

			pushFollow(FOLLOW_label_ref_in_catchall_directive1376);
			using=label_ref();
			state._fsp--;

			match(input, Token.UP, null); 


			    method_stack.peek().methodBuilder.addCatch(from, to, using);
			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "catchall_directive"



	// $ANTLR start "parameters"
	// smaliTreeWalker.g:561:1: parameters[List<SmaliMethodParameter> parameters] : ^( I_PARAMETERS ( parameter[parameters] )* ) ;
	public final void parameters(List<SmaliMethodParameter> parameters) throws RecognitionException {
		try {
			// smaliTreeWalker.g:562:3: ( ^( I_PARAMETERS ( parameter[parameters] )* ) )
			// smaliTreeWalker.g:562:5: ^( I_PARAMETERS ( parameter[parameters] )* )
			{
			match(input,I_PARAMETERS,FOLLOW_I_PARAMETERS_in_parameters1393); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// smaliTreeWalker.g:562:20: ( parameter[parameters] )*
				loop23:
				while (true) {
					int alt23=2;
					int LA23_0 = input.LA(1);
					if ( (LA23_0==I_PARAMETER) ) {
						alt23=1;
					}

					switch (alt23) {
					case 1 :
						// smaliTreeWalker.g:562:21: parameter[parameters]
						{
						pushFollow(FOLLOW_parameter_in_parameters1396);
						parameter(parameters);
						state._fsp--;

						}
						break;

					default :
						break loop23;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "parameters"



	// $ANTLR start "parameter"
	// smaliTreeWalker.g:564:1: parameter[List<SmaliMethodParameter> parameters] : ^( I_PARAMETER REGISTER ( string_literal )? annotations ) ;
	public final void parameter(List<SmaliMethodParameter> parameters) throws RecognitionException {
		CommonTree REGISTER86=null;
		CommonTree I_PARAMETER87=null;
		String string_literal88 =null;
		Set<Annotation> annotations89 =null;

		try {
			// smaliTreeWalker.g:565:3: ( ^( I_PARAMETER REGISTER ( string_literal )? annotations ) )
			// smaliTreeWalker.g:565:5: ^( I_PARAMETER REGISTER ( string_literal )? annotations )
			{
			I_PARAMETER87=(CommonTree)match(input,I_PARAMETER,FOLLOW_I_PARAMETER_in_parameter1412); 
			match(input, Token.DOWN, null); 
			REGISTER86=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_parameter1414); 
			// smaliTreeWalker.g:565:28: ( string_literal )?
			int alt24=2;
			int LA24_0 = input.LA(1);
			if ( (LA24_0==STRING_LITERAL) ) {
				alt24=1;
			}
			switch (alt24) {
				case 1 :
					// smaliTreeWalker.g:565:28: string_literal
					{
					pushFollow(FOLLOW_string_literal_in_parameter1416);
					string_literal88=string_literal();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_annotations_in_parameter1419);
			annotations89=annotations();
			state._fsp--;

			match(input, Token.UP, null); 


			        final int registerNumber = parseRegister_short((REGISTER86!=null?REGISTER86.getText():null));
			        int totalMethodRegisters = method_stack.peek().totalMethodRegisters;
			        int methodParameterRegisters = method_stack.peek().methodParameterRegisters;

			        if (registerNumber >= totalMethodRegisters) {
			            throw new SemanticException(input, I_PARAMETER87, "Register %s is larger than the maximum register v%d " +
			                    "for this method", (REGISTER86!=null?REGISTER86.getText():null), totalMethodRegisters-1);
			        }
			        final int indexGuess = registerNumber - (totalMethodRegisters - methodParameterRegisters) - (method_stack.peek().isStatic?0:1);

			        if (indexGuess < 0) {
			            throw new SemanticException(input, I_PARAMETER87, "Register %s is not a parameter register.",
			                    (REGISTER86!=null?REGISTER86.getText():null));
			        }

			        int parameterIndex = LinearSearch.linearSearch(parameters, SmaliMethodParameter.COMPARATOR,
			            new WithRegister() { public int getRegister() { return indexGuess; } },
			                indexGuess);

			        if (parameterIndex < 0) {
			            throw new SemanticException(input, I_PARAMETER87, "Register %s is the second half of a wide parameter.",
			                                (REGISTER86!=null?REGISTER86.getText():null));
			        }

			        SmaliMethodParameter methodParameter = parameters.get(parameterIndex);
			        methodParameter.name = string_literal88;
			        if (annotations89 != null && annotations89.size() > 0) {
			            methodParameter.annotations = annotations89;
			        }
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "parameter"



	// $ANTLR start "debug_directive"
	// smaliTreeWalker.g:598:1: debug_directive : ( line | local | end_local | restart_local | prologue | epilogue | source );
	public final void debug_directive() throws RecognitionException {
		try {
			// smaliTreeWalker.g:599:3: ( line | local | end_local | restart_local | prologue | epilogue | source )
			int alt25=7;
			switch ( input.LA(1) ) {
			case I_LINE:
				{
				alt25=1;
				}
				break;
			case I_LOCAL:
				{
				alt25=2;
				}
				break;
			case I_END_LOCAL:
				{
				alt25=3;
				}
				break;
			case I_RESTART_LOCAL:
				{
				alt25=4;
				}
				break;
			case I_PROLOGUE:
				{
				alt25=5;
				}
				break;
			case I_EPILOGUE:
				{
				alt25=6;
				}
				break;
			case I_SOURCE:
				{
				alt25=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 25, 0, input);
				throw nvae;
			}
			switch (alt25) {
				case 1 :
					// smaliTreeWalker.g:599:5: line
					{
					pushFollow(FOLLOW_line_in_debug_directive1436);
					line();
					state._fsp--;

					}
					break;
				case 2 :
					// smaliTreeWalker.g:600:5: local
					{
					pushFollow(FOLLOW_local_in_debug_directive1442);
					local();
					state._fsp--;

					}
					break;
				case 3 :
					// smaliTreeWalker.g:601:5: end_local
					{
					pushFollow(FOLLOW_end_local_in_debug_directive1448);
					end_local();
					state._fsp--;

					}
					break;
				case 4 :
					// smaliTreeWalker.g:602:5: restart_local
					{
					pushFollow(FOLLOW_restart_local_in_debug_directive1454);
					restart_local();
					state._fsp--;

					}
					break;
				case 5 :
					// smaliTreeWalker.g:603:5: prologue
					{
					pushFollow(FOLLOW_prologue_in_debug_directive1460);
					prologue();
					state._fsp--;

					}
					break;
				case 6 :
					// smaliTreeWalker.g:604:5: epilogue
					{
					pushFollow(FOLLOW_epilogue_in_debug_directive1466);
					epilogue();
					state._fsp--;

					}
					break;
				case 7 :
					// smaliTreeWalker.g:605:5: source
					{
					pushFollow(FOLLOW_source_in_debug_directive1472);
					source();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "debug_directive"



	// $ANTLR start "line"
	// smaliTreeWalker.g:607:1: line : ^( I_LINE integral_literal ) ;
	public final void line() throws RecognitionException {
		int integral_literal90 =0;

		try {
			// smaliTreeWalker.g:608:3: ( ^( I_LINE integral_literal ) )
			// smaliTreeWalker.g:608:5: ^( I_LINE integral_literal )
			{
			match(input,I_LINE,FOLLOW_I_LINE_in_line1483); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_integral_literal_in_line1485);
			integral_literal90=integral_literal();
			state._fsp--;

			match(input, Token.UP, null); 


			        method_stack.peek().methodBuilder.addLineNumber(integral_literal90);
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "line"



	// $ANTLR start "local"
	// smaliTreeWalker.g:613:1: local : ^( I_LOCAL REGISTER ( ( NULL_LITERAL |name= string_literal ) ( nonvoid_type_descriptor )? (signature= string_literal )? )? ) ;
	public final void local() throws RecognitionException {
		CommonTree REGISTER91=null;
		String name =null;
		String signature =null;
		TreeRuleReturnScope nonvoid_type_descriptor92 =null;

		try {
			// smaliTreeWalker.g:614:3: ( ^( I_LOCAL REGISTER ( ( NULL_LITERAL |name= string_literal ) ( nonvoid_type_descriptor )? (signature= string_literal )? )? ) )
			// smaliTreeWalker.g:614:5: ^( I_LOCAL REGISTER ( ( NULL_LITERAL |name= string_literal ) ( nonvoid_type_descriptor )? (signature= string_literal )? )? )
			{
			match(input,I_LOCAL,FOLLOW_I_LOCAL_in_local1503); 
			match(input, Token.DOWN, null); 
			REGISTER91=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_local1505); 
			// smaliTreeWalker.g:614:24: ( ( NULL_LITERAL |name= string_literal ) ( nonvoid_type_descriptor )? (signature= string_literal )? )?
			int alt29=2;
			int LA29_0 = input.LA(1);
			if ( (LA29_0==NULL_LITERAL||LA29_0==STRING_LITERAL) ) {
				alt29=1;
			}
			switch (alt29) {
				case 1 :
					// smaliTreeWalker.g:614:25: ( NULL_LITERAL |name= string_literal ) ( nonvoid_type_descriptor )? (signature= string_literal )?
					{
					// smaliTreeWalker.g:614:25: ( NULL_LITERAL |name= string_literal )
					int alt26=2;
					int LA26_0 = input.LA(1);
					if ( (LA26_0==NULL_LITERAL) ) {
						alt26=1;
					}
					else if ( (LA26_0==STRING_LITERAL) ) {
						alt26=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 26, 0, input);
						throw nvae;
					}

					switch (alt26) {
						case 1 :
							// smaliTreeWalker.g:614:26: NULL_LITERAL
							{
							match(input,NULL_LITERAL,FOLLOW_NULL_LITERAL_in_local1509); 
							}
							break;
						case 2 :
							// smaliTreeWalker.g:614:41: name= string_literal
							{
							pushFollow(FOLLOW_string_literal_in_local1515);
							name=string_literal();
							state._fsp--;

							}
							break;

					}

					// smaliTreeWalker.g:614:62: ( nonvoid_type_descriptor )?
					int alt27=2;
					int LA27_0 = input.LA(1);
					if ( (LA27_0==ARRAY_TYPE_PREFIX||LA27_0==CLASS_DESCRIPTOR||LA27_0==PRIMITIVE_TYPE) ) {
						alt27=1;
					}
					switch (alt27) {
						case 1 :
							// smaliTreeWalker.g:614:62: nonvoid_type_descriptor
							{
							pushFollow(FOLLOW_nonvoid_type_descriptor_in_local1518);
							nonvoid_type_descriptor92=nonvoid_type_descriptor();
							state._fsp--;

							}
							break;

					}

					// smaliTreeWalker.g:614:96: (signature= string_literal )?
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0==STRING_LITERAL) ) {
						alt28=1;
					}
					switch (alt28) {
						case 1 :
							// smaliTreeWalker.g:614:96: signature= string_literal
							{
							pushFollow(FOLLOW_string_literal_in_local1523);
							signature=string_literal();
							state._fsp--;

							}
							break;

					}

					}
					break;

			}

			match(input, Token.UP, null); 


			      int registerNumber = parseRegister_short((REGISTER91!=null?REGISTER91.getText():null));
			      method_stack.peek().methodBuilder.addStartLocal(registerNumber,
			              dexBuilder.internNullableStringReference(name),
			              dexBuilder.internNullableTypeReference((nonvoid_type_descriptor92!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor92).type:null)),
			              dexBuilder.internNullableStringReference(signature));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "local"



	// $ANTLR start "end_local"
	// smaliTreeWalker.g:623:1: end_local : ^( I_END_LOCAL REGISTER ) ;
	public final void end_local() throws RecognitionException {
		CommonTree REGISTER93=null;

		try {
			// smaliTreeWalker.g:624:3: ( ^( I_END_LOCAL REGISTER ) )
			// smaliTreeWalker.g:624:5: ^( I_END_LOCAL REGISTER )
			{
			match(input,I_END_LOCAL,FOLLOW_I_END_LOCAL_in_end_local1544); 
			match(input, Token.DOWN, null); 
			REGISTER93=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_end_local1546); 
			match(input, Token.UP, null); 


			      int registerNumber = parseRegister_short((REGISTER93!=null?REGISTER93.getText():null));
			      method_stack.peek().methodBuilder.addEndLocal(registerNumber);
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "end_local"



	// $ANTLR start "restart_local"
	// smaliTreeWalker.g:630:1: restart_local : ^( I_RESTART_LOCAL REGISTER ) ;
	public final void restart_local() throws RecognitionException {
		CommonTree REGISTER94=null;

		try {
			// smaliTreeWalker.g:631:3: ( ^( I_RESTART_LOCAL REGISTER ) )
			// smaliTreeWalker.g:631:5: ^( I_RESTART_LOCAL REGISTER )
			{
			match(input,I_RESTART_LOCAL,FOLLOW_I_RESTART_LOCAL_in_restart_local1564); 
			match(input, Token.DOWN, null); 
			REGISTER94=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_restart_local1566); 
			match(input, Token.UP, null); 


			      int registerNumber = parseRegister_short((REGISTER94!=null?REGISTER94.getText():null));
			      method_stack.peek().methodBuilder.addRestartLocal(registerNumber);
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "restart_local"



	// $ANTLR start "prologue"
	// smaliTreeWalker.g:637:1: prologue : I_PROLOGUE ;
	public final void prologue() throws RecognitionException {
		try {
			// smaliTreeWalker.g:638:3: ( I_PROLOGUE )
			// smaliTreeWalker.g:638:5: I_PROLOGUE
			{
			match(input,I_PROLOGUE,FOLLOW_I_PROLOGUE_in_prologue1583); 

			      method_stack.peek().methodBuilder.addPrologue();
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "prologue"



	// $ANTLR start "epilogue"
	// smaliTreeWalker.g:643:1: epilogue : I_EPILOGUE ;
	public final void epilogue() throws RecognitionException {
		try {
			// smaliTreeWalker.g:644:3: ( I_EPILOGUE )
			// smaliTreeWalker.g:644:5: I_EPILOGUE
			{
			match(input,I_EPILOGUE,FOLLOW_I_EPILOGUE_in_epilogue1599); 

			      method_stack.peek().methodBuilder.addEpilogue();
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "epilogue"



	// $ANTLR start "source"
	// smaliTreeWalker.g:649:1: source : ^( I_SOURCE ( string_literal )? ) ;
	public final void source() throws RecognitionException {
		String string_literal95 =null;

		try {
			// smaliTreeWalker.g:650:3: ( ^( I_SOURCE ( string_literal )? ) )
			// smaliTreeWalker.g:650:5: ^( I_SOURCE ( string_literal )? )
			{
			match(input,I_SOURCE,FOLLOW_I_SOURCE_in_source1616); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// smaliTreeWalker.g:650:16: ( string_literal )?
				int alt30=2;
				int LA30_0 = input.LA(1);
				if ( (LA30_0==STRING_LITERAL) ) {
					alt30=1;
				}
				switch (alt30) {
					case 1 :
						// smaliTreeWalker.g:650:16: string_literal
						{
						pushFollow(FOLLOW_string_literal_in_source1618);
						string_literal95=string_literal();
						state._fsp--;

						}
						break;

				}

				match(input, Token.UP, null); 
			}


			      method_stack.peek().methodBuilder.addSetSourceFile(dexBuilder.internNullableStringReference(string_literal95));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "source"



	// $ANTLR start "ordered_method_items"
	// smaliTreeWalker.g:655:1: ordered_method_items : ^( I_ORDERED_METHOD_ITEMS ( label_def | instruction | debug_directive )* ) ;
	public final void ordered_method_items() throws RecognitionException {
		try {
			// smaliTreeWalker.g:656:3: ( ^( I_ORDERED_METHOD_ITEMS ( label_def | instruction | debug_directive )* ) )
			// smaliTreeWalker.g:656:5: ^( I_ORDERED_METHOD_ITEMS ( label_def | instruction | debug_directive )* )
			{
			match(input,I_ORDERED_METHOD_ITEMS,FOLLOW_I_ORDERED_METHOD_ITEMS_in_ordered_method_items1637); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// smaliTreeWalker.g:656:30: ( label_def | instruction | debug_directive )*
				loop31:
				while (true) {
					int alt31=4;
					switch ( input.LA(1) ) {
					case I_LABEL:
						{
						alt31=1;
						}
						break;
					case I_STATEMENT_ARRAY_DATA:
					case I_STATEMENT_FORMAT10t:
					case I_STATEMENT_FORMAT10x:
					case I_STATEMENT_FORMAT11n:
					case I_STATEMENT_FORMAT11x:
					case I_STATEMENT_FORMAT12x:
					case I_STATEMENT_FORMAT20bc:
					case I_STATEMENT_FORMAT20t:
					case I_STATEMENT_FORMAT21c_FIELD:
					case I_STATEMENT_FORMAT21c_LAMBDA:
					case I_STATEMENT_FORMAT21c_METHOD:
					case I_STATEMENT_FORMAT21c_STRING:
					case I_STATEMENT_FORMAT21c_TYPE:
					case I_STATEMENT_FORMAT21ih:
					case I_STATEMENT_FORMAT21lh:
					case I_STATEMENT_FORMAT21s:
					case I_STATEMENT_FORMAT21t:
					case I_STATEMENT_FORMAT22b:
					case I_STATEMENT_FORMAT22c_FIELD:
					case I_STATEMENT_FORMAT22c_STRING:
					case I_STATEMENT_FORMAT22c_TYPE:
					case I_STATEMENT_FORMAT22s:
					case I_STATEMENT_FORMAT22t:
					case I_STATEMENT_FORMAT22x:
					case I_STATEMENT_FORMAT23x:
					case I_STATEMENT_FORMAT25x:
					case I_STATEMENT_FORMAT30t:
					case I_STATEMENT_FORMAT31c:
					case I_STATEMENT_FORMAT31i:
					case I_STATEMENT_FORMAT31t:
					case I_STATEMENT_FORMAT32x:
					case I_STATEMENT_FORMAT35c_METHOD:
					case I_STATEMENT_FORMAT35c_TYPE:
					case I_STATEMENT_FORMAT3rc_METHOD:
					case I_STATEMENT_FORMAT3rc_TYPE:
					case I_STATEMENT_FORMAT51l:
					case I_STATEMENT_PACKED_SWITCH:
					case I_STATEMENT_SPARSE_SWITCH:
						{
						alt31=2;
						}
						break;
					case I_END_LOCAL:
					case I_EPILOGUE:
					case I_LINE:
					case I_LOCAL:
					case I_PROLOGUE:
					case I_RESTART_LOCAL:
					case I_SOURCE:
						{
						alt31=3;
						}
						break;
					}
					switch (alt31) {
					case 1 :
						// smaliTreeWalker.g:656:31: label_def
						{
						pushFollow(FOLLOW_label_def_in_ordered_method_items1640);
						label_def();
						state._fsp--;

						}
						break;
					case 2 :
						// smaliTreeWalker.g:656:43: instruction
						{
						pushFollow(FOLLOW_instruction_in_ordered_method_items1644);
						instruction();
						state._fsp--;

						}
						break;
					case 3 :
						// smaliTreeWalker.g:656:57: debug_directive
						{
						pushFollow(FOLLOW_debug_directive_in_ordered_method_items1648);
						debug_directive();
						state._fsp--;

						}
						break;

					default :
						break loop31;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ordered_method_items"



	// $ANTLR start "label_ref"
	// smaliTreeWalker.g:658:1: label_ref returns [Label label] : SIMPLE_NAME ;
	public final Label label_ref() throws RecognitionException {
		Label label = null;


		CommonTree SIMPLE_NAME96=null;

		try {
			// smaliTreeWalker.g:659:3: ( SIMPLE_NAME )
			// smaliTreeWalker.g:659:5: SIMPLE_NAME
			{
			SIMPLE_NAME96=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_label_ref1664); 
			 label = method_stack.peek().methodBuilder.getLabel((SIMPLE_NAME96!=null?SIMPLE_NAME96.getText():null)); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return label;
	}
	// $ANTLR end "label_ref"


	public static class register_list_return extends TreeRuleReturnScope {
		public byte[] registers;
		public byte registerCount;
	};


	// $ANTLR start "register_list"
	// smaliTreeWalker.g:661:1: register_list returns [byte[] registers, byte registerCount] : ^( I_REGISTER_LIST ( REGISTER )* ) ;
	public final smaliTreeWalker.register_list_return register_list() throws RecognitionException {
		smaliTreeWalker.register_list_return retval = new smaliTreeWalker.register_list_return();
		retval.start = input.LT(1);

		CommonTree I_REGISTER_LIST97=null;
		CommonTree REGISTER98=null;


		    retval.registers = new byte[5];
		    retval.registerCount = 0;
		  
		try {
			// smaliTreeWalker.g:667:3: ( ^( I_REGISTER_LIST ( REGISTER )* ) )
			// smaliTreeWalker.g:667:5: ^( I_REGISTER_LIST ( REGISTER )* )
			{
			I_REGISTER_LIST97=(CommonTree)match(input,I_REGISTER_LIST,FOLLOW_I_REGISTER_LIST_in_register_list1689); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// smaliTreeWalker.g:668:7: ( REGISTER )*
				loop32:
				while (true) {
					int alt32=2;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==REGISTER) ) {
						alt32=1;
					}

					switch (alt32) {
					case 1 :
						// smaliTreeWalker.g:668:8: REGISTER
						{
						REGISTER98=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_register_list1698); 

						        if (retval.registerCount == 5) {
						          throw new SemanticException(input, I_REGISTER_LIST97, "A list of registers can only have a maximum of 5 " +
						                  "registers. Use the <op>/range alternate opcode instead.");
						        }
						        retval.registers[retval.registerCount++] = parseRegister_nibble((REGISTER98!=null?REGISTER98.getText():null));
						      
						}
						break;

					default :
						break loop32;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "register_list"


	public static class register_list4_return extends TreeRuleReturnScope {
		public byte[] registers;
		public byte registerCount;
	};


	// $ANTLR start "register_list4"
	// smaliTreeWalker.g:677:1: register_list4 returns [byte[] registers, byte registerCount] : ^( I_REGISTER_LIST ( REGISTER )* ) ;
	public final smaliTreeWalker.register_list4_return register_list4() throws RecognitionException {
		smaliTreeWalker.register_list4_return retval = new smaliTreeWalker.register_list4_return();
		retval.start = input.LT(1);

		CommonTree I_REGISTER_LIST99=null;
		CommonTree REGISTER100=null;


		    retval.registers = new byte[4];
		    retval.registerCount = 0;
		  
		try {
			// smaliTreeWalker.g:683:3: ( ^( I_REGISTER_LIST ( REGISTER )* ) )
			// smaliTreeWalker.g:683:5: ^( I_REGISTER_LIST ( REGISTER )* )
			{
			I_REGISTER_LIST99=(CommonTree)match(input,I_REGISTER_LIST,FOLLOW_I_REGISTER_LIST_in_register_list41732); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// smaliTreeWalker.g:684:7: ( REGISTER )*
				loop33:
				while (true) {
					int alt33=2;
					int LA33_0 = input.LA(1);
					if ( (LA33_0==REGISTER) ) {
						alt33=1;
					}

					switch (alt33) {
					case 1 :
						// smaliTreeWalker.g:684:8: REGISTER
						{
						REGISTER100=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_register_list41741); 

						        if (retval.registerCount == 4) {
						          throw new SemanticException(input, I_REGISTER_LIST99, "A list4 of registers can only have a maximum of 4 " +
						                  "registers. Use the <op>/range alternate opcode instead.");
						        }
						        retval.registers[retval.registerCount++] = parseRegister_nibble((REGISTER100!=null?REGISTER100.getText():null));
						      
						}
						break;

					default :
						break loop33;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "register_list4"


	public static class register_range_return extends TreeRuleReturnScope {
		public int startRegister;
		public int endRegister;
	};


	// $ANTLR start "register_range"
	// smaliTreeWalker.g:693:1: register_range returns [int startRegister, int endRegister] : ^( I_REGISTER_RANGE (startReg= REGISTER (endReg= REGISTER )? )? ) ;
	public final smaliTreeWalker.register_range_return register_range() throws RecognitionException {
		smaliTreeWalker.register_range_return retval = new smaliTreeWalker.register_range_return();
		retval.start = input.LT(1);

		CommonTree startReg=null;
		CommonTree endReg=null;
		CommonTree I_REGISTER_RANGE101=null;

		try {
			// smaliTreeWalker.g:694:3: ( ^( I_REGISTER_RANGE (startReg= REGISTER (endReg= REGISTER )? )? ) )
			// smaliTreeWalker.g:694:5: ^( I_REGISTER_RANGE (startReg= REGISTER (endReg= REGISTER )? )? )
			{
			I_REGISTER_RANGE101=(CommonTree)match(input,I_REGISTER_RANGE,FOLLOW_I_REGISTER_RANGE_in_register_range1766); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// smaliTreeWalker.g:694:24: (startReg= REGISTER (endReg= REGISTER )? )?
				int alt35=2;
				int LA35_0 = input.LA(1);
				if ( (LA35_0==REGISTER) ) {
					alt35=1;
				}
				switch (alt35) {
					case 1 :
						// smaliTreeWalker.g:694:25: startReg= REGISTER (endReg= REGISTER )?
						{
						startReg=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_register_range1771); 
						// smaliTreeWalker.g:694:49: (endReg= REGISTER )?
						int alt34=2;
						int LA34_0 = input.LA(1);
						if ( (LA34_0==REGISTER) ) {
							alt34=1;
						}
						switch (alt34) {
							case 1 :
								// smaliTreeWalker.g:694:49: endReg= REGISTER
								{
								endReg=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_register_range1775); 
								}
								break;

						}

						}
						break;

				}

				match(input, Token.UP, null); 
			}


			        if (startReg == null) {
			            retval.startRegister = 0;
			            retval.endRegister = -1;
			        } else {
			                retval.startRegister = parseRegister_short((startReg!=null?startReg.getText():null));
			                if (endReg == null) {
			                    retval.endRegister = retval.startRegister;
			                } else {
			                    retval.endRegister = parseRegister_short((endReg!=null?endReg.getText():null));
			                }

			                int registerCount = retval.endRegister-retval.startRegister+1;
			                if (registerCount < 1) {
			                    throw new SemanticException(input, I_REGISTER_RANGE101, "A register range must have the lower register listed first");
			                }
			            }
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "register_range"



	// $ANTLR start "verification_error_reference"
	// smaliTreeWalker.g:714:1: verification_error_reference returns [ImmutableReference reference] : ( CLASS_DESCRIPTOR | field_reference | method_reference );
	public final ImmutableReference verification_error_reference() throws RecognitionException {
		ImmutableReference reference = null;


		CommonTree CLASS_DESCRIPTOR102=null;
		ImmutableFieldReference field_reference103 =null;
		ImmutableMethodReference method_reference104 =null;

		try {
			// smaliTreeWalker.g:715:3: ( CLASS_DESCRIPTOR | field_reference | method_reference )
			int alt36=3;
			switch ( input.LA(1) ) {
			case CLASS_DESCRIPTOR:
				{
				int LA36_1 = input.LA(2);
				if ( (LA36_1==UP) ) {
					alt36=1;
				}
				else if ( (LA36_1==SIMPLE_NAME) ) {
					int LA36_3 = input.LA(3);
					if ( (LA36_3==ARRAY_TYPE_PREFIX||LA36_3==CLASS_DESCRIPTOR||LA36_3==PRIMITIVE_TYPE) ) {
						alt36=2;
					}
					else if ( (LA36_3==I_METHOD_PROTOTYPE) ) {
						alt36=3;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 36, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 36, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case ARRAY_TYPE_PREFIX:
				{
				int LA36_2 = input.LA(2);
				if ( (LA36_2==PRIMITIVE_TYPE) ) {
					int LA36_5 = input.LA(3);
					if ( (LA36_5==SIMPLE_NAME) ) {
						int LA36_3 = input.LA(4);
						if ( (LA36_3==ARRAY_TYPE_PREFIX||LA36_3==CLASS_DESCRIPTOR||LA36_3==PRIMITIVE_TYPE) ) {
							alt36=2;
						}
						else if ( (LA36_3==I_METHOD_PROTOTYPE) ) {
							alt36=3;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 36, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 36, 5, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				else if ( (LA36_2==CLASS_DESCRIPTOR) ) {
					int LA36_6 = input.LA(3);
					if ( (LA36_6==SIMPLE_NAME) ) {
						int LA36_3 = input.LA(4);
						if ( (LA36_3==ARRAY_TYPE_PREFIX||LA36_3==CLASS_DESCRIPTOR||LA36_3==PRIMITIVE_TYPE) ) {
							alt36=2;
						}
						else if ( (LA36_3==I_METHOD_PROTOTYPE) ) {
							alt36=3;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 36, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 36, 6, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 36, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case SIMPLE_NAME:
				{
				int LA36_3 = input.LA(2);
				if ( (LA36_3==ARRAY_TYPE_PREFIX||LA36_3==CLASS_DESCRIPTOR||LA36_3==PRIMITIVE_TYPE) ) {
					alt36=2;
				}
				else if ( (LA36_3==I_METHOD_PROTOTYPE) ) {
					alt36=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 36, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 36, 0, input);
				throw nvae;
			}
			switch (alt36) {
				case 1 :
					// smaliTreeWalker.g:715:5: CLASS_DESCRIPTOR
					{
					CLASS_DESCRIPTOR102=(CommonTree)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_verification_error_reference1798); 

					    reference = new ImmutableTypeReference((CLASS_DESCRIPTOR102!=null?CLASS_DESCRIPTOR102.getText():null));
					  
					}
					break;
				case 2 :
					// smaliTreeWalker.g:719:5: field_reference
					{
					pushFollow(FOLLOW_field_reference_in_verification_error_reference1808);
					field_reference103=field_reference();
					state._fsp--;


					    reference = field_reference103;
					  
					}
					break;
				case 3 :
					// smaliTreeWalker.g:723:5: method_reference
					{
					pushFollow(FOLLOW_method_reference_in_verification_error_reference1818);
					method_reference104=method_reference();
					state._fsp--;


					    reference = method_reference104;
					  
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return reference;
	}
	// $ANTLR end "verification_error_reference"



	// $ANTLR start "verification_error_type"
	// smaliTreeWalker.g:728:1: verification_error_type returns [int verificationError] : VERIFICATION_ERROR_TYPE ;
	public final int verification_error_type() throws RecognitionException {
		int verificationError = 0;


		CommonTree VERIFICATION_ERROR_TYPE105=null;

		try {
			// smaliTreeWalker.g:729:3: ( VERIFICATION_ERROR_TYPE )
			// smaliTreeWalker.g:729:5: VERIFICATION_ERROR_TYPE
			{
			VERIFICATION_ERROR_TYPE105=(CommonTree)match(input,VERIFICATION_ERROR_TYPE,FOLLOW_VERIFICATION_ERROR_TYPE_in_verification_error_type1835); 

			    verificationError = VerificationError.getVerificationError((VERIFICATION_ERROR_TYPE105!=null?VERIFICATION_ERROR_TYPE105.getText():null));
			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return verificationError;
	}
	// $ANTLR end "verification_error_type"


	public static class instruction_return extends TreeRuleReturnScope {
	};


	// $ANTLR start "instruction"
	// smaliTreeWalker.g:734:1: instruction : ( insn_format10t | insn_format10x | insn_format11n | insn_format11x | insn_format12x | insn_format20bc | insn_format20t | insn_format21c_field | insn_format21c_string | insn_format21c_type | insn_format21c_lambda | insn_format21c_method | insn_format21ih | insn_format21lh | insn_format21s | insn_format21t | insn_format22b | insn_format22c_field | insn_format22c_type | insn_format22c_string | insn_format22s | insn_format22t | insn_format22x | insn_format23x | insn_format25x | insn_format30t | insn_format31c | insn_format31i | insn_format31t | insn_format32x | insn_format35c_method | insn_format35c_type | insn_format3rc_method | insn_format3rc_type | insn_format51l_type | insn_array_data_directive | insn_packed_switch_directive | insn_sparse_switch_directive );
	public final smaliTreeWalker.instruction_return instruction() throws RecognitionException {
		smaliTreeWalker.instruction_return retval = new smaliTreeWalker.instruction_return();
		retval.start = input.LT(1);

		try {
			// smaliTreeWalker.g:735:3: ( insn_format10t | insn_format10x | insn_format11n | insn_format11x | insn_format12x | insn_format20bc | insn_format20t | insn_format21c_field | insn_format21c_string | insn_format21c_type | insn_format21c_lambda | insn_format21c_method | insn_format21ih | insn_format21lh | insn_format21s | insn_format21t | insn_format22b | insn_format22c_field | insn_format22c_type | insn_format22c_string | insn_format22s | insn_format22t | insn_format22x | insn_format23x | insn_format25x | insn_format30t | insn_format31c | insn_format31i | insn_format31t | insn_format32x | insn_format35c_method | insn_format35c_type | insn_format3rc_method | insn_format3rc_type | insn_format51l_type | insn_array_data_directive | insn_packed_switch_directive | insn_sparse_switch_directive )
			int alt37=38;
			switch ( input.LA(1) ) {
			case I_STATEMENT_FORMAT10t:
				{
				alt37=1;
				}
				break;
			case I_STATEMENT_FORMAT10x:
				{
				alt37=2;
				}
				break;
			case I_STATEMENT_FORMAT11n:
				{
				alt37=3;
				}
				break;
			case I_STATEMENT_FORMAT11x:
				{
				alt37=4;
				}
				break;
			case I_STATEMENT_FORMAT12x:
				{
				alt37=5;
				}
				break;
			case I_STATEMENT_FORMAT20bc:
				{
				alt37=6;
				}
				break;
			case I_STATEMENT_FORMAT20t:
				{
				alt37=7;
				}
				break;
			case I_STATEMENT_FORMAT21c_FIELD:
				{
				alt37=8;
				}
				break;
			case I_STATEMENT_FORMAT21c_STRING:
				{
				alt37=9;
				}
				break;
			case I_STATEMENT_FORMAT21c_TYPE:
				{
				alt37=10;
				}
				break;
			case I_STATEMENT_FORMAT21c_LAMBDA:
				{
				alt37=11;
				}
				break;
			case I_STATEMENT_FORMAT21c_METHOD:
				{
				alt37=12;
				}
				break;
			case I_STATEMENT_FORMAT21ih:
				{
				alt37=13;
				}
				break;
			case I_STATEMENT_FORMAT21lh:
				{
				alt37=14;
				}
				break;
			case I_STATEMENT_FORMAT21s:
				{
				alt37=15;
				}
				break;
			case I_STATEMENT_FORMAT21t:
				{
				alt37=16;
				}
				break;
			case I_STATEMENT_FORMAT22b:
				{
				alt37=17;
				}
				break;
			case I_STATEMENT_FORMAT22c_FIELD:
				{
				alt37=18;
				}
				break;
			case I_STATEMENT_FORMAT22c_TYPE:
				{
				alt37=19;
				}
				break;
			case I_STATEMENT_FORMAT22c_STRING:
				{
				alt37=20;
				}
				break;
			case I_STATEMENT_FORMAT22s:
				{
				alt37=21;
				}
				break;
			case I_STATEMENT_FORMAT22t:
				{
				alt37=22;
				}
				break;
			case I_STATEMENT_FORMAT22x:
				{
				alt37=23;
				}
				break;
			case I_STATEMENT_FORMAT23x:
				{
				alt37=24;
				}
				break;
			case I_STATEMENT_FORMAT25x:
				{
				alt37=25;
				}
				break;
			case I_STATEMENT_FORMAT30t:
				{
				alt37=26;
				}
				break;
			case I_STATEMENT_FORMAT31c:
				{
				alt37=27;
				}
				break;
			case I_STATEMENT_FORMAT31i:
				{
				alt37=28;
				}
				break;
			case I_STATEMENT_FORMAT31t:
				{
				alt37=29;
				}
				break;
			case I_STATEMENT_FORMAT32x:
				{
				alt37=30;
				}
				break;
			case I_STATEMENT_FORMAT35c_METHOD:
				{
				alt37=31;
				}
				break;
			case I_STATEMENT_FORMAT35c_TYPE:
				{
				alt37=32;
				}
				break;
			case I_STATEMENT_FORMAT3rc_METHOD:
				{
				alt37=33;
				}
				break;
			case I_STATEMENT_FORMAT3rc_TYPE:
				{
				alt37=34;
				}
				break;
			case I_STATEMENT_FORMAT51l:
				{
				alt37=35;
				}
				break;
			case I_STATEMENT_ARRAY_DATA:
				{
				alt37=36;
				}
				break;
			case I_STATEMENT_PACKED_SWITCH:
				{
				alt37=37;
				}
				break;
			case I_STATEMENT_SPARSE_SWITCH:
				{
				alt37=38;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 37, 0, input);
				throw nvae;
			}
			switch (alt37) {
				case 1 :
					// smaliTreeWalker.g:735:5: insn_format10t
					{
					pushFollow(FOLLOW_insn_format10t_in_instruction1849);
					insn_format10t();
					state._fsp--;

					}
					break;
				case 2 :
					// smaliTreeWalker.g:736:5: insn_format10x
					{
					pushFollow(FOLLOW_insn_format10x_in_instruction1855);
					insn_format10x();
					state._fsp--;

					}
					break;
				case 3 :
					// smaliTreeWalker.g:737:5: insn_format11n
					{
					pushFollow(FOLLOW_insn_format11n_in_instruction1861);
					insn_format11n();
					state._fsp--;

					}
					break;
				case 4 :
					// smaliTreeWalker.g:738:5: insn_format11x
					{
					pushFollow(FOLLOW_insn_format11x_in_instruction1867);
					insn_format11x();
					state._fsp--;

					}
					break;
				case 5 :
					// smaliTreeWalker.g:739:5: insn_format12x
					{
					pushFollow(FOLLOW_insn_format12x_in_instruction1873);
					insn_format12x();
					state._fsp--;

					}
					break;
				case 6 :
					// smaliTreeWalker.g:740:5: insn_format20bc
					{
					pushFollow(FOLLOW_insn_format20bc_in_instruction1879);
					insn_format20bc();
					state._fsp--;

					}
					break;
				case 7 :
					// smaliTreeWalker.g:741:5: insn_format20t
					{
					pushFollow(FOLLOW_insn_format20t_in_instruction1885);
					insn_format20t();
					state._fsp--;

					}
					break;
				case 8 :
					// smaliTreeWalker.g:742:5: insn_format21c_field
					{
					pushFollow(FOLLOW_insn_format21c_field_in_instruction1891);
					insn_format21c_field();
					state._fsp--;

					}
					break;
				case 9 :
					// smaliTreeWalker.g:743:5: insn_format21c_string
					{
					pushFollow(FOLLOW_insn_format21c_string_in_instruction1897);
					insn_format21c_string();
					state._fsp--;

					}
					break;
				case 10 :
					// smaliTreeWalker.g:744:5: insn_format21c_type
					{
					pushFollow(FOLLOW_insn_format21c_type_in_instruction1903);
					insn_format21c_type();
					state._fsp--;

					}
					break;
				case 11 :
					// smaliTreeWalker.g:745:5: insn_format21c_lambda
					{
					pushFollow(FOLLOW_insn_format21c_lambda_in_instruction1909);
					insn_format21c_lambda();
					state._fsp--;

					}
					break;
				case 12 :
					// smaliTreeWalker.g:746:5: insn_format21c_method
					{
					pushFollow(FOLLOW_insn_format21c_method_in_instruction1915);
					insn_format21c_method();
					state._fsp--;

					}
					break;
				case 13 :
					// smaliTreeWalker.g:747:5: insn_format21ih
					{
					pushFollow(FOLLOW_insn_format21ih_in_instruction1921);
					insn_format21ih();
					state._fsp--;

					}
					break;
				case 14 :
					// smaliTreeWalker.g:748:5: insn_format21lh
					{
					pushFollow(FOLLOW_insn_format21lh_in_instruction1927);
					insn_format21lh();
					state._fsp--;

					}
					break;
				case 15 :
					// smaliTreeWalker.g:749:5: insn_format21s
					{
					pushFollow(FOLLOW_insn_format21s_in_instruction1933);
					insn_format21s();
					state._fsp--;

					}
					break;
				case 16 :
					// smaliTreeWalker.g:750:5: insn_format21t
					{
					pushFollow(FOLLOW_insn_format21t_in_instruction1939);
					insn_format21t();
					state._fsp--;

					}
					break;
				case 17 :
					// smaliTreeWalker.g:751:5: insn_format22b
					{
					pushFollow(FOLLOW_insn_format22b_in_instruction1945);
					insn_format22b();
					state._fsp--;

					}
					break;
				case 18 :
					// smaliTreeWalker.g:752:5: insn_format22c_field
					{
					pushFollow(FOLLOW_insn_format22c_field_in_instruction1951);
					insn_format22c_field();
					state._fsp--;

					}
					break;
				case 19 :
					// smaliTreeWalker.g:753:5: insn_format22c_type
					{
					pushFollow(FOLLOW_insn_format22c_type_in_instruction1957);
					insn_format22c_type();
					state._fsp--;

					}
					break;
				case 20 :
					// smaliTreeWalker.g:754:5: insn_format22c_string
					{
					pushFollow(FOLLOW_insn_format22c_string_in_instruction1963);
					insn_format22c_string();
					state._fsp--;

					}
					break;
				case 21 :
					// smaliTreeWalker.g:755:5: insn_format22s
					{
					pushFollow(FOLLOW_insn_format22s_in_instruction1969);
					insn_format22s();
					state._fsp--;

					}
					break;
				case 22 :
					// smaliTreeWalker.g:756:5: insn_format22t
					{
					pushFollow(FOLLOW_insn_format22t_in_instruction1975);
					insn_format22t();
					state._fsp--;

					}
					break;
				case 23 :
					// smaliTreeWalker.g:757:5: insn_format22x
					{
					pushFollow(FOLLOW_insn_format22x_in_instruction1981);
					insn_format22x();
					state._fsp--;

					}
					break;
				case 24 :
					// smaliTreeWalker.g:758:5: insn_format23x
					{
					pushFollow(FOLLOW_insn_format23x_in_instruction1987);
					insn_format23x();
					state._fsp--;

					}
					break;
				case 25 :
					// smaliTreeWalker.g:759:5: insn_format25x
					{
					pushFollow(FOLLOW_insn_format25x_in_instruction1993);
					insn_format25x();
					state._fsp--;

					}
					break;
				case 26 :
					// smaliTreeWalker.g:760:5: insn_format30t
					{
					pushFollow(FOLLOW_insn_format30t_in_instruction1999);
					insn_format30t();
					state._fsp--;

					}
					break;
				case 27 :
					// smaliTreeWalker.g:761:5: insn_format31c
					{
					pushFollow(FOLLOW_insn_format31c_in_instruction2005);
					insn_format31c();
					state._fsp--;

					}
					break;
				case 28 :
					// smaliTreeWalker.g:762:5: insn_format31i
					{
					pushFollow(FOLLOW_insn_format31i_in_instruction2011);
					insn_format31i();
					state._fsp--;

					}
					break;
				case 29 :
					// smaliTreeWalker.g:763:5: insn_format31t
					{
					pushFollow(FOLLOW_insn_format31t_in_instruction2017);
					insn_format31t();
					state._fsp--;

					}
					break;
				case 30 :
					// smaliTreeWalker.g:764:5: insn_format32x
					{
					pushFollow(FOLLOW_insn_format32x_in_instruction2023);
					insn_format32x();
					state._fsp--;

					}
					break;
				case 31 :
					// smaliTreeWalker.g:765:5: insn_format35c_method
					{
					pushFollow(FOLLOW_insn_format35c_method_in_instruction2029);
					insn_format35c_method();
					state._fsp--;

					}
					break;
				case 32 :
					// smaliTreeWalker.g:766:5: insn_format35c_type
					{
					pushFollow(FOLLOW_insn_format35c_type_in_instruction2035);
					insn_format35c_type();
					state._fsp--;

					}
					break;
				case 33 :
					// smaliTreeWalker.g:767:5: insn_format3rc_method
					{
					pushFollow(FOLLOW_insn_format3rc_method_in_instruction2041);
					insn_format3rc_method();
					state._fsp--;

					}
					break;
				case 34 :
					// smaliTreeWalker.g:768:5: insn_format3rc_type
					{
					pushFollow(FOLLOW_insn_format3rc_type_in_instruction2047);
					insn_format3rc_type();
					state._fsp--;

					}
					break;
				case 35 :
					// smaliTreeWalker.g:769:5: insn_format51l_type
					{
					pushFollow(FOLLOW_insn_format51l_type_in_instruction2053);
					insn_format51l_type();
					state._fsp--;

					}
					break;
				case 36 :
					// smaliTreeWalker.g:770:5: insn_array_data_directive
					{
					pushFollow(FOLLOW_insn_array_data_directive_in_instruction2059);
					insn_array_data_directive();
					state._fsp--;

					}
					break;
				case 37 :
					// smaliTreeWalker.g:771:5: insn_packed_switch_directive
					{
					pushFollow(FOLLOW_insn_packed_switch_directive_in_instruction2065);
					insn_packed_switch_directive();
					state._fsp--;

					}
					break;
				case 38 :
					// smaliTreeWalker.g:772:5: insn_sparse_switch_directive
					{
					pushFollow(FOLLOW_insn_sparse_switch_directive_in_instruction2071);
					insn_sparse_switch_directive();
					state._fsp--;

					}
					break;

			}
		}
		catch (Exception ex) {

			    reportError(new SemanticException(input, ((CommonTree)retval.start), ex.getMessage()));
			    recover(input, null);
			  
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "instruction"



	// $ANTLR start "insn_format10t"
	// smaliTreeWalker.g:778:1: insn_format10t : ^( I_STATEMENT_FORMAT10t INSTRUCTION_FORMAT10t label_ref ) ;
	public final void insn_format10t() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT10t106=null;
		Label label_ref107 =null;

		try {
			// smaliTreeWalker.g:779:3: ( ^( I_STATEMENT_FORMAT10t INSTRUCTION_FORMAT10t label_ref ) )
			// smaliTreeWalker.g:780:5: ^( I_STATEMENT_FORMAT10t INSTRUCTION_FORMAT10t label_ref )
			{
			match(input,I_STATEMENT_FORMAT10t,FOLLOW_I_STATEMENT_FORMAT10t_in_insn_format10t2095); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT10t106=(CommonTree)match(input,INSTRUCTION_FORMAT10t,FOLLOW_INSTRUCTION_FORMAT10t_in_insn_format10t2097); 
			pushFollow(FOLLOW_label_ref_in_insn_format10t2099);
			label_ref107=label_ref();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT10t106!=null?INSTRUCTION_FORMAT10t106.getText():null));
			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction10t(opcode, label_ref107));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format10t"



	// $ANTLR start "insn_format10x"
	// smaliTreeWalker.g:786:1: insn_format10x : ^( I_STATEMENT_FORMAT10x INSTRUCTION_FORMAT10x ) ;
	public final void insn_format10x() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT10x108=null;

		try {
			// smaliTreeWalker.g:787:3: ( ^( I_STATEMENT_FORMAT10x INSTRUCTION_FORMAT10x ) )
			// smaliTreeWalker.g:788:5: ^( I_STATEMENT_FORMAT10x INSTRUCTION_FORMAT10x )
			{
			match(input,I_STATEMENT_FORMAT10x,FOLLOW_I_STATEMENT_FORMAT10x_in_insn_format10x2122); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT10x108=(CommonTree)match(input,INSTRUCTION_FORMAT10x,FOLLOW_INSTRUCTION_FORMAT10x_in_insn_format10x2124); 
			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT10x108!=null?INSTRUCTION_FORMAT10x108.getText():null));
			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction10x(opcode));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format10x"



	// $ANTLR start "insn_format11n"
	// smaliTreeWalker.g:794:1: insn_format11n : ^( I_STATEMENT_FORMAT11n INSTRUCTION_FORMAT11n REGISTER short_integral_literal ) ;
	public final void insn_format11n() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT11n109=null;
		CommonTree REGISTER110=null;
		short short_integral_literal111 =0;

		try {
			// smaliTreeWalker.g:795:3: ( ^( I_STATEMENT_FORMAT11n INSTRUCTION_FORMAT11n REGISTER short_integral_literal ) )
			// smaliTreeWalker.g:796:5: ^( I_STATEMENT_FORMAT11n INSTRUCTION_FORMAT11n REGISTER short_integral_literal )
			{
			match(input,I_STATEMENT_FORMAT11n,FOLLOW_I_STATEMENT_FORMAT11n_in_insn_format11n2147); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT11n109=(CommonTree)match(input,INSTRUCTION_FORMAT11n,FOLLOW_INSTRUCTION_FORMAT11n_in_insn_format11n2149); 
			REGISTER110=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format11n2151); 
			pushFollow(FOLLOW_short_integral_literal_in_insn_format11n2153);
			short_integral_literal111=short_integral_literal();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT11n109!=null?INSTRUCTION_FORMAT11n109.getText():null));
			      byte regA = parseRegister_nibble((REGISTER110!=null?REGISTER110.getText():null));

			      short litB = short_integral_literal111;
			      LiteralTools.checkNibble(litB);

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction11n(opcode, regA, litB));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format11n"



	// $ANTLR start "insn_format11x"
	// smaliTreeWalker.g:807:1: insn_format11x : ^( I_STATEMENT_FORMAT11x INSTRUCTION_FORMAT11x REGISTER ) ;
	public final void insn_format11x() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT11x112=null;
		CommonTree REGISTER113=null;

		try {
			// smaliTreeWalker.g:808:3: ( ^( I_STATEMENT_FORMAT11x INSTRUCTION_FORMAT11x REGISTER ) )
			// smaliTreeWalker.g:809:5: ^( I_STATEMENT_FORMAT11x INSTRUCTION_FORMAT11x REGISTER )
			{
			match(input,I_STATEMENT_FORMAT11x,FOLLOW_I_STATEMENT_FORMAT11x_in_insn_format11x2176); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT11x112=(CommonTree)match(input,INSTRUCTION_FORMAT11x,FOLLOW_INSTRUCTION_FORMAT11x_in_insn_format11x2178); 
			REGISTER113=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format11x2180); 
			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT11x112!=null?INSTRUCTION_FORMAT11x112.getText():null));
			      short regA = parseRegister_byte((REGISTER113!=null?REGISTER113.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction11x(opcode, regA));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format11x"



	// $ANTLR start "insn_format12x"
	// smaliTreeWalker.g:817:1: insn_format12x : ^( I_STATEMENT_FORMAT12x INSTRUCTION_FORMAT12x registerA= REGISTER registerB= REGISTER ) ;
	public final void insn_format12x() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree INSTRUCTION_FORMAT12x114=null;

		try {
			// smaliTreeWalker.g:818:3: ( ^( I_STATEMENT_FORMAT12x INSTRUCTION_FORMAT12x registerA= REGISTER registerB= REGISTER ) )
			// smaliTreeWalker.g:819:5: ^( I_STATEMENT_FORMAT12x INSTRUCTION_FORMAT12x registerA= REGISTER registerB= REGISTER )
			{
			match(input,I_STATEMENT_FORMAT12x,FOLLOW_I_STATEMENT_FORMAT12x_in_insn_format12x2203); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT12x114=(CommonTree)match(input,INSTRUCTION_FORMAT12x,FOLLOW_INSTRUCTION_FORMAT12x_in_insn_format12x2205); 
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format12x2209); 
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format12x2213); 
			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT12x114!=null?INSTRUCTION_FORMAT12x114.getText():null));
			      byte regA = parseRegister_nibble((registerA!=null?registerA.getText():null));
			      byte regB = parseRegister_nibble((registerB!=null?registerB.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction12x(opcode, regA, regB));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format12x"



	// $ANTLR start "insn_format20bc"
	// smaliTreeWalker.g:828:1: insn_format20bc : ^( I_STATEMENT_FORMAT20bc INSTRUCTION_FORMAT20bc verification_error_type verification_error_reference ) ;
	public final void insn_format20bc() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT20bc115=null;
		int verification_error_type116 =0;
		ImmutableReference verification_error_reference117 =null;

		try {
			// smaliTreeWalker.g:829:3: ( ^( I_STATEMENT_FORMAT20bc INSTRUCTION_FORMAT20bc verification_error_type verification_error_reference ) )
			// smaliTreeWalker.g:830:5: ^( I_STATEMENT_FORMAT20bc INSTRUCTION_FORMAT20bc verification_error_type verification_error_reference )
			{
			match(input,I_STATEMENT_FORMAT20bc,FOLLOW_I_STATEMENT_FORMAT20bc_in_insn_format20bc2236); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT20bc115=(CommonTree)match(input,INSTRUCTION_FORMAT20bc,FOLLOW_INSTRUCTION_FORMAT20bc_in_insn_format20bc2238); 
			pushFollow(FOLLOW_verification_error_type_in_insn_format20bc2240);
			verification_error_type116=verification_error_type();
			state._fsp--;

			pushFollow(FOLLOW_verification_error_reference_in_insn_format20bc2242);
			verification_error_reference117=verification_error_reference();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT20bc115!=null?INSTRUCTION_FORMAT20bc115.getText():null));

			      int verificationError = verification_error_type116;
			      ImmutableReference referencedItem = verification_error_reference117;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction20bc(opcode, verificationError,
			              dexBuilder.internReference(referencedItem)));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format20bc"



	// $ANTLR start "insn_format20t"
	// smaliTreeWalker.g:841:1: insn_format20t : ^( I_STATEMENT_FORMAT20t INSTRUCTION_FORMAT20t label_ref ) ;
	public final void insn_format20t() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT20t118=null;
		Label label_ref119 =null;

		try {
			// smaliTreeWalker.g:842:3: ( ^( I_STATEMENT_FORMAT20t INSTRUCTION_FORMAT20t label_ref ) )
			// smaliTreeWalker.g:843:5: ^( I_STATEMENT_FORMAT20t INSTRUCTION_FORMAT20t label_ref )
			{
			match(input,I_STATEMENT_FORMAT20t,FOLLOW_I_STATEMENT_FORMAT20t_in_insn_format20t2265); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT20t118=(CommonTree)match(input,INSTRUCTION_FORMAT20t,FOLLOW_INSTRUCTION_FORMAT20t_in_insn_format20t2267); 
			pushFollow(FOLLOW_label_ref_in_insn_format20t2269);
			label_ref119=label_ref();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT20t118!=null?INSTRUCTION_FORMAT20t118.getText():null));
			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction20t(opcode, label_ref119));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format20t"



	// $ANTLR start "insn_format21c_field"
	// smaliTreeWalker.g:849:1: insn_format21c_field : ^( I_STATEMENT_FORMAT21c_FIELD inst= ( INSTRUCTION_FORMAT21c_FIELD | INSTRUCTION_FORMAT21c_FIELD_ODEX ) REGISTER field_reference ) ;
	public final void insn_format21c_field() throws RecognitionException {
		CommonTree inst=null;
		CommonTree REGISTER120=null;
		ImmutableFieldReference field_reference121 =null;

		try {
			// smaliTreeWalker.g:850:3: ( ^( I_STATEMENT_FORMAT21c_FIELD inst= ( INSTRUCTION_FORMAT21c_FIELD | INSTRUCTION_FORMAT21c_FIELD_ODEX ) REGISTER field_reference ) )
			// smaliTreeWalker.g:851:5: ^( I_STATEMENT_FORMAT21c_FIELD inst= ( INSTRUCTION_FORMAT21c_FIELD | INSTRUCTION_FORMAT21c_FIELD_ODEX ) REGISTER field_reference )
			{
			match(input,I_STATEMENT_FORMAT21c_FIELD,FOLLOW_I_STATEMENT_FORMAT21c_FIELD_in_insn_format21c_field2292); 
			match(input, Token.DOWN, null); 
			inst=(CommonTree)input.LT(1);
			if ( (input.LA(1) >= INSTRUCTION_FORMAT21c_FIELD && input.LA(1) <= INSTRUCTION_FORMAT21c_FIELD_ODEX) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			REGISTER120=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_field2304); 
			pushFollow(FOLLOW_field_reference_in_insn_format21c_field2306);
			field_reference121=field_reference();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((inst!=null?inst.getText():null));
			      short regA = parseRegister_byte((REGISTER120!=null?REGISTER120.getText():null));

			      ImmutableFieldReference fieldReference = field_reference121;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21c(opcode, regA,
			              dexBuilder.internFieldReference(fieldReference)));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21c_field"



	// $ANTLR start "insn_format21c_string"
	// smaliTreeWalker.g:862:1: insn_format21c_string : ^( I_STATEMENT_FORMAT21c_STRING INSTRUCTION_FORMAT21c_STRING REGISTER string_literal ) ;
	public final void insn_format21c_string() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT21c_STRING122=null;
		CommonTree REGISTER123=null;
		String string_literal124 =null;

		try {
			// smaliTreeWalker.g:863:3: ( ^( I_STATEMENT_FORMAT21c_STRING INSTRUCTION_FORMAT21c_STRING REGISTER string_literal ) )
			// smaliTreeWalker.g:864:5: ^( I_STATEMENT_FORMAT21c_STRING INSTRUCTION_FORMAT21c_STRING REGISTER string_literal )
			{
			match(input,I_STATEMENT_FORMAT21c_STRING,FOLLOW_I_STATEMENT_FORMAT21c_STRING_in_insn_format21c_string2329); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT21c_STRING122=(CommonTree)match(input,INSTRUCTION_FORMAT21c_STRING,FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_insn_format21c_string2331); 
			REGISTER123=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_string2333); 
			pushFollow(FOLLOW_string_literal_in_insn_format21c_string2335);
			string_literal124=string_literal();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT21c_STRING122!=null?INSTRUCTION_FORMAT21c_STRING122.getText():null));
			      short regA = parseRegister_byte((REGISTER123!=null?REGISTER123.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21c(opcode, regA,
			              dexBuilder.internStringReference(string_literal124)));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21c_string"



	// $ANTLR start "insn_format21c_type"
	// smaliTreeWalker.g:873:1: insn_format21c_type : ^( I_STATEMENT_FORMAT21c_TYPE INSTRUCTION_FORMAT21c_TYPE REGISTER nonvoid_type_descriptor ) ;
	public final void insn_format21c_type() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT21c_TYPE125=null;
		CommonTree REGISTER126=null;
		TreeRuleReturnScope nonvoid_type_descriptor127 =null;

		try {
			// smaliTreeWalker.g:874:3: ( ^( I_STATEMENT_FORMAT21c_TYPE INSTRUCTION_FORMAT21c_TYPE REGISTER nonvoid_type_descriptor ) )
			// smaliTreeWalker.g:875:5: ^( I_STATEMENT_FORMAT21c_TYPE INSTRUCTION_FORMAT21c_TYPE REGISTER nonvoid_type_descriptor )
			{
			match(input,I_STATEMENT_FORMAT21c_TYPE,FOLLOW_I_STATEMENT_FORMAT21c_TYPE_in_insn_format21c_type2358); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT21c_TYPE125=(CommonTree)match(input,INSTRUCTION_FORMAT21c_TYPE,FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_insn_format21c_type2360); 
			REGISTER126=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_type2362); 
			pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format21c_type2364);
			nonvoid_type_descriptor127=nonvoid_type_descriptor();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT21c_TYPE125!=null?INSTRUCTION_FORMAT21c_TYPE125.getText():null));
			      short regA = parseRegister_byte((REGISTER126!=null?REGISTER126.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21c(opcode, regA,
			              dexBuilder.internTypeReference((nonvoid_type_descriptor127!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor127).type:null))));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21c_type"



	// $ANTLR start "insn_format21c_lambda"
	// smaliTreeWalker.g:884:1: insn_format21c_lambda : ^( I_STATEMENT_FORMAT21c_LAMBDA INSTRUCTION_FORMAT21c_LAMBDA REGISTER string_literal ) ;
	public final void insn_format21c_lambda() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT21c_LAMBDA128=null;
		CommonTree REGISTER129=null;
		String string_literal130 =null;

		try {
			// smaliTreeWalker.g:885:3: ( ^( I_STATEMENT_FORMAT21c_LAMBDA INSTRUCTION_FORMAT21c_LAMBDA REGISTER string_literal ) )
			// smaliTreeWalker.g:886:5: ^( I_STATEMENT_FORMAT21c_LAMBDA INSTRUCTION_FORMAT21c_LAMBDA REGISTER string_literal )
			{
			match(input,I_STATEMENT_FORMAT21c_LAMBDA,FOLLOW_I_STATEMENT_FORMAT21c_LAMBDA_in_insn_format21c_lambda2387); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT21c_LAMBDA128=(CommonTree)match(input,INSTRUCTION_FORMAT21c_LAMBDA,FOLLOW_INSTRUCTION_FORMAT21c_LAMBDA_in_insn_format21c_lambda2389); 
			REGISTER129=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_lambda2391); 
			pushFollow(FOLLOW_string_literal_in_insn_format21c_lambda2393);
			string_literal130=string_literal();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT21c_LAMBDA128!=null?INSTRUCTION_FORMAT21c_LAMBDA128.getText():null));
			      short regA = parseRegister_byte((REGISTER129!=null?REGISTER129.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21c(opcode, regA,
			              dexBuilder.internStringReference(string_literal130)));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21c_lambda"



	// $ANTLR start "insn_format21c_method"
	// smaliTreeWalker.g:895:1: insn_format21c_method : ^( I_STATEMENT_FORMAT21c_METHOD INSTRUCTION_FORMAT21c_METHOD REGISTER method_reference ) ;
	public final void insn_format21c_method() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT21c_METHOD131=null;
		CommonTree REGISTER132=null;
		ImmutableMethodReference method_reference133 =null;

		try {
			// smaliTreeWalker.g:896:3: ( ^( I_STATEMENT_FORMAT21c_METHOD INSTRUCTION_FORMAT21c_METHOD REGISTER method_reference ) )
			// smaliTreeWalker.g:897:5: ^( I_STATEMENT_FORMAT21c_METHOD INSTRUCTION_FORMAT21c_METHOD REGISTER method_reference )
			{
			match(input,I_STATEMENT_FORMAT21c_METHOD,FOLLOW_I_STATEMENT_FORMAT21c_METHOD_in_insn_format21c_method2416); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT21c_METHOD131=(CommonTree)match(input,INSTRUCTION_FORMAT21c_METHOD,FOLLOW_INSTRUCTION_FORMAT21c_METHOD_in_insn_format21c_method2418); 
			REGISTER132=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_method2420); 
			pushFollow(FOLLOW_method_reference_in_insn_format21c_method2422);
			method_reference133=method_reference();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT21c_METHOD131!=null?INSTRUCTION_FORMAT21c_METHOD131.getText():null));
			      short regA = parseRegister_byte((REGISTER132!=null?REGISTER132.getText():null));

			      ImmutableMethodReference methodReference = method_reference133;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21c(opcode, regA,
			              dexBuilder.internMethodReference(methodReference)));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21c_method"



	// $ANTLR start "insn_format21ih"
	// smaliTreeWalker.g:908:1: insn_format21ih : ^( I_STATEMENT_FORMAT21ih INSTRUCTION_FORMAT21ih REGISTER fixed_32bit_literal ) ;
	public final void insn_format21ih() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT21ih134=null;
		CommonTree REGISTER135=null;
		int fixed_32bit_literal136 =0;

		try {
			// smaliTreeWalker.g:909:3: ( ^( I_STATEMENT_FORMAT21ih INSTRUCTION_FORMAT21ih REGISTER fixed_32bit_literal ) )
			// smaliTreeWalker.g:910:5: ^( I_STATEMENT_FORMAT21ih INSTRUCTION_FORMAT21ih REGISTER fixed_32bit_literal )
			{
			match(input,I_STATEMENT_FORMAT21ih,FOLLOW_I_STATEMENT_FORMAT21ih_in_insn_format21ih2445); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT21ih134=(CommonTree)match(input,INSTRUCTION_FORMAT21ih,FOLLOW_INSTRUCTION_FORMAT21ih_in_insn_format21ih2447); 
			REGISTER135=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21ih2449); 
			pushFollow(FOLLOW_fixed_32bit_literal_in_insn_format21ih2451);
			fixed_32bit_literal136=fixed_32bit_literal();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT21ih134!=null?INSTRUCTION_FORMAT21ih134.getText():null));
			      short regA = parseRegister_byte((REGISTER135!=null?REGISTER135.getText():null));

			      int litB = fixed_32bit_literal136;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21ih(opcode, regA, litB));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21ih"



	// $ANTLR start "insn_format21lh"
	// smaliTreeWalker.g:920:1: insn_format21lh : ^( I_STATEMENT_FORMAT21lh INSTRUCTION_FORMAT21lh REGISTER fixed_64bit_literal ) ;
	public final void insn_format21lh() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT21lh137=null;
		CommonTree REGISTER138=null;
		long fixed_64bit_literal139 =0;

		try {
			// smaliTreeWalker.g:921:3: ( ^( I_STATEMENT_FORMAT21lh INSTRUCTION_FORMAT21lh REGISTER fixed_64bit_literal ) )
			// smaliTreeWalker.g:922:5: ^( I_STATEMENT_FORMAT21lh INSTRUCTION_FORMAT21lh REGISTER fixed_64bit_literal )
			{
			match(input,I_STATEMENT_FORMAT21lh,FOLLOW_I_STATEMENT_FORMAT21lh_in_insn_format21lh2474); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT21lh137=(CommonTree)match(input,INSTRUCTION_FORMAT21lh,FOLLOW_INSTRUCTION_FORMAT21lh_in_insn_format21lh2476); 
			REGISTER138=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21lh2478); 
			pushFollow(FOLLOW_fixed_64bit_literal_in_insn_format21lh2480);
			fixed_64bit_literal139=fixed_64bit_literal();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT21lh137!=null?INSTRUCTION_FORMAT21lh137.getText():null));
			      short regA = parseRegister_byte((REGISTER138!=null?REGISTER138.getText():null));

			      long litB = fixed_64bit_literal139;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21lh(opcode, regA, litB));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21lh"



	// $ANTLR start "insn_format21s"
	// smaliTreeWalker.g:932:1: insn_format21s : ^( I_STATEMENT_FORMAT21s INSTRUCTION_FORMAT21s REGISTER short_integral_literal ) ;
	public final void insn_format21s() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT21s140=null;
		CommonTree REGISTER141=null;
		short short_integral_literal142 =0;

		try {
			// smaliTreeWalker.g:933:3: ( ^( I_STATEMENT_FORMAT21s INSTRUCTION_FORMAT21s REGISTER short_integral_literal ) )
			// smaliTreeWalker.g:934:5: ^( I_STATEMENT_FORMAT21s INSTRUCTION_FORMAT21s REGISTER short_integral_literal )
			{
			match(input,I_STATEMENT_FORMAT21s,FOLLOW_I_STATEMENT_FORMAT21s_in_insn_format21s2503); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT21s140=(CommonTree)match(input,INSTRUCTION_FORMAT21s,FOLLOW_INSTRUCTION_FORMAT21s_in_insn_format21s2505); 
			REGISTER141=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21s2507); 
			pushFollow(FOLLOW_short_integral_literal_in_insn_format21s2509);
			short_integral_literal142=short_integral_literal();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT21s140!=null?INSTRUCTION_FORMAT21s140.getText():null));
			      short regA = parseRegister_byte((REGISTER141!=null?REGISTER141.getText():null));

			      short litB = short_integral_literal142;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21s(opcode, regA, litB));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21s"



	// $ANTLR start "insn_format21t"
	// smaliTreeWalker.g:944:1: insn_format21t : ^( I_STATEMENT_FORMAT21t INSTRUCTION_FORMAT21t REGISTER label_ref ) ;
	public final void insn_format21t() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT21t143=null;
		CommonTree REGISTER144=null;
		Label label_ref145 =null;

		try {
			// smaliTreeWalker.g:945:3: ( ^( I_STATEMENT_FORMAT21t INSTRUCTION_FORMAT21t REGISTER label_ref ) )
			// smaliTreeWalker.g:946:5: ^( I_STATEMENT_FORMAT21t INSTRUCTION_FORMAT21t REGISTER label_ref )
			{
			match(input,I_STATEMENT_FORMAT21t,FOLLOW_I_STATEMENT_FORMAT21t_in_insn_format21t2532); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT21t143=(CommonTree)match(input,INSTRUCTION_FORMAT21t,FOLLOW_INSTRUCTION_FORMAT21t_in_insn_format21t2534); 
			REGISTER144=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21t2536); 
			pushFollow(FOLLOW_label_ref_in_insn_format21t2538);
			label_ref145=label_ref();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT21t143!=null?INSTRUCTION_FORMAT21t143.getText():null));
			      short regA = parseRegister_byte((REGISTER144!=null?REGISTER144.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21t(opcode, regA, label_ref145));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21t"



	// $ANTLR start "insn_format22b"
	// smaliTreeWalker.g:954:1: insn_format22b : ^( I_STATEMENT_FORMAT22b INSTRUCTION_FORMAT22b registerA= REGISTER registerB= REGISTER short_integral_literal ) ;
	public final void insn_format22b() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree INSTRUCTION_FORMAT22b146=null;
		short short_integral_literal147 =0;

		try {
			// smaliTreeWalker.g:955:3: ( ^( I_STATEMENT_FORMAT22b INSTRUCTION_FORMAT22b registerA= REGISTER registerB= REGISTER short_integral_literal ) )
			// smaliTreeWalker.g:956:5: ^( I_STATEMENT_FORMAT22b INSTRUCTION_FORMAT22b registerA= REGISTER registerB= REGISTER short_integral_literal )
			{
			match(input,I_STATEMENT_FORMAT22b,FOLLOW_I_STATEMENT_FORMAT22b_in_insn_format22b2561); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT22b146=(CommonTree)match(input,INSTRUCTION_FORMAT22b,FOLLOW_INSTRUCTION_FORMAT22b_in_insn_format22b2563); 
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22b2567); 
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22b2571); 
			pushFollow(FOLLOW_short_integral_literal_in_insn_format22b2573);
			short_integral_literal147=short_integral_literal();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT22b146!=null?INSTRUCTION_FORMAT22b146.getText():null));
			      short regA = parseRegister_byte((registerA!=null?registerA.getText():null));
			      short regB = parseRegister_byte((registerB!=null?registerB.getText():null));

			      short litC = short_integral_literal147;
			      LiteralTools.checkByte(litC);

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction22b(opcode, regA, regB, litC));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format22b"



	// $ANTLR start "insn_format22c_field"
	// smaliTreeWalker.g:968:1: insn_format22c_field : ^( I_STATEMENT_FORMAT22c_FIELD inst= ( INSTRUCTION_FORMAT22c_FIELD | INSTRUCTION_FORMAT22c_FIELD_ODEX ) registerA= REGISTER registerB= REGISTER field_reference ) ;
	public final void insn_format22c_field() throws RecognitionException {
		CommonTree inst=null;
		CommonTree registerA=null;
		CommonTree registerB=null;
		ImmutableFieldReference field_reference148 =null;

		try {
			// smaliTreeWalker.g:969:3: ( ^( I_STATEMENT_FORMAT22c_FIELD inst= ( INSTRUCTION_FORMAT22c_FIELD | INSTRUCTION_FORMAT22c_FIELD_ODEX ) registerA= REGISTER registerB= REGISTER field_reference ) )
			// smaliTreeWalker.g:970:5: ^( I_STATEMENT_FORMAT22c_FIELD inst= ( INSTRUCTION_FORMAT22c_FIELD | INSTRUCTION_FORMAT22c_FIELD_ODEX ) registerA= REGISTER registerB= REGISTER field_reference )
			{
			match(input,I_STATEMENT_FORMAT22c_FIELD,FOLLOW_I_STATEMENT_FORMAT22c_FIELD_in_insn_format22c_field2596); 
			match(input, Token.DOWN, null); 
			inst=(CommonTree)input.LT(1);
			if ( (input.LA(1) >= INSTRUCTION_FORMAT22c_FIELD && input.LA(1) <= INSTRUCTION_FORMAT22c_FIELD_ODEX) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_field2610); 
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_field2614); 
			pushFollow(FOLLOW_field_reference_in_insn_format22c_field2616);
			field_reference148=field_reference();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((inst!=null?inst.getText():null));
			      byte regA = parseRegister_nibble((registerA!=null?registerA.getText():null));
			      byte regB = parseRegister_nibble((registerB!=null?registerB.getText():null));

			      ImmutableFieldReference fieldReference = field_reference148;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction22c(opcode, regA, regB,
			              dexBuilder.internFieldReference(fieldReference)));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format22c_field"



	// $ANTLR start "insn_format22c_type"
	// smaliTreeWalker.g:982:1: insn_format22c_type : ^( I_STATEMENT_FORMAT22c_TYPE INSTRUCTION_FORMAT22c_TYPE registerA= REGISTER registerB= REGISTER nonvoid_type_descriptor ) ;
	public final void insn_format22c_type() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree INSTRUCTION_FORMAT22c_TYPE149=null;
		TreeRuleReturnScope nonvoid_type_descriptor150 =null;

		try {
			// smaliTreeWalker.g:983:3: ( ^( I_STATEMENT_FORMAT22c_TYPE INSTRUCTION_FORMAT22c_TYPE registerA= REGISTER registerB= REGISTER nonvoid_type_descriptor ) )
			// smaliTreeWalker.g:984:5: ^( I_STATEMENT_FORMAT22c_TYPE INSTRUCTION_FORMAT22c_TYPE registerA= REGISTER registerB= REGISTER nonvoid_type_descriptor )
			{
			match(input,I_STATEMENT_FORMAT22c_TYPE,FOLLOW_I_STATEMENT_FORMAT22c_TYPE_in_insn_format22c_type2639); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT22c_TYPE149=(CommonTree)match(input,INSTRUCTION_FORMAT22c_TYPE,FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_insn_format22c_type2641); 
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_type2645); 
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_type2649); 
			pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format22c_type2651);
			nonvoid_type_descriptor150=nonvoid_type_descriptor();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT22c_TYPE149!=null?INSTRUCTION_FORMAT22c_TYPE149.getText():null));
			      byte regA = parseRegister_nibble((registerA!=null?registerA.getText():null));
			      byte regB = parseRegister_nibble((registerB!=null?registerB.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction22c(opcode, regA, regB,
			              dexBuilder.internTypeReference((nonvoid_type_descriptor150!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor150).type:null))));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format22c_type"



	// $ANTLR start "insn_format22c_string"
	// smaliTreeWalker.g:994:1: insn_format22c_string : ^( I_STATEMENT_FORMAT22c_STRING INSTRUCTION_FORMAT22c_STRING registerA= REGISTER registerB= REGISTER string_literal ) ;
	public final void insn_format22c_string() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree INSTRUCTION_FORMAT22c_STRING151=null;
		String string_literal152 =null;

		try {
			// smaliTreeWalker.g:995:3: ( ^( I_STATEMENT_FORMAT22c_STRING INSTRUCTION_FORMAT22c_STRING registerA= REGISTER registerB= REGISTER string_literal ) )
			// smaliTreeWalker.g:996:5: ^( I_STATEMENT_FORMAT22c_STRING INSTRUCTION_FORMAT22c_STRING registerA= REGISTER registerB= REGISTER string_literal )
			{
			match(input,I_STATEMENT_FORMAT22c_STRING,FOLLOW_I_STATEMENT_FORMAT22c_STRING_in_insn_format22c_string2674); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT22c_STRING151=(CommonTree)match(input,INSTRUCTION_FORMAT22c_STRING,FOLLOW_INSTRUCTION_FORMAT22c_STRING_in_insn_format22c_string2676); 
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_string2680); 
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_string2684); 
			pushFollow(FOLLOW_string_literal_in_insn_format22c_string2686);
			string_literal152=string_literal();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT22c_STRING151!=null?INSTRUCTION_FORMAT22c_STRING151.getText():null));
			      byte regA = parseRegister_nibble((registerA!=null?registerA.getText():null));
			      byte regB = parseRegister_nibble((registerB!=null?registerB.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction22c(opcode, regA, regB,
			              dexBuilder.internStringReference(string_literal152)));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format22c_string"



	// $ANTLR start "insn_format22s"
	// smaliTreeWalker.g:1006:1: insn_format22s : ^( I_STATEMENT_FORMAT22s INSTRUCTION_FORMAT22s registerA= REGISTER registerB= REGISTER short_integral_literal ) ;
	public final void insn_format22s() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree INSTRUCTION_FORMAT22s153=null;
		short short_integral_literal154 =0;

		try {
			// smaliTreeWalker.g:1007:3: ( ^( I_STATEMENT_FORMAT22s INSTRUCTION_FORMAT22s registerA= REGISTER registerB= REGISTER short_integral_literal ) )
			// smaliTreeWalker.g:1008:5: ^( I_STATEMENT_FORMAT22s INSTRUCTION_FORMAT22s registerA= REGISTER registerB= REGISTER short_integral_literal )
			{
			match(input,I_STATEMENT_FORMAT22s,FOLLOW_I_STATEMENT_FORMAT22s_in_insn_format22s2709); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT22s153=(CommonTree)match(input,INSTRUCTION_FORMAT22s,FOLLOW_INSTRUCTION_FORMAT22s_in_insn_format22s2711); 
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22s2715); 
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22s2719); 
			pushFollow(FOLLOW_short_integral_literal_in_insn_format22s2721);
			short_integral_literal154=short_integral_literal();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT22s153!=null?INSTRUCTION_FORMAT22s153.getText():null));
			      byte regA = parseRegister_nibble((registerA!=null?registerA.getText():null));
			      byte regB = parseRegister_nibble((registerB!=null?registerB.getText():null));

			      short litC = short_integral_literal154;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction22s(opcode, regA, regB, litC));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format22s"



	// $ANTLR start "insn_format22t"
	// smaliTreeWalker.g:1019:1: insn_format22t : ^( I_STATEMENT_FORMAT22t INSTRUCTION_FORMAT22t registerA= REGISTER registerB= REGISTER label_ref ) ;
	public final void insn_format22t() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree INSTRUCTION_FORMAT22t155=null;
		Label label_ref156 =null;

		try {
			// smaliTreeWalker.g:1020:3: ( ^( I_STATEMENT_FORMAT22t INSTRUCTION_FORMAT22t registerA= REGISTER registerB= REGISTER label_ref ) )
			// smaliTreeWalker.g:1021:5: ^( I_STATEMENT_FORMAT22t INSTRUCTION_FORMAT22t registerA= REGISTER registerB= REGISTER label_ref )
			{
			match(input,I_STATEMENT_FORMAT22t,FOLLOW_I_STATEMENT_FORMAT22t_in_insn_format22t2744); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT22t155=(CommonTree)match(input,INSTRUCTION_FORMAT22t,FOLLOW_INSTRUCTION_FORMAT22t_in_insn_format22t2746); 
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22t2750); 
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22t2754); 
			pushFollow(FOLLOW_label_ref_in_insn_format22t2756);
			label_ref156=label_ref();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT22t155!=null?INSTRUCTION_FORMAT22t155.getText():null));
			      byte regA = parseRegister_nibble((registerA!=null?registerA.getText():null));
			      byte regB = parseRegister_nibble((registerB!=null?registerB.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction22t(opcode, regA, regB, label_ref156));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format22t"



	// $ANTLR start "insn_format22x"
	// smaliTreeWalker.g:1030:1: insn_format22x : ^( I_STATEMENT_FORMAT22x INSTRUCTION_FORMAT22x registerA= REGISTER registerB= REGISTER ) ;
	public final void insn_format22x() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree INSTRUCTION_FORMAT22x157=null;

		try {
			// smaliTreeWalker.g:1031:3: ( ^( I_STATEMENT_FORMAT22x INSTRUCTION_FORMAT22x registerA= REGISTER registerB= REGISTER ) )
			// smaliTreeWalker.g:1032:5: ^( I_STATEMENT_FORMAT22x INSTRUCTION_FORMAT22x registerA= REGISTER registerB= REGISTER )
			{
			match(input,I_STATEMENT_FORMAT22x,FOLLOW_I_STATEMENT_FORMAT22x_in_insn_format22x2779); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT22x157=(CommonTree)match(input,INSTRUCTION_FORMAT22x,FOLLOW_INSTRUCTION_FORMAT22x_in_insn_format22x2781); 
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22x2785); 
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22x2789); 
			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT22x157!=null?INSTRUCTION_FORMAT22x157.getText():null));
			      short regA = parseRegister_byte((registerA!=null?registerA.getText():null));
			      int regB = parseRegister_short((registerB!=null?registerB.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction22x(opcode, regA, regB));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format22x"



	// $ANTLR start "insn_format23x"
	// smaliTreeWalker.g:1041:1: insn_format23x : ^( I_STATEMENT_FORMAT23x INSTRUCTION_FORMAT23x registerA= REGISTER registerB= REGISTER registerC= REGISTER ) ;
	public final void insn_format23x() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree registerC=null;
		CommonTree INSTRUCTION_FORMAT23x158=null;

		try {
			// smaliTreeWalker.g:1042:3: ( ^( I_STATEMENT_FORMAT23x INSTRUCTION_FORMAT23x registerA= REGISTER registerB= REGISTER registerC= REGISTER ) )
			// smaliTreeWalker.g:1043:5: ^( I_STATEMENT_FORMAT23x INSTRUCTION_FORMAT23x registerA= REGISTER registerB= REGISTER registerC= REGISTER )
			{
			match(input,I_STATEMENT_FORMAT23x,FOLLOW_I_STATEMENT_FORMAT23x_in_insn_format23x2812); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT23x158=(CommonTree)match(input,INSTRUCTION_FORMAT23x,FOLLOW_INSTRUCTION_FORMAT23x_in_insn_format23x2814); 
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format23x2818); 
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format23x2822); 
			registerC=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format23x2826); 
			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT23x158!=null?INSTRUCTION_FORMAT23x158.getText():null));
			      short regA = parseRegister_byte((registerA!=null?registerA.getText():null));
			      short regB = parseRegister_byte((registerB!=null?registerB.getText():null));
			      short regC = parseRegister_byte((registerC!=null?registerC.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction23x(opcode, regA, regB, regC));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format23x"



	// $ANTLR start "insn_format25x"
	// smaliTreeWalker.g:1053:1: insn_format25x : ^( I_STATEMENT_FORMAT25x INSTRUCTION_FORMAT25x REGISTER register_list4 ) ;
	public final void insn_format25x() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT25x159=null;
		CommonTree REGISTER160=null;
		TreeRuleReturnScope register_list4161 =null;

		try {
			// smaliTreeWalker.g:1054:3: ( ^( I_STATEMENT_FORMAT25x INSTRUCTION_FORMAT25x REGISTER register_list4 ) )
			// smaliTreeWalker.g:1055:5: ^( I_STATEMENT_FORMAT25x INSTRUCTION_FORMAT25x REGISTER register_list4 )
			{
			match(input,I_STATEMENT_FORMAT25x,FOLLOW_I_STATEMENT_FORMAT25x_in_insn_format25x2849); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT25x159=(CommonTree)match(input,INSTRUCTION_FORMAT25x,FOLLOW_INSTRUCTION_FORMAT25x_in_insn_format25x2851); 
			REGISTER160=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format25x2853); 
			pushFollow(FOLLOW_register_list4_in_insn_format25x2855);
			register_list4161=register_list4();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT25x159!=null?INSTRUCTION_FORMAT25x159.getText():null));

			      byte closureRegister = parseRegister_nibble((REGISTER160!=null?REGISTER160.getText():null));

			      //this depends on the fact that register_list4 returns a byte[4]
			      byte[] registers = (register_list4161!=null?((smaliTreeWalker.register_list4_return)register_list4161).registers:null);
			      int parameterRegisterCount = (register_list4161!=null?((smaliTreeWalker.register_list4_return)register_list4161).registerCount:0);  // don't count closure register

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction25x(opcode,
			            parameterRegisterCount, closureRegister, registers[0], registers[1],
			            registers[2], registers[3]));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format25x"



	// $ANTLR start "insn_format30t"
	// smaliTreeWalker.g:1070:1: insn_format30t : ^( I_STATEMENT_FORMAT30t INSTRUCTION_FORMAT30t label_ref ) ;
	public final void insn_format30t() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT30t162=null;
		Label label_ref163 =null;

		try {
			// smaliTreeWalker.g:1071:3: ( ^( I_STATEMENT_FORMAT30t INSTRUCTION_FORMAT30t label_ref ) )
			// smaliTreeWalker.g:1072:5: ^( I_STATEMENT_FORMAT30t INSTRUCTION_FORMAT30t label_ref )
			{
			match(input,I_STATEMENT_FORMAT30t,FOLLOW_I_STATEMENT_FORMAT30t_in_insn_format30t2878); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT30t162=(CommonTree)match(input,INSTRUCTION_FORMAT30t,FOLLOW_INSTRUCTION_FORMAT30t_in_insn_format30t2880); 
			pushFollow(FOLLOW_label_ref_in_insn_format30t2882);
			label_ref163=label_ref();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT30t162!=null?INSTRUCTION_FORMAT30t162.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction30t(opcode, label_ref163));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format30t"



	// $ANTLR start "insn_format31c"
	// smaliTreeWalker.g:1079:1: insn_format31c : ^( I_STATEMENT_FORMAT31c INSTRUCTION_FORMAT31c REGISTER string_literal ) ;
	public final void insn_format31c() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT31c164=null;
		CommonTree REGISTER165=null;
		String string_literal166 =null;

		try {
			// smaliTreeWalker.g:1080:3: ( ^( I_STATEMENT_FORMAT31c INSTRUCTION_FORMAT31c REGISTER string_literal ) )
			// smaliTreeWalker.g:1081:5: ^( I_STATEMENT_FORMAT31c INSTRUCTION_FORMAT31c REGISTER string_literal )
			{
			match(input,I_STATEMENT_FORMAT31c,FOLLOW_I_STATEMENT_FORMAT31c_in_insn_format31c2905); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT31c164=(CommonTree)match(input,INSTRUCTION_FORMAT31c,FOLLOW_INSTRUCTION_FORMAT31c_in_insn_format31c2907); 
			REGISTER165=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format31c2909); 
			pushFollow(FOLLOW_string_literal_in_insn_format31c2911);
			string_literal166=string_literal();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT31c164!=null?INSTRUCTION_FORMAT31c164.getText():null));
			      short regA = parseRegister_byte((REGISTER165!=null?REGISTER165.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction31c(opcode, regA,
			              dexBuilder.internStringReference(string_literal166)));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format31c"



	// $ANTLR start "insn_format31i"
	// smaliTreeWalker.g:1090:1: insn_format31i : ^( I_STATEMENT_FORMAT31i INSTRUCTION_FORMAT31i REGISTER fixed_32bit_literal ) ;
	public final void insn_format31i() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT31i167=null;
		CommonTree REGISTER168=null;
		int fixed_32bit_literal169 =0;

		try {
			// smaliTreeWalker.g:1091:3: ( ^( I_STATEMENT_FORMAT31i INSTRUCTION_FORMAT31i REGISTER fixed_32bit_literal ) )
			// smaliTreeWalker.g:1092:5: ^( I_STATEMENT_FORMAT31i INSTRUCTION_FORMAT31i REGISTER fixed_32bit_literal )
			{
			match(input,I_STATEMENT_FORMAT31i,FOLLOW_I_STATEMENT_FORMAT31i_in_insn_format31i2934); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT31i167=(CommonTree)match(input,INSTRUCTION_FORMAT31i,FOLLOW_INSTRUCTION_FORMAT31i_in_insn_format31i2936); 
			REGISTER168=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format31i2938); 
			pushFollow(FOLLOW_fixed_32bit_literal_in_insn_format31i2940);
			fixed_32bit_literal169=fixed_32bit_literal();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT31i167!=null?INSTRUCTION_FORMAT31i167.getText():null));
			      short regA = parseRegister_byte((REGISTER168!=null?REGISTER168.getText():null));

			      int litB = fixed_32bit_literal169;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction31i(opcode, regA, litB));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format31i"



	// $ANTLR start "insn_format31t"
	// smaliTreeWalker.g:1102:1: insn_format31t : ^( I_STATEMENT_FORMAT31t INSTRUCTION_FORMAT31t REGISTER label_ref ) ;
	public final void insn_format31t() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT31t170=null;
		CommonTree REGISTER171=null;
		Label label_ref172 =null;

		try {
			// smaliTreeWalker.g:1103:3: ( ^( I_STATEMENT_FORMAT31t INSTRUCTION_FORMAT31t REGISTER label_ref ) )
			// smaliTreeWalker.g:1104:5: ^( I_STATEMENT_FORMAT31t INSTRUCTION_FORMAT31t REGISTER label_ref )
			{
			match(input,I_STATEMENT_FORMAT31t,FOLLOW_I_STATEMENT_FORMAT31t_in_insn_format31t2963); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT31t170=(CommonTree)match(input,INSTRUCTION_FORMAT31t,FOLLOW_INSTRUCTION_FORMAT31t_in_insn_format31t2965); 
			REGISTER171=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format31t2967); 
			pushFollow(FOLLOW_label_ref_in_insn_format31t2969);
			label_ref172=label_ref();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT31t170!=null?INSTRUCTION_FORMAT31t170.getText():null));

			      short regA = parseRegister_byte((REGISTER171!=null?REGISTER171.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction31t(opcode, regA, label_ref172));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format31t"



	// $ANTLR start "insn_format32x"
	// smaliTreeWalker.g:1113:1: insn_format32x : ^( I_STATEMENT_FORMAT32x INSTRUCTION_FORMAT32x registerA= REGISTER registerB= REGISTER ) ;
	public final void insn_format32x() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree INSTRUCTION_FORMAT32x173=null;

		try {
			// smaliTreeWalker.g:1114:3: ( ^( I_STATEMENT_FORMAT32x INSTRUCTION_FORMAT32x registerA= REGISTER registerB= REGISTER ) )
			// smaliTreeWalker.g:1115:5: ^( I_STATEMENT_FORMAT32x INSTRUCTION_FORMAT32x registerA= REGISTER registerB= REGISTER )
			{
			match(input,I_STATEMENT_FORMAT32x,FOLLOW_I_STATEMENT_FORMAT32x_in_insn_format32x2992); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT32x173=(CommonTree)match(input,INSTRUCTION_FORMAT32x,FOLLOW_INSTRUCTION_FORMAT32x_in_insn_format32x2994); 
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format32x2998); 
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format32x3002); 
			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT32x173!=null?INSTRUCTION_FORMAT32x173.getText():null));
			      int regA = parseRegister_short((registerA!=null?registerA.getText():null));
			      int regB = parseRegister_short((registerB!=null?registerB.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction32x(opcode, regA, regB));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format32x"



	// $ANTLR start "insn_format35c_method"
	// smaliTreeWalker.g:1124:1: insn_format35c_method : ^( I_STATEMENT_FORMAT35c_METHOD INSTRUCTION_FORMAT35c_METHOD register_list method_reference ) ;
	public final void insn_format35c_method() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT35c_METHOD174=null;
		TreeRuleReturnScope register_list175 =null;
		ImmutableMethodReference method_reference176 =null;

		try {
			// smaliTreeWalker.g:1125:3: ( ^( I_STATEMENT_FORMAT35c_METHOD INSTRUCTION_FORMAT35c_METHOD register_list method_reference ) )
			// smaliTreeWalker.g:1126:5: ^( I_STATEMENT_FORMAT35c_METHOD INSTRUCTION_FORMAT35c_METHOD register_list method_reference )
			{
			match(input,I_STATEMENT_FORMAT35c_METHOD,FOLLOW_I_STATEMENT_FORMAT35c_METHOD_in_insn_format35c_method3025); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT35c_METHOD174=(CommonTree)match(input,INSTRUCTION_FORMAT35c_METHOD,FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_insn_format35c_method3027); 
			pushFollow(FOLLOW_register_list_in_insn_format35c_method3029);
			register_list175=register_list();
			state._fsp--;

			pushFollow(FOLLOW_method_reference_in_insn_format35c_method3031);
			method_reference176=method_reference();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT35c_METHOD174!=null?INSTRUCTION_FORMAT35c_METHOD174.getText():null));

			      //this depends on the fact that register_list returns a byte[5]
			      byte[] registers = (register_list175!=null?((smaliTreeWalker.register_list_return)register_list175).registers:null);
			      byte registerCount = (register_list175!=null?((smaliTreeWalker.register_list_return)register_list175).registerCount:0);

			      ImmutableMethodReference methodReference = method_reference176;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction35c(opcode, registerCount, registers[0], registers[1],
			              registers[2], registers[3], registers[4], dexBuilder.internMethodReference(methodReference)));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format35c_method"



	// $ANTLR start "insn_format35c_type"
	// smaliTreeWalker.g:1140:1: insn_format35c_type : ^( I_STATEMENT_FORMAT35c_TYPE INSTRUCTION_FORMAT35c_TYPE register_list nonvoid_type_descriptor ) ;
	public final void insn_format35c_type() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT35c_TYPE177=null;
		TreeRuleReturnScope register_list178 =null;
		TreeRuleReturnScope nonvoid_type_descriptor179 =null;

		try {
			// smaliTreeWalker.g:1141:3: ( ^( I_STATEMENT_FORMAT35c_TYPE INSTRUCTION_FORMAT35c_TYPE register_list nonvoid_type_descriptor ) )
			// smaliTreeWalker.g:1142:5: ^( I_STATEMENT_FORMAT35c_TYPE INSTRUCTION_FORMAT35c_TYPE register_list nonvoid_type_descriptor )
			{
			match(input,I_STATEMENT_FORMAT35c_TYPE,FOLLOW_I_STATEMENT_FORMAT35c_TYPE_in_insn_format35c_type3054); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT35c_TYPE177=(CommonTree)match(input,INSTRUCTION_FORMAT35c_TYPE,FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_insn_format35c_type3056); 
			pushFollow(FOLLOW_register_list_in_insn_format35c_type3058);
			register_list178=register_list();
			state._fsp--;

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format35c_type3060);
			nonvoid_type_descriptor179=nonvoid_type_descriptor();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT35c_TYPE177!=null?INSTRUCTION_FORMAT35c_TYPE177.getText():null));

			      //this depends on the fact that register_list returns a byte[5]
			      byte[] registers = (register_list178!=null?((smaliTreeWalker.register_list_return)register_list178).registers:null);
			      byte registerCount = (register_list178!=null?((smaliTreeWalker.register_list_return)register_list178).registerCount:0);

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction35c(opcode, registerCount, registers[0], registers[1],
			              registers[2], registers[3], registers[4], dexBuilder.internTypeReference((nonvoid_type_descriptor179!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor179).type:null))));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format35c_type"



	// $ANTLR start "insn_format3rc_method"
	// smaliTreeWalker.g:1154:1: insn_format3rc_method : ^( I_STATEMENT_FORMAT3rc_METHOD INSTRUCTION_FORMAT3rc_METHOD register_range method_reference ) ;
	public final void insn_format3rc_method() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT3rc_METHOD180=null;
		TreeRuleReturnScope register_range181 =null;
		ImmutableMethodReference method_reference182 =null;

		try {
			// smaliTreeWalker.g:1155:3: ( ^( I_STATEMENT_FORMAT3rc_METHOD INSTRUCTION_FORMAT3rc_METHOD register_range method_reference ) )
			// smaliTreeWalker.g:1156:5: ^( I_STATEMENT_FORMAT3rc_METHOD INSTRUCTION_FORMAT3rc_METHOD register_range method_reference )
			{
			match(input,I_STATEMENT_FORMAT3rc_METHOD,FOLLOW_I_STATEMENT_FORMAT3rc_METHOD_in_insn_format3rc_method3083); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT3rc_METHOD180=(CommonTree)match(input,INSTRUCTION_FORMAT3rc_METHOD,FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_in_insn_format3rc_method3085); 
			pushFollow(FOLLOW_register_range_in_insn_format3rc_method3087);
			register_range181=register_range();
			state._fsp--;

			pushFollow(FOLLOW_method_reference_in_insn_format3rc_method3089);
			method_reference182=method_reference();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT3rc_METHOD180!=null?INSTRUCTION_FORMAT3rc_METHOD180.getText():null));
			      int startRegister = (register_range181!=null?((smaliTreeWalker.register_range_return)register_range181).startRegister:0);
			      int endRegister = (register_range181!=null?((smaliTreeWalker.register_range_return)register_range181).endRegister:0);

			      int registerCount = endRegister-startRegister+1;

			      ImmutableMethodReference methodReference = method_reference182;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction3rc(opcode, startRegister, registerCount,
			              dexBuilder.internMethodReference(methodReference)));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format3rc_method"



	// $ANTLR start "insn_format3rc_type"
	// smaliTreeWalker.g:1170:1: insn_format3rc_type : ^( I_STATEMENT_FORMAT3rc_TYPE INSTRUCTION_FORMAT3rc_TYPE register_range nonvoid_type_descriptor ) ;
	public final void insn_format3rc_type() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT3rc_TYPE183=null;
		TreeRuleReturnScope register_range184 =null;
		TreeRuleReturnScope nonvoid_type_descriptor185 =null;

		try {
			// smaliTreeWalker.g:1171:3: ( ^( I_STATEMENT_FORMAT3rc_TYPE INSTRUCTION_FORMAT3rc_TYPE register_range nonvoid_type_descriptor ) )
			// smaliTreeWalker.g:1172:5: ^( I_STATEMENT_FORMAT3rc_TYPE INSTRUCTION_FORMAT3rc_TYPE register_range nonvoid_type_descriptor )
			{
			match(input,I_STATEMENT_FORMAT3rc_TYPE,FOLLOW_I_STATEMENT_FORMAT3rc_TYPE_in_insn_format3rc_type3112); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT3rc_TYPE183=(CommonTree)match(input,INSTRUCTION_FORMAT3rc_TYPE,FOLLOW_INSTRUCTION_FORMAT3rc_TYPE_in_insn_format3rc_type3114); 
			pushFollow(FOLLOW_register_range_in_insn_format3rc_type3116);
			register_range184=register_range();
			state._fsp--;

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format3rc_type3118);
			nonvoid_type_descriptor185=nonvoid_type_descriptor();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT3rc_TYPE183!=null?INSTRUCTION_FORMAT3rc_TYPE183.getText():null));
			      int startRegister = (register_range184!=null?((smaliTreeWalker.register_range_return)register_range184).startRegister:0);
			      int endRegister = (register_range184!=null?((smaliTreeWalker.register_range_return)register_range184).endRegister:0);

			      int registerCount = endRegister-startRegister+1;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction3rc(opcode, startRegister, registerCount,
			              dexBuilder.internTypeReference((nonvoid_type_descriptor185!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor185).type:null))));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format3rc_type"



	// $ANTLR start "insn_format51l_type"
	// smaliTreeWalker.g:1184:1: insn_format51l_type : ^( I_STATEMENT_FORMAT51l INSTRUCTION_FORMAT51l REGISTER fixed_64bit_literal ) ;
	public final void insn_format51l_type() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT51l186=null;
		CommonTree REGISTER187=null;
		long fixed_64bit_literal188 =0;

		try {
			// smaliTreeWalker.g:1185:3: ( ^( I_STATEMENT_FORMAT51l INSTRUCTION_FORMAT51l REGISTER fixed_64bit_literal ) )
			// smaliTreeWalker.g:1186:5: ^( I_STATEMENT_FORMAT51l INSTRUCTION_FORMAT51l REGISTER fixed_64bit_literal )
			{
			match(input,I_STATEMENT_FORMAT51l,FOLLOW_I_STATEMENT_FORMAT51l_in_insn_format51l_type3141); 
			match(input, Token.DOWN, null); 
			INSTRUCTION_FORMAT51l186=(CommonTree)match(input,INSTRUCTION_FORMAT51l,FOLLOW_INSTRUCTION_FORMAT51l_in_insn_format51l_type3143); 
			REGISTER187=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format51l_type3145); 
			pushFollow(FOLLOW_fixed_64bit_literal_in_insn_format51l_type3147);
			fixed_64bit_literal188=fixed_64bit_literal();
			state._fsp--;

			match(input, Token.UP, null); 


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT51l186!=null?INSTRUCTION_FORMAT51l186.getText():null));
			      short regA = parseRegister_byte((REGISTER187!=null?REGISTER187.getText():null));

			      long litB = fixed_64bit_literal188;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction51l(opcode, regA, litB));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format51l_type"



	// $ANTLR start "insn_array_data_directive"
	// smaliTreeWalker.g:1196:1: insn_array_data_directive : ^( I_STATEMENT_ARRAY_DATA ^( I_ARRAY_ELEMENT_SIZE short_integral_literal ) array_elements ) ;
	public final void insn_array_data_directive() throws RecognitionException {
		short short_integral_literal189 =0;
		List<Number> array_elements190 =null;

		try {
			// smaliTreeWalker.g:1197:3: ( ^( I_STATEMENT_ARRAY_DATA ^( I_ARRAY_ELEMENT_SIZE short_integral_literal ) array_elements ) )
			// smaliTreeWalker.g:1198:5: ^( I_STATEMENT_ARRAY_DATA ^( I_ARRAY_ELEMENT_SIZE short_integral_literal ) array_elements )
			{
			match(input,I_STATEMENT_ARRAY_DATA,FOLLOW_I_STATEMENT_ARRAY_DATA_in_insn_array_data_directive3170); 
			match(input, Token.DOWN, null); 
			match(input,I_ARRAY_ELEMENT_SIZE,FOLLOW_I_ARRAY_ELEMENT_SIZE_in_insn_array_data_directive3173); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_short_integral_literal_in_insn_array_data_directive3175);
			short_integral_literal189=short_integral_literal();
			state._fsp--;

			match(input, Token.UP, null); 

			pushFollow(FOLLOW_array_elements_in_insn_array_data_directive3178);
			array_elements190=array_elements();
			state._fsp--;

			match(input, Token.UP, null); 


			      int elementWidth = short_integral_literal189;
			      List<Number> elements = array_elements190;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderArrayPayload(elementWidth, array_elements190));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_array_data_directive"



	// $ANTLR start "insn_packed_switch_directive"
	// smaliTreeWalker.g:1206:1: insn_packed_switch_directive : ^( I_STATEMENT_PACKED_SWITCH ^( I_PACKED_SWITCH_START_KEY fixed_32bit_literal ) packed_switch_elements ) ;
	public final void insn_packed_switch_directive() throws RecognitionException {
		int fixed_32bit_literal191 =0;
		List<Label> packed_switch_elements192 =null;

		try {
			// smaliTreeWalker.g:1207:3: ( ^( I_STATEMENT_PACKED_SWITCH ^( I_PACKED_SWITCH_START_KEY fixed_32bit_literal ) packed_switch_elements ) )
			// smaliTreeWalker.g:1208:5: ^( I_STATEMENT_PACKED_SWITCH ^( I_PACKED_SWITCH_START_KEY fixed_32bit_literal ) packed_switch_elements )
			{
			match(input,I_STATEMENT_PACKED_SWITCH,FOLLOW_I_STATEMENT_PACKED_SWITCH_in_insn_packed_switch_directive3200); 
			match(input, Token.DOWN, null); 
			match(input,I_PACKED_SWITCH_START_KEY,FOLLOW_I_PACKED_SWITCH_START_KEY_in_insn_packed_switch_directive3203); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_fixed_32bit_literal_in_insn_packed_switch_directive3205);
			fixed_32bit_literal191=fixed_32bit_literal();
			state._fsp--;

			match(input, Token.UP, null); 

			pushFollow(FOLLOW_packed_switch_elements_in_insn_packed_switch_directive3208);
			packed_switch_elements192=packed_switch_elements();
			state._fsp--;

			match(input, Token.UP, null); 


			        int startKey = fixed_32bit_literal191;
			        method_stack.peek().methodBuilder.addInstruction(new BuilderPackedSwitchPayload(startKey,
			            packed_switch_elements192));
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_packed_switch_directive"



	// $ANTLR start "insn_sparse_switch_directive"
	// smaliTreeWalker.g:1215:1: insn_sparse_switch_directive : ^( I_STATEMENT_SPARSE_SWITCH sparse_switch_elements ) ;
	public final void insn_sparse_switch_directive() throws RecognitionException {
		List<SwitchLabelElement> sparse_switch_elements193 =null;

		try {
			// smaliTreeWalker.g:1216:3: ( ^( I_STATEMENT_SPARSE_SWITCH sparse_switch_elements ) )
			// smaliTreeWalker.g:1217:5: ^( I_STATEMENT_SPARSE_SWITCH sparse_switch_elements )
			{
			match(input,I_STATEMENT_SPARSE_SWITCH,FOLLOW_I_STATEMENT_SPARSE_SWITCH_in_insn_sparse_switch_directive3232); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_sparse_switch_elements_in_insn_sparse_switch_directive3234);
			sparse_switch_elements193=sparse_switch_elements();
			state._fsp--;

			match(input, Token.UP, null); 


			      method_stack.peek().methodBuilder.addInstruction(new BuilderSparseSwitchPayload(sparse_switch_elements193));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_sparse_switch_directive"



	// $ANTLR start "array_descriptor"
	// smaliTreeWalker.g:1222:1: array_descriptor returns [String type] : ARRAY_TYPE_PREFIX ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR ) ;
	public final String array_descriptor() throws RecognitionException {
		String type = null;


		CommonTree ARRAY_TYPE_PREFIX194=null;
		CommonTree PRIMITIVE_TYPE195=null;
		CommonTree CLASS_DESCRIPTOR196=null;

		try {
			// smaliTreeWalker.g:1223:3: ( ARRAY_TYPE_PREFIX ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR ) )
			// smaliTreeWalker.g:1223:5: ARRAY_TYPE_PREFIX ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR )
			{
			ARRAY_TYPE_PREFIX194=(CommonTree)match(input,ARRAY_TYPE_PREFIX,FOLLOW_ARRAY_TYPE_PREFIX_in_array_descriptor3255); 
			// smaliTreeWalker.g:1223:23: ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR )
			int alt38=2;
			int LA38_0 = input.LA(1);
			if ( (LA38_0==PRIMITIVE_TYPE) ) {
				alt38=1;
			}
			else if ( (LA38_0==CLASS_DESCRIPTOR) ) {
				alt38=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 38, 0, input);
				throw nvae;
			}

			switch (alt38) {
				case 1 :
					// smaliTreeWalker.g:1223:25: PRIMITIVE_TYPE
					{
					PRIMITIVE_TYPE195=(CommonTree)match(input,PRIMITIVE_TYPE,FOLLOW_PRIMITIVE_TYPE_in_array_descriptor3259); 
					 type = (ARRAY_TYPE_PREFIX194!=null?ARRAY_TYPE_PREFIX194.getText():null) + (PRIMITIVE_TYPE195!=null?PRIMITIVE_TYPE195.getText():null); 
					}
					break;
				case 2 :
					// smaliTreeWalker.g:1224:25: CLASS_DESCRIPTOR
					{
					CLASS_DESCRIPTOR196=(CommonTree)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_array_descriptor3287); 
					 type = (ARRAY_TYPE_PREFIX194!=null?ARRAY_TYPE_PREFIX194.getText():null) + (CLASS_DESCRIPTOR196!=null?CLASS_DESCRIPTOR196.getText():null); 
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return type;
	}
	// $ANTLR end "array_descriptor"


	public static class nonvoid_type_descriptor_return extends TreeRuleReturnScope {
		public String type;
	};


	// $ANTLR start "nonvoid_type_descriptor"
	// smaliTreeWalker.g:1226:1: nonvoid_type_descriptor returns [String type] : ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR | array_descriptor ) ;
	public final smaliTreeWalker.nonvoid_type_descriptor_return nonvoid_type_descriptor() throws RecognitionException {
		smaliTreeWalker.nonvoid_type_descriptor_return retval = new smaliTreeWalker.nonvoid_type_descriptor_return();
		retval.start = input.LT(1);

		String array_descriptor197 =null;

		try {
			// smaliTreeWalker.g:1227:3: ( ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR | array_descriptor ) )
			// smaliTreeWalker.g:1227:5: ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR | array_descriptor )
			{
			// smaliTreeWalker.g:1227:5: ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR | array_descriptor )
			int alt39=3;
			switch ( input.LA(1) ) {
			case PRIMITIVE_TYPE:
				{
				alt39=1;
				}
				break;
			case CLASS_DESCRIPTOR:
				{
				alt39=2;
				}
				break;
			case ARRAY_TYPE_PREFIX:
				{
				alt39=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 39, 0, input);
				throw nvae;
			}
			switch (alt39) {
				case 1 :
					// smaliTreeWalker.g:1227:6: PRIMITIVE_TYPE
					{
					match(input,PRIMITIVE_TYPE,FOLLOW_PRIMITIVE_TYPE_in_nonvoid_type_descriptor3305); 
					 retval.type = input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(retval.start),input.getTreeAdaptor().getTokenStopIndex(retval.start)); 
					}
					break;
				case 2 :
					// smaliTreeWalker.g:1228:5: CLASS_DESCRIPTOR
					{
					match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_nonvoid_type_descriptor3313); 
					 retval.type = input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(retval.start),input.getTreeAdaptor().getTokenStopIndex(retval.start)); 
					}
					break;
				case 3 :
					// smaliTreeWalker.g:1229:5: array_descriptor
					{
					pushFollow(FOLLOW_array_descriptor_in_nonvoid_type_descriptor3321);
					array_descriptor197=array_descriptor();
					state._fsp--;

					 retval.type = array_descriptor197; 
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "nonvoid_type_descriptor"


	public static class reference_type_descriptor_return extends TreeRuleReturnScope {
		public String type;
	};


	// $ANTLR start "reference_type_descriptor"
	// smaliTreeWalker.g:1232:1: reference_type_descriptor returns [String type] : ( CLASS_DESCRIPTOR | array_descriptor ) ;
	public final smaliTreeWalker.reference_type_descriptor_return reference_type_descriptor() throws RecognitionException {
		smaliTreeWalker.reference_type_descriptor_return retval = new smaliTreeWalker.reference_type_descriptor_return();
		retval.start = input.LT(1);

		String array_descriptor198 =null;

		try {
			// smaliTreeWalker.g:1233:3: ( ( CLASS_DESCRIPTOR | array_descriptor ) )
			// smaliTreeWalker.g:1233:5: ( CLASS_DESCRIPTOR | array_descriptor )
			{
			// smaliTreeWalker.g:1233:5: ( CLASS_DESCRIPTOR | array_descriptor )
			int alt40=2;
			int LA40_0 = input.LA(1);
			if ( (LA40_0==CLASS_DESCRIPTOR) ) {
				alt40=1;
			}
			else if ( (LA40_0==ARRAY_TYPE_PREFIX) ) {
				alt40=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 40, 0, input);
				throw nvae;
			}

			switch (alt40) {
				case 1 :
					// smaliTreeWalker.g:1233:6: CLASS_DESCRIPTOR
					{
					match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_reference_type_descriptor3342); 
					 retval.type = input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(retval.start),input.getTreeAdaptor().getTokenStopIndex(retval.start)); 
					}
					break;
				case 2 :
					// smaliTreeWalker.g:1234:5: array_descriptor
					{
					pushFollow(FOLLOW_array_descriptor_in_reference_type_descriptor3350);
					array_descriptor198=array_descriptor();
					state._fsp--;

					 retval.type = array_descriptor198; 
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "reference_type_descriptor"



	// $ANTLR start "type_descriptor"
	// smaliTreeWalker.g:1237:1: type_descriptor returns [String type] : ( VOID_TYPE | nonvoid_type_descriptor );
	public final String type_descriptor() throws RecognitionException {
		String type = null;


		TreeRuleReturnScope nonvoid_type_descriptor199 =null;

		try {
			// smaliTreeWalker.g:1238:3: ( VOID_TYPE | nonvoid_type_descriptor )
			int alt41=2;
			int LA41_0 = input.LA(1);
			if ( (LA41_0==VOID_TYPE) ) {
				alt41=1;
			}
			else if ( (LA41_0==ARRAY_TYPE_PREFIX||LA41_0==CLASS_DESCRIPTOR||LA41_0==PRIMITIVE_TYPE) ) {
				alt41=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 41, 0, input);
				throw nvae;
			}

			switch (alt41) {
				case 1 :
					// smaliTreeWalker.g:1238:5: VOID_TYPE
					{
					match(input,VOID_TYPE,FOLLOW_VOID_TYPE_in_type_descriptor3370); 
					type = "V";
					}
					break;
				case 2 :
					// smaliTreeWalker.g:1239:5: nonvoid_type_descriptor
					{
					pushFollow(FOLLOW_nonvoid_type_descriptor_in_type_descriptor3378);
					nonvoid_type_descriptor199=nonvoid_type_descriptor();
					state._fsp--;

					type = (nonvoid_type_descriptor199!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor199).type:null);
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return type;
	}
	// $ANTLR end "type_descriptor"



	// $ANTLR start "short_integral_literal"
	// smaliTreeWalker.g:1242:1: short_integral_literal returns [short value] : ( long_literal | integer_literal | short_literal | char_literal | byte_literal );
	public final short short_integral_literal() throws RecognitionException {
		short value = 0;


		long long_literal200 =0;
		int integer_literal201 =0;
		short short_literal202 =0;
		char char_literal203 =0;
		byte byte_literal204 =0;

		try {
			// smaliTreeWalker.g:1243:3: ( long_literal | integer_literal | short_literal | char_literal | byte_literal )
			int alt42=5;
			switch ( input.LA(1) ) {
			case LONG_LITERAL:
				{
				alt42=1;
				}
				break;
			case INTEGER_LITERAL:
				{
				alt42=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt42=3;
				}
				break;
			case CHAR_LITERAL:
				{
				alt42=4;
				}
				break;
			case BYTE_LITERAL:
				{
				alt42=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 42, 0, input);
				throw nvae;
			}
			switch (alt42) {
				case 1 :
					// smaliTreeWalker.g:1243:5: long_literal
					{
					pushFollow(FOLLOW_long_literal_in_short_integral_literal3396);
					long_literal200=long_literal();
					state._fsp--;


					      LiteralTools.checkShort(long_literal200);
					      value = (short)long_literal200;
					    
					}
					break;
				case 2 :
					// smaliTreeWalker.g:1248:5: integer_literal
					{
					pushFollow(FOLLOW_integer_literal_in_short_integral_literal3408);
					integer_literal201=integer_literal();
					state._fsp--;


					      LiteralTools.checkShort(integer_literal201);
					      value = (short)integer_literal201;
					    
					}
					break;
				case 3 :
					// smaliTreeWalker.g:1253:5: short_literal
					{
					pushFollow(FOLLOW_short_literal_in_short_integral_literal3420);
					short_literal202=short_literal();
					state._fsp--;

					value = short_literal202;
					}
					break;
				case 4 :
					// smaliTreeWalker.g:1254:5: char_literal
					{
					pushFollow(FOLLOW_char_literal_in_short_integral_literal3428);
					char_literal203=char_literal();
					state._fsp--;

					value = (short)char_literal203;
					}
					break;
				case 5 :
					// smaliTreeWalker.g:1255:5: byte_literal
					{
					pushFollow(FOLLOW_byte_literal_in_short_integral_literal3436);
					byte_literal204=byte_literal();
					state._fsp--;

					value = byte_literal204;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "short_integral_literal"



	// $ANTLR start "integral_literal"
	// smaliTreeWalker.g:1257:1: integral_literal returns [int value] : ( long_literal | integer_literal | short_literal | byte_literal );
	public final int integral_literal() throws RecognitionException {
		int value = 0;


		long long_literal205 =0;
		int integer_literal206 =0;
		short short_literal207 =0;
		byte byte_literal208 =0;

		try {
			// smaliTreeWalker.g:1258:3: ( long_literal | integer_literal | short_literal | byte_literal )
			int alt43=4;
			switch ( input.LA(1) ) {
			case LONG_LITERAL:
				{
				alt43=1;
				}
				break;
			case INTEGER_LITERAL:
				{
				alt43=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt43=3;
				}
				break;
			case BYTE_LITERAL:
				{
				alt43=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 43, 0, input);
				throw nvae;
			}
			switch (alt43) {
				case 1 :
					// smaliTreeWalker.g:1258:5: long_literal
					{
					pushFollow(FOLLOW_long_literal_in_integral_literal3451);
					long_literal205=long_literal();
					state._fsp--;


					      LiteralTools.checkInt(long_literal205);
					      value = (int)long_literal205;
					    
					}
					break;
				case 2 :
					// smaliTreeWalker.g:1263:5: integer_literal
					{
					pushFollow(FOLLOW_integer_literal_in_integral_literal3463);
					integer_literal206=integer_literal();
					state._fsp--;

					value = integer_literal206;
					}
					break;
				case 3 :
					// smaliTreeWalker.g:1264:5: short_literal
					{
					pushFollow(FOLLOW_short_literal_in_integral_literal3471);
					short_literal207=short_literal();
					state._fsp--;

					value = short_literal207;
					}
					break;
				case 4 :
					// smaliTreeWalker.g:1265:5: byte_literal
					{
					pushFollow(FOLLOW_byte_literal_in_integral_literal3479);
					byte_literal208=byte_literal();
					state._fsp--;

					value = byte_literal208;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "integral_literal"



	// $ANTLR start "integer_literal"
	// smaliTreeWalker.g:1268:1: integer_literal returns [int value] : INTEGER_LITERAL ;
	public final int integer_literal() throws RecognitionException {
		int value = 0;


		CommonTree INTEGER_LITERAL209=null;

		try {
			// smaliTreeWalker.g:1269:3: ( INTEGER_LITERAL )
			// smaliTreeWalker.g:1269:5: INTEGER_LITERAL
			{
			INTEGER_LITERAL209=(CommonTree)match(input,INTEGER_LITERAL,FOLLOW_INTEGER_LITERAL_in_integer_literal3495); 
			 value = LiteralTools.parseInt((INTEGER_LITERAL209!=null?INTEGER_LITERAL209.getText():null)); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "integer_literal"



	// $ANTLR start "long_literal"
	// smaliTreeWalker.g:1271:1: long_literal returns [long value] : LONG_LITERAL ;
	public final long long_literal() throws RecognitionException {
		long value = 0;


		CommonTree LONG_LITERAL210=null;

		try {
			// smaliTreeWalker.g:1272:3: ( LONG_LITERAL )
			// smaliTreeWalker.g:1272:5: LONG_LITERAL
			{
			LONG_LITERAL210=(CommonTree)match(input,LONG_LITERAL,FOLLOW_LONG_LITERAL_in_long_literal3510); 
			 value = LiteralTools.parseLong((LONG_LITERAL210!=null?LONG_LITERAL210.getText():null)); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "long_literal"



	// $ANTLR start "short_literal"
	// smaliTreeWalker.g:1274:1: short_literal returns [short value] : SHORT_LITERAL ;
	public final short short_literal() throws RecognitionException {
		short value = 0;


		CommonTree SHORT_LITERAL211=null;

		try {
			// smaliTreeWalker.g:1275:3: ( SHORT_LITERAL )
			// smaliTreeWalker.g:1275:5: SHORT_LITERAL
			{
			SHORT_LITERAL211=(CommonTree)match(input,SHORT_LITERAL,FOLLOW_SHORT_LITERAL_in_short_literal3525); 
			 value = LiteralTools.parseShort((SHORT_LITERAL211!=null?SHORT_LITERAL211.getText():null)); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "short_literal"



	// $ANTLR start "byte_literal"
	// smaliTreeWalker.g:1277:1: byte_literal returns [byte value] : BYTE_LITERAL ;
	public final byte byte_literal() throws RecognitionException {
		byte value = 0;


		CommonTree BYTE_LITERAL212=null;

		try {
			// smaliTreeWalker.g:1278:3: ( BYTE_LITERAL )
			// smaliTreeWalker.g:1278:5: BYTE_LITERAL
			{
			BYTE_LITERAL212=(CommonTree)match(input,BYTE_LITERAL,FOLLOW_BYTE_LITERAL_in_byte_literal3540); 
			 value = LiteralTools.parseByte((BYTE_LITERAL212!=null?BYTE_LITERAL212.getText():null)); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "byte_literal"



	// $ANTLR start "float_literal"
	// smaliTreeWalker.g:1280:1: float_literal returns [float value] : FLOAT_LITERAL ;
	public final float float_literal() throws RecognitionException {
		float value = 0.0f;


		CommonTree FLOAT_LITERAL213=null;

		try {
			// smaliTreeWalker.g:1281:3: ( FLOAT_LITERAL )
			// smaliTreeWalker.g:1281:5: FLOAT_LITERAL
			{
			FLOAT_LITERAL213=(CommonTree)match(input,FLOAT_LITERAL,FOLLOW_FLOAT_LITERAL_in_float_literal3555); 
			 value = LiteralTools.parseFloat((FLOAT_LITERAL213!=null?FLOAT_LITERAL213.getText():null)); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "float_literal"



	// $ANTLR start "double_literal"
	// smaliTreeWalker.g:1283:1: double_literal returns [double value] : DOUBLE_LITERAL ;
	public final double double_literal() throws RecognitionException {
		double value = 0.0;


		CommonTree DOUBLE_LITERAL214=null;

		try {
			// smaliTreeWalker.g:1284:3: ( DOUBLE_LITERAL )
			// smaliTreeWalker.g:1284:5: DOUBLE_LITERAL
			{
			DOUBLE_LITERAL214=(CommonTree)match(input,DOUBLE_LITERAL,FOLLOW_DOUBLE_LITERAL_in_double_literal3570); 
			 value = LiteralTools.parseDouble((DOUBLE_LITERAL214!=null?DOUBLE_LITERAL214.getText():null)); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "double_literal"



	// $ANTLR start "char_literal"
	// smaliTreeWalker.g:1286:1: char_literal returns [char value] : CHAR_LITERAL ;
	public final char char_literal() throws RecognitionException {
		char value = 0;


		CommonTree CHAR_LITERAL215=null;

		try {
			// smaliTreeWalker.g:1287:3: ( CHAR_LITERAL )
			// smaliTreeWalker.g:1287:5: CHAR_LITERAL
			{
			CHAR_LITERAL215=(CommonTree)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_char_literal3585); 
			 value = (CHAR_LITERAL215!=null?CHAR_LITERAL215.getText():null).charAt(1); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "char_literal"



	// $ANTLR start "string_literal"
	// smaliTreeWalker.g:1289:1: string_literal returns [String value] : STRING_LITERAL ;
	public final String string_literal() throws RecognitionException {
		String value = null;


		CommonTree STRING_LITERAL216=null;

		try {
			// smaliTreeWalker.g:1290:3: ( STRING_LITERAL )
			// smaliTreeWalker.g:1290:5: STRING_LITERAL
			{
			STRING_LITERAL216=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_string_literal3600); 

			      value = (STRING_LITERAL216!=null?STRING_LITERAL216.getText():null);
			      value = value.substring(1,value.length()-1);
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "string_literal"



	// $ANTLR start "bool_literal"
	// smaliTreeWalker.g:1296:1: bool_literal returns [boolean value] : BOOL_LITERAL ;
	public final boolean bool_literal() throws RecognitionException {
		boolean value = false;


		CommonTree BOOL_LITERAL217=null;

		try {
			// smaliTreeWalker.g:1297:3: ( BOOL_LITERAL )
			// smaliTreeWalker.g:1297:5: BOOL_LITERAL
			{
			BOOL_LITERAL217=(CommonTree)match(input,BOOL_LITERAL,FOLLOW_BOOL_LITERAL_in_bool_literal3619); 
			 value = Boolean.parseBoolean((BOOL_LITERAL217!=null?BOOL_LITERAL217.getText():null)); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "bool_literal"



	// $ANTLR start "array_literal"
	// smaliTreeWalker.g:1299:1: array_literal returns [List<EncodedValue> elements] : ^( I_ENCODED_ARRAY ( literal )* ) ;
	public final List<EncodedValue> array_literal() throws RecognitionException {
		List<EncodedValue> elements = null;


		EncodedValue literal218 =null;

		try {
			// smaliTreeWalker.g:1300:3: ( ^( I_ENCODED_ARRAY ( literal )* ) )
			// smaliTreeWalker.g:1300:5: ^( I_ENCODED_ARRAY ( literal )* )
			{
			elements = Lists.newArrayList();
			match(input,I_ENCODED_ARRAY,FOLLOW_I_ENCODED_ARRAY_in_array_literal3641); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// smaliTreeWalker.g:1301:23: ( literal )*
				loop44:
				while (true) {
					int alt44=2;
					int LA44_0 = input.LA(1);
					if ( (LA44_0==ARRAY_TYPE_PREFIX||(LA44_0 >= BOOL_LITERAL && LA44_0 <= BYTE_LITERAL)||(LA44_0 >= CHAR_LITERAL && LA44_0 <= CLASS_DESCRIPTOR)||LA44_0==DOUBLE_LITERAL||LA44_0==FLOAT_LITERAL||LA44_0==INTEGER_LITERAL||(LA44_0 >= I_ENCODED_ARRAY && LA44_0 <= I_ENCODED_METHOD)||LA44_0==I_SUBANNOTATION||LA44_0==LONG_LITERAL||LA44_0==NULL_LITERAL||LA44_0==PRIMITIVE_TYPE||LA44_0==SHORT_LITERAL||LA44_0==STRING_LITERAL||LA44_0==VOID_TYPE) ) {
						alt44=1;
					}

					switch (alt44) {
					case 1 :
						// smaliTreeWalker.g:1301:24: literal
						{
						pushFollow(FOLLOW_literal_in_array_literal3644);
						literal218=literal();
						state._fsp--;

						elements.add(literal218);
						}
						break;

					default :
						break loop44;
					}
				}

				match(input, Token.UP, null); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return elements;
	}
	// $ANTLR end "array_literal"



	// $ANTLR start "annotations"
	// smaliTreeWalker.g:1303:1: annotations returns [Set<Annotation> annotations] : ^( I_ANNOTATIONS ( annotation )* ) ;
	public final Set<Annotation> annotations() throws RecognitionException {
		Set<Annotation> annotations = null;


		Annotation annotation219 =null;

		try {
			// smaliTreeWalker.g:1304:3: ( ^( I_ANNOTATIONS ( annotation )* ) )
			// smaliTreeWalker.g:1304:5: ^( I_ANNOTATIONS ( annotation )* )
			{
			HashMap<String, Annotation> annotationMap = Maps.newHashMap();
			match(input,I_ANNOTATIONS,FOLLOW_I_ANNOTATIONS_in_annotations3669); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// smaliTreeWalker.g:1305:21: ( annotation )*
				loop45:
				while (true) {
					int alt45=2;
					int LA45_0 = input.LA(1);
					if ( (LA45_0==I_ANNOTATION) ) {
						alt45=1;
					}

					switch (alt45) {
					case 1 :
						// smaliTreeWalker.g:1305:22: annotation
						{
						pushFollow(FOLLOW_annotation_in_annotations3672);
						annotation219=annotation();
						state._fsp--;


						        Annotation anno = annotation219;
						        Annotation old = annotationMap.put(anno.getType(), anno);
						        if (old != null) {
						            throw new SemanticException(input, "Multiple annotations of type %s", anno.getType());
						        }
						    
						}
						break;

					default :
						break loop45;
					}
				}

				match(input, Token.UP, null); 
			}


			      if (annotationMap.size() > 0) {
			        annotations = ImmutableSet.copyOf(annotationMap.values());
			      }
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return annotations;
	}
	// $ANTLR end "annotations"



	// $ANTLR start "annotation"
	// smaliTreeWalker.g:1319:1: annotation returns [Annotation annotation] : ^( I_ANNOTATION ANNOTATION_VISIBILITY subannotation ) ;
	public final Annotation annotation() throws RecognitionException {
		Annotation annotation = null;


		CommonTree ANNOTATION_VISIBILITY220=null;
		TreeRuleReturnScope subannotation221 =null;

		try {
			// smaliTreeWalker.g:1320:3: ( ^( I_ANNOTATION ANNOTATION_VISIBILITY subannotation ) )
			// smaliTreeWalker.g:1320:5: ^( I_ANNOTATION ANNOTATION_VISIBILITY subannotation )
			{
			match(input,I_ANNOTATION,FOLLOW_I_ANNOTATION_in_annotation3701); 
			match(input, Token.DOWN, null); 
			ANNOTATION_VISIBILITY220=(CommonTree)match(input,ANNOTATION_VISIBILITY,FOLLOW_ANNOTATION_VISIBILITY_in_annotation3703); 
			pushFollow(FOLLOW_subannotation_in_annotation3705);
			subannotation221=subannotation();
			state._fsp--;

			match(input, Token.UP, null); 


			      int visibility = AnnotationVisibility.getVisibility((ANNOTATION_VISIBILITY220!=null?ANNOTATION_VISIBILITY220.getText():null));
			      annotation = new ImmutableAnnotation(visibility, (subannotation221!=null?((smaliTreeWalker.subannotation_return)subannotation221).annotationType:null), (subannotation221!=null?((smaliTreeWalker.subannotation_return)subannotation221).elements:null));
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return annotation;
	}
	// $ANTLR end "annotation"



	// $ANTLR start "annotation_element"
	// smaliTreeWalker.g:1326:1: annotation_element returns [AnnotationElement element] : ^( I_ANNOTATION_ELEMENT SIMPLE_NAME literal ) ;
	public final AnnotationElement annotation_element() throws RecognitionException {
		AnnotationElement element = null;


		CommonTree SIMPLE_NAME222=null;
		EncodedValue literal223 =null;

		try {
			// smaliTreeWalker.g:1327:3: ( ^( I_ANNOTATION_ELEMENT SIMPLE_NAME literal ) )
			// smaliTreeWalker.g:1327:5: ^( I_ANNOTATION_ELEMENT SIMPLE_NAME literal )
			{
			match(input,I_ANNOTATION_ELEMENT,FOLLOW_I_ANNOTATION_ELEMENT_in_annotation_element3726); 
			match(input, Token.DOWN, null); 
			SIMPLE_NAME222=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_annotation_element3728); 
			pushFollow(FOLLOW_literal_in_annotation_element3730);
			literal223=literal();
			state._fsp--;

			match(input, Token.UP, null); 


			      element = new ImmutableAnnotationElement((SIMPLE_NAME222!=null?SIMPLE_NAME222.getText():null), literal223);
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return element;
	}
	// $ANTLR end "annotation_element"


	public static class subannotation_return extends TreeRuleReturnScope {
		public String annotationType;
		public List<AnnotationElement> elements;
	};


	// $ANTLR start "subannotation"
	// smaliTreeWalker.g:1332:1: subannotation returns [String annotationType, List<AnnotationElement> elements] : ^( I_SUBANNOTATION CLASS_DESCRIPTOR ( annotation_element )* ) ;
	public final smaliTreeWalker.subannotation_return subannotation() throws RecognitionException {
		smaliTreeWalker.subannotation_return retval = new smaliTreeWalker.subannotation_return();
		retval.start = input.LT(1);

		CommonTree CLASS_DESCRIPTOR225=null;
		AnnotationElement annotation_element224 =null;

		try {
			// smaliTreeWalker.g:1333:3: ( ^( I_SUBANNOTATION CLASS_DESCRIPTOR ( annotation_element )* ) )
			// smaliTreeWalker.g:1333:5: ^( I_SUBANNOTATION CLASS_DESCRIPTOR ( annotation_element )* )
			{
			ArrayList<AnnotationElement> elements = Lists.newArrayList();
			match(input,I_SUBANNOTATION,FOLLOW_I_SUBANNOTATION_in_subannotation3757); 
			match(input, Token.DOWN, null); 
			CLASS_DESCRIPTOR225=(CommonTree)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_subannotation3767); 
			// smaliTreeWalker.g:1336:9: ( annotation_element )*
			loop46:
			while (true) {
				int alt46=2;
				int LA46_0 = input.LA(1);
				if ( (LA46_0==I_ANNOTATION_ELEMENT) ) {
					alt46=1;
				}

				switch (alt46) {
				case 1 :
					// smaliTreeWalker.g:1336:10: annotation_element
					{
					pushFollow(FOLLOW_annotation_element_in_subannotation3778);
					annotation_element224=annotation_element();
					state._fsp--;


					           elements.add(annotation_element224);
					        
					}
					break;

				default :
					break loop46;
				}
			}

			match(input, Token.UP, null); 


			      retval.annotationType = (CLASS_DESCRIPTOR225!=null?CLASS_DESCRIPTOR225.getText():null);
			      retval.elements = elements;
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "subannotation"



	// $ANTLR start "field_literal"
	// smaliTreeWalker.g:1346:1: field_literal returns [FieldReference value] : ^( I_ENCODED_FIELD field_reference ) ;
	public final FieldReference field_literal() throws RecognitionException {
		FieldReference value = null;


		ImmutableFieldReference field_reference226 =null;

		try {
			// smaliTreeWalker.g:1347:3: ( ^( I_ENCODED_FIELD field_reference ) )
			// smaliTreeWalker.g:1347:5: ^( I_ENCODED_FIELD field_reference )
			{
			match(input,I_ENCODED_FIELD,FOLLOW_I_ENCODED_FIELD_in_field_literal3817); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_field_reference_in_field_literal3819);
			field_reference226=field_reference();
			state._fsp--;

			match(input, Token.UP, null); 


			      value = field_reference226;
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "field_literal"



	// $ANTLR start "method_literal"
	// smaliTreeWalker.g:1352:1: method_literal returns [MethodReference value] : ^( I_ENCODED_METHOD method_reference ) ;
	public final MethodReference method_literal() throws RecognitionException {
		MethodReference value = null;


		ImmutableMethodReference method_reference227 =null;

		try {
			// smaliTreeWalker.g:1353:3: ( ^( I_ENCODED_METHOD method_reference ) )
			// smaliTreeWalker.g:1353:5: ^( I_ENCODED_METHOD method_reference )
			{
			match(input,I_ENCODED_METHOD,FOLLOW_I_ENCODED_METHOD_in_method_literal3840); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_method_reference_in_method_literal3842);
			method_reference227=method_reference();
			state._fsp--;

			match(input, Token.UP, null); 


			      value = method_reference227;
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "method_literal"



	// $ANTLR start "enum_literal"
	// smaliTreeWalker.g:1358:1: enum_literal returns [FieldReference value] : ^( I_ENCODED_ENUM field_reference ) ;
	public final FieldReference enum_literal() throws RecognitionException {
		FieldReference value = null;


		ImmutableFieldReference field_reference228 =null;

		try {
			// smaliTreeWalker.g:1359:3: ( ^( I_ENCODED_ENUM field_reference ) )
			// smaliTreeWalker.g:1359:5: ^( I_ENCODED_ENUM field_reference )
			{
			match(input,I_ENCODED_ENUM,FOLLOW_I_ENCODED_ENUM_in_enum_literal3863); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_field_reference_in_enum_literal3865);
			field_reference228=field_reference();
			state._fsp--;

			match(input, Token.UP, null); 


			      value = field_reference228;
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "enum_literal"

	// Delegated rules



	public static final BitSet FOLLOW_I_CLASS_DEF_in_smali_file52 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_header_in_smali_file54 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
	public static final BitSet FOLLOW_methods_in_smali_file56 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_fields_in_smali_file58 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_annotations_in_smali_file60 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_class_spec_in_header85 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L,0x0000200000000010L});
	public static final BitSet FOLLOW_super_spec_in_header87 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_implements_list_in_header90 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_source_spec_in_header92 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_class_spec110 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_access_list_in_class_spec112 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_SUPER_in_super_spec130 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_super_spec132 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_IMPLEMENTS_in_implements_spec152 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_implements_spec154 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_implements_spec_in_implements_list184 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
	public static final BitSet FOLLOW_I_SOURCE_in_source_spec213 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_string_literal_in_source_spec215 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_ACCESS_LIST_in_access_list248 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ACCESS_SPEC_in_access_list266 = new BitSet(new long[]{0x0000000000000018L});
	public static final BitSet FOLLOW_I_FIELDS_in_fields308 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_field_in_fields317 = new BitSet(new long[]{0x0000000000000008L,0x0000100000000000L});
	public static final BitSet FOLLOW_I_METHODS_in_methods349 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_method_in_methods358 = new BitSet(new long[]{0x0000000000000008L,0x0020000000000000L});
	public static final BitSet FOLLOW_I_FIELD_in_field383 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_field385 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_access_list_in_field387 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_I_FIELD_TYPE_in_field390 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_field392 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_field_initial_value_in_field395 = new BitSet(new long[]{0x0000000000000008L,0x0000000040000000L});
	public static final BitSet FOLLOW_annotations_in_field397 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_FIELD_INITIAL_VALUE_in_field_initial_value418 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_literal_in_field_initial_value420 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_integer_literal_in_literal442 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_long_literal_in_literal450 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_short_literal_in_literal458 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_byte_literal_in_literal466 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_float_literal_in_literal474 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_double_literal_in_literal482 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_char_literal_in_literal490 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_string_literal_in_literal498 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_bool_literal_in_literal506 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NULL_LITERAL_in_literal514 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_descriptor_in_literal522 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_literal_in_literal530 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_subannotation_in_literal538 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_field_literal_in_literal546 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_method_literal_in_literal554 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_enum_literal_in_literal562 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_fixed_64bit_literal_number578 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_long_literal_in_fixed_64bit_literal_number586 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_short_literal_in_fixed_64bit_literal_number594 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_byte_literal_in_fixed_64bit_literal_number602 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_float_literal_in_fixed_64bit_literal_number610 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_double_literal_in_fixed_64bit_literal_number618 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_char_literal_in_fixed_64bit_literal_number626 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_bool_literal_in_fixed_64bit_literal_number634 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_fixed_64bit_literal649 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_long_literal_in_fixed_64bit_literal657 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_short_literal_in_fixed_64bit_literal665 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_byte_literal_in_fixed_64bit_literal673 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_float_literal_in_fixed_64bit_literal681 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_double_literal_in_fixed_64bit_literal689 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_char_literal_in_fixed_64bit_literal697 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_bool_literal_in_fixed_64bit_literal705 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_fixed_32bit_literal722 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_long_literal_in_fixed_32bit_literal730 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_short_literal_in_fixed_32bit_literal738 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_byte_literal_in_fixed_32bit_literal746 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_float_literal_in_fixed_32bit_literal754 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_char_literal_in_fixed_32bit_literal762 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_bool_literal_in_fixed_32bit_literal770 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_ARRAY_ELEMENTS_in_array_elements792 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_fixed_64bit_literal_number_in_array_elements801 = new BitSet(new long[]{0x0000004000404C08L,0x0000000004000000L,0x0004000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_I_PACKED_SWITCH_ELEMENTS_in_packed_switch_elements837 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_label_ref_in_packed_switch_elements846 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_I_SPARSE_SWITCH_ELEMENTS_in_sparse_switch_elements881 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_sparse_switch_elements891 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_label_ref_in_sparse_switch_elements893 = new BitSet(new long[]{0x0000004000004C08L,0x0000000004000000L,0x0004000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_I_METHOD_in_method945 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_method_name_and_prototype_in_method953 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_access_list_in_method961 = new BitSet(new long[]{0x0000000000000000L,0x0210000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_registers_directive_in_method988 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_ordered_method_items_in_method1045 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_catches_in_method1053 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
	public static final BitSet FOLLOW_parameters_in_method1061 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_annotations_in_method1070 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_METHOD_PROTOTYPE_in_method_prototype1094 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_I_METHOD_RETURN_TYPE_in_method_prototype1097 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_descriptor_in_method_prototype1099 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_method_type_list_in_method_prototype1102 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_method_name_and_prototype1120 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_method_prototype_in_method_name_and_prototype1122 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_method_type_list1156 = new BitSet(new long[]{0x0000000000008102L,0x0000000000000000L,0x2000000000000000L});
	public static final BitSet FOLLOW_reference_type_descriptor_in_method_reference1185 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_method_reference1188 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_method_prototype_in_method_reference1190 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_reference_type_descriptor_in_field_reference1207 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_field_reference1210 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x2000000000000000L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_field_reference1212 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_REGISTERS_in_registers_directive1238 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_I_LOCALS_in_registers_directive1250 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_short_integral_literal_in_registers_directive1268 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_LABEL_in_label_def1288 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_label_def1290 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_CATCHES_in_catches1316 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_catch_directive_in_catches1318 = new BitSet(new long[]{0x0000000000000008L,0x0000000C00000000L});
	public static final BitSet FOLLOW_catchall_directive_in_catches1321 = new BitSet(new long[]{0x0000000000000008L,0x0000000800000000L});
	public static final BitSet FOLLOW_I_CATCH_in_catch_directive1334 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_catch_directive1336 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_label_ref_in_catch_directive1340 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_label_ref_in_catch_directive1344 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_label_ref_in_catch_directive1348 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_CATCHALL_in_catchall_directive1364 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_label_ref_in_catchall_directive1368 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_label_ref_in_catchall_directive1372 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_label_ref_in_catchall_directive1376 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_PARAMETERS_in_parameters1393 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_parameter_in_parameters1396 = new BitSet(new long[]{0x0000000000000008L,0x1000000000000000L});
	public static final BitSet FOLLOW_I_PARAMETER_in_parameter1412 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_parameter1414 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L,0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_string_literal_in_parameter1416 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_annotations_in_parameter1419 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_line_in_debug_directive1436 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_local_in_debug_directive1442 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_end_local_in_debug_directive1448 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_restart_local_in_debug_directive1454 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_prologue_in_debug_directive1460 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_epilogue_in_debug_directive1466 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_source_in_debug_directive1472 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_LINE_in_line1483 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_integral_literal_in_line1485 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_LOCAL_in_local1503 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_local1505 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0040000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_NULL_LITERAL_in_local1509 = new BitSet(new long[]{0x0000000000008108L,0x0000000000000000L,0x2000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_string_literal_in_local1515 = new BitSet(new long[]{0x0000000000008108L,0x0000000000000000L,0x2000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_local1518 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_string_literal_in_local1523 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_END_LOCAL_in_end_local1544 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_end_local1546 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_RESTART_LOCAL_in_restart_local1564 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_restart_local1566 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_PROLOGUE_in_prologue1583 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_EPILOGUE_in_epilogue1599 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_SOURCE_in_source1616 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_string_literal_in_source1618 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_ORDERED_METHOD_ITEMS_in_ordered_method_items1637 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_label_def_in_ordered_method_items1640 = new BitSet(new long[]{0x0000000000000008L,0x800E0C0000000000L,0x00000FFFFFFFFFD8L});
	public static final BitSet FOLLOW_instruction_in_ordered_method_items1644 = new BitSet(new long[]{0x0000000000000008L,0x800E0C0000000000L,0x00000FFFFFFFFFD8L});
	public static final BitSet FOLLOW_debug_directive_in_ordered_method_items1648 = new BitSet(new long[]{0x0000000000000008L,0x800E0C0000000000L,0x00000FFFFFFFFFD8L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_label_ref1664 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_REGISTER_LIST_in_register_list1689 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_register_list1698 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_I_REGISTER_LIST_in_register_list41732 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_register_list41741 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_I_REGISTER_RANGE_in_register_range1766 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_register_range1771 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_register_range1775 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_verification_error_reference1798 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_field_reference_in_verification_error_reference1808 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_method_reference_in_verification_error_reference1818 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VERIFICATION_ERROR_TYPE_in_verification_error_type1835 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format10t_in_instruction1849 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format10x_in_instruction1855 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format11n_in_instruction1861 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format11x_in_instruction1867 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format12x_in_instruction1873 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format20bc_in_instruction1879 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format20t_in_instruction1885 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_field_in_instruction1891 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_string_in_instruction1897 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_type_in_instruction1903 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_lambda_in_instruction1909 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_method_in_instruction1915 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21ih_in_instruction1921 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21lh_in_instruction1927 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21s_in_instruction1933 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21t_in_instruction1939 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22b_in_instruction1945 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22c_field_in_instruction1951 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22c_type_in_instruction1957 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22c_string_in_instruction1963 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22s_in_instruction1969 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22t_in_instruction1975 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22x_in_instruction1981 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format23x_in_instruction1987 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format25x_in_instruction1993 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format30t_in_instruction1999 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format31c_in_instruction2005 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format31i_in_instruction2011 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format31t_in_instruction2017 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format32x_in_instruction2023 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format35c_method_in_instruction2029 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format35c_type_in_instruction2035 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format3rc_method_in_instruction2041 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format3rc_type_in_instruction2047 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format51l_type_in_instruction2053 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_array_data_directive_in_instruction2059 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_packed_switch_directive_in_instruction2065 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_sparse_switch_directive_in_instruction2071 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT10t_in_insn_format10t2095 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT10t_in_insn_format10t2097 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_label_ref_in_insn_format10t2099 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT10x_in_insn_format10x2122 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT10x_in_insn_format10x2124 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT11n_in_insn_format11n2147 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT11n_in_insn_format11n2149 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format11n2151 = new BitSet(new long[]{0x0000000000004800L,0x0000000004000000L,0x0004000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_short_integral_literal_in_insn_format11n2153 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT11x_in_insn_format11x2176 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT11x_in_insn_format11x2178 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format11x2180 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT12x_in_insn_format12x2203 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT12x_in_insn_format12x2205 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format12x2209 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format12x2213 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT20bc_in_insn_format20bc2236 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT20bc_in_insn_format20bc2238 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_verification_error_type_in_insn_format20bc2240 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_verification_error_reference_in_insn_format20bc2242 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT20t_in_insn_format20t2265 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT20t_in_insn_format20t2267 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_label_ref_in_insn_format20t2269 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21c_FIELD_in_insn_format21c_field2292 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_set_in_insn_format21c_field2296 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_field2304 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_field_reference_in_insn_format21c_field2306 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21c_STRING_in_insn_format21c_string2329 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_insn_format21c_string2331 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_string2333 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_string_literal_in_insn_format21c_string2335 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21c_TYPE_in_insn_format21c_type2358 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_insn_format21c_type2360 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_type2362 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x2000000000000000L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format21c_type2364 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21c_LAMBDA_in_insn_format21c_lambda2387 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_LAMBDA_in_insn_format21c_lambda2389 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_lambda2391 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_string_literal_in_insn_format21c_lambda2393 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21c_METHOD_in_insn_format21c_method2416 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_METHOD_in_insn_format21c_method2418 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_method2420 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_method_reference_in_insn_format21c_method2422 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21ih_in_insn_format21ih2445 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21ih_in_insn_format21ih2447 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21ih2449 = new BitSet(new long[]{0x0000004000004C00L,0x0000000004000000L,0x0004000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_format21ih2451 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21lh_in_insn_format21lh2474 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21lh_in_insn_format21lh2476 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21lh2478 = new BitSet(new long[]{0x0000004000404C00L,0x0000000004000000L,0x0004000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_fixed_64bit_literal_in_insn_format21lh2480 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21s_in_insn_format21s2503 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21s_in_insn_format21s2505 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21s2507 = new BitSet(new long[]{0x0000000000004800L,0x0000000004000000L,0x0004000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_short_integral_literal_in_insn_format21s2509 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21t_in_insn_format21t2532 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21t_in_insn_format21t2534 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21t2536 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_label_ref_in_insn_format21t2538 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT22b_in_insn_format22b2561 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22b_in_insn_format22b2563 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22b2567 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22b2571 = new BitSet(new long[]{0x0000000000004800L,0x0000000004000000L,0x0004000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_short_integral_literal_in_insn_format22b2573 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT22c_FIELD_in_insn_format22c_field2596 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_set_in_insn_format22c_field2600 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_field2610 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_field2614 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_field_reference_in_insn_format22c_field2616 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT22c_TYPE_in_insn_format22c_type2639 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_insn_format22c_type2641 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_type2645 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_type2649 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x2000000000000000L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format22c_type2651 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT22c_STRING_in_insn_format22c_string2674 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_STRING_in_insn_format22c_string2676 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_string2680 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_string2684 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_string_literal_in_insn_format22c_string2686 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT22s_in_insn_format22s2709 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22s_in_insn_format22s2711 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22s2715 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22s2719 = new BitSet(new long[]{0x0000000000004800L,0x0000000004000000L,0x0004000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_short_integral_literal_in_insn_format22s2721 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT22t_in_insn_format22t2744 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22t_in_insn_format22t2746 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22t2750 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22t2754 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_label_ref_in_insn_format22t2756 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT22x_in_insn_format22x2779 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22x_in_insn_format22x2781 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22x2785 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22x2789 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT23x_in_insn_format23x2812 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT23x_in_insn_format23x2814 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format23x2818 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format23x2822 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format23x2826 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT25x_in_insn_format25x2849 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT25x_in_insn_format25x2851 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format25x2853 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_register_list4_in_insn_format25x2855 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT30t_in_insn_format30t2878 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT30t_in_insn_format30t2880 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_label_ref_in_insn_format30t2882 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT31c_in_insn_format31c2905 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31c_in_insn_format31c2907 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format31c2909 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_string_literal_in_insn_format31c2911 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT31i_in_insn_format31i2934 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31i_in_insn_format31i2936 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format31i2938 = new BitSet(new long[]{0x0000004000004C00L,0x0000000004000000L,0x0004000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_format31i2940 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT31t_in_insn_format31t2963 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31t_in_insn_format31t2965 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format31t2967 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_label_ref_in_insn_format31t2969 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT32x_in_insn_format32x2992 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT32x_in_insn_format32x2994 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format32x2998 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format32x3002 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT35c_METHOD_in_insn_format35c_method3025 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_insn_format35c_method3027 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_register_list_in_insn_format35c_method3029 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_method_reference_in_insn_format35c_method3031 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT35c_TYPE_in_insn_format35c_type3054 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_insn_format35c_type3056 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_register_list_in_insn_format35c_type3058 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x2000000000000000L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format35c_type3060 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT3rc_METHOD_in_insn_format3rc_method3083 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_in_insn_format3rc_method3085 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_register_range_in_insn_format3rc_method3087 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_method_reference_in_insn_format3rc_method3089 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT3rc_TYPE_in_insn_format3rc_type3112 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_TYPE_in_insn_format3rc_type3114 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_register_range_in_insn_format3rc_type3116 = new BitSet(new long[]{0x0000000000008100L,0x0000000000000000L,0x2000000000000000L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format3rc_type3118 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT51l_in_insn_format51l_type3141 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT51l_in_insn_format51l_type3143 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format51l_type3145 = new BitSet(new long[]{0x0000004000404C00L,0x0000000004000000L,0x0004000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_fixed_64bit_literal_in_insn_format51l_type3147 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_ARRAY_DATA_in_insn_array_data_directive3170 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_I_ARRAY_ELEMENT_SIZE_in_insn_array_data_directive3173 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_short_integral_literal_in_insn_array_data_directive3175 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_array_elements_in_insn_array_data_directive3178 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_PACKED_SWITCH_in_insn_packed_switch_directive3200 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_I_PACKED_SWITCH_START_KEY_in_insn_packed_switch_directive3203 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_packed_switch_directive3205 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_packed_switch_elements_in_insn_packed_switch_directive3208 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_SPARSE_SWITCH_in_insn_sparse_switch_directive3232 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_sparse_switch_elements_in_insn_sparse_switch_directive3234 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ARRAY_TYPE_PREFIX_in_array_descriptor3255 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000000L,0x2000000000000000L});
	public static final BitSet FOLLOW_PRIMITIVE_TYPE_in_array_descriptor3259 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_array_descriptor3287 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMITIVE_TYPE_in_nonvoid_type_descriptor3305 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_nonvoid_type_descriptor3313 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_descriptor_in_nonvoid_type_descriptor3321 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_reference_type_descriptor3342 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_descriptor_in_reference_type_descriptor3350 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_TYPE_in_type_descriptor3370 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_type_descriptor3378 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_long_literal_in_short_integral_literal3396 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_short_integral_literal3408 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_short_literal_in_short_integral_literal3420 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_char_literal_in_short_integral_literal3428 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_byte_literal_in_short_integral_literal3436 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_long_literal_in_integral_literal3451 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_integral_literal3463 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_short_literal_in_integral_literal3471 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_byte_literal_in_integral_literal3479 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_LITERAL_in_integer_literal3495 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LONG_LITERAL_in_long_literal3510 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SHORT_LITERAL_in_short_literal3525 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BYTE_LITERAL_in_byte_literal3540 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FLOAT_LITERAL_in_float_literal3555 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLE_LITERAL_in_double_literal3570 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CHAR_LITERAL_in_char_literal3585 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_string_literal3600 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOL_LITERAL_in_bool_literal3619 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_ENCODED_ARRAY_in_array_literal3641 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_literal_in_array_literal3644 = new BitSet(new long[]{0x000000400040CD08L,0x000003C004000000L,0x2044100000000000L,0x0000000000000444L});
	public static final BitSet FOLLOW_I_ANNOTATIONS_in_annotations3669 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_annotation_in_annotations3672 = new BitSet(new long[]{0x0000000000000008L,0x0000000020000000L});
	public static final BitSet FOLLOW_I_ANNOTATION_in_annotation3701 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ANNOTATION_VISIBILITY_in_annotation3703 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_subannotation_in_annotation3705 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_ANNOTATION_ELEMENT_in_annotation_element3726 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_annotation_element3728 = new BitSet(new long[]{0x000000400040CD00L,0x000003C004000000L,0x2044100000000000L,0x0000000000000444L});
	public static final BitSet FOLLOW_literal_in_annotation_element3730 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_SUBANNOTATION_in_subannotation3757 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_subannotation3767 = new BitSet(new long[]{0x0000000000000008L,0x0000000080000000L});
	public static final BitSet FOLLOW_annotation_element_in_subannotation3778 = new BitSet(new long[]{0x0000000000000008L,0x0000000080000000L});
	public static final BitSet FOLLOW_I_ENCODED_FIELD_in_field_literal3817 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_field_reference_in_field_literal3819 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_ENCODED_METHOD_in_method_literal3840 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_method_reference_in_method_literal3842 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_ENCODED_ENUM_in_enum_literal3863 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_field_reference_in_enum_literal3865 = new BitSet(new long[]{0x0000000000000008L});
}
