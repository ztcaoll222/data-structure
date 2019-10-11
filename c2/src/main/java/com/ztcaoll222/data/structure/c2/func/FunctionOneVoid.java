package com.ztcaoll222.data.structure.c2.func;

/**
 * 函数接口
 *
 * @author ztcaoll222
 * Create time: 2019/10/11 16:02
 */
@FunctionalInterface
public interface FunctionOneVoid<T> {
    /**
     * 接受一个为 T 的参数, 不返回
     *
     * @param arg 类型为 T 的参数
     */
    void execute(T arg);
}
