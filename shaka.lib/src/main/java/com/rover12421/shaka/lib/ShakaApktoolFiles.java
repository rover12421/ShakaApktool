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
package com.rover12421.shaka.lib;

import java.io.File;
import java.io.IOException;

/**
 * Created by rover12421 on 7/22/15.
 */
public class ShakaApktoolFiles {
    private static String ShakaAaptBinPath;
    private static String ShakaAaptlibPath;
    private static String ShakaAaktoolDir;

    private static String getShakaApktoolDir() throws ShakaException {
        if (ShakaAaktoolDir != null) {
            return ShakaAaktoolDir;
        }
        String path;

        File parentPath = new File(System.getProperty("user.home"));
        if (! parentPath.canWrite()) {
            LogHelper.severe(String.format("WARNING: Could not write to $HOME (%s), using %s instead...",
                    parentPath.getAbsolutePath(), System.getProperty("java.io.tmpdir")));
            LogHelper.severe("Please be aware this is a volatile directory and frameworks could go missing, " +
                    "please utilize --frame-path if the default storage directory is unavailable");

            parentPath = new File(System.getProperty("java.io.tmpdir"));
        }

        if (EnvironmentDetection.isMacOSX()) {
            path = parentPath.getAbsolutePath() + String.format("%1$sLibrary%1$sShakaApktool", File.separatorChar);
        } else {
            path = parentPath.getAbsolutePath() + String.format("%1$sShakaApktool", File.separatorChar);
        }

        File dir = new File(path);

        if (dir.getParentFile() != null && dir.getParentFile().isFile()) {
            LogHelper.severe("Please remove file at " + dir.getParentFile());
            System.exit(1);
        }

        if (! dir.exists()) {
            if (! dir.mkdirs()) {
                throw new ShakaException("Can't create directory: " + dir);
            }
        }

        ShakaAaktoolDir = path;
        return ShakaAaktoolDir;
    }

    private static void checkShakaAapt(String fileStr) throws ShakaException {
        boolean skip = false;
        File file = new File(fileStr);
        if (file.exists()) {
            long crc32 = ShakaAaptProperties.getFileCrc32(fileStr);
            try {
                if (crc32 == -1 || CRC32Util.getCRC32(file) == crc32) {
                    skip = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!skip) {
            String respath = "/ShakaAapt" +
                    file.getAbsolutePath().substring(getShakaApktoolDir().length()).replaceAll("\\\\", "/");
            try {
                JarUtil.getResourceAsFile(respath, fileStr);
            } catch (IOException e) {
                throw new ShakaException(e);
            }
        }
        if (!file.canExecute()) {
            file.setExecutable(true);
        }
    }
    private static void checkShakaAapt() throws ShakaException {
        //检查bin文件
        checkShakaAapt(ShakaAaptBinPath);

        //检查lib库
        if (!EnvironmentDetection.isWindows()) {
            checkShakaAapt(ShakaAaptlibPath);
        }
    }

    public static String getShakaAaptBinPath() throws ShakaException {
        if (ShakaAaptBinPath == null) {
            ShakaAaptBinPath = getShakaApktoolDir() +
                    String.format("%1$s%2$s-%3$s%1$sbin%1$saapt%4$s",
                            File.separatorChar,
                            EnvironmentDetection.getOSNAME(),
                            EnvironmentDetection.getARCH(),
                            EnvironmentDetection.getBINEXT());

            ShakaAaptlibPath = getShakaApktoolDir() +
                    String.format("%1$s%2$s-%3$s%1$s%4$s%1$slibc++%5$s",
                            File.separatorChar,
                            EnvironmentDetection.getOSNAME(),
                            EnvironmentDetection.getARCH(),
                            EnvironmentDetection.getLIBPATHNAME(),
                            EnvironmentDetection.getLIBEXT());

            checkShakaAapt();
        }
        return ShakaAaptBinPath;
    }
}
