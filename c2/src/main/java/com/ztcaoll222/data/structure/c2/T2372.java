package com.ztcaoll222.data.structure.c2;

import com.ztcaoll222.data.structure.c2.impl.SingleLinkTable;
import com.ztcaoll222.data.structure.c2.impl.SingleLinkTableNode;

import java.util.Objects;

/**
 * 第二章链表综合题
 *
 * @author ztcaoll222
 * Create time: 2019/10/3 12:25
 */
public class T2372 {
    private T2372() {
    }

    /**
     * 设计一个递归算法, 删除不带头结点的单链表中所有值为 @param x 的节点
     *
     * @param node 结点
     * @param x    值
     */
    private static <T> SingleLinkTableNode<T> t1(SingleLinkTableNode<T> node, T x) {
        if (node == null) {
            return null;
        }

        var next = t1(node.getNext(), x);
        if (Objects.equals(x, node.getValue())) {
            return next;
        } else {
            node.setNext(next);
            return node;
        }
    }

    /**
     * 设计一个递归算法, 删除不带头结点的单链表中所有值为 @param x 的节点
     *
     * @param table 表
     * @param x     值
     */
    public static <T> void t1(SingleLinkTable<T> table, T x) {
        t1(table.node, x);
    }
}
