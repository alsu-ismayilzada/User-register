package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")

public class UserController {

    private final UserService userService;
    private final UserImpl userImpl;


    public UserController(UserService userService, UserImpl userImpl) {
        this.userService = userService;
        this.userImpl = userImpl;
    }

    @PostMapping("/save")
    public void save(@RequestBody User user){
        userService.save(user);
    }

    @GetMapping("/{id}")
    User getById(@PathVariable int id) {
        return userImpl.getById(id);
    }
    @GetMapping("/{mail}")
    User getByMail(@PathVariable String mail) {
        return userService.getByMail(mail);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id){
        userService.deleteById(id);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user){
//        User user1 = userService.login(user);
//        return ResponseEntity.ok(user1);
//    }

}
