package com.ztcaoll222.data.structure.c3.stack;

import com.ztcaoll222.data.structure.base.Config;
import com.ztcaoll222.data.structure.c3.entity.SeqElem;
import com.ztcaoll222.data.structure.c3.interfaces.Stack;

import java.util.Optional;

/**
 * 顺序栈
 *
 * @author ztcaoll222
 * Create time: 2019/10/21 20:28
 */
public class SeqStack<T> implements Stack<SeqElem<T>, T> {
    private int maxSize;
    private SeqElem<T>[] data;
    private int top = -1;

    public SeqStack(int maxSize) {
        this.maxSize = maxSize;
        data = new SeqElem[maxSize];
    }

    @Override
    public boolean stackEmpty() {
        return top == -1;
    }

    @SafeVarargs
    @Override
    public final boolean push(T... values) {
        if (maxSize < (top + 1 + values.length)) {
            return false;
        }

        for (T value : values) {
            top++;
            data[top] = new SeqElem<T>(value);
        }
        return true;
    }

    @Override
    public Optional<SeqElem<T>> pop() {
        if (top == -1) {
            return Optional.empty();
        }

        var res = data[top];
        top--;
        return Optional.of(res);
    }

    @Override
    public Optional<SeqElem<T>> getTop() {
        if (top == -1) {
            return Optional.empty();
        }

        return Optional.of(data[top]);
    }

    @Override
    public void clearStack() {
        top = -1;
    }

    @Override
    public String toString() {
        if (stackEmpty()) {
            return "";
        }

        var sb = new StringBuilder();
        int i = 0;
        do {
            sb.append(data[i].getValue());
            sb.append(", ");
            i++;
        } while (i < top);
        sb.append(data[top].getValue());

        return sb.toString();
    }

    @SafeVarargs
    public static <T> SeqStack<T> of(T... args) {
        SeqStack<T> stack;
        if (args.length == 0) {
            stack = new SeqStack<>(Config.DEFAULT_COLLECTION_SIZE);
        } else {
            stack = new SeqStack<>(args.length);
            stack.push(args);
        }
        return stack;
    }
}
