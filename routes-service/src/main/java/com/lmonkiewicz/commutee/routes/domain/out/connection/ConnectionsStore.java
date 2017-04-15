package com.lmonkiewicz.commutee.routes.domain.out.connection;

import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import com.lmonkiewicz.commutee.routes.domain.model.ConnectionData;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
public interface ConnectionsStore {
    Optional<BusStopData> findBusStopById(@NotNull String id);

    void createConnection(@NotNull BusStopData fromBS, @NotNull BusStopData toBS, @NotNull ConnectionData data);

    void markAllConnectionsAsInvalid();

    void markAllBusStopsAsInvalid();

    void deleteInvalidConnections();

    void deleteInvalidBusStops();

    void updateBusStopData(@NotNull BusStopData busStop);

    void createBusStopData(@NotNull BusStopData busStop);

    Stream<ConnectionData> findBusStopConnection(
            @NotNull BusStopData fromBS, @NotNull BusStopData toBS, @NotNull ConnectionData code);
}
