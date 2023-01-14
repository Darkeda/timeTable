package uni.masters.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.masters.Model.LocationEntity;


@Repository
public interface LocationRepository extends JpaRepository<LocationEntity,Long> {

    LocationEntity findByName(String name);
}
