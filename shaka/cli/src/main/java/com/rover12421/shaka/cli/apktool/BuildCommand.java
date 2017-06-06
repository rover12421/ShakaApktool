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
import brut.common.BrutException;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.common.collect.Lists;
import com.rover12421.shaka.cli.base.ApktoolUsingFrameworkCommand;
import com.rover12421.shaka.cli.util.CommandUtil;
import org.jf.util.jcommander.ExtendedParameter;
import org.jf.util.jcommander.ExtendedParameters;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.List;

/**
 * Created by rover12421 on 05/06/2017.
 */
@Parameters(commandDescription = "build files into a apk file.")
@ExtendedParameters(
        commandName = "build",
        commandAliases = { "b" })
public class BuildCommand extends ApktoolUsingFrameworkCommand {

    public BuildCommand(@Nonnull List<JCommander> commandAncestors) {
        super(commandAncestors);
    }

    @Parameter(names = {"-c", "--copy-original"},
            description = "Copies original AndroidManifest.xml and META-INF. See project page for more info.")
    private boolean copyOriginal = false;

    @Parameter(names = {"-a", "--aapt"},
            description = "Loads aapt from specified location.")
    @ExtendedParameter(argumentNames = "loc")
    private String aaptPath;

    @Parameter(names = {"-df", "--default-framework"},
            description = "Using default framework file.")
    private boolean defaultFramework = false;

    @Parameter(names = {"-f", "--force-all"},
            description = "Skip changes detection and build all files.")
    private boolean forceAll = false;

    @Parameter(names = {"-o", "--output"},
            description = "The name of apk that gets written. Default is dist/name.apk")
    @ExtendedParameter(argumentNames = "loc")
    private String output;

    @Parameter(description = "decode directory.")
    @ExtendedParameter(argumentNames = "dir")
    protected List<String> inputList = Lists.newArrayList();

    @Override
    public void run() {
        super.run();

        String input = CommandUtil.getInput(this, inputList);
        apkOptions.forceBuildAll = forceAll;
        apkOptions.aaptPath = aaptPath;
        apkOptions.copyOriginalFiles = copyOriginal;
        File outFile = new File(output);

        try {
            new Androlib(apkOptions).build(new File(input), outFile);
        } catch (BrutException e) {
            CommandUtil.exceptionExit(e);
        }
    }
}
