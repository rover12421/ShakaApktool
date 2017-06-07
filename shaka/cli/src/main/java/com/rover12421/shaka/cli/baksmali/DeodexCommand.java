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

package com.rover12421.shaka.cli.baksmali;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import org.jf.util.jcommander.ExtendedParameter;
import org.jf.util.jcommander.ExtendedParameters;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Locale;

/**
 * Created by rover12421 on 06/06/2017.
 */
@Parameters(commandDescription = "Deodexes an odex/oat file")
@ExtendedParameters(
        commandName = "deodex",
        commandAliases = { "de", "x" })
public class DeodexCommand extends org.jf.baksmali.DeodexCommand {
    public DeodexCommand(@Nonnull List<JCommander> commandAncestors) {
        super(commandAncestors);
    }

    @Parameter(names = {"-lng", "--language"}, help = true,
            description = "Display language, e.g. zh-CN, zh-TW.")
    @ExtendedParameter(argumentNames = "Locale")
    protected String localeStr = Locale.getDefault().toLanguageTag();
}
