package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.section;

import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.SectionReaderException;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.ZtmUtils;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.Courses;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.Line;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.Lines;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.Routes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.util.List;

/**
 * Created by lmonkiewicz on 07.03.2017.
 */
public class LLSectionReader extends AbstractSectionReader<Lines, Line> {

    private Lines lines = new Lines();

    @Override
    public Lines result() {
        return lines;
    }

    @Nullable
    @Override
    protected Line onSectionContentLine(@NotNull String line) {
        final List<String> values = ZtmUtils.asColumns(1, line, 6, 6, 2, 50);

        final Line busLine = Line.builder()
                .number(values.get(1))
                .description(values.get(3))
                .build();

        lines.add(busLine);

        return busLine;
    }

    @Override
    protected void onSectionStart(@NotNull String sectionCode, @NotNull BufferedReader in) throws SectionReaderException {
        switch(sectionCode){
            case Sections.TR_Routes: {
                TRSectionReader trSectionReader = new TRSectionReader();
                trSectionReader.readSection(in);
                final Routes result = trSectionReader.result();
                getLastLineResult().ifPresent(last -> last.setRoutes(result));
                break;
            }
            case Sections.WK_Courses: {
                final WKSectionReader wkSectionReader = new WKSectionReader();
                wkSectionReader.readSection(in);
                final Courses courses = wkSectionReader.result();
                getLastLineResult().ifPresent(last -> last.setCourses(courses));
                break;
            }
        }
    }

    @Override
    protected String getSectionCode() {
        return Sections.LL_Lines;
    }
}
