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
import com.rover12421.shaka.lib.multiLanguage.MultiLanguageSupport;
import com.rover12421.shaka.lib.ShakaDecodeOption;
import com.rover12421.shaka.lib.ShakaProperties;
import com.rover12421.shaka.lib.ReflectUtil;
import org.apache.commons.cli.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Locale;

/**
 * Created by rover12421 on 7/11/14.
 */
@Aspect
public class MainAj {

    @Around("execution(void brut.apktool.Main.usage(..))" +
            "&& args(commandLine)")
    public void usage_around(ProceedingJoinPoint joinPoint, CommandLine commandLine) {
        System.out.println("ShakaApktool v" + ShakaProperties.getVersion() + " - Using AspectJ weaver Apktool project");
        System.out.println("Weaver by Rover12421 <rover12421@163.com>");
        String thanks = MultiLanguageSupport.getInstance().get(-1, null);
        if (thanks != null) {
            System.out.println(thanks);
        }

        System.out.println("----------------\n");

        try {
            joinPoint.proceed(joinPoint.getArgs());
            System.out.println("For ShakaApktool info, see: http://www.rover12421.com/");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
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

        try {
            Options normalOptions = (Options) ReflectUtil.getFieldValue(brut.apktool.Main.class, "normalOptions");
            Options DecodeOptions = (Options) ReflectUtil.getFieldValue(brut.apktool.Main.class, "DecodeOptions");
            normalOptions.addOption(language);
            DecodeOptions.addOption(no9png);
            DecodeOptions.addOption(usingDefaultFramework);
            DecodeOptions.addOption(showMoreRecognizableCharacters);
            DecodeOptions.addOption(fuckUnkownId);
            DecodeOptions.addOption(ignoreResDecodeError);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before("call(* org.apache.commons.cli.CommandLineParser.parse(..))" +
            "&& args(options, arguments, stopAtNonOption)" +
            "&& within(brut.apktool.Main)")
    public void before_parse(Options options, String[] arguments, boolean stopAtNonOption) {
        CommandLineParser parser = new PosixParser();
        CommandLine commandLine;

        try {
            commandLine = parser.parse(options, arguments, stopAtNonOption);
            if (commandLine.hasOption("lng") || commandLine.hasOption("language")) {
                String lngStr = commandLine.getOptionValue("lng");
                Locale locale = Locale.forLanguageTag(lngStr);
                if (locale.toString().isEmpty()) {
                    lngStr = lngStr.replaceAll("_", "-");
                    locale = Locale.forLanguageTag(lngStr);
                }
                MultiLanguageSupport.getInstance().setLang(locale);
            }
        } catch (Exception ex) {
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
    }
}
