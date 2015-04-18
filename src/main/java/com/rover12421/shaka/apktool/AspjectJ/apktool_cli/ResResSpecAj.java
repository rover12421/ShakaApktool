package com.rover12421.shaka.apktool.AspjectJ.apktool_cli;

import brut.androlib.AndrolibException;
import brut.androlib.res.data.ResResource;
import com.rover12421.shaka.apktool.util.LogHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by rover12421 on 8/2/14.
 */
@Aspect
public class ResResSpecAj {

    @Around("execution(void brut.androlib.res.data.ResResSpec.addResource(..))" +
            "&& args(res, overwrite)")
    public void addResource(ProceedingJoinPoint joinPoint, ResResource res, boolean overwrite) throws Throwable {
        try {
            joinPoint.proceed(joinPoint.getArgs());
        } catch (AndrolibException e) {
                LogHelper.getLogger().warning("Add Repeat Resource : " + e.getMessage());
        }
    }
}
