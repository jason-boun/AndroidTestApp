package com.jason.test.testapp.java.access.packtwo;

import com.jason.test.testapp.java.access.packone.TestOne;

/**
 * Created by jasonbook on 2017/5/16.
 */

public class TestTwo {

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
