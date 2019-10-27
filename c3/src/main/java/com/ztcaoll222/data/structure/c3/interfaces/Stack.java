package com.ztcaoll222.data.structure.c3.interfaces;

import com.ztcaoll222.data.structure.base.interfaces.Elem;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author ztcaoll222
 * Create time: 2019/10/21 9:23
 */
public interface Stack<B extends Elem<T>, T> {
    /**
     * 判断一个栈是否为空
     *
     * @return 空则返回 true, 否则 false
     */
    boolean stackEmpty();

    /**
     * 判断栈是否满
     *
     * @return 是返回 true, 否则 false
     */
    boolean stackOverFlow();

    /**
     * 获得栈已使用容量
     *
     * @return 已使用容量
     */
    int length();

    /**
     * 进栈
     *
     * @param values 值数组
     * @return 成功返回 true, 否则 false
     */
    boolean push(T[] values);

    /**
     * 进栈
     *
     * @param stream 连续
     * @return 成功返回 true, 否则 false
     */
    boolean pushs(Stream<T> stream);

    /**
     * 出栈
     *
     * @return 栈顶元素的值
     */
    Optional<T> pop();

    /**
     * 出栈
     *
     * @return 连续
     */
    default Stream<T> pops() {
        return Stream.generate(this::pop).takeWhile(Optional::isPresent).map(Optional::get);
    }

    /**
     * 读取栈顶元素
     *
     * @return 栈顶元素的值
     */
    Optional<T> getTop();

    /**
     * 销毁栈
     */
    void clearStack();
}
