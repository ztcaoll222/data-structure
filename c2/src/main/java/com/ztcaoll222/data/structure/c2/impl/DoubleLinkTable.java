package com.ztcaoll222.data.structure.c2.impl;

import com.ztcaoll222.data.structure.c2.LinearTable;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * 双链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/2 22:29
 */
public class DoubleLinkTable<T> implements LinearTable<T> {
    public int size = 0;
    public DoubleLinkTableNode<T> node;

    @Override
    public int length() {
        return size;
    }

    @Override
    public Optional<DoubleLinkTableNode<T>> locateElem(T value) {
        if (empty()) {
            return Optional.empty();
        }

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
    public Optional<DoubleLinkTableNode<T>> findElem(int i) {
        if (empty()) {
            return Optional.empty();
        }

        if (i < 0 || i > size) {
            return Optional.empty();
        }

        var tNode = node;
        for (int j = 1; j < i; j++) {
            tNode = tNode.getNext();
        }

        return Optional.of(tNode);
    }

    @Override
    public Optional<T> getElem(int i) {
        return findElem(i).map(DoubleLinkTableNode::getValue);
    }

    /**
     * 插入
     *
     * @param i     位置
     * @param datum 元素
     * @return 插入成功则返回 true, 否则返回 false
     */
    private boolean listInsert(int i, DoubleLinkTableNode<T> datum) {
        if (i < 0 || i > size) {
            return false;
        }

        if (size == 0) {
            node = datum;
            size++;
            return true;
        }

        var tNode = node;
        for (int j = 1; j < i; j++) {
            tNode = tNode.getNext();
        }

        var next = tNode.getNext();
        tNode.setNext(datum);
        datum.setPre(tNode);
        if (next != null) {
            datum.setNext(next);
            next.setPre(datum);
        }

        size++;
        return true;
    }

    @Override
    public boolean listInsert(int i, T value) {
        return listInsert(i, new DoubleLinkTableNode<>(value));
    }

    @Override
    public boolean listInsert(T value) {
        return listInsert(size, value);
    }

    @Override
    public Optional<DoubleLinkTableNode<T>> listDeleteLast() {
        return findElem(size).map(last -> {
            var pre = last.getPre();
            pre.setNext(null);
            size--;
            return last;
        });
    }

    @Override
    public Optional<DoubleLinkTableNode<T>> listDelete(int i) {
        return findElem(i).map(at -> {
            var pre = at.getPre();
            var next = at.getNext();
            if (next == null) {
                pre.setNext(null);
            } else {
                pre.setNext(next);
                next.setPre(pre);
            }
            size--;
            return at;
        });
    }

    @Override
    public String printList() {
        if (empty()) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        var tNode = node;
        for (int i = 1; i <= size; i++) {
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
        node = null;
        size = 0;
    }

    /**
     * 创建双链表
     */
    @SafeVarargs
    public static <T> DoubleLinkTable<T> of(T... objs) {
        var table = new DoubleLinkTable<T>();
        Arrays.stream(objs).forEach(table::listInsert);
        return table;
    }
}
