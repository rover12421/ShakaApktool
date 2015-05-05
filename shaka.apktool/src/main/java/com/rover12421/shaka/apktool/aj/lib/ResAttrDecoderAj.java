/**
 *  Copyright 2015 Rover12421 <rover12421@163.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.rover12421.shaka.apktool.aj.lib;

import brut.androlib.AndrolibException;
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
        try {
            ResAttrDecoder resAttrDecoder = (ResAttrDecoder) joinPoint.getThis();
            ResPackage mCurrentPackage = resAttrDecoder.getCurrentPackage();
            ResScalarValue resValue = mCurrentPackage.getValueFactory().factory(
                    type, value, rawValue);

            String decoded = null;
//            if (attrResId != 0) {
            if (attrResId > 0) {
//                ResAttr attr = (ResAttr) getCurrentPackage().getResTable()
                try {
                    ResAttr attr = (ResAttr) mCurrentPackage.getResTable()
                            .getResSpec(attrResId).getDefaultResource().getValue();
                    decoded = attr.convertToResXmlFormat(resValue);
                } catch (AndrolibException e) {
//                e.printStackTrace();
                }
            }

            return decoded != null ? decoded : resValue.encodeAsResXmlAttr();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ShakaRuntimeException(e);
        }
    }

}
