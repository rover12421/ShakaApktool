package com.rover12421.shaka.lib;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rover12421 on 7/22/15.
 */
public class JarUtil {
    public static void getResourceAsFile(String resourcePath, String desPath) throws IOException {
        File parentFile = new File(desPath).getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try (
                InputStream in = Class.class.getResourceAsStream(resourcePath);
                FileOutputStream fos = new FileOutputStream(desPath)
                ) {
            IOUtils.copy(in, fos);
        }
    }
}
