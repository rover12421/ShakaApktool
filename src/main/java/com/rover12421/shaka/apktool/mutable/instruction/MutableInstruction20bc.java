package com.rover12421.shaka.apktool.mutable.instruction;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import com.rover12421.shaka.apktool.mutable.reference.MutableReference;
import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction20bc;
import org.jf.dexlib2.iface.reference.Reference;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 9/30/14.
 */
public class MutableInstruction20bc extends DexBackedInstruction20bc {

    @Nonnull public MutableDexFile mutableDexFile;
    @Nonnull public DexBackedInstruction20bc dexBackedInstruction20bc;

    private Reference reference;

    public MutableInstruction20bc( @Nonnull MutableDexFile mutableDexFile, @Nonnull DexBackedInstruction20bc dexBackedInstruction20bc) {
        super(dexBackedInstruction20bc.dexFile, dexBackedInstruction20bc.opcode, dexBackedInstruction20bc.instructionStart);
        this.dexBackedInstruction20bc = dexBackedInstruction20bc;
        this.mutableDexFile = mutableDexFile;
    }

    @Nonnull
    @Override
    public Reference getReference() {
        if (reference == null) {
            int referenceType = getReferenceType();
            reference = MutableReference.makeReference(mutableDexFile, referenceType, dexFile.readUshort(instructionStart + 2));
        }

        return reference;
    }
}
