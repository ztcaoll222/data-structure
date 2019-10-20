package com.ztcaoll222.data.structure.c2.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 带访问频度的双链表的节点
 *
 * @author ztcaoll222
 * Create time: 2019/10/20 17:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DoubleNodeX<T> extends DoubleNode<T> {
    private static final long serialVersionUID = -4538287469607433132L;

    private int freq = 0;

    public DoubleNodeX(T value) {
        super(value);
    }
}
