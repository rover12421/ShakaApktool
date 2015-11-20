/**
 *  Copyright 2015 Rover12421 <rover12421@163.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.rover12421.shaka.apktool.lib;

import android.util.TypedValue;
import brut.androlib.AndrolibException;
import brut.androlib.res.data.ResConfigFlags;
import brut.androlib.res.data.ResPackage;
import brut.androlib.res.data.ResType;
import brut.androlib.res.data.value.ResIntBasedValue;
import brut.androlib.res.decoder.ARSCDecoder;
import brut.androlib.res.decoder.StringBlock;
import brut.util.ExtDataInput;
import com.rover12421.shaka.lib.LogHelper;
import com.rover12421.shaka.lib.ShakaDecodeOption;
import com.rover12421.shaka.lib.reflect.Reflect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.io.EOFException;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by rover12421 on 8/16/14.
 */
@Aspect
public class ARSCDecoderAj {

    //ResTable_header 结构的标准长度
    private final static int ResTable_header_SIZE= 12;

    @Around("execution(* brut.androlib.res.decoder.ARSCDecoder.addMissingResSpecs())")
    public void addMissingResSpecs(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!ShakaDecodeOption.getInstance().isFuckUnkownId()) {
            joinPoint.proceed(joinPoint.getArgs());
        }
    }

    public ExtDataInput mIn(ARSCDecoder thiz) throws Exception {
        return  Reflect.on(thiz).get("mIn");
    }

    public ResPackage mPkg(ARSCDecoder thiz) throws Exception {
        return  Reflect.on(thiz).get("mPkg");
    }

    public ARSCDecoder.Header mHeader(ARSCDecoder thiz) throws Exception {
        return  Reflect.on(thiz).get("mHeader");
    }

    public StringBlock mTableStrings(ARSCDecoder thiz) throws Exception {
        return  Reflect.on(thiz).get("mTableStrings");
    }

    public ResType mType(ARSCDecoder thiz) throws Exception {
        return  Reflect.on(thiz).get("mType");
    }

    @Around("execution(* brut.androlib.res.decoder.ARSCDecoder.nextChunk())")
    public ARSCDecoder.Header nextChunk(ProceedingJoinPoint joinPoint) throws Exception {
//        return mHeader = Header.read(mIn);
        ExtDataInput mIn = mIn((ARSCDecoder) joinPoint.getThis());
        ARSCDecoder.Header mHeader = ResChunk_header.read(mIn);
        Reflect.on(joinPoint.getThis()).set("mHeader", mHeader);
        return mHeader;
    }

    @Around("execution(* brut.androlib.res.decoder.ARSCDecoder.readTable())")
    public ResPackage[] readTable(ProceedingJoinPoint joinPoint) throws Exception {
        ARSCDecoder decoder = (ARSCDecoder) joinPoint.getThis();
        ExtDataInput mIn = mIn(decoder);

        Reflect decoderReflect = Reflect.on(decoder);
//        nextChunkCheckType(ARSCDecoder.Header.TYPE_TABLE);
        decoderReflect.method("nextChunkCheckType", int.class)
                .invoke(decoder, ARSCDecoder.Header.TYPE_TABLE);
        int packageCount = mIn.readInt();

        try {
            ResChunk_header header = (ResChunk_header) mHeader(decoder);
            if (header.headerSize > ResTable_header_SIZE) {
                int skip = header.headerSize - ResTable_header_SIZE;
                LogHelper.warning("ResChunk_header exception : read size = " + header.headerSize + ", skip " + skip);
                mIn.skipBytes(skip);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }


        StringBlock mTableStrings = StringBlock.read(mIn);
        decoderReflect.set("mTableStrings", mTableStrings);

        ResPackage[] packages = new ResPackage[packageCount];

//        nextChunk();
        decoderReflect.call("nextChunk");

        for (int i = 0; i < packageCount; i++) {
//            packages[i] = readPackage();
            packages[i] = decoderReflect.call("readPackage").get();
        }
        return packages;
    }

    public static class ResChunk_header extends ARSCDecoder.Header {
        public final short headerSize;

        public ResChunk_header(short type, short headerSize, int size) {
            super(type, size);
            this.headerSize = headerSize;
        }

        public static ResChunk_header read(ExtDataInput in) throws IOException {
            try {
                short type       = in.readShort();
                short headerSize = in.readShort();
                int size = in.readInt();
                return new ResChunk_header(type, headerSize, size);
            } catch (EOFException ex) {
                return new ResChunk_header(TYPE_NONE, (short) 0, 0);
            }
        }
    }

    @Around("execution(* brut.androlib.res.decoder.ARSCDecoder.readValue())")
    public ResIntBasedValue readValue(ProceedingJoinPoint joinPoint) throws Exception {
        ARSCDecoder decoder = (ARSCDecoder) joinPoint.getThis();
        ExtDataInput mIn = mIn(decoder);
        ResPackage mPkg = mPkg(decoder);

		/* size */mIn.skipCheckShort((short) 8);
		/* zero */mIn.skipCheckByte((byte) 0);
        byte type = mIn.readByte();
        int data = mIn.readInt();

        ResType mType = mType(decoder);
        StringBlock stringBlock = mTableStrings(decoder);

        if (mType.isString()) {
            /**
             * 字符串数量大于 0x01000000 或者等于 0 就没法判断是字符串还是id引用了,所以没法纠正
             */
            if (stringBlock.getCount() < 0x01000000 && data > 0) {
                int oldType = type;
                String tmpVale = stringBlock.getString(data);
                if (tmpVale != null && type != TypedValue.TYPE_STRING) {
                    type = TypedValue.TYPE_STRING;
                } else if (tmpVale == null
                        && type != TypedValue.TYPE_REFERENCE
                        && type != TypedValue.TYPE_DYNAMIC_REFERENCE) {
                    // TYPE_REFERENCE 和 TYPE_DYNAMIC_REFERENCE 目前是一样的操作
                    type = TypedValue.TYPE_REFERENCE;
                }

                if (oldType != type) {
                    LogHelper.warning("Correct value type : " + oldType + " > " + type);
                }
            }
        }

        return type == TypedValue.TYPE_STRING
//                ? mPkg.getValueFactory().factory(mTableStrings(decoder).getHTML(data), data)
                ? ResValueFactoryAj.factory(mType, stringBlock.getHTML(data), data)
                : mPkg.getValueFactory().factory(type, data, null);
    }

//    /**
//     * > readConfigFlags_read_setDeafultValue_28
//     * https://github.com/iBotPeaches/Apktool/pull/1084
//     * https://github.com/iBotPeaches/Apktool/commit/b0eb58a4263a198bc587f50c5ca855e849442d5d
//     * @param joinPoint
//     * @return
//     * @throws Exception
//     */
//    @Around("execution(* brut.androlib.res.decoder.ARSCDecoder.readConfigFlags())")
//    public ResConfigFlags readConfigFlags(ProceedingJoinPoint joinPoint) throws Exception {
//        ARSCDecoder decoder = (ARSCDecoder) joinPoint.getThis();
//        ExtDataInput mIn = mIn(decoder);
//
//        int size = mIn.readInt();
//        int read = 28;
//
//        if (size < 28) {
//            throw new AndrolibException("Config size < 28");
//        }
//
//        boolean isInvalid = false;
//
//        short mcc = mIn.readShort();
//        short mnc = mIn.readShort();
//
//        char[] language = unpackLanguageOrRegion(mIn.readByte(), mIn.readByte(), 'a');
//        char[] country = unpackLanguageOrRegion(mIn.readByte(), mIn.readByte(), '0');
//
//        byte orientation = mIn.readByte();
//        byte touchscreen = mIn.readByte();
//
//        int density = mIn.readUnsignedShort();
//
//        byte keyboard = mIn.readByte();
//        byte navigation = mIn.readByte();
//        byte inputFlags = mIn.readByte();
//		/* inputPad0 */mIn.skipBytes(1);
//
//        short screenWidth = mIn.readShort();
//        short screenHeight = mIn.readShort();
//
//        short sdkVersion = mIn.readShort();
//		/* minorVersion, now must always be 0 */mIn.skipBytes(2);
//
//        byte screenLayout = 0;
//        byte uiMode = 0;
//        short smallestScreenWidthDp = 0;
//        if (size >= 32) {
//            screenLayout = mIn.readByte();
//            uiMode = mIn.readByte();
//            smallestScreenWidthDp = mIn.readShort();
//            read = 32;
//        }
//
//        short screenWidthDp = 0;
//        short screenHeightDp = 0;
//        if (size >= 36) {
//            screenWidthDp = mIn.readShort();
//            screenHeightDp = mIn.readShort();
//            read = 36;
//        }
//
//        char[] localeScript = null;
//        char[] localeVariant = null;
//        if (size >= 48) {
//            localeScript = readScriptOrVariantChar(mIn, 4).toCharArray();
//            localeVariant = readScriptOrVariantChar(mIn, 8).toCharArray();
//            read = 48;
//        }
//
//        byte screenLayout2 = 0;
//        if (size >= 52) {
//            screenLayout2 = mIn.readByte();
//            mIn.skipBytes(3); // reserved padding
//            read = 52;
//        }
//
////        int exceedingSize = size - decoder.KNOWN_CONFIG_BYTES;
//        int exceedingSize = size - 52;
//        if (exceedingSize > 0) {
//            byte[] buf = new byte[exceedingSize];
//            read += exceedingSize;
//            mIn.readFully(buf);
//            BigInteger exceedingBI = new BigInteger(1, buf);
//
//            if (exceedingBI.equals(BigInteger.ZERO)) {
//                LogHelper.fine(String
//                        .format("Config flags size > %d, but exceeding bytes are all zero, so it should be ok.",
//                                52));
//            } else {
//                LogHelper.warning(String.format("Config flags size > %d. Exceeding bytes: 0x%X.",
//                        52, exceedingBI));
//                isInvalid = true;
//            }
//        }
//
//        int remainingSize = size - read;
//        if (remainingSize > 0) {
//            mIn.skipBytes(remainingSize);
//        }
//
//        return new ResConfigFlags(mcc, mnc, language, country,
//                orientation, touchscreen, density, keyboard, navigation,
//                inputFlags, screenWidth, screenHeight, sdkVersion,
//                screenLayout, uiMode, smallestScreenWidthDp, screenWidthDp,
//                screenHeightDp, localeScript, localeVariant, screenLayout2, isInvalid);
//    }
//
//    private char[] unpackLanguageOrRegion(byte in0, byte in1, char base) throws AndrolibException {
//        // check high bit, if so we have a packed 3 letter code
//        if (((in0 >> 7) & 1) == 1) {
//            int first = in1 & 0x1F;
//            int second = ((in1 & 0xE0) >> 5) + ((in0 & 0x03) << 3);
//            int third = (in0 & 0x7C) >> 2;
//
//            // since this function handles languages & regions, we add the value(s) to the base char
//            // which is usually 'a' or '0' depending on language or region.
//            return new char[] { (char) (first + base), (char) (second + base), (char) (third + base) };
//        }
//        return new char[] { (char) in0, (char) in1 };
//    }
//
//    private String readScriptOrVariantChar(ExtDataInput mIn, int length) throws AndrolibException, IOException {
//        StringBuilder string = new StringBuilder(16);
//
//        while(length-- != 0) {
//            short ch = mIn.readByte();
//            if (ch == 0) {
//                break;
//            }
//            string.append((char) ch);
//        }
//        mIn.skipBytes(length);
//
//        return string.toString();
//    }
//    /**
//     * < readConfigFlags_read_setDeafultValue_28
//     */

}
