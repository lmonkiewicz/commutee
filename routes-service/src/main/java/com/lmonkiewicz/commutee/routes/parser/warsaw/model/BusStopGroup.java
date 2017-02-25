package com.lmonkiewicz.commutee.routes.parser.warsaw.model;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by lmonkiewicz on 2017-02-23.
 */
@Data
public class BusStopGroup {
    private String id;
    private String name;
    private String townCode;
    private String townName;

    private Map<String, BusStop> busStops = new HashMap<>();

    public BusStopGroup() {
    }

    @Builder
    public BusStopGroup(String id, String name, String townCode, String townName, @Singular List<BusStop> busStops) {
        this.id = id;
        this.name = name;
        this.townCode = townCode;
        this.townName = townName;
        busStops.forEach(stop -> this.busStops.put(stop.getId(), stop));
    }

    public Stream<BusStop> stream() {
        return busStops.values().stream();
    }
}
