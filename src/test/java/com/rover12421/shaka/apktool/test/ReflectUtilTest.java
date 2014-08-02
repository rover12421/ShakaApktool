package com.rover12421.shaka.apktool.test;

import com.rover12421.shaka.apktool.util.ReflectUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by rover12421 on 1/5/14.
 */
public class ReflectUtilTest extends TestCase {

    @Test
    public void testNewInstanceNoParameters() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestClazz testClazz = (TestClazz) ReflectUtil.newInstance(TestClazz.class);
        System.out.println(testClazz);
    }

    @Test
    public void testNewInstanceWithParameters() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestClazz testClazz = (TestClazz) ReflectUtil.newInstance(TestClazz.class, "aaaa", "bbbb");
        System.out.println(testClazz);
    }

    @Test
    public void testFieldValue() throws NoSuchFieldException, IllegalAccessException {
        TestClazz testClazz = new TestClazz();

        String privateStaticStr = "Test-PrivateStaticStr";
        ReflectUtil.setFieldValue(TestClazz.class, "privateStaticStr", privateStaticStr);
        assertEquals(privateStaticStr, ReflectUtil.getFieldValue(TestClazz.class, "privateStaticStr"));

        privateStaticStr = "Test-PrivateStaticStr-twoTest";
        ReflectUtil.setFieldValue(testClazz, "privateStaticStr", privateStaticStr);
        assertEquals(privateStaticStr, ReflectUtil.getFieldValue(testClazz, "privateStaticStr"));

        String privateStr = "Test-privateStr";
        ReflectUtil.setFieldValue(testClazz, "privateStr", privateStr);
        assertEquals(privateStr, ReflectUtil.getFieldValue(testClazz, "privateStr"));
    }

    @Test
    public void testInvokeMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        System.out.println(ReflectUtil.invokeMethod(TestClazz.class, "staticMethod", new Object[]{new String[]{}}));
        System.out.println(ReflectUtil.invokeMethod(TestClazz.class, "staticMethod", new Object[]{new String[]{"111", "222"}}));

        TestClazz testClazz = new TestClazz();
        System.out.println(ReflectUtil.invokeMethod(testClazz, "staticMethod", new Object[]{new String[]{}}));
        System.out.println(ReflectUtil.invokeMethod(testClazz, "staticMethod", new Object[]{new String[]{"111", "222"}}));

        ReflectUtil.invokeMethod(testClazz, "voidMethod");
        System.out.println(ReflectUtil.invokeMethod(testClazz, "intMethod"));

        ReflectUtil.invokeMethod(testClazz, "arrayArgMethod", "arrayArgMethod-one", new String[]{});
        ReflectUtil.invokeMethod(testClazz, "arrayArgMethod", "arrayArgMethod-two", new String[]{"111", "222", "333"});
    }
}
