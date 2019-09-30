package com.ztcaoll222.data.structure.c2;

import com.ztcaoll222.data.structure.c2.impl.SequenceTable;
import com.ztcaoll222.data.structure.c2.impl.SequenceTableElem;

import java.util.Objects;
import java.util.Optional;

/**
 * 第二章综合题
 *
 * @author ztcaoll222
 * Create time: 2019/9/30 10:48
 */
public class T2232 {
    private T2232() {
    }

    /**
     * 删除具有最小值的元素, 并返回被删元素的值, 空出的位置由最后一个元素填补
     * 若表为空则返回空
     */
    public static Optional<Integer> t1(SequenceTable<Integer> table) {
        if (table.empty()) {
            return Optional.empty();
        }

        SequenceTableElem<Integer> datum = table.data[0];
        int minIndex = 0;
        for (int i = 1; i < table.size; i++) {
            if (table.data[i].getValue() < datum.getValue()) {
                datum = table.data[i];
                minIndex = i;
            }
        }

        table.data[minIndex] = table.data[table.size - 1];
        table.listDeleteLast();

        return Optional.of(datum.getValue());
    }

    /**
     * 逆置, 要求空间复杂度为 o(1)
     */
    public static <T> void t2(SequenceTable<T> table) {
        SequenceTableElem<T> tElem;
        for (int i = 0; i < table.size / 2; i++) {
            tElem = table.data[i];
            table.data[i] = table.data[table.size - 1 - i];
            table.data[table.size - 1 - i] = tElem;
        }
    }

    /**
     * 删除所有值为 @param value 的元素
     * 要求时间复杂度为 o(n), 空间复杂度为 o(1)
     */
    public static <T> int t3(SequenceTable<T> table, SequenceTableElem<T> elem) {
        int sum = 0;

        for (int i = 0; i < table.size; i++) {
            SequenceTableElem<T> tElem = table.data[i];

            if (Objects.equals(elem, tElem)) {
                sum++;
            } else {
                table.data[i - sum] = table.data[i];
            }
        }

        table.size -= sum;

        return sum;
    }

    /**
     * 删除所有值为 @param value 的元素
     * 要求时间复杂度为 o(n), 空间复杂度为 o(1)
     */
    public static <T> int t3(SequenceTable<T> table, T value) {
        return t3(table, new SequenceTableElem<>(value));
    }

    /**
     * 从有序表中删除其值在给定值在 @param s 和 @param t 之间的所有元素
     */
    public static int t4(SequenceTable<Integer> table, int s, int t) {
        int sum = 0;

        if (s > t) {
            return 0;
        }

        for (int i = 0; i < table.size; i++) {
            SequenceTableElem<Integer> tElem = table.data[i];
            int tValue = tElem.getValue();
            if (tValue >= s && tValue <= t) {
                sum++;
            } else {
                table.data[i - sum] = table.data[i];
            }
        }

        table.size -= sum;

        return sum;
    }

    /**
     * 从顺序表中删除其值在给定值在 @param s 和 @param t 之间的所有元素
     */
    public static int t5(SequenceTable<Integer> table, int s, int t) {
        return t4(table, s, t);
    }

    /**
     * 从有序顺序表中删除所有其值重复的元素, 使表中所有的元素的值均不同
     */
    public static <T> int t6(SequenceTable<T> table) {
        int sum = 0;

        SequenceTableElem<T> tElem = table.data[0];
        for (int i = 1; i < table.size; i++) {
            if (Objects.equals(table.data[i], tElem)) {
                sum++;
            } else {
                table.data[i - sum] = table.data[i];
                tElem = table.data[i];
            }
        }

        table.size -= sum;

        return sum;
    }

    /**
     * 合并两个有序表
     */
    private static SequenceTable<Integer> merge(SequenceTable<Integer> a, SequenceTable<Integer> b) {
        SequenceTable<Integer> newTable = new SequenceTable<>(a.size + b.size);

        int ia = 0;
        int ib = 0;

        while (true) {
            var da = a.data[ia];
            var db = b.data[ib];
            if (da == null || db == null) {
                break;
            }

            var va = da.getValue();
            var vb = db.getValue();
            if (va < vb) {
                newTable.listInsert(va);
                ia++;
            } else {
                newTable.listInsert(vb);
                ib++;
            }
        }

        if (a.size - ia > 0) {
            while (ia < a.size) {
                newTable.listInsert(a.data[ia].getValue());
                ia++;
            }
        }
        if (b.size - ib > 0) {
            while (ib < b.size) {
                newTable.listInsert(b.data[ib].getValue());
                ib++;
            }
        }

        return newTable;
    }

    /**
     * 将两个有序顺序表合并为一个新的有序顺序表
     *
     * @return 新的顺序表
     */
    public static SequenceTable<Integer> t7(SequenceTable<Integer> a, SequenceTable<Integer> b) {
        return merge(a, b);
    }

    /**
     * 把 @param i 后的元素移动到最前面
     *
     * @param i 位置
     */
    public static <T> boolean t8(SequenceTable<T> table, int i) {
        if (i < 0 || i >= table.size) {
            return false;
        }

        if (table.size + i > table.maxSize) {
            table.upgradeSize(table.size + i);
        }

        for (int j = 0; j < i; j++) {
            table.data[table.size] = table.data[j];
            table.size++;
        }
        for (int k = i, g = 0; k < table.size; k++, g++) {
            table.data[g] = table.data[k];
        }

        table.size -= i;

        return true;
    }

    /**
     * 用最少时间在表中查找数值为 @param x 的元素, 如果找到则与后继元素交换
     * 若找不到则插入, 但仍使表有序
     *
     * @param x 数值
     */
    public static void t9(SequenceTable<Integer> table, Integer x) {
        int at = table.findElem(x);
        if (at != -1) {
            SequenceTableElem<Integer> tDatum = table.data[at];
            if (at != table.size - 1) {
                table.data[at] = table.data[at + 1];
                table.data[at + 1] = tDatum;
            }
        } else {
            for (at = 0; at < table.size; at++) {
                if (x < table.data[at].getValue()) {
                    break;
                }
            }

            table.listInsert(at, x);
        }
    }

    /**
     * 向左移 @param p 位
     */
    public static <T> boolean t10(SequenceTable<T> table, int p) {
        if (p < 0 || p >= table.size) {
            return false;
        }

        for (int i = p, j = 0; i < table.size; i++, j++) {
            table.data[j] = table.data[i];
        }

        table.size -= p;

        return true;
    }

    /**
     * 获得两个有序表的中位数
     */
    public static double t11(SequenceTable<Integer> a, SequenceTable<Integer> b) {
        SequenceTable<Integer> table = merge(a, b);
        if (table.size % 2 != 0) {
            return table.data[table.size / 2].getValue();
        } else {
            return (table.data[table.size / 2].getValue() + table.data[table.size / 2 - 1].getValue()) / 2.0;
        }
    }

    public static <T> Optional<T> t12(SequenceTable<T> table) {
        if (table.empty()) {
            return Optional.empty();
        }

        int count = 0;
        SequenceTableElem<T> tDatum = table.data[0];
        for (int i = 1; i < table.size; i++) {
            if (count > table.size / 2) {
                return Optional.of(tDatum.getValue());
            }
            if (Objects.equals(table.data[i], tDatum)) {
                count++;
            } else {
                count--;
                if (count < 0) {
                    count = 0;
                    tDatum = table.data[i];
                }
            }
        }

        if (count > 0) {
            return Optional.of(tDatum.getValue());
        }
        return Optional.empty();
    }
}
