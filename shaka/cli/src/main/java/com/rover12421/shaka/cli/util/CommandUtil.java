/*
 * Copyright 2017 Rover12421 <rover12421@163.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rover12421.shaka.cli.util;

import org.jf.util.jcommander.Command;

import java.util.List;

/**
 * Created by rover12421 on 6/6/17.
 */
public class CommandUtil {
    public static final int ExceptionExitCode = 1;

    public static String getInput(Command command, List<String> inputList) {
        if (inputList == null || inputList.isEmpty()) {
            exceptionExit(command, "");
        }

        if (inputList.size() > 1) {
            exceptionExit(command, "Too many files specified");
        }

        return inputList.get(0);
    }

    public static void exceptionExit(Command command, String msg) {
        System.err.println(msg);
        command.usage();
        System.exit(ExceptionExitCode);
    }

    public static void exceptionExit(Throwable throwable) {
        System.err.println(throwable.getMessage());
        System.exit(ExceptionExitCode);
    }

    public static void exceptionExit(String msg) {
        System.err.println(msg);
        System.exit(ExceptionExitCode);
    }
}
