package com.ztcaoll222.data.structure.c2;

import com.ztcaoll222.data.structure.c2.func.FunctionTwoOne;
import com.ztcaoll222.data.structure.c2.impl.table.SingleLinkTable;
import com.ztcaoll222.data.structure.c2.impl.node.SingleLinkTableNodeImpl;
import com.ztcaoll222.data.structure.c2.impl.table.SingleLinkTableWithHead;

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
    private static <T> SingleLinkTableNodeImpl<T> deleteAllX(SingleLinkTableNodeImpl<T> node, T x) {
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
    private static <T> SingleLinkTableNodeImpl<T> reversePrint(SingleLinkTableNodeImpl<T> node,
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
    private static <T> SingleLinkTableNodeImpl<T> reverse(SingleLinkTableNodeImpl<T> node) {
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
    }

    /**
     * 合并两个节点
     *
     * @param left  左节点
     * @param right 右节点
     */
    private static SingleLinkTableNodeImpl<Integer> merge(SingleLinkTableNodeImpl<Integer> left,
                                                          SingleLinkTableNodeImpl<Integer> right) {
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        var res = new SingleLinkTableNodeImpl<Integer>();
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
     * 原地合并
     *
     * @param left  左节点
     * @param right 右节点
     */
    private static SingleLinkTableNodeImpl<Integer> mergeInSitu(SingleLinkTableNodeImpl<Integer> left,
                                                                SingleLinkTableNodeImpl<Integer> right) {
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        SingleLinkTableNodeImpl<Integer> res = null, tNode = null;
        while (left != null && right != null) {
            if (left.getValue() < right.getValue()) {
                if (res == null) {
                    tNode = res = left;
                } else {
                    tNode.setNext(left);
                    tNode = tNode.getNext();
                }
                left = left.getNext();
            } else {
                if (res == null) {
                    tNode = res = right;
                } else {
                    tNode.setNext(right);
                    tNode = tNode.getNext();
                }
                right = right.getNext();
            }
        }

        if (left != null) {
            tNode.setNext(left);
        }
        if (right != null) {
            tNode.setNext(right);
        }

        return res;
    }

    /**
     * 归并排序
     *
     * @param node 节点
     */
    private static SingleLinkTableNodeImpl<Integer> sort(SingleLinkTableNodeImpl<Integer> node,
                                                         FunctionTwoOne<SingleLinkTableNodeImpl<Integer>,
                                                                 SingleLinkTableNodeImpl<Integer>,
                                                                 SingleLinkTableNodeImpl<Integer>> func) {
        if (node == null || node.getNext() == null) {
            return node;
        }

        SingleLinkTableNodeImpl<Integer> walker = node, walkerPre = node, runner = node;
        while (runner != null && runner.getNext() != null) {
            runner = runner.getNext().getNext();
            walkerPre = walker;
            walker = walker.getNext();
        }
        walkerPre.setNext(null);

        var left = sort(node, func);
        var right = sort(walker, func);

        return func.execute(left, right);
    }

    /**
     * 使带头结点的单链表单调递增
     *
     * @param table 表
     */
    public static void t6(SingleLinkTableWithHead<Integer> table) {
        var node = sort(table.head.getNext(), T2372::merge);
        table.head.setNext(node);
    }

    /**
     * 带头结点的单链表删除大于 @param a 并且小于 @param b 的节点
     *
     * @return 被删掉的个数
     */
    public static int t7(SingleLinkTableWithHead<Integer> table, int a, int b) {
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
    private static <T> Optional<SingleLinkTableNodeImpl<T>> findCommonNode(SingleLinkTableNodeImpl<T> a,
                                                                           SingleLinkTableNodeImpl<T> b,
                                                                           int difference) {
        SingleLinkTableNodeImpl<T> res = null;
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
    public static <T> Optional<SingleLinkTableNodeImpl<T>> t8(SingleLinkTable<T> a, SingleLinkTable<T> b) {
        int aLen = a.length();
        int bLen = b.length();
        int difference = Math.abs(aLen - bLen);
        if (aLen > bLen) {
            return findCommonNode(b.node, a.node, difference);
        } else {
            return findCommonNode(a.node, b.node, difference);
        }
    }

    /**
     * 顺序输出抬带头节点的单链表, 要求空间复杂度的 o(1)
     *
     * @param table 单链表
     */
    public static String t9(SingleLinkTableWithHead<Integer> table) {
        if (table.empty()) {
            return "";
        }

        var node = sort(table.head.getNext(), T2372::mergeInSitu);
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
    public static <T> Pair<SingleLinkTableWithHead<T>, SingleLinkTableWithHead<T>> t10(SingleLinkTableWithHead<T> table) {
        var a = new SingleLinkTableWithHead<T>();
        var b = new SingleLinkTableWithHead<T>();

        int count = 1;
        var tNode = table.getFirst();
        while (tNode != null) {
            var pre = tNode;
            tNode = tNode.getNext();
            pre.setNext(null);

            if ((count & 1) == 1) {
                a.listInsertLast(pre);
            } else {
                b.listInsertLast(pre);
            }
            count++;
        }

        return Pair.of(a,b);
    }


    /**
     * 分解带头结点的单链表
     * 使得 a 表获得序号为奇数的节点, b 表获得序号为偶数的节点
     * 要求原地
     *
     * @param table 被分解的带头结点的单链表
     * @return <序号为奇数的带头结点的单链表, 序号为偶数的带头结点的单链表>
     */
    public static <T> Pair<SingleLinkTable<T>, SingleLinkTable<T>> t11(SingleLinkTableWithHead<T> table) {
        var a = new SingleLinkTable<T>();
        var b = new SingleLinkTable<T>();

        var tNode = table.getFirst();
        while (tNode != null && tNode.getNext() != null) {
            var pre0 = tNode;
            var pre1 = tNode.getNext();
            tNode = tNode.getNext().getNext();
            pre0.setNext(null);
            pre1.setNext(null);

            a.listInsertLast(pre0);
            b.listInsertLast(pre1);
        }

        if (tNode != null) {
            a.listInsertLast(tNode);
        }

        return Pair.of(a, b);
    }
}
