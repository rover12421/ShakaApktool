package com.rover12421.shaka.apktool.mutable;

import com.google.common.collect.Iterables;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import org.jf.dexlib2.base.reference.BaseTypeReference;
import org.jf.dexlib2.dexbacked.DexBackedAnnotation;
import org.jf.dexlib2.dexbacked.DexBackedClassDef;
import org.jf.dexlib2.dexbacked.DexBackedField;
import org.jf.dexlib2.dexbacked.DexBackedMethod;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.Field;
import org.jf.dexlib2.iface.Method;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by rover12421 on 9/29/14.
 */
public class MutableClassDef extends BaseTypeReference implements ClassDef  {
    @Nonnull private final MutableDexFile dexFile;
    @Nonnull private final DexBackedClassDef dexBackedClassDef;
    private int classDefOffset;

    private int accessFlags;
    private String superclass;
    private String type;
    private String sourceFile;

    private Set<String> interfaces;
    private Set<MutableAnnotation> annotations;
    private List<MutableField> staticFields;
    private List<MutableField> instanceFields;
    private List<MutableMethod> directMethods;
    private List<MutableMethod> virtualMethods;

    public MutableClassDef(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedClassDef dexBackedClassDef) {
        this.dexFile = dexFile;
        this.dexBackedClassDef = dexBackedClassDef;
        try {
            classDefOffset = (int) ReflectUtil.getFieldValue(dexBackedClassDef, "classDefOffset");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        accessFlags = dexBackedClassDef.getAccessFlags();
        superclass  = dexBackedClassDef.getSuperclass();
        type        = dexBackedClassDef.getType();
        sourceFile  = dexBackedClassDef.getSourceFile();
    }

    @Nonnull
    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getAccessFlags() {
        return accessFlags;
    }

    @Nullable
    @Override
    public String getSuperclass() {
        return superclass;
    }

    @Nullable
    @Override
    public String getSourceFile() {
        return sourceFile;
    }

    @Nonnull
    @Override
    public Set<String> getInterfaces() {
        if (interfaces == null) {
            interfaces = new HashSet<>();
            for (String inf : dexBackedClassDef.getInterfaces()) {
                interfaces.add(inf);
            }
        }
        return interfaces;
    }

    @Nonnull
    @Override
    public Set<? extends MutableAnnotation> getAnnotations() {
        if (annotations == null) {
            Set<DexBackedAnnotation> dexBackedAnnotations = (Set<DexBackedAnnotation>) dexBackedClassDef.getAnnotations();
            annotations = new HashSet<>();
            for (DexBackedAnnotation dexBackedAnnotation : dexBackedAnnotations) {
                annotations.add(new MutableAnnotation(dexFile, dexBackedAnnotation));
            }
        }

        return annotations;
    }

    @Nonnull
    @Override
    public Iterable<? extends MutableField> getStaticFields() {
        if (staticFields == null) {
            Iterable<? extends DexBackedField> dexBackedFields = dexBackedClassDef.getStaticFields();
            staticFields = new ArrayList<>();
            for (DexBackedField dexBackedField : dexBackedFields) {
                staticFields.add(new MutableField(dexFile, this, dexBackedField));
            }
        }

        return staticFields;
    }

    @Nonnull
    @Override
    public Iterable<? extends Field> getInstanceFields() {
        if (instanceFields == null) {
            Iterable<? extends DexBackedField> dexBackedFields = dexBackedClassDef.getInstanceFields();
            instanceFields = new ArrayList<>();
            for (DexBackedField dexBackedField : dexBackedFields) {
                instanceFields.add(new MutableField(dexFile, this, dexBackedField));
            }
        }

        return instanceFields;
    }

    @Nonnull
    @Override
    public Iterable<? extends Field> getFields() {
        return Iterables.concat(getStaticFields(), getInstanceFields());
    }

    @Nonnull
    @Override
    public Iterable<? extends MutableMethod> getDirectMethods() {
        if (directMethods == null) {
            Iterable<? extends DexBackedMethod> dexBackedMethods = dexBackedClassDef.getDirectMethods();
            directMethods = new ArrayList<>();
            for (DexBackedMethod dexBackedMethod : dexBackedMethods) {
                MutableMethod method = new MutableMethod(dexFile, this, dexBackedMethod);
                directMethods.add(method);
            }
        }
        return directMethods;
    }

    @Nonnull
    @Override
    public Iterable<? extends MutableMethod> getVirtualMethods() {
        if (virtualMethods == null) {
            Iterable<? extends DexBackedMethod> dexBackedMethods = dexBackedClassDef.getVirtualMethods();
            virtualMethods = new ArrayList<>();
            for (DexBackedMethod dexBackedMethod : dexBackedMethods) {
                MutableMethod method = new MutableMethod(dexFile, this, dexBackedMethod);
                virtualMethods.add(method);
            }
        }
        return virtualMethods;
    }

    @Nonnull
    @Override
    public Iterable<? extends Method> getMethods() {
        return Iterables.concat(getDirectMethods(), getVirtualMethods());
    }

}
