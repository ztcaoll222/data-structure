package com.ztcaoll222.data.structure.base.tools;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Flogger
class RangesTest {

    @Test
    void testRangeInt0() {
        var res = Ranges.rangeInt(1, 5, 2, true).map(Objects::toString).collect(Collectors.joining(", "));
        assertEquals("1, 3", res);
    }

    @Test
    void testRangeInt1() {
        var res = Ranges.rangeInt(1, 5, 3).map(Objects::toString).collect(Collectors.joining(", "));
        assertEquals("1, 4", res);
    }

    @Test
    void testRangeInt2() {
        var res = Ranges.rangeInt(1, 5).map(Objects::toString).collect(Collectors.joining(", "));
        assertEquals("1, 2, 3, 4", res);
    }
}
