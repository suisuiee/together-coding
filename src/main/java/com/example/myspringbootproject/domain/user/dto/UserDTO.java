package com.example.myspringbootproject.domain.user.dto;

import com.example.myspringbootproject.domain.user.model.UserEntity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private Date birthDate;
    private String profileUrl;
    private int roleId;
    private int gradeId;
    private Date createAt;
    private Date updateAt;
}
