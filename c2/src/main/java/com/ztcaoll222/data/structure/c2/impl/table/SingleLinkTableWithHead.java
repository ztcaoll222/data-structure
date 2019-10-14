package com.ztcaoll222.data.structure.c2.impl.table;

import com.ztcaoll222.data.structure.c2.abs.AbstractSingleLinkTable;
import com.ztcaoll222.data.structure.c2.impl.node.SingleLinkTableNodeImpl;
import com.ztcaoll222.data.structure.c2.interfaces.node.SingleLinkTableNode;

/**
 * 带头结点的单链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/3 17:53
 */
public class SingleLinkTableWithHead<T> extends AbstractSingleLinkTable<T> {
    public SingleLinkTableNode<T> head = new SingleLinkTableNodeImpl<>();

    @Override
    public SingleLinkTableWithHead<T> setFirst(SingleLinkTableNode<T> datum) {
        if (datum == null) {
            head.setNext(null);
            return this;
        }

        if (head.getNext() != null) {
            datum.setNext(head.getNext());
        }
        head.setNext(datum);
        return this;
    }

    @Override
    public SingleLinkTableNode<T> getFirst() {
        return head.getNext();
    }

    @Override
    protected void listInsertLastOpt(SingleLinkTableNode<T> last, SingleLinkTableNode<T> datum) {
        last.setNext(datum);
    }

    @Override
    protected void listInsertOpt(SingleLinkTableNode<T> current, SingleLinkTableNode<T> datum, SingleLinkTableNode<T> next) {
        current.setNext(datum);
        datum.setNext(next);
    }

    @Override
    protected SingleLinkTableNode<T> listDeleteLastOpt(SingleLinkTableNode<T> pre) {
        if (pre == null) {
            var tNode = head.getNext();
            head.setNext(null);
            return tNode;
        } else {
            var tNode = pre.getNext();
            pre.setNext(null);
            return tNode;
        }
    }

    @Override
    protected void listDeleteOpt(SingleLinkTableNode<T> pre) {
        if (pre != null) {
            var next = pre.getNext().getNext();
            pre.setNext(next);
        } else {
            head.setNext(head.getNext().getNext());
        }
    }

    @Override
    public boolean listInsert(int i, T value) {
        return listInsert(i, new SingleLinkTableNodeImpl<>(value));
    }

    @Override
    public void listInsertLast(T value) {
        listInsertLast(new SingleLinkTableNodeImpl<>(value));
    }

    /**
     * 创建单链表
     */
    @SafeVarargs
    public static <T> SingleLinkTableWithHead<T> of(T... objs) {
        var table = new SingleLinkTableWithHead<T>();
        return of(table, objs);
    }
}
