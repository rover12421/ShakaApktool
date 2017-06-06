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

import brut.util.OSDetection;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.jf.util.jcommander.ExtendedParameter;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.List;

/**
 * Created by rover12421 on 6/6/17.
 */
public abstract class ApktoolUsingFrameworkCommand extends ApktoolDefaultCommand {
    public ApktoolUsingFrameworkCommand(@Nonnull List<JCommander> commandAncestors) {
        super(commandAncestors);
    }

    @Parameter(names = {"-p", "--frame-path"},
            description = "Uses framework files located in <dir>.")
    @ExtendedParameter(argumentNames = "dir")
    protected String framePath = getDefaultFrameworkDir();

    @Override
    public void run() {
        super.run();

        apkOptions.frameworkFolderLocation = framePath;
    }

    private String getDefaultFrameworkDir() {
        File parentPath = new File(System.getProperty("user.home"));
        if (! parentPath.canWrite()) {
            parentPath = new File(System.getProperty("java.io.tmpdir"));
        }

        String path;
        if (OSDetection.isMacOSX()) {
            path = parentPath.getAbsolutePath() + String.format("%1$sLibrary%1$sShakaApktool%1$sframework", File.separatorChar);
        } else if (OSDetection.isWindows()) {
            path = parentPath.getAbsolutePath() + String.format("%1$sAppData%1$sLocal%1$sShakaApktool%1$sframework", File.separatorChar);
        } else {
            path = parentPath.getAbsolutePath() + String.format("%1$s.local%1$sshare%1$sShakaApktool%1$sframework", File.separatorChar);
        }
        return path;
    }
}
