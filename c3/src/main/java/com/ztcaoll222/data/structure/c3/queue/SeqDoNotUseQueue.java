package com.ztcaoll222.data.structure.c3.queue;

import com.ztcaoll222.data.structure.base.Config;
import com.ztcaoll222.data.structure.base.entity.SeqElem;
import com.ztcaoll222.data.structure.c3.interfaces.Queue;
import com.ztcaoll222.data.structure.c3.interfaces.SeqQueue;

import java.util.Optional;

/**
 * 顺序队列
 *
 * @author ztcaoll222
 * Create time: 2019/10/23 22:01
 */
public class SeqDoNotUseQueue<T> implements Queue<T>, SeqQueue {
    private SeqElem<T>[] data;
    private int maxSize;
    private int front = 0;
    private int tail = 0;

    public SeqDoNotUseQueue(int maxSize) {
        this.maxSize = maxSize;
        data = new SeqElem[this.maxSize];
    }

    public SeqDoNotUseQueue() {
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
        return tail == maxSize;
    }

    @Override
    public int length() {
        if (queueEmpty()) {
            return 0;
        }

        return tail - front;
    }

    @SafeVarargs
    @Override
    public final boolean enQueue(T... values) {
        if (tail + values.length > maxSize) {
            return false;
        }

        for (T value : values) {
            data[tail] = new SeqElem<>(value);
            tail++;
        }
        return true;
    }

    @Override
    public Optional<T> deQueue() {
        if (front == tail) {
            return Optional.empty();
        }

        var res = data[front].getValue();
        front++;
        return Optional.of(res);
    }

    @Override
    public Optional<T> getHead() {
        if (front == tail) {
            return Optional.empty();
        }

        return Optional.of(data[front].getValue());
    }

    @Override
    public String toString() {
        if (queueEmpty()) {
            return "";
        }

        var sb = new StringBuilder();
        for (int i = front; i < tail - 1; i++) {
            sb.append(data[i].getValue());
            sb.append(", ");
        }
        sb.append(data[tail - 1].getValue());

        return sb.toString();
    }
}
