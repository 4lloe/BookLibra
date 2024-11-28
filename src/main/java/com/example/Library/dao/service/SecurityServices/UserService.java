package com.example.Library.dao.service.SecurityServices;

import com.example.Library.dao.UserDao;
import com.example.Library.entity.User;
import com.example.Library.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDao {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> search(String... searchString) {
        return userRepository.findByEmailContainingIgnoreCase(searchString[0]);
    }

    @Override
    public User get(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll(Sort sort) {
        return userRepository.findAll(sort);
    }

    @Override
    public Page<User> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortField);
        return userRepository.findAll(PageRequest.of(pageNumber, pageSize, sort));
    }

    @Override
    public Page<User> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        Sort sort = Sort.by(sortDirection, sortField);
        return userRepository.findByEmailContainingIgnoreCase(searchString[0], PageRequest.of(pageNumber, pageSize, sort));
    }

    @Override
    public User findByEmail(String Email) {
        return userRepository.findByEmail(Email);
    }



    /*public void registerUser(String email, String rawPassword) {
        // Check if the user already exists
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        // Encrypt the password
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Create a new user and save to the database
        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }*/
}

