package com.ztcaoll222.data.structure.c3.matrix;

import com.ztcaoll222.data.structure.base.entity.SeqElem;
import com.ztcaoll222.data.structure.c3.abs.AbstractSeqSquareMatrix;
import lombok.Getter;

/**
 * 三对角矩阵
 *
 * @author ztcaoll222
 * Create time: 2019/10/30 17:22
 */
public class TridiagonalSeqMatrix<T> extends AbstractSeqSquareMatrix<SeqElem<T>, T> {
    private final int maxSize;
    @Getter
    private final SeqElem<T>[] data;

    public TridiagonalSeqMatrix(int n, T defaultValue) {
        this.maxSize = 3 * n - 2;
        this.data = new SeqElem[this.maxSize + 1];
        this.data[this.maxSize] = new SeqElem<>(defaultValue);
    }

    @Override
    public int getN() {
        return (maxSize + 2) / 3;
    }

    @Override
    public int getK(int i, int j) {
        int k;
        if (Math.abs(i - j) > 1) {
            k = this.maxSize;
        } else {
            k = 2 * i + j - 3;
        }
        return k;
    }

    @Override
    public boolean put(int i, int j, T value) {
        return put(i, j, new SeqElem<>(value));
    }
}
