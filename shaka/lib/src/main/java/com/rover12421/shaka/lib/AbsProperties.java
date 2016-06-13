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
