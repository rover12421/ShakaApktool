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

import brut.androlib.AndrolibException;
import brut.androlib.ApkDecoder;
import brut.androlib.err.CantFindFrameworkResException;
import brut.androlib.err.InFileNotFoundException;
import brut.androlib.err.OutDirExistsException;
import brut.directory.DirectoryException;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.validators.PositiveInteger;
import com.google.common.collect.Lists;
import com.rover12421.shaka.cli.base.ApktoolUsingFrameworkCommand;
import com.rover12421.shaka.cli.util.CommandUtil;
import org.jf.util.jcommander.Command;
import org.jf.util.jcommander.ExtendedParameter;
import org.jf.util.jcommander.ExtendedParameters;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by rover12421 on 05/06/2017.
 */
@Parameters(commandDescription = "Disassembles a apk/dex file.")
@ExtendedParameters(
        commandName = "decode",
        commandAliases = { "d" })
public class DecodeCommand extends ApktoolUsingFrameworkCommand {
    @Parameter(names = {"-f", "--force"},
            description = "Force delete destination directory.")
    private boolean force = false;

    @Parameter(names = {"-o", "--output"},
            description = "The directory to write the disassembled files to.")
    @ExtendedParameter(argumentNames = "dir")
    private String output = "out";

    @Parameter(names = {"-r", "--no-res"},
            description = "Do not decode resources.")
    private boolean noRes = false;

    @Parameter(names = {"-s", "--no-src"},
        description = "Do not decode sources.")
    private boolean noSrc = false;

    @Parameter(names = {"-t", "--frame-tag"},
            description = "Uses framework files tagged by <tag>.")
    @ExtendedParameter(argumentNames = "tag")
    private String frameTag;

    @Parameter(names = {"-a", "--api"},
            description = "The numeric api level of the file being disassembled.")
    @ExtendedParameter(argumentNames = "api")
    private int apiLevel = 15;

    @Parameter(names = {"-k", "--keep-broken-res"},
            description = "Use if there was an error and some resources were dropped, e.g. \"Invalid config flags detected. Dropping resources\", but you want to decode them anyway, even with errors. You will have to fix them manually before building.")
    private boolean keepBrokenRes = false;

    @Parameter(names = {"-m", "--match-original"},
            description = "Keeps files to closest to original as possible. Prevents rebuild.")
    private boolean matchOriginal = false;

    @Parameter(names = {"--debug-info", "--di"}, arity = 1,
            description = "Whether to include debug information in the output (.local, .param, .line, etc.). True " +
                    "by default, use --debug-info=false to disable.")
    @ExtendedParameter(argumentNames = "boolean")
    private boolean debugInfo = true;

    @Parameter(names = {"--code-offsets", "--offsets", "--off"},
            description = "Add a comment before each instruction with it's code offset within the method.")
    private boolean codeOffsets = false;

    @Parameter(names = {"--resolve-resources", "--rr"}, arity = 2,
            description = "This will attempt to find any resource id references within the bytecode and add a " +
                    "comment with the name of the resource being referenced. The parameter accepts 2 values:" +
                    "an arbitrary resource prefix and the path to a public.xml file. For example: " +
                    "--resolve-resources android.R framework/res/values/public.xml. This option can be specified " +
                    "multiple times to provide resources from multiple packages.")
    @ExtendedParameter(argumentNames = {"resource prefix", "public.xml file"})
    private List<String> resourceIdFiles = Lists.newArrayList();

    @Parameter(names = {"-j", "--jobs"},
            description = "The number of threads to use. Defaults to the number of cores available.",
            validateWith = PositiveInteger.class)
    @ExtendedParameter(argumentNames = "n")
    private int jobs = Runtime.getRuntime().availableProcessors();

    @Parameter(names = {"-l", "--use-locals"},
            description = "When disassembling, output the .locals directive with the number of non-parameter " +
                    "registers instead of the .registers directive with the total number of registers.")
    private boolean localsDirective = false;

    @Parameter(names = {"--accessor-comments", "--ac"}, arity = 1,
            description = "Generate helper comments for synthetic accessors. True by default, use " +
                    "--accessor-comments=false to disable.")
    @ExtendedParameter(argumentNames = "boolean")
    private boolean accessorComments = true;

    @Parameter(names = {"--normalize-virtual-methods", "--norm", "--nvm"},
            description = "Normalize virtual method references to use the base class where the method is " +
                    "originally declared.")
    private boolean normalizeVirtualMethods = false;

    @Parameter(names = {"--parameter-registers", "--preg", "--pr"}, arity = 1,
            description = "Use the pNN syntax for registers that refer to a method parameter on method entry. True " +
                    "by default, use --parameter-registers=false to disable.")
    @ExtendedParameter(argumentNames = "boolean")
    private boolean parameterRegisters = true;

    @Parameter(names = {"-ri", "--register-info"},
            description = "Add comments before/after each instruction with information about register types. " +
                    "The value is a comma-separated list of any of ALL, ALLPRE, ALLPOST, ARGS, DEST, MERGE and " +
                    "FULLMERGE. See \"baksmali help register-info\" for more information.")
    @ExtendedParameter(argumentNames = "register info specifier")
    private List<String> registerInfoTypes = Lists.newArrayList();

    @Parameter(names = {"--sequential-labels", "--seq", "--sl"},
            description = "Create label names using a sequential numbering scheme per label type, rather than " +
                    "using the bytecode address.")
    private boolean sequentialLabels = false;

    @Parameter(names = {"--implicit-references", "--implicit", "--ir"},
            description = "Use implicit method and field references (without the class name) for methods and " +
                    "fields from the current class.")
    private boolean implicitReferences = false;

    @Parameter(names = "--allow-odex-opcodes",
            description = "Allows odex opcodes to be disassembled, even if the result won't be able to be reassembled.")
    private boolean allowOdex = false;

    @Parameter(names = "--classes",
            description = "A comma separated list of classes. Only disassemble these classes")
    @ExtendedParameter(argumentNames = "classes")
    private List<String> classes = null;

    @Parameter(description = "A dex/apk/oat/odex file.")
    @ExtendedParameter(argumentNames = "file")
    protected List<String> inputList = Lists.newArrayList();

    public DecodeCommand(@Nonnull List<JCommander> commandAncestors) {
        super(commandAncestors);
    }

    @Override
    public void run() {
        super.run();

        String input = CommandUtil.getInput(this, inputList);

        ApkDecoder decoder = new ApkDecoder();
        try {
            if (noRes) {
                decoder.setDecodeResources(ApkDecoder.DECODE_RESOURCES_NONE);
            }
            if (noSrc) {
                decoder.setDecodeResources(ApkDecoder.DECODE_SOURCES_NONE);
            }
            decoder.setBaksmaliDebugMode(debugInfo);
            decoder.setFrameworkTag(frameTag);
            decoder.setForceDelete(force);
            decoder.setKeepBrokenResources(keepBrokenRes);
            decoder.setFrameworkDir(framePath);
            if (matchOriginal) {
                decoder.setAnalysisMode(true, false);
            }
            decoder.setApi(apiLevel);
            decoder.setOutDir(new File(output));
            decoder.setApkFile(new File(input));

        } catch (Throwable e) {
            CommandUtil.exceptionExit(e);
        }

        try {
            decoder.decode();
        } catch (OutDirExistsException ex) {
            CommandUtil.exceptionExit("Destination directory ("
                            + output
                            + ") "
                            + "already exists. Use -f switch if you want to overwrite it.");
        } catch (InFileNotFoundException ex) {
            CommandUtil.exceptionExit("Input file (" + input + ") " + "was not found or was not readable.");
        } catch (CantFindFrameworkResException ex) {
            CommandUtil.exceptionExit("Can't find framework resources for package of id: "
                            + String.valueOf(ex.getPkgId())
                            + ". You must install proper "
                            + "framework files, see project website for more info.");
        } catch (IOException ex) {
            CommandUtil.exceptionExit("Could not modify file. Please ensure you have permission.");
        } catch (DirectoryException ex) {
            CommandUtil.exceptionExit("Could not modify internal dex files. Please ensure you have permission.");
        } catch (AndrolibException e) {
            CommandUtil.exceptionExit(e);
        } finally {
            try {
                decoder.close();
            } catch (IOException ignored) {}
        }
    }
}
