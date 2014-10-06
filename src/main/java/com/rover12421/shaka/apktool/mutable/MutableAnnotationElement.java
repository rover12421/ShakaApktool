package com.rover12421.shaka.apktool.mutable;

import com.rover12421.shaka.apktool.mutable.value.MutableEncodedValue;
import org.jf.dexlib2.base.BaseAnnotationElement;
import org.jf.dexlib2.dexbacked.DexBackedAnnotationElement;
import org.jf.dexlib2.iface.value.EncodedValue;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 9/29/14.
 */
public class MutableAnnotationElement extends BaseAnnotationElement {

    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final DexBackedAnnotationElement dexBackedAnnotationElement;

    @Nonnull public final EncodedValue value;

    private String name;

    public MutableAnnotationElement(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedAnnotationElement dexBackedAnnotationElement) {
        this.dexFile = dexFile;
        this.dexBackedAnnotationElement = dexBackedAnnotationElement;

        name = dexBackedAnnotationElement.getName();
        value = MutableEncodedValue.makeEncodedValue(dexFile, dexBackedAnnotationElement.getValue());
    }

    @Nonnull
    @Override
    public String getName() {
        return name;
    }

    @Nonnull
    @Override
    public EncodedValue getValue() {
        return value;
    }
}
