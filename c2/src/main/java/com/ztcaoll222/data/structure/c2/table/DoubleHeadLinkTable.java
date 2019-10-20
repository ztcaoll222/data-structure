package com.ztcaoll222.data.structure.c2.table;

import com.ztcaoll222.data.structure.c2.abs.AbstractLinkTable;
import com.ztcaoll222.data.structure.c2.entity.DoubleNode;
import com.ztcaoll222.data.structure.c2.entity.Pair;

import java.util.Optional;

/**
 * 带头结点的双链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/16 22:08
 */
public class DoubleHeadLinkTable<T> extends AbstractLinkTable<DoubleNode<T>, T> {
    public DoubleNode<T> head = new DoubleNode<>();

    @Override
    protected DoubleNode<T> getFirst() {
        return head.getNext();
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
        var first = pair.getK();
        var tail = pair.getV();

        if (empty()) {
            head.setNext(first);
            first.setPre(head);
        } else {
            var currentFirst = head.getNext();
            head.setNext(first);
            first.setPre(head);
            tail.setNext(currentFirst);
            currentFirst.setPre(tail);
        }
        return true;
    }

    @Override
    protected boolean listInsert(int i, Pair<DoubleNode<T>, DoubleNode<T>> pair) {
        if (i <= 1 || empty()) {
            return listInsertFirst(pair);
        }

        var datum = pair.getK();
        var datumLast = pair.getV();

        DoubleNode<T> pre;
        var current = head.getNext();
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
        var tNode = head.getNext();
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

        var tNode = head.getNext();
        var next = tNode.getNext();
        head.setNext(next);
        if (next != null) {
            next.setPre(head);
        }
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

        var current = head.getNext();
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

        var first = head.getNext();
        if (first.getNext() == null) {
            head.setNext(null);
            return Optional.of(first);
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
        head.setNext(null);
    }

    /**
     * 创建带头结点的双链表
     */
    @SafeVarargs
    public static <T> DoubleHeadLinkTable<T> of(T... args) {
        var table = new DoubleHeadLinkTable<T>();
        return of(table, args);
    }
}
