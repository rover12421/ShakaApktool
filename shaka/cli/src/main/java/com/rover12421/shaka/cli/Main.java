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
package com.rover12421.shaka.cli;

import com.rover12421.shaka.apktool.cli.ApktoolMainAj;
import com.rover12421.shaka.lib.cli.CommandLineArgEnum;
import com.rover12421.shaka.lib.multiLanguage.MultiLanguageSupport;
import com.rover12421.shaka.smali.baksmali.baksmaliMainAj;
import com.rover12421.shaka.smali.smali.smaliMainAj;
import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Locale;

/**
 * Created by rover12421 on 7/11/14.
 */
public class Main {

    public static class IgnoreUnkownArgsPosixParser extends PosixParser {
        @Override
        protected void processOption(String arg, ListIterator iter) throws ParseException
        {
            try {
                super.processOption(arg, iter);
            } catch (ParseException e) {
            }
        }
    }

    public static void main(String[] args) throws Exception {
        boolean smali = false;
        boolean baksmali = false;

        String[] realyArgs = args;

        if (args.length > 0) {
            String cmd = args[0];
            if (cmd.equalsIgnoreCase("s") || cmd.equalsIgnoreCase("smali")) {
                smali = true;
            } else if (cmd.equalsIgnoreCase("bs") || cmd.equalsIgnoreCase("baksmali")) {
                baksmali = true;
            }

            if (smali || baksmali) {
                realyArgs = new String[args.length-1];
                System.arraycopy(args, 1, realyArgs, 0, realyArgs.length);
            }
        }

        // cli parser
        CommandLineParser parser = new IgnoreUnkownArgsPosixParser();
        CommandLine commandLine;

        Option language = CommandLineArgEnum.LANGUAGE.getOption();

        Options options = new Options();
        options.addOption(language);

        try {
            commandLine = parser.parse(options, args, false);
            if (CommandLineArgEnum.LANGUAGE.hasMatch(commandLine)) {
                String lngStr = commandLine.getOptionValue(CommandLineArgEnum.LANGUAGE.getOpt());
                Locale locale = Locale.forLanguageTag(lngStr);
                if (locale.toString().isEmpty()) {
                    lngStr = lngStr.replaceAll("_", "-");
                    locale = Locale.forLanguageTag(lngStr);
                }
                MultiLanguageSupport.getInstance().setLang(locale);
            }
        } catch (Exception ex) {
        }

        if (smali) {
            smaliMainAj.setHookMain(ApktoolMainAj.getHookMain());
            org.jf.smali.main.main(realyArgs);
        } else if (baksmali) {
            baksmaliMainAj.setHookMain(ApktoolMainAj.getHookMain());
            org.jf.baksmali.main.main(realyArgs);
        } else {
            brut.apktool.Main.main(realyArgs);
        }
    }
}
