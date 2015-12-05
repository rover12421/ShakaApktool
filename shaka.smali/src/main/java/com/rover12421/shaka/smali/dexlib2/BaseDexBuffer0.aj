package com.rover12421.shaka.smali.dexlib2;

import org.jf.dexlib2.dexbacked.BaseDexBuffer;

/**
 * Created by rover12421 on 12/5/15.
 */
privileged public aspect BaseDexBuffer0 {
    public int BaseDexBuffer.getBaseOffset0() {
        return this.getBaseOffset();
    }

    public byte[] org.jf.dexlib2.dexbacked.BaseDexBuffer.getBuf0() {
        return this.getBuf();
    }
}
