package com.lmonkiewicz.commutee.routes.domain.service;

import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import com.lmonkiewicz.commutee.routes.domain.out.connection.ConnectionsStoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by lmonkiewicz on 12.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TimetablesImportServiceTest {


    @Mock
    ConnectionsStoreService connectionsStoreService;

    @Captor
    ArgumentCaptor<BusStopData> busStopDataArgumentCaptor;

    @Test
    public void shouldCreateNewBusStop() throws Exception {

        //        given:
        final String STOP_ID = "123456";

        when(connectionsStoreService.findBusStopById(STOP_ID)).thenReturn(Optional.empty());

        final TimetablesImportService service = new TimetablesImportService(connectionsStoreService);
        final BusStopData data = BusStopData.builder()
                .id(STOP_ID)
                .direction("testDirection")
                .name("testStop")
                .posX(10.0)
                .posY(15.0)
                .build();

        //        when:

        service.start();
        service.updateBusStop(data);
        service.finish();

        //        then:
        verify(connectionsStoreService, times(1))
                .createBusStopData(busStopDataArgumentCaptor.capture());
        verify(connectionsStoreService, times(0)).updateBusStopData(any());

        assertThat(busStopDataArgumentCaptor.getValue()).isEqualTo(data);
    }

    @Test
    public void shouldUpdateExistingBusStop() throws Exception {

        //        given:
        final String STOP_ID = "123456";
        final BusStopData existingData = BusStopData.builder()
                .id(STOP_ID)
                .direction("testDirection")
                .name("testStop")
                .posX(10.0)
                .posY(15.0)
                .build();

        when(connectionsStoreService.findBusStopById(STOP_ID)).thenReturn(Optional.of(existingData));

        final TimetablesImportService service = new TimetablesImportService(connectionsStoreService);
        final BusStopData data = BusStopData.builder()
                .id(STOP_ID)
                .direction("testDirectionNew")
                .name("testStopNew")
                .posX(10.5)
                .posY(15.5)
                .build();

        //        when:

        service.start();
        service.updateBusStop(data);
        service.finish();

        //        then:
        verify(connectionsStoreService, times(1))
                .updateBusStopData(busStopDataArgumentCaptor.capture());
        verify(connectionsStoreService, times(0)).createBusStopData(any());

        assertThat(busStopDataArgumentCaptor.getValue()).isEqualTo(data);
    }
}