package com.example.myspringbootproject.domain.post.model;

import com.example.myspringbootproject.domain.post.dto.Post;
import com.example.myspringbootproject.domain.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;


@Getter @Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private String attachment;

    @Column(name = "is_visible",  nullable = false)
    private int isVisible; // 게시물이 보이는지 여부에 대한 값

    @Column(name = "ctg_id", nullable = false)
    private int ctgId; // 카테고리

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    private UserEntity userEntity;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    // == 연관관계 메서드
    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
        userEntity.getPosts().add(this);
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }
}