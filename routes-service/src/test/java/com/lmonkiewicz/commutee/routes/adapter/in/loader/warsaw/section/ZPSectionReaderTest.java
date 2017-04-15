package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.section;

import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.BaseSectionReaderTest;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.BusStop;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.BusStopGroup;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.BusStopGroups;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.BusStopLineType;
import org.junit.Test;

import java.io.BufferedReader;

import static org.assertj.core.api.Assertions.assertThat;
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

            assertThat(group.stream().count()).isEqualTo(9);

            BusStop busStop = group.getBusStop("100101");
            assertThat(busStop).isNotNull();

            assertThat(busStop.getId()).isEqualTo("100101");
            assertThat(busStop.getName()).isEqualTo("Ul./Pl.: TARGOWA");
            assertThat(busStop.getDirection()).isEqualTo("Kier.: AL.ZIELENIECKA");
            assertThat(busStop.getX()).isEqualTo(21.044226);
            assertThat(busStop.getY()).isEqualTo(52.248678);
        }
    }

    @Test
    public void shouldCorrectlyParseBusStopLines() throws Exception {
        try(BufferedReader input = createReader("ZPSectionData.txt")){
            final ZPSectionReader reader = new ZPSectionReader();
            reader.readSection(input);

            final BusStopGroups busStopGroups = reader.result();
            final BusStopGroup group = busStopGroups.get("1001");
            BusStop busStop = group.getBusStop("100101");
            assertThat(busStop.getLines(BusStopLineType.PERMANENT)).containsExactly("123", "138", "146", "147", "166", "509", "517");
            assertThat(busStop.getLines(BusStopLineType.ON_DEMAND)).containsExactly("N02", "N03", "N21", "N71");
        }

    }
}