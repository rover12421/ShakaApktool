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

package com.rover12421.shaka.cli.apktool;

import brut.androlib.Androlib;
import brut.androlib.AndrolibException;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.rover12421.shaka.cli.base.ApktoolUsingFrameworkCommand;
import com.rover12421.shaka.cli.util.CommandUtil;
import org.jf.util.jcommander.ExtendedParameters;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by rover12421 on 6/6/17.
 */
@Parameters(commandDescription = "empty framework dir")
@ExtendedParameters(
        commandName = "empty-framework-dir",
        commandAliases = { "ef" })
public class EmptyFrameworkDirCommand extends ApktoolUsingFrameworkCommand {
    public EmptyFrameworkDirCommand(@Nonnull List<JCommander> commandAncestors) {
        super(commandAncestors);
    }

    @Parameter(names = {"-f", "--force"},
            description = "Force delete destination directory.")
    private boolean force = false;

    @Override
    public void run() {
        super.run();

        apkOptions.forceDeleteFramework = force;
        try {
            new Androlib(apkOptions).emptyFrameworkDirectory();
        } catch (AndrolibException e) {
            CommandUtil.exceptionExit(e);
        }
    }
}
