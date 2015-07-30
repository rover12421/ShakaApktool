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
package com.rover12421.shaka.lib.multiLanguage;

import com.rover12421.shaka.lib.ShakaDecodeOption;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by rover12421 on 2/9/15.
 */
public class MultiLanguageSupport {
    private Locale locale = Locale.getDefault();
    private final Properties properties = new Properties();

    private static final MultiLanguageSupport multiLanguageSupport = new MultiLanguageSupport();

    private MultiLanguageSupport() {
        loadLang();
    }

    private void loadLang() {
        String langFile = getLangFile();
        InputStream is = getClass().getClassLoader().getResourceAsStream(langFile);
        if (is != null) {
            try {
                properties.clear();
                properties.load(new InputStreamReader(is, "UTF-8"));
            } catch (IOException e) {
//            e.printStackTrace();
            }
        }
    }

    public Locale getLocale() {
        return locale;
    }

    public static MultiLanguageSupport getInstance() {
        return multiLanguageSupport;
    }

    public void setLang(Locale locale) {
        if (locale != null && !this.locale.equals(locale)) {
            this.locale = locale;
            loadLang();
        }
    }

    private String getLangFile() {
        return getLangFile(locale.toString());
    }

    private static String getLangFile(String lang) {
        return "lang/" + lang + ".lng";
    }

    public String get(int id, String def) {
        return properties.getProperty(Integer.toString(id), def);
    }

    public String get(String key, String def) {
        return properties.getProperty(key, def);
    }

    private static int MINLEN = Integer.MAX_VALUE;

    private static final Map<String, Integer> langStrMap = new TreeMap<>(new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            //按字符串长度从大到小排序
            if (o2.length() == o1.length()) {
                return o2.compareTo(o1);
            }
            return o2.length() - o1.length();
        }
    });
    static {
        String langFile = getLangFile("en_US");
        try (
                InputStream is = MultiLanguageSupport.class.getClassLoader().getResourceAsStream(langFile)
                ) {
            if (is != null) {
                Properties enProps = new Properties();
                enProps.load(is);
                for (int i=0; i<enProps.size(); i++) {
                    String key = Integer.toString(i);
                    String val = (String) enProps.get(key);
                    if (val != null) {
                        langStrMap.put(val, i);
                        if (val.length() < MINLEN) {
                            MINLEN = val.length();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String[] langRpStr = new String[]{
            "Copying (.+?) file\\.\\.\\.",
            "Smaling (.+?) folder into (.+?)\\.\\.\\.",
            "Baksmaling (.+?)\\.\\.\\.",
            "Using Apktool (.+?) on (.+?)",
            "Renamed manifest package found! Replacing (.+?) with (.+?)",
            "Arsc file contains multiple packages\\. Using package (.+?) as default",
            "Using Apktool (.+?)",
            "^Copying unknown file (.+?) with method (.+?)$",
            "Copying raw (.+?) file\\.\\.\\."
    };

    private static final String[] langRpStrDef = new String[]{
            "Copying $1 file...",
            "Smaling $1 folder into $2...",
            "Baksmaling $1...",
            "Using Apktool $1 on $2",
            "Renamed manifest package found! Replacing $1 with $2",
            "Arsc file contains multiple packages. Using package $1 as default",
            "Using Apktool $1",
            "Copying unknown file $1 with method $2",
            "Copying raw $1 file..."
    };

    public static String covertLocaleInfo(String str) {
        if (str == null) {
            return str;
        }

        //特殊罗辑处理
        if (ShakaDecodeOption.getInstance().isIgnoreResDecodeError()
                && str.contains("replacing by FALSE value")) {
            str = str.replaceAll("replacing by FALSE value", "ignore");
        }

        if (str.length() < MINLEN) {
            return str;
        }

        MultiLanguageSupport languageSupport = MultiLanguageSupport.getInstance();
        for (String key : langStrMap.keySet()) {
            if (str.contains(key)) {
                String val = languageSupport.get(langStrMap.get(key), key);
                if (key.equals(val)) continue;
                str = str.replace(key, val);
            }
        }

        for (int i=0; i<langRpStr.length; i++) {
            try {
                String key = langRpStr[i];
                String val = languageSupport.get("r" + i, langRpStrDef[i]);

                if (key.equals(val)) continue;
                str = str.replaceFirst(key, val);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return str;
    }
}
