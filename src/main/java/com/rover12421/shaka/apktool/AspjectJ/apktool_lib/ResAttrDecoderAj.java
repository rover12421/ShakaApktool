package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.androlib.res.data.ResPackage;
import brut.androlib.res.data.value.ResAttr;
import brut.androlib.res.data.value.ResScalarValue;
import brut.androlib.res.decoder.ResAttrDecoder;
import com.rover12421.shaka.apktool.util.ShakaRuntimeException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by rover12421 on 8/9/14.
 * brut.androlib.res.decoder.ResAttrDecoder
 */
@Aspect
public class ResAttrDecoderAj {

    @Around("execution(* brut.androlib.res.decoder.ResAttrDecoder.decode(..))" +
            "&& args(type, value, rawValue, attrResId)")
    public String decode_around(ProceedingJoinPoint joinPoint, int type, int value, String rawValue, int attrResId) {
        String result = null;
        try {
            ResAttrDecoder resAttrDecoder = (ResAttrDecoder) joinPoint.getThis();
            ResPackage mCurrentPackage = resAttrDecoder.getCurrentPackage();
            ResScalarValue resValue = mCurrentPackage.getValueFactory().factory(
                    type, value, rawValue);

            String decoded = null;
//            if (attrResId != 0) {
            if (attrResId > 0) {
//                ResAttr attr = (ResAttr) getCurrentPackage().getResTable()
                ResAttr attr = (ResAttr) mCurrentPackage.getResTable()
                        .getResSpec(attrResId).getDefaultResource().getValue();
                decoded = attr.convertToResXmlFormat(resValue);
            }

            result = decoded != null ? decoded : resValue.encodeAsResXmlAttr();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ShakaRuntimeException(e);
        }

        return result;
    }

}
