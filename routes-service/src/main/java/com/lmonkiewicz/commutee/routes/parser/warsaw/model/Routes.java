package com.lmonkiewicz.commutee.routes.parser.warsaw.model;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by lmonkiewicz on 07.03.2017.
 */
public class Routes {
    private List<Route> routes = Lists.newArrayList();

    public void add(Route route){
        routes.add(route);
    }

    public Stream<Route> stream(){
        return routes.stream();
    }
}
