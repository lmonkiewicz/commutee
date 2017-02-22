package com.lmonkiewicz.commutee.routes.parser.warsaw.kd;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Created by lmonkiewicz on 2017-02-22.
 */
public class LinesCalendarTest {


    @Test
    public void noDateResultsWithNull() throws Exception {
        final LinesCalendar linesCalendar = new LinesCalendar();

        linesCalendar.set(LocalDate.now(), "175", "DS");

        assertEquals(null, linesCalendar.get(null, "175"));
    }

    @Test
    public void noLineResultsWithNull() throws Exception {
        final LinesCalendar linesCalendar = new LinesCalendar();

        linesCalendar.set(LocalDate.now(), "175", "DS");

        assertEquals(null, linesCalendar.get(LocalDate.now(), null));
    }

    @Test
    public void shouldCorrectlyReturnValue() throws Exception {
        final LinesCalendar linesCalendar = new LinesCalendar();

        linesCalendar.set(LocalDate.now(), "175", "DS");

        final String dayType = linesCalendar.get(LocalDate.now(), "175");
        assertEquals("DS", dayType);

    }
}