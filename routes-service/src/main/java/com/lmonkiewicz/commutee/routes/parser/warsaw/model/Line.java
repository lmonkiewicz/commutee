package com.lmonkiewicz.commutee.routes.parser.warsaw.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lmonkiewicz on 07.03.2017.
 */
@Builder
@Getter
@EqualsAndHashCode
public class Line {
    private String number;
    private String description;

    @Setter
    private Routes routes;

    @Setter
    private Courses courses;
}
