package com.example.Library.spring.repository;

import com.example.Library.entity.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    List<Publisher> findByNameIgnoreCaseOrderByName(String publisherName);
    Page<Publisher> findByNameIgnoreCase(String publisherName, Pageable pageable);

    Optional<Publisher> findById(Long id);

}
