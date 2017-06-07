package com.jason.test.testapp.java.access.packone;

import com.jason.test.testapp.java.access.packtwo.TestTwo;
import com.jason.test.testapp.utils.LogUtil;

/**
 * Created by jasonbook on 2017/5/16.
 */

public class TestOne extends TestTwo implements Cloneable{

    public Integer num = 1;

    public TestOne() {
        LogUtil.print("TestOne()");
    }

    public static void main(String[] params) {
        if (true){
            new TestOne();
            return;
        }

        TestTwo tt = new TestTwo();
        System.out.println(tt.num);
        System.out.println(new TestOne().num);

        try {
            TestOne src= new TestOne();
            TestOne dest = (TestOne)src.clone();
            src.num = 100;
            dest.num = 200;

            System.out.println(src.num);
            System.out.println(dest.num);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void myMethod() {
        publicMethod();
        protectedMethod();//实质是调用自己的方法
    }

    public void test(){
        new TestOne();
    }
}
