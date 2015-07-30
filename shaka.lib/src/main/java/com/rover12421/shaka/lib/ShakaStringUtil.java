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

/**
 * Created by rover12421 on 5/25/15.
 */
public class ShakaStringUtil {
    public static String escaped(char c) {
        String ret;
        int type = Character.getType(c);
        if (Character.isJavaIdentifierPart(c)
                || ((c >= ' ') && (c < 0x7f))
                || (type >= 20 && type <= 28)   //[20-24]是所有标点PUNCTUATION,[25-28]是所有符号SYMBOL
                ) {
            ret = String.valueOf(c);

            if ((c == '\'') || (c == '\"') || (c == '\\')) {
                ret = "\\" + ret;
            }
        } else {
            switch (c) {
                case '\n':
                    ret = "\\n";
                    break;
                case '\r':
                    ret = "\\r";
                    break;
                case '\t':
                    ret = "\\t";
                    break;
                default:
                    ret = "\\u"
                            + Character.forDigit(c >> 12, 16)
                            + Character.forDigit((c >> 8) & 0x0f, 16)
                            + Character.forDigit((c >> 4) & 0x0f, 16)
                            + Character.forDigit(c & 0x0f, 16);
                    break;
            }
        }

        return ret;
    }

    public static String escaped(String str) throws Throwable {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(escaped(str.charAt(i)));
        }
        return sb.toString();
    }
}
