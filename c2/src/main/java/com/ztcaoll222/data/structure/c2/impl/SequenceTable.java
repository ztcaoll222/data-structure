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
    public boolean listInsert(int i, String value) {
        if (i < 0 || i > size + 1) {
            return false;
        }

        if (size == maxSize) {
            upgradeSize();
        }

        SequenceTableElem datum = new SequenceTableElem(value);
        for (int j = i; j < size + 1; j++) {
            SequenceTableElem tElem = data[j];
            data[j] = datum;
            datum = tElem;
        }
        size++;
        return true;
    }

    @Override
    public Optional<Elem<String>> listDelete(int i) {
        if (i < 0 || i > size) {
            return Optional.empty();
        }

        SequenceTableElem elem = data[i];
        for (int j = i; j < size; j++) {
            data[i] = data[i + 1];
        }
        size--;
        return Optional.ofNullable(elem);
    }

    @Override
    public String printList() {
        return Arrays.stream(data).filter(Objects::nonNull).map(Elem::getValue).collect(Collectors.joining(", "));
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
}
