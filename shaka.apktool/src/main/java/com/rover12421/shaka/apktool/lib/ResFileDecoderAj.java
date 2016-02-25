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
import brut.androlib.res.data.value.ResFileValue;
import brut.androlib.res.decoder.ResFileDecoder;
import brut.directory.Directory;
import brut.directory.PathNotExist;
import com.rover12421.shaka.lib.LogHelper;
import com.rover12421.shaka.lib.reflect.Reflect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

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

    /**
     * 是否记录需要重新decode的资源
     */
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
        /**
         * SystemUI
         * menu/sidebar_popup_menu_fill 的ResFileValue是空的
         */
        ResFileValue fileValue = (ResFileValue) res.getValue();

        if (fileValue.getPath() == null) {
            String outResName = res.getFilePath();
            String inPath = outResName + ".";
            for (String file : inDir.getFiles(true)) {
                file.replace("\\", "/");
                if (file.startsWith(inPath)) {
                    inPath = "res/" + file;
                    LogHelper.warning("Find null res path. Fix to : " + inPath);
                    Reflect.on(fileValue).set("mPath", inPath);
                    break;
                }
            }
        }

        if (!DonotRecord) {
            CanNeedReDecodeFiles.add(new ReDecodeResFile((ResFileDecoder) joinPoint.getThis(), res, inDir, outDir));
        }
    }

    @Around("execution(* brut.androlib.res.decoder.ResFileDecoder.decode(..))" +
            "&& args(inDir, inFileName, outDir, outFileName, decoder)")
    public void decode(ProceedingJoinPoint joinPoint, Directory inDir, String inFileName, Directory outDir,
                       String outFileName, String decoder) throws Throwable {
        if (inFileName.startsWith("/")) {
            //inFileName是完整路径,说明不是标准的资源目录结构
            inFileName = inFileName.substring(1);
            inDir = AndrolibResourcesAj.getApkFile().getDirectory();
            if (!inFileName.equals(outFileName)) {
                AndrolibAj.metaInfo.addDecodeFileMap(inFileName, "res/" + outFileName);
            }
        } else {
            if (!inFileName.equals(outFileName)) {
                AndrolibAj.metaInfo.addDecodeFileMap("res/" + inFileName, "res/" + outFileName);
            }
        }

        if (inDir == null) {
            //没有资源目录,skip!
            LogHelper.warning("No resources directory, skip res : " + inFileName);
            return;
        }

        /**
         * .9.xml 被当成 nine patch images 已经在 099cc0fcb3baec56f0ba3150c82dda56a63501d5 修复
         * 方法类似,他用的inFileName做判断
         * 这里不再处理,移除
         */
//        /**
//         * 解决 .9.xml 被当成 nine patch images 处理
//         */
//        if (outFileName.endsWith(".xml") && decoder.equals("9patch")) {
//            LogHelper.warning(String.format("Correct decoder [%s] : %s >>> xml", outFileName, decoder));
//            decoder = "xml";
//        }
        try {
            joinPoint.proceed(new Object[]{inDir, inFileName, outDir, outFileName, decoder});
        } catch (AndrolibException e) {
            Throwable cause = e.getCause();
            if (cause != null && (cause instanceof PathNotExist)) {
                LogHelper.warning("File not exist, Try using [fnd|fuck-not-defined-res] parameter when build the project : " + inFileName);
                return;
            }
            if (outFileName.endsWith(".9.png")) {
                /**
                 * 如果异常的文件 9patch 图片,使用raw方式copy一次
                 */
                LogHelper.info("Decode 9patch exception, Using raw decode try again : " + inFileName);
                joinPoint.proceed(new Object[]{inDir, inFileName, outDir, outFileName, "raw"});
            }
        }
    }

    @Before("execution(* brut.androlib.res.decoder.ResFileDecoder.decodeManifest(..))" +
            "&& args(inDir, inFileName, outDir, outFileName)")
    public void decodeManifest(Directory inDir, String inFileName,
                               Directory outDir, String outFileName) {
        if (!inFileName.equals(outFileName)) {
            AndrolibAj.metaInfo.addDecodeFileMap(inFileName, outFileName);
        }
    }

}
