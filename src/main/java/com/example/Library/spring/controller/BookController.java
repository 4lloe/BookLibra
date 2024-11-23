package com.example.Library.spring.controller;

import com.example.Library.entity.Book;
import com.example.Library.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping()
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
}
