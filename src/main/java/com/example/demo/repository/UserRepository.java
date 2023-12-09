package com.example.demo.repository;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserCreateDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface UserRepository {

    void save(User user);
    void activateByUsername(String username);
    void deleteById(Integer id);
    User getById(Integer id);
    User getByMail(String mail);
    User getByName(String name);

 }
