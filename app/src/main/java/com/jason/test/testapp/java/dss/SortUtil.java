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
//        insertSort(testArr);
//        mergeSort(testArr);
        shellSort(testArr);
        printArr(testArr);
    }

    /**
     * 冒泡排序，时间复杂度O(N^2)
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
     * 选择排序，时间复杂度O(N^2)
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
     * 插入排序，时间复杂度O(N^2)
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
        }
    }

    /**
     * 归并排序，时间复杂度O(N*logN)
     *
     * @param a
     */
    public static void mergeSort(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        sort(a, 0, a.length - 1);
    }

    public static void sort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        sort(a, left, mid);
        sort(a, mid + 1, right);
        merge(a, left, mid, right);
    }

    public static void merge(int[] a, int left, int mid, int right) {
        int aIndex = left;
        int[] temp = new int[a.length];
        int tempIndex = left;
        int rightIndex = mid + 1;

        // 逐个归并：归并左右两个数组相同长度的部分
        while (left <= mid && rightIndex <= right) {
            if (a[left] <= a[rightIndex]) {
                temp[tempIndex++] = a[left++];
            } else {
                temp[tempIndex++] = a[rightIndex++];
            }
        }
        //将左边剩余的归并
        while (left <= mid) {
            temp[tempIndex++] = a[left++];
        }
        //将右边剩余的归并
        while (rightIndex <= right) {
            temp[tempIndex++] = a[rightIndex++];
        }
        //从临时数组拷贝到原数组
        while (aIndex <= right) {
            a[aIndex] = temp[aIndex];
            aIndex++;
        }
    }

    /**
     * 希尔排序，时间复杂度O(N*(logN)^2)
     *
     * @param a
     */
    public static void shellSort(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        //计算增量h（从最大开始）
        int h = 1;
        while (h < a.length / 3) {
            h = h * 3 + 1;
        }
        //开始希尔排序
        int out, in, temp;
        while (h > 0) {
            for (out = h; out < a.length; out++) {
                //零时变量记录
                temp = a[out];
                in = out;
                while (in > h - 1 && a[in - h] >= temp) {
                    a[in] = a[in - h];
                    in = in - h;
                }
                a[in] = temp;
            }
            //增量依次递减
            h = (h - 1) / 3;
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
            LogUtil.printSystemInfo("arr", i + "");
        }
    }
}
