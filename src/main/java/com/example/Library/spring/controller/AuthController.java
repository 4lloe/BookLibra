package com.example.Library.spring.controller;


import com.example.Library.dao.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

   /* private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Имя HTML-шаблона
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email, @RequestParam String password) {
        userService.registerUser(email, password);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Имя HTML-шаблона
    }*/
}
