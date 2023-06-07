package com.example.myspringbootproject.domain.post.service;

import com.example.myspringbootproject.domain.post.dto.PostDTO;

import java.util.List;

public interface BoardService {

    boolean createPost(PostDTO postDTO);

    List<PostDTO> getAllPosts();

    PostDTO getPostById(int id);

    boolean updatePost(PostDTO postDTO);


}
