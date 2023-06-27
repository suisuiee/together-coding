package com.example.myspringbootproject.domain.post.mapper;

import com.example.myspringbootproject.domain.post.dto.AddPostRequest;
import com.example.myspringbootproject.domain.post.dto.Post;
import com.example.myspringbootproject.domain.post.model.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target="userId", source="postEntity.userEntity.id")
    Post toDTO(PostEntity postEntity);

    PostEntity toEntity(Post post);

    // @Mapping(target="userEntity", source="request.userId")
    PostEntity toEntity(AddPostRequest request);

}
