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

import brut.androlib.res.data.value.ResFileValue;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by rover12421 on 7/31/15.
 */
@Aspect
public class ResFileValueAj {
    @Around("execution(* brut.androlib.res.data.value.ResFileValue.getStrippedPath())")
    public String getStrippedPath(ProceedingJoinPoint joinPoint) {
        try {
            return (String) joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            //不是标准res目录结构,返回完整路径
            //添加"/"前缀,后面用来区分是完整路径,还是资源相对路径
            return "/" + ((ResFileValue)joinPoint.getThis()).getPath();
        }
    }
}
