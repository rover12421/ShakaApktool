package com.rover12421.shaka.lib;

/**
 * Created by rover12421 on 3/30/15.
 */
public class ShakaBuildOption {
    private static final ShakaBuildOption option = new ShakaBuildOption();

    public static ShakaBuildOption getInstance() {
        return option;
    }

    private ShakaBuildOption() {
    }

    private boolean fuckNotDefinedRes = false;

    public boolean isFuckNotDefinedRes() {
        return fuckNotDefinedRes;
    }

    public void setFuckNotDefinedRes(boolean fuckNotDefinedRes) {
        this.fuckNotDefinedRes = fuckNotDefinedRes;
    }
}
