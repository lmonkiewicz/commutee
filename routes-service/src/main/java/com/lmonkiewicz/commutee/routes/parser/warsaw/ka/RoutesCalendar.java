package com.lmonkiewicz.commutee.routes.parser.warsaw.ka;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.util.Collections;
import java.util.List;

/**
 * Created by lmonkiewicz on 2017-02-20.
 */
public class RoutesCalendar {

    private ListMultimap<String, String> data = ArrayListMultimap.create();

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

    public List<String> getRouteTypesFor(String date){
        if (data.containsKey(date)) {
            return Collections.unmodifiableList(data.get(date));
        }
        else {
            return Collections.emptyList();
        }
    }

    public boolean isType(String date, String type){
        return data.containsEntry(date, type);
    }


}
