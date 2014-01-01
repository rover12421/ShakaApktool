package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import com.rover12421.shaka.apktool.util.ShakaRuntimeException;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rover12421 on 1/1/14.
 * brut.androlib.res.AndrolibResources
 * aaptPackage(File apkFile, File manifest, File resDir,
 *      File rawDir, File assetDir, File[] include,
 *      HashMap<String, Boolean> flags, String aaptPath)
 */
public aspect AaptPackage {

    /**
     * Androidlib.UNK_DIRNAME
     */
    private final static String UNK_DIRNAME = "unknown";
    private final static String SHAKA_PNG  = "/png/Shaka.png";
    private final static String SHAKA_9_PNG  = "/png/Shaka.9.png";

    pointcut aaptPackagePointcut(File apkFile, File manifest, File resDir,
                         File rawDir, File assetDir, File[] include,
                         HashMap<String, Boolean> flags, String aaptPath)
            : execution(void brut.androlib.res.AndrolibResources.aaptPackage(File, File, File, File, File, File[], HashMap<String, Boolean>, String))
            && args(apkFile, manifest, resDir, rawDir, assetDir, include, flags, aaptPath);

    void around(File apkFile, File manifest, File resDir,
                File rawDir, File assetDir, File[] include,
                HashMap<String, Boolean> flags, String aaptPath)
            : aaptPackagePointcut(apkFile, manifest, resDir, rawDir, assetDir, include, flags, aaptPath)
            && !within(AaptPackage +) {
        while (true) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream olderr = System.err;
            System.setErr(ps);
            try {
                proceed(apkFile, manifest, resDir, rawDir, assetDir, include, flags, aaptPath);
                System.setErr(olderr);
                break;
            } catch (Exception e) {
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

    }
}
