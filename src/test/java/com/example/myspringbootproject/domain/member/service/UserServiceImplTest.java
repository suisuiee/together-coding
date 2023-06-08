package com.example.myspringbootproject.domain.member.service;

import com.example.myspringbootproject.SpringConfig;
import com.example.myspringbootproject.domain.user.dto.UserDTO;
import com.example.myspringbootproject.domain.user.model.UserEntity;
import com.example.myspringbootproject.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;


    @Test
    @DisplayName("회원 가입 서비스 테스트")
    public void signUpUserTest() {
        // given
        UserDTO userDTO = UserDTO.builder()
            .name("김테스트")
            .email("email@email.com")
            .phone("01011111111")
            .password("12134")
            .roleId(10)
            .gradeId(20)
            .build();

        System.out.println(userDTO);

        // when
        int createUserId = userService.signUp(userDTO);
        Optional<UserEntity> user = userService.findUserById(createUserId);

        // then
        assertThat(user).isNotEmpty();
        assertThat(user.get().getName()).isEqualTo("김테스트");

    }

}