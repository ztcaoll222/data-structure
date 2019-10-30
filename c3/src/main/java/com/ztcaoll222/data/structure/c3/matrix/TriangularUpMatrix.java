package com.ztcaoll222.data.structure.c3.matrix;

import com.ztcaoll222.data.structure.base.entity.SeqElem;

/**
 * 上三角矩阵
 *
 * @author ztcaoll222
 * Create time: 2019/10/30 14:50
 */
public class TriangularUpMatrix<T> {
    private final int maxSize;
    private final SeqElem<T>[] data;

    public TriangularUpMatrix(int n, T downValue) {
        this.maxSize = n * (n + 1) / 2;
        this.data = new SeqElem[this.maxSize + 1];
        this.data[this.maxSize] = new SeqElem<>(downValue);
    }

    public int getN() {
        return (int) (-1 + Math.sqrt(1 + 8 * maxSize)) / 2;
    }

    private int getK(int i, int j, int n) {
        int k;
        if (i > j) {
            k = this.maxSize;
        } else {
            k = (i - 1) * (2 * n - i + 2) / 2 + j - i;
        }
        return k;
    }

    private boolean put(int i, int j, SeqElem<T> elem) {
        int n = getN();
        if (n < i || n < j) {
            return false;
        }

        int k = getK(i, j, n);
        data[k] = elem;

        return true;
    }

    public boolean put(int i, int j, T value) {
        return put(i, j, new SeqElem<>(value));
    }

    public String toString(String delimiter, String branch) {
        int n = getN();
        if (n == 0) {
            return "";
        }

        var sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int k = getK(i + 1, j + 1, n);
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
