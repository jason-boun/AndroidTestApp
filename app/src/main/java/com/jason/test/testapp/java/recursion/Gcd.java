package com.jason.test.testapp.java.recursion;

/**
 * @Description: [�ݹ����gcdŷ������㷨]
 * @Author: [�ֻ�]
 * @CreateDate: [2014-3-31 ����10:51:47]
 * @CsdnUrl: [http://blog.csdn.net/ljphhj]
 */
public class Gcd {

    public static int gcd(int m, int n) {
        /*�ݹ��ս�����*/
        if (n == 0) {
            return m;
        }
        /*�ݹ����*/
        return gcd(n, m % n);
    }

    public static void main(String[] args) {
        System.out.println(Gcd.gcd(6, 4));
    }
}
