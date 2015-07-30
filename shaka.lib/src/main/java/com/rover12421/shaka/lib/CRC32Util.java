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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;

/**
 * Created by rover12421 on 7/22/15.
 */
public class CRC32Util {
    public static long getCRC32(InputStream is) throws IOException {
        byte[] buff = new byte[4096];
        CRC32 crc32 = new CRC32();
        int len;
        while ((len = is.read(buff)) != -1) {
            crc32.update(buff, 0, len);
        }

        return crc32.getValue();
    }

    public static long getCRC32(File file) throws IOException {
        try (
                FileInputStream fis = new FileInputStream(file)
                ){
            return getCRC32(fis);
        }
    }
}
