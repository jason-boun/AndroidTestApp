package com.jason.test.testapp.java.recursion;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: [求解最近点对问题]
 * @Author: [胖虎]
 * @CreateDate: [2014-4-2 上午9:57:22]
 * @CsdnUrl: [http://blog.csdn.net/ljphhj]
 */
class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[x=" + x + ", y=" + y + "]";
    }
}

public class PointMinDistanceProblem {
    private Point leftPoint = new Point(-1, -1);
    private Point rightPoint = new Point(-1, -1);

    private static Point[] points = new Point[]{
            new Point(1, 3),
            new Point(5, 6),
            new Point(-1, 10),
            new Point(10, 6),
            new Point(2, 5),
            new Point(4, 9),
            new Point(3, 2),
            new Point(7, 7),
    };

    public static void main(String[] args) {
        PointMinDistanceProblem p = new PointMinDistanceProblem();
        //1. 蛮力法求解：O(n^2)
//		double dis1 = p.getMinDistance1(points,0,points.length-1);
//		System.out.println("直接暴力: " + dis1);

        //2. 分治法求解
        double dis2 = p.getMinDistance2(points);
        System.out.println("分治法求解最近点对距离: " + dis2 + " Point1:" + p.leftPoint + " Point2:" + p.rightPoint);
    }

    /*蛮力法*/
    public double getMinDistance1(Point[] points, int left, int right) {

        double minDistance = Double.MAX_VALUE;
        for (int i = left; i <= right; ++i) {
            for (int j = i + 1; j <= right; ++j) {
                double distance = getDistance(points[i], points[j]);
                if (minDistance > distance) {
                    leftPoint = points[i];
                    rightPoint = points[j];
                    minDistance = distance;
                }
            }
        }
        return minDistance;
    }

    class xcomparator implements Comparator<Point> {

        public int compare(Point p1, Point p2) {
            return p1.x > p2.x ? 1 : p1.x == p2.x ? 0 : -1;
        }
    }

    /*分治法*/
    public double getMinDistance2(Point[] points) {

        int len = points.length;
        if (len == 1) {
            return Double.MAX_VALUE;
        }
        /*根据所有点的x值进行排序*/
        Arrays.sort(points, new xcomparator());
        return getMinDistance(points, 0, len - 1);
    }

    public double getMin(double leftdis, double rightdis) {
        return leftdis > rightdis ? rightdis : leftdis;
    }

    public double getMinDistance(Point[] points, int left, int right) {

        if (right - left < 3) {
            return getMinDistance1(points, left, right);
        }

        //求出中间
        int mid = (left + right) / 2;
        double leftMin = getMinDistance(points, left, mid);
        double rightMin = getMinDistance(points, mid + 1, right);
        double currentMin = leftMin > rightMin ? rightMin : leftMin;
        //求出左边
        int leftR = 0;
        int rightR = 0;
        for (int i = left; i < mid; ++i) {
            if (points[mid].x - points[i].x < currentMin) {
                leftR = i;
                break;
            }
        }
        for (int i = right; i > mid; --i) {
            if (points[i].x - points[mid].x < currentMin) {
                rightR = i;
                break;
            }
        }
        double midMin = getMinDistance1(points, leftR, rightR);

        return midMin > currentMin ? currentMin : midMin;
    }


    /*求两点间的距离*/
    public double getDistance(Point p1, Point p2) {

        return Math.sqrt(((p2.y - p1.y) * (p2.y - p1.y)) + ((p2.x - p1.x) * (p2.x - p1.x)));
    }
}