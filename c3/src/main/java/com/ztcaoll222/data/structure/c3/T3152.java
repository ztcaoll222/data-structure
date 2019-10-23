package com.ztcaoll222.data.structure.c3;

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
}
