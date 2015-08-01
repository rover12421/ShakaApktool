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
public class ShakaDecodeOption {
    private static final ShakaDecodeOption option = new ShakaDecodeOption();

    public static ShakaDecodeOption getInstance() {
        return option;
    }

    private ShakaDecodeOption() {
    }

    private boolean no9png = false;
    private boolean usingDefaultFramework = false;
    //显示更多可识别字符
    private boolean showMoreRecognizableCharacters = false;

    private boolean fuckUnkownId = false;
    private boolean ignoreResDecodeError = false;

    private boolean xmlAttributeNameCorrect = false;

    public boolean isNo9png() {
        return no9png;
    }

    public void setNo9png(boolean no9png) {
        this.no9png = no9png;
    }

    public boolean isUsingDefaultFramework() {
        return usingDefaultFramework;
    }

    public void setUsingDefaultFramework(boolean usingDefaultFramework) {
        this.usingDefaultFramework = usingDefaultFramework;
    }

    public boolean isShowMoreRecognizableCharacters() {
        return showMoreRecognizableCharacters;
    }

    public void setShowMoreRecognizableCharacters(boolean showMoreRecognizableCharacters) {
        this.showMoreRecognizableCharacters = showMoreRecognizableCharacters;
    }

    public boolean isFuckUnkownId() {
        return fuckUnkownId;
    }

    public void setFuckUnkownId(boolean fuckUnkownId) {
        this.fuckUnkownId = fuckUnkownId;
    }

    public boolean isIgnoreResDecodeError() {
        return ignoreResDecodeError;
    }

    public void setIgnoreResDecodeError(boolean ignoreResDecodeError) {
        this.ignoreResDecodeError = ignoreResDecodeError;
    }

    public boolean isXmlAttributeNameCorrect() {
        return xmlAttributeNameCorrect;
    }

    public void setXmlAttributeNameCorrect(boolean xmlAttributeNameCorrect) {
        this.xmlAttributeNameCorrect = xmlAttributeNameCorrect;
    }
}
