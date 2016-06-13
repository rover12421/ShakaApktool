package com.rover12421.shaka.lib.compress;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.ZipException;

/**
 * Created by rover12421 on 1/30/16.
 */
@Aspect
public class ZipFileAj {
    @Around("call(java.util.zip.ZipFile.new(..))" +
            "&& args(name)")
    public ZipFile _new(String name) throws IOException {
        return new ZipFile(name);
    }

    @Around("call(java.util.zip.ZipFile.new(..))" +
            "&& args(file, mode)")
    public ZipFile _new(File file, int mode) throws IOException {
        return new ZipFile(file, mode);
    }

    @Around("call(java.util.zip.ZipFile.new(..))" +
            "&& args(file)")
    public ZipFile _new(File file) throws ZipException, IOException {
        return new ZipFile(file);
    }

    @Around("call(java.util.zip.ZipFile.new(..))" +
            "&& args(file, mode, charset)")
    public ZipFile _new(File file, int mode, Charset charset) throws IOException {
        return new ZipFile(file, mode, charset);
    }

    @Around("call(java.util.zip.ZipFile.new(..))" +
            "&& args(name, charset)")
    public ZipFile _new(String name, Charset charset) throws IOException {
        return new ZipFile(name, charset);
    }

    @Around("call(java.util.zip.ZipFile.new(..))" +
            "&& args(file, charset)")
    public ZipFile _new(File file, Charset charset) throws IOException {
        return new ZipFile(file, charset);
    }
}
