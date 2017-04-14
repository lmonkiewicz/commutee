package com.lmonkiewicz.commutee.routes.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
@Builder
@Getter
@EqualsAndHashCode(exclude = {"valid"})
public class ConnectionData {

    @NotNull
    @Builder.Default
    private final Type type = Type.WALK;

    @Nullable
    private final String code;

    @Nullable
    private final LocalDate validSince;

    @Nullable
    private final LocalDate validTo;

    @NotNull
    @Builder.Default
    private final CourseType courseType = CourseType.WEEKDAY;

    @Nullable
    private final LocalTime departureTime;

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
