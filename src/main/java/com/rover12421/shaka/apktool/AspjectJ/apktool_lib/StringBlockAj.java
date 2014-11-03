package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.androlib.res.decoder.StringBlock;
import brut.util.ExtDataInput;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import com.rover12421.shaka.apktool.util.ShakaRuntimeException;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by rover12421 on 8/9/14.
 */
@Aspect
public class StringBlockAj {
    // ResChunk_header = header.type (0x0001) + header.headerSize (0x001C)
    private static final int CHUNK_STRINGPOOL_TYPE = 0x001C0001;
    private static final int CHUNK_NULL_TYPE = 0x00000000;
    private static final int UTF8_FLAG = 0x00000100;

    /**
     *
     I: Using ShakaApktool 1.0-747a5b-2.0.0-a7b06f-SNAPSHOT
     I: Using Apktool 2.0.0-a7b06f-SNAPSHOT on AutorunManagerv4.0.1.apk
     I: Loading resource table...
     I: Loading resource table...
     I: Decoding AndroidManifest.xml with resources...
     I: Loading resource table from file: /home/rover12421/apktool/framework/1.apk
     I: Regular manifest package...
     I: Decoding file-resources...
     S: Could not decode file, replacing by FALSE value: layout/receiverrow.xml
     S: Could not decode file, replacing by FALSE value: layout/abs__action_bar_tab_bar_view.xml
     S: Could not decode file, replacing by FALSE value: layout/abs__action_bar_home.xml
     S: Could not decode file, replacing by FALSE value: layout-large/abs__action_mode_close_item.xml
     S: Could not decode file, replacing by FALSE value: layout/abs__action_menu_layout.xml
     S: Could not decode file, replacing by FALSE value: layout/abs__action_bar_tab.xml
     S: Could not decode file, replacing by FALSE value: layout/frg_application.xml
     S: Could not decode file, replacing by FALSE value: layout/abs__search_view.xml
     ...

     * public static StringBlock read(ExtDataInput reader) throws IOException
     */

    /**
     * fix : 9b1c7d22ef7ef14207b781164a25c892b5da54af
     * @param reader
     * @return
     */
//    @Around("execution(* brut.androlib.res.decoder.StringBlock.read(..))" +
//            "&& args(reader)")
//    public StringBlock read_around(ExtDataInput reader) {
//        StringBlock block = null;
//        try {
//            reader.skipCheckChunkTypeInt(CHUNK_STRINGPOOL_TYPE, CHUNK_NULL_TYPE);
//            int chunkSize = reader.readInt();
//
//            // ResStringPool_header
//            int stringCount = reader.readInt();
//            int styleCount = reader.readInt();
//            int flags = reader.readInt();
//            int stringsOffset = reader.readInt();
//            int stylesOffset = reader.readInt();
//
////            StringBlock block = new StringBlock();
//            block = (StringBlock) ReflectUtil.newInstance(StringBlock.class);
////            block.m_isUTF8 = (flags & UTF8_FLAG) != 0;
//            ReflectUtil.setFieldValue(block, "m_isUTF8", (flags & UTF8_FLAG) != 0);
////            block.m_stringOffsets = reader.readIntArray(stringCount);
//            ReflectUtil.setFieldValue(block, "m_stringOffsets", reader.readIntArray(stringCount));
////            block.m_stringOwns = new int[stringCount];
//            int[] stringOwns = new int[stringCount];
//            ReflectUtil.setFieldValue(block, "m_stringOwns", stringOwns);
////            Arrays.fill(block.m_stringOwns, -1);
//            Arrays.fill(stringOwns, -1);
//
//            if (styleCount != 0) {
////                block.m_styleOffsets = reader.readIntArray(styleCount);
//                ReflectUtil.setFieldValue(block, "m_styleOffsets", reader.readIntArray(styleCount));
//            }
//            {
////                int size = ((stylesOffset == 0) ? chunkSize : stylesOffset) - stringsOffset;
//                int size = ((styleCount == 0) ? chunkSize : stylesOffset) - stringsOffset;
//                if ((size % 4) != 0) {
//                    throw new IOException("String data size is not multiple of 4 (" + size + ").");
//                }
////                block.m_strings = new byte[size];
////                reader.readFully(block.m_strings);
//                byte[] strings = new byte[size];
//                reader.readFully(strings);
//                ReflectUtil.setFieldValue(block, "m_strings", strings);
//            }
////            if (stylesOffset != 0) {
//            if (styleCount != 0) {
//                int size = (chunkSize - stylesOffset);
//                if ((size % 4) != 0) {
//                    throw new IOException("Style data size is not multiple of 4 (" + size + ").");
//                }
////                block.m_styles = reader.readIntArray(size / 4);
//                ReflectUtil.setFieldValue(block, "m_styles", reader.readIntArray(size / 4));
//            }
//
////            return block;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ShakaRuntimeException(e);
//        }
//
//        return block;
//    }

}
