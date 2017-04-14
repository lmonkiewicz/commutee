package com.lmonkiewicz.commutee.routes.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
@Builder
@Getter
@EqualsAndHashCode(exclude = {"valid"})
public class BusStopData {

    @NotNull
    private final String id;
    @NotNull
    private final String name;
    @NotNull
    private final String direction;

    private final double posX;
    private final double posY;

    @Setter
    @Builder.Default
    private boolean valid = true;

}
