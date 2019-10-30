package com.ztcaoll222.data.structure.c3.interfaces;

import com.ztcaoll222.data.structure.base.func.FunctionOneVoid;
import com.ztcaoll222.data.structure.base.func.FunctionThreeVoid;

import java.util.Optional;

/**
 * @author ztcaoll222
 * Create time: 2019/10/31 19:04
 */
public interface Matrix<T> {
    /**
     * 获得总列数
     *
     * @return 总列数
     */
    int getN();

    /**
     * 获得总行数
     *
     * @return 总行数
     */
    int getM();

    /**
     * 获得第 i 行, 第 j 列的 value
     *
     * @param i 行号, 从 1 开始数
     * @param j 列号, 从 1 开始数
     * @return 值
     */
    Optional<T> get(int i, int j);

    /**
     * 在第 i 行, 第 j 列放入数据 value
     *
     * @param i     行号, 从 1 开始数
     * @param j     列号, 从 1 开始数
     * @param value 数据
     * @return 成功返回 true, 否则 false
     */
    boolean put(int i, int j, T value);

    /**
     * 遍历所有元素
     *
     * @param funcJ 列循环, 行号, 列号, 值
     * @param funcI 行循环 行号
     */
    void forEach(FunctionThreeVoid<Integer, Integer, T> funcJ, FunctionOneVoid<Integer> funcI);
}
