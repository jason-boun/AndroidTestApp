package com.jason.test.testapp.java.access.packtwo;

import com.jason.test.testapp.java.access.packone.TestOne;
import com.jason.test.testapp.utils.LogUtil;

/**
 * Created by jasonbook on 2017/5/16.
 */

public class TestTwo extends TestThree{

    public TestTwo() {
        LogUtil.print("TestTwo()");
    }

    public Integer num = 2;

    public static void main(String[] params){
        new TestThree();
        new TestOne();
    }

    public void publicMethod(){
        System.out.println("publicMethod");
    }

    void defaultMethod(){
        System.out.println("defaultMethod");
    }

    protected void protectedMethod(){
        System.out.println("protectedMethod");
    }

    private void privateMethod(){
        System.out.println("privateMethod");
    }
}
