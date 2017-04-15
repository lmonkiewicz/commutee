package com.lmonkiewicz.commutee.routes.domain.model;

import lombok.*;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
@Builder
@Getter
@EqualsAndHashCode(exclude = {"valid"})
public class BusStopData {

    @NonNull
    private final String id;
    @NonNull
    private final String name;
    @NonNull
    private final String direction;

    private final double posX;
    private final double posY;

    @Setter
    @Builder.Default
    private boolean valid = true;

}
