package com.jason.test.testapp.java.recursion;

/**
 * @Description: [展示快速排序中的递归算法 + 分治思想]
 * @Author: [胖虎]
 * @CreateDate: [2014-4-1 上午12:46:14]
 * @CsdnUrl: [http://blog.csdn.net/ljphhj]
 * <p>
 * 待排序列：{7,-1,10,2,0,9,11,3,4}
 * 输出排序结果：{-1,0,2,3,4,7,9,10,11}
 */
public class QuickSortProblem {

    public static int Partion(int[] arrays, int left, int right) {
        if (right == left)
            return left;
        int key = arrays[left];
        while (left < right) {
            //从后往前搜索比key小的元素,填入arrays[i]的位置中，并跳出循环
            while (left < right) {
                if (arrays[right] < key) {
                    arrays[left] = arrays[right];
                    break;
                }
                right--;
            }
            while (left < right) {
                if (arrays[left] > key) {
                    arrays[right] = arrays[left];
                    break;
                }
                left++;
            }
        }
        arrays[left] = key;    //最终取出来的这个比较数的位置
        //此时middle = left = right
        return left;
    }

    public static void QuickSort(int[] arrays, int left, int right) {
        if (left >= right)
            return;
        int middle = Partion(arrays, left, right);
        QuickSort(arrays, left, middle);
        QuickSort(arrays, middle + 1, right);
    }

    public static void main(String[] args) {
        int[] arrays = new int[]{7, -1, 10, 2, 0, 9, 11, 3, 4};
        QuickSort(arrays, 0, arrays.length - 1);
        for (int i = 0; i < arrays.length; ++i) {
            System.out.print(arrays[i] + " ");
        }
    }

}
