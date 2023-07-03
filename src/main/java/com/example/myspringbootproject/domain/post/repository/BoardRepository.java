package com.example.myspringbootproject.domain.post.repository;


import com.example.myspringbootproject.domain.post.dto.Post;

import java.util.List;

public interface BoardRepository {
    boolean createPost(Post post);

    List<Post> findAllPosts();

    Post findPostById(int id);

    boolean updatePost(Post post);



}
