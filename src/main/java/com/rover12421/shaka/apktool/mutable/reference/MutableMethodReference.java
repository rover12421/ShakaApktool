package com.rover12421.shaka.apktool.mutable.reference;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import org.jf.dexlib2.base.reference.BaseMethodReference;
import org.jf.dexlib2.dexbacked.reference.DexBackedMethodReference;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rover12421 on 9/30/14.
 */
public class MutableMethodReference extends BaseMethodReference {
    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final DexBackedMethodReference dexBackedMethodReference;

    private int methodIdItemOffset;
    private String definingClass;
    private String name;
    private String returnType;
    private List<String> parameterTypes;

    public MutableMethodReference(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedMethodReference dexBackedMethodReference) {
        this.dexFile = dexFile;
        this.dexBackedMethodReference = dexBackedMethodReference;

        methodIdItemOffset = dexBackedMethodReference.methodIdItemOffset;
        definingClass = dexBackedMethodReference.getDefiningClass();
        name = dexBackedMethodReference.getName();
        returnType = dexBackedMethodReference.getReturnType();
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
    public List<? extends CharSequence> getParameterTypes() {
        if (parameterTypes == null) {
            parameterTypes = new ArrayList<>(dexBackedMethodReference.getParameterTypes());

        }
        return parameterTypes;
    }

    @Nonnull
    @Override
    public String getReturnType() {
        return returnType;
    }

    private int getProtoIdItemOffset() {
        try {
            return (int) ReflectUtil.invokeMethod(dexBackedMethodReference, "getProtoIdItemOffset", new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
