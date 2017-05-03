package com.lmonkiewicz.commutee.routes.adapter.out.timetable;

import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import com.lmonkiewicz.commutee.routes.domain.model.ConnectionData;
import com.lmonkiewicz.commutee.routes.domain.out.timetable.TimetablesStore;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Created by lmonkiewicz on 02.05.2017.
 */
@Slf4j
public class LoggingTimetablesStore implements TimetablesStore {
    @Override
    public Optional<BusStopData> findBusStopById(@NotNull String id) {
        return Optional.empty();
    }

    @Override
    public void updateBusStopData(@NotNull BusStopData busStop) {
        log.info("updateBusStopData {}", busStop.toString());
    }

    @Override
    public void createBusStopData(@NotNull BusStopData busStop) {
        log.info("createBusStopData {}", busStop.toString());
    }

    @Override
    public void createDeparture(@NotNull BusStopData fromBS, @NotNull ConnectionData connectionData) {
        log.info("createDeparture {} @ {}", fromBS.getId(), connectionData.toString());
    }
}
