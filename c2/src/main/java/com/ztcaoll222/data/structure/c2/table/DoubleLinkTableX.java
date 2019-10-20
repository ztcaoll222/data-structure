package com.ztcaoll222.data.structure.c2.table;

import com.ztcaoll222.data.structure.c2.abs.AbstractLinkTable;
import com.ztcaoll222.data.structure.c2.entity.DoubleNodeX;
import com.ztcaoll222.data.structure.c2.entity.Pair;

import java.util.Optional;

/**
 * 带访问频度的双链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/15 20:28
 */
public class DoubleLinkTableX<T> extends AbstractLinkTable<DoubleNodeX<T>, T> {
    public DoubleNodeX<T> first;

    private void setFirst(DoubleNodeX<T> first) {
        this.first = first;
    }

    @Override
    protected DoubleNodeX<T> getFirst() {
        return first;
    }

    @Override
    protected boolean isEnd(DoubleNodeX<T> node) {
        return node == null;
    }

    @Override
    protected DoubleNodeX<T> getNext(DoubleNodeX<T> node) {
        return (DoubleNodeX<T>) node.getNext();
    }

    /**
     * 处理频度
     * 如果当前节点的频度大于前导节点, 那么向前移一位
     *
     * @param elem 当前节点
     * @return 处理过后的当前节点
     */
    private DoubleNodeX<T> handleFreq(DoubleNodeX<T> elem) {
        elem.setFreq(elem.getFreq() + 1);
        var pre = (DoubleNodeX<T>) elem.getPre();
        var next = (DoubleNodeX<T>) elem.getNext();
        if (pre != null && pre.getFreq() < elem.getFreq()) {
            var prePre = pre.getPre();

            if (prePre != null) {
                prePre.setNext(elem);
                elem.setPre(prePre);
            } else {
                setFirst(elem);
                elem.setPre(null);
            }

            elem.setNext(pre);
            pre.setPre(elem);

            pre.setNext(next);
            if (next != null) {
                next.setPre(pre);
            }
        }
        return elem;
    }

    @Override
    public Optional<DoubleNodeX<T>> locateElem(T value) {
        return super.locateElem(value).map(this::handleFreq);
    }

    @Override
    public Optional<DoubleNodeX<T>> findElem(int i) {
        return super.findElem(i).map(this::handleFreq);
    }

    @SafeVarargs
    @Override
    protected final Optional<Pair<DoubleNodeX<T>, DoubleNodeX<T>>> createNode(T... values) {
        if (values.length == 0) {
            return Optional.empty();
        }

        var first = new DoubleNodeX<>(values[0]);
        var current = first;
        for (int i = 1; i < values.length; i++) {
            var tNode = new DoubleNodeX<>(values[i]);
            current.setNext(tNode);
            tNode.setPre(current);
            current = getNext(current);
        }

        return Optional.of(Pair.of(first, current));
    }

    @Override
    protected boolean listInsertFirst(Pair<DoubleNodeX<T>, DoubleNodeX<T>> pair) {
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
    protected boolean listInsert(int i, Pair<DoubleNodeX<T>, DoubleNodeX<T>> pair) {
        if (i <= 1 || empty()) {
            listInsertFirst(pair);
            return true;
        }

        var datum = pair.getK();
        var datumLast = pair.getV();

        DoubleNodeX<T> pre;
        var current = first;
        int j = 1;

        do {
            pre = current;
            current = getNext(current);
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
    protected boolean listInsertLast(Pair<DoubleNodeX<T>, DoubleNodeX<T>> pair) {
        if (empty()) {
            return listInsertFirst(pair);
        }

        var datum = pair.getK();
        var tNode = first;
        while (tNode.getNext() != null) {
            tNode = getNext(tNode);
        }
        tNode.setNext(datum);
        datum.setPre(tNode);
        return true;
    }

    @Override
    public Optional<DoubleNodeX<T>> listDeleteFirst() {
        if (empty()) {
            return Optional.empty();
        }

        var tNode = first;
        first = getNext(first);
        if (first != null) {
            first.setPre(null);
        }
        return Optional.of(tNode);
    }

    @Override
    public Optional<DoubleNodeX<T>> listDelete(int i) {
        if (i < 0) {
            return Optional.empty();
        }

        if (i == 1) {
            return listDeleteFirst();
        }

        var current = first;
        DoubleNodeX<T> pre;
        int j = 1;
        do {
            pre = current;
            current = getNext(current);
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
    public Optional<DoubleNodeX<T>> listDeleteLast() {
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
            tNode = getNext(tNode);
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
     * 创建带访问频度的双链表
     */
    @SafeVarargs
    public static <T> DoubleLinkTableX<T> of(T... args) {
        var table = new DoubleLinkTableX<T>();
        return of(table, args);
    }
}
