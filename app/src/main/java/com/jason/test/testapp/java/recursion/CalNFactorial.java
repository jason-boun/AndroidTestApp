package com.jason.test.testapp.java.recursion;

/**
 * @Description: [递归求N阶乘]
 * @Author: [胖虎]
 * @CreateDate: [2014-3-31 下午9:36:20]
 * @CsdnUrl: [http://blog.csdn.net/ljphhj]
 */
public class CalNFactorial {
    public static int f(int n) {
        /*递归结束条件*/
        if (n == 1) {
            return 1;
        }
        /*递归调用*/
        return n * f(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(f(5));
    }
}
