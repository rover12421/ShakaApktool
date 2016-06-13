package com.rover12421.shaka.cli;

/**
 * Created by rover12421 on 7/23/15.
 */
public class TestApk {
    public static void main(String[] args) throws Exception {
        String apkName;
//        apkName = "越狱/rjs_pep_2.0.0.49.121347";
//        apkName = "越狱/zanticn"; //java.lang.ArrayIndexOutOfBoundsException: -266
//        apkName = "96070/weixin637android660";
//        apkName = "self/com.lbe.parallel-0.3.1521-8";
//        apkName = "self/weixin639android700";
//        apkName = "余威克/101android_apk_sign";
//        apkName = "随意_2627677121/101android_apk_sign";
//        apkName = "apktool/issues_1105";
//        apkName = "dimaggio99/1";
//        apkName = "黄小波/personaldoc_SJGW_V_3.0.0_30000";
        apkName = "随意_2627677121/sanxing6.01/twframework-res";

        String inApkName = "ApkSample/" + apkName + ".apk";
        String outDir = "ApkSample/out";

        Main.main(new String[]{
                "d", "-f",
                inApkName,
                "-o", outDir,
//                "-df",
                "-mc",
                "-xn"
//                "-n9"
        });

//        Main.main(new String[]{
//                "b",
//                "-fnd",
////                "-df",
////                "-f",
////                outDir
//                "/home/rover12421/Desktop/222222222/kunlun昆仑-神魔圣域-v1.72.1.0303"
//        });

    }
}
