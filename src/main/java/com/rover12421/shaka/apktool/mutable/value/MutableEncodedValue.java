package com.rover12421.shaka.apktool.mutable.value;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import org.jf.dexlib2.ValueType;
import org.jf.dexlib2.dexbacked.value.*;
import org.jf.dexlib2.iface.value.EncodedValue;

/**
 * Created by rover12421 on 10/7/14.
 */
public abstract class MutableEncodedValue {
    public static EncodedValue makeEncodedValue(MutableDexFile dexFile, EncodedValue encodedValue) {

        switch (encodedValue.getValueType()) {
            case ValueType.STRING:
                if (encodedValue instanceof DexBackedStringEncodedValue) {
                    return new MutableStringEncodedValue(dexFile, (DexBackedStringEncodedValue) encodedValue);
                } else {
                    return encodedValue;
                }
            case ValueType.TYPE:
                if (encodedValue instanceof DexBackedTypeEncodedValue) {
                    return new MutableTypeEncodedValue(dexFile, (DexBackedTypeEncodedValue) encodedValue);
                } else {
                    return encodedValue;
                }
            case ValueType.FIELD:
                if (encodedValue instanceof DexBackedFieldEncodedValue) {
                    return new MutableFieldEncodedValue(dexFile, (DexBackedFieldEncodedValue) encodedValue);
                } else {
                    return encodedValue;
                }
            case ValueType.METHOD:
                if (encodedValue instanceof DexBackedMethodEncodedValue) {
                    return new MutableMethodEncodedValue(dexFile, (DexBackedMethodEncodedValue) encodedValue);
                } else {
                    return encodedValue;
                }
            case ValueType.ENUM:
                if (encodedValue instanceof DexBackedEnumEncodedValue) {
                    return new MutableEnumEncodedValue(dexFile, (DexBackedEnumEncodedValue) encodedValue);
                } else {
                    return encodedValue;
                }
            case ValueType.ARRAY:
                if (encodedValue instanceof DexBackedArrayEncodedValue) {
                    return new MutableArrayEncodedValue(dexFile, (DexBackedArrayEncodedValue) encodedValue);
                } else {
                    return encodedValue;
                }
            case ValueType.ANNOTATION:
                if (encodedValue instanceof DexBackedAnnotationEncodedValue) {
                    return new MutableAnnotationEncodedValue(dexFile, (DexBackedAnnotationEncodedValue) encodedValue);
                } else {
                    return encodedValue;
                }
            default:
                return encodedValue;
        }

    }
}
