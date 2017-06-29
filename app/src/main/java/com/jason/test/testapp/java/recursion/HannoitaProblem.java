package com.jason.test.testapp.java.recursion;

/**
 * @Description: [递归方式求解汉诺塔问题]
 * @Author: [胖虎]
 * @CreateDate: [2014-4-1 上午12:16:59]
 * @CsdnUrl: [http://blog.csdn.net/ljphhj]
 */
public class HannoitaProblem {

    public static void Hannoita(int n, char A, char B, char C) {
        /*递归结束条件*/
        if (n == 1) {
            System.out.println("n=" + n + " " + A + "-->" + C);
        } else {
            /*递归的调用*/
            Hannoita(n - 1, A, C, B);
            System.out.println("n=" + n + " " + A + "-->" + C);
            Hannoita(n - 1, B, A, C);
        }
    }

    public static void main(String[] args) {
        HannoitaProblem.Hannoita(3, 'A', 'B', 'C');
    }
}
