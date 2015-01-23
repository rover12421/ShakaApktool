package com.rover12421.shaka.apktool.AspjectJ.apktool_cli;

import brut.androlib.Androlib;
import brut.androlib.ApktoolProperties;
import com.rover12421.shaka.apktool.lib.ShakaProperties;
import org.apache.commons.cli.CommandLine;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

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
}
