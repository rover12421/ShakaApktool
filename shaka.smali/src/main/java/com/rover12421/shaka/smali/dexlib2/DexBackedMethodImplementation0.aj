package com.rover12421.shaka.smali.dexlib2;

import org.jf.dexlib2.dexbacked.DexBackedMethodImplementation;

/**
 * Created by rover12421 on 12/6/15.
 */
privileged public aspect DexBackedMethodImplementation0 {
    public int DexBackedMethodImplementation.getCodeOffset() {
        return this.codeOffset;
    }
}
