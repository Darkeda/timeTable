package uni.masters.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uni.masters.Model.LocationEntity;
import uni.masters.Model.RouteEntity;

import java.util.List;


@Repository
public interface RouteRepository extends JpaRepository<RouteEntity,Long> {

    List<RouteEntity> findByStartingLocationEntity(LocationEntity locationEntity);

    List<RouteEntity> findByFinalLocationEntity(LocationEntity locationEntity);


    @Query(
            value = "SELECT * FROM route r WHERE (:start is null OR r.STARTING_LOCATION  = :start)" +
                    "and (:final is null OR r.FINAL_LOCATION  = :final)"+
                    "and (:departure is null OR r.DEPARTURE  = :departure)",
            nativeQuery = true)
    List<RouteEntity> filterBy(@Param("start") Long startingLocationName,
                               @Param("final") Long finalLocationName,
                               @Param("departure") String departure);
}
