package com.rover12421.shaka.lib.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

/**
 * Created by rover12421 on 8/26/15.
 */
public enum CommandLineArgEnum {
    LANGUAGE("lng", "language", true, "Locale", "Display language, e.g. zh-CN, zh-TW"),
    NO_9_PNG("n9", "no-9png", "Do not decode .9 png file."),
    USING_DEFAULT_FRAMEWORK("df", "default-framework", "Using default framework file."),
    SHOW_MORE_RECOGNIZABLE_CHARACTERS("mc", "more-recognizable-characters", "Show more recognizable characters."),
    FUCK_UNKOWN_ID("fui", "fuck-unkown-id", "Fuck unkown id"),
    IGNORE_RES_DECODE_ERROR("ir", "ignore-res-decode-error", "ignore res decode error."),
    XML_ATTRIBUTE_NAME_CORRECT("xn", "xml-attribute-name-correct", "xml attribute name correct. May be has problem, not recommended."),
    FUCK_NOT_DEFINED_RES("fnd", "fuck-not-defined-res", "Fuck the `Public symbol drawable/? declared here is not defined`."),
    InlieMethodResolverFromFile("li", "load-inline-method", true, "file", "load inline method resolver from file"),
    ;

    private Option option;
    private String longOpt;
    private String opt;
    private String desc;
    private boolean hasArg;
    private String argName;

    CommandLineArgEnum(String opt, String longOpt, boolean hasArg, String argName, String desc) {
        this.longOpt = longOpt;
        this.opt = opt;
        this.desc = desc;
        this.hasArg = hasArg;
        this.argName = argName;

        option = OptionBuilder.withLongOpt(longOpt)
                .withDescription(desc)
                .hasArg(hasArg)
                .withArgName(argName)
                .create(opt);
    }

    CommandLineArgEnum(String opt, String longOpt, String desc) {
        this.longOpt = longOpt;
        this.opt = opt;
        this.desc = desc;
        hasArg = false;

        option = OptionBuilder.withLongOpt(longOpt)
                .withDescription(desc)
                .create(opt);
    }

    public Option getOption() {
        return option;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 是否匹配
     * @param cli
     * @return
     */
    public boolean hasMatch(CommandLine cli) {
        return cli.hasOption(opt) || cli.hasOption(longOpt);
    }

    public String getLongOpt() {
        return longOpt;
    }

    public String getOpt() {
        return opt;
    }
}
