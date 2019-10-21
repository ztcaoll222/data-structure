package com.ztcaoll222.data.structure.c3.entity;

import com.ztcaoll222.data.structure.base.interfaces.Elem;
import lombok.Data;

/**
 * 顺序栈元素
 *
 * @author ztcaoll222
 * Create time: 2019/10/21 20:29
 */
@Data
public class SeqElem<T> implements Elem<T> {
    private static final long serialVersionUID = -4740409459278407206L;

    private T value;

    public SeqElem(T value) {
        this.value = value;
    }
}
