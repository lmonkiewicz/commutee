package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.section;

import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.SectionReaderException;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.ZtmUtils;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.BusStop;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.BusStopGroup;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.BusStopGroups;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lmonkiewicz on 2017-02-24.
 */
public class ZPSectionReader extends AbstractSectionReader<BusStopGroups, BusStopGroup> {

    private final BusStopGroups data = new BusStopGroups();


    @Override
    public BusStopGroups result() {
        return data;
    }

    @Override
    protected BusStopGroup onSectionContentLine(@NotNull String line) {
        if (1 == ZtmUtils.getIndentationLevel(line, ZtmUtils.DEFAULT_INDENT_SIZE)) {
            final List<String> columns = ZtmUtils.asColumns(1, line, 7, 33, 4, 48);

            final BusStopGroup group = BusStopGroup.builder()
                    .id(columns.get(0))
                    .name(columns.get(1))
                    .townCode(columns.get(2))
                    .townName(columns.get(3))
                    .build();

            data.add(group);

            return group;
        }
        else {
            return null;
        }
    }


    @Override
    protected void onSectionStart(@NotNull String sectionCode, @NotNull BufferedReader in) throws SectionReaderException {
        if (Objects.equals(Sections.PR_BusStops, sectionCode)){
            PRSectionReader prSectionReader = new PRSectionReader();
            prSectionReader.readSection(in);

            final Map<String, BusStop> busStops = prSectionReader.result();

            getLastLineResult().ifPresent(last -> last.setBusStops(busStops));
        }
    }

    @Override
    protected String getSectionCode() {
        return Sections.ZP_BusStopGroups;
    }

    @Override
    protected void onSectionEnd(@NotNull String sectionCode, @NotNull BufferedReader in) {
    }
}
