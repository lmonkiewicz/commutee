package com.lmonkiewicz.commutee.routes.domain.in.loader.exception;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
public class BusStopNotFoundException extends LoaderException {
    public BusStopNotFoundException() {
    }

    public BusStopNotFoundException(String message) {
        super(message);
    }

    public BusStopNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusStopNotFoundException(Throwable cause) {
        super(cause);
    }
}
