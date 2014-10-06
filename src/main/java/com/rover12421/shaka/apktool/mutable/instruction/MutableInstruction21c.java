package com.rover12421.shaka.apktool.mutable.instruction;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import com.rover12421.shaka.apktool.mutable.reference.MutableReference;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction21c;
import org.jf.dexlib2.iface.reference.Reference;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 9/30/14.
 */
public class MutableInstruction21c extends DexBackedInstruction21c {
    @Nonnull public MutableDexFile mutableDexFile;
    @Nonnull public DexBackedInstruction21c dexBackedInstruction21c;

    private Reference reference;

    public MutableInstruction21c(@Nonnull MutableDexFile mutableDexFile, @Nonnull DexBackedInstruction21c dexBackedInstruction21c) {
        super(dexBackedInstruction21c.dexFile, dexBackedInstruction21c.opcode, dexBackedInstruction21c.instructionStart);
        this.dexBackedInstruction21c = dexBackedInstruction21c;
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
