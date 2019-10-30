package com.ztcaoll222.data.structure.c3.entity;

import com.ztcaoll222.data.structure.base.interfaces.Elem;
import lombok.Data;

/**
 * 三元组
 *
 * @author ztcaoll222
 * Create time: 2019/10/30 21:17
 */
@Data
public class ThreeTuple<T> implements Elem<T> {
    private static final long serialVersionUID = 9108541130411655885L;

    private int i;

    private int j;

    private T value;

    public ThreeTuple(int i, int j, T value) {
        this.i = i;
        this.j = j;
        this.value = value;
    }
}
