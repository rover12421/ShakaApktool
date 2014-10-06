package com.rover12421.shaka.apktool.mutable.reference;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import org.jf.dexlib2.base.reference.BaseStringReference;
import org.jf.dexlib2.dexbacked.reference.DexBackedStringReference;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 9/30/14.
 */
public class MutableStringReference extends BaseStringReference {
    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final DexBackedStringReference dexBackedStringReference;
    private String string;

    public MutableStringReference(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedStringReference dexBackedStringReference) {
        this.dexFile = dexFile;
        this.dexBackedStringReference = dexBackedStringReference;

        string = dexBackedStringReference.getString();
    }

    @Nonnull
    @Override
    public String getString() {
        return string;
    }
}
