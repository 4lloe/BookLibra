package com.example.Library.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



import com.example.Library.dao.service.PasswordService;
import com.example.Library.entity.User;
import com.example.Library.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
                           @RequestParam String name) {
        Optional<User> existingUser = userRepository.findUserByEmail(email);
        if (existingUser.isPresent()) {
            // Пользователь с таким email уже существует
            return "redirect:/register?error=emailExists";
        } else {

            // Шифрование пароля перед сохранением
            String encryptedPassword = passwordService.encryptPassword(password);

            // Создание нового пользователя
            User user = new User(name, email, encryptedPassword);
            user.setCreatedAt(new Date());  // Установка даты создания
            user.setUpdatedAt(new Date());  // Установка даты обновления
            user.setRole("ROLE_USER");

            // Сохранение пользователя в базе данных
            userRepository.save(user);

            // Перенаправление на страницу входа после успешной регистрации
            return "redirect:/login";
        }
    }
}


