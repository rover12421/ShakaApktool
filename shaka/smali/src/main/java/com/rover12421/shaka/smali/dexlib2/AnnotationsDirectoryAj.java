package com.rover12421.shaka.smali.dexlib2;

import com.google.common.collect.ImmutableSet;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jf.dexlib2.dexbacked.DexBackedAnnotation;

import java.util.Set;

/**
 * Created by rover12421 on 11/9/15.
 */
@Aspect
public class AnnotationsDirectoryAj {

    @Around("execution(* org.jf.dexlib2.dexbacked.util.AnnotationsDirectory.getAnnotations(..))")
    public Set<? extends DexBackedAnnotation> getAnnotations(ProceedingJoinPoint joinPoint) {
        try {
            return (Set<? extends DexBackedAnnotation>) joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            return ImmutableSet.of();
        }
    }
}
