package com.ztcaoll222.data.structure.c3.interfaces;

import com.ztcaoll222.data.structure.base.interfaces.Elem;

import java.util.Optional;

/**
 * @author ztcaoll222
 * Create time: 2019/10/23 22:02
 */
public interface Queue<B extends Elem<T>, T> {
    /**
     * 判队列空
     *
     * @return 空则返回 true, 否则 false
     */
    boolean queueEmpty();

    /**
     * 队列长度
     *
     * @return 长度
     */
    int length();

    /**
     * 入队
     *
     * @param values 值数组
     * @return 成功返回 true, 否则 false
     */
    boolean enQueue(T[] values);

    /**
     * 出队
     *
     * @return 队头
     */
    Optional<B> deQueue();

    /**
     * 读队头元素
     *
     * @return 队头
     */
    Optional<B> getHead();
}
