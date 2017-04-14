package com.lmonkiewicz.commutee.routes.domain.service;

import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.entry;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by lmonkiewicz on 12.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TimetablesImportServiceTest {

    @Test
    public void shouldCreateNewBusStop() throws Exception {
        InMemoryConnectionStore connectionsStore = new InMemoryConnectionStore();
        InMemoryTimetablesStore timetablesStore = new InMemoryTimetablesStore();

        // given
        final String STOP_ID = "123456";

        final TimetablesImportService service = new TimetablesImportService(connectionsStore, timetablesStore);
        final BusStopData data = BusStopData.builder()
                .id(STOP_ID)
                .direction("testDirection")
                .name("testStop")
                .posX(10.0)
                .posY(15.0)
                .build();

        // when
        service.start();
        service.updateBusStop(data);
        service.finish();

        // then
        assertThat(connectionsStore.getData()).containsOnly(entry(STOP_ID, data));
    }

    @Test
    public void shouldUpdateExistingBusStop() throws Exception {
        InMemoryConnectionStore connectionsStore = new InMemoryConnectionStore();
        InMemoryTimetablesStore timetablesStore = new InMemoryTimetablesStore();

        // given
        final String STOP_ID = "123456";
        final BusStopData newData = BusStopData.builder()
                .id(STOP_ID)
                .direction("testDirection")
                .name("testStop")
                .posX(10.0)
                .posY(15.0)
                .build();
        final BusStopData updatedData = BusStopData.builder()
                .id(STOP_ID)
                .direction("testDirectionNew")
                .name("testStopNew")
                .posX(10.5)
                .posY(15.5)
                .build();

        final TimetablesImportService service = new TimetablesImportService(connectionsStore, timetablesStore);

        // when
        service.start();
        service.updateBusStop(newData);
        service.finish();

        service.start();
        service.updateBusStop(updatedData);
        service.finish();

        // then
        assertThat(connectionsStore.getData()).containsOnly(entry(STOP_ID, updatedData));
    }
}