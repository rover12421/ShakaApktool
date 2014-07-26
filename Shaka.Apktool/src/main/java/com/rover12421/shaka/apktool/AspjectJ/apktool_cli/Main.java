package com.rover12421.shaka.apktool.AspjectJ.apktool_cli;

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
public class Main {

    @Pointcut(value = "execution(void brut.apktool.Main.usage(org.apache.commons.cli.CommandLine))" +
                      "&& args(commandLine)")
    private void pointcut_usage(CommandLine commandLine) {}

    @Around(value = "pointcut_usage(org.apache.commons.cli.CommandLine)" +
            "&& args(commandLine)" +
            "&& !within(com.rover12421.shaka.apktool.AspjectJ.apktool_cli.Main +)")
    public void usage_around(final ProceedingJoinPoint joinPoint, CommandLine commandLine) {
        System.out.println("ShakaApktool (aspectj using clazz)" + ShakaProperties.getVersion() + " >>>");
        try {
            joinPoint.proceed(new Object[]{commandLine});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Pointcut(value = "execution(void brut.apktool.Main._version())")
    private void pointcut_version(){}

    @Around(value = "pointcut_version()" +
            "&& !within(com.rover12421.shaka.apktool.AspjectJ.apktool_cli.Main +)")
    public void version_around(ProceedingJoinPoint joinPoint) {
        System.out.println("this version function, using aspectj catch it....");
    }
}
