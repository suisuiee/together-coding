package com.example.myspringbootproject.domain.post.service;

import com.example.myspringbootproject.common.exception.UserNotFoundException;
import com.example.myspringbootproject.domain.post.dto.AddPostRequest;
import com.example.myspringbootproject.domain.post.dto.Post;
import com.example.myspringbootproject.domain.post.model.PostEntity;
import com.example.myspringbootproject.domain.user.dto.UserDTO;
import com.example.myspringbootproject.domain.user.repository.UserRepository;
import com.example.myspringbootproject.domain.user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class PostServiceImplTest {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        userRepository.deleteAll();
    }

    @Test
    public void 게시글_생성() throws UserNotFoundException {
        //given
        AddPostRequest addPostRequest = new AddPostRequest();
        addPostRequest.setTitle("게시글 테스트 타이틀");
        addPostRequest.setContent("게시글 테스트 컨텐츠");
        addPostRequest.setCtgId(10);

        long userId = getUserEntity();
        addPostRequest.setUserId(userId);

        //when
        Post post = postService.createPost(addPostRequest);
        System.out.println("post = " + post);

        //then
        assertThat(post).isNotNull();
        assertEquals(addPostRequest.getTitle(), post.getTitle(), "게시글은 타이틀은 같아야 한다.");
        assertEquals(addPostRequest.getContent(), post.getContent(), "게시글의 컨텐츠는 같아야 한다.");
        assertEquals(10, post.getCtgId(), "게시글의 카테고리는 같아야 한다.");
        assertEquals(1, post.getId(), "게시글의 유저 아이디는 같아야 한다.");

    }

    @Test
    public void 여러개_글쓰기_테스트() throws UserNotFoundException {
        //given
        AddPostRequest addPostRequest1 = new AddPostRequest();
        addPostRequest1.setTitle("게시글 테스트 타이틀");
        addPostRequest1.setContent("게시글 테스트 컨텐츠");
        addPostRequest1.setCtgId(10);

        AddPostRequest addPostRequest2 = new AddPostRequest();
        addPostRequest2.setTitle("게시글 테스트 타이틀2");
        addPostRequest2.setContent("게시글 테스트 컨텐츠2");
        addPostRequest2.setCtgId(20);

        long userId = getUserEntity();
        addPostRequest1.setUserId(userId);
        addPostRequest2.setUserId(userId);

        //when
        Post post1 = postService.createPost(addPostRequest1);

        Post post2 = postService.createPost(addPostRequest2);

        System.out.println("userRepository.getById(userId).getPosts() = " + userRepository.getById(userId).getPosts());
        userRepository.getById(userId).getPosts();

        //then

        assertThat(post1).isNotNull();
        assertEquals(addPostRequest1.getTitle(), post1.getTitle(), "게시글은 타이틀은 같아야 한다.");
        assertEquals(addPostRequest1.getContent(), post1.getContent(), "게시글의 컨텐츠는 같아야 한다.");
        assertEquals(10, post1.getCtgId(), "게시글의 카테고리는 같아야 한다.");
        assertEquals(1, post1.getUserId(), "게시글의 사용자 아이디는 같아야 한다.");

        assertThat(post2).isNotNull();
        assertEquals(addPostRequest2.getTitle(), post2.getTitle(), "게시글은 타이틀은 같아야 한다.");
        assertEquals(addPostRequest2.getContent(), post2.getContent(), "게시글의 컨텐츠는 같아야 한다.");
        assertEquals(20, post2.getCtgId(), "게시글의 카테고리는 같아야 한다.");
        assertEquals(1, post2.getUserId(), "게시글의 사용자 아이디는 같아야 한다.");
    }

    @Test
    public void 게시글_id로_찾기() throws Exception, UserNotFoundException {
        //given
        AddPostRequest addPostRequest1 = new AddPostRequest();
        addPostRequest1.setTitle("게시글 테스트 타이틀");
        addPostRequest1.setContent("게시글 테스트 컨텐츠");
        addPostRequest1.setCtgId(10);

        long userId = getUserEntity();

        addPostRequest1.setUserId(userId);

        Post post1 = postService.createPost(addPostRequest1);
        Long keywordId = post1.getId();

        //when
        Post findPost = postService.findPostById(keywordId);
        System.out.println("findPost = " + findPost.toString());

        // then
        Assertions.assertThat(findPost).isNotNull();
        Assertions.assertThat(findPost.getId()).isEqualTo(keywordId);
    }


    @Test()
    public void 유효하지_않은_유저_글쓰기() throws UserNotFoundException {
        //given
        AddPostRequest addPostRequest = new AddPostRequest();
        addPostRequest.setTitle("게시글 테스트 타이틀");
        addPostRequest.setContent("게시글 테스트 컨텐츠");
        addPostRequest.setCtgId(10);
        addPostRequest.setUserId(100);

        //then
        assertThrows(UserNotFoundException.class,
            () -> postService.createPost(addPostRequest));
    }

    @Test
    public void 포스트_목록_가저오기() throws Exception, UserNotFoundException {

        //given
        AddPostRequest addPostRequest1 = new AddPostRequest();
        addPostRequest1.setTitle("게시글 테스트 타이틀");
        addPostRequest1.setContent("게시글 테스트 컨텐츠");
        addPostRequest1.setCtgId(10);

        AddPostRequest addPostRequest2 = new AddPostRequest();
        addPostRequest2.setTitle("게시글 테스트 타이틀2");
        addPostRequest2.setContent("게시글 테스트 컨텐츠2");
        addPostRequest2.setCtgId(20);

        long userId = getUserEntity();
        addPostRequest1.setUserId(userId);

        addPostRequest2.setUserId(userId);
        Post post1 = postService.createPost(addPostRequest1);
        Post post2 = postService.createPost(addPostRequest2);

        //when
        List<Post> posts = postService.findAll();
        System.out.println("posts = " + posts);

        //then
        assertThat(posts).isNotNull();

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
}