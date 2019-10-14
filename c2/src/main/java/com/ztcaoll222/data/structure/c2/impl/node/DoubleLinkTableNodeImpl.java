package com.ztcaoll222.data.structure.c2.impl.node;

import com.ztcaoll222.data.structure.c2.interfaces.node.DoubleLinkTableNode;
import lombok.Data;

/**
 * 双链表的一个节点
 *
 * @author ztcaoll222
 * Create time: 2019/10/2 22:29
 */
@Data
public class DoubleLinkTableNodeImpl<T> implements DoubleLinkTableNode<T> {
    private static final long serialVersionUID = -2028015147074696198L;

    private T value;
    private DoubleLinkTableNode<T> pre;
    private DoubleLinkTableNode<T> next;

    public DoubleLinkTableNodeImpl(T value) {
        this.value = value;
    }

    public DoubleLinkTableNodeImpl() {
    }
}
