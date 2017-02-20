package com.lmonkiewicz.commutee.routes.parser.warsaw;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by lmonkiewicz on 2017-02-20.
 */
public class BaseSectionParserTest {
    protected BufferedReader createReader(String fileName){
        return new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName)));
    }
}
