package com.example.myspringbootproject.domain.post.service;

import com.example.myspringbootproject.common.exception.UserNotFoundException;
import com.example.myspringbootproject.domain.post.dto.AddPostRequest;
import com.example.myspringbootproject.domain.post.dto.Post;

import java.util.List;

public interface PostService {
    Post createPost(AddPostRequest request) throws UserNotFoundException;
    List<Post> findAll();
    Post findPostById(Long id);
}
