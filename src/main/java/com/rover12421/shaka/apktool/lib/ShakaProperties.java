package com.rover12421.shaka.apktool.lib;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class ShakaProperties {
    public static String get(String key) {
        return get().getProperty(key);
    }

    public static Properties get() {
        if (sProps == null) {
            loadProps();
        }
        return sProps;
    }

    private static void loadProps() {
        InputStream in = ShakaProperties.class.getResourceAsStream("/properties/shaka.properties");
        sProps = new Properties();
        try {
            sProps.load(in);
            in.close();
        } catch (IOException ex) {
            LOGGER.warning("Can't load properties.");
        }

    }

    private static Properties sProps;

    private static final Logger LOGGER = Logger
            .getLogger(ShakaProperties.class.getName());

    public static boolean isDebug() {
        String str = get("debug");
        if (str != null && str.equalsIgnoreCase("true")) {
            return true;
        } else {
            return false;
        }
    }

    public static String getVersion() {
        return get("version");
    }
}