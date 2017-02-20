package com.lmonkiewicz.commutee.routes.parser.warsaw.ka;

import com.google.common.base.Strings;
import com.lmonkiewicz.commutee.routes.parser.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.parser.warsaw.ZtmUtils;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.util.List;

/**
 * Created by lmonkiewicz on 2017-02-20.
 */
public class KASectionReader extends AbstractSectionReader<RoutesCalendar> {

    private final RoutesCalendar routesCalendar = new RoutesCalendar();

    @Override
    public RoutesCalendar result() {
        return routesCalendar;
    }

    @Override
    protected void onSectionEnd(String sectionCode, BufferedReader in) {
    }

    @Override
    protected void onSectionStart(String sectionCode, BufferedReader in) {
    }

    @Override
    protected void onSectionContentLine(@NotNull String line) {
        final List<String> columns = ZtmUtils.asColumns(line, 14, 5, 4, 4, 4, 4, 4, 4, 4);

        final String date = columns.get(0);

        final Integer count = Integer.valueOf(columns.get(1));
        for (int i = 0; i < count; i++) {
            String type = columns.get(i + 2);
            if (!Strings.isNullOrEmpty(type)) {
                routesCalendar.put(date, type);
            }
        }

    }
}
