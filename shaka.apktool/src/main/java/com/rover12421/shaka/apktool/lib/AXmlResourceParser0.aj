package com.rover12421.shaka.apktool.lib;

import brut.androlib.res.decoder.AXmlResourceParser;
import brut.androlib.res.decoder.StringBlock;

/**
 * Created by rover12421 on 12/5/15.
 */
privileged public aspect AXmlResourceParser0 {
    public int AXmlResourceParser.getAttributeOffset0(int index) {
        return this.getAttributeOffset(index);
    }

    public int[] AXmlResourceParser.getAttributes() {
        return m_attributes;
    }

    public StringBlock AXmlResourceParser.getStrings0() {
        return this.m_strings;
    }
}
