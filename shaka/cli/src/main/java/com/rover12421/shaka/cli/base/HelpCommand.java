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

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.jf.util.jcommander.Command;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by rover12421 on 6/6/17.
 */
public abstract class HelpCommand extends Command {
    protected HelpCommand(@Nonnull List<JCommander> commandAncestors) {
        super(commandAncestors);
    }

    @Parameter(names = {"-h", "-?", "--help"}, help = true,
            description = "Show usage information for this command.")
    protected boolean help;

    @Override
    public void run() {
        if (help) {
            usage();
            System.exit(0);
        }
    }
}
