package com.rover12421.shaka.smali.baksmali;

import com.beust.jcommander.JCommander;
import org.jf.baksmali.Main;

/**
 * Created by rover12421 on 12/5/15.
 */
privileged public aspect baksmaliMain {
    public JCommander Main.getJcommander() {
        return this.jc;
    }

    public static Main Main.getInstance() {
        return baksmaliMainAj.Instance;
    }
}
