package com.example.myspringbootproject.domain.post.repository;

import com.example.myspringbootproject.domain.post.dto.AddPostRequest;
import com.example.myspringbootproject.domain.post.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
