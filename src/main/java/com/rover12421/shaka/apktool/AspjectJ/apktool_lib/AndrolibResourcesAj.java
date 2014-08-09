package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.androlib.Androlib;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import com.rover12421.shaka.apktool.util.ShakaRuntimeException;
import org.apache.commons.io.IOUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

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

    @Pointcut("execution(void brut.androlib.res.AndrolibResources.aaptPackage(..))" +
            "&& args(apkFile, manifest, resDir, rawDir, assetDir, include, flags, aaptPath)")
    private void pointcut_aaptPackage(File apkFile, File manifest, File resDir,
                                      File rawDir, File assetDir, File[] include,
                                      HashMap<String, Boolean> flags, String aaptPath) {}

    /**
     * 异常png图片处理
     */
    @Around("pointcut_aaptPackage(apkFile, manifest, resDir, rawDir, assetDir, include, flags, aaptPath)")
    public void aaptPackage_around(ProceedingJoinPoint joinPoint, File apkFile, File manifest, File resDir,
                                   File rawDir, File assetDir, File[] include,
                                   HashMap<String, Boolean> flags, String aaptPath) {
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
                    joinPoint.proceed(new Object[]{apkFile, manifest, resDir, rawDir, assetDir, include, flags, aaptPath});
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
                                Files.copy(srcPath, desPath, StandardCopyOption.REPLACE_EXISTING);

                                //用ok的png替换异常png
                                InputStream pngIs = null;
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
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
