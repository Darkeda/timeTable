package uni.masters.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.masters.Model.LocationEntity;
import uni.masters.Model.RouteEntity;
import uni.masters.Model.RouteFilterDTO;
import uni.masters.Repository.RouteRepository;

import java.util.List;
import java.util.Objects;

@Service
public class RouteService {

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    LocationService locationService;

    public void addRoute(RouteEntity routeEntity) {
        routeRepository.save(routeEntity);
    }

    public void deleteRoute(RouteEntity routeEntity) {
        routeRepository.delete(routeEntity);
    }

    public RouteEntity getRouteById(Long id) {
        return routeRepository.getReferenceById(id);
    }

    public List<RouteEntity> filterRoutersByy(RouteFilterDTO routeFilterDTO) {
        Long startingId = null;
        Long finalId = null;
        String departure = null;

        if (routeFilterDTO.getStartingLocation() != null) {
            if (locationService.isLocationInDatabase(routeFilterDTO.getStartingLocation())) {
                startingId = locationService.findByName(routeFilterDTO.getStartingLocation()).getId();
            }
        }


        if (routeFilterDTO.getFinalLocation() != null) {
            if (locationService.isLocationInDatabase(routeFilterDTO.getFinalLocation())) {
                finalId = locationService.findByName(routeFilterDTO.getFinalLocation()).getId();
            }
        }

        if (!Objects.equals(routeFilterDTO.getDeparture(), "")) {
            departure = routeFilterDTO.getDeparture();
        }


        return routeRepository.filterBy
                (startingId, finalId, departure);
    }

    public List<RouteEntity> filterRoutersByStartingLocation(LocationEntity locationEntity) {
        return routeRepository.findByStartingLocationEntity(locationEntity);
    }

    public List<RouteEntity> filterRoutersByEndingLocation(LocationEntity locationEntity) {
        return routeRepository.findByFinalLocationEntity(locationEntity);
    }

    public List<RouteEntity> getAlRouters() {
        return routeRepository.findAll();
    }

    public void updateRoute(RouteEntity locationEntity) {
        routeRepository.save(locationEntity);
    }
}
