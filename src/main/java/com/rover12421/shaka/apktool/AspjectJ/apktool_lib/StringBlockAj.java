package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import com.rover12421.shaka.apktool.util.LogHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by rover12421 on 4/1/15.
 */
@Aspect
public class StringBlockAj {
    @Around("execution(* brut.androlib.res.decoder.StringBlock.outputStyleTag(..))" +
            "&& args(tag, builder, close)")
    public void outputStyleTag(ProceedingJoinPoint joinPoint, String tag, StringBuilder builder, boolean close) throws Throwable {
        String newTag = tag.replaceAll(";+", ";");
        if (!newTag.equals(tag)) {
            LogHelper.getLogger().info("outputStyleTag " + tag + " >>> " + newTag);
        }

        joinPoint.proceed(new Object[]{newTag, builder, close});
    }
}
