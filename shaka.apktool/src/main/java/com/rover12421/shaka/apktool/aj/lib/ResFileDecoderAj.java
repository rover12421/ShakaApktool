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

import brut.directory.Directory;
import com.rover12421.shaka.apktool.util.LogHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by rover12421 on 4/3/15.
 */
@Aspect
public class ResFileDecoderAj {
    @Around("execution(* brut.androlib.res.decoder.ResFileDecoder.decode(..))" +
            "&& args(inDir, inFileName, outDir, outFileName, decoder)")
    public void decode(ProceedingJoinPoint joinPoint, Directory inDir, String inFileName, Directory outDir,
                       String outFileName, String decoder) throws Throwable {
        if (!inFileName.equals(outFileName)) {
            AndrolibAj.DecodeFileMaps.put("res/" + inFileName, "res/" + outFileName);
        }

        /**
         * 解决 .9.xml 被当成 nine patch images 处理
         * 如果
         */
        if (outFileName.endsWith(".xml") && decoder.equals("9patch")) {
            LogHelper.getLogger().warning(String.format("Correct decoder [%s] : %s >>> xml", outFileName, decoder));
            decoder = "xml";
            joinPoint.proceed(new Object[]{inDir, inFileName, outDir, outFileName, decoder});
        } else {
            joinPoint.proceed(joinPoint.getArgs());
        }
    }

    @Before("execution(* brut.androlib.res.decoder.ResFileDecoder.decodeManifest(..))" +
            "&& args(inDir, inFileName, outDir, outFileName)")
    public void decodeManifest(Directory inDir, String inFileName,
                               Directory outDir, String outFileName) {
        if (!inFileName.equals(outFileName)) {
            AndrolibAj.DecodeFileMaps.put(inFileName, outFileName);
        }
    }

}
