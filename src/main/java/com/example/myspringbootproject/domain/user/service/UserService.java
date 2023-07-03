package com.example.myspringbootproject.domain.user.service;

import com.example.myspringbootproject.domain.user.dto.UserDTO;
import com.example.myspringbootproject.domain.user.model.UserEntity;

import java.util.Optional;

public interface UserService{
    long signUp(UserDTO userDTO);
    Optional<UserEntity> findUserById(long id);
    Optional<UserEntity> findUserByEmail(String email);
}
