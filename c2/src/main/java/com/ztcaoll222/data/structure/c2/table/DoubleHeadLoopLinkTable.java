package com.ztcaoll222.data.structure.c2.table;

import com.ztcaoll222.data.structure.c2.abs.AbstractLinkTable;
import com.ztcaoll222.data.structure.c2.entity.DoubleNode;
import com.ztcaoll222.data.structure.c2.entity.Pair;

import java.util.Optional;

/**
 * 带头结点的循环双链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/16 22:36
 */
public class DoubleHeadLoopLinkTable<T> extends AbstractLinkTable<DoubleNode<T>, T> {
    public DoubleNode<T> head;

    public DoubleHeadLoopLinkTable() {
        this.head = new DoubleNode<>();
        head.setNext(head);
        head.setPre(head);
    }

    @Override
    protected DoubleNode<T> getFirst() {
        return head.getNext();
    }

    @Override
    protected boolean isEnd(DoubleNode<T> node) {
        return node == head;
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
        var tail = pair.getV();

        if (empty()) {
            head.setNext(datumFirst);
            datumFirst.setPre(head);
            tail.setNext(head);
            head.setPre(tail);
        } else {
            var first = head.getNext();
            head.setNext(datumFirst);
            datumFirst.setPre(head);
            tail.setNext(first);
            first.setPre(tail);
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
        var current = head.getNext();
        int j = 1;

        do {
            pre = current;
            current = current.getNext();
            j++;
        } while (current != head && j < i);

        if (i == j && current != head) {
            pre.setNext(datum);
            datum.setPre(pre);

            datumLast.setNext(current);
            current.setPre(datumLast);
        } else {
            pre.setNext(datum);
            datum.setPre(pre);
            datum.setNext(head);
            head.setPre(datum);
        }

        return true;
    }

    @Override
    protected boolean listInsertLast(Pair<DoubleNode<T>, DoubleNode<T>> pair) {
        if (empty()) {
            return listInsertFirst(pair);
        }

        var tail = head.getPre();
        var datum = pair.getK();
        var datumTail = pair.getV();
        tail.setNext(datum);
        datum.setPre(tail);

        datumTail.setNext(head);
        head.setPre(datumTail);
        return true;
    }

    @Override
    public Optional<DoubleNode<T>> listDeleteFirst() {
        if (empty()) {
            return Optional.empty();
        }

        var tNode = head.getNext();
        head.setNext(tNode.getNext());
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
        } while (current != head && j < i);

        if (current == head) {
            return Optional.empty();
        }

        var next = current.getNext();
        if (next != head) {
            pre.setNext(next);
            next.setPre(pre);
        } else {
            pre.setNext(head);
        }

        return Optional.of(current);
    }

    @Override
    public Optional<DoubleNode<T>> listDeleteLast() {
        if (empty()) {
            return Optional.empty();
        }

        var first = head.getNext();
        if (first.getNext() == head) {
            head.setNext(head);
            head.setPre(head);
            return Optional.of(first);
        }

        var tNode = first;
        while (tNode.getNext() != head) {
            tNode = tNode.getNext();
        }

        var pre = tNode.getPre();
        pre.setNext(head);
        head.setPre(pre);
        return Optional.of(tNode);
    }

    @Override
    public void destroyList() {
        head.setNext(head);
        head.setPre(head);
    }

    /**
     * 创建带头结点的循环双链表
     */
    @SafeVarargs
    public static <T> DoubleHeadLoopLinkTable<T> of(T... args) {
        var table = new DoubleHeadLoopLinkTable<T>();
        return of(table, args);
    }
}
