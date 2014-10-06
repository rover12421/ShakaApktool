package com.rover12421.shaka.apktool.mutable;

import com.google.common.collect.Iterators;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import org.jf.dexlib2.base.reference.BaseMethodReference;
import org.jf.dexlib2.dexbacked.DexBackedAnnotation;
import org.jf.dexlib2.dexbacked.DexBackedMethod;
import org.jf.dexlib2.dexbacked.DexBackedMethodImplementation;
import org.jf.dexlib2.dexbacked.util.ParameterIterator;
import org.jf.dexlib2.iface.Annotation;
import org.jf.dexlib2.iface.Method;
import org.jf.dexlib2.iface.MethodParameter;
import org.jf.util.AbstractForwardSequentialList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by rover12421 on 9/29/14.
 */
public class MutableMethod extends BaseMethodReference implements Method {
    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final MutableClassDef classDef;
    @Nonnull public final DexBackedMethod dexBackedMethod;

    private int accessFlags;
    private Set<MutableAnnotation> annotations;
    private String name;
    private String returnType;
    private List<String> parameterTypes;
    private List<HashSet<MutableAnnotation>> parameterAnnotations;

    public MutableMethod(@Nonnull MutableDexFile dexFile, @Nonnull MutableClassDef classDef, @Nonnull DexBackedMethod dexBackedMethod) {
        this.dexFile = dexFile;
        this.classDef = classDef;
        this.dexBackedMethod = dexBackedMethod;

        accessFlags = dexBackedMethod.getAccessFlags();
        name        = dexBackedMethod.getName();
        returnType  = dexBackedMethod.getReturnType();
    }

    @Override
    public int getAccessFlags() {
        return accessFlags;
    }

    @Nonnull
    @Override
    public Set<? extends Annotation> getAnnotations() {
        if (annotations == null) {
            Set<DexBackedAnnotation> dexBackedAnnotations = (Set<DexBackedAnnotation>) dexBackedMethod.getAnnotations();
            annotations = new HashSet<>();
            for (DexBackedAnnotation dexBackedAnnotation : dexBackedAnnotations) {
                annotations.add(new MutableAnnotation(dexFile, dexBackedAnnotation));
            }
        }

        return annotations;
    }

    @Nullable
    @Override
    public MutableMethodImplementation getImplementation() {
        DexBackedMethodImplementation dexBackedMethodImplementation = dexBackedMethod.getImplementation();
        if (dexBackedMethodImplementation != null) {
            return new MutableMethodImplementation(dexFile, this, dexBackedMethodImplementation);
        }
        return null;
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
    public String getReturnType() {
        return returnType;
    }

    @Nonnull
    @Override
    public List<String> getParameterTypes() {
        if (parameterTypes == null) {
            parameterTypes = new ArrayList<>(dexBackedMethod.getParameterTypes());
        }
        return parameterTypes;
    }

    @Nonnull
    @Override
    public List<? extends MethodParameter> getParameters() {
        int parametersOffset = getParametersOffset();
        if (parametersOffset > 0) {
            final List<String> parameterTypes = getParameterTypes();

            return new AbstractForwardSequentialList<MethodParameter>() {
                @Nonnull @Override public Iterator<MethodParameter> iterator() {
                    return new ParameterIterator(parameterTypes,
                            getParameterAnnotations(),
                            getParameterNames());
                }

                @Override public int size() {
                    return parameterTypes.size();
                }
            };
        }
        return new ArrayList<>();
    }

    @Nonnull
    public List<? extends Set<? extends MutableAnnotation>> getParameterAnnotations() {
        if (parameterAnnotations == null) {
            List<Set<DexBackedAnnotation>> dexBackParameterAnnotations = (List<Set<DexBackedAnnotation>>) dexBackedMethod.getParameterAnnotations();
            parameterAnnotations = new ArrayList<>();
            for (Set<DexBackedAnnotation> dexBackedAnnotations : dexBackParameterAnnotations) {
                HashSet<MutableAnnotation> mutableAnnotations = new HashSet<>();
                for (DexBackedAnnotation dexBackedAnnotation : dexBackedAnnotations) {
                    mutableAnnotations.add(new MutableAnnotation(dexFile, dexBackedAnnotation));
                }
                parameterAnnotations.add(mutableAnnotations);
            }
        }

        return parameterAnnotations;
    }

    @Nonnull
    public Iterator<String> getParameterNames() {
        MutableMethodImplementation methodImpl = getImplementation();
        if (methodImpl != null) {
            return methodImpl.getParameterNames();
        }
        return Iterators.emptyIterator();
    }

    private int getMethodIdItemOffset() {
        try {
            return (int) ReflectUtil.invokeMethod(dexBackedMethod, "getMethodIdItemOffset", new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private int getProtoIdItemOffset() {
        try {
            return (int) ReflectUtil.invokeMethod(dexBackedMethod, "getProtoIdItemOffset", new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private int getParametersOffset() {
        try {
            return (int) ReflectUtil.invokeMethod(dexBackedMethod, "getParametersOffset", new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
