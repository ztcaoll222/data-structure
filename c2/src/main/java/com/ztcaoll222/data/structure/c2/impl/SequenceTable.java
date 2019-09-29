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
public class SequenceTable implements LinearTable<String> {
    @Getter
    private int maxSize;
    private SequenceTableElem[] data;
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
    public Optional<Elem> locateElem(String value) {
        for (int i = 0; i < size; i++) {
            SequenceTableElem datum = data[i];
            if (Objects.equals(datum.getValue(), value)) {
                return Optional.of(datum);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getElem(int i) {
        return Optional.ofNullable(data[i]).map(SequenceTableElem::getValue);
    }

    private void upgradeSize(int newMaxSize) {
        if (size >= maxSize) {
            SequenceTableElem[] newData = new SequenceTableElem[newMaxSize];

            System.arraycopy(data, 0, newData, 0, size);

            data = newData;
            maxSize = newMaxSize;
        }
    }

    private void upgradeSize() {
        upgradeSize(maxSize * 2);
    }

    @Override
    public boolean listInsert(int i, Elem<String> datum) {
        if (i < 0 || i > size + 1) {
            return false;
        }

        if (size == maxSize) {
            upgradeSize();
        }

        for (int j = i; j < size + 1; j++) {
            SequenceTableElem tElem = data[j];
            data[j] = (SequenceTableElem) datum;
            datum = tElem;
        }
        size++;
        return true;
    }

    @Override
    public boolean listInsert(int i, String value) {
        SequenceTableElem datum = new SequenceTableElem(value);
        return listInsert(i, datum);
    }

    @Override
    public Elem<String> listDeleteLast() {
        SequenceTableElem lastDatum = data[size - 1];
        data[size - 1] = null;
        size--;
        return lastDatum;
    }

    @Override
    public Optional<Elem<String>> listDelete(int i) {
        if (i < 0 || i > size) {
            return Optional.empty();
        }

        if (i == size - 1) {
            return Optional.of(listDeleteLast());
        }

        SequenceTableElem elem = data[i];
        System.arraycopy(data, i + 1, data, i, size - i);
        size--;
        return Optional.ofNullable(elem);
    }

    @Override
    public String printList() {
        return Arrays.stream(data).limit(size).map(Elem::getValue).collect(Collectors.joining(", "));
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
    public Optional<String> t22321() {
        if (empty()) {
            return Optional.empty();
        }

        SequenceTableElem datum = data[0];
        int minIndex = 0;
        for (int i = 1; i < size; i++) {
            if (Integer.parseInt(data[i].getValue()) < Integer.parseInt(datum.getValue())) {
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
        SequenceTableElem tElem;
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
    public int t22323(SequenceTableElem elem) {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            SequenceTableElem tElem = data[i];

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
    public int t22323(String value) {
        return t22323(new SequenceTableElem(value));
    }
}
