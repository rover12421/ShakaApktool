package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.androlib.res.data.ResPackage;
import brut.androlib.res.data.value.ResAttr;
import brut.androlib.res.data.value.ResScalarValue;
import brut.androlib.res.decoder.ResAttrDecoder;
import com.rover12421.shaka.apktool.util.ShakaRuntimeException;

/**
 * Created by rover12421 on 1/6/14.
 * brut.androlib.res.decoder.ResAttrDecoder
 */
public aspect ResAttrDecoderAj {

    pointcut decodePointcut(int type, int value, String rawValue, int attrResId)
            : execution(String brut.androlib.res.decoder.ResAttrDecoder.decode(int, int, String, int))
            && args(type, value, rawValue, attrResId);

    String around(int type, int value, String rawValue, int attrResId)
            : decodePointcut(type, value, rawValue, attrResId)
            && !within(ResAttrDecoderAj +) {

        String result = null;
        try {
            ResAttrDecoder resAttrDecoder = (ResAttrDecoder) thisJoinPoint.getThis();
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
