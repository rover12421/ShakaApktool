package com.rover12421.shaka.smali.baksmali;

/**
 * Created by rover12421 on 9/14/15.
 */
public class ShakaBaksmaliOption {
    private static String InlineMethodResolverFile = null;

    public static String getInlineMethodResolverFile() {
        return InlineMethodResolverFile;
    }

    public static void setInlineMethodResolverFile(String inlineMethodResolverFile) {
        InlineMethodResolverFile = inlineMethodResolverFile;
    }
}
