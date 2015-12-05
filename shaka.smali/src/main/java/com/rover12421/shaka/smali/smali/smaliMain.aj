package com.rover12421.shaka.smali.smali;

import org.apache.commons.cli.Options;

/**
 * Created by rover12421 on 12/6/15.
 */
privileged public aspect smaliMain {
    public static Options org.jf.smali.main.getBasicOptions() {
        return org.jf.smali.main.basicOptions;
    }

    public static Options org.jf.smali.main.getDebugOptions() {
        return org.jf.smali.main.debugOptions;
    }

    public static Options org.jf.smali.main.getOptions() {
        return org.jf.smali.main.options;
    }

}
