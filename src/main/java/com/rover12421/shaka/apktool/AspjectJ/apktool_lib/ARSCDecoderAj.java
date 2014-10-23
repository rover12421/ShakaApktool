package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.androlib.res.decoder.StringBlock;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by rover12421 on 8/16/14.
 */
@Aspect
public class ARSCDecoderAj {

//    @After("set(brut.androlib.res.decoder.StringBlock brut.androlib.res.decoder.ARSCDecoder.mSpecNames)" +
//            "&& args(mSpecNames)")
//    public void mSpecNames_after(StringBlock mSpecNames) {
//        try {
//            System.out.println("mSpecNames.m_isUTF8 = " + ReflectUtil.getFieldValue(mSpecNames, "m_isUTF8"));
//            ReflectUtil.setFieldValue(mSpecNames, "m_isUTF8", true);
//            System.out.println("mSpecNames.m_isUTF8 = " + ReflectUtil.getFieldValue(mSpecNames, "m_isUTF8"));
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }

}
