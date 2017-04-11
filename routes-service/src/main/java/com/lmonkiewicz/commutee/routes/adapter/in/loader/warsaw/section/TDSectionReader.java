package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.section;

import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.SectionReaderException;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.ZtmUtils;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.RoutePointTimetable;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.RoutePointTimetables;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.util.List;

/**
 * Created by lmonkiewicz on 12.03.2017.
 */
public class TDSectionReader extends AbstractSectionReader<RoutePointTimetables, RoutePointTimetable>{
    private RoutePointTimetables timetables = new RoutePointTimetables();

    @Override
    public RoutePointTimetables result() {
        return timetables;
    }

    @Nullable
    @Override
    protected RoutePointTimetable onSectionContentLine(@NotNull String line) {
        final List<String> values = ZtmUtils.asColumns(7, line, 4, 30);
        final RoutePointTimetable timetable = RoutePointTimetable.builder()
                .dayType(values.get(0))
                .description(values.get(1))
                .build();

        timetables.add(timetable);

        return timetable;
    }

    @Override
    protected void onSectionStart(@NotNull String sectionCode, @NotNull BufferedReader in) throws SectionReaderException {
        switch (sectionCode) {
            case Sections.WG_DepartTimes: {
                WGSectionReader reader = new WGSectionReader();
                reader.readSection(in);
                getLastLineResult().ifPresent(last -> last.setDepartTimes(reader.result()));
                break;
            }
            case Sections.OD_Departures: {
                ODSectionReader reader = new ODSectionReader();
                reader.readSection(in);
                getLastLineResult().ifPresent(last -> last.setCourses(reader.result()));
                break;
            }
        }
    }

    @Override
    protected String getSectionCode() {
        return Sections.TD_Timetables;
    }
}
