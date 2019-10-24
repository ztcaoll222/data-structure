package com.ztcaoll222.data.structure.base.entity;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Flogger
class PairTest {

    @Test
    void of() {
        var pair = Pair.of(9, "9");
        assertEquals("9, 9", pair.toString());
    }

    @Test
    void getK() {
        var pair = Pair.of(9, "9");
        assertEquals(9, pair.getK());
    }

    @Test
    void getV() {
        var pair = Pair.of(9, "9");
        assertEquals("9", pair.getV());
    }

    @Test
    void setK() {
        var pair = Pair.of(9, "9");
        assertEquals(9, pair.getK());
        pair.setK(8);
        assertEquals(8, pair.getK());
    }

    @Test
    void setV() {
        var pair = Pair.of(9, "9");
        assertEquals("9", pair.getV());
        pair.setV("8");
        assertEquals("8", pair.getV());
    }
}
