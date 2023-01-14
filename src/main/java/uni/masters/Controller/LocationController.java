package uni.masters.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.masters.Model.LocationEntity;
import uni.masters.Service.LocationService;

import java.util.List;

@RestController()
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping
    public List<LocationEntity> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping(path = "/get/{id}")
    public LocationEntity getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id);
    }

    @PostMapping(path = "/add")
    public void addLocation(@RequestBody LocationEntity locationEntity) {
        locationService.addLocation(locationEntity);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteLocation (@PathVariable Long id){
        locationService.deleteLocation(locationService.getLocationById(id));
    }

    @PutMapping(path = "/update")
    public void updateLocation (@RequestBody LocationEntity locationEntity){
        locationService.updateLocation(locationEntity);
    }
}