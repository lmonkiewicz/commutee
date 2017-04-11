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
@Setter
@EqualsAndHashCode
public class Route {
    private String routeId;
    private String beginingStop;
    private String beginingTown;
    private String endingStop;
    private String endingTown;
    private String direction;
    private String position;

    private RouteDefinition routeDefinition;
    private RoutePoints routePoints;

}
