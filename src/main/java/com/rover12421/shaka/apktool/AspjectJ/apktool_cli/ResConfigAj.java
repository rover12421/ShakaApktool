package com.rover12421.shaka.apktool.AspjectJ.apktool_cli;

import brut.androlib.AndrolibException;
import brut.androlib.res.data.ResResource;
import com.rover12421.shaka.apktool.util.LogHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by rover12421 on 8/2/14.
 */
@Aspect
public class ResConfigAj {
    @Pointcut("execution(void brut.androlib.res.data.ResConfig.addResource(..))" +
            "&& args(res, overwrite)")
    private void pointcut_addResource(ResResource res, boolean overwrite){}

    @Around("pointcut_addResource(res, overwrite)")
    public void usage_around(ProceedingJoinPoint joinPoint, ResResource res, boolean overwrite) {
        try {
            try {
                joinPoint.proceed(new Object[]{res, overwrite});
            } catch (AndrolibException e) {
                LogHelper.getLogger().warning("Add Repeat Resource : " + e.getMessage());
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
