package uni.masters.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uni.masters.Model.RouteEntity;
import uni.masters.Model.RouteFilterDTO;
import uni.masters.Service.RouteService;

import java.util.List;

@RestController()
@RequestMapping("/route")
public class RouteController {

    @Autowired
    RouteService routeService;


    @GetMapping
    public List<RouteEntity> getAllRoutes() {
        return routeService.getAlRouters();
    }

    @GetMapping(path = "/get/{id}")
    public RouteEntity getRouteById(@PathVariable Long id) {
        return routeService.getRouteById(id);
    }


    @PostMapping(path = "/add")
    public void addRoute(@RequestBody RouteEntity routeEntity) {
        routeService.addRoute(routeEntity);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(routeService.getRouteById(id));
    }

    @PutMapping(path = "/update")
    public void updateRoute(@RequestBody RouteEntity routeEntity) {
        routeService.updateRoute(routeEntity);
    }

    @PostMapping(path = "/filter", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<RouteEntity> filterRoute(@RequestBody RouteFilterDTO routeEntity) {
        if (routeEntity.getDeparture().equals("")
                && routeEntity.getStartingLocation().equals("")
                && routeEntity.getFinalLocation().equals("")) {
            return routeService.getAlRouters();
        }
        return routeService.filterRoutersByy(routeEntity);
    }
}
