package com.rover12421.shaka.apktool.cli;

import brut.apktool.Main;
import org.apache.commons.cli.Options;

/**
 * Created by rover12421 on 12/5/15.
 */
privileged public aspect ApktoolMain {
    public static Options Main.getNormalOptions() {
        return Main.normalOptions;
    }

    public static Options Main.getDecodeOptions() {
        return Main.DecodeOptions;
    }

    public static Options Main.getBuildOptions() {
        return Main.BuildOptions;
    }

    public static Options Main.getFrameOptions() {
        return Main.frameOptions;
    }

    public static Options Main.getAllOptions() {
        return Main.allOptions;
    }

    public static Options Main.getEmptyOptions() {
        return Main.emptyOptions;
    }

    public static void Main._Options0() {
        Main._Options();
    }

    public static String Main.verbosityHelp0() {
        return Main.verbosityHelp();
    }
}
