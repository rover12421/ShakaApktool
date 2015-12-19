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
import brut.androlib.res.data.ResTypeSpec;
import brut.androlib.res.data.value.ResIntBasedValue;
import brut.androlib.res.decoder.ARSCDecoder;
import brut.androlib.res.decoder.StringBlock;
import brut.util.ExtDataInput;
import com.rover12421.shaka.lib.LogHelper;
import com.rover12421.shaka.lib.ShakaDecodeOption;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

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

    @Around("execution(* brut.androlib.res.decoder.ARSCDecoder.readTableHeader())")
    public ResPackage[] readTableHeader(ProceedingJoinPoint joinPoint) throws Exception {
        ARSCDecoder decoder = (ARSCDecoder) joinPoint.getThis();
        ExtDataInput mIn = decoder.getIn();

//        nextChunkCheckType(ARSCDecoder.Header.TYPE_TABLE);
        decoder.nextChunkCheckType0(ARSCDecoder.Header.TYPE_TABLE);
        int packageCount = mIn.readInt();

        try {
            ARSCDecoder.Header header = decoder.getHeader();
            if (header.headerSize > ResTable_header_SIZE) {
                int skip = header.headerSize - ResTable_header_SIZE;
                LogHelper.warning("ResChunk_header exception : read size = " + header.headerSize + ", skip " + skip);
                mIn.skipBytes(skip);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }


        StringBlock mTableStrings = StringBlock.read(mIn);
        decoder.setTableStrings(mTableStrings);

        ResPackage[] packages = new ResPackage[packageCount];

//        nextChunk();
        decoder.nextChunk0();

        for (int i = 0; i < packageCount; i++) {
//            packages[i] = readTablePackage();
            packages[i] = decoder.readTablePackage0();
        }
        return packages;
    }

    @Around("execution(* brut.androlib.res.decoder.ARSCDecoder.readValue())")
    public ResIntBasedValue readValue(ProceedingJoinPoint joinPoint) throws Exception {
        ARSCDecoder decoder = (ARSCDecoder) joinPoint.getThis();
        ExtDataInput mIn = decoder.getIn();
        ResPackage mPkg = decoder.getPkg();

		/* size */mIn.skipCheckShort((short) 8);
		/* zero */mIn.skipCheckByte((byte) 0);
        byte type = mIn.readByte();
        int data = mIn.readInt();

        ResTypeSpec mTypeSpec = decoder.getTypeSpec();
        StringBlock stringBlock = decoder.getTableStrings();

        return type == TypedValue.TYPE_STRING
//                ? mPkg.getValueFactory().factory(mTableStrings(decoder).getHTML(data), data)
                ? ResValueFactoryAj.factory(mTypeSpec, stringBlock.getHTML(data), data)
                : mPkg.getValueFactory().factory(type, data, null);
    }

}
