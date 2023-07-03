package com.example.myspringbootproject.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class AddPostRequest {
    private String title;
    private String content;
    private String attachment = null;
    private int ctgId; // 카테고리 id
    private long userId;
}
