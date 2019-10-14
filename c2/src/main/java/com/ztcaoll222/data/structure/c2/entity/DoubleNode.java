package com.ztcaoll222.data.structure.c2.entity;

import com.ztcaoll222.data.structure.c2.interfaces.Elem;
import lombok.Data;

/**
 * 双链表的一个节点
 *
 * @author ztcaoll222
 * Create time: 2019/10/2 22:29
 */
@Data
public class DoubleNode<T> implements Elem<T> {
    private static final long serialVersionUID = -2028015147074696198L;

    private T value;
    private DoubleNode<T> pre;
    private DoubleNode<T> next;

    public DoubleNode(T value) {
        this.value = value;
    }

    public DoubleNode() {
    }
}
