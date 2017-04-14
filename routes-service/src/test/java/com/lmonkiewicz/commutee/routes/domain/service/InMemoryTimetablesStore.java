package com.lmonkiewicz.commutee.routes.domain.service;

import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import com.lmonkiewicz.commutee.routes.domain.model.ConnectionData;
import com.lmonkiewicz.commutee.routes.domain.out.timetable.TimetablesStore;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by lmonkiewicz on 14.04.2017.
 */
public class InMemoryTimetablesStore implements TimetablesStore {

    @Getter
    private Map<String, BusStopData> data = new HashMap<>();

    @Getter
    private Map<String, List<ConnectionData>> departures = new HashMap<>();

    @Override
    public Optional<BusStopData> findBusStopById(@NotNull String id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void updateBusStopData(@NotNull BusStopData busStop) {
        data.put(busStop.getId(), busStop);
    }

    @Override
    public void createBusStopData(@NotNull BusStopData busStop) {
        data.putIfAbsent(busStop.getId(), busStop);
    }

    @Override
    public void createDeparture(@NotNull BusStopData fromBS, @NotNull ConnectionData connectionData) {
        departures.putIfAbsent(fromBS.getId(), new ArrayList<>());
        departures.get(fromBS.getId()).add(connectionData);
    }
}
