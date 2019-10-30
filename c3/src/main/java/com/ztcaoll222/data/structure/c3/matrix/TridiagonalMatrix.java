package com.ztcaoll222.data.structure.c3.matrix;

import com.ztcaoll222.data.structure.base.entity.SeqElem;

/**
 * 三对角矩阵
 *
 * @author ztcaoll222
 * Create time: 2019/10/30 17:22
 */
public class TridiagonalMatrix<T> {
    private final int maxSize;
    private final SeqElem<T>[] data;

    public TridiagonalMatrix(int n, T defaultValue) {
        this.maxSize = 3 * n - 2;
        this.data = new SeqElem[this.maxSize + 1];
        this.data[this.maxSize] = new SeqElem<>(defaultValue);
    }

    public int getN() {
        return (maxSize + 2) / 3;
    }

    private int getK(int i, int j) {
        int k;
        if (Math.abs(i - j) > 1) {
            k = this.maxSize;
        } else {
            k = 2 * i + j - 3;
        }
        return k;
    }

    private boolean put(int i, int j, SeqElem<T> elem) {
        int n = getN();
        if (n < i || n < j) {
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
        int n = getN();
        var sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
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
