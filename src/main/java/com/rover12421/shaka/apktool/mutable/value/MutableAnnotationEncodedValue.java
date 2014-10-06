package com.rover12421.shaka.apktool.mutable.value;

import com.rover12421.shaka.apktool.mutable.MutableAnnotationElement;
import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import org.jf.dexlib2.base.value.BaseAnnotationEncodedValue;
import org.jf.dexlib2.dexbacked.DexBackedAnnotationElement;
import org.jf.dexlib2.dexbacked.value.DexBackedAnnotationEncodedValue;
import org.jf.dexlib2.iface.value.AnnotationEncodedValue;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rover12421 on 10/7/14.
 */
public class MutableAnnotationEncodedValue extends BaseAnnotationEncodedValue implements AnnotationEncodedValue {
    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final DexBackedAnnotationEncodedValue dexBackedAnnotationEncodedValue;
    private String type;
    private Set<MutableAnnotationElement> elements;

    public MutableAnnotationEncodedValue(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedAnnotationEncodedValue dexBackedAnnotationEncodedValue) {
        this.dexFile = dexFile;
        this.dexBackedAnnotationEncodedValue = dexBackedAnnotationEncodedValue;

        type = dexBackedAnnotationEncodedValue.getType();
    }

    @Nonnull
    @Override
    public String getType() {
        return type;
    }

    @Nonnull
    @Override
    public Set<? extends MutableAnnotationElement> getElements() {
        if (elements == null) {
            Set<DexBackedAnnotationElement> dexBackedAnnotationElements = (Set<DexBackedAnnotationElement>) dexBackedAnnotationEncodedValue.getElements();
            elements = new HashSet<>();
            for (DexBackedAnnotationElement dexBackedAnnotationElement : dexBackedAnnotationElements) {
                elements.add(new MutableAnnotationElement(dexFile, dexBackedAnnotationElement));
            }
        }
        return elements;
    }
}
