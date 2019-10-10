package com.ztcaoll222.data.structure.c2.impl.table;

import com.ztcaoll222.data.structure.c2.abs.AbstractLinkTable;
import com.ztcaoll222.data.structure.c2.impl.node.SingleLinkTableNodeImpl;

/**
 * 带头结点的单链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/3 17:53
 */
public class SingleLinkTableWithHead<T> extends AbstractLinkTable<SingleLinkTableNodeImpl<T>, T> {
    public SingleLinkTableNodeImpl<T> head = new SingleLinkTableNodeImpl<>();

    private SingleLinkTableWithHead() {
    }

    @Override
    protected void setFirst(SingleLinkTableNodeImpl<T> datum) {
        head.setNext(datum);
    }

    @Override
    protected SingleLinkTableNodeImpl<T> getFirst() {
        return head.getNext();
    }

    @Override
    public boolean listInsert(int i, T value) {
        return listInsert(i, new SingleLinkTableNodeImpl<>(value));
    }

    @Override
    public boolean listInsertLast(T value) {
        return listInsertLast(new SingleLinkTableNodeImpl<>(value));
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
