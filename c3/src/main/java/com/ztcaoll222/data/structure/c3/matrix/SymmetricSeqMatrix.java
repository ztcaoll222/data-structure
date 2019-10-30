package com.ztcaoll222.data.structure.c3.matrix;

import com.ztcaoll222.data.structure.base.entity.SeqElem;
import com.ztcaoll222.data.structure.c3.abs.AbstractSeqSquareMatrix;
import lombok.Getter;

/**
 * 对称矩阵
 *
 * @author ztcaoll222
 * Create time: 2019/10/29 22:48
 */
public class SymmetricSeqMatrix<T> extends AbstractSeqSquareMatrix<SeqElem<T>, T> {
    private final int maxSize;
    @Getter
    private SeqElem<T>[] data;

    public SymmetricSeqMatrix(int n) {
        this.maxSize = n * (n + 1) / 2;
        this.data = new SeqElem[this.maxSize];
    }

    @Override
    public int getN() {
        return (int) (-1 + Math.sqrt(1 + 8 * maxSize)) / 2;
    }

    @Override
    protected int getK(int i, int j) {
        int k;
        if (i >= j) {
            k = (i - 1) * i / 2 + j - 1;
        } else {
            k = (j - 1) * j / 2 + i - 1;
        }
        return k;
    }

    @Override
    public boolean put(int i, int j, T value) {
        return put(i, j, new SeqElem<>(value));
    }
}
