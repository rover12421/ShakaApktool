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

import brut.androlib.err.UndefinedResObject;
import brut.androlib.res.data.ResID;
import brut.androlib.res.data.ResPackage;
import brut.androlib.res.data.ResResSpec;
import brut.androlib.res.data.ResTypeSpec;
import com.rover12421.shaka.lib.LogHelper;
import com.rover12421.shaka.lib.ShakaDecodeOption;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by rover12421 on 4/21/15.
 */
@Aspect
public class ResPackageAj {

    @Around("execution(* brut.androlib.res.data.ResPackage.getResSpec(..))" +
            "&& args(resID)")
    public ResResSpec getResSpec(ProceedingJoinPoint joinPoint, ResID resID) throws Throwable {
        try {
            return (ResResSpec) joinPoint.proceed(joinPoint.getArgs());
        } catch (UndefinedResObject e) {
            if (ShakaDecodeOption.getInstance().isFuckUnkownId()) {
                ResPackage thiz = (ResPackage) joinPoint.getThis();
                return new ResResSpec(resID, String.format("[%08x]", resID.id), thiz, new ResTypeSpec("FuckUnkownId", thiz.getResTable(), thiz, (byte) (resID.id&0xFF), 0));
            } else {
                throw e;
            }
        }
    }

    @Around("execution(* brut.androlib.res.data.ResPackage.addResSpec(..))")
    public void addResSpec(ProceedingJoinPoint joinPoint) {
        try {
            joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            LogHelper.warning(throwable.getMessage());
        }
    }
}
