package com.rover12421.shaka.lib;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by rover12421 on 1/30/16.
 */
public class ShakaIO {
    public static void copy(ZipFile inputFile, ZipArchiveOutputStream outputFile, ZipArchiveEntry entry) throws IOException {
        try (
                InputStream is = inputFile.getInputStream(entry)
        ) {
            IOUtils.copy(is, outputFile);
        }
    }

    public static void copy(File inputFile, ZipArchiveOutputStream outputFile) throws IOException {
        try (
                FileInputStream fis = new FileInputStream(inputFile)
        ) {
            IOUtils.copy(fis, outputFile);
        }
    }
}
