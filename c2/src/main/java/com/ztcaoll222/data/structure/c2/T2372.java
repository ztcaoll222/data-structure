package com.ztcaoll222.data.structure.c2;

import com.ztcaoll222.data.structure.c2.impl.SingleLinkTable;
import com.ztcaoll222.data.structure.c2.impl.SingleLinkTableNode;
import com.ztcaoll222.data.structure.c2.impl.SingleLinkTableWithHead;

import java.util.Objects;
import java.util.Optional;

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
    private static <T> SingleLinkTableNode<T> deleteAllX(SingleLinkTableNode<T> node, T x) {
        if (node == null) {
            return null;
        }

        var next = deleteAllX(node.getNext(), x);
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
        deleteAllX(table.node, x);
    }

    /**
     * 删除带头结点的单链表中所有值为 @param x 的节点
     *
     * @param table 表
     * @param x     值
     */
    public static <T> void t2(SingleLinkTableWithHead<T> table, T x) {
        deleteAllX(table.head.getNext(), x);
    }

    /**
     * 逆置输出获得带头结点的单链表
     */
    private static <T> SingleLinkTableNode<T> reversePrint(SingleLinkTableNode<T> node, StringBuilder stringBuilder) {
        if (node == null) {
            return null;
        }

        var next = reversePrint(node.getNext(), stringBuilder);
        if (next != null) {
            stringBuilder.append(next.getValue());
            stringBuilder.append(", ");
        }

        return node;
    }

    /**
     * 逆置输出获得带头结点的单链表
     *
     * @param table 带头结点的单链表
     */
    public static <T> String t3(SingleLinkTableWithHead<T> table) {
        StringBuilder stringBuilder = new StringBuilder();
        reversePrint(table.head, stringBuilder);
        stringBuilder.setLength(stringBuilder.length() - 2);
        return stringBuilder.toString();
    }

    /**
     * 删除带头结点的单链表的最小节点, 假设最小节点只有 1 个
     *
     * @param table 表
     */
    public static Optional<Integer> t4(SingleLinkTableWithHead<Integer> table) {
        if (table.empty()) {
            return Optional.empty();
        }

        var current = table.head.getNext();
        var minPre = table.head;
        var min = current;
        while (current != null) {
            if (current.getValue() < min.getValue()) {
                min = current;
                minPre = minPre.getNext();
            }
            current = current.getNext();
        }

        var minNext = min.getNext();
        if (minNext == null) {
            minPre.setNext(null);
        } else {
            minPre.setNext(minNext);
        }

        return Optional.of(min.getValue());
    }

    /**
     * 原地逆置带头结点的单链表
     *
     * @param node 头结点
     */
    private static <T> SingleLinkTableNode<T> reverse(SingleLinkTableNode<T> node) {
        if (node == null) {
            return null;
        }

        var next = reverse(node.getNext());
        if (next == null) {
            return node;
        } else {
            var tail = node.getNext();
            tail.setNext(node);
            node.setNext(null);
            return next;
        }
    }

    /**
     * 原地逆置带头结点的单链表
     *
     * @param table 表
     */
    public static <T> void t5(SingleLinkTableWithHead<T> table) {
        var node = reverse(table.head.getNext());
        table.head.setNext(node);
        table.node = node;
    }
}
