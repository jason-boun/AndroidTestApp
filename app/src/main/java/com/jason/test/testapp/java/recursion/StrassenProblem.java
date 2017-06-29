package com.jason.test.testapp.java.recursion;

/**
 * @Description: [Strassen矩阵乘法]
 * @Author: [胖虎]
 * @CreateDate: [2014-4-2 上午9:13:16]
 * @CsdnUrl: [http://blog.csdn.net/ljphhj]
 * <p>
 * 输入的矩阵：A
 * 1	2	3	4
 * 2	4	6	8
 * 3	6	9	12
 * 4	8	12	16
 * 输入的矩阵：B
 * 1	2	3	4
 * 2	4	6	8
 * 3	6	9	12
 * 4	8	12	16
 * 输出的矩阵：C
 * 30	60	90	120
 * 60	120	180	240
 * 90	180	270	360
 * 120	240	360	480
 */
public class StrassenProblem {

    public void solve(int N, int[][] A, int[][] B, int[][] C) {
        if (N <= 0)
            return;
        strassen(N, A, B, C);
    }

    /**
     * @param N 矩阵的规模
     * @param A 矩阵A
     * @param B 矩阵B
     * @param C 结果矩阵C
     */
    private void strassen(int N, int[][] A, int[][] B, int[][] C) {
        //定义一些中间变量
        int[][] M1 = new int[N][N];
        int[][] M2 = new int[N][N];
        int[][] M3 = new int[N][N];
        int[][] M4 = new int[N][N];
        int[][] M5 = new int[N][N];
        int[][] M6 = new int[N][N];
        int[][] M7 = new int[N][N];

        int[][] C11 = new int[N][N];
        int[][] C12 = new int[N][N];
        int[][] C21 = new int[N][N];
        int[][] C22 = new int[N][N];

        int[][] A11 = new int[N][N];
        int[][] A12 = new int[N][N];
        int[][] A21 = new int[N][N];
        int[][] A22 = new int[N][N];

        int[][] B11 = new int[N][N];
        int[][] B12 = new int[N][N];
        int[][] B21 = new int[N][N];
        int[][] B22 = new int[N][N];

        int[][] temp = new int[N][N];
        int[][] temp1 = new int[N][N];

		
		
		/*递归结束条件： 如果矩阵为2*2规模的，直接算！*/
        if (A.length == 2) {
            MatrixMul(A, B, C);
        } else {
            //首先将矩阵A，B 分为4块
            for (int i = 0; i < A.length / 2; i++) {
                for (int j = 0; j < A.length / 2; j++) {
                    A11[i][j] = A[i][j];
                    A12[i][j] = A[i][j + A.length / 2];
                    A21[i][j] = A[i + A.length / 2][j];
                    A22[i][j] = A[i + A.length / 2][j + A.length / 2];
                    B11[i][j] = B[i][j];
                    B12[i][j] = B[i][j + A.length / 2];
                    B21[i][j] = B[i + A.length / 2][j];
                    B22[i][j] = B[i + A.length / 2][j + A.length / 2];
                }
            }
            //计算M1
            MatrixSub(B12, B22, temp);
            MatrixMul(A11, temp, M1);
            //计算M2
            MatrixAdd(A11, A12, temp);
            MatrixMul(temp, B22, M2);
            //计算M3
            MatrixAdd(A21, A22, temp);
            MatrixMul(temp, B11, M3);
            //M4
            MatrixSub(B21, B11, temp);
            MatrixMul(A22, temp, M4);
            //M5
            MatrixAdd(A11, A22, temp1);
            MatrixAdd(B11, B22, temp);
            MatrixMul(temp1, temp, M5);
            //M6
            MatrixSub(A12, A22, temp1);
            MatrixAdd(B21, B22, temp);
            MatrixMul(temp1, temp, M6);
            //M7
            MatrixSub(A11, A21, temp1);
            MatrixAdd(B11, B12, temp);
            MatrixMul(temp1, temp, M7);

            //计算C11
            MatrixAdd(M5, M4, temp1);
            MatrixSub(temp1, M2, temp);
            MatrixAdd(temp, M6, C11);
            //计算C12
            MatrixAdd(M1, M2, C12);
            //C21
            MatrixAdd(M3, M4, C21);
            //C22
            MatrixAdd(M5, M1, temp1);
            MatrixSub(temp1, M3, temp);
            MatrixSub(temp, M7, C22);

            //结果送回C中
            for (int i = 0; i < C.length / 2; i++) {
                for (int j = 0; j < C.length / 2; j++) {
                    C[i][j] = C11[i][j];
                    C[i][j + C.length / 2] = C12[i][j];
                    C[i + C.length / 2][j] = C21[i][j];
                    C[i + C.length / 2][j + C.length / 2] = C22[i][j];
                }
            }
        }
    }

    /**
     * 矩阵乘法，此处只是定义了2*2矩阵的乘法
     */
    public void MatrixMul(int[][] first, int[][] second, int[][] resault) {
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                resault[i][j] = 0;
                for (int k = 0; k < 2; ++k) {
                    resault[i][j] += first[i][k] * second[k][j];
                }
            }
        }

    }

    /**
     * 矩阵的加法运算
     */
    public void MatrixAdd(int[][] first, int[][] second, int[][] resault) {
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first[i].length; j++) {
                resault[i][j] = first[i][j] + second[i][j];
            }
        }
    }

    /**
     * 矩阵的减法运算
     */
    public void MatrixSub(int[][] first, int[][] second, int[][] resault) {
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first[i].length; j++) {
                resault[i][j] = first[i][j] - second[i][j];
            }
        }
    }

    public static void main(String[] args) {
        int N = 4;    //矩阵的大小
        int[][] A = new int[N][N];
        int[][] B = new int[N][N];
        int[][] C = new int[N][N];
        /*A, B矩阵的初始化,具体值无所谓哈！也可以自己录入*/
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                A[i][j] = (i + 1) * (j + 1);
                B[i][j] = (i + 1) * (j + 1);
            }
        }

        System.out.println("输入的矩阵：A");
        printfMatrix(A);
        System.out.println("输入的矩阵：B");
        printfMatrix(B);
        StrassenProblem strassenProblem = new StrassenProblem();
        strassenProblem.solve(N, A, B, C);
        System.out.println("输出的矩阵：C");
        printfMatrix(C);
    }

    private static void printfMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println("");
        }
    }
}
