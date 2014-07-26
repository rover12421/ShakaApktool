package com.rover12421.shaka.apktool.test;

import brut.common.BrutException;
import org.apache.commons.cli.CommandLine;

import java.io.IOException;

/**
 * Created by rover12421 on 7/11/14.
 */
public class TestMain {
    public static void main(String[] args) throws InterruptedException, BrutException, IOException {
        brut.apktool.Main.main(new String[]{"usage", "--version"});
    }
}
