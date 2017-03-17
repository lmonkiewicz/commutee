package com.lmonkiewicz.commutee.routes.parser.warsaw.section;

import com.lmonkiewicz.commutee.routes.parser.warsaw.AbstractSectionReader;
import com.lmonkiewicz.commutee.routes.parser.warsaw.ZtmUtils;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.Course;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.Courses;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by lmonkiewicz on 17.03.2017.
 */
public class WKSectionReader extends AbstractSectionReader<Courses, Course>{

    private Courses courses = new Courses();


    @Nullable
    @Override
    protected Course onSectionContentLine(@NotNull String line) {
        final List<String> values = ZtmUtils.asColumns(3, line, 19, 7, 3, 2, 1, 2, 10);
        final Course course = Course.builder()
                .courseId(values.get(0))
                .stopId(values.get(1))
                .dayType(values.get(2))
                .departTime(LocalTime.of(Integer.valueOf(values.get(3)), Integer.valueOf(values.get(5))))
                .bonusStop("B".equals(values.get(6)))
                .endStop("P".equals(values.get(6)))
                .build();

        courses.add(course);

        return course;
    }

    @Override
    public Courses result() {
        return courses;
    }

    @Override
    protected String getSectionCode() {
        return "WK";
    }
}
