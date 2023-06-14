package com.example.myspringbootproject.domain.post.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDTO {
    private long id;
    private String title;
    private String content;
    private String attachment;
    private int isVisible; // 게시물이 보이는지 여부에 대한 값
    private int ctgId; // 카테고리
    private long userId;
    private Date createdAt;
    private Date updatedAt;
}
