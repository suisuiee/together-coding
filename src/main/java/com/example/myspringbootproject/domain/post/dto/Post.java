package com.example.myspringbootproject.domain.post.dto;

import com.example.myspringbootproject.domain.post.model.PostEntity;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Post {
    private long id;
    private String title;
    private String content;
    private String attachment;
    private int isVisible; // 게시물이 보이는지 여부에 대한 값
    private int ctgId; // 카테고리
    private long userId;
    private Date createdAt;
    private Date updatedAt;

    public Post(PostEntity postEntity) {
        this.id = postEntity.getId();
        this.title = postEntity.getTitle();
        this.content = postEntity.getContent();
        this.attachment = postEntity.getAttachment();
        this.isVisible = postEntity.getIsVisible();
        this.ctgId = postEntity.getCtgId();
        this.userId = postEntity.getUserEntity().getId();
        this.createdAt = postEntity.getCreatedAt();
        this.updatedAt = postEntity.getUpdatedAt();
    }
}
