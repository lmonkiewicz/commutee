package com.lmonkiewicz.commutee.routes.parser.warsaw.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by lmonkiewicz on 02.03.2017.
 */
public enum BusStopLineType {
    ON_DEMAND("na żądanie"),
    PERMANENT("stały"),
    ENTRY_ONLY("dla wsiadających"),
    EXIT_ONLY("dla wysiadających"),
    END_OF_LINE("krańcowy"),
    WAITING("postojowy");

    @NotNull
    private final String code;

    BusStopLineType(@NotNull String code) {
        this.code = code;
    }

    @Nullable
    public static BusStopLineType fromCode(@NotNull String code){
        for (BusStopLineType type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        return null;
    }
}
