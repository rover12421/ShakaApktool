package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.androlib.err.UndefinedResObject;
import brut.androlib.res.data.*;
import com.rover12421.shaka.apktool.lib.ShakaDecodeOption;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by rover12421 on 4/21/15.
 */
@Aspect
public class ResPackageAj {

    public ResTable mResTable(ResPackage thiz) throws NoSuchFieldException, IllegalAccessException {
        return (ResTable) ReflectUtil.getFieldValue(thiz, "mResTable");
    }

    @Around("execution(* brut.androlib.res.data.ResPackage.getResSpec(..))" +
            "&& args(resID)")
    public ResResSpec getResSpec(ProceedingJoinPoint joinPoint, ResID resID) throws Throwable {
        try {
            return (ResResSpec) joinPoint.proceed(joinPoint.getArgs());
        } catch (UndefinedResObject e) {
            if (ShakaDecodeOption.getInstance().isFuckUnkownId()) {
                ResPackage thiz = (ResPackage) joinPoint.getThis();
                return new ResResSpec(resID, String.format("[%08x]", resID.id), thiz, new ResType("FuckUnkownId", mResTable(thiz), thiz));
            } else {
                throw e;
            }
        }
    }
}
