package com.example.Library.spring.repository;

import com.example.Library.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByFioContainingIgnoreCaseOrderByFio(String fio);

    Page<Author> findByFioContainingIgnoreCase(String fio, Pageable pageable);

    Author findById(long id);
}