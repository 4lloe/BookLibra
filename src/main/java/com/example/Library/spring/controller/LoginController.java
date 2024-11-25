package com.example.Library.spring.controller;

import com.example.Library.dao.service.PasswordService;
import com.example.Library.entity.User;
import com.example.Library.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    // Обрабатываем GET запрос для отображения формы логина
    @GetMapping
    public String showLoginPage() {
        return "login"; // Возвращаем имя страницы login.html
    }

    // Обрабатываем POST запрос для аутентификации пользователя
    @PostMapping
    public String login(@RequestParam String email, @RequestParam String password) {
        User user = userRepository.findByEmail(email);

        if (user != null && passwordService.matches(password, user.getPassword())) {
            // Логика успешной аутентификации
            return "redirect:/home"; // Пример: редирект на главную страницу после успешного логина
        }

        // Логика при ошибке аутентификации
        return "redirect:/login?error"; // Перенаправляем на логин с ошибкой
    }
}

