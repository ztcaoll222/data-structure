package com.ztcaoll222.data.structure.c3.stack;

import com.ztcaoll222.data.structure.base.Config;
import com.ztcaoll222.data.structure.base.entity.SeqElem;
import com.ztcaoll222.data.structure.c3.emuns.SeqShareStackType;
import com.ztcaoll222.data.structure.c3.interfaces.Stack;

import java.util.Optional;

/**
 * 共享栈
 *
 * @author ztcaoll222
 * Create time: 2019/10/21 22:28
 */
public class SeqShareStack<T> {
    private SeqShareStackElem a = new SeqShareStackElem(SeqShareStackType.A);
    private SeqShareStackElem b = new SeqShareStackElem(SeqShareStackType.B);
    private int maxSize;
    private SeqElem<T>[] data;

    public SeqShareStack(int maxSize) {
        this.maxSize = maxSize;
        data = new SeqElem[maxSize];
    }

    public SeqShareStack() {
        this.maxSize = Config.DEFAULT_COLLECTION_SIZE;
        data = new SeqElem[this.maxSize];
    }

    /**
     * 获得 a 栈
     *
     * @return a 栈
     */
    public SeqShareStackElem getA() {
        return a;
    }

    /**
     * 获得 b 栈
     *
     * @return b 栈
     */
    public SeqShareStackElem getB() {
        return b;
    }

    /**
     * 判断一个栈是否为空
     *
     * @return 空则返回 true, 否则 false
     */
    public boolean stackEmpty() {
        return a.stackEmpty() && b.stackEmpty();
    }

    /**
     * 判断栈是否满
     *
     * @return 是返回 true, 否则 false
     */
    private boolean stackOverFlow() {
        return maxSize <= ((a.top + 1) + (b.top + 1));
    }

    /**
     * 获得栈已使用容量
     *
     * @param type 共享栈类型
     * @return 已使用容量
     */
    private int length(SeqShareStackType type) {
        switch (type) {
            case A:
                return a.top + 1;
            case B:
            default:
                return b.top + 1;
        }
    }

    /**
     * 进栈
     *
     * @param type   共享栈类型
     * @param values 值数组
     * @return 成功返回 true, 否则 false
     */
    private boolean push(SeqShareStackType type, T[] values) {
        if (values.length == 0 || maxSize < ((a.top + 1) + (b.top + 1)) + values.length) {
            return false;
        }

        switch (type) {
            case A:
                for (T value : values) {
                    a.top++;
                    data[a.top] = new SeqElem<>(value);
                }
                return true;
            case B:
            default:
                for (T value : values) {
                    b.top++;
                    data[(maxSize - 1) - b.top] = new SeqElem<>(value);
                }
                return true;
        }
    }

    /**
     * 出栈
     *
     * @param type 共享栈类型
     * @return 栈顶元素的值
     */
    private Optional<T> pop(SeqShareStackType type) {
        T res;
        switch (type) {
            case A:
                if (a.stackEmpty()) {
                    return Optional.empty();
                }
                res = data[a.top].getValue();
                a.top--;
                return Optional.of(res);
            case B:
            default:
                if (b.stackEmpty()) {
                    return Optional.empty();
                }
                res = data[(maxSize - 1) - b.top].getValue();
                b.top--;
                return Optional.of(res);
        }
    }

    /**
     * 读取栈顶元素
     *
     * @param type 共享栈类型
     * @return 栈顶元素的值
     */
    private Optional<T> getTop(SeqShareStackType type) {
        switch (type) {
            case A:
                if (a.stackEmpty()) {
                    return Optional.empty();
                }
                return Optional.of(data[a.top].getValue());
            case B:
            default:
                if (b.stackEmpty()) {
                    return Optional.empty();
                }
                return Optional.of(data[(maxSize - 1) - b.top].getValue());
        }
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{a: {");

        if (!a.stackEmpty()) {
            int i = 0;
            while (i < a.top) {
                sb.append(data[i].getValue());
                sb.append(", ");
                i++;
            }
            sb.append(data[a.top].getValue());
        }

        sb.append("}, b: {");

        if (!b.stackEmpty()) {
            int i = 0;
            while (i < b.top) {
                sb.append(data[(maxSize - 1) - i].getValue());
                sb.append(", ");
                i++;
            }
            sb.append(data[(maxSize - 1) - b.top].getValue());
        }

        sb.append("}}");
        return sb.toString();
    }

    /**
     * 销毁栈
     */
    public void clearStack() {
        a.clearStack();
        b.clearStack();
    }

    public class SeqShareStackElem implements Stack<SeqElem<T>, T> {
        private int top = -1;
        private SeqShareStackType type;

        public SeqShareStackElem(SeqShareStackType type) {
            this.type = type;
        }

        @Override
        public boolean stackEmpty() {
            return top == -1;
        }

        @Override
        public boolean stackOverFlow() {
            return SeqShareStack.this.stackOverFlow();
        }

        @Override
        public int length() {
            return SeqShareStack.this.length(type);
        }

        @SafeVarargs
        @Override
        public final boolean push(T... values) {
            return SeqShareStack.this.push(type, values);
        }

        @Override
        public Optional<T> pop() {
            return SeqShareStack.this.pop(type);
        }

        @Override
        public Optional<T> getTop() {
            return SeqShareStack.this.getTop(type);
        }

        @Override
        public void clearStack() {
            top = -1;
        }
    }
}
