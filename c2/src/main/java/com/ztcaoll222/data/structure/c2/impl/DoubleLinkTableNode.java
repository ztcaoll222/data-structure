package com.ztcaoll222.data.structure.c2.impl;

import com.ztcaoll222.data.structure.c2.Elem;
import lombok.Data;

/**
 * 双链表的一个节点
 *
 * @author ztcaoll222
 * Create time: 2019/10/2 22:29
 */
@Data
public class DoubleLinkTableNode<T> implements Elem<T> {
    private static final long serialVersionUID = -2028015147074696198L;

    private T value;
    private DoubleLinkTableNode<T> pre;
    private DoubleLinkTableNode<T> next;

    public DoubleLinkTableNode(T value) {
        this.value = value;
    }
}
