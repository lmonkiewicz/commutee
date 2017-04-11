package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.section;

import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.ZtmUtils;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lmonkiewicz on 12.03.2017.
 */
public class OPSectionReader extends AbstractSectionReader<Metadata, String>{

    private final static Logger LOG = LoggerFactory.getLogger(OPSectionReader.class);

    public static final Pattern PATTERN = Pattern.compile("20[0-9][0-9]-[0-1][0-9]-[0-3][0-9]");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private Metadata metadata = new Metadata();

    @Override
    public Metadata result() {
        return metadata;
    }

    @Nullable
    @Override
    protected String onSectionContentLine(@NotNull String line) {
        final List<String> values = ZtmUtils.asColumns(7, line, 4, 300);
        switch(values.get(0)){
            case "D": {
                final String value = values.get(1);
                metadata.getHeader().add(value);
                final Matcher matcher = PATTERN.matcher(value);
                if (matcher.find()){
                    try {
                        final Date date = DATE_FORMAT.parse(matcher.group());
                        final LocalDate validFrom = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        metadata.setValidFrom(validFrom);
                    } catch (ParseException e) {
                        LOG.error("Error while parsing date '" + value + "'", e);
                    }
                }

                break;
            }
            case "K":{
                metadata.getComments().add(values.get(1));
                break;
            }
            case "S":{
                metadata.getSymbols().add(values.get(1));
                break;
            }
        }
        return null;
    }

    @Override
    protected String getSectionCode() {
        return Sections.OP_Metadata;
    }
}
