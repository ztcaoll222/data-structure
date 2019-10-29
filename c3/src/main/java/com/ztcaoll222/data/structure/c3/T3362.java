package com.ztcaoll222.data.structure.c3;

import com.ztcaoll222.data.structure.base.entity.Pair;
import com.ztcaoll222.data.structure.c3.queue.SeqLoopDeQueue;
import com.ztcaoll222.data.structure.c3.queue.SeqLoopQueue;
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
            Optional<Character> value;
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    value = stack.pop();
                    if (value.isEmpty() || !value.get().equals('(')) {
                        return false;
                    }
                    break;
                case ']':
                    value = stack.pop();
                    if (value.isEmpty() || !value.get().equals('[')) {
                        return false;
                    }
                    break;
                case '}':
                    value = stack.pop();
                    if (value.isEmpty() || !value.get().equals('{')) {
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

        stack.pops().forEach(value -> {
            optLog.append('O');
            res.append(value);
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
        stack.pops().forEach(i -> {
            double value = 2 * x * t1.get() - 2 * (i - 1) * t0.get();
            t0.set(t1.get());
            t1.set(value);
        });

        if (n == 0) {
            return 1;
        }
        return t1.get();
    }

    /**
     * 不断向分组中存进车辆
     *
     * @param res 分组
     * @param h   车辆
     */
    private static void pushCar(SeqLoopDeQueue<SeqLoopQueue<Character>> res, Character h) {
        var tail = res.getTail().filter(elem -> !elem.queueOverFlow()).orElseGet(() -> {
            var resItem = new SeqLoopQueue<Character>(11);
            res.enQueue(resItem);
            return resItem;
        });
        tail.enQueue(h);
    }

    /**
     * 重排客车(K)与货车(H)
     * 使其每 10 辆分为 1 组, 客车有限度比货车优先度, 每 4 辆客车才会上 1 辆货车, 如果客车不足 4 辆, 这用货车补齐
     *
     * @param str 客车和货车的排队顺序
     * @return 重排后的结果, 组与组之间用 "; " 分割, 辆与辆之间用 ", " 分割
     */
    public static String t4(String str) {
        int length = str.length();

        var hQueue = new SeqLoopQueue<Character>(length + 1);
        var res = new SeqLoopDeQueue<SeqLoopQueue<Character>>(length / 10 + 1);

        int j = 1;
        for (int i = 0; i < length; i++) {
            if (j % 5 == 0) {
                hQueue.deQueue().ifPresent(h -> pushCar(res, h));
                j = 1;
            }

            var c = str.charAt(i);
            if (c == 'H') {
                hQueue.enQueue(c);
            } else {
                pushCar(res, c);
                j++;
            }
        }

        while (!hQueue.queueEmpty()) {
            hQueue.deQueue().ifPresent(h -> pushCar(res, h));
        }

        return res.toString("; ");
    }
}
