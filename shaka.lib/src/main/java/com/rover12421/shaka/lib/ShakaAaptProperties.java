package com.rover12421.shaka.lib;

/**
 * Created by rover12421 on 7/22/15.
 */
public class ShakaAaptProperties extends AbsProperties {

    private final static ShakaAaptProperties shakaAaptProperties = new ShakaAaptProperties();

    private ShakaAaptProperties() {
    }

    @Override
    protected String getPropertiesPath() {
        return "/properties/ShakaAapt.properties";
    }

    public static long getFileCrc32(String filePath) {
        String covertFilePath = filePath.replaceAll("/|\\\\", ".");
        for (Object key : shakaAaptProperties.getProps().keySet()) {
            if (covertFilePath.endsWith(key.toString())) {
                String value = (String) shakaAaptProperties.getProps().get(key);
                if (value != null) {
                    return Long.parseLong(value, 16);
                }
            }
        }

        return -1;
    }
}
