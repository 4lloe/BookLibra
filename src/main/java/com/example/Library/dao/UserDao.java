package com.example.Library.dao;

import com.example.Library.entity.Book;
import com.example.Library.entity.User;

public interface UserDao extends GeneralDao<User>{
    User findByEmail(String username);
}
