package com.rover12421.shaka.apktool.util;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by rover12421 on 11/20/15.
 */
@Aspect
public class Global {
    private static String packageName;

    public static String getPackageName() {
        return packageName;
    }

    public static void setPackageName(String packageName) {
        Global.packageName = packageName;
    }

    /************************************************/
    @Before("execution(* brut.androlib.res.data.ResTable.setPackageRenamed(..))" +
            "&& args(pkg)")
    public void setPackageRenamed(String pkg) {
        setPackageName(pkg);
    }

}
