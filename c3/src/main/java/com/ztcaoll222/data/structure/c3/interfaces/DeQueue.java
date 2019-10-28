package com.ztcaoll222.data.structure.c3.interfaces;

import java.util.Optional;

/**
 * @author ztcaoll222
 * Create time: 2019/10/23 22:02
 */
public interface DeQueue<T> {
    /**
     * 获得双端队列的最后一个元素
     *
     * @return 元素的值
     */
    Optional<T> getTail();

    /**
     * 将一个元素添加到双端队列尾部
     *
     * @param values 元素
     * @return 成功返回 true, 否则 false
     */
    boolean enQueueHead(T[] values);

    /**
     * 从双端队列队尾出队
     *
     * @return 元素的值
     */
    Optional<T> deQueueTail();
}
