package com.ztcaoll222.data.structure.c2.interfaces;

import com.ztcaoll222.data.structure.base.interfaces.Elem;

import java.util.Optional;

/**
 * @author ztcaoll222
 * Create time: 2019/10/15 22:03
 */
public interface LinearTable<B extends Elem<T>, T> {
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
    Optional<B> locateElem(T value);

    /**
     * 按位查找
     * 从 1 开始数
     *
     * @param i 位置
     * @return 返回第 i 个位置的元素
     */
    Optional<B> findElem(int i);

    /**
     * 在最前面插入元素
     *
     * @param values 元素的值
     * @return 成功返回 true, 否则 false
     */
    boolean listInsertFirst(T[] values);

    /**
     * 插入
     * 从 1 开始数
     *
     * @param i      位置
     * @param values 元素的值
     * @return 插入成功则返回 true, 否则返回 false
     */
    boolean listInsert(int i, T[] values);

    /**
     * 在末尾插入元素
     *
     * @param values 元素的值
     * @return 成功返回 true, 否则 false
     */
    boolean listInsertLast(T[] values);

    /**
     * 删除第一个的元素
     *
     * @return 返回被删除的元素
     */
    Optional<B> listDeleteFirst();

    /**
     * 删除
     * 从 1 开始数
     *
     * @param i 位置
     * @return 返回被删除的元素
     */
    Optional<B> listDelete(int i);

    /**
     * 删除最后的元素
     *
     * @return 返回被删除的元素
     */
    Optional<B> listDeleteLast();

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
