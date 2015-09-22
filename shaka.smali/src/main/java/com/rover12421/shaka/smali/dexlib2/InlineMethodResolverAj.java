package com.rover12421.shaka.smali.dexlib2;

import com.rover12421.shaka.smali.baksmali.InlineMethodResolverFromFile;
import com.rover12421.shaka.smali.baksmali.ShakaBaksmaliOption;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jf.dexlib2.analysis.InlineMethodResolver;

import java.io.File;

/**
 * Created by rover12421 on 9/13/15.
 */
@Aspect
public class InlineMethodResolverAj {

    @Around("execution(* org.jf.dexlib2.analysis.InlineMethodResolver.createInlineMethodResolver(..))")
    public InlineMethodResolver createInlineMethodResolver(ProceedingJoinPoint joinPoint) throws Throwable {
        String file = ShakaBaksmaliOption.getInlineMethodResolverFile();
        if (file != null && new File(file).exists()) {
            return new InlineMethodResolverFromFile(file);
        }

        return (InlineMethodResolver) joinPoint.proceed(joinPoint.getArgs());
    }
}
