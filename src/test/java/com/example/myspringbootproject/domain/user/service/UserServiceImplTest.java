package com.example.myspringbootproject.domain.user.service;

import com.example.myspringbootproject.domain.user.dto.UserDTO;
import com.example.myspringbootproject.domain.user.model.UserEntity;
import com.example.myspringbootproject.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// @SpringBootTest(properties = "spring.config.location=classpath:application-starter.yml")
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        userRepository.deleteAll();
    }


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

        // when
        long createUserId = userService.signUp(userDTO);
        Optional<UserEntity> user = userService.findUserById(createUserId);

        System.out.println("user = " + user);

        // then
        assertThat(user).isNotEmpty();
        assertThat(user.get().getName()).isEqualTo("김테스트");
    }

    @Test
    @DisplayName("id 로 User 찾기 테스트")
    public void findUserByIdTest() {
        // given
        UserDTO userDTO = UserDTO.builder()
            .name("김테스트")
            .email("email@email.com")
            .phone("01011111111")
            .password("12134")
            .roleId(10)
            .gradeId(20)
            .build();

        long createUserId = userService.signUp(userDTO);

        // when
        Optional<UserEntity> userById1 = userService.findUserById(createUserId);
        Optional<UserEntity> userById2 = userService.findUserById(100);

        // then
        assertThat(userById1).isNotEmpty();
        assertThat(userById2).isEmpty();
    }

    @Test
    @DisplayName("email 로 User 찾기 테스트")
    public void findUserByEmailTest() {
        // given
        UserDTO userDTO = UserDTO.builder()
            .name("김테스트")
            .email("email@email.com")
            .phone("01011111111")
            .password("12134")
            .roleId(10)
            .gradeId(20)
            .build();

        userService.signUp(userDTO);

        // when
        String email1 = "email@email.com";
        String email2 = "a@b.c";

        // when
        Optional<UserEntity> userByEmail1 = userService.findUserByEmail(email1);
        Optional<UserEntity> userByEmail2 = userService.findUserByEmail(email2);

        // then
        assertThat(userByEmail1).isNotEmpty();
        assertThat(userByEmail2).isEmpty();
    }

}
