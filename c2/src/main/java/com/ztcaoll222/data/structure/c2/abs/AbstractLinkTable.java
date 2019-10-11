package com.ztcaoll222.data.structure.c2.abs;

import com.ztcaoll222.data.structure.c2.func.FunctionOneOne;
import com.ztcaoll222.data.structure.c2.interfaces.node.SingleLinkTableNode;
import com.ztcaoll222.data.structure.c2.interfaces.table.LinearTable;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * 链表的抽象类
 *
 * @author ztcaoll222
 * Create time: 2019/10/6 21:25
 */
public abstract class AbstractLinkTable<B extends SingleLinkTableNode<T>, T> implements LinearTable<B, T> {
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
    public int length() {
        if (empty()) {
            return 0;
        }
        B tNode = getFirst();
        int count = 0;
        while (tNode != null) {
            count++;
            tNode = tNode.getNext();
        }
        return count;
    }

    /**
     * 根据某个条件获得某个节点
     *
     * @param func 条件
     * @return 某一个节点
     */
    protected Optional<B> find(FunctionOneOne<B, Boolean> func) {
        if (empty()) {
            return Optional.empty();
        }

        var tNode = getFirst();
        while (tNode != null && !func.execute(tNode)) {
            tNode = tNode.getNext();
        }

        return Optional.ofNullable(tNode);
    }

    @Override
    public Optional<B> locateElem(T value) {
        return find(datum -> Objects.equals(value, datum.getValue()));
    }

    @Override
    public Optional<B> findElem(int i) {
        if (empty() || i < 0) {
            return Optional.empty();
        }

        try {
            var tNode = getFirst();
            for (int j = 1; j <= i; j++) {
                tNode = tNode.getNext();
            }
            return Optional.of(tNode);
        } catch (NullPointerException ignore) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> getElem(int i) {
        return findElem(i - 1).map(B::getValue);
    }

    /**
     * 在末尾插入元素
     *
     * @param datum 元素
     * @return 插入成功则返回 true, 否则返回 false
     */
    public boolean listInsertLast(B datum) {
        if (empty()) {
            setFirst(datum);
            return true;
        }

        return finLast().map(last -> {
            last.setNext(datum);
            return true;
        }).orElse(false);
    }

    /**
     * 在链表中的某一位插入一个节点
     *
     * @param limit 位置
     * @param datum 待插入的某一个节点
     */
    protected boolean listInsert(int limit, B datum) {
        if (limit < 0) {
            return false;
        }

        if (empty()) {
            setFirst(datum);
            return true;
        }

        var tNode = getFirst();
        for (int j = 0; j < limit - 1; j++) {
            tNode = tNode.getNext();
        }

        var next = tNode.getNext();
        if (next != null) {
            tNode.setNext(datum);
            datum.setNext(next);
        } else {
            tNode.setNext(datum);
        }

        return true;
    }

    /**
     * 找到最后一个节点
     *
     * @return 最后一个节点
     */
    protected Optional<B> finLast() {
        return find(datum -> datum.getNext() == null);
    }

    @Override
    public Optional<B> listDeleteLast() {
        return find(datum -> datum.getNext().getNext() == null).map(pre -> {
            B last = pre.getNext();
            pre.setNext(null);
            return last;
        });
    }

    @Override
    public Optional<B> listDelete(int i) {
        return findElem(i - 2).map(pre -> {
            B tNode = pre.getNext();
            if (tNode.getNext() == null) {
                pre.setNext(null);
            } else {
                pre.setNext(tNode.getNext());
            }
            return tNode;
        });
    }

    @Override
    public String printList() {
        if (empty()) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        var tNode = getFirst();
        while (tNode != null) {
            stringBuilder.append(tNode.getValue());
            tNode = tNode.getNext();
            if (tNode != null) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
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
     */
    @SafeVarargs
    protected static <C extends AbstractLinkTable<B, T>, B extends SingleLinkTableNode<T>, T> C of(C table, T... objs) {
        Arrays.stream(objs).forEach(table::listInsertLast);
        return table;
    }
}
