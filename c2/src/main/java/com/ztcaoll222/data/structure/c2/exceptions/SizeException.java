package com.ztcaoll222.data.structure.c2.exceptions;

/**
 * 表大小异常
 *
 * @author ztcaoll222
 * Create time: 2019/10/12 23:09
 */
public class SizeException extends RuntimeException {
    private static final long serialVersionUID = 2542561550625095777L;

    public SizeException(int i, int size) {
        super(String.format("The size returned by the length: %d function does not match the actual size: %d", size, i));
    }
}
