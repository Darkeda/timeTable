package uni.masters.Security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.masters.Model.LocationEntity;
import uni.masters.Security.model.Role;
import uni.masters.Security.repository.RolesRepository;
import uni.masters.Security.service.RoleService;

import java.util.List;

@RestController()
@RequestMapping("/role")
public class RolesController {

    @Autowired
    RoleService roleService;


    @GetMapping
    public List<Role> getAllLocations() {
        return roleService.getAllRoles();
    }

    @PostMapping(path = "/add/{name}")
    public void addRole(@PathVariable String name) {
        System.out.println(name);
        Role role = new Role(name);
        roleService.addRole(role);
    }
}
