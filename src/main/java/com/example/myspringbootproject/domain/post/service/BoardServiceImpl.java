package com.example.myspringbootproject.domain.post.service;

import com.example.myspringbootproject.domain.post.repository.BoardRepository;
import com.example.myspringbootproject.domain.post.dto.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    @Override
    public boolean createPost(Post post) {
        return boardRepository.createPost(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return boardRepository.findAllPosts();
    }

    @Override
    public Post getPostById(int id) {
        return boardRepository.findPostById(id);
    }

    @Override
    public boolean updatePost(Post post) {
        return boardRepository.updatePost(post);
    }
}
