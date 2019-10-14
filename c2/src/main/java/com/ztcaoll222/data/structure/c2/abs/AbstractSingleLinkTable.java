package com.ztcaoll222.data.structure.c2.abs;

import com.ztcaoll222.data.structure.c2.func.FunctionOneOne;
import com.ztcaoll222.data.structure.c2.interfaces.node.SingleLinkTableNode;

import java.util.Objects;
import java.util.Optional;

/**
 * 单链表的抽象类
 *
 * @author ztcaoll222
 * Create time: 2019/10/6 21:25
 */
public abstract class AbstractSingleLinkTable<T> extends AbstractLinkTable<SingleLinkTableNode<T>, T> {
    /**
     * 设置第一个节点
     *
     * @param datum 节点
     * @return 当前表
     */
    @Override
    public abstract AbstractSingleLinkTable<T> setFirst(SingleLinkTableNode<T> datum);

    /**
     * 获得第一个节点
     *
     * @return 第一个节点
     */
    @Override
    public abstract SingleLinkTableNode<T> getFirst();

    /**
     * 删除链表的最后一个节点的操作
     *
     * @param pre 最后一个元素的前一个节点
     * @return 被删掉的元素
     */
    @Override
    protected abstract SingleLinkTableNode<T> listDeleteLastOpt(SingleLinkTableNode<T> pre);

    /**
     * 删除链表的某一个节点的操作
     *
     * @param pre 被删除的节点的前一个节点, 不用判断被删除元素是否是最后一个元素
     */
    @Override
    protected abstract void listDeleteOpt(SingleLinkTableNode<T> pre);

    @Override
    public int length() {
        if (empty()) {
            return 0;
        }
        SingleLinkTableNode<T> tNode = getFirst();
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
     * @param func 条件, 当返回值为 true 结束循环
     * @return 某一个节点
     */
    private Optional<SingleLinkTableNode<T>> find(FunctionOneOne<SingleLinkTableNode<T>, Boolean> func) {
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
    public Optional<SingleLinkTableNode<T>> locateElem(T value) {
        return find(datum -> Objects.equals(value, datum.getValue()));
    }

    @Override
    public Optional<SingleLinkTableNode<T>> findElem(int i) {
        if (empty() || i < 0) {
            return Optional.empty();
        }

        try {
            var tNode = getFirst();
            for (int j = 1; j < i; j++) {
                tNode = tNode.getNext();
            }
            return Optional.of(tNode);
        } catch (NullPointerException ignore) {
            return Optional.empty();
        }
    }

    @Override
    public boolean listInsert(int limit, SingleLinkTableNode<T> datum) {
        if (limit < 0) {
            return false;
        }

        if (empty() || limit == 1) {
            setFirst(datum);
            return true;
        }

        var tNode = getFirst();
        for (int j = 1; j < limit - 1; j++) {
            tNode = tNode.getNext();
        }

        if (tNode == null) {
            return false;
        }

        var next = tNode.getNext();
        if (next != null) {
            listInsertOpt(tNode, datum, next);
        } else {
            listInsertLastOpt(tNode, datum);
        }

        return true;
    }

    @Override
    protected Optional<SingleLinkTableNode<T>> finLast() {
        return find(datum -> datum.getNext() == null);
    }

    @Override
    public Optional<SingleLinkTableNode<T>> listDeleteLast() {
        if (empty()) {
            return Optional.empty();
        }

        var first = getFirst();
        if (first.getNext() == null) {
            setFirst(null);
            return Optional.of(first);
        }

        return find(datum -> datum.getNext().getNext() == null).map(this::listDeleteLastOpt);
    }

    @Override
    public Optional<SingleLinkTableNode<T>> listDelete(int i) {
        if (empty()) {
            return Optional.empty();
        }

        if (i == 1) {
            var first = getFirst();
            listDeleteOpt(null);
            return Optional.of(first);
        }

        return findElem(i - 1).map(pre -> {
            var current = pre.getNext();
            if (pre.getNext().getNext() == null) {
                listDeleteLastOpt(pre);
            } else {
                listDeleteOpt(pre);
            }
            return current;
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
}
