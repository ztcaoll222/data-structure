package com.ztcaoll222.data.structure.c2.exceptions;

import com.ztcaoll222.data.structure.c2.entity.DoubleNode;

/**
 * 双链表的节点的异常
 *
 * @author ztcaoll222
 * Create time: 2019/10/16 23:11
 */
public class DoubleNodeException extends RuntimeException {
    private static final long serialVersionUID = 2187644193558200387L;

    public DoubleNodeException(DoubleNode<?> node) {
        super(String.format("The pre of DoubleNode: %s is empty", node.getValue()));
    }
}
