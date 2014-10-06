package com.rover12421.shaka.apktool.mutable.value;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import com.rover12421.shaka.apktool.mutable.reference.MutableFieldReference;
import org.jf.dexlib2.base.value.BaseEnumEncodedValue;
import org.jf.dexlib2.dexbacked.reference.DexBackedFieldReference;
import org.jf.dexlib2.dexbacked.value.DexBackedEnumEncodedValue;
import org.jf.dexlib2.iface.reference.FieldReference;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 10/7/14.
 */
public class MutableEnumEncodedValue extends BaseEnumEncodedValue {
    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final DexBackedEnumEncodedValue dexBackedEnumEncodedValue;

    private MutableFieldReference fieldReference;

    public MutableEnumEncodedValue(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedEnumEncodedValue dexBackedEnumEncodedValue) {
        this.dexFile = dexFile;
        this.dexBackedEnumEncodedValue = dexBackedEnumEncodedValue;

        fieldReference = new MutableFieldReference(dexFile, (DexBackedFieldReference) dexBackedEnumEncodedValue.getValue());
    }

    @Nonnull
    @Override
    public FieldReference getValue() {
        return fieldReference;
    }
}
