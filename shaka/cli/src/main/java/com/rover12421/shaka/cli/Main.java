/*
 * Copyright 2017 Rover12421 <rover12421@163.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rover12421.shaka.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.common.collect.Lists;
import org.jf.util.jcommander.Command;
import org.jf.util.jcommander.ExtendedCommands;
import org.jf.util.jcommander.ExtendedParameters;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by rover12421 on 05/06/2017.
 */
@ExtendedParameters(
        includeParametersInUsage = true,
        commandName = "ShakaApktool",
        postfixDescription = "See ShakaApktool help <command> for more information about a specific command")
public class Main extends Command {
    public static final String VERSION = loadVersion();

    @Parameter(names = {"-h", "-?", "--help"}, help = true,
            description = "Show usage information")
    private boolean help;

    @Parameter(names = {"-v", "--version"}, help = true,
            description = "Print the version of baksmali and then exit")
    public boolean version;

    private JCommander jc;

    public Main() {
        super(Lists.newArrayList());
    }

    @Override
    public void run() {
    }

    @Override protected JCommander getJCommander() {
        return jc;
    }

    private static String loadVersion() {
        String version = "[unknown version]";

        try(
                InputStream propertiesStream = Main.class.getClassLoader().getResourceAsStream("properties/shaka.properties")
                ) {
            Properties properties = new Properties();
            properties.load(propertiesStream);
            version = properties.getProperty("version");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static void main(String[] args) {
        Main main = new Main();
        JCommander jc = new JCommander(main);
        main.jc = jc;
        jc.setProgramName("ShakaApktool");
        List<JCommander> commandHierarchy = main.getCommandHierarchy();
        ExtendedCommands.addExtendedCommand(jc, new DecodeCommand(commandHierarchy));
        jc.parse(args);

        if (main.version) {
            version();
        }

        if (jc.getParsedCommand() == null || main.help) {
            main.usage();
            return;
        }

        Command command = (Command)jc.getCommands().get(jc.getParsedCommand()).getObjects().get(0);
        command.run();
    }

    protected static void version() {
        System.out.println("ShakaApktool " + VERSION);
        System.out.println("Copyright (C) 2017 Rover12421 <rover12421@163.com>");
        System.out.println("Apache2 license (http://www.apache.org/licenses/LICENSE-2.0)");
        System.exit(0);
    }

}
