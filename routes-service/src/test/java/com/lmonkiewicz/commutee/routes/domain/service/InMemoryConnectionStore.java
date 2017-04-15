package com.lmonkiewicz.commutee.routes.domain.service;

import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import com.lmonkiewicz.commutee.routes.domain.model.ConnectionData;
import com.lmonkiewicz.commutee.routes.domain.out.connection.ConnectionsStore;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by lmonkiewicz on 13.04.2017.
 */
public class InMemoryConnectionStore implements ConnectionsStore {

    @Getter
    private Map<String, BusStopData> data = new HashMap<>();

    @Getter
    private Map<String, List<ConnectionData>> connections = new HashMap<>();

    @Override
    public Optional<BusStopData> findBusStopById(@NotNull String id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void createConnection(@NotNull BusStopData fromBS, @NotNull BusStopData toBS, @NotNull ConnectionData connectionData) {
        final String id = id(fromBS, toBS, connectionData);
        connections.putIfAbsent(id, new ArrayList<>());
        connections.get(id).add(connectionData);
    }

    @Override
    public void markAllConnectionsAsInvalid() {
        connections.values().forEach(connectionData -> connectionData.forEach(con -> con.setValid(false)));
    }

    @Override
    public void markAllBusStopsAsInvalid() {
        data.values().forEach(busStopData -> busStopData.setValid(false));
    }

    @Override
    public void deleteInvalidConnections() {
        connections.values().forEach(cons -> cons.removeIf(connectionData -> !connectionData.isValid()));
        connections.values().removeIf(List::isEmpty);
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
    public Stream<ConnectionData> findBusStopConnection(@NotNull BusStopData fromBS, @NotNull BusStopData toBS, @NotNull ConnectionData connectionData) {
        return get(fromBS.getId(), toBS.getId(), connectionData.getCode());
    }

    public Stream<ConnectionData> get(String fromId, String toId, String code){
        return connections.getOrDefault(id(fromId, toId, code), Collections.emptyList()).stream();
    }

    private String id(@NotNull BusStopData fromBS, @NotNull BusStopData toBS, @NotNull ConnectionData connectionData) {
        return id(fromBS.getId(), toBS.getId(), connectionData.getCode());
    }

    private String id(String fromId, String toId, String code) {
        return String.format("%s_@_%s_@_%s", fromId, toId, code);
    }

}
