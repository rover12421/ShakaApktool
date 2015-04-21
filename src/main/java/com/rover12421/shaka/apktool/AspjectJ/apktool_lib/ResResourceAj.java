package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.androlib.res.data.value.ResValue;
import com.rover12421.shaka.apktool.lib.ShakaDecodeOption;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by rover12421 on 4/21/15.
 */
@Aspect
public class ResResourceAj {

    @Around("execution(* brut.androlib.res.data.ResResource.replace(..))" +
            "&& args(value)")
    public void replace(ProceedingJoinPoint joinPoint, ResValue value) throws Throwable {
        if (!ShakaDecodeOption.getInstance().isIgnoreResDecodeError()) {
            joinPoint.proceed(joinPoint.getArgs());
        }
    }
}
