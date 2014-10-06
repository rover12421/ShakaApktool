package com.rover12421.shaka.apktool.mutable;

import org.jf.dexlib2.dexbacked.DexBackedCatchAllExceptionHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by rover12421 on 9/29/14.
 */
public class MutableCatchAllExceptionHandler extends MutableExceptionHandler {

    @Nonnull private final MutableDexFile dexFile;
    @Nonnull private final DexBackedCatchAllExceptionHandler dexBackedCatchAllExceptionHandler;


    public MutableCatchAllExceptionHandler(@Nonnull MutableDexFile dexFile, @Nonnull DexBackedCatchAllExceptionHandler dexBackedCatchAllExceptionHandler) {
        this.dexFile = dexFile;
        this.dexBackedCatchAllExceptionHandler = dexBackedCatchAllExceptionHandler;
    }

    @Nullable
    @Override
    public String getExceptionType() {
        return null;
    }

    @Override
    public int getHandlerCodeAddress() {
        return dexBackedCatchAllExceptionHandler.getHandlerCodeAddress();
    }
}
