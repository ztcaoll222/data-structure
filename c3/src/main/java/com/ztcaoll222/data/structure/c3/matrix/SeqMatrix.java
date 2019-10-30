package com.ztcaoll222.data.structure.c3.matrix;

import com.ztcaoll222.data.structure.base.entity.SeqElem;
import com.ztcaoll222.data.structure.c3.abs.AbstractMatrix;
import lombok.Getter;

import java.util.Optional;

/**
 * 矩阵
 *
 * @author ztcaoll222
 * Create time: 2019/10/30 21:55
 */
public class SeqMatrix<T> extends AbstractMatrix<SeqElem<T>, T> {
    @Getter
    private final int n;
    @Getter
    private final int m;
    private SeqElem<T>[] data;

    public SeqMatrix(int n, int m) {
        this.n = n;
        this.m = m;
        this.data = new SeqElem[n * m];
    }

    private int getK(int i, int j) {
        return (i - 1) * m + j - 1;
    }

    @Override
    protected Optional<SeqElem<T>> getElem(int i, int j) {
        if (m < i || n < j) {
            return Optional.empty();
        }

        int k = getK(i, j);
        return Optional.ofNullable(data[k]);
    }

    @Override
    protected boolean put(int i, int j, SeqElem<T> elem) {
        if (m < i || n < j) {
            return false;
        }

        int k = getK(i, j);
        data[k] = elem;

        return true;
    }

    @Override
    public boolean put(int i, int j, T value) {
        return put(i, j, new SeqElem<>(value));
    }
}
