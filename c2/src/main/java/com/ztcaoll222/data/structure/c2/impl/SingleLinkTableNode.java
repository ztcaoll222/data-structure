package com.ztcaoll222.data.structure.c2.impl;

import com.ztcaoll222.data.structure.c2.Elem;
import lombok.Data;

/**
 * 单链表的节点
 *
 * @author ztcaoll222
 * Create time: 2019/10/2 16:33
 */
@Data
public class SingleLinkTableNode<T> implements Elem<T> {
    private static final long serialVersionUID = 2661177743848625492L;

    private T value;

    private SingleLinkTableNode<T> next;

    public SingleLinkTableNode(T value) {
        this.value = value;
    }
}
