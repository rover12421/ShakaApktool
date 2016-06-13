package com.rover12421.shaka.apktool.meta;

import brut.androlib.meta.MetaInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rover12421 on 1/21/16.
 */
public aspect MetaInfo0 {
    public Map<String, String> MetaInfo.decodeFileMaps;
    public Map<String, String> MetaInfo.dexMaps;
    public String MetaInfo.shakaVer;

    public String MetaInfo.addDexMap(String key, String val) {
        if (dexMaps == null) {
            dexMaps = new HashMap();
        }
        return dexMaps.put(key, val);
    }

    public String MetaInfo.getDexMap(String key) {
        if (dexMaps == null) {
            return null;
        }

        return dexMaps.get(key);
    }

    public String MetaInfo.addDecodeFileMap(String key, String val) {
        if (decodeFileMaps == null) {
            decodeFileMaps = new HashMap<>();
        }

        return decodeFileMaps.put(key, val);
    }

    public String MetaInfo.getDecodeFileMap(String key) {
        if (decodeFileMaps == null) {
            return null;
        }

        return decodeFileMaps.get(key);
    }
}
