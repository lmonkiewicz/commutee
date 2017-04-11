package com.lmonkiewicz.commutee.routes.domain.service;

import com.lmonkiewicz.commutee.routes.domain.in.loader.TimetableDataLoader;
import com.lmonkiewicz.commutee.routes.domain.in.loader.exception.BusStopNotFoundException;
import com.lmonkiewicz.commutee.routes.domain.in.loader.exception.LoaderException;
import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import com.lmonkiewicz.commutee.routes.domain.model.ConnectionData;
import com.lmonkiewicz.commutee.routes.domain.out.connection.ConnectionsStoreService;
import org.jetbrains.annotations.NotNull;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
public class TimetablesImportService implements TimetableDataLoader {


    private final ConnectionsStoreService connectionsStoreService;

    public TimetablesImportService(ConnectionsStoreService connectionsStoreService) {
        this.connectionsStoreService = connectionsStoreService;
    }

    @Override
    public void updateBusStop(@NotNull BusStopData busStop) throws LoaderException {
        if (connectionsStoreService.findBusStopById(busStop.getId()).isPresent()){
            connectionsStoreService.updateBusStopData(busStop);
        }
        else {
            connectionsStoreService.createBusStopData(busStop);
        }
    }

    @Override
    public void updateConnection(@NotNull String from, @NotNull String to, @NotNull ConnectionData connectionData)
            throws LoaderException {

        final BusStopData fromBS = connectionsStoreService.findBusStopById(from)
                .orElseThrow(() -> new BusStopNotFoundException(from));

        final BusStopData toBS = connectionsStoreService.findBusStopById(to)
                .orElseThrow(() -> new BusStopNotFoundException(to));

        connectionsStoreService.createConnection(fromBS, toBS, connectionData);

    }

    @Override
    public void start() throws LoaderException {
        connectionsStoreService.markAllConnectionsAsInvalid();
        connectionsStoreService.markAllBusStopsAsInvalid();
    }

    @Override
    public void finish() throws LoaderException {
        connectionsStoreService.deleteInvalidConnections();
        connectionsStoreService.deleteInvalidBusStops();

    }
}
