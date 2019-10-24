package com.ztcaoll222.data.structure.c3.queue;

import com.ztcaoll222.data.structure.base.Config;
import com.ztcaoll222.data.structure.c3.entity.SeqElem;
import com.ztcaoll222.data.structure.c3.interfaces.Queue;

import java.util.Arrays;
import java.util.Optional;

/**
 * 循环顺序队列
 *
 * @author ztcaoll222
 * Create time: 2019/10/24 9:29
 */
public class SeqLoopQueue<T> implements Queue<SeqElem<T>, T> {
    private SeqElem<T>[] data;
    private int maxSize;
    private int front = 0;
    private int tail = 0;

    public SeqLoopQueue(int maxSize) {
        this.maxSize = maxSize;
        data = new SeqElem[this.maxSize];
    }

    public SeqLoopQueue() {
        this.maxSize = Config.DEFAULT_COLLECTION_SIZE;
        data = new SeqElem[this.maxSize];
    }

    @Override
    public boolean queueEmpty() {
        return front == tail;
    }

    /**
     * 入队
     *
     * @param elem 元素
     * @return 成功返回 true, 否则 false
     */
    private boolean enQueue(SeqElem<T> elem) {
        if ((tail + 1) % maxSize == front) {
            return false;
        }

        data[tail] = elem;
        tail = (tail + 1) % maxSize;
        return true;
    }

    @SafeVarargs
    @Override
    public final boolean enQueue(T... values) {
        return Arrays.stream(values).allMatch(value -> enQueue(new SeqElem<>(value)));
    }

    @Override
    public Optional<SeqElem<T>> deQueue() {
        if (queueEmpty()) {
            return Optional.empty();
        }

        var res = data[front];
        front = (front + 1) % maxSize;
        return Optional.of(res);
    }

    @Override
    public Optional<SeqElem<T>> getHead() {
        if (queueEmpty()) {
            return Optional.empty();
        }
        return Optional.of(data[front]);
    }

    @Override
    public String toString() {
        if (queueEmpty()) {
            return "";
        }

        var sb = new StringBuilder();
        int i = front;
        while (i < tail) {
            sb.append(data[i].getValue());
            sb.append(", ");
            i = (i + 1) % maxSize;
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }
}
