package com.example.Library.spring.controller;

import com.example.Library.dao.service.BookService;
import com.example.Library.entity.Book;
import com.google.common.net.HttpHeaders;
import lombok.extern.java.Log;
import com.example.Library.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Base64;
import java.util.List;
import java.util.Optional;



@Controller
@Log
public class RedirectController {
    @Autowired
    private BookRepository bookRepository;

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

    /*@GetMapping("/read/{name}")
    public String getContentAsTextByName(@PathVariable("name") String name, Model model) {
        try {
            byte[] content = bookRepository.getContentByName(name);

            if (content != null) {
                String decodedContent = new String(content, java.nio.charset.StandardCharsets.UTF_8);

                model.addAttribute("content", decodedContent);
                return "read-book";
            } else {
                model.addAttribute("errorMessage", "Content not found for the book");
                return "errorPage";
            }
        } catch (Exception e) {
            log.severe("Error retrieving content: " + e.getMessage());
            model.addAttribute("errorMessage", "An error occurred while retrieving the content");
            return "errorPage";
        }
    }*/
    @GetMapping("/read/{name}")
    public String getContentAsTextByName(@PathVariable("name") String name, Model model) {
        try {
            Optional<byte[]> optionalContent = bookRepository.getContentByName(name);

            if (optionalContent.isPresent()) {
                byte[] content = optionalContent.get();
                String decodedContent = new String(content, java.nio.charset.StandardCharsets.UTF_8);
                model.addAttribute("content", decodedContent);
            } else {
                model.addAttribute("content", "Content not found for this book.");
                model.addAttribute("name", name);
            }

            return "read-book"; // Название шаблона страницы
        } catch (Exception e) {
            log.severe("Error retrieving content: " + e.getMessage());
            model.addAttribute("errorMessage", "An error occurred while retrieving the content.");
            return "errorPage";
        }
    }



}


