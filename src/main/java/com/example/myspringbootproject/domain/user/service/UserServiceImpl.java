package com.example.myspringbootproject.domain.user.service;

import com.example.myspringbootproject.domain.user.dto.UserDTO;
import com.example.myspringbootproject.domain.user.mapper.UserMapper;
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


    /**
     * 회원가입
     * @param userDTO
     * @return
     */
    @Override
    public long signUp(UserDTO userDTO) {
        String passwordEncode = bCryptPasswordEncoder.encode(userDTO.getPassword());
        UserEntity userEntity = UserMapper.INSTANCE.toEntity(userDTO);
        userEntity.setPassword(passwordEncode);
        // TODO : 여기서 유효성 검사 하기 (id, name unique 체크)
        return memberRepository.save(userEntity).getId();
    }

    @Override
    public Optional<UserEntity> findUserById(long id) {
        return memberRepository.findById(id);
    }


    /**
     * email 로 회원 찾기
     * @param email
     * @return
     */
    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

}
