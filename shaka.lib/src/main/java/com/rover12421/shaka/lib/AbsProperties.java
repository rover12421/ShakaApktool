package com.rover12421.shaka.lib;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by rover12421 on 7/22/15.
 */
public abstract class AbsProperties {
    private Properties sProps;

    public String get(String key) {
        return get().getProperty(key);
    }

    private Properties get() {
        if (sProps == null) {
            loadProps();
        }
        return sProps;
    }

    protected abstract String getPropertiesPath();

    protected Properties getProps() {
        return get();
    }

    private void loadProps() {
        sProps = new Properties();
        try (
                InputStream in = ShakaProperties.class.getResourceAsStream(getPropertiesPath())
                ){
            sProps.load(in);
        } catch (IOException e) {
            LogHelper.warning("Can't load properties.");
        }
    }
}
