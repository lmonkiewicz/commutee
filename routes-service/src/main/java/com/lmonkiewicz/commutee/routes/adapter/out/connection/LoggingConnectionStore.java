package com.lmonkiewicz.commutee.routes.adapter.out.connection;

import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import com.lmonkiewicz.commutee.routes.domain.model.ConnectionData;
import com.lmonkiewicz.commutee.routes.domain.out.connection.ConnectionsStore;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by lmonkiewicz on 02.05.2017.
 */
@Slf4j
public class LoggingConnectionStore implements ConnectionsStore {

    private final Map<String, BusStopData> busStops = new HashMap<>();

    @Override
    public Optional<BusStopData> findBusStopById(@NotNull String id) {
        return Optional.ofNullable(busStops.get(id));
    }

    @Override
    public void createConnection(@NotNull BusStopData fromBS, @NotNull BusStopData toBS, @NotNull ConnectionData data) {
        log.info("createConnection {} -> {}: {}", fromBS.getId(), toBS.getId(), data.toString());
    }

    @Override
    public void markAllConnectionsAsInvalid() {
        log.info("markAllConnectionsAsInvalid");
    }

    @Override
    public void markAllBusStopsAsInvalid() {
        log.info("markAllBusStopsAsInvalid");
    }

    @Override
    public void deleteInvalidConnections() {
        log.info("deleteInvalidConnections");
    }

    @Override
    public void deleteInvalidBusStops() {
        log.info("deleteInvalidBusStops");
    }

    @Override
    public void updateBusStopData(@NotNull BusStopData busStop) {
        log.info("updateBusStopData {}", busStop.toString());
    }

    @Override
    public void createBusStopData(@NotNull BusStopData busStop) {
        log.info("createBusStopData {}", busStop.toString());
        busStops.putIfAbsent(busStop.getId(), busStop);
    }

    @Override
    public Stream<ConnectionData> findBusStopConnection(@NotNull BusStopData fromBS, @NotNull BusStopData toBS, @NotNull ConnectionData code) {
        return Stream.empty();
    }
}
