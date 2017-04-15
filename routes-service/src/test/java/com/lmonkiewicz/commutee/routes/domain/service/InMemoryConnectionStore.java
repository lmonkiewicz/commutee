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
    private Map<String, ConnectionData> connections = new HashMap<>();

    @Override
    public Optional<BusStopData> findBusStopById(@NotNull String id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void createConnection(@NotNull BusStopData fromBS, @NotNull BusStopData toBS, @NotNull ConnectionData connectionData) {
        connections.putIfAbsent(id(fromBS, toBS, connectionData), connectionData);
    }

    @Override
    public void markAllConnectionsAsInvalid() {
        connections.values().forEach(connectionData -> connectionData.setValid(false));
    }

    @Override
    public void markAllBusStopsAsInvalid() {
        data.values().forEach(busStopData -> busStopData.setValid(false));
    }

    @Override
    public void deleteInvalidConnections() {
        connections.values().removeIf(connectionData -> !connectionData.isValid());
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
    public Optional<ConnectionData> findBusStopConnection(@NotNull BusStopData fromBS, @NotNull BusStopData toBS, @NotNull ConnectionData connectionData) {
        return get(fromBS.getId(), toBS.getId(), connectionData.getCode());
    }

    @Override
    public void updateConnection(@NotNull BusStopData fromBS, @NotNull BusStopData toBS, @NotNull ConnectionData connectionData) {
        connections.put(id(fromBS, toBS, connectionData), connectionData);
    }

    public Optional<ConnectionData> get(String fromId, String toId, String code){
        return Optional.ofNullable(connections.get(id(fromId, toId, code)));
    }

    private String id(@NotNull BusStopData fromBS, @NotNull BusStopData toBS, @NotNull ConnectionData connectionData) {
        return id(fromBS.getId(), toBS.getId(), connectionData.getCode());
    }

    private String id(String fromId, String toId, String code) {
        return String.format("%s_@_%s_@_%s", fromId, toId, code);
    }

}
