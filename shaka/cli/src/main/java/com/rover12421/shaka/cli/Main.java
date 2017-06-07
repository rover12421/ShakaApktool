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
import com.rover12421.shaka.cli.apktool.*;
import com.rover12421.shaka.cli.baksmali.DisassembleCommand;
import com.rover12421.shaka.cli.baksmali.DumpCommand;
import com.rover12421.shaka.cli.baksmali.ListCommand;
import com.rover12421.shaka.cli.base.HelpAndLanguageCommand;
import com.rover12421.shaka.cli.smali.AssembleCommand;
import com.rover12421.shaka.cli.util.CommandUtil;
import org.jf.baksmali.DeodexCommand;
import org.jf.util.jcommander.Command;
import org.jf.util.jcommander.ExtendedParameters;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by rover12421 on 05/06/2017.
 */
@ExtendedParameters(
        includeParametersInUsage = true,
        commandName = "ShakaApktool",
        postfixDescription = "See ShakaApktool help <command> for more information about a specific command")
public class Main extends HelpAndLanguageCommand {
    public static final String VERSION = loadVersion();

    @Parameter(names = {"-v", "--version"}, help = true,
            descriptionKey = "Command.main.version",
            description = "Print program version.")
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

    public Command command;

    public static void main(String[] args) {
        Main main = new Main();

        Locale locale = Locale.getDefault();
        for (int i = 0; i < args.length-1; i++) {
            String arg = args[i];
            //"-lng", "--language"
            if (arg.equals("-lng") || arg.equals("--language")) {
                String localeStr = args[i+1];
                locale = Locale.forLanguageTag(localeStr);
                if (locale.toString().isEmpty()) {
                    localeStr = localeStr.replaceAll("_", "-");
                    locale = Locale.forLanguageTag(localeStr);
                }
                break;
            }
        }
        ResourceBundle bundle = ResourceBundle.getBundle("i18n/Messages", locale);

        CommandUtil.modifyAnnotationValue(Main.class.getAnnotation(ExtendedParameters.class),
                "postfixDescription", bundle.getString("Command.main.postfixDescription"));

        JCommander jc = new JCommander(main, bundle);
        main.jc = jc;
        jc.setProgramName("ShakaApktool");
        jc.setAllowParameterOverwriting(true);
        List<JCommander> commandHierarchy = main.getCommandHierarchy();

        /**
         * Apktool
         */
        CommandUtil.addExtendedCommand(jc, new DecodeCommand(commandHierarchy));
        CommandUtil.addExtendedCommand(jc, new BuildCommand(commandHierarchy));
        CommandUtil.addExtendedCommand(jc, new InstallFrameworkCommand(commandHierarchy));
        CommandUtil.addExtendedCommand(jc, new PublicizeResourcesCommand(commandHierarchy));
        CommandUtil.addExtendedCommand(jc, new EmptyFrameworkDirCommand(commandHierarchy));

        /**
         * baksmali
         */
        CommandUtil.addExtendedCommand(jc, new DisassembleCommand(commandHierarchy));
        CommandUtil.addExtendedCommand(jc, new DeodexCommand(commandHierarchy));
        CommandUtil.addExtendedCommand(jc, new DumpCommand(commandHierarchy));
        CommandUtil.addExtendedCommand(jc, new ListCommand(commandHierarchy));

        /**
         * smali
         */
        CommandUtil.addExtendedCommand(jc, new AssembleCommand(commandHierarchy));

        try {
            jc.parse(args);
        } catch (Throwable e) {
            e.printStackTrace();
            CommandUtil.exceptionExit(jc, e);
        }

        if (main.version) {
            version();
            return;
        }

        if (jc.getParsedCommand() == null || main.help) {
            main.usage();
            return;
        }

        Command command = (Command)jc.getCommands().get(jc.getParsedCommand()).getObjects().get(0);
        main.command = command;
        command.run();
    }

    protected static void version() {
        System.out.println("ShakaApktool " + VERSION);
        System.out.println("Copyright (C) 2017 Rover12421 <rover12421@163.com>");
        System.out.println("Apache2 license (http://www.apache.org/licenses/LICENSE-2.0)");
        System.exit(0);
    }

}
