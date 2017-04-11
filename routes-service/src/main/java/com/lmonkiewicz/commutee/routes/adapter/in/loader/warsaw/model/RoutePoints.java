package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model;

import com.google.common.collect.Maps;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by lmonkiewicz on 07.03.2017.
 */
@Getter
@Setter
@EqualsAndHashCode
public class RoutePoints {

    private Map<String, RoutePoint> routePoints = Maps.newHashMap();

    public Stream<RoutePoint> stream() {
        return routePoints.values().stream();
    }

    public void add(@NotNull RoutePoint routePoint) {
        routePoints.put(routePoint.getId(), routePoint);
    }

    public Optional<RoutePoint> get(@NotNull String id) {
        return Optional.ofNullable(routePoints.get(id));
    }
}
