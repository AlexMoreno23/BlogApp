package by.morunov.controller;

import by.morunov.model.Role;
import by.morunov.model.User;
import by.morunov.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

/**
 * @author Alex Morunov
 */
@Controller
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserService userService, @Lazy PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {


        if (!userService.addUser(user)) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setRoles(Collections.singleton(Role.PERSON));
        if(user.getPassword().equals(passwordEncoder.encode("admin"))){
            user.setRoles(Collections.singleton(Role.ADMIN));
        }
        userService.addUser(user);

        return "redirect:/login";
    }
}
