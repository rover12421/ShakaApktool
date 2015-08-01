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
package com.rover12421.shaka.apktool.lib;

import brut.androlib.AndrolibException;
import brut.androlib.res.data.ResResSpec;
import brut.androlib.res.data.ResType;
import brut.androlib.res.decoder.AXmlResourceParser;
import brut.androlib.res.decoder.ResAttrDecoder;
import brut.androlib.res.decoder.StringBlock;
import com.rover12421.shaka.lib.LogHelper;
import com.rover12421.shaka.lib.ReflectUtil;
import com.rover12421.shaka.lib.ShakaDecodeOption;
import com.rover12421.shaka.lib.ShakaRuntimeException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by rover12421 on 4/3/15.
 */
@Aspect
public class AXmlResourceParserAj {
    private static final int ATTRIBUTE_IX_NAMESPACE_URI = 0,
            ATTRIBUTE_IX_NAME = 1, ATTRIBUTE_IX_VALUE_STRING = 2,
            ATTRIBUTE_IX_VALUE_TYPE = 3, ATTRIBUTE_IX_VALUE_DATA = 4,
            ATTRIBUTE_LENGTH = 5;

    private int getAttributeOffset(AXmlResourceParser parser, int index) {
        try {
            return (int) ReflectUtil.getMethod(parser, "getAttributeOffset", int.class).invoke(parser, index);
        } catch (Exception e) {
            throw new ShakaRuntimeException(e);
        }
    }

    private int[] m_attributes(AXmlResourceParser parser) {
        try {
            return (int[]) ReflectUtil.getFieldValue(parser, "m_attributes");
        } catch (Exception e) {
            throw new ShakaRuntimeException(e);
        }
    }

    private StringBlock m_strings(AXmlResourceParser parser) {
        try {
            return (StringBlock) ReflectUtil.getFieldValue(parser, "m_strings");
        } catch (Exception e) {
            throw new ShakaRuntimeException(e);
        }
    }

    private String android_ns(AXmlResourceParser parser) {
        try {
            return (String) ReflectUtil.getFieldValue(parser, "android_ns");
        } catch (Exception e) {
            throw new ShakaRuntimeException(e);
        }
    }

    private ResAttrDecoder mAttrDecoder(AXmlResourceParser parser) {
        try {
            return (ResAttrDecoder) ReflectUtil.getFieldValue(parser, "mAttrDecoder");
        } catch (Exception e) {
            throw new ShakaRuntimeException(e);
        }
    }

    @Around("execution(* brut.androlib.res.decoder.AXmlResourceParser.getAttributeNamespace(..))" +
            "&& args(index)")
    public String getAttributeNamespace(ProceedingJoinPoint joinPoint, int index) {
        AXmlResourceParser parser = (AXmlResourceParser) joinPoint.getThis();

        int offset = getAttributeOffset(parser, index);
        int namespace = m_attributes(parser)[offset + ATTRIBUTE_IX_NAMESPACE_URI];
        if (namespace == -1) {
            return "";
        }

        String value = m_strings(parser).getString(namespace);

        try {
            String name = mAttrDecoder(parser).decodeManifestAttr(parser.getAttributeNameResource(index));
            if (name == null && value.equalsIgnoreCase(android_ns(parser))) {
                m_attributes(parser)[offset + ATTRIBUTE_IX_NAMESPACE_URI] = -1;
                value = "";
            } else if (name != null && value.isEmpty()) {
                value = android_ns(parser);
            }
        } catch (AndrolibException e) {
            value = "";
        }

        return value;
    }

    @Around("execution(* brut.androlib.res.decoder.AXmlResourceParser.getAttributeName(..))" +
            "&& args(index)")
    public String getAttributeName(ProceedingJoinPoint joinPoint, int index) {
        AXmlResourceParser parser = (AXmlResourceParser) joinPoint.getThis();
        String value = null;
        try {
            value = mAttrDecoder(parser).decodeManifestAttr(parser.getAttributeNameResource(index));
        } catch (AndrolibException e) {
        }



        if (value == null) {
            int offset = getAttributeOffset(parser, index);
            int name = m_attributes(parser)[offset + ATTRIBUTE_IX_NAME];
            if (name == -1) {
                value = "";
            } else {
                value = m_strings(parser).getString(name);
            }
        } else {
            if (ShakaDecodeOption.getInstance().isXmlAttributeNameCorrect()) {
                String value2 = "";
                int offset = getAttributeOffset(parser, index);
                int name = m_attributes(parser)[offset + ATTRIBUTE_IX_NAME];
                if (name != -1) {
                    value2 = m_strings(parser).getString(name);
                }

                if (value2.trim().length() > 0 && !value.equals(value2) && !value2.equals("name")) {
                    LogHelper.warning("Xml attribute name correct : " + value + " to " + value2);
                    if (value.startsWith(ResConfigAj.MultipleSpec_Perfix)) {
                        try {
                            // ResConfigAj.MultipleSpec_Perfix0xId
                            String mId = value.substring(ResConfigAj.MultipleSpec_Perfix.length() + 2);
                            int id = Integer.parseInt(mId, 16);
                            ResResSpec spec = ResTypeAj.AllSpecs.get(id);
                            if (spec != null && spec.getName().equals(value)) {
                                ResResSpecAj.setName(spec, value2);
                            }
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                    value = value2;
                }
            }
        }
        return value;
    }
}
