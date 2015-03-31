package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.androlib.Androlib;
import brut.androlib.AndrolibException;
import brut.androlib.res.AndrolibResources;
import brut.androlib.res.decoder.AXmlResourceParser;
import brut.androlib.res.decoder.ResFileDecoder;
import brut.androlib.res.decoder.ResRawStreamDecoder;
import brut.androlib.res.decoder.ResStreamDecoderContainer;
import brut.util.Duo;
import com.rover12421.shaka.apktool.lib.ShakaDecodeOption;
import com.rover12421.shaka.apktool.util.LogHelper;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import com.rover12421.shaka.apktool.util.ShakaRuntimeException;
import org.apache.commons.io.IOUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rover12421 on 8/9/14.
 * brut.androlib.res.AndrolibResources
 */
@Aspect
public class AndrolibResourcesAj {
    /**
     * Androidlib.UNK_DIRNAME
     */
    private final static String SHAKA_PNG  = "/png/Shaka.png";
    private final static String SHAKA_9_PNG  = "/png/Shaka.9.png";

    /**
     * 异常png图片处理
     * brut.androlib.res.AndrolibResources
     * public void aaptPackage(File apkFile, File manifest, File resDir, File rawDir, File assetDir, File[] include)
     */
    @Around("execution(* brut.androlib.res.AndrolibResources.aaptPackage(..))" +
            "&& args(apkFile, manifest, resDir, rawDir, assetDir, include)")
    public void aaptPackage_around(ProceedingJoinPoint joinPoint,
                                   File apkFile, File manifest, File resDir, File rawDir, File assetDir, File[] include) {
        try {
            String UNK_DIRNAME = (String) ReflectUtil.getFieldValue(Androlib.class, "UNK_DIRNAME");
            /**
             * 最大尝试10次,防止无限循环
             */
            int max = 10;
            while (max-- > 0) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                PrintStream olderr = System.err;
                System.setErr(ps);
                try {
                    joinPoint.proceed(joinPoint.getArgs());
                    System.setErr(olderr);
                    break;
                } catch (Throwable e) {
                    System.setErr(olderr);
                    String errStr = new String(baos.toByteArray());
                    Pattern patternPng = Pattern.compile("ERROR: Failure processing PNG image (.+)");
                    Pattern pattern9Png = Pattern.compile("ERROR: 9-patch image (.+) malformed\\.");

                    String rootDir = manifest.getParentFile().getAbsolutePath();

                    Matcher matcherPng = patternPng.matcher(errStr);
                    Matcher matcher9Png = pattern9Png.matcher(errStr);

                    Map<String, String> replacePng = new HashMap<>();

                    while (matcherPng.find()) {
                        String png = matcherPng.group(1);
                        String desPath = rootDir + File.separatorChar + UNK_DIRNAME + png.substring(rootDir.length());
                        replacePng.put(png, desPath);
                    }

                    while (matcher9Png.find()) {
                        String png = matcher9Png.group(1);
                        String desPath = rootDir + File.separatorChar + UNK_DIRNAME + png.substring(rootDir.length());
                        replacePng.put(png, desPath);
                    }

                    if (replacePng.size() > 0 ) {
                        for (String srcPng : replacePng.keySet()) {
                            if (!new File(srcPng).exists()) {
                                /**
                                 * 文件不存在.跳过.
                                 * 发现错误流有被篡写的现象
                                 */
                                continue;
                            }

                            String desPng = replacePng.get(srcPng);
                            //创建目录
                            new File(desPng).getParentFile().mkdirs();

                            try {
                                //备份原始文件
                                Path srcPath = Paths.get(srcPng);
                                Path desPath = Paths.get(desPng);
                                LogHelper.getLogger().warning("Found exception png file : " + srcPng);
                                Files.copy(srcPath, desPath, StandardCopyOption.REPLACE_EXISTING);

                                //用ok的png替换异常png
                                InputStream pngIs;
                                if (srcPng.endsWith(".9.png")) {
                                    pngIs = this.getClass().getResourceAsStream(SHAKA_9_PNG);
                                } else {
                                    pngIs = this.getClass().getResourceAsStream(SHAKA_PNG);
                                }
                                Files.copy(pngIs, srcPath, StandardCopyOption.REPLACE_EXISTING);
                                IOUtils.closeQuietly(pngIs);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    } else {
                        throw new ShakaRuntimeException(e);
                    }

                } finally {
                    System.setErr(olderr);
                    ps.close();
                    IOUtils.closeQuietly(baos);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @AfterReturning(pointcut = "execution(* brut.androlib.res.AndrolibResources.getResFileDecoder(..))", returning = "duo")
    public void getResFileDecoder_after(Duo<ResFileDecoder, AXmlResourceParser> duo) {
        if (!ShakaDecodeOption.getInstance().isNo9png()) {
            return;
        }
        ResFileDecoder fileDecoder = duo.m1;
        try {
            ResStreamDecoderContainer mDecoders = (ResStreamDecoderContainer) ReflectUtil.getFieldValue(fileDecoder, "mDecoders");
            mDecoders.setDecoder("9patch", new ResRawStreamDecoder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterReturning(pointcut = "execution(* brut.androlib.res.AndrolibResources.getFrameworkApk(..))" +
            "&& args(id, frameTag)", returning = "apk")
    public void getFrameworkApk(int id, String frameTag, File apk) throws AndrolibException {
        if (!ShakaDecodeOption.getInstance().isUsingDefaultFramework()) {
            return;
        }

        if (id == 1 && apk.getAbsolutePath().endsWith("/1.apk")) {
            try (InputStream in = AndrolibResources.class.getResourceAsStream("/brut/androlib/android-framework.jar");
                 OutputStream out = new FileOutputStream(apk)) {
                IOUtils.copy(in, out);
            } catch (IOException ex) {
                throw new AndrolibException(ex);
            }
        }
    }
}
