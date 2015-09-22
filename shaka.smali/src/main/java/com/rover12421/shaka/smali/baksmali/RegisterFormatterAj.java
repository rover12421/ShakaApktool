package com.rover12421.shaka.smali.baksmali;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jf.baksmali.Adaptors.RegisterFormatter;
import org.jf.util.IndentingWriter;

import java.io.IOException;

/**
 * Created by rover12421 on 9/22/15.
 * org.jf.baksmali.Adaptors.RegisterFormatter
 */
@Aspect
public class RegisterFormatterAj {
    @Around("execution(* org.jf.baksmali.Adaptors.RegisterFormatter.writeTo(..))" +
            "&& args(writer, register)")
    public void writeTo(ProceedingJoinPoint joinPoint, IndentingWriter writer, int register) throws Throwable {
        RegisterFormatter thiz = (RegisterFormatter) joinPoint.getThis();
        if (register > thiz.registerCount) {
            throw new IOException("register > registerCount");
        } else {
            joinPoint.proceed(joinPoint.getArgs());
        }
    }
}
