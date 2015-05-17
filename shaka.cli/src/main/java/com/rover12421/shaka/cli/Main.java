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

import java.util.ArrayList;

/**
 * Created by rover12421 on 7/11/14.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        boolean smali = false;
        boolean baksmali = false;
        String[] newArgs = new String[list.size()];
        newArgs = list.toArray(newArgs);
        for (String arg : args) {
            if (arg.equalsIgnoreCase("s") || arg.equalsIgnoreCase("smali")) {
                smali = true;
            } else if (arg.equalsIgnoreCase("bs") || arg.equalsIgnoreCase("baksmali")) {
                baksmali = true;
            } else {
                list.add(arg);
            }
        }
        if (smali) {
            org.jf.smali.main.main(newArgs);
        } else if (baksmali) {
            org.jf.baksmali.main.main(newArgs);
        } else {
            brut.apktool.Main.main(args);
        }
    }
}
