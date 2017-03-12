package com.lmonkiewicz.commutee.routes.parser.warsaw.section;

import com.lmonkiewicz.commutee.routes.parser.warsaw.BaseSectionReaderTest;
import com.lmonkiewicz.commutee.routes.parser.warsaw.model.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lmonkiewicz on 07.03.2017.
 */
public class LLSectionReaderTest extends BaseSectionReaderTest {

    @Test
    public void shouldCorrectlyParseLinesList() throws Exception {
        try(BufferedReader input = createReader("LLSectionData.txt")){
            LLSectionReader reader = new LLSectionReader();
            reader.readSection(input);
            Lines lines = reader.result();

            assertThat(lines).isNotNull();
            assertThat(lines.stream().count()).isEqualTo(1);

            assertThat(lines.get("123").isPresent()).isFalse();

            final Optional<Line> line = lines.get("1");
            assertThat(line.isPresent()).isTrue();
            assertThat(line.get().getNumber()).isEqualTo("1");
            assertThat(line.get().getDescription()).isEqualTo("LINIA TRAMWAJOWA");
        }

    }

    @Test
    public void shouldCorrectlyParseLinesRoutes() throws Exception {
        try(BufferedReader input = createReader("LLSectionData.txt")){
            LLSectionReader reader = new LLSectionReader();
            reader.readSection(input);
            Lines lines = reader.result();

            final Line line = lines.get("1").orElseThrow(Exception::new);
            final Routes routes = line.getRoutes();
            assertThat(routes.stream().count()).isEqualTo(1);

            final Optional<Route> route = routes.get("TP-ANN");
            assertThat(route.isPresent()).isTrue();

            final Route routeTpAnn = route.get();
            assertThat(routeTpAnn.getBeginingStop()).isEqualTo("BANACHA");
            assertThat(routeTpAnn.getBeginingTown()).isEqualTo("WA");
            assertThat(routeTpAnn.getEndingStop()).isEqualTo("ANNOPOL");
            assertThat(routeTpAnn.getEndingTown()).isEqualTo("AN");
            assertThat(routeTpAnn.getDirection()).isEqualTo("A");
            assertThat(routeTpAnn.getPosition()).isEqualTo("0");
        }
    }

    @Test
    public void shouldCorrectlyParseRouteStops() throws Exception {
        try(BufferedReader input = createReader("LLSectionData.txt")){
            LLSectionReader reader = new LLSectionReader();
            reader.readSection(input);
            Lines lines = reader.result();

            final RouteDefinition route = lines.get("1").orElseThrow(Exception::new).getRoutes().get("TP-ANN").get().getRouteDefinition();

            assertThat(route).isNotNull();
            assertThat(route.stream().count()).isEqualTo(2);

            RouteStop first = route.stream().findFirst().orElseThrow(Exception::new);
            RouteStop second = route.stream().skip(1).findFirst().orElseThrow(Exception::new);

            assertThat(first.getStreetName()).isEqualTo("BANACHA");
            assertThat(first.getType()).isEqualTo("r");
            assertThat(first.getBusStopId()).isEqualTo("410804");
            assertThat(first.getStopName()).isEqualTo("BANACHA");
            assertThat(first.getTownCode()).isEqualTo("--");
            assertThat(first.getBusStopGroupCode()).isEqualTo("04");
            assertThat(first.isOnDemand()).isFalse();
            assertThat(first.getMinArrivalTime()).isEqualTo(0);
            assertThat(first.getMaxArrivalTime()).isEqualTo(0);

            assertThat(second.getStreetName()).isEqualTo("BANACHA");
            assertThat(second.getType()).isEqualTo("r");
            assertThat(second.getBusStopId()).isEqualTo("410803");
            assertThat(second.getStopName()).isEqualTo("BANACHA");
            assertThat(second.getTownCode()).isEqualTo("--");
            assertThat(second.isOnDemand()).isTrue();
            assertThat(second.getBusStopGroupCode()).isEqualTo("03");
            assertThat(second.getMinArrivalTime()).isEqualTo(1);
            assertThat(second.getMaxArrivalTime()).isEqualTo(3);


        }
    }

    @Test
    public void shouldCorrectlyParseRoutePoints() throws Exception {
        try(BufferedReader input = createReader("LLSectionData.txt")){
            LLSectionReader reader = new LLSectionReader();
            reader.readSection(input);
            Lines lines = reader.result();

            final RoutePoints points = lines.get("1").orElseThrow(Exception::new).getRoutes().get("TP-ANN").get().getRoutePoints();

            assertThat(points).isNotNull();
            assertThat(points.stream().count()).isEqualTo(1);
        }
    }

    @Test
    public void shouldCorrectlyParseRoutePointTimetable() throws Exception {
        try(BufferedReader input = createReader("LLSectionData.txt")){
            LLSectionReader reader = new LLSectionReader();
            reader.readSection(input);
            Lines lines = reader.result();

            final RoutePoint routePoint = lines.get("1")
                    .flatMap(line -> line.getRoutes().get("TP-ANN"))
                    .flatMap(route -> route.getRoutePoints().get("410804"))
                    .orElseThrow(Exception::new);

            final RoutePointTimetables timetables = routePoint.getTimetables();
            assertThat(timetables.stream().count()).isEqualTo(3);

            final Optional<RoutePointTimetable> weekDay = timetables.get("DP");
            final Optional<RoutePointTimetable> saturday = timetables.get("SB");
            final Optional<RoutePointTimetable> holiday = timetables.get("DS");

            assertThat(weekDay.isPresent()).isTrue();
            assertThat(saturday.isPresent()).isTrue();
            assertThat(holiday.isPresent()).isTrue();

            final Stream<Integer> timesAt5 = weekDay.map(RoutePointTimetable::getDepartTimes)
                    .map(departTimes -> departTimes.get(5))
                    .orElseGet(Stream::empty);

            assertThat(timesAt5).containsExactly(8, 18, 28, 38, 48, 58);

            final Stream<Integer> timesAt6 = weekDay.map(RoutePointTimetable::getDepartTimes)
                    .map(departTimes -> departTimes.get(6))
                    .orElseGet(Stream::empty);

            assertThat(timesAt6).containsExactly(8,18,24,30,36,42,48,54);

            Metadata metadata = routePoint.getMetadata();
            assertThat(metadata).isNotNull();
            assertThat(metadata.getValidFrom()).isEqualTo(LocalDate.of(2017, 2, 27));
            assertThat(metadata.getSymbols()).containsExactly(
                    "[] - kurs realizowany przez pojazd niskopodłogowy",
                    "e - kurs wydłużony do krańca ŻERAŃ WSCHODNI",
                    "m - kurs tylko do przystanku PL.NARUTOWICZA i dalej do zajezdni",
                    "\"MOKOTÓW\"",
                    "z - kurs tylko do przystanku POWĄZKOWSKA i dalej do zajezdni",
                    "\"ŻOLIBORZ\""
            );


        }
    }
}
