package com.rover12421.shaka.apktool.mutable.value;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import org.jf.dexlib2.base.value.BaseArrayEncodedValue;
import org.jf.dexlib2.dexbacked.value.DexBackedArrayEncodedValue;
import org.jf.dexlib2.iface.value.ArrayEncodedValue;
import org.jf.dexlib2.iface.value.EncodedValue;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by rover12421 on 10/7/14.
 */
public class MutableArrayEncodedValue extends BaseArrayEncodedValue implements ArrayEncodedValue {
    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final DexBackedArrayEncodedValue dexBackedArrayEncodedValue;

    private List<EncodedValue> values;

    public MutableArrayEncodedValue(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedArrayEncodedValue dexBackedArrayEncodedValue) {
        this.dexFile = dexFile;
        this.dexBackedArrayEncodedValue = dexBackedArrayEncodedValue;
    }

    @Nonnull
    @Override
    public List<? extends EncodedValue> getValue() {
        if (values == null) {
            List<EncodedValue> dexBackValues = (List<EncodedValue>) dexBackedArrayEncodedValue.getValue();
            for (EncodedValue value : dexBackValues) {
                values.add(MutableEncodedValue.makeEncodedValue(dexFile, value));
            }
        }

        return values;
    }
}
