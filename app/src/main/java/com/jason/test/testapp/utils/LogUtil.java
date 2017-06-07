package com.jason.test.testapp.utils;

/**
 * Created by JiaBo on 2017/5/17.
 */

public class LogUtil {

    public static final String tag = "LogUtil";

    public static void print(String tag, String info) {
        System.out.println(tag + info);
    }

    public static void print(String info) {
        print(tag, info);
    }
}
