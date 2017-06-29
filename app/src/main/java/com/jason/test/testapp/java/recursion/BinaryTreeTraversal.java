package com.jason.test.testapp.java.recursion;

/**
 * @Description: [递归方式做二叉树的三种遍历]
 * @Author: [胖虎]
 * @CreateDate: [2014-4-1 上午12:29:35]
 * @CsdnUrl: [http://blog.csdn.net/ljphhj]
 * <p>
 * <p>
 * 输出结果：
 * 前序遍历：
 * 0 1 3 7 8 4 2 5 6
 * 中序遍历：
 * 7 3 8 1 4 0 5 2 6
 * 后序遍历：
 * 7 8 3 4 1 5 6 2 0
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class BinaryTreeTraversal {
    public static void PreOrderTraversal(TreeNode root) {
        /*递归结束条件*/
        if (root == null)
            return;
        System.out.print(root.val + " ");
        /*递归调用*/
        PreOrderTraversal(root.left);
        PreOrderTraversal(root.right);
    }

    public static void InOrderTraversal(TreeNode root) {
        /*递归结束条件*/
        if (root == null)
            return;

		/*递归调用*/
        InOrderTraversal(root.left);
        System.out.print(root.val + " ");
        InOrderTraversal(root.right);
    }

    public static void PostOrderTraversal(TreeNode root) {
		/*递归结束条件*/
        if (root == null)
            return;
		
		/*递归调用*/
        PostOrderTraversal(root.left);
        PostOrderTraversal(root.right);
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        //初始化一棵二叉树, 不影响递归的思想，可忽略
        TreeNode[] nodes = new TreeNode[9];
        for (int i = 8; i >= 0; --i) {
            nodes[i] = new TreeNode(i);
            if (2 * i + 1 > 8) {
                nodes[i].left = null;
                nodes[i].right = null;
            } else {
                nodes[i].left = nodes[2 * i + 1];
                if (2 * i + 2 > 8) {
                    nodes[i].right = null;
                } else {
                    nodes[i].right = nodes[2 * i + 2];
                }
            }
        }
        System.out.println("前序遍历：");
        PreOrderTraversal(nodes[0]);
        System.out.println("\n中序遍历：");
        InOrderTraversal(nodes[0]);
        System.out.println("\n后序遍历：");
        PostOrderTraversal(nodes[0]);
    }
}
