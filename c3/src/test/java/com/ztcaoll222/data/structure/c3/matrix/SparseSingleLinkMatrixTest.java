package com.ztcaoll222.data.structure.c3.matrix;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Flogger
class SparseSingleLinkMatrixTest {
    private SequenceMatrix<Integer> matrix = new SequenceMatrix<>(4, 3);

    @BeforeEach
    void setUp() {
        matrix.put(2, 3, 5);
    }

    @Test
    void get() {
        var matrix = new SparseSingleLinkMatrix<>(this.matrix);
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
        var matrix = new SparseSingleLinkMatrix<>(this.matrix);

        assertTrue(matrix.put(1, 1, 1));
        assertEquals("1, 0, 0, 0;\n" +
                "0, 0, 5, 0;\n" +
                "0, 0, 0, 0", matrix.toString());

        assertTrue(matrix.put(2, 3, 23));
        assertEquals("1, 0, 0, 0;\n" +
                "0, 0, 23, 0;\n" +
                "0, 0, 0, 0", matrix.toString());
    }

    @Test
    void testToString() {
        var matrix = new SparseSingleLinkMatrix<>(this.matrix);

        assertEquals("0, 0, 0, 0;\n" +
                "0, 0, 5, 0;\n" +
                "0, 0, 0, 0", matrix.toString());
    }
}
