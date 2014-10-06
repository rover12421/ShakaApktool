package com.rover12421.shaka.apktool.mutable;

import com.rover12421.shaka.apktool.mutable.instruction.*;
import com.rover12421.shaka.apktool.mutable.reference.*;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import org.jf.dexlib2.ReferenceType;
import org.jf.dexlib2.dexbacked.DexBackedMethodImplementation;
import org.jf.dexlib2.dexbacked.DexBackedTryBlock;
import org.jf.dexlib2.dexbacked.DexReader;
import org.jf.dexlib2.dexbacked.instruction.*;
import org.jf.dexlib2.dexbacked.reference.DexBackedFieldReference;
import org.jf.dexlib2.dexbacked.reference.DexBackedMethodReference;
import org.jf.dexlib2.dexbacked.reference.DexBackedStringReference;
import org.jf.dexlib2.dexbacked.reference.DexBackedTypeReference;
import org.jf.dexlib2.iface.MethodImplementation;
import org.jf.dexlib2.iface.debug.DebugItem;
import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.ReferenceInstruction;
import org.jf.dexlib2.iface.reference.*;
import org.jf.util.ExceptionWithContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rover12421 on 9/29/14.
 */
public class MutableMethodImplementation implements MethodImplementation {
    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final MutableMethod method;
    @Nonnull public final DexBackedMethodImplementation dexBackedMethodImplementation;

    private int registerCount;
    private List<MutableTryBlock> tryBlocks;
    private List<Instruction> instructions;
    private List<Reference> instructionsReferences;

    public MutableMethodImplementation(@Nonnull MutableDexFile dexFile, @Nonnull MutableMethod method, @Nonnull DexBackedMethodImplementation dexBackedMethodImplementation) {
        this.dexFile = dexFile;
        this.method = method;
        this.dexBackedMethodImplementation = dexBackedMethodImplementation;

        registerCount = dexBackedMethodImplementation.getRegisterCount();
    }

    @Override
    public int getRegisterCount() {
        return registerCount;
    }

    @Nonnull
    @Override
    public Iterable<? extends Instruction> getInstructions() {
        if (instructions == null) {
            instructions = new ArrayList<>();
            instructionsReferences = new ArrayList<>();
            for (Instruction instruction: dexBackedMethodImplementation.getInstructions()) {
                if (instruction instanceof ReferenceInstruction) {
                    ReferenceInstruction referenceInstruction = (ReferenceInstruction) instruction;
                    Reference reference = referenceInstruction.getReference();
                    Reference newReference = null;
                    switch (instruction.getOpcode().referenceType) {
                        case ReferenceType.STRING:
                            newReference = new MutableStringReference(dexFile, (DexBackedStringReference) reference);
                            break;
                        case ReferenceType.TYPE:
                            newReference = new MutableTypeReference(dexFile, (DexBackedTypeReference) reference);
                            break;
                        case ReferenceType.FIELD:
                            newReference = new MutableFieldReference(dexFile, (DexBackedFieldReference) reference);
                            break;
                        case ReferenceType.METHOD:
                            newReference = new MutableMethodReference(dexFile, (DexBackedMethodReference) reference);
                            break;
                        default:
                            throw new ExceptionWithContext("Unrecognized reference type: %d",
                                    instruction.getOpcode().referenceType);
                    }

                    if (instruction instanceof DexBackedInstruction20bc) {
                        instruction = new MutableInstruction20bc(dexFile, (DexBackedInstruction20bc) instruction);
                    } else if (instruction instanceof DexBackedInstruction21c) {
                        instruction = new MutableInstruction21c(dexFile, (DexBackedInstruction21c) instruction);
                    } else if (instruction instanceof DexBackedInstruction22c) {
                        instruction = new MutableInstruction22c(dexFile, (DexBackedInstruction22c) instruction);
                    } else if (instruction instanceof DexBackedInstruction31c) {
                        instruction = new MutableInstruction31c(dexFile, (DexBackedInstruction31c) instruction);
                    } else if (instruction instanceof DexBackedInstruction35c) {
                        instruction = new MutableInstruction35c(dexFile, (DexBackedInstruction35c) instruction);
                    } else if (instruction instanceof DexBackedInstruction3rc) {
                        instruction = new MutableInstruction3rc(dexFile, (DexBackedInstruction3rc) instruction);
                    }

                    if (newReference != null) {
                        instructionsReferences.add(newReference);
                        try {
                            ReflectUtil.setFieldValue(instruction, "reference", newReference);
                        } catch (NoSuchFieldException e) {
                            System.out.println("instruction clazz : " + instruction.getClass());
//                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

                instructions.add(instruction);
            }
        }

        return instructions;
    }

    @Nonnull
    @Override
    public List<? extends MutableTryBlock> getTryBlocks() {
        if (tryBlocks == null) {
            List<DexBackedTryBlock> dexBackedTryBlocks = (List<DexBackedTryBlock>) dexBackedMethodImplementation.getTryBlocks();
            tryBlocks = new ArrayList<>();
            for (DexBackedTryBlock dexBackedTryBlock : dexBackedTryBlocks) {
                tryBlocks.add(new MutableTryBlock(dexFile, dexBackedTryBlock));
            }
        }

        return tryBlocks;
    }

    @Nonnull
    @Override
    public Iterable<? extends DebugItem> getDebugItems() {
        return dexBackedMethodImplementation.getDebugItems();
    }

    @Nonnull
    public Iterator<String> getParameterNames() {
        return dexBackedMethodImplementation.getParameterNames(null);
    }
}
