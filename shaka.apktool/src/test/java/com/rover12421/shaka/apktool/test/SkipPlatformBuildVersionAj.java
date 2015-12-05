package com.rover12421.shaka.apktool.test;

import brut.androlib.BuildAndDecodeTest;
import brut.androlib.res.util.ExtFile;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.custommonkey.xmlunit.ElementQualifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by rover12421 on 11/19/15.
 *
 * 目前已经发现`Linux`平台下, `aapt 21.0.0`之后,打包会使用`aapt`自带版本覆盖原有的 `platformBuildVersion`
 * 等待官方修复
 * 因该属性不重要,暂时强制修正,仅用于`test case`
 */
@Aspect
public class SkipPlatformBuildVersionAj {

    private String replacePlatformBuildVersion(String src,
                                               String platformBuildVersionCode,
                                               String platformBuildVersionName) {
        return src
                .replaceAll("(.+?platformBuildVersionCode=\")([^\"]+?)(\".+?)", "$1" + platformBuildVersionCode + "$3")
                .replaceAll("(.+?platformBuildVersionName=\")([^\"]+?)(\".+?)", "$1" + platformBuildVersionName + "$3");
    }

    @Before("execution(* brut.androlib.BuildAndDecodeTest.compareXmlFiles(..))" +
            "&& args(path, qualifier)")
    public void compareXmlFiles(String path, ElementQualifier qualifier) {
        try {
            ExtFile sTestOrigDir = BuildAndDecodeTest.getTestOrigDir();
            ExtFile sTestNewDir = BuildAndDecodeTest.getTestNewDir();
            Path originPath = Paths.get(sTestOrigDir.getAbsolutePath(), path);
            Path newPath = Paths.get(sTestNewDir.getAbsolutePath(), path);
            String originString = new String(Files.readAllBytes(originPath));
            String newString = new String(Files.readAllBytes(newPath));

            String platformBuildVersionCode = originString
                    .replaceAll("[\\r\\n]", "")
                    .replaceAll(".+platformBuildVersionCode=\"([^\"]+?)\".+", "$1");
            String platformBuildVersionName = originString
                    .replaceAll("[\\r\\n]", "")
                    .replaceAll(".+platformBuildVersionName=\"([^\"]+?)\".+", "$1");
            newString = replacePlatformBuildVersion(newString, platformBuildVersionCode, platformBuildVersionName);

            Files.write(newPath, newString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Around("execution(* brut.androlib.ProviderAttributeTest.replaceNewlines(..))" +
            "&& args(value)")
    public String replaceNewlines(ProceedingJoinPoint joinPoint, String value) throws Throwable {
        value = replacePlatformBuildVersion(value, "22", "5.1-1756733");
        return (String) joinPoint.proceed(new Object[]{value});
    }
}
