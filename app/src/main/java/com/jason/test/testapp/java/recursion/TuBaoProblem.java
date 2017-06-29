package com.jason.test.testapp.java.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 详细注释,分治解决凸包问题
 *
 * @Description: [分治+递归解决凸包问题]
 * @Author: [胖虎]
 * @CreateDate: [2014-4-2 下午1:59:36]
 * @CsdnUrl: [http://blog.csdn.net/ljphhj]
 */

/*凸包上的点*/
class TuPoint {
    double x;
    double y;

    public TuPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "TuPoint [x=" + x + ", y=" + y + "]";
    }

}

/*凸包上的两点的连线*/
class TuLine {
    TuPoint point1, point2;

    public TuLine(TuPoint point1, TuPoint point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    //两点的距离（线的长度）
    public double getDistance() {
        double dx = point1.x - point2.x;
        double dy = point1.y - point2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return "TuLine [point1=" + point1 + ", point2=" + point2 + "]";
    }

}

public class TuBaoProblem {
    //要处理的点集合
    private List<TuPoint> pointList = null;
    //点集pointList对应的凸包结果。
    private List<TuLine> lineList = new ArrayList<TuLine>();

    public TuBaoProblem(List<TuPoint> pointList) {
        super();
        this.pointList = pointList;
    }

    //求解凸包，并把结果存入到lineList中。
    public void solve() {
        //初始化：clear
        lineList.clear();
        if (pointList == null | pointList.isEmpty())
            return;
        /*左凸包中的点集合*/
        List<TuPoint> leftPointList = new ArrayList<TuPoint>();
        /*右凸包中的点集合*/
        List<TuPoint> rightPointList = new ArrayList<TuPoint>();

		/*根据point的x坐标来排序*/
        Collections.sort(pointList, new xcomparator());

		/*找到x最小的点，即最左边的点*/
        TuPoint leftPoint = pointList.get(0);

		/*找到x最大的点，即最右边的点*/
        TuPoint rightPoint = pointList.get(pointList.size() - 1);

		/*leftPoint ~~ rightPoint的连线把大的凸包问题，分解成两个小的凸包问题*/
        /*把总的点集，分成两半,对应放到leftPointList(左凸包点集) 或者 rightPointList(右凸包点集)*/
        for (int i = 0; i < pointList.size(); i++) {// 穷举所有的点,
            TuPoint tempPoint = pointList.get(i);
            //判断点tempPoint所在区域为左凸包还是右凸包
            double result = findArea(leftPoint, rightPoint, tempPoint);
            if (result > 0) {
                //tempPoint属于左边
                leftPointList.add(tempPoint);
            } else if (result < 0) {
                //tempPoint属于右边
                rightPointList.add(tempPoint);
            }
        }

        //分别求解左右两个凸包
        dealTuBao(leftPoint, rightPoint, leftPointList);
        dealTuBao(rightPoint, leftPoint, rightPointList);
    }

    private void dealTuBao(TuPoint p1, TuPoint p2, List<TuPoint> subPointList) {
        if (subPointList.isEmpty()) {
            /*递归结束条件*/
            //这两个点所连成的线将是最终结果凸包上的一条!
            lineList.add(new TuLine(p1, p2));
            return;
        }
        //subPointList不为空的话，我们要去找博文中图示上写的 Pmax 点

        double maxArea = Double.MIN_VALUE;
        TuPoint pMax = null;
        for (int i = 0; i < subPointList.size(); i++) {
            // 最大面积对应的点就是Pmax
            double area = findArea(p1, p2, subPointList.get(i));
            if (area > maxArea) {
                pMax = subPointList.get(i);
                maxArea = area;
            }
        }

		/*下面的处理跟之前solve()函数中的处理一样*/

        // 找出位于(p1, pMax)直线左边的点集s1
        // 找出位于(pMax, p2)直线右边的点集s2
		/*左凸包中的点集合*/
        List<TuPoint> leftPointList = new ArrayList<TuPoint>();
		/*右凸包中的点集合*/
        List<TuPoint> rightPointList = new ArrayList<TuPoint>();
		
		/*把点集subPointList，分成两半,对应放到leftPointList(左凸包点集) 
		 * 或者 rightPointList(右凸包点集)*/
        for (int i = 1; i < subPointList.size(); i++) {// 穷举所有的点,
            TuPoint tempPoint = subPointList.get(i);
            //判断点tempPoint所在区域为左凸包还是右凸包
			/*线: p1 ~ pMax*/
            double result1 = findArea(p1, pMax, tempPoint);
			/*线: p2 ~ pMax*/
            double result2 = findArea(pMax, p2, tempPoint);
            if (result1 > 0) {
                //tempPoint属于左边
                leftPointList.add(tempPoint);
            } else if (result2 > 0) {
                //tempPoint属于右边
                rightPointList.add(tempPoint);
            }
        }
        //递归调用咯~~
        dealTuBao(p1, pMax, leftPointList);
        dealTuBao(pMax, p2, rightPointList);
    }

    /* 函数的功能: 1. 判断点在子凸包的左边或者右边  2.用来算三角形的面积，来找到Pmax点
     * 三角形的面积等于返回值绝对值的二分之一
     * 点p3位于直线(p1, p2)左侧时，表达式的结果为正
     * 点p3位于直线(p1, p2)右侧时，表达式的结果为负
     * */
    private double findArea(TuPoint p1, TuPoint p2, TuPoint p3) {
        return p1.x * p2.y + p3.x * p1.y + p2.x * p3.y - p3.x * p2.y - p2.x
                * p1.y - p1.x * p3.y;
    }

    public static void main(String[] args) {
        List<TuPoint> arrays = new ArrayList<TuPoint>();
        arrays.add(new TuPoint(2, 4));
        arrays.add(new TuPoint(3, 4));
        arrays.add(new TuPoint(3, 3));
        arrays.add(new TuPoint(4, 3));
        arrays.add(new TuPoint(4, 4));
        arrays.add(new TuPoint(5, 4));
        arrays.add(new TuPoint(5, 2));
        arrays.add(new TuPoint(3.5, 2));
        arrays.add(new TuPoint(2, 2));
        TuBaoProblem t = new TuBaoProblem(arrays);
        t.solve();
        t.printResult();
    }

    /*输出结果*/
    public void printResult() {
        for (Object i : lineList.toArray()) {
            System.out.println(i);
        }
    }

    /*x比较器*/
    class xcomparator implements Comparator<TuPoint> {

        public int compare(TuPoint p1, TuPoint p2) {
            return p1.x > p2.x ? 1 : p1.x == p2.x ? 0 : -1;
        }
    }

}
