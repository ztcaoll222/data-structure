package com.ztcaoll222.data.structure.c3.interfaces;

/**
 * @author ztcaoll222
 * Create time: 2019/11/1 0:17
 */
public interface SeqMatrix {
    /**
     * 把第 i 行, 第 j 列转换为在数组中的位置
     *
     * @param i 行号, 从 1 开始数
     * @param j 列号, 从 1 开始数
     * @return 在数组中的位置
     */
    int getK(int i, int j);
}
