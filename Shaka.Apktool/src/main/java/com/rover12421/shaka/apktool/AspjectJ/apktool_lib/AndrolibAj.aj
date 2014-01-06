package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.androlib.AndrolibException;
import brut.directory.Directory;
import brut.directory.DirectoryException;
import brut.directory.FileDirectory;
import com.rover12421.shaka.apktool.util.AndroidZip;
import com.rover12421.shaka.apktool.util.ShakaRuntimeException;
import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;

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
     * 支持带非URI字符的文件名
     */
    pointcut writeMeateFilePointcut(File mOutDir, Map<String, Object> meta)
            : execution(void brut.androlib.Androlib.writeMetaFile(File, Map<String, Object>))
            && args(mOutDir, meta);

    void around(File mOutDir, Map<String, Object> meta)
            : writeMeateFilePointcut(mOutDir, meta)
            && !within(AndrolibAj +) {

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        // options.setIndent(4);
        Yaml yaml = new Yaml(options);

        Writer writer = null;
        try {
//            writer = new FileWriter(new File(mOutDir, "apktool.yml"));
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File(mOutDir, "apktool.yml")), "UTF-8"));
            yaml.dump(meta, writer);
        } catch (IOException ex) {
            throw new ShakaRuntimeException(new AndrolibException(ex));
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    pointcut URIpointcut(String scheme, String ssp, String fragment)
            : call(URI.new(String, String, String))
            && args(scheme, ssp, fragment);

    URI around(String scheme, String ssp, String fragment)
            : URIpointcut(scheme, ssp, fragment)
            && withincode(void brut.androlib.Androlib.buildUnknownFiles(File, File, Map<java.lang.String, java.lang.Object>))
            && !within(AndrolibAj +) {

        String sspNew = ssp;
        try {
            File file = new File(new URI(ssp));
            sspNew = file.toURI().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return proceed(scheme, sspNew, fragment);
    }
}
