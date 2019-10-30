package com.ztcaoll222.data.structure.c3.matrix;

import com.ztcaoll222.data.structure.c2.table.SingleLoopLinkTable;
import com.ztcaoll222.data.structure.c3.abs.AbstractMatrix;
import com.ztcaoll222.data.structure.c3.entity.ThreeTuple;
import com.ztcaoll222.data.structure.c3.interfaces.Matrix;
import lombok.Getter;

import java.util.Objects;
import java.util.Optional;

/**
 * 稀疏矩阵
 *
 * @author ztcaoll222
 * Create time: 2019/10/30 20:56
 */
public class SparseSingleLinkMatrix<T> extends AbstractMatrix<ThreeTuple<T>, T> {
    @Getter
    private final int n;
    @Getter
    private final int m;
    private SingleLoopLinkTable<ThreeTuple<T>> data = new SingleLoopLinkTable<>();

    public SparseSingleLinkMatrix(Matrix<T> matrix) {
        this.n = matrix.getN();
        this.m = matrix.getM();

        matrix.forEach((i, j, value) -> {
            if (value != null) {
                data.listInsertLast(new ThreeTuple<>(i, j, value));
            }
        }, null);
    }

    @Override
    protected Optional<ThreeTuple<T>> getElem(int i, int j) {
        var cur = data.tail;
        do {
            cur = cur.getNext();
            var elem = cur.getValue();
            if (Objects.equals(i, elem.getI()) && Objects.equals(j, elem.getJ())) {
                return Optional.of(elem);
            }
        } while (cur != data.tail);
        return Optional.empty();
    }

    @Override
    protected boolean put(int i, int j, ThreeTuple<T> elem) {
        getElem(i, j).ifPresentOrElse(it -> it.setValue(elem.getValue()),
                () -> data.listInsertLast(elem));
        return true;
    }

    @Override
    public boolean put(int i, int j, T value) {
        return put(i, j, new ThreeTuple<>(i, j, value));
    }
}
