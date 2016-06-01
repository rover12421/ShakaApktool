package com.rover12421.shaka.apktool.lib;

import brut.androlib.res.data.value.ResStyleValue;

/**
 * Created by rover12421 on 12/5/15.
 */
privileged public aspect ResStyleValue0 {
    /**
     * 返回值需要调用的时候强转成 Duo<ResReferenceValue, ResScalarValue>[]
     * aspectj 1.8.4 以后,写成Duo<ResReferenceValue, ResScalarValue>[]编译失败
     * @return
     */
    public Object ResStyleValue.getItems() {
        return this.mItems;
    }
}
