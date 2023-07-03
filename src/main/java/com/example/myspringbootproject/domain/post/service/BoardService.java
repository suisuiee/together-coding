package com.example.myspringbootproject.domain.post.service;

import com.example.myspringbootproject.domain.post.dto.Post;

import java.util.List;

public interface BoardService {

    boolean createPost(Post post);

    List<Post> getAllPosts();

    Post getPostById(int id);

    boolean updatePost(Post post);


}
