package com.rover12421.shaka.smali.dexlib2;

import com.google.common.io.ByteStreams;
import com.rover12421.shaka.lib.LogHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jf.baksmali.main;
import org.jf.dexlib2.Opcodes;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.dexbacked.raw.HeaderItem;
import org.jf.util.ExceptionWithContext;

import javax.annotation.Nonnull;
import java.io.EOFException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by rover12421 on 11/10/15.
 */
@Aspect
public class DexBackedDexFileAj {
    public static void verifyMagicAndByteOrder(@Nonnull byte[] buf, int offset) {
        if (!HeaderItem.verifyMagic(buf, offset)) {
            StringBuilder sb = new StringBuilder("Invalid magic value:");
            for (int i=0; i<8; i++) {
                sb.append(String.format(" %02x", buf[i]));
            }
            throw new DexBackedDexFile.NotADexFile(sb.toString());
        }

        int endian = HeaderItem.getEndian(buf, offset);
        if (endian == HeaderItem.BIG_ENDIAN_TAG) {
            throw new ExceptionWithContext("Big endian dex files are not currently supported");
        }

        if (endian != HeaderItem.LITTLE_ENDIAN_TAG) {
            throw new ExceptionWithContext("Invalid endian tag: 0x%x", endian);
        }
    }

    private static final byte[] MAGIC = new byte[]{0x64, 0x65, 0x78, 0x0a, 0x30, 0x33};

    @Around("execution(org.jf.dexlib2.dexbacked.DexBackedDexFile org.jf.dexlib2.dexbacked.DexBackedDexFile.fromInputStream(..))" +
            "&& args(opcodes, is)")
    public DexBackedDexFile fromInputStream(ProceedingJoinPoint joinPoint, Opcodes opcodes, InputStream is)
            throws Throwable {

        //如果是反odex,使用原逻辑
        if (main.getOptions().hasOption("x")) {
            return (DexBackedDexFile) joinPoint.proceed(joinPoint.getArgs());
        }

        if (!is.markSupported()) {
            throw new IllegalArgumentException("InputStream must support mark");
        }
        byte[] flag = new byte[MAGIC.length];
        int skip = 0;
        int len = is.available() - 44;
        for (int i=0; i<len; i++) {
            is.mark(len);
            is.skip(i);
            is.read(flag);

            if (Arrays.equals(flag, MAGIC)) {
                int ver = is.read();
                int non = is.read();
                if (non == 0x00 && ver >= 0x35 && ver <= 0x36) {
                    skip = i;
                    if (skip != 0) {
                        LogHelper.info("Search Dex Offset : " + skip);
                    }
                    is.reset();
                    break;
                }
            }
            is.reset();
        }

        is.mark(44+skip);
        is.skip(skip);
        byte[] partialHeader = new byte[44];
        try {
            ByteStreams.readFully(is, partialHeader);
        } catch (EOFException ex) {
            throw new DexBackedDexFile.NotADexFile("File is too short");
        } finally {
            is.reset();
        }

        verifyMagicAndByteOrder(partialHeader, 0);

        byte[] buf = ByteStreams.toByteArray(is);
//        return new DexBackedDexFile(opcodes, buf, 0, false);
        return new DexBackedDexFile(opcodes, buf, skip, false, null);
    }
}
