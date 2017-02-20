package com.lmonkiewicz.commutee.routes.parser.warsaw.ka;

import com.lmonkiewicz.commutee.routes.parser.warsaw.BaseSectionParserTest;
import org.junit.Test;

import java.io.BufferedReader;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lmonkiewicz on 2017-02-20.
 */
public class KASectionReaderTest extends BaseSectionParserTest {

    @Test
    public void correctlyParsesInputStream() throws Exception {

        try(BufferedReader input = createReader("KASectionData.txt")){

            KASectionReader reader = new KASectionReader();

            reader.readSection(input);

            RoutesCalendar result = reader.result();

            assertNotNull(result);

            final List<String> firstDay = result.getRouteTypesFor("2015-12-01");
            assertEquals(5, firstDay.size());
            assertTrue(firstDay.contains("D2"));
            assertTrue(firstDay.contains("N2"));
            assertTrue(firstDay.contains("DP"));
            assertTrue(firstDay.contains("NS"));
            assertTrue(firstDay.contains("NO"));
            assertFalse(firstDay.contains("D4"));

            assertTrue(result.isType("2015-12-01", "D2"));
            assertTrue(result.isType("2015-12-01", "N2"));
            assertTrue(result.isType("2015-12-01", "DP"));
            assertTrue(result.isType("2015-12-01", "NS"));
            assertTrue(result.isType("2015-12-01", "NO"));
            assertFalse(result.isType("2015-12-01", "D4"));


            final List<String> lastDay = result.getRouteTypesFor("2015-12-06");
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