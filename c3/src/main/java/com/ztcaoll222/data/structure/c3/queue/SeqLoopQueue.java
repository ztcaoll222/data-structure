package com.ztcaoll222.data.structure.c3.queue;

import com.ztcaoll222.data.structure.base.Config;
import com.ztcaoll222.data.structure.base.entity.SeqElem;
import com.ztcaoll222.data.structure.c3.interfaces.Queue;
import com.ztcaoll222.data.structure.c3.interfaces.SeqQueue;

import java.util.Arrays;
import java.util.Optional;

/**
 * 循环顺序队列
 *
 * @author ztcaoll222
 * Create time: 2019/10/24 9:29
 */
public class SeqLoopQueue<T> implements Queue<T>, SeqQueue {
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
        if (maxSize == 0) {
            return false;
        }

        return front == tail;
    }

    @Override
    public boolean queueOverFlow() {
        if (maxSize == 0) {
            return true;
        }

        return (tail + 1) % maxSize == front;
    }

    @Override
    public int length() {
        if (queueEmpty()) {
            return 0;
        }

        if (tail > front) {
            return tail - front;
        } else {
            return maxSize - front + tail;
        }
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
    public Optional<T> deQueue() {
        if (queueEmpty()) {
            return Optional.empty();
        }

        var res = data[front].getValue();
        front = (front + 1) % maxSize;
        return Optional.of(res);
    }

    @Override
    public Optional<T> getHead() {
        if (queueEmpty()) {
            return Optional.empty();
        }
        return Optional.of(data[front].getValue());
    }

    @Override
    public String toString(String delimiter) {
        if (queueEmpty()) {
            return "";
        }

        var sb = new StringBuilder();
        int i = front;
        do {
            sb.append(data[i].getValue());
            sb.append(delimiter);
            i = (i + 1) % maxSize;
        } while (i != tail);
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    @Override
    public String toString() {
        return toString(", ");
    }
}
