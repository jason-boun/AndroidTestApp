package com.jason.test.testapp.java.recursion;

/**
 * @Description: [详细讲解N皇后问题（超详细注释）]
 * @Author: [胖虎]
 * @CreateDate: [2014-4-2 上午12:40:49]
 * @CsdnUrl: [http://blog.csdn.net/ljphhj]
 */
public class NQueenProblem {

    private final int SAFE_POSITION = 0;//表示当前位置为安全位置
    private int QueenCount;                //皇后的个数
    private int result = 0;                //解法的个数
    /**
     * 表示棋盘的位置，数组里的值表示为“冲突的皇后数”
     * 如：Position[i][j] = 2   表示 i 行, j列这个位置有两个皇后和它冲突
     * 所以你放在这个位置，就会有两个皇后和你冲突！！即不满足条件
     * <p>
     * Position[i][j] = SAFE_POSITION = 0; 这样表示0个皇后和它冲突
     * 所以你可以把皇后放在这个位置！
     */
    private int[][] Position;            //表示棋盘的数组

    /*
     * 数组下标代表棋盘的row向量，
     * 而数组的值为棋盘的col向量。
     * 如：皇后(row, col) 可表示为(SafeQueens[row] = col)
     * */
    private int[] SafeQueens;            //表示已经找到安全位置的皇后(为了输出解)

    private long time;                    //用来计算算法所用时间

    public NQueenProblem(int n) {
        this.QueenCount = n;            //皇后的个数
        this.Position = new int[n][n];    //n*n棋盘
        this.SafeQueens = new int[n];
    }

    public void Solve() {
        if (QueenCount <= 0)
            return;
        time = System.currentTimeMillis();
        FindSafeQueen(0);
    }

    /**
     * 这个是要递归的函数
     *
     * @param row 表示要找的是第row行的皇后（row: 0 ~ N-1）
     */
    public void FindSafeQueen(int row) {
        for (int col = 0; col < QueenCount; ++col) {
            /*如果此位置现在是安全的位置*/
            if (Position[row][col] == SAFE_POSITION) {
                SafeQueens[row] = col;//设置row行的皇后位置

				/*为棋盘添加冲突数： (以下注释较多，方便理解，也自己可以去除掉！！)*/
                /* 下面要做处理：把跟这个row行皇后处于
				 * “竖直方向”，“斜线方向”，“反斜线方向”
				 * 的位置标记一下
				 * 我们只处理row+1行 ~ N-1行所不能放皇后的位置
				 * 而不考虑小于等于row行的那些情况，因为我们取皇后的时候是一行行往下的
				 * */
                for (int dealRow = row + 1; dealRow < QueenCount; ++dealRow) {
					/* "|" （竖直方向）所波及到的位置,冲突皇后数+1个*/
                    Position[dealRow][col]++;
					
					/* "/"（斜杠方向）所波及到的位置, 由于我们只考虑大于 row + 1 ~ 第N行，
					 * 只需要考虑斜线的左下方的那些位置（这样才能保证大于row行）
					 * 即条件：列 (col - (dealRow - row)) >= 0
					 * "列"不要越过下边界 0
					 * */
                    if ((col - (dealRow - row)) >= 0) {
						/* "/"（斜杠方向）所波及到的位置,冲突皇后数+1个*/
                        Position[dealRow][(col - (dealRow - row))]++;
                    }
					
					/* "\"（反斜杠方向）所波及到的位置, 由于我们只考虑大于 row + 1 ~ 第N(即QueenCount)行
					 * 我们只需要考虑反斜线的右下方的那些位置（这样才能保证大于row行）
					 * 即条件：(col + (dealRow - row)) < QueenCount
					 * "列"不要越过上边界 N(即QueenCount)
					 * */
                    if ((col + (dealRow - row)) < QueenCount) {
						/* "/"（斜杠方向）所波及到的位置,冲突皇后数+1个*/
                        Position[dealRow][(col + (dealRow - row))]++;
                    }
                }
				
				/*最后一行*/
                if (row == QueenCount - 1) {
                    printOneResult(++result);
                } else {
                    //递归调用,去求row+1行的N皇后位置
                    FindSafeQueen(row + 1);
                }
				
				/*为棋盘减少冲突数（跟增加冲突数相反）*/
                for (int dealRow = row + 1; dealRow < QueenCount; ++dealRow) {
					/* "|" （竖直方向）所波及到的位置,冲突皇后数 -1个*/
                    Position[dealRow][col]--;
                    if ((col - (dealRow - row)) >= 0) {
						/* "/"（斜杠方向）所波及到的位置,冲突皇后数 -1个*/
                        Position[dealRow][(col - (dealRow - row))]--;
                    }
                    if ((col + (dealRow - row)) < QueenCount) {
						/* "/"（斜杠方向）所波及到的位置,冲突皇后数 -1个*/
                        Position[dealRow][(col + (dealRow - row))]--;
                    }
                }
            }
        }
        if (row == 0) {
            System.out.println(QueenCount + "皇后共有 " + result + " 个解\n包括printf,共耗时："
                    + (System.currentTimeMillis() - time) + "毫秒");
        }
    }

    private void printOneResult(int result) {
        System.out.println(QueenCount + "皇后的第 " + result + " 个解：(皇后: ▲  其他空位置: - )");
        for (int i = 0; i < QueenCount; i++) {
            for (int j = 0; j < QueenCount; j++) {
                System.out.print(SafeQueens[i] == j ? " ▲ " : " - ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int N = 8;    //定义N皇后问题
        NQueenProblem queenProblem = new NQueenProblem(N);
        queenProblem.Solve();
    }
}
