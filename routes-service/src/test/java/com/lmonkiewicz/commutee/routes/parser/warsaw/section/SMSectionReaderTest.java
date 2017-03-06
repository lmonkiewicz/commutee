package com.lmonkiewicz.commutee.routes.parser.warsaw.section;

import com.lmonkiewicz.commutee.routes.parser.warsaw.BaseSectionReaderTest;
import org.junit.Test;

import java.io.BufferedReader;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by lmonkiewicz on 06.03.2017.
 */
public class SMSectionReaderTest extends BaseSectionReaderTest {


    @Test
    public void shouldCorrectlyParseTownData() throws Exception {
        try (BufferedReader input = createReader("SMSectionData.txt")){
            final SMSectionReader reader = new SMSectionReader();
            reader.readSection(input);

            final Map<String, String> result = reader.result();
            assertThat(result).hasSize(6);
            assertThat(result).containsEntry("AN","ANTONINÓW");
            assertThat(result).containsEntry("BD","BORZĘCIN DUŻY");
        }

    }
}