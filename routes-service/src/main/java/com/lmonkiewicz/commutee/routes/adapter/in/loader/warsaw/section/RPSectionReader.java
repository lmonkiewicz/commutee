package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.section;

import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.SectionReaderException;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.ZtmUtils;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.RoutePoint;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.RoutePoints;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.util.List;

/**
 * Created by lmonkiewicz on 07.03.2017.
 */
public class RPSectionReader extends AbstractSectionReader<RoutePoints, RoutePoint>{

    private RoutePoints routePoints = new RoutePoints();

    @Override
    public RoutePoints result() {
        return routePoints;
    }

    @Nullable
    @Override
    protected RoutePoint onSectionContentLine(@NotNull String line) {
        final List<String> values = ZtmUtils.asColumns(5, line, 6, 34, 2, 7, 14, 3, 14);
        final RoutePoint routePoint = RoutePoint.builder()
                .id(values.get(0))
                .name(values.get(1))
                .townCode(values.get(2))
                .y(Double.valueOf(values.get(4)))
                .x(Double.valueOf(values.get(6)))
                .build();

        routePoints.add(routePoint);

        return routePoint;

    }

    @Override
    protected void onSectionStart(@NotNull String sectionCode, @NotNull BufferedReader in) throws SectionReaderException {
        switch (sectionCode) {
            case Sections.TD_Timetables: {
                TDSectionReader reader = new TDSectionReader();
                reader.readSection(in);
                getLastLineResult().ifPresent(routePoint -> routePoint.setTimetables(reader.result()));
                break;
            }
            case Sections.OP_Metadata: {
                OPSectionReader reader = new OPSectionReader();
                reader.readSection(in);
                getLastLineResult().ifPresent(routePoint -> routePoint.setMetadata(reader.result()));
                break;
            }
        }
    }

    @Override
    protected String getSectionCode() {
        return Sections.RP_RoutePoints;
    }
}
