package com.rover12421.shaka.apktool.util;

import com.rover12421.shaka.apktool.lib.ShakaProperties;

/**
 * Created by rover12421 on 12/25/13.
 */
public class DebugInfo {
    private static Boolean DEBUG = null;

    public static boolean isDEBUG() {
        if (DEBUG == null) {
            DEBUG = ShakaProperties.isDebug();
        }
        return DEBUG;
    }

    public static void info(String info) {
        if (isDEBUG()) {
            System.out.println(info);
        }
    }

    public static void error(String info) {
        if (isDEBUG()) {
            System.err.println(info);
        }
    }
}
