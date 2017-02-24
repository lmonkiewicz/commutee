package com.lmonkiewicz.commutee.routes.parser.warsaw.kd;

import com.lmonkiewicz.commutee.routes.parser.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.parser.warsaw.ZtmUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by lmonkiewicz on 2017-02-22.
 */
public class KDSectionReader extends AbstractSectionReader<LinesCalendar> {

    private final static Logger LOG = LoggerFactory.getLogger(KDSectionReader.class);

    private static final int DATES_LEVEL = 1;
    private static final int LINE_LEVEL = 2;

    private LinesCalendar linesCalendar = new LinesCalendar();

    private LocalDate currentDate = null;

    @Override
    public LinesCalendar result() {
        return linesCalendar;
    }

    @Override
    protected void onSectionContentLine(@NotNull String line) {
        final int level = ZtmUtils.getIndentationLevel(line, ZtmUtils.DEFAULT_INDENT_SIZE);
        switch(level){
            case DATES_LEVEL: {
                final List<String> values = ZtmUtils.asColumns(1, line, 12, 5);
                currentDate = LocalDate.parse(values.get(0), DateTimeFormatter.ofPattern(ZtmUtils.DATE_PATTERN));
                break;
            }
            case LINE_LEVEL: {
                final List<String> values = ZtmUtils.asColumns(2, line, 6, 2);
                linesCalendar.set(currentDate, values.get(0), values.get(1));
                break;
            }
            default: {
                LOG.warn("Bad section format on line: '{}'", line);
            }
        }
    }
}
