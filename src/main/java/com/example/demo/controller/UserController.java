package com.example.demo.controller;

import com.example.demo.service.MailSenderService;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetailsService;



@RestController
@RequestMapping("/api/user")

public class UserController {

    private final UserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;
    private final MailSenderService mailSenderService;


    public UserController(UserDetailsService userDetailsService, MailSenderService mailSenderService) {
        this.userDetailsService = userDetailsService;

        this.mailSenderService = mailSenderService;
    }

    @PostMapping("/save")
    public void save(@RequestBody User  user){
        userRepository.save(user);
    }

    @GetMapping("/{id}")
    User getById(@PathVariable int id) {
        return userRepository.getById(id);
    }
    @GetMapping("/mail/{mail}")
    User getByMail(@PathVariable String mail) {
        return userRepository.getByMail(mail);
    }
    @GetMapping("/name/{name}")
    User getByName(@PathVariable String name) {
        return userRepository.getByName(name);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id){
        userRepository.deleteById(id);
    }




}
