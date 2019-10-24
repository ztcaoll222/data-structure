package com.ztcaoll222.data.structure.c2.table;

import com.ztcaoll222.data.structure.c2.abs.AbstractLinkTable;
import com.ztcaoll222.data.structure.c2.entity.DoubleNode;
import com.ztcaoll222.data.structure.base.entity.Pair;

import java.util.Optional;

/**
 * 循环双链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/19 12:19
 */
public class DoubleLoopLinkTable<T> extends AbstractLinkTable<DoubleNode<T>, T> {
    public DoubleNode<T> first;

    @Override
    protected DoubleNode<T> getFirst() {
        return first;
    }

    @Override
    protected boolean isEnd(DoubleNode<T> node) {
        return node == first;
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

        current.setNext(first);
        first.setPre(current);

        return Optional.of(Pair.of(first, current));
    }

    @Override
    protected boolean listInsertFirst(Pair<DoubleNode<T>, DoubleNode<T>> pair) {
        var datumFirst = pair.getK();
        var datumTail = pair.getV();

        if (empty()) {
            first = datumFirst;
            first.setPre(datumTail);
        } else {
            var tail = first.getPre();
            datumTail.setNext(first);
            first.setPre(datumTail);
            tail.setNext(datumFirst);
            datumFirst.setPre(tail);

            first = datumFirst;
        }
        return true;
    }

    @Override
    protected boolean listInsert(int i, Pair<DoubleNode<T>, DoubleNode<T>> pair) {
        if (i <= 1 || empty()) {
            return listInsertFirst(pair);
        }

        var datumFirst = pair.getK();
        var datumTail = pair.getV();

        DoubleNode<T> pre;
        var current = getFirst();
        int j = 1;
        do {
            pre = current;
            current = getNext(current);
            j++;
        } while (!isEnd(current) && j < i);

        if (i == j && !isEnd(current)) {
            pre.setNext(datumFirst);
            datumFirst.setPre(pre);

            datumTail.setNext(current);
            current.setPre(datumTail);
        } else {
            pre.setNext(datumFirst);
            datumFirst.setPre(pre);
            datumTail.setNext(first);
            first.setPre(datumTail);
        }

        return true;
    }

    @Override
    protected boolean listInsertLast(Pair<DoubleNode<T>, DoubleNode<T>> pair) {
        if (empty()) {
            return listInsertFirst(pair);
        }

        var datumFirst = pair.getK();
        var datumTail = pair.getV();

        var tail = first.getPre();
        tail.setNext(datumFirst);
        datumFirst.setPre(tail);
        datumTail.setNext(first);
        first.setPre(datumTail);
        return true;
    }

    @Override
    public Optional<DoubleNode<T>> listDeleteFirst() {
        if (empty()) {
            return Optional.empty();
        }

        if (first.getPre() == first) {
            var res = first;
            first = null;
            return Optional.of(res);
        }

        var current = first;
        var tail = first.getPre();
        var next = first.getNext();
        tail.setNext(next);
        next.setPre(tail);
        first = next;
        return Optional.of(current);
    }

    @Override
    public Optional<DoubleNode<T>> listDelete(int i) {
        if (i < 0) {
            return Optional.empty();
        }

        if (i == 1) {
            return listDeleteFirst();
        }

        var current = getFirst();
        DoubleNode<T> pre;
        int j = 1;
        do {
            pre = current;
            current = getNext(current);
            j++;
        } while (!isEnd(current) && j < i);

        if (isEnd(current)) {
            return Optional.empty();
        }

        var next = getNext(current);
        pre.setNext(next);
        next.setPre(pre);

        return Optional.of(current);
    }

    @Override
    public Optional<DoubleNode<T>> listDeleteLast() {
        if (empty()) {
            return Optional.empty();
        }

        if (first.getPre() == first) {
            var res = first;
            first = null;
            return Optional.of(res);
        }

        var tail = first.getPre();
        var pre = tail.getPre();
        pre.setNext(first);
        first.setPre(pre);
        return Optional.of(tail);
    }

    @Override
    public boolean empty() {
        return first == null;
    }

    @Override
    public void destroyList() {
        first = null;
    }

    /**
     * 创建循环双链表
     */
    @SafeVarargs
    public static <T> DoubleLoopLinkTable<T> of(T... objs) {
        var table = new DoubleLoopLinkTable<T>();
        return of(table, objs);
    }
}
