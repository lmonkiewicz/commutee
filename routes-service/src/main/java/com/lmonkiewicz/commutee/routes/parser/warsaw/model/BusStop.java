package com.lmonkiewicz.commutee.routes.parser.warsaw.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by lmonkiewicz on 2017-02-25.
 */
@Builder
@Getter
@EqualsAndHashCode
public class BusStop {
    private String id;
    private String name;
    private String direction;
    private double x;
    private double y;

}
