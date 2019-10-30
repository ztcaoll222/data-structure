package com.ztcaoll222.data.structure.c3.abs;

import com.ztcaoll222.data.structure.base.func.FunctionOneVoid;
import com.ztcaoll222.data.structure.base.func.FunctionThreeVoid;
import com.ztcaoll222.data.structure.base.interfaces.Elem;
import com.ztcaoll222.data.structure.c3.interfaces.Matrix;

import java.util.Optional;

/**
 * @author ztcaoll222
 * Create time: 2019/10/31 20:58
 */
public abstract class AbstractMatrix<B extends Elem<T>, T> implements Matrix<T> {
    protected abstract Optional<B> getElem(int i, int j);

    @Override
    public Optional<T> get(int i, int j) {
        return getElem(i, j).map(B::getValue);
    }

    protected abstract boolean put(int i, int j, B elem);

    @Override
    public void forEach(FunctionThreeVoid<Integer, Integer, T> funcJ, FunctionOneVoid<Integer> funcI) {
        for (int i = 0; i < getM(); i++) {
            for (int j = 0; j < getN(); j++) {
                Optional<T> value = get(i + 1, j + 1);
                if (funcJ != null) {
                    if (value.isPresent()) {
                        funcJ.execute(i + 1, j + 1, value.get());
                    } else {
                        funcJ.execute(i + 1, j + 1, null);
                    }
                }
            }
            if (funcI != null) {
                funcI.execute(i);
            }
        }
    }

    protected String toString(String delimiter, String branch, String nullValue) {
        if (getM() == 0 || getN() == 0) {
            return "";
        }

        var sb = new StringBuilder();
        forEach((i, j, value) -> {
            if (value == null) {
                sb.append(nullValue);
            } else {
                sb.append(value);
            }
            sb.append(delimiter);
        }, i->{
            sb.setLength(sb.length() - 2);
            sb.append(branch);
        });
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    @Override
    public String toString() {
        return toString(", ", ";\n", "0");
    }
}
