package com.rover12421.shaka.apktool.AspjectJ.smali_dexlib2;

import com.rover12421.shaka.apktool.util.LogHelper;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.dexbacked.DexBackedMethodImplementation;
import org.jf.dexlib2.dexbacked.DexReader;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction10x;
import org.jf.dexlib2.dexbacked.raw.CodeItem;
import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.OffsetInstruction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rover12421 on 4/12/15.
 */
@Aspect
public class DexBackedMethodImplementationAj {
    private int codeOffset(DexBackedMethodImplementation thiz) throws NoSuchFieldException, IllegalAccessException {
        return (int) ReflectUtil.getFieldValue(thiz, "codeOffset");
    }

    @Around("execution(* org.jf.dexlib2.dexbacked.DexBackedMethodImplementation.getInstructions(..))")
    public Iterable<? extends Instruction> getInstructions(ProceedingJoinPoint joinPoint) throws Exception {
        DexBackedMethodImplementation thiz = (DexBackedMethodImplementation) joinPoint.getThis();

        // instructionsSize is the number of 16-bit code units in the instruction list, not the number of instructions
        int instructionsSize = thiz.dexFile.readSmallUint(codeOffset(thiz) + CodeItem.INSTRUCTION_COUNT_OFFSET);

        final int instructionsStartOffset = codeOffset(thiz) + CodeItem.INSTRUCTION_START_OFFSET;
        final int endOffset = instructionsStartOffset + (instructionsSize*2);

        List<Instruction> instructions = new ArrayList<>();
        DexReader reader = thiz.dexFile.readerAt(instructionsStartOffset);
        while (reader.getOffset() < endOffset) {
            Instruction instruction = DexBackedInstruction.readFrom(reader);
            instructions.add(instruction);
        }
        if (instructions.size() == 3
                && instructions.get(0) instanceof OffsetInstruction
                && instructions.get(1).getOpcode() == Opcode.FILL_ARRAY_DATA
                && instructions.get(2).getOpcode() == Opcode.ARRAY_PAYLOAD) {
            /**
             * 检测到dalvik-obfuscator代码快
             * 先重置instructions和reader
             * dalvik-obfuscator用nop替换,再继续读取正常的指令数据
             */
            LogHelper.getLogger().warning("Detected dalvik-obfuscator code block : " + thiz.method.getDefiningClass() + "->" + thiz.method.getName());
            instructions.clear();
            reader = thiz.dexFile.readerAt(instructionsStartOffset);
            /**
             * dalvik-obfuscator最少要9个nop宽度的数据,可多不可少
             */
            for (int i=0; i<9; i++) {
                DexBackedInstruction10x nop = new DexBackedInstruction10x(thiz.dexFile, Opcode.NOP, instructionsStartOffset + i*2);
                instructions.add(nop);
                reader.moveRelative(2);
            }
            while (reader.getOffset() < endOffset) {
                Instruction instruction = DexBackedInstruction.readFrom(reader);
                instructions.add(instruction);
            }
        }
        return instructions;
    }
}
