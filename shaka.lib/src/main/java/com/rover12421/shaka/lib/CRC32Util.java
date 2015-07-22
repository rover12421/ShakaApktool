package com.rover12421.shaka.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;

/**
 * Created by rover12421 on 7/22/15.
 */
public class CRC32Util {
    public static long getCRC32(InputStream is) throws IOException {
        byte[] buff = new byte[4096];
        CRC32 crc32 = new CRC32();
        int len;
        while ((len = is.read(buff)) != -1) {
            crc32.update(buff, 0, len);
        }

        return crc32.getValue();
    }

    public static long getCRC32(File file) throws IOException {
        try (
                FileInputStream fis = new FileInputStream(file)
                ){
            return getCRC32(fis);
        }
    }
}
