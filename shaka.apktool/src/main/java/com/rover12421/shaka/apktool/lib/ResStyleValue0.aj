package com.rover12421.shaka.apktool.lib;

import brut.androlib.res.data.value.ResReferenceValue;
import brut.androlib.res.data.value.ResScalarValue;
import brut.androlib.res.data.value.ResStyleValue;
import brut.util.Duo;

/**
 * Created by rover12421 on 12/5/15.
 */
privileged public aspect ResStyleValue0 {
    public Duo<ResReferenceValue, ResScalarValue>[] ResStyleValue.getItems() {
        return this.mItems;
    }
}
