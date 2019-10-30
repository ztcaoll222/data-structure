package com.ztcaoll222.data.structure.c3.matrix;

import com.ztcaoll222.data.structure.base.entity.SeqElem;
import com.ztcaoll222.data.structure.c3.abs.AbstractSeqSquareMatrix;
import lombok.Getter;

/**
 * 下三角矩阵
 *
 * @author ztcaoll222
 * Create time: 2019/10/30 12:27
 */
public class TriangularSeqDownMatrix<T> extends AbstractSeqSquareMatrix<SeqElem<T>, T> {
    private final int maxSize;
    @Getter
    private final SeqElem<T>[] data;

    public TriangularSeqDownMatrix(int n, T upValue) {
        this.maxSize = n * (n + 1) / 2;
        this.data = new SeqElem[this.maxSize + 1];
        this.data[this.maxSize] = new SeqElem<>(upValue);
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
            k = this.maxSize;
        }
        return k;
    }

    @Override
    public boolean put(int i, int j, T value) {
        return put(i, j, new SeqElem<>(value));
    }
}
