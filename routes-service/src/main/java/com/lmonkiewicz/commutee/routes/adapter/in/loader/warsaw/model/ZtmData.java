package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by lmonkiewicz on 17.03.2017.
 */
@Data
public class ZtmData {
    private Map<String, String> dayTypes;
    private RoutesCalendar routesCalendar;
    private LinesCalendar linesCalendar;
    private BusStopGroups busStopGroupsList;
    private BusStopGroups busStopGroups;
    private Map<String, String> townCodes;
    private Lines lines;

    public void setTownCodes(Map<String, String> townCodes) {
        this.townCodes = townCodes;
    }

    public void setLines(Lines lines) {
        this.lines = lines;
    }
}
