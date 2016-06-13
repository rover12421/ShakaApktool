package com.rover12421.shaka.cli;

import java.io.File;

/**
 * Created by rover12421 on 12/21/15.
 */
public class TestApkFramework {
    public static void main(String[] args) throws Exception {
        String apkName;
        apkName = "随意_2627677121/sanxing5.1.1/SystemUI";
        apkName = "随意_2627677121/framework/SystemUI";
//        apkName = "随意_2627677121/huawei/SystemUI";

        String inApkName = "ApkSample/" + apkName + ".apk";
        String framePath = new File(inApkName).getParentFile().getAbsolutePath();
        String outDir = "ApkSample/out";

        Main.main(new String[]{
                "d", "-f",
                inApkName,
                "-o", outDir,
//                "-df",
                "-mc",
                "-p", framePath,
//                "-xn"
//                "-n9"
        });

//        Main.main(new String[]{
//                "b",
////                "-p", framePath,
//                "-fnd",
//                outDir
//        });

    }
}
