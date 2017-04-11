package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lmonkiewicz on 07.03.2017.
 */
@Builder
@Getter
@EqualsAndHashCode
public class RoutePoint {
    private String id;
    private String name;
    private String townCode;
    private double y;
    private double x;

    @Setter
    private RoutePointTimetables timetables;
    @Setter
    private Metadata metadata;

}
