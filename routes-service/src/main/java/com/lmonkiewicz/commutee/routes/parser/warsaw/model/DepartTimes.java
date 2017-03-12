package com.lmonkiewicz.commutee.routes.parser.warsaw.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by lmonkiewicz on 12.03.2017.
 */
@Data
public class DepartTimes {

    private ListMultimap<Integer, Integer> times = ArrayListMultimap.create();

    public void set(@NotNull Integer hour, @NotNull List<Integer> times) {
          this.times.putAll(hour, times);
    }

    public Stream<Integer> get(@NotNull Integer hour){
        if (times.containsKey(hour)) {
            return times.get(hour).stream();
        }
        else {
            return Stream.empty();
        }
    }
}
