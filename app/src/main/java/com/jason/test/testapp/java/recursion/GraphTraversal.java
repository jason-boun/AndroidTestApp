package com.jason.test.testapp.java.recursion;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: [图遍历算法（主要是DFS）中的递归算法]
 * @Author: [胖虎]
 * @CreateDate: [2014-4-1 下午5:58:33]
 * @CsdnUrl: [http://blog.csdn.net/ljphhj]
 * <p>
 * <p>
 * 输入： graph为博文中 图三 示例的有向图的邻接矩阵
 * 输出：
 * 图的深度遍历
 * A顶点(V0)被访问了！
 * B顶点(V1)被访问了！
 * E顶点(V4)被访问了！
 * D顶点(V3)被访问了！
 * C顶点(V2)被访问了！
 * 图的广度遍历
 * A顶点(V0)被访问了！
 * B顶点(V1)被访问了！
 * D顶点(V3)被访问了！
 * E顶点(V4)被访问了！
 * C顶点(V2)被访问了！
 */


class Graph {
    String[] Vexs;
    int[][] Edges;
    int VexsLen;

    public Graph(String[] vexs, int[][] edges) {
        super();
        Vexs = vexs;
        Edges = edges;
        VexsLen = vexs.length;
    }
}

public class GraphTraversal {

    public static void main(String[] args) {

		/*图的初始化: 我们选取一个博文中图3中的有向图做例子*/
        String[] vexs = new String[5];
        for (int i = 0; i < 5; ++i) {
            vexs[i] = (char) ('A' + i) + "顶点(V" + i + ")";
        }
        int[][] edges = new int[][]{
                {0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0}
        };
        Graph graph = new Graph(vexs, edges);

		/*图的深度遍历(递归)*/
        System.out.println("图的深度遍历");
        DFSTraversal(graph);

		
		/*图的广度遍历(无关递归,但居然涉及到图遍历就也写下)*/
        System.out.println("图的广度遍历");
        BFSTraversal(graph);

    }

    public static void DFSTraversal(Graph graph) {
        /*初始化visited[]数组*/
        boolean[] visited = new boolean[graph.VexsLen];
        for (int i = 0; i < graph.VexsLen; ++i) {
            visited[i] = false;
        }
		
		/*深度遍历*/
        for (int i = 0; i < graph.VexsLen; ++i) {
            if (!visited[i]) {
                DFS(graph, i, visited);
            }
        }
    }

    /**
     * @param graph 图对象
     * @param i     要访问的顶点下标i
     */
    public static void DFS(Graph graph, int i, boolean[] visited) {
		/*访问该顶点*/
        visitedMethod(graph, i);
		/*设置成已访问*/
        visited[i] = true;
        for (int k = 0; k < graph.VexsLen; ++k) {
			/*寻找还没有被访问过的邻接点*/
            if (graph.Edges[i][k] == 1 && !visited[k]) {
                DFS(graph, k, visited);
            }
        }

    }

    public static void BFSTraversal(Graph graph) {
		/*初始化visited[]数组*/
        boolean[] visited = new boolean[graph.VexsLen];
        for (int i = 0; i < graph.VexsLen; ++i) {
            visited[i] = false;
        }
		
		/*广度遍历,寻找源点*/
        for (int i = 0; i < graph.VexsLen; ++i) {
            if (!visited[i]) {
                BFS(graph, i, visited);
            }
        }
    }

    public static void BFS(Graph graph, int i, boolean[] visited) {
        if (graph.VexsLen < 0)
            return;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(i);    //把源点的下标i加入到队列中

        while (!queue.isEmpty()) {
            int index = queue.remove();
            visitedMethod(graph, index);
            visited[index] = true;    //设置下标index的顶点为已访问过
            for (int k = 0; k < graph.VexsLen; ++k) {
				/*寻找还没有被访问过的邻接点*/
                if (graph.Edges[index][k] == 1 && !visited[k]) {
                    queue.add(k);    //该顶点下标加入到队列中
                }
            }
        }

    }


    public static void visitedMethod(Graph graph, int i) {
        System.out.println(graph.Vexs[i] + "被访问了！");
    }
}
