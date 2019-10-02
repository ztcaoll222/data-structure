package com.ztcaoll222.data.structure.c2.impl;

import com.ztcaoll222.data.structure.c2.LinearTable;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * 单链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/2 16:39
 */
public class SingleLinkTable<T> implements LinearTable<T> {
    public int size = 0;
    public SingleLinkTableNode<T> node;

    @Override
    public int length() {
        return size;
    }

    @Override
    public Optional<SingleLinkTableNode<T>> locateElem(T value) {
        var tNode = node;
        while (tNode != null) {
            if (Objects.equals(value, tNode.getValue())) {
                return Optional.of(tNode);
            }
            tNode = tNode.getNext();
        }
        return Optional.empty();
    }

    /**
     * 获得某一位的节点
     */
    private Optional<SingleLinkTableNode<T>> findElem(int i) {
        if (size == 0) {
            return Optional.empty();
        }

        if (i < 0 || i > size - 1) {
            return Optional.empty();
        }

        var tNode = node;
        for (int j = 1; j <= i; j++) {
            tNode = tNode.getNext();
        }
        return Optional.of(tNode);
    }

    @Override
    public Optional<T> getElem(int i) {
        return findElem(i-1).map(SingleLinkTableNode::getValue);
    }

    /**
     * 插入
     *
     * @param i     位置
     * @param datum 元素
     * @return 插入成功则返回 true, 否则返回 false
     */
    private boolean listInsert(int i, SingleLinkTableNode<T> datum) {
        if (i < 0 || i > size) {
            return false;
        }

        if (size == 0) {
            node = datum;
            size++;
            return true;
        }

        var tNode = node;
        for (int j = 0; j < i - 1; j++) {
            tNode = tNode.getNext();
        }

        var next = tNode.getNext();
        if (next != null) {
            tNode.setNext(datum);
            datum.setNext(next);
        } else {
            tNode.setNext(datum);
        }

        size++;

        return true;
    }

    @Override
    public boolean listInsert(int i, T value) {
        return listInsert(i, new SingleLinkTableNode<>(value));
    }

    @Override
    public boolean listInsert(T value) {
        return listInsert(size, value);
    }

    @Override
    public Optional<SingleLinkTableNode<T>> listDeleteLast() {
        return findElem(size - 2).map(pre -> {
            var tNode = pre.getNext();
            pre.setNext(null);
            size--;
            return tNode;
        });
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
            size--;
            return tNode;
        });
    }

    @Override
    public String printList() {
        if (size == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        var tNode = node;
        for (int i = 1; i < size + 1; i++) {
            stringBuilder.append(tNode.getValue());
            if (i < size) {
                stringBuilder.append(", ");
            }
            tNode = tNode.getNext();
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public void destroyList() {
        size = 0;
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
