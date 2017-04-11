package com.lmonkiewicz.commutee.routes.domain.in.query;

import com.lmonkiewicz.commutee.routes.domain.in.query.dto.QueryParameters;
import com.lmonkiewicz.commutee.routes.domain.in.query.dto.Route;
import com.lmonkiewicz.commutee.routes.domain.in.query.dto.Timetable;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.jetbrains.annotations.NotNull;

/**
 * Created by lmonkiewicz on 11.04.2017.
 */
public interface TimetablesQuery {

    Observable<Route> findRoutes(@NotNull QueryParameters query);

    Single<Timetable> findTimetable(String busStopId, String lineId);

    Single<Route> findLineRoute(String lineId);
}
