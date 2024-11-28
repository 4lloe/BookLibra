/*package com.example.Library.spring.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



import com.example.Library.dao.service.PasswordService;
import com.example.Library.entity.User;
import com.example.Library.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.Optional;


@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";  // Это имя HTML-шаблона, например register.html
    }

    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password,
                           @RequestParam String name, RedirectAttributes redirectAttributes) {
        Optional<User> existingUser = userRepository.findUserByEmail(email);
        if (existingUser.isPresent()) {
            // User with this email already exists
            redirectAttributes.addFlashAttribute("error", "Email already exists!");
            return "redirect:/register"; // Redirect to register page
        } else {
            // Encrypt password before saving
            String encryptedPassword = passwordService.encryptPassword(password);
            // Create and save new user
            User user = new User(name, email, encryptedPassword);
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());
            user.setRole("ROLE_USER");

            userRepository.save(user);

            // Add success message
            redirectAttributes.addFlashAttribute("message", "Registration successful!");
            return "redirect:/login"; // Redirect to login page
        }
    }



}*/


