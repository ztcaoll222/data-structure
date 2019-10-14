package com.ztcaoll222.data.structure.c2.abs;

import com.ztcaoll222.data.structure.c2.exceptions.SizeException;
import com.ztcaoll222.data.structure.c2.func.FunctionTwoOne;
import com.ztcaoll222.data.structure.c2.interfaces.node.DoubleLinkTableNode;

import java.util.Objects;
import java.util.Optional;

/**
 * 双链表的抽象类
 *
 * @author ztcaoll222
 * Create time: 2019/10/12 18:10
 */
public abstract class AbstractDoubleLinkTable<T> extends AbstractLinkTable<DoubleLinkTableNode<T>, T> {
    /**
     * 设置第一个节点
     *
     * @param datum 节点
     * @return 当前表
     */
    @Override
    public abstract AbstractDoubleLinkTable<T> setFirst(DoubleLinkTableNode<T> datum);

    /**
     * 获得第一个节点
     *
     * @return 第一个节点
     */
    @Override
    public abstract DoubleLinkTableNode<T> getFirst();

    /**
     * 检测是否存在表大小异常
     *
     * @param node 当前节点
     * @param size 表大小
     * @param i    当前循环的 index
     */
    private void checkSize(DoubleLinkTableNode<T> node, int size, int i) {
        if (node == null && i < size) {
            throw new SizeException(i, size);
        }
    }

    /**
     * 根据某个条件获得某个节点
     *
     * @param func 条件, 当返回值为 true 结束循环
     * @return 某一个节点
     */
    private Optional<DoubleLinkTableNode<T>> find(FunctionTwoOne<Integer, DoubleLinkTableNode<T>, Boolean> func) {
        if (empty()) {
            return Optional.empty();
        }

        int size = length();
        var tNode = getFirst();
        int i;
        for (i = 0; i < size; i++) {
            checkSize(tNode, size, i);

            if (func != null && func.execute(i, tNode)) {
                break;
            }

            tNode = tNode.getNext();
        }

        if (i == size) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(tNode);
        }
    }

    @Override
    protected Optional<DoubleLinkTableNode<T>> finLast() {
        return find((i, datum) -> i == length() - 1);
    }

    @Override
    public Optional<DoubleLinkTableNode<T>> locateElem(T value) {
        return find((i, datum) -> Objects.equals(value, datum.getValue()));
    }

    @Override
    public Optional<DoubleLinkTableNode<T>> findElem(int i) {
        if (empty() || i < 0) {
            return Optional.empty();
        }

        var size = length();
        if (i > size) {
            return Optional.empty();
        }

        var tNode = getFirst();
        for (int j = 1; j < i; j++) {
            checkSize(tNode, size, j);
            tNode = tNode.getNext();
        }
        return Optional.ofNullable(tNode);
    }

    @Override
    public boolean listInsert(int limit, DoubleLinkTableNode<T> datum) {
        if (limit < 0) {
            return false;
        }

        var size = length();
        if (limit - 1 > size) {
            return false;
        }

        if (empty() || limit == 1) {
            setFirst(datum);
            return true;
        }

        var tNode = getFirst();
        for (int j = 1; j < limit - 1; j++) {
            checkSize(tNode, size, j);
            tNode = tNode.getNext();
        }

        DoubleLinkTableNode<T> next = tNode.getNext();
        if (next != null) {
            listInsertOpt(tNode, datum, next);
        } else {
            listInsertLastOpt(tNode, datum);
        }

        return true;
    }

    @Override
    public Optional<DoubleLinkTableNode<T>> listDeleteLast() {
        return finLast().map(this::listDeleteLastOpt);
    }

    @Override
    public Optional<DoubleLinkTableNode<T>> listDelete(int i) {
        return findElem(i).map(current -> {
            if (i == length()) {
                listDeleteLastOpt(current);
            } else {
                listDeleteOpt(current);
            }
            return current;
        });
    }

    @Override
    public String printList() {
        if (empty()) {
            return "";
        }

        int size = length();
        var tNode = getFirst();
        var sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            checkSize(tNode, size, i);

            sb.append(tNode.getValue());
            if (i < size - 1) {
                sb.append(", ");
            }
            tNode = tNode.getNext();
        }
        return sb.toString();
    }
}
