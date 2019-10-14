package com.ztcaoll222.data.structure.c2.interfaces.node;

/**
 * 双链表节点的接口
 *
 * @author ztcaoll222
 * Create time: 2019/10/10 11:42
 */
public interface DoubleLinkTableNode<T> extends SingleLinkTableNode<T> {
    /**
     * 设置下一个节点
     *
     * @param next 下一个节点
     */
    void setNext(DoubleLinkTableNode<T> next);

    /**
     * 设置下一个节点
     *
     * @param next 下一个节点
     */
    @Override
    default void setNext(SingleLinkTableNode<T> next) {
        setNext((DoubleLinkTableNode<T>) next);
    }

    /**
     * 获得上一个节点
     *
     * @return 上一个节点
     */
    <B extends DoubleLinkTableNode<T>> B getPre();

    /**
     * 设置上一个节点
     *
     * @param pre 上一个节点
     */
    void setPre(DoubleLinkTableNode<T> pre);
}
