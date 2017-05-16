package com.jason.test.testapp.java.test;

/**
 * Created by jasonbook on 2017/5/16.
 */

public class JavaTest {

    public static void main(String[] params) {
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        String s4 = new String("abc");
        String s5 = new String(s1);

        System.out.println("s1==s2:" + (s1 == s2));
        System.out.println("s1==s3:" + (s1 == s3));
        System.out.println("s3==s4:" + (s3 == s4));
        System.out.println("s1==s5:" + (s1 == s5));

        System.out.println("s1 equals s2:" + (s1.equals(s2)));


        Object obj = new Object();
        obj.equals("");
    }
}
