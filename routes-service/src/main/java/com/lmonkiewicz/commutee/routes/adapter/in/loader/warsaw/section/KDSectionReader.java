package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.section;

import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.ZtmUtils;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.LinesCalendar;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by lmonkiewicz on 2017-02-22.
 */
public class KDSectionReader extends AbstractSectionReader<LinesCalendar, LocalDate> {

    private final static Logger LOG = LoggerFactory.getLogger(KDSectionReader.class);

    private static final int DATES_LEVEL = 1;
    private static final int LINE_LEVEL = 2;

    private LinesCalendar linesCalendar = new LinesCalendar();

    @Override
    public LinesCalendar result() {
        return linesCalendar;
    }

    @Override
    protected String getSectionCode() {
        return Sections.KD_LinesCalendar;
    }

    @Override
    protected LocalDate onSectionContentLine(@NotNull String line) {
        final int level = ZtmUtils.getIndentationLevel(line, ZtmUtils.DEFAULT_INDENT_SIZE);
        switch(level){
            case DATES_LEVEL: {
                final List<String> values = ZtmUtils.asColumns(1, line, 12, 5);
                return LocalDate.parse(values.get(0), DateTimeFormatter.ofPattern(ZtmUtils.DATE_PATTERN));
            }
            case LINE_LEVEL: {
                final List<String> values = ZtmUtils.asColumns(2, line, 6, 2);
                getLastLineResult().ifPresent(last -> linesCalendar.set(last, values.get(0), values.get(1)));
                break;
            }
            default: {
                LOG.warn("Bad section format on line: '{}'", line);
            }
        }
        return null;
    }
}
