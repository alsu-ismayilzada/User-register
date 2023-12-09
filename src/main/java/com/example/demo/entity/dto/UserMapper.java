package com.example.demo.entity.dto;

import com.example.demo.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {

    User toUserEntity(UserCreateDto userCreateDto);
}
