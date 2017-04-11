package com.lmonkiewicz.commutee.routes.domain.in.loader.exception;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
public class LoaderException extends Exception {
    public LoaderException() {
    }

    public LoaderException(String message) {
        super(message);
    }

    public LoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoaderException(Throwable cause) {
        super(cause);
    }
}
