package com.rover12421.shaka.smali.dexlib2;

import org.jf.dexlib2.dexbacked.DexBackedMethod;

/**
 * Created by rover12421 on 12/5/15.
 */
privileged public aspect DexBackedMethod0 {
    public int DexBackedMethod.getCodeOffset() {
        return this.codeOffset;
    }
}
