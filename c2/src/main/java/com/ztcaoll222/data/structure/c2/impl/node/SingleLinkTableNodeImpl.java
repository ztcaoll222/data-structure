package com.ztcaoll222.data.structure.c2.impl.node;

import com.ztcaoll222.data.structure.c2.interfaces.node.SingleLinkTableNode;
import lombok.Data;

/**
 * 单链表的节点
 *
 * @author ztcaoll222
 * Create time: 2019/10/2 16:33
 */
@Data
public class SingleLinkTableNodeImpl<T> implements SingleLinkTableNode<T> {
    private static final long serialVersionUID = 2661177743848625492L;

    private T value;
    private SingleLinkTableNode<T> next;

    public SingleLinkTableNodeImpl(T value) {
        this.value = value;
    }

    public SingleLinkTableNodeImpl() {
    }
}
