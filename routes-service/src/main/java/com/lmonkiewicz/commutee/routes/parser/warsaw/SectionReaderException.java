package com.lmonkiewicz.commutee.routes.parser.warsaw;

/**
 * Created by lmonkiewicz on 2017-02-19.
 */
public class SectionReaderException extends Exception {
    public SectionReaderException() {
    }

    public SectionReaderException(String message) {
        super(message);
    }

    public SectionReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public SectionReaderException(Throwable cause) {
        super(cause);
    }
}
