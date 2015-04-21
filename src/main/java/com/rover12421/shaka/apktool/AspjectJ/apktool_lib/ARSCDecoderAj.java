package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import com.rover12421.shaka.apktool.lib.ShakaDecodeOption;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by rover12421 on 8/16/14.
 */
@Aspect
public class ARSCDecoderAj {

    @Around("execution(* brut.androlib.res.decoder.ARSCDecoder.addMissingResSpecs())")
    public void addMissingResSpecs(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!ShakaDecodeOption.getInstance().isFuckUnkownId()) {
            joinPoint.proceed(joinPoint.getArgs());
        }
    }

}
