package com.ztcaoll222.data.structure.c3;

import com.ztcaoll222.data.structure.base.entity.SeqElem;
import com.ztcaoll222.data.structure.c3.queue.SeqLoopQueue;
import com.ztcaoll222.data.structure.c3.stack.SeqStack;

/**
 * 第三章队列综合题
 *
 * @author ztcaoll222
 * Create time: 2019/10/25 17:16
 */
public class T3252 {
    private T3252() {
    }

    /**
     * 通过栈, 将一个队列逆置
     *
     * @param queue 队列
     */
    public static <T> void t2(SeqLoopQueue<T> queue) {
        int length = queue.length();
        var stack = new SeqStack<T>(length);
        for (int i = 0; i < length; i++) {
            queue.deQueue().map(SeqElem::getValue).ifPresent(stack::push);
        }

        for (int i = 0; i < length; i++) {
            stack.pop().ifPresent(queue::enQueue);
        }
    }
}
