package com.rover12421.shaka.apktool.lib;

import com.rover12421.shaka.lib.ShakaDecodeOption;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by rover12421 on 11/20/15.
 */
@Aspect
public class ResReferenceValueAj {
    @Around("execution(* brut.androlib.res.data.value.ResReferenceValue.encodeAsResXml())")
    public String encodeAsResXml(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return (String) joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable e) {
            if (ShakaDecodeOption.getInstance().isIgnoreResDecodeError()) {
                return "@null";
            } else {
                throw e;
            }
        }
    }
}
