package com.ztcaoll222.data.structure.c2.interfaces.node;

import java.io.Serializable;

/**
 * 数据元素
 *
 * @author ztcaoll222
 * Create time: 2019/9/28 20:32
 */
public interface Elem<T> extends Serializable {
    /**
     * 获得数据元素的值
     *
     * @return 数据元素的值
     */
    T getValue();

    /**
     * 设置元素的值
     *
     * @param value 元素的值
     */
    void setValue(T value);
}
