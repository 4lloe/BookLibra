package com.example.Library.spring.repository;

import com.example.Library.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Page<Genre> findByNameIgnoreCase(String name, Pageable pageable);


    List<Genre> findByNameIgnoreCaseOrderByName(String name);

    Optional<Genre> findById(Long id);
}
