package com.jason.test.testapp.utils;

import android.content.Context;

import java.text.DecimalFormat;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 16/12/08
 *     desc  : Utils初始化相关
 * </pre>
 */
public final class Utils {

    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }


    /**
     * 把double（四舍五入）转换为两位小数显示
     */
    public final static String formatDoubleForStr(double d) {
        double dou = (double) Math.round(d * 100) / 100;
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(dou);
    }

    /**
     * 把double（四舍五入）转换为两位小数显示
     */
    public static double formatDouble(double d) {
        return Double.parseDouble(formatDoubleForStr(d));
    }

    public static double sqrt(double a, double b) {
        double one = Math.abs(formatDouble(a));
        double two = Math.abs(formatDouble(b));
        return Math.sqrt(one * one + two * two);
    }

    public static void main(String[] params) {
        String result = formatDoubleForStr(sqrt(2.252345234,4.1345542134));
        LogUtil.print(result);
    }
}