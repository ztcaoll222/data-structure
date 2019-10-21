package com.ztcaoll222.data.structure.base.interfaces;

import java.io.Serializable;

/**
 * 元素接口
 *
 * @author ztcaoll222
 * Create time: 2019/10/14 17:24
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
