package com.lmonkiewicz.commutee.routes;

import com.lmonkiewicz.commutee.routes.adapter.out.connection.Neo4jConnectionStoreService;
import com.lmonkiewicz.commutee.routes.domain.in.loader.TimetableDataLoader;
import com.lmonkiewicz.commutee.routes.domain.out.connection.ConnectionsStoreService;
import com.lmonkiewicz.commutee.routes.domain.service.TimetablesImportService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
@Configuration
public class RoutesServiceConfig {

    @Bean
    ConnectionsStoreService connectionsStoreService(){
        return new Neo4jConnectionStoreService();
    }

    @Bean
    TimetableDataLoader timetableDataLoader(ConnectionsStoreService connectionsStoreService){
        return new TimetablesImportService(connectionsStoreService);
    }
}
