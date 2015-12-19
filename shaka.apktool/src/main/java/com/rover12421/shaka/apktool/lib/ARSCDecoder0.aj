package com.rover12421.shaka.apktool.lib;

import brut.androlib.AndrolibException;
import brut.androlib.res.data.ResPackage;
import brut.androlib.res.data.ResType;
import brut.androlib.res.data.ResTypeSpec;
import brut.androlib.res.decoder.ARSCDecoder;
import brut.androlib.res.decoder.StringBlock;
import brut.util.ExtDataInput;

import java.io.IOException;

/**
 * Created by rover12421 on 12/5/15.
 */
privileged public aspect ARSCDecoder0 {
    public Header ARSCDecoder.getHeader() {
        return this.mHeader;
    }

    public ExtDataInput ARSCDecoder.getIn() {
        return this.mIn;
    }

    public ResPackage ARSCDecoder.getPkg() {
        return this.mPkg;
    }

    public StringBlock ARSCDecoder.getTableStrings() {
        return this.mTableStrings;
    }

    public void ARSCDecoder.setTableStrings(StringBlock stringBlock) {
        this.mTableStrings = stringBlock;
    }

    public ResType ARSCDecoder.getType() {
        return mType;
    }

    public ResTypeSpec ARSCDecoder.getTypeSpec() {
        return mTypeSpec;
    }

    public void ARSCDecoder.nextChunkCheckType0(int expectedType) throws IOException,
            AndrolibException {
        this.nextChunkCheckType(expectedType);
    }

    public Header ARSCDecoder.nextChunk0() throws IOException {
        return this.nextChunk();
    }

    public ResPackage ARSCDecoder.readTablePackage0() throws IOException, AndrolibException {
        return this.readTablePackage();
    }
}
