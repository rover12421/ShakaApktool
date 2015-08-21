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

import java.util.zip.ZipEntry;

/**
 * Created by rover12421 on 1/1/14.
 */
public class AndroidZip {
    /**
     * 来源于aapt源代码
     * 位置:
     * /frameworks/base/tools/aapt/Package.cpp
     */
		/* these formats are already compressed, or don't compress well */
    private final static String kNoCompressExt[] = {
            ".jpg", ".jpeg", ".png", ".gif",
            ".wav", ".mp2", ".mp3", ".ogg", ".aac",
            ".mpg", ".mpeg", ".mid", ".midi", ".smf", ".jet",
            ".rtttl", ".imy", ".xmf", ".mp4", ".m4a",
            ".m4v", ".3gp", ".3gpp", ".3g2", ".3gpp2",
            ".amr", ".awb", ".wma", ".wmv"
    };

    /**
     * 是否需要压缩
     * true : 需要压缩
     * false : 不需要压缩
     * @param path
     * @return
     */
    public static boolean needCompress(String path) {
        String minPath = path.toLowerCase();
        for (String ext : kNoCompressExt) {
            if (minPath.endsWith(ext)) {
                return false;
            }
        }

        return true;
    }

    /**
     * zip中的压缩方式
     * 需要压缩为ZipEntry.DEFLATED
     * 不需要压缩为ZipEntry.STORED
     * @param path
     * @return
     */
    public static int getZipMethod(String path) {
        if (needCompress(path)) {
            return ZipEntry.STORED;
        } else {
            return ZipEntry.DEFLATED;
        }
    }

}
