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
import brut.androlib.res.data.ResResource;
import brut.androlib.res.decoder.ResFileDecoder;
import brut.directory.Directory;
import com.rover12421.shaka.lib.LogHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rover12421 on 4/3/15.
 */
@Aspect
public class ResFileDecoderAj {
    public static final List<ReDecodeResFile> CanNeedReDecodeFiles = new ArrayList<>();
    public static boolean NeedReDecodeFiles = false;

    public class ReDecodeResFile {
        private final ResFileDecoder decoder;
        private final ResResource res;
        private final Directory inDir;
        private final Directory outDir;

        public ReDecodeResFile(ResFileDecoder decoder, ResResource res, Directory inDir, Directory outDir) {
            this.decoder = decoder;
            this.res = res;
            this.inDir = inDir;
            this.outDir = outDir;
        }

        public void decode() throws AndrolibException {
            decoder.decode(res, inDir, outDir);
        }
    }

    public static boolean DonotRecord = false;
    public static void ReDecodeFiles() throws AndrolibException {
        if (NeedReDecodeFiles) {
            DonotRecord = true;
            LogHelper.info("Re Decoding file-resources...");
            for (ReDecodeResFile resFile : CanNeedReDecodeFiles) {
                resFile.decode();
            }
            DonotRecord = false;
        }
        CanNeedReDecodeFiles.clear();
    }

    @Before("execution(* brut.androlib.res.decoder.ResFileDecoder.decode(..))" +
            "&& args(res, inDir, outDir)")
    public void decode_before(JoinPoint joinPoint, ResResource res, Directory inDir, Directory outDir) {
        if (DonotRecord) {
            return;
        }
        CanNeedReDecodeFiles.add(new ReDecodeResFile((ResFileDecoder) joinPoint.getThis(), res, inDir, outDir));
    }

    @Around("execution(* brut.androlib.res.decoder.ResFileDecoder.decode(..))" +
            "&& args(inDir, inFileName, outDir, outFileName, decoder)")
    public void decode(ProceedingJoinPoint joinPoint, Directory inDir, String inFileName, Directory outDir,
                       String outFileName, String decoder) throws Throwable {
        if (inDir == null) {
            //inDir == null,说明不是标准的资源目录结构
            inDir = AndrolibResourcesAj.getApkFile().getDirectory();
            if (!inFileName.equals(outFileName)) {
                AndrolibAj.DecodeFileMaps.put(inFileName, "res/" + outFileName);
            }
        } else {
            if (!inFileName.equals(outFileName)) {
                AndrolibAj.DecodeFileMaps.put("res/" + inFileName, "res/" + outFileName);
            }
        }

        /**
         * 解决 .9.xml 被当成 nine patch images 处理
         * 如果
         */
        if (outFileName.endsWith(".xml") && decoder.equals("9patch")) {
            LogHelper.warning(String.format("Correct decoder [%s] : %s >>> xml", outFileName, decoder));
            decoder = "xml";
        }
        joinPoint.proceed(new Object[]{inDir, inFileName, outDir, outFileName, decoder});
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
