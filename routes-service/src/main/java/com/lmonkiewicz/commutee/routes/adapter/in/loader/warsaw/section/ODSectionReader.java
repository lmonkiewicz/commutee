package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.section;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.ZtmUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by lmonkiewicz on 12.03.2017.
 */
public class ODSectionReader extends AbstractSectionReader<ListMultimap<String, Double>, String> {

    private ListMultimap<String, Double> result = ArrayListMultimap.create();

    @Override
    public ListMultimap<String, Double> result() {
        return result;
    }

    @Nullable
    @Override
    protected String onSectionContentLine(@NotNull String line) {
        final List<String> values = ZtmUtils.asColumns(9, line, 7, 100);

        final Double time = Double.valueOf(values.get(0));
        result.put(values.get(1).replaceAll("[_]",""), time);

        return null;
    }

    @Override
    protected String getSectionCode() {
        return Sections.OD_Departures;
    }
}
