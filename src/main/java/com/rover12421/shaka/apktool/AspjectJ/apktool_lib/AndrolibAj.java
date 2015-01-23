package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.androlib.Androlib;
import brut.androlib.res.data.ResUnknownFiles;
import brut.directory.Directory;
import brut.directory.DirectoryException;
import brut.directory.FileDirectory;
import com.rover12421.shaka.apktool.lib.ShakaProperties;
import com.rover12421.shaka.apktool.util.AndroidZip;
import com.rover12421.shaka.apktool.util.LogHelper;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * Created by rover12421 on 8/9/14.
 */
@Aspect
public class AndrolibAj {
    /**
     * 未知文件处理,在编译未知文件之前,重新扫描一次"unknown"目录
     */
    @Before("execution(void brut.androlib.Androlib.buildUnknownFiles(..))" +
            "&& args(appDir, outFile, meta)")
    public void buildUnknownFiles_before(JoinPoint joinPoint, File appDir, File outFile, Map<String, Object> meta) {
        try {
            String UNK_DIRNAME = (String) ReflectUtil.getFieldValue(Androlib.class, "UNK_DIRNAME");
            File unknownFile = new File(appDir, UNK_DIRNAME);
            if (!unknownFile.exists()) {
                return;
            }

            try {
                Directory directory = new FileDirectory(unknownFile);
                Set<String> addFiles = directory.getFiles(true);
                Map<String, String> files = (Map<String, String>)meta.get("unknownFiles");
                if (files == null) {
                    ResUnknownFiles mResUnknownFiles = (ResUnknownFiles) ReflectUtil.getFieldValue(joinPoint.getThis(), "mResUnknownFiles");
                    files = mResUnknownFiles.getUnknownFiles();
                    meta.put("unknownFiles", files);
                }
                for (String file : addFiles) {
                    files.put(file, AndroidZip.getZipMethod(new File(appDir, file).getAbsolutePath()) + "");
                }
            } catch (DirectoryException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * public void build(ExtFile appDir, File outFile)
     */
    @Before("execution(void brut.androlib.Androlib.build(brut.androlib.res.util.ExtFile, java.io.File))")
    public void build_before() {
        LogHelper.getLogger().info("Using ShakaApktool " + ShakaProperties.getVersion());
    }


    /**
     *
     <B>com.carrot.carrotfantasy.apk</B>

     Exception in thread "main" org.jf.util.ExceptionWithContext: The assets/cfg.dex file in com.carrot.carrotfantasy.apk is too small to be a valid dex file
     at org.jf.dexlib2.DexFileFactory.loadDexFile(DexFileFactory.java:77)
     at org.jf.dexlib2.DexFileFactory.loadDexFile(DexFileFactory.java:59)
     at brut.androlib.src.SmaliDecoder.decode(SmaliDecoder.java:94)
     at brut.androlib.src.SmaliDecoder.decode(SmaliDecoder.java:46)
     at brut.androlib.Androlib.decodeSourcesSmali(Androlib.java:83)
     at brut.androlib.ApkDecoder.decode(ApkDecoder.java:146)
     at brut.apktool.Main.cmdDecode(Main.java:170)
     at brut.apktool.Main.main(Main.java:86)
     */
    @Around("execution(* brut.androlib.Androlib.decodeSourcesSmali(..))" +
            "&& args(apkFile, outDir, filename, debug, debugLinePrefix, bakdeb, api)")
    public void decodeSourcesSmali_around(ProceedingJoinPoint joinPoint, File apkFile, File outDir, String filename, boolean debug, String debugLinePrefix,
                            boolean bakdeb, int api) throws Throwable {
        try {
            joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable e) {
            if (!"classes.dex".equals(filename)) {
                LogHelper.getLogger().warning("decodeSourcesSmali " + filename + " error!");
            } else {
                throw e;
            }
        }
    }
}
