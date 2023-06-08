package com.example.myspringbootproject.domain.user.service;

import com.example.myspringbootproject.domain.user.dto.UserDTO;
import com.example.myspringbootproject.domain.user.model.UserEntity;

import java.util.Optional;

public interface UserService {
    int signUp(UserDTO userDTO);
    Optional<UserEntity> findUserById(int id);
}
