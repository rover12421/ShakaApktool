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
package com.rover12421.shaka.smali.smali;

import com.rover12421.shaka.lib.HookMain;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by rover12421 on 5/17/15.
 */
@Aspect
public class smaliMainAj {
    private static HookMain hookMain;

    public static void setHookMain(HookMain hookMain) {
        smaliMainAj.hookMain = hookMain;
    }

    @Around("execution(* org.jf.smali.main.usage(..))" +
            "&& args(printDebugOptions)")
    public void usage(boolean printDebugOptions) {
        hookMain.usage();
    }

    @Around("execution(* org.jf.smali.main.version())")
    public void version() {
        hookMain.version();
    }
}
