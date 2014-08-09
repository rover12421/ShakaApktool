package com.rover12421.shaka.apktool.AspjectJ.apktool_cli;

import brut.androlib.res.data.ResResSpec;
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
public class ResConfigAj {
    @Pointcut("execution(void brut.androlib.res.data.ResConfig.addResource(..))" +
            "&& args(res, overwrite)")
    private void pointcut_addResource(ResResource res, boolean overwrite){}

    @Around("pointcut_addResource(res, overwrite)")
    public void usage_around(ProceedingJoinPoint joinPoint, ResResource res, boolean overwrite) {
        try {
            Map mResources = (Map) ReflectUtil.getFieldValue(joinPoint.getThis(), "mResources");
            ResResSpec spec = res.getResSpec();
            if (mResources.put(spec, res) != null && !overwrite) {
//            throw new AndrolibException(String.format(
//                    "Multiple resources: spec=%s, config=%s", spec, this));
                System.out.println(String.format("[-] Add Repeat Resource > ResConfig addResource Multiple resources: spec=%s, config=%s", spec, joinPoint.getThis()));
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
