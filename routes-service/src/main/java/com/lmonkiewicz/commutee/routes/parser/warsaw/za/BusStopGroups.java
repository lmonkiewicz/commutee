package com.lmonkiewicz.commutee.routes.parser.warsaw.za;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lmonkiewicz on 2017-02-23.
 */
public class BusStopGroups {

    private Map<String, BusStopGroup> data = new HashMap<>();

    public void add(@NotNull BusStopGroup group){
        data.put(group.getId(), group);
    }

    public BusStopGroup get(@NotNull String id){
        return data.get(id);
    }
}
