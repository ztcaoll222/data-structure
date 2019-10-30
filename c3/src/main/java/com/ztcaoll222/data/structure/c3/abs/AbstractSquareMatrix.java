package com.ztcaoll222.data.structure.c3.abs;

import com.ztcaoll222.data.structure.base.interfaces.Elem;

/**
 * @author ztcaoll222
 * Create time: 2019/10/31 23:14
 */
public abstract class AbstractSquareMatrix<B extends Elem<T>, T> extends AbstractMatrix<B, T> {
    @Override
    public int getM() {
        return getN();
    }

    protected abstract int getK(int i, int j);
}
