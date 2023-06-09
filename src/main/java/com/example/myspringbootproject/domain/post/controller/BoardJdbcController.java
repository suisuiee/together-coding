package com.example.myspringbootproject.domain.post.controller;

import com.example.myspringbootproject.common.response.ResponseDTO;
import com.example.myspringbootproject.domain.post.dto.Post;
import com.example.myspringbootproject.domain.post.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boardJdbc")
public class BoardJdbcController {
    private final BoardService boardService;

    @GetMapping("/test")
    public String testController() {
        return "TEST!";
    }

    @PostMapping("/post")
    @ResponseBody
    public ResponseEntity<?> createPost(@RequestBody Post post){
        List<Boolean> postsList = new ArrayList<>();
        postsList.add(boardService.createPost(post));
        ResponseDTO<Boolean> response = ResponseDTO.<Boolean>builder().data(postsList).build();
        return ResponseEntity.ok().body(response);
    }

    /**
     * 전체 글 받아오기
     * @return
     */
    @GetMapping(value = "/posts")
    @ResponseBody
    public ResponseEntity<?> getAllPostList() {

        // postsList 를 가져온다.
        List<Post> postsList = boardService.getAllPosts();
        ResponseDTO<Post> response = ResponseDTO.<Post>builder().data(postsList).build();
        return ResponseEntity.ok().body(response);
    }

    /**
     * post의 id 로 글 받아오기
     * @param id
     * @return
     */
    @GetMapping(value = "/post")
    @ResponseBody
    public ResponseEntity<?> getPostById(@RequestParam(required = false) int id ) {
        List<Post> postsList = new ArrayList<>();
        postsList.add(boardService.getPostById(id));

        ResponseDTO<Post> response = ResponseDTO
            .<Post>builder()
            .data(postsList)
            .build();
        return ResponseEntity.ok().body(response);
    }

    /**
     * post update
     * @param post
     * @return
     */
    @PutMapping(value = "/post")
    @ResponseBody
    public ResponseEntity<?> updatePostById(@RequestBody Post post) {
        List<Boolean> postsList = new ArrayList<>();
        postsList.add(boardService.updatePost(post));

        ResponseDTO<Boolean> response = ResponseDTO.<Boolean>builder().data(postsList).build();
        return ResponseEntity.ok().body(response);
    }
}
