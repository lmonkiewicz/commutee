package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by lmonkiewicz on 2017-02-20.
 */
public class BaseSectionReaderTest {
    protected BufferedReader createReader(String fileName) throws UnsupportedEncodingException {
        return new BufferedReader(
                new InputStreamReader(this.getClass().getResourceAsStream(fileName), "UTF-8"));
    }
}
