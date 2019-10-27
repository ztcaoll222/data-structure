package com.ztcaoll222.data.structure.c3.stack;

import com.ztcaoll222.data.structure.base.Config;
import com.ztcaoll222.data.structure.base.entity.SeqElem;
import com.ztcaoll222.data.structure.c3.interfaces.Stack;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

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

    public SeqStack() {
        this.maxSize = Config.DEFAULT_COLLECTION_SIZE;
        data = new SeqElem[this.maxSize];
    }

    @Override
    public boolean stackEmpty() {
        return top == -1;
    }

    @Override
    public boolean stackOverFlow() {
        return maxSize <= (top + 1);
    }

    @Override
    public int length() {
        return top + 1;
    }

    /**
     * 进栈
     *
     * @param elem 元素
     * @return 成功返回 true, 否则 false
     */
    private boolean push(SeqElem<T> elem) {
        if (stackOverFlow()) {
            return false;
        }

        top++;
        data[top] = elem;
        return true;
    }

    @SafeVarargs
    @Override
    public final boolean push(T... values) {
        if (values.length == 0) {
            return false;
        }

        return Arrays.stream(values).map(SeqElem::new).allMatch(this::push);
    }

    @Override
    public boolean pushs(Stream<T> stream) {
        if (stackOverFlow()) {
            return false;
        }

        return stream.map(SeqElem::new).allMatch(this::push);
    }

    @Override
    public Optional<T> pop() {
        if (stackEmpty()) {
            return Optional.empty();
        }

        var res = data[top].getValue();
        top--;
        return Optional.of(res);
    }

    @Override
    public Optional<T> getTop() {
        if (stackEmpty()) {
            return Optional.empty();
        }

        return Optional.of(data[top].getValue());
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
