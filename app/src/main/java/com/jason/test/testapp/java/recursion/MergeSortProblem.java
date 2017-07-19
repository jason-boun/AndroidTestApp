package com.jason.test.testapp.java.recursion;

/**
 * @Description: [归并排序:体现递归和分治]
 * @Author: [胖虎]
 * @CreateDate: [2014-4-1 下午9:18:42]
 * @CsdnUrl: [http://blog.csdn.net/ljphhj]
 * <p>
 * 输入： 归并排序前的序列 {-7,1,0,10,9,-20,100,8,1}
 * 输出： 归并排序后的序列 {-20,-7,0,1,1,8,9,10,100}
 */
public class MergeSortProblem {

    public static void main(String[] args) {
        int[] arrays = new int[]{-7, 1, 0, 10, 9, -20, 100, 8, 1};
        MergeSort(arrays);
    }

    /**
     *
     *
     * @param arrays
     */
    public static void MergeSort(int[] arrays) {
        int len = arrays.length;
        if (len < 2)
            return;
        int left = 0;
        int right = len - 1;

        Sort(arrays, left, right);

        System.out.println("归并排序后的序列");
        for (int i : arrays) {
            System.out.print(i + " ");
        }
    }

    /**
     * 归并排序
     *
     * @param arrays
     * @param left
     * @param right
     */
    private static void Sort(int[] arrays, int left, int right) {
        /*递归结束条件*/
        if (right == left) {
            return;
        }
        int middle = (right + left) / 2;  //二分
        /*递归调用(大问题分解成小问题)+分治思想(先左右两边排序)*/
        Sort(arrays, left, middle);
        Sort(arrays, middle + 1, right);
        Merge(arrays, left, middle, right);/*结果的合并*/
    }

    //合并
    public static void Merge(int[] arrays, int left, int middle, int right) {
        int len = right - left + 1;
        int[] temp = new int[len];
        int k = 0;
        int i = left;
        int j = middle + 1;
        while (i <= middle && j <= right) {
            if (arrays[i] < arrays[j]) {
                temp[k++] = arrays[i];
                i++;
                continue;
            } else {
                temp[k++] = arrays[j];
                j++;
                continue;
            }
        }
        while (i <= middle) {
            temp[k++] = arrays[i];
            i++;
        }
        while (j <= right) {
            temp[k++] = arrays[j];
            j++;
        }
        k = k - 1;
        for (int m = right; m >= left; --m) {
            arrays[m] = temp[k--];
        }
    }
}
