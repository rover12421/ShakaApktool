package com.rover12421.shaka.smali.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by rover12421 on 11/19/15.
 */
@Aspect
public class BaseDexReaderLeb128TestAj {
    @Around("execution(* org.jf.dexlib2.dexbacked.BaseDexReaderLeb128Test.performFailureTest(..))")
    public void performFailureTest(ProceedingJoinPoint joinPoint) {
        try {
            joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable e) {
            /**
             * 为了支持方向搜索,这里是没有异常的
             */
        }
    }
}
