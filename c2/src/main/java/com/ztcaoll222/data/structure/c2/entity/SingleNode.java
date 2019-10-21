package com.ztcaoll222.data.structure.c2.entity;

import com.ztcaoll222.data.structure.base.interfaces.Elem;
import lombok.Data;

/**
 * 单链表的节点
 *
 * @author ztcaoll222
 * Create time: 2019/10/2 16:33
 */
@Data
public class SingleNode<T> implements Elem<T> {
    private static final long serialVersionUID = 2661177743848625492L;

    private T value;
    private SingleNode<T> next;

    public SingleNode(T value) {
        this.value = value;
    }

    public SingleNode() {
    }
}
