package com.lmonkiewicz.commutee.routes.parser.warsaw.section;

import com.google.common.base.Splitter;
import com.lmonkiewicz.commutee.routes.parser.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.parser.warsaw.ZtmUtils;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.DepartTimes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lmonkiewicz on 12.03.2017.
 */
public class WGSectionReader extends AbstractSectionReader<DepartTimes, List<Integer>> {

    private DepartTimes departTimes = new DepartTimes();

    @Override
    public DepartTimes result() {
        return departTimes;
    }


    @Nullable
    @Override
    protected List<Integer> onSectionContentLine(@NotNull String line) {
        final List<String> values = ZtmUtils.asColumns(9, line, 3, 3, 2, 3, 500);


        final List<Integer> times = Splitter.fixedLength(6)
                .trimResults()
                .omitEmptyStrings()
                .splitToList(values.get(4))
                .stream()
                .map(val -> val.replaceAll("[^0-9]", ""))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        departTimes.set(Integer.valueOf(values.get(2)), times);

        return null;
    }

    @Override
    protected String getSectionCode() {
        return Sections.WG_DepartTimes;
    }
}
