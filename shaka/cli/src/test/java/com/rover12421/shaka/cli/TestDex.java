package com.rover12421.shaka.cli;

/**
 * Created by rover12421 on 12/6/15.
 */
public class TestDex {
    /**
     * 397216633 Say、红尘 七少月
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String dexName;
        dexName = "397216633/classes未修复.dex";
//        dexName = "397216633/classes修复后.dex";
//        dexName = "越狱/framework/oat/arm64/services.odex";

//        String inDexFile = "/home/rover12421/KuaiPan/Rs/apktoolSample/" + dexName + ".dex";
        String inDexFile = "ApkSample/" + dexName;
        String outDir = "ApkSample/out2";

        Main.main(new String[]{
                "bs", "-f",
                inDexFile,
//                "-x",
//                "-d", new File(inDexFile).getParentFile().getParentFile().getParent() + "/",
                "-o", outDir,
        });
    }
}
