package com.ztcaoll222.data.structure.c2.impl.table;

import com.ztcaoll222.data.structure.c2.abs.AbstractDoubleLinkTableLoop;
import com.ztcaoll222.data.structure.c2.impl.node.DoubleLinkTableNodeImpl;
import com.ztcaoll222.data.structure.c2.interfaces.node.DoubleLinkTableNode;

/**
 * 循环双链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/12 17:07
 */
public class DoubleLinkTableLoopWithHead<T> extends AbstractDoubleLinkTableLoop<T> {
    private int size = 0;
    public DoubleLinkTableNode<T> head = new DoubleLinkTableNodeImpl<>();

    @Override
    public int length() {
        return size;
    }

    @Override
    public DoubleLinkTableLoopWithHead<T> setFirst(DoubleLinkTableNode<T> datum) {
        if (datum == null) {
            head.setNext(head);
            head.setPre(head);
            size = 0;
            return this;
        }

        if (size != 0) {
            DoubleLinkTableNode<T> first = head.getNext();
            head.setNext(datum);
            datum.setPre(head);
            datum.setNext(first);
            first.setPre(datum);
        } else {
            head.setNext(datum);
            datum.setPre(head);
            datum.setNext(head);
            head.setPre(datum);
        }
        size++;
        return this;
    }

    @Override
    public DoubleLinkTableNode<T> getFirst() {
        return size == 0 ? null : head.getNext();
    }

    @Override
    protected void listInsertLastOpt(DoubleLinkTableNode<T> last, DoubleLinkTableNode<T> datum) {
        last.setNext(datum);
        datum.setPre(last);
        datum.setNext(head);
        head.setPre(datum);
        size++;
    }

    @Override
    protected void listInsertOpt(DoubleLinkTableNode<T> current, DoubleLinkTableNode<T> datum, DoubleLinkTableNode<T> next) {
        current.setNext(datum);
        datum.setPre(current);
        datum.setNext(next);
        next.setPre(datum);
        size++;
    }

    @Override
    protected DoubleLinkTableNode<T> listDeleteLastOpt(DoubleLinkTableNode<T> last) {
        if (size != 1) {
            DoubleLinkTableNode<T> pre = last.getPre();
            pre.setNext(head);
            head.setPre(pre);
            size--;
        } else {
            setFirst(null);
        }
        return last;
    }

    @Override
    protected void listDeleteOpt(DoubleLinkTableNode<T> current) {
        DoubleLinkTableNode<T> pre = current.getPre();
        DoubleLinkTableNode<T> next = current.getNext();
        pre.setNext(next);
        next.setPre(pre);
        size--;
    }

    @Override
    public boolean listInsert(int i, T value) {
        var datum = new DoubleLinkTableNodeImpl<>(value);
        return listInsert(i, datum);
    }

    @Override
    public void listInsertLast(T value) {
        var datum = new DoubleLinkTableNodeImpl<>(value);
        listInsertLast(datum);
    }

    @SafeVarargs
    public static <T> DoubleLinkTableLoopWithHead<T> of(T... args) {
        var table = new DoubleLinkTableLoopWithHead<T>();
        return of(table, args);
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public void destroyList() {
        setFirst(null);
    }
}
