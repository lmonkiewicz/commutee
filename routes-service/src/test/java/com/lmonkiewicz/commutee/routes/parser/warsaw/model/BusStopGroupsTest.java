package com.lmonkiewicz.commutee.routes.parser.warsaw.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by lmonkiewicz on 2017-02-23.
 */
public class BusStopGroupsTest {

    @Test
    public void shouldCorrectlyReturnGroup() throws Exception {
        final BusStopGroup group = BusStopGroup.builder()
                .id("12345")
                .name("BUS STOP")
                .townCode("WW")
                .townName("WARSAW")
                .build();

        final BusStopGroups groups = new BusStopGroups();
        groups.add(group);

        assertEquals(group, groups.get("12345"));

    }
}