package com.rover12421.shaka.lib.compress;

import com.rover12421.shaka.lib.AndroidZip;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;

/**
 * Created by rover12421 on 9/5/16.
 */
public class ZipOutputStream extends java.util.zip.ZipOutputStream {
    private ZipArchiveOutputStream zos;

    public ZipOutputStream(OutputStream out) {
        super(new ByteArrayOutputStream());
        zos = new ZipArchiveOutputStream(out);
    }

    public ZipOutputStream(OutputStream out, Charset charset) {
        this(out);
    }

    @Override
    public void setComment(String comment) {
        zos.setComment(comment);
    }

    @Override
    public void setMethod(int method) {
        zos.setMethod(method);
    }

    @Override
    public void setLevel(int level) {
        zos.setLevel(level);
    }

    @Override
    public void putNextEntry(ZipEntry e) throws IOException {
        if (e instanceof ZipArchiveEntry) {
            zos.putArchiveEntry((ArchiveEntry) e);
        } else {
            if (e.getMethod() == -1) {
                e.setMethod(AndroidZip.getZipMethod(e.getName()));
            }
            zos.putArchiveEntry(new ZipArchiveEntry(e));
        }
    }

    @Override
    public void closeEntry() throws IOException {
        zos.closeArchiveEntry();
    }

    @Override
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        zos.write(b, off, len);
    }

    @Override
    public void finish() throws IOException {
        zos.finish();
    }

    @Override
    public void close() throws IOException {
        zos.close();
    }

    @Override
    public void write(int b) throws IOException {
        zos.write(b);
    }

//    @Override
//    protected void deflate() throws IOException {
//        zos.deflate();
//    }

    @Override
    public void flush() throws IOException {
        zos.flush();
    }

    @Override
    public void write(byte[] b) throws IOException {
        zos.write(b);
    }
}
