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
    /**
     * 获得第 i 行, 第 j 列的元素
     *
     * @param i 行号, 从 1 开始数
     * @param j 列号, 从 1 开始数
     * @return 元素
     */
    protected abstract Optional<B> getElem(int i, int j);

    @Override
    public Optional<T> get(int i, int j) {
        return getElem(i, j).map(B::getValue);
    }

    /**
     * 在第 i 行, 第 j 列放入元素
     *
     * @param i    行号, 从 1 开始数
     * @param j    列号, 从 1 开始数
     * @param elem 元素
     * @return 成功返回 true, 否则 false
     */
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

    /**
     * 转为字符串
     *
     * @param delimiter 列与列之间的分隔符
     * @param branch    行与行之间的分隔符
     * @param nullValue 如果某个元素为空, 那么放入的数据
     * @return 字符串
     */
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
        }, i -> {
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
