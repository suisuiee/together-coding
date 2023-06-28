package com.example.myspringbootproject.domain.post.service;

import com.example.myspringbootproject.common.exception.UserNotFoundException;
import com.example.myspringbootproject.domain.post.dto.AddPostRequest;
import com.example.myspringbootproject.domain.post.dto.Post;
import com.example.myspringbootproject.domain.post.dto.UpdatePostRequest;
import com.example.myspringbootproject.domain.post.mapper.PostMapper;
import com.example.myspringbootproject.domain.post.model.PostEntity;
import com.example.myspringbootproject.domain.post.repository.PostRepository;
import com.example.myspringbootproject.domain.user.mapper.UserMapper;
import com.example.myspringbootproject.domain.user.model.UserEntity;
import com.example.myspringbootproject.domain.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    /**
     * 게시글 저장
     *
     * @param request
     * @return
     */
    @Transactional
    @Override
    public Post createPost(AddPostRequest request) throws UserNotFoundException {

        Optional<UserEntity> findUserById = userService.findUserById(request.getUserId());

        if (!findUserById.isPresent()) {
            throw new UserNotFoundException("유저가 없습니다.");
        }

        PostEntity createPostEntity = PostMapper.INSTANCE.toEntity(request);
        createPostEntity.setUser(findUserById.get());


        PostEntity savedPostEntity = postRepository.save(createPostEntity);


        Post post = PostMapper.INSTANCE.toDTO(savedPostEntity);

        return post;
    }

    /**
     * 게시글 목록 받아오기
     * TODO : 글 갯수 제한 해야함
     *
     * @return
     */
    @Override
    public List<Post> findAll() {
        // Entity -> DTO 변환
        List<Post> posts = postRepository.findAll()
            .stream()
            .map(PostMapper.INSTANCE::toDTO)
            .collect(Collectors.toList());

        return posts;
    }

    /**
     * 게시글 단건(id)으로 받아오기
     * @param id
     * @return
     */
    @Override
    public Post findPostById(Long id) {
        PostEntity postEntity = postRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Post with id " + id + " not found"));

        Post post = PostMapper.INSTANCE.toDTO(postEntity);
        return post;
    }

    @Override
    @Transactional
    public Post updatePost(Long id, UpdatePostRequest request) {
        PostEntity postEntity = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("post not found " + id));

        // Transactional 에 의해 commit 됨
        postEntity.update(request);

        // Entity -> DTO
        Post post = PostMapper.INSTANCE.toDTO(postEntity);
        return post;
    }
}
