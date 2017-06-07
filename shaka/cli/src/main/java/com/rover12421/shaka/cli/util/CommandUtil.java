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

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import org.jf.util.jcommander.Command;
import org.jf.util.jcommander.ExtendedCommands;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

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

    public static void exceptionExit(JCommander command, Throwable throwable) {
        System.err.println(throwable.getMessage());
        command.usage();
        System.exit(ExceptionExitCode);
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

    public static void modifyAnnotationValue(Annotation annotation, String key, Object value){
        try {
            Object handler = Proxy.getInvocationHandler(annotation);
            Field f = handler.getClass().getDeclaredField("memberValues");
            f.setAccessible(true);
            Map<String, Object> memberValues = (Map<String, Object>) f.get(handler);
            memberValues.put(key, value);
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    private static void setParameterDescriptionKey(Parameter parameter, String descriptionKey){
        modifyAnnotationValue(parameter, "descriptionKey", descriptionKey);
    }

    private static void setParametersCommandDescriptionKey(Parameters parameters, String commandDescriptionKey){
        modifyAnnotationValue(parameters, "commandDescriptionKey", commandDescriptionKey);
    }

    public static void addExtendedCommand(JCommander jc, Command command) {
        Class<?> commandClass = command.getClass();
        String commandName = ExtendedCommands.commandName(command);
        String prefix = "Command." + commandName;
        Parameters commandClassParameters = commandClass.getAnnotation(Parameters.class);
        if (commandClassParameters != null) {
            //System.out.println(prefix + "=" + commandClassParameters.commandDescription());
            setParametersCommandDescriptionKey(commandClassParameters, prefix);
        }
        prefix = prefix + ".";

        while (Command.class.isAssignableFrom(commandClass)) {
            Field[] declaredFields = commandClass.getDeclaredFields();
            for (Field field : declaredFields) {
                Parameter annotation = field.getAnnotation(Parameter.class);
                if (annotation != null) {
                    String descriptionKey = prefix + field.getName();
                    //System.out.println(descriptionKey + "=" + annotation.description());
                    setParameterDescriptionKey(annotation, descriptionKey);
                }
            }

            commandClass = commandClass.getSuperclass();
        }

        ExtendedCommands.addExtendedCommand(jc, command);
    }
}
