package com.ztcaoll222.data.structure.c3.abs;

import com.ztcaoll222.data.structure.base.interfaces.Elem;
import com.ztcaoll222.data.structure.c3.interfaces.SeqMatrix;

import java.util.Optional;

/**
 * @author ztcaoll222
 * Create time: 2019/10/31 21:55
 */
public abstract class AbstractSeqSquareMatrix<B extends Elem<T>, T> extends AbstractSquareMatrix<B, T> implements SeqMatrix {
    /**
     * 获得保存元素的数组
     *
     * @return 数组
     */
    protected abstract B[] getData();

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
