package com.example.Library.spring.repository;

import com.example.Library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByNameContainingIgnoreCaseOrAuthorFioContainingIgnoreCase(String name, String authorFio, Pageable pageable);

    @Query("select new com.example.Library.entity.Book(b.id, b.name, b.pageCount, b.isbn, b.genre, b.authorFio, b.publisher, b.publishYear, b.description, b.viewCount, b.totalRating, b.totalVoteCount, b.averageRating) from Book b")
    Page<Book> findAllWithoutContent(Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("update Book b set b.content = :content where b.id = :id")
    void updateContent(@Param("content") byte[] content, @Param("id") Long id);

    @Query("SELECT b FROM Book b WHERE b.genre = :genre")
    Page<Book> findByGenre(@Param("genre") String genre, Pageable pageable);

    @Query("SELECT b.content FROM Book b WHERE b.id = :id")
    byte[] getContent(@Param("id") Long id);

    @Query("SELECT new com.example.Library.entity.Book(b.id, b.name, b.pageCount, b.isbn, b.genre, b.authorFio, b.publisher, b.publishYear, b.description, b.viewCount, b.totalRating, b.totalVoteCount, b.averageRating) FROM Book b")
    List<Book> findAllWithoutLargeColumns();

    @Query("SELECT new com.example.Library.entity.Book(b.id, b.name, b.pageCount, b.isbn, b.genre, b.authorFio, b.publisher, b.publishYear, b.description, b.viewCount, b.totalRating, b.totalVoteCount, b.averageRating) FROM Book b")
    List<Book> findAllWithoutLargeColumnsAndImage();

    Optional<Book> findByName(String name);

    @Query("SELECT b.content FROM Book b WHERE b.name = :name")
    Optional<byte[]> getContentByName(@Param("name") String name);
}
