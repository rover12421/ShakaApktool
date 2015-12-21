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
import brut.androlib.res.decoder.AXmlResourceParser;
import com.rover12421.shaka.apktool.util.Global;
import com.rover12421.shaka.lib.LogHelper;
import com.rover12421.shaka.lib.ShakaDecodeOption;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.xmlpull.v1.XmlPullParserException;

/**
 * Created by rover12421 on 4/3/15.
 */
@Aspect
public class AXmlResourceParserAj {
    private static final int ATTRIBUTE_IX_NAMESPACE_URI = 0,
            ATTRIBUTE_IX_NAME = 1, ATTRIBUTE_IX_VALUE_STRING = 2,
            ATTRIBUTE_IX_VALUE_TYPE = 3, ATTRIBUTE_IX_VALUE_DATA = 4,
            ATTRIBUTE_LENGTH = 5;

    private final static String android_ns = "http://schemas.android.com/apk/res/android";

    @Around("execution(* brut.androlib.res.decoder.AXmlResourceParser.getAttributeNamespace(..))" +
            "&& args(index)")
    public String getAttributeNamespace(ProceedingJoinPoint joinPoint, int index) {
        AXmlResourceParser parser = (AXmlResourceParser) joinPoint.getThis();

        int offset = parser.getAttributeOffset0(index);
        int namespace = parser.getAttributes()[offset + ATTRIBUTE_IX_NAMESPACE_URI];
        if (namespace == -1) {
            return "";
        }

        String value = parser.getStrings0().getString(namespace);
        if (value == null || value.isEmpty()) {
            int resId = parser.getAttributeNameResource(index);
            if (resId > 0) {
                value = android_ns;
                int depth = parser.getDepth();
                try {
                    int nameSpaceCount = parser.getNamespaceCount(depth);
                    if (nameSpaceCount == 0) {
                        // 没有命名空间,不需要前缀
                        value = "";
                    } else if (nameSpaceCount == 1) {
                        // 只有一个命名空间的时候,直接返回这个命名空间
                        value = parser.getNamespaceUri(0);
                    } else {
                        int packageId = (resId >> 24) & 0xFF;
                        if (packageId >= 0x7f) {    //这个判断可能会不准确,等遇到非正常样本再修改
                            // 系统包,使用"android"命名空间, 非系统包需要选择
                            if (nameSpaceCount == 2) {
                                // 不使用非"android"的命名空间
                                value = parser.getNamespaceUri(0);
                                if (value.equalsIgnoreCase(android_ns)) {
                                    value = parser.getNamespaceUri(1);
                                }
                            } else {
                                // 多于两个的时候,尝试使用本身的命名空间
                                LogHelper.warning("Namespace quantities greater than 2, " +
                                        "may not select the correct namespace, " +
                                        "attempt to automatically select. 0x" + Integer.toHexString(resId));
                                String pkg_ns = "http://schemas.android.com/apk/res/" + Global.getPackageName();
                                for (int i=0; i<nameSpaceCount; i++) {
                                    String ns = parser.getNamespaceUri(i);
                                    if (ns.equalsIgnoreCase("http://schemas.android.com/apk/res-auto")
                                            || ns.equalsIgnoreCase(pkg_ns)) {
                                        value = ns;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            } else {
                value = "";
            }
        }

        return value;
    }

    @Around("execution(* brut.androlib.res.decoder.AXmlResourceParser.getAttributeName(..))" +
            "&& args(index)")
    public String getAttributeName(ProceedingJoinPoint joinPoint, int index) {
        AXmlResourceParser parser = (AXmlResourceParser) joinPoint.getThis();
        String value = null;
        try {
            value = parser.getAttrDecoder().decodeManifestAttr(parser.getAttributeNameResource(index));
        } catch (AndrolibException e) {
        }

        if (value == null) {
            int offset = parser.getAttributeOffset0(index);
            int name = parser.getAttributes()[offset + ATTRIBUTE_IX_NAME];
            if (name == -1) {
                value = "";
            } else {
                value = parser.getStrings0().getString(name);
            }
        } else {
            String newName = "";
            int offset = parser.getAttributeOffset0(index);
            int name = parser.getAttributes()[offset + ATTRIBUTE_IX_NAME];
            if (name != -1) {
                newName = parser.getStrings0().getString(name);
            }

            if (newName.trim().length() > 0 && !value.equals(newName) && !newName.equals("name")) {
                if (ShakaDecodeOption.getInstance().isXmlAttributeNameCorrect()) {
                    LogHelper.warning("Xml attribute name correct : " + value + " to " + newName);
                    int resId = parser.getAttributeNameResource(index);
                    if (resId > 0) {
                        ResResSpec spec = ResTypeSpecAj.AllSpecs.get(resId);
                        if (spec != null && spec.getName().equals(value)) {
                            try {
                                ResResSpecAj.setName(spec, newName);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (value.startsWith(ResTypeAj.MultipleSpec_Perfix)) {
                        try {
                            // ResConfigAj.MultipleSpec_Perfix0xId
                            String mId = value.substring(ResTypeAj.MultipleSpec_Perfix.length() + 2);
                            int id = Integer.parseInt(mId, 16);
                            ResResSpec spec = ResTypeSpecAj.AllSpecs.get(id);
                            if (spec != null && spec.getName().equals(value)) {
                                ResResSpecAj.setName(spec, newName);
                            }
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                    value = newName;
                } else {
                    // 不自动纠正,提示下
                    LogHelper.info("You can use [-xn|--xml-attribute-name-correct] parameter to correct the xml attribute name : " + value + " to " + newName);
                }
            }

        }
        return value;
    }
}
