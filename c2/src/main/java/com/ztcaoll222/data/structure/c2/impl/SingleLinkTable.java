package com.ztcaoll222.data.structure.c2.impl;

import com.ztcaoll222.data.structure.c2.abs.AbstractLinkTable;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * 单链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/2 16:39
 */
public class SingleLinkTable<T> extends AbstractLinkTable<T> {
    public SingleLinkTableNode<T> node;

    @Override
    public int length() {
        if (empty()) {
            return 0;
        }
        var tNode = node;
        int count = 0;
        while (tNode != null) {
            count++;
            tNode = tNode.getNext();
        }
        return count;
    }

    @Override
    public Optional<SingleLinkTableNode<T>> locateElem(T value) {
        if (empty()) {
            return Optional.empty();
        }

        var tNode = node;
        while (tNode != null) {
            if (Objects.equals(value, tNode.getValue())) {
                break;
            }

            tNode = tNode.getNext();
        }

        return Optional.ofNullable(tNode);
    }

    @Override
    public Optional<SingleLinkTableNode<T>> findElem(int i) {
        if (empty() || i < 0) {
            return Optional.empty();
        }

        var tNode = node;
        try {
            for (int j = 1; j <= i; j++) {
                tNode = tNode.getNext();
            }
            return Optional.of(tNode);
        } catch (NullPointerException ignore) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> getElem(int i) {
        return findElem(i - 1).map(SingleLinkTableNode::getValue);
    }

    /**
     * 插入
     *
     * @param i     位置
     * @param datum 元素
     * @return 插入成功则返回 true, 否则返回 false
     */
    private boolean listInsert(int i, SingleLinkTableNode<T> datum) {
        if (i < 0) {
            return false;
        }

        if (empty()) {
            node = datum;
            return true;
        }

        var tNode = node;
        return listInsert(tNode, datum, i - 1);
    }

    @Override
    public boolean listInsert(int i, T value) {
        return listInsert(i, new SingleLinkTableNode<>(value));
    }

    public boolean listInsert(SingleLinkTableNode<T> datum) {
        if (empty()) {
            node = datum;
            return true;
        }

        var tNode = node;
        while (tNode.getNext() != null) {
            tNode = tNode.getNext();
        }
        tNode.setNext(datum);

        return true;
    }

    @Override
    public boolean listInsert(T value) {
        return listInsert(new SingleLinkTableNode<>(value));
    }

    @Override
    public Optional<SingleLinkTableNode<T>> listDeleteLast() {
        if (empty()) {
            return Optional.empty();
        }

        var tNode = node;
        while (tNode.getNext().getNext() != null) {
            tNode = tNode.getNext();
        }

        var next = tNode.getNext();
        tNode.setNext(null);
        return Optional.of(next);
    }

    @Override
    public String printList() {
        return printList(node);
    }

    @Override
    public boolean empty() {
        return node == null;
    }

    @Override
    public void destroyList() {
        node = null;
    }

    /**
     * 创建单链表
     */
    @SafeVarargs
    public static <T> SingleLinkTable<T> of(T... objs) {
        var table = new SingleLinkTable<T>();
        Arrays.stream(objs).forEach(table::listInsert);
        return table;
    }
}
