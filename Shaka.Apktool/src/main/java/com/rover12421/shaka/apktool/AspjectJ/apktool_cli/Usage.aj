package com.rover12421.shaka.apktool.AspjectJ.apktool_cli;

import com.rover12421.shaka.apktool.lib.ShakaProperties;
import com.rover12421.shaka.apktool.util.DebugInfo;
import org.apache.commons.cli.CommandLine;

/**
 * Created by rover12421 on 12/25/13.
 */
public aspect Usage {

    pointcut usagePointcut(CommandLine commandLine) : execution(void brut.apktool.Main.usage(CommandLine)) && args(commandLine);

    void around(CommandLine commandLine) : usagePointcut(commandLine) && !within(Usage +) {
        System.out.println("ShakaApktool " + ShakaProperties.getVersion() + " >>>");
        proceed(commandLine);
    }

    after(CommandLine commandLine) : usagePointcut(commandLine) {
        DebugInfo.info("-------------------- AspectJ : Usage -----------");
        DebugInfo.info("Signature : " + thisJoinPoint.getStaticPart().getSignature());
        DebugInfo.info("------------------------------------------------");
        System.out.println("For ShakaApktool info, see: http://www.rover12421.com/");
    }
}

