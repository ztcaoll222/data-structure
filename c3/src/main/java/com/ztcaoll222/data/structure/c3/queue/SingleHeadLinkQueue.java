package com.ztcaoll222.data.structure.c3.queue;

import com.ztcaoll222.data.structure.c2.entity.SingleNode;
import com.ztcaoll222.data.structure.c3.interfaces.Queue;

import java.util.Optional;

/**
 * 使用头结点的链式队列
 *
 * @author ztcaoll222
 * Create time: 2019/10/24 10:48
 */
public class SingleHeadLinkQueue<T> implements Queue<SingleNode<T>, T> {
    private SingleNode<T> head;
    private SingleNode<T> tail;

    public SingleHeadLinkQueue() {
        head = tail = new SingleNode<>();
    }

    @Override
    public boolean queueEmpty() {
        return head == tail;
    }

    @Override
    public int length() {
        if (queueEmpty()) {
            return 0;
        }

        int count = 1;
        var tNode = head.getNext();
        while (tNode.getNext() != null) {
            count++;
            tNode = tNode.getNext();
        }
        return count;
    }

    @SafeVarargs
    @Override
    public final boolean enQueue(T... values) {
        for (T value : values) {
            tail.setNext(new SingleNode<>(value));
            tail = tail.getNext();
        }
        return true;
    }

    @Override
    public Optional<SingleNode<T>> deQueue() {
        if (queueEmpty()) {
            return Optional.empty();
        }

        head = head.getNext();
        return Optional.of(head);
    }

    @Override
    public Optional<SingleNode<T>> getHead() {
        if (queueEmpty()) {
            return Optional.empty();
        }
        return Optional.of(head.getNext());
    }

    @Override
    public String toString() {
        if (queueEmpty()) {
            return "";
        }

        var sb = new StringBuilder();
        var tNode = head.getNext();
        while (tNode.getNext() != null) {
            sb.append(tNode.getValue());
            sb.append(", ");
            tNode = tNode.getNext();
        }
        sb.append(tNode.getValue());

        return sb.toString();
    }
}
