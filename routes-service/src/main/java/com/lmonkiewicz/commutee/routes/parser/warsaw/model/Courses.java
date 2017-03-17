package com.lmonkiewicz.commutee.routes.parser.warsaw.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

/**
 * Created by lmonkiewicz on 17.03.2017.
 */
@Data
public class Courses {
    private ListMultimap<String, Course> courses = ArrayListMultimap.create();

    public Stream<Course> streamAll() {
        return courses.values().stream();
    }

    public Stream<Course> streamById(@NotNull String id) {
        if (courses.containsKey(id)){
            return courses.get(id).stream();
        }
        else {
            return Stream.empty();
        }
    }

    public void add(@NotNull Course course) {
        courses.put(course.getCourseId(), course);
    }
}
