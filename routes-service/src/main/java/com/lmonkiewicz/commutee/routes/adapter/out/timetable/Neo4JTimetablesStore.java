package com.lmonkiewicz.commutee.routes.adapter.out.timetable;

import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import com.lmonkiewicz.commutee.routes.domain.model.ConnectionData;
import com.lmonkiewicz.commutee.routes.domain.out.timetable.TimetablesStore;
import org.jetbrains.annotations.NotNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Optional;

/**
 * Created by lmonkiewicz on 14.04.2017.
 */
public class Neo4JTimetablesStore implements TimetablesStore {
    @Override
    public Optional<BusStopData> findBusStopById(@NotNull String id) {
        throw new NotImplementedException();
    }

    @Override
    public void updateBusStopData(@NotNull BusStopData busStop) {
        throw new NotImplementedException();
    }

    @Override
    public void createBusStopData(@NotNull BusStopData busStop) {
        throw new NotImplementedException();
    }

    @Override
    public void createDeparture(@NotNull BusStopData fromBS, @NotNull ConnectionData connectionData) {
        throw new NotImplementedException();
    }
}
