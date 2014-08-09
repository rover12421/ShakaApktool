package com.rover12421.shaka.apktool.AspjectJ.apktool_cli;

import brut.androlib.res.data.ResConfigFlags;
import brut.androlib.res.data.ResResource;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Map;

/**
 * Created by rover12421 on 8/2/14.
 */
@Aspect
public class ResResSpecAj {
    @Pointcut("execution(void brut.androlib.res.data.ResResSpec.addResource(..))" +
            "&& args(res, overwrite)")
    private void pointcut_addResource(ResResource res, boolean overwrite){}

    @Around("pointcut_addResource(res, overwrite)")
    public void usage_around(ProceedingJoinPoint joinPoint, ResResource res, boolean overwrite) {
        try {
            Map mResources = (Map) ReflectUtil.getFieldValue(joinPoint.getThis(), "mResources");
            ResConfigFlags flags = res.getConfig().getFlags();
            if (mResources.put(flags, res) != null && !overwrite) {
//                throw new AndrolibException(String.format(
//                        "Multiple resources: spec=%s, config=%s", this, flags));
                System.out.println(String.format("[-] Add Repeat Resource > ResResSpec addResource Multiple resources: spec=%s, config=%s", this, flags));
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
