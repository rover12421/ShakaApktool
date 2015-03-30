package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.androlib.Androlib;
import brut.androlib.AndrolibException;
import brut.androlib.res.data.ResTable;
import brut.androlib.res.data.ResUnknownFiles;
import brut.androlib.res.util.ExtFile;
import brut.directory.Directory;
import brut.directory.DirectoryException;
import brut.directory.FileDirectory;
import brut.util.BrutIO;
import com.rover12421.shaka.apktool.lib.ShakaProperties;
import com.rover12421.shaka.apktool.util.AndroidZip;
import com.rover12421.shaka.apktool.util.LogHelper;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import org.apache.commons.io.IOUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.*;
import java.nio.file.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.*;

/**
 * Created by rover12421 on 8/9/14.
 */
@Aspect
public class AndrolibAj {

    public String getUNK_DIRNAME() throws NoSuchFieldException, IllegalAccessException {
        return (String) ReflectUtil.getFieldValue(Androlib.class, "UNK_DIRNAME");
    }

    /**
     * 未知文件处理,在编译未知文件之前,重新扫描一次"unknown"目录
     */
    @Before("execution(void brut.androlib.Androlib.buildUnknownFiles(..))" +
            "&& args(appDir, outFile, meta)")
    public void buildUnknownFiles_before(JoinPoint joinPoint, File appDir, File outFile, Map<String, Object> meta) {
        try {
            String UNK_DIRNAME = getUNK_DIRNAME();
            File unknownFile = new File(appDir, UNK_DIRNAME);
            if (!unknownFile.exists()) {
                return;
            }

            try {
                Directory directory = new FileDirectory(unknownFile);
                Set<String> addFiles = directory.getFiles(true);
                Map<String, String> files = (Map<String, String>)meta.get("unknownFiles");
                if (files == null) {
                    ResUnknownFiles mResUnknownFiles = (ResUnknownFiles) ReflectUtil.getFieldValue(joinPoint.getThis(), "mResUnknownFiles");
                    files = mResUnknownFiles.getUnknownFiles();
                    meta.put("unknownFiles", files);
                }
                for (String file : addFiles) {
                    files.put(file, AndroidZip.getZipMethod(new File(appDir, file).getAbsolutePath()) + "");
                }
            } catch (DirectoryException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * public void build(ExtFile appDir, File outFile)
     */
    @Before("execution(void brut.androlib.Androlib.build(brut.androlib.res.util.ExtFile, java.io.File))")
    public void build_before() {
        LogHelper.getLogger().info("Using ShakaApktool " + ShakaProperties.getVersion());
    }


    /**
     *
     <B>com.carrot.carrotfantasy.apk</B>

     Exception in thread "main" org.jf.util.ExceptionWithContext: The assets/cfg.dex file in com.carrot.carrotfantasy.apk is too small to be a valid dex file
     at org.jf.dexlib2.DexFileFactory.loadDexFile(DexFileFactory.java:77)
     at org.jf.dexlib2.DexFileFactory.loadDexFile(DexFileFactory.java:59)
     at brut.androlib.src.SmaliDecoder.decode(SmaliDecoder.java:94)
     at brut.androlib.src.SmaliDecoder.decode(SmaliDecoder.java:46)
     at brut.androlib.Androlib.decodeSourcesSmali(Androlib.java:83)
     at brut.androlib.ApkDecoder.decode(ApkDecoder.java:146)
     at brut.apktool.Main.cmdDecode(Main.java:170)
     at brut.apktool.Main.main(Main.java:86)
     */
    @Around("execution(* brut.androlib.Androlib.decodeSourcesSmali(..))" +
            "&& args(apkFile, outDir, filename, debug, debugLinePrefix, bakdeb, api)")
    public void decodeSourcesSmali_around(ProceedingJoinPoint joinPoint, File apkFile, File outDir, String filename, boolean debug, String debugLinePrefix,
                            boolean bakdeb, int api) throws Throwable {
        try {
            joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable e) {
            if (!"classes.dex".equals(filename)) {
                LogHelper.getLogger().warning("decodeSourcesSmali " + filename + " error!");
            } else {
                throw e;
            }
        }
    }

    @Around("execution(* brut.androlib.Androlib.buildUnknownFiles(..))" +
            "&& args(appDir, outFile, meta)")
    public void buildUnknownFiles_around(File appDir, File outFile, Map<String, Object> meta)
            throws Throwable {
        if (meta.containsKey("unknownFiles")) {
            LogHelper.getLogger().info("Copying unknown files/dir...");

            Map<String, String> files = (Map<String, String>)meta.get("unknownFiles");
            File tempFile = File.createTempFile("buildUnknownFiles", "tmp");
            tempFile.delete();
            boolean renamed = outFile.renameTo(tempFile);
            if(!renamed) {
                throw new AndrolibException("Unable to rename temporary file");
            }

            try (
                    ZipFile inputFile = new ZipFile(tempFile);
                    ZipOutputStream actualOutput = new ZipOutputStream(new FileOutputStream(outFile))
            ) {
                copyExistingFiles(inputFile, actualOutput, files);
                copyUnknownFiles(appDir, actualOutput, files);
            } catch (IOException ex) {
                throw new AndrolibException(ex);
            }

            // Remove our temporary file.
            tempFile.delete();
        }
    }

    private void copyExistingFiles(ZipFile inputFile, ZipOutputStream outputFile, Map<String, String> excludeFiles) throws IOException {
        // First, copy the contents from the existing outFile:
        Enumeration<? extends ZipEntry> entries = inputFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = new ZipEntry(entries.nextElement());
            if (excludeFiles.get(entry.getName()) != null) {
                //排除的文件不处理.
                continue;
            }
            // We can't reuse the compressed size because it depends on compression sizes.
            entry.setCompressedSize(-1);
            outputFile.putNextEntry(entry);

            // No need to create directory entries in the final apk
            if (!entry.isDirectory()) {
                try (
                        InputStream is = inputFile.getInputStream(entry)
                ){
                    IOUtils.copy(is, outputFile);
                }
            }

            outputFile.closeEntry();
        }
    }

    private void copyUnknownFiles(File appDir, ZipOutputStream outputFile, Map<String, String> files)
            throws IOException, NoSuchFieldException, IllegalAccessException {
        String UNK_DIRNAME = getUNK_DIRNAME();
        File unknownFileDir = new File(appDir, UNK_DIRNAME);

        // loop through unknown files
        for (Map.Entry<String,String> unknownFileInfo : files.entrySet()) {
            File inputFile = new File(unknownFileDir, unknownFileInfo.getKey());
            if(inputFile.isDirectory()) {
                continue;
            }

            ZipEntry newEntry = new ZipEntry(unknownFileInfo.getKey());
            int method = Integer.valueOf(unknownFileInfo.getValue());
            LogHelper.getLogger().fine(String.format("Copying unknown file %s with method %d", unknownFileInfo.getKey(), method));
            if(method == ZipEntry.STORED) {
                newEntry.setMethod(ZipEntry.STORED);
                newEntry.setSize(inputFile.length());
                newEntry.setCompressedSize(-1);
                BufferedInputStream unknownFile = new BufferedInputStream(new FileInputStream(inputFile));
                CRC32 crc = calculateCrc(unknownFile);
                newEntry.setCrc(crc.getValue());

//                LogHelper.getLogger().fine("\tsize: " + newEntry.getSize());
            } else {
                newEntry.setMethod(ZipEntry.DEFLATED);
            }
            outputFile.putNextEntry(newEntry);

            try (
                    FileInputStream fis = new FileInputStream(inputFile)
            ){
                IOUtils.copy(fis, outputFile);
            }
            outputFile.closeEntry();
        }
    }

    public static CRC32 calculateCrc(InputStream input) throws IOException {
        CRC32 crc = new CRC32();
        int bytesRead;
        byte[] buffer = new byte[4096];
        while((bytesRead = input.read(buffer)) != -1) {
            crc.update(buffer, 0, bytesRead);
        }
        return crc;
    }

    /**
     * res中的资源没有被arsc中引用,没有生成id,就会丢失.
     * 这里是把这类丢失的文件添加到unkown目录下
     * @param joinPoint
     * @param apkFile
     * @param outDir
     * @param resTable
     */
    @After("execution(* brut.androlib.Androlib.decodeUnknownFiles(..))" +
            "&& args(apkFile, outDir, resTable)")
    public void decodeUnknownFiles_after(JoinPoint joinPoint, ExtFile apkFile, File outDir, ResTable resTable) {
        try {
            File unknownOut = new File(outDir, getUNK_DIRNAME());
            ResUnknownFiles mResUnknownFiles = (ResUnknownFiles) ReflectUtil.getFieldValue(joinPoint.getThis(), "mResUnknownFiles");
            ZipFile zipFile = new ZipFile(apkFile.getAbsolutePath());
            try (
                    FileInputStream fis = new FileInputStream(apkFile.getAbsoluteFile());
                    ZipInputStream zis = new ZipInputStream(fis)
                    ){
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.getName().startsWith("res/")) {
                        File resFile = new File(outDir, entry.getName());
                        File unFile = new File(unknownOut, entry.getName());
                        if (!resFile.exists()) {
                            unFile.getParentFile().mkdirs();
                            try (
                                    FileOutputStream fos = new FileOutputStream(unFile);
                                    InputStream is = zipFile.getInputStream(entry)
                                    ){
                                IOUtils.copy(is, fos);
                            }
                            mResUnknownFiles.addUnknownFileInfo(entry.getName(), String.valueOf(entry.getMethod()));
                        }
                    }
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }

}
