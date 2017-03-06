package com.lmonkiewicz.commutee.routes.parser.warsaw.section;

import com.lmonkiewicz.commutee.routes.parser.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.parser.warsaw.ZtmUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lmonkiewicz on 06.03.2017.
 */
public class SMSectionReader extends AbstractSectionReader<Map<String, String>, String> {

    private Map<String, String> data = new HashMap<>();

    @Override
    public Map<String, String> result() {
        return data;
    }

    @Nullable
    @Override
    protected String onSectionContentLine(@NotNull String line) {
        final List<String> values = ZtmUtils.asColumns(1, line, 5, 100);
        final String town = values.get(1);
        data.put(values.get(0), town);
        return town;
    }

    @Override
    protected String getSectionCode() {
        return "SM";
    }
}
