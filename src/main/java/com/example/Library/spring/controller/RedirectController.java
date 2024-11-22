package com.example.Library.spring.controller;

import com.example.Library.dao.service.BookService;
import com.example.Library.entity.Book;
import com.google.common.net.HttpHeaders;
import lombok.extern.java.Log;
import com.example.Library.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;



@Controller
@Log
public class RedirectController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title", "Home page");
        return "home";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        try {
            List<Book> books = bookRepository.findAllWithoutLargeColumns();
            model.addAttribute("books", books);
            return "books";
        } catch (Exception e) {
            log.severe("Book retrieval error: " + e.getMessage());
            model.addAttribute("errorMessage", "An error occurred while retrieving the book");
            return "errorPage";
        }
    }


    @GetMapping("/book/{name}")
    public String getBookByName(@PathVariable("name") String name, Model model) {
        try {
            Optional<Book> bookOptional = bookRepository.findByName(name);

            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();

                if (book.getId() != null) {
                    model.addAttribute("book", book);
                    return "book-details";
                } else {
                    model.addAttribute("errorMessage", "Invalid book data");
                    return "errorPage";
                }
            } else {
                model.addAttribute("errorMessage", "Book not found");
                return "errorPage";
            }
        } catch (Exception e) {
            log.severe("Error retrieving book: " + e.getMessage());
            model.addAttribute("errorMessage", "An error occurred while retrieving the book");
            return "errorPage";
        }
    }

}


