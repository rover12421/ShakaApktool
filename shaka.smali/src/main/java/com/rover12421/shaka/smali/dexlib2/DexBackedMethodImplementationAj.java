/**
 *  Copyright 2015 Rover12421 <rover12421@163.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.rover12421.shaka.smali.dexlib2;

import com.rover12421.shaka.lib.LogHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.dexbacked.DexBackedMethodImplementation;
import org.jf.dexlib2.dexbacked.DexReader;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction;
import org.jf.dexlib2.dexbacked.instruction.DexBackedInstruction10x;
import org.jf.dexlib2.dexbacked.raw.CodeItem;
import org.jf.dexlib2.dexbacked.util.DebugInfo;
import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.OffsetInstruction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rover12421 on 4/12/15.
 */
@Aspect
public class DexBackedMethodImplementationAj {

    @Around("execution(* org.jf.dexlib2.dexbacked.DexBackedMethodImplementation.getInstructions(..))")
    public Iterable<? extends Instruction> getInstructions(ProceedingJoinPoint joinPoint) throws Exception {
        DexBackedMethodImplementation thiz = (DexBackedMethodImplementation) joinPoint.getThis();

        int codeOffset = thiz.getCodeOffset();

        // instructionsSize is the number of 16-bit code units in the instruction list, not the number of instructions
        int instructionsSize = thiz.dexFile.readSmallUint(codeOffset + CodeItem.INSTRUCTION_COUNT_OFFSET);

        final int instructionsStartOffset = codeOffset + CodeItem.INSTRUCTION_START_OFFSET;
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
            LogHelper.warning("Detected dalvik-obfuscator code block : " + thiz.method.getDefiningClass() + "->" + thiz.method.getName());
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

    @Around("execution(* org.jf.dexlib2.dexbacked.DexBackedMethodImplementation.getDebugInfo())")
    public DebugInfo getDebugInfo(ProceedingJoinPoint joinPoint) {
        DexBackedMethodImplementation thiz = (DexBackedMethodImplementation) joinPoint.getThis();
        DexBackedDexFile dexFile = thiz.dexFile;
        int codeOffset = thiz.getCodeOffset();

        int debugOffset = dexFile.readInt(codeOffset + CodeItem.DEBUG_INFO_OFFSET);
        try {
            return DebugInfo.newOrEmpty(dexFile, debugOffset, thiz);
        } catch (Throwable e) {
            return DebugInfo.newOrEmpty(dexFile, 0, thiz);
        }
    }
}
