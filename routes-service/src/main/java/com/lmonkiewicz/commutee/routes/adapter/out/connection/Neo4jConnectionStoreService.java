package com.lmonkiewicz.commutee.routes.adapter.out.connection;

import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import com.lmonkiewicz.commutee.routes.domain.out.connection.ConnectionsStoreService;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
public class Neo4jConnectionStoreService implements ConnectionsStoreService {
    @Override
    public Optional<BusStopData> findBusStopById(@NotNull String id) {
        return Optional.empty();
    }
}
