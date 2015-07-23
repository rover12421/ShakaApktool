package com.rover12421.shaka.lib;

/**
 * Created by rover12421 on 7/22/15.
 */
public class EnvironmentDetection {
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String JVM_ARCH = System.getProperty("sun.arch.data.model").toLowerCase();

    private static String LIBEXT;
    private static String LIBPATHNAME;
    private static String ARCH;
    private static String OSNAME;

    static {
        if (isRunning64Bit()) {
            ARCH = "x86_64";
            LIBPATHNAME = "lib64";
        } else if (isRunning32Bit()) {
            ARCH = "x86";
            LIBPATHNAME = "lib";
        }

        if (isWindows()) {
            LIBEXT = "dll";
            OSNAME = "windows";
            
            //windows 暂时还没有64bit版本
            ARCH = "x86";
        } else if (isUnix()) {
            LIBEXT = "so";
            OSNAME = "linux";
        } else if (isMacOSX()) {
            LIBEXT = "dylib";
            OSNAME = "darwin";
        }
    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMacOSX() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 || (OS.indexOf("sunos") >= 0));
    }

    public static boolean isRunning64Bit() {
        return JVM_ARCH.equals("64");
    }

    public static boolean isRunning32Bit() {
        return JVM_ARCH.equals("32");
    }

    public static String getOS() {
        return OS;
    }

    public static String getJvmArch() {
        return JVM_ARCH;
    }

    public static String getLIBEXT() {
        return LIBEXT;
    }

    public static String getLIBPATHNAME() {
        return LIBPATHNAME;
    }

    public static String getARCH() {
        return ARCH;
    }

    public static String getOSNAME() {
        return OSNAME;
    }
}
