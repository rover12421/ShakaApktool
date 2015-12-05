package com.rover12421.shaka.apktool.lib;

import brut.androlib.Androlib;
import brut.androlib.res.data.ResUnknownFiles;

/**
 * Created by rover12421 on 12/5/15.
 */
privileged public aspect Androlib0 {
    public ResUnknownFiles Androlib.getResUnknownFiles() {
        return this.mResUnknownFiles;
    }
}
