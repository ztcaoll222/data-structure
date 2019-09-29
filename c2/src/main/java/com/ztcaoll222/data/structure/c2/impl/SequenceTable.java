package com.ztcaoll222.data.structure.c2.impl;

import com.ztcaoll222.data.structure.c2.Elem;
import com.ztcaoll222.data.structure.c2.LinearTable;
import lombok.Getter;

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
    @Getter
    private int maxSize;
    private SequenceTableElem<T>[] data;
    private int size = 0;

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

    private void upgradeSize(int newMaxSize) {
        if (size >= maxSize) {
            SequenceTableElem<T>[] newData = new SequenceTableElem[newMaxSize];

            System.arraycopy(data, 0, newData, 0, size);

            data = newData;
            maxSize = newMaxSize;
        }
    }

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
     * 删除具有最小值的元素, 并返回被删元素的值, 空出的位置由最后一个元素填补
     * 若表为空则返回空
     */
    public Optional<T> t22321() {
        if (empty()) {
            return Optional.empty();
        }

        SequenceTableElem<T> datum = data[0];
        int minIndex = 0;
        for (int i = 1; i < size; i++) {
            if (Integer.parseInt(data[i].getValue().toString()) < Integer.parseInt(datum.getValue().toString())) {
                datum = data[i];
                minIndex = i;
            }
        }

        data[minIndex] = data[size - 1];
        listDeleteLast();

        return Optional.of(datum.getValue());
    }

    /**
     * 逆置, 要求空间复杂度为 o(1)
     */
    public void t22322() {
        SequenceTableElem<T> tElem;
        for (int i = 0; i < size / 2; i++) {
            tElem = data[i];
            data[i] = data[size - 1 - i];
            data[size - 1 - i] = tElem;
        }
    }

    /**
     * 删除所有值为 @param value 的元素
     * 要求时间复杂度为 o(n), 空间复杂度为 o(1)
     */
    public int t22323(SequenceTableElem<T> elem) {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            SequenceTableElem<T> tElem = data[i];

            if (Objects.equals(elem, tElem)) {
                sum++;
            } else {
                data[i - sum] = data[i];
            }
        }
        size -= sum;
        return sum;
    }

    /**
     * 删除所有值为 @param value 的元素
     * 要求时间复杂度为 o(n), 空间复杂度为 o(1)
     */
    public int t22323(T value) {
        return t22323(new SequenceTableElem<>(value));
    }

    /**
     * 从有序表中删除其值在给定值在 @param s 和 @param t 之间的所有元素
     */
    public int t22324(int s, int t) {
        int sum = 0;

        if (s > t) {
            return 0;
        }

        for (int i = 0; i < size; i++) {
            SequenceTableElem<T> tElem = data[i];
            int tValue = Integer.parseInt(tElem.getValue().toString());
            if (tValue >= s && tValue <= t) {
                sum++;
            } else {
                data[i - sum] = data[i];
            }
        }

        size -= sum;

        return sum;
    }

    /**
     * 从顺序表中删除其值在给定值在 @param s 和 @param t 之间的所有元素
     */
    public int t22325(int s, int t) {
        return t22324(s, t);
    }

    /**
     * 从有序顺序表中删除所有其值重复的元素, 使表中所有的元素的值均不同
     */
    public int t22326() {
        int sum = 0;

        SequenceTableElem<T> tElem = data[0];
        for (int i = 1; i < size; i++) {
            if (Objects.equals(data[i], tElem)) {
                sum++;
            } else {
                data[i - sum] = data[i];
                tElem = data[i];
            }
        }

        size -= sum;

        return sum;
    }

    /**
     * 将两个有序顺序表合并为一个新的有序顺序表
     *
     * @return 新的顺序表
     */
    public SequenceTable<T> t22327(SequenceTable<T> b) {
        SequenceTable<T> newTable = new SequenceTable<>(maxSize + b.maxSize);

        int ia = 0;
        int ib = 0;
        while (ia < size || ib < b.size) {
            Optional<T> elemA = getElem(ia);
            Optional<T> elemB = b.getElem(ib);
            if (elemA.isEmpty() || elemB.isEmpty()) {
                break;
            }
            Integer va = Integer.parseInt(getElem(ia).get().toString());
            Integer vb = Integer.parseInt(b.getElem(ib).get().toString());
            if (va < vb) {
                newTable.listInsert((T) va);
                ia++;
            } else {
                newTable.listInsert((T) vb);
                ib++;
            }
        }

        if (size - ia > 0) {
            for (int i = ia; i < size; i++) {
                Integer va = Integer.parseInt(getElem(i).get().toString());
                newTable.listInsert((T) va);
            }
        }

        if (b.size - ib > 0) {
            for (int i = ib; i < size; i++) {
                Integer vb = Integer.parseInt(b.getElem(i).get().toString());
                newTable.listInsert((T) vb);
            }
        }
        return newTable;
    }
}
