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
        while (ia < a.size && ib < b.size) {
            var da = a.data[ia];
            var db = b.data[ib];
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
     * 二分查找法
     */
    private static Optional<Integer> binarySearch(SequenceTable<Integer> table, Integer target) {
        if (table.empty()) {
            return Optional.empty();
        }

        if (table.data[table.size - 1].getValue() < target) {
            return Optional.empty();
        }

        int left = 0;
        int right = table.size - 1;
        while (left < right) {
            // ">>>" 无符号右移, 即使相加溢出, 变为负数, 转为无符号之后会变为正数
            int mid = (left + right) >>> 1;
            if (table.data[mid].getValue() < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (table.data[left].getValue().equals(target)) {
            return Optional.of(left);
        } else {
            return Optional.empty();
        }
    }

    /**
     * 用最少时间在有序表中查找数值为 @param x 的元素, 如果找到则与后继元素交换
     * 若找不到则插入, 但仍使表有序
     *
     * @param x 数值
     */
    public static void t9(SequenceTable<Integer> table, Integer x) {
        var at = binarySearch(table, x);
        if (at.isPresent()) {
            SequenceTableElem<Integer> tDatum = table.data[at.get()];
            if (at.get() != table.size - 1) {
                table.data[at.get()] = table.data[at.get() + 1];
                table.data[at.get() + 1] = tDatum;
            }
        } else {
            int i;
            for (i = 0; i < table.size; i++) {
                if (x < table.data[i].getValue()) {
                    break;
                }
            }
            table.listInsert(i, x);
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
     * 在数组中获取中位数
     */
    private static double getMedian(SequenceTableElem<Integer>[] data, int size) {
        if (size <= 0) {
            return -1.0;
        }

        if (size % 2 != 0) {
            return data[size / 2].getValue();
        } else {
            return (data[size / 2].getValue() + data[size / 2 - 1].getValue()) / 2.0;
        }
    }

    /**
     * 获得有序表的中位数
     */
    private static double getMedian(SequenceTable<Integer> table) {
        return getMedian(table.data, table.size);
    }

    /**
     * 获得两个有序表的中位数
     */
    public static Optional<Double> t11(SequenceTable<Integer> a, SequenceTable<Integer> b) {
        // 如果 a 为空, 那么中位数就在 b 中
        if (a.empty()) {
            return Optional.of(getMedian(b));
        }

        // 如果 a 是长度大于 b, 那么互换
        if (a.size > b.size) {
            return t11(b, a);
        }

        int left = 0;
        int total = a.size + b.size;
        // 因为在 a[max] < b[min] 时不好去做什么处理, 所以不减 1
        int right = a.size;
        while (left < right) {
            int midA = (right + left) >>> 1;
            // 左半边 left_part_size = (total + 1) / 2 = midA + midB
            //                    ==> midB = (m + n + 1) / 2 - midA
            int midB = ((total + 1) >>> 1) - midA;

            if (a.data[midA].getValue() < b.data[midB - 1].getValue()) {
                left = midA + 1;
            } else {
                right = midA;
            }
        }

        int i = left;
        int j = ((total + 1) >>> 1) - i;

        // 当 i == 0 时, 即切在 a 的最左边, 说明中位数在 b 上, 而且此时切线也不可能在 b 的最左边, 所以返回 b[切线]
        int leftMaxA = (i == 0 ? Integer.MIN_VALUE : a.data[i - 1].getValue());
        // 当 j == 0 时, 即切在 b 的最左边, 说明中位数在 a 上, 而且此时切线也不可能在 a 的最左边, 所以返回 a[切线]
        int leftMaxB = (j == 0 ? Integer.MIN_VALUE : b.data[j - 1].getValue());
        // 当 total 是偶数的时候, 需要考虑右半边的大小了
        // 当 i == a.size 时, 即切在 a 的最右边, 说明中位数在 b 上, 而且此时切线也不可能在 b 的最右边, 所以返回 b[切线]
        int rightMinA = (i == a.size ? Integer.MAX_VALUE : a.data[i].getValue());
        // 当 i == b.size 时, 即切在 b 的最右边, 说明中位数在 a 上, 而且此时切线也不可能在 a 的最右边, 所以返回 a[切线]
        int rightMinB = (j == b.size ? Integer.MAX_VALUE : b.data[j].getValue());

        if (((a.size + b.size) & 1) == 1) {
            // 奇数, 只要左边最大的
            return Optional.of((double) Math.max(leftMaxA, leftMaxB));
        } else {
            // 偶数, 左边最大的和右边最大的相加除 2
            return Optional.of((Math.max(leftMaxA, leftMaxB) + Math.min(rightMinA, rightMinB)) / 2.0);
        }
    }

    /**
     * 获得顺序表的主元素
     */
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

    /**
     * 找到顺序表中未出现的最小正整数
     */
    public static int t13(SequenceTable<Integer> table) {
        for (int i = 0; i < table.size; i++) {
            var tDatum = table.data[i];
            if (tDatum.getValue() > 0
                    && tDatum.getValue() < table.size
                    && !tDatum.getValue().equals(table.data[tDatum.getValue() - 1].getValue())) {
                table.data[i] = table.data[tDatum.getValue() - 1];
                table.data[tDatum.getValue() - 1] = tDatum;
            }
        }

        for (int i = 0; i < table.size; i++) {
            if (table.data[i].getValue() != i + 1) {
                return i + 1;
            }
        }

        return table.size + 1;
    }
}
