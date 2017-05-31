package com.jason.test.testapp.java.dss;

import com.jason.test.testapp.utils.LogUtil;

/**
 * Created by JiaBo on 2017/5/31.
 */

public class SortUtil {

    public static void main(String[] args) {
        int[] testArr = {19, 32, 1, 56, 2, 8, 5, 134};
//        printArr(testArr);
//        bubbleSort(testArr);
//        selectSort(testArr);
        insertSort(testArr);
        printArr(testArr);
    }

    /**
     * 冒泡排序
     *
     * @param a
     */
    public static void bubbleSort(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        int out, in;
        for (out = a.length - 1; out > 1; out--) {
            for (in = 0; in < out; in++) {
                if (a[in] > a[in + 1]) {
                    swap(a, in, in + 1);
                }
            }
        }
    }

    /**
     * 选择排序
     *
     * @param a
     */
    public static void selectSort(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        int out, in, min;
        for (out = 0; out < a.length - 1; out++) {
            min = out;
            for (in = out + 1; in < a.length; in++) {
                if (a[in] < a[min]) {
                    min = in;
                }
            }
            swap(a, out, min);
        }
    }

    /**
     * 插入排序
     *
     * @param a
     */
    public static void insertSort(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        int out, in;
        for (out = 1; out < a.length; out++) {
            int temp = a[out];
            int index = out;
            for (in = out - 1; in >= 0 && a[in] > temp; in--) {
                a[in + 1] = a[in];
                index = in;
            }
            a[index] = temp;

//            in = out;
//            while (in > 0 && a[in - 1] > temp) {
//                a[in] = a[in - 1];
//                --in;
//            }
//            a[in] = temp;
        }
    }

    public static void swap(int[] a, int left, int right) {
        if (a == null || a.length == 0) {
            return;
        }
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }

    public static void printArr(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        for (int i : a) {
            LogUtil.printSystemInfo(i + "");
        }
    }
}
