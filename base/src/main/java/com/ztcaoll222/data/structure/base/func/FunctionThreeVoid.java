package com.ztcaoll222.data.structure.base.func;

/**
 * 函数接口
 *
 * @author ztcaoll222
 * Create time: 2019/10/7 11:03
 */
@FunctionalInterface
public interface FunctionThreeVoid<T, B, A> {
    /**
     * 接受两个分别为 T, B 的参数, 返回一个 C
     *
     * @param arg0 类型为 T 的参数
     * @param arg1 类型为 B 的参数
     * @param arg2 类型为 A 的参数
     */
    void execute(T arg0, B arg1, A arg2);
}
