package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by lmonkiewicz on 07.03.2017.
 */
@Builder
@Getter
@EqualsAndHashCode
public class RouteStop {

    private String streetName;
    private String type;
    private String busStopId;
    private String stopName;
    private String townCode;
    private String busStopGroupCode;
    private boolean onDemand;
    private Integer minArrivalTime;
    private Integer maxArrivalTime;
}