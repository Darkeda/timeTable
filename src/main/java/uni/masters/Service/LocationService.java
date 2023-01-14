package uni.masters.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.masters.Model.LocationEntity;
import uni.masters.Repository.LocationRepository;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public void addLocation(LocationEntity locationEntity) {
        locationRepository.save(locationEntity);
    }

    public void deleteLocation(LocationEntity locationEntity) {
        locationRepository.delete(locationEntity);
    }

    public LocationEntity getLocationById(Long id) {
        return locationRepository.getReferenceById(id);
    }

    public List<LocationEntity> getAllLocations() {
        return locationRepository.findAll();
    }

    public void updateLocation(LocationEntity locationEntity) {
        locationRepository.save(locationEntity);
    }

    public LocationEntity findByName(String name) {
        return locationRepository.findByName(name);
    }

    public boolean isLocationInDatabase(String name) {
        if (locationRepository.findByName(name) != null) {
            return true;
        } else return false;
    }

}
