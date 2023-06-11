package com.example.myspringbootproject.domain.user.dto;

import com.example.myspringbootproject.domain.user.model.UserEntity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
    private int id;
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

    @Builder
    public UserDTO(int id, String name, String password, String email, String phone, Date birthDate, String profileUrl, int roleId, int gradeId,
                   Date createAt, Date updateAt) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.profileUrl = profileUrl;
        this.roleId = roleId;
        this.gradeId = gradeId;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    // Entity -> DTO 변환
    public UserDTO(final UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.password = userEntity.getPassword();
        this.email = userEntity.getEmail();
        this.phone = userEntity.getPhone();
        this.birthDate = userEntity.getBirthDate();
        this.profileUrl = userEntity.getProfileUrl();
        this.roleId = userEntity.getRoleId();
        this.gradeId = userEntity.getGradeId();
        this.createAt = userEntity.getCreatedAt();
        this.updateAt = userEntity.getUpdatedAt();
    }

    // DTO -> Entity 변환
    public static UserEntity toEntity(final UserDTO dto) {
        return UserEntity.builder()
            .id(dto.getId())
            .name(dto.getName())
            .password(dto.getPassword())
            .email(dto.getEmail())
            .phone(dto.getPhone())
            .birthDate(dto.getBirthDate())
            .profileUrl(dto.getProfileUrl())
            .roleId(dto.roleId)
            .gradeId(dto.gradeId)
            .createdAt(dto.createAt)
            .updatedAt(dto.updateAt)
            .build();
    }
}
