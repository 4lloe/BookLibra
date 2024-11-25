package com.example.Library.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Entity
@Table(name="vote", catalog="Library")
@EqualsAndHashCode(of = "id")
@DynamicUpdate @DynamicInsert
@Setter @Getter
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String value;

    @Column(name="book_id")
    private Long book_id;

    private String username;
}
