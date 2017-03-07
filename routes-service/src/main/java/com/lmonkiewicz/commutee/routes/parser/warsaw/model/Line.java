package com.lmonkiewicz.commutee.routes.parser.warsaw.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by lmonkiewicz on 07.03.2017.
 */
@Builder
@Getter
@EqualsAndHashCode
public class Line {
    private String number;
    private String description;
    private Routes routes;

    public void setRoutes(Routes routes) {
        this.routes = routes;
    }

    public Routes getRoutes(){
        return this.routes;
    }
}
