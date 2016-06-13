package com.rover12421.shaka.apktool.lib;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by rover12421 on 9/22/15.
 *
 * brut.androlib.res.xml.ResXmlEncoders
 */
@Aspect
public class ResXmlEncodersAj {

    @Around("execution(* brut.androlib.res.xml.ResXmlEncoders.encodeAsXmlValue(..))" +
            "&& args(str)")
    public String encodeAsXmlValue(ProceedingJoinPoint joinPoint, String str) throws Throwable {
        if (str == null) {
            return "";
        }
        return (String) joinPoint.proceed(joinPoint.getArgs());
    }
}
