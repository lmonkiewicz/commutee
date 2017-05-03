package com.lmonkiewicz.commutee.routes;

import com.lmonkiewicz.commutee.routes.adapter.in.loader.warsaw.ZtmDataLoader;
import com.lmonkiewicz.commutee.routes.adapter.out.connection.LoggingConnectionStore;
import com.lmonkiewicz.commutee.routes.adapter.out.timetable.LoggingTimetablesStore;
import com.lmonkiewicz.commutee.routes.domain.in.loader.TimetableDataLoader;
import com.lmonkiewicz.commutee.routes.domain.out.connection.ConnectionsStore;
import com.lmonkiewicz.commutee.routes.domain.out.timetable.TimetablesStore;
import com.lmonkiewicz.commutee.routes.domain.service.TimetablesImportService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
@Configuration
public class RoutesServiceConfig {

    @Bean
    ZtmDataLoader ztmDataLoader(TimetableDataLoader timetableDataLoader){
        return new ZtmDataLoader(timetableDataLoader);
    }

    @Bean
    ConnectionsStore connectionsStoreService(){
//        return new Neo4JConnectionStore();
        return new LoggingConnectionStore();
    }

    @Bean
    TimetablesStore timetablesStore() {
//        return new Neo4JTimetablesStore();
        return new LoggingTimetablesStore();
    }

    @Bean
    TimetableDataLoader timetableDataLoader(ConnectionsStore connectionsStore, TimetablesStore timetablesStore){
        return new TimetablesImportService(connectionsStore, timetablesStore);
    }
}
