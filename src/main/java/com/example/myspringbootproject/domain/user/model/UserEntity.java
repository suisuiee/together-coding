package com.example.myspringbootproject.domain.user.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_user")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(name = "role_id", nullable = false)
    private int roleId;

    @Column(name = "grade_id", nullable = false)
    private int gradeId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Builder
    public UserEntity(String email, String password){
        this.email = email;
        this.password = password;
    }

    // TODO:  스프링 시큐리티 UserDetails 구현하기

    /**
     * 권한 반한
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    /**
     * 사용자 id 반환(고유값)
     * @return
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * 계정 만료 여부 반환
     * (true) 만료되지 않았음
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠금 여부 반환
     * (true) 잠금되지 않았음
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 패스워드 만료 여부
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계쩡 사용 여부
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
