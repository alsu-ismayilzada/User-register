package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserCreateDto;
import com.example.demo.entity.dto.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void save(UserCreateDto userCreateDto) {

        User user = userMapper.toUserEntity(userCreateDto);
        userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getById(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByMail(String mail) {

        return userRepository.getByMail(mail);
    }

    @Override
    public User getByName(String name) {
        return userRepository.getByName(name);
    }


}
