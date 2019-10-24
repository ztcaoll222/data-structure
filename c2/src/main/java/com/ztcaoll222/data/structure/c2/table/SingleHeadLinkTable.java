package com.ztcaoll222.data.structure.c2.table;

import com.ztcaoll222.data.structure.c2.abs.AbstractLinkTable;
import com.ztcaoll222.data.structure.base.entity.Pair;
import com.ztcaoll222.data.structure.c2.entity.SingleNode;

import java.util.Optional;

/**
 * 带头结点的单链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/17 21:07
 */
public class SingleHeadLinkTable<T> extends AbstractLinkTable<SingleNode<T>, T> {
    public SingleNode<T> head = new SingleNode<>();

    @Override
    protected SingleNode<T> getFirst() {
        return head.getNext();
    }

    @Override
    protected boolean isEnd(SingleNode<T> node) {
        return node == null;
    }

    @Override
    protected SingleNode<T> getNext(SingleNode<T> node) {
        return node.getNext();
    }

    @SafeVarargs
    @Override
    protected final Optional<Pair<SingleNode<T>, SingleNode<T>>> createNode(T... values) {
        if (values.length == 0) {
            return Optional.empty();
        }

        var first = new SingleNode<>(values[0]);
        var current = first;
        for (int i = 1; i < values.length; i++) {
            var tNode = new SingleNode<>(values[i]);
            current.setNext(tNode);
            current = current.getNext();
        }

        return Optional.of(Pair.of(first, current));
    }

    @Override
    protected boolean listInsertFirst(Pair<SingleNode<T>, SingleNode<T>> pair) {
        var first = pair.getK();
        var tail = pair.getV();

        if (empty()) {
            head.setNext(first);
        } else {
            var currentFirst = head.getNext();
            head.setNext(first);
            tail.setNext(currentFirst);
        }
        return true;
    }

    @Override
    protected boolean listInsert(int i, Pair<SingleNode<T>, SingleNode<T>> pair) {
        if (i <= 1 || empty()) {
            listInsertFirst(pair);
            return true;
        }

        var datum = pair.getK();
        var datumLast = pair.getV();

        SingleNode<T> pre;
        var current = head.getNext();
        int j = 1;

        do {
            pre = current;
            current = current.getNext();
            j++;
        } while (current != null && j < i);

        if (i == j && current != null) {
            pre.setNext(datum);
            datumLast.setNext(current);
        } else {
            pre.setNext(datum);
        }

        return true;
    }

    @Override
    protected boolean listInsertLast(Pair<SingleNode<T>, SingleNode<T>> pair) {
        if (empty()) {
            return listInsertFirst(pair);
        }

        var datum = pair.getK();
        var tNode = head.getNext();
        while (tNode.getNext() != null) {
            tNode = tNode.getNext();
        }
        tNode.setNext(datum);
        return true;
    }

    @Override
    public Optional<SingleNode<T>> listDeleteFirst() {
        if (empty()) {
            return Optional.empty();
        }

        var tNode = head.getNext();
        head.setNext(tNode.getNext());
        return Optional.of(tNode);
    }

    @Override
    public Optional<SingleNode<T>> listDelete(int i) {
        if (i < 0) {
            return Optional.empty();
        }

        if (i == 1) {
            return listDeleteFirst();
        }

        var current = head.getNext();
        SingleNode<T> pre;
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
        } else {
            pre.setNext(null);
        }

        return Optional.of(current);
    }

    @Override
    public Optional<SingleNode<T>> listDeleteLast() {
        if (empty()) {
            return Optional.empty();
        }

        var first = head.getNext();
        if (first.getNext() == null) {
            head.setNext(null);
            return Optional.of(first);
        }

        var tNode = first;
        while (tNode.getNext().getNext() != null) {
            tNode = tNode.getNext();
        }

        var last = tNode.getNext();
        tNode.setNext(null);
        return Optional.of(last);
    }

    @Override
    public void destroyList() {
        head.setNext(null);
    }

    /**
     * 创建双链表
     */
    @SafeVarargs
    public static <T> SingleHeadLinkTable<T> of(T... args) {
        var table = new SingleHeadLinkTable<T>();
        return of(table, args);
    }
}
