package com.lmonkiewicz.commutee.routes.parser.warsaw.section;

import com.lmonkiewicz.commutee.routes.parser.warsaw.BaseSectionReaderTest;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.BusStopGroup;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.BusStopGroups;
import org.junit.Test;

import java.io.BufferedReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by lmonkiewicz on 2017-02-25.
 */
public class ZPSectionReaderTest extends BaseSectionReaderTest{

    @Test
    public void shouldCorrectlyParseBusStopGroupData() throws Exception {
        try(BufferedReader input = createReader("ZPSectionData.txt")){
            final ZPSectionReader reader = new ZPSectionReader();
            reader.readSection(input);

            final BusStopGroups busStopGroups = reader.result();

            assertEquals(3, busStopGroups.stream().count());

            final BusStopGroup g1001 = busStopGroups.get("1001");
            assertNotNull(g1001);
            assertEquals("KIJOWSKA,", g1001.getName());
            assertEquals("--", g1001.getTownCode());
            assertEquals("WARSZAWA", g1001.getTownName());
        }
    }


    @Test
    public void shouldCorrectlyParseBusStopsInGroups() throws Exception {
        try(BufferedReader input = createReader("ZPSectionData.txt")){
            final ZPSectionReader reader = new ZPSectionReader();
            reader.readSection(input);

            final BusStopGroups busStopGroups = reader.result();

            final BusStopGroup group = busStopGroups.get("1001");
            assertEquals(9, group.stream().count());

        }
    }

}