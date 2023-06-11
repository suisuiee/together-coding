package com.example.myspringbootproject.domain.post.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDTO {
    private int id;
    private String title;
    private String content;
    private String attachment;
    private String isVisible; // 게시물이 보이는지 여부에 대한 값
    private int ctgId; // 카테고리
    private int userId;
    private Date createdAt;
    private Date updatedAt;
}
