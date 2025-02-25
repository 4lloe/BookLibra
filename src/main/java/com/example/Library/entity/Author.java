package com.example.Library.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="author",catalog = "Library")
@EqualsAndHashCode(of="id")
@Getter @Setter
@DynamicUpdate
@DynamicInsert
public class Author {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String fio;

    private Date birthday;

    //@Basic(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "authorFio")
    private List<Book> books;

    @Override
    public String toString() {return fio;}

}
