package com.lmonkiewicz.commutee.routes.parser.warsaw.section;

import com.lmonkiewicz.commutee.routes.parser.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.parser.warsaw.ZtmUtils;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.RouteDefinition;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.RouteStop;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * Created by lmonkiewicz on 07.03.2017.
 */
public class LWSectionReader extends AbstractSectionReader<RouteDefinition,RouteStop>{

    private RouteDefinition routeDefinition = new RouteDefinition();

    @Override
    public RouteDefinition result() {
        return routeDefinition;
    }

    @Nullable
    @Override
    protected RouteStop onSectionContentLine(@NotNull String line) {
        final List<String> values = ZtmUtils.asColumns(5, line, 32, 2, 8, 32, 3, 4, 4, 1, 2, 1, 2, 1);
        final RouteStop routeStop = RouteStop.builder()
                .streetName(Optional.ofNullable(values.get(0))
                        .map(name -> ZtmUtils.trimTrailingString(name, ","))
                        .filter(value -> !value.trim().isEmpty())
                        .orElseGet(() -> getLastLineResult()
                                .map(RouteStop::getStreetName)
                                .orElse(null)))
                .type(values.get(1))
                .busStopId(values.get(2))
                .stopName(ZtmUtils.trimTrailingString(values.get(3), ","))
                .townCode(values.get(4))
                .busStopGroupCode(values.get(5))
                .onDemand("NŻ".equals(values.get(6)))
                .minArrivalTime(Integer.valueOf(values.get(8)))
                .maxArrivalTime(Integer.valueOf(values.get(10)))
                .build();

        routeDefinition.add(routeStop);

        return routeStop;
    }

    @Override
    protected String getSectionCode() {
        return "LW";
    }
}
