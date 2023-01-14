package uni.masters.Security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uni.masters.Security.model.User;
import uni.masters.Security.model.UserDTO;
import uni.masters.Security.service.UserService;


@Controller
public class SecurityController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/register" )
    public String registerView(Model model, User user) {
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping(path = "/register" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(@RequestBody UserDTO user) {

    userService.registerNewUser(user);
    }
}
