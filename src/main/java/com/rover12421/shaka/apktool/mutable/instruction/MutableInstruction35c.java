package com.rover12421.shaka.apktool.mutable.instruction;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import com.rover12421.shaka.apktool.mutable.reference.MutableReference;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction35c;
import org.jf.dexlib2.iface.reference.Reference;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 9/30/14.
 */
public class MutableInstruction35c extends DexBackedInstruction35c {
    @Nonnull public MutableDexFile mutableDexFile;
    @Nonnull public DexBackedInstruction35c dexBackedInstruction35c;

    private Reference reference;

    public MutableInstruction35c(@Nonnull MutableDexFile mutableDexFile, @Nonnull DexBackedInstruction35c dexBackedInstruction35c) {
        super(dexBackedInstruction35c.dexFile, dexBackedInstruction35c.opcode, dexBackedInstruction35c.instructionStart);
        this.dexBackedInstruction35c = dexBackedInstruction35c;
        this.mutableDexFile = mutableDexFile;
    }

    @Nonnull
    @Override
    public Reference getReference() {
        if (reference == null) {
            reference = MutableReference.makeReference(mutableDexFile, opcode.referenceType,
                    dexFile.readUshort(instructionStart + 2));
        }
        return reference;
    }
}
