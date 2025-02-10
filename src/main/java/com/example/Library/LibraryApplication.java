package com.example.Library;

import com.example.Library.dao.service.BookService;
import com.example.Library.entity.Book;
import com.example.Library.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@SpringBootApplication
@EnableAspectJAutoProxy
@RestController
@RequestMapping("application/json")
public class LibraryApplication extends SpringBootServletInitializer {

	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}


	@RequestMapping("create-book")
	public Book createBook() {
		return bookRepository.save(new Book());
	}
}


