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
package com.rover12421.shaka.smali.baksmali;

import com.rover12421.shaka.lib.HookMain;
import com.rover12421.shaka.lib.LogHelper;
import com.rover12421.shaka.lib.cli.CommandLineArgEnum;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.PosixParser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

/**
 * Created by rover12421 on 5/17/15.
 */
@Aspect
public class baksmaliMainAj {
    private static HookMain hookMain;

    public static void setHookMain(HookMain hookMain) {
        baksmaliMainAj.hookMain = hookMain;
    }

    @Around("execution(* org.jf.baksmali.main.usage(..))" +
            "&& args(printDebugOptions)")
    public void usage(boolean printDebugOptions) {
        hookMain.usage();
    }

    @Around("execution(* org.jf.baksmali.main.version())")
    public void version() {
        hookMain.version();
    }

    @After("execution(* org.jf.baksmali.main.buildOptions(..))")
    public void buildOptions() {
        org.jf.baksmali.main.getBasicOptions().addOption(CommandLineArgEnum.InlieMethodResolverFromFile.getOption());
        org.jf.baksmali.main.getOptions().addOption(CommandLineArgEnum.InlieMethodResolverFromFile.getOption());
    }

    @Around("execution(* org.jf.baksmali.main.main(..))" +
            "&& args(args)")
    public void main(ProceedingJoinPoint joinPoint, String[] args) throws Throwable {
        try {
            CommandLineParser parser = new PosixParser();
            CommandLine cli = parser.parse(org.jf.baksmali.main.getOptions(), args);
            if (CommandLineArgEnum.InlieMethodResolverFromFile.hasMatch(cli)) {
                ShakaBaksmaliOption.setInlineMethodResolverFile(cli.getOptionValue(CommandLineArgEnum.InlieMethodResolverFromFile.getOpt()));
                int index = Arrays.binarySearch(args, CommandLineArgEnum.InlieMethodResolverFromFile.getOpt());
                if (index < 0) {
                    index = Arrays.binarySearch(args, CommandLineArgEnum.InlieMethodResolverFromFile.getLongOpt());
                }

                if (index >= 0) {
                    String[] nArgs = new String[args.length - 2];
                    if (index > 0) {
                        System.arraycopy(args, 0, nArgs, 0, index);
                    }
                    System.arraycopy(args, index + 2, nArgs, index, nArgs.length - index);
                    joinPoint.proceed(nArgs);
                } else {
                    LogHelper.warning("args error!!!");
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        joinPoint.proceed(joinPoint.getArgs());
    }
}
