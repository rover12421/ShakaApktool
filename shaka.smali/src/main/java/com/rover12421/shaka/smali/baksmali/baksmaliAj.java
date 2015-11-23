package com.rover12421.shaka.smali.baksmali;

import com.rover12421.shaka.lib.reflect.Reflect;
import com.rover12421.shaka.smali.util.NIOIndentingWriter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jf.util.IndentingWriter;

import java.io.*;

/**
 * Created by rover12421 on 11/24/15.
 */
@Aspect
public class baksmaliAj {
    @Around("call(org.jf.util.IndentingWriter.new(..))" +
            "&& args(writer)" +
            "&& withincode(* org.jf.baksmali.baksmali.disassembleClass(..))")
    public IndentingWriter IndentingWriter_new(ProceedingJoinPoint joinPoint, Writer writer) throws Throwable {
        if (writer instanceof BufferedWriter) {
            Writer out = Reflect.on(writer).get("out");
            if (out instanceof OutputStreamWriter) {
                Object lock = Reflect.on(out).get("lock");
                if (lock instanceof FileOutputStream) {
                    return new NIOIndentingWriter((FileOutputStream) lock);
                }
            }
        }
        return (IndentingWriter) joinPoint.proceed(joinPoint.getArgs());
    }
}
