package com.ztcaoll222.data.structure.c2.impl.table;

import com.ztcaoll222.data.structure.c2.impl.node.SequenceTableElem;
import com.ztcaoll222.data.structure.c2.interfaces.table.LinearTable;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 顺序表
 *
 * @author ztcaoll222
 * Create time: 2019/9/28 20:27
 */
public class SequenceTable<T> implements LinearTable<SequenceTableElem<T>, T> {
    public int maxSize;
    public SequenceTableElem<T>[] data;
    public int size = 0;

    public SequenceTable(int maxSize) {
        this.maxSize = maxSize;
        data = new SequenceTableElem[maxSize];
    }

    @Override
    public int length() {
        return this.size;
    }

    @Override
    public Optional<SequenceTableElem<T>> locateElem(T value) {
        for (int i = 0; i < size; i++) {
            SequenceTableElem<T> datum = data[i];
            if (Objects.equals(datum.getValue(), value)) {
                return Optional.of(datum);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<SequenceTableElem<T>> findElem(int i) {
        if (i > size || i < 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(data[i - 1]);
    }

    @Override
    public Optional<T> getElem(int i) {
        return findElem(i).map(SequenceTableElem::getValue);
    }

    /**
     * 扩展存储空间, 时间复杂度是 o(n)
     *
     * @param newMaxSize 新的空间大小
     */
    public void upgradeSize(int newMaxSize) {
        SequenceTableElem<T>[] newData = new SequenceTableElem[newMaxSize];

        System.arraycopy(data, 0, newData, 0, size);

        data = newData;
        maxSize = newMaxSize;
    }

    /**
     * 扩展存储空间, 时间复杂度是 o(n)
     * 扩展的空间为当前空间的两倍
     */
    private void upgradeSize() {
        upgradeSize(maxSize * 2);
    }

    /**
     * 插入
     *
     * @param i     位置
     * @param datum 元素
     * @return 插入成功则返回 true, 否则返回 false
     */
    private boolean listInsert(int i, SequenceTableElem<T> datum) {
        if (i < 0 || i > size + 1) {
            return false;
        }

        if (size == maxSize) {
            upgradeSize();
        }

        for (int j = i; j < size + 1; j++) {
            SequenceTableElem<T> tElem = data[j];
            data[j] = datum;
            datum = tElem;
        }
        size++;
        return true;
    }

    @Override
    public boolean listInsert(int i, T value) {
        SequenceTableElem<T> datum = new SequenceTableElem<>(value);
        return listInsert(i, datum);
    }

    @Override
    public boolean listInsertLast(T value) {
        SequenceTableElem<T> datum = new SequenceTableElem<>(value);
        return listInsert(size, datum);
    }

    @Override
    public Optional<SequenceTableElem<T>> listDeleteLast() {
        if (size == 0) {
            return Optional.empty();
        }
        SequenceTableElem<T> lastDatum = data[size - 1];
        data[size - 1] = null;
        size--;
        return Optional.ofNullable(lastDatum);
    }

    @Override
    public Optional<SequenceTableElem<T>> listDelete(int i) {
        if (i < 0 || i > size) {
            return Optional.empty();
        }

        if (i == size - 1) {
            return listDeleteLast();
        }

        SequenceTableElem<T> elem = data[i - 1];
        for (int j = i - 1; j < size - 1; j++) {
            data[j] = data[j + 1];
        }
        size--;
        return Optional.ofNullable(elem);
    }

    @Override
    public String printList() {
        return Arrays.stream(data).limit(size)
                .map(elem -> elem.getValue().toString())
                .collect(Collectors.joining(", "));
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public void destroyList() {
        int newMaxSize = 16;
        data = new SequenceTableElem[newMaxSize];
        maxSize = newMaxSize;
        size = 0;
    }

    /**
     * 创建顺序表
     */
    @SafeVarargs
    public static <T> SequenceTable<T> of(T... objs) {
        SequenceTable<T> table = new SequenceTable<>(objs.length);
        Arrays.stream(objs).forEach(table::listInsertLast);
        return table;
    }
}
