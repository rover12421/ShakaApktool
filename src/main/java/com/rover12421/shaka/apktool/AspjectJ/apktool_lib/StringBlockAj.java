package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.androlib.res.decoder.StringBlock;
import brut.util.ExtDataInput;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import com.rover12421.shaka.apktool.util.ShakaRuntimeException;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.io.IOException;

/**
 * Created by rover12421 on 8/9/14.
 */
@Aspect
public class StringBlockAj {
    private static final int CHUNK_TYPE = 0x001C0001;
    private static final int UTF8_FLAG = 0x00000100;

    /**
     * public static StringBlock read(ExtDataInput reader) throws IOException
     */

    @Around("execution(* brut.androlib.res.decoder.StringBlock.read(..))" +
            "&& args(reader)")
    public StringBlock read_around(ExtDataInput reader) {
        StringBlock block = null;
        try {
            reader.skipCheckInt(CHUNK_TYPE);
            int chunkSize = reader.readInt();
            int stringCount = reader.readInt();
            int styleOffsetCount = reader.readInt();
            int flags = reader.readInt();
            int stringsOffset = reader.readInt();
            int stylesOffset = reader.readInt();

//            StringBlock block = new StringBlock();
            block = (StringBlock) ReflectUtil.newInstance(StringBlock.class);
//            block.m_isUTF8 = (flags & UTF8_FLAG) != 0;
//            block.m_stringOffsets = reader.readIntArray(stringCount);
//            block.m_stringOwns = new int[stringCount];
            ReflectUtil.setFieldValue(block, "m_isUTF8", (flags & UTF8_FLAG) != 0);
            ReflectUtil.setFieldValue(block, "m_stringOffsets", reader.readIntArray(stringCount));
            int[] stringOwns = new int[stringCount];
            ReflectUtil.setFieldValue(block, "m_stringOwns", stringOwns);


            for (int i = 0; i < stringCount; i++) {
//                block.m_stringOwns[i] = -1;
                stringOwns[i] = -1;
            }
            if (styleOffsetCount != 0) {
//                block.m_styleOffsets = reader.readIntArray(styleOffsetCount);
                ReflectUtil.setFieldValue(block, "m_styleOffsets", reader.readIntArray(styleOffsetCount));
            } else {
                stylesOffset = 0;
            }

            {
                int size = ((stylesOffset == 0) ? chunkSize : stylesOffset)
                        - stringsOffset;
                if ((size % 4) != 0) {
                    throw new IOException("String data size is not multiple of 4 ("
                            + size + ").");
                }
//                block.m_strings = new byte[size];
//                reader.readFully(block.m_strings);
                byte[] strings = new byte[size];
                reader.readFully(strings);
                ReflectUtil.setFieldValue(block, "m_strings", strings);
            }
            if (stylesOffset != 0) {
                int size = (chunkSize - stylesOffset);
                if ((size % 4) != 0) {
                    throw new IOException("Style data size is not multiple of 4 ("
                            + size + ").");
                }
//                block.m_styles = reader.readIntArray(size / 4);
                ReflectUtil.setFieldValue(block, "m_styles", reader.readIntArray(size / 4));
            }

//            return block;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ShakaRuntimeException(e);
        }

        return block;
    }

}
