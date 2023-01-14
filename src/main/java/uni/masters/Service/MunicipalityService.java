package uni.masters.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.masters.Model.LocationEntity;
import uni.masters.Model.MunicipalityEntity;
import uni.masters.Repository.MunicipalityRepository;

import java.util.List;

@Service
public class MunicipalityService {

    @Autowired
    MunicipalityRepository municipalityRepository;

    public void addMunicipality(MunicipalityEntity municipalityEntity) {
        municipalityRepository.save(municipalityEntity);
    }

    public void deleteMunicipality(MunicipalityEntity municipalityEntity) {
        municipalityRepository.delete(municipalityEntity);
    }

    public MunicipalityEntity getMunicipalityById(Long id) {
       return municipalityRepository.getReferenceById(id);
    }

    public List<MunicipalityEntity> getAllMunicipalities() {
        return municipalityRepository.findAll();
    }

    public List<MunicipalityEntity> getAllMunicipality() {
        return municipalityRepository.findAll();
    }

    public void updateMunicipality(MunicipalityEntity municipalityEntity) {
        municipalityRepository.save(municipalityEntity);
    }
}
