package com.ztcaoll222.data.structure.base.entity;

import com.ztcaoll222.data.structure.base.interfaces.Elem;
import lombok.Data;

/**
 * 基础元素
 *
 * @author ztcaoll222
 * Create time: 2019/9/28 20:52
 */
@Data
public class SeqElem<T> implements Elem<T> {
    private static final long serialVersionUID = 3510394235383243005L;

    private T value;

    public SeqElem(T value) {
        this.value = value;
    }
}
