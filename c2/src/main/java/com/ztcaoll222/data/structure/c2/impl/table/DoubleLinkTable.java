package com.ztcaoll222.data.structure.c2.impl.table;

import com.ztcaoll222.data.structure.c2.abs.AbstractLinkTable;
import com.ztcaoll222.data.structure.c2.impl.node.DoubleLinkTableNodeImpl;

/**
 * 双链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/2 22:29
 */
public class DoubleLinkTable<T> extends AbstractLinkTable<DoubleLinkTableNodeImpl<T>, T> {
    private DoubleLinkTableNodeImpl<T> node;

    @Override
    public void setFirst(DoubleLinkTableNodeImpl<T> datum) {
        node = datum;
    }

    @Override
    public DoubleLinkTableNodeImpl<T> getFirst() {
        return node;
    }

    @Override
    public boolean listInsert(int i, T value) {
        return listInsert(i, new DoubleLinkTableNodeImpl<>(value));
    }

    @Override
    public boolean listInsertLast(T value) {
        return listInsertLast(new DoubleLinkTableNodeImpl<>(value));
    }

    /**
     * 创建双链表
     */
    @SafeVarargs
    public static <T> DoubleLinkTable<T> of(T... objs) {
        var table = new DoubleLinkTable<T>();
        return of(table, objs);
    }
}
