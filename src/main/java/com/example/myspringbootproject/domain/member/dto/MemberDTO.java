package com.example.myspringbootproject.domain.member.dto;

import com.example.myspringbootproject.domain.member.model.MemberEntity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
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

    // Entity -> DTO 변환
    public MemberDTO(final MemberEntity memberEntity) {
        this.id = memberEntity.getId();
        this.name = memberEntity.getName();
        this.password = memberEntity.getPassword();
        this.email = memberEntity.getEmail();
        this.phone = memberEntity.getPhone();
        this.birthDate = memberEntity.getBirthDate();
        this.profileUrl = memberEntity.getProfileUrl();
        this.roleId = memberEntity.getRoleId();
        this.gradeId = memberEntity.getGradeId();
        this.createAt = memberEntity.getCreatedAt();
        this.updateAt = memberEntity.getUpdatedAt();
    }

    // DTO -> Entity 변환
    public static MemberEntity toEntity(final MemberDTO dto){
        return MemberEntity.builder()
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
