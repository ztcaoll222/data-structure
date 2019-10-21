package com.ztcaoll222.data.structure.c2.abs;

import com.ztcaoll222.data.structure.base.interfaces.Elem;
import com.ztcaoll222.data.structure.c2.entity.Pair;
import com.ztcaoll222.data.structure.c2.interfaces.LinearTable;

import java.util.Objects;
import java.util.Optional;

/**
 * 链表抽象类
 *
 * @author ztcaoll222
 * Create time: 2019/10/18 18:07
 */
public abstract class AbstractLinkTable<B extends Elem<T>, T> implements LinearTable<B, T> {

    /**
     * 获得第一个节点
     *
     * @return 第一个节点
     */
    protected abstract B getFirst();

    /**
     * 判断是否是最后一个节点
     *
     * @param node 某个节点
     * @return 是则返回 true, 否则 false
     */
    protected abstract boolean isEnd(B node);

    /**
     * 获得下一个节点
     *
     * @param node 某个节点
     * @return 某个节点的下一个节点
     */
    protected abstract B getNext(B node);

    @Override
    public int length() {
        if (empty()) {
            return 0;
        }

        int count = 0;
        var tNode = getFirst();
        do {
            tNode = getNext(tNode);
            count++;
        } while (!isEnd(tNode));
        return count;
    }

    @Override
    public Optional<B> locateElem(T value) {
        if (empty()) {
            return Optional.empty();
        }

        var tNode = getFirst();
        do {
            tNode = getNext(tNode);
        } while (!isEnd(tNode) && !Objects.equals(value, tNode.getValue()));

        if (isEnd(tNode)) {
            return Optional.empty();
        } else {
            return Optional.of(tNode);
        }
    }

    @Override
    public Optional<B> findElem(int i) {
        if (empty() && i <= 0) {
            return Optional.empty();
        }

        if (i == 1) {
            return Optional.ofNullable(getFirst());
        }

        var tNode = getFirst();
        int j = 1;
        do {
            tNode = getNext(tNode);
            j++;
        } while (j < i && !isEnd(tNode));

        if (isEnd(tNode)) {
            return Optional.empty();
        } else {
            return Optional.of(tNode);
        }
    }

    /**
     * 根据元素的值创建节点
     *
     * @param values 元素的值
     * @return key: first value: tail
     */
    protected abstract Optional<Pair<B, B>> createNode(T[] values);

    /**
     * 在最前面插入元素
     *
     * @param pair 待插入的节点链, key: first, value: tail
     * @return 成功返回 true, 否则返回 false
     */
    protected abstract boolean listInsertFirst(Pair<B, B> pair);

    @SafeVarargs
    @Override
    public final boolean listInsertFirst(T... values) {
        return createNode(values).map(this::listInsertFirst).orElse(false);
    }

    /**
     * 插入
     * 从 1 开始数
     *
     * @param i    位置
     * @param pair 待插入的节点链, key: first, value: tail
     * @return 插入成功则返回 true, 否则返回 false
     */
    protected abstract boolean listInsert(int i, Pair<B, B> pair);

    @SafeVarargs
    @Override
    public final boolean listInsert(int i, T... values) {
        return createNode(values).map(first -> listInsert(i, first)).orElse(false);
    }

    /**
     * 在末尾插入元素
     *
     * @param pair 待插入的节点链, key: first, value: tail
     * @return 成功返回 true, 否则返回 false
     */
    protected abstract boolean listInsertLast(Pair<B, B> pair);

    @SafeVarargs
    @Override
    public final boolean listInsertLast(T... values) {
        return createNode(values).map(this::listInsertLast).orElse(false);
    }

    @Override
    public String printList() {
        if (empty()) {
            return "";
        }

        var sb = new StringBuilder();
        var tNode = getFirst();
        while (!isEnd(getNext(tNode))) {
            sb.append(tNode.getValue());
            sb.append(", ");
            tNode = getNext(tNode);
        }
        sb.append(tNode.getValue());
        return sb.toString();
    }

    @Override
    public boolean empty() {
        return isEnd(getFirst());
    }

    /**
     * 初始化链表
     *
     * @param table  链表
     * @param values 元素的值
     */
    protected static <B extends AbstractLinkTable<C, T>, C extends Elem<T>, T> B of(B table, T[] values) {
        if (values.length != 0) {
            table.listInsertLast(values);
        }
        return table;
    }
}
