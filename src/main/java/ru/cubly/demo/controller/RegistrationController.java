package ru.cubly.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.cubly.demo.entity.User;
import ru.cubly.demo.service.UserService;

import javax.persistence.NonUniqueResultException;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(User user, Model model) {
        try {
            user = this.userService.register(user);
        } catch (NonUniqueResultException e) {
            model.addAttribute("message", "User already exists");
        }

        return "redirect:/login";
    }
}
