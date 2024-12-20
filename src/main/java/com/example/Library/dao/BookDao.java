package com.example.Library.dao;

import com.example.Library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BookDao extends GeneralDao<Book>{

    List<Book> findTopBooks(int limit);

    byte[] getContent(long id);

    Page<Book> findByGenre(int pageNumber, int pageSize,String sortField, Sort.Direction sortDirection,long genreId);
}
