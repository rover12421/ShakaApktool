package com.rover12421.shaka.apktool.lib;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by rover12421 on 8/7/15.
 */
@Aspect
public class LoggerAj {

    @Around("call(* java.util.logging.Logger.info(..)) " +
            "&& args(msg)" +
            "&& (" +
            "withincode(* brut.androlib.Androlib.build(..))" +
            "|| withincode(* brut.androlib.ApkDecoder.decode(..))" +
            ")")
    public void info(ProceedingJoinPoint joinPoint, String msg) {
        if (!msg.contains("Apktool")) {
            try {
                joinPoint.proceed(joinPoint.getArgs());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
