package com.ztcaoll222.data.structure.c3.matrix;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Flogger
class SparseMatrixTest {
    private SeqMatrix<Integer> matrix = new SeqMatrix<>(4, 3);

    @BeforeEach
    void setUp() {
        matrix.put(2, 3, 5);
    }

    @Test
    void getMaxSize() {
        var matrix = new SparseMatrix<>(this.matrix);
        var value = matrix.getValue(0, 1);
        assertTrue(value.isEmpty());

        value = matrix.getValue(2, 3);
        assertTrue(value.isPresent());
        assertEquals(5, value.get());
    }

    @Test
    void testToString() {
        var matrix = new SparseMatrix<>(this.matrix);

        assertEquals("0, 0, 0, 0;\n" +
                "0, 0, 5, 0;\n" +
                "0, 0, 0, 0", matrix.toString());
    }
}
