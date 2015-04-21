package com.rover12421.shaka.apktool.lib;

/**
 * Created by rover12421 on 3/30/15.
 */
public class ShakaDecodeOption {
    private static ShakaDecodeOption option = new ShakaDecodeOption();

    public static ShakaDecodeOption getInstance() {
        return option;
    }

    private ShakaDecodeOption() {
    }

    private boolean no9png = false;
    private boolean usingDefaultFramework = false;
    //显示更多可识别字符
    private boolean showMoreRecognizableCharacters = false;

    private boolean fuckUnkownId = false;
    private boolean ignoreResDecodeError = false;

    public boolean isNo9png() {
        return no9png;
    }

    public void setNo9png(boolean no9png) {
        this.no9png = no9png;
    }

    public boolean isUsingDefaultFramework() {
        return usingDefaultFramework;
    }

    public void setUsingDefaultFramework(boolean usingDefaultFramework) {
        this.usingDefaultFramework = usingDefaultFramework;
    }

    public boolean isShowMoreRecognizableCharacters() {
        return showMoreRecognizableCharacters;
    }

    public void setShowMoreRecognizableCharacters(boolean showMoreRecognizableCharacters) {
        this.showMoreRecognizableCharacters = showMoreRecognizableCharacters;
    }

    public boolean isFuckUnkownId() {
        return fuckUnkownId;
    }

    public void setFuckUnkownId(boolean fuckUnkownId) {
        this.fuckUnkownId = fuckUnkownId;
    }

    public boolean isIgnoreResDecodeError() {
        return ignoreResDecodeError;
    }

    public void setIgnoreResDecodeError(boolean ignoreResDecodeError) {
        this.ignoreResDecodeError = ignoreResDecodeError;
    }
}
