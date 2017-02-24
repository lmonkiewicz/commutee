package com.lmonkiewicz.commutee.routes.parser.warsaw.ty;

import com.lmonkiewicz.commutee.routes.parser.warsaw.AbstractSectionReader;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lmonkiewicz on 2017-02-20.
 */
public class TYSectionReader extends AbstractSectionReader<Map<String,String>, String> {

    private final Map<String,String> result = new HashMap<>();

    @Override
    public Map<String,String> result() {
        return result;
    }

    @Override
    protected String onSectionContentLine(@NotNull String line) {
        String code = line.substring(3,5);
        String description = line.substring(8).trim();
        result.put(code, description);
        return code;
    }
}
