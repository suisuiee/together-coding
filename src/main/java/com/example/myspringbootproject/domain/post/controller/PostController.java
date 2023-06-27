package com.example.myspringbootproject.domain.post.controller;

import com.example.myspringbootproject.common.exception.UserNotFoundException;
import com.example.myspringbootproject.common.response.ApiResponse;
import com.example.myspringbootproject.common.response.ResponseDTO;
import com.example.myspringbootproject.domain.post.dto.AddPostRequest;
import com.example.myspringbootproject.domain.post.dto.Post;
import com.example.myspringbootproject.domain.post.mapper.PostMapper;
import com.example.myspringbootproject.domain.post.model.PostEntity;
import com.example.myspringbootproject.domain.post.service.PostService;
import com.example.myspringbootproject.domain.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    /**
     * 게시글 생성
     *
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody AddPostRequest request) {
        try {
            Post post = postService.createPost(request);
            ApiResponse apiResponse = ApiResponse
                .create(HttpStatus.CREATED, "Post created successfully.", post);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        } catch (UserNotFoundException e) {
            ApiResponse apiResponse = ApiResponse
                .create(HttpStatus.BAD_REQUEST, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    /**
     * 게시글 단건 조회
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") Long id) {
        // ApiResponse apiResponse = new ApiResponse();
        try {
            Post post = postService.findPostById(id);
            ApiResponse apiResponse = ApiResponse
                .create(HttpStatus.OK, "Post find successfully.", post);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    /**
     * 게시글 전체 조회
     *
     * @return
     */
    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts() {
        try {
            List<Post> posts = postService.findAll();

            ApiResponse apiResponse = ApiResponse
                .create(HttpStatus.OK, "ost list received successfully.", posts);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    /**
     * Exception Handler
     * @param e
     * @return
     */
    private ResponseEntity<?> handleException(Exception e) {
        ApiResponse apiResponse = ApiResponse
            .create(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
