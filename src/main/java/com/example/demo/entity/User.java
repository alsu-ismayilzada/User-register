package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String mail;
    String password;
    Boolean isActive;

    public User(String name, String mail, String password, boolean isActive) {
        this.name= name;
        this.mail=mail;
        this.password=password;
        this.isActive=isActive;
    }

}
