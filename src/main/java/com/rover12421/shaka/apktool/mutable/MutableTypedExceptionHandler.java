package com.rover12421.shaka.apktool.mutable;

import org.jf.dexlib2.dexbacked.DexBackedTypedExceptionHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by rover12421 on 9/29/14.
 */
public class MutableTypedExceptionHandler extends MutableExceptionHandler {

    @Nonnull private final MutableDexFile dexFile;
    @Nonnull private final DexBackedTypedExceptionHandler dexBackedTypedExceptionHandler;

    private String exceptionType;

    public MutableTypedExceptionHandler(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedTypedExceptionHandler dexBackedTypedExceptionHandler) {
        this.dexFile = dexFile;
        this.dexBackedTypedExceptionHandler = dexBackedTypedExceptionHandler;

        exceptionType = dexBackedTypedExceptionHandler.getExceptionType();
    }

    @Nullable
    @Override
    public String getExceptionType() {
        return exceptionType;
    }

    @Override
    public int getHandlerCodeAddress() {
        return dexBackedTypedExceptionHandler.getHandlerCodeAddress();
    }
}
