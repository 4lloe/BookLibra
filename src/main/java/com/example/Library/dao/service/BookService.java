package com.example.Library.dao.service;

import com.example.Library.entity.Book;
import com.example.Library.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public List<Book> getAll(Sort sort) {
        return bookRepository.findAll(sort);
    }

    public Page<Book> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return bookRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortField)));
    }

    public Book save(Book book) {
        bookRepository.save(book);
        return book;
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public Book get(long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.orElse(null); // Вернет null, если книга не найдена
    }

    public byte[] getContent(long id) {
        return bookRepository.getContent(id);
    }

    @Transactional
    public Optional<Book> getBookByName(String name) {
        return bookRepository.findByName(name);
    }


}
