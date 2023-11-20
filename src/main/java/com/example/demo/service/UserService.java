package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    void save(User user);
    User login(User user);
    void deleteById(Integer id);
    User getById(Integer id);
    User getByMail(String mail);
}
