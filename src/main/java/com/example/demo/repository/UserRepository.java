package com.example.demo.repository;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository  {

    void save(User user);
    User login(User user);
    void deleteById(Integer id);
    User getById(Integer id);
    User getByMail(String mail);
}
