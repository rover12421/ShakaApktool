package com.rover12421.shaka.apktool.mutable;

import com.rover12421.shaka.apktool.mutable.value.MutableEncodedValue;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import org.jf.dexlib2.base.reference.BaseFieldReference;
import org.jf.dexlib2.dexbacked.DexBackedAnnotation;
import org.jf.dexlib2.dexbacked.DexBackedField;
import org.jf.dexlib2.dexbacked.raw.FieldIdItem;
import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.Field;
import org.jf.dexlib2.iface.value.EncodedValue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rover12421 on 9/29/14.
 */
public class MutableField extends BaseFieldReference implements Field {
    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final MutableClassDef classDef;
    @Nonnull public final DexBackedField dexBackedField;

    private int accessFlags;
    private Set<MutableAnnotation> annotations;
    private String name;
    private String type;
    private EncodedValue initialValue;

    public MutableField(@Nonnull MutableDexFile dexFile, @Nonnull MutableClassDef classDef, @Nonnull DexBackedField dexBackedField) {
        this.dexFile = dexFile;
        this.classDef = classDef;
        this.dexBackedField = dexBackedField;

        accessFlags = dexBackedField.getAccessFlags();
        name = dexBackedField.getName();
        type = dexBackedField.getType();
        initialValue = MutableEncodedValue.makeEncodedValue(dexFile, dexBackedField.getInitialValue());
    }

    @Override
    public int getAccessFlags() {
        return accessFlags;
    }

    @Nullable
    @Override
    public EncodedValue getInitialValue() {
        return initialValue;
    }

    @Nonnull
    @Override
    public Set<? extends Annotation> getAnnotations() {
        if (annotations == null) {
            Set<DexBackedAnnotation> dexBackedAnnotations = (Set<DexBackedAnnotation>) dexBackedField.getAnnotations();
            annotations = new HashSet<>();
            for (DexBackedAnnotation dexBackedAnnotation : dexBackedAnnotations) {
                annotations.add(new MutableAnnotation(dexFile, dexBackedAnnotation));
            }
        }

        return annotations;
    }

    @Nonnull
    @Override
    public String getDefiningClass() {
        return classDef.getType();
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

    private int getFieldIdItemOffset() {
        try {
            return (int) ReflectUtil.invokeMethod(dexBackedField, "getFieldIdItemOffset", new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
