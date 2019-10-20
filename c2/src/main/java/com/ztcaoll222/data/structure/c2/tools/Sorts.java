package com.ztcaoll222.data.structure.c2.tools;

import com.ztcaoll222.data.structure.c2.entity.SingleNode;
import com.ztcaoll222.data.structure.c2.func.FunctionThreeOne;
import lombok.extern.flogger.Flogger;

/**
 * 排序工具类
 *
 * @author ztcaoll222
 * Create time: 2019/10/20 11:55
 */
@Flogger
public class Sorts {
    private Sorts() {
    }

    /**
     * 合并两个节点
     *
     * @param left      左节点
     * @param right     右节点
     * @param endSignal 用于结束循环的信号
     */
    public static SingleNode<Integer> merge(SingleNode<Integer> left,
                                            SingleNode<Integer> right,
                                            SingleNode<Integer> endSignal) {
        if (left == endSignal && right == endSignal) {
            return endSignal;
        }
        if (left == endSignal) {
            return right;
        }
        if (right == endSignal) {
            return left;
        }

        var res = new SingleNode<Integer>();
        var tNode = res;
        while (left != endSignal && right != endSignal) {
            if (left.getValue() < right.getValue()) {
                tNode.setNext(left);
                left = left.getNext();
            } else {
                tNode.setNext(right);
                right = right.getNext();
            }
            tNode = tNode.getNext();
        }

        if (left != endSignal) {
            tNode.setNext(left);
        }
        if (right != endSignal) {
            tNode.setNext(right);
        }
        return res.getNext();
    }

    /**
     * 原地合并
     *
     * @param left      左节点
     * @param right     右节点
     * @param endSignal 用于结束循环的信号
     */
    public static SingleNode<Integer> mergeInSitu(SingleNode<Integer> left,
                                                  SingleNode<Integer> right,
                                                  SingleNode<Integer> endSignal) {
        if (left == endSignal && right == endSignal) {
            return endSignal;
        }
        if (left == endSignal) {
            return right;
        }
        if (right == endSignal) {
            return left;
        }

        SingleNode<Integer> res = endSignal, tNode = endSignal;
        while (left != endSignal && right != endSignal) {
            if (left.getValue() < right.getValue()) {
                if (res == endSignal) {
                    tNode = res = left;
                } else {
                    tNode.setNext(left);
                    tNode = tNode.getNext();
                }
                left = left.getNext();
            } else {
                if (res == endSignal) {
                    tNode = res = right;
                } else {
                    tNode.setNext(right);
                    tNode = tNode.getNext();
                }
                right = right.getNext();
            }
        }

        if (left != endSignal) {
            tNode.setNext(left);
        }
        if (right != endSignal) {
            tNode.setNext(right);
        }

        return res;
    }

    /**
     * 归并排序
     *
     * @param node 节点
     */
    public static SingleNode<Integer> mergeSort(SingleNode<Integer> node,
                                                FunctionThreeOne<SingleNode<Integer>,
                                                        SingleNode<Integer>,
                                                        SingleNode<Integer>,
                                                        SingleNode<Integer>> func,
                                                SingleNode<Integer> endSignal) {
        if (node == endSignal || node.getNext() == endSignal) {
            return node;
        }

        SingleNode<Integer> walker = node, walkerPre = node, runner = node;
        while (runner != endSignal && runner.getNext() != endSignal) {
            runner = runner.getNext().getNext();
            walkerPre = walker;
            walker = walker.getNext();
        }
        walkerPre.setNext(endSignal);

        var left = mergeSort(node, func, endSignal);
        var right = mergeSort(walker, func, endSignal);

        return func.execute(left, right, endSignal);
    }
}
