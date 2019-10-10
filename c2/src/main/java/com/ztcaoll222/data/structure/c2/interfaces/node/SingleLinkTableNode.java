package com.ztcaoll222.data.structure.c2.interfaces.node;

/**
 * 单链表节点接口
 *
 * @author ztcaoll222
 * Create time: 2019/10/10 11:40
 */
public interface SingleLinkTableNode<T> extends Elem<T> {
    /**
     * 获得下一个节点
     *
     * @return 下一个节点
     */
    <B extends SingleLinkTableNode<T>> B getNext();

    /**
     * 设置下一个节点
     *
     * @param next 下一个节点
     */
    void setNext(SingleLinkTableNode<T> next);
}
