package com.jason.test.testapp.utils;

/**
 * Created by JiaBo on 2017/5/17.
 */

public class LogUtil {

    public static final String tag = "LogUtil";

    public static void printSystemInfo(String tag, String info) {
        System.out.println(tag + "==" + info);
    }

    public static void printSystemInfo(String info) {
        printSystemInfo(tag, info);
    }
}
