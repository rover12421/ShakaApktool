package com.rover12421.shaka.smali.baksmali;

import com.google.common.collect.ImmutableList;
import com.rover12421.shaka.lib.LogHelper;
import org.jf.dexlib2.analysis.AnalyzedInstruction;
import org.jf.dexlib2.analysis.InlineMethodResolver;
import org.jf.dexlib2.iface.Method;
import org.jf.dexlib2.iface.instruction.InlineIndexInstruction;
import org.jf.dexlib2.immutable.ImmutableMethod;
import org.jf.dexlib2.immutable.ImmutableMethodParameter;
import org.jf.dexlib2.immutable.util.ParamUtil;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rover12421 on 9/12/15.
 * 从文件读取InlineMethod的配置信息
 */
public class InlineMethodResolverFromFile extends InlineMethodResolver {
    private List<Method> inlineMethods;

    @Nonnull
    private static Method inlineMethod(int accessFlags, @Nonnull String cls, @Nonnull String name,
                                       @Nonnull String params, @Nonnull String returnType) {
        ImmutableList<ImmutableMethodParameter> paramList = ImmutableList.copyOf(ParamUtil.parseParamString(params));
        return new ImmutableMethod(cls, name, paramList, returnType, accessFlags, null, null);
    }

    public InlineMethodResolverFromFile(String file) {
        inlineMethods = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
            int lineNum = 0;
            for (String line : lines) {
                lineNum++;
                line = line.trim();
                if (!line.startsWith("#")) {
                    String[] info = line.split(",");
                    if (info.length != 5) {
                        LogHelper.warning("Error line (" + lineNum + "): " + line);
                        continue;
                    }

                    LogHelper.info("Add InlineMethodResolver : " + Arrays.toString(info));

//                    public static final int STATIC = 0x8; // AccessFlags.STATIC.value;
//                    public static final int VIRTUAL = 0x1; // AccessFlags.PUBLIC.value;
//                    public static final int DIRECT = 0x2; // AccessFlags.PRIVATE.value;
                    int accessFlags;
                    String accessFlagsStr = info[0].trim().toUpperCase();
                    if (accessFlagsStr.equals("STATIC")) {
                        accessFlags = InlineMethodResolver.STATIC;
                    } else if (accessFlagsStr.equals("VIRTUAL")) {
                        accessFlags = InlineMethodResolver.VIRTUAL;
                    } else if (accessFlagsStr.equals("DIRECT")) {
                        accessFlags = InlineMethodResolver.DIRECT;
                    } else {
                        LogHelper.warning("Error access flags : " + info[0]);
                        continue;
                    }

                    String cls          = info[1].trim();
                    String name         = info[2].trim();
                    String params       = info[3].trim();
                    String returnType   = info[4].trim();

                    Method method = inlineMethod(accessFlags, cls, name, params, returnType);
                    inlineMethods.add(method);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Nonnull
    public Method resolveExecuteInline(@Nonnull AnalyzedInstruction analyzedInstruction) {
        InlineIndexInstruction instruction = (InlineIndexInstruction) analyzedInstruction.getInstruction();
        int inlineIndex = instruction.getInlineIndex();

        if (inlineIndex < 0 || inlineIndex >= inlineMethods.size()) {
            throw new RuntimeException("Invalid inline index: " + inlineIndex);
        }
        return inlineMethods.get(inlineIndex);
    }
}
