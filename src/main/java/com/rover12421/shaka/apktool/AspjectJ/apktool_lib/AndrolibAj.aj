package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.androlib.Androlib;
import brut.androlib.res.util.ExtFile;
import brut.directory.Directory;
import brut.directory.DirectoryException;
import brut.directory.FileDirectory;
import com.rover12421.shaka.apktool.lib.ShakaProperties;
import com.rover12421.shaka.apktool.util.AndroidZip;
import com.rover12421.shaka.apktool.util.ReflectUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by rover12421 on 1/1/14.
 * brut.androlib.Androlib;
 */
public aspect AndrolibAj {

    private final static String UNK_DIRNAME = "unknown";

    /**
     * 未知文件处理,在编译未知文件之前,重新扫描一次"unknown"目录
     */
    pointcut buildUnknownFilesPointcut(File appDir, File outFile, Map<String, Object> meta)
            : call(void brut.androlib.Androlib.buildUnknownFiles(File, File, Map<java.lang.String, java.lang.Object>))
            && args(appDir, outFile, meta);

    before(File appDir, File outFile, Map<String, Object> meta)
            : buildUnknownFilesPointcut(appDir, outFile, meta)
            && !within(AndrolibAj +) {

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
    }

    /**
     * 已经合并到主线
     * https://github.com/iBotPeaches/Apktool/pull/81
     * https://github.com/iBotPeaches/Apktool/commit/f85dc478b2c1cc4038be509eab0fbf85432b0d40
     */
//    /**
//     * 支持带非URI字符的文件名
//     */
//    pointcut writeMeateFilePointcut(File mOutDir, Map<String, Object> meta)
//            : execution(void brut.androlib.Androlib.writeMetaFile(File, Map<String, Object>))
//            && args(mOutDir, meta);
//
//    void around(File mOutDir, Map<String, Object> meta)
//            : writeMeateFilePointcut(mOutDir, meta)
//            && !within(AndrolibAj +) {
//
//        DumperOptions options = new DumperOptions();
//        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
//        // options.setIndent(4);
//        Yaml yaml = new Yaml(options);
//
//        Writer writer = null;
//        try {
////            writer = new FileWriter(new File(mOutDir, "apktool.yml"));
//            writer = new BufferedWriter(new OutputStreamWriter(
//                    new FileOutputStream(new File(mOutDir, "apktool.yml")), "UTF-8"));
//            yaml.dump(meta, writer);
//        } catch (IOException ex) {
//            throw new ShakaRuntimeException(new AndrolibException(ex));
//        } finally {
//            IOUtils.closeQuietly(writer);
//        }
//    }
//
//    pointcut URIpointcut(String scheme, String ssp, String fragment)
//            : call(URI.new(String, String, String))
//            && args(scheme, ssp, fragment);
//
//    URI around(String scheme, String ssp, String fragment)
//            : URIpointcut(scheme, ssp, fragment)
//            && withincode(void brut.androlib.Androlib.buildUnknownFiles(File, File, Map<java.lang.String, java.lang.Object>))
//            && !within(AndrolibAj +) {
//
//        String sspNew = ssp;
//        try {
//            File file = new File(new URI(ssp));
//            sspNew = file.toURI().toString();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//
//        return proceed(scheme, sspNew, fragment);
//    }

    /**
     * public void build(ExtFile appDir, File outFile,
     * HashMap<String, Boolean> flags, String aaptPath)
     */
    pointcut build_Pointcut(ExtFile appDir, File outFile, HashMap<String, Boolean> flags, String aaptPath)
            : call(void brut.androlib.Androlib.build(ExtFile, File, HashMap<String, Boolean>, String))
            && args(appDir, outFile, flags, aaptPath);

    before(ExtFile appDir, File outFile, HashMap<String, Boolean> flags, String aaptPath)
            : build_Pointcut(appDir, outFile, flags, aaptPath)
            && !within(AndrolibAj +) {

        try {
            Logger LOGGER = (Logger) ReflectUtil.getFieldValue(thisJoinPoint.getThis(), "LOGGER");
            LOGGER.info("Using ShakaApktool " + ShakaProperties.getVersion());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        new File("");
    }
}
