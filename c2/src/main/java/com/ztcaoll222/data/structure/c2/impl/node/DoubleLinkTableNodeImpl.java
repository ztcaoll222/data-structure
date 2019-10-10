package com.ztcaoll222.data.structure.c2.impl.node;

import com.ztcaoll222.data.structure.c2.interfaces.node.DoubleLinkTableNode;
import com.ztcaoll222.data.structure.c2.interfaces.node.SingleLinkTableNode;
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
    private DoubleLinkTableNodeImpl<T> pre;
    private DoubleLinkTableNodeImpl<T> next;

    public DoubleLinkTableNodeImpl(T value) {
        this.value = value;
    }

    @Override
    public void setNext(SingleLinkTableNode<T> next) {
        this.next = (DoubleLinkTableNodeImpl<T>) next;
    }

    @Override
    public void setPre(DoubleLinkTableNode<T> pre) {
        this.pre = (DoubleLinkTableNodeImpl<T>) pre;
    }
}
