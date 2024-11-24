package com.example.Library.entity;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.sql.Types;

@EqualsAndHashCode(of="id")
@Table(catalog="Library")
@DynamicUpdate
@DynamicInsert
@Entity
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JdbcTypeCode(Types.VARBINARY)
    @Column( nullable = false)
    private byte[] content;

    @Column(name = "page_count")
    private Integer pageCount;

    private String isbn;

    @ManyToOne
    @JoinColumn
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author authorFio;

    @ManyToOne
    @JoinColumn
    private Publisher publisher;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column(name = "publish_year")
    private Integer publishYear;

    private String description;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "total_rating")
    private Long totalRating;

    @Column(name="total_vote_count")
    private Long totalVoteCount;

    @Column(name="average_rating")
    private double averageRating;


    public Book(Long id, String name, Integer pageCount, String isbn, Genre genre, Author authorFio, Publisher publisher,
                Integer publishYear, String description, long viewCount, long totalRating,
                long totalVoteCount, double averageRating) {
        this.id = id;
        this.name = name;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.genre = genre;
        this.authorFio = authorFio;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.description = description;
        this.viewCount = viewCount;
        this.totalRating = totalRating;
        this.totalVoteCount = totalVoteCount;
        this.averageRating = averageRating;
    }


    @Override
    public String toString() {return name;}


}

