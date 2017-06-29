package com.jason.test.testapp.java.recursion;

/**
 * ps: 只为讲解递归,所以并不是最优的算法，望牛牛们勿喷!
 *
 * @Description: [递归方式求解斐波那契数列]
 * @Author: [胖虎]
 * @CreateDate: [2014-3-31 下午11:04:29]
 * @CsdnUrl: [http://blog.csdn.net/ljphhj]
 */
public class FibonacciSequence {

    public static int fibonacci(int n) {
        /*递归结束条件*/
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        /*递归调用*/
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(FibonacciSequence.fibonacci(7));
    }
}
