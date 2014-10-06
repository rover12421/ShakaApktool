package com.rover12421.shaka.apktool.mutable.instruction;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import com.rover12421.shaka.apktool.mutable.reference.MutableReference;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction22c;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction31c;
import org.jf.dexlib2.iface.reference.Reference;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 9/30/14.
 */
public class MutableInstruction31c extends DexBackedInstruction31c {
    @Nonnull public MutableDexFile mutableDexFile;
    @Nonnull public DexBackedInstruction31c dexBackedInstruction31c;

    private Reference reference;

    public MutableInstruction31c(@Nonnull MutableDexFile mutableDexFile, @Nonnull DexBackedInstruction31c dexBackedInstruction31c) {
        super(dexBackedInstruction31c.dexFile, dexBackedInstruction31c.opcode, dexBackedInstruction31c.instructionStart);
        this.dexBackedInstruction31c = dexBackedInstruction31c;
        this.mutableDexFile = mutableDexFile;
    }

    @Nonnull
    @Override
    public Reference getReference() {
        if (reference == null) {
            reference = MutableReference.makeReference(mutableDexFile, opcode.referenceType,
                    dexFile.readSmallUint(instructionStart + 2));
        }
        return reference;
    }
}
