package com.ztcaoll222.data.structure.c3.queue;

import com.ztcaoll222.data.structure.base.Config;
import com.ztcaoll222.data.structure.base.entity.SeqElem;
import com.ztcaoll222.data.structure.c3.interfaces.Queue;

import java.util.Arrays;
import java.util.Optional;

/**
 * 使用 tab 区分队满还是队空的循环顺序队列
 *
 * @author ztcaoll222
 * Create time: 2019/10/25 15:14
 */
public class SeqTabLoopQueue<T> implements Queue<SeqElem<T>, T> {
    private SeqElem<T>[] data;
    private int maxSize;
    private int front = 0;
    private int tail = 0;
    /**
     * 当为 0 时, 若因删除导致 front == tail, 则说明队空
     * 当为 1 时, 若因删除导致 front == tail, 则说明队满
     */
    private int tab = 0;

    public SeqTabLoopQueue(int maxSize) {
        this.maxSize = maxSize;
        data = new SeqElem[this.maxSize];
    }

    public SeqTabLoopQueue() {
        this.maxSize = Config.DEFAULT_COLLECTION_SIZE;
        data = new SeqElem[this.maxSize];
    }

    @Override
    public boolean queueEmpty() {
        return tab == 0 && front == tail;
    }

    @Override
    public int length() {
        if (queueEmpty()) {
            return 0;
        }

        int count = 0;
        int i = front;
        do {
            count++;
            i = (i + 1) % maxSize;
        } while (i != tail);
        return count;
    }

    /**
     * 入队
     *
     * @param elem 元素
     * @return 成功返回 true, 否则 false
     */
    private boolean enQueue(SeqElem<T> elem) {
        if (tab == 1 && front == tail) {
            return false;
        }

        data[tail] = elem;
        tail = (tail + 1) % maxSize;
        tab = 1;
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
        tab = 0;
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
        do {
            sb.append(data[i].getValue());
            sb.append(", ");
            i = (i + 1) % maxSize;
        } while (i != tail);
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }
}
