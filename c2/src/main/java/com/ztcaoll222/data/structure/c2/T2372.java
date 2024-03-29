package com.ztcaoll222.data.structure.c2;

import com.ztcaoll222.data.structure.c2.entity.DoubleNodeX;
import com.ztcaoll222.data.structure.base.entity.Pair;
import com.ztcaoll222.data.structure.c2.entity.SingleNode;
import com.ztcaoll222.data.structure.c2.table.*;
import com.ztcaoll222.data.structure.c2.tools.Sorts;

import java.util.BitSet;
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
    private static <T> SingleNode<T> deleteAllX(SingleNode<T> node, T x) {
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
        deleteAllX(table.first, x);
    }

    /**
     * 删除带头结点的单链表中所有值为 @param x 的节点
     *
     * @param table 表
     * @param x     值
     */
    public static <T> void t2(SingleHeadLinkTable<T> table, T x) {
        deleteAllX(table.head.getNext(), x);
    }

    /**
     * 逆置输出获得带头结点的单链表
     */
    private static <T> SingleNode<T> reversePrint(SingleNode<T> node,
                                                  StringBuilder stringBuilder) {
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
    public static <T> String t3(SingleHeadLinkTable<T> table) {
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
    public static Optional<Integer> t4(SingleHeadLinkTable<Integer> table) {
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
    private static <T> SingleNode<T> reverse(SingleNode<T> node) {
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
    public static <T> void t5(SingleHeadLinkTable<T> table) {
        var node = reverse(table.head.getNext());
        table.head.setNext(node);
    }

    /**
     * 使带头结点的单链表单调递增
     *
     * @param table 表
     */
    public static void t6(SingleHeadLinkTable<Integer> table) {
        var node = Sorts.mergeSort(table.head.getNext(), Sorts::merge, null);
        table.head.setNext(node);
    }

    /**
     * 带头结点的单链表删除大于 @param a 并且小于 @param b 的节点
     *
     * @return 被删掉的个数
     */
    public static int t7(SingleHeadLinkTable<Integer> table, int a, int b) {
        int count = 0;
        var pre = table.head;
        var current = pre.getNext();
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

    /**
     * 找到两个单链表的公共节点
     *
     * @param a          a 链表的第一个节点, 短的那条
     * @param b          b 链表的第一个节点, 长的那条
     * @param difference 两条链表长度的差数
     */
    private static <T> Optional<SingleNode<T>> findCommonNode(SingleNode<T> a,
                                                              SingleNode<T> b,
                                                              int difference) {
        SingleNode<T> res = null;
        var aCurrent = a;
        var bCurrent = b;
        int count = 0;
        while (count < difference) {
            bCurrent = bCurrent.getNext();
            count++;
        }

        while (aCurrent != null) {
            if (Objects.equals(aCurrent, bCurrent)) {
                res = aCurrent;
                break;
            }
            aCurrent = aCurrent.getNext();
            bCurrent = bCurrent.getNext();
        }

        return Optional.ofNullable(res);
    }

    /**
     * 找到两个单链表的公共节点
     *
     * @param a a 链表
     * @param b b 链表
     */
    public static <T> Optional<SingleNode<T>> t8(SingleLinkTable<T> a, SingleLinkTable<T> b) {
        int aLen = a.length();
        int bLen = b.length();
        int difference = Math.abs(aLen - bLen);
        if (aLen > bLen) {
            return findCommonNode(b.first, a.first, difference);
        } else {
            return findCommonNode(a.first, b.first, difference);
        }
    }

    /**
     * 顺序输出抬带头节点的单链表, 要求空间复杂度的 o(1)
     *
     * @param table 单链表
     */
    public static String t9(SingleHeadLinkTable<Integer> table) {
        if (table.empty()) {
            return "";
        }

        var node = Sorts.mergeSort(table.head.getNext(), Sorts::mergeInSitu, null);
        table.head.setNext(node);
        return table.printList();
    }

    /**
     * 分解带头结点的单链表
     * 使得 a 表获得序号为奇数的节点, b 表获得序号为偶数的节点
     *
     * @param table 被分解的带头结点的单链表
     * @return <序号为奇数的带头结点的单链表, 序号为偶数的带头结点的单链表>
     */
    public static <T> Pair<SingleHeadLinkTable<T>, SingleHeadLinkTable<T>> t10(SingleHeadLinkTable<T> table) {
        var a = new SingleHeadLinkTable<T>();
        var b = new SingleHeadLinkTable<T>();

        int count = 1;
        var tNode = table.head.getNext();
        while (tNode != null) {
            var pre = tNode;
            tNode = tNode.getNext();
            pre.setNext(null);

            if ((count & 1) == 1) {
                a.listInsertLast(pre.getValue());
            } else {
                b.listInsertLast(pre.getValue());
            }
            count++;
        }

        return Pair.of(a, b);
    }


    /**
     * 分解带头结点的单链表
     * 使得 a 表获得序号为奇数的节点, b 表获得序号为偶数的节点
     * 要求原地
     *
     * @param table 被分解的带头结点的单链表
     * @return <序号为奇数的带头结点的单链表, 序号为偶数的带头结点的单链表>
     */
    public static <T> Pair<SingleLinkTable<T>, SingleLinkTable<T>> t11(SingleHeadLinkTable<T> table) {
        var a = new SingleLinkTable<T>();
        var b = new SingleLinkTable<T>();

        var tNode = table.head.getNext();
        while (tNode != null && tNode.getNext() != null) {
            var pre0 = tNode;
            var pre1 = tNode.getNext();
            tNode = tNode.getNext().getNext();
            pre0.setNext(null);
            pre1.setNext(null);

            a.listInsertLast(pre0.getValue());
            b.listInsertLast(pre1.getValue());
        }

        if (tNode != null) {
            a.listInsertLast(tNode.getValue());
        }

        return Pair.of(a, b);
    }

    /**
     * 去重有序单链表中的重复数据
     *
     * @param table 有序单链表
     */
    public static void t12(SingleLinkTable<Integer> table) {
        if (table.empty()) {
            return;
        }

        var pre = table.first;
        var current = pre.getNext();
        while (pre != null && current != null) {
            if (Objects.equals(pre.getValue(), current.getValue())) {
                current = current.getNext();
                pre.setNext(current);
            } else {
                pre = pre.getNext();
                current = current.getNext();
            }
        }
    }

    /**
     * 合并两个递增单链表, 使其递减
     *
     * @param a 递增单链表
     * @param b 递增单链表
     * @return 递减单链表
     */
    public static SingleLinkTable<Integer> t13(SingleLinkTable<Integer> a, SingleLinkTable<Integer> b) {
        var aNode = a.first;
        var bNode = b.first;
        SingleNode<Integer> res = null;
        while (aNode != null && bNode != null) {
            SingleNode<Integer> current;
            if (aNode.getValue() < bNode.getValue()) {
                current = aNode;
                aNode = aNode.getNext();
                current.setNext(null);
            } else {
                current = bNode;
                bNode = bNode.getNext();
                current.setNext(null);
            }
            if (res == null) {
                res = current;
            } else {
                current.setNext(res);
                res = current;
            }
        }

        if (aNode != null) {
            while (aNode != null) {
                var current = aNode;
                aNode = aNode.getNext();
                current.setNext(res);
                res = current;
            }
        }
        if (bNode != null) {
            while (bNode != null) {
                var current = bNode;
                bNode = bNode.getNext();
                current.setNext(res);
                res = current;
            }
        }

        var table = new SingleLinkTable<Integer>();
        table.first = res;
        return table;
    }

    /**
     * 获得两个带头结点的单链表的重复部分, 且不破坏原链表的顺序
     *
     * @param a 带头结点的单链表
     * @param b 带头结点的单链表
     * @return a 和 b 从重复节点组成的带头结点的单链表
     */
    public static SingleHeadLinkTable<Integer> t14(SingleHeadLinkTable<Integer> a, SingleHeadLinkTable<Integer> b) {
        var aNode = a.head.getNext();
        var bNode = b.head.getNext();

        var res = new SingleHeadLinkTable<Integer>();
        var tNode = res.head.getNext();
        while (aNode != null && bNode != null) {
            if (Objects.equals(aNode.getValue(), bNode.getValue())) {
                if (tNode == null) {
                    res.listInsertFirst(aNode.getValue());
                    tNode = res.head.getNext();
                } else {
                    tNode.setNext(new SingleNode<>(aNode.getValue()));
                    tNode = tNode.getNext();
                }
            }

            aNode = aNode.getNext();
            bNode = bNode.getNext();
        }

        return res;
    }

    /**
     * 求两个集合(元素不重复)的并集, 存放在 a 集合中
     *
     * @param a 集合
     * @param b 集合
     */
    public static void t15(SingleHeadLinkTable<Integer> a, SingleHeadLinkTable<Integer> b) {
        var aPre = a.head;
        var aNode = aPre.getNext();
        var bPre = b.head;
        var bNode = bPre.getNext();

        while (aNode != null && bNode != null) {
            if (aNode.getValue() < bNode.getValue()) {
                aPre.setNext(aNode.getNext());
                aNode = aNode.getNext();
            } else if (aNode.getValue() > bNode.getValue()) {
                bPre.setNext(bNode.getNext());
                bNode = bNode.getNext();
            } else {
                aPre = aPre.getNext();
                aNode = aNode.getNext();
            }
        }

        if (aNode != null) {
            aPre.setNext(null);
        }
    }

    /**
     * 判断 @param b 是否是 @param a 的连续子序列
     *
     * @param a 单链表 a
     * @param b 单链表 b
     */
    public static <T> boolean t16(SingleLinkTable<T> a, SingleLinkTable<T> b) {
        var aNode = a.first;
        var bNode = b.first;

        while (aNode != null && !Objects.equals(aNode.getValue(), bNode.getValue())) {
            aNode = aNode.getNext();
        }

        if (aNode == null) {
            return false;
        }

        while (aNode != null && bNode != null) {
            if (!Objects.equals(aNode.getValue(), bNode.getValue())) {
                return false;
            }
            aNode = aNode.getNext();
            bNode = bNode.getNext();
        }

        return bNode == null;
    }

    /**
     * 判断循环双链表是否对称
     *
     * @param table 循环双链表
     */
    public static <T> boolean t17(DoubleHeadLoopLinkTable<T> table) {
        var head = table.head;
        var left = head.getPre();
        var right = head.getNext();

        while (!Objects.equals(left, right) && !Objects.equals(left.getPre(), right)) {
            if (!Objects.equals(left.getValue(), right.getValue())) {
                return false;
            }

            left = left.getPre();
            right = right.getNext();
        }

        return true;
    }

    /**
     * 将 @param b 链接到 @param a 之后
     *
     * @param a 循环单链表 a
     * @param b 循环单链表 b
     */
    public static <T> void t18(SingleLoopLinkTable<T> a, SingleLoopLinkTable<T> b) {
        var aFirst = a.tail.getNext();
        var bFirst = b.tail.getNext();

        a.tail.setNext(bFirst);
        b.tail.setNext(aFirst);
        a.tail = b.tail;
    }

    /**
     * 从小到大输出某个带头结点的循环单链表, 并删除
     *
     * @param table 循环单链表
     */
    public static String t19(SingleHeadLoopLinkTable<Integer> table) {
        var node = Sorts.mergeSort(table.head.getNext(), Sorts::merge, table.head);
        table.head.setNext(node);

        var sb = new StringBuilder();
        while (!table.empty()) {
            var tNode = table.listDeleteFirst();
            sb.append(tNode.get().getValue());
        }
        return sb.toString();
    }

    /**
     * 实现一个基于双链表的 LRU cache
     *
     * @param table 双链表
     * @param value 元素的值
     */
    public static <T> Optional<DoubleNodeX<T>> t20(DoubleLinkTableX<T> table, T value) {
        return table.locateElem(value);
    }

    /**
     * 获得带头结点的单链表的倒数 @param k 位的节点
     *
     * @param table 带头结点的单链表
     * @param k     倒数第几位
     */
    public static <T> Optional<SingleNode<T>> t21(SingleHeadLinkTable<T> table, int k) {
        if (k <= 1 || table.empty()) {
            return Optional.empty();
        }

        SingleNode<T> runner, walker;
        walker = runner = table.head.getNext();

        try {
            for (int i = 0; i < k; i++) {
                runner = runner.getNext();
            }
        } catch (NullPointerException ignore) {
            return Optional.empty();
        }

        while (runner != null) {
            walker = walker.getNext();
            runner = runner.getNext();
        }

        return Optional.of(walker);
    }

    /**
     * 找到两个单链表的公共节点
     *
     * @param a a 链表
     * @param b b 链表
     */
    public static <T> Optional<SingleNode<T>> t22(SingleLinkTable<T> a, SingleLinkTable<T> b) {
        return t8(a, b);
    }

    /**
     * 删除在单链表中绝对值相等的节点, 节点中的值的绝对值不超过 @param n
     *
     * @param table 单链表
     * @param n     节点中的绝对值最大的大小
     */
    public static void t23(SingleLinkTable<Integer> table, int n) {
        if (table.empty()) {
            return;
        }

        var log = new BitSet(n);

        SingleNode<Integer> tNode = table.first;
        SingleNode<Integer> pre = null;
        do {
            var value = Math.abs(tNode.getValue());
            if (value >= n) {
                throw new RuntimeException(String.format("value: %d is bigger than n: %d", value, n));
            }
            if (!log.get(value)) {
                log.set(value);
                pre = tNode;
                tNode = tNode.getNext();
            } else {
                pre.setNext(tNode.getNext());
                tNode = tNode.getNext();
            }
        } while (tNode != null);
    }
}
