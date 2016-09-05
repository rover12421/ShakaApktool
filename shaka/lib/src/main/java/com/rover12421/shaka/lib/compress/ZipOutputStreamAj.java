package com.rover12421.shaka.lib.compress;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * Created by rover12421 on 9/5/16.
 */
@Aspect
public class ZipOutputStreamAj {
    @Around("call(java.util.zip.ZipOutputStream.new(..))" +
            "&& args(out)")
    public ZipOutputStream _new(OutputStream out) throws IOException {
        return new ZipOutputStream(out);
    }

    @Around("call(java.util.zip.ZipOutputStream.new(..))" +
            "&& args(out, charset)")
    public ZipOutputStream _new(OutputStream out, Charset charset) throws IOException {
        return new ZipOutputStream(out, charset);
    }
}
