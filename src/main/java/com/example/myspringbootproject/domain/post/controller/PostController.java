package com.example.myspringbootproject.domain.post.controller;

import com.example.myspringbootproject.common.exception.UserNotFoundException;
import com.example.myspringbootproject.common.response.ApiResponse;
import com.example.myspringbootproject.domain.post.dto.AddPostRequest;
import com.example.myspringbootproject.domain.post.dto.Post;
import com.example.myspringbootproject.domain.post.dto.UpdatePostRequest;
import com.example.myspringbootproject.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                .create(HttpStatus.OK, "Post list received successfully.", posts);

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    /**
     * 포스트 업데이트
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(
        @PathVariable("id") Long id, @RequestBody UpdatePostRequest request) {
        try {
            Post post = postService.updatePostById(id, request);
            ApiResponse apiResponse = ApiResponse
                .create(HttpStatus.OK, "Post update successfully.", post);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id){
        try{
            List<Post> posts = postService.deletePostById(id);ApiResponse apiResponse = ApiResponse
                .create(HttpStatus.OK, "Post delete successfully.", posts);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (Exception e){
            return handleException(e);
        }
    }


    /**
     * Exception Handler
     *
     * @param e
     * @return
     */
    private ResponseEntity<?> handleException(Exception e) {
        ApiResponse apiResponse = ApiResponse
            .create(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
