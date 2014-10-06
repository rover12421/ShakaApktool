package com.rover12421.shaka.apktool.mutable.value;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import org.jf.dexlib2.base.value.BaseTypeEncodedValue;
import org.jf.dexlib2.dexbacked.value.DexBackedTypeEncodedValue;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 10/7/14.
 */
public class MutableTypeEncodedValue extends BaseTypeEncodedValue {
    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final DexBackedTypeEncodedValue dexBackedTypeEncodedValue;

    private String type;

    public MutableTypeEncodedValue(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedTypeEncodedValue dexBackedTypeEncodedValue) {
        this.dexFile = dexFile;
        this.dexBackedTypeEncodedValue = dexBackedTypeEncodedValue;

        type = dexBackedTypeEncodedValue.getValue();
    }

    @Nonnull
    @Override
    public String getValue() {
        return type;
    }
}
