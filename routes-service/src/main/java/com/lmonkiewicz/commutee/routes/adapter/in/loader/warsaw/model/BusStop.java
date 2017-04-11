package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by lmonkiewicz on 2017-02-25.
 */

@Getter
@EqualsAndHashCode
public class BusStop {
    private String id;
    private String name;
    private String direction;
    private double x;
    private double y;

    @Getter(AccessLevel.NONE)
    private ListMultimap<BusStopLineType, String> lines = ArrayListMultimap.create();

    @Builder
    public BusStop(String id, String name, String direction, double x, double y) {
        this.id = id;
        this.name = name;
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    public Collection<String> getLines(@NotNull BusStopLineType type) {
        return Collections.unmodifiableCollection(lines.get(type));
    }


    public void addLine(@NotNull BusStopLineType type, @NotNull String line) {
        lines.put(type, line);
    }
}
