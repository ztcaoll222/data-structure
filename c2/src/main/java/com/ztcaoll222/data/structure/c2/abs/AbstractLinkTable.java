package com.ztcaoll222.data.structure.c2.abs;

import com.ztcaoll222.data.structure.c2.LinearTable;
import com.ztcaoll222.data.structure.c2.impl.SingleLinkTableNode;

import java.util.Optional;

/**
 * 链表的抽象类
 *
 * @author ztcaoll222
 * Create time: 2019/10/6 21:25
 */
public abstract class AbstractLinkTable<T> implements LinearTable<T> {
    /**
     * 按位查找
     *
     * @param i 位置
     * @return 返回第 i 个位置的元素
     */
    @Override
    public abstract Optional<SingleLinkTableNode<T>> findElem(int i);

    /**
     * 在单链表中的某一位插入一个节点
     *
     * @param node  单链表的第一个节点
     * @param datum 待插入的某一个节点
     * @param limit 位置
     */
    protected boolean listInsert(SingleLinkTableNode<T> node, SingleLinkTableNode<T> datum, int limit) {
        for (int j = 0; j < limit; j++) {
            node = node.getNext();
        }

        var next = node.getNext();
        if (next != null) {
            node.setNext(datum);
            datum.setNext(next);
        } else {
            node.setNext(datum);
        }

        return true;
    }

    @Override
    public Optional<SingleLinkTableNode<T>> listDelete(int i) {
        return findElem(i - 2).map(pre -> {
            var tNode = pre.getNext();
            if (tNode.getNext() == null) {
                pre.setNext(null);
            } else {
                pre.setNext(tNode.getNext());
            }
            return tNode;
        });
    }

    /**
     * 输出
     *
     * @param node 单链表的第一个节点
     */
    protected String printList(SingleLinkTableNode<T> node) {
        if (empty()) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        var tNode = node;
        while (tNode != null) {
            stringBuilder.append(tNode.getValue());
            tNode = tNode.getNext();
            if (tNode != null) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}
