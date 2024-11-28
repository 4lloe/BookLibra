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

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_details")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@DynamicUpdate @DynamicInsert
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name="created_at", nullable=false)
    private Date createdAt;

    @Column(name = "updated_at", nullable=false)
    private Date updatedAt;

    @Column(nullable=false, unique = true,name = "email")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @NotBlank
    private String email;

    @Column(nullable = false)
    @NotBlank
    private String password;

    @Transient //значит что поле временное и не отлетит в базу
    private String confirmPassword;

    @Column(nullable = false)
    private String role = "ROLE_USER";

    @Column(nullable = false)
    private String name;

    @Basic(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "user")
    private List<Book> books;


    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    // Конструктор, который принимает имя, email и зашифрованный пароль
    public User(String name, String email, String encryptedPassword) {
        this.name = name;
        this.email = email;
        this.password = encryptedPassword;
    }

    public User( String email, String encryptedPassword) {
        this.email = email;
        this.password = encryptedPassword;
    }

    /*// Метод для шифрования пароля с помощью BCrypt
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }*/

}
