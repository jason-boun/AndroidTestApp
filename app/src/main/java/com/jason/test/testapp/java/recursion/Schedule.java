package com.jason.test.testapp.java.recursion;
/* 赛程表安排问题，有2的k次方（n）支球队，在n-1天两两比赛，每支球队每天只进行一场比赛，求安排日程表。
 * 可用分治递归的方法求解，由样例图分析可知，可将整个日程表矩阵划分为四个同等大小部分，若左上角填充完毕，
 * 则，右上角每个元素为左上角中同行对应元素再加上小矩阵长度，左下角矩阵每个元素为左上角矩阵中同列对应元
 * 素再加上矩阵长度，而右下角矩阵和左上角矩阵一致。所以整个问题就在于左上角的填充，左上角的填充依然和原
 * 问题一致，可以递归调用，递归结束条件是当矩阵长度为1时，将矩阵第一行第一列元素赋为1*/

/**
 * @Description: [求解循环赛日程表安排问题（分治+递归）]
 * @Author: [胖虎]
 * @CreateDate: [2014-4-2 上午10:12:50]
 * @CsdnUrl: [http://blog.csdn.net/ljphhj]
 */
public class Schedule {
    public void scheduleTable(int[][] t, int n) {
        if (n == 1) {
            t[0][0] = 1;
        } else {
            int m = n / 2;
            scheduleTable(t, m);
            //填充右上角矩阵，每个元素为同一行，列下标减m的元素再加m
            for (int i = 0; i < m; i++) {
                for (int j = m; j < n; j++) {
                    t[i][j] = t[i][j - m] + m;
                }
            }

            //填充左下角矩阵，每个元素为同一列，行下标减m的元素再加m
            for (int i = m; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    t[i][j] = t[i - m][j] + m;
                }
            }

            //填充右下下角矩阵，右下角矩阵与左上角矩阵一致
            for (int i = m; i < n; i++) {
                for (int j = m; j < n; j++) {
                    t[i][j] = t[i - m][j - m];
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] num = new int[8][8];
        int n = 8;
        Schedule s = new Schedule();
        s.scheduleTable(num, n);
        int c = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(num[i][j] + " ");
                c++;
                if ((c % n) == 0) {
                    System.out.println();
                }
            }
        }
    }
}
