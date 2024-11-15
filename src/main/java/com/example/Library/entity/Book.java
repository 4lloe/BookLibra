package com.example.Library.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@EqualsAndHashCode(of="id")
@Table(catalog="Library")
@DynamicUpdate
@DynamicInsert
@Entity
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    public Book(Long id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    @Column( nullable = false)
    private byte[] content;

    @Column(name = "page_count")
    private Integer pageCount;

    private String isbn;

    @ManyToOne
    @JoinColumn
    private Genre genre;

    @ManyToOne
    @JoinColumn
    private Author author;

    @ManyToOne
    @JoinColumn
    private Publisher publisher;

    @Column(name = "publish_year")
    private Integer publishYear;

    private byte[] image;

    private String description;

    @Column(name = "view_count")
    private long viewCount;

    @Column(name = "total_rating")
    private long totalRating;

    @Column(name="total_vote_count")
    private long totalVoteCount;

    @Column(name="average_rating")
    private double averageRating;

    public Book(Long id, String name, Integer pageCount, String isbn, Genre genre, Author author, Publisher publisher,
                Integer publishYear, byte[] image, String description, long viewCount, long totalRating,
                long totalVoteCount, double averageRating) {
        this.id = id;
        this.name = name;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.image = image;
        this.description = description;
        this.viewCount = viewCount;
        this.totalRating = totalRating;
        this.totalVoteCount = totalVoteCount;
        this.averageRating = averageRating;
    }


    @Override
    public String toString() {return name;}
}

