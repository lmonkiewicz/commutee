package com.lmonkiewicz.commutee.routes.domain.in.loader;

import com.lmonkiewicz.commutee.routes.domain.in.loader.exception.LoaderException;
import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import com.lmonkiewicz.commutee.routes.domain.model.ConnectionData;
import org.jetbrains.annotations.NotNull;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
public interface TimetableDataLoader {

    void updateBusStop(@NotNull BusStopData busStop)
            throws LoaderException;

    void updateConnection(@NotNull String from, @NotNull String to, @NotNull ConnectionData data)
            throws LoaderException;

    void start() throws LoaderException;

    void finish() throws LoaderException;
}
