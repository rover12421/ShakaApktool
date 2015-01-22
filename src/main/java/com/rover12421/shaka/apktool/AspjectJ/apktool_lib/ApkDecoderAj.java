package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import com.rover12421.shaka.apktool.lib.ShakaProperties;
import com.rover12421.shaka.apktool.util.LogHelper;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by rover12421 on 8/9/14.
 * brut.androlib.ApkDecoder
 */
@Aspect
public class ApkDecoderAj {

    @Before("execution(void brut.androlib.ApkDecoder.decode())")
    public void decode_before() {
        LogHelper.getLogger().info("Using ShakaApktool " + ShakaProperties.getVersion());
    }
}
