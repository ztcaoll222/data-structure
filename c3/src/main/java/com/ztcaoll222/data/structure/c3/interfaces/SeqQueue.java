package com.ztcaoll222.data.structure.c3.interfaces;

/**
 * @author ztcaoll222
 * Create time: 2019/10/28 10:53
 */
public interface SeqQueue {
    /**
     * 判断队列是否满
     *
     * @return 是返回 true, 否则 false
     */
    boolean queueOverFlow();

    /**
     * 转为字符串
     *
     * @param delimiter 分隔符
     * @return 字符串
     */
    String toString(String delimiter);
}
