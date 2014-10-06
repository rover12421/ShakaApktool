package com.rover12421.shaka.apktool.mutable.reference;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import org.jf.dexlib2.base.reference.BaseTypeReference;
import org.jf.dexlib2.dexbacked.reference.DexBackedTypeReference;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 9/30/14.
 */
public class MutableTypeReference extends BaseTypeReference {
    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final DexBackedTypeReference dexBackedTypeReference;

    private String type;

    public MutableTypeReference(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedTypeReference dexBackedTypeReference) {
        this.dexFile = dexFile;
        this.dexBackedTypeReference = dexBackedTypeReference;

        type = dexBackedTypeReference.getType();
    }

    @Nonnull
    @Override
    public String getType() {
        return type;
    }
}
