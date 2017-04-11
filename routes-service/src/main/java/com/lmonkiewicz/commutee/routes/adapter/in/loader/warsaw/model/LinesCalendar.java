package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

/**
 * Created by lmonkiewicz on 2017-02-22.
 */
public class LinesCalendar {

    private Table<LocalDate, String, String> data = HashBasedTable.create();

    /**
     * Sets the day type for a line at specific date
     *
     * @param currentDate - date
     * @param lineName - line
     * @param dayType - day type
     */
    public void set(@NotNull LocalDate currentDate,
                    @NotNull String lineName,
                    @NotNull String dayType) {
        data.put(currentDate, lineName, dayType);
    }

    /**
     * Return day type for a line at specific date
     *
     * @param date - date
     * @param lineName - line
     * @return - day type
     */
    @Nullable
    public String get(@Nullable LocalDate date, @Nullable String lineName){
        if (date == null) return null;
        if (lineName == null) return null;

        return data.get(date, lineName);
    }
}
