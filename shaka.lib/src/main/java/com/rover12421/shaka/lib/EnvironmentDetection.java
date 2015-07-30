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
package com.rover12421.shaka.lib;

/**
 * Created by rover12421 on 7/22/15.
 */
public class EnvironmentDetection {
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final String JVM_ARCH = System.getProperty("sun.arch.data.model").toLowerCase();

    private static String LIBEXT;
    private static String BINEXT = "";
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
            LIBEXT = ".dll";
            BINEXT = ".exe";
            OSNAME = "windows";

            //windows 暂时还没有64bit版本
            ARCH = "x86";
        } else if (isUnix()) {
            LIBEXT = ".so";
            OSNAME = "linux";
        } else if (isMacOSX()) {
            LIBEXT = ".dylib";
            OSNAME = "darwin";
        }
    }

    public static boolean isWindows() {
        return OS.contains("win");
    }

    public static boolean isMacOSX() {
        return OS.contains("mac");
    }

    public static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0 || (OS.contains("sunos")));
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

    public static String getBINEXT() {
        return BINEXT;
    }
}
