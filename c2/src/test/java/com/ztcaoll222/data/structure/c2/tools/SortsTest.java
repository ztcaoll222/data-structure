package com.ztcaoll222.data.structure.c2.tools;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@Log
class SortsTest {

    @Test
    void bubbleSort() {
        log.info(Arrays.toString(Sorts.bubbleSort(6, 4, 8, 6, 2, 2)));
    }
}
