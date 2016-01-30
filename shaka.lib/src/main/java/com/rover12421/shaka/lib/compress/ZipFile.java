package com.rover12421.shaka.lib.compress;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;

/**
 * Created by rover12421 on 1/30/16.
 */
public class ZipFile extends java.util.zip.ZipFile {

    private String archiveName;
    private org.apache.commons.compress.archivers.zip.ZipFile zipFile;
    private static File tmpFile;

    static {
        try {
            tmpFile = File.createTempFile("ZipFile", "zip");
            tmpFile.deleteOnExit();
            new ZipArchiveOutputStream(tmpFile).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ZipFile(String name) throws IOException {
        this(new File(name));
    }

    public ZipFile(File file, int mode) throws IOException {
        this(file);
    }

    public ZipFile(File file) throws ZipException, IOException {
        super(tmpFile);
        zipFile = new org.apache.commons.compress.archivers.zip.ZipFile(file);
        archiveName = file.getAbsolutePath();
    }

    public ZipFile(File file, int mode, Charset charset) throws IOException {
        this(file, charset);
    }

    public ZipFile(String name, Charset charset) throws IOException {
        this(new File(name), charset);
    }

    public ZipFile(File file, Charset charset) throws IOException {
        super(tmpFile);
        zipFile = new org.apache.commons.compress.archivers.zip.ZipFile(file, charset.name());
        archiveName = file.getAbsolutePath();
    }

    @Override
    public String getComment() {
        /**
         * apache compress 默然不提供该方法
         * 暂时没有找到好的方式获取
         */
        return "";
    }

    @Override
    public InputStream getInputStream(ZipEntry entry) throws IOException {
        if (entry instanceof ZipArchiveEntry) {
            return zipFile.getInputStream((ZipArchiveEntry) entry);
        } else {
            return zipFile.getInputStream(zipFile.getEntry(entry.getName()));
        }
    }

    @Override
    public String getName() {
        return archiveName;
    }

    @Override
    public Enumeration<? extends ZipEntry> entries() {
        return zipFile.getEntries();
    }

    private int total = 0;
    @Override
    public int size() {
        if (total > 0) {
            return total;
        }
        Enumeration<? extends ArchiveEntry> enumeration = zipFile.getEntries();
        while (enumeration.hasMoreElements()) {
            enumeration.nextElement();
            total++;
        }
        return total;
    }

    @Override
    public ZipEntry getEntry(String name) {
        return zipFile.getEntry(name);
    }

    @Override
    public void close() throws IOException {
        zipFile.close();
    }
}
