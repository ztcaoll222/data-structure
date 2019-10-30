package com.ztcaoll222.data.structure.c3.matrix;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Flogger
class TriangularSeqUpMatrixTest {

    @Test
    void getN() {
        var matrix = new TriangularSeqUpMatrix<>(3, 3);
        assertEquals(3, matrix.getN());
    }

    @Test
    void get() {
        var matrix = new TriangularSeqUpMatrix<>(4, 3);
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
        var matrix = new TriangularSeqUpMatrix<>(3, 3);
        assertFalse(matrix.put(1, 4, 2));
        assertFalse(matrix.put(4, 1, 2));

        assertTrue(matrix.put(1, 1, 2));
        assertTrue(matrix.put(1, 2, 2));
        assertTrue(matrix.put(1, 3, 2));
        assertTrue(matrix.put(2, 2, 2));
        assertTrue(matrix.put(2, 3, 2));
        assertTrue(matrix.put(3, 3, 2));

        assertEquals("2, 2, 2;\n" +
                "3, 2, 2;\n" +
                "3, 3, 2", matrix.toString());
    }

    @Test
    void testToString() {
        var matrix = new TriangularSeqUpMatrix<>(0, 3);
        assertEquals("", matrix.toString());

        matrix = new TriangularSeqUpMatrix<>(3, 3);

        assertTrue(matrix.put(1, 1, 2));
        assertTrue(matrix.put(1, 2, 2));
        assertTrue(matrix.put(2, 3, 2));
        assertTrue(matrix.put(3, 3, 2));

        assertEquals("2, 2, 0;\n" +
                "3, 0, 2;\n" +
                "3, 3, 2", matrix.toString());
    }
}
