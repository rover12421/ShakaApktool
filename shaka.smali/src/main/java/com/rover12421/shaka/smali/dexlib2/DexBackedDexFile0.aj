package com.rover12421.shaka.smali.dexlib2;

import org.jf.dexlib2.Opcodes;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 12/5/15.
 */
privileged public aspect DexBackedDexFile0 {
    public DexBackedDexFile.new(@Nonnull Opcodes opcodes, @Nonnull byte[] buf, int offset, boolean verifyMagic
            , Object _) {
        this(opcodes, buf, offset, verifyMagic);
    }
}
