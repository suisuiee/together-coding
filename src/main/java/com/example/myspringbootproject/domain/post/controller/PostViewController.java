package com.example.myspringbootproject.domain.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostViewController {
    @RequestMapping("/post/create-post")
    public String createPostForm(){
        return "/post/create-post-form";
    }

    @RequestMapping("/post/post-list")
    public String postList(){
        return "/post/post-list";
    }

    @RequestMapping("/post/{id}")
    public String getPost(){
        return "";
    }
}
