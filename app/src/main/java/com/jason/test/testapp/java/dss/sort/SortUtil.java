package com.jason.test.testapp.java.dss.sort;

import com.jason.test.testapp.utils.LogUtil;

/**
 * Created by JiaBo on 2017/5/31.
 */

public class SortUtil {

    public static void main(String[] args) {
        int[] testArr = ArrayData.arr;
//        printArr(testArr);
//        bubbleSort(testArr);
//        selectSort(testArr);
//        insertSort(testArr);
//        mergeSort(testArr);
//        mergeSort(testArr,0, testArr.length-1);
        shellSort(testArr);
//        partition(testArr, 0, testArr.length - 1, 56);
//        quickSort(testArr, 0, testArr.length - 1);
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
    public static void mergeSort(int[] a, int left, int right) {
        if (a == null || a.length == 0 || left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(a, left, mid);
        mergeSort(a, mid + 1, right);
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

    /**
     * 快速排序，时间复杂度（不稳定，平均是O(N*logN)，最差可以到O(N^2））
     */
    public static void quickSort(int[] a, int left, int right) {
        if (a == null || a.length == 0) {
            return;
        }
        if (left >= right || right >= a.length) {
            return;
        }
        int partition = partition(a, left, right);
        quickSort(a, left, partition - 1);
        quickSort(a, partition + 1, right);
    }

    /**
     * 对数组通过指定枢纽值进行划分（是快速排序的基础和核心）
     *
     * @param a
     * @param left
     * @param right
     * @return
     */
    public static int partition(int[] a, int left, int right) {
        //三数据项获取枢纽值，提升效率
        int mid = left + (right - left) / 2;
        if (a[mid] > a[right]) {
            swap(a, mid, right);
        }
        if (a[left] > a[right]) {
            swap(a, left, right);
        }
        if (a[mid] > a[left]) {
            swap(a, mid, left);
        }
        int key = a[left];

        while (left < right) {
            while (a[right] >= key && right > left) {
                right--;
            }
            a[left] = a[right];
            while (a[left] <= key && left < right) {
                left++;
            }
            a[right] = a[left];
        }
        a[right] = key;
        return right;
    }

    /**
     * 对数组通过指定枢纽值进行划分（是快速排序的基础和核心）
     *
     * @param pivot 枢纽值：可以选择a[right]
     * @return
     */
    public static int partition(int[] a, int left, int right, int pivot) {
        if (a == null || a.length == 0) {
            return 0;
        }
        while (true) {
            while (left < right && a[left] < pivot) {
                left++;
            }
            while (left < right && a[right] > pivot) {
                right--;
            }
            if (left >= right) {
                break;
            } else {
                swap(a, left, right);
            }
        }
        swap(a, left, right);
        return left;
    }

    public static void swap(int[] a, int left, int right) {
        if (a == null || a.length == 0 || left < 0 || right >= a.length) {
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
            LogUtil.print("arr", i + "");
        }
    }


}
