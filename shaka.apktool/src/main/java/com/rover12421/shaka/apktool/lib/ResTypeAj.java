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
import com.rover12421.shaka.lib.LogHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rover12421 on 7/30/15.
 */
@Aspect
public class ResTypeAj {

    public final static Map<Integer, ResResSpec> MultipleSpecs = new HashMap<>();
    public final static Map<Integer, ResResSpec> AllSpecs = new HashMap<>();

    public static void addMultipleResResSpec(ResResSpec spec) {
        MultipleSpecs.put(spec.getId().id, spec);
    }

    public static void addAllResResSpec(ResResSpec spec) {
        AllSpecs.put(spec.getId().id, spec);
    }

    @Around("execution(* brut.androlib.res.data.ResType.addResSpec(..))" +
            "&& args(spec)")
    public void addResSpec(ProceedingJoinPoint joinPoint, ResResSpec spec) throws Throwable {
        ResType thiz = (ResType) joinPoint.getThis();

        addAllResResSpec(spec);

        ResResSpec exitsSpec = null;
        try {
            exitsSpec = thiz.getResSpec(spec.getName());
        } catch (Exception e) {
        }

        if (exitsSpec == null) {
            joinPoint.proceed(joinPoint.getArgs());
        } else {
            LogHelper.warning(String.format(
                    "Multiple res specs: %s/%s", thiz.getName(), spec.getName()));
            if (exitsSpec.getId() != spec.getId()) {
                addMultipleResResSpec(spec);
                addMultipleResResSpec(exitsSpec);
            }
        }
    }

    public static void addSpecToResType(ResResSpec spec) {
        ResType type = spec.getType();
        ResResSpec findSpec = null;
        try {
            findSpec = type.getResSpec(spec.getName());
        } catch (Exception e) {
        }
        if (findSpec == null) {
            try {
                type.addResSpec(spec);
            } catch (AndrolibException e) {
            }
        }
    }
}
