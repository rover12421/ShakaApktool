package com.rover12421.shaka.apktool.test;

/**
 * Created by rover12421 on 1/5/14.
 */
public class TestClazz {
    private String privateStr = "privateStr";
    private static String privateStaticStr = "privateStaticStr";

    public TestClazz() {
    }

    TestClazz(String str1, String str2) {
        privateStr = str1;
        privateStaticStr = str2;
    }

    private void voidMethod() {
        System.out.println("voidMethod");
    }

    private int intMethod() {
        return 1;
    }

    private static int staticMethod(String ... strs) {
        for (String str : strs) {
            System.out.println(str);
        }

        return strs.length;
    }

    private void arrayArgMethod(String str, String array[]) {
        System.out.println(str);
        System.out.println("array : " + array);
    }

    @Override
    public String toString() {
        return "TestClazz{" +
                "privateStr='" + privateStr + '\'' +
                "privateStaticStr='" + privateStaticStr + '\'' +
                '}';
    }
}
