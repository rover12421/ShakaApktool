package com.rover12421.shaka.apktool.AspjectJ.apktool_cli;

import com.rover12421.shaka.apktool.lib.ShakaProperties;

/**
 * Created by rover12421 on 12/29/13.
 */
public aspect Version {
    pointcut versionPointcut() : execution(void brut.apktool.Main._version());

    void around() : versionPointcut() && !within(Version +) {
        System.out.println(ShakaProperties.getVersion());
    }
}
