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
import com.rover12421.shaka.lib.ShakaBuildOption;
import com.rover12421.shaka.lib.ShakaDecodeOption;
import com.rover12421.shaka.lib.ShakaProperties;
import com.rover12421.shaka.lib.cli.CommandLineArgEnum;
import com.rover12421.shaka.lib.multiLanguage.MultiLanguageSupport;
import org.apache.commons.cli.CommandLine;
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

    @Around("execution(void brut.apktool.Main.usage(..))" +
            "&& args(commandLine)")
    public void usage_around(CommandLine commandLine) throws Exception {
        // load basicOptions
        Main._Options0();
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
        formatter.printHelp("ShakaApktool " + Main.verbosityHelp0(), Main.getNormalOptions());
        formatter.printHelp("ShakaApktool " + Main.verbosityHelp0() + "if|install-framework [options] <framework.apk>", Main.getFrameOptions());
        formatter.printHelp("ShakaApktool " + Main.verbosityHelp0() + "d[ecode] [options] <file_apk>", Main.getDecodeOptions());
        formatter.printHelp("ShakaApktool " + Main.verbosityHelp0() + "b[uild] [options] <app_path>", Main.getBuildOptions());

        System.out.println("\n******************** smali ********************\n");

        formatter.printHelp("ShakaApktool " + "s[mali] [options] [--] [<smali-file>|folder]*",
                "assembles a set of smali files into a dex file", org.jf.smali.main.getBasicOptions(), org.jf.smali.main.getDebugOptions());

        System.out.println("\n******************** baksmali ********************\n");

        formatter.printHelp("ShakaApktool " + "bs|baksmali [options] <dex-file>",
                "disassembles and/or dumps a dex file", org.jf.baksmali.main.getBasicOptions(), org.jf.baksmali.main.getDebugOptions());

        if (Main.isAdvanceMode()) {
            System.out.println("\n******************** AdvanceMode ********************\n");
            formatter.printHelp("ShakaApktool " + "publicize-resources <file_path>",
                    "Make all framework resources public.", Main.getEmptyOptions(), (String)null);
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
        try {
            Options normalOptions = Main.getNormalOptions();
            Options DecodeOptions = Main.getDecodeOptions();
            Options BuildOptions = Main.getBuildOptions();

            normalOptions.addOption(CommandLineArgEnum.LANGUAGE.getOption());

            DecodeOptions.addOption(CommandLineArgEnum.NO_9_PNG.getOption());
            DecodeOptions.addOption(CommandLineArgEnum.USING_DEFAULT_FRAMEWORK.getOption());
            DecodeOptions.addOption(CommandLineArgEnum.SHOW_MORE_RECOGNIZABLE_CHARACTERS.getOption());
            DecodeOptions.addOption(CommandLineArgEnum.FUCK_UNKOWN_ID.getOption());
            DecodeOptions.addOption(CommandLineArgEnum.IGNORE_RES_DECODE_ERROR.getOption());
            DecodeOptions.addOption(CommandLineArgEnum.XML_ATTRIBUTE_NAME_CORRECT.getOption());

            BuildOptions.addOption(CommandLineArgEnum.FUCK_NOT_DEFINED_RES.getOption());
            BuildOptions.addOption(CommandLineArgEnum.USING_DEFAULT_FRAMEWORK.getOption());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before("execution(void brut.apktool.Main.cmdDecode(..))" +
            "&& args(cli)")
    public void cmdDecode_before(CommandLine cli) {
        ShakaDecodeOption decodeOption = ShakaDecodeOption.getInstance();

        if (CommandLineArgEnum.NO_9_PNG.hasMatch(cli)) {
            decodeOption.setNo9png(true);
        }

        if (CommandLineArgEnum.USING_DEFAULT_FRAMEWORK.hasMatch(cli)) {
            decodeOption.setUsingDefaultFramework(true);
        }

        if (CommandLineArgEnum.SHOW_MORE_RECOGNIZABLE_CHARACTERS.hasMatch(cli)) {
            decodeOption.setShowMoreRecognizableCharacters(true);
        }

        if (CommandLineArgEnum.FUCK_UNKOWN_ID.hasMatch(cli)) {
            decodeOption.setFuckUnkownId(true);
        }

        if (CommandLineArgEnum.IGNORE_RES_DECODE_ERROR.hasMatch(cli)) {
            decodeOption.setIgnoreResDecodeError(true);
        }

        if (CommandLineArgEnum.XML_ATTRIBUTE_NAME_CORRECT.hasMatch(cli)) {
            decodeOption.setXmlAttributeNameCorrect(true);
        }
    }

    @Before("execution(void brut.apktool.Main.cmdBuild(..))" +
            "&& args(cli)")
    public void cmdBuild_before(CommandLine cli) {
        ShakaBuildOption buildOption = ShakaBuildOption.getInstance();

        if (CommandLineArgEnum.FUCK_NOT_DEFINED_RES.hasMatch(cli)) {
            buildOption.setFuckNotDefinedRes(true);
        }

        if (CommandLineArgEnum.USING_DEFAULT_FRAMEWORK.hasMatch(cli)) {
            buildOption.setUsingDefaultFramework(true);
        }
    }
}
