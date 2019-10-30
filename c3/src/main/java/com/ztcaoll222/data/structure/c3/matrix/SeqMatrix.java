package com.ztcaoll222.data.structure.c3.matrix;

import com.ztcaoll222.data.structure.base.entity.SeqElem;

/**
 * 矩阵
 *
 * @author ztcaoll222
 * Create time: 2019/10/30 21:55
 */
public class SeqMatrix<T> {
    /**
     * 宽
     */
    private final int n;
    /**
     * 长
     */
    private final int m;
    private SeqElem<T>[] data;

    public SeqMatrix(int n, int m) {
        this.n = n;
        this.m = m;
        this.data = new SeqElem[n * m];
    }

    public int getMaxSize() {
        return n * m;
    }

    private int getK(int i, int j) {
        return (i - 1) * m + j - 1;
    }

    private boolean put(int i, int j, SeqElem<T> elem) {
        if (m < i || n < j) {
            return false;
        }

        int k = getK(i, j);
        data[k] = elem;

        return true;
    }

    public boolean put(int i, int j, T value) {
        return put(i, j, new SeqElem<>(value));
    }

    public String toString(String delimiter, String branch) {
        var sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int k = getK(i + 1, j + 1);
                var elem = data[k];
                if (elem == null) {
                    sb.append(0);
                } else {
                    sb.append(elem.getValue());
                }
                sb.append(delimiter);
            }
            sb.setLength(sb.length() - 2);
            sb.append(branch);
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    @Override
    public String toString() {
        return toString(", ", ";\n");
    }
}
