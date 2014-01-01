package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.directory.Directory;
import brut.directory.DirectoryException;
import brut.directory.FileDirectory;
import com.rover12421.shaka.apktool.util.AndroidZip;

import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * Created by rover12421 on 1/1/14.
 * brut.androlib.Androlib;
 * public void buildUnknownFiles(File appDir, File outFile, Map<String, Object> meta)
 */
public aspect BuildUnknownFiles {

    private final static String UNK_DIRNAME = "unknown";

    pointcut buildUnknownFilesPointcut(File appDir, File outFile, Map<String, Object> meta)
            : call(void brut.androlib.Androlib.buildUnknownFiles(File, File, Map<String, Object>))
            && args(appDir, outFile, meta);

    before(File appDir, File outFile, Map<String, Object> meta)
            : buildUnknownFilesPointcut(appDir, outFile, meta)
            && !within(BuildUnknownFiles +) {
        File unknownFile = new File(appDir, UNK_DIRNAME);
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
}
