package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import com.rover12421.shaka.apktool.lib.ShakaProperties;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.logging.Logger;

/**
 * Created by rover12421 on 8/9/14.
 * brut.androlib.ApkDecoder
 */
@Aspect
public class ApkDecoderAj {

    @Pointcut("execution(void brut.androlib.ApkDecoder.decode())")
    private void pointcut_decode() {}

    @Before("pointcut_decode()")
    public void decode_before(JoinPoint joinPoint) {
        try {
            Logger LOGGER = (Logger) ReflectUtil.getFieldValue(joinPoint.getThis(), "LOGGER");
            LOGGER.info("Using ShakaApktool " + ShakaProperties.getVersion());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
