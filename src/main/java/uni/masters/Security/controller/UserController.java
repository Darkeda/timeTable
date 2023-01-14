package uni.masters.Security.controller;


import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import uni.masters.Security.model.Role;
import uni.masters.Security.model.User;

import uni.masters.Security.repository.RolesRepository;
import uni.masters.Security.repository.UserRepository;
import uni.masters.Security.service.RoleService;
import uni.masters.Security.service.UserService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping
    public List<User> getAllUsers() {
        Collection<SimpleGrantedAuthority> authorities =
                (Collection<SimpleGrantedAuthority>)
                        SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println(authorities);
        return userService.getAllUsers();
    }
    
    @PostMapping(path = "/add")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }
}
