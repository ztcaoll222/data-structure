package com.ztcaoll222.data.structure.c2.abs;

/**
 * 循环双链表的抽象类
 *
 * @author ztcaoll222
 * Create time: 2019/10/14 10:36
 */
public abstract class AbstractDoubleLinkTableLoop<T> extends AbstractDoubleLinkTable<T> {
    /**
     * 判空
     *
     * @return 若为空, 则返回 true, 否则 false
     */
    @Override
    public abstract boolean empty();

    /**
     * 销毁
     */
    @Override
    public abstract void destroyList();
}
