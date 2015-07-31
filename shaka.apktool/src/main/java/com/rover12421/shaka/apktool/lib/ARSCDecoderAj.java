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
import brut.androlib.res.data.ResPackage;
import brut.androlib.res.data.ResType;
import brut.androlib.res.data.value.ResIntBasedValue;
import brut.androlib.res.decoder.ARSCDecoder;
import brut.androlib.res.decoder.StringBlock;
import brut.util.ExtDataInput;
import com.rover12421.shaka.lib.LogHelper;
import com.rover12421.shaka.lib.ReflectUtil;
import com.rover12421.shaka.lib.ShakaDecodeOption;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.io.EOFException;
import java.io.IOException;

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
        return  (ExtDataInput) ReflectUtil.getFieldValue(thiz, "mIn");
    }

    public ResPackage mPkg(ARSCDecoder thiz) throws Exception {
        return  (ResPackage) ReflectUtil.getFieldValue(thiz, "mPkg");
    }

    public ARSCDecoder.Header mHeader(ARSCDecoder thiz) throws Exception {
        return  (ARSCDecoder.Header) ReflectUtil.getFieldValue(thiz, "mHeader");
    }

    public StringBlock mTableStrings(ARSCDecoder thiz) throws Exception {
        return  (StringBlock) ReflectUtil.getFieldValue(thiz, "mTableStrings");
    }

    public ResType mType(ARSCDecoder thiz) throws Exception {
        return  (ResType) ReflectUtil.getFieldValue(thiz, "mType");
    }

    @Around("execution(* brut.androlib.res.decoder.ARSCDecoder.nextChunk())")
    public ARSCDecoder.Header nextChunk(ProceedingJoinPoint joinPoint) throws Exception {
//        return mHeader = Header.read(mIn);
        ExtDataInput mIn = mIn((ARSCDecoder) joinPoint.getThis());
        ARSCDecoder.Header mHeader = ResChunk_header.read(mIn);
        ReflectUtil.setFieldValue(joinPoint.getThis(), "mHeader", mHeader);
        return mHeader;
    }

    @Around("execution(* brut.androlib.res.decoder.ARSCDecoder.readTable())")
    public ResPackage[] readTable(ProceedingJoinPoint joinPoint) throws Exception {
        ARSCDecoder decoder = (ARSCDecoder) joinPoint.getThis();
        ExtDataInput mIn = mIn(decoder);

//        nextChunkCheckType(ARSCDecoder.Header.TYPE_TABLE);
        ReflectUtil.getMethod(decoder, "nextChunkCheckType", int.class)
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
        ReflectUtil.setFieldValue(decoder, "mTableStrings", mTableStrings);

        ResPackage[] packages = new ResPackage[packageCount];

//        nextChunk();
        ReflectUtil.invokeMethod(decoder, "nextChunk");

        for (int i = 0; i < packageCount; i++) {
//            packages[i] = readPackage();
            packages[i] = (ResPackage) ReflectUtil.invokeMethod(decoder, "readPackage");
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

        return type == TypedValue.TYPE_STRING
//                ? mPkg.getValueFactory().factory(mTableStrings(decoder).getHTML(data), data)
                ? ResValueFactoryAj.factory(mType, mTableStrings(decoder).getHTML(data), data)
                : mPkg.getValueFactory().factory(type, data, null);
    }


}
