package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalTime;

/**
 * Created by lmonkiewicz on 17.03.2017.
 */
@Builder
@Getter
@EqualsAndHashCode
public class Course {
    private String courseId;
    private String stopId;
    private String dayType;
    private LocalTime departTime;
    private boolean endStop;
    private boolean bonusStop;
}
