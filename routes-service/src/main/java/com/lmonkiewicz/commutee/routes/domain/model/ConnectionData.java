package com.lmonkiewicz.commutee.routes.domain.model;

import lombok.*;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
@Builder
@Getter
@EqualsAndHashCode(exclude = {"valid"})
@ToString
public class ConnectionData {

    @NonNull
    @Builder.Default
    private Type type = Type.WALK;

    @NonNull
    private String code;

    @Nullable
    private LocalDate validSince;

    @Nullable
    private LocalDate validTo;

    @NonNull
    @Builder.Default
    private CourseType courseType = CourseType.WEEKDAY;

    @Nullable
    private LocalTime departureTime;

    @Setter
    @Builder.Default
    private boolean valid = true;

    public boolean hasDepartureTime(){
        return departureTime != null;
    }

    public enum CourseType {
        WEEKDAY,
        HOLIDAY,
        NIGHT
    }

    public enum Type {
        WALK,
        BUS,
        TRAM,
        SUBWAY,
        SUBURBAN_TRAIN
    }
}
