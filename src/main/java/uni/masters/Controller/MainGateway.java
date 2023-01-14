package uni.masters.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uni.masters.Model.LocationEntity;
import uni.masters.Model.MunicipalityEntity;
import uni.masters.Model.RouteEntity;
import uni.masters.Repository.LocationRepository;
import uni.masters.Repository.MunicipalityRepository;
import uni.masters.Repository.RouteRepository;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class MainGateway {

    @Autowired
    LocationRepository locationRepository;
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    MunicipalityRepository municipalityRepository;

    @GetMapping(path="/locations")
    @ModelAttribute
    public String locationView(Model model){
        model.addAttribute("locations", locationRepository.findAll());
        return "locations";
    }

    @GetMapping(path="/municipalities")
    @ModelAttribute
    public String municipalityView(Model model){
        model.addAttribute("municipality", municipalityRepository.findAll());
        return "municipalities";
    }

    @GetMapping(path="/routes")
    @ModelAttribute
    public String routesView(Model model){
        model.addAttribute("routes", routeRepository.findAll());
        return "routes";
    }


    @GetMapping(path="/search")
    @ModelAttribute
    public void searcView(Model model){

        model.addAttribute("routes", routeRepository.findAll());
        model.addAttribute("locations", locationRepository.findAll());

    }

    @PostMapping(path="/search")
    @ModelAttribute
    public void searchPost(Model model){


    }




    @GetMapping(path = "/test")
    public RouteEntity testController(){
        MunicipalityEntity municipality = new MunicipalityEntity("Bulgaria");

        municipalityRepository.save(municipality);

        LocationEntity locationEntity1 = new LocationEntity("Sofiq",municipality);
        LocationEntity locationEntity2 = new LocationEntity("Varna",municipality);

        LocationEntity locationEntity3 = new LocationEntity("Pleven",municipality);
        LocationEntity locationEntity4 = new LocationEntity("Plovdiv",municipality);
        LocationEntity locationEntity5 = new LocationEntity("Shumata",municipality);


        locationRepository.save(locationEntity1);
        locationRepository.save(locationEntity2);
        locationRepository.save(locationEntity3);
        locationRepository.save(locationEntity4);
        locationRepository.save(locationEntity5);





        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime lt = LocalTime.now();
        RouteEntity routeEntity1 = new RouteEntity(locationEntity1, locationEntity2, lt.format(dtf));
        routeRepository.save(routeEntity1);

        return routeEntity1;
    }
}
