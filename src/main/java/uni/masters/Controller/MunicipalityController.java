package uni.masters.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.masters.Model.LocationEntity;
import uni.masters.Model.MunicipalityEntity;
import uni.masters.Service.MunicipalityService;

import java.util.List;

@RestController()
@RequestMapping("/municipality")
public class MunicipalityController {
    @Autowired
    MunicipalityService municipalityService;

    @GetMapping
    public List<MunicipalityEntity> getAllMunicipalities() {
        return municipalityService.getAllMunicipalities();
    }

    @GetMapping(path = "/get/{id}")
    public MunicipalityEntity getMunicipalityById(@PathVariable Long id) {
        return municipalityService.getMunicipalityById(id);
    }

    @PostMapping(path = "/add/{name}")
    public void addMunicipality(@PathVariable String name) {
        municipalityService.addMunicipality(new MunicipalityEntity(name));
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteMunicipality(@PathVariable Long id) {
        municipalityService.deleteMunicipality(municipalityService.getMunicipalityById(id));
    }

    @PutMapping(path = "/update")
    public void updateMunicipality(@RequestBody MunicipalityEntity municipalityEntity) {
        municipalityService.updateMunicipality(municipalityEntity);

    }
}