package com.jason.test.testapp.java.recursion;

/**
 * @Description: [递归求解gcd欧几里德算法]
 * @Author: [胖虎]
 * @CreateDate: [2014-3-31 下午10:51:47]
 * @CsdnUrl: [http://blog.csdn.net/ljphhj]
 */
public class Gcd {

    public static int gcd(int m, int n) {
        /*递归终结条件*/
        if (n == 0) {
            return m;
        }
        /*递归调用*/
        return gcd(n, m % n);
    }

    public static void main(String[] args) {
        System.out.println(Gcd.gcd(24, 56));
    }
}
