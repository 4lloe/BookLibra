package com.example.Library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@DynamicUpdate @DynamicInsert
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name="created_at", nullable=false)
    private Date createdAt;

    @Column(name = "updated_at", nullable=false)
    private Date updatedAt;

    @Column(nullable=false,unique = true)

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @NotBlank
    private String email;

    @Column(nullable = false)
    @NotBlank
    private String password;

    @Basic(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "user")
    private List<Book> books;

}
