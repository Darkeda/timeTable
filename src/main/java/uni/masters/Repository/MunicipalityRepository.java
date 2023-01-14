package uni.masters.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.masters.Model.LocationEntity;
import uni.masters.Model.MunicipalityEntity;

@Repository
public interface MunicipalityRepository extends JpaRepository<MunicipalityEntity,Long> {
}
