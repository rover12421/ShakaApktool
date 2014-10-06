package com.rover12421.shaka.apktool.mutable.reference;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import org.jf.dexlib2.ReferenceType;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.dexbacked.reference.DexBackedFieldReference;
import org.jf.dexlib2.dexbacked.reference.DexBackedMethodReference;
import org.jf.dexlib2.dexbacked.reference.DexBackedStringReference;
import org.jf.dexlib2.dexbacked.reference.DexBackedTypeReference;
import org.jf.dexlib2.iface.reference.Reference;
import org.jf.util.ExceptionWithContext;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 9/30/14.
 */
public class MutableReference {
    public static Reference makeReference(@Nonnull MutableDexFile dexFile, int referenceType, int referenceIndex) {
        switch (referenceType) {
            case ReferenceType.STRING:
                return new MutableStringReference(dexFile, new DexBackedStringReference(dexFile.getDexBackedDexFile(), referenceIndex));
            case ReferenceType.TYPE:
                return new MutableTypeReference(dexFile, new DexBackedTypeReference(dexFile.getDexBackedDexFile(), referenceIndex));
            case ReferenceType.METHOD:
                return new MutableMethodReference(dexFile, new DexBackedMethodReference(dexFile.getDexBackedDexFile(), referenceIndex));
            case ReferenceType.FIELD:
                return new MutableFieldReference(dexFile, new DexBackedFieldReference(dexFile.getDexBackedDexFile(), referenceIndex));
            default:
                throw new ExceptionWithContext("Invalid reference type: %d", referenceType);
        }
    }
}
