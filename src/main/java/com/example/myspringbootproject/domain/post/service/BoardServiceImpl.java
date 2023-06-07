package com.example.myspringbootproject.domain.post.service;

import com.example.myspringbootproject.domain.post.repository.BoardRepository;
import com.example.myspringbootproject.domain.post.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    @Override
    public boolean createPost(PostDTO postDTO) {
        return boardRepository.createPost(postDTO);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return boardRepository.getAllPosts();
    }

    @Override
    public PostDTO getPostById(int id) {
        return boardRepository.getPostById(id);
    }

    @Override
    public boolean updatePost(PostDTO postDTO) {
        return boardRepository.updatePost(postDTO);
    }
}
