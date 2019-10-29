package com.ztcaoll222.data.structure.c3.matrix;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Flogger
class SymmetricMatrixTest {

    @Test
    void getN() {
        var matrix = new SymmetricMatrix<Integer>(3);
        assertEquals(3, matrix.getN());
    }

    @Test
    void put() {
        var matrix = new SymmetricMatrix<Integer>(3);
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
        var matrix = new SymmetricMatrix<Integer>(0);
        assertEquals("", matrix.toString());

        matrix = new SymmetricMatrix<Integer>(3);

        assertTrue(matrix.put(1, 1, 2));
        assertTrue(matrix.put(2, 1, 2));
        assertTrue(matrix.put(3, 2, 2));
        assertTrue(matrix.put(3, 3, 2));

        assertEquals("2, 2, 0;\n" +
                "2, 0, 2;\n" +
                "0, 2, 2", matrix.toString());
    }
}
