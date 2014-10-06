package com.rover12421.shaka.apktool.mutable;

import org.jf.dexlib2.base.BaseAnnotation;
import org.jf.dexlib2.dexbacked.DexBackedAnnotation;
import org.jf.dexlib2.dexbacked.DexBackedAnnotationElement;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rover12421 on 9/29/14.
 */
public class MutableAnnotation extends BaseAnnotation {

    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final DexBackedAnnotation dexBackedAnnotation;

    private int visibility;
    private String type;

    Set<MutableAnnotationElement> elements;

    public MutableAnnotation(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedAnnotation dexBackedAnnotation) {
        this.dexFile = dexFile;
        this.dexBackedAnnotation = dexBackedAnnotation;

        visibility = dexBackedAnnotation.getVisibility();
        type = dexBackedAnnotation.getType();
    }

    @Override
    public int getVisibility() {
        return visibility;
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
            Set<DexBackedAnnotationElement> dexBackedAnnotationElements = (Set<DexBackedAnnotationElement>) dexBackedAnnotation.getElements();
            elements = new HashSet<>();
            for (DexBackedAnnotationElement dexBackedAnnotationElement : dexBackedAnnotationElements) {
                elements.add(new MutableAnnotationElement(dexFile, dexBackedAnnotationElement));
            }
        }
        return elements;
    }
}
