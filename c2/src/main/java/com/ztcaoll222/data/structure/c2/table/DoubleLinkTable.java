package com.ztcaoll222.data.structure.c2.table;

import com.ztcaoll222.data.structure.c2.abs.AbstractLinkTable;
import com.ztcaoll222.data.structure.c2.entity.DoubleNode;
import com.ztcaoll222.data.structure.c2.entity.Pair;

import java.util.Optional;

/**
 * 双链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/15 20:28
 */
public class DoubleLinkTable<T> extends AbstractLinkTable<DoubleNode<T>, T> {
    private DoubleNode<T> first;

    @Override
    protected DoubleNode<T> getFirst() {
        return first;
    }

    @Override
    protected boolean isEnd(DoubleNode<T> node) {
        return node == null;
    }

    @Override
    protected DoubleNode<T> getNext(DoubleNode<T> node) {
        return node.getNext();
    }

    @SafeVarargs
    @Override
    protected final Optional<Pair<DoubleNode<T>, DoubleNode<T>>> createNode(T... values) {
        if (values.length == 0) {
            return Optional.empty();
        }

        var first = new DoubleNode<>(values[0]);
        var current = first;
        for (int i = 1; i < values.length; i++) {
            var tNode = new DoubleNode<>(values[i]);
            current.setNext(tNode);
            tNode.setPre(current);
            current = current.getNext();
        }

        return Optional.of(Pair.of(first, current));
    }

    @Override
    protected boolean listInsertFirst(Pair<DoubleNode<T>, DoubleNode<T>> pair) {
        var datumFirst = pair.getK();
        var tail = pair.getV();

        if (empty()) {
            first = datumFirst;
        } else {
            var currentFirst = first;
            first = datumFirst;
            tail.setNext(currentFirst);
            currentFirst.setPre(tail);
        }

        return true;
    }

    @Override
    protected boolean listInsert(int i, Pair<DoubleNode<T>, DoubleNode<T>> pair) {
        if (i <= 1 || empty()) {
            listInsertFirst(pair);
            return true;
        }

        var datum = pair.getK();
        var datumLast = pair.getV();

        DoubleNode<T> pre;
        var current = first;
        int j = 1;

        do {
            pre = current;
            current = current.getNext();
            j++;
        } while (current != null && j < i);

        if (i == j && current != null) {
            pre.setNext(datum);
            datum.setPre(pre);

            datumLast.setNext(current);
            current.setPre(datumLast);
        } else {
            pre.setNext(datum);
            datum.setPre(pre);
        }

        return true;
    }

    @Override
    protected boolean listInsertLast(Pair<DoubleNode<T>, DoubleNode<T>> pair) {
        if (empty()) {
            return listInsertFirst(pair);
        }

        var datum = pair.getK();
        var tNode = first;
        while (tNode.getNext() != null) {
            tNode = tNode.getNext();
        }
        tNode.setNext(datum);
        datum.setPre(tNode);
        return true;
    }

    @Override
    public Optional<DoubleNode<T>> listDeleteFirst() {
        if (empty()) {
            return Optional.empty();
        }

        var tNode = first;
        first = first.getNext();
        return Optional.of(tNode);
    }

    @Override
    public Optional<DoubleNode<T>> listDelete(int i) {
        if (i < 0) {
            return Optional.empty();
        }

        if (i == 1) {
            return listDeleteFirst();
        }

        var current = first;
        DoubleNode<T> pre;
        int j = 1;
        do {
            pre = current;
            current = current.getNext();
            j++;
        } while (current != null && j < i);

        if (current == null) {
            return Optional.empty();
        }

        var next = current.getNext();
        if (next != null) {
            pre.setNext(next);
            next.setPre(pre);
        } else {
            pre.setNext(null);
        }

        return Optional.of(current);
    }

    @Override
    public Optional<DoubleNode<T>> listDeleteLast() {
        if (empty()) {
            return Optional.empty();
        }

        if (first.getNext() == null) {
            var tNode = first;
            first = null;
            return Optional.of(tNode);
        }

        var tNode = first;
        while (tNode.getNext() != null) {
            tNode = tNode.getNext();
        }

        var pre = tNode.getPre();
        pre.setNext(null);
        return Optional.of(tNode);
    }

    @Override
    public void destroyList() {
        first = null;
    }

    /**
     * 创建双链表
     */
    @SafeVarargs
    public static <T> DoubleLinkTable<T> of(T... args) {
        var table = new DoubleLinkTable<T>();
        return of(table, args);
    }
}
