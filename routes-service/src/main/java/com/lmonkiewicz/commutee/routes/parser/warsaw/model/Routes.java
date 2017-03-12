package com.lmonkiewicz.commutee.routes.parser.warsaw.model;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by lmonkiewicz on 07.03.2017.
 */
public class Routes {
    private Map<String, Route> routes = Maps.newHashMap();

    public void add(Route route){
        routes.put(route.getRouteId(), route);
    }

    public Optional<Route> get(@NotNull String routeId){
        return Optional.ofNullable(routes.get(routeId));
    }

    public Stream<Route> stream(){
        return routes.values().stream();
    }
}
