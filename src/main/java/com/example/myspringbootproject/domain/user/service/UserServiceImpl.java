package com.example.myspringbootproject.domain.user.service;

import com.example.myspringbootproject.domain.user.dto.UserDTO;
import com.example.myspringbootproject.domain.user.model.UserEntity;
import com.example.myspringbootproject.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public int signUp(UserDTO userDTO) {
        String passwordEncode = bCryptPasswordEncoder.encode(userDTO.getPassword());
        UserEntity userEntity = UserDTO.toEntity(userDTO);
        userEntity.setPassword(passwordEncode);
        // TODO : 여기서 유효성 검사 하기 (id, name unique 체크)
        return memberRepository.save(userEntity).getId();
    }

    @Override
    public Optional<UserEntity> findUserById(int id) {
        return memberRepository.findById(id);
    }

}
