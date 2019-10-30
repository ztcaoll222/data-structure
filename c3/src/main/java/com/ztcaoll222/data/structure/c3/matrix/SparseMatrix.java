package com.ztcaoll222.data.structure.c3.matrix;

import com.ztcaoll222.data.structure.c2.table.SingleLoopLinkTable;
import com.ztcaoll222.data.structure.c3.entity.ThreeTuple;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 稀疏矩阵
 *
 * @author ztcaoll222
 * Create time: 2019/10/30 20:56
 */
public class SparseMatrix<T> {
    private final int n;
    private final int m;
    private final int maxSize;
    private SingleLoopLinkTable<ThreeTuple<T>> data = new SingleLoopLinkTable<>();

    public SparseMatrix(SeqMatrix<T> matrix) {
        this.n = matrix.getN();
        this.m = matrix.getM();
        this.maxSize = matrix.getMaxSize();

        var count = new AtomicInteger(0);
        matrix.forEach((i, j, elem) -> {
            if (elem != null) {
                data.listInsertLast(new ThreeTuple<>(i, j, elem.getValue()));
            }
        }, null);
    }

    public Optional<T> getValue(int i, int j) {
        var cur = data.tail;
        do {
            cur = cur.getNext();
            var elem = cur.getValue();
            if (Objects.equals(i, elem.getI()) && Objects.equals(j, elem.getJ())) {
                return Optional.of(elem.getValue());
            }
        } while (cur != data.tail);
        return Optional.empty();
    }

    public String toString(String delimiter, String branch) {
        var sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                getValue(i + 1, j + 1).ifPresentOrElse(sb::append, () -> sb.append(0));
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
