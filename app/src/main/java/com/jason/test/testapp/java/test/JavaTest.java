package com.jason.test.testapp.java.test;

import com.jason.test.testapp.data.bean.TestBean;
import com.jason.test.testapp.utils.JsonUtil;
import com.jason.test.testapp.utils.LogUtil;

import org.json.JSONObject;

import java.util.WeakHashMap;

/**
 * Created by jasonbook on 2017/5/16.
 */

public class JavaTest {

    public static void main(String[] params) {
//        StringObject();
        TestBean testBean = new TestBean();
        testBean.setData(5);
        testBean.setAge(11);
        testBean.setGrade(5.63);
        String jsonStr = JsonUtil.toJson(testBean);
        LogUtil.print(jsonStr);
        jsonConvert(jsonStr);

    }

    private static void StringObject() {
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

    public static <T> void jsonConvert(String newValue) {
        try {
            JSONObject dataJson = new JSONObject(newValue);
            Integer age = (Integer) dataJson.opt("age");
            Double gradle = (Double) dataJson.opt("gradle");
            LogUtil.print(age.toString());
            LogUtil.print(gradle.toString());
//            T temp = (T) dataJson.opt("data");
        } catch (Exception e) {
            LogUtil.print("Error" + e.getMessage());
        }

    }

    public void testMethod() {
        WeakHashMap<String, Object> weakHashMap = new WeakHashMap<>();
    }


}
