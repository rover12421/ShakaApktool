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

import brut.androlib.res.data.ResType;
import brut.androlib.res.data.value.ResFileValue;
import brut.androlib.res.data.value.ResIntBasedValue;
import brut.androlib.res.data.value.ResStringValue;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rover12421 on 7/31/15.
 */
@Aspect
public class ResValueFactoryAj {
    /**
     * 可能是文件的资源类型
     */
    private static final List<String> HavaFileTypes = Arrays.asList(
            "anim",
            "animator",
            "color",
            "drawable",
            "interpolator",
            "layout",
            "menu",
            "mipmap",
            "raw",
            "transition",
            "xml"
    );

    public static ResIntBasedValue factory(ResType mType, String value, int rawValue) {
        String typeName = mType.getName();
        if (HavaFileTypes.contains(typeName) &&
            (
                    !typeName.startsWith("@") ||
                    !typeName.startsWith("#") ||
                    !typeName.startsWith("?")
            )
        ) {
            return new ResFileValue(value, rawValue);
        }
//        if (value.startsWith("res/")) {
//            return new ResFileValue(value, rawValue);
//        }
        return new ResStringValue(value, rawValue);
    }
}
