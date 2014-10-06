package com.rover12421.shaka.apktool.mutable;

import org.jf.dexlib2.dexbacked.DexBackedClassDef;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.iface.DexFile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by rover12421 on 9/29/14.
 */
public class MutableDexFile implements DexFile {

    private DexBackedDexFile dexBackedDexFile;

    private Map<Integer, String> stringPool = new HashMap<>();
    private Map<Integer, Integer> typeStringMapTables = new HashMap<>();

    public MutableDexFile(DexBackedDexFile dexBackedDexFile) {
        this.dexBackedDexFile = dexBackedDexFile;
    }

    public int getStringIdItemOffset(int stringIndex) {
        return dexBackedDexFile.getStringIdItemOffset(stringIndex);
    }

    public int getTypeIdItemOffset(int typeIndex) {
        return dexBackedDexFile.getTypeIdItemOffset(typeIndex);
    }

    public int getFieldIdItemOffset(int fieldIndex) {
        return dexBackedDexFile.getFieldIdItemOffset(fieldIndex);
    }

    public int getMethodIdItemOffset(int methodIndex) {
        return dexBackedDexFile.getMethodIdItemOffset(methodIndex);
    }

    public int getProtoIdItemOffset(int protoIndex) {
        return dexBackedDexFile.getProtoIdItemOffset(protoIndex);
    }

    public int getClassDefItemOffset(int classIndex) {
        return dexBackedDexFile.getClassDefItemOffset(classIndex);
    }

    public int readSmallUint(int offset) {
        return dexBackedDexFile.readSmallUint(offset);
    }

    public int readOptionalUint(int offset) {
        return dexBackedDexFile.readOptionalUint(offset);
    }

    public int readUshort(int offset) {
        return dexBackedDexFile.readUshort(offset);
    }

    @Nonnull
    @Override
    public Set<? extends MutableClassDef> getClasses() {
        Set clazzs = new HashSet<MutableClassDef>();
        Set<DexBackedClassDef> dexBackedClassDefs = (Set<DexBackedClassDef>) dexBackedDexFile.getClasses();
        for (DexBackedClassDef dexBackedClassDef : dexBackedClassDefs) {
            clazzs.add(new MutableClassDef(this, dexBackedClassDef));
        }

        return clazzs;
    }

    @Nonnull
    public String getString(int stringIndex) {
        String str = stringPool.get(stringIndex);
        if (str == null) {
            str = dexBackedDexFile.getString(stringIndex);
            stringPool.put(stringIndex, str);
        }

        return str;
    }

    @Nullable
    public String getOptionalString(int stringIndex) {
        if (stringIndex == -1) {
            return null;
        }
        return getString(stringIndex);
    }

    @Nonnull
    public String getType(int typeIndex) {
        Integer stringIndex = typeStringMapTables.get(typeIndex);
        if (stringIndex == null) {
            int typeOffset = getTypeIdItemOffset(typeIndex);
            stringIndex = readSmallUint(typeOffset);
            typeStringMapTables.put(typeIndex, stringIndex);
        }
        return getString(stringIndex);
    }

    @Nullable
    public String getOptionalType(int typeIndex) {
        if (typeIndex == -1) {
            return null;
        }
        return getType(typeIndex);
    }

    public DexBackedDexFile getDexBackedDexFile() {
        return dexBackedDexFile;
    }
}
