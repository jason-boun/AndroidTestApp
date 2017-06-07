package com.jason.test.testapp.utils;

/**
 * Created by JiaBo on 2017/6/7.
 */

public class NumberUtil {

    public static int getRandom(int max) {
        double result = Math.random() * max;
        return (int) Math.floor(result);
    }

    public static int getCeilRandom(int max) {
        double result = Math.random() * max;
        return (int) Math.ceil(result);
    }

    public static int getFloorRandom(int max) {
        double result = Math.random() * max;
        return (int) Math.floor(result);
    }

    public static int getRoundRandom(int max) {
        double result = Math.random() * max;
        return (int) Math.round(result);
    }
}
