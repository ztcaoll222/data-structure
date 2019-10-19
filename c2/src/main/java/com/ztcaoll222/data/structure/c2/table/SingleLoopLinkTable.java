package com.ztcaoll222.data.structure.c2.table;

import com.ztcaoll222.data.structure.c2.abs.AbstractLinkTable;
import com.ztcaoll222.data.structure.c2.entity.Pair;
import com.ztcaoll222.data.structure.c2.entity.SingleNode;

import java.util.Optional;

/**
 * 循环单链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/19 20:43
 */
public class SingleLoopLinkTable<T> extends AbstractLinkTable<SingleNode<T>, T> {
    public SingleNode<T> tail;

    @Override
    protected SingleNode<T> getFirst() {
        return tail.getNext();
    }

    @Override
    protected boolean isEnd(SingleNode<T> node) {
        return node == tail.getNext();
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

        var first = new SingleNode<T>(values[0]);
        var current = first;
        for (int i = 1; i < values.length; i++) {
            current.setNext(new SingleNode<>(values[i]));
            current = getNext(current);
        }

        current.setNext(first);

        return Optional.of(Pair.of(first, current));
    }

    @Override
    protected boolean listInsertFirst(Pair<SingleNode<T>, SingleNode<T>> pair) {
        var datumFirst = pair.getK();
        var datumTail = pair.getV();

        if (empty()) {
            tail = datumTail;
        } else {
            var first = getFirst();
            datumTail.setNext(first);
            tail.setNext(datumFirst);
        }
        return true;
    }

    @Override
    protected boolean listInsert(int i, Pair<SingleNode<T>, SingleNode<T>> pair) {
        if (i <= 1 || empty()) {
            return listInsertFirst(pair);
        }

        var datumFirst = pair.getK();
        var datumTail = pair.getV();

        SingleNode<T> pre;
        var current = getFirst();
        int j = 1;
        do {
            pre = current;
            current = getNext(current);
            j++;
        } while (!isEnd(current) && j < i);

        if (i == j && !isEnd(current)) {
            pre.setNext(datumFirst);
            datumTail.setNext(current);
        } else {
            var first = getFirst();
            tail.setNext(datumFirst);
            datumTail.setNext(first);
            tail = datumTail;
        }
        return true;
    }

    @Override
    protected boolean listInsertLast(Pair<SingleNode<T>, SingleNode<T>> pair) {
        if (empty()) {
            return listInsertFirst(pair);
        }

        var datumFirst = pair.getK();
        var datumTail = pair.getV();

        var first = getFirst();
        tail.setNext(datumFirst);
        datumTail.setNext(first);
        tail = datumTail;
        return true;
    }

    @Override
    public Optional<SingleNode<T>> listDeleteFirst() {
        if (empty()) {
            return Optional.empty();
        }

        if (tail == getFirst()) {
            var res = tail;
            tail = null;
            return Optional.of(res);
        }

        var first = getFirst();
        tail.setNext(first.getNext());

        return Optional.of(first);
    }

    @Override
    public Optional<SingleNode<T>> listDelete(int i) {
        if (i < 0) {
            return Optional.empty();
        }

        if (i == 1) {
            return listDeleteFirst();
        }

        var current = getFirst();
        SingleNode<T> pre;
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

        if (current == tail) {
            tail = pre;
        }

        return Optional.of(current);
    }

    @Override
    public Optional<SingleNode<T>> listDeleteLast() {
        if (empty()) {
            return Optional.empty();
        }

        var tNode = getFirst();
        while (tNode.getNext() != tail) {
            tNode = getNext(tNode);
        }

        if (tail == getFirst()) {
            var res = tail;
            tail = null;
            return Optional.of(res);
        }

        var res = tail;
        var first = getFirst();
        tNode.setNext(first);
        tail = tNode;
        return Optional.of(res);
    }

    @Override
    public boolean empty() {
        return tail == null;
    }

    @Override
    public void destroyList() {
        tail = null;
    }

    /**
     * 创建循环单链表
     */
    @SafeVarargs
    public static <T> SingleLoopLinkTable<T> of(T... args) {
        var table = new SingleLoopLinkTable<T>();
        return of(table, args);
    }
}
