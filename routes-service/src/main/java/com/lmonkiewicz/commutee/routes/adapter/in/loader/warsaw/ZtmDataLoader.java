package com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw;

import com.google.common.base.Strings;
import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.model.*;
import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.section.RootSectionReader;
import com.lmonkiewicz.commutee.routes.domain.in.loader.TimetableDataLoader;
import com.lmonkiewicz.commutee.routes.domain.in.loader.exception.LoaderException;
import com.lmonkiewicz.commutee.routes.domain.model.BusStopData;
import com.lmonkiewicz.commutee.routes.domain.model.ConnectionData;
import lombok.extern.slf4j.Slf4j;
import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.util.ByteArrayStream;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by lmonkiewicz on 16.04.2017.
 */
@Slf4j
public class ZtmDataLoader {


    private final TimetableDataLoader timetableDataLoader;

    public ZtmDataLoader(TimetableDataLoader timetableDataLoader) {
        this.timetableDataLoader = timetableDataLoader;
    }

    // for now, load it on startup
//    @PostConstruct
    public void loadData(){
        log.info("Loading data");

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(fetchData(), "Windows-1250"))) {
            log.info("Parsing data");

            final RootSectionReader sectionReader = new RootSectionReader();
            sectionReader.readSection(reader);
            final ZtmData ztmData = sectionReader.result();
            log.info("Data parsed");

            processData(ztmData);

        } catch (IOException e) {
            log.error("Error while loading data",e);
        } catch (SectionReaderException e) {
            log.error("Error while parsing data",e);
        }
    }

    private void processData(ZtmData ztmData) {
        log.info("Loaded data: " + ztmData);

        // create all bus stops
        try {
            timetableDataLoader.start();
            createBusStops(ztmData);
            createConnections(ztmData);
            timetableDataLoader.finish();
        } catch (LoaderException e) {
            log.error("Error while loading data", e);
        }
    }

    private void createConnections(ZtmData ztmData) {
        ztmData.getLines().stream()
                .forEach(line -> {
                    final List<RouteStop> routeStops = line.getRoutes().stream()
                            .map(Route::getRouteDefinition)
                            .flatMap(RouteDefinition::stream)
                            .collect(Collectors.toList());

                    final PeekingIterator<RouteStop> routeStopIterator = Iterators.peekingIterator(routeStops.iterator());
                    while(routeStopIterator.hasNext()){
                        final RouteStop fromStop = routeStopIterator.next();
                        if (routeStopIterator.hasNext()) {
                            final RouteStop toStop = routeStopIterator.peek();

                            // TODO check why it has only name
                            if (Strings.isNullOrEmpty(toStop.getBusStopId())){
                                continue;
                            }

                            line.getCourses().streamAll()
                                    .filter(course -> Objects.equals(course.getStopId(), fromStop.getBusStopId()))
                                    .forEach(course -> createConnection(line, fromStop, toStop, course));
                        }
                    }

                });
    }

    private void createConnection(Line line, RouteStop fromStop, RouteStop toStop, Course course) {
        final ConnectionData connection = ConnectionData.builder()
                .type(ConnectionData.Type.BUS) // TODO set correct type
                .courseType(getCourseTypeByDayType(course.getDayType()))
                .code(line.getNumber())
                .departureTime(course.getDepartTime())
                .valid(true)
//              .validSince() // TODO find date
//              .validTo() // TODO find date
                .build();
        try {
            timetableDataLoader.createConnection(fromStop.getBusStopId(), toStop.getBusStopId(), connection);
        } catch (LoaderException e) {
            log.error(
                    "Error while crating connection between {} and {}: {}",
                    fromStop.getBusStopId(),
                    toStop.getBusStopId(),
                    e.getMessage());
        }
    }

    private ConnectionData.CourseType getCourseTypeByDayType(String dayType) {
        log.debug("Course day type: {}", dayType);
        return ConnectionData.CourseType.WEEKDAY;
    }

    private void createBusStops(ZtmData ztmData) {
        ztmData.getBusStopGroups().stream()
                .flatMap(BusStopGroup::stream)
                .map(busStop -> BusStopData.builder()
                        .id(busStop.getId())
                        .direction(busStop.getDirection())
                        .name(busStop.getName())
                        .posX(busStop.getX())
                        .posY(busStop.getY())
                        .valid(true)
                        .build())
                .forEach(busStop -> {
                    try {
                        timetableDataLoader.updateBusStop(busStop);
                    } catch (LoaderException e) {
                        log.error(String.format("Error while updating bus stop %s", busStop.getId()), e);
                    }
                });
    }

    private InputStream fetchData() throws IOException {
        final InputStream inputStream = this.getClass().getResourceAsStream("/ZTM_DATA_RA170422.7z");

        final byte[] bytes = IOUtils.toByteArray(inputStream);

        try (final ByteArrayStream bas = new ByteArrayStream(bytes, false)) {
            final IInArchive archive = SevenZip.openInArchive(ArchiveFormat.SEVEN_ZIP, bas);

            try (final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                final ISimpleInArchive simpleInterface = archive.getSimpleInterface();
                if (simpleInterface.getNumberOfItems() > 0) {
                    simpleInterface.getArchiveItem(0).extractSlow(data -> {
                        try {
                            byteArrayOutputStream.write(data);
                            log.info("Loaded bytes:" + byteArrayOutputStream.size());
                        } catch (IOException e) {
                            log.error("Error while reading archive data", e);
                        }
                        return data.length;
                    });
                    final byte[] resultData = byteArrayOutputStream.toByteArray();
                    log.info("Decompressed data (B): " + resultData.length);
                    return new ByteArrayInputStream(resultData);
                }
                else {
                    log.warn("No files in the archive");
                    return new ByteArrayInputStream(new byte[0]);
                }
            }
        }
    }
}
