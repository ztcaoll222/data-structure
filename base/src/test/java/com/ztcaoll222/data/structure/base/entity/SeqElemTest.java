package com.ztcaoll222.data.structure.base.entity;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Flogger
class SeqElemTest {

    @Test
    void getValue() {
        var elem = new SeqElem<>(1);
        assertEquals(1, elem.getValue());
    }

    @Test
    void setValue() {
        var elem = new SeqElem<>(1);
        assertEquals(1, elem.getValue());

        elem.setValue(2);
        assertEquals(2, elem.getValue());
    }
}
