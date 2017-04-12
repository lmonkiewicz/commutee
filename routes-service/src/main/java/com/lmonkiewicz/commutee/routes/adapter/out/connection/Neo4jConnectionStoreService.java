package com.lmonkiewicz.commutee.routes.adapter.out.connection;

import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import com.lmonkiewicz.commutee.routes.domain.model.ConnectionData;
import com.lmonkiewicz.commutee.routes.domain.out.connection.ConnectionsStoreService;
import org.jetbrains.annotations.NotNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Optional;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
public class Neo4jConnectionStoreService implements ConnectionsStoreService {
    @Override
    public Optional<BusStopData> findBusStopById(@NotNull String id) {
        return Optional.empty();
    }

    @Override
    public void createConnection(@NotNull BusStopData fromBS, @NotNull BusStopData toBS, @NotNull ConnectionData data) {
        throw new NotImplementedException();
    }

    @Override
    public void markAllConnectionsAsInvalid() {
        throw new NotImplementedException();
    }

    @Override
    public void markAllBusStopsAsInvalid() {
        throw new NotImplementedException();
    }

    @Override
    public void deleteInvalidConnections() {
        throw new NotImplementedException();
    }

    @Override
    public void deleteInvalidBusStops() {
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
}