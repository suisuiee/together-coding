package com.example.myspringbootproject.domain.user.mapper;

import com.example.myspringbootproject.domain.user.dto.UserDTO;
import com.example.myspringbootproject.domain.user.model.UserEntity;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    @DisplayName("User : DTO -> Entity")
    public void toEntityTest() {
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
        UserEntity userEntity = UserMapper.INSTANCE.toEntity(userDTO);

        // then
        assertEquals(userEntity.getId(), userDTO.getId());
        assertEquals(userEntity.getName(), userDTO.getName());
        assertEquals(userEntity.getEmail(), userDTO.getEmail());
        assertEquals(userEntity.getPhone(), userDTO.getPhone());
        assertEquals(userEntity.getRoleId(), userDTO.getRoleId());
        assertEquals(userEntity.getGradeId(), userDTO.getGradeId());
    }

    @Test
    @DisplayName("User : Entity -> DTO")
    public void toDtoTest() {
        // given
        UserEntity userEntity = UserEntity.builder()
            .name("김테스트")
            .email("email@email.com")
            .phone("01011111111")
            .password("12134")
            .roleId(10)
            .gradeId(20)
            .build();

        // when
        UserDTO userDTO = UserMapper.INSTANCE.toDTO(userEntity);

        // then
        assertEquals(userEntity.getId(), userDTO.getId());
        assertEquals(userEntity.getName(), userDTO.getName());
        assertEquals(userEntity.getEmail(), userDTO.getEmail());
        assertEquals(userEntity.getPhone(), userDTO.getPhone());
        assertEquals(userEntity.getRoleId(), userDTO.getRoleId());
        assertEquals(userEntity.getGradeId(), userDTO.getGradeId());

    }


}