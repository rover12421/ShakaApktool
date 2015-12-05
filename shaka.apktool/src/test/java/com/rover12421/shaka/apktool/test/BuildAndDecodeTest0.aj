package com.rover12421.shaka.apktool.test;

import brut.androlib.BuildAndDecodeTest;
import brut.androlib.res.util.ExtFile;

/**
 * Created by rover12421 on 12/5/15.
 */
privileged public aspect BuildAndDecodeTest0 {
    public static ExtFile BuildAndDecodeTest.getTestOrigDir() {
        return BuildAndDecodeTest.sTestOrigDir;
    }

    public static ExtFile BuildAndDecodeTest.getTestNewDir() {
        return BuildAndDecodeTest.sTestNewDir;
    }
}
