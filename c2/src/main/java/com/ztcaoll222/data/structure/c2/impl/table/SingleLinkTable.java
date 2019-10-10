package com.ztcaoll222.data.structure.c2.impl.table;

import com.ztcaoll222.data.structure.c2.abs.AbstractLinkTable;
import com.ztcaoll222.data.structure.c2.impl.node.SingleLinkTableNodeImpl;

/**
 * 单链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/2 16:39
 */
public class SingleLinkTable<T> extends AbstractLinkTable<SingleLinkTableNodeImpl<T>, T> {
    public SingleLinkTableNodeImpl<T> node;

    @Override
    protected void setFirst(SingleLinkTableNodeImpl<T> datum) {
        node = datum;
    }

    @Override
    protected SingleLinkTableNodeImpl<T> getFirst() {
        return node;
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
    public static <T> SingleLinkTable<T> of(T... objs) {
        var table = new SingleLinkTable<T>();
        return of(table, objs);
    }
}
