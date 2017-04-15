package com.lmonkiewicz.commutee.routes.domain.service;

import com.lmonkiewicz.commutee.routes.domain.in.loader.TimetableDataLoader;
import com.lmonkiewicz.commutee.routes.domain.in.loader.exception.BusStopNotFoundException;
import com.lmonkiewicz.commutee.routes.domain.in.loader.exception.LoaderException;
import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import com.lmonkiewicz.commutee.routes.domain.model.ConnectionData;
import com.lmonkiewicz.commutee.routes.domain.out.connection.ConnectionsStore;
import com.lmonkiewicz.commutee.routes.domain.out.timetable.TimetablesStore;
import org.jetbrains.annotations.NotNull;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
public class TimetablesImportService implements TimetableDataLoader {


    private final ConnectionsStore connectionsStore;
    private final TimetablesStore timetablesStore;

    public TimetablesImportService(
            ConnectionsStore connectionsStore,
            TimetablesStore timetablesStore) {

        this.connectionsStore = connectionsStore;
        this.timetablesStore = timetablesStore;
    }

    @Override
    public void updateBusStop(@NotNull BusStopData busStop) throws LoaderException {
        updateConnectionBusStop(busStop);
        updateTimetableBusStop(busStop);
    }

    private void updateTimetableBusStop(@NotNull BusStopData busStop) {
        if (timetablesStore.findBusStopById(busStop.getId()).isPresent()) {
            timetablesStore.updateBusStopData(busStop);
        }
        else {
            timetablesStore.createBusStopData(busStop);
        }
    }

    private void updateConnectionBusStop(@NotNull BusStopData busStop) {
        if (connectionsStore.findBusStopById(busStop.getId()).isPresent()){
            connectionsStore.updateBusStopData(busStop);
        }
        else {
            connectionsStore.createBusStopData(busStop);
        }
    }

    @Override
    public void createConnection(@NotNull String from, @NotNull String to, @NotNull ConnectionData connectionData)
            throws LoaderException {

        final BusStopData fromBS = connectionsStore.findBusStopById(from)
                .orElseThrow(() -> new BusStopNotFoundException(from));

        final BusStopData toBS = connectionsStore.findBusStopById(to)
                .orElseThrow(() -> new BusStopNotFoundException(to));

        connectionsStore.createConnection(fromBS, toBS, connectionData);
        timetablesStore.createDeparture(fromBS, connectionData);

    }

    @Override
    public void start() throws LoaderException {
        connectionsStore.markAllConnectionsAsInvalid();
        connectionsStore.markAllBusStopsAsInvalid();
    }

    @Override
    public void finish() throws LoaderException {
        connectionsStore.deleteInvalidConnections();
        connectionsStore.deleteInvalidBusStops();

    }
}
