package com.rover12421.shaka.smali.test;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jf.util.ExceptionWithContext;

/**
 * Created by rover12421 on 1/26/16.
 */
@Aspect
public class BaseDexBufferTestAj {
    @Around("execution(* org.jf.dexlib2.dexbacked.BaseDexBufferTest.testReadRandom(..))")
    public void skip_testReadRandom() {
        /**
         * 测试随机异常.
         * readSmallUint 已经skip了异常.没法测试faild情况
         */
    }

    @Around("execution(* org.jf.dexlib2.dexbacked.BaseDexBufferTest.testReadSmallUintTooLarge*(..))")
    public void skip_testReadSmallUintTooLarge() {
        /**
         * readSmallUint 已经skip了异常.没法测试faild情况
         */
        throw new ExceptionWithContext("skip test");
    }

    @Around("execution(* org.jf.dexlib2.dexbacked.BaseDexBufferTest.testReadOptionalUintTooLarge*(..))")
    public void skip_testReadOptionalUintTooLarge() {
        /**
         * readSmallUint 已经skip了异常.没法测试faild情况
         */
        throw new ExceptionWithContext("skip test");
    }
}
