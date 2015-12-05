package com.rover12421.shaka.smali.baksmali;

import org.apache.commons.cli.Options;

/**
 * Created by rover12421 on 12/5/15.
 */
privileged public aspect baksmaliMain {
    public static Options org.jf.baksmali.main.getBasicOptions(){
        return org.jf.baksmali.main.basicOptions;
    }

    public static Options org.jf.baksmali.main.getDebugOptions(){
        return org.jf.baksmali.main.debugOptions;
    }

    public static Options org.jf.baksmali.main.getOptions(){
        return org.jf.baksmali.main.options;
    }

}
