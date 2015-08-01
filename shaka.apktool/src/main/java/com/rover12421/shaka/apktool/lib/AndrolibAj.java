/**
 *  Copyright 2015 Rover12421 <rover12421@163.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 *  Copyright 2015 Rover12421 <rover12421@163.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.rover12421.shaka.apktool.lib;

import brut.androlib.Androlib;
import brut.androlib.AndrolibException;
import brut.androlib.res.data.ResTable;
import brut.androlib.res.data.ResUnknownFiles;
import brut.androlib.res.util.ExtFile;
import brut.androlib.src.SmaliDecoder;
import brut.directory.Directory;
import brut.directory.DirectoryException;
import brut.directory.FileDirectory;
import brut.util.BrutIO;
import brut.util.OS;
import com.rover12421.shaka.lib.ShakaProperties;
import com.rover12421.shaka.lib.AndroidZip;
import com.rover12421.shaka.lib.LogHelper;
import com.rover12421.shaka.lib.ReflectUtil;
import org.apache.commons.io.IOUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.io.*;
import java.nio.file.*;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.*;

/**
 * Created by rover12421 on 8/9/14.
 */
@Aspect
public class AndrolibAj {

    public String getUNK_DIRNAME() {
//        return (String) ReflectUtil.getFieldValue(Androlib.class, "UNK_DIRNAME");
        return "unknown";
    }

    private final static String SMALI_DIRNAME = "smali";
    private final static String APK_DIRNAME = "build/apk";

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
                    //判断下.是否已经存在.已经存在的,压缩模式使用原包的模式
                    if (!files.containsKey(file)) {
                        files.put(file, AndroidZip.getZipMethod(new File(appDir, file).getAbsolutePath()) + "");
                    }
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
        LogHelper.info("Using ShakaApktool " + ShakaProperties.getVersion());
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

        File smaliDir = null;
        String mapDirName = null;
        try {

            if (filename.equalsIgnoreCase("classes.dex")) {
                smaliDir = new File(outDir, SMALI_DIRNAME);
            } else {
                mapDirName = SMALI_DIRNAME + "_" + filename.replaceAll("\\\\|/", "_");
                //去掉.dex后缀
                mapDirName = mapDirName.substring(0, mapDirName.length()-4);
                smaliDir = new File(outDir, mapDirName);
            }
            OS.rmdir(smaliDir);
            smaliDir.mkdirs();
            LogHelper.info("Baksmaling " + filename + "...");
            SmaliDecoder.decode(apkFile, smaliDir, filename, debug, debugLinePrefix, bakdeb, api);
            //没有异常才添加
            if (mapDirName != null) {
                DexMaps.put(filename, mapDirName);
            }
        } catch (Exception ex) {
            //只要不是反编译classes.dex的时候抛出异常,都不终止程序
            if (!"classes.dex".equals(filename)) {
                LogHelper.warning("decodeSourcesSmali " + filename + " error!");
                if (smaliDir != null) {
                    OS.rmdir(smaliDir);
                }
            } else {
                throw ex;
            }
        }
    }

    @Around("execution(* brut.androlib.Androlib.buildNonDefaultSources(..))" +
            "&& args(appDir)")
    public void buildNonDefaultSources(ProceedingJoinPoint joinPoint, ExtFile appDir) {
        /**
         * 兼容未添加到`apktool.yml`的情况
         * 只兼容5.0多包标准结构, `gradle test` 会测试到这种情况
         * 其它情况需手动添加到`apktool.yml`下
         */
        File[] files = appDir.getAbsoluteFile().listFiles();
        if (files != null) {
            for (File file : files) {
                String name = file.getName();
                if (file.isDirectory() && !DexMaps.containsKey(name) && name.startsWith("smali_")) {
                    String key = name.substring("smali_".length()) + ".dex";
                    DexMaps.put(key, name);
                }
            }
        }

        for (String dex : DexMaps.keySet()) {
            try {
                Androlib androlib = (Androlib) joinPoint.getThis();
                File dexFile = new File(appDir, APK_DIRNAME + "/" + dex);
                dexFile.getParentFile().mkdirs();
                androlib.buildSourcesSmali(appDir, DexMaps.get(dex), dex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Around("execution(* brut.androlib.Androlib.decodeSourcesRaw(..))" +
            "&& args(apkFile, outDir, filename)")
    public void decodeSourcesRaw(ProceedingJoinPoint joinPoint, ExtFile apkFile, File outDir, String filename) throws Throwable {
        /**
         * 不解码dex的时候,只有classes.dex需要copy,其他的全部作为unkownfiles文件处理即可
         */
        if (filename.equals("classes.dex")) {
            joinPoint.proceed(joinPoint.getArgs());
        }
    }

    @Around("execution(* brut.androlib.Androlib.buildUnknownFiles(..))" +
            "&& args(appDir, outFile, meta)")
    public void buildUnknownFiles_around(File appDir, File outFile, Map<String, Object> meta)
            throws Throwable {
        if (meta.containsKey("unknownFiles")) {
            LogHelper.info("Copying unknown files/dir...");

            Map<String, String> files = (Map<String, String>)meta.get("unknownFiles");
            File tempFile = File.createTempFile("buildUnknownFiles", "tmp", outFile.getParentFile());
            tempFile.delete();
            boolean renamed = outFile.renameTo(tempFile);
            if(!renamed) {
                throw new AndrolibException("Unable to rename temporary file");
            }

            try (
                    ZipFile inputFile = new ZipFile(tempFile);
                    FileOutputStream fos = new FileOutputStream(outFile);
                    ZipOutputStream actualOutput = new ZipOutputStream(fos)
            ) {
                copyExistingFiles(inputFile, actualOutput, files);
                copyUnknownFiles(appDir, actualOutput, files);
            } catch (IOException ex) {
                throw new AndrolibException(ex);
            } finally {
                // Remove our temporary file.
                tempFile.delete();
            }
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
                BrutIO.copy(inputFile, outputFile, entry);
            }

            outputFile.closeEntry();
        }
    }

    private void copyUnknownFiles(File appDir, ZipOutputStream outputFile, Map<String, String> files)
            throws IOException {
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
            LogHelper.fine(String.format("Copying unknown file %s with method %d", unknownFileInfo.getKey(), method));
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

            BrutIO.copy(inputFile, outputFile);
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
                    String entryName = entry.getName();

                    if (entry.isDirectory()
                            || entryName.equals("classes.dex")
                            || entryName.equals("resources.arsc")
                            ) {
                        continue;
                    }

                    /**
                     * 过滤签名文件
                     */
                    if (entryName.replaceFirst("META-INF[/\\\\]+[^/\\\\]+\\.(SF|RSA)", "").isEmpty()) {
                        continue;
                    }

                    /**
                     * 过滤dex文件
                     */
                    if (DexMaps.containsKey(entryName)) {
                        continue;
                    }

                    String decodeMapFileName = getDecodeFileMapName(entryName);
                    File resFile = new File(outDir, decodeMapFileName);
                    if (resFile.exists()) {
                        //已经编码过的文件,从未知表中移除
                        mResUnknownFiles.getUnknownFiles().remove(entryName);
                        File needDeleteFile = new File(unknownOut, entryName);
                        if (needDeleteFile.exists()) {
                            //已编码文件在未知目录中,需要删除
                            needDeleteFile.delete();
                        }
                    } else {
                        File unFile = new File(unknownOut, entryName);
                        if (unFile.exists()) {
                            //未知文件已经存在
                            continue;
                        }

                        //不存在,解压文件
                        unFile.getParentFile().mkdirs();
                        try (
                                FileOutputStream fos = new FileOutputStream(unFile);
                                InputStream is = zipFile.getInputStream(entry)
                        ){
                            IOUtils.copy(is, fos);
                        }
                        mResUnknownFiles.addUnknownFileInfo(entryName, String.valueOf(entry.getMethod()));
                    }
                }
            }

            if (unknownOut.exists()) {
                //删除空目录
                Files.walkFileTree(Paths.get(unknownOut.getAbsolutePath()), new SimpleFileVisitor<Path>(){
                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        try {
                            Files.deleteIfExists(dir);
                        } catch (Exception e){
                            // ignore exception
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final String DecodeFileMapsMetaName = "DecodeFileMaps";
    public static Map<String, String> DecodeFileMaps = new LinkedHashMap<>();
    public static final String DexMapsMetaName = "DexMaps";
    public static Map<String, String> DexMaps = new LinkedHashMap<>();

    @Before("execution(* brut.androlib.Androlib.writeMetaFile(..))" +
            "&& args(mOutDir, meta)")
    public void writeMetaFile(File mOutDir, Map<String, Object> meta) {
        meta.put(DecodeFileMapsMetaName, DecodeFileMaps);
        meta.put(DexMapsMetaName, DexMaps);
    }

    @AfterReturning(pointcut = "execution(* brut.androlib.Androlib.readMetaFile(..))", returning = "meta")
    public void readMetaFile(Map<String, Object> meta) {
        if (meta.get(DecodeFileMapsMetaName) != null) {
            DecodeFileMaps = (Map<String, String>) meta.get(DecodeFileMapsMetaName);
        }
        if (meta.get(DexMapsMetaName) != null) {
            DexMaps = (Map<String, String>) meta.get(DexMapsMetaName);
        }
    }

    public String getDecodeFileMapName(String name) {
        String mapName = DecodeFileMaps.get(name);
        if (mapName == null) {
            mapName = name;
        }
        return mapName;
    }
}
