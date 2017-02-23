package com.lmonkiewicz.commutee.routes.parser.warsaw;

import java.io.BufferedReader;

/**
 * Created by lmonkiewicz on 2017-02-19.
 */
public interface SectionReader<T> {
    /**
     * Parses stream of data
     *
     * @param in input stream of data
     * @throws SectionReaderException
     */
    void readSection(BufferedReader in) throws SectionReaderException;

    /**
     * Returns a result of parsing
     * @return
     */
    T result();
}
