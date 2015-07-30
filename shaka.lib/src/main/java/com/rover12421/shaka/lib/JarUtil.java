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

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rover12421 on 7/22/15.
 */
public class JarUtil {
    public static void getResourceAsFile(String resourcePath, String desPath) throws IOException {
        File parentFile = new File(desPath).getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try (
                InputStream in = Class.class.getResourceAsStream(resourcePath);
                FileOutputStream fos = new FileOutputStream(desPath)
                ) {
            IOUtils.copy(in, fos);
        }
    }
}
