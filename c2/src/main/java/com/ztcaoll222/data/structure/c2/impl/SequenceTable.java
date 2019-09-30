package com.ztcaoll222.data.structure.c2.impl;

import com.ztcaoll222.data.structure.c2.Elem;
import com.ztcaoll222.data.structure.c2.LinearTable;

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
public class SequenceTable<T> implements LinearTable<T> {
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
    public Optional<Elem<T>> locateElem(T value) {
        for (int i = 0; i < size; i++) {
            SequenceTableElem<T> datum = data[i];
            if (Objects.equals(datum.getValue(), value)) {
                return Optional.of(datum);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> getElem(int i) {
        if (i >= size) {
            return Optional.empty();
        }
        return Optional.ofNullable(data[i]).map(SequenceTableElem::getValue);
    }

    /**
     * 扩展存储空间, 时间复杂度是 o(n)
     *
     * @param newMaxSize 新的空间大小
     */
    public void upgradeSize(int newMaxSize) {
        if (size >= maxSize) {
            SequenceTableElem<T>[] newData = new SequenceTableElem[newMaxSize];

            System.arraycopy(data, 0, newData, 0, size);

            data = newData;
            maxSize = newMaxSize;
        }
    }

    /**
     * 扩展存储空间, 时间复杂度是 o(n)
     * 扩展的空间为当前空间的两倍
     */
    private void upgradeSize() {
        upgradeSize(maxSize * 2);
    }

    @Override
    public boolean listInsert(int i, Elem<T> datum) {
        if (i < 0 || i > size + 1) {
            return false;
        }

        if (size == maxSize) {
            upgradeSize();
        }

        for (int j = i; j < size + 1; j++) {
            Elem<T> tElem = data[j];
            data[j] = (SequenceTableElem<T>) datum;
            datum = tElem;
        }
        size++;
        return true;
    }

    @Override
    public boolean listInsert(int i, T value) {
        Elem<T> datum = new SequenceTableElem<>(value);
        return listInsert(i, datum);
    }

    @Override
    public boolean listInsert(T value) {
        Elem<T> datum = new SequenceTableElem<>(value);
        return listInsert(size, datum);
    }

    @Override
    public Elem<T> listDeleteLast() {
        SequenceTableElem<T> lastDatum = data[size - 1];
        data[size - 1] = null;
        size--;
        return lastDatum;
    }

    @Override
    public Optional<Elem<T>> listDelete(int i) {
        if (i < 0 || i > size) {
            return Optional.empty();
        }

        if (i == size - 1) {
            return Optional.of(listDeleteLast());
        }

        SequenceTableElem<T> elem = data[i];
        System.arraycopy(data, i + 1, data, i, size - i);
        size--;
        return Optional.ofNullable(elem);
    }

    @Override
    public String printList() {
        return Arrays.stream(data).limit(size).map(elem -> elem.getValue().toString()).collect(Collectors.joining(", "));
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
     * 通过二分法查找数值为 @param x 的元素
     *
     * @param x 数值
     * @return 如果找到则返回具体位置, 否则返回 -1
     */
    public int findElem(T x) {
        for (int i = 0; i < size / 2; i++) {
            var a = data[i].getValue();
            var b = data[size / 2 + i].getValue();
            if (a == x) {
                return i;
            }
            if (b == x) {
                return size / 2 + i;
            }
        }

        return -1;
    }
}
