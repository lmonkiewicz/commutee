package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model;

import com.google.common.collect.Maps;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by lmonkiewicz on 12.03.2017.
 */
@EqualsAndHashCode
public class RoutePointTimetables {
    private Map<String, RoutePointTimetable> types = Maps.newHashMap();

    public Stream<RoutePointTimetable> stream() {
        return types.values().stream();
    }

    public Optional<RoutePointTimetable> get(@NotNull String dayType) {
        return Optional.ofNullable(types.get(dayType));
    }

    public void add(@NotNull RoutePointTimetable timetable) {
        types.put(timetable.getDayType(), timetable);
    }

}
