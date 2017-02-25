package com.lmonkiewicz.commutee.routes.parser.warsaw.section;

import com.lmonkiewicz.commutee.routes.parser.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.parser.warsaw.ZtmUtils;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.BusStop;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lmonkiewicz on 2017-02-25.
 */
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
        return "PR";
    }

    @Nullable
    @Override
    protected BusStop onSectionContentLine(@NotNull String line) {
        switch(ZtmUtils.getIndentationLevel(line, ZtmUtils.DEFAULT_INDENT_SIZE)){
            case BUS_STOP_INDENT: {
                final List<String> values = ZtmUtils.asColumns(BUS_STOP_INDENT, line, 9, 7, 43, 40, 3, 14, 3, 14);

                final BusStop busStop = BusStop.builder()
                        .id(values.get(0))
                        .build();

                data.put(busStop.getId(), busStop);
                return busStop;
            }
            case LINES_INDENT: {
                break;
            }
        }
        return null;
    }
}
