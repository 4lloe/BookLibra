package com.example.Library.dao.service;

import com.example.Library.dao.BookDao;
import com.example.Library.entity.Book;
import com.example.Library.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAll() {return bookRepository.findAll();}

    @Override
    public List<Book> getAll(Sort sort) {return bookRepository.findAll(sort);}

    @Override
    public Page<Book> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortField);
        return bookRepository.findAll(PageRequest.of(pageNumber, pageSize, sort));
    }

    @Override
    public List<Book> search(String... searchString){return null;}

    @Override
    public Page<Book> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        if (searchString == null || searchString.length == 0) {
            return Page.empty();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortField));

        return bookRepository.findByNameContainingIgnoreCaseOrAuthorFioContainingIgnoreCase(searchString[0], searchString[0], pageRequest);
    }

    @Override
    public Book save(Book book) {
        bookRepository.save(book);
        if(book.getContent()!=null){
            bookRepository.updateContent(book.getContent(),book.getId());
        }
        return book;
    }

    @Override
    public void delete(Book book) {bookRepository.delete(book);}

    @Override
    public Book get(long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.orElse(null); // Вернет null, если книга не найдена
    }

    @Override
    public byte[] getContent(long id) { return bookRepository.getContent(id);}

    public List<Book> findTopBooks(int limit) {
        return bookRepository.findTopBooks(PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "viewCount")));
    }

    @Override
    public Page<Book> findByGenre(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, long genreId) {
        return bookRepository.findByGenre(genreId, PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortField)));
    }
}
