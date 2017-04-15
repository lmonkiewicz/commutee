package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.section;

import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.BaseSectionReaderTest;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.ZtmData;
import org.junit.Test;

import java.io.BufferedReader;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by lmonkiewicz on 17.03.2017.
 */
public class RootSectionReaderTest extends BaseSectionReaderTest {

    @Test
    public void correctlyParsesWholeFileStructure() throws Exception {
        try(BufferedReader in = createReader("RootSectionData.txt")){
            RootSectionReader reader = new RootSectionReader();
            reader.readSection(in);

            final ZtmData ztmData = reader.result();

            assertThat(ztmData).isNotNull();
            assertThat(ztmData.getDayTypes()).isNotNull();
            assertThat(ztmData.getRoutesCalendar()).isNotNull();
            assertThat(ztmData.getLinesCalendar()).isNotNull();
            assertThat(ztmData.getBusStopGroupsList()).isNotNull();
            assertThat(ztmData.getBusStopGroups()).isNotNull();
            assertThat(ztmData.getTownCodes()).isNotNull();
            assertThat(ztmData.getLines()).isNotNull();
        }

    }
}