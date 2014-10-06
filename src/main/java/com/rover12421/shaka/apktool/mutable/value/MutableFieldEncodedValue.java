package com.rover12421.shaka.apktool.mutable.value;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import com.rover12421.shaka.apktool.mutable.reference.MutableFieldReference;
import org.jf.dexlib2.base.value.BaseFieldEncodedValue;
import org.jf.dexlib2.dexbacked.reference.DexBackedFieldReference;
import org.jf.dexlib2.dexbacked.value.DexBackedFieldEncodedValue;
import org.jf.dexlib2.iface.reference.FieldReference;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 10/7/14.
 */
public class MutableFieldEncodedValue extends BaseFieldEncodedValue {
    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final DexBackedFieldEncodedValue dexBackedFieldEncodedValue;

    private MutableFieldReference fieldReference;

    public MutableFieldEncodedValue(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedFieldEncodedValue dexBackedFieldEncodedValue) {
        this.dexFile = dexFile;
        this.dexBackedFieldEncodedValue = dexBackedFieldEncodedValue;

        fieldReference = new MutableFieldReference(dexFile, (DexBackedFieldReference) dexBackedFieldEncodedValue.getValue());
    }

    @Nonnull
    @Override
    public FieldReference getValue() {
        return fieldReference;
    }
}
