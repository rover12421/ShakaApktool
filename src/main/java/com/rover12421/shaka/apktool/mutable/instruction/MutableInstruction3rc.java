package com.rover12421.shaka.apktool.mutable.instruction;

import com.rover12421.shaka.apktool.mutable.MutableDexFile;
import com.rover12421.shaka.apktool.mutable.reference.MutableReference;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction3rc;
import org.jf.dexlib2.iface.reference.Reference;

import javax.annotation.Nonnull;

/**
 * Created by rover12421 on 9/30/14.
 */
public class MutableInstruction3rc extends DexBackedInstruction3rc {
    @Nonnull public MutableDexFile mutableDexFile;
    @Nonnull public DexBackedInstruction3rc dexBackedInstruction3rc;

    private Reference reference;

    public MutableInstruction3rc(@Nonnull MutableDexFile mutableDexFile, @Nonnull DexBackedInstruction3rc dexBackedInstruction3rc) {
        super(dexBackedInstruction3rc.dexFile, dexBackedInstruction3rc.opcode, dexBackedInstruction3rc.instructionStart);
        this.dexBackedInstruction3rc = dexBackedInstruction3rc;
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
