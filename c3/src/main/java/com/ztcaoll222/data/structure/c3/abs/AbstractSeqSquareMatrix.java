package com.ztcaoll222.data.structure.c3.abs;

import com.ztcaoll222.data.structure.base.interfaces.Elem;

import java.util.Optional;

/**
 * @author ztcaoll222
 * Create time: 2019/10/31 21:55
 */
public abstract class AbstractSeqSquareMatrix<B extends Elem<T>, T> extends AbstractSquareMatrix<B, T> {
    /**
     * 获得保存元素的数组
     *
     * @return 数组
     */
    protected abstract B[] getData();

    /**
     * 把第 i 行, 第 j 列转换为在数组中的位置
     *
     * @param i 行号, 从 1 开始数
     * @param j 列号, 从 1 开始数
     * @return 在数组中的位置
     */
    protected abstract int getK(int i, int j);

    @Override
    protected Optional<B> getElem(int i, int j) {
        int n = getN();
        if (n < i || n < j) {
            return Optional.empty();
        }

        int k = getK(i, j);
        return Optional.ofNullable(getData()[k]);
    }

    @Override
    protected boolean put(int i, int j, B elem) {
        int n = getN();
        if (n < i || n < j) {
            return false;
        }

        int k = getK(i, j);
        getData()[k] = elem;

        return true;
    }
}
