package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.section;

import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.LinesCalendar;
import com.lmonkiewicz.commutee.routes.parser.warsaw.BaseSectionReaderTest;
import org.junit.Test;

import java.io.BufferedReader;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Created by lmonkiewicz on 2017-02-22.
 */
public class KDSectionReaderTest extends BaseSectionReaderTest {

    @Test
    public void correctlyParsesInputStream() throws Exception {
        try (BufferedReader reader = createReader("KDSectionData.txt")){

            final KDSectionReader kdSectionReader = new KDSectionReader();
            kdSectionReader.readSection(reader);

            final LinesCalendar linesCalendar = kdSectionReader.result();

            assertEquals("SB", linesCalendar.get(LocalDate.of(2017, 2, 25), "1"));
            assertEquals("NO", linesCalendar.get(LocalDate.of(2017, 2, 25), "N95"));
            assertEquals("DS", linesCalendar.get(LocalDate.of(2017, 2, 25), "Z-1"));

            assertEquals("DS", linesCalendar.get(LocalDate.of(2017, 2, 26), "3"));
            assertEquals("SB", linesCalendar.get(LocalDate.of(2017, 2, 26), "R9"));
            assertEquals("SB", linesCalendar.get(LocalDate.of(2017, 2, 26), "RL"));
        }
    }
}