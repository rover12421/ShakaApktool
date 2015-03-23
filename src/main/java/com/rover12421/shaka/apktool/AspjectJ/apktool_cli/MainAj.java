package com.rover12421.shaka.apktool.AspjectJ.apktool_cli;

import brut.androlib.Androlib;
import brut.androlib.ApkDecoder;
import brut.androlib.ApktoolProperties;
import com.rover12421.shaka.apktool.Main;
import com.rover12421.shaka.apktool.lib.MultiLanguageSupport;
import com.rover12421.shaka.apktool.lib.ShakaProperties;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import org.apache.commons.cli.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

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
        System.out.println();
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

        try {
            Options normalOptions = (Options) ReflectUtil.getFieldValue(brut.apktool.Main.class, "normalOptions");
            normalOptions.addOption(language);
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
}
