package com.lmonkiewicz.commutee.routes.domain.out.timetable;

import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import com.lmonkiewicz.commutee.routes.domain.model.ConnectionData;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
public interface TimetablesStore {
    Optional<BusStopData> findBusStopById(@NotNull String id);

    void updateBusStopData(@NotNull BusStopData busStop);

    void createBusStopData(@NotNull BusStopData busStop);

    void createDeparture(@NotNull BusStopData fromBS, @NotNull ConnectionData connectionData);
}
