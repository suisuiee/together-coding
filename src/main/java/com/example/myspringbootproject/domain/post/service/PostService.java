package com.example.myspringbootproject.domain.post.service;

import com.example.myspringbootproject.common.exception.UserNotFoundException;
import com.example.myspringbootproject.domain.post.dto.AddPostRequest;
import com.example.myspringbootproject.domain.post.dto.Post;
import com.example.myspringbootproject.domain.post.dto.UpdatePostRequest;

import java.util.List;

public interface PostService {
    Post createPost(AddPostRequest request) throws UserNotFoundException;
    List<Post> findAll();
    Post findPostById(Long id);
    Post updatePostById(Long id, UpdatePostRequest request);
    List<Post> deletePostById(long id);

}
