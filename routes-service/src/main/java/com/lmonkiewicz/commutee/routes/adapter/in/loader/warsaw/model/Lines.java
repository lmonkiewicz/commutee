package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model;

import com.google.common.collect.Maps;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by lmonkiewicz on 07.03.2017.
 */
@EqualsAndHashCode
public class Lines {
    private Map<String, Line> lines = Maps.newHashMap();


    public void add(@NotNull Line line){
        lines.put(line.getNumber(), line);
    }

    public Optional<Line> get(@NotNull String number){
        return Optional.ofNullable(lines.get(number));
    }

    public Stream<Line> stream(){
        return lines.values().stream();
    }
}
