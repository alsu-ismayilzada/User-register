package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserCreateDto;

public interface UserService {

    void save(UserCreateDto  user);
    void deleteById(Integer id);
    User getById(Integer id);
    User getByMail(String mail);
    User getByName(String name);

}
