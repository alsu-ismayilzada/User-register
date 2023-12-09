package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserActivation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserActivationRepository {
    void add(UserActivation user);
    void deleteById(Integer id);
    UserActivation getById(Integer id);
    UserActivation getByName(String name);
}
