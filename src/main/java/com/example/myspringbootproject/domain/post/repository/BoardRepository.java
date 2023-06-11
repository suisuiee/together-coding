package com.example.myspringbootproject.domain.post.repository;


import com.example.myspringbootproject.domain.post.dto.PostDTO;

import java.util.List;

public interface BoardRepository {
    boolean createPost(PostDTO postDTO);

    List<PostDTO> findAllPosts();

    PostDTO findPostById(int id);

    boolean updatePost(PostDTO postDTO);



}
