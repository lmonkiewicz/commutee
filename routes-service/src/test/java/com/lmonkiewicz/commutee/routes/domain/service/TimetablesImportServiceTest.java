package com.lmonkiewicz.commutee.routes.domain.service;

import com.lmonkiewicz.commutee.routes.domain.in.loader.exception.BusStopNotFoundException;
import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import com.lmonkiewicz.commutee.routes.domain.model.ConnectionData;
import org.assertj.core.api.ThrowableAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.entry;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by lmonkiewicz on 12.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TimetablesImportServiceTest {

    private TimetablesImportService service;
    private InMemoryTimetablesStore timetablesStore;
    private InMemoryConnectionStore connectionStore;

    @Before
    public void setUp() throws Exception {
        connectionStore = new InMemoryConnectionStore();
        timetablesStore = new InMemoryTimetablesStore();
        service = new TimetablesImportService(connectionStore, timetablesStore);
    }

    @Test
    public void shouldCreateNewBusStop() throws Exception {
        // given
        final String STOP_ID = "123456";
        final BusStopData data = busStop(STOP_ID, "testDirection", "testStop", 10.0, 15.0);

        // when
        service.start();
        service.updateBusStop(data);
        service.finish();

        // then
        assertThat(connectionStore.getData()).containsOnly(entry(STOP_ID, data));
    }

    @Test
    public void shouldUpdateExistingBusStop() throws Exception {
        // given
        final String STOP_ID = "123456";
        final BusStopData newData = busStop(STOP_ID, "testDirection", "testStop", 10.0, 15.0);
        final BusStopData updatedData = busStop(STOP_ID, "testDirectionNew", "testStopNew", 10.5, 15.5);


        // when
        service.start();
        service.updateBusStop(newData);
        service.finish();

        service.start();
        service.updateBusStop(updatedData);
        service.finish();

        // then
        assertThat(connectionStore.getData()).containsOnly(entry(STOP_ID, updatedData));
    }

    @Test
    public void shouldAddMultipleBusStops() throws Exception {
        // given
        final String STOP_1 = "1";
        final String STOP_2 = "2";

        final BusStopData source = busStop(STOP_1, "direction", "stop1", 1, 2);
        final BusStopData target = busStop(STOP_2, "direction", "stop2", 2, 3);

        // when
        service.start();
        service.updateBusStop(source);
        service.updateBusStop(target);
        service.finish();

        // then
        assertThat(connectionStore.getData()).containsExactly(
                entry(STOP_1, source),
                entry(STOP_2, target)
        );
    }

    @Test
    public void shouldThrowWhenAddingConnectionForNotExistingSourceBusStop() throws Exception {
        // given
        final ConnectionData connection = connection("175", LocalTime.NOON, LocalDate.now(), ConnectionData.Type.BUS, ConnectionData.CourseType.WEEKDAY);

        // when
        final ThrowableAssert.ThrowingCallable methodCall = () -> service.createConnection("BAD_ID", "BAD_ID2", connection);

        // then
        assertThatExceptionOfType(BusStopNotFoundException.class).isThrownBy(methodCall);
    }

    @Test
    public void shouldThrowWhenAddingConnectionForNotExistingTargetBusStop() throws Exception {
        // given
        final String STOP_ID = "123456";
        final BusStopData sourceBusStop = busStop(STOP_ID, "testDirection", "testStop", 10.0, 15.0);
        final ConnectionData connection = connection("175", LocalTime.NOON, LocalDate.now(), ConnectionData.Type.BUS, ConnectionData.CourseType.WEEKDAY);
        service.updateBusStop(sourceBusStop);

        // when
        final ThrowableAssert.ThrowingCallable methodCall = () -> service.createConnection(STOP_ID, "BAD_ID2", connection);

        // then
        assertThatExceptionOfType(BusStopNotFoundException.class).isThrownBy(methodCall);
    }

    @Test
    public void shouldAddConnectionBetweenTwoStops() throws Exception {
        // given
        final String STOP_1 = "1";
        final String STOP_2 = "2";

        final BusStopData source = busStop(STOP_1, "direction", "stop1", 1, 2);
        final BusStopData target = busStop(STOP_2, "direction", "stop2", 2, 3);
        service.updateBusStop(source);
        service.updateBusStop(target);

        final ConnectionData connection = connection("175", LocalTime.NOON, LocalDate.now(), ConnectionData.Type.BUS, ConnectionData.CourseType.WEEKDAY);

        // when
        service.createConnection(STOP_1, STOP_2, connection);

        // then
        assertThat(connectionStore.getConnections()).hasSize(1);
        assertThat(connectionStore.get(STOP_1, STOP_2, connection.getCode())).containsExactly(connection);
    }

    @Test
    public void shouldAddMultipleConnectionBetweenTwoStops() throws Exception {
        // given
        final String STOP_1 = "1";
        final String STOP_2 = "2";

        final BusStopData source = busStop(STOP_1, "direction", "stop1", 1, 2);
        final BusStopData target = busStop(STOP_2, "direction", "stop2", 2, 3);
        service.updateBusStop(source);
        service.updateBusStop(target);

        final ConnectionData connection1 = connection("175", LocalTime.NOON, LocalDate.now(), ConnectionData.Type.BUS, ConnectionData.CourseType.WEEKDAY);
        final ConnectionData connection2 = connection("185", LocalTime.NOON, LocalDate.now(), ConnectionData.Type.BUS, ConnectionData.CourseType.WEEKDAY);
        final ConnectionData connection3 = connection("185", LocalTime.MIDNIGHT, LocalDate.now(), ConnectionData.Type.BUS, ConnectionData.CourseType.WEEKDAY);

        // when
        service.createConnection(STOP_1, STOP_2, connection1);
        service.createConnection(STOP_1, STOP_2, connection2);
        service.createConnection(STOP_1, STOP_2, connection3);

        // then
        assertThat(connectionStore.getConnections()).hasSize(2);
        assertThat(connectionStore.get(STOP_1, STOP_2, connection1.getCode())).containsExactly(connection1);
        assertThat(connectionStore.get(STOP_1, STOP_2, connection2.getCode())).containsExactly(connection2, connection3);
        assertThat(connectionStore.get(STOP_1, STOP_2, "FAKE")).isEmpty();
        assertThat(connectionStore.get(STOP_1, "FAKE", "FAKE")).isEmpty();
        assertThat(connectionStore.get("FAKE", "FAKE", "FAKE")).isEmpty();
    }

    private ConnectionData connection(String code, LocalTime departureTime, LocalDate validSince, ConnectionData.Type type, ConnectionData.CourseType courseType) {
        return ConnectionData.builder()
                .code(code)
                .departureTime(departureTime)
                .validSince(validSince)
                .type(type)
                .courseType(courseType)
                .build();
    }

    private BusStopData busStop(String stopId, String direction, String name, double x, double y) {
        return BusStopData.builder()
                .id(stopId)
                .direction(direction)
                .name(name)
                .posX(x)
                .posY(y)
                .build();
    }
}