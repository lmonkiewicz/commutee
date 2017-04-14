package com.lmonkiewicz.commutee.routes.domain.service;

import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import com.lmonkiewicz.commutee.routes.domain.model.ConnectionData;
import com.lmonkiewicz.commutee.routes.domain.out.connection.ConnectionsStore;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by lmonkiewicz on 13.04.2017.
 */
public class InMemoryConnectionStore implements ConnectionsStore {

    @Getter
    private Map<String, BusStopData> data = new HashMap<>();

    @Getter
    private Map<String, Map<String, ConnectionData>> connections = new HashMap<>();

    @Override
    public Optional<BusStopData> findBusStopById(@NotNull String id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void createConnection(@NotNull BusStopData fromBS, @NotNull BusStopData toBS, @NotNull ConnectionData data) {
        connections.putIfAbsent(fromBS.getId(), new HashMap<>());
        connections.get(fromBS.getId()).put(toBS.getId(), data);
    }

    @Override
    public void markAllConnectionsAsInvalid() {
        connections.values()
                .forEach(con2 -> con2.values()
                    .forEach(connectionData -> connectionData.setValid(false)));
    }

    @Override
    public void markAllBusStopsAsInvalid() {
        data.values().forEach(busStopData -> busStopData.setValid(false));
    }

    @Override
    public void deleteInvalidConnections() {
        connections.values()
                .forEach(innerMap -> innerMap.values()
                        .removeIf(connectionData -> !connectionData.isValid()));
    }

    @Override
    public void deleteInvalidBusStops() {
        data.values().removeIf(busStopData -> !busStopData.isValid());
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
    public Optional<ConnectionData> findBusStopConnection(@NotNull BusStopData fromBS, @NotNull BusStopData toBS, @NotNull ConnectionData code) {
        final Map<String, ConnectionData> to = connections.get(fromBS.getId());
        return Optional.ofNullable(to != null ? to.get(toBS.getId()) : null);
    }

    @Override
    public void updateConnection(@NotNull BusStopData fromBS, @NotNull BusStopData toBS, @NotNull ConnectionData connectionData) {
        connections.putIfAbsent(fromBS.getId(), new HashMap<>());
        connections.get(fromBS.getId()).put(toBS.getId(), connectionData);
    }
}
