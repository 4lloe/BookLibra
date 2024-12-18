package com.example.Library.spring.controller;

import com.example.Library.entity.Author;
import com.example.Library.entity.Book;
import com.example.Library.spring.repository.AuthorRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import com.example.Library.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//@RequestMapping("BookLibra/")
//RestController
@Controller
@Log
@AllArgsConstructor
public class RedirectController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title", "Home page");
        return "home";
    }



}
