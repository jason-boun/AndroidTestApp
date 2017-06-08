package com.jason.test.testapp.java.dss.other;

import com.jason.test.testapp.utils.LogUtil;

import java.util.ArrayList;

/**
 * Created by JiaBo on 2017/6/7.
 */

public class OtherUtil {

    public static void main(String[] args) {
//        primeTest();
        fn(15);
    }


    public static void fn(int num) {
        int start = 1;
        int end = 0;
        int sum = 0;
        while (start < num / 2 + 1) {
            if (sum == num) {
                show(num, start, end);
                sum = sum - (start++);
            } else if (sum > num) {
                sum = sum - (start++);
            } else {
                sum = sum + (++end);
            }
        }
    }

    public static void show(int n, int s, int b) {
        String str = " ";
        for (int i = s; i <= b; i++) {
            str += i + "+";
        }

        StringBuffer sb = new StringBuffer(str);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("=" + n);
        System.out.println(sb.toString().trim());
    }

    /**
     * 寻找给定数字范围内的素数测试
     */
    public static void primeTest() {
        ArrayList<Integer> primes = primeNum(100);
        LogUtil.print("size=" + primes.size());
        for (int i : primes) {
            LogUtil.print(i + "");
        }
    }

    /**
     * 寻找给定数字范围内的素数
     *
     * @param max
     * @return
     */
    public static ArrayList<Integer> primeNum(int max) {
        ArrayList<Integer> record = new ArrayList<>();
        for (int i = 1; i <= max; i++) {
            if (isPrime(i)) {
                record.add(i);
            }
        }
        return record;
    }

    /**
     * 判断一个数是否为素数（质数）
     *
     * @param num
     * @return
     */
    public static boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }
        boolean result = true;
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                result = false;
                break;
            }
        }
        return result;
    }
}
