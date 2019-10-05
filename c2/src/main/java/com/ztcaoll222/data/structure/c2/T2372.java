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

    /**
     * 合并两个节点
     *
     * @param left  左节点
     * @param right 右节点
     */
    private static SingleLinkTableNode<Integer> merge(SingleLinkTableNode<Integer> left, SingleLinkTableNode<Integer> right) {
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        var res = new SingleLinkTableNode<Integer>();
        var tNode = res;
        while (left != null && right != null) {
            if (left.getValue() < right.getValue()) {
                tNode.setNext(left);
                left = left.getNext();
            } else {
                tNode.setNext(right);
                right = right.getNext();
            }
            tNode = tNode.getNext();
        }

        if (left != null) {
            tNode.setNext(left);
        }
        if (right != null) {
            tNode.setNext(right);
        }
        return res.getNext();
    }

    /**
     * 归并排序
     *
     * @param node 节点
     */
    private static SingleLinkTableNode<Integer> sort(SingleLinkTableNode<Integer> node) {
        if (node == null || node.getNext() == null) {
            return node;
        }

        SingleLinkTableNode<Integer> walker = node, walkerPre = node, runner = node;
        while (runner != null && runner.getNext() != null) {
            runner = runner.getNext().getNext();
            walkerPre = walker;
            walker = walker.getNext();
        }
        walkerPre.setNext(null);

        var left = sort(node);
        var right = sort(walker);

        return merge(left, right);
    }

    /**
     * 使带头结点的单链表单调递增
     *
     * @param table 表
     */
    public static void t6(SingleLinkTableWithHead<Integer> table) {
        var node = sort(table.head.getNext());
        table.head.setNext(node);
        table.node = node;
    }

    /**
     * 带头结点的单链表删除大于 @param a 并且小于 @param b 的节点
     *
     * @return 被删掉的个数
     */
    public static int t7(SingleLinkTableWithHead<Integer> table, int a, int b) {
        int count = 0;
        var pre = table.head;
        var current = table.head.getNext();
        while (current != null) {
            var currentValue = current.getValue();
            if (currentValue > a && currentValue < b) {
                pre.setNext(current.getNext());
                current = current.getNext();
                count++;
            } else {
                pre = current;
                current = current.getNext();
            }
        }
        return count;
    }
}
