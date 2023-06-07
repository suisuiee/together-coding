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
    private String isVisible;
    private int ctgId;
    private int userId;
    private Date createdAt;
    private Date updatedAt;
}
