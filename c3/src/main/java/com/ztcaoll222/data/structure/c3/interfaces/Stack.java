package com.ztcaoll222.data.structure.c3.interfaces;

import com.ztcaoll222.data.structure.base.interfaces.Elem;

import java.util.Optional;

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
     * 进栈
     *
     * @param values 值数组
     * @return 成功返回 true, 否则 false
     */
    boolean push(T[] values);

    /**
     * 出栈
     *
     * @return 栈顶元素
     */
    Optional<B> pop();

    /**
     * 读取栈顶元素
     *
     * @return 栈顶元素
     */
    Optional<B> getTop();

    /**
     * 销毁栈
     */
    void clearStack();
}
