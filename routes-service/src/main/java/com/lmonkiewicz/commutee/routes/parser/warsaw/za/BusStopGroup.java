package com.lmonkiewicz.commutee.routes.parser.warsaw.za;

import lombok.Builder;
import lombok.Data;

/**
 * Created by lmonkiewicz on 2017-02-23.
 */
@Data
public class BusStopGroup {
    private String id;
    private String name;
    private String townCode;
    private String townName;


    public BusStopGroup() {
    }

    @Builder
    public BusStopGroup(String id, String name, String townCode, String townName) {
        this.id = id;
        this.name = name;
        this.townCode = townCode;
        this.townName = townName;
    }
}
