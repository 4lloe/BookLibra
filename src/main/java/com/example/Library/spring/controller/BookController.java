package com.example.Library.spring.controller;

import com.example.Library.entity.Book;
import com.example.Library.spring.repository.BookRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@Log
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/read/{name}")
    public ResponseEntity<byte[]> getPdf(@PathVariable String name) {
        Optional<Book> book = bookRepository.findByName(name);
        if (book.isPresent()) {
            byte[] pdfData = book.get().getContent();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=book.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfData);
        } else {
            return ResponseEntity.notFound().build();
        }
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