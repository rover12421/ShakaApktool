/**
 *  Copyright 2015 Rover12421 <rover12421@163.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.rover12421.shaka.apktool.cli;

import brut.androlib.Androlib;
import brut.androlib.ApktoolProperties;
import brut.apktool.Main;
import com.rover12421.shaka.lib.HookMain;
import com.rover12421.shaka.lib.ReflectUtil;
import com.rover12421.shaka.lib.ShakaDecodeOption;
import com.rover12421.shaka.lib.ShakaProperties;
import com.rover12421.shaka.lib.multiLanguage.MultiLanguageSupport;
import com.rover12421.shaka.smali.baksmali.baksmaliMainAj;
import com.rover12421.shaka.smali.smali.smaliMainAj;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jf.util.ConsoleUtil;
import org.jf.util.SmaliHelpFormatter;

/**
 * Created by rover12421 on 7/11/14.
 */
@Aspect
public class ApktoolMainAj {

    private static final HookMain hookMain = new HookMain() {
        private final ApktoolMainAj apktoolMainAj = new ApktoolMainAj();
        @Override
        public void version() {
            apktoolMainAj.version_around();
        }

        @Override
        public void usage() {
            try {
                apktoolMainAj.usage_around(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public static HookMain getHookMain() {
        return hookMain;
    }

    public static Options normalOptions() throws Exception {
        return (Options) ReflectUtil.getFieldValue(Main.class, "normalOptions");
    }

    public static Options DecodeOptions() throws Exception {
        return (Options) ReflectUtil.getFieldValue(Main.class, "DecodeOptions");
    }

    public static Options BuildOptions() throws Exception {
        return (Options) ReflectUtil.getFieldValue(Main.class, "BuildOptions");
    }

    public static Options frameOptions() throws Exception {
        return (Options) ReflectUtil.getFieldValue(Main.class, "frameOptions");
    }

    public static Options allOptions() throws Exception {
        return (Options) ReflectUtil.getFieldValue(Main.class, "allOptions");
    }

    public static Options emptyOptions() throws Exception {
        return (Options) ReflectUtil.getFieldValue(Main.class, "emptyOptions");
    }

    private static String verbosityHelp() throws Exception {
        return (String) ReflectUtil.invokeMethod(Main.class, "verbosityHelp");
    }

    private void _Options() {
        try {
            ReflectUtil.invokeMethod(Main.class, "_Options");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Around("execution(void brut.apktool.Main.usage(..))" +
            "&& args(commandLine)")
    public void usage_around(CommandLine commandLine) throws Exception {
        // load basicOptions
        _Options();
        SmaliHelpFormatter formatter = new SmaliHelpFormatter();
//        formatter.setWidth(120);
        int consoleWidth = ConsoleUtil.getConsoleWidth();
        if (consoleWidth <= 0) {
            consoleWidth = 120;
        }
        formatter.setWidth(consoleWidth);

        // print out license info prior to formatter.
        System.out.println(
                "ShakaApktool v" + ShakaProperties.getVersion() + " - a tool for reengineering Android apk files\n" +
                        "with\n" +
                        "\tapktool v" + Androlib.getVersion() + ",\n" +
                        "\tsmali v" + ApktoolProperties.get("smaliVersion") + ",\n" +
                        "\tbaksmali v" + ApktoolProperties.get("baksmaliVersion") + "\n" +
                        "Copyright 2015 Rover12421 <rover12421@163.com>");

        System.out.println("Apache License 2.0 (http://www.apache.org/licenses/LICENSE-2.0)\n");

        String thanks = MultiLanguageSupport.getInstance().get(-1, null);
        if (thanks != null) {
            System.out.println(thanks);
        }

        System.out.println("\n******************** apktool ********************\n");

        // 4 usage outputs (general, frameworks, decode, build)
        formatter.printHelp("ShakaApktool " + verbosityHelp(), normalOptions());
        formatter.printHelp("ShakaApktool " + verbosityHelp() + "if|install-framework [options] <framework.apk>", frameOptions());
        formatter.printHelp("ShakaApktool " + verbosityHelp() + "d[ecode] [options] <file_apk>", DecodeOptions());
        formatter.printHelp("ShakaApktool " + verbosityHelp() + "b[uild] [options] <app_path>", BuildOptions());

        System.out.println("\n******************** smali ********************\n");

        formatter.printHelp("ShakaApktool " + verbosityHelp() + "s[mali] [options] [--] [<smali-file>|folder]*",
                "assembles a set of smali files into a dex file", smaliMainAj.basicOptions(), smaliMainAj.debugOptions());

        System.out.println("\n******************** baksmali ********************\n");

        formatter.printHelp("ShakaApktool " + verbosityHelp() + "bs|baksmali [options] <dex-file>",
                "disassembles and/or dumps a dex file", baksmaliMainAj.basicOptions(), baksmaliMainAj.debugOptions());

        if (Main.isAdvanceMode()) {
            System.out.println("\n******************** AdvanceMode ********************\n");
            formatter.printHelp("ShakaApktool " + verbosityHelp() + "publicize-resources <file_path>",
                    "Make all framework resources public.", emptyOptions(), (String)null);
        }

        // print out more information
        System.out.println("\n******************** More ********************\n");
        System.out.println("For additional info, see: http://www.rover12421.com/");
        System.out.println("For Apktool info, see: http://ibotpeaches.github.io/Apktool/");
        System.out.println("For smali/baksmali info, see: http://code.google.com/p/smali/");
    }


    @Around("execution(void brut.apktool.Main._version())")
    public void version_around() {
        System.out.println("ShakaApktool v" + ShakaProperties.getVersion());
        System.out.println("Apktool v" + Androlib.getVersion());
        System.out.println("smali v" + ApktoolProperties.get("smaliVersion"));
        System.out.println("baksmali v" + ApktoolProperties.get("baksmaliVersion"));
    }

    @Before("execution(void brut.apktool.Main._Options())")
    public void before_Options() {
        Option language = OptionBuilder.withLongOpt("language")
                .withDescription("Display language, e.g. zh-CN, zh-TW")
                .hasArg(true)
                .withArgName("Locale")
                .create("lng");

        Option no9png = OptionBuilder.withLongOpt("no-9png")
                .withDescription("Do not decode .9 png file.")
                .create("n9");

        Option usingDefaultFramework = OptionBuilder.withLongOpt("default-framework")
                .withDescription("Using default framework file.")
                .create("df");

        Option showMoreRecognizableCharacters = OptionBuilder.withLongOpt("more-recognizable-characters")
                .withDescription("Show more recognizable characters")
                .create("mc");

        Option fuckUnkownId = OptionBuilder.withLongOpt("fuck_unkown_id")
                .withDescription("Fuck unkown id")
                .create("fui");

        Option ignoreResDecodeError = OptionBuilder.withLongOpt("ignore_res_decode_error")
                .withDescription("ignore res decode error")
                .create("ir");

        Option xmlAttributeNameCorrect = OptionBuilder.withLongOpt("xml_attribute_name_correct")
                .withDescription("xml attribute name correct. May be has problem, not recommended.")
                .create("xn");

        try {
            Options normalOptions = normalOptions();
            Options DecodeOptions = DecodeOptions();
            normalOptions.addOption(language);
            DecodeOptions.addOption(no9png);
            DecodeOptions.addOption(usingDefaultFramework);
            DecodeOptions.addOption(showMoreRecognizableCharacters);
            DecodeOptions.addOption(fuckUnkownId);
            DecodeOptions.addOption(ignoreResDecodeError);
            DecodeOptions.addOption(xmlAttributeNameCorrect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before("execution(void brut.apktool.Main.cmdDecode(..))" +
            "&& args(cli)")
    public void cmdDecode_before(CommandLine cli) {
        if (cli.hasOption("n9") || cli.hasOption("no-9png")) {
            ShakaDecodeOption.getInstance().setNo9png(true);
        }

        if (cli.hasOption("df") || cli.hasOption("default-framework")) {
            ShakaDecodeOption.getInstance().setUsingDefaultFramework(true);
        }

        if (cli.hasOption("mc") || cli.hasOption("more-recognizable-characters")) {
            ShakaDecodeOption.getInstance().setShowMoreRecognizableCharacters(true);
        }

        if (cli.hasOption("fui") || cli.hasOption("fuck_unkown_id")) {
            ShakaDecodeOption.getInstance().setFuckUnkownId(true);
        }

        if (cli.hasOption("ir") || cli.hasOption("ignore_res_decode_error")) {
            ShakaDecodeOption.getInstance().setIgnoreResDecodeError(true);
        }

        if (cli.hasOption("xn") || cli.hasOption("xml_attribute_name_correct")) {
            ShakaDecodeOption.getInstance().setXmlAttributeNameCorrect(true);
        }
    }
}
