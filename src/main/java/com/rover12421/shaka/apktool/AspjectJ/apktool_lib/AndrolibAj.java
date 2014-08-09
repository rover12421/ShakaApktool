package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.androlib.Androlib;
import brut.androlib.res.util.ExtFile;
import brut.directory.Directory;
import brut.directory.DirectoryException;
import brut.directory.FileDirectory;
import com.rover12421.shaka.apktool.lib.ShakaProperties;
import com.rover12421.shaka.apktool.util.AndroidZip;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by rover12421 on 8/9/14.
 */
@Aspect
public class AndrolibAj {
    /**
     * 未知文件处理,在编译未知文件之前,重新扫描一次"unknown"目录
     */
    @Pointcut("execution(void brut.androlib.Androlib.buildUnknownFiles(..))" +
            "&& args(appDir, outFile, meta)")
    private void pointcut_buildUnknownFiles(File appDir, File outFile, Map<String, Object> meta) {}


    @Before("pointcut_buildUnknownFiles(appDir, outFile, meta)")
    public void buildUnknownFiles_before(File appDir, File outFile, Map<String, Object> meta) {
        try {
            String UNK_DIRNAME = (String) ReflectUtil.getFieldValue(Androlib.class, "UNK_DIRNAME");
            File unknownFile = new File(appDir, UNK_DIRNAME);
            if (!unknownFile.exists()) return;

            try {
                Directory directory = new FileDirectory(unknownFile);
                Set<String> addFiles = directory.getFiles(true);
                Map<String, String> files = (Map<String, String>)meta.get("unknownFiles");
                for (String file : addFiles) {
                    files.put(file, AndroidZip.getZipMethod(new File(appDir, file).getAbsolutePath()) + "");
                }
            } catch (DirectoryException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * public void build(ExtFile appDir, File outFile,
     * HashMap<String, Boolean> flags, String aaptPath)
     */
    @Pointcut("execution(void brut.androlib.Androlib.build(..))" +
            "&& args(appDir, outFile, flags, aaptPath)")
    private void pointcut_build(ExtFile appDir, File outFile, HashMap<String, Boolean> flags, String aaptPath) {}

    @Before("pointcut_build(appDir, outFile, flags, aaptPath)")
    public void build_before(JoinPoint joinPoint, ExtFile appDir, File outFile, HashMap<String, Boolean> flags, String aaptPath) {
        try {
            Logger LOGGER = (Logger) ReflectUtil.getFieldValue(joinPoint.getThis(), "LOGGER");
            LOGGER.info("Using ShakaApktool " + ShakaProperties.getVersion());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
