package com.lmonkiewicz.commutee.routes.parser.warsaw.model;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Created by lmonkiewicz on 2017-02-20.
 */
public class RoutesCalendar {

    private ListMultimap<LocalDate, String> data = ArrayListMultimap.create();

    public void put(LocalDate date, String value){
        data.put(date, value);
    }

    public void put(LocalDate date, Iterable<String> values) {
        data.putAll(date, values);
    }

    public void set(LocalDate date, Iterable<String> values){
        data.removeAll(date);
        put(date, values);
    }

    public List<String> getRouteTypesFor(LocalDate date){
        if (data.containsKey(date)) {
            return Collections.unmodifiableList(data.get(date));
        }
        else {
            return Collections.emptyList();
        }
    }

    public boolean isType(LocalDate date, String type){
        return data.containsEntry(date, type);
    }


}
