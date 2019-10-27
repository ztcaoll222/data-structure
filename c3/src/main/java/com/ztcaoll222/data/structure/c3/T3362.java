package com.ztcaoll222.data.structure.c3;

import com.ztcaoll222.data.structure.base.entity.Pair;
import com.ztcaoll222.data.structure.base.entity.SeqElem;
import com.ztcaoll222.data.structure.c3.stack.SeqStack;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 第三章栈和队列综合题
 *
 * @author ztcaoll222
 * Create time: 2019/10/26 7:57
 */
public class T3362 {
    private T3362() {
    }

    /**
     * 判断一段字符串中的 "([{}])" 是否匹配
     *
     * @param str 字符串
     * @return 是返回 true, 否则 false
     */
    public static boolean t1(String str) {
        var stack = new SeqStack<Character>(str.length());
        for (char c : str.toCharArray()) {
            Optional<SeqElem<Character>> elem;
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    elem = stack.pop();
                    if (elem.isEmpty() || !elem.get().getValue().equals('(')) {
                        return false;
                    }
                    break;
                case ']':
                    elem = stack.pop();
                    if (elem.isEmpty() || !elem.get().getValue().equals('[')) {
                        return false;
                    }
                    break;
                case '}':
                    elem = stack.pop();
                    if (elem.isEmpty() || !elem.get().getValue().equals('{')) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return stack.stackEmpty();
    }

    /**
     * 重新排序 str 中的 'H', 'S'
     * 使 'S' 全部排到 'H' 前面
     *
     * @param str 字符串
     * @return <操作记录, 结果>
     */
    public static Pair<String, String> t2(String str) {
        var stack = new SeqStack<Character>(str.length());
        var optLog = new StringBuilder();
        var res = new StringBuilder();
        str.chars().filter(i -> {
            char c = (char) i;
            if ('S' == c) {
                optLog.append("IO");
                res.append(c);
                return false;
            }
            return true;
        }).forEach(i -> {
            char c = (char) i;
            optLog.append('I');
            stack.push(c);
        });

        stack.pops().forEach(elem -> {
            optLog.append('O');
            res.append(elem.getValue());
        });

        return Pair.of(optLog.toString(), res.toString());
    }

    /**
     * 使用栈实现:
     * 1,                           n=0
     * 2x,                          n=1
     * 2xP(n-1)(x)-2(n-1)P(n-2)(x), x > 1
     */
    public static double t3(int n, double x) {
        var stack = new SeqStack<Integer>(n);
        for (int i = n; i >= 2; i--) {
            stack.push(i);
        }

        AtomicReference<Double> t0 = new AtomicReference<>((double) 1);
        AtomicReference<Double> t1 = new AtomicReference<>(2 * x);
        stack.pops().map(SeqElem::getValue).forEach(i -> {
            double value = 2 * x * t1.get() - 2 * (i - 1) * t0.get();
            t0.set(t1.get());
            t1.set(value);
        });

        if (n == 0) {
            return 1;
        }
        return t1.get();
    }
}
