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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        List<Book> books = bookRepository.findAll();

        for (Book book : books) {
            if (book.getImage() != null) {

                String mimeType = "image/jpg";
                String encodedImage = Base64.getEncoder().encodeToString(book.getImage());
                model.addAttribute("image_" + book.getId(), "data:" + mimeType + ";base64," + encodedImage);
            }
        }

        model.addAttribute("books", books);

        return "books";
    }

    @GetMapping("/book/{name}")
    public String getBookByName(@PathVariable("name") String name, Model model) {
        Optional<Book> bookOptional = bookRepository.findByName(name);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            model.addAttribute("book", book);
            return "book-details"; // имя шаблона
        } else {
            return "errorPage"; // страница с ошибкой, если книга не найдена
        }
    }

    /*@GetMapping("/read/{id}")
    public ResponseEntity<String> getBookContent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        String content = BookService.getBookContentAsText(id);

        if (content == null || content.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Книга не найдена или не содержит текста");
            return ResponseEntity.status(302)
                    .header(HttpHeaders.LOCATION, "/error")
                    .build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "text/plain")
                .body(content);
    }*/



}
