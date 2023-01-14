package uni.masters.Security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.masters.Security.model.Role;
import uni.masters.Security.repository.RolesRepository;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RolesRepository rolesRepository;

    public List<Role> getAllRoles(){
        return rolesRepository.findAll();
    }

    public Role getRoleById(Long id){
        return rolesRepository.getReferenceById(id);
    }

    public void addRole(Role role) {
        rolesRepository.save(role);
    }
}
