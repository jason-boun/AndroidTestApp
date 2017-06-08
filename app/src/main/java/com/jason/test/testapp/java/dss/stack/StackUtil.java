package com.jason.test.testapp.java.dss.stack;

import com.jason.test.testapp.utils.LogUtil;
import com.jason.test.testapp.utils.StringUtil;

/**
 * Created by JiaBo on 2017/6/7.
 */

public class StackUtil {

    public static void main(String[] args) {
        String str = "asdkfa230912=,.sdfqe";
        LogUtil.print("" + str);
        String temp = strTest(str);
        LogUtil.print("" + temp);
    }

    public static String strTest(String str) {
        if (StringUtil.isBlank(str)) {
            return str;
        }
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
