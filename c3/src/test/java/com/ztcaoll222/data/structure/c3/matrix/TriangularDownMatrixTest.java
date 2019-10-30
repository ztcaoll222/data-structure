package com.ztcaoll222.data.structure.c3.matrix;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Flogger
class TriangularDownMatrixTest {

    @Test
    void getN() {
        var matrix = new TriangularDownMatrix<>(3, 3);
        assertEquals(3, matrix.getN());
    }

    @Test
    void put() {
        var matrix = new TriangularDownMatrix<>(3, 3);
        assertFalse(matrix.put(1, 4, 2));
        assertFalse(matrix.put(4, 1, 2));

        assertTrue(matrix.put(1, 1, 2));
        assertTrue(matrix.put(2, 1, 2));
        assertTrue(matrix.put(2, 2, 2));
        assertTrue(matrix.put(3, 1, 2));
        assertTrue(matrix.put(3, 2, 2));
        assertTrue(matrix.put(3, 3, 2));

        assertEquals("2, 3, 3;\n" +
                "2, 2, 3;\n" +
                "2, 2, 2", matrix.toString());
    }

    @Test
    void testToString() {
        var matrix = new TriangularDownMatrix<>(0, 3);
        assertEquals("", matrix.toString());

        matrix = new TriangularDownMatrix<>(3, 3);

        assertTrue(matrix.put(1, 1, 2));
        assertTrue(matrix.put(2, 1, 2));
        assertTrue(matrix.put(3, 2, 2));
        assertTrue(matrix.put(3, 3, 2));

        assertEquals("2, 3, 3;\n" +
                "2, 0, 3;\n" +
                "0, 2, 2", matrix.toString());
    }
}
