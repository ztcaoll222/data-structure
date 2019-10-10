package com.ztcaoll222.data.structure.c2.interfaces.node;

/**
 * 双链表节点的接口
 *
 * @author ztcaoll222
 * Create time: 2019/10/10 11:42
 */
public interface DoubleLinkTableNode<T> extends SingleLinkTableNode<T> {
    /**
     * 获得上一个节点
     *
     * @return 上一个节点
     */
    DoubleLinkTableNode<T> getPre();

    /**
     * 设置上一个节点
     *
     * @param pre 上一个节点
     */
    void setPre(DoubleLinkTableNode<T> pre);
}
