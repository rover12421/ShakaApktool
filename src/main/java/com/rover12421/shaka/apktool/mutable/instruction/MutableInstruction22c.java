package com.rover12421.shaka.apktool.mutable.instruction;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import com.rover12421.shaka.apktool.mutable.reference.MutableReference;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction22c;
import org.jf.dexlib2.iface.reference.Reference;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 9/30/14.
 */
public class MutableInstruction22c extends DexBackedInstruction22c {
    @Nonnull public MutableDexFile mutableDexFile;
    @Nonnull public DexBackedInstruction22c dexBackedInstruction22c;

    private Reference reference;

    public MutableInstruction22c(@Nonnull MutableDexFile mutableDexFile, @Nonnull DexBackedInstruction22c dexBackedInstruction22c) {
        super(dexBackedInstruction22c.dexFile, dexBackedInstruction22c.opcode, dexBackedInstruction22c.instructionStart);
        this.dexBackedInstruction22c = dexBackedInstruction22c;
        this.mutableDexFile = mutableDexFile;
    }

    @Nonnull
    @Override
    public Reference getReference() {
        if (reference == null) {
            reference = MutableReference.makeReference(mutableDexFile, opcode.referenceType, dexFile.readUshort(instructionStart + 2));
        }
        return reference;
    }
}
