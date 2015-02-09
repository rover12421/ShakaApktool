package com.rover12421.shaka.apktool.lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by rover12421 on 2/9/15.
 */
public class MultiLanguageSupport {
    private Locale locale = Locale.getDefault();
    private Properties properties = new Properties();

    private static MultiLanguageSupport multiLanguageSupport = new MultiLanguageSupport();

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
        if (locale != null && !this.locale.toString().equals(locale)) {
            this.locale = locale;
            loadLang();
        }
    }

    private String getLangFile() {
        return getLangFile(locale.toString());
    }

    private String getLangFile(String lang) {
        return "lang/" + lang + ".lng";
    }

    public String get(int id, String def) {
        return properties.getProperty(Integer.toString(id), def);
    }

    public String get(String key, String def) {
        return properties.getProperty(key, def);
    }
}
