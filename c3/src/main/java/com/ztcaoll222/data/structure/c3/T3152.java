package com.ztcaoll222.data.structure.c3;

import com.ztcaoll222.data.structure.c2.table.SingleLinkTable;
import com.ztcaoll222.data.structure.c3.stack.SeqStack;

import java.util.Objects;

/**
 * 第三章栈综合题
 *
 * @author ztcaoll222
 * Create time: 2019/10/21 9:21
 */
public class T3152 {
    private T3152() {
    }

    /**
     * 判断一段操作序列是否合法
     *
     * @param chars such as "IOIOIO"
     * @return 是返回 true, 否则 false
     */
    public static boolean t3_2(char... chars) {
        int count = 0;
        for (char aChar : chars) {
            switch (aChar) {
                case 'I':
                    count++;
                    break;
                case 'O':
                default:
                    count--;
                    if (count < 0) {
                        return false;
                    }
                    break;
            }
        }
        return count == 0;
    }

    /**
     * 判断一个单链表是否对称
     *
     * @param table 单链表
     */
    public static <T> boolean t4(SingleLinkTable<T> table) {
        var first = table.first;
        int length = table.length();
        var stack = new SeqStack<T>(length);
        int mid = length / 2;
        for (int i = 0; i < mid; i++) {
            stack.push(first.getValue());
            first = first.getNext();
        }

        if ((length & 1) == 1) {
            first = first.getNext();
        }

        while (first != null) {
            var elem = stack.pop().get();
            if (!Objects.equals(elem.getValue(), first.getValue())) {
                return false;
            }
            first = first.getNext();
        }

        return true;
    }
}
