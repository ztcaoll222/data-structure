package com.ztcaoll222.data.structure.c2.impl.table;

import com.ztcaoll222.data.structure.c2.abs.AbstractDoubleLinkTable;
import com.ztcaoll222.data.structure.c2.impl.node.DoubleLinkTableNodeImpl;
import com.ztcaoll222.data.structure.c2.interfaces.node.DoubleLinkTableNode;

/**
 * 双链表
 *
 * @author ztcaoll222
 * Create time: 2019/10/2 22:29
 */
public class DoubleLinkTable<T> extends AbstractDoubleLinkTable<T> {
    private int size = 0;
    public DoubleLinkTableNode<T> node;

    @Override
    public int length() {
        return size;
    }

    @Override
    public DoubleLinkTable<T> setFirst(DoubleLinkTableNode<T> datum) {
        if (datum == null) {
            node = null;
            size = 0;
            return this;
        }

        if (node != null) {
            datum.setNext(node);
            node.setPre(datum);
        }
        node = datum;
        size++;
        return this;
    }

    @Override
    public DoubleLinkTableNode<T> getFirst() {
        return node;
    }

    @Override
    protected void listInsertLastOpt(DoubleLinkTableNode<T> last, DoubleLinkTableNode<T> datum) {
        last.setNext(datum);
        datum.setPre(last);
        size++;
    }

    @Override
    protected void listInsertOpt(DoubleLinkTableNode<T> current, DoubleLinkTableNode<T> datum, DoubleLinkTableNode<T> next) {
        current.setNext(datum);
        datum.setPre(current);
        datum.setNext(next);
        next.setPre(datum);
        size++;
    }

    @Override
    protected DoubleLinkTableNode<T> listDeleteLastOpt(DoubleLinkTableNode<T> last) {
        var pre = last.getPre();
        if (pre != null) {
            pre.setNext(null);
        } else {
            node = null;
        }
        size--;
        return last;
    }

    @Override
    protected void listDeleteOpt(DoubleLinkTableNode<T> current) {
        DoubleLinkTableNode<T> pre = current.getPre();
        DoubleLinkTableNode<T> next = current.getNext();
        if (pre != null) {
            pre.setNext(current.getNext());
            next.setPre(pre);
        } else {
            node = next;
        }
        size--;
    }

    @Override
    public boolean listInsert(int i, T value) {
        return listInsert(i, new DoubleLinkTableNodeImpl<>(value));
    }

    @Override
    public void listInsertLast(T value) {
        listInsertLast(new DoubleLinkTableNodeImpl<>(value));
    }

    /**
     * 创建双链表
     */
    @SafeVarargs
    public static <T> DoubleLinkTable<T> of(T... objs) {
        var table = new DoubleLinkTable<T>();
        return of(table, objs);
    }
}
