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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by rover12421 on 1/5/14.
 * 反射静态库
 */
public class ReflectUtil {

    private static Class[] getObjesType(Object[] objects) {
        Class[] clazzes = new Class[objects.length];
        for (int i = 0; i < clazzes.length; i++) {
            clazzes[i] = objects[i].getClass();
        }
        return clazzes;
    }

    public static Object newInstance(Class<?> clazz, Object ... objects) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = clazz.getDeclaredConstructor(getObjesType(objects));
        constructor.setAccessible(true);
        return constructor.newInstance(objects);
    }

    public static Field getField(Class<?> clazz, String name) throws NoSuchFieldException {

        Class<?> cl = clazz;
        NoSuchFieldException exception = null;
        while (cl != null && !cl.getName().equals(Object.class.getName())) {
            try {
                Field field = cl.getDeclaredField(name);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                if (exception == null) {
                    exception = e;
                }
                cl = cl.getSuperclass();
            }
        }

        throw exception;
    }

    public static Field getField(Object obj, String name) throws NoSuchFieldException {
        return getField(obj.getClass(), name);
    }

    public static void setFieldValue(Object obj, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(obj, name);
        field.set(obj, value);
    }

    /**
     * 设置类中静态字段值
     * @param clazz
     * @param name
     * @param value
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void setFieldValue(Class<?> clazz, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(clazz, name);
        field.set(clazz, value);
    }

    public static Object getFieldValue(Object obj, String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(obj, name);
        return field.get(obj);
    }

    /**
     * 获取类中静态字段的值
     * @param clazz
     * @param name
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getFieldValue(Class<?> clazz, String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(clazz, name);
        return field.get(clazz);
    }

    public static Method getMethod(Class<?> clazz, String name, Class ... classes) throws NoSuchMethodException {

        Class<?> cl = clazz;
        NoSuchMethodException exception = null;
        while (cl != null && !cl.getName().equals(Object.class.getName())) {
            try {
                Method method = cl.getDeclaredMethod(name, classes);
                method.setAccessible(true);
                return method;
            } catch (NoSuchMethodException e) {
                if (exception == null) {
                    exception = e;
                }
                cl = cl.getSuperclass();
            }
        }

        throw exception;
    }

    public static Method getMethod(Class<?> clazz, String name, Object ... objects) throws NoSuchMethodException {
        return getMethod(clazz, name, getObjesType(objects));
    }

    public static Method getMethod(Object obj, String name, Class ... classes) throws NoSuchMethodException {
        return getMethod(obj.getClass(), name, classes);
    }

    public static Method getMethod(Object obj, String name, Object ... objects) throws NoSuchMethodException {
        return getMethod(obj.getClass(), name, objects);
    }

    public static Object invokeMethod(Object obj, String name, Object ... objects) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = getMethod(obj, name, objects);
        return method.invoke(obj, objects);
    }

    public static Object invokeMethod(Class<?> clazz, String name, Object ... objects) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = getMethod(clazz, name, objects);
        return method.invoke(null, objects);
    }
}
