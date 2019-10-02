package com.ztcaoll222.data.structure.c2;

import java.util.Optional;

/**
 * 线性表
 *
 * @author ztcaoll222
 * Create time: 2019/9/28 20:26
 */
public interface LinearTable<T> {
    /**
     * 求表长
     *
     * @return 返回线性表的长度, 即数据元素的个数
     */
    int length();

    /**
     * 按值查找
     *
     * @param value 关键字
     * @return 返回查找具有给定关键字的元素
     */
    Optional<? extends Elem<T>> locateElem(T value);

    /**
     * 按位查找
     *
     * @param i 位置
     * @return 返回第 i 个位置的元素的值
     */
    Optional<T> getElem(int i);

    /**
     * 插入
     *
     * @param i     位置
     * @param value 元素的值
     * @return 插入成功则返回 true, 否则返回 false
     */
    boolean listInsert(int i, T value);

    /**
     * 插入
     *
     * @param value 元素的值
     * @return 插入成功则返回 true, 否则返回 false
     */
    boolean listInsert(T value);

    /**
     * 删除最后的元素
     *
     * @return 返回被删除的元素
     */
    Optional<? extends Elem<T>> listDeleteLast();

    /**
     * 删除
     *
     * @param i 位置
     * @return 返回被删除的元素
     */
    Optional<? extends Elem<T>> listDelete(int i);

    /**
     * 输出
     *
     * @return 按前后输出线性表的所有元素值
     */
    String printList();

    /**
     * 判空
     *
     * @return 若为空, 则返回 true, 否则 false
     */
    boolean empty();

    /**
     * 销毁
     */
    void destroyList();
}
