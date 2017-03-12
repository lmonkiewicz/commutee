package com.lmonkiewicz.commutee.routes.parser.warsaw.section;

import com.lmonkiewicz.commutee.routes.parser.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.parser.warsaw.SectionReaderException;
import com.lmonkiewicz.commutee.routes.parser.warsaw.ZtmUtils;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.Route;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.RouteDefinition;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.RoutePoints;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.Routes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.util.List;

/**
 * Created by lmonkiewicz on 07.03.2017.
 */
public class TRSectionReader extends AbstractSectionReader<Routes, Route>{

    private Routes routes = new Routes();

    @Override
    public Routes result() {
        return routes;
    }

    @Nullable
    @Override
    protected Route onSectionContentLine(@NotNull String line) {
        final List<String> values = ZtmUtils.asColumns(3, line, 9, 39, 4, 3, 34, 9, 5, 5, 4, 10);

        final Route route = Route.builder()
                .routeId(ZtmUtils.trimTrailingString(values.get(0), ",").trim())
                .beginingStop(ZtmUtils.trimTrailingString(values.get(1), ",").trim())
                .beginingTown(values.get(2))
                .endingStop(ZtmUtils.trimTrailingString(values.get(4), ",").trim())
                .endingTown(values.get(5))
                .direction(values.get(7))
                .position(values.get(9))
                .build();

        routes.add(route);

        return route;
    }

    @Override
    protected void onSectionStart(@NotNull String sectionCode, @NotNull BufferedReader in) throws SectionReaderException {
        switch (sectionCode) {
            case "LW": {
                LWSectionReader lwSectionReader = new LWSectionReader();
                lwSectionReader.readSection(in);
                final RouteDefinition result = lwSectionReader.result();
                getLastLineResult().ifPresent(last -> last.setRouteDefinition(result));
                break;
            }
            case "RP": {
                RPSectionReader rpSectionReader = new RPSectionReader();
                rpSectionReader.readSection(in);
                final RoutePoints result = rpSectionReader.result();
                getLastLineResult().ifPresent(last -> last.setRoutePoints(result));
                break;
            }
        }
    }

    @Override
    protected String getSectionCode() {
        return "TR";
    }
}
