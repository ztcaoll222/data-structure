package com.ztcaoll222.data.structure.c2.table;

import com.ztcaoll222.data.structure.c2.entity.SeqElem;
import com.ztcaoll222.data.structure.c2.interfaces.LinearTable;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 顺序表
 *
 * @author ztcaoll222
 * Create time: 2019/9/28 20:27
 */
public class SequenceTable<T> implements LinearTable<SeqElem<T>, T> {
    public int maxSize;
    public SeqElem<T>[] data;
    public int size = 0;

    public SequenceTable(int maxSize) {
        this.maxSize = maxSize;
        data = new SeqElem[maxSize];
    }

    @Override
    public int length() {
        return this.size;
    }

    @Override
    public Optional<SeqElem<T>> locateElem(T value) {
        for (int i = 0; i < size; i++) {
            SeqElem<T> datum = data[i];
            if (Objects.equals(datum.getValue(), value)) {
                return Optional.of(datum);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<SeqElem<T>> findElem(int i) {
        if (i > size || i <= 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(data[i - 1]);
    }

    /**
     * 扩展存储空间, 时间复杂度是 o(n)
     *
     * @param newMaxSize 新的空间大小
     */
    public void upgradeSize(int newMaxSize) {
        SeqElem<T>[] newData = new SeqElem[newMaxSize];

        System.arraycopy(data, 0, newData, 0, size);

        data = newData;
        maxSize = newMaxSize;
    }

    /**
     * 扩展存储空间, 时间复杂度是 o(n)
     * 扩展的空间为当前空间的两倍
     */
    private void upgradeSize() {
        if (maxSize == 0) {
            upgradeSize(1);
        } else {
            upgradeSize(maxSize * 2);
        }
    }

    /**
     * 在最前面插入元素
     *
     * @param datum 元素
     */
    private boolean listInsertFirst(SeqElem<T> datum) {
        if (size == maxSize) {
            upgradeSize();
        }

        if (empty()) {
            data[0] = datum;
        } else {
            System.arraycopy(data, 0, data, 1, size);
            data[0] = datum;
        }
        size++;
        return true;
    }

    /**
     * 反向遍历集合
     *
     * @param values 元素的值
     */
    @SafeVarargs
    private Stream<T> reverse(T... values) {
        return IntStream.rangeClosed(0, values.length - 1).mapToObj(i -> values[values.length - i - 1]);
    }

    @SafeVarargs
    @Override
    public final boolean listInsertFirst(T... values) {
        if (values.length == 0) {
            return false;
        }
        return reverse(values).map(SeqElem::new).allMatch(this::listInsertFirst);
    }

    /**
     * 插入
     *
     * @param i     位置
     * @param datum 元素
     * @return 插入成功则返回 true, 否则返回 false
     */
    private boolean listInsert(int i, SeqElem<T> datum) {
        if (i <= 1) {
            listInsertFirst(datum);
            return true;
        }

        if (i - 1 >= size) {
            listInsertLast(datum);
            return true;
        }

        if (size == maxSize) {
            upgradeSize();
        }

        System.arraycopy(data, i - 1, data, i, size - (i - 1));
        data[i - 1] = datum;
        size++;
        return true;
    }

    @SafeVarargs
    @Override
    public final boolean listInsert(int i, T... values) {
        if (values.length == 0) {
            return false;
        }
        return reverse(values).map(SeqElem::new).allMatch(datum -> listInsert(i, datum));
    }

    /**
     * 在末尾插入元素
     *
     * @param datum 元素
     */
    private boolean listInsertLast(SeqElem<T> datum) {
        if (size == maxSize) {
            upgradeSize();
        }

        data[size] = datum;
        size++;
        return true;
    }

    @SafeVarargs
    @Override
    public final boolean listInsertLast(T... values) {
        if (values.length == 0) {
            return false;
        }
        return Arrays.stream(values).map(SeqElem::new).allMatch(this::listInsertLast);
    }

    @Override
    public Optional<SeqElem<T>> listDeleteFirst() {
        if (empty()) {
            return Optional.empty();
        }

        var elem = data[0];
        System.arraycopy(data, 1, data, 0, size - 1);
        return Optional.of(elem);
    }

    @Override
    public Optional<SeqElem<T>> listDelete(int i) {
        if (i < 0 || i > size) {
            return Optional.empty();
        }

        if (i == 1) {
            return listDeleteFirst();
        }

        if (i == size) {
            return listDeleteLast();
        }

        SeqElem<T> elem = data[i - 1];
        for (int j = i - 1; j < size - 1; j++) {
            data[j] = data[j + 1];
        }
        size--;
        return Optional.ofNullable(elem);
    }

    @Override
    public Optional<SeqElem<T>> listDeleteLast() {
        if (size == 0) {
            return Optional.empty();
        }
        SeqElem<T> lastDatum = data[size - 1];
        data[size - 1] = null;
        size--;
        return Optional.ofNullable(lastDatum);
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
        data = new SeqElem[newMaxSize];
        maxSize = newMaxSize;
        size = 0;
    }

    /**
     * 创建顺序表
     */
    @SafeVarargs
    public static <T> SequenceTable<T> of(T... objs) {
        SequenceTable<T> table = new SequenceTable<>(objs.length);
        table.listInsertLast(objs);
        return table;
    }
}
