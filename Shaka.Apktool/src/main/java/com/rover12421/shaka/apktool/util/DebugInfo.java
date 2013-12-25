package com.rover12421.shaka.apktool.util;

/**
 * Created by rover12421 on 12/25/13.
 */
public class DebugInfo {
    private static boolean DEBUG = true;

    public static boolean isDEBUG() {
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
