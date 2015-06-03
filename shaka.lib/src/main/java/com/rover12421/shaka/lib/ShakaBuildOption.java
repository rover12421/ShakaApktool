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
 * Created by rover12421 on 3/30/15.
 */
public class ShakaBuildOption {
    private static ShakaBuildOption option = new ShakaBuildOption();

    public static ShakaBuildOption getInstance() {
        return option;
    }

    private ShakaBuildOption() {
    }

    // 使用默然的appt
    private boolean usingDefaultAapt = false;

    // 指定aapt路径
    private String aaptPath = null;

    public boolean isUsingDefaultAapt() {
        return usingDefaultAapt;
    }

    public void setUsingDefaultAapt(boolean usingDefaultAapt) {
        this.usingDefaultAapt = usingDefaultAapt;
    }

    public String getAaptPath() {
        return aaptPath;
    }

    public void setAaptPath(String aaptPath) {
        this.aaptPath = aaptPath;
    }
}
