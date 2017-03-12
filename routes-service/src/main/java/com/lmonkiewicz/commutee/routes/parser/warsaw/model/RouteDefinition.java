package com.lmonkiewicz.commutee.routes.parser.warsaw.model;

import com.google.common.collect.Lists;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by lmonkiewicz on 07.03.2017.
 */
@Getter
@EqualsAndHashCode
public class RouteDefinition {

    private List<RouteStop> stops = Lists.newArrayList();

    public Stream<RouteStop> stream() {
        return stops.stream();
    }

    public void add(RouteStop routeStop){
        stops.add(routeStop);
    }

}
