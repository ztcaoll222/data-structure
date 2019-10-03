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
    public SingleLinkTableNode<T> node;

    @Override
    public int length() {
        if (node == null) {
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
        var tNode = node;
        while (tNode != null) {
            if (Objects.equals(value, tNode.getValue())) {
                return Optional.of(tNode);
            }
            tNode = tNode.getNext();
        }
        return Optional.empty();
    }

    @Override
    public Optional<SingleLinkTableNode<T>> findElem(int i) {
        if (node == null || i < 0) {
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

        if (node == null) {
            node = datum;
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

        return true;
    }

    @Override
    public boolean listInsert(int i, T value) {
        return listInsert(i, new SingleLinkTableNode<>(value));
    }

    @Override
    public boolean listInsert(T value) {
        var datum = new SingleLinkTableNode<>(value);

        if (node == null) {
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
    public Optional<SingleLinkTableNode<T>> listDeleteLast() {
        if (node == null) {
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

    @Override
    public String printList() {
        if (node == null) {
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
