package com.rover12421.shaka.apktool.mutable;

import org.jf.dexlib2.base.BaseTryBlock;
import org.jf.dexlib2.dexbacked.DexBackedCatchAllExceptionHandler;
import org.jf.dexlib2.dexbacked.DexBackedExceptionHandler;
import org.jf.dexlib2.dexbacked.DexBackedTryBlock;
import org.jf.dexlib2.dexbacked.DexBackedTypedExceptionHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rover12421 on 9/29/14.
 */
public class MutableTryBlock extends BaseTryBlock<MutableExceptionHandler>  {
    @Nonnull public final MutableDexFile dexFile;
    @Nonnull public final DexBackedTryBlock dexBackedTryBlock;

    List<MutableExceptionHandler> exceptionHandlers;

    public MutableTryBlock(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedTryBlock dexBackedTryBlock) {
        this.dexFile = dexFile;
        this.dexBackedTryBlock = dexBackedTryBlock;
    }

    @Override
    public int getStartCodeAddress() {
        return dexBackedTryBlock.getStartCodeAddress();
    }

    @Override
    public int getCodeUnitCount() {
        return dexBackedTryBlock.getCodeUnitCount();
    }

    @Nonnull
    @Override
    public List<? extends MutableExceptionHandler> getExceptionHandlers() {
        if (exceptionHandlers == null) {
            List<DexBackedExceptionHandler> dexBackedExceptionHandlers = (List<DexBackedExceptionHandler>) dexBackedTryBlock.getExceptionHandlers();
            exceptionHandlers = new ArrayList<>();
            for (DexBackedExceptionHandler dexBackedExceptionHandler : dexBackedExceptionHandlers) {
                if (dexBackedExceptionHandler instanceof DexBackedTypedExceptionHandler) {
                    exceptionHandlers.add(new MutableTypedExceptionHandler(dexFile, (DexBackedTypedExceptionHandler)dexBackedExceptionHandler));
                } else if (dexBackedExceptionHandler instanceof DexBackedCatchAllExceptionHandler) {
                    exceptionHandlers.add(new MutableCatchAllExceptionHandler(dexFile, (DexBackedCatchAllExceptionHandler)dexBackedExceptionHandler));
                } else {
                    throw new RuntimeException("MutableTryBlock getExceptionHandlers Unkown Type : " + dexBackedExceptionHandler.getClass());
                }
            }
        }

        return exceptionHandlers;
    }
}
