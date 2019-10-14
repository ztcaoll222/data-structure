package com.ztcaoll222.data.structure.c2.entity;

import lombok.Data;

/**
 * kv 对
 *
 * @author ztcaoll222
 * Create time: 2019/10/5 21:08
 */
@Data
public class Pair<T, B> {
    private T k;
    private B v;

    /**
     * 创建 kv 对
     *
     * @param k key 值
     * @param v value 值
     */
    public static <T, B> Pair<T, B> of(T k, B v) {
        var pair = new Pair<T, B>();
        pair.setK(k);
        pair.setV(v);
        return pair;
    }

    @Override
    public String toString() {
        return k.toString() + ", " + v.toString();
    }
}
