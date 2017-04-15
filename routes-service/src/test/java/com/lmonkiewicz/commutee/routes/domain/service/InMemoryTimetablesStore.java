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
        final String id = id(fromBS.getId(), connectionData.getCode());
        departures.putIfAbsent(id, new ArrayList<>());
        departures.get(id).add(connectionData);
    }

    public List<ConnectionData> get(String busStopId, String connectionCode) {
        final String id = id(busStopId, connectionCode);
        return departures.getOrDefault(id, Collections.emptyList());
    }


    private String id(String fromId, String code) {
        return String.format("%s_@_%s", fromId, code);
    }
}
