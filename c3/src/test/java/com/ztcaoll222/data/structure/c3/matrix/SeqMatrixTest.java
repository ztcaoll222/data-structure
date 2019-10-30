package com.ztcaoll222.data.structure.c3.matrix;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Flogger
class SeqMatrixTest {

    @Test
    void getMaxSize() {
        var matrix = new SeqMatrix<Integer>(4, 3);
        assertEquals(12, matrix.getMaxSize());
    }

    @Test
    void put() {
        var matrix = new SeqMatrix<Integer>(4, 3);
        assertFalse(matrix.put(1, 5, 2));
        assertFalse(matrix.put(5, 1, 2));

        assertTrue(matrix.put(1, 1, 2));
        assertTrue(matrix.put(2, 1, 2));
        assertTrue(matrix.put(2, 2, 2));
        assertTrue(matrix.put(3, 1, 2));
        assertTrue(matrix.put(3, 2, 2));
        assertTrue(matrix.put(3, 3, 2));

        assertEquals("2, 0, 0, 2;\n" +
                "2, 2, 0, 2;\n" +
                "2, 2, 2, 0", matrix.toString());
    }

    @Test
    void testToString() {
        var matrix = new SeqMatrix<Integer>(4, 3);

        assertTrue(matrix.put(1, 1, 2));
        assertTrue(matrix.put(2, 1, 2));
        assertTrue(matrix.put(3, 2, 2));
        assertTrue(matrix.put(3, 3, 2));

        assertEquals("2, 0, 0, 2;\n" +
                "2, 0, 0, 0;\n" +
                "0, 2, 2, 0", matrix.toString());
    }
}
