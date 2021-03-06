package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.section;

import com.google.common.base.Splitter;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.ZtmUtils;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.BusStop;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.BusStopLineType;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lmonkiewicz on 2017-02-25.
 */
@Slf4j
public class PRSectionReader extends AbstractSectionReader<Map<String, BusStop>, BusStop> {

    private static final int BUS_STOP_INDENT = 3;
    private static final int LINES_INDENT = 4;

    private Map<String, BusStop> data = new HashMap<>();

    @Override
    public Map<String, BusStop> result() {
        return data;
    }

    @Override
    protected String getSectionCode() {
        return Sections.PR_BusStops;
    }

    @Nullable
    @Override
    protected BusStop onSectionContentLine(@NotNull String line) {
        switch(ZtmUtils.getIndentationLevel(line, ZtmUtils.DEFAULT_INDENT_SIZE)){
            case BUS_STOP_INDENT: {
                final List<String> values = ZtmUtils.asColumns(BUS_STOP_INDENT, line, 9, 7, 43, 41, 3, 14, 3, 14);

                final String id = values.get(0);

                Double y = -1.0;
                Double x = -1.0;

                try {
                    y = Double.valueOf(values.get(5));
                    x = Double.valueOf(values.get(7));
                } catch (NumberFormatException e) {
                    log.warn("Bad coordinates for {}: x={}, y={}", id, values.get(5), values.get(7));
                }

                final BusStop busStop = BusStop.builder()
                        .id(id)
                        .name(ZtmUtils.trimTrailingString(values.get(2),","))
                        .direction(ZtmUtils.trimTrailingString(values.get(3),","))
                        .y(y)
                        .x(x)
                        .build();

                data.put(busStop.getId(), busStop);
                return busStop;
            }
            case LINES_INDENT: {
                final List<String> values = ZtmUtils.asColumns(LINES_INDENT, line, 2, 4, 2, 20, 600);

                final BusStopLineType type = BusStopLineType.fromCode(ZtmUtils.trimTrailingString(values.get(3), ":"));
                final List<String> linesList = Splitter.fixedLength(6).trimResults().splitToList(values.get(4));

                linesList.stream()
                        .map(lineCode -> lineCode.endsWith("^") ? ZtmUtils.trimTrailingString(lineCode, "^") : lineCode)
                        .forEachOrdered(lineCode -> getLastLineResult()
                                .ifPresent(last -> last.addLine(type, lineCode)));
                break;
            }
        }
        return null;
    }
}
