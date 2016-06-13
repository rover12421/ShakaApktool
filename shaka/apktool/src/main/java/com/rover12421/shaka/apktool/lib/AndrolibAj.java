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
import brut.androlib.meta.MetaInfo;
import brut.androlib.res.data.ResTable;
import brut.androlib.res.data.ResUnknownFiles;
import brut.androlib.res.util.ExtFile;
import brut.androlib.src.SmaliDecoder;
import brut.apktool.Main;
import brut.directory.Directory;
import brut.directory.DirectoryException;
import brut.directory.FileDirectory;
import brut.util.OS;
import com.rover12421.shaka.lib.AndroidZip;
import com.rover12421.shaka.lib.LogHelper;
import com.rover12421.shaka.lib.ShakaIO;
import com.rover12421.shaka.lib.ShakaProperties;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.*;
import java.nio.file.*;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.zip.CRC32;

/**
 * Created by rover12421 on 8/9/14.
 */
@Aspect
public class AndrolibAj {
    public static MetaInfo metaInfo = new MetaInfo();

    public static final String UNK_DIRNAME = "unknown";
    private final static String SMALI_DIRNAME = "smali";
    private final static String APK_DIRNAME = "build/apk";

    /**
     * 未知文件处理,在编译未知文件之前,重新扫描一次"unknown"目录
     */
    @Before("execution(void brut.androlib.Androlib.buildUnknownFiles(..))" +
            "&& args(appDir, outFile, meta)")
    public void buildUnknownFiles_before(JoinPoint joinPoint, File appDir, File outFile, MetaInfo meta) {
        Androlib thiz = (Androlib) joinPoint.getThis();
        Map<String, String> files = meta.unknownFiles;
        if (files == null) {
            ResUnknownFiles mResUnknownFiles = thiz.getResUnknownFiles();
            files = mResUnknownFiles.getUnknownFiles();
            meta.unknownFiles = files;
        }

        if (AndrolibResourcesAj.doNotCompress != null) {
            for (String file : AndrolibResourcesAj.doNotCompress) {
                String pFile = file.replace("\\", "/");
                pFile = getDecodeFileMapName(pFile);
                files.put(pFile, String.valueOf(ZipArchiveEntry.STORED));
            }
        }

        try {
            File unknownFile = new File(appDir, UNK_DIRNAME);
            if (!unknownFile.exists()) {
                return;
            }

            try {
                Directory directory = new FileDirectory(unknownFile);
                Set<String> addFiles = directory.getFiles(true);

                for (String file : addFiles) {
                    //判断下.是否已经存在.已经存在的,压缩模式使用原包的模式
                    file = file.replace("\\", "/");
                    if (!files.containsKey(file)) {
                        files.put(file, AndroidZip.getZipMethod(new File(appDir, file).getAbsolutePath()) + "");
                    }
                }
            } catch (DirectoryException e) {
                e.printStackTrace();
            }
        } catch (Throwable e) {
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
            "&& args(apkFile, outDir, filename, bakdeb, api)")
    public void decodeSourcesSmali_around(ProceedingJoinPoint joinPoint,
                                          File apkFile, File outDir, String filename, boolean bakdeb, int api) throws Throwable {

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
            SmaliDecoder.decode(apkFile, smaliDir, filename, bakdeb, api);
            //没有异常才添加
            if (mapDirName != null) {
                metaInfo.addDexMap(filename, mapDirName);
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
                if (file.isDirectory() &&
                        metaInfo.getDexMap(name) == null &&
                        name.startsWith("smali_")) {
                    String key = name.substring("smali_".length()) + ".dex";
                    metaInfo.addDexMap(key, name);
                }
            }
        }

        if (metaInfo.dexMaps == null) return;
        for (String dex : metaInfo.dexMaps.keySet()) {
            try {
                Androlib androlib = (Androlib) joinPoint.getThis();
                File dexFile = new File(appDir, APK_DIRNAME + "/" + dex);
                dexFile.getParentFile().mkdirs();
                androlib.buildSourcesSmali(appDir, metaInfo.getDexMap(dex), dex);
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
    public void buildUnknownFiles_around(File appDir, File outFile, MetaInfo meta)
            throws Throwable {
        if (meta.unknownFiles != null) {
            LogHelper.info("Copying unknown files/dir...");

            Map<String, String> files = meta.unknownFiles;
            File tempFile = File.createTempFile("buildUnknownFiles", "tmp", outFile.getParentFile());
            tempFile.delete();
            boolean renamed = outFile.renameTo(tempFile);
            if(!renamed) {
                throw new AndrolibException("Unable to rename temporary file");
            }

            ZipFile inputFile = new ZipFile(tempFile);
            try (
                    ZipArchiveOutputStream actualOutput = new ZipArchiveOutputStream(outFile)
            ) {
                copyExistingFiles(inputFile, actualOutput, files);
                copyUnknownFiles(appDir, actualOutput, files);
            } catch (IOException ex) {
                throw new AndrolibException(ex);
            } finally {
                inputFile.close();
                // Remove our temporary file.
                tempFile.delete();
            }
        }
    }

    private void copyExistingFiles(ZipFile inputFile, ZipArchiveOutputStream outputFile, Map<String, String> excludeFiles) throws IOException {
        // First, copy the contents from the existing outFile:
        Enumeration<? extends ZipArchiveEntry> entries = inputFile.getEntries();
        while (entries.hasMoreElements()) {
            ZipArchiveEntry oldEntry = entries.nextElement();
            ZipArchiveEntry entry = new ZipArchiveEntry(oldEntry);
            if (excludeFiles.get(entry.getName()) != null) {
                //排除的文件不处理.
                continue;
            }
            // We can't reuse the compressed size because it depends on compression sizes.
            entry.setCompressedSize(-1);
            outputFile.putArchiveEntry(entry);

            // No need to create directory entries in the final apk
            if (!entry.isDirectory()) {
                ShakaIO.copy(inputFile, outputFile, oldEntry);
            }

            outputFile.closeArchiveEntry();
        }
    }

    private void copyUnknownFiles(File appDir, ZipArchiveOutputStream outputFile, Map<String, String> files)
            throws IOException {
        File unknownFileDir = new File(appDir, UNK_DIRNAME);
        File buildDir = new File(appDir, "build/apk");

        // loop through unknown files
        for (Map.Entry<String,String> unknownFileInfo : files.entrySet()) {
            String file = unknownFileInfo.getKey();
            file = getDecodeFileMapName(file);
            File inputFile = new File(unknownFileDir, file);
            if(inputFile.isDirectory()) {
                continue;
            }

            if (!inputFile.exists()) {
                inputFile = new File(buildDir, file);
                if (!inputFile.exists()) {
                    inputFile = new File(appDir, file);
//                    if (inputFile.exists()) {
//                        LogHelper.info("Find unkown file in app dir : " + file);
//                    }
//                } else {
//                    LogHelper.info("Find unkown file in build dir : " + file);
                }
            }

            if (!inputFile.exists()) {
                LogHelper.info("Not found unkown file : " + file);
                continue;
            }

            ZipArchiveEntry newEntry = new ZipArchiveEntry(unknownFileInfo.getKey());
            int method = Integer.valueOf(unknownFileInfo.getValue());
            LogHelper.fine(String.format("Copying unknown file %s with method %d", unknownFileInfo.getKey(), method));
            if(method == ZipArchiveEntry.STORED) {
                newEntry.setMethod(ZipArchiveEntry.STORED);
                newEntry.setSize(inputFile.length());
                newEntry.setCompressedSize(-1);
                BufferedInputStream unknownFile = new BufferedInputStream(new FileInputStream(inputFile));
                CRC32 crc = calculateCrc(unknownFile);
                newEntry.setCrc(crc.getValue());

//                LogHelper.getLogger().fine("\tsize: " + newEntry.getSize());
            } else {
                newEntry.setMethod(ZipArchiveEntry.DEFLATED);
            }
            outputFile.putArchiveEntry(newEntry);

            ShakaIO.copy(inputFile, outputFile);
            outputFile.closeArchiveEntry();
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
            Androlib thiz = (Androlib) joinPoint.getThis();
            File unknownOut = new File(outDir, UNK_DIRNAME);
            ResUnknownFiles mResUnknownFiles = thiz.getResUnknownFiles();

            Directory unk = apkFile.getDirectory();
            // loop all items in container recursively, ignoring any that are pre-defined by aapt
            Set<String> files = unk.getFiles(true);
            for (String file : files) {
                if (
                        file.equals("classes.dex")
                        || file.equals("resources.arsc")) {
                    continue;
                }

                /**
                 * 过滤签名文件
                 */
                if (file.replaceFirst("META-INF[/\\\\]+[^/\\\\]+\\.(SF|RSA|DSA)", "").isEmpty()) {
                    continue;
                }

                /**
                 * 过滤dex文件
                 */
                if (metaInfo.getDexMap(file) != null) {
                    continue;
                }

                String decodeMapFileName = getDecodeFileMapName(file);
                File resFile = new File(outDir, decodeMapFileName);
                if (resFile.exists()) {
                    //已经编码过的文件,从未知表中移除
                    mResUnknownFiles.getUnknownFiles().remove(file);
                    File needDeleteFile = new File(unknownOut, file);
                    if (needDeleteFile.exists()) {
                        //已编码文件在未知目录中,需要删除
                        needDeleteFile.delete();
                    }
                } else {
                    File unFile = new File(unknownOut, file);
                    if (unFile.exists()) {
                        //未知文件已经存在
                        continue;
                    }

                    //不存在,解压文件
                    // copy file out of archive into special "unknown" folder
                    unk.copyToDir(unknownOut, file);
                    // lets record the name of the file, and its compression type
                    // so that we may re-include it the same way
                    mResUnknownFiles.addUnknownFileInfo(file, String.valueOf(unk.getCompressionLevel(file)));
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

//    public static final String DecodeFileMapsMetaName = "DecodeFileMaps";
//    public static Map<String, String> DecodeFileMaps = new LinkedHashMap<>();
//    public static final String DexMapsMetaName = "DexMaps";
//    public static Map<String, String> DexMaps = new LinkedHashMap<>();

    @Before("execution(* brut.androlib.Androlib.writeMetaFile(..))" +
            "&& args(mOutDir, meta)")
    public void writeMetaFile(File mOutDir, MetaInfo meta) {
        meta.dexMaps = metaInfo.dexMaps;
        meta.decodeFileMaps = metaInfo.decodeFileMaps;
        meta.shakaVer = ShakaProperties.getVersion();
    }

//    @AfterReturning(pointcut = "execution(* brut.androlib.Androlib.readMetaFile(..))", returning = "meta")
//    public void readMetaFile(Map<String, Object> meta) {
//        if (meta.get(DecodeFileMapsMetaName) != null) {
//            DecodeFileMaps = (Map<String, String>) meta.get(DecodeFileMapsMetaName);
//        }
//        if (meta.get(DexMapsMetaName) != null) {
//            DexMaps = (Map<String, String>) meta.get(DexMapsMetaName);
//        }
//    }

    @Around("execution(* brut.androlib.Androlib.readMetaFile(..))")
    public MetaInfo readMetaFile(ProceedingJoinPoint joinPoint) throws Throwable {
        metaInfo = (MetaInfo) joinPoint.proceed(joinPoint.getArgs());
        return metaInfo;
    }

    public String getDecodeFileMapName(String name) {
        String mapName = metaInfo.getDecodeFileMap(name);
        if (mapName == null) {
            mapName = name;
        }
        return mapName;
    }

    /**
     * 针对的是指定文件,而不使用扩展名
     * @param apkFile
     * @param uncompressedFiles
     * @throws AndrolibException
     */
    @Around("execution(* brut.androlib.Androlib.recordUncompressedFiles(..))" +
            "&& args(apkFile, uncompressedFiles)")
    public void recordUncompressedFiles(ExtFile apkFile, Collection<String> uncompressedFiles) throws AndrolibException {
        try {
            Directory unk = apkFile.getDirectory();
            Set<String> files = unk.getFiles(true);

            for (String file : files) {
                if (unk.getCompressionLevel(file) == ZipArchiveEntry.STORED //原本是存储模式
                        && AndroidZip.needCompress(file)   // Android 默然情况下是需要压缩的
                        ) {
                    //只有想冲突的时候才需要记录
                    uncompressedFiles.add(file);
                }
            }
        } catch (DirectoryException ex) {
            throw new AndrolibException(ex);
        }
    }

    @After("execution(* brut.androlib.Androlib.decodeResourcesFull(..))" +
            "&& args(apkFile, outDir, resTable)")
    public void decodeResourcesFull(JoinPoint joinPoint, ExtFile apkFile, File outDir, ResTable resTable) {
        if (ResResSpecAj.RenameResResSpec) {
            //只要有重命名资源,就重新反编译 AndroidManifest.xml, 其实只要和 `decodeManifestWithResources`换下执行顺序即可.
            Androlib thiz = (Androlib) joinPoint.getThis();
            try {
                thiz.decodeManifestWithResources(apkFile, outDir, resTable);
            } catch (AndrolibException e) {
                e.printStackTrace();
            }
        }
    }
}
