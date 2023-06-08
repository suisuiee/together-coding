package com.example.myspringbootproject.domain.user.service;

import com.example.myspringbootproject.domain.user.dto.UserDTO;
import com.example.myspringbootproject.domain.user.model.UserEntity;
import com.example.myspringbootproject.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository memberRepository;


    @Override
    public int signUp(UserDTO userDTO) {
        UserEntity userEntity = UserDTO.toEntity(userDTO);
        // TODO : 여기서 유효성 검사 하기 (id, name unique 체크)
        return memberRepository.save(userEntity).getId();
    }

    @Override
    public Optional<UserEntity> findUserById(int id) {
        return memberRepository.findById(id);
    }
}
