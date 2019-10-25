package com.ztcaoll222.data.structure.c3.queue;

import com.ztcaoll222.data.structure.base.Config;
import com.ztcaoll222.data.structure.base.entity.SeqElem;
import com.ztcaoll222.data.structure.c3.interfaces.Queue;
import com.ztcaoll222.data.structure.c3.stack.SeqStack;

import java.util.Optional;

/**
 * 基于栈的队列
 *
 * @author ztcaoll222
 * Create time: 2019/10/25 21:27
 */
public class SeqStackQueue<T> implements Queue<SeqElem<T>, T> {
    private SeqStack<T> a;
    private SeqStack<T> b;
    private int maxSize;

    public SeqStackQueue(int maxSize) {
        this.maxSize = maxSize;
        a = new SeqStack<>(this.maxSize);
        b = new SeqStack<>(this.maxSize);
    }

    public SeqStackQueue() {
        this.maxSize = Config.DEFAULT_COLLECTION_SIZE;
        a = new SeqStack<>(this.maxSize);
        b = new SeqStack<>(this.maxSize);
    }

    @Override
    public boolean queueEmpty() {
        return a.stackEmpty() && b.stackEmpty();
    }

    @Override
    public int length() {
        return a.length() + b.length();
    }

    @SafeVarargs
    @Override
    public final boolean enQueue(T... values) {
        if (!a.stackOverFlow()) {
            return a.push(values);
        }

        if (a.stackOverFlow() && b.stackEmpty()) {
            int length = a.length();
            for (int i = 0; i < length; i++) {
                a.pop().map(SeqElem::getValue).ifPresent(b::push);
            }
            return a.push(values);
        } else {
            return false;
        }
    }

    @Override
    public Optional<SeqElem<T>> deQueue() {
        if (!b.stackEmpty()) {
            return b.pop();
        }

        if (!a.stackEmpty()) {
            int length = a.length();
            for (int i = 0; i < length; i++) {
                a.pop().map(SeqElem::getValue).ifPresent(b::push);
            }
            return b.pop();
        }

        return Optional.empty();
    }

    @Override
    public Optional<SeqElem<T>> getHead() {
        if (!b.stackEmpty()) {
            return b.getTop();
        }

        if (!a.stackEmpty() && b.stackEmpty()) {
            int length = a.length();
            for (int i = 0; i < length; i++) {
                a.pop().map(SeqElem::getValue).ifPresent(b::push);
            }
            return b.getTop();
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        if (queueEmpty()) {
            return "";
        }

        if (!b.stackEmpty()) {
            return b.toString() + ", " + a.toString();
        } else {
            return a.toString();
        }
    }
}
