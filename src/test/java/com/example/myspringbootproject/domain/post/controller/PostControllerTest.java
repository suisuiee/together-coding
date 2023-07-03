package com.example.myspringbootproject.domain.post.controller;

import com.example.myspringbootproject.common.exception.UserNotFoundException;
import com.example.myspringbootproject.domain.post.dto.AddPostRequest;
import com.example.myspringbootproject.domain.post.dto.Post;
import com.example.myspringbootproject.domain.post.dto.UpdatePostRequest;
import com.example.myspringbootproject.domain.post.model.PostEntity;
import com.example.myspringbootproject.domain.post.repository.PostRepository;
import com.example.myspringbootproject.domain.post.service.PostService;
import com.example.myspringbootproject.domain.user.dto.UserDTO;
import com.example.myspringbootproject.domain.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; // 직렬화, 역직렬화를 위한 클래스

    @Autowired
    private WebApplicationContext context;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        postRepository.deleteAll();
    }

    @Test
    public void 포스트_생성_컨트롤러_테스트() throws Exception {
        //given
        String url = "/api/post";
        String title = "title";
        String content = "content";

        long userId = getUserEntity();

        AddPostRequest userRequest = new AddPostRequest();
        userRequest.setUserId(userId);
        userRequest.setTitle(title);
        userRequest.setContent(content);
        userRequest.setCtgId(10);

        // 객체 JSON 으로 직렬화
        String requestBody = objectMapper.writeValueAsString(userRequest);

        //when
        // 설정한 내용 바탕으로 요청 전송
        ResultActions resultActions = mockMvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody));


        //then
        System.out.println("resultActions = " + resultActions.andReturn().getResponse().getContentAsString());
        resultActions
            .andExpect(status().isCreated());
    }

    @Test
    public void 포스트_findById_컨트롤러_테스트() throws Exception, UserNotFoundException {
        //given
        String url = "/api/post/{id}";
        String title = "title";
        String content = "content";

        long userId = getUserEntity();

        Post postTest1 = createPostTest(title, content, 10, userId);

        //when
        // 설정한 내용 바탕으로 요청 전송
        ResultActions resultActions = mockMvc.perform(get(url, postTest1.getId()));

        //then
        System.out.println("resultActions = " +
            resultActions.andReturn().getResponse().getContentAsString());

        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.content").value(content))
            .andExpect(jsonPath("$.data.title").value(title));

    }

    @Test
    public void 포스트_findAll_컨트롤러_테스트() throws Exception, UserNotFoundException {
        //given
        String url = "/api/post/posts";
        String title = "title";
        String content = "content";

        long userId = getUserEntity();

        createPostTest(title, content, 10, userId);
        createPostTest(title, content, 20, userId);

        //when
        // 설정한 내용 바탕으로 요청 전송
        ResultActions resultActions = mockMvc.perform(get(url)
            .accept(MediaType.APPLICATION_JSON));

        //then
        System.out.println("resultActions = " +
            resultActions.andReturn().getResponse().getContentAsString());

        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data[0].content").value(content))
            .andExpect(jsonPath("$.data[0].title").value(title));

    }

    @Test
    public void 포스트_수정_컨트롤러_테스트() throws Exception, UserNotFoundException {
        //given
        String url = "/api/post/{id}";
        String title = "title";
        String content = "content";

        long userId = getUserEntity();

        Post postTest1 = createPostTest(title, content, 10, userId);

        UpdatePostRequest updatePostRequest = new UpdatePostRequest();
        updatePostRequest.setTitle("게시글 타이틀 수정수정");
        updatePostRequest.setContent("게시글 내용 수정수정");
        updatePostRequest.setCtgId(30);


        // 객체 JSON 으로 직렬화
        String requestBody = objectMapper.writeValueAsString(updatePostRequest);

        //when
        // 설정한 내용 바탕으로 요청 전송
        ResultActions resultActions = mockMvc.perform(put(url, postTest1.getId())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody));


        //then
        System.out.println("resultActions = "
            + resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8));
        resultActions
            .andExpect(status().isOk());

    }

    @Test
    public void 포스트_삭제_컨트롤러_테스트() throws Exception, UserNotFoundException {
        //given
        String url = "/api/post/{id}";
        String title = "title";
        String content = "content";

        long userId = getUserEntity();

        AddPostRequest userRequest = new AddPostRequest();
        userRequest.setUserId(userId);
        userRequest.setTitle(title);
        userRequest.setContent(content);
        userRequest.setCtgId(10);

        Post savedPost = createPostTest(title, content, 10, userId);

        //when
        // 설정한 내용 바탕으로 요청 전송
        ResultActions resultActions = mockMvc.perform(delete(url, savedPost.getId()));


        //then
        System.out.println("resultActions = " + resultActions.andReturn().getResponse().getContentAsString());
        resultActions
            .andExpect(status().isOk());

        List<PostEntity> posts = postRepository.findAll();
        Assertions.assertThat(posts).isEmpty();
    }

    private long getUserEntity() {
        UserDTO userDTO = UserDTO.builder()
            .name("김크앙")
            .email("email22@email.com")
            .phone("01011111111")
            .password("12134")
            .roleId(10)
            .gradeId(20)
            .build();

        return userService.signUp(userDTO);
    }

    private Post createPostTest(String title, String content, int ctgId, long userId) throws UserNotFoundException {
        AddPostRequest addPostRequest1 = new AddPostRequest();
        addPostRequest1.setTitle(title);
        addPostRequest1.setContent(content);
        addPostRequest1.setCtgId(ctgId);
        addPostRequest1.setUserId(userId);
        return postService.createPost(addPostRequest1);
    }
}