package com.rover12421.shaka.cli;

import java.io.File;

/**
 * Created by rover12421 on 1/27/16.
 */
public class TestNexus5 {
    public static void main(String[] args) throws Exception {

        String inDexFile = "ApkSample/Nexus5_hammerhead-mmb29k_framework/oat/arm/services.odex";
        String outDir = "ApkSample/out";
        String bootclasspath = "ApkSample/Nexus5_hammerhead-mmb29k_framework/arm";

        bootclasspath = new File(bootclasspath).getCanonicalPath();

        System.out.println("bootclasspath : " + bootclasspath);

//        String[] files = new File(bootclasspath).list();
//        String bootFiles = "";
//        for (String path : files) {
//            bootFiles += ":"+bootclasspath+"/"+path;
//        }

        String bootFiles = new File("ApkSample/Nexus5_hammerhead-mmb29k_framework/arm/boot.oat").getCanonicalPath();

//        Main.main(new String[]{
//                "bs",
//                inDexFile,
//                "-o", outDir,
//                "-x",
//                "-d", bootclasspath,
//                "-a", "23",
//                "-c", bootFiles,
//        });

        Main.main(new String[]{
                "s",
                outDir,
                "-o", outDir+ "/services.dex"
        });
    }
}
