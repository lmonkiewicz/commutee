package com.lmonkiewicz.commutee.routes.parser.warsaw.section;

import com.lmonkiewicz.commutee.routes.parser.warsaw.BaseSectionReaderTest;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.Line;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.Lines;
import org.junit.Test;

import java.io.BufferedReader;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lmonkiewicz on 07.03.2017.
 */
public class LLSectionReaderTest extends BaseSectionReaderTest {

    @Test
    public void shouldCorrectlyParseLinesList() throws Exception {
        try(BufferedReader input = createReader("LLSectionData.txt")){
            LLSectionReader reader = new LLSectionReader();
            reader.readSection(input);
            Lines lines = reader.result();

            assertThat(lines).isNotNull();
            assertThat(lines.stream().count()).isEqualTo(1);

            assertThat(lines.get("123").isPresent()).isFalse();

            final Optional<Line> line = lines.get("1");
            assertThat(line.isPresent()).isTrue();
            assertThat(line.get().getNumber()).isEqualTo("1");
            assertThat(line.get().getDescription()).isEqualTo("LINIA TRAMWAJOWA");
        }

    }
}
