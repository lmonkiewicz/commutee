package com.lmonkiewicz.commutee.routes.parser.warsaw.ka;

import com.lmonkiewicz.commutee.routes.parser.warsaw.BaseSectionReaderTest;
import org.junit.Test;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lmonkiewicz on 2017-02-20.
 */
public class KASectionReaderTest extends BaseSectionReaderTest {

    @Test
    public void correctlyParsesInputStream() throws Exception {

        try(BufferedReader input = createReader("KASectionData.txt")){

            final KASectionReader reader = new KASectionReader();

            reader.readSection(input);

            final RoutesCalendar result = reader.result();

            assertNotNull(result);

            final List<String> firstDay = result.getRouteTypesFor(LocalDate.of(2015, 12, 1));
            assertEquals(5, firstDay.size());
            assertTrue(firstDay.contains("D2"));
            assertTrue(firstDay.contains("N2"));
            assertTrue(firstDay.contains("DP"));
            assertTrue(firstDay.contains("NS"));
            assertTrue(firstDay.contains("NO"));
            assertFalse(firstDay.contains("D4"));

            assertTrue(result.isType(LocalDate.of(2015, 12, 1), "D2"));
            assertTrue(result.isType(LocalDate.of(2015, 12, 1), "N2"));
            assertTrue(result.isType(LocalDate.of(2015, 12, 1), "DP"));
            assertTrue(result.isType(LocalDate.of(2015, 12, 1), "NS"));
            assertTrue(result.isType(LocalDate.of(2015, 12, 1), "NO"));
            assertFalse(result.isType(LocalDate.of(2015, 12, 1), "D4"));


            final List<String> lastDay = result.getRouteTypesFor(LocalDate.of(2015, 12, 6));
            assertEquals(6, lastDay.size());
            assertTrue(lastDay.contains("D7"));
            assertTrue(lastDay.contains("N7"));
            assertTrue(lastDay.contains("TS"));
            assertTrue(lastDay.contains("DS"));
            assertTrue(lastDay.contains("NS"));
            assertTrue(lastDay.contains("NO"));
            assertFalse(lastDay.contains("D2"));
        }
    }
}