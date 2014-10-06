package com.rover12421.shaka.apktool.mutable.value;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import org.jf.dexlib2.base.value.BaseStringEncodedValue;
import org.jf.dexlib2.dexbacked.value.DexBackedStringEncodedValue;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 10/7/14.
 */
public class MutableStringEncodedValue extends BaseStringEncodedValue {
    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final DexBackedStringEncodedValue dexBackedStringEncodedValue;

    private String string;

    public MutableStringEncodedValue(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedStringEncodedValue dexBackedStringEncodedValue) {
        this.dexFile = dexFile;
        this.dexBackedStringEncodedValue = dexBackedStringEncodedValue;

        string = dexBackedStringEncodedValue.getValue();
    }

    @Nonnull
    @Override
    public String getValue() {
        return string;
    }
}
