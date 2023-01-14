package uni.masters.Security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.masters.Security.model.Role;

public interface RolesRepository extends JpaRepository<Role,Long> {
}
