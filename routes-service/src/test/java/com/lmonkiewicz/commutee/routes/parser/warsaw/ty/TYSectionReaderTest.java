package com.lmonkiewicz.commutee.routes.parser.warsaw.ty;

import com.lmonkiewicz.commutee.routes.parser.warsaw.BaseSectionReaderTest;
import org.junit.Test;

import java.io.BufferedReader;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by lmonkiewicz on 2017-02-20.
 */
public class TYSectionReaderTest extends BaseSectionReaderTest {

    @Test
    public void correctlyParsesInputStream() throws Exception {

        try(BufferedReader input = createReader("TYSectionData.txt")){

            TYSectionReader parser = new TYSectionReader();

            parser.readSection(input);

            Map<String, String> result = parser.result();

            assertEquals(6, result.size());
            assertEquals("PONIEDZIAŁEK", result.get("D1"));
            assertEquals("DZIEŃ POWSZEDNI", result.get("DP"));
            assertEquals("ŚWIĘTO", result.get("DS"));
            assertEquals("SOBOTA", result.get("SB"));
            assertEquals("NOC SOBOTA/NIEDZIELA", result.get("N6"));
            assertEquals("SYLWESTER", result.get("SY"));

        }
    }

}