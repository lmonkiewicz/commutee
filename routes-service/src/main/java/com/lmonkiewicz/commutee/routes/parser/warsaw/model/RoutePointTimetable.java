package com.lmonkiewicz.commutee.routes.parser.warsaw.model;

import com.google.common.collect.ListMultimap;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lmonkiewicz on 12.03.2017.
 */
@Builder
@Getter
@EqualsAndHashCode
public class RoutePointTimetable {
    private String dayType;
    private String description;

    @Setter
    private DepartTimes departTimes;
    @Setter
    private ListMultimap<String, Double> courses;

}
