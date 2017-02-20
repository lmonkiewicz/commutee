package com.lmonkiewicz.commutee.routes.parser.warsaw.ka;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import java.util.Collections;
import java.util.Set;

/**
 * Created by lmonkiewicz on 2017-02-20.
 */
public class RoutesCalendar {

    private SetMultimap<String, String> data = HashMultimap.create();

    public void put(String date, String value){
        data.put(date, value);
    }

    public void put(String date, Iterable<String> values) {
        data.putAll(date, values);
    }

    public void set(String date, Iterable<String> values){
        data.removeAll(date);
        put(date, values);
    }

    public Set<String> getRouteTypesFor(String date){
        if (data.containsKey(date)) {
            return Collections.unmodifiableSet(data.get(date));
        }
        else {
            return Collections.emptySet();
        }
    }


}
