package com.ztcaoll222.data.structure.c3.matrix;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Flogger
class SymmetricSeqMatrixTest {

    @Test
    void getN() {
        var matrix = new SymmetricSeqMatrix<Integer>(3);
        assertEquals(3, matrix.getN());
    }

    @Test
    void get() {
        var matrix = new SymmetricSeqMatrix<Integer>(4);
        var value = matrix.get(9, 9);
        assertTrue(value.isEmpty());

        value = matrix.get(1, 1);
        assertTrue(value.isEmpty());

        assertTrue(matrix.put(1, 1, 5));
        value = matrix.get(1, 1);
        assertTrue(value.isPresent());
        assertEquals(5, value.get());
    }

    @Test
    void put() {
        var matrix = new SymmetricSeqMatrix<Integer>(3);
        assertFalse(matrix.put(1, 4, 2));
        assertFalse(matrix.put(4, 1, 2));

        assertTrue(matrix.put(1, 1, 2));
        assertTrue(matrix.put(2, 1, 2));
        assertTrue(matrix.put(2, 2, 2));
        assertTrue(matrix.put(3, 1, 2));
        assertTrue(matrix.put(3, 2, 2));
        assertTrue(matrix.put(3, 3, 2));

        assertEquals("2, 2, 2;\n" +
                "2, 2, 2;\n" +
                "2, 2, 2", matrix.toString());
    }

    @Test
    void testToString() {
        var matrix = new SymmetricSeqMatrix<Integer>(0);
        assertEquals("", matrix.toString());

        matrix = new SymmetricSeqMatrix<Integer>(3);

        assertTrue(matrix.put(1, 1, 2));
        assertTrue(matrix.put(2, 1, 2));
        assertTrue(matrix.put(3, 2, 2));
        assertTrue(matrix.put(3, 3, 2));

        assertEquals("2, 2, 0;\n" +
                "2, 0, 2;\n" +
                "0, 2, 2", matrix.toString());
    }
}
