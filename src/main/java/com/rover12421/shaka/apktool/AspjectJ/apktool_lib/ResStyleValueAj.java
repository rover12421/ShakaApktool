package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.androlib.res.data.ResResSpec;
import brut.androlib.res.data.ResResource;
import brut.androlib.res.data.value.ResAttr;
import brut.androlib.res.data.value.ResReferenceValue;
import brut.androlib.res.data.value.ResScalarValue;
import brut.androlib.res.data.value.ResValue;
import brut.util.Duo;
import com.rover12421.shaka.apktool.util.ReflectUtil;
import com.rover12421.shaka.apktool.util.ShakaRuntimeException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.xmlpull.v1.XmlSerializer;

/**
 * Created by rover12421 on 8/9/14.
 */
@Aspect
public class ResStyleValueAj {
    /**
     * package brut.androlib.res.data.value
     * public class ResStyleValue extends ResBagValue implements ResValuesXmlSerializable
     * public void serializeToResValuesXml(XmlSerializer serializer,ResResource res)
     */

    @Pointcut("execution(void brut.androlib.res.data.value.ResStyleValue.serializeToResValuesXml(..))" +
            "&& args(serializer, res)")
    private void pointcut_serializeToResValuesXml(XmlSerializer serializer, ResResource res) {}

    @Around("pointcut_serializeToResValuesXml(serializer, res)")
    public void serializeToResValuesXml_around(ProceedingJoinPoint joinPoint, XmlSerializer serializer, ResResource res) {
        try {

            ResReferenceValue mParent = (ResReferenceValue) ReflectUtil.getFieldValue(joinPoint.getThis(), "mParent");
            Duo<ResReferenceValue, ResScalarValue>[] mItems = (Duo<ResReferenceValue, ResScalarValue>[]) ReflectUtil.getFieldValue(joinPoint.getThis(), "mItems");

            serializer.startTag(null, "style");
            serializer.attribute(null, "name", res.getResSpec().getName());
            if (!mParent.isNull()) {
                serializer.attribute(null, "parent", mParent.encodeAsResXmlAttr());
            } else if (res.getResSpec().getName().indexOf('.') != -1) {
                serializer.attribute(null, "parent", "");
            }
            for (int i = 0; i < mItems.length; i++) {
                ResResSpec spec = mItems[i].m1.getReferent();

                // hacky-fix remove bad ReferenceVars
                if (spec.getDefaultResource().getValue().toString()
                        .contains("ResReferenceValue@")) {
                    continue;
                }
                ResValue resValue = spec.getDefaultResource().getValue();
                if (!(resValue instanceof ResAttr)) {
                    //异常跳过
                    continue;
                }
                ResAttr attr = (ResAttr)resValue;
                String value = attr.convertToResXmlFormat(mItems[i].m2);

                if (value == null) {
                    value = mItems[i].m2.encodeAsResXmlValue();
                }

                if (value == null) {
                    continue;
                }

                serializer.startTag(null, "item");
                serializer.attribute(null, "name",
                        spec.getFullName(res.getResSpec().getPackage(), true));
                serializer.text(value);
                serializer.endTag(null, "item");
            }
            serializer.endTag(null, "style");
        } catch (Exception e) {
            throw new ShakaRuntimeException(e);
        }
    }

}
