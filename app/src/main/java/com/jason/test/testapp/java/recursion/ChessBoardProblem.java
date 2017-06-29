package com.jason.test.testapp.java.recursion;

/**
 * @Description: [棋盘覆盖问题：涉及递归]
 * @Author: [胖虎]
 * @CreateDate: [2014-4-2 上午2:06:51]
 * @CsdnUrl: [http://blog.csdn.net/ljphhj]
 * <p>
 * 呜呜~~我要睡觉！！！！！！！！
 */
public class ChessBoardProblem {

    private int[][] Board;    //模拟棋盘
    private int specialRow;
    private int specialCol;
    private int size;
    private int type = 0;

    public ChessBoardProblem(int specialRow, int specialCol, int size) {
        //初始化棋盘 和 特殊方格位置
        this.size = (int) Math.pow(2, size);
        this.Board = new int[this.size][this.size];
        this.specialRow = specialRow;
        this.specialCol = specialCol;
    }

    /**
     * @param specialRow 特殊方格所在位置的行标
     * @param specialCol 特殊方格所在位置的列标
     * @param leftRow    要处理的子棋盘的左上方行标
     * @param leftCol    要处理的子棋盘的左上方列标
     * @param size       要处理的子棋盘的大小(size * size)
     */
    private void ChessBoard(int specialRow, int specialCol, int leftRow, int leftCol, int size) {
        if (size == 1)
            return;
        int subSize = size / 2;
        type = type % 4 + 1;
        int t = type;
        /*处理划分四个子棋盘的左上方那个棋盘*/
        if (specialRow < leftRow + subSize && specialCol < leftCol + subSize) {
            //表示特殊方格存在于子棋盘的左上角
            ChessBoard(specialRow, specialCol, leftRow, leftCol, subSize);    //本来就有特殊方格，递归调用咯!
        } else {
            //如果特殊方格不在子棋盘的左上角，那么必定被划分之后要补充的特殊方格在左上角区域中的右下方那个方格
            Board[leftRow + subSize - 1][leftCol + subSize - 1] = t;    //设置为type类型的骨牌(即这个子棋盘也有了特殊方格)
            ChessBoard(leftRow + subSize - 1, leftCol + subSize - 1, leftRow, leftCol, subSize);    //有了这个特殊方格之后，递归调用咯!
        }

		/*处理划分四个子棋盘的右上方那个棋盘*/
        if (specialRow < leftRow + subSize && specialCol >= leftCol + subSize) {
            //表示特殊方格存在于子棋盘的右上角
            ChessBoard(specialRow, specialCol, leftRow, leftCol + subSize, subSize);    //本来就有特殊方格，递归调用咯!
        } else {
            //如果特殊方格不在子棋盘的右上角，那么必定被划分之后要补充的特殊方格在右上角区域中的左下方那个方格
            Board[leftRow + subSize - 1][leftCol + subSize] = t;    //设置为type类型的骨牌(即这个子棋盘也有了特殊方格)
            ChessBoard(leftRow + subSize - 1, leftCol + subSize, leftRow, leftCol + subSize, subSize);    //有了这个特殊方格之后，递归调用咯!
        }
        /*处理划分四个子棋盘的左下方那个棋盘*/
        if (specialRow >= leftRow + subSize && specialCol < leftCol + subSize) {
            //表示特殊方格存在于子棋盘的左下角
            ChessBoard(specialRow, specialCol, leftRow + subSize, leftCol, subSize);    //本来就有特殊方格，递归调用咯!
        } else {
            //如果特殊方格不在子棋盘的左下角，那么必定被划分之后要补充的特殊方格在左下角区域中的右上方那个方格
            Board[leftRow + subSize][leftCol + subSize - 1] = t;    //设置为type类型的骨牌(即这个子棋盘也有了特殊方格)
            ChessBoard(leftRow + subSize, leftCol + subSize - 1, leftRow + subSize, leftCol, subSize);    //有了这个特殊方格之后，递归调用咯!
        }

		/*处理划分四个子棋盘的右下方那个棋盘*/
        if (specialRow >= leftRow + subSize && specialCol >= leftCol + subSize) {
            //表示特殊方格存在于子棋盘的右下角
            ChessBoard(specialRow, specialCol, leftRow + subSize, leftCol + subSize, subSize);    //本来就有特殊方格，递归调用咯!
        } else {
            //如果特殊方格不在子棋盘的右下角，那么必定被划分之后要补充的特殊方格在右下角区域中的左上方那个方格
            Board[leftRow + subSize][leftCol + subSize] = t;    //设置为type类型的骨牌(即这个子棋盘也有了特殊方格)
            ChessBoard(leftRow + subSize, leftCol + subSize, leftRow + subSize, leftCol + subSize, subSize);    //有了这个特殊方格之后，递归调用咯!
        }
    }

    public void solve() {
        ChessBoard(specialRow, specialCol, 0, 0, size);
        printfResult();
    }

    private void printfResult() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                System.out.print(Board[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        int N = 2;
        int specialRow = 0;
        int specialCol = 1;
        ChessBoardProblem chessBoardProblem = new ChessBoardProblem(specialRow, specialCol, N);
        chessBoardProblem.solve();
    }
}
