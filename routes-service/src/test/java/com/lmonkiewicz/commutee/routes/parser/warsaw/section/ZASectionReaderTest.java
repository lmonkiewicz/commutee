package com.lmonkiewicz.commutee.routes.parser.warsaw.section;

import com.lmonkiewicz.commutee.routes.parser.warsaw.BaseSectionReaderTest;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.BusStopGroup;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.BusStopGroups;
import org.junit.Test;

import java.io.BufferedReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by lmonkiewicz on 2017-02-23.
 */
public class ZASectionReaderTest extends BaseSectionReaderTest {

    @Test
    public void shouldCorrectlyParseZASectionData() throws Exception {
        try(BufferedReader input = createReader("ZASectionData.txt")){
            ZASectionReader reader = new ZASectionReader();
            reader.readSection(input);

            BusStopGroups result = reader.result();

            BusStopGroup g4009 = result.get("4009");
            assertNotNull(g4009);
            assertEquals("1 SIERPNIA,", g4009.getName());
            assertEquals("--", g4009.getTownCode());
            assertEquals("WARSZAWA", g4009.getTownName());

            BusStopGroup g5131 = result.get("5131");
            assertNotNull(g5131);
            assertEquals("3 MAJA,", g5131.getName());
            assertEquals("OM", g5131.getTownCode());
            assertEquals("OŻARÓW MAZOWIECKI", g5131.getTownName());
        }

    }
}