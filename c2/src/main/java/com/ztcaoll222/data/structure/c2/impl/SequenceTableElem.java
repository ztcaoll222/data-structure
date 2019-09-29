package com.ztcaoll222.data.structure.c2.impl;

import com.ztcaoll222.data.structure.c2.Elem;
import lombok.Data;

/**
 * 顺序表元素
 *
 * @author ztcaoll222
 * Create time: 2019/9/28 20:52
 */
@Data
public class SequenceTableElem<T> implements Elem<T> {
    private static final long serialVersionUID = 3510394235383243005L;

    private T value;

    public SequenceTableElem(T value) {
        this.value = value;
    }
}
