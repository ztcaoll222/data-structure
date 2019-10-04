package com.ztcaoll222.data.structure.c2.impl;

import java.util.Arrays;

/**
 * 带头结点的单链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/3 17:53
 */
public class SingleLinkTableWithHead<T> extends SingleLinkTable<T> {
    public SingleLinkTableNode<T> head = new SingleLinkTableNode<>();

    public SingleLinkTableWithHead() {
        head.setNext(node);
    }

    /**
     * 创建单链表
     */
    @SafeVarargs
    public static <T> SingleLinkTableWithHead<T> of(T... objs) {
        var table = new SingleLinkTableWithHead<T>();
        Arrays.stream(objs).forEach(table::listInsert);
        return table;
    }
}
