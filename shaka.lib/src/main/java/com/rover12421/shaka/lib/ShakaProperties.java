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

public class ShakaProperties extends AbsProperties {

    private static final ShakaProperties shakaProperties = new ShakaProperties();

    private ShakaProperties() {
    }

    public static boolean isDebug() {
        String str = shakaProperties.get("debug");
        return str != null && str.equalsIgnoreCase("true");
    }


    public static String getVersion() {
        return shakaProperties.get("version");
    }

    @Override
    protected String getPropertiesPath() {
        return "/properties/shaka.properties";
    }
}