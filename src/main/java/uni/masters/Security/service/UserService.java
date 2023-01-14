package uni.masters.Security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uni.masters.Security.model.Role;
import uni.masters.Security.model.User;
import uni.masters.Security.model.UserDTO;
import uni.masters.Security.repository.UserRepository;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }



    public void registerNewUser(UserDTO userDTO0){
        User newUser = new User();
        newUser.setUsername(userDTO0.getUsername());
        newUser.setPassword(new BCryptPasswordEncoder().encode(userDTO0.getPassword()));
        newUser.setEnabled(true);
        newUser.setRoles(roleService.getAllRoles());
        userRepository.save(newUser);


    }

    public void addUser(User user) {
        userRepository.save(user);
    }
}
