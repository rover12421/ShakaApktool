package com.rover12421.shaka.apktool.lib;

import brut.androlib.res.decoder.ResFileDecoder;
import brut.androlib.res.decoder.ResStreamDecoderContainer;

/**
 * Created by rover12421 on 12/5/15.
 */
privileged public aspect ResFileDecoder0 {
    public ResStreamDecoderContainer ResFileDecoder.getDecoders() {
        return this.mDecoders;
    }
}
