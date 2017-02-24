package com.lmonkiewicz.commutee.routes.parser.warsaw.za;

import com.lmonkiewicz.commutee.routes.parser.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.parser.warsaw.ZtmUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by lmonkiewicz on 2017-02-23.
 */
public class ZASectionReader extends AbstractSectionReader<BusStopGroups, BusStopGroup> {
    private final BusStopGroups data = new BusStopGroups();

    @Override
    public BusStopGroups result() {
        return data;
    }

    @Override
    protected BusStopGroup onSectionContentLine(@NotNull String line) {
        final List<String> columns = ZtmUtils.asColumns(1, line, 6, 36, 4, 48);

        final BusStopGroup group = BusStopGroup.builder()
                .id(columns.get(0))
                .name(columns.get(1))
                .townCode(columns.get(2))
                .townName(columns.get(3))
                .build();

        data.add(group);
        return group;
    }
}
