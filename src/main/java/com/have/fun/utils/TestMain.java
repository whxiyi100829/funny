package com.have.fun.utils;

/**
 * <pre>
 * User: hzwangxx Date: 14/12/22 Time: 21:52
 * </pre>
 */
public class TestMain {
    public static void main(String[] args) {
        System.out.println("tests");
        System.out.println(DynamicConfig.getStr("base.resource.path"));
        System.out.println(ClassLoader.getSystemResource(FunnyConstants.ITEM_FILE));
        System.out.println(System.getProperty("user.dir"));
    }
}
