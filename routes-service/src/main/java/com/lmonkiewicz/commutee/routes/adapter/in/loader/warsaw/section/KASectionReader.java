package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.section;

import com.google.common.base.Strings;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.ZtmUtils;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.RoutesCalendar;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by lmonkiewicz on 2017-02-20.
 */
public class KASectionReader extends AbstractSectionReader<RoutesCalendar, LocalDate> {

    private final RoutesCalendar routesCalendar = new RoutesCalendar();

    @Override
    public RoutesCalendar result() {
        return routesCalendar;
    }

    @Override
    protected String getSectionCode() {
        return Sections.KA_RoutesCalendar;
    }

    @Override
    protected LocalDate onSectionContentLine(@NotNull String line) {
        final List<String> columns = ZtmUtils.asColumns(1, line, 14, 5, 4, 4, 4, 4, 4, 4, 4);

        final LocalDate date = LocalDate.parse(columns.get(0), DateTimeFormatter.ofPattern(ZtmUtils.DATE_PATTERN));

        final Integer count = Integer.valueOf(columns.get(1));
        for (int i = 0; i < count; i++) {
            String type = columns.get(i + 2);
            if (!Strings.isNullOrEmpty(type)) {
                routesCalendar.put(date, type);
            }
        }
        return date;
    }
}
