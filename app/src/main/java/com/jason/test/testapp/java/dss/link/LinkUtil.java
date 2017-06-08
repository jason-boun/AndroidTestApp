package com.jason.test.testapp.java.dss.link;

import com.jason.test.testapp.utils.LogUtil;

/**
 * Created by JiaBo on 2017/6/7.
 */

public class LinkUtil {

    public static void main(String[] args) {
        Link link = new Link();
        link.printLink();
        LogUtil.print("", "\n============\n");
        Node re = reverse(link.getHead());
        link.printLink(re);
    }

    /**
     * 单链表翻转（递归）
     * 递归至最后那个节点，然后在逐次回退出堆栈过程中，
     * 先给当前节点的下一个节点的next引用赋值，然后断开当前节点next引用
     *
     * @param head
     * @return
     */
    public static Node reverseLink(Node head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        //保存上次返回的节点，通过递归可知，逐次保存的都是最后的那个节点
        Node reverse = reverseLink(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return reverse;
    }

    /**
     * 单链表翻转（遍历）
     * 遍历的思想在于，从头至尾向下进行
     * 在断开之前，先保存第三个的引用，然后再处理第一个和第二个之间的引用翻转
     *
     * @param head
     * @return
     */
    public static Node reverse(Node head) {
        if (head == null) {
            return head;
        }
        Node pre = head;
        Node current = head.getNext();
        while (current != null) {
            Node temp = current.getNext();
            current.setNext(pre);

            pre = current;
            current = temp;
        }
        head.setNext(null);
        return pre;
    }

}
