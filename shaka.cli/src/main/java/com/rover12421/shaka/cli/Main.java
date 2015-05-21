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
        ArrayList<String> list = new ArrayList<>();
        boolean smali = false;
        boolean baksmali = false;

        for (String arg : args) {
            if (arg.equalsIgnoreCase("s") || arg.equalsIgnoreCase("smali")) {
                smali = true;
            } else if (arg.equalsIgnoreCase("bs") || arg.equalsIgnoreCase("baksmali")) {
                baksmali = true;
            } else {
                list.add(arg);
            }
        }

        // cli parser
        CommandLineParser parser = new IgnoreUnkownArgsPosixParser();
        CommandLine commandLine;

        Option language = OptionBuilder.withLongOpt("language")
            .withDescription("Display language, e.g. zh-CN, zh-TW")
            .hasArg(true)
            .withArgName("Locale")
            .create("lng");

        Options options = new Options();
        options.addOption(language);

        try {
            commandLine = parser.parse(options, args, false);
            if (commandLine.hasOption("lng") || commandLine.hasOption("language")) {
                String lngStr = commandLine.getOptionValue("lng");
                Locale locale = Locale.forLanguageTag(lngStr);
                if (locale.toString().isEmpty()) {
                    lngStr = lngStr.replaceAll("_", "-");
                    locale = Locale.forLanguageTag(lngStr);
                }
                MultiLanguageSupport.getInstance().setLang(locale);
            }
        } catch (Exception ex) {
        }

        String[] newArgs = new String[list.size()];
        newArgs = list.toArray(newArgs);

        if (smali) {
            smaliMainAj.setHookMain(ApktoolMainAj.getHookMain());
            org.jf.smali.main.main(newArgs);
        } else if (baksmali) {
            baksmaliMainAj.setHookMain(ApktoolMainAj.getHookMain());
            org.jf.baksmali.main.main(newArgs);
        } else {
            brut.apktool.Main.main(args);
        }
    }
}
