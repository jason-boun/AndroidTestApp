package com.jason.test.testapp.java.access.packtwo;

import com.jason.test.testapp.utils.LogUtil;

/**
 * Created by jasonbook on 2017/5/16.
 */

class TestThree {

    public TestThree() {
        LogUtil.printSystemInfo("TestThree()");
    }

    public static void main(String[] params) {

        TestTwo tt = new TestTwo();

        tt.publicMethod();

        tt.defaultMethod();

        tt.protectedMethod();
    }
}
