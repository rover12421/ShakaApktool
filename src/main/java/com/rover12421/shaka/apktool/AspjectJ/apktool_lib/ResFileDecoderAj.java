package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.directory.Directory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by rover12421 on 4/3/15.
 */
@Aspect
public class ResFileDecoderAj {
    @Before("execution(* brut.androlib.res.decoder.ResFileDecoder.decode(..))" +
            "&& args(inDir, inFileName, outDir, outFileName, decoder)")
    public void decode(Directory inDir, String inFileName, Directory outDir,
                       String outFileName, String decoder) {
        if (!inFileName.equals(outFileName)) {
            AndrolibAj.DecodeFileMaps.put("res/" + inFileName, "res/" + outFileName);
        }
    }

    @Before("execution(* brut.androlib.res.decoder.ResFileDecoder.decodeManifest(..))" +
            "&& args(inDir, inFileName, outDir, outFileName)")
    public void decodeManifest(Directory inDir, String inFileName,
                               Directory outDir, String outFileName) {
        if (!inFileName.equals(outFileName)) {
            AndrolibAj.DecodeFileMaps.put(inFileName, outFileName);
        }
    }
}
