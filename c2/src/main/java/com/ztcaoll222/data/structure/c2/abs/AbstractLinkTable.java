package com.ztcaoll222.data.structure.c2.abs;

import com.ztcaoll222.data.structure.c2.interfaces.node.Elem;
import com.ztcaoll222.data.structure.c2.interfaces.table.LinearTable;

import java.util.Arrays;
import java.util.Optional;

/**
 * 链表的抽象类
 *
 * @author ztcaoll222
 * Create time: 2019/10/6 21:25
 */
public abstract class AbstractLinkTable<B extends Elem<T>, T> implements LinearTable<B, T> {
    /**
     * 设置第一个节点
     *
     * @param datum 节点
     * @return 当前表
     */
    public abstract AbstractLinkTable<B, T> setFirst(B datum);

    /**
     * 获得第一个节点
     *
     * @return 第一个节点
     */
    public abstract B getFirst();

    @Override
    public Optional<T> getElem(int i) {
        return findElem(i).map(B::getValue);
    }

    /**
     * 在末尾插入元素的操作
     *
     * @param last  当前表最后一个元素
     * @param datum 元素
     */
    protected abstract void listInsertLastOpt(B last, B datum);

    /**
     * 在链表中的某一位插入一个节点的操作
     *
     * @param current 当前的节点
     * @param datum   待插入的节点
     * @param next    当前节点的下一个节点, 一定不为空
     */
    protected abstract void listInsertOpt(B current, B datum, B next);

    /**
     * 删除链表的最后一个节点的操作
     *
     * @param last 当前表最后一个元素
     * @return 被删掉的元素
     */
    protected abstract B listDeleteLastOpt(B last);

    /**
     * 删除链表的某一个节点的操作
     *
     * @param current 被删除的节点, 一定不是最后一个节点
     */
    protected abstract void listDeleteOpt(B current);

    /**
     * 找到最后一个节点
     *
     * @return 最后一个节点
     */
    protected abstract Optional<B> finLast();

    /**
     * 在链表中的某一位插入一个节点
     *
     * @param limit 位置
     * @param datum 待插入的某一个节点
     * @return 插入成功则返回 true, 否则返回 false
     */
    public abstract boolean listInsert(int limit, B datum);

    /**
     * 在末尾插入元素
     *
     * @param datum 元素
     */
    public void listInsertLast(B datum) {
        if (empty()) {
            setFirst(datum);
            return;
        }

        finLast().ifPresent(last -> listInsertLastOpt(last, datum));
    }

    @Override
    public boolean empty() {
        return getFirst() == null;
    }

    @Override
    public void destroyList() {
        setFirst(null);
    }

    /**
     * 创建链表
     * 如果 @param args 的长度为 0, 那么返回的类型不建议简写成 var
     */
    @SafeVarargs
    protected static <C extends AbstractLinkTable<B, T>, B extends Elem<T>, T> C of(C table, T... objs) {
        if (objs.length == 0) {
            return table;
        }

        Arrays.stream(objs).forEach(table::listInsertLast);
        return table;
    }
}
