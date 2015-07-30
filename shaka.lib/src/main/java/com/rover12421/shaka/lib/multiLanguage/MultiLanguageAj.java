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

package com.rover12421.shaka.lib.multiLanguage;

import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by rover12421 on 2/9/15.
 */
@Aspect
public class MultiLanguageAj {

    private final MultiLanguageSupport mts = MultiLanguageSupport.getInstance();

    @Around("call(void java.io.PrintStream.println(..))" +
            "&& args(x)" +
            "&& !within(com.rover12421.shaka.lib.multiLanguage.* +)" +
            "&& !within(com.rover12421.shaka.apktool.test.*)")
    public void around_sout_println(ProceedingJoinPoint joinPoint, String x) throws Throwable {
        joinPoint.proceed(new Object[]{MultiLanguageSupport.covertLocaleInfo(x)});
    }

    @Around("call(* org.apache.commons.cli.OptionBuilder.withDescription(..))" +
            "&& args(newDescription)" +
            "&& !within(com.rover12421.shaka.lib.multiLanguage.* +)")
    public OptionBuilder around_OptionBuilder_withDescription(ProceedingJoinPoint joinPoint, String newDescription) throws Throwable {
        return (OptionBuilder) joinPoint.proceed(new Object[]{MultiLanguageSupport.covertLocaleInfo(newDescription)});
    }

    @Around("call(void org.apache.commons.cli.HelpFormatter.printHelp(..))" +
            "&& args(cmdLineSyntax, header, options, footer)" +
            "&& !within(com.rover12421.shaka.lib.multiLanguage.* +)")
    public void around_HelpFormatter_printHelp(ProceedingJoinPoint joinPoint,
                                                        String cmdLineSyntax, String header, Options options, String footer) throws Throwable {
        joinPoint.proceed(new Object[]{MultiLanguageSupport.covertLocaleInfo(cmdLineSyntax), MultiLanguageSupport.covertLocaleInfo(header), options, footer});
    }

    @Around("call(* java.lang.String.format(..))" +
            "&& args(format, args)" +
            "&& !within(com.rover12421.shaka.lib.multiLanguage.* +)" +
            "&& !within(org.jf.dexlib2.util.* +)")
    public String around_String_format(ProceedingJoinPoint joinPoint,
                                     String format, Object... args) throws Throwable {
        return MultiLanguageSupport.covertLocaleInfo((String) joinPoint.proceed(joinPoint.getArgs()));
    }


    @Around("call(void java.util.logging.Logger.*(String))" +
            "&& args(msg)" +
            "&& !within(com.rover12421.shaka.lib.multiLanguage.* +)")
    public void around_Logger_msg(ProceedingJoinPoint joinPoint,
                                             String msg) throws Throwable {
        joinPoint.proceed(new Object[]{MultiLanguageSupport.covertLocaleInfo(msg)});
    }
}



