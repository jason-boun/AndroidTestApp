package com.jason.test.testapp.java.dss.link;

import com.jason.test.testapp.utils.LogUtil;
import com.jason.test.testapp.utils.NumberUtil;

/**
 * Created by JiaBo on 2017/6/7.
 * 链表
 */

public class Link {

    private Node head;

    public Link() {
        buildLink();
    }

    /**
     * 初始化链表
     */
    private void buildLink() {
        if (head == null) {
            setHead(buildNode());
            head.setData(0);
        }
        Node current = head;
        Node next;
        for (int i = 1; i < 20; i++) {
            next = buildNode();
            next.setData(i);
            current.setNext(next);
            current = next;
        }
    }

    /**
     * 打印链表元素
     */
    public void printLink() {
        printLink(getHead());
    }

    /**
     * 构建节点
     *
     * @return
     */
    public Node buildNode() {
        Node node = new Node();
        node.setData(NumberUtil.getRandom(100));
        node.setNext(null);
        return node;
    }

    /**
     * 打印节点
     *
     * @param node
     */
    private void printLink(Node node) {
        if (node == null) {
            return;
        }
        LogUtil.print(node.getData() + "");
        printLink(node.getNext());
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

}
