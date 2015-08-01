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
import brut.androlib.res.data.ResResource;
import brut.androlib.res.data.value.ResFileValue;
import brut.androlib.res.data.value.ResValue;
import com.rover12421.shaka.lib.LogHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rover12421 on 8/2/14.
 */
@Aspect
public class ResConfigAj {

    public final static Map<Integer, ResResource> MultopleResFileValue = new HashMap<>();

    //重复资源前缀
    public final static String MultipleSpec_Perfix = "_?m@";

    @Around("execution(void brut.androlib.res.data.ResConfig.addResource(..))" +
            "&& args(res, overwrite)")
    public void addResource(ProceedingJoinPoint joinPoint, ResResource res, boolean overwrite) throws Throwable {
        ResResSpec spec = res.getResSpec();

        ResResSpec resSpec = ResTypeAj.MultipleSpecs.get(spec.getId().id);
        if (resSpec != null) {
            //有重复的ResResSpec
            ResValue resValue = res.getValue();
            String rename;
            if (resValue instanceof ResFileValue) {
                ResFileValue fileValue = (ResFileValue) resValue;
                rename = fileValue.getPath().replaceAll("/|\\\\|\\.", "_");
                MultopleResFileValue.put(spec.getId().id, res);
            } else {
                rename = MultipleSpec_Perfix + spec.getId();
            }

            if (rename != null) {
//                LogHelper.warning("Rename ResResSpec " + spec.getName() + " to " + rename);
                ResResSpecAj.setName(spec, rename);
                ResTypeAj.addSpecToResType(spec);
            }
        }

        try {
            joinPoint.proceed(joinPoint.getArgs());
        } catch (AndrolibException e) {
            LogHelper.warning(e.getMessage());
        }
    }
}
