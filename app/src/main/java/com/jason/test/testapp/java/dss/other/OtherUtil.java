package com.jason.test.testapp.java.dss.other;

import com.jason.test.testapp.utils.LogUtil;

import java.util.ArrayList;

/**
 * Created by JiaBo on 2017/6/7.
 */

public class OtherUtil {

    public static void main(String[] args) {
        String result = "";
//        primeTest();
//        fn(15);
//        method(90);
//        factor(36);
//        result = sb.toString();
//        result = "" + minCommonMultiple(27, 22);
        result = "" + maxCommonDivisor2(28, 21);
        LogUtil.print(result);
    }

    /**
     * @param num
     */
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


    /**
     * 分解质因数
     */
    private static ArrayList<String> list = new ArrayList<String>();//存储质数
    private static StringBuffer sb = new StringBuffer();

    public static void method(int x) {
        if (!isZhiShu(x)) {//判断 如果不是指数继续分解，不是的话直接结束
            for (int i = 2; i <= x / 2; i++) {
                if (x % i == 0) {
                    list.add(i + "");
                    method(x / i);//使用递归
                    break;
                }
            }
        } else {
            list.add(x + "");
        }
    }


    static void factor(int number) {
        processList(list);
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
//                sb.append(i + "---");
                System.out.print(i + "---");
                //判断number/i是不是素数，如果是素数就直接输出
                if (isPrime(number / i)) {
//                    sb.append(number / i + "===");
                    System.out.print(number / i + "===");
                } else {
                    factor(number / i);
                }
                return; //或者break
            }
        }
    }

    public static void processList(ArrayList<String> list) {
        if (list == null) {
            list = new ArrayList<>();
            return;
        }
        list.clear();
        if (sb == null) {
            sb = new StringBuffer();
            return;
        }
        sb.delete(0, sb.length());
    }

    public static String processResult(ArrayList<String> list) {
        if (list == null) {
            return "";
        }
        LogUtil.print("list size =" + list.size());
        StringBuffer sb = new StringBuffer();
        for (String i : list) {
            sb.append(i + "*");
        }
        String result = sb.toString();
        if (result.endsWith("*")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    /**
     * 将判断是否为质数
     */
    public static boolean isZhiShu(int f) {
        for (int i = 2; i <= f / 2; i++) {
            if (f % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 递归法求最大公约数
    public static int maxCommonDivisor(int m, int n) {
        if (m < n) {// 保证m>n,若m<n,则进行数据交换
            int temp = m;
            m = n;
            n = temp;
        }
        if (m % n == 0) {// 若余数为0,返回最大公约数
            return n;
        } else { // 否则,进行递归,把n赋给m,把余数赋给n
            return maxCommonDivisor(n, m % n);
        }
    }

    // 循环法求最大公约数
    public static int maxCommonDivisor2(int m, int n) {
        if (m < n) {// 保证m>n,若m<n,则进行数据交换
            int temp = m;
            m = n;
            n = temp;
        }
        while (m % n != 0) {// 在余数不能为0时,进行循环
            int temp = m % n;
            m = n;
            n = temp;
        }
        return n;// 返回最大公约数
    }

    // 求最小公倍数
    public static int minCommonMultiple(int m, int n) {
        return m * n / maxCommonDivisor(m, n);
    }
}
