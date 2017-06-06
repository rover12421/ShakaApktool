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

package com.rover12421.shaka.cli.base;

import brut.androlib.ApkOptions;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by rover12421 on 6/6/17.
 */
public abstract class ApktoolDefaultCommand extends HelpCommand {
    public ApktoolDefaultCommand(@Nonnull List<JCommander> commandAncestors) {
        super(commandAncestors);
    }

    protected ApkOptions apkOptions = new ApkOptions();

    @Parameter(names = {"-q", "--quiet"},
            description = "suppress all output including errors.")
    protected boolean quiet = false;

    @Parameter(names = {"-v", "--verbose"},
            description = "Generate verbose error messages.")
    protected boolean verbose = false;

    @Override
    public void run() {
        super.run();
//        ApktoolMain.setupLogging();

        apkOptions.verbose = verbose;
    }
}
