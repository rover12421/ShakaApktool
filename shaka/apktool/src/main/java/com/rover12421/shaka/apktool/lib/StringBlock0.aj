package com.rover12421.shaka.apktool.lib;

import brut.androlib.res.decoder.StringBlock;

/**
 * Created by rover12421 on 2/1/16.
 */
privileged public aspect StringBlock0 {
    public int[] StringBlock.getStyles() {
        return m_styles;
    }

    public int[] StringBlock.getStyleOffsets() {
        return m_styleOffsets;
    }
}
