package com.rover12421.shaka.apktool.mutable.reference;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import org.jf.dexlib2.base.reference.BaseFieldReference;
import org.jf.dexlib2.dexbacked.DexBackedClassDef;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.dexbacked.raw.FieldIdItem;
import org.jf.dexlib2.dexbacked.reference.DexBackedFieldReference;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 9/30/14.
 */
public class MutableFieldReference extends BaseFieldReference {
    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final DexBackedFieldReference dexBackedFieldReference;
    private int fieldIdItemOffset;
    private String definingClass;
    private String name;
    private String type;

    public MutableFieldReference(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedFieldReference dexBackedFieldReference) {
        this.dexFile = dexFile;
        this.dexBackedFieldReference = dexBackedFieldReference;

        fieldIdItemOffset = dexBackedFieldReference.fieldIdItemOffset;

        definingClass = dexBackedFieldReference.getDefiningClass();
        name = dexBackedFieldReference.getName();
        type = dexBackedFieldReference.getType();
    }

    @Nonnull
    @Override
    public String getDefiningClass() {
        return definingClass;
    }

    @Nonnull
    @Override
    public String getName() {
        return name;
    }

    @Nonnull
    @Override
    public String getType() {
        return type;
    }
}
