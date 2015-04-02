package com.rover12421.shaka.apktool.AspjectJ.apktool_cli;

import com.rover12421.shaka.apktool.lib.MultiLanguageSupport;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Locale;

/**
 * Created by rover12421 on 2/9/15.
 */
@Aspect
public class MultiLanguageAj {

    private final static int MINLEN = 9;

    private static final String[] langStr = new String[]{
            "Using AspectJ weaver Apktool project",
            "Weaver by",
            "a tool for reengineering Android apk files",
            "For additional info, see:",
            "For smali/baksmali info, see:",
            "For ShakaApktool info, see:",
            "prints advance information",
            "prints the version then exits",
            "The name of apk that gets written",
            "Stores framework files into <dir>",
            "Tag frameworks using <tag>",
            "Force delete destination directory",
            "The name of folder that gets written",
            "Uses framework files located in <dir>",
            "Do not decode resources",
            "Do not decode sources",
            "Uses framework files tagged by <tag>",
            "Skip changes detection and build all files",
            "The numeric api-level of the file to generate, e.g. 14 for ICS",
            "don't write out debug info",
            "Decode in debug mode. Check project page for more info",
            "Smali line prefix when decoding in debug mode.",
            "Use if there was an error and some resources were dropped, e.g.\n            \"Invalid config flags detected. Dropping resources\", but you\n            want to decode them anyway, even with errors. You will have to\n            fix them manually before building",
            "Keeps files to closest to original as possible. Prevents rebuild",
            "Loads aapt from specified location",
            "Copies original AndroidManifest.xml and META-INF. See project page for more info",
            "Builds in debug mode. Check project page for more info",
            "Apache License 2.0",
            "Make all framework resources public",
            "Default is",
            "publicize-resources",
            "Loading resource table from file",
            "Building apk file",
            "Copy original files",
            "Copy AndroidManifest.xml",
            "Copy META-INF",
            "Copying libs",
            "Checking whether resources has changed",
            "Building AndroidManifest.xml",
            "Copying raw AndroidManifest.xml",
            "Building resources",
            "Copying raw resources",
            "Building java sources",
            "Copying unknown files/dir",
            "Copying raw manifest",
            "Copying assets and libs",
            "Decoding Java sources",
            "Copying raw classes.dex file",
            "Copying unknown files",
            "Copying original files",
            "being used as aapt location",
            "command ran",
            "Regular manifest package",
            "Decoding file-resources",
            "Decoding values */* XMLs",
            "Decoding AndroidManifest.xml with only framework resources",
            "Decoding AndroidManifest.xml with resources",
            "Framework installed to",
            "Loading resource table",
            "Decoding Shared Library",
            "Could not decode file, replacing by FALSE value",
            "Could not decode attr value, using undecoded value",
            "Cant find 9patch chunk in file",
            "Undefined resource spec in %s",
            "Multiple resources",
            "Multiple res specs",
            "Config flags size > %d. Exceeding bytes: 0x%X",
            "Using ShakaApktool",
            "Ignoring duplicate field",
            "Duplicate static+instance field found",
            "You will need to rename one of these fields, including all references",
            "duplicate field ignored",
            "There is both a static and instance field with this signature",
            "duplicate method ignored",
            "# direct methods",
            "# virtual methods",
            "# instance fields",
            "Duplicate direct+virtual method found",
            "AnalysisException:",
            "Try start offset %d is past the end of the code block",
            "Try end offset %d is past the end of the code block",
            "invalid reference type",
            "invalid_reference",
            "Invalid 31t opcode",
            "invalid payload reference",
            "disallowed odex opcode",
            "unknown opcode:",
            "Cannot parse dependency value %s",
            "warning: cannot open %s for reading. Will continue looking",
            "Error while reading boot class path entry",
            "Cannot locate boot class path file",
            "odex version %d is not supported yet",
            "code address:",
            "Invalid register type %s for register v%d",
            "v%d cannot be used as the first register in a wide registerpair because it is the last register.",
            "Incorrect field type \"%s\" for %s",
            "Not an odexed field access opcode:",
            "Unknown type %s",
            "Switch instruction at address/index 0x%x/%d points to the end of the method",
            "Switch instruction at address/index 0x%x/%d does not refer to a payload instruction",
            "Switch instruction at address/index 0x%x/%d refers to the wrong type of payload instruction",
            "Multiple switch instructions refer to the same payload",
            "This is not currently supported",
            "Please file a bug",
            "Didn't throw an exception for value",
            "Threw an exception for value",
            "Unrecognized item type",
            "Param string \"%s\" contains invalid type prefix",
            "Annotating outside the parent bounds",
            "Invalid opcode %s for %s",
            "Invalid register",
            "Must be between v0 and v15, inclusive.",
            "Must be between v0 and v255, inclusive.",
            "Must be between v0 and v65535, inclusive.",
            "Must be between -8 and 7, inclusive.",
            "Must be between -128 and 127, inclusive.",
            "Invalid literal value",
            "Must be between -32768 and 32767, inclusive.",
            "Low 16 bits must be zeroed out",
            "Low 48 bits must be zeroed out",
            "Invalid code offset",
            "Invalid register count",
            "Must be between 0 and 5, inclusive.",
            "Must be between 0 and 255, inclusive.",
            "Invalid value_arg value %d for an encoded_value. Expecting 0",
            "Invalid value_arg value %d for an encoded_value. Expecting 0..%d, inclusive",
            "Invalid field offset",
            "Must be between 0x0000 and 0xFFFF inclusive",
            "Invalid vtable index",
            "Must be between 0 and 65535, inclusive",
            "Invalid inline index",
            "Invalid verification error value",
            "Must be between 1 and 9, inclusive",
            "Invalid reference",
            "expecting a type reference",
            "expecting a field reference",
            "expecting a method reference",
            "Not a valid reference type",
            "Invalid character '%c' in param list \"%s\" at position %d",
            "Unknown reference type",
            "Cannot get the address of a BuilderDebugItem that isn't associated with a method",
            "Cannot get the location of an instruction that hasn't been added to a method",
            "Target is out of range",
            "The referrer has not been set yet",
            "Cannot get the location of a label that hasn't been placed yet",
            "This iterator was invalidated by a change to this MutableMethodImplementation",
            "Only StringReference instances returned by DexBuilder.internStringReference or DexBuilder.internNullableStringReference may be used",
            "Only TypeReference instances returned by DexBuilder.internTypeReference or DexBuilder.internNullableTypeReference may be used",
            "Must call setSuffix() first",
            "Can't delete %s to make it into a directory",
            "Couldn't create directory %s",
            "The suffix can only be set once",
            "suffix must be in [0, 100000)",
            "unsupported feature",
            "feature name can not be null",
            "duplicated prefix",
            "namespace must be not null",
            "default namespace cannot have prefix",
            "unsupported property",
            "setOutput() must called set before serialization can start",
            "start tag can not be written in empty default namespace as default namespace is currently bound to",
            "# annotations",
            "# interfaces",
            "# static fields",
            "There is both a direct and virtual method with this signature",
            "You will need to rename one of these methods, including all references",
            "The value of this static final field might be set in the static constructor",
            "was invalid verification error type",
            "Replaced unresolvable odex instruction with a throw",
            "Unknown access type",
            "Invalid debug item type",
            "Invalid annotation visibility",
            "Invalid verification error:",
            "Cannot call getDestinationRegister() for an instruction that doesn't store a value",
            "Invalid array type:",
            "Cannot locate boot class path file %s",
            "Cannot construct ClassProto for non reference type",
            "Couldn't find accessible class while resolving method %s",
            "Couldn't find accessible class while resolving field %s",
            "Cannot call getCommonSuperclass on PrimitiveProto",
            "Invalid type",
            "Cannot use this method for narrow register type: %s",
            "Instruction format %s not supported",
            "Invalid debug item type:",
            "Encountered optional uint that is out of range at offset 0x%x",
            "Encountered small uint that is out of range at offset 0x%x",
            "Invalid size %d for sized int at offset 0x%x",
            "Invalid size %d for sized long at offset 0x%x",
            "Invalid size %d for sized uint at offset 0x%x",
            "Big endian dex files are not currently supported",
            "Expected:",
            "Display language",
            "Checking whether sources has changed",
            "Could not find sources",
            "Renaming it to",
            "Could not decode arsc file",
            "Could not decode XML",
            "Could not detect whether app is framework one",
            "Out dir not set",
            "Using default framework file",
            "Do not decode .9 png file",
            "Unable to rename temporary file"
    };

    private static final String[] langRpStr = new String[]{
            "Copying (.+?) file\\.\\.\\.",
            "Smaling (.+?) folder into (.+?)\\.\\.\\.",
            "Baksmaling (.+?)\\.\\.\\.",
            "Using Apktool (.+?) on (.+?)",
            "Renamed manifest package found! Replacing (.+?) with (.+?)",
            "Arsc file contains multiple packages\\. Using package (.+?) as default",
            "Using Apktool (.+?)",
            "^Copying unknown file (.+?) with method (.+?)$"
    };

    private static final String[] langRpStrDef = new String[]{
            "Copying $1 file...",
            "Smaling $1 folder into $2...",
            "Baksmaling $1...",
            "Using Apktool $1 on $2",
            "Renamed manifest package found! Replacing $1 with $2",
            "Arsc file contains multiple packages. Using package $1 as default",
            "Using Apktool $1",
            "Copying unknown file $1 with method $2"
    };

    public static String covertLocaleInfo(String str) {
        if (str == null || str.length() < MINLEN) return str;
        MultiLanguageSupport languageSupport = MultiLanguageSupport.getInstance();
//        if (languageSupport.getLocale().equals(Locale.US)) return str;
//        int len = str.length();
        for (int i=0; i<langStr.length; i++) {
            String key = langStr[i];
//            if (len < key.length()) continue;
            String val = languageSupport.get(i, langStr[i]);
            if (key.equals(val)) continue;
            str = str.replace(key, val);
        }

        for (int i=0; i<langRpStr.length; i++) {
            try {
                String key = langRpStr[i];
//                if (len < key.length()) continue;
                String val = languageSupport.get("r" + i, langRpStrDef[i]);
                if (key.equals(val)) continue;
                str = str.replaceFirst(key, val);
            } catch (Exception e) {
            }
        }

        return str;
    }

    @Around("call(void java.io.PrintStream.println(..))" +
            "&& args(x)" +
            "&& !within(com.rover12421.shaka.apktool.AspjectJ.apktool_cli.MultiLanguageAj)" +
            "&& !within(com.rover12421.shaka.apktool.test.*)")
    public void around_sout_println(ProceedingJoinPoint joinPoint, String x) throws Throwable {
        joinPoint.proceed(new Object[]{covertLocaleInfo(x)});
    }

    @Around("call(* org.apache.commons.cli.OptionBuilder.withDescription(..))" +
            "&& args(newDescription)" +
            "&& !within(com.rover12421.shaka.apktool.AspjectJ.apktool_cli.MultiLanguageAj)")
    public OptionBuilder around_OptionBuilder_withDescription(ProceedingJoinPoint joinPoint, String newDescription) throws Throwable {
        return (OptionBuilder) joinPoint.proceed(new Object[]{covertLocaleInfo(newDescription)});
    }

    @Around("call(void org.apache.commons.cli.HelpFormatter.printHelp(..))" +
            "&& args(cmdLineSyntax, header, options, footer)" +
            "&& !within(com.rover12421.shaka.apktool.AspjectJ.apktool_cli.MultiLanguageAj)")
    public void around_HelpFormatter_printHelp(ProceedingJoinPoint joinPoint,
                                                        String cmdLineSyntax, String header, Options options, String footer) throws Throwable {
        joinPoint.proceed(new Object[]{covertLocaleInfo(cmdLineSyntax), covertLocaleInfo(header), options, footer});
    }

    @Around("call(* java.lang.String.format(..))" +
            "&& args(format, args)" +
            "&& !within(com.rover12421.shaka.apktool.AspjectJ.apktool_cli.MultiLanguageAj)")
    public String around_String_format(ProceedingJoinPoint joinPoint,
                                     String format, Object... args) throws Throwable {
        return covertLocaleInfo((String) joinPoint.proceed(joinPoint.getArgs()));
    }


    @Around("call(void org.jf.util.IndentingWriter.write(..))" +
            "&& args(s)" +
            "&& !within(com.rover12421.shaka.apktool.AspjectJ.apktool_cli.MultiLanguageAj)")
    public void around_IndentingWriter_write(ProceedingJoinPoint joinPoint,
                                                   String s) throws Throwable {
        joinPoint.proceed(new Object[]{covertLocaleInfo(s)});
    }

    @Around("call(void java.util.logging.Logger.*(String))" +
            "&& args(msg)" +
            "&& !within(com.rover12421.shaka.apktool.AspjectJ.apktool_cli.MultiLanguageAj)")
    public void around_Logger_msg(ProceedingJoinPoint joinPoint,
                                             String msg) throws Throwable {
        joinPoint.proceed(new Object[]{covertLocaleInfo(msg)});
    }
}



