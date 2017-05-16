package com.jason.test.testapp.java.access.packone;

import com.jason.test.testapp.java.access.packtwo.TestTwo;

/**
 * Created by jasonbook on 2017/5/16.
 */

public class TestOne extends TestTwo {

    public static void main(String[] params){
        TestTwo tt = new TestTwo();
        tt.publicMethod();
    }

    public void myMethod(){
        publicMethod();
        protectedMethod();//实质是调用自己的方法
    }
}
