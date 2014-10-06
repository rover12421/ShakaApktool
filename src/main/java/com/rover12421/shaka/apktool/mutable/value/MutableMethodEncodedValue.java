package com.rover12421.shaka.apktool.mutable.value;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import com.rover12421.shaka.apktool.mutable.reference.MutableMethodReference;
import org.jf.dexlib2.base.value.BaseMethodEncodedValue;
import org.jf.dexlib2.dexbacked.reference.DexBackedMethodReference;
import org.jf.dexlib2.dexbacked.value.DexBackedMethodEncodedValue;
import org.jf.dexlib2.iface.reference.MethodReference;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 10/7/14.
 */
public class MutableMethodEncodedValue extends BaseMethodEncodedValue {
    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final DexBackedMethodEncodedValue dexBackedMethodEncodedValue;

    private MutableMethodReference methodReference;

    public MutableMethodEncodedValue(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedMethodEncodedValue dexBackedMethodEncodedValue) {
        this.dexFile = dexFile;
        this.dexBackedMethodEncodedValue = dexBackedMethodEncodedValue;

        methodReference = new MutableMethodReference(dexFile, (DexBackedMethodReference) dexBackedMethodEncodedValue.getValue());
    }

    @Override
    public MethodReference getValue() {
        return methodReference;
    }
}
