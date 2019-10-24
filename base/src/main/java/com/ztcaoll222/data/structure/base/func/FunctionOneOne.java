package com.ztcaoll222.data.structure.base.func;

/**
 * 函数接口
 *
 * @author ztcaoll222
 * Create time: 2019/10/10 15:43
 */
@FunctionalInterface
public interface FunctionOneOne<T, C> {
    /**
     * 接受两个分别为 T 的参数, 返回一个 C
     *
     * @param arg 类型为 T 的参数
     * @return 返回一个类型的 C 的参数
     */
    C execute(T arg);
}
