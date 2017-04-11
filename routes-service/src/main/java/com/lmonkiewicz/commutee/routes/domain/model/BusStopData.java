package com.lmonkiewicz.commutee.routes.domain.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
@Builder
@Getter
public class BusStopData {

    private String id;
    private String name;
    private String direction;
    private double posX;
    private double posY;
}
